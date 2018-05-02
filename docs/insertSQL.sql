insert into category(no, name, type)
    values (null, "소설", "BOOK");
insert into category (no, name, type)
values (null, "수필", "BOOK");
insert into category(no, name, type)
values (null, "논문", "BOOK");
insert into category(no, name, type)
values (null, "컴퓨터/IT", "BOOK");
insert into category(no, name, type)
values (null, "과학", "BOOK");
insert into category(no, name, type)
values (null, "시", "BOOK");

insert into category(no, name, type)
values (null, "가요", "CD");
insert into category(no, name, type)
values (null, "팝", "CD");
insert into category(no, name, type)
values (null, "락", "CD");
insert into category(no, name, type)
values (null, "인디", "CD");

insert into category(no, name, type)
    values(null, "SF", "DVD");
insert into category(no, name, type)
values(null, "스릴러", "DVD");
insert into category(no, name, type)
values(null, "멜로", "DVD");
insert into category(no, name, type)
values(null, "액션", "DVD");
insert into category(no, name, type)
values(null, "공포", "DVD");

insert into item(no, title, type, category_no)
    values (null, "해리 포터", "B", 1);
insert into item(no, title, type, category_no)
values (null, "신정민의 일기", "B", 2);
insert into item(no, title, type, category_no)
values (null, "스프링 잘하는 방법 연구", "B", 3);
insert into item(no, title, type, category_no)
values (null, "스프링 배우기", "B", 4);
insert into item(no, title, type, category_no)
values (null, "물리학", "B", 5);
insert into item(no, title, type, category_no)
values (null, "한용운 시집", "B", 6);
insert into book(no, isbn)
    values (1, "0123456789-0000001");
insert into book(no, isbn)
values (2, "0123456789-0000002");
insert into book(no, isbn)
values (3, "0123456789-0000003");
insert into book(no, isbn)
values (4, "0123456789-0000004");
insert into book(no, isbn)
values (5, "0123456789-0000005");
insert into book(no, isbn)
values (6, "0123456789-0000006");

insert into item(no, title, type, category_no)
    values (null , "아이유 1집 앨범", "C", 7);
insert into item(no, title, type, category_no)
values (null , "Adam Levine 1st Album", "C", 8);
insert into item(no, title, type, category_no)
values (null , "로맨틱 펀치 1집 앨범", "C", 9);
insert into item(no, title, type, category_no)
values (null , "어쿠스틱 콜라보 1집 앨범", "C", 10);
insert into cd(no, artist)
    values (7, "아이유");
insert into cd(no, artist)
values (8, "Adam Levine");
insert into cd(no, artist)
values (9, "로맨틱 펀치");
insert into cd(no, artist)
values (10, "어쿠스틱 콜라보");

insert into item(no, title, type, category_no)
    values (null, "인터스텔라", "D", 11);
insert into item(no, title, type, category_no)
values (null, "추격자", "D", 12);
insert into item(no, title, type, category_no)
values (null, "러브 액츄얼리", "D", 13);
insert into item(no, title, type, category_no)
values (null, "엽문", "D", 14);
insert into item(no, title, type, category_no)
values (null, "파라노말 액티비티", "D", 15);
insert into dvd(no, manufacturer)
    values (11, "CJ E&M");
insert into dvd(no, manufacturer)
values (12, "쇼박스");
insert into dvd(no, manufacturer)
values (13, "넥스트");
insert into dvd(no, manufacturer)
values (14, "롯데");
insert into dvd(no, manufacturer)
values (15, "CJ E&M");