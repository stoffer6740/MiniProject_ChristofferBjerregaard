package dk.cbjerregaard.miniproject.Application;

/**
 * Created by Christoffer on 11-05-2015.
 */
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@ComponentScan({"dk.cbjerregaard.miniproject.Controllers", "dk.cbjerregaard.miniproject.Filters"})
@ImportResource("classpath:resources/applicationContext.xml")
@SpringBootApplication
public class Server {
    public static void main(String[] args) {
        SpringApplication.run(Server.class, args);
    }
}