CREATE TABLE Profile (
  Name varchar(50),
  Adress varchar(150),
  Shoe_Size smallint,
  PRIMARY KEY (Name));

CREATE TABLE Follow(
  Profile_Name1 varchar(50), 
  Profile_Name2 varchar(50),
  PRIMARY KEY (Profile_Name1, Profile_Name2),
  FOREIGN KEY (Profile_Name1) REFERENCES Profile(Name) ON DELETE CASCADE,
  FOREIGN KEY (Profile_Name2) REFERENCES Profile(Name) ON DELETE CASCADE);


CREATE TABLE Item(
  Name varchar(50) DEFAULT 'New Lot',
  Description varchar (100),
  Price smallint NOT NULL, 
  ItemID int GENERATED BY DEFAULT AS IDENTITY
    (START WITH 1 INCREMENT BY 1), 
  Seller_Name varchar(50),
  PRIMARY KEY (ItemID),
  FOREIGN KEY (Seller_Name) REFERENCES Profile(Name) ON DELETE CASCADE,
  CHECK (Price>0));


CREATE TABLE Paying_Account(
  Name varchar(50), 
  Profile_Name varchar(50), 
  Card_Number int, 
  Card_Name varchar(50), 
  Card_Code smallint,
  PRIMARY KEY (Card_Number),
  FOREIGN KEY (Profile_Name) REFERENCES Profile(Name));

CREATE TABLE Buy_Item(
  ItemID int,
  Buyer_Name varchar(50), 
  Card_Number int , 
  PRIMARY KEY (ItemID, Buyer_Name, Card_Number),
  FOREIGN KEY (ItemID) REFERENCES Item ON DELETE CASCADE,
  FOREIGN KEY (Buyer_Name) REFERENCES Profile(Name) ON DELETE CASCADE,
  FOREIGN KEY (Card_Number) REFERENCES Paying_Account ON DELETE CASCADE);

CREATE TABLE Commentary(
  Writer_Profile_Name varchar(50), 
  CommentID int, 
  ItemID int, 
  Content varchar(200),
  PRIMARY KEY (CommentID, ItemID),
  FOREIGN KEY (ItemID) REFERENCES Item ON DELETE CASCADE);
  
CREATE SEQUENCE seq_1
START WITH 1
INCREMENT BY 1;

CREATE OR REPLACE TRIGGER trg_1
BEFORE INSERT ON Commentary
FOR EACH ROW
BEGIN
:new.CommentID:=seq_1.nextval;
END;
/

CREATE TABLE Add_Comment(
  Profile_Name varchar(50), 
  CommentID int,
  ItemID int,
  PRIMARY KEY(Profile_Name, CommentID, ItemID),
  FOREIGN KEY (Profile_Name) REFERENCES Profile(Name) ON DELETE CASCADE,
  FOREIGN KEY (CommentID, ItemID) REFERENCES Commentary ON DELETE CASCADE);


CREATE TABLE Moderator(
  ModId int GENERATED BY DEFAULT AS IDENTITY, 
  Points smallint, 
  Priority smallint, 
  Profile_Name varchar(50),
  PRIMARY KEY (ModId),
  FOREIGN KEY (Profile_Name) REFERENCES Profile(Name) ON DELETE CASCADE);

CREATE TABLE Moderate(
  ModId int, 
  ItemID int, 
  PRIMARY KEY (ItemID),
  FOREIGN KEY (ModId) REFERENCES Moderator ON DELETE CASCADE,
  FOREIGN KEY (ItemID) REFERENCES Item ON DELETE CASCADE);
  
-- profiles that have more than 2 items on sale
CREATE OR REPLACE VIEW v1 AS
SELECT DISTINCT Seller_Name, COUNT(ItemID) AS Counted
FROM Item
GROUP BY Seller_Name
HAVING COUNT(ItemID) > 2;

CREATE OR REPLACE VIEW v2 AS
SELECT Moderator.ModID, Moderator.Profile_Name FROM Moderator
  JOIN Moderate
  ON Moderator.ModId = Moderate.ModId;
  
--INSERT INTO Moderator (Points, Priority, Profile_Name) VALUES (10, 2, 'morga13');
--INSERT INTO Moderate (ModId, ItemID) VALUES (1, 2,);
  
CREATE OR REPLACE PROCEDURE p_delete_item(
   p_item_id  IN  item.ItemID%TYPE,
   p_error_code OUT NUMBER
)
AS
  BEGIN
    DELETE
    FROM item
    WHERE  p_item_id = item.ItemID;
 
    p_error_code := SQL%ROWCOUNT;
    IF (p_error_code = 1)
    THEN
      COMMIT;
    ELSE
      ROLLBACK;
    END IF;
    EXCEPTION
    WHEN OTHERS
    THEN
      p_error_code := SQLCODE;
  END p_delete_item;
/


commit;
