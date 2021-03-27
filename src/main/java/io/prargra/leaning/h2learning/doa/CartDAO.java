package io.prargra.leaning.h2learning.doa;

import io.prargra.leaning.h2learning.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;

@Repository
@AllArgsConstructor
public class CartDAO {
    private NamedParameterJdbcTemplate template;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String sql =
                "CREATE TABLE cart (" +
                        "id INT PRIMARY KEY AUTO_INCREMENT, " +
                        "product_id INT REFERENCES products(id))";
        try {
            jdbcTemplate.execute(sql);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void addProduct(int productId) {
        String sql =
                "INSERT INTO cart (product_id) VALUES (" +
                        productId + ")";
        jdbcTemplate.execute(sql);
    }

    public void removeProduct(int productId) {
        String sql =
                "DELETE FROM cart WHERE product_id =" +
                        productId;
        jdbcTemplate.execute(sql);
    }

    public List<Product> getProducts() {
        String sql = "SELECT * " +
                "FROM cart " +
                "JOIN products " +
                "ON products.id = cart.product_id";
        return jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<>(Product.class));
    }

    public float getCartTotal() {
        String sql = "SELECT sum(price * discount)" +
                "FROM cart " +
                "JOIN prices " +
                "ON prices.product_id = cart.product_id";
        return jdbcTemplate.queryForObject(sql, Float.class);
    }

    public void printCart() {
        this.getProducts().forEach(System.out::println);
        System.out.println("The total for your purchase is: " +
                this.getCartTotal());
    }
}
