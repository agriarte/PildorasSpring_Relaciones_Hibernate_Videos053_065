package pildoras.relacioneshibernate_videos053_065;


import java.util.ArrayList;
import java.util.List;
// Ojo al import. Aunque parezca contradictorio se importa este paquete y no el org.hibernate.anotations.*
import javax.persistence.*;

/**
 * Hibernate transforma cada clase en entidades para realizar el mapeo.
 * 1 -@Entity No olvidar está anotación al princio de la clase. Indica que esta 
 * clase está mapeada a una tabla de base de datos. Es decir, están relacionados 
 * los campos de la clase con las columnas de la tabla
 * 2- Un atributo por cada columna de la tabla 
 * 3- @Table: Se crean anotaciones
 * para crear mapeo de class a tabla. 
 * 4- @Column: anotacion de las columnas.
 * Name debe ser el nombre tal cual está escrito 
 * 5- @Id es para indicar que esa columna es "clave" o "Primary" En phpMyAdmin 
 * muestra un icono de llave
 * 6- Deben crearse 2 constructores: una vacío y otro con todos los campos salvo
 * el ID que es automático 
 * 7- Getters/Setter de todos los campos, incluyendo ID
 * porque se puede usar para búsquedas 
 * 8- generar método toString para recibir datos en lenguaje humano.
 */
@Entity
@Table(name = "cliente")
public class Cliente {

    /*
    Anotaciones para mapeo ORM usadas:
    
    relación uno a uno, relacion en cascada de tipo all. Un cambio en una columna de una tabla se propaga en cascada por todas las tablas 
    si, por el motivo que sea, tiene ese columna duplicada. Los valores posibles de tipo de cascada son:
    ALL(Aplica todas las operaciones de cascada (crear, actualizar, borrar).
    PERSIST: Aplica la operación de persistencia (crear).
    MERGE: Aplica la operación de fusión (actualizar).
    REMOVE: Aplica la operación de eliminación (borrar).
    REFRESH: Aplica la operación de refrescar.
    DETACH: Aplica la operación de desvincular.
    
    columna que hace de unión entre ambas tablas, el Id
    detallesCliente es el campo que relaciona "clientes" con "detallescliente"
    Para que la relación sea bidireccional en la otra clase debe crearse otra anotación que referencia a esta clase:
    //anotación para relación bidiriccional
    @OneToOne(mappedBy = "detallesCliente" , cascade = CascadeType.ALL)
    private Cliente elCliente;
     */
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "Id")
    private DetallesCliente detallesCliente;

    public DetallesCliente getDetallesCliente() {
        return detallesCliente;
    }

    public void setDetallesCliente(DetallesCliente detallesCliente) {
        this.detallesCliente = detallesCliente;
    }
    /*
    @GeneratedValue(strategy = GenerationType.IDENTITY): El más indicado para mySQL. Especifica la estrategia de 
    generación de valores para la clave primaria. En este caso, se utiliza la identidad de la base de datos, lo 
    que significa que la base de datos generará automáticamente valores únicos. Si no se pone Hibernate no puede 
    obtener el valor del ID dando 0 al hacer System.out.println("Lectura del registro con ID: " + cliente1.getID());
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @Column(name = "Nombre")
    private String nombre;
    @Column(name = "Apellido")
    private String apellido;
    @Column(name = "Direccion")
    private String direccion;

    // array para almacenar pedidos de un cliente
    // se permite todas las operaciones salvo el borrado
    // El FetchType es una configuración utilizada para definir cómo se deben cargar las asociaciones 
    // entre entidades cuando se realiza una consulta a la base de datos.
    // Valores posibles:
    // EAGER: Carga toda la información de golpe en una única consulta a la BBDD
    // LAZY: Carga sobre demanda
    // fetch=FetchType.EAGER es el valor predeterminado para una relación "uno para varios" y no hace ponerlo. 
    @OneToMany( fetch=FetchType.EAGER, mappedBy = "cliente", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private List<Pedido> pedidos;

    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    @Override
    public String toString() {
        return "Clientes{" + "ID=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion + '}';
    }

    // método que agrega pedidos a un arrayList
    // si no está creado crear el array y agregarlo
    public void agregarPedidos(Pedido miPedido) {
        if (pedidos == null) {
            pedidos = new ArrayList<>();
            pedidos.add(miPedido);
            // setear cliente el propio cliente donde nos encontramos, el this
            miPedido.setCliente(this);
        } else {
            // si el array existe, no hace falta crearlo y se añaden pedidos
            pedidos.add(miPedido);
            miPedido.setCliente(this);
        }
    }
    
    // Método para recuperar pedidos
    public List<Pedido> getPedidos() {
        return pedidos;
    }

    
}
