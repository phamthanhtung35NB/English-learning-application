-- CREATE TABLE account (
--   id_account INT NOT NULL,
--   user_name VARCHAR(45) NOT NULL,
--   password VARCHAR(45) NOT NULL,
--   studying_array VARCHAR(1000),
--   PRIMARY KEY (id_account, user_name)
--   );

-- INSERT INTO account (id_account, user_name, password) 
-- VALUES (1, 'admin1', 'admin1');



UPDATE account
SET studying_array = '1,5,32,524,234523,52345342,2345342523,567,2134,653426,54567,567,568764,5645,63,6234,63426,3426,54647,567,5675673546,63426532,8'
WHERE id_account = 1 AND user_name = 'admin2';

SELECT * FROM account;
