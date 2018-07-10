/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ottol.antigo;

import java.util.ArrayList;
import javax.swing.JLabel;

/**
 *
 * @author juliano.ezequiel
 */
public class Gerar_Sequencial implements Runnable {

    Long count = new Long(0l);
    Object jogo = null;
    Integer resultado[] = new Integer[60];
    int quantidade = 6;
    Integer numeros[] = new Integer[61];
    JLabel jLblTotal = null;
    JLabel jLabelAnalizado = null;
    ArrayList jgAprovado = new ArrayList();

    @Override
    public void run() {

    }

//    public void busca_6(int inicio, int fim, int profundidade) throws Exception {
//        //  while (Start) {
//
//        count = 0l;
//        if ((profundidade + 1) >= quantidade) {
//            for (int x = inicio; x <= fim; x++) {
//                resultado[profundidade] = numeros[x];
//
//                count++;
//                jogo = resultado[0].toString() + "-" + resultado[1].toString() + "-" + resultado[2].toString() + "-"
//                        + resultado[3].toString() + "-" + resultado[4].toString() + "-" + resultado[5].toString();
//                jLblTotal.setText(contador.toString());
//                jLabelAnalizado.setText(jogo.toString());
//                Integer[] numteste = {resultado[0], resultado[1], resultado[2], resultado[3], resultado[4], resultado[5]};
//                Boolean ap = analize2(resultado);
//
//                if (ap) {
//                    addlist();
//                    contadoraprovados++;
//                    jgAprovado.add(jogo);
//                    jLblTotalAprovados.setText(contadoraprovados.toString());
//                    System.out.println(contadoraprovados + " -- " + jogo);
//                }
//                if (jgAprovado.size() > 100 || jogo.toString().equals("55-56-57-58-59-60")) {
//                    grava_dados_base.gravar_jogos_possiveis(jgAprovado);
//                    jgAprovado.clear();
//
//                }
//
//            }
//        } else {
//            for (int x = inicio; x <= fim; x++) {
//
//                resultado[profundidade] = numeros[x];
//                busca_6(x + 1, fim + 1, profundidade + 1);
//
//            }
//        }
//    }

}
