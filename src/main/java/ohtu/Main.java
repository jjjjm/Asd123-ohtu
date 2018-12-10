package ohtu;

import ohtu.handlers.ConnectionHandler;
import ohtu.handlers.DatabaseHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ImportResource({"prod-context.xml", "dev-context.xml"})
public class Main {

    public static void main(String[] args) throws Exception {
        // before programs starts... create database tables if they do not exist
        ConnectionHandler connectionHandler = new ConnectionHandler(System.getenv("JDBC_DATABASE_URL"), System.getenv("DB_USER"), System.getenv("DB_PASSWORD"));
        DatabaseHandler dbHandler = new DatabaseHandler(connectionHandler.getDatabaseConnection());
        dbHandler.initDatabase();
        // now the program is ready to start
        SpringApplication application = new SpringApplication(Main.class);
        application.run(args);
    }

}
