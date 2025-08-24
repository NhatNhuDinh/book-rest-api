package com.book.bookrestapi.dto.author;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Request cập nhật tác giả")
public class AuthorUpdateRequest {

    @Schema(description = "Tên đầy đủ của tác giả", example = "Nguyễn Du")
    private String name;

    @Schema(description = "Quốc gia của tác giả", example = "Việt Nam")
    private String country;
}
