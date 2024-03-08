package isthatkirill.vkproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class VkProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(VkProjectApplication.class, args);
    }

}
