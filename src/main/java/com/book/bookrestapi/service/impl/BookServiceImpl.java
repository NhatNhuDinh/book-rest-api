package com.book.bookrestapi.service.impl;

import com.book.bookrestapi.domain.book.Book;
import com.book.bookrestapi.dto.book.BookCreateRequest;
import com.book.bookrestapi.dto.book.BookResponse;
import com.book.bookrestapi.dto.book.BookUpdateRequest;
import com.book.bookrestapi.repository.BookRepository;
import com.book.bookrestapi.service.AuthorService;
import com.book.bookrestapi.service.BookService;
import com.book.bookrestapi.web.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorService authorService;

    @Override
    public BookResponse create(BookCreateRequest request) {
        // Kiểm tra tác giả tồn tại
        authorService.getAuthorEntityById(request.getAuthorId());

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setPublishedYear(request.getPublishedYear());
        book.setAuthor(authorService.getAuthorEntityById(request.getAuthorId()));
        book.setAvailable(request.getAvailable() != null ? request.getAvailable() : true);

        Book savedBook = bookRepository.save(book);
        return toDto(savedBook);
    }

    @Override
    @Transactional(readOnly = true)
    public BookResponse getById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sách với ID: " + id));
        return toDto(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookResponse> list(String keyword, Long authorId) {
        List<Book> books;
        
        if (StringUtils.hasText(keyword) && authorId != null) {
            books = bookRepository.findByTitleContainingIgnoreCaseAndAuthorId(keyword, authorId);
        } else if (StringUtils.hasText(keyword)) {
            books = bookRepository.findByTitleContainingIgnoreCase(keyword);
        } else if (authorId != null) {
            books = bookRepository.findByAuthorId(authorId);
        } else {
            books = bookRepository.findAll();
        }
        
        return books.stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public BookResponse update(Long id, BookUpdateRequest request) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sách với ID: " + id));

        if (StringUtils.hasText(request.getTitle())) {
            book.setTitle(request.getTitle());
        }
        if (request.getPublishedYear() != null) {
            book.setPublishedYear(request.getPublishedYear());
        }
        if (request.getAuthorId() != null) {
            // Kiểm tra tác giả mới tồn tại
            book.setAuthor(authorService.getAuthorEntityById(request.getAuthorId()));
        }
        if (request.getAvailable() != null) {
            book.setAvailable(request.getAvailable());
        }

        Book updatedBook = bookRepository.save(book);
        return toDto(updatedBook);
    }

    @Override
    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Không tìm thấy sách với ID: " + id));
        bookRepository.delete(book);
    }

    private BookResponse toDto(Book book) {
        return new BookResponse(
                book.getId(),
                book.getTitle(),
                book.getPublishedYear(),
                book.isAvailable(),
                book.getAuthor().getId(),
                book.getAuthor().getName()
        );
    }
}
