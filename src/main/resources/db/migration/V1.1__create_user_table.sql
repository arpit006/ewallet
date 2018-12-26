CREATE TABLE user_details(
uuid                CHARACTER VARYING (60) PRIMARY KEY,
name                CHARACTER VARYING (60),
user_name           CHARACTER VARYING (60),
email               CHARACTER VARYING (100),
mobile_no           CHARACTER VARYING (10),
wallet_balance      NUMERIC (12) DEFAULT 0.00,
password            CHARACTER VARYING(100));
