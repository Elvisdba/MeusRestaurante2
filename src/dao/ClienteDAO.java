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
import model.Cliente;
import org.dom4j.DocumentException;

/**
 *
 * @author sala308b
 */
public class ClienteDAO implements IDao {

    Conexao conexao = null;

    @Override
    public boolean incluir(Object o) {
        boolean retorno = false;

        try {
            conexao = new Conexao();
            StringBuilder sb = new StringBuilder();

            sb.append(" insert into clientes( nome,cpf,cep,endereco, ");
            sb.append(" bairro,cidade,uf, receber_promocoes, sexo, estado_civil ) ");
            sb.append(" values (?,?,?,?,?,?,?,?,?,? ) ");

            String sql = sb.toString();

            try (PreparedStatement stmt = conexao.getCon().prepareStatement(sql)) {
                Cliente cli = (Cliente) o;
                
                //Pegando as propriedades do objeto.
                //E atribuindo aos parametros.
                stmt.setString(1, cli.getNome());
                stmt.setString(2, cli.getCpf());
                stmt.setString(3, cli.getCep());
                stmt.setString(4, cli.getEndereco());
                stmt.setString(5, cli.getBairro());
                stmt.setString(6, cli.getCidade());
                stmt.setString(7, cli.getUf());
                stmt.setBoolean(8, cli.isReceberPromocoes());
                stmt.setString(9, cli.getSexo());
                stmt.setString(10, cli.getEstadoCivil());
                
                retorno = stmt.executeUpdate() > 0;
            }

        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            //retorno = false;
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
            String sql = "delete from clientes where codigo = ? ";
            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Cliente cli = (Cliente) o;

            stmt.setInt(1, cli.getCodigo());

            retorno = stmt.executeUpdate() > 0;

        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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

            conexao = new Conexao();
            StringBuilder sb = new StringBuilder();
            sb.append("update clientes ");
            sb.append("set nome =  ?, cpf = ?, cep = ?, endereco = ?, ");
            sb.append(" bairro = ?, cidade = ?, uf = ?, receber_promocoes = ?, ");
            sb.append(" sexo = ?, estado_civil = ?  ");

            sb.append(" where codigo = ? ");

            Cliente cli = (Cliente) o;

            String sql = sb.toString();

            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            //Pegando as propriedades do objeto.
            //E atribuindo aos parametros.
            stmt.setString(1, cli.getNome());
            stmt.setString(2, cli.getCpf());
            stmt.setString(3, cli.getCep());
            stmt.setString(4, cli.getEndereco());
            stmt.setString(5, cli.getBairro());
            stmt.setString(6, cli.getCidade());
            stmt.setString(7, cli.getUf());
            stmt.setBoolean(8, cli.isReceberPromocoes());
            stmt.setString(9, cli.getSexo());
            stmt.setString(10, cli.getEstadoCivil());

            stmt.setInt(11, cli.getCodigo());

            retorno = stmt.executeUpdate() >= 0;

        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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

            String sql = "select * from clientes ";
            Statement stmt = conexao.getCon().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Cliente cliente;

            while (rs.next()) {
                cliente = new Cliente();

                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setCep(rs.getString("cep"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setUf(rs.getString("uf"));
                cliente.setReceberPromocoes(rs.getBoolean("receber_promocoes"));
                cliente.setSexo(rs.getString("sexo"));
                cliente.setEstadoCivil(rs.getString("estado_civil"));

                lista.add((Object) cliente);

            }
            return (ArrayList<Object>) lista;

        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
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

        Cliente cliente = null;

        try {
            conexao = new Conexao();

            String sql = "select * from clientes where codigo = ? ";
            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Cliente cli = (Cliente) o;

            stmt.setInt(1, cli.getCodigo());

            ResultSet rs = stmt.executeQuery();
            rs.first();

            cliente = new Cliente();
            cliente.setCodigo(rs.getInt("codigo"));
            cliente.setNome(rs.getString("nome"));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setCep(rs.getString("cep"));
            cliente.setEndereco(rs.getString("endereco"));
            cliente.setBairro(rs.getString("bairro"));
            cliente.setCidade(rs.getString("cidade"));
            cliente.setUf(rs.getString("uf"));
            cliente.setSexo(rs.getString("sexo"));
            cliente.setEstadoCivil(rs.getString("estado_civil"));
            cliente.setReceberPromocoes(rs.getBoolean("receber_promocoes"));

        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexao != null) {
                conexao.Fechar();
                conexao = null;
            }
        }

        return cliente;
    }

    public Cliente pesquisarPorNome(String nome) {

        Cliente cliente = null;

        try {

            conexao = new Conexao();
            String sql = "select * from clientes where nome like ?";
            ResultSet rs;
            try (PreparedStatement stmt = conexao.getCon().prepareStatement(sql)) {
                stmt.setString(1, nome.concat("%"));
                rs = stmt.executeQuery();
                rs.first();
                cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCep(rs.getString("cep"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setReceberPromocoes(rs.getBoolean("receber_promocoes"));
                cliente.setEstadoCivil(rs.getString("estado_civil"));
                cliente.setSexo(rs.getString("sexo"));
            }
            rs.close();

        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexao != null) {
                conexao.Fechar();
                conexao = null;
            }
        }

        return cliente;
    }

    public Cliente pesquisarPorCpf(String cpf) {
        Cliente cliente = null;

        try {

            conexao = new Conexao();
            String sql = "select * from clientes where cpf = ?";
            ResultSet rs;
            try (PreparedStatement stmt = conexao.getCon().prepareStatement(sql)) {
                stmt.setString(1, cpf);
                rs = stmt.executeQuery();
                rs.first();
                cliente = new Cliente();
                cliente.setCodigo(rs.getInt("codigo"));
                cliente.setNome(rs.getString("nome"));
                cliente.setCep(rs.getString("cep"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setEndereco(rs.getString("endereco"));
                cliente.setBairro(rs.getString("bairro"));
                cliente.setCidade(rs.getString("cidade"));
                cliente.setReceberPromocoes(rs.getBoolean("receber_promocoes"));
                cliente.setEstadoCivil(rs.getString("estado_civil"));
                cliente.setSexo(rs.getString("sexo"));
            }
            rs.close();

        } catch (SQLException | DocumentException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (conexao != null) {
                conexao.Fechar();
                conexao = null;
            }
        }

        return cliente;
    }

}
