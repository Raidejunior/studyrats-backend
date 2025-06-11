# StudyRats Backend

**PT-BR:**  
Backend da StudyRats, um aplicativo que gamifica o estudo em grupo. Usuários criam grupos, definem matérias e subáreas, respondem e criam questões, acumulam pontos por acertos e curtidas.  

**EN:**  
Backend for StudyRats, an app that gamifies group studying. Users form groups, define subjects and subtopics, answer and author questions, and earn points for correct answers and likes.

---

## Funcionalidades principais | Key Features

- Cadastro e login de usuários  / User registration and login
  
- Criação e associação de usuários a grupos  / Creation and association of users to groups
  
- Gerenciamento de categorias (matérias) e subcategorias (áreas) / Management of categories (subjects) and subcategories (areas)
  
- CRUD de questões de múltipla escolha  / CRUD of multiple choice questions
  
- Sistema de pontuação: +10 por acerto, +3 por curtida  / Scoring system: +10 for correct answers, +3 for likes
  
- Endpoints de ranking por usuário e por grupo  / Ranking endpoints by user and by group

---

## Stack de Tecnologias | Tech Stack

- **Java 17**  
- **Spring Boot 3.5**
- **Domain-Driven Design (DDD)**  
- **Clean Architecture** (camadas `domain` • `application` • `infrastructure` • `api`)  
- **Clean Code** e **SOLID Principles** para código legível e manutenível  / for readable and maintainable code
- **PostgreSQL** com Flyway para migrações de banco / with Flyway for bank migrations  
- **MapStruct** para mapeamento DTO ↔ Entidade ↔ Domínio  / for mapping DTO ↔ Entity ↔ Domain
- **Lombok** para reduzir boilerplate  / to reduce boilerplate
- **Jakarta Bean Validation** para validação de entrada (`@Valid`, `@NotBlank`, `@Email`, etc.)  / for input validation
- **JUnit 5 + Mockito** para testes unitários  / for unit tests
- **Docker** para conteinerização local e consistência de ambiente  / for local containerization and environment consistency
- **Terraform** para Infrastructure as Code  
- **AWS**  
  - RDS (PostgreSQL)  
  - S3 (armazenamento de arquivos)  / (file storage)
  - ECS / EKS (orquestração de containers)  / (container orchestration)
  - CloudWatch (monitoramento)  / (monitoring)
- **Swagger / OpenAPI** para documentação automática da API REST / for automatic REST API documentation

---

## Como rodar localmente | How to run locally

1. **Clone o repositório / Clone the repo**
   ```bash
   git clone https://github.com/seu-usuario/studyrats-backend.git
   cd studyrats-backend

2. **Configure o banco / Configure the database**
   - Crie um banco studyrats no PostgreSQL. / Create the studyrats database using PostgreSQL.
   - Edite src/main/resources/application.yml com suas credenciais. / Edit src/main/resources/application.yml with your credentials.

3. **Execute as migrações / Run migrations**
  `./mvnw flyway:migrate`

4. **Inicie a aplicação / Start the app**
  `./mvnw spring-boot:run`

---

## Estrutura do projeto | Project Structure
  - com.studyrats.domain         # Modelos e regras de negócio / Business models and rules
  - com.studyrats.application    # Serviços de caso de uso e DTOs / Use Case Services and DTOs
  - com.studyrats.infrastructure # Persistência, mapeadores e migrações / Persistence, mappers and migrations
  - com.studyrats.api      # REST controllers e mappers para API / REST controllers and mappers for API

---

## Contribuindo | Contributing
  - Este projeto é mantido pela equipe interna e **não está aberto** a contribuições externas no momento.
  - This project is maintained by the internal team and is **not open** to external contributions at this time.

---

## Licença | License
  - Este projeto é licenciado sob a MIT License. Olhe o arquivo LICENSE para mais informações. / Original license: MIT. See LICENSE file for more information.
  - Este projeto usa a convenção de commits Conventional Commits. / This project uses the Conventional Commits commit convention.
  
