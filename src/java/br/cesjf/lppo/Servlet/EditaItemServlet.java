/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lppo.Servlet;

import br.cesjf.lppo.DAO.ItemDAO;
import br.cesjf.lppo.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruna Alves
 */
@WebServlet(name = "EditaItemServlet", urlPatterns = {"/Edita.html"})
public class EditaItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            ItemDAO dao = new ItemDAO();
            Item item = dao.getByItens(id);

            request.setAttribute("item", item);

            request.getRequestDispatcher("WEB-INF/editar-item.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("listarItemPedido.html");
        } catch (Exception ex) {
            Logger.getLogger(EditaItemServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("listarItemPedido.html");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Long id = Long.parseLong(request.getParameter("id"));

            ItemDAO dao = new ItemDAO();
            Item pedido = dao.getByItens(id);
            System.out.println(pedido.getDono());

            pedido.setPedido(Integer.parseInt(request.getParameter("pedido")));
            pedido.setDono(request.getParameter("dono"));
            pedido.setValor(Float.parseFloat(request.getParameter("valor")));
            pedido.setNome(request.getParameter("nome"));

            dao.atualiza(pedido);
            response.sendRedirect("listarItemPedido.html?pedido=" + pedido.getPedido());

        } catch (NumberFormatException e) {
            response.sendRedirect("listarItemPedido.html");
        } catch (Exception ex) {
            Logger.getLogger(EditaItemServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("listarItemPedido.html");
        }
    }

}
