<%@page import="sistema.devgo.java.Livro"%>
<%@page import="sistema.devgo.Model.dao.LivroDAO"%>
<%@page import="sistema.devgo.java.Plano"%>
<%@page import="sistema.devgo.Model.dao.PlanoDAO"%>
<%@page import="sistema.devgo.java.Venda"%>
<%@page import="sistema.devgo.Model.dao.VendaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html> 
<%@ page import="sistema.devgo.java.Cliente" %>
<%@ page import="sistema.devgo.Model.dao.ClienteDAO" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="CSS/ApresentacaoVenda.css" type="text/css" />
        <c:url value="CSS/ApresentacaoVenda.css" var="ApresentacaoVenda."/>
        <link type="text/css" rel="stylesheet" href="${Vendas}"/>
        <script type="text/javascript" src="SCRIPT/funcoes.js"></script>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Vendas efetudas</title>


    </head>
    <input type="hidden" id="departamento" value="<c:out value="${departamento}"/>" />
    <input type="hidden" id="mens" value="<c:out value="${msgm}"/>" />
    <input type="hidden" id="act" value="" />
    <input type="hidden" id="req" value="" />
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
                    <li><a href="RelatorioCliente">Relatorios</a></li>
                    <li id="vendas"><a href="Venda">Vendas</a></li>
                </ul>
            </div>
        </header>
        <input type="hidden" id="departamento" value="<c:out value="${departamento}"/>" />
        <c:url value="ApresentacaoVenda" var="ApresentacaoVenda" />
        <form action="${ApresentacaoVenda}" method="post" enctype="application/x-www-form-urlencoded">

            <h1>Venda finalizada com sucesso</h1>

            <%
                VendaDAO dao = new VendaDAO();
                List<Venda> venda = dao.findVenda();
                Iterator<Venda> itrVenda = venda.iterator();
                Venda vendas = null;

            %>
            <table id="exTable" class="table">
                <tr>
                    <th>Registro da venda</th>
                    <th>Cliente</th>
                    <th>Plano</th>
                    <th>Idioma</th>
                    <th>Quantidade de alunos</th>
                    <th>Valor total</th>


                </tr>
                <tr>
                    <%while (itrVenda.hasNext()) {
                            vendas = itrVenda.next();
                    %>
                    <td><%= vendas.getCodVenda()%></td>
                    <td><%= vendas.getRazao()%></td>
                    <td><%= vendas.getPlano()%></td>
                    <td><%= vendas.getIdioma()%></td>
                    <td><%= vendas.getQuantidadeAluno()%></td>
                    <td><%= vendas.getValorVenda()%></td>
                </tr>
                <%
                    }
                %>	
            </table>
            <div class="botoes">
                <input id="VoltarPesquisa" title="VoltarPesquisa" value="Nova Venda" type="submit">
            </div>
        </form>

    </body>
</html>
