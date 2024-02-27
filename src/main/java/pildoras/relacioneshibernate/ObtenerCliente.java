package pildoras.relacioneshibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pildoras.relacioneshibernate_videos053_065.Cliente;
import pildoras.relacioneshibernate_videos053_065.DetallesCliente;

/**
 * Clase que buscando en "detalles_cliente" mostrará el registro relacionado en
 * "cliente" Relación Bidireccional
 */
public class ObtenerCliente {

    public static void main(String[] args) {
        SessionFactory miSessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .buildSessionFactory();
        Session miSession = miSessionFactory.openSession();

        try {
            miSession.beginTransaction();

            // Obtener objeto de DetallesCliente con ID 1
            DetallesCliente detallesCliente = miSession.get(DetallesCliente.class, 1);
            System.out.println("\nMostrando el registro: " + detallesCliente.getId()+ "\n" );
            System.out.println(detallesCliente);
            System.out.println(detallesCliente.getElCliente());
            

            miSession.getTransaction().commit();

        } catch (Exception e) {
            // Captura cualquier excepción que pueda ocurrir durante la transacción
            e.printStackTrace();
            System.out.println("\nError al mostrar el registro\n");
        } finally {
            miSession.close();
            miSessionFactory.close();
        }

    }
}
