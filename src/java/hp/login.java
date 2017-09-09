/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.DriverManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author PC 2
 */
@WebServlet(name = "login", urlPatterns = {"/login"})
public class login extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try{
            HttpSession se = request.getSession(true);
            se.setMaxInactiveInterval(600);
            String username = request.getParameter("TT1");
            String pass = request.getParameter("TT2");
             Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_order","root","tushar99");
	String sql ="select * from user_login where USERNAME='"+username+"' and PASSWORD = '"+pass+"'";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if(rs.next())
        {
        se.setAttribute("name1",rs.getString(1));
        se.setAttribute("user1", rs.getString(4));
        RequestDispatcher dis = request.getRequestDispatcher("/welcome.jsp");
        dis.forward(request, response);
        }
        else
        {
        se.setAttribute("id1","PLEASE LOGIN WITH VALID ACCOUNT or CHECK PASSWORD");
       response.sendRedirect("login.jsp");
        }
        }
        catch(Exception e){
        out.print(e);
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
