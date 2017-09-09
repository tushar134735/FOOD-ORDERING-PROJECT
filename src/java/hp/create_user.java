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
@WebServlet(name = "create_user", urlPatterns = {"/create_user"})
public class create_user extends HttpServlet {

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
        PrintWriter out = response.getWriter();
        try{
           HttpSession se = request.getSession(true);
            se.setMaxInactiveInterval(600);
            String name = request.getParameter("name");
             String mob = request.getParameter("mob");
              String dob = request.getParameter("dob");
               String username = request.getParameter("user");
                String email = request.getParameter("email");
                 String address = request.getParameter("address");
                  String pass = request.getParameter("ps");
            String cpass = request.getParameter("cps");
             Class.forName("com.mysql.jdbc.Driver");
           //  out.println(name+"  "+mob+"  "+dob+"  "+username+" "+email+"  "+" "+address+"  "+pass+" "+" "+cpass);
	 if(username.equals("")|| name.equals("")||email.equals("")){
         out.println("<center>PLEASE MUST FILL USERNAME or NAME or EMAIL</center>");
     out.println("<center><a href='create_user.jsp'>>>PRESS HERE TO GO BACK>></a></center>");
        }
         else{
                 Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_order","root","tushar99");
      if(cpass.equals(pass)){
          String sql ="insert into user_login values('"+name+"','"+email+"','"+mob+"','"+username+"','"+address+"','"+pass+"','"+dob+"')";
     PreparedStatement pst =con.prepareStatement(sql);
     int x = pst.executeUpdate();
     if(x>0)
     {
     out.println("<center>YOU ARE SUCCESSFULLY REGISTER</center>");
      out.println("<center><a href='index.jsp'>>>PRESS HERE TO GO BACK MAIN PAGE>></a></center>");
     }
      
      }else
      {
      out.println("<center>PASSWORD AND CONFIRM PASSWORD NOT MATCH</center>");
      out.println("<center><a href='create_user.jsp'>>>PRESS HERE TO GO BACK>></a></center>");
      }
        }}
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
