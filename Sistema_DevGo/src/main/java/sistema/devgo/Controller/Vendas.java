/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sistema.devgo.Controller;

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import sistema.devgo.Model.dao.VendaDAO;
import sistema.devgo.java.Venda;

/**
 *
 * @author Sibele
 */
@WebServlet(name = "Vendas", urlPatterns = {"/Vendas"})
public class Vendas extends HttpServlet {

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
            out.println("<title>Servlet Vendas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Vendas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vendas.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dataVenda= request.getParameter("DataVenda");
        long cliente = Long.parseLong(request.getParameter("opcaoCliente"));
        long plano = Long.parseLong(request.getParameter("opcaoPlano"));
        double valor_venda = Double.parseDouble(request.getParameter("Valor"));
        int quantAluno = Integer.parseInt(request.getParameter("QTDE_ALUNO"));
         
        
      Date dtVenda;
        try {
            dtVenda = new SimpleDateFormat("yyyy-MM-dd").parse(dataVenda);
        } catch (ParseException ex) {
            out.println("Erro de conversão de data");
            return;
        }

        Venda venda = new Venda();
        venda.setCodCliente(cliente);
        venda.setCodPlano(plano);
        venda.setQuantidadeAluno(quantAluno);
        venda.setDataVenda(dtVenda);
        venda.setValorVenda(valor_venda);

        VendaDAO dao = new VendaDAO();
        try {
            dao.insert(venda);
        } catch (SQLException ex) {
            Logger.getLogger(Venda.class.getName()).log(Level.SEVERE, null, ex);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("");
        dispatcher.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}