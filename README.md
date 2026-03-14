# 🏠 HomeCycle

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![React](https://img.shields.io/badge/React-20232A?style=for-the-badge&logo=react&logoColor=61DAFB)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![CSS3](https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white)

---

## 📋 Description

HomeCycle is a full-stack home management application that helps homeowners stay on top of both recurring household chores and scheduled home maintenance tasks. Whether it's tracking when the HVAC filter needs replacing or organizing weekly cleaning responsibilities, HomeCycle gives you a centralized place to manage and monitor everything that keeps a home running smoothly.

---

## 🛠️ Technologies Used

### Backend
- **Java** — Core language for server-side logic
- **Spring Boot** — Backend framework powering the REST API
- **Spring Data JPA** — Database persistence and ORM
- **Maven** — Dependency management and build tool

### Frontend
- **React** — Component-based UI library
- **JavaScript (ES6+)** — Frontend logic
- **CSS3** — Styling and layout
- **HTML5** — Markup

---

## ⚙️ Installation & Setup

Follow these steps to run HomeCycle locally after forking and cloning the repository.

### Prerequisites

Make sure you have the following installed:
- [Java 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/install.html)
- [Node.js & npm](https://nodejs.org/)
- A database (e.g., MySQL or PostgreSQL) — update `application.properties` with your credentials

---

### 1. Clone the Repository

```bash
git clone https://github.com/capndenney/home-cycle-2.git
cd home-cycle-2
```

---

### 2. Start the Backend (Spring Boot)

```bash
cd data
```

Update `src/main/resources/application.properties` with your local database credentials:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/homecycle
spring.datasource.username=your_username
spring.datasource.password=your_password
```

Then run the backend:

```bash
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

---

### 3. Start the Frontend (React)

Open a new terminal tab/window:

```bash
cd home-cycle-front
npm install
npm start
```

The app will open at `http://localhost:3000`.

---

## 🖼️ Wireframes & Screenshots

[Wireframes for current and future state](https://www.figma.com/design/pJaPIrSY7HOvdE9blKkgX9/Untitled?node-id=0-1&p=f)


---

## 🗂️ Entity Relationship Diagram (ERD)

[Check out the basic Entity Relationship Diagram](https://dbdiagram.io/d/Home-Cycle-69910ea9bd82f5fce2bbac01)

---

## 🚧 Unsolved Problems & Future Features

- **Multi-member households** — Currently supports a single user; a future update would allow multiple household members to share and collaborate on the same task list
- **Notifications & reminders** — Email or push notifications when a recurring task or maintenance cycle is approaching
- **Task history display** — Completed task history is currently being saved to the database but is not yet surfaced in the UI
- **Responsive design testing** — Basic responsive layouts are in place for different device sizes, but have not been fully tested across all screen widths and devices

---

## 🔌 API Endpoints

<details>
<summary><strong>Auth — <code>/api</code></strong></summary>

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/login` | Authenticates a user with email and password, returns a JWT token along with the user's ID and household ID |
| `POST` | `/api/logout` | Invalidates the current session by adding the user's JWT token to a blacklist |
| `POST` | `/api/validate-token` | Checks whether a given JWT token is still valid for a specified user |

</details>

<details>
<summary><strong>Registration — <code>/api/register</code></strong></summary>

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/register` | Registers a new household along with its first user in a single transaction |

</details>

<details>
<summary><strong>Users — <code>/api/users</code></strong></summary>

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/users/{id}` | Retrieves profile information for a specific user by ID |
| `POST` | `/api/users/new` | Creates a new user account and saves it to the database |
| `PUT` | `/api/users/{id}` | Updates a user's name and password |
| `PATCH` | `/api/users/{id}` | Partially updates a user — currently handles name changes only |
| `PUT` | `/api/users/{id}/delete` | Soft-deletes a user by appending `_deleted_{id}` to their email, preserving the record in the database |
| `PUT` | `/api/users/update-password` | Allows an authenticated user to change their password after verifying their current one |

</details>

<details>
<summary><strong>Households — <code>/api/household</code></strong></summary>

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/household/{id}` | Retrieves a specific household by ID |
| `POST` | `/api/household/new` | Creates a new household |
| `PUT` | `/api/household/{id}` | Updates the details of an existing household |
| `DELETE` | `/api/household/{id}` | Permanently deletes a household by ID |

</details>

<details>
<summary><strong>Tasks — <code>/api/tasks</code></strong></summary>

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/tasks` | Returns all tasks belonging to the authenticated user's household, or tasks they created if not yet in a household |
| `GET` | `/api/tasks/{id}` | Retrieves a single task by ID |
| `POST` | `/api/tasks/newtask` | Creates a new task and associates it with a user and household |
| `PUT` | `/api/tasks/{id}` | Updates an existing task's details; if the task is marked complete and has a recurrence interval, automatically generates the next occurrence |
| `PUT` | `/api/tasks/{id}/complete` | Marks a task as complete, records who completed it and when, and automatically schedules the next occurrence if the task is recurring |
| `DELETE` | `/api/tasks/{id}` | Permanently deletes a task by ID |

</details>

<details>
<summary><strong>Feedback — <code>/api/feedback</code></strong></summary>

| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/feedback` | Submits a feedback form and triggers an email to the development team |

</details>

---

## 👤 Author

**capndenney** — [GitHub Profile](https://github.com/capndenney)