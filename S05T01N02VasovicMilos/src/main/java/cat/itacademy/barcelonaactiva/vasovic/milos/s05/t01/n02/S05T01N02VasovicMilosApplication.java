package cat.itacademy.barcelonaactiva.vasovic.milos.s05.t01.n02;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(title = "Flowers", version = "1.0.0"),
		servers = {@Server(url = "http://localhost:9000"), @Server(url = "http://localhost:9004")}
)
public class S05T01N02VasovicMilosApplication {

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(S05T01N02VasovicMilosApplication.class, args);
	}

//	localhost:9000/swagger-ui/index.html

}
