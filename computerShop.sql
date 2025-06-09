
-- Tạo database
CREATE DATABASE ComputerShop;
GO

USE ComputerShop;
GO

-- Bảng Role
CREATE TABLE Role (
  RoleID INT PRIMARY KEY,
  RoleName VARCHAR(50) NOT NULL
);

-- Bảng User
CREATE TABLE [User] (
  UserID INT IDENTITY(1,1) PRIMARY KEY,
  RoleID INT NOT NULL,
  FullName NVARCHAR(50) NOT NULL,
  Email VARCHAR(50) NOT NULL,
  PhoneNumber CHAR(10) CHECK (PhoneNumber LIKE '0_________'),
  Address VARCHAR(100),
  Gender VARCHAR(10),
  FOREIGN KEY (RoleID) REFERENCES Role(RoleID)
);

-- Bảng Account
CREATE TABLE Account (
  AccountID INT IDENTITY(1,1) PRIMARY KEY,
  UserName VARCHAR(50) NOT NULL UNIQUE,
  PasswordHash VARCHAR(255) NOT NULL,
  UserID INT NOT NULL UNIQUE,
  FOREIGN KEY (UserID) REFERENCES [User](UserID)
);

-- Bảng Type
CREATE TABLE [Type] (
  TypeID INT IDENTITY(1,1) PRIMARY KEY,
  TypeName VARCHAR(50) NOT NULL
);

-- Bảng Brand
CREATE TABLE Brand (
  BrandID INT IDENTITY(1,1) PRIMARY KEY,
  BrandName VARCHAR(50) NOT NULL
);

-- Bảng Product
CREATE TABLE Product (
  ProductID INT IDENTITY(1,1) PRIMARY KEY,
  ProductName VARCHAR(100) NOT NULL,
  ProductQuantity INT NOT NULL CHECK (ProductQuantity > 0),
  Description NVARCHAR(MAX) NOT NULL,
  Price MONEY NOT NULL,
  TypeID INT NOT NULL,
  BrandID INT NOT NULL,
  CreatedAt DATETIME DEFAULT GETDATE(),
  UpdatedAt DATETIME,
  FOREIGN KEY (TypeID) REFERENCES [Type](TypeID),
  FOREIGN KEY (BrandID) REFERENCES Brand(BrandID)
);

-- Bảng ProductOption (tùy chọn cấu hình của sản phẩm)
CREATE TABLE ProductOption (
  OptionID INT IDENTITY(1,1) PRIMARY KEY,
  ProductID INT NOT NULL,
  OptionName VARCHAR(100) NOT NULL, -- Ví dụ: "RAM 8GB, SSD 512GB"
  AdditionalPrice MONEY DEFAULT 0, -- giá cộng thêm nếu có
  Quantity INT NOT NULL CHECK (Quantity >= 0),
  FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Bảng Specifications (tên thông số như RAM, SSD, CPU)
CREATE TABLE Specifications (
  SpecID INT IDENTITY(1,1) PRIMARY KEY,
  SpecName VARCHAR(50) NOT NULL
);

-- Bảng OptionHasSpec (thông số kỹ thuật cho mỗi option)
CREATE TABLE OptionHasSpec (
  OptionID INT NOT NULL,
  SpecID INT NOT NULL,
  SpecValue VARCHAR(255) NOT NULL, -- Ví dụ: "16GB", "Intel i7"
  PRIMARY KEY (OptionID, SpecID),
  FOREIGN KEY (OptionID) REFERENCES ProductOption(OptionID),
  FOREIGN KEY (SpecID) REFERENCES Specifications(SpecID)
);

-- Bảng Image
CREATE TABLE ProductImage (
  ImageID INT IDENTITY(1,1) PRIMARY KEY,
  URL VARCHAR(255) NOT NULL,
  ProductID INT NOT NULL,
  FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);
CREATE TABLE BrandImage (
  ImageID INT IDENTITY(1,1) PRIMARY KEY,
  URL VARCHAR(255) NOT NULL,
  BrandID INT NOT NULL,
  FOREIGN KEY (BrandID) REFERENCES Brand(BrandID)
);
CREATE TABLE UserImage (
  ImageID INT IDENTITY(1,1) PRIMARY KEY,
  URL VARCHAR(255) NOT NULL,
  UserID INT NOT NULL,
  FOREIGN KEY (UserID) REFERENCES [User](UserID)
);


-- Bảng Order
CREATE TABLE [Order] (
  OrderID INT IDENTITY(1,1) PRIMARY KEY,
  OrderDate DATE,
  Status INT NOT NULL CHECK (Status IN (0, 1)), -- 0: chưa mua, 1: đã mua
  UserID INT NOT NULL,
  FOREIGN KEY (UserID) REFERENCES [User](UserID)
);

-- Bảng OrderDetail
CREATE TABLE OrderDetail (
  OrderID INT NOT NULL,
  OptionID INT NOT NULL,
  OrderQuantity INT NOT NULL CHECK (OrderQuantity > 0),
  PRIMARY KEY (OrderID, OptionID),
  FOREIGN KEY (OrderID) REFERENCES [Order](OrderID),
  FOREIGN KEY (OptionID) REFERENCES ProductOption(OptionID)
);

-- Bảng Payment
CREATE TABLE Payment (
  PaymentID INT IDENTITY(1,1) PRIMARY KEY,
  OrderID INT NOT NULL,
  PaymentMethod VARCHAR(50) NOT NULL,
  PaymentStatus VARCHAR(50) NOT NULL,
  PaymentDate DATE,
  FOREIGN KEY (OrderID) REFERENCES [Order](OrderID)
);

-- Bảng Rating
CREATE TABLE Rating (
  UserID INT NOT NULL,
  ProductID INT NOT NULL,
  Rate INT NOT NULL CHECK (Rate BETWEEN 1 AND 5),
  Comment NVARCHAR(MAX),
  PRIMARY KEY (UserID, ProductID),
  FOREIGN KEY (UserID) REFERENCES [User](UserID),
  FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

-- Bảng Discount
CREATE TABLE Discount (
  DiscountID INT IDENTITY(1,1) PRIMARY KEY,
  ProductID INT NOT NULL,
  DiscountPercent FLOAT CHECK (DiscountPercent > 0 AND DiscountPercent <= 100),
  StartDate DATE NOT NULL,
  EndDate DATE NOT NULL,
  CreatedBy INT,
  FOREIGN KEY (ProductID) REFERENCES Product(ProductID),
  FOREIGN KEY (CreatedBy) REFERENCES [User](UserID)
);

-- Bảng Cart
CREATE TABLE Cart (
  CartID INT IDENTITY(1,1) PRIMARY KEY,
  UserID INT NOT NULL UNIQUE,
  CreatedAt DATETIME DEFAULT GETDATE(),
  FOREIGN KEY (UserID) REFERENCES [User](UserID)
);

-- Bảng CartItem 
CREATE TABLE CartItem (
  CartID INT NOT NULL,
  OptionID INT NOT NULL,
  Quantity INT NOT NULL CHECK (Quantity > 0),
  AddedAt DATETIME DEFAULT GETDATE(),
  PRIMARY KEY (CartID, OptionID),
  FOREIGN KEY (CartID) REFERENCES Cart(CartID),
  FOREIGN KEY (OptionID) REFERENCES ProductOption(OptionID)
);

-------------------------------------------Trigger-------------------------------------------
-- Trigger cập nhật giá thấp nhất
go
CREATE TRIGGER trg_UpdateProductPrice
ON ProductOption
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE p
    SET Price = ISNULL(po.MinPrice, 0)
    FROM Product p
    INNER JOIN (
        SELECT ProductID, MIN(AdditionalPrice) AS MinPrice
        FROM ProductOption
        GROUP BY ProductID
    ) po ON p.ProductID = po.ProductID;
END;
GO

-- Trigger cập nhật tổng số lượng tồn kho
CREATE TRIGGER trg_UpdateProductQuantity
ON ProductOption
AFTER INSERT, UPDATE, DELETE
AS
BEGIN
    SET NOCOUNT ON;
    UPDATE p
    SET ProductQuantity = ISNULL(po.TotalQty, 0)
    FROM Product p
    INNER JOIN (
        SELECT ProductID, SUM(Quantity) AS TotalQty
        FROM ProductOption
        GROUP BY ProductID
    ) po ON p.ProductID = po.ProductID;
END;

ALTER TABLE ProductOption
ADD IsActive BIT DEFAULT 1
UPDATE ProductOption
SET IsActive = 1
WHERE IsActive IS NULL;


--------------------------------------------------------------Insert ------------------------------------------------
-- ROLE
INSERT INTO Role (RoleID, RoleName)
VALUES (1, 'Admin'), (2, 'Customer');

-- USER
INSERT INTO [User] (RoleID, FullName, Email, PhoneNumber, Address, Gender)
VALUES 
(1, N'Anh Ngọc', 'ngocleanh05@gmail.com', '0334480483', N'Hà Tĩnh','Male'),
(1, N'Hoàng Linh', 'peapea4122005@gmail.com', '0987654321', N'Hưng Yên','Male'),
(1, N'Quang Minh', 'pqm1290@gmail.com', '0909123456', N'Lai Châu','Male'),
(2, N'Tú Nguyễn', 'tunguyen@gmail.com', '0987654320', N'Hà Nội','Male');

-- ACCOUNT
INSERT INTO Account (UserName, PasswordHash, UserID)
VALUES 
('oxyligen', 'admin', 1),
('magicteapot', 'admin', 2),
('byming', 'admin', 3),
('tunguyen', 'user', 4);

-- TYPE
INSERT INTO [Type] (TypeName) 
VALUES (N'Laptop Gaming'), (N'Laptop Văn Phòng'), ('Ultrabook'), ('MacBook');

-- BRAND
INSERT INTO Brand (BrandName) 
VALUES ('Dell'), ('HP'), ('Asus'), ('Lenovo'), ('Apple'), ('Acer');

-- PRODUCT
INSERT INTO Product (ProductName, ProductQuantity, Description, Price, TypeID, BrandID)
VALUES 
('Dell Gaming G15', 10, N'Máy tính chơi game mạnh mẽ', 25000000, 1, 1),
('HP Pavilion 14', 15, N'Laptop văn phòng bền bỉ', 15000000, 2, 2),
('Asus Zenbook 13', 8, N'Máy tính mỏng nhẹ, đẹp', 20000000, 3, 3),
('Lenovo LOQ 2024',7, N'Laptop hiệu năng cao, giá thành hợp lý',20000000,1,4),
('MacBook Air M2 2024', 10, N'Thanh lịch và sang trọng',24990000,4,5),
('HP 15 fd0015TU', 10, N'Laptop văn phòng phổ thông với cấu hình i7 thế hệ 13', 18990000, 2, 2),    
('HP 245 G9', 15, N'Laptop học tập, văn phòng giá rẻ sử dụng Ryzen 5', 8990000, 2, 2),               
('MacBook Pro M1 2021', 5, N'MacBook hiệu năng cao, chip M1 Pro', 34340000, 4, 5),                  
('Dell Inspiron 15 3530', 8, N'Laptop văn phòng hiện đại với CPU Intel Gen 13', 18490000, 2, 1),    
('MSI Modern 14 C7M', 12, N'Laptop mỏng nhẹ, cấu hình cao từ MSI', 9490000, 2, 1),                   
('HP Elitebook 630 G10', 7, N'Dòng laptop doanh nhân Elitebook cao cấp', 19990000, 2, 2),            
('Samsung Galaxy Book Pro', 6, N'Laptop mỏng nhẹ với SSD 1TB, màn OLED', 19290000, 3, 2),           
('Acer Aspire 5 A514', 9, N'Laptop văn phòng tầm trung, i7 Gen 13', 17990000, 2, 6),               
('HP Probook 450 G10', 10, N'Dòng laptop văn phòng Probook nổi bật', 18990000, 2, 2),              
('Dell Latitude 3440', 8, N'Lựa chọn phổ thông cho doanh nghiệp', 18490000, 2, 1); 

-- IMAGE
INSERT INTO ProductImage(URL, ProductID)
VALUES 
('https://i.postimg.cc/2S6Fnrbk/LOQ-15-IAX9-I-CT1-05-1739241100-1.png', 4),
('https://i.postimg.cc/t4Md5wWh/LOQ-15-IAX9-I-THUMPNAIL-1-1739241099-1.png', 4),
('https://i.postimg.cc/3J3ZJg3D/LOQ-15-IAX9-I-CT2-05-1739241098-1.png', 4),
('https://i.postimg.cc/RCJbk1X1/macbook-air-13-m2-midnight-1-35053fbcf9.png', 5),
('https://i.postimg.cc/QMMf0cFT/20448-asus-zenbook-13-ux325ea-eg079t-7.jpg',3),
('https://i.postimg.cc/3Rs986nX/c08165906.png',2),
('https://i.postimg.cc/MGXYyxL0/dell-gaming-g15-phong-cach-thiet-ke-1920x1080-800-resize.jpg',1),
('https://i.postimg.cc/Gt6gCBS1/hp-15-fd0015tu-i7-a19c5pa-12-750x500.jpg', 6),
('https://i.postimg.cc/28hN5mdx/hp-245-g9-r5-6l1n9pa-600x600.jpg', 7),
('https://i.postimg.cc/63yDxN5n/macbook-pro-16-teacherstore-e0a3f46f-e024-4e7a-85a8-d5041bac9fd0.webp', 8),
('https://i.postimg.cc/CK6QN6H1/dell-inspiron-15-3530-i5-n5i5791w1-thumb-1-600x600.jpg', 9),
('https://i.postimg.cc/wTc4PBcn/kv-nb.png', 10),
('https://i.postimg.cc/Jh8xkNct/83298-laptop-hp-elitebook-630-g10.jpg', 11),
('https://i.postimg.cc/nzSBLBhs/Samsung-Galaxy-Book-Pro.webp', 12),
('https://i.postimg.cc/FKfWyyY4/Acer-Aspire-5-A514.png', 13),
('https://i.postimg.cc/m2j1wK9Y/HP-Probook-450-G10.jpg', 14),
('https://i.postimg.cc/k5bxffq7/dell-latitude-3440.webp', 15);


-- BrandImage
INSERT INTO BrandImage(URL, BrandID)
VALUES 
('https://i.postimg.cc/2S6Fnrbk/LOQ-15-IAX9-I-CT1-05-1739241100-1.png', 3),
('https://i.postimg.cc/fR09KqtJ/a41821ec96cb7c0a5dba556bdc6d7ecb.png', 1),
('https://i.postimg.cc/CL28bpK3/662da513b477d8a711a60f5d138e1b8a.png', 2),
('https://i.postimg.cc/Y2bYqWBq/4c0bc875c3675810451738dbeb5a68be.png', 4),
('https://i.postimg.cc/1zGNxgKS/5706e2768457b2ae7e9c43fe32bcea9e.png',5)
;


-- SPECIFICATIONS
INSERT INTO Specifications (SpecName)
VALUES 
('RAM'), 
('Storage'), 
('CPU'),
('GPU'), 
('Screen Size'),
('Refresh rate'),
('Resolution'),
('Battery'),
('Port'),
('Weight'),
('Warranty');

-- PRODUCT OPTION 
INSERT INTO ProductOption (ProductID, OptionName, AdditionalPrice, Quantity)
VALUES 
-- Dell Gaming G15
(1, '8GB / 512GB SSD / Ryzen 5 5600H', 28500000, 5),    
(1, '16GB / 1TB SSD / Ryzen 7 5800H', 33000000, 3),
(1, '16GB / 512GB SSD / Ryzen 5 5600H', 30000000, 5),

-- HP Pavilion 14
(2, '8GB / 512GB SSD / Core i5-1235U', 18000000, 5),    
(2, '16GB / 512GB SSD / Ryzen 7 7730U', 18700000, 1),


-- Asus Zenbook 13
(3, '8GB / 512GB SSD / Core i5-1240P', 28000000, 5),    
(3, '16GB / 512GB SSD / Ryzen 7 7730U', 30700000, 1),

-- Lenovo LOQ 2024
(4, '8GB / 512GB SSD / Core i5-12450H', 18500000, 5),
(4, '16GB / 512GB SSD / Ryzen 7 7735HS', 24490000, 5),

-- MacBook Air M2
(5, '8GB / 256GB SSD / Apple M2 (8-core GPU)', 19990000, 5),
(5, '16GB / 512GB SSD / Apple M2 (10-core GPU)', 20990000, 5),

(6, '16GB / 512GB SSD / Core i7-1355U', 18990000, 10),
(7, '8GB / 256GB SSD / Ryzen 5 5625U', 8990000, 15),
(8, '16GB / 512GB SSD / M1 Pro', 34340000, 5),
(9, '16GB / 512GB SSD / Core i7-1355U', 18490000, 8),
(10, '16GB / 512GB SSD / Ryzen 7 7730U', 9490000, 12),
(11, '16GB / 512GB SSD / Core i7-1355U', 19990000, 7),
(12, '16GB / 1TB SSD / Core i7-1165G7', 19290000, 6),
(13, '16GB / 512GB SSD / Core i7-1355U', 17990000, 9),
(14, '16GB / 512GB SSD / Core i7-1355U', 18990000, 10),
(15, '16GB / 512GB SSD / Core i7-1355U', 18490000, 8);


-- OPTION HAS SPEC
INSERT INTO OptionHasSpec (OptionID , SpecID , SpecValue)
VALUES

--Dell Gaming G15
(1,1,'8GB'),
(1,2,'512GB SSD DDR4'),
(1,3,'Ryzen 5 5600H'),
(1,4,'RTX 3050'),
(1,5,'15.6"'),
(1,6,'120Hz'),
(1,7,'FHD 1920x1080'),
(1,8,'3-cell, 56 Wh'),
(1,9,'1x USB-C, 2x USB-A'),
(1,10,'~2.57 kg'),
(1,11,'24 Month'),

(2,1,'16GB'),
(2,2,'1TB SSD DDR4'),
(2,3,'Ryzen 7 5800H'),
(2,4,'RTX 3050 Ti'),
(2,5,'15.6"'),
(2,6,'144Hz'),
(2,7,'FHD 1920x1080'),
(2,8,'3-cell, 56 Wh'),
(2,9,'1x USB-C, 2x USB-A'),
(2,10,'~2.57 kg'),
(2,11,'24 Month'),

(3,1,'16GB'),
(3,2,'512GB SSD DDR4'),
(3,3,'Ryzen 5 5600H'),
(3,4,'RTX 3050'),
(3,5,'15.6"'),
(3,6,'120Hz'),
(3,7,'FHD 1920x1080'),
(3,8,'3-cell, 56 Wh'),
(3,9,'1x USB-C, 2x USB-A'),
(3,10,'~2.57 kg'),
(3,11,'24 Month'),

-- HP Pavilion 14
(4,1,'8GB'),
(4,2,'512GB SSD DDR4'),
(4,3,'Intel Core i5-1235U'),
(4,4,'Intel Iris Xe Graphics'),
(4,5,'14"'),
(4,6,'60Hz'),
(4,7,'FHD 1920x1080'),
(4,8,'3-cell, 43 Wh'),
(4,9,'1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(4,10,'~1.41 kg'),
(4,11,'12 Month'),

(5,1,'16GB'),
(5,2,'512GB SSD DDR4'),
(5,3,'AMD Ryzen 7 7730U'),
(5,4,'AMD Radeon Graphics'),
(5,5,'14"'),
(5,6,'60Hz'),
(5,7,'FHD 1920x1080'),
(5,8,'3-cell, 43 Wh'),
(5,9,'1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(5,10,'~1.41 kg'),
(5,11,'12 Month'),

-- Asus Zenbook 13
(6,1,'8GB'),
(6,2,'512GB SSD LPDDR5'),
(6,3,'Intel Core i5-1240P'),
(6,4,'Intel Iris Xe Graphics'),
(6,5,'13.3"'),
(6,6,'60Hz'),
(6,7,'FHD 1920x1080'),
(6,8,'67 Wh'),
(6,9,'2x Thunderbolt 4, 1x USB-A, HDMI, jack 3.5mm'),
(6,10,'~1.1 kg'),
(6,11,'12 Month'),

(7,1,'16GB'),
(7,2,'512GB SSD LPDDR5'),
(7,3,'AMD Ryzen 7 7730U'),
(7,4,'AMD Radeon Graphics'),
(7,5,'13.3"'),
(7,6,'60Hz'),
(7,7,'FHD 1920x1080'),
(7,8,'67 Wh'),
(7,9,'2x USB-C, 1x USB-A, HDMI, jack 3.5mm'),
(7,10,'~1.1 kg'),
(7,11,'12 Month'),

-- Option 8: Lenovo LOQ 2024
(8,1,'8GB'),
(8,2,'512GB SSD DDR5'),
(8,3,'Intel Core i5-12450H'),
(8,4,'RTX 4060'),
(8,5,'15.6"'),
(8,6,'144Hz'),
(8,7,'FHD 1920x1080'),
(8,8,'60 Wh'),
(8,9,'1x USB-C, 2x USB-A, HDMI, Ethernet, jack 3.5mm'),
(8,10,'~2.4 kg'),
(8,11,'24 Month'),

(9,1,'16GB'),
(9,2,'512GB SSD DDR5'),
(9,3,'AMD Ryzen 7 7735HS'),
(9,4,'RTX 4050'),
(9,5,'15.6"'),
(9,6,'165Hz'),
(9,7,'WQHD 2560x1440'),
(9,8,'80 Wh'),
(9,9,'1x USB-C, 2x USB-A, HDMI, Ethernet, jack 3.5mm'),
(9,10,'~2.4 kg'),
(9,11,'24 Month'),

-- Option 10: MacBook Air M2
(10,1,'8GB'),
(10,2,'256GB SSD'),
(10,3,'Apple M2 (8-core CPU, 8-core GPU)'),
(10,4,'Integrated 8-core GPU'),
(10,5,'13.6"'),
(10,6,'60Hz'),
(10,7,'2560x1664'),
(10,8,'52.6 Wh'),
(10,9,'2x Thunderbolt 4, MagSafe 3, jack 3.5mm'),
(10,10,'~1.24 kg'),
(10,11,'12 Month'),

(11,1,'16GB'),
(11,2,'512GB SSD'),
(11,3,'Apple M2 (8-core CPU, 10-core GPU)'),
(11,4,'Integrated 10-core GPU'),
(11,5,'13.6"'),
(11,6,'60Hz'),
(11,7,'2560x1664'),
(11,8,'52.6 Wh'),
(11,9,'2x Thunderbolt 4, MagSafe 3, jack 3.5mm'),
(11,10,'~1.24 kg'),
(11,11,'12 Month'),

(12, 1, '16GB'), (12, 2, '512GB SSD'), (12, 3, 'Intel Core i7-1355U'),
(12, 4, 'Intel Iris Xe Graphics'), (12, 5, '15.6"'), (12, 6, '60Hz'),
(12, 7, 'FHD 1920x1080'), (12, 8, '3-cell, 41 Wh'),
(12, 9, '1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(12, 10, '~1.7 kg'), (12, 11, '12 Month'),

-- Option 13: HP 245 G9
(13, 1, '8GB'), (13, 2, '256GB SSD'), (13, 3, 'AMD Ryzen 5 5625U'),
(13, 4, 'AMD Radeon Graphics'), (13, 5, '14"'), (13, 6, '60Hz'),
(13, 7, 'FHD 1920x1080'), (13, 8, '3-cell, 41 Wh'),
(13, 9, '1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(13, 10, '~1.47 kg'), (13, 11, '12 Month'),

-- Option 14: MacBook Pro M1
(14, 1, '16GB'), (14, 2, '512GB SSD'), (14, 3, 'Apple M1 Pro'),
(14, 4, 'Integrated 16-core GPU'), (14, 5, '14.2"'), (14, 6, '120Hz'),
(14, 7, '3024x1964'), (14, 8, '70 Wh'),
(14, 9, '3x Thunderbolt 4, HDMI, SDXC, MagSafe 3'),
(14, 10, '~1.6 kg'), (14, 11, '12 Month'),

-- Option 15: Dell Inspiron 15 3530
(15, 1, '16GB'), (15, 2, '512GB SSD'), (15, 3, 'Intel Core i7-1355U'),
(15, 4, 'Intel Iris Xe Graphics'), (15, 5, '15.6"'), (15, 6, '60Hz'),
(15, 7, 'FHD 1920x1080'), (15, 8, '3-cell, 41 Wh'),
(15, 9, '1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(15, 10, '~1.65 kg'), (15, 11, '12 Month'),

-- Option 16: MSI Modern 14 C7M
(16, 1, '16GB'), (16, 2, '512GB SSD'), (16, 3, 'AMD Ryzen 7 7730U'),
(16, 4, 'AMD Radeon Graphics'), (16, 5, '14"'), (16, 6, '60Hz'),
(16, 7, 'FHD 1920x1080'), (16, 8, '3-cell, 39 Wh'),
(16, 9, '1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(16, 10, '~1.4 kg'), (16, 11, '12 Month'),

-- Option 17: HP Elitebook 630 G10
(17, 1, '16GB'), (17, 2, '512GB SSD'), (17, 3, 'Intel Core i7-1355U'),
(17, 4, 'Intel Iris Xe Graphics'), (17, 5, '13.3"'), (17, 6, '60Hz'),
(17, 7, 'FHD 1920x1080'), (17, 8, '3-cell, 42 Wh'),
(17, 9, '1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(17, 10, '~1.24 kg'), (17, 11, '12 Month'),

-- Option 18: Samsung Galaxy Book Pro
(18, 1, '16GB'), (18, 2, '1TB SSD'), (18, 3, 'Intel Core i7-1165G7'),
(18, 4, 'Intel Iris Xe Graphics'), (18, 5, '15.6"'), (18, 6, '60Hz'),
(18, 7, 'FHD 1920x1080'), (18, 8, '3-cell, 68 Wh'),
(18, 9, '1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(18, 10, '~1.05 kg'), (18, 11, '12 Month'),

-- Option 19: Acer Aspire 5 A514
(19, 1, '16GB'), (19, 2, '512GB SSD'), (19, 3, 'Intel Core i7-1355U'),
(19, 4, 'Intel Iris Xe Graphics'), (19, 5, '14"'), (19, 6, '60Hz'),
(19, 7, 'FHD 1920x1080'), (19, 8, '3-cell, 50 Wh'),
(19, 9, '1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(19, 10, '~1.3 kg'), (19, 11, '12 Month'),

-- Option 20: HP Probook 450 G10
(20, 1, '16GB'), (20, 2, '512GB SSD'), (20, 3, 'Intel Core i7-1355U'),
(20, 4, 'Intel Iris Xe Graphics'), (20, 5, '15.6"'), (20, 6, '60Hz'),
(20, 7, 'FHD 1920x1080'), (20, 8, '3-cell, 45 Wh'),
(20, 9, '1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(20, 10, '~1.74 kg'), (20, 11, '12 Month'),

-- Option 21: Dell Latitude 3440
(21, 1, '16GB'), (21, 2, '512GB SSD'), (21, 3, 'Intel Core i7-1355U'),
(21, 4, 'Intel Iris Xe Graphics'), (21, 5, '14"'), (21, 6, '60Hz'),
(21, 7, 'FHD 1920x1080'), (21, 8, '3-cell, 42 Wh'),
(21, 9, '1x USB-C, 2x USB-A, HDMI, jack 3.5mm'),
(21, 10, '~1.5 kg'), (21, 11, '12 Month');



-- ORDER
INSERT INTO [Order] (OrderDate, Status, UserID)
VALUES (GETDATE(), 1, 2); -- OrderID = 1

-- ORDER DETAIL
INSERT INTO OrderDetail (OrderID, ProductID, OrderQuantity)
VALUES (1, 1, 1);

-- PAYMENT
INSERT INTO Payment (OrderID, PaymentMethod, PaymentStatus, PaymentDate)
VALUES (1, 'Credit Card', 'Paid', GETDATE());

-- RATING
INSERT INTO Rating (UserID, ProductID, Rate, Comment)
VALUES (2, 1, 5, N'Rất tốt!');

-- DISCOUNT
INSERT INTO Discount (ProductID, DiscountPercent, StartDate, EndDate, CreatedBy)
VALUES (1, 10, '2025-06-01', '2025-06-30', 1);

-- CART
INSERT INTO Cart (UserID) VALUES (2); -- CartID = 1

-- CART ITEM
INSERT INTO CartItem (CartID, ProductID, Quantity)
VALUES (1, 1, 1);