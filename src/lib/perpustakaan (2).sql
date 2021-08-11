-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 12, 2021 at 06:58 AM
-- Server version: 5.7.24-log
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `perpustakaan`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_anggota`
--

CREATE TABLE `tb_anggota` (
  `id` varchar(15) NOT NULL,
  `kode_anggota` varchar(10) NOT NULL,
  `nomor_induk` varchar(15) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `kelas` varchar(15) DEFAULT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `alamat` longtext,
  `no_telpn` varchar(15) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `hak_akses` varchar(100) DEFAULT NULL,
  `tanggal_pendaftaran` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_anggota`
--

INSERT INTO `tb_anggota` (`id`, `kode_anggota`, `nomor_induk`, `status`, `kelas`, `nama`, `alamat`, `no_telpn`, `email`, `username`, `password`, `hak_akses`, `tanggal_pendaftaran`) VALUES
('1', 'SMKLI-0001', '122333', 'pengurus', NULL, 'Ahmad Rifaldi', 'jl. H. Sarmili', '+6287771034769', 'ahmadrifaldi2402@gmail.com', 'ahmadrifaldi123', 'ahmadrifaldi123', '2', '2021-07-18'),
('2', 'SMKLI-0002', '123', 'Staff', NULL, 'ahmad rifaldi', 'jalan', '+62', 'ahmadrifaldi', 'username', 'password', 'Staff', '2021-07-18'),
('3', 'SMKLI-0003', '131401002', 'Siswa/i', 'X TKJ', 'Ahmad Rifaldi', 'jdwadwad', '132132132', '1dwadwadwad', 'ahmad123', 'ahmad123', '2', '2021-08-11');

-- --------------------------------------------------------

--
-- Table structure for table `tb_buku`
--

CREATE TABLE `tb_buku` (
  `no_regis` varchar(5) NOT NULL,
  `isbn_13` varchar(13) NOT NULL,
  `isbn_10` varchar(10) DEFAULT NULL,
  `judul` varchar(255) NOT NULL,
  `pengarang` varchar(255) NOT NULL,
  `penerbit` varchar(255) NOT NULL,
  `thn_terbit` int(11) NOT NULL,
  `harga` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `lokasi_rak` varchar(255) NOT NULL,
  `ringkasan` longtext NOT NULL,
  `foto` varchar(10000) DEFAULT NULL,
  `tanggal` datetime NOT NULL,
  `kondisi` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_buku`
--

INSERT INTO `tb_buku` (`no_regis`, `isbn_13`, `isbn_10`, `judul`, `pengarang`, `penerbit`, `thn_terbit`, `harga`, `jumlah`, `lokasi_rak`, `ringkasan`, `foto`, `tanggal`, `kondisi`) VALUES
('1', '9786025992230', '9786025992', 'This is Me', 'Muhajjah Saratini', 'C-Klik Media', 2019, 55000, 1, 'Novel', 'Pokoknya begitu dah', NULL, '2021-08-11 15:23:00', 'Baik');

-- --------------------------------------------------------

--
-- Table structure for table `tb_pengunjung`
--

CREATE TABLE `tb_pengunjung` (
  `kode_anggota` varchar(10) NOT NULL,
  `nomor_induk` varchar(10) NOT NULL,
  `nama` varchar(255) NOT NULL,
  `status` varchar(50) NOT NULL,
  `kelas` varchar(100) DEFAULT NULL,
  `tanggal` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `tb_pengunjung`
--

INSERT INTO `tb_pengunjung` (`kode_anggota`, `nomor_induk`, `nama`, `status`, `kelas`, `tanggal`) VALUES
('SMKLI-0003', '131401002', 'Ahmad Rifaldi', 'Siswa/i', 'X TKJ', '2021-08-11 00:00:00'),
('SMKLI-0003', '131401002', 'Ahmad Rifaldi', 'Siswa/i', 'X TKJ', '2021-08-11 00:00:00'),
('SMKLI-0003', '131401002', 'Ahmad Rifaldi', 'Siswa/i', 'X TKJ', '2021-08-11 00:00:00'),
('SMKLI-0003', '131401002', 'Ahmad Rifaldi', 'Siswa/i', 'X TKJ', '2021-08-11 14:18:33');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_anggota`
--
ALTER TABLE `tb_anggota`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_buku`
--
ALTER TABLE `tb_buku`
  ADD PRIMARY KEY (`no_regis`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
