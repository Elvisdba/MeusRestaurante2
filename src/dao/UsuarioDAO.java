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
import model.Perfil;
import model.Usuario;
import org.dom4j.DocumentException;

/**
 *
 * @author sala308b
 */
public class UsuarioDAO implements IDao {

    Conexao conexao = null;

    @Override
    public boolean incluir(Object o) {
        boolean retorno = false;
        try {
            conexao = new Conexao(); //cria uma nova instancia de conexao.
            StringBuilder sb = new StringBuilder();

            sb.append("insert into usuarios (nome,login,senha,perfil,ativo )");
            sb.append("values(?,?,?,?,? ) ");

            String sql = sb.toString();

            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Usuario usu = (Usuario) o;

            //Pegando as propriedades do objeto.
            //E atribuindo aos parametros.
            stmt.setString(1, usu.getNome());
            stmt.setString(2, usu.getLogin());
            stmt.setString(3, usu.getSenha());
            stmt.setString(4, usu.getPerfil().getNome());
            stmt.setBoolean(5, usu.isAtivo());

            retorno = stmt.executeUpdate() > 0;
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            String sql = "delete from usuarios where codigo = ? ";
            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Usuario usu = (Usuario) o;

            stmt.setInt(1, usu.getCodigo());

            retorno = stmt.executeUpdate() > 0;
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            sb.append("update usuarios ");
            sb.append("set nome =  ?, login = ?, senha = ?, perfil = ?, ativo = ? ");
            sb.append(" where codigo = ? ");

            Usuario usu = (Usuario) o;

            String sql = sb.toString();

            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            //Pegando as propriedades do objeto.
            //E atribuindo aos parametros.
            stmt.setString(1, usu.getNome());
            stmt.setString(2, usu.getLogin());
            stmt.setString(3, usu.getSenha());
            stmt.setString(4, usu.getPerfil().getNome());
            stmt.setBoolean(5, usu.isAtivo());
            stmt.setInt(6, usu.getCodigo());

            retorno = stmt.executeUpdate() > 0;
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
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

            String sql = "select * from usuarios ";
            Statement stmt = conexao.getCon().createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            Usuario usuario = null;
            Perfil perfil = null;

            while (rs.next()) {
                usuario = new Usuario();
                perfil = new Perfil(rs.getString("perfil"));

                usuario.setCodigo(rs.getInt("codigo"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPerfil(perfil);
                usuario.setAtivo(rs.getBoolean("ativo"));

                lista.add((Object) usuario);

            }

            stmt.close();
            rs.close();

            return (ArrayList<Object>) lista;

        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {

            conexao.Fechar();
        }

        return (ArrayList<Object>) lista;
    }

    @Override
    public Object exibir(Object o) {
        Usuario usuario = null;

        try {
            conexao = new Conexao();

            String sql = "select * from usuarios where codigo = ? ";
            PreparedStatement stmt = conexao.getCon().prepareStatement(sql);
            Usuario usu = (Usuario) o;

            stmt.setInt(1, usu.getCodigo());

            ResultSet rs = stmt.executeQuery();
            rs.first();
            usuario = new model.Usuario();
            usuario.setCodigo(rs.getInt("codigo"));
            usuario.setNome(rs.getString("nome"));
            usuario.setLogin(rs.getString("login"));
            usuario.setSenha(rs.getString("senha"));
            usuario.setPerfil(new Perfil(rs.getString("perfil")));
            usuario.setAtivo(rs.getBoolean("ativo"));

            stmt.close();
            rs.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexao.Fechar();
        }

        return usuario;
    }

    public Usuario pesquisar(String tipo, String texto) {

        model.Usuario usuario = null;

        try {
            String sql;
            PreparedStatement stmt = null;
            ResultSet rs;

            switch (tipo) {
                case "nome":
                    sql = "select * from usuarios where nome like ? ";
                    stmt = conexao.getCon().prepareStatement(sql);
                    stmt.setString(1, texto.concat("%"));

                    break;
                case "codigo":
                    sql = "select * from usuarios where codigo = ? ";
                    stmt = conexao.getCon().prepareStatement(sql);
                    stmt.setInt(1, Integer.parseInt(texto));

                    break;
            }
            rs = stmt.executeQuery();

            if (rs != null) {
                rs.first();

                usuario = new model.Usuario();
                usuario.setCodigo(rs.getInt("codigo"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPerfil(new Perfil(rs.getString("perfil")));
                usuario.setAtivo(rs.getBoolean("ativo"));

                rs.close();
            }
            stmt.close();

        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberFormatException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            conexao.Fechar();
        }

        return usuario;
    }

}
