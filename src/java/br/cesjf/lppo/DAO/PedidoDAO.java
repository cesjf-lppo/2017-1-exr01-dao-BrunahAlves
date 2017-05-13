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

    public PedidoDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opListar = conexao.prepareStatement("SELECT * FROM Pedido");
        opNovo = conexao.prepareStatement("INSERT INTO Pedido(Pedido, Dono, Valor, Nome) VALUES(?,?,?,?)");
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

    public void cria(Pedido novoPedido) throws Exception {
        try{
            opNovo.clearParameters();
            opNovo.setInt(1, novoPedido.getPedido());
            opNovo.setString(2, novoPedido.getDono());
            opNovo.setFloat(3, novoPedido.getValor());
            opNovo.setString(4, novoPedido.getNome());
            opNovo.executeUpdate();
        } catch (SQLException ex) {
            throw new Exception("Erro ao inserir novo Pedido", ex);
        }
    }
}
