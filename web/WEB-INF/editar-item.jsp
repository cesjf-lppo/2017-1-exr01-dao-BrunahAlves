<%-- 
    Document   : editar-pedido
    Created on : 15/05/2017, 21:42:08
    Author     : alunoces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar pedido</title>
    </head>
    <body>
        <%@include file="JSPF/Menu.jspf" %>
        <h1>Editar pedido!</h1>
         <div style="color: red;">${mensagem}</div>
        <form method="post" action="Edita.html">
            <input type="hidden" name="id" value="${item.id}"/>
            <div><label> Pedido <input type="text" name="pedido" placeholder="Digite o pedido" value="${item.pedido}"/></label></div>
            <div><label> Dono: <input type="text" name="dono" placeholder="Digite o dono" value="${item.dono}" /></label></div>
            <div><label> Valor: <input type="text" name="valor" placeholder="Digite o valor" value="${item.valor}"/></label></div>
            <div><label> Nome: <input type="text" name="nome" placeholder="Digite o nome" value="${item.nome}"/></label></div>
            <div><input type="submit" /></div>   
        </form>
    </body>
</html>
