-- db/migration/V1__create_todo_list_table.sql

CREATE TABLE IF NOT EXISTS  todo_list (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    note VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO todo_list (name, note) VALUES
('Shopping List', 'Buy groceries for the week'),
('Workout Plan', 'Go to the gym every morning'),
('Study Schedule', 'Study for the upcoming exam'),
('Work List', 'Finish project X by Friday');
