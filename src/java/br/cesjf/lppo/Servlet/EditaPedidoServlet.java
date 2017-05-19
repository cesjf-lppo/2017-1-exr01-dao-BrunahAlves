/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cesjf.lppo.Servlet;

import br.cesjf.lppo.DAO.PedidoDAO;
import br.cesjf.lppo.Pedido;
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
 * @author alunoces
 */
@WebServlet(name = "EditaPedidoServlet", urlPatterns = {"/Editar.html"})
public class EditaPedidoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            PedidoDAO dao = new PedidoDAO();
            Pedido pedido = dao.getById(id);
            if (pedido == null){
                
                System.out.println(id+"teste");
            }
            request.setAttribute("pedido", pedido); 
           
            request.getRequestDispatcher("WEB-INF/editar-pedido.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            response.sendRedirect("listar.html");
        } catch (Exception ex) {
            Logger.getLogger(EditaPedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("listar.html");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            PedidoDAO dao = new PedidoDAO();
            Pedido pedido = dao.getById(id);
            System.out.println(pedido.getDono());

            pedido.setPedido(Integer.parseInt(request.getParameter("pedido")));
            pedido.setDono(request.getParameter("dono"));
            pedido.setValor(Float.parseFloat(request.getParameter("valor")));
            pedido.setNome(request.getParameter("nome"));
          
dao.atualiza(pedido);
            response.sendRedirect("listar.html");
          

        } catch (NumberFormatException e) {
            response.sendRedirect("listar.html");
        } catch (Exception ex) {
            Logger.getLogger(EditaPedidoServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("listar.html");
        }
    }

}
