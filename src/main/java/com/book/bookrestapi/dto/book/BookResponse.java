package com.book.bookrestapi.dto.book;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response thông tin sách")
public class BookResponse {

    @Schema(description = "ID duy nhất của sách", example = "1")
    private Long id;

    @Schema(description = "Tiêu đề của sách", example = "Truyện Kiều")
    private String title;

    @Schema(description = "Năm xuất bản", example = "1820")
    private Integer publishedYear;

    @Schema(description = "Trạng thái có sẵn để mượn", example = "true")
    private boolean available;

    @Schema(description = "ID của tác giả", example = "1")
    private Long authorId;

    @Schema(description = "Tên tác giả", example = "Nguyễn Du")
    private String authorName;
}
