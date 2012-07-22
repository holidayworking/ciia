DROP TABLE IF EXISTS user_item_tag;
DROP TABLE IF EXISTS item_tag;
DROP TABLE IF EXISTS tag_summary;
DROP TABLE IF EXISTS user;
DROP TABLE IF EXISTS item;
DROP TABLE IF EXISTS tags;
DROP TABLE IF EXISTS tagging_source;
DROP TABLE IF EXISTS days;

CREATE TABLE user (
    user_id int unsigned PRIMARY KEY NOT NULL auto_increment,
    name varchar(50)
) ENGINE=InnoDB;

CREATE TABLE item (
    item_id int unsigned PRIMARY KEY NOT NULL auto_increment,
    item_type_id int unsigned not null,
    name varchar(50)
 ) ENGINE=InnoDB;

CREATE TABLE tags (
    tag_id int unsigned PRIMARY KEY NOT NULL auto_increment,
    tag_text varchar(50) NOT NULL,
    stemmed_text varchar(50) default null,
    UNIQUE INDEX uidx_tag_text (tag_text)
) ENGINE=InnoDB;

CREATE TABLE user_item_tag (
    user_id int unsigned not null,
    item_id int unsigned not null,
    tag_id int unsigned not null,
    create_date timestamp NULL default CURRENT_TIMESTAMP,
    PRIMARY KEY (user_id, item_id, tag_id),
    FOREIGN KEY (user_id) REFERENCES user (user_id),
    FOREIGN KEY (item_id) REFERENCES item (item_id),
    FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
) ENGINE=InnoDB;

CREATE TABLE tagging_source (
    source_id int unsigned PRIMARY KEY NOT NULL auto_increment,
    source_name varchar(50) NOT NULL,
    UNIQUE INDEX uidx_tagging_source (source_name)
 ) ENGINE=InnoDB;

CREATE TABLE item_tag (
    source_id int unsigned not null,
    item_id int unsigned not null,
    tag_id int unsigned not null,
    weight double not null,
    create_date timestamp NULL default CURRENT_TIMESTAMP,
    PRIMARY KEY (source_id, item_id, tag_id),
    FOREIGN KEY (source_id) REFERENCES tagging_source (source_id),
    FOREIGN KEY (item_id) REFERENCES item (item_id),
    FOREIGN KEY (tag_id) REFERENCES tags (tag_id)
) ENGINE=InnoDB;

CREATE TABLE days (
    day_id int unsigned PRIMARY KEY NOT NULL auto_increment,
    day timestamp NOT NULL,
    UNIQUE INDEX uidx_days (day)
) ENGINE=InnoDB;

CREATE TABLE tag_summary (
    tag_id int unsigned NOT NULL,
    day_id int unsigned NOT NULL,
    number int unsigned NOT NULL,
    PRIMARY KEY (tag_id, day_id),
    FOREIGN KEY (tag_id) REFERENCES tags (tag_id),
    FOREIGN KEY (day_id) REFERENCES days (day_id)
) ENGINE=InnoDB;


delete from user where user_id in (1, 2);
insert into user(user_id, name) values (1, "John");
insert into user(user_id, name) values (2,"Jane");

delete from item where item_id in (1, 2, 3);
insert into item(item_id, name) values (1, "item1");
insert into item(item_id, name)values (2, "item2");
insert into item(item_id, name)values (3, "item3");

delete from tags where tag_id in (1, 2, 3, 4, 5, 6);
insert into tags(tag_id, tag_text) values (1, "tagging");
insert into tags(tag_id, tag_text) values (2, "schema");
insert into tags(tag_id, tag_text) values (3, "denormalized");
insert into tags(tag_id, tag_text) values (4, "database");
insert into tags(tag_id, tag_text) values (5, "binary");
insert into tags(tag_id, tag_text) values (6, "normalized");

delete from user_item_tag where user_id in (1, 2, 3);
insert into user_item_tag(user_id, item_id, tag_id) values(1, 1, 1);
insert into user_item_tag(user_id, item_id, tag_id) values(1, 1, 2);
insert into user_item_tag(user_id, item_id, tag_id) values(1, 1, 3);
insert into user_item_tag(user_id, item_id, tag_id) values(1, 2, 4);
insert into user_item_tag(user_id, item_id, tag_id) values(1, 2, 5);
insert into user_item_tag(user_id, item_id, tag_id) values(1, 2, 2);
insert into user_item_tag(user_id, item_id, tag_id) values(2, 3, 6);
insert into user_item_tag(user_id, item_id, tag_id) values(2, 3, 4);
insert into user_item_tag(user_id, item_id, tag_id) values(2, 3, 2);
