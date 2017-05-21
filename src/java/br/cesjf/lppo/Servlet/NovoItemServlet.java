package br.cesjf.lppo.Servlet;

import br.cesjf.lppo.DAO.ItemDAO;
import br.cesjf.lppo.Item;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Bruna Alves
 */
@WebServlet(name = "NovoItemServlet", urlPatterns = {"/Novo.html"})
public class NovoItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/novo-itempedido.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Item novoItempedido = new Item();

        novoItempedido.setPedido(Integer.parseInt(request.getParameter("pedido")));
        novoItempedido.setDono(request.getParameter("dono"));
        novoItempedido.setValor(Float.parseFloat(request.getParameter("valor")));
        novoItempedido.setNome(request.getParameter("nome"));

        try {
            ItemDAO dao = new ItemDAO();
            dao.cria(novoItempedido);
        } catch (Exception ex) {
            request.setAttribute("mensagem", ex);
            request.getRequestDispatcher("WEB-INF/novo-itempedido.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("listaPedido.html");
    }

}
