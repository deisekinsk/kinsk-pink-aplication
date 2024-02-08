-- Insert users
INSERT INTO PINKUSER (NAME, PASS, STARTER, LAST_UPDATE)
VALUES
    ('Luke Skywalker', 'password', NOW(), NOW()),
    ('Princess Leia', 'password', NOW(), NOW()),
    ('Han Solo', 'password', NOW(), NOW()),
    ('Darth Vader', 'password', NOW(), NOW()),
    ('Yoda', 'password', NOW(), NOW());

-- Insert products
INSERT INTO PRODUCT (NAME) VALUES ('PINK'), ('PURPLE'), ('VIOLET');

-- Insert pricing categories
INSERT INTO PRICING_CATEGORY (NAME, FIXED_RATE, DISCOUNT, USAGE_RATE)
VALUES
    ('Basic', 10.0, 0.0, 0.0),
    ('Premium', 20.0, 5.0, 0.0),
    ('Gold', 30.0, 10.0, 0.0),
    ('Silver', 15.0, 3.0, 0.0),
    ('Bronze', 8.0, 1.0, 0.0),
    ('Platinum', 50.0, 15.0, 0.0);

-- Insert subscription
INSERT INTO SUBSCRIPTION (START_DATE, END_DATE, LAST_UPDATE, SUBS_STATUS, PRICING_CATEGORY_ID, PRODUCT_ID, USER_ID)
VALUES
    (NOW(), NULL, NOW(), 'ACTIVE', 1, 1, 1),
    (NOW(), NULL, NOW(), 'ACTIVE', 1, 1, 1),
    (NOW(), NOW(), NOW(), 'CANCEL', 2, 2, 2),
    (NOW(), NULL, NOW(), 'PENDING', 3, 1, 3),
    (NOW(), NOW(), NOW(), 'CANCEL', 2, 3, 4),
    (NOW(), NULL, NOW(), 'ACTIVE', 1, 2, 5);
