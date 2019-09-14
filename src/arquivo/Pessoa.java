package arquivo;

public class Pessoa {
    String nome;
    int entrada;
    int dia;
    int mes;

    public Pessoa() {
    }
    

    public Pessoa(String nome, int entrada, int dia, int mes) {
        this.nome = nome;
        this.entrada = entrada;
        this.dia = dia;
        this.mes = mes;
    }
    int soma;


    
    public int getEntrada() {
        return entrada;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setEntrada(int entrada, int dia, int mes) {
        this.entrada = entrada;
        this.dia = dia;
        this.mes = mes;
    }

    public int getSoma() {
        return soma;
    }

    public void setSoma(int soma) {
        this.soma = soma;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
