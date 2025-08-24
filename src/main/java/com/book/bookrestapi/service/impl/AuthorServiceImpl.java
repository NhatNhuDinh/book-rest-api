package com.book.bookrestapi.service.impl;

import com.book.bookrestapi.domain.author.Author;
import com.book.bookrestapi.dto.author.AuthorCreateRequest;
import com.book.bookrestapi.dto.author.AuthorResponse;
import com.book.bookrestapi.dto.author.AuthorUpdateRequest;
import com.book.bookrestapi.repository.AuthorRepository;
import com.book.bookrestapi.service.AuthorService;
import com.book.bookrestapi.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    public AuthorResponse create(AuthorCreateRequest request) {
        // Kiểm tra tác giả đã tồn tại
        if (authorRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Tác giả với tên '" + request.getName() + "' đã tồn tại");
        }

        Author author = new Author();
        author.setName(request.getName());
        author.setCountry(request.getCountry());

        Author savedAuthor = authorRepository.save(author);
        return toDto(savedAuthor);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorResponse getById(Long id) {
        Author author = getAuthorEntityById(id);
        return toDto(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AuthorResponse> list(String keyword, Pageable pageable) {
        Page<Author> authors;
        if (StringUtils.hasText(keyword)) {
            authors = authorRepository.findByNameContainingIgnoreCase(keyword, pageable);
        } else {
            authors = authorRepository.findAll(pageable);
        }
        return authors.map(this::toDto);
    }

    @Override
    public AuthorResponse update(Long id, AuthorUpdateRequest request) {
        Author author = getAuthorEntityById(id);

        // Kiểm tra tên mới có trùng với tác giả khác không
        if (StringUtils.hasText(request.getName()) && 
            !request.getName().equalsIgnoreCase(author.getName()) &&
            authorRepository.existsByNameIgnoreCase(request.getName())) {
            throw new IllegalArgumentException("Tác giả với tên '" + request.getName() + "' đã tồn tại");
        }

        if (StringUtils.hasText(request.getName())) {
            author.setName(request.getName());
        }
        if (StringUtils.hasText(request.getCountry())) {
            author.setCountry(request.getCountry());
        }

        Author updatedAuthor = authorRepository.save(author);
        return toDto(updatedAuthor);
    }

    @Override
    public void delete(Long id) {
        Author author = getAuthorEntityById(id);
        authorRepository.delete(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorEntityById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy tác giả với ID: " + id));
    }

    private AuthorResponse toDto(Author author) {
        return new AuthorResponse(
                author.getId(),
                author.getName(),
                author.getCountry()
        );
    }
}
