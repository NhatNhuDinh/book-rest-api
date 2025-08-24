package com.book.bookrestapi.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request tạo mới sách")
public class BookCreateRequest {

    @NotBlank(message = "Tiêu đề sách không được để trống")
    @Schema(description = "Tiêu đề của sách", example = "Truyện Kiều", required = true)
    private String title;

    @Schema(description = "Năm xuất bản", example = "1820")
    private Integer publishedYear;

    @NotNull(message = "ID tác giả không được để trống")
    @Schema(description = "ID của tác giả", example = "1", required = true)
    private Long authorId;

    @Schema(description = "Trạng thái có sẵn để mượn", example = "true")
    private Boolean available = true;
}
