<%@page import="sistema.devgo.java.Funcionario"%>
<%@page import="sistema.devgo.Model.dao.FuncionarioDAO"%>
<!DOCTYPE html> 
<%@ page import="sistema.devgo.java.Cliente" %>
<%@ page import="sistema.devgo.Model.dao.ClienteDAO" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="CSS/listaFuncionarios.css" type="text/css" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script type="text/javascript" src="SCRIPT/funcoes.js"></script>
        <title>Clientes Cadastrados</title>
    </head>
    <body>
        <header>
            <div class="logo">
                <img src="<c:url value="/IMG/DevGo.png"/>" />
            </div>
            <div class="logout">
                <h3><a href="Login">Sair</a></h3>
            </div>
            <div class="bar">
                <ul id="menu">
                    <li class="dropdown">
                        <a href="#" class="btn">Cadastros</a>
                        <div class="dropdown-content">
                            <a href="CadastroCliente">Cliente</a>
                            <a href="CadastroFuncionario">Funcionario</a>
                        </div>
                    </li>
                    <li><a href="CadastroProduto">Produtos e Serviços</a><li>
                    <li id="relatorio"><a href="RelatorioCliente">Relatorios</a></li>
                    <li><a href="BuscaCNPJvenda">Vendas</a></li>
                </ul>
            </div>
        </header>
        <input type="hidden" id="departamento" value="<c:out value="${departamento}"/>" />
        <input type="hidden" id="mens" value="<c:out value="${msgm}"/>" />
        <input type="hidden" id="act" value="" />
        <input type="hidden" id="req" value="" />
        <aside>
            <ul>
                <li><a href="RelatorioCliente">Listar Clientes</a></li>
                <li><a href="#">Listar Funcionários</a></li>
                <li><a href="RelatorioVenda">Listar Vendas</a></li>
            </ul>
        </aside>
        <%
            FuncionarioDAO dao = new FuncionarioDAO();
            List<Funcionario> userList = dao.findFuncionarioDep();
            Iterator<Funcionario> itr = userList.iterator();
            Funcionario utilisateur = null;
        %>
        <form>
            <table id="exTable" class="table">
                <tr>
                    <th>CPF</th>
                    <th>Nome</th>
                    <th>Sobrenome</th>
                    <th>Telefone</th>
                    <th>Status</th>
                    <th>Departamento</th>
                </tr>
                <tr>
                    <%
                        while (itr.hasNext()) {
                            utilisateur = itr.next();
                    %>
                    <td><%= utilisateur.getCpf()%></td>
                    <td><%= utilisateur.getNome()%></td>
                    <td><%= utilisateur.getSobrenome()%></td>
                    <td><%= utilisateur.getTelefone()%></td>
                    <td><%= utilisateur.getStatus()%></td>
                    <td><%= utilisateur.getDepartamento()%></td>

                </tr>
                <%
                    }
                %>	
            </table>
        </form>
        <input type="button" value="Gerar Relatório" id="exportar" onclick="exportToExcel('exTable')"/>
    </body>
</html>
