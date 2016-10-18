/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Estoque;
import model.Produto;
import org.dom4j.DocumentException;

/**
 *
 * @author sala308b
 */
public class EstoqueDAO implements IDao {

    Conexao conexao = null;

    @Override
    public boolean incluir(Object o) {
        boolean retorno = false;
        try {
            conexao = new Conexao(); //cria uma nova instancia de conexao.
            StringBuilder sb = new StringBuilder();

            sb.append("insert into estoque(produto_codigo,qtde_min,qtde_max,qtde_atual ");
            sb.append("values(?,?,?,? ) ");

            String sql = sb.toString();

            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Estoque est = (Estoque) o;

            //Pegando as propriedades do objeto.
            //E atribuindo aos parametros.
            stmt.setInt(0, est.getProduto().getCodigo());
            stmt.setInt(1, est.getQtdeMinima());
            stmt.setInt(2, est.getQtdeMaxima());
            stmt.setInt(3, est.getQtdeAtual());

            retorno = stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } catch (DocumentException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexao != null) {
                conexao.Fechar();
                conexao = null;
            }
        }
        return retorno;
    }

    @Override
    public boolean excluir(Object o) {

        boolean retorno = false;

        try {
            conexao = new Conexao();
            String sql = "delete from estoque where codigo = ? ";
            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Estoque est = (Estoque) o;

            stmt.setInt(0, est.getCodigo());

            retorno = stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } catch (DocumentException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexao != null) {
                conexao.Fechar();
                conexao = null;
            }
        }

        return retorno;
    }

    @Override
    public boolean update(Object o) {

        boolean retorno = false;

        try {

            StringBuilder sb = new StringBuilder();
            sb.append("update estoque ");
            sb.append("set produto_codigo =  ?, qtde_min = ?, qtde_max = ?, qtde_atual = ? ");
            sb.append(" where codigo = ? ");

            Estoque est = (Estoque) o;

            String sql = sb.toString();

            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            //Pegando as propriedades do objeto.
            //E atribuindo aos parametros.
            stmt.setInt(0, est.getProduto().getCodigo());
            stmt.setInt(1, est.getQtdeMinima());
            stmt.setInt(2, est.getQtdeMaxima());
            stmt.setInt(3, est.getQtdeAtual());

            retorno = stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } finally {
            if (conexao != null) {
                conexao.Fechar();
                conexao = null;
            }
        }

        return retorno;
    }

    @Override
    public ArrayList<Object> listar() {

        List<Object> lista = new ArrayList<>();

        try {

            conexao = new Conexao();

            String sql = "select * from estoque ";
            Statement stmt = conexao.getCon().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Estoque estoque;
            Produto produto;

            while (rs.next()) {
                estoque = new Estoque();
                produto = new Produto();
                produto.setCodigo(rs.getInt("produto_codigo"));

                estoque.setProduto(produto);
                estoque.setQtdeMinima(rs.getInt("qtde_minima"));
                estoque.setQtdeMaxima(rs.getInt("qtde_maxima"));
                estoque.setQtdeAtual(rs.getInt("qtde_atual"));

                lista.add((Object) estoque);

            }
            return (ArrayList<Object>) lista;

        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            if (conexao != null) {
                conexao.Fechar();
                conexao = null;
            }
        }

        return (ArrayList<Object>) lista;
    }

    @Override
    public Object exibir(Object o) {

        Object estoque = null;

        try {
            conexao = new Conexao();

            String sql = "select * from estoque where codigo = ? ";
            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Estoque est = (Estoque) o;

            stmt.setInt(0, est.getCodigo());

            estoque = (Object) stmt.executeQuery();

        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(EstoqueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexao != null) {
                conexao.Fechar();
                conexao = null;
            }
        }

        return estoque;
    }

}
