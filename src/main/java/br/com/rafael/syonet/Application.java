package br.com.rafael.syonet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import br.com.rafael.syonet.config.JacksonConfig;

/**
 * Classe de start da aplicação.
 *
 * @author Rafael Braga
 */
@SpringBootApplication
@Import(value = { JacksonConfig.class })
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
