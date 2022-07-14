package com.prominentpixel.jdbctemplatedemo.repository;

import com.prominentpixel.jdbctemplatedemo.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class JDBCBookRepository implements BookRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("SELECT COUNT(*) from books", Integer.class);
    }

    @Override
    public int save(Book book) {
        return jdbcTemplate.update("INSERT INTO books (name, price) VALUES(?,?)", book.getName(), book.getPrice());
    }

    @Override
    public int update(Book book) {
        return jdbcTemplate.update("UPDATE books SET price = ? WHERE id = ?", book.getPrice(), book.getId());
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("DELETE FROM books WHERE id = ?", id);
    }

    @Override
    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM books", (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price")));
    }

    @Override
    public List<Book> findByNameAndPrice(String name, BigDecimal price) {
        return jdbcTemplate.query("SELECT * FROM books WHERE name LIKE ? AND price <= ?", new Object[]{"%" + name + "%", price}, (rs, rowNum) -> new Book(rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price")));
    }

    @Override
    public Optional<Book> findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM books WHERE id = ?", new Object[]{id}, (rs, rowNum) -> Optional.of(new Book(rs.getLong("id"), rs.getString("name"), rs.getBigDecimal("price"))));
    }

    @Override
    public String getNameById(Long id) {
        return jdbcTemplate.queryForObject("SELECT name FROM books WHERE id = ?", new Object[]{id}, String.class);
    }
}
