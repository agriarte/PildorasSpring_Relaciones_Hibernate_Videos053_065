package pildoras.relacioneshibernate_videos053_065;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import javax.persistence.*;

/**
 *
 *
 */
@Entity
@Table(name = "pedido")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "FECHA")
    private GregorianCalendar fecha;

    @Column(name = "FORMA_PAGO")
    private String formaPago;

    //anotación uno a varios.
    //comportamientos de cascada, se ponen todos menos el REMOVE para no eliminar en cascada
    //la tabla de cliente se relaciona con esta tabla a través de este campo con un tipo de ralación "Varios a Uno"
    //La parte pedido es el "varios" y la parte "uno" es el cliente debido a que un cliente puede tener varios pedidos pero no al revés.
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "CLIENTE_ID")
    private Cliente cliente;

    //constructor por defecto( es necesario porque si falta da error
    public Pedido() {
    }

    // constructor que obliga a indicar una fecha
    public Pedido(GregorianCalendar fecha) {
        this.fecha = fecha;
    }

    // se crean getters y setters solo de Cliente y forma de pago.
    // de Fecha no hace falta porque ese dato se incluye en el constructor.
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        // simplifico el campo fecha para que solo muestre año,mes,dia, hora, minuto
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String fechaSimple = dateFormat.format(fecha.getTime());

        return "Pedido{"
                + "id=" + id
                + ", fecha=" + fechaSimple
                + ", formaPago=" + formaPago
                + ", cliente=" + cliente
                + '}';
    }

}
