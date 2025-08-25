package com.book.bookrestapi.web;

import com.book.bookrestapi.dto.author.AuthorCreateRequest;
import com.book.bookrestapi.dto.author.AuthorResponse;
import com.book.bookrestapi.dto.author.AuthorUpdateRequest;
import com.book.bookrestapi.service.AuthorService;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/authors")
@RequiredArgsConstructor
@Tag(name = "Authors", description = "Quản lý tác giả")
public class AuthorController {

    private final AuthorService authorService;

    @PostMapping
    @Operation(
            summary = "Tạo mới tác giả",
            description = "Tạo một tác giả mới với thông tin cơ bản"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Tác giả được tạo thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorResponse.class),
                            examples = @ExampleObject(
                                    value = "{\"id\": 1, \"name\": \"Nguyễn Du\", \"country\": \"Việt Nam\"}"
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
                                    value = "{\"errors\": {\"name\": \"Tên tác giả không được để trống\"}}"
                            )
                    )
            )
    })
    public ResponseEntity<AuthorResponse> createAuthor(
            @Valid @RequestBody AuthorCreateRequest request
    ) {
        AuthorResponse response = authorService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Lấy thông tin tác giả theo ID",
            description = "Lấy thông tin chi tiết của một tác giả dựa trên ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Tìm thấy tác giả",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy tác giả",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class),
                            examples = @ExampleObject(
                                    value = "{\"error\": \"Không tìm thấy tác giả với ID: 1\"}"
                            )
                    )
            )
    })
    public ResponseEntity<AuthorResponse> getAuthor(
            @Parameter(description = "ID của tác giả", example = "1")
            @PathVariable Long id
    ) {
        AuthorResponse response = authorService.getById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    @Operation(
            summary = "Lấy danh sách tác giả",
            description = "Lấy danh sách tác giả với khả năng tìm kiếm"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Danh sách tác giả",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = List.class)
                    )
            )
    })
    public ResponseEntity<List<AuthorResponse>> getAuthors(
            @Parameter(description = "Từ khóa tìm kiếm theo tên tác giả", example = "Nguyễn")
            @RequestParam(required = false) String q
    ) {
        List<AuthorResponse> response = authorService.list(q);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}")
    @Operation(
            summary = "Cập nhật thông tin tác giả",
            description = "Cập nhật thông tin của một tác giả dựa trên ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Cập nhật thành công",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AuthorResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy tác giả",
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
    public ResponseEntity<AuthorResponse> updateAuthor(
            @Parameter(description = "ID của tác giả", example = "1")
            @PathVariable Long id,
            @Valid @RequestBody AuthorUpdateRequest request
    ) {
        AuthorResponse response = authorService.update(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Xóa tác giả",
            description = "Xóa một tác giả dựa trên ID"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Xóa thành công"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Không tìm thấy tác giả",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Map.class)
                    )
            )
    })
    public ResponseEntity<Void> deleteAuthor(
            @Parameter(description = "ID của tác giả", example = "1")
            @PathVariable Long id
    ) {
        authorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
