/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cse.maven_webmail.storage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author lee-yejin
 */
@WebServlet(name = "Delete", urlPatterns = {"/Delete"})
public class Delete extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
             /*
            String className = loadDBConfig.getInstance().getDriver();
            String url = loadDBConfig.getInstance().getUrl();
            String User = loadDBConfig.getInstance().getId();
            String Password = loadDBConfig.getInstance().getPw();
            */
           final String JdbcDriver = "com.mysql.cj.jdbc.Driver";
            final String JdbcUrl = "jdbc:mysql://113.198.235.233:20002/webmail?serverTimezone=Asia/Seoul";
            final String User = "team7";
            final String Password = "1234";
            
            try{
                String idx = request.getParameter("idx");
                
                /*
                Class.forName(loadDBConfig.getInstance().getDriver());
                Connection conn = DriverManager.getConnection(url, User, Password);
                */
                Class.forName(JdbcDriver);
                Connection conn = DriverManager.getConnection(JdbcUrl,User,Password);
                String sql = "DELETE FROM save WHERE idx='" + idx + "';" ;
                
                PreparedStatement pstmt = conn.prepareStatement(sql);
                request.setCharacterEncoding("UTF-8");
               
                if (!(idx == null) && !idx.equals("")) {
                    pstmt.executeUpdate();
                }
                
                pstmt.close();
                conn.close();
                response.sendRedirect("storage_list.jsp");
                 
            } catch (Exception ex) {
               out.println("오류가 발생했습니다. (발생 오류: " + ex.getMessage() + ")");
            } finally{
                out.close();
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
