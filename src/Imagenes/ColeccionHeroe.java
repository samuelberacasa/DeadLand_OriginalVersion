/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Imagenes;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import javax.imageio.ImageIO;

/**
 *
 * @author Samuel
 */
public class ColeccionHeroe {

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

    public ColeccionHeroe(){
        sprites.add(cargaImagen("imagenes//heroe//ParadaAdelante.png"));
        sprites.add(cargaImagen("imagenes//heroe//ParadaAtras.png"));
        sprites.add(cargaImagen("imagenes//heroe//ParadaIzquierda.png"));
        sprites.add(cargaImagen("imagenes//heroe//ParadaDerecha.png"));
        sprites.add(cargaImagen("imagenes//heroe//CaminandoAdelante.png"));
        sprites.add(cargaImagen("imagenes//heroe//CaminandoAtras.png"));
        sprites.add(cargaImagen("imagenes//heroe//CaminandoIzquierda.png"));
        sprites.add(cargaImagen("imagenes//heroe//CaminandoDerecha.png"));
        sprites.add(cargaImagen("imagenes//heroe//CuchilloAdelante.png"));
        sprites.add(cargaImagen("imagenes//heroe//CuchilloAtras.png"));
        sprites.add(cargaImagen("imagenes//heroe//CuchilloIzquierda.png"));
        sprites.add(cargaImagen("imagenes//heroe//CuchilloDerecha.png"));
        sprites.add(cargaImagen("imagenes//heroe//DisparaAdelante.png"));
        sprites.add(cargaImagen("imagenes//heroe//DisparaAtras.png"));
        sprites.add(cargaImagen("imagenes//heroe//DisparaIzquierda.png"));
        sprites.add(cargaImagen("imagenes//heroe//DisparaDerecha.png"));
        sprites.add(cargaImagen("imagenes//heroe//personajeRadar.png"));
    }

    public Object descargarImagen(int index){
        return sprites.get(index);
    }

}
