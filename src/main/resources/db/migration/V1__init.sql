CREATE TABLE households (
                            id UUID PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                            created_by UUID NOT NULL
);

CREATE TABLE users (
                       id UUID PRIMARY KEY,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       firstName VARCHAR(255) NOT NULL,
                       lastName VARCHAR(255) NOT NULL,
                       password_hash VARCHAR(255) NOT NULL,
                       created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE household_members (
                                   id UUID PRIMARY KEY,
                                   household_id UUID NOT NULL REFERENCES households(id) ON DELETE CASCADE,
                                   user_id UUID NOT NULL UNIQUE REFERENCES users(id) ON DELETE CASCADE,
                                   role VARCHAR(50) NOT NULL,
                                   joined_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE household_invites (
                                   id UUID PRIMARY KEY,
                                   household_id UUID NOT NULL REFERENCES households(id) ON DELETE CASCADE,
                                   code VARCHAR(6) NOT NULL UNIQUE,
                                   expires_at TIMESTAMP WITH TIME ZONE NOT NULL,
                                   created_by UUID NOT NULL,
                                   active BOOLEAN NOT NULL,
                                   created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE inventory_items (
                                 id UUID PRIMARY KEY,
                                 household_id UUID NOT NULL,
                                 name VARCHAR(255) NOT NULL,
                                 brand VARCHAR(255),
                                 quantity INTEGER NOT NULL,
                                 package_unit VARCHAR(50) NOT NULL,
                                 size_per_unit DOUBLE PRECISION,
                                 size_unit VARCHAR(50),
                                 category VARCHAR(100),
                                 storage_location VARCHAR(100),
                                 note TEXT,
                                 created_by UUID NOT NULL,
                                 created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                                 updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE shopping_list_items (
                                     id UUID PRIMARY KEY,
                                     household_id UUID NOT NULL REFERENCES households(id) ON DELETE CASCADE,
                                     name VARCHAR(255) NOT NULL,
                                     brand VARCHAR(255),
                                     quantity NUMERIC(12, 2) NOT NULL,
                                     package_unit VARCHAR(50) NOT NULL,
                                     size_per_unit NUMERIC(12, 2),
                                     size_unit VARCHAR(50),
                                     category VARCHAR(100),
                                     note TEXT,
                                     status VARCHAR(50) NOT NULL,
                                     created_by UUID NOT NULL,
                                     created_at TIMESTAMP WITH TIME ZONE NOT NULL,
                                     updated_at TIMESTAMP WITH TIME ZONE NOT NULL
);