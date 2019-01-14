/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Imagenes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author Samuel Beracasa
 */
public class ColeccionJuego {
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

    public ColeccionJuego(){
        sprites.add(cargaImagen("imagenes//pantalla//hp0.png"));
        sprites.add(cargaImagen("imagenes//pantalla//hp1.png"));
        sprites.add(cargaImagen("imagenes//pantalla//hp2.png"));
        sprites.add(cargaImagen("imagenes//pantalla//pausa.png"));
        sprites.add(cargaImagen("imagenes//pantalla//pantalla.png"));
    }

    public Object descargarImagen(int index){
        return sprites.get(index);
    }

}
