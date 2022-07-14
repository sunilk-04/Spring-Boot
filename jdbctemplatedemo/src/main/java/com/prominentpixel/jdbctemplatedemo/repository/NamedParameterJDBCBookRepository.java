package com.prominentpixel.jdbctemplatedemo.repository;

import com.prominentpixel.jdbctemplatedemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class NamedParameterJDBCBookRepository extends JDBCBookRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public int update(Book book) {
        return namedParameterJdbcTemplate.update("UPDATE books SET price = :price WHERE id = :id", new BeanPropertySqlParameterSource(book));
    }

    @Override
    public Optional<Book> findById(Long id) {
        return namedParameterJdbcTemplate.queryForObject("SELECT * FROM books WHERE id = :id", new MapSqlParameterSource("id", id), (rs, rowNum) -> Optional.of(new Book(rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price"))));
    }

    @Override
    public List<Book> findByNameAndPrice(String name, BigDecimal price) {

        MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
        mapSqlParameterSource.addValue("name", "%" + name + "%");
        mapSqlParameterSource.addValue("price", price);

        return namedParameterJdbcTemplate.query("SELECT * FROM books WHERE name LIKE :name AND price <= :price", mapSqlParameterSource, (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price")));
    }

}
