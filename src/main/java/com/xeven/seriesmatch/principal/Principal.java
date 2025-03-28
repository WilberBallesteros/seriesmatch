package com.xeven.seriesmatch.principal;

import com.xeven.seriesmatch.model.DatosSerie;
import com.xeven.seriesmatch.model.DatosTemporadas;
import com.xeven.seriesmatch.service.ConsumoAPI;
import com.xeven.seriesmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoApi = new ConsumoAPI();
    private final String URL_BASE = "https://www.omdbapi.com/?t=";
    private  final String API_KEY = "&apikey=fbb8ae8";
    private ConvierteDatos conversor = new ConvierteDatos();

    //menu para q el usuario ingrese nombre de la serie
    public void muestrarElMenu() {
        //Busca los datos generales de la serie
        System.out.println("Por favor escribe el nombre de la serie que deseas buscar: ");
        var nombreSerie = teclado.nextLine();
        var json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + API_KEY);
        var datos = conversor.obtenerDatos(json, DatosSerie.class);
        System.out.println(datos);

        //busca los datos de todas las temporadas
        List<DatosTemporadas> temporadas = new ArrayList<>();
        for (int i = 1; i <= datos.totalTemporadas() ; i++) { //inicia en 1 porq inicia en temporada 1
            json = consumoApi.obtenerDatos(URL_BASE + nombreSerie.replace(" ", "+") + "&Season=" + i +API_KEY); //la i va cambiando de temporada
            var datosTemporadas = conversor.obtenerDatos(json, DatosTemporadas.class);
            temporadas.add(datosTemporadas);
        }
        temporadas.forEach(System.out::println); //imprimimos lo q tenemos en la lista

    }
}
