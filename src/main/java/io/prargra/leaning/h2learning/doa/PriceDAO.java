package io.prargra.leaning.h2learning.doa;

import io.prargra.leaning.h2learning.domain.Price;
import io.prargra.leaning.h2learning.domain.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
@AllArgsConstructor
public class PriceDAO {
    private NamedParameterJdbcTemplate template;
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        String sql =
                "CREATE TABLE prices (" +
                        "price_id INT PRIMARY KEY, " +
                        "product_id INT UNIQUE REFERENCES products(id)," +
                        "price float," +
                        "discount float)";
        try {
            jdbcTemplate.execute(sql);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public int addPrice(Price price) {
        String sql =
                "INSERT INTO prices VALUES " +
                        "(:priceId, :productId, :price, :discount)";
        return template.update(sql,
                new BeanPropertySqlParameterSource(price));
    }

    public int updatePrice(Price price) {
        String sql =
                "UPDATE products SET price = :price," +
                        "discount = :discount " +
                        "WHERE price_id = :priceId";
        return template.update(sql,
                new BeanPropertySqlParameterSource(price));
    }
}
