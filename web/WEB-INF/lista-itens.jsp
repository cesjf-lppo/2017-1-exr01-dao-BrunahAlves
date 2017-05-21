<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
                <th>Última atualização</th>
                </tr>
            </thead>    
            <tbody>
                <c:forEach var="pedido" items="${pedidos}">    
                <tr>
                    <td><a href="listar.html?id=${pedido.id}">${pedido.id}</a></td>
                    <td>${pedido.pedido}</a></td>
                    <td>${pedido.dono}</a></td>
                    <td>${pedido.valor}</td>
                    <td>${pedido.nome}</td>
                    <td>${pedido.atualizacao}</td>
                </tr>
                </c:forEach>
            </tbody>
    </body>
</html>