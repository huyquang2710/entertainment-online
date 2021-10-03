CREATE TABLE `entertainment`.`user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `isAdmin` BIT NOT NULL DEFAULT 0,
  `isActive` BIT NOT NULL DEFAULT 1,
  UNIQUE INDEX `username_UNIQUE` (`username` ASC) VISIBLE,
  UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
  PRIMARY KEY (`id`));

  
  CREATE TABLE `entertainment`.`video` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `href` VARCHAR(45) NOT NULL,
  `poster` VARCHAR(255) NULL,
  `views` INT NOT NULL DEFAULT 0,
  `shares` INT NOT NULL DEFAULT 0,
  `description` LONGTEXT NOT NULL,
  `isActive` INT NOT NULL DEFAULT 1,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `href_UNIQUE` (`href` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

CREATE TABLE `entertainment`.`history` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `userId` INT NULL,
  `likeDate` VARCHAR(45) NULL,
  `videoId` INT NULL,
  `viewedDate` DATETIME NOT NULL CURRENT_TIMESTAMP,
  `isLiked` BIT NOT NULL DEFAULT 0,
  `likedDate` DATETIME,
  PRIMARY KEY (`id`),
  INDEX `fk_history_user_idx` (`userId` ASC) VISIBLE,
  INDEX `fk_history_video_idx` (`videoId` ASC) VISIBLE,
  CONSTRAINT `fk_history_user`
    FOREIGN KEY (`userId`)
    REFERENCES `entertainment`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_history_video`
    FOREIGN KEY (`videoId`)
    REFERENCES `entertainment`.`video` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin;

INSERT INTO `entertainment`.`user` (`username`, `password`, `email`, `isAdmin`) VALUES ('admin', '12345', 'phunghuyquang69@gmail.com', b'1');
INSERT INTO `entertainment`.`user` (`username`, `password`, `email`, `isAdmin`) VALUES ('user', '12345', 'user@gmail.com', b'0');

INSERT INTO `entertainment`.`video` (`title`, `href`, `description`) VALUES ('Lời tỏ tình Lời tỏ tình mùa hạ', '5gGtXH0cIxo', 'Sáng, Ngọc phóng một lèo hai bậc cầu thang, xông vào lớp vừa đúng lúc tiếng');
INSERT INTO `entertainment`.`video` (`title`, `href`, `description`) VALUES ('Như một lời tỏ tình', 'BE96jNkELSU', 'Bạn “Đã bao giờ bạn thích một người đủ nhiều để chấp nhận mặc một chiếc váy khác size và đeo lên một chiếc mặt nạ cầu kỳ để được ở bên cạnh họ. Tóm lại là vì họ mà bạn cố gắng trở thành một con người khác”.');

INSERT INTO `entertainment`.`history` (`userId`, `videoId`, `isLiked`, `likedDate`) VALUES ('2', '2', b'1', NOW());

