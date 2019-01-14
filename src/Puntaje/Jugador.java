package Puntaje;


import java.io.Serializable;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Samuel Beracasa
 */
public class Jugador implements Serializable{
    String nombre, puntaje;

    public Jugador (String n, String m){
        nombre = n;
        puntaje = m;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String matricula) {
        this.puntaje = matricula;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString(){
        return "Nombre: " + nombre + "Puntaje:" + puntaje + "\n";
    }

}
