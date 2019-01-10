package escenario;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Samuel
 */
public class MapaMatriz {

    String nombre;
    int height;
    String matriz[][];
    int matrizLimites[][], matrizPosiciones[][], matrizAparecer[][], matrizPerks[][];
    ImageIcon matrizTerreno[][], perks[][];

    public MapaMatriz(String nom1) throws FileNotFoundException, IOException {
        nombre = nom1;
        BufferedReader file = new BufferedReader(new FileReader(nombre));
        String width[] = file.readLine().split("");
        String lin;
        height = 1;
        while ((lin = file.readLine()) != null) {
            height++;
        }
        matriz = new String[width.length][height];
        matrizTerreno = new ImageIcon[width.length][height];
        perks = new ImageIcon[width.length][height];
        matrizLimites = new int[width.length][height];
        matrizPosiciones = new int[width.length][height];
        matrizAparecer = new int[width.length][height];
        matrizPerks = new int[width.length][height];
        file.close();
    }

    public void llenarMatrizTerreno() throws FileNotFoundException, IOException {
        BufferedReader arch = new BufferedReader(new FileReader(nombre));
        String linea, split[];
        int i = 0;
        while ((linea = arch.readLine()) != null) {
            split = linea.split("");
            for (int j = 0; j < matriz.length; j++) {
                String r = split[j];
                matriz[j][i] = r;
            }
            i++;
        }

        ImageIcon temp;
        for (int k = 0; k < matriz.length; k++) {
            for (int l = 0; l < matriz[k].length; l++) {
                temp = new ImageIcon("imagenes//tails//" + matriz[k][l] + ".png");
                matrizTerreno[k][l] = temp;
            }
        }
    }

    public void llenarMatrizPerks(String s) throws FileNotFoundException, IOException {
        BufferedReader arch = new BufferedReader(new FileReader(s));
        String linea, split[];
        int i = 0;
        while ((linea = arch.readLine()) != null) {
            split = linea.split("");
            for (int j = 0; j < matriz.length; j++) {
                String r = split[j];
                matriz[j][i] = r;
            }
            i++;
        }
        for (int k = 0; k < matriz.length; k++) {
            for (int l = 0; l < matriz[k].length; l++) {
                if (matriz[k][l].contains("b")) {
                    matrizPerks[k][l] = 1;
                } else {
                    if (matriz[k][l].contains("d")) {
                        matrizPerks[k][l] = 2;
                    } else {
                        if (matriz[k][l].contains("f")) {
                            matrizPerks[k][l] = 3;
                        } else {
                            if (matriz[k][l].contains("h")) {
                                matrizPerks[k][l] = 4;
                            } else {
                                if (matriz[k][l].contains("j")) {
                                    matrizPerks[k][l] = 5;
                                } else {
                                    if (matriz[k][l].contains("l")) {
                                        matrizPerks[k][l] = 6;
                                    } else {
                                        if (matriz[k][l].contains("n")) {
                                            matrizPerks[k][l] = 7;
                                        } else {
                                            matrizPerks[k][l] = 0;
                                        }
                                    }

                                }

                            }

                        }

                    }

                }

            }
        }
    }

    public void llenarMatrizLimites() {
        for (int k = 0; k < matriz.length; k++) {
            for (int l = 0; l < matriz[k].length; l++) {
                if (matriz[k][l].contains("0") || matriz[k][l].contains("a")
                        || matriz[k][l].contains("b") || matriz[k][l].contains("c") || matriz[k][l].contains("d")
                        || matriz[k][l].contains("f") || matriz[k][l].contains("m") || matriz[k][l].contains("e")) {
                    matrizLimites[k][l] = 0;
                } else {
                    matrizLimites[k][l] = 1;
                }
            }
        }
    }

    public void llenarMatrizAparecer() {
        for (int k = 0; k < matriz.length; k++) {
            for (int l = 0; l < matriz[k].length; l++) {
                if (matriz[k][l].contains("1")) {
                    matrizAparecer[k][l] = 1;
                } else {
                    matrizAparecer[k][l] = 0;
                }
            }
        }
    }

    public void llenarMatrizPosiciones() {
        for (int k = 0; k < matrizPosiciones.length; k++) {
            for (int l = 0; l < matrizPosiciones[k].length; l++) {
                matrizPosiciones[k][l] = 0;
            }
        }
    }

    public ImageIcon[][] getMatrizTerreno() throws FileNotFoundException, IOException {
        llenarMatrizTerreno();
        return matrizTerreno;
    }

    public int[][] getMatrizLimites() {
        llenarMatrizLimites();
        return matrizLimites;
    }

    public int[][] getMatrizPosiciones() {
        llenarMatrizPosiciones();
        return matrizPosiciones;
    }

    public int[][] getMatrizAparecer() {
        llenarMatrizAparecer();
        return matrizAparecer;
    }
    public int[][] getMatrizPerks(String s) throws FileNotFoundException, IOException {
        llenarMatrizPerks(s);
        return matrizPerks;
    }
}
