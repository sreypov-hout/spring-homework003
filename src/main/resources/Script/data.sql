-- Insert instructors
INSERT INTO instructors (instructor_name, email) VALUES
                                                     ('Mr. Smith', 'smith@example.com'),
                                                     ('Ms. Johnson', 'johnson@example.com');

-- Insert courses
INSERT INTO courses (course_name, description, instructor_id) VALUES
                                                                  ('Math 101', 'Basic Mathematics', 1),
                                                                  ('History 201', 'World History', 2);

-- Insert students
INSERT INTO students (student_name, email, phone_number) VALUES
                                                             ('Alice', 'alice@example.com', '123456789'),
                                                             ('Bob', 'bob@example.com', '987654321');

-- Enroll students in courses
INSERT INTO student_course (student_id, course_id) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (2, 1);
