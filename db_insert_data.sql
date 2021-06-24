-- Заполнение таблицы UserType исходными данными
INSERT INTO UserType(Name) VALUES ('admin');
INSERT INTO UserType(Name) VALUES ('moderator');
INSERT INTO UserType(Name) VALUES ('customer');
INSERT INTO UserType(Name) VALUES ('master');
INSERT INTO UserType(Name) VALUES ('warehouser');

-- Заполнение таблицы OrderStatus исходными данными
INSERT INTO OrderStatus(Name) VALUES ('check_by_moderator');
INSERT INTO OrderStatus(Name) VALUES ('wait_master');
INSERT INTO OrderStatus(Name) VALUES ('creation_parts_list');
INSERT INTO OrderStatus(Name) VALUES ('wait_replacement_parts');
INSERT INTO OrderStatus(Name) VALUES ('repair_process');
INSERT INTO OrderStatus(Name) VALUES ('completed');

-- Заполнение таблицы ReplacementPart исходными данными
INSERT INTO ReplacementPart(Name, InStock, Cost) VALUES ('Электродвигатель DC', 2, 250);
INSERT INTO ReplacementPart(Name, InStock, Cost) VALUES ('Реле 250В 6А', 3, 80);
INSERT INTO ReplacementPart(Name, InStock, Cost) VALUES ('Транзисторный ключ 12В', 2, 100);
INSERT INTO ReplacementPart(Name, InStock, Cost) VALUES ('Щетки электродвигателя', 6, 30);
INSERT INTO ReplacementPart(Name, InStock, Cost) VALUES ('Микровыключатель', 6, 90);

-- Заполнение таблицы UserEntity исходными данными
INSERT INTO UserEntity(Login, Password, Status, UserType, FirstName, LastName, Phone, Authorized) 
	VALUES ('admin', '1', 'unlocked', 1, 'Николай', 'Смирнов', '89106401824', 0);
INSERT INTO UserEntity(Login, Password, Status, UserType, FirstName, LastName, Phone, Authorized) 
	VALUES ('moderator', '1', 'unlocked', 2, 'Владимир', 'Макаров', '89156401826', 0);
INSERT INTO UserEntity(Login, Password, Status, UserType, FirstName, LastName, Phone, Authorized) 
	VALUES ('customer', '1', 'unlocked', 3, 'Алексей', 'Иванов', '89206403824', 0);
INSERT INTO UserEntity(Login, Password, Status, UserType, FirstName, LastName, Phone, Authorized) 
	VALUES ('master', '1', 'unlocked', 4, 'Анатолий', 'Сидоров', '89106201524', 0);
INSERT INTO UserEntity(Login, Password, Status, UserType, FirstName, LastName, Phone, Authorized) 
	VALUES ('warehouser', '1', 'unlocked', 5, 'Григорий', 'Егоров', '89106107844', 0);
INSERT INTO UserEntity(Login, Password, Status, UserType, FirstName, LastName, Phone, Authorized) 
	VALUES ('customer2', '1', 'locked', 3, 'Евгений', 'Петров', '89106177874', 0);
	
-- Заполнение таблицы OrderEntity исходными данными
INSERT INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted) 
	VALUES ('Фен для волос', 'Фен не греет воздух', 1, 3, 0);
INSERT INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted) 
	VALUES ('Пылесос LG', 'Не включается', 2, 3, 0);
INSERT INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted) 
	VALUES ('Миксер', 'Не работает кнопка', 2, 3, 0);
INSERT INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted, Master) 
	VALUES ('Утюг', 'Не включается', 3, 3, 0, 4);
INSERT INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted, Master, CostWork) 
	VALUES ('Мясорубка', 'Не включается', 6, 3, 0, 4, 400);
	
-- Заполнение таблицы PartsList исходными данными
INSERT INTO PartsList(OrderEntity, ReplacementPart, NumberParts) VALUES (5, 4, 2);
INSERT INTO PartsList(OrderEntity, ReplacementPart, NumberParts) VALUES (5, 3, 1);