CREATE TABLE organizations (
                               organizationId SERIAL PRIMARY KEY,
                               organizationName VARCHAR(50) NOT NULL,
                               organizationOwner VARCHAR(50) NOT NULL
);

CREATE TABLE users (
                       userId SERIAL PRIMARY KEY,
                       userName VARCHAR(50) NOT NULL,
                       firstName VARCHAR(50) NOT NULL,
                       lastName VARCHAR(50) NOT NULL,
                       phoneNumber VARCHAR(50) NOT NULL,
                       password VARCHAR(16) NOT NULL,
                       position VARCHAR(16) NOT NULL,
                       role VARCHAR(16) NOT NULL,
                       organizationId INTEGER,
                       CONSTRAINT fk_organization
                           FOREIGN KEY(organizationId)
                               REFERENCES organizations(organizationId)
);

CREATE TABLE games (
                       gameId SERIAL PRIMARY KEY,
                       homeOrganizationId INTEGER NOT NULL,
                       awayOrganizationId INTEGER NOT NULL,
                       homeScore INTEGER,
                       awayScore INTEGER,
                       squad VARCHAR(200),
                       CONSTRAINT fk_home_organization
                           FOREIGN KEY(homeOrganizationId)
                               REFERENCES organizations(organizationId),
                       CONSTRAINT fk_away_organization
                           FOREIGN KEY(awayOrganizationId)
                               REFERENCES organizations(organizationId)
);

CREATE TABLE ratings (
                         ratingId SERIAL PRIMARY KEY,
                         "from" INTEGER NOT NULL,
                         "to" INTEGER NOT NULL,
                         ratingValue INTEGER NOT NULL,
                         gameId INTEGER NOT NULL,
                         CONSTRAINT fk_game
                             FOREIGN KEY(gameId)
                                 REFERENCES games(gameId),
                         CONSTRAINT fk_from_user
                             FOREIGN KEY ("from")
                                 REFERENCES users(userId),
                         CONSTRAINT fk_to_user
                             FOREIGN KEY ("to")
                                 REFERENCES users(userId)
);