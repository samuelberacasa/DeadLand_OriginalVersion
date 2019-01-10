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
 * @author Monica
 */
public class ColeccionPerks {
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

    public ColeccionPerks(){
        sprites.add(cargaImagen("imagenes//perks//hardline.png"));
        sprites.add(cargaImagen("imagenes//perks//lightweight.png"));
        sprites.add(cargaImagen("imagenes//perks//sleightofhand.png"));
        sprites.add(cargaImagen("imagenes//perks//bling.png"));
        sprites.add(cargaImagen("imagenes//perks//commando.png"));
        sprites.add(cargaImagen("imagenes//perks//scrambler.png"));
        sprites.add(cargaImagen("imagenes//perks//bullets.png"));
        sprites.add(cargaImagen("imagenes//perks//perkhardline.png"));
        sprites.add(cargaImagen("imagenes//perks//perklightweight.png"));
        sprites.add(cargaImagen("imagenes//perks//perksleightofhand.png"));
        sprites.add(cargaImagen("imagenes//perks//perkbling.png"));
        sprites.add(cargaImagen("imagenes//perks//perkcommando.png"));
        sprites.add(cargaImagen("imagenes//perks//perkscrambler.png"));
    }

    public Object descargarImagen(int index){
        return sprites.get(index);
    }

}
