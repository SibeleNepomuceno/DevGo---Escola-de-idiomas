package sistema.devgo.java;

/* @author Sibele */
public class Plano {

    private long cod_plano;
    private String nomePlano;
    private double preco;
    private long cod_periodo;
    private long cod_idioma;
    
    

    //Construtores
    public Plano() {
    }

    
    public Plano(long cod_plano,double preco, String nomePlano, long cod_periodo,long cod_idioma) {
        this.cod_idioma = cod_idioma;
        this.cod_periodo = cod_periodo;
        this.preco = preco;
        this.nomePlano = nomePlano;
        this.cod_plano=cod_plano;
    }
   
    //Get e Set de Idioma
    public long getCod_idioma() {
        return cod_idioma;
    }
    public void setCod_idioma(long cod_idioma) {
        this.cod_idioma = cod_idioma;
    }
    
    //Get e Set de Preço
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }

    //Get e Set do Nome dos planos
    public String getNomePlano() {
        return nomePlano;
    }
    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    //Get e Set do Identificador do plano
    public long getCod_plano() {
        return cod_plano;
    }
    public void setCod_plano(long cod_plano) {
        this.cod_plano = cod_plano;
    }

    //Get e Set do periodo
    public long getCod_periodo() {
        return cod_periodo;
    }
    public void setCod_Periodo(long cod_periodo) {
        this.cod_periodo = cod_periodo;
    }

}
