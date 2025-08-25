package com.book.bookrestapi.repository;

import com.book.bookrestapi.domain.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Kiểm tra tác giả có tồn tại theo tên (không phân biệt hoa thường)
     */
    boolean existsByNameIgnoreCase(String name);

    /**
     * Tìm tác giả theo tên (không phân biệt hoa thường)
     */
    Optional<Author> findByNameIgnoreCase(String name);

    /**
     * Tìm tác giả theo tên chứa từ khóa (không phân biệt hoa thường)
     */
    List<Author> findByNameContainingIgnoreCase(String name);
}
