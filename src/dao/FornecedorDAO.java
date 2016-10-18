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
import model.Fornecedor;
import org.dom4j.DocumentException;

/**
 *
 * @author sala308b
 */
public class FornecedorDAO implements IDao{
    Conexao conexao = null;

    @Override
    public boolean incluir(Object o) {
        boolean retorno = false;
        try {
            conexao = new Conexao(); //cria uma nova instancia de conexao.
            StringBuilder sb = new StringBuilder();

            sb.append("insert into fornecedor(nome,cnpj,cep,endereco,");
            sb.append("bairro,cidade,uf) ");
            sb.append("values(?,?,?,?,?,?,? ) ");

            String sql = sb.toString();

            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Fornecedor forn = (Fornecedor) o;

            //Pegando as propriedades do objeto.
            //E atribuindo aos parametros.
            stmt.setString(0, forn.getNome());
            stmt.setString(1, forn.getCnpj());
            stmt.setString(2, forn.getCep());
            stmt.setString(3, forn.getEndereco());
            stmt.setString(4, forn.getBairro());
            stmt.setString(5, forn.getCidade());
            stmt.setString(6, forn.getUf());
            

            retorno = stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } catch (DocumentException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexao.Fechar();
        }
        return retorno;
    }

    @Override
    public boolean excluir(Object o) {

        boolean retorno = false;

        try {
            conexao = new Conexao();
            String sql = "delete from fornecedores where codigo = ? ";
            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Fornecedor forn = (Fornecedor) o;

            stmt.setInt(0, forn.getCodigo());

            retorno = stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } catch (DocumentException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexao.Fechar();
        }

        return retorno;
    }

    @Override
    public boolean update(Object o) {

        boolean retorno = false;

        try {

            StringBuilder sb = new StringBuilder();
            sb.append("update fornecedor ");
            sb.append("set nome =  ?, cnpj = ?, cep = ?, endereco = ? ");
            sb.append(" bairro = ?, cidade = ?, uf = ? ");
            sb.append(" where codigo = ? ");

            Fornecedor forn = (Fornecedor) o;

            String sql = sb.toString();

            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            //Pegando as propriedades do objeto.
            //E atribuindo aos parametros.
            stmt.setString(0, forn.getNome());
            stmt.setString(1, forn.getCnpj());
            stmt.setString(2, forn.getCep());
            stmt.setString(3, forn.getEndereco());
            stmt.setString(4, forn.getBairro());
            stmt.setString(5, forn.getCidade());
            stmt.setString(6, forn.getUf());
            stmt.setInt(7, forn.getCodigo());

            retorno = stmt.execute();

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        } finally {
            conexao.Fechar();
        }

        return retorno;
    }

    @Override
    public ArrayList<Object> listar() {

        List<Object> lista = new ArrayList<>();

        try {

            conexao = new Conexao();

            String sql = "select * from fornecedores ";
            Statement stmt = conexao.getCon().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Fornecedor fornecedor = null;

            while (rs.next()) {
                fornecedor = new Fornecedor();

                fornecedor.setCodigo(rs.getInt("codigo"));
                fornecedor.setNome(rs.getString("nome"));
                fornecedor.setCnpj(rs.getString("cpf"));
                fornecedor.setCep(rs.getString("cep"));
                fornecedor.setEndereco(rs.getString("endereco"));
                fornecedor.setBairro(rs.getString("bairro"));
                fornecedor.setCidade(rs.getString("cidade"));
                fornecedor.setUf(rs.getString("uf"));
                

                lista.add((Object) fornecedor);

            }
            return (ArrayList<Object>) lista;

        } catch (SQLException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            
            conexao.Fechar();
        }

        return (ArrayList<Object>) lista;
    }

    @Override
    public Object exibir(Object o) {
     
        Object fornecedor = null;
        
        try {
            conexao = new Conexao();
            
            String sql = "select * from fornecedores where codigo = ? ";
            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Fornecedor forn = (Fornecedor) o;
            
            stmt.setInt(0, forn.getCodigo());
            
            fornecedor = (Object) stmt.executeQuery();
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(FornecedorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexao.Fechar();
        }
         
        return fornecedor;
    }

    
}
