# Host: localhost  (Version 5.5.21)
# Date: 2018-04-19 16:47:36
# Generator: MySQL-Front 5.3  (Build 5.24)

/*!40101 SET NAMES latin1 */;

#
# Structure for table "area_conhecimento"
#

DROP TABLE IF EXISTS `area_conhecimento`;
CREATE TABLE `area_conhecimento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

#
# Structure for table "autor"
#

DROP TABLE IF EXISTS `autor`;
CREATE TABLE `autor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=5571 DEFAULT CHARSET=utf8;

#
# Structure for table "anal"
#

DROP TABLE IF EXISTS `anal`;
CREATE TABLE `anal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tipo` enum('ARTIGO','POSTER','RESUMO') NOT NULL,
  `titulo` varchar(45) NOT NULL,
  `congresso` varchar(45) NOT NULL,
  `ano_pub` date NOT NULL,
  `cidade_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`cidade_id`),
  KEY `fk_anal_cidade1_idx` (`cidade_id`),
  CONSTRAINT `fk_anal_cidade1` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Structure for table "curso"
#

DROP TABLE IF EXISTS `curso`;
CREATE TABLE `curso` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `sigla` varchar(2) NOT NULL DEFAULT '',
  `tipo` enum('GRADUACAO','POS_GRADUACAO') NOT NULL,
  `area_conhecimento_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_curso_area_conhecimento1_idx` (`area_conhecimento_id`),
  CONSTRAINT `fk_curso_area_conhecimento1` FOREIGN KEY (`area_conhecimento_id`) REFERENCES `area_conhecimento` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

#
# Structure for table "aluno"
#

DROP TABLE IF EXISTS `aluno`;
CREATE TABLE `aluno` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(45) NOT NULL,
  `cpf` int(11) unsigned NOT NULL DEFAULT '0',
  `rg` int(11) unsigned NOT NULL DEFAULT '0',
  `naturalidade` varchar(45) NOT NULL,
  `nomeCompleto` varchar(45) NOT NULL,
  `nomeMae` varchar(45) NOT NULL,
  `endereco` varchar(45) NOT NULL,
  `telefone` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `anoIngresso` date NOT NULL,
  `periodoIngresso` int(11) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `curso_id` int(11) NOT NULL,
  `nivel` enum('G','E','M','D','P') DEFAULT NULL,
  PRIMARY KEY (`id`,`curso_id`),
  KEY `fk_aluno_curso_idx` (`curso_id`),
  CONSTRAINT `fk_aluno_curso` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

#
# Structure for table "editora"
#

DROP TABLE IF EXISTS `editora`;
CREATE TABLE `editora` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

#
# Structure for table "funcionario"
#

DROP TABLE IF EXISTS `funcionario`;
CREATE TABLE `funcionario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cpf` int(10) unsigned NOT NULL,
  `rg` int(11) NOT NULL,
  `naturalidade` varchar(45) NOT NULL,
  `nomeCompleto` varchar(45) NOT NULL,
  `nomeUsuario` varchar(45) NOT NULL DEFAULT '',
  `endereco` varchar(45) NOT NULL,
  `telefone` int(11) NOT NULL,
  `email` varchar(45) NOT NULL,
  `senha` varchar(45) NOT NULL,
  `admin` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=89 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

#
# Structure for table "orientador"
#

DROP TABLE IF EXISTS `orientador`;
CREATE TABLE `orientador` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `formacao` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

#
# Structure for table "tcc"
#

DROP TABLE IF EXISTS `tcc`;
CREATE TABLE `tcc` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(45) NOT NULL,
  `autor_id` int(11) NOT NULL,
  `orientador_id` int(11) NOT NULL,
  `tipo` enum('MONOGRAFIA','TESE','DISSERTACAO') NOT NULL,
  `defesa` date NOT NULL,
  `cidade_id` int(11) NOT NULL,
  PRIMARY KEY (`id`,`autor_id`,`orientador_id`,`cidade_id`),
  KEY `fk_tcc_autor1_idx` (`autor_id`),
  KEY `fk_tcc_orientador1_idx` (`orientador_id`),
  KEY `fk_tcc_cidade1_idx` (`cidade_id`),
  CONSTRAINT `fk_tcc_autor1` FOREIGN KEY (`autor_id`) REFERENCES `autor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tcc_cidade1` FOREIGN KEY (`cidade_id`) REFERENCES `cidade` (`Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tcc_orientador1` FOREIGN KEY (`orientador_id`) REFERENCES `orientador` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

#
# Structure for table "tema"
#

DROP TABLE IF EXISTS `tema`;
CREATE TABLE `tema` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `areaconhecimento_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `areaconhecimento_id` (`areaconhecimento_id`),
  CONSTRAINT `tema_ibfk_1` FOREIGN KEY (`areaconhecimento_id`) REFERENCES `area_conhecimento` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;
