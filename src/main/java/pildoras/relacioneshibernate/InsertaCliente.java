package pildoras.relacioneshibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pildoras.relacioneshibernate_videos053_065.Cliente;
import pildoras.relacioneshibernate_videos053_065.DetallesCliente;
import pildoras.relacioneshibernate_videos053_065.Pedido;

public class InsertaCliente {

    public static void main(String[] args) {
        SessionFactory miSessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .addAnnotatedClass(DetallesCliente.class)
                .addAnnotatedClass(Pedido.class)
                .buildSessionFactory();
        Session miSession = miSessionFactory.openSession();

        try {
            Cliente cliente1 = new Cliente("Pedro", "Marín", "calle Bilbao");
            DetallesCliente detallesCliente1 = new DetallesCliente("www.pildorasinformaticas.com", "444-444-444", "Un segundo registro en 2 tablas");

            //IMPORTANTE Asociar los obietos
            cliente1.setDetallesCliente(detallesCliente1);

            miSession.beginTransaction();

            // al estar las 2 tablas relacionadas, al guardar cliente1 se guardará automáticamente "detallesCliente1"
            miSession.save(cliente1);
            miSession.getTransaction().commit();

            System.out.println("\nRegistro insertado correctamente\n");
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
