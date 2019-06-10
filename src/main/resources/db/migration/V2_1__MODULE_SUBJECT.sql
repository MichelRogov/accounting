INSERT INTO MODULE (ID, HOURS, NAME, PRICE)
VALUES (1, 40, 'BASIC_JAVA', 400.00),
       (2, 35, 'JAVA', 700.00),
       (3, 89, 'DEVOPS', 500.50),
       (4, 41, 'FRONTEND', 645.25);

INSERT INTO SUBJECT (ID, NAME)
VALUES (1, 'BACKEND'),
       (2, 'FRONTEND'),
       (3, 'QA');

INSERT INTO MODULE_SUBJECT (MODULE_ID, SUBJECT_ID)
VALUES (1, 3),
       (2, 1),
       (1, 2),
       (4, 2),
       (3, 2),
       (4, 1);

