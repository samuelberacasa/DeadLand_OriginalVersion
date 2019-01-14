/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Armas;

import Tiempo.Cronometro;

/**
 *
 * @author Samuel Beracasa
 */
public class Arma {

    int ataque;
    int balasDisponibles;
    int balasEnCargador;
    int alcance;
    Cronometro recargar = new Cronometro(400);

    public void setRecargar(int x) {
        recargar = new Cronometro(x);
    }

    public int getAlcance() {
        return alcance;
    }

    public int getAtaque() {
        return ataque;
    }

    public int getBalasDisponibles() {
        return balasDisponibles;
    }

    public int getBalasEnCargador() {
        return balasEnCargador;
    }

    public void setAlcance(int alcance) {
        this.alcance = alcance;
    }

    public void setAtaque(int ataque) {
        this.ataque = ataque;
    }

    public void setBalasDisponibles(int balasDisponibles) {
        this.balasDisponibles += balasDisponibles;
    }

    public void setBalasEnCargador(int balasEnCargador) {
        this.balasEnCargador = balasEnCargador;
    }

    public Arma(int ataque, int balasEnCargador, int balasDisponibles, int alcance) {
        this.ataque = ataque;
        this.balasDisponibles = balasDisponibles;
        this.balasEnCargador = balasEnCargador;
        this.alcance = alcance;
    }

    public void setTiempoRecarga(){
        recargar=new Cronometro(100);
    }

    public void recargar() {
        if (recargar.esTiempo()) {
            int temp = 10 - this.balasEnCargador;
            if (this.balasDisponibles + this.balasEnCargador > 10) {
                this.balasDisponibles -= temp;
                this.balasEnCargador = 10;
            } else {
                if (this.balasDisponibles + this.balasEnCargador < 10 && this.balasDisponibles > 0) {
                    this.balasEnCargador += this.balasDisponibles;
                    this.balasDisponibles = 0;
                } else {
                    this.balasDisponibles = 0;
                    this.balasEnCargador = 0;
                }
            }
        }
    }
}
