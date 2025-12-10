-- 新建一个名为bank的数据库
create database bank;

-- 切换到bank数据库
use bank;

-- 在bank中新建一个名为users的表
create table users(
    bank_ID int(10) unsigned zerofill auto_increment,
    name varchar(10) not null default 'name',
    password varchar(10) not null default 'password',
    identify_ID varchar(12) not null default 'identyid' unique,
    tel varchar(11) default 'tel' not null,
    gender varchar(1) not null default 'F',
    birth date not null default '2022-12-15',
    money double not null default 0,
    primary key(bank_ID)
    );

alter table users AUTO_INCREMENT=1000000000;

insert into users(bank_ID,name,password) values(null,'manager','feimabank');
insert into users values(null,'小红','AAA01','111111111111','12345678901','F','2002-12-15',80.0);
insert into users values(null,'小明','BBB02','222222222222','12345678901','M','2000-12-15',70.0);
insert into users values(null,'钮祜禄·甄嬛','CCC03','333333333333','12345678901','F','1980-12-15',10000.0);
insert into users values(null,'乾隆帝爱新觉罗·弘历','abc00','567890123456','12345678901','M','2000-12-15',1000.0);

