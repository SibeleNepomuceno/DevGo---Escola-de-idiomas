/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.devgo.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistema.devgo.Model.dao.ClienteDAO;
import sistema.devgo.java.Cliente;

/**
 *
 * @author roberto.slinhares
 */
@WebServlet(name = "BuscarCliente", urlPatterns = {"/BuscarCliente"})
public class BuscarCliente extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BuscarCliente</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuscarCliente at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String msgm = request.getParameter("msgm"); 
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/BuscarCliente.jsp");
        dispatcher.forward(request, response);
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
        
       
        String serv = "/WEB-INF/EditarCliente.jsp";
          
        Cliente cliente = new Cliente();
   
        ClienteDAO dao = new ClienteDAO ();

        String CNPJ = request.getParameter("CNPJ");
        
        try {
            
            cliente = dao.findByName(CNPJ);
            request.setAttribute("CNPJ", CNPJ);
               
        } catch (SQLException ex) {
           
        }
        
        if(cliente.getBairro()== null){
            
            request.setAttribute("msgm", "erro");
            serv = "/WEB-INF/BuscarCliente.jsp";
                
            }
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(serv);
        dispatcher.forward(request, response);
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
