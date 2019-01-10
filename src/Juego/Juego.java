/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import GUI.Game;
//import Puntaje.Puntaje;
import Personajes.Zombies;
import Personajes.heroe;
import Tiempo.Cronometro;
import java.awt.Graphics;
import escenario.MapaMatriz;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.ImageIcon;
import Imagenes.ColeccionJuego;
import Imagenes.Imagenes;
import java.awt.image.BufferedImage;
import Imagenes.ColeccionPerks;

/**
 *
 * @author Samuel
 */
public class Juego {

    public enum EstadoJuego {

        activo, pausa, gameover
    };
    heroe heroe;
    int nivel;
    int puntaje = 0;
    int point;
    Cronometro tMover;
    Cronometro subirVida;
    MapaMatriz mapa;
    ImageIcon terreno[][];
    int limites[][], posiciones[][], aparecer[][], matrizperks[][];
    int XI, XF, YI, YF, indiceRadarX, indiceRadarY;
    int dX, dY, n = 20;
    Zombies enemigos[];
    public EstadoJuego modo;
    Cronometro subirNivel;
    BufferedImage pantalla;
    BufferedImage pausa;
    BufferedImage ImgNivel;
    BufferedImage danio;
    BufferedImage sleight;
    BufferedImage hardlineA;
    BufferedImage bulletsA;
    BufferedImage blingA;
    BufferedImage lightweightA;
    BufferedImage CommandoA;
    BufferedImage scramblerA;
    BufferedImage sleightA;
    boolean sleightTF;
    BufferedImage hardline;
    boolean hardlineTF;
    BufferedImage bling;
    boolean blingTF;
    BufferedImage lightweight;
    boolean lightweightTF;
    BufferedImage Commando;
    boolean commandoTF;
    BufferedImage scrambler;
    boolean scramblerTF;
    ColeccionJuego adornos;
    ColeccionPerks perks;
    int puntajeActual;

    public Juego() {
        try {
            nivel = 50;
            modo = modo.activo;
            heroe = null;
            danio = null;
            tMover = new Cronometro(4);
            subirVida = new Cronometro(500);
            mapa = new MapaMatriz("imagenes//tails//terreno.txt");
            terreno = mapa.getMatrizTerreno();
            limites = mapa.getMatrizLimites();
            posiciones = mapa.getMatrizPosiciones();
            aparecer = mapa.getMatrizAparecer();
            enemigos = new Zombies[(2 * nivel) + 5];
            XI = (terreno.length / 2) - 15;
            XF = (terreno.length / 2) + 15;
            YI = (terreno[1].length / 2) - 15;
            YF = (terreno[1].length / 2) + 15;
            point = 25;
            puntajeActual = 30000;
            indiceRadarX = 425;
            indiceRadarY = 630;
            adornos = new ColeccionJuego();
            perks = new ColeccionPerks();
            pantalla = (BufferedImage) adornos.descargarImagen(4);
            pausa = (BufferedImage) adornos.descargarImagen(3);
            matrizperks = mapa.getMatrizPerks("imagenes//tails//perks.txt");
            hardline = (BufferedImage) perks.descargarImagen(7);
            hardlineTF = false;
            lightweight = (BufferedImage) perks.descargarImagen(8);
            lightweightTF = false;
            sleight = (BufferedImage) perks.descargarImagen(9);
            sleightTF = false;
            bling = (BufferedImage) perks.descargarImagen(10);
            blingTF = false;
            Commando = (BufferedImage) perks.descargarImagen(11);
            commandoTF = false;
            scrambler = (BufferedImage) perks.descargarImagen(12);
            scramblerTF = false;
            hardlineA = (BufferedImage) perks.descargarImagen(0);
            bulletsA = (BufferedImage) perks.descargarImagen(6);
            blingA = (BufferedImage) perks.descargarImagen(3);
            lightweightA = (BufferedImage) perks.descargarImagen(1);
            CommandoA = (BufferedImage) perks.descargarImagen(4);
            scramblerA = (BufferedImage) perks.descargarImagen(5);
            sleightA = (BufferedImage) perks.descargarImagen(2);
            ImgNivel = Imagenes.cargaImagen("imagenes//niveles//" + nivel + ".png");
            subirNivel = new Cronometro(1000);
        } catch (FileNotFoundException fnfe) {
            System.out.println(fnfe.getMessage());
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

    public void setModo(EstadoJuego x) {
        if (this.modo == x) {
        } else {
            modo = x;
        }
    }

    public void setHeroe(int width, int lenght) {
        heroe = new heroe(nivel, width, lenght, posiciones);
        heroe.setX(XI + 15);
        heroe.setY(YI + 15);
    }

    public void setEnemigos() {
        for (int x = 0; x < (2 * nivel) + 5; x++) {
            enemigos[x] = new Zombies();
            enemigos[x].setAparecer(aparecer, posiciones);
        }
    }

    public void acutaliza(Game ventana) {
        BufferStrategy preparacion = ventana.getBufferStrategy();
        Graphics dibujar = preparacion.getDrawGraphics();
        this.danioheroe();
        if (modo == modo.activo) {
            for (int x = 0; x < (2 * nivel) + 5; x++) {
                enemigos[x].accion(heroe, posiciones, limites);
            }

            dY = -1;
            for (int i = YI; i < YF; i++) {
                dY++;
                dX = -1;
                for (int j = XI; j < XF; j++) {
                    dX++;
                    dibujar.drawImage(terreno[j][i].getImage(), dX * n, dY * n, ventana);
                }
            }
            for (int y = 0; y < (2 * nivel) + 5; y++) {
                if (enemigos[y].imprimir(XI, YI, XF, YF)) {
                    if (enemigos[y].getPosY(YI) <= 230) {
                        dibujar.drawImage(enemigos[y].getImagen(), enemigos[y].getPosX(XI),
                                enemigos[y].getPosY(YI), ventana);
                    }
                }
            }
            dibujar.drawImage(heroe.getImagen(), heroe.getPosX(), heroe.getPosY(), ventana);
            for (int y = 0; y < (2 * nivel) + 5; y++) {
                if (enemigos[y].imprimir(XI, YI, XF, YF)) {
                    if (enemigos[y].getPosY(YI) >= 231) {
                        dibujar.drawImage(enemigos[y].getImagen(), enemigos[y].getPosX(XI),
                                enemigos[y].getPosY(YI), ventana);
                    }
                }
            }
            dibujar.drawImage(danio, 0, 0, ventana);
            if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 1 && hardlineTF == false) {
                dibujar.drawImage(hardlineA, 25, 25, ventana);
            }
            if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 2 && lightweightTF == false) {
                dibujar.drawImage(lightweightA, 25, 25, ventana);
            }
            if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 3 && sleightTF == false) {
                dibujar.drawImage(sleightA, 25, 25, ventana);
            }
            if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 4 && blingTF == false) {
                dibujar.drawImage(blingA, 25, 25, ventana);
            }
            if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 5 && commandoTF == false) {
                dibujar.drawImage(CommandoA, 25, 25, ventana);
            }
            if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 6 && scramblerTF == false) {
                dibujar.drawImage(scramblerA, 25, 25, ventana);
            }
            if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 7) {
                dibujar.drawImage(bulletsA, 25, 25, ventana);
            }
            dibujar.drawImage(this.pantalla, 0, 575, ventana);
            dibujar.drawImage(ImgNivel, 0, 600, ventana);
            if (hardlineTF == true) {
                dibujar.drawImage(hardline, 180, 540, ventana);
            }
            if (lightweightTF == true) {
                dibujar.drawImage(lightweight, 220, 540, ventana);
            }
            if (sleightTF == true) {
                dibujar.drawImage(sleight, 260, 540, ventana);
            }
            if (blingTF == true) {
                dibujar.drawImage(bling, 300, 540, ventana);
            }
            if (commandoTF == true) {
                dibujar.drawImage(Commando, 340, 540, ventana);
            }
            if (scramblerTF == true) {
                dibujar.drawImage(scrambler, 380, 540, ventana);
            }
            dibujar.drawString(this.getPuntajeActual(), 330, 630);
            dibujar.drawString(heroe.getBalas() + "", 330, 700);
            dibujar.drawString(heroe.getBalasDisponibles() + "", 330, 720);
            for (int y = 0; y < (2 * nivel) + 5; y++) {
                if (enemigos[y].getEstado() == enemigos[y].estado.muerto) {
                } else {
                    dibujar.drawImage(enemigos[y].getImagenRadar(), this.indiceRadarX + enemigos[y].getX(),
                            enemigos[y].getY() + this.indiceRadarY, ventana);
                }
            }
            dibujar.drawImage(heroe.getImagenRadar(), this.indiceRadarX + heroe.getX(), this.indiceRadarY + heroe.getY(), ventana);
            preparacion.show();
            if (this.enemigosMuertos()) {
                this.subeNivel();
            }
            if(heroe.getHp()==0){
                this.modo=modo.gameover;
            }
        }
        if (modo == modo.pausa) {
            dibujar.drawImage(this.pausa, 0, 0, ventana);
            preparacion.show();
        }
    }

    public EstadoJuego getEstado(){
        return modo;
    }

    public void danioheroe() {
        if (modo == modo.activo) {
            if (heroe.getHp() >= 3) {
                this.danio = null;
            } else {
                danio = (BufferedImage) adornos.descargarImagen(heroe.getHp());
                if (heroe.getHp() < 3) {
                    if (subirVida.esTiempo()) {
                        heroe.setHp(heroe.getHp() + 1);
                    }
                }
            }
        }
    }

    public void subeNivel() {
        if (subirNivel.esTiempo()) {
            nivel++;
            enemigos = new Zombies[(2 * nivel) + 5];
            ImgNivel = Imagenes.cargaImagen("imagenes//niveles//" + nivel + ".png");
            this.setEnemigos();
        }
    }

    public boolean enemigosMuertos() {
        int j = -1;
        int x = 0;
        for (int i = 0; i < enemigos.length; i++) {
            if (enemigos[i].getEstado() == enemigos[i].estado.muerto) {
                j++;
            }
            x = i;
        }
        if (x == j) {
            return true;
        } else {
            return false;
        }
    }

    public void accion(int tecla) throws IOException, ClassNotFoundException {
        if (modo == modo.activo) {
            switch (tecla) {
                case KeyEvent.VK_ESCAPE:
                    System.exit(0);
                    break;
                case KeyEvent.VK_1:
                    this.modo=modo.gameover;
                    break;
                case KeyEvent.VK_RIGHT:
                    heroe.setEstado(heroe.estado.corriendo);
                    heroe.setDireccion(heroe.direc.derecha);
                    heroe.setEstatus();
                    if (heroe.disponible(limites) == true) {
                        posiciones[heroe.getX()][heroe.getY()] = 0;
                        posiciones[heroe.getX() + 1][heroe.getY()] = 2;
                        if (tMover.esTiempo() && XF != terreno.length - 1) {
                            XI++;
                            XF++;
                            heroe.setX(heroe.getX() + 1);
                        }
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    heroe.setEstado(heroe.estado.corriendo);
                    heroe.setDireccion(heroe.direc.izquierda);
                    heroe.setEstatus();
                    if (heroe.disponible(limites) == true) {
                        posiciones[heroe.getX()][heroe.getY()] = 0;
                        posiciones[heroe.getX() - 1][heroe.getY()] = 2;
                        if (tMover.esTiempo() && XI != 0) {
                            XI--;
                            XF--;
                            heroe.setX(heroe.getX() - 1);
                        }
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    heroe.setEstado(heroe.estado.corriendo);
                    heroe.setDireccion(heroe.direc.adelante);
                    heroe.setEstatus();
                    if (heroe.disponible(limites) == true) {
                        posiciones[heroe.getX()][heroe.getY()] = 0;
                        posiciones[heroe.getX()][heroe.getY() + 1] = 2;
                        if (tMover.esTiempo() && YF != terreno[0].length - 1) {
                            YI++;
                            YF++;
                            heroe.setY(heroe.getY() + 1);
                        }
                    }
                    break;
                case KeyEvent.VK_UP:
                    heroe.setEstado(heroe.estado.corriendo);
                    heroe.setDireccion(heroe.direc.atras);
                    heroe.setEstatus();
                    if (heroe.disponible(limites) == true) {
                        posiciones[heroe.getX()][heroe.getY()] = 0;
                        posiciones[heroe.getX()][heroe.getY() - 1] = 2;
                        if (tMover.esTiempo() && YI != 1) {
                            YI--;
                            YF--;
                            heroe.setY(heroe.getY() - 1);
                        }
                    }
                    break;
                case KeyEvent.VK_S:
                    heroe.recargar();
                    break;
                case KeyEvent.VK_D:
                    heroe.setEstado(heroe.estado.cuchillo);
                    heroe.setEstatus();
                    for (int x = 0; x < (2 * nivel) + 5; x++) {
                        if (enemigos[x].recibeDanio(heroe.atacaCuchillo(enemigos[x]), heroe.getDanio()) == true) {
                            puntaje += point;
                            puntajeActual += point;
                        }
                    }
                    break;
                case KeyEvent.VK_F:
                    if (heroe.getBalas() == 0) {
                    } else {
                        heroe.atacaPistola();
                        heroe.setEstado(heroe.estado.disparando);
                        heroe.setEstatus();
                        for (int x = 0; x < (2 * nivel) + 5; x++) {
                            if (enemigos[x].recibeDanio(heroe.alcanzaPistola(enemigos[x]), heroe.getAtaqueArma()) == true) {
                                puntaje += point;
                                puntajeActual += point;
                            }
                        }
                    }
                    break;
                case KeyEvent.VK_P:
                    this.setModo(modo.pausa);
                    break;
            }
        } else {
            if (modo == modo.pausa) {
                switch (tecla) {
                    case KeyEvent.VK_P:
                        this.setModo(modo.activo);
                        break;
                }
            }
        }

    }

    public String getPuntaje() {
        return puntaje + "";
    }

    public String getPuntajeActual() {
        return puntajeActual + "";
    }

    public void reaccion(int tecla) {
        if (modo == modo.activo) {
            switch (tecla) {
                case KeyEvent.VK_RIGHT:
                    heroe.setEstado(heroe.estado.parado);
                    heroe.setDireccion(heroe.direc.derecha);
                    heroe.setEstatus();
                    break;
                case KeyEvent.VK_LEFT:
                    heroe.setEstado(heroe.estado.parado);
                    heroe.setDireccion(heroe.direc.izquierda);
                    heroe.setEstatus();
                    break;
                case KeyEvent.VK_DOWN:
                    heroe.setEstado(heroe.estado.parado);
                    heroe.setDireccion(heroe.direc.adelante);
                    heroe.setEstatus();
                    break;
                case KeyEvent.VK_UP:
                    heroe.setEstado(heroe.estado.parado);
                    heroe.setDireccion(heroe.direc.atras);
                    heroe.setEstatus();
                    break;
                case KeyEvent.VK_F:
                    heroe.setEstado(heroe.estado.parado);
                    heroe.setEstatus();
                    break;
                case KeyEvent.VK_A:
                    if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 1 && hardlineTF == false) {
                        if (puntajeActual > 2000) {
                            puntajeActual -= 2000;
                            this.hardline();
                        }
                    }
                    if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 2 && lightweightTF == false) {
                        if (puntajeActual > 2000) {
                            puntajeActual -= 2000;
                            this.lightweight();
                        }
                    }
                    if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 3 && sleightTF == false) {
                        if (puntajeActual > 2000) {
                            puntajeActual -= 2000;
                            this.sleight();
                        }
                    }
                    if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 4 && blingTF == false) {
                        if (puntajeActual > 2000) {
                            puntajeActual -= 2000;
                            this.bling();
                        }
                    }
                    if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 5 && commandoTF == false) {
                        if (puntajeActual > 2000) {
                            puntajeActual -= 2000;
                            this.commando();
                        }
                    }
                    if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 6 && scramblerTF == false) {
                        if (puntajeActual > 2000) {
                            puntajeActual -= 2000;
                            this.scrambler();
                        }
                    }
                    if (matrizperks[heroe.getX()-2][heroe.getY()-1] == 7) {
                        if (puntajeActual > 500) {
                            puntajeActual -= 500;
                            this.suply();
                        }
                    }
            }
        }

    }

    public void hardline() {
        heroe.setHp(5);
        hardlineTF = true;
    }

    public void lightweight() {
        tMover = new Cronometro(3);
        heroe.heroeLight();
        lightweightTF = true;
    }

    public void sleight() {
        heroe.heroesleight();
        sleightTF = true;

    }

    public void bling() {
        point = 50;
        blingTF = true;
    }

    public void commando() {
        heroe.heroeCommando();
        commandoTF = true;
    }

    public void scrambler() {
        heroe.heroeScrambler();
        scramblerTF = true;
    }

    public void suply() {
        heroe.suply();
    }
}
