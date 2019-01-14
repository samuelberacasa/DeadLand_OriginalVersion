package Personajes;

import Armas.Arma;
import Imagenes.ColeccionHeroe;
import Tiempo.Cronometro;
import java.awt.image.BufferedImage;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Samuel Beracasa
 */
public class heroe extends Personaje {

    public enum Estado {

        parado, corriendo, cuchillo, disparando, muerto
    };
    Arma pistola;
    Cronometro tiempoCuchillo;
    public Estado estado;
    ColeccionHeroe colec = new ColeccionHeroe();

    public heroe(int nivel, int width, int lenght, int arr[][]) {
        imagen = (BufferedImage) colec.descargarImagen(0);
        sprites = 4;
        posX = width / 2 + imagen.getWidth();
        posY = lenght / 2 + imagen.getHeight();
        x = arr.length / 2;
        y = arr[1].length / 2;
        hp = 3;
        danio = 10000;
        velocidad = 1;
        sprites = 1;
        paso = 0;
        direc = direc.adelante;
        estado = estado.parado;
        imagenRadar = (BufferedImage) colec.descargarImagen(16);
        tiempoPaso = new Cronometro(4);
        tiempoCuchillo = new Cronometro(2);
        pistola = new Arma(2, 10, 32, 5);
    }

    public void suply(){
        pistola.setBalasDisponibles(80);
    }

    public void setEstado(Estado est) {
        if (this.estado != est) {
            this.estado = est;
            paso = 0;
        }
    }

    public void heroeLight(){
        tiempoPaso= new Cronometro(3);
    }

    public void heroesleight(){
        pistola.setTiempoRecarga();
    }

    public void heroeCommando(){
        tiempoCuchillo=new Cronometro(1);
    }

    public void heroeScrambler(){
        pistola.setAlcance(pistola.getAlcance()*2);
    }

    public int getBalas() {
        return pistola.getBalasEnCargador();
    }

    public int getBalasDisponibles(){
        return pistola.getBalasDisponibles();
    }

    public void recargar() {
        pistola.recargar();
    }

    public int getAtaqueArma() {
        return pistola.getAtaque();
    }

    public void setEstatus() {
        int x = 0;
        switch (estado) {
            case parado:
                switch (this.direc) {
                    case adelante:
                        sprites = 1;
                        paso = 0;
                        x = 0;
                        break;
                    case atras:
                        sprites = 1;
                        paso = 0;
                        x = 1;
                        break;
                    case izquierda:
                        sprites = 1;
                        paso = 0;
                        x = 2;
                        break;
                    case derecha:
                        sprites = 1;
                        paso = 0;
                        x = 3;
                        break;
                }
                break;
            case corriendo:
                switch (this.direc) {
                    case adelante:
                        sprites = 6;
                        x = 4;
                        break;
                    case atras:
                        sprites = 6;
                        x = 5;
                        break;
                    case izquierda:
                        sprites = 6;
                        x = 6;
                        break;
                    case derecha:
                        sprites = 6;
                        x = 7;
                        break;
                }
                break;
            case cuchillo:
                switch (this.direc) {
                    case adelante:
                        sprites = 6;
                        paso = 0;
                        x = 8;
                        break;
                    case atras:
                        sprites = 6;
                        paso = 0;
                        x = 9;
                        break;
                    case izquierda:
                        sprites = 6;
                        paso = 0;
                        x = 10;
                        break;
                    case derecha:
                        sprites = 6;
                        paso = 0;
                        x = 11;
                        break;
                }
                break;
            case disparando:
                switch (this.direc) {
                    case adelante:
                        sprites = 1;
                        paso = 0;
                        x = 12;
                        break;
                    case atras:
                        sprites = 1;
                        paso = 0;
                        x = 13;
                        break;
                    case izquierda:
                        sprites = 1;
                        paso = 0;
                        x = 14;
                        break;
                    case derecha:
                        sprites = 1;
                        paso = 0;
                        x = 15;
                        break;
                }

        }
        BufferedImage image = (BufferedImage) colec.descargarImagen(x);
        this.setImagen(image);
    }

    public boolean atacaCuchillo(Personaje p) {
        if (this.direc == direc.derecha) {
            if (p.getX() > this.getX() - 3 && p.getX() < this.getX() + 1) {
                if (p.getY() > this.getY() - 5 && p.getY() < this.getY() - 1) {
                    return true;
                }
            }
        } else {
            if (this.direc == direc.izquierda) {
                if (p.getX() < this.getX() - 1 && p.getX() > this.getX() - 5) {
                    if (p.getY() > this.getY() - 5 && p.getY() < this.getY() - 1) {
                        return true;
                    }

                }
            } else {
                if (this.direc == direc.atras) {
                    if (p.getY() > this.getY() - 7 && p.getY() < this.getY() - 3) {
                        if (p.getX() > this.getX() - 3 && p.getX() < this.getX()) {
                            return true;
                        }
                    }
                } else {
                    if (this.direc == direc.adelante) {
                        if (p.getY() > this.getY() - 4 && p.getY() < this.getY()) {
                            if (p.getX() > this.getX() - 3 && p.getX() < this.getX()) {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

    public void atacaPistola() {
        if (pistola.getBalasEnCargador() == 0) {
            this.recargar();
        } else {
            pistola.setBalasEnCargador(pistola.getBalasEnCargador() - 1);
        }
    }

    public boolean alcanzaPistola(Personaje p){
            if (this.direc == direc.derecha) {
                if (p.getX() > this.getX() - 3 && p.getX() < this.getX() + 1 + this.pistola.getAlcance()) {
                    if (p.getY() > this.getY() - 5 && p.getY() < this.getY() - 1) {
                        return true;
                    }
                }
            } else {
                if (this.direc == direc.izquierda) {
                    if (p.getX() < this.getX() - 1 && p.getX() > this.getX() - 5 - this.pistola.getAlcance()) {
                        if (p.getY() > this.getY() - 5 && p.getY() < this.getY() - 1) {
                            return true;
                        }

                    }
                } else {
                    if (this.direc == direc.atras) {
                        if (p.getY() > this.getY() - 7 - this.pistola.getAlcance() && p.getY() < this.getY() - 3) {
                            if (p.getX() > this.getX() - 3 && p.getX() < this.getX()) {
                                return true;
                            }
                        }
                    } else {
                        if (this.direc == direc.adelante) {
                            if (p.getY() > this.getY() - 4 && p.getY() < this.getY() + this.pistola.getAlcance()) {
                                if (p.getX() > this.getX() - 3 && p.getX() < this.getX()) {
                                    return true;
                                }
                            }
                        }
                    }
                }
            }
            return false;
        }

    @Override
    public BufferedImage getImagen() {
        int x, y, ancho, alto;

        try {
            ancho = imagen.getWidth() / sprites;
            alto = imagen.getHeight();
            x = paso * ancho;
            y = 0;

            if (estado == estado.cuchillo) {
                if (tiempoCuchillo.esTiempo()) {
                    paso = paso + 1;
                    if (paso == 5) {
                        this.setEstado(estado.parado);
                        this.setEstatus();
                    }
                }
            }
            if (estado == estado.corriendo) {
                if (tiempoPaso.esTiempo()) {
                    paso = (paso + 1) % 6;
                }
            }
            return imagen.getSubimage(x, y, ancho, alto);
        } catch (Exception e) {}

        return null;
    }
}
