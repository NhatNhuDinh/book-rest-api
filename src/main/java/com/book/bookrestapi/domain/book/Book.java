package com.book.bookrestapi.domain.book;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Sách trong thư viện")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID duy nhất của sách", example = "1")
    private Long id;

    @NotBlank(message = "Tiêu đề sách không được để trống")
    @Column(nullable = false)
    @Schema(description = "Tiêu đề của sách", example = "Truyện Kiều")
    private String title;

    @Schema(description = "Năm xuất bản", example = "1820")
    private Integer publishedYear;

    @Schema(description = "Trạng thái có sẵn để mượn", example = "true")
    private boolean available = true;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    @NotNull(message = "Tác giả không được để trống")
    @JsonIgnore
    @Schema(description = "Tác giả của sách")
    private com.book.bookrestapi.domain.author.Author author;
}
