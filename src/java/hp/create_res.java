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
public class create_res extends HttpServlet {

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
            
            float y=1;
             HttpSession se = request.getSession(true);
            se.setMaxInactiveInterval(600);
            String name = request.getParameter("n_ad");
             String mob = request.getParameter("mob");
              String reg = request.getParameter("res_ad");
               String city = request.getParameter("city");
                String email = request.getParameter("e_ad");
                 String address = request.getParameter("address");
                  String pass = request.getParameter("pass");
            String cpass = request.getParameter("cpass");
             String locality = request.getParameter("locality");
            
            String item1 = request.getParameter("select1");
            String item2 = request.getParameter("select2");
           
            String item3 = request.getParameter("select3");
           
            String item4 = request.getParameter("select4");
          
            String item5 = request.getParameter("select5");
           
            String item6 = request.getParameter("select6");
            
            String item7 = request.getParameter("select7");
            
            String item8 = request.getParameter("select8");
           
           
               Class.forName("com.mysql.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_order","root","tushar99");
       if(cpass.equals(pass)){
       int x=0;
       x++;
           String sql ="insert into restrurent_det values('"+name+"','"+reg+"','"+email+"','"+mob+"','"+address+"','"+city+"','"+pass+"','"+locality+"')";
           PreparedStatement pst1 = con.prepareStatement(sql);
           int n = pst1.executeUpdate();
           if(n>0){}
           
           String sql1 = "insert into food_item values('"+reg+"','"+name+"','"+item1+"','"+city+"','"+locality+"','"+y+"')";
             String sql2 = "insert into food_item values('"+reg+"','"+name+"','"+item2+"','"+city+"','"+locality+"','"+y+"')";
               String sql3 = "insert into food_item values('"+reg+"','"+name+"','"+item3+"','"+city+"','"+locality+"','"+y+"')";
                 String sql4 = "insert into food_item values('"+reg+"','"+name+"','"+item4+"','"+city+"','"+locality+"','"+y+"')";
                   String sql5 = "insert into food_item values('"+reg+"','"+name+"','"+item5+"','"+city+"','"+locality+"','"+y+"')";
                     String sql6 = "insert into food_item values('"+reg+"','"+name+"','"+item6+"','"+city+"','"+locality+"','"+y+"')";
                       String sql7 = "insert into food_item values('"+reg+"','"+name+"','"+item7+"','"+city+"','"+locality+"','"+y+"')";
                   String sql8 = "insert into food_item values('"+reg+"','"+name+"','"+item8+"','"+city+"','"+locality+"','"+y+"')";
                 
                PreparedStatement pst = con.prepareStatement(sql1);
                pst.addBatch(sql1);
                pst.addBatch(sql2);
                  pst.addBatch(sql3);
                    pst.addBatch(sql4);  pst.addBatch(sql5);
                      pst.addBatch(sql6);
                        pst.addBatch(sql7);  pst.addBatch(sql8);
                    int z[] = pst.executeBatch();
                   
                  for(int i=0;i<8;i++){
                   if(z[i]>0){
                 x++;
                   }}
                  if(x>0){
                 
                      se.setAttribute("name", name);
                      se.setAttribute("id", reg);
                      response.sendRedirect("pricefill.jsp");
                 //  out.println("<center>YOU ARE SUCCESSFULLY REGISTER</center>");
    //  out.println("<center><a href='pricefill.jsp'>>>PRESS HERE TO GO TO FILL PRICE OF EACH ITEM>></a></center>");
                  }
                   }else
      {
      out.println("<center>PASSWORD AND CONFIRM PASSWORD NOT MATCH</center>");
      out.println("<center><a href='admin_work.jsp'>>>PRESS HERE TO GO BACK<<</a></center>");
      }
        }
        catch(Exception e){out.print(e);
             out.println("<center>YOU ARE SUCCESSFULLY REGISTER</center>");
      out.println("<center><a href='index.jsp'>>>PRESS HERE TO GO BACK MAIN PAGE>></a></center>");
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
