
package br.cesjf.lppo.Servlet;

import br.cesjf.lppo.DAO.PedidoDAO;
import br.cesjf.lppo.Pedido;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alunoces
 */
@WebServlet(name = "NovoPedidoServlet", urlPatterns = {"/novo.html"})
public class NovoPedidoServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/novo-pedido.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Pedido novoPedido = new Pedido();
        
        novoPedido.setPedido(Integer.parseInt(request.getParameter("pedido")));
        novoPedido.setDono(request.getParameter("dono"));
        novoPedido.setValor(Float.parseFloat(request.getParameter("valor")));
        novoPedido.setNome(request.getParameter("nome"));
        
        try {
            PedidoDAO dao = new PedidoDAO();
            dao.cria(novoPedido);
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex);
            request.getRequestDispatcher("WEB-INF/novo-pedido.jsp").forward(request, response);
            return;
        }
        
        response.sendRedirect("listar.html");
    }

}