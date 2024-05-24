CREATE DATABASE QLDL;
USE QLDL;
DROP DATABASE QLDL;

CREATE TABLE  ROLE
(
	Role_Id  int NOT NULL,
	Role_Name varchar(40),
	CONSTRAINT PK_ROLE PRIMARY KEY(Role_Id)
);

CREATE TABLE PERMISSION_ITEM
(
	PermissionItem_Id int NOT NULL,
	PermissionItem_Name varchar(40),
	CONSTRAINT PK_PERMISSION_ITEM PRIMARY KEY(PermissionItem_Id)
);

CREATE TABLE ROLEDETAIL
(
	RoleDetail_Id int NOT NULL,
	Id_Role int,
	Id_PermissionItem int,
	Permission varchar(30),
	CONSTRAINT PK_ROLEDETAIL PRIMARY KEY(RoleDetail_Id),
	CONSTRAINT FK_ROLEDETAIL1 FOREIGN KEY(Id_Role) REFERENCES ROLE(Role_Id),
	CONSTRAINT FK_ROLEDETAIL2 FOREIGN KEY(Id_PermissionItem) REFERENCES PERMISSION_ITEM(PermissionItem_Id)
);

CREATE TABLE USER
(
	User_Id int NOT NULL,
	IdRole int,
	UserName varchar(40),
	Password varchar(40),
    User_Status tinyint,
	CONSTRAINT PK_USER PRIMARY KEY(User_Id),
	CONSTRAINT FK_USER1 FOREIGN KEY(IdRole) REFERENCES ROLE(Role_Id)
);
CREATE TABLE USER_INFO
(
	UserInfo_Id int NOT NULL,
	IdUser int,
	UserInfo_Name varchar(40),
	UserInfo_Address varchar(40),
	UserInfo_BirthDate Date,
    UserInfo_Email varchar(40),
    UserInfo_PhoneNumber varchar(40),
	CONSTRAINT PK_USER_INFO PRIMARY KEY(UserInfo_Id),
	CONSTRAINT FK_USER_INFO FOREIGN KEY(IdUser) REFERENCES USER(User_Id)
);

CREATE TABLE AGENT
(
	Agent_Id int NOT NULL,
	Agent_Name varchar(40),
	Agent_Phone varchar(10),
	Agent_Address varchar(200),
	Agent_District varchar(30),
    Agent_Status tinyint,
    Agent_Debt double,
    Agent_Type varchar(30),
    Agent_Email varchar(30),
	CONSTRAINT PK_AG PRIMARY KEY(Agent_Id)
);


CREATE TABLE AGENT_TYPE
(
	AgentType_Id int NOT NULL,
	AgentType_Name varchar(40),
    AgentType_MaxDebt long,
    AgentType_Status tinyint,
	CONSTRAINT PK_AGT PRIMARY KEY(AgentType_Id)
);

CREATE TABLE RECEPTION
(
	Reception_Id int NOT NULL,
	IdAgent int,
	IdAgentType int,
	ReceptionDate date,
	CONSTRAINT PK_RCP PRIMARY KEY(Reception_Id),
	CONSTRAINT FK_RCP1 FOREIGN KEY(IdAgent) REFERENCES AGENT(Agent_Id),
	CONSTRAINT FK_RCP2 FOREIGN KEY(IdAgentType) REFERENCES AGENT_TYPE(AgentType_Id)
);

CREATE TABLE RECEIPT
(
	Receipt_Id int NOT NULL,
	Id_Agent int,	
    Id_User int,
	ReceiptDate date,	
	MoneyReceived double,
    IsReceived tinyint,
	CONSTRAINT PK_RECEIPT PRIMARY KEY(Receipt_Id),
	CONSTRAINT FK_RECEIPT FOREIGN KEY(Id_Agent) REFERENCES Agent(Agent_Id),
    CONSTRAINT FK_RECEIPT2 FOREIGN KEY(Id_User) REFERENCES User(User_Id)
);

CREATE TABLE IMPORT	
(
	ImportId int NOT NULL,
    Id_User int,
	ImportDate date,
	Import_TotalMoney double,
	CONSTRAINT PK_IMPORT PRIMARY KEY(ImportId),
    CONSTRAINT FK_IMPORT FOREIGN KEY(Id_User) REFERENCES USER(User_Id)
);

CREATE TABLE ITEMS
(
	Items_Id int NOT NULL,
	Items_Name varchar(30),
	Items_Price double,
	Items_Quantity int,
    Items_Status tinyint,
	CONSTRAINT PK_ITEMS PRIMARY KEY(Items_Id)
);


CREATE TABLE UNIT
(
	Unit_Id int NOT NULL,
	Unit_Name varchar(40),
    Unit_Status tinyint,
	CONSTRAINT PK_UNIT PRIMARY KEY(Unit_Id)
);

CREATE TABLE IMPORT_DETAIL
(
	Import_Detail_Id int NOT NULL,
	IdImport int,
	IdUnit int,
	IdItems int,
	Items_Price double,
	Total_Money double,
	Items_Quantity int,
	CONSTRAINT PK_IMPORT_DETAIL PRIMARY KEY(Import_Detail_Id),
	CONSTRAINT FK_IMPORT_DETAIL1 FOREIGN KEY(IdImport) REFERENCES IMPORT(ImportId),
	CONSTRAINT FK_IMPORT_DETAIL2 FOREIGN KEY(IdUnit) REFERENCES UNIT(Unit_Id),
	CONSTRAINT FK_IMPORT_DETAIL3 FOREIGN KEY(IdItems) REFERENCES ITEMS(Items_Id)
);

CREATE TABLE EXPORT
(
	Export_Id int NOT NULL,
	Id_Agent int,
    Id_User int,
    Id_Receipt int,
	Export_Date date,
	Export_TotalMoney double,
	PayAmount double,
    Remaining double,
	CONSTRAINT PK_EXPORT PRIMARY KEY(Export_Id),
	CONSTRAINT FK_EXPORT FOREIGN KEY(Id_Agent) REFERENCES AGENT(Agent_Id),
    CONSTRAINT FK_EXPORT2 FOREIGN KEY(Id_User) REFERENCES USER(User_Id),
    CONSTRAINT FK_EXPORT3 FOREIGN KEY(Id_Receipt) REFERENCES Receipt(Receipt_Id)
);

CREATE TABLE EXPORT_DETAIL
(
	ExportDetail_Id int NOT NULL,
	IdExport int,
	ItemsUnit int,
	ItemsId int,
	ItemsQuantity int,
	PriceExport double,
	TotalMoney_Export double,
	CONSTRAINT PK_EXPORT_DETAIL PRIMARY KEY(ExportDetail_Id),
	CONSTRAINT FK_EXPORT_DETAIL1 FOREIGN KEY(IdExport) REFERENCES EXPORT(Export_Id),
	CONSTRAINT FK_EXPORT_DETAIL2 FOREIGN KEY(ItemsUnit) REFERENCES UNIT(Unit_Id),
	CONSTRAINT FK_EXPORT_DETAIL3 FOREIGN KEY(ItemsId) REFERENCES ITEMS(Items_Id)
);

CREATE TABLE SALES_REPORT
(
	SalesReport_Id int NOT NULL,
    Id_User int,
	SalesReport_Date date,
	CONSTRAINT PK_SALES_REPORT PRIMARY KEY(SalesReport_Id),
    CONSTRAINT FK_SALES_REPORT FOREIGN KEY (Id_User) references USER(User_Id)
);

CREATE TABLE SALES_REPORT_DETAIL
(
	SalesReportDetail_Id int NOT NULL,
	IdSalesReport int,
	IdAgentType int,
	AmountExport int,
	TotalMoney double,
	Rate float,
	CONSTRAINT PK_SALES_REPORT_DETAIL1 PRIMARY KEY(SalesReportDetail_Id),
	CONSTRAINT FK_SALES_REPORT_DETAIL1 FOREIGN KEY(IdSalesReport) REFERENCES SALES_REPORT(SalesReport_Id),
	CONSTRAINT FK_SALES_REPORT_DETAIL2 FOREIGN KEY(IdAgentType) REFERENCES AGENT_TYPE(AgentType_Id)
);

CREATE TABLE DEBT_AGENT_REPORT
(
	DebtAgentReport_Id int,
	DebtAgentReport_Date date,
	CONSTRAINT PK_DEBT_AGENT_REPORT PRIMARY KEY(DebtAgentReport_Id)
);

CREATE TABLE DEBT_AGENT_REPORT_DETAIL
(
	DebtAgentReportDetail_Id int NOT NULL,
	IdDebtAgentReport int,
	IdAgent int,
	NoDau double, 
	PhatSinh double,
	NoCuoi double,
	CONSTRAINT PK_DEBT_AGENT_REPORT_DETAIL PRIMARY KEY(DebtAgentReportDetail_Id),
	CONSTRAINT FK_DEBT_AGENT_REPORT_DETAIL1 FOREIGN KEY(IdDebtAgentReport) REFERENCES DEBT_AGENT_REPORT(DebtAgentReport_Id),
	CONSTRAINT FK_DEBT_AGENT_REPORT_DETAIL2 FOREIGN KEY(IdAgent) REFERENCES AGENT(Agent_Id)
);

CREATE TABLE FIXED_VALUES (
  Maximum_Agent INT NOT NULL,
  Maximum_InDebt DECIMAL(10,2) NOT NULL,
  Maximum_IsOverPay DECIMAL(10,2) NOT NULL
);


CREATE TABLE district (
    district_Id INT PRIMARY KEY,
    district_Name VARCHAR(40)
);


INSERT INTO AGENT(Agent_Id, Agent_Name, Agent_Phone, Agent_Address, Agent_District, Agent_Debt)
VALUES ('<Unique_Agent_Id>', '<Agent_Name>', '<Agent_Phone>', '<Agent_Address>', '<Agent_District>','Agent_Debt');

INSERT INTO AGENT_TYPE(AgentType_Id, AgentType_Name)
VALUES ('<Unique_AgentType_Id>', '<AgentType_Name>');

INSERT INTO RECEPTION(Reception_Id, IdAgent, IdAgentType, ReceptionDate)
VALUES ('<Unique_Reception_Id>', '<IdAgent>', '<IdAgentType>', '<ReceptionDate>');

INSERT INTO RECEIPT(Receipt_Id, Id_Agent, Phone, Email, ReceiptDate, MoneyReceived)
VALUES ('<Unique_Receipt_Id>', '<Id_Agent>', '<Phone>', '<Email>', '<ReceiptDate>', '<MoneyReceived>');

INSERT INTO IMPORT(ImportId, ImportDate, Import_TotalMoney)
VALUES ('<Unique_ImportId>', '<ImportDate>', '<Import_TotalMoney>');

INSERT INTO ITEMS(Items_Id, Items_Name, Items_Price, Items_Quentity)
VALUES ('<Unique_Items_Id>', '<Items_Name>', '<Items_Price>', '<Items_Quentity>');

INSERT INTO UNIT(Unit_Id, Unit_Name)
VALUES ('<Unique_Unit_Id>', '<Unit_Name>');

INSERT INTO IMPORT_DETAIL(Import_Detail_Id, IdImport, IdUnit, IdItems, Items_Price, Total_Money, Items_Quentity)
VALUES ('<Unique_Import_Detail_Id>', '<IdImport>', '<IdUnit>', '<IdItems>', '<Items_Price>', '<Total_Money>', '<Items_Quentity>');

INSERT INTO EXPORT(Export_Id, IdAgent, Export_Date, Export_TotalMoney,PayAmount)
VALUES ('<Unique_Export_Id>', '<IdAgent>', '<Export_Date>', '<Export_TotalMoney>','<PayAmout>');

INSERT INTO EXPORT_DETAIL(ExportDetail_Id, IdExport, ItemsUnit, Items, ItemsQuantity, PriceExport, TotalMoney_Export, Remaining)
VALUES ('<Unique_ExportDetail_Id>', '<IdExport>', '<ItemsUnit>', '<Items>', '<ItemsQuantity>', '<PriceExport>', '<TotalMoney_Export>', '<Remaining>');

INSERT INTO SALES_REPORT(SalesReport_Id, SalesReport_Date)
VALUES ('<Unique_SalesReport_Id>', '<SalesReport_Date>');

INSERT INTO SALES_REPORT_DETAIL(SalesReportDetail_Id, IdSalesReport, IdAgentType, AmountExport, TotalMoney, Rate)
VALUES ('<Unique_SalesReportDetail_Id>', '<IdSalesReport>', '<IdAgentType>', '<AmountExport>', '<TotalMoney>', '<Rate>');

INSERT INTO DEBT_AGENT_REPORT(DebtAgentReport_Id, DebtAgentReport_Date)
VALUES ('<Unique_DebtAgentReport_Id>', '<DebtAgentReport_Date>');

INSERT INTO DEBT_AGENT_REPORT_DETAIL(DebtAgentReportDetail_Id, IdDebtAgentReport, IdAgent, NoDau, PhatSinh, NoCuoi)
VALUES ('<Unique_DebtAgentReportDetail_Id>', '<IdDebtAgentReport>', '<IdAgent>', '<NoDau>', '<PhatSinh>', '<NoCuoi>');

INSERT INTO FIXED_VALUES(Maximum_Agent, Maximum_InDebt, Maximum_IsOverPay)
VALUES ('<Maximum_Agent>', '<Maximum_InDebt>', '<Maximum_IsOverPay>');

INSERT INTO DISTRICT(maximum_Agent)
VALUES ('<maximum_Agent>');

INSERT INTO DVT(SL_DVT)
VALUES ('<SL_DVT>');


UPDATE AGENT
SET Agent_Name = '<New_Agent_Name>',
    Agent_Phone = '<New_Agent_Phone>',
    Agent_Address = '<New_Agent_Address>',
    Agent_District = '<New_Agent_District>',
	Agent_Debt = '<New_Agent_Debt>'
WHERE Agent_Id = '<Agent_Id_to_Update>';

---
UPDATE AGENT_TYPE
SET AgentType_Name = '<New_AgentType_Name>'
WHERE AgentType_Id = '<AgentType_Id_to_Update>';

---
UPDATE RECEPTION 
SET IdAgent = '<New_IdAgent>',
    IdAgentType = '<New_IdAgentType>',
    ReceptionDate = '<New_ReceptionDate>'
WHERE Reception_Id = '<Reception_Id_to_Update>';

---
UPDATE RECEIPT
SET Id_Agent = '<New_Id_Agent>',
	Phone = '<New_Phone>',
    Email = '<New_Email>',
    ReceiptDate = '<New_ReceiptDate>',
    MoneyReceived = '<New_MoneyReceived>'
WHERE Receipt_Id = '<Receipt_Id_to_Update>';

---
UPDATE IMPORT
SET ImportDate = '<New_ImportDate>',
    Import_TotalMoney = '<New_Import_TotalMoney>'
WHERE ImportId = '<ImportId_to_Update>';

---
UPDATE ITEMS
SET Items_Name = '<New_Items_Name>',
    Items_Price = '<New_Items_Price>',
    Items_Quentity = '<New_Items_Quentity>'
WHERE Items_Id = '<Items_Id_to_Update>';

---
UPDATE UNIT
SET Unit_Name = '<New_Unit_Name>'
WHERE Unit_Id = '<Unit_Id_to_Update>';

---
UPDATE IMPORT_DETAIL
SET IdImport = '<New_IdImport>',
    IdUnit = '<New_IdUnit>',
    IdItems = '<New_IdItems>',
    Items_Price = '<New_Items_Price>',
    Total_Money = '<New_Total_Money>',
    Items_Quentity = '<New_Items_Quentity>'
WHERE Import_Detail_Id = '<Import_Detail_Id_to_Update>';

---
UPDATE EXPORT
SET IdAgent = '<New_IdAgent>',
    Export_Date = '<New_Export_Date>',
    Export_TotalMoney = '<New_Export_TotalMoney>',
	PayAmount = '<New_PayAmount>'
WHERE Export_Id = '<Export_Id_to_Update>';


---
UPDATE EXPORT_DETAIL
SET IdExport = '<New_IdExport>',
    ItemsUnit = '<New_ItemsUnit>',
    Items = '<New_Items>',
    ItemsQuantity = '<New_ItemsQuantity>',
    PriceExport = '<New_PriceExport>',
    TotalMoney_Export = '<New_TotalMoney_Export>',
    Remaining = '<New_Remaining>'
WHERE ExportDetail_Id = '<ExportDetail_Id_to_Update>';


---
UPDATE SALES_REPORT
SET SalesReport_Date = '<New_SalesReport_Date>'
WHERE SalesReport_Id = '<SalesReport_Id_to_Update>';

---
UPDATE SALES_REPORT_DETAIL
SET IdSalesReport = '<New_IdSalesReport>',
    IdAgentType = '<New_IdAgentType>',
    AmountExport = '<New_AmountExport>',
    TotalMoney = '<New_TotalMoney>',
    Rate = '<New_Rate>'
WHERE SalesReportDetail_Id = '<SalesReportDetail_Id_to_Update>';

---
UPDATE DEBT_AGENT_REPORT
SET DebtAgentReport_Date = '<New_DebtAgentReport_Date>'
WHERE DebtAgentReport_Id = '<DebtAgentReport_Id_to_Update>';

---
UPDATE DEBT_AGENT_REPORT_DETAIL
SET IdDebtAgentReport = '<New_IdDebtAgentReport>',
    IdAgent = '<New_IdAgent>',
    NoDau = '<New_NoDau>',
    PhatSinh = '<New_PhatSinh>',
    NoCuoi = '<New_NoCuoi>'
WHERE DebtAgentReportDetail_Id = '<DebtAgentReportDetail_Id_to_Update>';

---
UPDATE FIXED_VALUES
SET Maximum_Agent = '<New_Maximum_Agent>',
    Maximum_InDebt = '<New_Maximum_InDebt>',
    Maximum_IsOverPay = '<New_Maximum_IsOverPay>';

---
UPDATE DISTRICT
SET maximum_Agent = '<New_Maximum_Agent>';

---
UPDATE DVT
SET SL_DVT = '<New_SL_DVT>';



DELETE FROM AGENT
WHERE Agent_Id = '[ID_VALUE]';

---
DELETE FROM AGENT_TYPE
WHERE AgentType_Id = '[ID_VALUE]';

---
DELETE FROM RECEPTION
WHERE Reception_Id = '[ID_VALUE]';

---
DELETE FROM RECEIPT
WHERE Receipt_Id = '[ID_VALUE]';

---
DELETE FROM IMPORT
WHERE ImportId = '[ID_VALUE]';

---
DELETE FROM ITEMS
WHERE Items_Id = '[ID_VALUE]';

---
DELETE FROM UNIT
WHERE Unit_Id = '[ID_VALUE]';

---
DELETE FROM IMPORT_DETAIL
WHERE Import_Detail_Id = '[ID_VALUE]';

---
DELETE FROM EXPORT
WHERE Export_Id = '[ID_VALUE]';

---
DELETE FROM EXPORT_DETAIL
WHERE ExportDetail_Id = '[ID_VALUE]';

---
DELETE FROM SALES_REPORT
WHERE SalesReport_Id = '[ID_VALUE]';

---
DELETE FROM SALES_REPORT_DETAIL
WHERE SalesReportDetail_Id = '[ID_VALUE]';

---
DELETE FROM DEBT_AGENT_REPORT
WHERE DebtAgentReport_Id = '[ID_VALUE]';

---
DELETE FROM DEBT_AGENT_REPORT_DETAIL
WHERE DebtAgentReportDetail_Id = '[ID_VALUE]';

---
DELETE FROM FIXED_VALUES;

---
DELETE FROM DISTRICT;

---
DELETE FROM DVT;

