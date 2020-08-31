-- ORDER OF THE TABLES
-- (1) HEADQUARTER
-- (2) INGREDIENT
-- (3) CHAIN
-- (4) USERS
-- (5) CONTAINER
-- (6) ORDERS
-- (7) ORDERDETAIL
-- (8) SALES
-- (9) MENU
-- (10) SALESDETAIL

DROP TABLE ORDERDETAIL; 
DROP TABLE ORDERS; 
DROP TABLE CONTAINER; 
DROP TABLE USERS; 
DROP TABLE INGREDIENT;
DROP TABLE SALESDETAIL;
DROP TABLE MENU;  
DROP TABLE SALES;  
DROP TABLE CHAIN; 
DROP TABLE HEADQUARTER; 

DROP SEQUENCE ingID_seq;
DROP SEQUENCE chainID_seq;
DROP SEQUENCE conID_seq;
DROP SEQUENCE orderID_seq;
DROP SEQUENCE orderDetailID_seq;
DROP SEQUENCE salesID_seq;
DROP SEQUENCE salDetailID_seq;
DROP SEQUENCE menuID_seq;


-- (1) HEADQUARTER TABLE --
CREATE TABLE HEADQUARTER(
  hqID VARCHAR2(30) PRIMARY KEY,
  hqName VARCHAR2(30) NOT NULL,
  hqPwd VARCHAR2(50) NOT NULL UNIQUE,
  hqEmail VARCHAR2(100) NOT NULL,
  hqPhone VARCHAR2(30) NOT NULL,
  hqRegDate VARCHAR2(30) NOT NULL,
  hqAddress VARCHAR2(100) NOT NULL,
  chainCount NUMBER DEFAULT 0
);

INSERT INTO HEADQUARTER VALUES('cafe_TOP_hq','ī��TOP����','1234','top2020@cafetop.com','010-1234-1234','20200404','����� ������ ���ﵿ',10);


-- (2) INGREDIENT TABLE --
CREATE TABLE INGREDIENT(
  ingID VARCHAR2(30) PRIMARY KEY,
  ingCategory VARCHAR2(30) NOT NULL,
  ingName VARCHAR2(30) NOT NULL,
  ingPrice NUMBER(30) NOT NULL,
  ingUnit VARCHAR2(30) NOT NULL,
  ingBrand VARCHAR2(30) NOT NULL,
  ingType VARCHAR2(30) NOT NULL,
  ingWeight NUMBER NOT NULL,
  ingRegDate VARCHAR2(30) NOT NULL,
  ingLink VARCHAR2(1000) NOT NULL,
  ingrLinkCount NUMBER DEFAULT 0 ,
  ingImgName VARCHAR2(30) NOT NULL
);  

CREATE SEQUENCE ingID_seq START WITH 1000000 INCREMENT BY 1;

INSERT INTO INGREDIENT VALUES('ingID_'||TO_CHAR(ingID_seq.NEXTVAL),'Ŀ��','Ŀ����',10000,'kg','��Ÿ����','whole','10','20200412080000','https://www.costco.co.kr/c/Starbucks-Breakfast-Whole-Bean-Coffee-113kg/p/90082',DEFAULT,'coffee_beans_starbucks.png');
INSERT INTO INGREDIENT VALUES('ingID_'||TO_CHAR(ingID_seq.NEXTVAL),'����','����',2000,'Liter','�������','single','1','20200412090000','https://homedirect.seoulmilk.co.kr:446/product/view.asp?idx=31',DEFAULT,'drink_milk_seoul.png');
INSERT INTO INGREDIENT VALUES('ingID_'||TO_CHAR(ingID_seq.NEXTVAL),'����','����',1000,'Liter','��ټ�','single','1','20200412091500','http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=2805832524&gclid=Cj0KCQjw-Mr0BRDyARIsAKEFbeesj21zCH1BxgoIQm-uSTpnUmmtkddWYPg3ISZh6drimBbJ2kNu-toaAp16EALw_wcB&utm_term=&utm_campaign=%B1%B8%B1%DB%BC%EE%C7%CEPC+%C3%DF%B0%A1%C0%DB%BE%F7&utm_source=%B1%B8%B1%DB_PC_S_%BC%EE%C7%CE&utm_medium=%B0%CB%BB%F6',DEFAULT,'drink_water_samdasu.png');
INSERT INTO INGREDIENT VALUES('ingID_'||TO_CHAR(ingID_seq.NEXTVAL),'����','����',3000,'Liter','��귣��','single','5','20200412092000','https://image.ytn.co.kr/general/jpg/2018/0803/201808031110474398_t.jpg',DEFAULT,'drink_ice_nobrand.png');
INSERT INTO INGREDIENT VALUES('ingID_'||TO_CHAR(ingID_seq.NEXTVAL),'����','ź���',1500,'Liter','Ʈ����','single','1','20200412092500','http://www.11st.co.kr/product/SellerProductDetail.tmall?method=getSellerProductDetail&prdNo=1562035786&gclid=Cj0KCQjw-Mr0BRDyARIsAKEFbeeTLZ_kNrJrhoDgtISWvBbErvRgrsZnHLf1fbemOBj0FvQktG1CELIaAgd3EALw_wcB&utm_term=&utm_campaign=%B1%B8%B1%DB%BC%EE%C7%CEPC+%C3%DF%B0%A1%C0%DB%BE%F7&utm_source=%B1%B8%B1%DB_PC_S_%BC%EE%C7%CE&utm_medium=%B0%CB%BB%F6',DEFAULT,'drink_soda_trevi.png');
INSERT INTO INGREDIENT VALUES('ingID_'||TO_CHAR(ingID_seq.NEXTVAL),'����','������',15000,'kg','������','bundle','5','20200412093000','https://www.coupang.com/vp/products/1273898941?itemId=2314026202&vendorItemId=4384369180&q=������&itemsCount=36&searchId=781c203716ba4c08846a8699dfe13620&rank=6&isAddedCart=',DEFAULT,'fruit_orange_delmonte.png');

-- (3) CHAIN TABLE --
CREATE TABLE CHAIN(
  chainID VARCHAR2(30) PRIMARY KEY,
  chainName VARCHAR2(30) NOT NULL,
  chainAddress VARCHAR2(100) NOT NULL,
  chainRegDate VARCHAR2(30) NOT NULL,
  hqID VARCHAR2(30) NOT NULL CONSTRAINT FK_CHAIN_hqID REFERENCES HEADQUARTER(hqID)
);

CREATE SEQUENCE chainID_seq START WITH 1000000 INCREMENT BY 1;

INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 1ȣ��)','����� ������ ���ﵿ','20200404','cafe_TOP_hq');
INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 2ȣ��)','����� ������ ���ﵿ','20200405','cafe_TOP_hq');
INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 3ȣ��)','����� ������ ���ﵿ','20200406','cafe_TOP_hq');
INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 4ȣ��)','����� ������ ���ﵿ','20200407','cafe_TOP_hq');
INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 5ȣ��)','����� ������ ���ﵿ','20200408','cafe_TOP_hq');
INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 6ȣ��)','����� ������ ���ﵿ','20200409','cafe_TOP_hq');
INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 7ȣ��)','����� ������ ���ﵿ','20200410','cafe_TOP_hq');
INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 8ȣ��)','����� ������ ���ﵿ','20200411','cafe_TOP_hq');
INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 9ȣ��)','����� ������ ���ﵿ','20200412','cafe_TOP_hq');
INSERT INTO CHAIN VALUES('chainID_'||chainID_seq.NEXTVAL,'ī�� TOP(���� 10ȣ��)','����� ������ ���ﵿ','20200413','cafe_TOP_hq');

-- (4) USERS TABLE --
CREATE TABLE USERS(
  userID VARCHAR2(30) PRIMARY KEY,
  userName VARCHAR2(30) NOT NULL,
  userPwd VARCHAR2(50) NOT NULL UNIQUE,
  userEmail VARCHAR2(100) NOT NULL,
  userPhone VARCHAR2(30) NOT NULL,
  userRegDate VARCHAR2(30) NOT NULL,
  chainID VARCHAR2(30) NOT NULL CONSTRAINT FK_USERS_chainID REFERENCES CHAIN(chainID)
);  

INSERT INTO USERS VALUES('cafe_top_chain_0001','user0001','pwd0001','top_0001@cafetop.com','010-1234-0001','20200404','chainID_1000000');
INSERT INTO USERS VALUES('cafe_top_chain_0002','user0002','pwd0002','top_0002@cafetop.com','010-1234-0002','20200405','chainID_1000001');
INSERT INTO USERS VALUES('cafe_top_chain_0003','user0003','pwd0003','top_0003@cafetop.com','010-1234-0003','20200406','chainID_1000002');
INSERT INTO USERS VALUES('cafe_top_chain_0004','user0004','pwd0004','top_0004@cafetop.com','010-1234-0004','20200407','chainID_1000003');
INSERT INTO USERS VALUES('cafe_top_chain_0005','user0005','pwd0005','top_0005@cafetop.com','010-1234-0005','20200408','chainID_1000004');
INSERT INTO USERS VALUES('cafe_top_chain_0006','user0006','pwd0006','top_0006@cafetop.com','010-1234-0006','20200409','chainID_1000005');
INSERT INTO USERS VALUES('cafe_top_chain_0007','user0007','pwd0007','top_0007@cafetop.com','010-1234-0007','20200410','chainID_1000006');
INSERT INTO USERS VALUES('cafe_top_chain_0008','user0008','pwd0008','top_0008@cafetop.com','010-1234-0008','20200411','chainID_1000007');
INSERT INTO USERS VALUES('cafe_top_chain_0009','user0009','pwd0009','top_0009@cafetop.com','010-1234-0009','20200412','chainID_1000008');
INSERT INTO USERS VALUES('cafe_top_chain_0010','user0010','pwd0010','top_0010@cafetop.com','010-1234-0010','20200413','chainID_1000009');


-- (5) CONTAINER TABLE --
CREATE TABLE CONTAINER(
  conID VARCHAR2(30) PRIMARY KEY,
  conSize VARCHAR2(30) NOT NULL,
  conMaxWeight NUMBER NOT NULL,
  conFullWeight NUMBER NOT NULL,
  conFullQuantity NUMBER NOT NULL,
  conWarningWeight NUMBER NOT NULL,
  conWarningQuantity NUMBER NOT NULL,
  conCurrWeight NUMBER NOT NULL,
  conCurrQuantity NUMBER NOT NULL,
  conUnitWeight NUMBER NOT NULL,
  ingID VARCHAR2(30) NOT NULL CONSTRAINT FK_CONTAINER_ingID REFERENCES INGREDIENT(ingID),
  chainID VARCHAR2(30) NOT NULL CONSTRAINT FK_CONTAINER_chainID REFERENCES CHAIN(chainID)
); 

CREATE SEQUENCE conID_seq START WITH 1000000 INCREMENT BY 1;


-- (6) ORDERS TABLE --
CREATE TABLE ORDERS(
  orderID VARCHAR(30) PRIMARY KEY,
  payment NUMBER NOT NULL,
  deliveryDate VARCHAR2(30) NOT NULL,
  deliveryState VARCHAR2(30) NOT NULL,
  orderPerson VARCHAR2(30) NOT NULL,
  orderState VARCHAR2(30) NOT NULL,
  hqID VARCHAR2(30) NOT NULL CONSTRAINT FK_ORDERS_hqID REFERENCES HEADQUARTER(hqID),
  chainID VARCHAR2(30) NOT NULL CONSTRAINT FK_ORDERS_chainID REFERENCES CHAIN(chainID),
  userID VARCHAR2(30) NOT NULL CONSTRAINT FK_ORDERS_userID REFERENCES USERS(userID)
);

CREATE SEQUENCE orderID_seq START WITH 1000000 INCREMENT BY 1;


-- (7) ORDERDETAIL TABLE --
CREATE TABLE ORDERDETAIL(
  orderDetailID VARCHAR2(30) PRIMARY KEY,
  totWeight NUMBER NOT NULL,
  ingQuantity NUMBER NOT NULL,
  ingID VARCHAR2(30) NOT NULL CONSTRAINT FK_ORDERDETAIL_ingID REFERENCES INGREDIENT(ingID),
  orderID VARCHAR2(30) NOT NULL CONSTRAINT FK_ORDERDETAIL_orderID REFERENCES ORDERS(orderID)
);

CREATE SEQUENCE orderDetailID_seq START WITH 1000000 INCREMENT BY 1;


-- (8) SALES TABLE --
CREATE TABLE SALES(
  salesID VARCHAR2(30) PRIMARY KEY,
  salesRegDate VARCHAR2(30) NOT NULL,
  totSales NUMBER NOT NULL,
  chainID VARCHAR2(30) NOT NULL CONSTRAINT FK_SALES_chainID REFERENCES CHAIN(chainID)
);

CREATE SEQUENCE salesID_seq START WITH 1000000 INCREMENT BY 1;


-- (9) MENU TABLE --
CREATE TABLE MENU(
  menuID VARCHAR2(30) PRIMARY KEY,
  menuName VARCHAR2(30) NOT NULL,
  menuPrice NUMBER NOT NULL
);

CREATE SEQUENCE menuID_seq START WITH 1000000 INCREMENT BY 1;

INSERT INTO MENU VALUES('menuID_'||TO_CHAR(menuID_seq.NEXTVAL),'�Ƹ޸�ī��',4000);
INSERT INTO MENU VALUES('menuID_'||TO_CHAR(menuID_seq.NEXTVAL),'���ִ� ������',12000);
INSERT INTO MENU VALUES('menuID_'||TO_CHAR(menuID_seq.NEXTVAL),'���ũ ġ������ũ',7000);
INSERT INTO MENU VALUES('menuID_'||TO_CHAR(menuID_seq.NEXTVAL),'�� & ������ ������ġ',5000);
INSERT INTO MENU VALUES('menuID_'||TO_CHAR(menuID_seq.NEXTVAL),'���̽�ī���',6000);
INSERT INTO MENU VALUES('menuID_'||TO_CHAR(menuID_seq.NEXTVAL),'�������ֽ�',4000);


-- (10) SALESDETAIL TABLE --
CREATE TABLE SALESDETAIL(
  salDetailID VARCHAR2(30) PRIMARY KEY,
  salDetailRegDate VARCHAR2(30) NOT NULL,
  salesID VARCHAR2(30) NOT NULL CONSTRAINT FK_SALESDETAIL_salesID REFERENCES SALES(salesID),
  menuID VARCHAR2(30) NOT NULL CONSTRAINT FK_SALESDETAIL_menuID REFERENCES MENU(menuID)
);

CREATE SEQUENCE salDetailID_seq START WITH 1000000 INCREMENT BY 1;



