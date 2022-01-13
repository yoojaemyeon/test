create table member(
	id varchar2(10),
	password varchar2(10) not null,
	name varchar2(50) not null,
	register_number varchar2(13) not null,
	mileage number,
	constraint member_pk primary key(id)
)

select * from member;

select * from member where id=1111 and password=1111

delete from member;