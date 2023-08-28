-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 27 Bulan Mei 2021 pada 03.40
-- Versi server: 10.4.18-MariaDB
-- Versi PHP: 8.0.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `aplikasi_penjualan`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `jual`
--

CREATE TABLE `jual` (
  `ID_Penjualan` varchar(50) NOT NULL,
  `ID_Obat` varchar(50) NOT NULL,
  `Harga` int(20) NOT NULL,
  `Qty` varchar(50) NOT NULL,
  `Sub_Total` varchar(50) NOT NULL,
  `Time` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `jual`
--

INSERT INTO `jual` (`ID_Penjualan`, `ID_Obat`, `Harga`, `Qty`, `Sub_Total`, `Time`) VALUES
('21052100001', 'K0004', 10000, '1', '10000', '2021-05-21 11:39:30'),
('21052100002', 'K0006', 22000, '1', '22000', '2021-05-21 11:41:04'),
('21052200001', 'K0005', 28000, '1', '28000', '2021-05-22 03:13:23'),
('21052200002', 'K0006', 22000, '1', '22000', '2021-05-22 03:14:54'),
('21052200003', 'K0011', 5000, '2', '10000', '2021-05-22 09:51:15'),
('21052200004', 'K0008', 15000, '1', '15000', '2021-05-22 09:55:53'),
('21052300001', 'K0002', 20000, '2', '40000', '2021-05-23 04:38:31'),
('21052300002', 'K0002', 20000, '3', '60000', '2021-05-23 04:45:02'),
('21052600001', 'K0002', 20000, '1', '20000', '2021-05-26 08:11:00'),
('21052600001', 'K0002', 20000, '1', '20000', '2021-05-26 08:21:10'),
('21052600001', 'K0009', 500, '2', '1000', '2021-05-26 08:23:23');

--
-- Trigger `jual`
--
DELIMITER $$
CREATE TRIGGER `penguranganStok` AFTER INSERT ON `jual` FOR EACH ROW BEGIN
UPDATE obat SET Stok = Stok-new.Qty WHERE ID_Obat=new.ID_Obat;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `obat`
--

CREATE TABLE `obat` (
  `ID_Obat` varchar(50) NOT NULL,
  `Nama_Obat` varchar(50) NOT NULL,
  `Jenis_Obat` varchar(50) NOT NULL,
  `Harga` varchar(50) NOT NULL,
  `Stok` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `obat`
--

INSERT INTO `obat` (`ID_Obat`, `Nama_Obat`, `Jenis_Obat`, `Harga`, `Stok`) VALUES
('K0001', 'Proris Forte', 'Syrup', '75000', '143'),
('K0002', 'Mylanta 100 ml', 'Tablet', '20000', '191'),
('K0003', 'Bioplacenton', 'Salep', '30000', '200'),
('K0004', 'Biolysin kids', 'Syrup', '10000', '135'),
('K0005', 'CDR Fortos', 'Tablet', '28000', '330'),
('K0006', 'Combi KIds', 'Tablet', '22000', '150'),
('K0007', 'Curcuma Plus', 'Syrup', '18000', '170'),
('K0008', 'Dettol Soap', 'Liquid', '15000', '100'),
('K0009', 'Promag', 'Tablet', '500', '194'),
('K0010', 'Fair & Lovely', 'Cream', '25000', '180'),
('K0011', 'Oskadon', 'Tablet', '5000', '300'),
('K0012', 'remacyl', 'Tablet', '5000', '250'),
('K0013', 'Amoxicillin', 'Tablet', '50000', '80'),
('K0014', 'CTM', 'Tablet', '72000', '100'),
('K0015', 'Combantrin 125 mg', 'Tablet', '13000', '70'),
('K0016', 'Combantrin 250 mg', 'Tablet', '13000', '100'),
('K0017', 'Capivlex', 'Tablet', '44000', '180'),
('K0018', 'Cetahpil', 'Cream', '250000', '50'),
('K0019', 'Ibuprofen', 'Tablet', '300', '500'),
('K0020', 'Kalpanax', 'Cream', '2500', '300'),
('K0021', 'Voltaren 25 mg', 'Tablet', '5000', '200'),
('K0022', 'Vitamin C 1000', 'Tablet', '25000', '190'),
('K0023', 'Stimuno', 'Kapsul', '200000', '185');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pegawai`
--

CREATE TABLE `pegawai` (
  `ID_Pegawai` varchar(50) NOT NULL,
  `Nama_Pegawai` varchar(50) NOT NULL,
  `Jenis_Kelamin` varchar(50) NOT NULL,
  `No_Hp` varchar(50) NOT NULL,
  `Alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pegawai`
--

INSERT INTO `pegawai` (`ID_Pegawai`, `Nama_Pegawai`, `Jenis_Kelamin`, `No_Hp`, `Alamat`) VALUES
('H0001', 'Angelica', 'Perempuan', '085720208059', 'Bandung'),
('H0002', 'Galih Pratama', 'Laki-laki', '087372169892', 'Bandung'),
('H0003', 'Padila Hanizar', 'Perempuan', '089827836512', 'Bojong');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `ID_Pelanggan` varchar(50) NOT NULL,
  `Nama_Pelanggan` varchar(50) NOT NULL,
  `Jenis_Kelamin` varchar(50) NOT NULL,
  `Alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`ID_Pelanggan`, `Nama_Pelanggan`, `Jenis_Kelamin`, `Alamat`) VALUES
('P1120', 'Syahni Sahla', 'Perempuan', 'Bandung'),
('P1121', 'Windi Sri', 'Perempuan', 'Tasik'),
('P1122', 'Rizal Mauludin', 'Laki-laki', 'Bandung'),
('P1123', 'Syahira Putri', 'Perempuan', 'Cimahi'),
('P1124', 'Santi Latifah', 'Perempuan', 'Garut'),
('P1125', 'Inaya Nur', 'Perempuan', 'Bandung'),
('P1126', 'Yoga Syahputra', 'Laki-laki', 'Tasik'),
('P1127', 'Muhammad Radit', 'Laki-laki', 'Bandung'),
('P1128', 'Hilmansyah', 'Laki-laki', 'Bandung'),
('P1129', 'Angel Karamoy', 'Perempuan', 'amerika'),
('P1130', 'Maria Simarmata', 'Perempuan', 'yogyakarta'),
('P1131', 'Jonathan Christi', 'Laki-laki', 'tegalkuuk');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembelian`
--

CREATE TABLE `pembelian` (
  `No_Nota` varchar(50) NOT NULL,
  `Tanggal` date NOT NULL,
  `Nama_Pegawai` varchar(50) NOT NULL,
  `ID_Obat` varchar(50) NOT NULL,
  `Jumlah` int(50) NOT NULL,
  `Harga_Beli` int(50) NOT NULL,
  `Harga_Jual` int(11) NOT NULL,
  `Total_bayar` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Trigger `pembelian`
--
DELIMITER $$
CREATE TRIGGER `penambahanStok` AFTER INSERT ON `pembelian` FOR EACH ROW BEGIN
UPDATE obat SET Stok = Stok+new.Jumlah WHERE ID_Obat = new.ID_Obat;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE `penjualan` (
  `ID_Penjualan` varchar(50) NOT NULL,
  `Tanggal` date NOT NULL,
  `Pelanggan` varchar(50) NOT NULL,
  `ID_Obat` varchar(50) NOT NULL,
  `Harga` varchar(50) NOT NULL,
  `Qty` varchar(50) NOT NULL,
  `Total` varchar(50) NOT NULL,
  `Bayar` varchar(50) NOT NULL,
  `Kembali` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `penjualan`
--

INSERT INTO `penjualan` (`ID_Penjualan`, `Tanggal`, `Pelanggan`, `ID_Obat`, `Harga`, `Qty`, `Total`, `Bayar`, `Kembali`) VALUES
('21052100001', '2021-05-21', 'Syahni', '', '', '', '10000', '10000', '0'),
('21052100002', '2021-05-21', 'Windi', 'K0006', '22000', '1', '22000', '25000', '3000'),
('21052200001', '2021-05-22', ' Rizal Mauludin', 'K0005', '28000', '1', '28000', '30000', '2000'),
('21052200002', '2021-05-22', 'Windi', 'K0006', '22000', '1', '22000', '22000', 'press Enter'),
('21052200003', '2021-05-22', 'Hilman', 'K0011', '5000', '2', '10000', '10000', '0'),
('21052200004', '2021-05-22', 'Santi', 'K0008', '15000', '1', '15000', '15000', '0'),
('21052300001', '2021-05-23', 'simarmata', 'K0002', '20000', '2', '40000', '50000', '10000'),
('21052300002', '2021-05-23', 'jonathan', 'K0002', '20000', '3', '60000', '80000', '20000');

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'admin', 'admin', 'Pegawai'),
(2, 'pelanggan', 'pelanggan', 'Pasien');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `jual`
--
ALTER TABLE `jual`
  ADD PRIMARY KEY (`Time`);

--
-- Indeks untuk tabel `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`ID_Obat`),
  ADD UNIQUE KEY `ID_Obat` (`ID_Obat`);

--
-- Indeks untuk tabel `pegawai`
--
ALTER TABLE `pegawai`
  ADD PRIMARY KEY (`ID_Pegawai`);

--
-- Indeks untuk tabel `pelanggan`
--
ALTER TABLE `pelanggan`
  ADD PRIMARY KEY (`ID_Pelanggan`),
  ADD UNIQUE KEY `ID_Pelanggan` (`ID_Pelanggan`);

--
-- Indeks untuk tabel `pembelian`
--
ALTER TABLE `pembelian`
  ADD PRIMARY KEY (`No_Nota`),
  ADD UNIQUE KEY `No_Nota` (`No_Nota`);

--
-- Indeks untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD UNIQUE KEY `ID_Penjualan` (`ID_Penjualan`);

--
-- Indeks untuk tabel `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
