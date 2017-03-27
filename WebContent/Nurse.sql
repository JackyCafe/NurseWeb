-- phpMyAdmin SQL Dump
-- version 4.7.0-beta1
-- https://www.phpmyadmin.net/
--
-- 主機: localhost
-- 產生時間： 2017 年 03 月 27 日 00:16
-- 伺服器版本: 5.7.16-enterprise-commercial-advanced
-- PHP 版本： 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- 資料庫： `Nurse`
--

-- --------------------------------------------------------

--
-- 資料表結構 `Nurse`
--

CREATE TABLE `Nurse` (
  `id` int(11) NOT NULL,
  `NurseId` varchar(5) COLLATE utf8_bin NOT NULL,
  `Name` varchar(10) COLLATE utf8_bin NOT NULL,
  `updateTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 資料表的匯出資料 `Nurse`
--

INSERT INTO `Nurse` (`id`, `NurseId`, `Name`, `updateTime`) VALUES
(1, 'N1', 'N1', '2017-03-27 00:17:29'),
(2, 'N2', 'N2', '2017-03-27 00:17:45'),
(3, 'N3', 'N3', '2017-03-27 00:18:57');

-- --------------------------------------------------------

--
-- 資料表結構 `NurseOper`
--

CREATE TABLE `NurseOper` (
  `nurse_id` int(11) NOT NULL,
  `oper_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 資料表的匯出資料 `NurseOper`
--

INSERT INTO `NurseOper` (`nurse_id`, `oper_id`) VALUES
(1, 4),
(1, 5),
(1, 6),
(2, 4),
(2, 5),
(2, 6);

-- --------------------------------------------------------

--
-- 資料表結構 `Oper`
--

CREATE TABLE `Oper` (
  `oper_id` int(11) NOT NULL,
  `name` varchar(16) COLLATE utf8_bin NOT NULL,
  `updateTime` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 資料表的匯出資料 `Oper`
--

INSERT INTO `Oper` (`oper_id`, `name`, `updateTime`) VALUES
(4, ' job1', '2017-03-27 00:53:07'),
(5, ' job2', '2017-03-27 00:53:11'),
(6, ' job3', '2017-03-27 00:53:15');

--
-- 已匯出資料表的索引
--

--
-- 資料表索引 `Nurse`
--
ALTER TABLE `Nurse`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `Name` (`Name`);

--
-- 資料表索引 `NurseOper`
--
ALTER TABLE `NurseOper`
  ADD KEY `oper_fk` (`oper_id`),
  ADD KEY `nurse_fk` (`nurse_id`);

--
-- 資料表索引 `Oper`
--
ALTER TABLE `Oper`
  ADD PRIMARY KEY (`oper_id`),
  ADD UNIQUE KEY `name` (`name`);

--
-- 在匯出的資料表使用 AUTO_INCREMENT
--

--
-- 使用資料表 AUTO_INCREMENT `Nurse`
--
ALTER TABLE `Nurse`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- 使用資料表 AUTO_INCREMENT `Oper`
--
ALTER TABLE `Oper`
  MODIFY `oper_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- 已匯出資料表的限制(Constraint)
--

--
-- 資料表的 Constraints `NurseOper`
--
ALTER TABLE `NurseOper`
  ADD CONSTRAINT `nurse_fk` FOREIGN KEY (`nurse_id`) REFERENCES `Nurse` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `oper_fk` FOREIGN KEY (`oper_id`) REFERENCES `Oper` (`oper_id`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
