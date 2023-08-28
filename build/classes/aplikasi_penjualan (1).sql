-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 05 Bulan Mei 2021 pada 15.31
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
-- Struktur dari tabel `obat`
--

CREATE TABLE `obat` (
  `ID_Obat` int(50) NOT NULL,
  `Nama_Obat` varchar(50) NOT NULL,
  `Jenis_Obat` varchar(50) NOT NULL,
  `Harga` varchar(50) NOT NULL,
  `Stok` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `obat`
--

INSERT INTO `obat` (`ID_Obat`, `Nama_Obat`, `Jenis_Obat`, `Harga`, `Stok`) VALUES
(2021050101, 'Proris Forte', 'Syrup', 'Rp. 75000', '150');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pelanggan`
--

CREATE TABLE `pelanggan` (
  `ID_Pelanggan` int(50) NOT NULL,
  `Nama_Pelanggan` varchar(50) NOT NULL,
  `Jenis_Kelamin` varchar(50) NOT NULL,
  `Alamat` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `pelanggan`
--

INSERT INTO `pelanggan` (`ID_Pelanggan`, `Nama_Pelanggan`, `Jenis_Kelamin`, `Alamat`) VALUES
(112, 'syahni sahla', 'Perempuan', 'bandung');

-- --------------------------------------------------------

--
-- Struktur dari tabel `pembelian`
--

CREATE TABLE `pembelian` (
  `No_Nota` varchar(50) NOT NULL,
  `Tanggal` date NOT NULL,
  `Nama_Pegawai` varchar(50) NOT NULL,
  `Nama _Supplier` varchar(50) NOT NULL,
  `ID_Obat` varchar(50) NOT NULL,
  `Jumlah` varchar(50) NOT NULL,
  `Harga` varchar(50) NOT NULL,
  `Total_bayar` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `penjualan`
--

CREATE TABLE `penjualan` (
  `No_Fak` varchar(50) NOT NULL,
  `Tanggal` date NOT NULL,
  `ID_Obat` varchar(50) NOT NULL,
  `Jumlah` varchar(50) NOT NULL,
  `Harga` varchar(50) NOT NULL,
  `Total_bayar` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Struktur dari tabel `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `nama_lengkap` varchar(100) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `role` varchar(20) NOT NULL,
  `no_telp` varchar(15) NOT NULL,
  `alamat` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data untuk tabel `user`
--

INSERT INTO `user` (`id_user`, `nama_lengkap`, `username`, `password`, `role`, `no_telp`, `alamat`) VALUES
(1, 'Syahni Sahla Fatimah', 'syahnisahla', 'syahnisahla', 'Pegawai', '085156916974', 'Bandung'),
(2, 'Muhammad Rafli Jatnika', 'raflijatnika', 'raflijatnika', 'Pegawai', '087851885448', 'Bandung'),
(3, 'Nur Jamilah', 'nurjamilah', 'nurjamilah', 'Pegawai', '081275327826', 'Garut'),
(4, 'Wisnu Wardana', 'wisnuwardana', 'wisnuwardana', 'Pegawai', '083267167313', 'Tasikmalaya'),
(5, 'Khoirunnisa', 'khoirunnisa', 'khoirunnisa', 'Pasien', '08193286327', 'Bandung'),
(6, 'Puspitasari', 'puspitasari', 'puspitasari', 'Pasien', '087323665276', 'Bandung'),
(7, 'Amrillah', 'amrillah', 'amrillah', 'Pasien', '085612782827', 'Bandung'),
(8, 'Stefanus', 'stefanus', 'stefanus', 'Pasien', '081761328727', 'Bandung');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `obat`
--
ALTER TABLE `obat`
  ADD PRIMARY KEY (`ID_Obat`),
  ADD UNIQUE KEY `ID_Obat` (`ID_Obat`);

--
-- Indeks untuk tabel `penjualan`
--
ALTER TABLE `penjualan`
  ADD PRIMARY KEY (`ID_Obat`);

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
