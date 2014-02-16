SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `intermotors` DEFAULT CHARACTER SET latin1 ;
USE `intermotors` ;

-- -----------------------------------------------------
-- Table `intermotors`.`automovel`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`automovel` (
  `ID_AUTO` INT(11) NOT NULL AUTO_INCREMENT ,
  `PLACA` VARCHAR(45) NOT NULL ,
  `VALOR` DOUBLE NOT NULL ,
  `STATUS` VARCHAR(45) NOT NULL ,
  `KILOMETRAGEM` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_AUTO`) ,
  UNIQUE INDEX `PLACA_UNIQUE` (`PLACA` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 51
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`espautomovel`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`espautomovel` (
  `FK_ID_AUTOMOVEL` INT(11) NOT NULL ,
  `MODELO` VARCHAR(45) NOT NULL ,
  `MARCA` VARCHAR(45) NOT NULL ,
  `ANO` INT(11) NOT NULL ,
  `ADICIONAIS` VARCHAR(200) NULL DEFAULT NULL ,
  `cor` VARCHAR(45) NULL DEFAULT NULL ,
  INDEX `FK_ID_AUTOMOVEL` (`FK_ID_AUTOMOVEL` ASC) ,
  CONSTRAINT `FK_ID_AUTOMOVEL`
    FOREIGN KEY (`FK_ID_AUTOMOVEL` )
    REFERENCES `intermotors`.`automovel` (`ID_AUTO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`usuario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`usuario` (
  `ID_USUARIO` INT(11) NOT NULL AUTO_INCREMENT ,
  `NOME` VARCHAR(45) NOT NULL ,
  `CPF` VARCHAR(45) NOT NULL ,
  `ENDERECO` VARCHAR(80) NULL DEFAULT NULL ,
  `EMAIL` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID_USUARIO`) ,
  UNIQUE INDEX `CPF_UNIQUE` (`CPF` ASC) )
ENGINE = InnoDB
AUTO_INCREMENT = 25
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`funcionario`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`funcionario` (
  `ID_FUNCIONARIO` INT(11) NOT NULL AUTO_INCREMENT ,
  `FK_ID_USUARIO` INT(11) NOT NULL ,
  `TIPO` VARCHAR(45) NULL DEFAULT NULL ,
  `salario` DOUBLE NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_FUNCIONARIO`) ,
  INDEX `FK_ID_USUARIO` (`FK_ID_USUARIO` ASC) ,
  CONSTRAINT `FK_ID_USUARIO`
    FOREIGN KEY (`FK_ID_USUARIO` )
    REFERENCES `intermotors`.`usuario` (`ID_USUARIO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 14
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`historico_manutencao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`historico_manutencao` (
  `ID_HIST_MANUTENCAO` INT(11) NOT NULL AUTO_INCREMENT ,
  `FK_AUTO` INT(11) NULL DEFAULT NULL ,
  `FK_USUARIO` INT(11) NULL DEFAULT NULL ,
  `FK_FUNC` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`ID_HIST_MANUTENCAO`) ,
  INDEX `FK_AUTOMOVEL` (`FK_AUTO` ASC) ,
  INDEX `FK_USU` (`FK_USUARIO` ASC) ,
  INDEX `FK_FUNCIONARIO` (`FK_FUNC` ASC) ,
  CONSTRAINT `FK_AUTOMOVEL`
    FOREIGN KEY (`FK_AUTO` )
    REFERENCES `intermotors`.`automovel` (`ID_AUTO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FUNCIONARIO`
    FOREIGN KEY (`FK_FUNC` )
    REFERENCES `intermotors`.`funcionario` (`ID_FUNCIONARIO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USU`
    FOREIGN KEY (`FK_USUARIO` )
    REFERENCES `intermotors`.`usuario` (`ID_USUARIO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`historico_venda`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`historico_venda` (
  `ID_HIST_VENDA` INT(11) NOT NULL AUTO_INCREMENT ,
  `FK_AUTO` INT(11) NOT NULL ,
  `FK_USUARIO` INT(11) NOT NULL ,
  `FK_FUNC` INT(11) NOT NULL ,
  PRIMARY KEY (`ID_HIST_VENDA`) ,
  INDEX `FK_AUTO` (`FK_AUTO` ASC) ,
  INDEX `FK_USUARIO` (`FK_USUARIO` ASC) ,
  INDEX `FK_FUNC` (`FK_FUNC` ASC) ,
  CONSTRAINT `FK_AUTO`
    FOREIGN KEY (`FK_AUTO` )
    REFERENCES `intermotors`.`automovel` (`ID_AUTO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_FUNC`
    FOREIGN KEY (`FK_FUNC` )
    REFERENCES `intermotors`.`funcionario` (`ID_FUNCIONARIO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `FK_USUARIO`
    FOREIGN KEY (`FK_USUARIO` )
    REFERENCES `intermotors`.`usuario` (`ID_USUARIO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`imagem`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`imagem` (
  `fk_id_auto` INT(11) NOT NULL ,
  `caminho_img` VARCHAR(200) NULL DEFAULT NULL ,
  `caminho_thumb` VARCHAR(200) NULL DEFAULT NULL ,
  INDEX `fk_id_auto_im` (`fk_id_auto` ASC) ,
  CONSTRAINT `fk_id_auto_im`
    FOREIGN KEY (`fk_id_auto` )
    REFERENCES `intermotors`.`automovel` (`ID_AUTO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`login`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`login` (
  `FK_ID_FUNC` INT(11) NOT NULL ,
  `NOME_USUARIO` VARCHAR(45) NOT NULL ,
  `SENHA` VARCHAR(45) NOT NULL ,
  UNIQUE INDEX `NOME_USUARIO_UNIQUE` (`NOME_USUARIO` ASC) ,
  INDEX `FK_ID_FUNC` (`FK_ID_FUNC` ASC) ,
  CONSTRAINT `FK_ID_FUNC`
    FOREIGN KEY (`FK_ID_FUNC` )
    REFERENCES `intermotors`.`funcionario` (`ID_FUNCIONARIO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`manutencao`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`manutencao` (
  `ID_MANUTENCAO` INT(11) NOT NULL AUTO_INCREMENT ,
  `FK_ID_HIST_MANUTENCAO` INT(11) NOT NULL ,
  `DATA` DATE NOT NULL ,
  `VALOR` FLOAT NULL DEFAULT NULL ,
  `DESCRICAO` VARCHAR(45) NULL DEFAULT NULL ,
  `STATUS` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID_MANUTENCAO`) ,
  INDEX `FK_ID_HIST_MANUTENCAO` (`FK_ID_HIST_MANUTENCAO` ASC) ,
  CONSTRAINT `FK_ID_HIST_MANUTENCAO`
    FOREIGN KEY (`FK_ID_HIST_MANUTENCAO` )
    REFERENCES `intermotors`.`historico_manutencao` (`ID_HIST_MANUTENCAO` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`reserva`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`reserva` (
  `ID_RESERVA` INT(11) NOT NULL AUTO_INCREMENT ,
  `FK_ID_HIST_VENDA` INT(11) NOT NULL ,
  `SINAL` INT(11) NULL DEFAULT NULL ,
  `DATA` DATE NOT NULL ,
  `STATUS` VARCHAR(45) NOT NULL ,
  PRIMARY KEY (`ID_RESERVA`) ,
  INDEX `FK_ID_HIST_VENDAS` (`FK_ID_HIST_VENDA` ASC) ,
  CONSTRAINT `FK_ID_HIST_VENDAS`
    FOREIGN KEY (`FK_ID_HIST_VENDA` )
    REFERENCES `intermotors`.`historico_venda` (`ID_HIST_VENDA` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;


-- -----------------------------------------------------
-- Table `intermotors`.`venda`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `intermotors`.`venda` (
  `ID_VENDA` INT(11) NOT NULL AUTO_INCREMENT ,
  `FK_ID_HIST_VENDA` INT(11) NOT NULL ,
  `VALOR` INT(11) NOT NULL ,
  `N_PRESTACOES` INT(11) NULL DEFAULT NULL ,
  `DATA` DATE NOT NULL ,
  PRIMARY KEY (`ID_VENDA`) ,
  INDEX `FK_ID_HIST_VENDA` (`FK_ID_HIST_VENDA` ASC) ,
  CONSTRAINT `FK_ID_HIST_VENDA`
    FOREIGN KEY (`FK_ID_HIST_VENDA` )
    REFERENCES `intermotors`.`historico_venda` (`ID_HIST_VENDA` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
