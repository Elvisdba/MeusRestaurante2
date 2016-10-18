/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author sala308b
 */
public class RestauranteTest {

    public RestauranteTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void listarClientes() {
        controller.ClientesController controller = new controller.ClientesController();
        ArrayList<Object> listaClientes = controller.listar();
        listaClientes.stream().map((o) -> (model.Cliente) o).forEach((cli) -> {
            System.out.println(cli.getNome());
        });
    }

    @Test
    public void salvarCliente() {
        controller.ClientesController c = new controller.ClientesController();
        model.Cliente cli = new model.Cliente();

        cli.setCodigo(0);
        cli.setNome("Maria Francisca");
        cli.setCpf("99888976543");
        cli.setCep("34567980");
        cli.setEndereco("dafasfafsfafasfasdfsafsa, 91");
        cli.setBairro("Sao Pesro");
        cli.setCidade("Vitória");
        cli.setUf("ES");
        cli.setEstadoCivil("Solteiro");
        cli.setSexo("Masculino");
        cli.setReceberPromocoes(true);

        if (cli.getCodigo() == 0) {
            assertTrue("Incluir cliente", c.incluir(cli));
        } else {
            assertTrue("Atualizar cliente", c.atualizar(cli));
            assertTrue("Atualizou cliente", "Maria Francisca".equals(c.exibir(cli).getNome()));
        }

    }
    
    @Test
    public void clienteExiste(){
        controller.ClientesController controller = new controller.ClientesController();
        model.Cliente cli = new model.Cliente();
        cli.setCodigo(1);
        assertNotNull("Achou um cliente?", controller.exibir(cli));
    }
    @Test
    public void RetornouUmCliente(){
        controller.ClientesController controller = new controller.ClientesController();
        model.Cliente cli = new model.Cliente();
        cli.setCodigo(1);
        //assertNotNull("Retorna um cliente?", controller.exibir(cli));
        assertEquals("Retorna um cliente?", cli.getClass(), controller.exibir(cli).getClass());
    }
    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
