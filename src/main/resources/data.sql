INSERT INTO patient (name, gender, birth_date, email, blood_group) VALUES
('John Doe', 'Male', '1990-05-15', 'johndoe@example.com', 'A+'),
('Jane Smith', 'Female', '1985-08-22', 'janesmith@example.com', 'O-'),
('Alice Johnson', 'Female', '1992-12-03', 'alicej@example.com', 'B+'),
('Bob Williams', 'Male', '1988-03-19', 'bobw@example.com', 'AB-');

INSERT INTO doctor (name, specialization, email) VALUES
('Dr. Emily Carter', 'Cardiology', 'emily.carter@hospital.com'),
('Dr. Michael Brown', 'Neurology', 'michael.brown@hospital.com'),
('Dr. Sarah Lee', 'Pediatrics', 'sarah.lee@hospital.com'),
('Dr. David Kim', 'Orthopedics', 'david.kim@hospital.com');

INSERT INTO appointment (patient_id, doctor_id, appointment_date, reason) VALUES
(1, 1, '2024-07-10 10:00:00', 'Routine check-up'),
(2, 2, '2024-07-11 14:30:00', 'Migraine consultation'),
(1, 2, '2024-07-12 09:00:00', 'Child vaccination'),
(4, 4, '2024-07-13 11:15:00', 'Knee pain evaluation');
