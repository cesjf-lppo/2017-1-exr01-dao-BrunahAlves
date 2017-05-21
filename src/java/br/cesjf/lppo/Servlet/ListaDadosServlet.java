package br.cesjf.lppo.Servlet;

import br.cesjf.lppo.DAO.ItemDAO;
import br.cesjf.lppo.Item;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
@WebServlet(name = "ListaDadosServlet", urlPatterns = {"/listaPedido.html", "/listarItemPedido.html", "/listaDono.html"})
public class ListaDadosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getRequestURI().contains("/listaPedido.html")) {
            List<Item> pedidos;

            try {
                ItemDAO dao = new ItemDAO();
                pedidos = dao.listByPedido();

            } catch (Exception ex) {
                Logger.getLogger(ListaDadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                pedidos = new ArrayList<>();
                request.setAttribute("mensagem", ex.getLocalizedMessage());
            }

            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("WEB-INF/lista-porPedido.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("/listarItemPedido.html")) {

            List<Item> Itenspedido;

            try {
                ItemDAO dao = new ItemDAO();
                int id = Integer.parseInt(request.getParameter("pedido"));
                Itenspedido = dao.listByItensPedido(id);
            } catch (Exception ex) {
                Logger.getLogger(ListaDadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                Itenspedido = new ArrayList<>();
                request.setAttribute("mensagem", ex.getLocalizedMessage());
            }

            request.setAttribute("Itenspedido", Itenspedido);
            request.getRequestDispatcher("WEB-INF/lista-itensPorPedido.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("/listaDono.html")) {
            List<Item> pedidos;

            try {
                ItemDAO dao = new ItemDAO();
                pedidos = dao.listByDono();

            } catch (Exception ex) {
                Logger.getLogger(ListaDadosServlet.class.getName()).log(Level.SEVERE, null, ex);
                pedidos = new ArrayList<>();
                request.setAttribute("mensagem", ex.getLocalizedMessage());
            }

            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("WEB-INF/lista-porDono.jsp").forward(request, response);
        }
    }
}
