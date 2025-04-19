# Bash Share - CLI Upload Tool

A simple and efficient file sharing tool inspired by [bashupload.com](https://bashupload.com/), built with modern practices following hexagonal architecture principles.

## About

Share allows you to quickly upload files and share them through a simple URL. Files are automatically deleted after being downloaded or when they expire, ensuring clean storage management.

## Architecture

This project follows hexagonal architecture (also known as ports and adapters) principles to maintain:
- Clear separation of concerns
- Business logic isolation
- Framework independence
- Testability

The architecture is divided into:
- Domain: Core business logic
- Application: Use cases and ports
- Infrastructure: Adapters for web, persistence, etc.

## Getting Started

### Prerequisites
- Docker
- Docker Compose
- Java 21

### Running locally
```bash
# Start the application and database
docker-compose up
```

### Usage
```bash
# Upload a file
curl http://localhost:8080 -T file

# Download a file
wget --content-disposition http://127.0.0.1:8080/uuid
```

## To-Do List

- [ ] Add comprehensive test suite
    - [ ] Unit tests
    - [ ] Integration tests
    - [ ] E2E tests

- [ ] Improve error pages
    - [ ] Create file not found page
    - [ ] Create file already downloaded page
    - [ ] Add proper error handling and user feedback

- [ ] Storage management
    - [ ] Create scheduler to remove downloaded files
    - [ ] Implement file expiration mechanism
    - [ ] Add cleanup job for expired files

- [ ] Admin features
    - [ ] Create admin dashboard
    - [ ] Add storage usage insights
    - [ ] Add file statistics
    - [ ] Monitor system health

## Contributing

Feel free to open issues and submit pull requests. All contributions are welcome!

## License

This project is licensed under the MIT License - see the LICENSE file for details