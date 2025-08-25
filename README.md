# Library REST API

REST API cho quản lý thư viện với Authors và Books, được xây dựng bằng Spring Boot 3.5.5 và Java 17.

## Công nghệ sử dụng

- **Java 17**
- **Spring Boot 3.5.5**
- **Spring Data JPA**
- **MySQL**
- **SpringDoc OpenAPI 3** (Swagger UI)
- **Maven**
- **Lombok**

## Cấu trúc dự án

```
com.book.bookrestapi/
├─ config/
│  └─ OpenApiConfig.java          # Cấu hình OpenAPI
├─ domain/
│  ├─ author/Author.java          # Entity Author
│  └─ book/Book.java              # Entity Book
├─ dto/
│  ├─ author/                     # DTOs cho Author
│  │  ├─ AuthorCreateRequest.java
│  │  ├─ AuthorUpdateRequest.java
│  │  └─ AuthorResponse.java
│  └─ book/                       # DTOs cho Book
│     ├─ BookCreateRequest.java
│     ├─ BookUpdateRequest.java
│     └─ BookResponse.java
├─ repository/
│  ├─ AuthorRepository.java       # Repository cho Author
│  └─ BookRepository.java         # Repository cho Book
├─ service/
│  ├─ AuthorService.java          # Service interface cho Author
│  ├─ BookService.java            # Service interface cho Book
│  └─ impl/
│     ├─ AuthorServiceImpl.java   # Service implementation cho Author
│     └─ BookServiceImpl.java     # Service implementation cho Book
└─ web/
   ├─ AuthorController.java       # REST Controller cho Author
   ├─ BookController.java         # REST Controller cho Book
   └─ exception/
      ├─ ApiExceptionHandler.java # Global exception handler
      └─ NotFoundException.java   # Custom exception
```

## API Endpoints

### Authors

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/authors` | Tạo mới tác giả |
| GET | `/api/authors/{id}` | Lấy thông tin tác giả theo ID |
| GET | `/api/authors?q=&page=&size=&sort=` | Lấy danh sách tác giả với tìm kiếm và phân trang |
| PATCH | `/api/authors/{id}` | Cập nhật thông tin tác giả |
| DELETE | `/api/authors/{id}` | Xóa tác giả |

### Books

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/books` | Tạo mới sách |
| GET | `/api/books/{id}` | Lấy thông tin sách theo ID |
| GET | `/api/books?q=&authorId=&page=&size=&sort=` | Lấy danh sách sách với tìm kiếm, lọc và phân trang |
| PATCH | `/api/books/{id}` | Cập nhật thông tin sách |
| DELETE | `/api/books/{id}` | Xóa sách |

## OpenAPI Documentation

### Swagger UI
- **URL**: http://localhost:8080/swagger-ui/index.html
- **Mô tả**: Giao diện web để test và xem documentation của API

### OpenAPI JSON
- **URL**: http://localhost:8080/v3/api-docs
- **Mô tả**: File JSON chứa specification của API

### OpenAPI YAML
- **URL**: http://localhost:8080/v3/api-docs.yaml
- **Mô tả**: File YAML chứa specification của API

## Cách sử dụng

### 1. Build project
```bash
mvn clean compile
```

### 2. Chạy ứng dụng
```bash
mvn spring-boot:run
```

### 3. Truy cập Swagger UI
Mở trình duyệt và truy cập: http://localhost:8080/swagger-ui/index.html

### 4. Export OpenAPI specification
```bash
# Export JSON
curl http://localhost:8080/v3/api-docs -o openapi.json

# Export YAML
curl http://localhost:8080/v3/api-docs.yaml -o openapi.yaml
```

## Ví dụ sử dụng API

### Tạo tác giả
```bash
curl -X POST "http://localhost:8080/api/authors" \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Nguyễn Du",
    "country": "Việt Nam"
  }'
```

### Tạo sách
```bash
curl -X POST "http://localhost:8080/api/books" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Truyện Kiều",
    "publishedYear": 1820,
    "authorId": 1,
    "available": true
  }'
```

### Lấy danh sách tác giả
```bash
curl -X GET "http://localhost:8080/api/authors?q=Nguyễn&page=0&size=10&sort=name,asc"
```

### Lấy danh sách sách theo tác giả
```bash
curl -X GET "http://localhost:8080/api/books?authorId=1&page=0&size=10"
```

## Tính năng chính

- ✅ **RESTful API** với đầy đủ CRUD operations
- ✅ **OpenAPI 3 Specification** với Swagger UI
- ✅ **Validation** với Bean Validation
- ✅ **Exception Handling** toàn cục
- ✅ **Pagination** và **Sorting**
- ✅ **Search/Filter** functionality
- ✅ **Database Relationships** (OneToMany/ManyToOne)
- ✅ **Transaction Management**
- ✅ **Lombok** để giảm boilerplate code

## Cấu hình

### Database
- **MySQL** (cấu hình trong `application.properties`)
- **Hibernate DDL**: `update` (tự động tạo schema)
- **Show SQL**: `true` (hiển thị SQL queries)

### OpenAPI
- **Title**: Library API
- **Version**: v1
- **Server**: http://localhost:8080
- **License**: MIT

## Lưu ý

- Đảm bảo MySQL server đang chạy và có database được cấu hình
- API sử dụng UTF-8 encoding cho tiếng Việt
- Tất cả responses đều có format JSON
- Error responses có cấu trúc chuẩn với message rõ ràng

## Khắc phục lỗi

### Lỗi NoSuchMethodError với ApiExceptionHandler
Nếu gặp lỗi `NoSuchMethodError: 'void org.springframework.web.method.ControllerAdviceBean.<init>(java.lang.Object)'` khi truy cập OpenAPI endpoints:

1. **Tạm thời comment out ApiExceptionHandler** (đã được thực hiện)
2. **Sử dụng version SpringDoc tương thích** (đã cập nhật)
3. **Clean và rebuild project**:
   ```bash
   mvn clean compile
   ```

### Test API
Chạy script test để kiểm tra API:
```bash
# Windows
test-api.bat

# Hoặc test thủ công
curl http://localhost:8080/v3/api-docs.yaml
curl http://localhost:8080/swagger-ui/index.html
```
