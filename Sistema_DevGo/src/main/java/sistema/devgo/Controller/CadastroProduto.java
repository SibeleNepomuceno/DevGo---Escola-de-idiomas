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
import javax.servlet.http.HttpSession;
import sistema.devgo.Model.dao.LivroDAO;
import sistema.devgo.java.Livro;
import sistema.devgo.java.UsuarioSistema;

/**
 *
 * @author Natanael
 */
@WebServlet(name = "CadastroProduto", urlPatterns = {"/CadastroProduto"})
public class CadastroProduto extends HttpServlet {

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
            out.println("<title>Servlet CadastroProduto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CadastroProduto at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession sessao = httpRequest.getSession(false);
        
        Object objSessao = sessao.getAttribute("user");
        UsuarioSistema usuario = (UsuarioSistema) objSessao;
        usuario.getDepartamento();
        
        request.setAttribute("departamento", usuario.getDepartamento());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/CadastrarProduto.jsp");
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
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        HttpSession sessao = httpRequest.getSession(false);

        Object objSessao = sessao.getAttribute("user");
        UsuarioSistema usuario = (UsuarioSistema) objSessao;
        usuario.getDepartamento();
         
        // Guardando dados vindos da tela nas variaveis
        String idioma = request.getParameter("LivroIdioma");
        String preco = request.getParameter("Preco");
        String quantidade = request.getParameter("Quantidade");

        double preco1 = Double.parseDouble(request.getParameter("Preco"));
        int quantidade1 = Integer.parseInt(request.getParameter("Quantidade"));
        Livro livro = new Livro();

                
        livro.setIdioma(idioma);
        livro.setPreco(preco1);
        livro.setQuantidade(quantidade1);
        long cod =0;
        LivroDAO dao = new LivroDAO();
        try {
            cod = dao.verificaProdutoExistente(idioma);
        } catch (SQLException ex) {
            Logger.getLogger(EditarProduto.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(cod == 0){
        try {
            dao.salvar(livro);
            request.setAttribute("departamento", usuario.getDepartamento());
            request.setAttribute("msgm", "sucesso");
        } catch (SQLException ex) {           
            request.setAttribute("departamento", usuario.getDepartamento());
            request.setAttribute("msgm", "erro");
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/CadastrarProduto.jsp");
        dispatcher.forward(request, response);
        }else{
             request.setAttribute("departamento", usuario.getDepartamento());
            request.setAttribute("msgm", "erro");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/CadastrarProduto.jsp");
        dispatcher.forward(request, response); 
        }
   
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
