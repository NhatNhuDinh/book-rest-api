package com.book.bookrestapi.domain.author;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Tác giả của sách")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ID duy nhất của tác giả", example = "1")
    private Long id;

    @NotBlank(message = "Tên tác giả không được để trống")
    @Column(unique = true, nullable = false)
    @Schema(description = "Tên đầy đủ của tác giả", example = "Nguyễn Du")
    private String name;

    @Schema(description = "Quốc gia của tác giả", example = "Việt Nam")
    private String country;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnore
    @Schema(description = "Danh sách sách của tác giả")
    private List<com.book.bookrestapi.domain.book.Book> books = new ArrayList<>();
}
