package Puntaje;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeMap;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Samuel Beracasa
 */
public class ListaJugadores extends TreeMap implements Serializable{

    public void agregaJugador(Jugador p){
        this.put(p.getPuntaje(),p);
    }

    public void guardaLista() throws IOException{
        ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream("jugadores.dat"));
        oos.writeObject(this);
        oos.close();
    }

    public void cargaLista() throws IOException, ClassNotFoundException{
        ObjectInputStream ois = new ObjectInputStream (new FileInputStream("jugadores.dat"));
        ListaJugadores x= (ListaJugadores) ois.readObject();
        this.putAll(x);
        ois.close();

    }

    public String muestraLista(){
        return this.toString();
    }


}
