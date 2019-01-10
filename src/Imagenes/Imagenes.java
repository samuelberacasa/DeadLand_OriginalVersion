package Imagenes;


import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashSet;
import javax.imageio.ImageIO;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Samuel
 */
public class Imagenes {
    public static BufferedImage cargaImagen(String nombreImagen){

        try{
            BufferedImage leer= ImageIO.read(new File(nombreImagen));
            return leer;
        }catch(Exception e){
            System.out.print("Error al cargar"+ nombreImagen+ e.toString());
            return null;
        }
    }

}
