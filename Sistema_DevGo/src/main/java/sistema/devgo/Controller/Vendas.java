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
import javax.servlet.http.HttpSession;
import sistema.devgo.Model.dao.ClienteDAO;
import sistema.devgo.Model.dao.LivroDAO;
import sistema.devgo.Model.dao.PlanoDAO;
import sistema.devgo.Model.dao.VendaDAO;
import sistema.devgo.java.Cliente;
import sistema.devgo.java.Livro;
import sistema.devgo.java.Plano;
import sistema.devgo.java.UsuarioSistema;
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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession sessao = httpRequest.getSession(false);

        Object objSessao = sessao.getAttribute("user");
        UsuarioSistema usuario = (UsuarioSistema) objSessao;
        usuario.getDepartamento();

        request.setAttribute("departamento", usuario.getDepartamento());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/Vendas.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession sessao = httpRequest.getSession(false);

        Object objSessao = sessao.getAttribute("user");
        UsuarioSistema usuario = (UsuarioSistema) objSessao;
        usuario.getDepartamento();

        request.setAttribute("departamento", usuario.getDepartamento());
        PlanoDAO planodao = new PlanoDAO();
        LivroDAO livrodao = new LivroDAO();

        long codCliente = Long.parseLong(request.getParameter("Id"));//Pega Id do cliente
        long codIdioma = Long.parseLong(request.getParameter("opcaoIdioma")); // pega ID
        long codPlano = Long.parseLong(request.getParameter("opcaoPlano"));// pega ID
        int quantAluno = Integer.parseInt(request.getParameter("QTDE_ALUNO"));

        Plano modeloPlano = null;
        try {
            modeloPlano = planodao.trasPlano(codPlano);
        } catch (SQLException ex) {
            Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        Livro modelolivro = null;
        try {
            modelolivro = livrodao.trasLivro(codIdioma);
        } catch (SQLException ex) {
            Logger.getLogger(Vendas.class.getName()).log(Level.SEVERE, null, ex);
        }

        double resultado1 = quantAluno * modelolivro.getPreco();
        double resultado2 = resultado1 + modeloPlano.getPreco();
        double valor_venda = resultado2;

        Venda venda = new Venda();
        venda.setCodPlano(codPlano);
        venda.setCodCliente(codCliente);
        venda.setCodIdioma(codIdioma);
        venda.setQuantidadeAluno(quantAluno);
        venda.setValorVenda(valor_venda);

        VendaDAO dao = new VendaDAO();

        Livro livroQuant = null;

        try {
            livroQuant = livrodao.verificaQuantidadeLivros(codIdioma);

        } catch (SQLException ex) {
            if (quantAluno > modelolivro.getQuantidade() || quantAluno == 0 ) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/QuantLivroIndisponivel.jsp");
                dispatcher.forward(request, response);
            }

        }
        if (quantAluno <= modelolivro.getQuantidade() && quantAluno > 0 ) {
            try {
                dao.insert(venda);
                request.setAttribute("departamento", usuario.getDepartamento());
                response.sendRedirect("ApresentacaoVenda");
            } catch (SQLException ex) {
                request.setAttribute("departamento", usuario.getDepartamento());
                request.setAttribute("msgm", "erro");
                response.sendRedirect("ApresentacaoVenda");
            }

            response.setContentType("text/html;charset=UTF-8");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
