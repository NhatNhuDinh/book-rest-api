package com.book.bookrestapi.dto.author;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Response thông tin tác giả")
public class AuthorResponse {

    @Schema(description = "ID duy nhất của tác giả", example = "1")
    private Long id;

    @Schema(description = "Tên đầy đủ của tác giả", example = "Nguyễn Du")
    private String name;

    @Schema(description = "Quốc gia của tác giả", example = "Việt Nam")
    private String country;
}
