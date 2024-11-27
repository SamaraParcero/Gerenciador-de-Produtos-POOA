package br.com.ucsal.persistencia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.ucsal.model.Produto;
import br.com.ucsal.util.DatabaseUtil;

/**
 * Implementação de ProdutoRepository usando HSQLDB. Esta classe manipula os
 * dados da tabela de produtos em um banco de dados HSQLDB.
 */
public class HSQLProdutoRepository implements ProdutoRepository<Produto, Integer>{

	//Adiciona um novo produto ao banco de dados.
    @Override
    public void adicionar(Produto entidade) {
        String sql = "INSERT INTO produtos (nome, preco) VALUES (?, ?)";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, entidade.getNome());
            stmt.setDouble(2, entidade.getPreco());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entidade.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  //Remove um produto do banco de dados com base no ID.
    @Override
    public void remover(Integer id) {
        String sql = "DELETE FROM produtos WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

  //Retorna uma lista de todos os produtos do banco de dados.
    @Override
    public List<Produto> listar() {
        String sql = "SELECT * FROM produtos";
        List<Produto> produtos = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Produto produto = new Produto(
                    rs.getInt("id"),
                    rs.getString("nome"),
                    rs.getDouble("preco")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }
    
    //Atualiza as informações de um produto no banco de dados.
    @Override
    public void atualizar(Produto entidade) {
        String sql = "UPDATE produtos SET nome = ?, preco = ? WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, entidade.getNome());
            stmt.setDouble(2, entidade.getPreco());
            stmt.setInt(3, entidade.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


  //Obtém um produto pelo ID do banco de dados.
    @Override
    public Produto obterPorID(Integer id) {
        String sql = "SELECT * FROM produtos WHERE id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}


