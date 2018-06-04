CREATE DATABASE cadel_bd;

USE cadel_bd;

--
-- Table structure for table `curso`
--
CREATE TABLE `curso` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_bdhliwglt8i7q1v80fb95vea9` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `disciplina`
--
CREATE TABLE `disciplina` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `carga_horaria` int(11) NOT NULL,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_5e7mrxyfs14yqtibi2frwwxcc` (`nome`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `curso_disciplina`
--
CREATE TABLE `curso_disciplina` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `curso_id` bigint(20) DEFAULT NULL,
  `disciplina_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_curso_disciplina_curso` (`curso_id`),
  KEY `FK_curso_disciplina_disciplina` (`disciplina_id`),
  CONSTRAINT `curso_disciplina_curso` FOREIGN KEY (`curso_id`)
        REFERENCES `curso` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `curso_disciplina_disciplina` FOREIGN KEY (`disciplina_id`)
        REFERENCES `disciplina` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `aluno`
--
CREATE TABLE `aluno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `matricula` varchar(25) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `sexo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_prosr3jo55p8vlcu7e0g7s2ov` (`matricula`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `turma`
--
CREATE TABLE `turma` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ano` varchar(4) NOT NULL,
  `semestre` varchar(1) NOT NULL,
  `sigla` varchar(10) NOT NULL,
  `turno_letivo` varchar(255) DEFAULT NULL,
  `curso_id` bigint(20) DEFAULT NULL,
  `disciplina_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_turma_curso` (`curso_id`),
  KEY `FK_turma_disciplina` (`disciplina_id`),
  CONSTRAINT `turma_curso` FOREIGN KEY (`curso_id`)
        REFERENCES `curso` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `turma_disciplina` FOREIGN KEY (`disciplina_id`)
        REFERENCES `disciplina` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


--
-- Table structure for table `aluno_turma`
--
CREATE TABLE `aluno_turma` (
    `id` BIGINT(20) NOT NULL AUTO_INCREMENT,
    `aluno_id` BIGINT(20) DEFAULT NULL,
    `turma_id` BIGINT(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK_aluno_turma_aluno` (`aluno_id`),
    KEY `FK_aluno_turma_turma` (`turma_id`),
    CONSTRAINT `aluno_turma_aluno` FOREIGN KEY (`aluno_id`)
        REFERENCES `aluno` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
    CONSTRAINT `aluno_turma_turma` FOREIGN KEY (`turma_id`)
        REFERENCES `turma` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `professor`
--
CREATE TABLE `professor` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cpf` varchar(255) NOT NULL,
  `email` varchar(100) NOT NULL,
  `matricula` varchar(25) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `sexo` varchar(255) NOT NULL,
  `notificacao_email` int(11) DEFAULT '10',
  `perfil_id` int(11) NOT NULL,
  `senha` varchar(255) DEFAULT NULL,
  `curso_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_hbc55xkmu9ty3qpw3y9rehvoc` (`matricula`),
  KEY `FK_professor_curso` (`curso_id`),
  CONSTRAINT `professor_curso` FOREIGN KEY (`curso_id`)
        REFERENCES `curso` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `professor_disciplina`
--
CREATE TABLE `professor_disciplina` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `disciplina_id` bigint(20) DEFAULT NULL,
  `professor_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_professor_disciplina_disciplina` (`disciplina_id`),
  KEY `FK_professor_disciplina_professor` (`professor_id`),
  CONSTRAINT `professor_disciplina_disciplina` FOREIGN KEY (`disciplina_id`)
        REFERENCES `disciplina` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `professor_disciplina_professor` FOREIGN KEY (`professor_id`)
        REFERENCES `professor` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `classe`
--
CREATE TABLE `classe` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ano` varchar(4) NOT NULL,
  `codigo_dia` int(11) NOT NULL,
  `hora_abertura` varchar(255) NOT NULL,
  `hora_fechamento` varchar(255) NOT NULL,
  `semestre` varchar(1) NOT NULL,
  `turno` varchar(255) NOT NULL,
  `curso_id` bigint(20) NOT NULL,
  `disciplina_id` bigint(20) NOT NULL,
  `professor_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_classe_curso` (`curso_id`),
  KEY `FK_classe_disciplina` (`disciplina_id`),
  KEY `FK_classe_professor` (`professor_id`),
  CONSTRAINT `classe_curso` FOREIGN KEY (`curso_id`)
        REFERENCES `curso` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `classe_disciplina` FOREIGN KEY (`disciplina_id`)
        REFERENCES `disciplina` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `classe_professor` FOREIGN KEY (`professor_id`)
        REFERENCES `professor` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION      
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `classe_turma`
--
CREATE TABLE `classe_turma` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `classe_id` bigint(20) DEFAULT NULL,
  `turma_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_classe_turma_classe` (`classe_id`),
  KEY `FK_classe_turma_turma` (`turma_id`),
  CONSTRAINT `classe_turma_classe` FOREIGN KEY (`classe_id`)
        REFERENCES `classe` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `classe_turma_turma` FOREIGN KEY (`turma_id`)
        REFERENCES `turma` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `aula`
--
CREATE TABLE `aula` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` datetime DEFAULT NULL,
  `registro` varchar(255) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `classe_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_aula_classe` (`classe_id`),
    CONSTRAINT `aula_classe` FOREIGN KEY (`classe_id`)
        REFERENCES `classe` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Table structure for table `frequencia_aluno`
--
CREATE TABLE `frequencia_aluno` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `presenca` bit(1) NOT NULL,
  `aluno_id` bigint(20) DEFAULT NULL,
  `aula_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_frequencia_aluno_aluno` (`aluno_id`),
  KEY `FK_frequencia_aluno_aula` (`aula_id`),
  CONSTRAINT `frequencia_aluno_aluno` FOREIGN KEY (`aluno_id`)
        REFERENCES `aluno` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `frequencia_aluno_aula` FOREIGN KEY (`aula_id`)
        REFERENCES `aula` (`id`)
        ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;