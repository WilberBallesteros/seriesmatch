package com.xeven.seriesmatch;

import com.xeven.seriesmatch.model.DatosEpisodio;
import com.xeven.seriesmatch.model.DatosSerie;
import com.xeven.seriesmatch.model.DatosTemporadas;
import com.xeven.seriesmatch.principal.Principal;
import com.xeven.seriesmatch.service.ConsumoAPI;
import com.xeven.seriesmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SeriesmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SeriesmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.muestrarElMenu();
	}
}
