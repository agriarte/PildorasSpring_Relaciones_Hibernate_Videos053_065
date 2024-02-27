package pildoras.relacioneshibernate_videos053_065;

// Ojo al import. Aunque parezca contradictorio se importa este paquete y no el org.hibernate.anotations.*

import javax.persistence.*;



/**
 * Hibernate transforma cada clase en entidades para realizar el mapeo. @Entity
 * 1- Un atributo por cada columna de la tabla
 * 2- @Table: Se crean anotaciones para crear mapeo de class a tabla. 
 * 3- @Column: anotacion de las columnas. Name debe ser el nombre tal cual está escrito
 * 4- @Id es para indicar que esa columna es "clave" o Primary tal como se ve en phpMyAdmin. Le añade un icono de llave
 * 
 * 5- Deben crearse 2 constructores: una vacío y otro con todos los campos salvo el ID que es automático
 * 6- Getters/Setter de todos los campos, incluyendo ID porque se puede usar para búsquedas
 * 7- generar método toString para recibir datos en lenguaje humano.
 */
@Entity
@Table (name="detalles_cliente")
public class DetallesCliente {
    
        /*
    @GeneratedValue(strategy = GenerationType.IDENTITY): El más indicado para mySQL. Especifica la estrategia de 
    generación de valores para la clave primaria. En este caso, se utiliza la identidad de la base de datos, lo 
    que significa que la base de datos generará automáticamente valores únicos. Si no se pone Hibernate no puede 
    obtener el valor del Id dando 0 al hacer System.out.println("Lectura del registro con Id: " + cliente1.getID());
    */
    
    @Id
    @GeneratedValue (strategy=GenerationType.IDENTITY)
    @Column(name="Id")
    private int id;
 
    @Column(name="Web")
    private String web;
    @Column(name="Telefono")
    private String telefono;
    @Column(name="Comentarios")
    private String comentarios;
    
    //anotación para relación bidiriccional
    @OneToOne(mappedBy = "detallesCliente" , cascade = CascadeType.ALL)
    private Cliente elCliente;

    public DetallesCliente() {
    }

    public DetallesCliente(String web, String telefono, String comentarios) {
        this.web = web;
        this.telefono = telefono;
        this.comentarios = comentarios;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "DetallesCliente{" + "ID=" + id + ", web=" + web + ", telefono=" + telefono + ", comentarios=" + comentarios + '}';
    }

    public Cliente getElCliente() {
        return elCliente;
    }

    public void setElCliente(Cliente elCliente) {
        this.elCliente = elCliente;
    }

    


    
    
}
