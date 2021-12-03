-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : lun. 18 jan. 2021 à 19:34
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestion_hotel`
--

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `nom_category` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `category`
--

INSERT INTO `category` (`nom_category`) VALUES
('cosmétique'),
('nettoyage'),
('pharmaceutique'),
('meuble');

-- --------------------------------------------------------

--
-- Structure de la table `chambre`
--

DROP TABLE IF EXISTS `chambre`;
CREATE TABLE IF NOT EXISTS `chambre` (
  `num_chambre` int(11) NOT NULL,
  `etage_chambre` int(11) DEFAULT NULL,
  `nom_type` varchar(255) DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL,
  `etat_chambre` varchar(255) DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  PRIMARY KEY (`num_chambre`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `chambre`
--

INSERT INTO `chambre` (`num_chambre`, `etage_chambre`, `nom_type`, `cin`, `etat_chambre`, `date_debut`, `date_fin`) VALUES
(1, 1, 'Régulière', 'SL17629', 'reserved', '2021-01-18', '2021-01-19');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

DROP TABLE IF EXISTS `client`;
CREATE TABLE IF NOT EXISTS `client` (
  `id_client` int(11) NOT NULL AUTO_INCREMENT,
  `nom_client` varchar(100) DEFAULT NULL,
  `prenom_client` varchar(100) DEFAULT NULL,
  `cin` varchar(100) DEFAULT NULL,
  `num_chambre` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_client`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `client`
--

INSERT INTO `client` (`id_client`, `nom_client`, `prenom_client`, `cin`, `num_chambre`) VALUES
(15, 'SOUIDENA', 'MED', 'SH999', 21),
(19, 'NAJI', 'OUSSAMA', 'SH777', 9),
(20, 'LFARISSI', 'WAHBI', 'SL987', 8),
(21, 'fghjkl', 'rtyuio', 'RTYUIqsrtyuio', 7),
(22, 'eRTYUIa', 'ertyu', 'DFGHJK', 6),
(23, 'ERtyuioe', 'eRTyuio', 'TJRTYUI', 5),
(24, 'SOUIDENA ', 'MOHAMED', 'SL17629', 1),
(25, 'NAJI ', 'OUSSAMA', 'SL999', 2),
(26, 'EL MZOUART', 'Hassan', 'J6565', NULL),
(27, 'First', 'Second', 'J2368', NULL),
(28, 'First', 'Second', 'JQ515251', NULL),
(29, 'First', 'Second', 'JE54984', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

DROP TABLE IF EXISTS `salle`;
CREATE TABLE IF NOT EXISTS `salle` (
  `num_salle` int(11) NOT NULL,
  `etage_salle` int(11) DEFAULT NULL,
  `nom_typeS` varchar(255) DEFAULT NULL,
  `dateS_debut` date DEFAULT NULL,
  `dateS_fin` date DEFAULT NULL,
  `cin` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`num_salle`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`num_salle`, `etage_salle`, `nom_typeS`, `dateS_debut`, `dateS_fin`, `cin`) VALUES
(1, 1, 'Mariage', '2021-01-22', '2021-01-27', 'J2368'),
(2, 1, 'Mariage', NULL, NULL, NULL),
(3, 1, 'Conferences', NULL, NULL, NULL),
(4, 1, 'Conferences', '2021-01-15', '2021-01-21', 'JQ515251'),
(5, 2, 'Conferences', '2021-01-27', '2021-01-31', 'JE54984');

-- --------------------------------------------------------

--
-- Structure de la table `statisqtiques`
--

DROP TABLE IF EXISTS `statisqtiques`;
CREATE TABLE IF NOT EXISTS `statisqtiques` (
  `mois` int(11) DEFAULT NULL,
  `Annee` int(11) DEFAULT NULL,
  `nbr` int(11) DEFAULT NULL,
  `typesChmabres` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `statisqtiques`
--

INSERT INTO `statisqtiques` (`mois`, `Annee`, `nbr`, `typesChmabres`) VALUES
(1, 2021, 4, NULL),
(2, 2021, 1, NULL),
(3, 2021, 1, NULL),
(4, 2021, 1, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `stock`
--

DROP TABLE IF EXISTS `stock`;
CREATE TABLE IF NOT EXISTS `stock` (
  `id_stock` int(11) NOT NULL AUTO_INCREMENT,
  `nom_produit` varchar(255) DEFAULT NULL,
  `quantite_produit` int(11) DEFAULT NULL,
  `category_produit` varchar(255) DEFAULT NULL,
  `prix_unite` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_stock`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `type_chambre`
--

DROP TABLE IF EXISTS `type_chambre`;
CREATE TABLE IF NOT EXISTS `type_chambre` (
  `id_type` int(11) NOT NULL AUTO_INCREMENT,
  `nom_type` varchar(255) DEFAULT NULL,
  `prix_type` int(11) DEFAULT NULL,
  `nbr_chambre` int(11) DEFAULT NULL,
  `nbr_lits` int(11) DEFAULT NULL,
  `nbr_pers` int(11) DEFAULT NULL,
  `wc` varchar(255) DEFAULT NULL,
  `douche` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_chambre`
--

INSERT INTO `type_chambre` (`id_type`, `nom_type`, `prix_type`, `nbr_chambre`, `nbr_lits`, `nbr_pers`, `wc`, `douche`) VALUES
(3, 'Régulière', 50, 1, 1, 1, '1', '1'),
(4, ' Familiale', 200, 0, 4, 4, '1', '1'),
(6, 'Suite', 400, 0, 4, 6, '2', '2'),
(7, 'Communicantes', 300, 0, 2, 2, '1', '1'),
(8, 'Voisines', 150, 0, 1, 1, '1', '1'),
(9, 'Adjacentes', 100, 0, 1, 1, '1', '1');

-- --------------------------------------------------------

--
-- Structure de la table `type_salle`
--

DROP TABLE IF EXISTS `type_salle`;
CREATE TABLE IF NOT EXISTS `type_salle` (
  `id_typeS` int(11) NOT NULL AUTO_INCREMENT,
  `nom_typeS` varchar(255) DEFAULT NULL,
  `prix_typeS` int(11) DEFAULT NULL,
  `nbr_salles` int(11) DEFAULT NULL,
  `nbr_tables` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_typeS`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `type_salle`
--

INSERT INTO `type_salle` (`id_typeS`, `nom_typeS`, `prix_typeS`, `nbr_salles`, `nbr_tables`) VALUES
(1, 'Mariage', 30000, 2, 30),
(2, 'Conferences', 1000, 3, 100);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`username`, `password`) VALUES
('Admin', 'Admin');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
