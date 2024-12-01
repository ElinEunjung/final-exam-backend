package no.kristiania;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SequenceResetter implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SequenceResetter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        String customerSql = "ALTER SEQUENCE customer_seq RESTART WITH 1;";
        String customerAddressSql = "ALTER SEQUENCE customer_address_seq RESTART WITH 1;";
        String orderSql = "ALTER SEQUENCE order_seq RESTART WITH 1;";
        String productSql = "ALTER SEQUENCE product_seq RESTART WITH 1;";
        String orderProductSql = "ALTER SEQUENCE order_product_seq RESTART WITH 1;";
        jdbcTemplate.execute(customerSql);
        jdbcTemplate.execute(customerAddressSql);
        jdbcTemplate.execute(orderSql);
        jdbcTemplate.execute(productSql);
        jdbcTemplate.execute(orderProductSql);
    }
}
