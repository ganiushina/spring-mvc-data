DROP TABLE IF EXISTS students
CREATE TABLE students (id int IDENTITY PRIMARY KEY, name VARCHAR(255), score int);
INSERT students (name, score) VALUES ('Bob1', 70), ('Bob2', 80), ('Bob3', 50), ('Bob4', 60), ('Bob5', 80), ('Bob6', 80), ('Bob7', 60), ('Bob8', 90), ('Bob9', 50), ('Bob10', 60), ('Bob11', 40), ('Bob12', 70), ('Bob13', 80), ('Bob14', 40), ('Bob15', 50), ('Bob16', 70), ('Bob17', 80), ('Bob18', 90), ('Bob19', 90), ('Bob20', 50);

DROP TABLE IF EXISTS goods
CREATE TABLE goods (id int IDENTITY PRIMARY KEY, title VARCHAR(255), coast int);
INSERT goods (title, coast) VALUES ('Beer', 70), ('Beer2', 80), ('Beer3', 50), ('Beer4', 60), ('Beer5', 80), ('Beer6', 80), ('Beer7', 60), ('Beer8', 90), ('Beer9', 50), ('Beer10', 60), ('Beer11', 40), ('Beer12', 70), ('Beer13', 80), ('Beer14', 40), ('Beer15', 50), ('Beer16', 70), ('Beer17', 80), ('Beer18', 90), ('Beer19', 90), ('Beer20', 50);