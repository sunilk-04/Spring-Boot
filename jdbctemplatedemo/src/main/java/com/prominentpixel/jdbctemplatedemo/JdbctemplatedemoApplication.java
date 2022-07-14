package com.prominentpixel.jdbctemplatedemo;

import com.prominentpixel.jdbctemplatedemo.entity.Book;
import com.prominentpixel.jdbctemplatedemo.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@SpringBootApplication
public class JdbctemplatedemoApplication implements CommandLineRunner {

    private static final Logger LOGGER = Logger.getLogger(JdbctemplatedemoApplication.class.getName());

    @Autowired
    // @Qualifier("JDBCBookRepository")
    @Qualifier("namedParameterJDBCBookRepository")
    private BookRepository bookRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(JdbctemplatedemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        LOGGER.info("Starting an application...");
        this.runJDBC();
    }

    void runJDBC() {
        LOGGER.info("Creating tables for testing...");

        this.jdbcTemplate.execute("DROP TABLE IF EXISTS books;");
        this.jdbcTemplate.execute("CREATE TABLE books(id SERIAL, name VARCHAR(255), price NUMERIC(15, 2));");

        List<Book> books = Arrays.asList(new Book("Thinking in Java", new BigDecimal("570.25")),
				new Book("Java design patterns", new BigDecimal("150.00")),
				new Book("Getting started Java EE development", new BigDecimal("370.50")),
				new Book("Head First Android Development", new BigDecimal("200.50")));

        LOGGER.info("[SAVE]");
        books.forEach(book -> {
            LOGGER.info("Saving... " + book.getName());
            this.bookRepository.save(book);
        });

        LOGGER.info("[COUNT] Total books : " + this.bookRepository.count());

        LOGGER.info("[FIND_ALL] " + this.bookRepository.findAll());

        LOGGER.info("[FIND_BY_ID] :2");
        Book book = this.bookRepository.findById(2L).orElseThrow(IllegalArgumentException::new);
        LOGGER.info(book.getName());

        LOGGER.info("[FIND_BY_NAME_AND_PRICE] : LIKE '%Java%' AND price <= 200");
        LOGGER.info(this.bookRepository.findByNameAndPrice("Java", new BigDecimal(200)).toString());

        LOGGER.info("[GET_NAME_BY_ID] : 1 : " + this.bookRepository.getNameById(1L));

        LOGGER.info("[UPDATE] : 2 : 399.99");
        book.setPrice(new BigDecimal("399.00"));
        LOGGER.info("Rows affected : " + this.bookRepository.update(book));

        LOGGER.info("[DELETE] : 3");
        LOGGER.info("Rows affected : " + this.bookRepository.deleteById(3L));

        LOGGER.info("[FIND_ALL] " + this.bookRepository.findAll());
    }
}
