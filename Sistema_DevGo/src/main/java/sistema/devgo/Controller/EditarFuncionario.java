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
import sistema.devgo.Model.dao.FuncionarioDAO;
import sistema.devgo.Model.dao.PermissaoDAO;
import sistema.devgo.java.Funcionario;
import sistema.devgo.java.Permissao;
import sistema.devgo.java.UsuarioSistema;

/**
 *
 * @author Natanael
 */
@WebServlet(name = "EditarFuncionario", urlPatterns = {"/EditarFuncionario"})
public class EditarFuncionario extends HttpServlet {

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
            out.println("<title>Servlet EditarFuncionario</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditarFuncionario at " + request.getContextPath() + "</h1>");
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
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/EditarFuncionario.jsp");
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
         // Guardando dados vindos da tela nas variaveis
        String nome = request.getParameter("Nome");
        String sobrenome = request.getParameter("Sobrenome");
        String cpf = request.getParameter("CPF");
        long departamento = Long.parseLong(request.getParameter("opcao"));
        String telefone = request.getParameter("Telefone");
        String dataNasc = request.getParameter("Datanasc");
        String usuario = request.getParameter("Usuario");
        String senha = request.getParameter("Senha");
        String status = request.getParameter("status");
        PermissaoDAO dao2 = new PermissaoDAO();
        UsuarioSistema user = new UsuarioSistema(usuario, senha, departamento);
        String senhagerada = String.valueOf(user.getHashSenha());

        Date dtNasc;
        try {
            dtNasc = new SimpleDateFormat("yyyy-MM-dd").parse(dataNasc);
        } catch (ParseException ex) {
            out.println("Erro de conversão de data");
            return;
        }
        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setSobrenome(sobrenome);
        funcionario.setTelefone(telefone);
        funcionario.setCpf(cpf);
        funcionario.setCodDepartamento(departamento);
        funcionario.setDtNascimento(dtNasc);
        funcionario.setStatus(status);

        FuncionarioDAO dao = new FuncionarioDAO();

        try {
            dao.update(funcionario);
            
            Permissao p = new Permissao();
            try {

                long id = dao2.buscarId();

                p.setUsuario(usuario);
                p.setSenha(senhagerada);
                p.setCod_funcionario(id);

                dao2.update(p);
                request.setAttribute("msgm", "sucesso");
            } catch (SQLException ex) {
            }

            RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/BuscarFuncionario.jsp");
            dispatcher.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(EditarFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
