/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class i_offer extends HttpServlet {

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
             DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//get current date time with Date()
Calendar cal = Calendar.getInstance();
//out.println(dateFormat.format(cal.getTime()));
            
          int k =0;
              HttpSession se = request.getSession(true);
            se.setMaxInactiveInterval(600);
            String off = (String)se.getAttribute("off");
            String name = (String)se.getAttribute("name");
          String id = (String)se.getAttribute("id");
             String l = (String)se.getAttribute("locality");
          String c = (String)se.getAttribute("city");
            String off1 = request.getParameter("t1");
            String off2 = request.getParameter("t2");
             String food = request.getParameter("select1");
             String size = request.getParameter("select3");
              String day = request.getParameter("select2");
             Class.forName("com.mysql.jdbc.Driver");
	  Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_order","root","tushar99");
	
          if(off.equals("SPECIAL")){
         
              String sql = "insert into offer_s values('"+id+"','"+name+"','"+food+"','"+size+"','"+off1+"','"+off2+"','"+dateFormat.format(cal.getTime())+"','"+k+"','"+c+"','"+l+"') ";
              PreparedStatement pst = con.prepareStatement(sql);
              int x = pst.executeUpdate();
              if(x>0)
              {
              response.sendRedirect("welcome_res.jsp");
              }
              
        }else
          {
          
           String sql = "insert into offer_day values('"+id+"','"+name+"','"+food+"','"+size+"','"+off1+"','"+off2+"','"+day+"','"+k+"','"+c+"','"+l+"') ";
              PreparedStatement pst = con.prepareStatement(sql);
              int x = pst.executeUpdate();
              if(x>0)
              {
              response.sendRedirect("welcome_res.jsp");
              }
          
          }
            
            
            
        }catch(Exception e){out.print(e);}
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
