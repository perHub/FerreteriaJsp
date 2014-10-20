/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DAO.*;
import Modelo.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Peri
 */
public class NewEmptyJUnitTest {
    
    public NewEmptyJUnitTest() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
     public void hello() throws CantidadInsuficienteException {
         PropertyConfigurator.configure("log4j.properties");
         DAOProducto dProd = new DAOProducto();
         DAOCompra dComp = new DAOCompra();
         DAOPedido dPedido = new DAOPedido();
         

;         
//         Compra c1 = dComp.read(1);
//         Compra c2 = dComp.read(4);
//         Compra c3 = dComp.read(5);
         
         Pedido p = dPedido.read(1);
         
         Map a = p.obtenerProductos();
         
   
 
         
         
         System.out.println("fu");
     }
}
