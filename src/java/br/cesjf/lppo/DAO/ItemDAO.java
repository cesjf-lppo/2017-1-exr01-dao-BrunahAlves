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
    private final PreparedStatement opListarDono;
    private final PreparedStatement opBuscaPorItemPedido;
    private final PreparedStatement opAtualiza;
    private final PreparedStatement opBuscaPorItens;
    
    public ItemDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opListarPedido = conexao.prepareStatement("SELECT pedido, SUM(valor) as valorTotal FROM Item GROUP BY pedido");
        opListarDono = conexao.prepareStatement("SELECT dono, SUM(valor) as valorTotal FROM Item GROUP BY dono");
        opBuscaPorItemPedido = conexao.prepareStatement("SELECT * FROM Item WHERE pedido = ?");
        opAtualiza = conexao.prepareStatement("UPDATE Item SET Pedido = ?, Dono = ?, Valor=?, Nome=? WHERE id = ?");
        opBuscaPorItens = conexao.prepareStatement("SELECT * FROM Item WHERE id = ?");
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
    
    public List<Item> listByItensPedido(Integer pedido) throws Exception {
        try {
            List<Item> itenspedido = new ArrayList<>();
            
            
            opBuscaPorItemPedido.setInt(1, pedido);
            ResultSet resultado = opBuscaPorItemPedido.executeQuery();

            while (resultado.next()) {
                Item itemPedido = new Item();

                itemPedido.setId(resultado.getLong("id"));
                itemPedido.setPedido(resultado.getInt("pedido"));
                itemPedido.setDono(resultado.getString("dono"));
                itemPedido.setValor(resultado.getFloat("valor"));
                itemPedido.setNome(resultado.getString("nome"));
                itemPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
                itenspedido.add(itemPedido);
            }

            return itenspedido;

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos no banco!", ex);
        }
    } 
    
    public List<Item> listByDono() throws Exception {
        try {
            List<Item> pedidosDono = new ArrayList<>();

            ResultSet resultado = opListarDono.executeQuery();

            while (resultado.next()) {
                Item novoItem = new Item();

                novoItem.setDono(resultado.getString("dono"));
                novoItem.setValor(resultado.getFloat("valorTotal"));
                pedidosDono.add(novoItem);
            }

            return pedidosDono;

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos no banco!", ex);
        }
    }
        public Item getByItens(Long id) throws Exception {
        try {
            Item item = null;
            opBuscaPorItens.clearParameters();
            opBuscaPorItens.setLong(1, id);

            ResultSet resultado = opBuscaPorItens.executeQuery();

            if (resultado.next()) {
                item = new Item();

                item.setId(resultado.getLong("id"));
                item.setPedido(resultado.getInt("pedido"));
                item.setDono(resultado.getString("dono"));
                item.setValor(resultado.getFloat("valor"));
                item.setNome(resultado.getString("nome"));
                item.setAtualizacao(resultado.getTimestamp("atualizacao"));
            }
            return item;

        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar os pedidos no banco!", ex);
        }
    }

    public void atualiza(Item item) throws Exception {
        try {
            opAtualiza.clearParameters();
            opAtualiza.setInt(1, item.getPedido());
            opAtualiza.setString(2, item.getDono());
            opAtualiza.setFloat(3, item.getValor());
            opAtualiza.setString(4, item.getNome());
            opAtualiza.setLong(5, item.getId());
            opAtualiza.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Erro atualizar Item!", ex);
        }
    }

}
