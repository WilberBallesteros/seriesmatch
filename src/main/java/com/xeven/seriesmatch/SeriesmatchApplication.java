package com.xeven.seriesmatch;

import com.xeven.seriesmatch.model.DatosSerie;
import com.xeven.seriesmatch.service.ConsumoAPI;
import com.xeven.seriesmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SeriesmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SeriesmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		var consumoApi = new ConsumoAPI();
		var json = consumoApi.obtenerDatos("https://www.omdbapi.com/?t=Mr+Robot&apikey=fbb8ae8");
		//var json = consumoApi.obtenerDatos("https://coffee.alexflipnote.dev/random.json");

		System.out.println(json);

		ConvierteDatos conversor = new ConvierteDatos();
		var datos = conversor.obtenerDatos(json, DatosSerie.class); //quiero un datosSerie a partir de este json
		System.out.println(datos);
	}
}
