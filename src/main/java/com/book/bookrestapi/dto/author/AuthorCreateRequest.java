package com.book.bookrestapi.dto.author;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request tạo mới tác giả")
public class AuthorCreateRequest {

    @NotBlank(message = "Tên tác giả không được để trống")
    @Schema(description = "Tên đầy đủ của tác giả", example = "Nguyễn Du", required = true)
    private String name;

    @Schema(description = "Quốc gia của tác giả", example = "Việt Nam")
    private String country;
}
