package practica;

import java.io.Serializable;

public class Escuderia implements Serializable{
    
    private String id;
    private String nombre_escud;

    public Escuderia() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre_escud() {
        return nombre_escud;
    }

    public void setNombre_escud(String nombre_escud) {
        this.nombre_escud = nombre_escud;
    }

    @Override
    public String toString() {
        return "Escuderias{" + "id=" + id + ", nombre_escud=" + nombre_escud + '}';
    }
    
    
}
