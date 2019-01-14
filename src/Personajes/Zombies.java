package Personajes;

import Imagenes.ColeccionZombies;
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
public class Zombies extends Personaje {

    public enum Estado {

        aparece, persigue, ataca, danio, muerto
    };

    ColeccionZombies colec = new ColeccionZombies();
    Cronometro tiempoAparece;
    Cronometro tiempoAtaca;
    Cronometro tiempoRecupera;
    public Estado estado;

    public Zombies() {
        colec = new ColeccionZombies();
        x = 0;
        y = 0;
        posX = 0;
        posY = 0;
        imagen = (BufferedImage) colec.descargarImagen(0);
        paso = 0;
        hp = 6;
        danio = 1;
        velocidad = 1;
        sprites = 5;
        estado = estado.aparece;
        direc = direc.izquierda;
        imagenRadar = (BufferedImage) colec.descargarImagen(10);
        tiempoAtaca = new Cronometro(50);
        tiempoRecupera = new Cronometro(20);
        tiempoPaso = new Cronometro(4);
        tiempoAvance = new Cronometro(8);
        tiempoAparece = new Cronometro(10);
    }

    public Estado getEstado(){
        return this.estado;
    }

    public void avanza(int posicion[][]) {
        if (tiempoAvance.esTiempo()) {
            if (direc == direc.izquierda) {
                posicion[this.x][this.y] = 0;
                this.setX(this.x - velocidad);
                posicion[this.x][this.y] = 1;
            }
            if (direc == direc.derecha) {
                posicion[this.x][this.y] = 0;
                this.setX(this.x + velocidad);
                posicion[this.x][this.y] = 1;
            }
            if (direc == direc.atras) {
                posicion[this.x][this.y] = 0;
                this.setY(this.y - velocidad);
                posicion[this.x][this.y] = 1;
            }
            if (direc == direc.adelante) {
                posicion[this.x][this.y] = 0;
                this.setY(this.y + velocidad);
                posicion[this.x][this.y] = 1;
            }
        }
    }

    public void setEstado(Estado e) {
        if (this.estado != e) {
            this.estado = e;
            paso = 0;
            this.setEstatus();
        }
    }

    @Override
    public void setDireccion(Direccion direc) {
        if (this.direc != direc) {
            this.direc = direc;
            paso = 0;
            velocidad = 1;
        }
    }

    public void setEstatus() {
        int c = 0;
        switch (estado) {
            case aparece:
                velocidad = 0;
                sprites = 5;
                c = 0;
                break;
            case persigue:
                velocidad = 1;
                switch (direc) {
                    case adelante:
                        sprites = 4;
                        c = 1;
                        break;
                    case atras:
                        sprites = 4;
                        c = 2;
                        break;
                    case izquierda:
                        sprites = 4;
                        c = 3;
                        break;
                    case derecha:
                        sprites = 4;
                        c = 4;
                        break;
                }
                break;
            case danio:
                velocidad = 0;
                switch (direc) {
                    case adelante:
                        sprites = 1;
                        paso = 0;
                        c = 5;
                        break;
                    case atras:
                        sprites = 1;
                        paso = 0;
                        c = 6;
                        break;
                    case izquierda:
                        sprites = 1;
                        paso = 0;
                        c = 7;
                        break;
                    case derecha:
                        sprites = 1;
                        paso = 0;
                        c = 8;
                        break;
                }
                break;
            case ataca:
                velocidad = 0;
                switch (direc) {
                    case adelante:
                        sprites = 4;
                        c = 1;
                        break;
                    case atras:
                        sprites = 4;
                        c = 2;
                        break;
                    case izquierda:
                        sprites = 4;
                        c = 3;
                        break;
                    case derecha:
                        sprites = 4;
                        c = 4;
                        break;
                }
                break;
            case muerto:
                velocidad = 0;
                sprites = 6;
                c = 9;
                break;
        }
        BufferedImage image = (BufferedImage) colec.descargarImagen(c);
        this.setImagen(image);
    }

    public void setAparecer(int aparecer[][], int posicion[][]) {
        int z, d;
        do {
            z = (int) (Math.random() * (aparecer.length - 15) + 15);
            d = (int) (Math.random() * (aparecer[1].length - 15) + 15);
        } while (aparecer[z][d] != 1);
        this.setX(z);
        this.setY(d);
        posicion[x][y] = 1;
    }

    public void perseguir(Personaje p, int posicion[][], int lim[][]) {
        if (p.getX() - 2 > x) {
            this.setDireccion(direc.derecha);
            if (this.disponible(lim)) {
                this.setEstatus();
                this.avanza(posicion);
            } else {
                if (p.getY() - 5 < y) {
                    this.setDireccion(direc.adelante);
                    if (this.disponible(lim)) {
                        this.setEstatus();
                        this.avanza(posicion);
                    }
                } else {
                    if (p.getY() - 1 > y) {
                        this.setDireccion(direc.atras);
                        if (this.disponible(lim)) {
                            this.setEstatus();
                            this.avanza(posicion);
                        }
                    }
                }
            }
        } else {
            if (p.getX() - 1 < x) {
                this.setDireccion(direc.izquierda);
                if (this.disponible(lim)) {
                    this.setEstatus();
                    this.avanza(posicion);
                } else {
                    if (p.getY() - 5 < y) {
                        this.setDireccion(direc.adelante);
                        if (this.disponible(lim)) {
                            this.setEstatus();
                            this.avanza(posicion);
                        }
                    } else {
                        if (p.getY() - 1 > y) {
                            this.setDireccion(direc.atras);
                            if (this.disponible(lim)) {
                                this.setEstatus();
                                this.avanza(posicion);
                            }
                        }
                    }
                }
            } else {
                if (p.getY() - 5 > y) {
                    this.setDireccion(direc.adelante);
                    if (this.disponible(lim)) {
                        this.setEstatus();
                        this.avanza(posicion);
                    }
                } else {
                    if (p.getY() - 1 < y) {
                        this.setDireccion(direc.atras);
                        if (this.disponible(lim)) {
                            this.setEstatus();
                            this.avanza(posicion);
                        }
                    } else {
                        this.setEstado(estado.ataca);
                        this.atacar(p);
                    }
                }
            }

        }
    }

    public void accion(Personaje p, int posicion[][], int limites[][]) {
        switch (estado) {
            case aparece:
                break;
            case persigue:
                this.perseguir(p, posicion, limites);
                break;
            case ataca:
                this.atacar(p);
                break;
            case danio:
                break;
            case muerto:
                break;
        }
    }

    public void atacar(Personaje p) {
        if (tiempoAtaca.esTiempo()) {
            if (this.direc == direc.derecha) {
                if (p.getX() - 2 == this.getX() && p.getY() - 1 > y && p.getY() - 5 < y) {
                    p.setHp(p.getHp() - 1);
                } else {
                    this.setEstado(estado.persigue);
                }
            } else {
                if (this.direc == direc.izquierda) {
                    if (p.getX() - 1 == this.getX() && p.getY() - 1 > y && p.getY() - 5 < y) {
                        p.setHp(p.getHp() - 1);
                    } else {
                        this.setEstado(estado.persigue);
                    }
                } else {
                    if (this.direc == direc.atras) {
                        if (p.getY()-1 == this.getY() && p.getX() - 3 < this.getX() && p.getX() > this.getX() ) {
                            p.setHp(p.getHp() - 1);
                        } else {
                            this.setEstado(estado.persigue);
                        }
                    } else {
                        if (this.direc == direc.adelante) {
                            if (p.getY() - 5 == this.getY() && p.getX() - 3 < this.getX() && p.getX() > this.getX() ) {
                                p.setHp(p.getHp() - 1);
                            } else {
                                this.setEstado(estado.persigue);
                            }
                        }

                    }
                }
            }

        }
    }
    

    public boolean recibeDanio(boolean d, int x) {
        if (d == true) {
            if(estado==estado.muerto){

            }else{
                this.setEstado(estado.danio);
            this.setEstatus();
            this.hp = hp - x;
            if (this.hp < 0) {
                this.setEstado(estado.muerto);
                this.setEstatus();
                return true;
            }
            }
        }
        return false;
    }

    public boolean imprimir(int xi, int yi, int xf, int yf) {
        if (this.getX() > xi && this.getX() < xf && this.getY() < yf && this.getY() > yi) {
            return true;
        } else {
            return false;
        }
    }

    public int getPosY(int py) {
        return (this.y - py) * 20;
    }

    public int getPosX(int px) {
        return (this.x - px) * 20;
    }

    @Override
    public BufferedImage getImagen() {
        int px, py, ancho, alto;

        ancho = imagen.getWidth() / sprites;
        alto = imagen.getHeight();
        px = paso * ancho;
        py = 0;

        if (estado == estado.aparece) {
            if (tiempoAparece.esTiempo()) {
                paso = paso + 1;
                if (paso == 4) {
                    this.setEstado(estado.persigue);
                }
            }
        } else {
            if (estado == estado.muerto) {
                if (tiempoPaso.esTiempo()) {
                    if (paso == 5) {
                    } else {
                        paso = paso + 1;
                    }
                }
            } else {
                if (estado == estado.persigue) {
                    if (tiempoPaso.esTiempo()) {
                        paso = (paso + 1) % 4;
                    }
                } else {
                    if (estado == estado.ataca) {
                        if (tiempoPaso.esTiempo()) {
                            paso = (paso + 1) % 4;
                        }
                    } else {
                        if (estado == estado.danio) {
                            if (tiempoRecupera.esTiempo()) {
                                this.setEstado(estado.persigue);
                            }
                        }
                    }
                }
            }
        }
        return imagen.getSubimage(px, py, ancho, alto);
    }
}
