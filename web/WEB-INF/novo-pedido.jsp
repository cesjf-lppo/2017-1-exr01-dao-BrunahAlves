<%-- 
    Document   : novo-pedido
    Created on : 12/05/2017, 21:47:10
    Author     : alunoces
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Novo pedido</title>
    </head>
    <body>
        <%@include file="JSPF/Menu.jspf" %>
        <h1>Novo Pedido</h1>
        <div style="color: red;">${mensagem}</div>
        <form method="post">
            <div><label> Pedido <input type="text" name="pedido" placeholder="Digite o pedido" /></label></div>
            <div><label> Dono: <input type="text" name="dono" placeholder="Digite o dono" /></label></div>
            <div><label> Valor: <input type="text" name="valor" placeholder="Digite o valor" /></label></div>
            <div><label> Nome: <input type="text" name="nome" placeholder="Digite o nome" /></label></div>
            <div><input type="submit" /></div>   
        </form>
    </body>
</html>