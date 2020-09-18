/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.ot.antigo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author juliano.ezequiel
 */
public class GerarAleatorio {
    
    
    public String Valor() throws InterruptedException {
    //ArrayList s = new ArrayList();
    List list = new ArrayList();
    String saida = "0-0-0-0-0-0";
    String novo = null;
    Random ger = new Random();
    
    for(int z = 0; z < 1;z++){
       
        for(int i=0; i < 6; i++){        
        int num = ger.nextInt(61);
        list.add(num);
        }
        Collections.sort(list);
        
        for(int s=0;s<5;s++){
            if(list.get(s).equals(list.get(s+1))){
            list.clear();           
            novo = Valor();
            return novo;
            }
            }
        }
        
        if(!list.get(0).equals(0)){
        saida = list.get(0).toString()+"-"+list.get(1).toString()+"-"+list.get(2).toString()+"-"+
                list.get(3).toString()+"-"+list.get(4).toString()+"-"+list.get(5).toString();
        list.clear();
        return saida;
        }
        list.clear();
        novo = Valor();
        return novo;   
    }  
    
    public Integer[] Valor2() throws InterruptedException {
    //ArrayList s = new ArrayList();
    List list = new ArrayList();
    Integer[] saida = {0,0,0,0,0,0};
    Integer[] novo = null;
    Random ger = new Random();
    
    for(int z = 0; z < 1;z++){
       
        for(int i=0; i < 6; i++){        
        int num = ger.nextInt(61);
        list.add(num);
        }
        Collections.sort(list);
        
        for(int s=0;s<5;s++){
            if(list.get(s).equals(list.get(s+1))){
            list.clear();           
            novo = Valor2();
            return novo;
            }
            }
        }
        
        if(!list.get(0).equals(0)){
            
        saida[0] = (Integer) list.get(0);
        saida[1] = (Integer) list.get(1);
        saida[2] = (Integer) list.get(2);
        saida[3] = (Integer) list.get(3);
        saida[4] = (Integer) list.get(4);
        saida[5] = (Integer) list.get(5);
        list.clear();
        return saida;
        }
        list.clear();
        novo = Valor2();
        return novo;   
    }  
    
}

 

    
