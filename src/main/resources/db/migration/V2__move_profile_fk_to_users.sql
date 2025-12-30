ALTER TABLE profiles DROP FOREIGN KEY profiles_ibfk_1;
ALTER TABLE profiles DROP INDEX user_id;
ALTER TABLE profiles DROP COLUMN user_id;

ALTER TABLE users
    ADD COLUMN profile_id BIGINT UNIQUE;

ALTER TABLE users
    ADD CONSTRAINT fk_user_profile
        FOREIGN KEY (profile_id) REFERENCES profiles (id)
            ON DELETE CASCADE;