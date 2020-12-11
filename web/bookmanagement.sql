DROP DATABASE IF EXISTS bookmanagement;
create database bookmanagement DEFAULT CHARACTER SET utf8;

use bookmanagement;

CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
	password varchar(255) default null,
  stud varchar(255) DEFAULT NULL,
	dept varchar(255) default null, -- 系名
	grade varchar(40) default null, -- 年级
	gender varchar(4) NOT NULL, -- 性别
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE book (
  id int(11) NOT NULL AUTO_INCREMENT,
	bookid varchar(255) not null,  -- 书号
	name varchar(255) not null,  -- 书名
	press varchar(255) default null, -- 出版社
	publishdate datetime DEFAULT NULL, -- 出版日期
	author varchar(255) not null, -- 作者
	content varchar(255) default null, -- 内容摘要
	stock int(11) default 0, -- 数量
	primary key(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE borrow (
	id int(11) not null auto_increment,
	uid int(11) not null, -- 用户外键
	bid int(11) not null, -- 书籍外键
	btime datetime not null, -- 借书时间
	bdays int(11) default 0, -- 借书天数
	foreign key (uid) references user (id),
  foreign key (bid) references book (id),
	primary key(id)
) DEFAULT CHARSET=utf8;

