-- Создание таблицы "Тип пользователя"
CREATE TABLE UserType (
	Id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Name VARCHAR(40)
);

-- Создание таблицы "Статус заказы"
CREATE TABLE OrderStatus (
	Id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Name VARCHAR(50)
);

-- Создание таблицы "Запасная часть"
CREATE TABLE ReplacementPart (
	Id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Name VARCHAR(50),
	InStock NUMBER(5),
	Cost NUMBER(5)
);

-- Создание таблицы "Пользователь"
CREATE TABLE UserEntity (
	Id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	Login VARCHAR(50),
	Password VARCHAR(50),
	Status VARCHAR(50),
	UserType NUMBER(5),
	FirstName VARCHAR(50),
	LastName VARCHAR(50),
	Phone VARCHAR(11),
	Authorized NUMBER(1),
	FOREIGN KEY (UserType) REFERENCES UserType (Id)
);

-- Создание таблицы "Заказ"		
CREATE TABLE OrderEntity (
	Id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
	ThingName VARCHAR(50),
	Description VARCHAR(255),
	OrderStatus NUMBER(5),
	Customer NUMBER(5),
	Master NUMBER(5),
	CostWork NUMBER(5),
	Deleted NUMBER(1),
	FOREIGN KEY (OrderStatus) REFERENCES OrderStatus (Id),
	FOREIGN KEY (Customer) REFERENCES UserEntity (Id) ON DELETE CASCADE,
	FOREIGN KEY (Master) REFERENCES UserEntity (Id) ON DELETE CASCADE
);

-- Создание таблицы "Список запасных частей"
CREATE TABLE PartsList (
	OrderEntity NUMBER(5),
	ReplacementPart NUMBER(5),
	NumberParts Number(5),
	FOREIGN KEY (OrderEntity) REFERENCES OrderEntity (Id) ON DELETE CASCADE,
	FOREIGN KEY (ReplacementPart) REFERENCES ReplacementPart (Id) ON DELETE CASCADE
);