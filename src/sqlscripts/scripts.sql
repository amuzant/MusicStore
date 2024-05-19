DROP TABLE IF EXISTS proiectpao.debitcard,proiectpao.user,proiectpao.produs,proiectpao.album,proiectpao.discalbum,proiectpao.discinterior,proiectpao.melodie,proiectpao.inchiriere,proiectpao.comanda,proiectpao.produscomandat,proiectpao.chitara,chitaraacustica,proiectpao.chitaraelectrica cascade;

-- Tabela pentru DebitCard
CREATE TABLE proiectpao.DebitCard (
                           codCard VARCHAR(255) PRIMARY KEY,
                           balanta FLOAT,
                           banca VARCHAR(255),
                           limita FLOAT,
                           dataExpirare DATE
);

-- Tabela pentru User
CREATE TABLE proiectpao.User (
                      id INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                      nume VARCHAR(255),
                      email VARCHAR(255),
                      nrTelefon VARCHAR(20),
                      adresa VARCHAR(255),
                      dataAlaturarii DATETIME,
                      card_cod VARCHAR(255),
                      FOREIGN KEY (card_cod) REFERENCES DebitCard(codCard) ON DELETE CASCADE
);

-- Tabela pentru Produs
CREATE TABLE proiectpao.Produs (
                        id INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                        denumire VARCHAR(255),
                        pret FLOAT,
                        conditie VARCHAR(255),
                        stoc INT,
                        rating FLOAT DEFAULT 0,
                        nrReviewuri INT DEFAULT 0
);



-- Tabela pentru Album
CREATE TABLE proiectpao.Album (
                       id INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                       numeArtist VARCHAR(255),
                       numeAlbum VARCHAR(255),
                       genMuzical VARCHAR(255)
);

-- Tabela pentru DiscAlbum
CREATE TABLE proiectpao.DiscAlbum (
                           tipDisc VARCHAR(50),
                           anLansare INT,
                           numeCasaDeDiscuri VARCHAR(255),
                           nrDiscuri INT,
                           pretInchirierePeZi FLOAT,
                           album_id INT,
                           produs_id INT,
                           PRIMARY KEY (produs_id),
                           FOREIGN KEY (produs_id) REFERENCES Produs(id) ON DELETE CASCADE
);

-- Tabela pentru DiscInterior
CREATE TABLE proiectpao.DiscInterior (
                              id INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                              produs_id INT,
                              denumire VARCHAR(255),
                              nrDisc INT,
                              nrPiese INT,
                              FOREIGN KEY (produs_id) REFERENCES Produs(id) ON DELETE CASCADE
);

-- Tabela pentru Melodie
CREATE TABLE proiectpao.Melodie (
                                    id INT NOT NULL UNIQUE AUTO_INCREMENT,
                                    discInterior_id INT,
                                    denumire VARCHAR(255),
                                    indexPiesa INT,
                                    durata INT,
                                    PRIMARY KEY(denumire,discInterior_id),
                                    FOREIGN KEY (discInterior_id) REFERENCES DiscInterior(id) ON DELETE CASCADE
);

-- Tabela pentru Inchiriere
CREATE TABLE proiectpao.Inchiriere (
                            id INT AUTO_INCREMENT NOT NULL UNIQUE PRIMARY KEY,
                            client_id INT,
                            albumImprumutat_id INT,
                            dataInchirierii DATE,
                            zileInchiriate INT,
                            pretPlatit FLOAT,
                            FOREIGN KEY (client_id) REFERENCES User(id) ON DELETE CASCADE ,
                            FOREIGN KEY (albumImprumutat_id) REFERENCES DiscAlbum(produs_id) ON DELETE CASCADE
);

-- Tabela pentru Comanda
CREATE TABLE proiectpao.Comanda (
                         id INT AUTO_INCREMENT PRIMARY KEY NOT NULL UNIQUE,
                         client_id INT,
                         pretTotal FLOAT,
                         dataAchizitiei DATETIME,
                         FOREIGN KEY (client_id) REFERENCES User(id) ON DELETE CASCADE
);

-- Tabela asociativa (Produsele care intra intr-o comanda)
CREATE TABLE proiectpao.ProdusComandat
(
                        id INT AUTO_INCREMENT NOT NULL UNIQUE,
                        comanda_id INT,
                        produs_id INT,
                        reviewed BOOL,
                        primary key (comanda_id,produs_id),
                        FOREIGN KEY (comanda_id) REFERENCES Comanda(id) ON DELETE CASCADE,
                        FOREIGN KEY (produs_id) REFERENCES Produs(id) ON DELETE CASCADE
);

-- Tabela pentru Chitara
CREATE TABLE proiectpao.Chitara (
                         culoare VARCHAR(50),
                         produs_id INT,
                         PRIMARY KEY (produs_id),
                         FOREIGN KEY (produs_id) REFERENCES Produs(id) ON DELETE CASCADE
);

-- Tabela pentru ChitaraElectrica
CREATE TABLE proiectpao.ChitaraElectrica (
                                  configuratie VARCHAR(255),
                                  produs_id INT,
                                  PRIMARY KEY (produs_id),
                                  FOREIGN KEY (produs_id) REFERENCES Produs(id) ON DELETE CASCADE
);

-- Tabela pentru ChitaraAcustica
CREATE TABLE proiectpao.ChitaraAcustica (
                                 forma VARCHAR(255),
                                 produs_id INT,
                                 PRIMARY KEY (produs_id),
                                 FOREIGN KEY (produs_id) REFERENCES Produs(id) ON DELETE CASCADE
);
