-- MySQL dump 10.16  Distrib 10.1.30-MariaDB, for Win32 (AMD64)
--
-- Host: 127.0.0.1    Database: db_assistencia_m
-- ------------------------------------------------------
-- Server version	10.1.30-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `chamado`
--

create database db_assistencia;

use db_assistencia;

DROP TABLE IF EXISTS `chamado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chamado` (
  `idChamado` int(11) NOT NULL AUTO_INCREMENT,
  `dt_entrada` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `observacao` varchar(200) NOT NULL,
  `problema_relatado` varchar(200) NOT NULL,
  `problema_constatado` varchar(200) DEFAULT NULL,
  `idCliente` int(11) NOT NULL,
  `idtecnico` int(11) NOT NULL,
  `status` varchar(20) NOT NULL DEFAULT 'Aberto' COMMENT '1 => Aberto\\\\n2 => Fechado\\\\n3 => Cancelado\\\\n4 => Aguardando cliente',
  `servico_idservico` int(11) NOT NULL,
  PRIMARY KEY (`idChamado`),
  KEY `fk_orcamento_Cliente1_idx` (`idCliente`),
  KEY `fk_orcamento_tecnico1_idx` (`idtecnico`),
  KEY `fk_chamado_servico1_idx` (`servico_idservico`),
  CONSTRAINT `fk_chamado_servico1` FOREIGN KEY (`servico_idservico`) REFERENCES `servico` (`idservico`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orcamento_Cliente1` FOREIGN KEY (`idCliente`) REFERENCES `cliente` (`idcliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_orcamento_tecnico1` FOREIGN KEY (`idtecnico`) REFERENCES `tecnico` (`idtecnico`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamado`
--

LOCK TABLES `chamado` WRITE;
/*!40000 ALTER TABLE `chamado` DISABLE KEYS */;
/*!40000 ALTER TABLE `chamado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chamado_produtos`
--

DROP TABLE IF EXISTS `chamado_produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chamado_produtos` (
  `idchamado_produto` int(11) NOT NULL AUTO_INCREMENT,
  `idchamado` int(11) NOT NULL,
  `idprodutos` int(11) NOT NULL,
  `valor_produto` float NOT NULL DEFAULT '0',
  `quantidade_produto` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`idchamado_produto`),
  KEY `fk_chamado_has_produtos_produtos1_idx` (`idprodutos`),
  KEY `fk_chamado_has_produtos_chamado1_idx` (`idchamado`),
  CONSTRAINT `fk_chamado_has_produtos_chamado1` FOREIGN KEY (`idchamado`) REFERENCES `chamado` (`idChamado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_chamado_has_produtos_produtos1` FOREIGN KEY (`idprodutos`) REFERENCES `produtos` (`idprodutos`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chamado_produtos`
--

LOCK TABLES `chamado_produtos` WRITE;
/*!40000 ALTER TABLE `chamado_produtos` DISABLE KEYS */;
/*!40000 ALTER TABLE `chamado_produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idcliente` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `cpf` char(11) NOT NULL,
  `telefone` varchar(14) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`idcliente`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Augusto','83593684063','4888331122','sose@getsimpleemail.com'),(8,'Heyr','86576128010','93797777','musicaux@clock-sale24.ru'),(9,'Towea','34328247000','2329432542','mauricio@gmail.com'),(10,'Kawu','22762366046','4043117274','Kaw@gmail.com'),(11,'Neyer','90838596061','4277065707','Neyer@gmail.com'),(12,'Zieis','45632539016','2756910295','Zieis@gmail.com'),(13,'Caers','97246317023','3845662046','Caers@gmail.com'),(14,'Vaizya','38837857098','2439919732','Vaizya@gmail.com'),(15,'Soels','32313453081','8840969493','Soels@gmail.com'),(16,'Bovia','36377558074','3890105837','Bovia@gmail.com'),(17,'Douwa','06488941003','7629592741','Douwa@gmail.com');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produtos`
--

DROP TABLE IF EXISTS `produtos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produtos` (
  `idprodutos` int(11) NOT NULL AUTO_INCREMENT,
  `marca` varchar(45) NOT NULL,
  `modelo` varchar(45) NOT NULL,
  `quantidade` int(11) NOT NULL DEFAULT '0',
  `descricao` varchar(200) DEFAULT NULL,
  `valor_custo` float NOT NULL DEFAULT '0',
  `valor_venda` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`idprodutos`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produtos`
--

LOCK TABLES `produtos` WRITE;
/*!40000 ALTER TABLE `produtos` DISABLE KEYS */;
INSERT INTO `produtos` VALUES (1,'avell','xee-93',1,'processador2',600,2500),(2,'dell','nbr-top1',3,'',1000,2000),(3,'dell','nbr-sss',3,'',500,200),(17,'DELL','Dell Inspiron Série 3000',1,'Problema no HD quebrado devido a uma queda',500,1000);
/*!40000 ALTER TABLE `produtos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `servico`
--

DROP TABLE IF EXISTS `servico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `servico` (
  `idservico` int(11) NOT NULL AUTO_INCREMENT,
  `valor_servico` float NOT NULL DEFAULT '0',
  `nome_servico` varchar(100) NOT NULL,
  PRIMARY KEY (`idservico`),
  UNIQUE KEY `nome_servico_UNIQUE` (`nome_servico`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `servico`
--

LOCK TABLES `servico` WRITE;
/*!40000 ALTER TABLE `servico` DISABLE KEYS */;
INSERT INTO `servico` VALUES (1,500,'Limpeza de disco '),(3,41,'Formatar nootbook'),(4,55.5,'Formatar gabinete'),(6,69,'Limpeza completa de nootbook'),(7,78,'Limpeza completa de gabinete');
/*!40000 ALTER TABLE `servico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tecnico`
--

DROP TABLE IF EXISTS `tecnico`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tecnico` (
  `idtecnico` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(45) NOT NULL,
  `telefone` varchar(45) DEFAULT NULL,
  `CPF` char(11) NOT NULL,
  PRIMARY KEY (`idtecnico`),
  UNIQUE KEY `CPF_UNIQUE` (`CPF`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tecnico`
--

LOCK TABLES `tecnico` WRITE;
/*!40000 ALTER TABLE `tecnico` DISABLE KEYS */;
INSERT INTO `tecnico` VALUES (1,'Mauricio','48991781977','73735807003'),(2,'Augusto','4833221100','93318352055'),(3,'Mauricia','22333','63190612005'),(4,'joao1','58991822','93753186058'),(5,'Ana Clara','99588232','21755406002'),(8,'Jose','213123545','95088407068'),(9,'Juan','213123545','92325138038'),(10,'Roberta','213123545','22056333090'),(11,'Sergio','213123545','70402159004'),(12,'Luan','13123131','07047775099'),(38,'Tefen','7912066141','57031839017'),(44,'Baylal','0980964940','68791213061'),(45,'Cower','9824223580','38084183036'),(48,'Xyekan','982422359','91237339006');
/*!40000 ALTER TABLE `tecnico` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary table structure for view `telainical`
--

DROP TABLE IF EXISTS `telainical`;
/*!50001 DROP VIEW IF EXISTS `telainical`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `telainical` (
  `idChamado` tinyint NOT NULL,
  `nome_cliente` tinyint NOT NULL,
  `nome_servico` tinyint NOT NULL,
  `modelo` tinyint NOT NULL,
  `nome_Tecnico` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `telainical`
--

/*!50001 DROP TABLE IF EXISTS `telainical`*/;
/*!50001 DROP VIEW IF EXISTS `telainical`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `telainical` AS select `t_chamado`.`idChamado` AS `idChamado`,`t_cliente`.`nome` AS `nome_cliente`,`t_servico`.`nome_servico` AS `nome_servico`,`t_produtos`.`modelo` AS `modelo`,`t_tecnico`.`nome` AS `nome_Tecnico` from (((((`chamado` `t_chamado` join `cliente` `t_cliente` on((`t_cliente`.`idcliente` = `t_chamado`.`idCliente`))) join `servico` `t_servico` on((`t_servico`.`idservico` = `t_chamado`.`servico_idservico`))) join `chamado_produtos` `t_chamado_produtos` on((`t_chamado_produtos`.`idchamado_produto` = `t_chamado`.`idChamado`))) join `produtos` `t_produtos` on((`t_produtos`.`idprodutos` = `t_chamado_produtos`.`idprodutos`))) join `tecnico` `t_tecnico` on((`t_tecnico`.`idtecnico` = `t_chamado`.`idtecnico`))) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-01  7:04:58
