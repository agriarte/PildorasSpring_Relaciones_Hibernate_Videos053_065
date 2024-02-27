package pildoras.relacioneshibernate;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pildoras.relacioneshibernate_videos053_065.Cliente;
import pildoras.relacioneshibernate_videos053_065.DetallesCliente;
import pildoras.relacioneshibernate_videos053_065.Pedido;

/*
 Obtener pedidos por HQL
*/

public class ObtenerPedidosCliente2 {

    public static void main(String[] args) {
        SessionFactory miSessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(Pedido.class)
                .addAnnotatedClass(DetallesCliente.class)
                .buildSessionFactory();
        Session miSession = miSessionFactory.openSession();

        try {

            miSession.beginTransaction();

            // me aparece Query como si estuviera obsoleto aunque funciona todo.
            Query<Cliente> consulta = miSession.createQuery("SELECT CL FROM Cliente CL JOIN FETCH CL.pedidos "
                    + "WHERE CL.id=:clienteID", Cliente.class);
            
            consulta.setParameter("clienteID", 4);
            
            Cliente miCliente = consulta.getSingleResult();

            System.out.println("\nDatos de cliente:\n" + miCliente);
            System.out.println("Pedidos:\n");

            List<Pedido> pedidos = miCliente.getPedidos();
            for (Pedido p : pedidos) {
                System.out.println(p);
            }
            miSession.getTransaction().commit();

            System.out.println("\nFin de consulta de pedidos\n");
        } catch (Exception e) {
            // Captura cualquier excepción que pueda ocurrir durante la transacción
            e.printStackTrace();
            System.out.println("\nError al consultar pedidos\n");
        } finally {
            miSession.close();
            miSessionFactory.close();
        }

    }

}
