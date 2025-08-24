package com.book.bookrestapi.web;

import com.book.bookrestapi.dto.book.BookCreateRequest;
import com.book.bookrestapi.dto.book.BookResponse;
import com.book.bookrestapi.dto.book.BookUpdateRequest;
import com.book.bookrestapi.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
@Tag(name = "Books", description = "Quản lý sách")
public class BookController {

    private final BookService bookService;

    @PostMapping
    @Operation(
            summary = "Tạo mới sách",
            description = "Tạo một sách mới với thông tin cơ bản"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Sách được tạo thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookResponse.class),
                            examples = @ExampleObject(
                                    value = "{\"id\": 1, \"title\": \"Truyện Kiều\", \"publishedYear\": 1820, \"available\": true, \"authorId\": 1, \"authorName\": \"Nguyễn Du\"}"
                            )
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dữ liệu không hợp lệ",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class),
                            examples = @ExampleObject(
                                    value = "{\"errors\": {\"title\": \"Tiêu đề sách không được để trống\"}}"
                            )
                    )
            )
    })
    public ResponseEntity<BookResponse> createBook(
            @Valid @RequestBody BookCreateRequest request
    ) {
        BookResponse response = bookService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Lấy thông tin sách theo ID",
            description = "Lấy thông tin chi tiết của một sách dựa trên ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm thấy sách",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy sách",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class),
                            examples = @ExampleObject(
                                    value = "{\"error\": \"Không tìm thấy sách với ID: 1\"}"
                            )
                    )
            )
    })
    public ResponseEntity<BookResponse> getBook(
            @Parameter(description = "ID của sách", example = "1")
            @PathVariable Long id
    ) {
        BookResponse response = bookService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(
            summary = "Lấy danh sách sách",
            description = "Lấy danh sách sách với khả năng tìm kiếm theo tiêu đề, lọc theo tác giả và phân trang"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Danh sách sách",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Page.class)
                    )
            )
    })
    public ResponseEntity<Page<BookResponse>> getBooks(
            @Parameter(description = "Từ khóa tìm kiếm theo tiêu đề sách", example = "Truyện")
            @RequestParam(required = false) String q,
            @Parameter(description = "ID của tác giả để lọc", example = "1")
            @RequestParam(required = false) Long authorId,
            Pageable pageable
    ) {
        Page<BookResponse> response = bookService.list(q, authorId, pageable);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Cập nhật thông tin sách",
            description = "Cập nhật thông tin của một sách dựa trên ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy sách",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dữ liệu không hợp lệ",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    )
            )
    })
    public ResponseEntity<BookResponse> updateBook(
            @Parameter(description = "ID của sách", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody BookUpdateRequest request
    ) {
        BookResponse response = bookService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Xóa sách",
            description = "Xóa một sách dựa trên ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Xóa thành công"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy sách",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    )
            )
    })
    public ResponseEntity<Void> deleteBook(
            @Parameter(description = "ID của sách", example = "1")
            @PathVariable Long id
    ) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
