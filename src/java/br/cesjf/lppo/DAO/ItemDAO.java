package br.cesjf.lppo.DAO;

import br.cesjf.lppo.Item;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bruna Alves
 */
public class ItemDAO {

    private final PreparedStatement opListarPedido;
    
    public ItemDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opListarPedido = conexao.prepareStatement("SELECT pedido, SUM(valor) as valorTotal FROM Item GROUP BY pedido");
        
        
    }
    
    public List<Item> listByPedido() throws Exception {
        try {
            List<Item> pedidos = new ArrayList<>();

            ResultSet resultado = opListarPedido.executeQuery();

            while (resultado.next()) {
                Item novoItem = new Item();

                novoItem.setPedido(resultado.getInt("pedido"));

                novoItem.setValor(resultado.getFloat("valorTotal"));
                pedidos.add(novoItem);
            }

            return pedidos;

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos no banco!", ex);
        }
    }
    
}
