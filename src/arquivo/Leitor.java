package arquivo;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Leitor {
    public static int contains (ArrayList<Pessoa> pessoa, String x){
        for (int i=0 ; i<pessoa.size() ; i++){
            if (pessoa.get(i).nome.equals(x)){
                return i;
            }
        }
        return -1;
    }
    public static void nomes(ArrayList<Pessoa> pessoa) throws IOException{
        FileInputStream anome;
        try {
            anome = new FileInputStream("nomes.txt");
            InputStreamReader input = new InputStreamReader(anome);
            BufferedReader br = new BufferedReader(input);
            
            String linha;
            do{
                linha = br.readLine();
                Pessoa p = new Pessoa();
                p.setNome (linha);
                pessoa.add(p);
            }while (linha != null);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Leitor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main (String args[]){
        int debs = 0;
        try {
            FileInputStream arquivo = new FileInputStream("aiesec.txt");
            InputStreamReader input = new InputStreamReader(arquivo);
            BufferedReader br = new BufferedReader(input);
            ArrayList<Pessoa> p = new ArrayList<Pessoa>();
            nomes(p);
            String linha;
            System.out.println("Oii");
            do{
                linha = br.readLine();
                if (linha !=null){
                    String[] palavras = linha.split("\t");
                    String[] dh = palavras[2].split(" ");
                    String[] dia = dh[0].split("/");
                    int d[] = new int[3];
                    
                    for (int i = 0 ; i < dia.length ; i++){
                        d[i] = Integer.parseInt(dia[i]);
                    }
                    String[] hora = dh[1].split(":");
                    int h = (Integer.parseInt(hora[0])*60) + Integer.parseInt(hora[1]);
                    
                    System.out.println("-----------------------------------------------------------");
                    System.out.println(debs + "Nome: " + palavras[0]);
                    debs++;
                    int n = contains(p, palavras[0]);
                    if (n == -1){
                        Pessoa x = new Pessoa(palavras[0], h, d[0], d[1]);
                        p.add (x);
                        System.out.println("Entrou");
                    }else {
                        if (palavras[1].equals("Escritório") && p.get(n).getEntrada() == 0){
                            p.get(n).setEntrada(h, d[0], d[1]);
                            System.out.println("Entrou");
                        } else if (palavras[1].equals("Escritório") && (d[0] != p.get(n).getDia() || d[1] != p.get(n).getMes())){
                            p.get(n).setEntrada(h, d[0], d[1]);
                            System.out.println("Entrou");
                        }else if (palavras[1].equals("Externa")){
                            if (d[0] == p.get(n).getDia() && d[1] == p.get(n).getMes()){
                                p.get(n).setSoma (p.get(n).getSoma() + (h-p.get(n).getEntrada()));
                                p.get(n).setEntrada(0,0,0);
                                System.out.println("Saiu");
                            } else if (p.get(n).getEntrada() == 0){
                                System.out.println("Saiu sem entrar");
                            } else{
                                System.out.println("Saiu em outro dia");
                            }
                        } else {
                            System.out.println("Ignorar entrada");
                        }
                    } 
                }
            }while (linha != null);
            
            
            for (Pessoa x : p){
                if (x.soma%60 > 10){
                    System.out.println(x.nome+"\t"+x.soma/60 + ":" +x.soma%60+"\t horas");
                } else {
                    System.out.println(x.nome+"\t"+x.soma/60 + ":0" +x.soma%60+"\t horas");
                        }
            }
        } catch (IOException e) {
            System.err.println("IOException");
        } catch (NumberFormatException x){
            System.err.println("Number format exception");
        }
    }
    

}