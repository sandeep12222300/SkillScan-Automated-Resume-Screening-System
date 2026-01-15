# SkillScan - Automated Resume Screening System

SkillScan is an AI-powered resume screening system that helps recruiters and hiring managers quickly evaluate candidate resumes against job descriptions. The system extracts skills from resumes and job descriptions, then calculates a match percentage to help prioritize candidates.

## Features

- 📄 **Resume Parsing**: Supports PDF, DOC, and DOCX file formats using Apache Tika
- 🔍 **Skill Extraction**: Automatically identifies technical skills from resumes and job descriptions
- 📊 **Match Scoring**: Calculates match percentage based on required skills vs. candidate skills
- 💾 **Database Storage**: Stores screening results in PostgreSQL for future reference
- 🔐 **Simple Authentication**: Basic login system to protect the screening interface
- 🐳 **Docker Support**: Easy deployment with Docker and Docker Compose

## Tech Stack

- **Backend**: Java 17, Spring Boot 3.5.9
- **Database**: PostgreSQL 15
- **Frontend**: HTML, CSS, JavaScript (Vanilla)
- **Text Extraction**: Apache Tika 3.2.2
- **Build Tool**: Maven
- **Containerization**: Docker

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- PostgreSQL 15 (or use Docker Compose)
- Docker and Docker Compose (optional, for containerized deployment)

## Getting Started

### Option 1: Using Docker Compose (Recommended)

1. Clone the repository:
```bash
git clone https://github.com/sandeep12222300/SkillScan-Automated-Resume-Screening-System.git
cd SkillScan-Automated-Resume-Screening-System
```

2. Build the application:
```bash
./mvnw clean package -DskipTests
```

3. Start the application with Docker Compose:
```bash
docker-compose up -d
```

4. Access the application at `http://localhost:8080/login`

### Option 2: Local Development

1. Clone the repository:
```bash
git clone https://github.com/sandeep12222300/SkillScan-Automated-Resume-Screening-System.git
cd SkillScan-Automated-Resume-Screening-System
```

2. Set up PostgreSQL database:
```sql
CREATE DATABASE resume_db;
```

3. Update `src/main/resources/application.properties` with your database credentials:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/resume_db
spring.datasource.username=your_username
spring.datasource.password=your_password
```

4. Build and run the application:
```bash
./mvnw spring-boot:run
```

5. Access the application at `http://localhost:8080/login`

## Configuration

### Environment Variables

For production deployment, configure the following environment variables:

- `ADMIN_USERNAME`: Admin login username (default: admin)
- `ADMIN_PASSWORD`: Admin login password (default: admin123) **⚠️ MUST be changed in production!**
- `SPRING_DATASOURCE_URL`: Database connection URL
- `SPRING_DATASOURCE_USERNAME`: Database username
- `SPRING_DATASOURCE_PASSWORD`: Database password

**Security Warning**: The default credentials (admin/admin123) are for development only. Always set strong, unique credentials via environment variables in production:

Example:
```bash
export ADMIN_USERNAME=myusername
export ADMIN_PASSWORD='YourStr0ng&SecureP@ssw0rd!'
```

### File Upload Limits

Maximum file size: 5MB (configurable in `application.properties`)

## Usage

1. **Login**: Use the credentials (default for development: admin/admin123)
   - ⚠️ **Change these credentials immediately in production!**
2. **Upload Resume**: Select a PDF, DOC, or DOCX resume file
3. **Enter Job Description**: Paste the job requirements including required skills
4. **View Results**: See the match percentage and candidate details

## API Endpoints

### Resume Upload
- **URL**: `/api/resume/upload`
- **Method**: `POST`
- **Content-Type**: `multipart/form-data`
- **Parameters**:
  - `resume` (file): Resume file (PDF/DOC/DOCX)
  - `jobDescription` (text): Job description text
- **Response**: JSON with candidate name and match percentage

## Project Structure

```
├── src/
│   ├── main/
│   │   ├── java/com/example/resume/
│   │   │   ├── controller/        # REST and MVC controllers
│   │   │   ├── model/             # JPA entities
│   │   │   ├── repository/        # Data access layer
│   │   │   ├── service/           # Business logic
│   │   │   └── ResumeScreeningSystemApplication.java
│   │   └── resources/
│   │       ├── static/            # CSS and JS files
│   │       ├── templates/         # HTML templates
│   │       └── application.properties
│   └── test/                      # Unit and integration tests
├── Dockerfile
├── docker-compose.yml
└── pom.xml
```

## Security Considerations

⚠️ **Important Security Notes**:

1. **Change Default Credentials**: The default admin credentials (admin/admin123) should be changed immediately in production
2. **Use Environment Variables**: Never hardcode sensitive credentials in source code
3. **HTTPS**: Always use HTTPS in production environments
4. **Input Validation**: The system includes basic input validation; enhance as needed for your use case
5. **Apache Tika**: Using version 3.2.2 to avoid known XXE vulnerabilities

## Testing

Run the test suite:
```bash
./mvnw test
```

## Building for Production

1. Build the JAR file:
```bash
./mvnw clean package
```

2. The executable JAR will be created at `target/resume-screening-system-0.0.1-SNAPSHOT.jar`

3. Run the JAR:
```bash
java -jar target/resume-screening-system-0.0.1-SNAPSHOT.jar
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## Known Limitations

- Currently supports a predefined list of skills (java, spring, spring boot, sql, python, kafka, docker)
- Basic authentication without encryption
- No user management system
- No API authentication/authorization

## Future Enhancements

- [ ] AI-powered skill extraction using NLP
- [ ] Resume ranking and comparison
- [ ] Batch resume processing
- [ ] Advanced filtering and search
- [ ] User role management
- [ ] API authentication (JWT/OAuth2)
- [ ] Email notifications
- [ ] Resume templates and formatting analysis

## License

This project is open source and available under the MIT License.

## Support

For issues, questions, or contributions, please open an issue on GitHub.

## Acknowledgments

- Apache Tika for document parsing
- Spring Boot for the application framework
- PostgreSQL for database management
