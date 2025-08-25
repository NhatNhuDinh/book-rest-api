package com.book.bookrestapi.service;

import com.book.bookrestapi.domain.author.Author;
import com.book.bookrestapi.dto.author.AuthorCreateRequest;
import com.book.bookrestapi.dto.author.AuthorResponse;
import com.book.bookrestapi.dto.author.AuthorUpdateRequest;

import java.util.List;

public interface AuthorService {

    /**
     * Tạo mới tác giả
     */
    AuthorResponse create(AuthorCreateRequest request);

    /**
     * Lấy thông tin tác giả theo ID
     */
    AuthorResponse getById(Long id);

    /**
     * Lấy danh sách tác giả với filter
     */
    List<AuthorResponse> list(String keyword);

    /**
     * Cập nhật thông tin tác giả
     */
    AuthorResponse update(Long id, AuthorUpdateRequest request);

    /**
     * Xóa tác giả
     */
    void delete(Long id);

    /**
     * Lấy entity Author theo ID (cho internal use)
     */
    Author getAuthorEntityById(Long id);
}
