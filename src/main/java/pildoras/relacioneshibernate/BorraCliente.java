package pildoras.relacioneshibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pildoras.relacioneshibernate_videos053_065.Cliente;

public class BorraCliente {

    public static void main(String[] args) {
        SessionFactory miSessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Cliente.class)
                .buildSessionFactory();
        Session miSession = miSessionFactory.openSession();

        try {
            miSession.beginTransaction();

            // Obtener objeto de Cliente con el registro del Id indicado
            Cliente elCliente = miSession.get(Cliente.class, 5);

            if (elCliente != null) {
                // Eliminar registro. Gracias a la anotación de cascada y la relación entre tablas se borra también el registro de detalles_cliente
                miSession.delete(elCliente);

                miSession.getTransaction().commit();

                System.out.println("\nRegistro eliminado correctamente\n");
            } else {
                System.out.println("\nNo se encontró el registro con el Id indicado. Ningún registro fue eliminado.\n");
            }
        } catch (Exception e) {
            // Captura cualquier excepción que pueda ocurrir durante la transacción
            e.printStackTrace();
            System.out.println("\nError al eliminar el registro\n");
        } finally {
            miSession.close();
            miSessionFactory.close();
        }
    }
}
