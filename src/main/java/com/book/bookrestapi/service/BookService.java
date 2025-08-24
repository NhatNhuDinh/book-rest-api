package com.book.bookrestapi.service;

import com.book.bookrestapi.dto.book.BookCreateRequest;
import com.book.bookrestapi.dto.book.BookResponse;
import com.book.bookrestapi.dto.book.BookUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {

    /**
     * Tạo mới sách
     */
    BookResponse create(BookCreateRequest request);

    /**
     * Lấy thông tin sách theo ID
     */
    BookResponse getById(Long id);

    /**
     * Lấy danh sách sách với filter và phân trang
     */
    Page<BookResponse> list(String keyword, Long authorId, Pageable pageable);

    /**
     * Cập nhật thông tin sách
     */
    BookResponse update(Long id, BookUpdateRequest request);

    /**
     * Xóa sách
     */
    void delete(Long id);
}
