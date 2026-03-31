--liquibase formatted sql

--changeset tpe:1
insert into category_entity (id,name) values(1,"Bonnet");
insert into category_entity (id,name) values(2,"Gant");
insert into category_entity (id,name) values(3,"Echarpe");

insert into product_entity (id,name,price,quantity,category_id,url,description) values (1,'Bonnet rouge',25,8,1,'bonnet_rouge_1.jpg','Un beau bonnet rouge');
insert into product_entity (id,name,price,quantity,category_id,url,description) values (2,'Bonnet orange',36,9,1,'bonnet_orange_1.jpg', 'Un beau bonnet orange');
insert into product_entity (id,name,price,quantity,category_id,url,description) values (3,'Bonnet mauve',38,9,1,'bonnet_mauve_1.jpg','Un beau bonnet mauve' );
insert into product_entity (id,name,price,quantity,category_id,url,description) values (4,'Gant noir',14,10,2,'gant_noir_1.png', 'Un beau gant noir');
insert into product_entity (id,name,price,quantity,category_id,url,description) values (5,'Echarpe rouge',78,12,3, 'echarpe_rouge_1.png','Un belle echarpe rouge');

insert into address_entity (id,street,city,number,country) values (1,'Avenue Ponty','Mons','15','BELGIUM');
insert into address_entity (id,street,city,number,country) values (2,'Rue Boucher','Bruxelles','8','BELGIUM');
insert into address_entity (id,street,city,number,country) values (3,'Rue Merlot','Nice','2','FRANCE');
insert into address_entity (id,street,city,number,country) values (4,'Rue Poincon','Paris','26','FRANCE');
insert into address_entity (id,street,city,number,country) values (5,'Rue Seige-gang','Zimbaboue','6','CROATIA');

insert into supplier_entity (id,address_id,product_id,registration_number) values(1,3,1,'0123456789');
insert into supplier_entity (id,address_id,product_id,registration_number) values(2,4,2,'123456');
insert into supplier_entity (id,address_id,product_id,registration_number) values(3,5,3,'123457');
insert into supplier_entity (id,address_id,product_id,registration_number) values(4,5,4,'123457');

--changeset tpe:2
insert into client_entity(id,address_id,is_active,created_at,updated_at,email,password,username)values(1,1,true,now(),now(),'thomas@hotmail.com','$2a$10$VCtb0afUP.nWv2YOGjJ0Sedci7HKV/trVZDDIGXNUFa33/UyhKILa','Thomas');
insert into role_entity (id,name) values(1,'ROLE_ADMIN');
insert into client_role_entity (role_id,client_id) values(1,1);

--changeset tpe:3
insert into client_entity(id,address_id,is_active,created_at,updated_at,email,password,username)values(2,2,true,now(),now(),'james@hotmail.com','$2a$10$VCtb0afUP.nWv2YOGjJ0Sedci7HKV/trVZDDIGXNUFa33/UyhKILa','James');
insert into role_entity (id,name) values(2,'ROLE_USER');
insert into client_role_entity (role_id,client_id) values(2,2);

--changeset tpe:4
insert into comment_entity (id,header,body,created_at,updated_at,client_id) values(1,'Congratulation','Hello there',now(),now(),1);
insert into comment_entity (id,header,body,created_at,updated_at,client_id) values(2,'Bad Luck','Good by',now(),now(),1);

--changeset tpe:5
insert into order_entity (id,client_id,create_at,is_done) values (1,1,now(),true);
insert into order_entity (id,client_id,create_at,is_done) values (2,2,now(),true);
insert into order_entity (id,client_id,create_at,is_done) values (3,1,now(),false);

--changeset tpe:6
insert into order_product_entity (id,order_id,product_id,quantity_product)values(1,1,1,3);
insert into order_product_entity (id,order_id,product_id,quantity_product) values(2,1,3,2);
insert into order_product_entity (id,order_id,product_id,quantity_product) values(3,2,1,1);
insert into order_product_entity (id,order_id,product_id,quantity_product) values(4,3,1,1);
insert into order_product_entity (id,order_id,product_id,quantity_product) values(5,3,2,1);

--changeset tpe:7
insert into image_entity (id,product_id,path) values(1,1,'bonnet_rouge_2.jpg');
insert into image_entity (id,product_id,path) values(2,1,'bonnet_rouge_3.jpg');
insert into image_entity (id,product_id,path) values(3,1,'bonnet_rouge_4.jpg');
insert into image_entity (id,product_id,path) values(4,2,'bonnet_orange_2.jpg');
insert into image_entity (id,product_id,path) values(5,2,'bonnet_orange_3.jpg');
insert into image_entity (id,product_id,path) values(6,3,'bonnet_mauve_2.jpg');
insert into image_entity (id,product_id,path) values(7,3,'bonnet_mauve_3.jpg');
insert into image_entity (id,product_id,path) values(8,4,'gant_noir_2.png');
insert into image_entity (id,product_id,path) values(9,5,'echarpe_rouge_2.png');

--changeset tpe:8
insert into label_entity (id,product_id,title_fr,title_en,title_ru,description_fr,description_en,description_ru) values (id,1,'Bonnet rouge','Red hat','красная шляп','Un beau bonnet rouge','A beautifully red hat', 'Красивая красная шляпа');
insert into label_entity (id,product_id,title_fr,title_en,title_ru,description_fr,description_en,description_ru) values (id,2,'Bonnet orange','Orange hat','оранжевая шляпа','Un beau bonnet orange','A beautifully orange hat', 'Красивая оранжевая шляпа');
insert into label_entity (id,product_id,title_fr,title_en,title_ru,description_fr,description_en,description_ru) values (id,3,'Bonnet mauve','Mauve hat','сиреневого цвета','Un beau bonnet mauve','A beautifully mauve hat', 'Красивая шляпа сиреневого цвета');
insert into label_entity (id,product_id,title_fr,title_en,title_ru,description_fr,description_en,description_ru) values (id,4,'Gant noir','Black glove','Черная перчатка','Des beau gants noir','A beautifull Black glove', 'Красивая Черная перчатка');
insert into label_entity (id,product_id,title_fr,title_en,title_ru,description_fr,description_en,description_ru) values (id,5,'Écharpe rouge','Red scarf','Красный шарф','Une belle écharpe rouge','A beautifull red scarf', 'Красивая Красный шарф');