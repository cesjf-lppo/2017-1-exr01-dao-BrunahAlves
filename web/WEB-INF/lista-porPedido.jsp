<%-- 
    Document   : lista-porPedido
    Created on : 19/05/2017, 18:52:35
    Author     : alunoces
--%>

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
                <th>Pedido</th>
                <th>Valor Total</th>
                </tr>
            </thead>    
            <tbody>
                <c:forEach var="pedido" items="${pedidos}">    
                <tr>
                    <td><a href="listarPedido.html?id=${pedido.pedido}">${pedido.pedido}</a></td>
                    <td>${pedido.valor}</td>
                </tr>
                </c:forEach>
            </tbody>
    </body>
</html>
