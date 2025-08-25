@echo off
echo Testing Library API...
echo.

echo Waiting for application to start...
timeout /t 10 /nobreak > nul

echo.
echo Testing OpenAPI endpoints:
echo.

echo 1. Testing OpenAPI JSON...
curl -s http://localhost:8080/v3/api-docs > openapi.json
if %errorlevel% equ 0 (
    echo ✓ OpenAPI JSON exported successfully
) else (
    echo ✗ Failed to export OpenAPI JSON
)

echo.
echo 2. Testing OpenAPI YAML...
curl -s http://localhost:8080/v3/api-docs.yaml > openapi.yaml
if %errorlevel% equ 0 (
    echo ✓ OpenAPI YAML exported successfully
) else (
    echo ✗ Failed to export OpenAPI YAML
)

echo.
echo 3. Testing Swagger UI...
curl -s -o nul http://localhost:8080/swagger-ui/index.html
if %errorlevel% equ 0 (
    echo ✓ Swagger UI is accessible
) else (
    echo ✗ Swagger UI is not accessible
)

echo.
echo 4. Testing API endpoints:
echo.

echo Creating test author...
curl -X POST "http://localhost:8080/api/authors" -H "Content-Type: application/json" -d "{\"name\": \"Test Author\", \"country\": \"Test Country\"}"
echo.

echo Getting authors list...
curl -X GET "http://localhost:8080/api/authors"
echo.

echo.
echo Test completed!
echo.
echo Files created:
echo - openapi.json (OpenAPI specification in JSON format)
echo - openapi.yaml (OpenAPI specification in YAML format)
echo.
echo You can now access:
echo - Swagger UI: http://localhost:8080/swagger-ui/index.html
echo - OpenAPI JSON: http://localhost:8080/v3/api-docs
echo - OpenAPI YAML: http://localhost:8080/v3/api-docs.yaml
