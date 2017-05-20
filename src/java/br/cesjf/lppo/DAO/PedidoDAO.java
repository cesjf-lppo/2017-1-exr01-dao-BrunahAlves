package br.cesjf.lppo.DAO;

import br.cesjf.lppo.Itens;
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
    private final PreparedStatement opBuscaPorItens;
    private final PreparedStatement opBuscaPorPedido;
    private final PreparedStatement opBuscaPorDono;
    private final PreparedStatement opListarPedidos;

    public PedidoDAO() throws Exception {
        Connection conexao = ConnectionFactory.createConnection();
        opListar = conexao.prepareStatement("SELECT * FROM Pedido");
        opNovo = conexao.prepareStatement("INSERT INTO Pedido(Pedido, Dono, Valor, Nome) VALUES(?,?,?,?)");
        opAtualiza = conexao.prepareStatement("UPDATE Pedido SET Pedido = ?, Dono = ?, Valor=?, Nome=? WHERE id = ?");
        opBuscaPorItens = conexao.prepareStatement("SELECT * FROM Pedido WHERE id = ?");
        opBuscaPorPedido = conexao.prepareStatement("SELECT * FROM Pedido WHERE pedido = ?");
        opBuscaPorDono = conexao.prepareStatement("SELECT * FROM Pedido WHERE dono = ?");
        opListarPedidos = conexao.prepareStatement("SELECT pedido FROM Pedido GROUP BY pedido");
    }

    public List<Itens> listAll() throws Exception {
        try {
            List<Itens> pedidos = new ArrayList<>();

            ResultSet resultado = opListar.executeQuery();

            while (resultado.next()) {
                Itens novoPedido = new Itens();

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
    public List<Itens> listByPedido(Long id) throws Exception {
        try {
            List<Itens> pedidos = new ArrayList<>();

            opBuscaPorPedido.setLong(1, id);
            ResultSet resultado = opBuscaPorPedido.executeQuery();

            while (resultado.next()) {
                Itens novoPedido = new Itens();

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

    public Itens getByItens(Long id) throws Exception {
        try {
            Itens pedido = null;
            opBuscaPorItens.clearParameters();
            opBuscaPorItens.setLong(1, id);

            ResultSet resultado = opBuscaPorItens.executeQuery();

            if (resultado.next()) {
                pedido = new Itens();

                pedido.setId(resultado.getLong("id"));
                pedido.setPedido(resultado.getInt("pedido"));
                pedido.setDono(resultado.getString("dono"));
                pedido.setValor(resultado.getFloat("valor"));
                pedido.setNome(resultado.getString("nome"));
                pedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
            }
            return pedido;

        } catch (SQLException ex) {
            throw new Exception("Erro ao buscar os pedidos no banco!", ex);
        }
    }

    public void cria(Itens novoPedido) throws Exception {
        try {
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

    public void atualiza(Itens pedido) throws Exception {
        try {
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

    public Itens getByPedido(String listaPedido) throws Exception {
        
        
        return null;
    }

    public Itens getByDono(String listaDono) throws Exception {
       
        return null;
    }

    public List<Long> listPedidos(){
        List<Long> pedidos = new ArrayList();
        
        return pedidos;
    }
}
