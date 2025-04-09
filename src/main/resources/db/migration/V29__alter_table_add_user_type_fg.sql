
ALTER TABLE users ADD CONSTRAINT user_fg  FOREIGN KEY (type) REFERENCES user_types(type_id) on delete  cascade ;
