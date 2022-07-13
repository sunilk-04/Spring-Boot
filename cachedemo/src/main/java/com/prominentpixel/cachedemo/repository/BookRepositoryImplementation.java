package com.prominentpixel.cachedemo.repository;

import com.prominentpixel.cachedemo.entity.Book;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class BookRepositoryImplementation implements BookRepository {

    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        this.simulateService();
        return new Book(isbn, "Learn Java programming");
    }

    private void simulateService() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }
}
