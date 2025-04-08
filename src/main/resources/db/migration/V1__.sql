ALTER TABLE report_meals
    DROP CONSTRAINT fk64p7elgykm9u6cpwj26uk2h75;

ALTER TABLE report_meals
    DROP CONSTRAINT fkoyeajo6sukaqaja5v7nagq25p;

ALTER TABLE dishes
    ADD CONSTRAINT pk_dishes PRIMARY KEY (id);

ALTER TABLE meal_dish
    ADD CONSTRAINT pk_meal_dish PRIMARY KEY (dish_id, meal_id);

ALTER TABLE users
    ADD CONSTRAINT pk_users PRIMARY KEY (id);

ALTER TABLE meal
    ADD CONSTRAINT FK_MEAL_ON_USER FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE meal_dish
    ADD CONSTRAINT fk_meal_dish_on_dish FOREIGN KEY (dish_id) REFERENCES dishes (id);

DROP TABLE report CASCADE;

DROP TABLE report_meals CASCADE;

ALTER TABLE meal
    ALTER COLUMN user_id DROP NOT NULL;