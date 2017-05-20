package br.cesjf.lppo.Servlet;

import br.cesjf.lppo.DAO.PedidoDAO;
import br.cesjf.lppo.Itens;
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
 * @author alunoces
 */
@WebServlet(name = "ListaPedidosServlet", urlPatterns = {"/listarItens.html", "/listarPedido.html", "/listarDono.html"})
public class ListaPedidosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getRequestURI().contains("/listarItens.html")) {

            List<Itens> pedidos;

            try {
                PedidoDAO dao = new PedidoDAO();
               pedidos = dao.listPedidos();
            } catch (Exception ex) {
                Logger.getLogger(ListaPedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
                pedidos = new ArrayList<>();
                request.setAttribute("mensagem", ex.getLocalizedMessage());
            }

            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("WEB-INF/lista-itens.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("/listarPedido.html")) {

            List<Itens> pedidos;

            try {
                PedidoDAO dao = new PedidoDAO();
                pedidos = dao.listAll();
            } catch (Exception ex) {
                Logger.getLogger(ListaPedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
                pedidos = new ArrayList<>();
                request.setAttribute("mensagem", ex.getLocalizedMessage());
            }

            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("WEB-INF/lista-porPedido.jsp").forward(request, response);
        } else if (request.getRequestURI().contains("/listarDono.html")) {

            List<Itens> pedidos;

            try {
                PedidoDAO dao = new PedidoDAO();
                pedidos = dao.listAll();
            } catch (Exception ex) {
                Logger.getLogger(ListaPedidosServlet.class.getName()).log(Level.SEVERE, null, ex);
                pedidos = new ArrayList<>();
                request.setAttribute("mensagem", ex.getLocalizedMessage());
            }

            request.setAttribute("pedidos", pedidos);
            request.getRequestDispatcher("WEB-INF/lista-porDono.jsp").forward(request, response);
        }
    }
}
