package com.book.bookrestapi.repository;

import com.book.bookrestapi.domain.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Tìm sách theo tiêu đề chứa từ khóa (không phân biệt hoa thường) với phân trang
     */
    Page<Book> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    /**
     * Tìm sách theo ID tác giả với phân trang
     */
    Page<Book> findByAuthorId(Long authorId, Pageable pageable);

    /**
     * Tìm sách theo tiêu đề chứa từ khóa và ID tác giả với phân trang
     */
    Page<Book> findByTitleContainingIgnoreCaseAndAuthorId(String title, Long authorId, Pageable pageable);
}
