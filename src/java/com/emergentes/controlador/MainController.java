package com.emergentes.controlador;

import com.emergentes.modelo.post;
import com.emergentes.utiles.conexionBD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String op;
        op = (request.getParameter("op") != null) ? request.getParameter("op") : "list";
        
        ArrayList<post> lista = new ArrayList<post>();
        
        conexionBD canal = new conexionBD();
        Connection conn = canal.conectar();
        
        PreparedStatement ps;
        ResultSet rs;
        
        if (op.equals("list")) {
            try {
                String sql = "SELECT * FROM post ORDER BY fecha DESC";
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                
                while(rs.next()){
                    post pt = new post();
                    pt.setId_usuario(rs.getInt("id_usuario"));
                    pt.setTitulo(rs.getString("titulo"));
                    pt.setDescripcion(rs.getString("descripcion"));
                    pt.setFecha(rs.getString("fecha"));
                    
                    lista.add(pt);
                }
                
                request.setAttribute("lista", lista);
                request.getRequestDispatcher("panel.jsp").forward(request, response);
            } catch (SQLException ex) {
                System.out.println("error en sql "+ex.getMessage());
            }finally{
                canal.desconectar();
            }
        }
        if (op.equals("nuevo")) {
            post newPost = new post();
            request.setAttribute("post", newPost);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        if (op.equals("editar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id_usuario"));
                String sql = "select * from post where id_usuario = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                
                rs = ps.executeQuery();
                
                post po = new post();
                while(rs.next()){
                    po.setId_usuario(rs.getInt("id_usuario"));
                    po.setTitulo(rs.getString("titulo"));
                    po.setDescripcion(rs.getString("descripcion"));
                    po.setFecha(rs.getString("fecha"));
                }
                request.setAttribute("lista", po);
                request.getRequestDispatcher("editar.jsp").forward(request, response);
                
            } catch (SQLException ex) {
                System.out.println("Error en sql "+ex.getMessage());
            }
            
            
        }
        if (op.equals("eliminar")) {
            try {
                int id = Integer.parseInt(request.getParameter("id_usuario"));
                String sql = "delete from post where id_usuario = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, id);
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error de sql "+ex.getMessage());
            }finally{
                canal.desconectar();
            }
            response.sendRedirect("MainController");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id_usuario"));
        String titulo = request.getParameter("titulo");
        String descripcion = request.getParameter("descripcion");
        String fecha = request.getParameter("fecha");
        
        post pst = new post();
        pst.setId_usuario(id);
        pst.setTitulo(titulo);
        pst.setDescripcion(descripcion);
        pst.setFecha(fecha);
        
        conexionBD canal = new conexionBD();
        Connection conn  = canal.conectar();
        
        PreparedStatement ps;
        ResultSet rs;
        
        if (id == 0) {
            
                String sql = "insert into post(id_usuario, titulo, descripcion, fecha) values (?,?,?,?)";
            try {
                ps = conn.prepareStatement(sql);
                ps.setInt(1, pst.getId_usuario());
                ps.setString(2, pst.getTitulo());
                ps.setString(3, pst.getDescripcion());
                ps.setString(4, pst.getFecha());
                
                ps.executeUpdate();
                
            } catch (SQLException ex) {
                System.out.println("Error de SQL "+ex.getMessage());
            } finally{
                canal.conectar();
            }
            response.sendRedirect("MainController");
        }
        //si el reg es antiguo
        else{
            try {
                String sql = "update post set fecha=?, titulo=?, descripcion=? where id=?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1, pst.getId_usuario());
                ps.setString(2, pst.getFecha());
                ps.setString(3, pst.getTitulo());
                ps.setString(4, pst.getDescripcion());
                
                ps.executeUpdate();
            } catch (SQLException ex) {
                System.out.println("Error al actualizar "+ex.getMessage());
            }
            response.sendRedirect("MainController");
        }
    }
}





































