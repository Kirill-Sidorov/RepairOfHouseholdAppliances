-- Получить всех пользователей системы
SELECT UserEntity.Id, UserEntity.Login, 
	UserEntity.Password, UserEntity.Status, 
	UserEntity.FirstName, UserEntity.LastName, 
	UserEntity.Phone, UserEntity.Authorized, 
	UserTypeTable.Name AS UserType 
FROM UserEntity INNER JOIN UserType UserTypeTable 
	ON UserEntity.UserType = UserTypeTable.Id

-- Получить пользователя по логину
SELECT UserEntity.Id, UserEntity.Login, 
	UserEntity.Password, UserEntity.Status, 
	UserEntity.FirstName, UserEntity.LastName, 
	UserEntity.Phone, UserEntity.Authorized, 
	UserTypeTable.Name AS UserType 
FROM UserEntity INNER JOIN UserType UserTypeTable 
	ON UserEntity.UserType = UserTypeTable.Id 
WHERE Login=?

-- Получить пользователя по id
SELECT UserEntity.Id, UserEntity.Login, 
	UserEntity.Password, UserEntity.Status, 
	UserEntity.FirstName, UserEntity.LastName, 
	UserEntity.Phone, UserEntity.Authorized, 
	UserTypeTable.Name AS UserType 
FROM UserEntity INNER JOIN UserType UserTypeTable 
	ON UserEntity.UserType = UserTypeTable.Id 
WHERE UserEntity.Id=?

-- Получить список пользователей по статусу
SELECT UserEntity.Id, UserEntity.Login, 
	UserEntity.Password, UserEntity.Status, 
	UserEntity.FirstName, UserEntity.LastName, 
	UserEntity.Phone, UserEntity.Authorized, 
	UserTypeTable.Name AS UserType 
FROM UserEntity INNER JOIN UserType UserTypeTable 
	ON UserEntity.UserType = UserTypeTable.Id 
WHERE UserEntity.Status=?

-- Изменить поле авторизации пользователя
UPDATE UserEntity SET Authorized=? WHERE Id=?

-- Изменить стаус пользователя
UPDATE UserEntity SET Status=? WHERE Id=?

-- Создать пользователя
INSERT INTO UserEntity(Login, Password, Status, UserType, FirstName, LastName, Phone, Authorized) 
	VALUES (?, ?, 'unlocked', ?, ?, ?, ?, 0)

-- Обновить запись пользователя
UPDATE UserEntity SET Login=?, FirstName=?, LastName=?, Phone=? WHERE Id=?

-- Удалить пользователя по id
DELETE FROM UserEntity WHERE Id=?

-- Получить все заказы
SELECT OrderEntity.Id, OrderEntity.ThingName, 
	OrderEntity.Description, OrderEntity.CostWork, 
	OrderEntity.Customer AS CustomerId, 
	CustomerTable.LastName AS CustomerName, 
	OrderEntity.Master AS MasterId, 
	MasterTable.LastName AS MasterName, 
	OrderStatus.Name AS OrderStatus, OrderEntity.Deleted 
FROM OrderEntity INNER JOIN UserEntity CustomerTable 
	ON OrderEntity.Customer = CustomerTable.Id 
		INNER JOIN OrderStatus 
			ON OrderEntity.OrderStatus = OrderStatus.Id 
				LEFT JOIN UserEntity MasterTable 
					ON OrderEntity.Master = MasterTable.Id

-- Получить заказы пользователя
SELECT OrderEntity.Id, OrderEntity.ThingName, 
	OrderEntity.Description, OrderEntity.CostWork, 
	OrderEntity.Customer AS CustomerId, 
	CustomerTable.LastName AS CustomerName, 
	OrderEntity.Master AS MasterId, 
	MasterTable.LastName AS MasterName, 
	OrderStatus.Name AS OrderStatus, 
	OrderEntity.Deleted 
FROM OrderEntity INNER JOIN UserEntity CustomerTable 
	ON OrderEntity.Customer = CustomerTable.Id 
		INNER JOIN OrderStatus 
			ON OrderEntity.OrderStatus = OrderStatus.Id 
				LEFT JOIN UserEntity MasterTable 
					ON OrderEntity.Master = MasterTable.Id 
WHERE OrderEntity.Customer=? AND OrderEntity.Deleted=?

-- Получить заказы мастера по id мастера и статусу заказа
SELECT OrderEntity.Id, OrderEntity.ThingName,
	OrderEntity.Description, OrderEntity.CostWork, 
	OrderEntity.Customer AS CustomerId, 
	CustomerTable.LastName AS CustomerName, 
	OrderEntity.Master AS MasterId, 
	MasterTable.LastName AS MasterName, 
	OrderStatus.Name AS OrderStatus, 
	OrderEntity.Deleted 
FROM OrderEntity 
	INNER JOIN UserEntity CustomerTable 
		ON OrderEntity.Customer = CustomerTable.Id 
			INNER JOIN OrderStatus 
				ON OrderEntity.OrderStatus = OrderStatus.Id 
					LEFT JOIN UserEntity MasterTable 
						ON OrderEntity.Master = MasterTable.Id 
WHERE OrderEntity.Master=? AND OrderEntity.OrderStatus=?

-- Создать заказ
INSERT INTO OrderEntity(ThingName, Description, OrderStatus, Customer, Deleted) 
	VALUES (?, ?, ?, ?, 0)

-- Получить список заказов по статусу
SELECT OrderEntity.Id, OrderEntity.ThingName, 
	OrderEntity.Description, OrderEntity.CostWork, 
	OrderEntity.Customer AS CustomerId, 
	CustomerTable.LastName AS CustomerName, 
	OrderEntity.Master AS MasterId, 
	MasterTable.LastName AS MasterName, 
	OrderStatus.Name AS OrderStatus, 
	OrderEntity.Deleted 
FROM OrderEntity INNER JOIN UserEntity CustomerTable 
	ON OrderEntity.Customer = CustomerTable.Id 
		INNER JOIN OrderStatus 
			ON OrderEntity.OrderStatus = OrderStatus.Id 
				LEFT JOIN UserEntity MasterTable 
					ON OrderEntity.Master = MasterTable.Id 
WHERE OrderEntity.OrderStatus=?

-- Получить заказ по id
SELECT OrderEntity.Id, OrderEntity.ThingName, 
OrderEntity.Description, OrderEntity.CostWork, 
OrderEntity.Customer AS CustomerId, 
CustomerTable.LastName AS CustomerName, 
OrderEntity.Master AS MasterId, 
MasterTable.LastName AS MasterName, 
OrderStatus.Name AS OrderStatus, OrderEntity.Deleted 
FROM OrderEntity INNER JOIN UserEntity CustomerTable 
	ON OrderEntity.Customer = CustomerTable.Id 
		INNER JOIN OrderStatus 
			ON OrderEntity.OrderStatus = OrderStatus.Id 
				LEFT JOIN UserEntity MasterTable 
					ON OrderEntity.Master = MasterTable.Id 
WHERE OrderEntity.Id=?

-- Получить список заказов, которые помечены для удаления
SELECT OrderEntity.Id, OrderEntity.ThingName, 
	OrderEntity.Description, OrderEntity.CostWork, 
	OrderEntity.Customer AS CustomerId, 
	CustomerTable.LastName AS CustomerName, 
	OrderEntity.Master AS MasterId, 
	MasterTable.LastName AS MasterName, 
	OrderStatus.Name AS OrderStatus, OrderEntity.Deleted 
FROM OrderEntity INNER JOIN UserEntity CustomerTable 
	ON OrderEntity.Customer = CustomerTable.Id 
		INNER JOIN OrderStatus 
			ON OrderEntity.OrderStatus = OrderStatus.Id 
				LEFT JOIN UserEntity MasterTable 
					ON OrderEntity.Master = MasterTable.Id 
WHERE OrderEntity.Deleted=?

-- Обновить запись заказа
UPDATE OrderEntity SET ThingName=?, Description=? WHERE Id=?

-- Обновить статус заказа
UPDATE OrderEntity SET OrderStatus=? WHERE Id=?

-- Изменить поле удаления заказа
UPDATE OrderEntity SET Deleted=? WHERE Id=?

-- Изменить мастера заказа
UPDATE OrderEntity SET Master=? WHERE Id=?

-- Удалить заказ по id
DELETE FROM OrderEntity WHERE Id=?

-- Изменить стоимость работы заказа
UPDATE OrderEntity SET CostWork=? WHERE Id=?

-- Получить список всех запасных частей склада
SELECT * FROM ReplacementPart

-- Получить запасную часть по id
SELECT * FROM ReplacementPart WHERE Id=?

-- Обновить запись запасной части
UPDATE ReplacementPart SET Name=?, InStock=?, Cost=? WHERE Id=?

-- Создать запасную часть
INSERT INTO ReplacementPart(Name, InStock, Cost) VALUES (?, ?, ?)

-- Удалить запасную часть по id
DELETE FROM ReplacementPart WHERE Id=?

/*
	Обновить количество запчастей в наличии 
	на склде для запасной части по id
*/
UPDATE ReplacementPart SET InStock=? WHERE Id=?

-- Получить список запасных частей заказа по id
SELECT * FROM PartsList WHERE OrderEntity=?

-- Обновить количество запасных частей заказа
UPDATE PartsList SET NumberParts=? 
WHERE OrderEntity=? AND ReplacementPart=?

/*
	Добавить (создать) запасную часть 
	в список запасных частей заказа
*/
INSERT INTO PartsList(OrderEntity, ReplacementPart, NumberParts) 
	VALUES (?, ?, ?)
	
/*
	Удалить запасную часть из
	списка запасных частей заказа
*/
DELETE FROM PartsList WHERE OrderEntity=? AND ReplacementPart=?

/*
	Получить список запасных частей заказа
	со всеми запчастями со склада
*/
SELECT PartsList.OrderEntity AS OrderEntity, 
	ReplacementPart.Id AS ReplacementPart,  
	ReplacementPart.Name AS Name,  
	ReplacementPart.Cost AS Cost,  
	PartsList.NumberParts AS NumberParts  
FROM ReplacementPart 
	INNER JOIN PartsList 
		ON ReplacementPart.Id = PartsList.ReplacementPart 
WHERE PartsList.OrderEntity=? 
UNION 
SELECT ? AS OrderEntity, 
	ReplacementPart.Id AS ReplacementPart, 
	ReplacementPart.Name AS Name, 
	ReplacementPart.Cost AS Cost, 0 AS NumberParts 
FROM ReplacementPart 
WHERE ReplacementPart.Id NOT IN (SELECT PartsList.ReplacementPart 
								 FROM PartsList 
								 WHERE PartsList.OrderEntity=?)

/*
	Получить список запасных 
	частей заказас количеством 
	этих запасных частей на складе
*/
SELECT PartsList.ReplacementPart, PartsList.OrderEntity, 
	PartsList.NumberParts, ReplacementPart.Name AS Name, 
	ReplacementPart.InStock AS InStock 
FROM ReplacementPart LEFT JOIN PartsList 
	ON ReplacementPart.Id = PartsList.ReplacementPart 
WHERE PartsList.OrderEntity=?

-- Получить список запасных частей заказа
SELECT PartsList.OrderEntity AS OrderEntity, 
	ReplacementPart.Id AS ReplacementPart, 
	ReplacementPart.Name AS Name, 
	ReplacementPart.Cost AS Cost, 
	PartsList.NumberParts AS NumberParts 
FROM ReplacementPart INNER JOIN PartsList 
	ON ReplacementPart.Id = PartsList.ReplacementPart 
WHERE PartsList.OrderEntity=?
