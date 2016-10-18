/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import com.mysql.jdbc.MySQLConnection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 *
 * @author sala308b
 */
public class Conexao {

    private MySQLConnection con;
    private String driver, url, dbuser, dbpass;

    public Conexao() throws DocumentException {
        getConfig();
    }

    private void getConfig() throws DocumentException {
        SAXReader reader = new SAXReader();
        Document doc = reader.read("src/config/config.xml");

        org.dom4j.Element root = doc.getRootElement();
        Element elementRoot = doc.getRootElement();

        elementRoot.elements().stream().map((o) -> {
            this.driver = ((Element) o ).element("driver").getText() ;
            return o;
        }).map((o) -> {
            this.url = ((Element) o ).element("url").getText();
            return o;
        }).map((o) -> {
            this.dbuser = ((Element) o ).element("dbuser").getText();
            return o;
        }).forEach((o) -> {
            this.dbpass = ((Element) o ).element("dbpass").getText();
        });
    }

    public MySQLConnection getCon() {

        try {
            Class.forName(this.driver);
            con = (MySQLConnection) DriverManager.getConnection(this.url, this.dbuser, this.dbpass);

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return con;

    }

    public void Fechar() {
        try {
            if (this.con != null) {
                this.con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
