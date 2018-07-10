/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ottol.antigo;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *
 * @author Juliano
 */
public class GerarRetornaSeguencias {
    Integer  numeros[] = new Integer[61];
    Long count = 0l, Res_ent1 = 0l, Res_ent2 = 0l, aux = 0l;
    Long saida1  = null, saida2 = null, saida3 = null;
    Boolean encontrar = true, ver2 = true;
    int resultado[] = new int[60];   
    int quantidade = 0;
    ArrayList lista_resultados = new ArrayList();
//    
    public ArrayList calc(int inicio,int total,int qtd,int fim,int tipo){   
        quantidade = qtd;
        
        for(int i=0; i<60;i++){
            numeros[i]= i+1;
        }
        switch (tipo){
        
            case 3:
            busca_3(inicio, (total-quantidade), fim);
            break;
            case 4:
            busca_4(inicio, (total-quantidade), fim);
            break;
            case 5:
            busca_5(inicio, (total-quantidade), fim);
            break;
            case 6:
            busca_6(inicio, (total-quantidade), fim);
            break;
        }
        
        return lista_resultados;
  
    } 
    public void busca_3(int inicio,int fim, int profundidade){   
 
        if ( (profundidade + 1) >= quantidade)   
        for(int x = inicio; x <= fim; x++){   
            resultado[profundidade] = numeros[x];    
            
            count++;
            lista_resultados.add(new Object[]{resultado[0],resultado[1],resultado[2]});
            
            //lista_resultados.add(resultado[0] + "-" + resultado[1] + "-" + resultado[2]);
           // System.out.println(resultado[0] + "-" + resultado[1] + "-" + resultado[2]); 

         }
    else { 
        for(int x = inicio; x <= fim; x++){   
            
            resultado[profundidade] = numeros[x];   
            busca_3(x + 1,fim + 1,profundidade + 1);   
            
        }   
        }
    }
    
    public void busca_4(int inicio,int fim, int profundidade){   
 
        if ( (profundidade + 1) >= quantidade)   
        for(int x = inicio; x <= fim; x++){   
            resultado[profundidade] = numeros[x];    
            
            count++;
             lista_resultados.add(new Object[]{resultado[0],resultado[1],resultado[2],resultado[3]});
             System.out.println(resultado[0] + "-" + resultado[1] + "-" + resultado[2]); 
          }
    else { 
        for(int x = inicio; x <= fim; x++){   
            
            resultado[profundidade] = numeros[x];   
            busca_4(x + 1,fim + 1,profundidade + 1);   
            
        }   
        }
    }
    
    public void busca_5(int inicio,int fim, int profundidade){   
 
        if ( (profundidade + 1) >= quantidade)   
        for(int x = inicio; x <= fim; x++){   
            resultado[profundidade] = numeros[x];    
            
            count++;
            lista_resultados.add(new Object[]{resultado[0],resultado[1],resultado[2],resultado[3],resultado[5]});
            System.out.println(resultado[0] + "-" + resultado[1] + "-" + resultado[2]); 

         }
    else { 
        for(int x = inicio; x <= fim; x++){   
            
            resultado[profundidade] = numeros[x];   
            busca_5(x + 1,fim + 1,profundidade + 1);   
            
        }   
        }
    }
    
    public void busca_6(int inicio,int fim, int profundidade){   
 
        if ( (profundidade + 1) >= quantidade)   
        for(int x = inicio; x <= fim; x++){   
            resultado[profundidade] = numeros[x];    
            
                count++;
                lista_resultados.add(new Object[]{resultado[0],resultado[1],resultado[2],resultado[3],resultado[4],resultado[5]});
                System.out.println(resultado[0] + "-" + resultado[1] + "-" + resultado[2]); 

         }
    else { 
        for(int x = inicio; x <= fim; x++){   
            
            resultado[profundidade] = numeros[x];   
            busca_6(x + 1,fim + 1,profundidade + 1);   
            
        }   
        }
    }
}
