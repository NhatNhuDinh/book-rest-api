package com.book.bookrestapi.repository;

import com.book.bookrestapi.domain.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Tìm sách theo tiêu đề chứa từ khóa (không phân biệt hoa thường)
     */
    List<Book> findByTitleContainingIgnoreCase(String title);

    /**
     * Tìm sách theo ID tác giả
     */
    List<Book> findByAuthorId(Long authorId);

    /**
     * Tìm sách theo tiêu đề chứa từ khóa và ID tác giả
     */
    List<Book> findByTitleContainingIgnoreCaseAndAuthorId(String title, Long authorId);
}
