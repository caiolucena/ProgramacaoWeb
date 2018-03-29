# Host: localhost  (Version 5.5.21)
# Date: 2018-03-28 16:52:09
# Generator: MySQL-Front 5.3  (Build 5.24)

/*!40101 SET NAMES utf8 */;
CREATE SCHEMA IF NOT EXISTS `biblioteca` DEFAULT CHARACTER SET utf8 ;
USE `biblioteca` ;
#
# Structure for table "area_conhecimento"
#

DROP TABLE IF EXISTS `area_conhecimento`;
CREATE TABLE `area_conhecimento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=56 DEFAULT CHARSET=utf8;

#
# Structure for table "autor"
#

DROP TABLE IF EXISTS `autor`;
CREATE TABLE `autor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

#
# Structure for table "cidade"
#

DROP TABLE IF EXISTS `cidade`;
CREATE TABLE `cidade` (
  `Id` int(11) NOT NULL AUTO_INCREMENT,
  `Codigo` int(11) NOT NULL,
  `Nome` varchar(255) NOT NULL,
  `Uf` varchar(2) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "anal"
#

DROP TABLE IF EXISTS `anal`;
CREATE TABLE `anal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` enum('artigo','poster','resumo') NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `congresso` varchar(45) NOT NULL,
  `ano_pub` date NOT NULL,
  `local` varchar(45) NOT NULL,
  `cidade_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`cidade_id`),
  KEY `fk_anal_cidade1_idx` (`cidade_id`),
  CONSTRAINT `fk_anal_cidade1` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "anal_has_autor"
#

DROP TABLE IF EXISTS `anal_has_autor`;
CREATE TABLE `anal_has_autor` (
  `anal_id` int(11) NOT NULL AUTO_INCREMENT,
  `autor_id` int(11) NOT NULL,
  PRIMARY KEY (`anal_id`,`autor_id`),
  KEY `fk_anal_has_autor_autor1_idx` (`autor_id`),
  KEY `fk_anal_has_autor_anal1_idx` (`anal_id`),
  CONSTRAINT `fk_anal_has_autor_anal1` FOREIGN KEY (`anal_id`) REFERENCES `anal` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_anal_has_autor_autor1` FOREIGN KEY (`autor_id`) REFERENCES `autor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "curso"
#

DROP TABLE IF EXISTS `curso`;
CREATE TABLE `curso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `tipo` enum('graduacao','pos_graduacao') NOT NULL,
  `area_conhecimento_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_curso_area_conhecimento1_idx` (`area_conhecimento_id`),
  CONSTRAINT `fk_curso_area_conhecimento1` FOREIGN KEY (`area_conhecimento_id`) REFERENCES `area_conhecimento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

#
# Structure for table "editora"
#

DROP TABLE IF EXISTS `editora`;
CREATE TABLE `editora` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8;

#
# Structure for table "jornal"
#

DROP TABLE IF EXISTS `jornal`;
CREATE TABLE `jornal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `data` date NOT NULL,
  `edicao` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "livro"
#

DROP TABLE IF EXISTS `livro`;
CREATE TABLE `livro` (
  `isbn` int(11) NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `editora_id` int(11) NOT NULL,
  `ano` date NOT NULL,
  `edicao` int(11) NOT NULL,
  `num_pag` int(11) NOT NULL,
  `area_conhecimento_id` int(11) NOT NULL,
  PRIMARY KEY (`isbn`,`editora_id`,`area_conhecimento_id`),
  KEY `fk_livro_editora1_idx` (`editora_id`),
  KEY `fk_livro_area_conhecimento1_idx` (`area_conhecimento_id`),
  CONSTRAINT `fk_livro_area_conhecimento1` FOREIGN KEY (`area_conhecimento_id`) REFERENCES `area_conhecimento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_livro_editora1` FOREIGN KEY (`editora_id`) REFERENCES `editora` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "autor_has_livro"
#

DROP TABLE IF EXISTS `autor_has_livro`;
CREATE TABLE `autor_has_livro` (
  `autor_id` int(11) NOT NULL AUTO_INCREMENT,
  `livro_isbn` int(11) NOT NULL,
  PRIMARY KEY (`autor_id`,`livro_isbn`),
  KEY `fk_autor_has_livro_livro1_idx` (`livro_isbn`),
  KEY `fk_autor_has_livro_autor_idx` (`autor_id`),
  CONSTRAINT `fk_autor_has_livro_autor` FOREIGN KEY (`autor_id`) REFERENCES `autor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_autor_has_livro_livro1` FOREIGN KEY (`livro_isbn`) REFERENCES `livro` (`isbn`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8;

#
# Structure for table "midia"
#

DROP TABLE IF EXISTS `midia`;
CREATE TABLE `midia` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `tipo` enum('CD','DVD') NOT NULL,
  `data_gravacao` date NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "orientador"
#

DROP TABLE IF EXISTS `orientador`;
CREATE TABLE `orientador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `formacao` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "revista"
#

DROP TABLE IF EXISTS `revista`;
CREATE TABLE `revista` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `editora_id` int(11) NOT NULL,
  `data` date NOT NULL,
  `edicao` int(11) NOT NULL,
  `num_pag` int(11) NOT NULL,
  PRIMARY KEY (`id`,`editora_id`),
  KEY `fk_revista_editora1_idx` (`editora_id`),
  CONSTRAINT `fk_revista_editora1` FOREIGN KEY (`editora_id`) REFERENCES `editora` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "tcc"
#

DROP TABLE IF EXISTS `tcc`;
CREATE TABLE `tcc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `autor_id` int(11) NOT NULL,
  `orientador_id` int(11) NOT NULL,
  `tipo` enum('monografia','tese','dissertacao') NOT NULL,
  `defesa` date NOT NULL,
  `cidade_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`autor_id`,`orientador_id`,`cidade_id`),
  KEY `fk_tcc_autor1_idx` (`autor_id`),
  KEY `fk_tcc_orientador1_idx` (`orientador_id`),
  KEY `fk_tcc_cidade1_idx` (`cidade_id`),
  CONSTRAINT `fk_tcc_autor1` FOREIGN KEY (`autor_id`) REFERENCES `autor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tcc_cidade1` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tcc_orientador1` FOREIGN KEY (`orientador_id`) REFERENCES `orientador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Structure for table "tema"
#

DROP TABLE IF EXISTS `tema`;
CREATE TABLE `tema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `areaconhecimento_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8;
