CREATE TABLE IF NOT EXISTS feedback(
    id int Auto_Increment primary key,
    name varchar(45),
    phone_no varchar(45),
    address varchar(45),
    feedback varchar(100)
);