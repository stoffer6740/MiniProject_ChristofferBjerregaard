package dk.cbjerregaard.miniproject.Application;

/**
 * Created by Christoffer on 11-05-2015.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"dk.cbjerregaard.miniproject.Controllers", "dk.cbjerregaard.miniproject.Filters"})
@SpringBootApplication
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}