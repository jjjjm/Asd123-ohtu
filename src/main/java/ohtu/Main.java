package ohtu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({ "prod-context.xml", "dev-context.xml" })
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication application = new SpringApplication(Main.class);
        application.run(args);
    }
   
}
