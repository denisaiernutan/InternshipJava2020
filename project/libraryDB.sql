CREATE DATABASE libraryDB;


use libraryDB;

create table employees( 
	employee_id int unique not null auto_increment,
    employee_name varchar(70) not null,
    employee_pass varchar(40) not null,
    employee_email varchar(50) not null,
    employee_role varchar(15) not null,
    primary key(employee_id)
    );
    
alter table employees add unique (employee_email);
    
    
create table books(
	book_id int unique not null auto_increment,
    book_title varchar(70) not null,
    book_author varchar(70) not null,
    book_description varchar(100) not null,
    book_added_date  datetime not null,
    primary key (book_id)
    );
    
    
create table tags(
	tag_id int unique not null auto_increment,
    tag_description varchar(20) not null,
    primary key (tag_id)
    );
    
create table book_tag(
	book_id int not null,
    tag_id int not null, 
    primary key (book_id, tag_id),
    foreign key (book_id) references books(book_id) on delete cascade,
    foreign key (tag_id) references tags(tag_id) on delete cascade 
    );
    
create table copies(
	copy_id int unique not null auto_increment,
    book_id int not null,
    copy_flag boolean not null,
    copy_status varchar(20) not null,
    primary key (copy_id),
    foreign key (book_id) references books(book_id) on delete cascade
    );
    
create table book_rents(
	book_rent_id int unique not null auto_increment,
    copy_id int not null, 
    book_id int not null, 
    rental_date datetime not null,
    return_date datetime,
    book_rent_status varchar(20) not null,
    grade double not null, 
    primary key (book_rent_id),
    foreign key (copy_id) references copies(copy_id) on delete cascade,
    foreign key (book_id) references books(book_id) on delete cascade
    );
    
create table rent_requests(
    rent_req_id int unique not null auto_increment,
	employee_id int not null,
    book_id int not null,
    request_date datetime not null,
    rent_req_status varchar(20) not null,
    primary key (rent_req_id) ,
    foreign key(employee_id) references employees(employee_id) on delete cascade,
	foreign key (book_id) references books(book_id) on delete cascade
    );
    
create table book_requests(
	book_req_id int unique not null auto_increment,
    employee_id int not null,
    book_title varchar(70) not null,
    book_author varchar(70) not null,
    publishing_company varchar(70) not null,
    online_library varchar(70),
    no_copies int not null,
    total_cost double not null,
    book_req_status varchar(20),
    primary key(book_req_id),
     foreign key(employee_id) references employees(employee_id) on delete cascade
     );
    
    
insert into employees(employee_name,employee_pass,employee_email, employee_role) values("employee1",md5('pass'),"string","user");

select employee_pass from employees where employee_email="string@yahoo.com";

delete from employees where employee_email="string@yahoo.com";

