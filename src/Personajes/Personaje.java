package Personajes;

import Tiempo.Cronometro;
import java.awt.image.BufferedImage;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Samuel
 */
public class Personaje {

    public enum Direccion {izquierda, derecha, adelante, atras};
    int x;
    int y;
    int posX;
    int posY;
    int hp;
    int danio;
    int velocidad;
    int sprites;
    int paso;
    BufferedImage imagen;
    BufferedImage imagenRadar;



    public BufferedImage getImagenRadar() {
        return imagenRadar;
    }
    public Direccion direc;
    Cronometro tiempoAvance, tiempoPaso;

    public int getDanio() {
        return danio;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direccion getDirec() {
        return direc;
    }

    public int getHp() {
        return hp;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public Cronometro getTiempoAvance() {
        return tiempoAvance;
    }

    public Cronometro getTiempoPaso() {
        return tiempoPaso;
    }

    public int getPaso() {
        return paso;
    }

    public int getPosX() {
        return posX;
    }

    public int getPosY() {
        return posY;
    }

    public int getSprites() {
        return sprites;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDireccion(Direccion direc) {
        if (this.direc != direc) {
            this.direc = direc;
            paso = 0;
            velocidad = 1;
        }
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }

    public void setPaso(int paso) {
        this.paso = paso;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public void setSprites(int sprites) {
        this.sprites = sprites;
    }

    public void setTiempoAvance(Cronometro tiempoAvance) {
        this.tiempoAvance = tiempoAvance;
    }

    public void setTiempoPaso(Cronometro tiempoPaso) {
        this.tiempoPaso = tiempoPaso;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }

    public boolean disponible(int arr[][]) {
        if (direc == direc.izquierda) {
            if (arr[x - 2][y] == 0) {
                return false;
            }
        } else {
            if (direc == direc.derecha) {
                if (arr[x + 1][y] == 0) {
                    return false;
                }
            } else {
                if (direc == direc.atras) {
                    if (arr[x][y - 2] == 0) {
                        return false;
                    }
                } else {
                    if (direc == direc.adelante) {
                        if (arr[x][y + 1] == 0) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }
}
