package io.prargra.leaning.h2learning;

import io.prargra.leaning.h2learning.doa.CartDAO;
import io.prargra.leaning.h2learning.doa.PriceDAO;
import io.prargra.leaning.h2learning.doa.ProductDAO;
import io.prargra.leaning.h2learning.domain.Price;
import io.prargra.leaning.h2learning.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
@AllArgsConstructor
public class H2LearningApplication {

    private ProductDAO productDAO;
    private PriceDAO priceDAO;
    private CartDAO cartDAO;

    private JdbcTemplate template;

    public static void main(String[] args) {
        SpringApplication.run(H2LearningApplication.class, args);
    }

    @Bean
    CommandLineRunner runner() {
        return args->{
            productDAO.addProduct(new Product(1, "Apple", "Juicy Apples"));
            productDAO.addProduct(new Product(2, "Naan", "On sale"));
            productDAO.addProduct(new Product(3, "Dumpling", "Delicious"));
            productDAO.updateProduct(new Product(1, "Orange", "Freshy Oranges"));

            priceDAO.addPrice(new Price(1, 1, 1.99f, 0.9f));
            priceDAO.addPrice(new Price(2, 2, 0.99f, 1));
            priceDAO.addPrice(new Price(3, 3, 0.19f, 1));

            cartDAO.addProduct(1);
            cartDAO.addProduct(1);
            cartDAO.addProduct(2);
            cartDAO.addProduct(3);
            cartDAO.removeProduct(2);

            float total = cartDAO.getCartTotal();
            cartDAO.printCart();


        };
    }

}
