-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:3306
-- Généré le : ven. 13 août 2021 à 11:40
-- Version du serveur :  5.7.24
-- Version de PHP : 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `db_gestion_inventaire`
--

-- --------------------------------------------------------

--
-- Structure de la table `departement`
--

CREATE TABLE `departement` (
  `id_Dep` int(11) NOT NULL,
  `nom_dep` varchar(255) NOT NULL,
  `abbr` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `departement`
--

INSERT INTO `departement` (`id_Dep`, `nom_dep`, `abbr`) VALUES
(1, 'Banques de Données', 'BD'),
(2, 'Recherches et Technique Minieres', 'RTM'),
(3, 'Etude Metallurgique et Integration Industrielle ', 'EMII'),
(4, 'Hygiene, Sante et Environnement Miniers', 'HSEM'),
(5, 'Artisanat Minier et des Carrieres', 'AMC'),
(6, 'Etude Juridique, Strategies et Politiques de Developpement', 'EJSPD'),
(7, 'Communication et Promotion des Investissements', 'CPI'),
(8, 'Ressources Humaines et Services Generaux ', 'RHSG'),
(9, 'Finance', 'Finance'),
(10, 'Coordination', 'Coordination');

-- --------------------------------------------------------

--
-- Structure de la table `materiels`
--

CREATE TABLE `materiels` (
  `num_Ref` int(11) NOT NULL,
  `code` varchar(255) NOT NULL,
  `etat` varchar(255) NOT NULL,
  `marque` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `bailleur` varchar(255) DEFAULT NULL,
  `type` varchar(255) NOT NULL,
  `montant` int(11) DEFAULT NULL,
  `nom_utilisateur` varchar(255) DEFAULT NULL,
  `fonction_utilisateur` varchar(255) DEFAULT NULL,
  `id_Dep` int(11) NOT NULL,
  `date_saved` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `materiels`
--

INSERT INTO `materiels` (`num_Ref`, `code`, `etat`, `marque`, `date`, `bailleur`, `type`, `montant`, `nom_utilisateur`, `fonction_utilisateur`, `id_Dep`, `date_saved`) VALUES
(1, '0x123', 'Bien', 'Lenovo', '2021-07-07', 'Ezechiel', 'Oridinateur', 2100, 'yves', 'huissier', 1, '2021-07-31 18:47:15'),
(2, '2sdefh', 'Mal', 'Samsung', '2021-07-13', 'Sums', 'Imprimante', 500, 'muyaya', 'Balayeur', 1, '2021-07-31 18:47:15'),
(3, '002', 'Excellent', 'Acer', '2021-07-26', 'Shadownet', 'Ordinateur', 2500, 'Sum', 'DG', 5, '2021-08-02 11:10:45'),
(4, '055', 'Bien', 'Asus', '2021-08-05', 'eze', 'Machine', 1500, 'Shadow', 'Travaileur', 1, '2021-08-03 07:42:28'),
(5, '055555', 'Bien', 'HHPP', '0121-07-05', '', 'ordinateur', 2500, '', '', 2, '2021-08-03 08:06:54'),
(6, '1010', 'Bien', 'Junior', '2021-08-06', 'Sums', 'Micro', 200, 'shadow', 'DG', 10, '2021-08-13 11:36:12');

-- --------------------------------------------------------

--
-- Structure de la table `services`
--

CREATE TABLE `services` (
  `id_service` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `id_Dep` int(11) DEFAULT NULL,
  `DATE_CREATED` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `services`
--

INSERT INTO `services` (`id_service`, `nom`, `id_Dep`, `DATE_CREATED`) VALUES
(1, 'bibliotheque', 1, '2021-07-31 18:22:33'),
(2, 'infonet', 1, '2021-07-31 18:22:33');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `user` varchar(255) NOT NULL,
  `pass` varchar(255) NOT NULL,
  `privilege` int(11) NOT NULL DEFAULT '1',
  `user_created` varchar(255) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `nom`, `user`, `pass`, `privilege`, `user_created`, `date_created`) VALUES
(1, 'Ezechiel', 'sums', 'sums', 0, NULL, '2021-08-04 14:11:50');

-- --------------------------------------------------------

--
-- Doublure de structure pour la vue `v_materiel`
-- (Voir ci-dessous la vue réelle)
--
CREATE TABLE `v_materiel` (
`num_ref` int(11)
,`code` varchar(255)
,`etat` varchar(255)
,`marque` varchar(255)
,`date_acquisition` date
,`bailleur` varchar(255)
,`type_materiel` varchar(255)
,`montant` int(11)
,`nom_utilisateur` varchar(255)
,`fonction_utilisateur` varchar(255)
,`date_saved` timestamp
,`id_Dep` int(11)
,`nom_dep` varchar(255)
);

-- --------------------------------------------------------

--
-- Structure de la vue `v_materiel`
--
DROP TABLE IF EXISTS `v_materiel`;

CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `v_materiel`  AS  (select `materiels`.`num_Ref` AS `num_ref`,`materiels`.`code` AS `code`,`materiels`.`etat` AS `etat`,`materiels`.`marque` AS `marque`,`materiels`.`date` AS `date_acquisition`,`materiels`.`bailleur` AS `bailleur`,`materiels`.`type` AS `type_materiel`,`materiels`.`montant` AS `montant`,`materiels`.`nom_utilisateur` AS `nom_utilisateur`,`materiels`.`fonction_utilisateur` AS `fonction_utilisateur`,`materiels`.`date_saved` AS `date_saved`,`departement`.`id_Dep` AS `id_Dep`,`departement`.`nom_dep` AS `nom_dep` from (`materiels` join `departement`) where (`materiels`.`id_Dep` = `departement`.`id_Dep`)) ;

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `departement`
--
ALTER TABLE `departement`
  ADD PRIMARY KEY (`id_Dep`);

--
-- Index pour la table `materiels`
--
ALTER TABLE `materiels`
  ADD PRIMARY KEY (`num_Ref`),
  ADD KEY `id_Dep` (`id_Dep`);

--
-- Index pour la table `services`
--
ALTER TABLE `services`
  ADD PRIMARY KEY (`id_service`),
  ADD KEY `id_Dep` (`id_Dep`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `departement`
--
ALTER TABLE `departement`
  MODIFY `id_Dep` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT pour la table `materiels`
--
ALTER TABLE `materiels`
  MODIFY `num_Ref` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `services`
--
ALTER TABLE `services`
  MODIFY `id_service` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `materiels`
--
ALTER TABLE `materiels`
  ADD CONSTRAINT `materiels_ibfk_1` FOREIGN KEY (`id_Dep`) REFERENCES `departement` (`id_Dep`);

--
-- Contraintes pour la table `services`
--
ALTER TABLE `services`
  ADD CONSTRAINT `services_ibfk_1` FOREIGN KEY (`id_Dep`) REFERENCES `departement` (`id_Dep`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
