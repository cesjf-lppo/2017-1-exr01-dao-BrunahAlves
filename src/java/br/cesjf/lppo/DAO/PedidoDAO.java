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
    
    public PedidoDAO() throws Exception{
        Connection conexao = ConnectionFactory.createConnection();
        opListar = conexao.prepareStatement("SELECT * FROM Pedido");
    }
            
    public List<Pedido> listAll() throws Exception{
       try{
           List<Pedido> pedidos = new ArrayList<>();
           
           ResultSet resultado = opListar.executeQuery();
           
           while(resultado.next()){
               Pedido novoPedido = new Pedido();
               
               novoPedido.setId(resultado.getLong("id"));
               novoPedido.setPedido(resultado.getInt("pedido"));
               novoPedido.setDono(resultado.getString("dono"));
               novoPedido.setValor(resultado.getFloat("real"));
               novoPedido.setNome(resultado.getString("nome"));
               novoPedido.setAtualizacao(resultado.getTimestamp("atualizacao"));
               pedidos.add(novoPedido);
           }
           return pedidos;

       } catch (SQLException ex) {
            throw new Exception("Erro ao listar os pedidos no banco!", ex);
        } 
    }
            
}