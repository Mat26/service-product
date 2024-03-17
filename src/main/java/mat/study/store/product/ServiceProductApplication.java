package mat.study.store.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class ServiceProductApplication {

  public static void main(String[] args) {
    SpringApplication.run(ServiceProductApplication.class, args);
  }

}
