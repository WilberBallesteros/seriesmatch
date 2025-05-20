package com.xeven.seriesmatch.principal;

import com.xeven.seriesmatch.model.DatosEpisodio;
import com.xeven.seriesmatch.model.DatosSerie;
import com.xeven.seriesmatch.model.DatosTemporadas;
import com.xeven.seriesmatch.model.Episodio;
import com.xeven.seriesmatch.service.ConsumoAPI;
import com.xeven.seriesmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

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
        //temporadas.forEach(System.out::println); //imprimimos lo q tenemos en la lista

        //mostrar solo el titulo de los episodios para las temporadas
//        for (int i = 0; i < datos.totalTemporadas(); i++) {
//            List<DatosEpisodio> episodiosTemporada = temporadas.get(i).episodios(); //traer el indice de la lista de episodios
//            for (int j = 0; j < episodiosTemporada.size(); j++) { //itera los episodios para traer los titulos de los episodios
//                System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        //expresiones lamba, ayuda a la legibilidad
        //con esta linea sustituimos los dos for anteriores

        //temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo()))); //primera t es el argumento, despues de la flecha es el cuerpo de la funcion lo q quiero q haga es q traiga los episodios, luego en otro foreach, imprimo los titulos de esos episodios q traje antes

        //Convertir todas las informaciones a una lista del tipo datos episodio

        List<DatosEpisodio> datosEpisodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream())
                .collect(Collectors.toList()); //creamos una lista mutable

        //top 5 episodios

        System.out.println("Top de los 5 mejores episodios");
        datosEpisodios.stream()
                .filter(e -> !e.evaluacion().equalsIgnoreCase("N/A")) //excluimos las evaluaciones NA q no tienen evaluacion
                .sorted(Comparator.comparing(DatosEpisodio::evaluacion).reversed())   //de menor a mayor el sorted, pero el comparator nos ayuda a comparar cada evaluacio, el reversed para q los traiga de > a <
                .limit(5)
                .forEach(System.out::println);

        //convirtiendo los datos a una list ade tipo episodio

        List<Episodio> episodios = temporadas.stream()
                .flatMap(t -> t.episodios().stream()
                        .map(d -> new Episodio(t.numeroTemporada(), d)))  //transformar cada dato de tipo episdio a nuevo episodio
                        .collect(Collectors.toList());

        episodios.forEach(System.out::println);

    }
}
