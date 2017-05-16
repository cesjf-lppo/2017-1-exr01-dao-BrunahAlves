package br.cesjf.lppo.DAO;

import br.cesjf.lppo.Pedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    private final PreparedStatement opListar;
    private final PreparedStatement opNovo;
    private final PreparedStatement opAtualiza;
    private final PreparedStatement opBuscaPorId;

    public PedidoDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opListar = conexao.prepareStatement("SELECT * FROM Pedido");
        opNovo = conexao.prepareStatement("INSERT INTO Pedido(Pedido, Dono, Valor, Nome) VALUES(?,?,?,?)");
        opAtualiza = conexao.prepareStatement("UPDATE Pedido SET Pedido = ?, Dono = ?, Valor=?, Nome=? WHERE id = ?");
        opBuscaPorId = conexao.prepareStatement("SELECT * FROM Pedido WHERE id = ?");
    }

    public List<Pedido> listAll() throws Exception {
        try {
            List<Pedido> pedidos = new ArrayList<>();

            ResultSet resultado = opListar.executeQuery();

            while (resultado.next()) {
                Pedido novoPedido = new Pedido();

                novoPedido.setId(resultado.getLong("id"));
                novoPedido.setPedido(resultado.getInt("pedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getFloat("valor"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
                pedidos.add(novoPedido);
            }
            return pedidos;

        } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos no banco!", ex);
        }
    }

        public Pedido getById(Long id) throws Exception {
        try {
            Pedido pedido = null;
            opBuscaPorId.clearParameters();
            opBuscaPorId.setLong(1, id);
            ResultSet resultado = opBuscaPorId.executeQuery();

            if (resultado.next()) {
                Pedido novoPedido = new Pedido();

                novoPedido.setId(resultado.getLong("id"));
                novoPedido.setPedido(resultado.getInt("pedido"));
                novoPedido.setDono(resultado.getString("dono"));
                novoPedido.setValor(resultado.getFloat("valor"));
                novoPedido.setNome(resultado.getString("nome"));
                novoPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
            }
            return pedido;

        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar os pedidos no banco!", ex);
        }
    }

    public void cria(Pedido novoPedido) throws Exception {
        try{
            opNovo.clearParameters();
            opNovo.setInt(1, novoPedido.getPedido());
            opNovo.setString(2, novoPedido.getDono());
            opNovo.setFloat(3, novoPedido.getValor());
            opNovo.setString(4, novoPedido.getNome());
            opNovo.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir novo Pedido!", ex);
        }
    }
    
     public void atualiza(Pedido pedido) throws Exception {
        try{
            opAtualiza.clearParameters();
            opAtualiza.setInt(1, pedido.getPedido());
            opAtualiza.setString(2, pedido.getDono());
            opAtualiza.setFloat(3, pedido.getValor());
            opAtualiza.setString(4, pedido.getNome());
            opAtualiza.setLong(5, pedido.getId());
            opAtualiza.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Erro atualizar Pedido!", ex);
        }
    }
}
