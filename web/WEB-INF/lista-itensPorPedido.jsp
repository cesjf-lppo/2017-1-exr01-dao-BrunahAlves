<%-- 
    Document   : lista-porPedido
    Created on : 21/05/2017, 11:32:53
    Author     : Bruna Alves
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="JSPF/Menu.jspf" %>
        <h1>Lista de Pedidos</h1>
        <div style="color: red;">${mensagem}</div>
        <table>
            <thead>
                <tr>
                <th>Id</th>
                <th>Pedido</th>
                <th>Dono</th>
                <th>Valor</th>
                <th>Nome</th>
                <th>Atualização</th>
                <th>Editar Itens</th>
                </tr>
            </thead>    
            <tbody>
                <c:forEach var="Itenspedido" items="${Itenspedido}">    
                <tr>
                    <td>${Itenspedido.id}</td>
                    <td>${Itenspedido.pedido}</td>
                    <td>${Itenspedido.dono}</td>
                    <td>${Itenspedido.valor}</td>
                    <td>${Itenspedido.nome}</td>
                    <td>${Itenspedido.atualizacao}</td>
                    <td><a href="Edita.html?id=${Itenspedido.id}">---X---</a></td>
                </tr>
                </c:forEach>
            </tbody>
    </body>
</html>
