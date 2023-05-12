INSERT INTO Role (role_id,role_name)
VALUES (1,'ADMIN');

INSERT INTO Role (role_id,role_name)
VALUES (2,'LABORANT');

INSERT INTO laboratories (id,firstname,lastname,laborant_id,is_enabled,password)
VALUES (1,'Arslan','Küçükkafa','87654321',true,'$2a$10$QQDeXRAmgvEEWWrCg5RZ7eQikmOEWv5TrNnQF.0KnK9oqcfgB.93i');

INSERT INTO laborant_roles (laborant_id,role_id)
VALUES (1,1);

INSERT INTO laboratories (id,firstname,lastname,laborant_id,is_enabled,password)
VALUES (2,'MehmetCinar','Küçükkafa','7654321',true,'$2a$10$QQDeXRAmgvEEWWrCg5RZ7eQikmOEWv5TrNnQF.0KnK9oqcfgB.93i');

INSERT INTO laborant_roles (laborant_id,role_id)
VALUES (2,2);

