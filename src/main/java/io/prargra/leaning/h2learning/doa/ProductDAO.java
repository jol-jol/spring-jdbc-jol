package io.prargra.leaning.h2learning.doa;

import io.prargra.leaning.h2learning.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@AllArgsConstructor
public class ProductDAO {

    private NamedParameterJdbcTemplate template;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String sql =
                "CREATE TABLE products (" +
                        "id INT PRIMARY KEY, " +
                        "name VARCHAR(50)," +
                        "description VARCHAR(100))";
        try {
            jdbcTemplate.execute(sql);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int addProduct(Product product) {
        String sql =
                "INSERT INTO products VALUES " +
                        "(:id, :name, :description)";
        return template.update(sql,
                new BeanPropertySqlParameterSource(product));
    }

    public int updateProduct(Product product) {
        String sql =
                "UPDATE products SET name = :name," +
                        "description = :description " +
                        "WHERE id = :id";
        return template.update(sql,
                new BeanPropertySqlParameterSource(product));
    }
}
