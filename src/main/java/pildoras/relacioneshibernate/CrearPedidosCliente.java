package pildoras.relacioneshibernate;

import java.util.GregorianCalendar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pildoras.relacioneshibernate_videos053_065.Cliente;
import pildoras.relacioneshibernate_videos053_065.DetallesCliente;
import pildoras.relacioneshibernate_videos053_065.Pedido;

public class CrearPedidosCliente {

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
            
            // Obtener el cliente Id x de la tabla cliente
            Cliente miCliente = miSession.get(Cliente.class, 4);
            
            // Crear pedidos del cliente
            // Pide por constructor una fecha: año, mes y día
            Pedido pedido1  = new Pedido(new GregorianCalendar(2024,6,4));
            Pedido pedido2  = new Pedido(new GregorianCalendar(2022,1,4));
            Pedido pedido3  = new Pedido(new GregorianCalendar(2021,6,12));
            
            // agregar pedidos creados al cliente creado
            miCliente.agregarPedidos(pedido1);
            miCliente.agregarPedidos(pedido2);
            miCliente.agregarPedidos(pedido3);
            
            // guardar los pedidos en la BBDD
            miSession.save(pedido1);
            miSession.save(pedido2);
            miSession.save(pedido3);
            

            miSession.getTransaction().commit();

            System.out.println("\nPedido insertado correctamente\n");
        } catch (Exception e) {
            // Captura cualquier excepción que pueda ocurrir durante la transacción
            e.printStackTrace();
            System.out.println("\nError al insertar el registro\n");
        } finally {
            miSession.close();
            miSessionFactory.close();
        }

    }

}
