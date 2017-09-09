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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hp
 */
public class pricefill extends HttpServlet {
double k=0;
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
           String food = request.getParameter("select4");
            String size1 = request.getParameter("select1");
            String size2 = request.getParameter("select2");
            String size3 = request.getParameter("select3");
            
             String price1 = request.getParameter("pc1");
            String price2 = request.getParameter("pc2");
           
            String price3 = request.getParameter("pc3");
            
            HttpSession se = request.getSession(true);
     String name =      (String) se.getAttribute("n_res");
       String reg =      (String) se.getAttribute("n_id");      
               Class.forName("com.mysql.jdbc.Driver");
               Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/food_order","root","tushar99");
           String sql1 = "insert into price_list values('"+reg+"','"+name+"','"+food+"','"+size1+"','"+price1+"','"+k+"')";
            String sql2 = "insert into price_list values('"+reg+"','"+name+"','"+food+"','"+size2+"','"+price2+"','"+k+"')";
       
            String sql3 = "insert into price_list values('"+reg+"','"+name+"','"+food+"','"+size3+"','"+price3+"','"+k+"')";
         
         PreparedStatement pst = con.prepareStatement(sql1);
         pst.addBatch(sql1);
          pst.addBatch(sql2);
           pst.addBatch(sql3);
           int x=0;
           int z[]  = pst.executeBatch();
           for(int i=0;i<3;i++)
           {
           x++;
           }
            if(x>0)
            {
             se.setAttribute("name", name);
                      se.setAttribute("id", reg);
                      response.sendRedirect("pricefill.jsp");
            }
        }
         catch(Exception e){
        out.print(e);
         response.sendRedirect("pricefill.jsp");
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
