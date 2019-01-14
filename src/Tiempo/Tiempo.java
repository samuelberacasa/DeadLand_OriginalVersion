package Tiempo;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Samuel Beracasa
 */
public class Tiempo {
    private long tiempoAnterior;
    private long tiempoTranscurrido;
    private long diferenciaMaxima;

    public Tiempo() {
        this.tiempoAnterior = 0;
        this.tiempoTranscurrido = 0;
        this.diferenciaMaxima = 0;
    }

    public long getDiferenciaMaxima() {
        return diferenciaMaxima;
    }

    public long getTiempoAnterior() {
        return tiempoAnterior;
    }

    public long getTiempoTranscurrido() {
        return tiempoTranscurrido;
    }

    public void calculaTiempo(){
        if (tiempoAnterior==0){
            tiempoAnterior=System.currentTimeMillis();
        }
        long tiempoActual=System.currentTimeMillis();
        long diferencia= tiempoActual-tiempoAnterior;
        if(diferencia>diferenciaMaxima){
            diferenciaMaxima=diferencia;
        }
        tiempoAnterior=tiempoActual;
        tiempoTranscurrido+=diferencia;
    }
}
