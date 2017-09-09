/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class Feedback extends HttpServlet {

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
      
        try {
          
            
              HttpSession se = request.getSession(true);
              String user = (String) se.getAttribute("user");
            String food = (String) se.getAttribute("food");
            String id = (String) se.getAttribute("res_id");
             String res_name = (String) se.getAttribute("res_name");
           // int id1 = Integer.valueOf(id);
                   String u_n = (String) se.getAttribute("name");
                   
           String r1 = request.getParameter("t1");
           String r2 = request.getParameter("t2");
           String r3 = request.getParameter("t3");
           String r4 = request.getParameter("t4");
           String r5 = request.getParameter("t5");
           String coment = request.getParameter("comment");
           String improve = request.getParameter("improve");
          
           float r11 = Float.valueOf(r1);
           float r22 = Float.valueOf(r2);
           float r33 = Float.valueOf(r3);
         float r44 = Float.valueOf(r4);
         float r55 = Float.valueOf(r5);
            Class.forName("com.mysql.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_order","root","tushar99");
            float ret=0.0f;
               ret =((3*r11)+(3*r22)+(2*r33)+(r44)+(r55))/10;
              
               //out.print(ret);
               DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//get current date time with Date()
Calendar cal = Calendar.getInstance();
//out.println(dateFormat.format(cal.getTime()));
        
            
               String sql1 = "select * from feed_det where USER='"+user+"' and FOOD='"+food+"' and RES_ID='"+id+"' ";
              PreparedStatement pst = con.prepareStatement(sql1);
              ResultSet rs = pst.executeQuery();
              if(rs.next()){
              
                  String sql2 = "update feed_det set RATING='"+ret+"' where USER='"+user+"' and FOOD='"+food+"' and RES_ID='"+id+"'";
                   String sql2a = "update feed_det set COMMENT='"+coment+"' where USER='"+user+"' and FOOD='"+food+"' and RES_ID='"+id+"'";
                     String sql2b = "update feed_det set IMPROVE='"+improve+"' where USER='"+user+"' and FOOD='"+food+"' and RES_ID='"+id+"'";
                 String sql2c = "update feed_det set TIME='"+dateFormat.format(cal.getTime())+"' where USER='"+user+"' and FOOD='"+food+"' and RES_ID='"+id+"'";
                     
                     PreparedStatement pst1 = con.prepareStatement(sql2);
                  pst1.addBatch(sql2);  pst1.addBatch(sql2a);  pst1.addBatch(sql2b);pst1.addBatch(sql2c);
                  int x=0;
                  int z[] = pst1.executeBatch();
                  for(int i=0; i<=3;i++){
                      if(z[i]>0){
                      x++;}}
                  if(x>0){
                   String a= "FEEDBACK SUBMITTED SUCESSFULLY";
       se.setAttribute("ans",a);
       se.setAttribute("user",user);
       se.setAttribute("food",food);
       se.setAttribute("id",id);
       response.sendRedirect("feed_complete.jsp");
                  }
              }
              else{
                
                   String sql5 = "insert into feed_det values('"+user+"','"+food+"','"+id+"','"+improve+"','"+coment+"','"+u_n+"','"+ret+"','"+res_name+"','"+dateFormat.format(cal.getTime())+"')";
                  PreparedStatement pst5 = con.prepareStatement(sql5);
                  int x = pst5.executeUpdate();
                  if(x>0){
                   String a= "FEEDBACK SUBMITTED SUCESSFULLY";
       se.setAttribute("ans",a);
         se.setAttribute("user",user);
       se.setAttribute("food",food);
       se.setAttribute("id",id);
       response.sendRedirect("feed_complete.jsp");
                  }
              
              
             
              
                  
              
                  
              
              }
                   
              
        }
        catch(Exception e){
       out.print(e);
              out.print("<center>ANSWER OF ALL QUESTION IS COMPALSARY GO BACK AND FILL</center>");
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
