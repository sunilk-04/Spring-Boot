package com.prominentpixel.cachedemo.repository;

import com.prominentpixel.cachedemo.entity.Book;

public interface BookRepository {

    Book getByIsbn(String isbn) throws InterruptedException;

}
