/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Imagenes;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author Samuel
 */
public class ColeccionZombies {
    LinkedList sprites = new LinkedList();

    public static BufferedImage cargaImagen(String nombreImagen){

        try{
            BufferedImage leer= ImageIO.read(new File(nombreImagen));
            return leer;

        }catch(Exception e){
            System.out.print("Error al cargar"+ nombreImagen+ e.toString());
            return null;
        }
    }

    public ColeccionZombies(){
        sprites.add(cargaImagen("imagenes//zombie//Aparece.png"));
        sprites.add(cargaImagen("imagenes//zombie//ZombieAdelante.png"));
        sprites.add(cargaImagen("imagenes//zombie//ZombieAtras.png"));
        sprites.add(cargaImagen("imagenes//zombie//ZombieIzquierda.png"));
        sprites.add(cargaImagen("imagenes//zombie//ZombieDerecha.png"));
        sprites.add(cargaImagen("imagenes//zombie//DanoAdelante.png"));
        sprites.add(cargaImagen("imagenes//zombie//DanoAtras.png"));
        sprites.add(cargaImagen("imagenes//zombie//DanoIzquierda.png"));
        sprites.add(cargaImagen("imagenes//zombie//DanoDerecha.png"));
        sprites.add(cargaImagen("imagenes//zombie//Muere.png"));
        sprites.add(cargaImagen("imagenes//zombie//ZombieRadar.png"));
    }

    public Object descargarImagen(int index){
        return sprites.get(index);
    }
}
