SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


INSERT INTO `Extra` (`idExtra`, `libelleExtra`, `prix`) VALUES
(3, 'Coca', 2),
(4, 'Fanta', 2.5);

INSERT INTO `Film` (`idFilm`, `titreFilm`, `dateFilm`) VALUES
(1, 'Robin des bois', '2017-03-08'),
(2, 'Oblivion', '2017-03-08');

INSERT INTO `Majoration` (`idMajoration`, `libelleMajoration`, `valeurMajoration`) VALUES
(1, '1 siège réservé', 2.5),
(2, '3+ sièges réservés', 1.5);

INSERT INTO `Porte` (`idEquipement`, `X`, `Y`, `entree`, `sortie`, `idSalle`) VALUES
(1, 1, 1, 1, 0, 2),
(2, 1, 2, 0, 1, 2);

INSERT INTO `Reservation` (`idReservation`, `dateReservation`, `idUtilisateur`, `idSeance`, `idMajoration`) VALUES
(1, '2017-03-08', 2, 1, 1);

INSERT INTO `reservation_siege_extra` (`quantite`, `idReservation`, `idSiege`, `idExtra`) VALUES
(1, 1, 1, 3);

INSERT INTO `reservation_siege_tarif` (`idReservation`, `idTarif`, `idSiege`) VALUES
(1, 3, 1);

INSERT INTO `Salle` (`idSalle`, `libelleSalle`) VALUES
(1, 'toto'),
(2, 'Salle 2'),
(3, 'salleTest'),
(9, 'salleTest'),
(10, 'salleTest'),
(11, 'salleTest'),
(12, 'salleTest'),
(13, 'salleTest'),
(14, 'salleTest'),
(15, 'salleTest'),
(16, 'salleTest'),
(17, 'salleTest'),
(18, 'salleTest');

INSERT INTO `Seance` (`idSeance`, `libelle`, `dateSeance`, `idFilm`, `idSalle`) VALUES
(1, '8h - 10h', '2017-03-08', 1, 2),
(2, '10h - 12h', '2017-03-08', 2, 3);

INSERT INTO `Siege` (`idSiege`, `X`, `Y`, `handicapee`, `idSalle`) VALUES
(1, 1, 1, 0, 1),
(2, 1, 2, 1, 1);

INSERT INTO `Tarif` (`idTarif`, `libelleTarif`, `prix`) VALUES
(3, 'Adulte', 10),
(4, 'Enfant', 7.5);

INSERT INTO `Utilisateur` (`idUtilisateur`, `identifiantAbonne`, `motdepasseAbonne`, `nomAbonne`, `prenomAbonne`, `dateInscription`, `admin`) VALUES
(1, 'ADM1', '123456', 'DOE', 'John ', '2017-03-08', 1),
(2, 'UTIL1', '123456', 'SCOUBI', 'Doe', '2017-03-08', 0);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
