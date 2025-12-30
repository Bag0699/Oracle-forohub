CREATE TABLE courses
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    category VARCHAR(100)
);

CREATE TABLE users
(
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(100) NOT NULL
);

CREATE TABLE profiles
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    bio        TEXT,
    user_id    BIGINT       NOT NULL UNIQUE,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);

CREATE TABLE topics
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    title         VARCHAR(200) NOT NULL,
    message       TEXT         NOT NULL,
    creation_date DATETIME     NOT NULL,
    status        VARCHAR(100) NOT NULL,
    user_id       BIGINT       NOT NULL,
    course_id     BIGINT       NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (course_id) REFERENCES courses (id)
);

CREATE TABLE replies
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    message       TEXT     NOT NULL,
    creation_date DATETIME NOT NULL,
    is_solution   BOOLEAN DEFAULT FALSE,
    topic_id      BIGINT   NOT NULL,
    user_id       BIGINT   NOT NULL,

    FOREIGN KEY (topic_id) REFERENCES topics (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

