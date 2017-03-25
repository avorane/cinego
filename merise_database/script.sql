#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: Utilisateur
#------------------------------------------------------------

CREATE TABLE Utilisateur(
        idUtilisateur     int (11) Auto_increment  NOT NULL ,
        identifiantAbonne Varchar (25) ,
        motdepasseAbonne  Varchar (25) ,
        nomAbonne         Varchar (25) NOT NULL ,
        prenomAbonne      Varchar (25) ,
        dateInscription   Date ,
        active            Bool ,
        idTypeUtilisateur Int ,
        PRIMARY KEY (idUtilisateur )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Reservation
#------------------------------------------------------------

CREATE TABLE Reservation(
        idReservation   int (11) Auto_increment  NOT NULL ,
        dateReservation Date ,
        idUtilisateur   Int NOT NULL ,
        idSeance        Int NOT NULL ,
        idMajoration    Int NOT NULL ,
        PRIMARY KEY (idReservation )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Seance
#------------------------------------------------------------

CREATE TABLE Seance(
        idSeance   int (11) Auto_increment  NOT NULL ,
        dateSeance Datetime ,
        idFilm     Int NOT NULL ,
        idSalle    Int NOT NULL ,
        PRIMARY KEY (idSeance )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Film
#------------------------------------------------------------

CREATE TABLE Film(
        idFilm           int (11) Auto_increment  NOT NULL ,
        titreFilm        Varchar (80) NOT NULL ,
        idFilmWebService Varchar (50) ,
        dureeFilm        Int ,
        PRIMARY KEY (idFilm ) ,
        UNIQUE (idFilmWebService )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Majoration
#------------------------------------------------------------

CREATE TABLE Majoration(
        idMajoration      int (11) Auto_increment  NOT NULL ,
        libelleMajoration Varchar (25) NOT NULL ,
        valeurMajoration  Float NOT NULL ,
        PRIMARY KEY (idMajoration )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Siege
#------------------------------------------------------------

CREATE TABLE Siege(
        idSiege     int (11) Auto_increment  NOT NULL ,
        active      Bool ,
        idRangee    Int ,
        idTypeSiege Int ,
        PRIMARY KEY (idSiege )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Tarif
#------------------------------------------------------------

CREATE TABLE Tarif(
        idTarif      int (11) Auto_increment  NOT NULL ,
        libelleTarif Varchar (25) NOT NULL ,
        prix         Float NOT NULL ,
        PRIMARY KEY (idTarif )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Salle
#------------------------------------------------------------

CREATE TABLE Salle(
        idSalle      int (11) Auto_increment  NOT NULL ,
        libelleSalle Varchar (25) NOT NULL ,
        active       Bool ,
        idTypeSalle  Int ,
        idCinema     Int ,
        PRIMARY KEY (idSalle )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Extra
#------------------------------------------------------------

CREATE TABLE Extra(
        idExtra      int (11) Auto_increment  NOT NULL ,
        libelleExtra Varchar (25) NOT NULL ,
        prix         Float NOT NULL ,
        PRIMARY KEY (idExtra )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Equipement
#------------------------------------------------------------

CREATE TABLE Equipement(
        idEquipement      int (11) Auto_increment  NOT NULL ,
        libelleEquipement Varchar (50) ,
        idSalle           Int NOT NULL ,
        idTypeEquipement  Int ,
        PRIMARY KEY (idEquipement )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TypeSalle
#------------------------------------------------------------

CREATE TABLE TypeSalle(
        idTypeSalle      int (11) Auto_increment  NOT NULL ,
        libelleTypeSalle Varchar (25) ,
        PRIMARY KEY (idTypeSalle )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TypeEquipement
#------------------------------------------------------------

CREATE TABLE TypeEquipement(
        idTypeEquipement      int (11) Auto_increment  NOT NULL ,
        libelleTypeEquipement Varchar (25) ,
        PRIMARY KEY (idTypeEquipement )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Cinema
#------------------------------------------------------------

CREATE TABLE Cinema(
        idCinema      int (11) Auto_increment  NOT NULL ,
        libelleCinema Varchar (25) ,
        idUtilisateur Int NOT NULL ,
        PRIMARY KEY (idCinema )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TypeUtilisateur
#------------------------------------------------------------

CREATE TABLE TypeUtilisateur(
        idTypeUtilisateur      int (11) Auto_increment  NOT NULL ,
        libelleTypeUtilisateur Varchar (50) ,
        PRIMARY KEY (idTypeUtilisateur )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Rangee
#------------------------------------------------------------

CREATE TABLE Rangee(
        idRangee int (11) Auto_increment  NOT NULL ,
        active   Bool ,
        idSalle  Int NOT NULL ,
        PRIMARY KEY (idRangee )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: TypeSiege
#------------------------------------------------------------

CREATE TABLE TypeSiege(
        idTypeSiege      int (11) Auto_increment  NOT NULL ,
        libelleTypeSiege Varchar (50) ,
        PRIMARY KEY (idTypeSiege )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: reservation_siege_tarif
#------------------------------------------------------------

CREATE TABLE reservation_siege_tarif(
        idReservation Int NOT NULL ,
        idTarif       Int NOT NULL ,
        idSiege       Int NOT NULL ,
        PRIMARY KEY (idReservation ,idTarif ,idSiege )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: reservation_siege_extra
#------------------------------------------------------------

CREATE TABLE reservation_siege_extra(
        quantite      Int ,
        idReservation Int NOT NULL ,
        idSiege       Int NOT NULL ,
        idExtra       Int NOT NULL ,
        PRIMARY KEY (idReservation ,idSiege ,idExtra )
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: propose
#------------------------------------------------------------

CREATE TABLE propose(
        idSeance Int NOT NULL ,
        idTarif  Int NOT NULL ,
        PRIMARY KEY (idSeance ,idTarif )
)ENGINE=InnoDB;

ALTER TABLE Utilisateur ADD CONSTRAINT FK_Utilisateur_idTypeUtilisateur FOREIGN KEY (idTypeUtilisateur) REFERENCES TypeUtilisateur(idTypeUtilisateur);
ALTER TABLE Reservation ADD CONSTRAINT FK_Reservation_idUtilisateur FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur);
ALTER TABLE Reservation ADD CONSTRAINT FK_Reservation_idSeance FOREIGN KEY (idSeance) REFERENCES Seance(idSeance);
ALTER TABLE Reservation ADD CONSTRAINT FK_Reservation_idMajoration FOREIGN KEY (idMajoration) REFERENCES Majoration(idMajoration);
ALTER TABLE Seance ADD CONSTRAINT FK_Seance_idFilm FOREIGN KEY (idFilm) REFERENCES Film(idFilm);
ALTER TABLE Seance ADD CONSTRAINT FK_Seance_idSalle FOREIGN KEY (idSalle) REFERENCES Salle(idSalle);
ALTER TABLE Siege ADD CONSTRAINT FK_Siege_idRangee FOREIGN KEY (idRangee) REFERENCES Rangee(idRangee);
ALTER TABLE Siege ADD CONSTRAINT FK_Siege_idTypeSiege FOREIGN KEY (idTypeSiege) REFERENCES TypeSiege(idTypeSiege);
ALTER TABLE Salle ADD CONSTRAINT FK_Salle_idTypeSalle FOREIGN KEY (idTypeSalle) REFERENCES TypeSalle(idTypeSalle);
ALTER TABLE Salle ADD CONSTRAINT FK_Salle_idCinema FOREIGN KEY (idCinema) REFERENCES Cinema(idCinema);
ALTER TABLE Equipement ADD CONSTRAINT FK_Equipement_idSalle FOREIGN KEY (idSalle) REFERENCES Salle(idSalle);
ALTER TABLE Equipement ADD CONSTRAINT FK_Equipement_idTypeEquipement FOREIGN KEY (idTypeEquipement) REFERENCES TypeEquipement(idTypeEquipement);
ALTER TABLE Cinema ADD CONSTRAINT FK_Cinema_idUtilisateur FOREIGN KEY (idUtilisateur) REFERENCES Utilisateur(idUtilisateur);
ALTER TABLE Rangee ADD CONSTRAINT FK_Rangee_idSalle FOREIGN KEY (idSalle) REFERENCES Salle(idSalle);
ALTER TABLE reservation_siege_tarif ADD CONSTRAINT FK_reservation_siege_tarif_idReservation FOREIGN KEY (idReservation) REFERENCES Reservation(idReservation);
ALTER TABLE reservation_siege_tarif ADD CONSTRAINT FK_reservation_siege_tarif_idTarif FOREIGN KEY (idTarif) REFERENCES Tarif(idTarif);
ALTER TABLE reservation_siege_tarif ADD CONSTRAINT FK_reservation_siege_tarif_idSiege FOREIGN KEY (idSiege) REFERENCES Siege(idSiege);
ALTER TABLE reservation_siege_extra ADD CONSTRAINT FK_reservation_siege_extra_idReservation FOREIGN KEY (idReservation) REFERENCES Reservation(idReservation);
ALTER TABLE reservation_siege_extra ADD CONSTRAINT FK_reservation_siege_extra_idSiege FOREIGN KEY (idSiege) REFERENCES Siege(idSiege);
ALTER TABLE reservation_siege_extra ADD CONSTRAINT FK_reservation_siege_extra_idExtra FOREIGN KEY (idExtra) REFERENCES Extra(idExtra);
ALTER TABLE propose ADD CONSTRAINT FK_propose_idSeance FOREIGN KEY (idSeance) REFERENCES Seance(idSeance);
ALTER TABLE propose ADD CONSTRAINT FK_propose_idTarif FOREIGN KEY (idTarif) REFERENCES Tarif(idTarif);
