package br.com.aleanse.clonenetflix.principal;

import br.com.aleanse.clonenetflix.service.ConsumoApi;

import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private  ConsumoApi consumo = new ConsumoApi();
    private final String ENDEREÇO = "http://www.omdbapi.com/?t=";
    private final String API_KEY = "7c3783a2";
    public void exibeMenu(){
        System.out.println("digite o nome da serie");
        var nomeSerie = leitura.nextLine();
        var json = consumo.obterDados( ENDEREÇO + nomeSerie.replace(" ","+") + API_KEY);

    }


}
