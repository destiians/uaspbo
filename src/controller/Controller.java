/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import database.Database;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Mahasiswa;
import model.Transaksi;

/**
 *
 * @author user
 */
public class Controller {
    private Database database;

    public Controller() {
        this.database = new Database();
    }

    public String authenticate(String username, String password) {
        String sql = "SELECT id FROM user WHERE username = ? AND password = ?";
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return rs.getString("id"); // Return the ID if user found
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    public List<Transaksi> getAllTransaksi() {
        List<Transaksi> transaksiList = new ArrayList<>();
        String sql = "SELECT * FROM Transaksi";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(rs.getInt("idTransaksi"));
                transaksi.setIdUser(rs.getString("idUser"));
                transaksi.setTimestamp(rs.getString("tanggal"));
                transaksi.setPemasukan(rs.getDouble("pemasukan"));
                transaksi.setPengeluaran(rs.getDouble("pengeluaran"));
                transaksi.setSaldo(rs.getDouble("saldo"));
                transaksi.setKeterangan(rs.getString("keterangan"));
                transaksiList.add(transaksi);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return transaksiList;
    }
    
    public Mahasiswa cariByNim(String nim) {
        String query = "SELECT * FROM mahasiswa WHERE nim = ?";
        try (Connection conn = database.connect();
                PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, nim);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(rs.getString("nim"));
                mahasiswa.setNama(rs.getString("nama"));
                mahasiswa.setKasSep(rs.getString("kasSep"));
                mahasiswa.setKasOkt(rs.getString("kasOkt"));
                mahasiswa.setKasNov(rs.getString("kasNov"));
                mahasiswa.setKasDes(rs.getString("kasDes"));
                mahasiswa.setKasFeb(rs.getString("kasFeb"));
                mahasiswa.setKasMar(rs.getString("kasMar"));
                mahasiswa.setKasApr(rs.getString("kasApr"));
                mahasiswa.setKasMei(rs.getString("kasMei"));
                mahasiswa.setKasJun(rs.getString("kasJun"));
                mahasiswa.setKasJul(rs.getString("kasJul"));
                return mahasiswa;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null jika tidak ditemukan
    }
    
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        String sql = "SELECT * FROM Mahasiswa";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(rs.getString("nim"));
                mahasiswa.setNama(rs.getString("nama"));
                mahasiswa.setKasSep(rs.getString("kasSep"));
                mahasiswa.setKasOkt(rs.getString("kasOkt"));
                mahasiswa.setKasNov(rs.getString("kasNov"));
                mahasiswa.setKasDes(rs.getString("kasDes"));
                mahasiswa.setKasFeb(rs.getString("kasFeb"));
                mahasiswa.setKasMar(rs.getString("kasMar"));
                mahasiswa.setKasApr(rs.getString("kasApr"));
                mahasiswa.setKasMei(rs.getString("kasMei"));
                mahasiswa.setKasJun(rs.getString("kasJun"));
                mahasiswa.setKasJul(rs.getString("kasJul"));
                mahasiswaList.add(mahasiswa);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return mahasiswaList;
    }
    
    public double getSaldoTerakhir() {
        double saldo = 0.0;
        String sql = "SELECT saldo FROM Transaksi ORDER BY idTransaksi DESC LIMIT 1";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            if (rs.next()) {
                saldo = rs.getDouble("saldo");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return saldo;
    }

    public boolean simpanTransaksi(String idUser, String jenisTransaksi, String tanggal, double jumlah, String keterangan) {
        String sql = "INSERT INTO Transaksi (idUser, tanggal, pemasukan, pengeluaran, saldo, keterangan) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            double pemasukan = jenisTransaksi.equals("Pemasukan") ? jumlah : 0;
            double pengeluaran = jenisTransaksi.equals("Pengeluaran") ? jumlah : 0;
            
            // Mendapatkan saldo terakhir
            double saldoSebelumnya = getSaldoTerakhir();
            double saldo = saldoSebelumnya + pemasukan - pengeluaran;
            
            pstmt.setString(1, idUser);
            pstmt.setString(2, tanggal);
            pstmt.setDouble(3, pemasukan);
            pstmt.setDouble(4, pengeluaran);
            pstmt.setDouble(5, saldo);
            pstmt.setString(6, keterangan);
            
            int rowsInserted = pstmt.executeUpdate();
            
            return rowsInserted > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public void updateDataMahasiswa(String idUser, String tanggal, int jumlah) {
      
        StringBuilder sql = new StringBuilder("UPDATE mahasiswa SET ");
        String[] kolomKas = {"kasSep", "kasOkt", "kasNov", "kasDes", "kasFeb", "kasMar", "kasApr", "kasMei", "kasJun", "kasJul"};
        int kolomCounter = 0;
        for (String kolom : kolomKas) {
            if (kolomCounter < jumlah) {
                if (kolomCounter > 0) {
                    sql.append(", ");
                }
                sql.append(kolom).append(" = CASE WHEN ").append(kolom).append(" IS NULL OR ").append(kolom).append(" = '' THEN ? ELSE ").append(kolom).append(" END");
                kolomCounter++;
            } else {
                break;
            }
        }       
        sql.append(" WHERE nim = ?");      
        
        try (Connection conn = database.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
                for (int i = 1; i <= jumlah; i++) {
                    pstmt.setString(i, tanggal);
                }

                pstmt.setString(jumlah + 1, idUser);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Data mahasiswa berhasil diupdate.");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void exportMahasiswaToCSV(String filePath) {
        String sql = "SELECT * FROM Mahasiswa";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            // Header untuk file CSV
            writer.write("NIM,Nama,KasSep,KasOkt,KasNov,KasDes,KasFeb,KasMar,KasApr,KasMei,KasJun,KasJul\n");

            // Data dari database
            while (rs.next()) {
                Mahasiswa mahasiswa = new Mahasiswa();
                mahasiswa.setNim(rs.getString("nim"));
                mahasiswa.setNama(rs.getString("nama"));
                mahasiswa.setKasSep(rs.getString("kasSep"));
                mahasiswa.setKasOkt(rs.getString("kasOkt"));
                mahasiswa.setKasNov(rs.getString("kasNov"));
                mahasiswa.setKasDes(rs.getString("kasDes"));
                mahasiswa.setKasFeb(rs.getString("kasFeb"));
                mahasiswa.setKasMar(rs.getString("kasMar"));
                mahasiswa.setKasApr(rs.getString("kasApr"));
                mahasiswa.setKasMei(rs.getString("kasMei"));
                mahasiswa.setKasJun(rs.getString("kasJun"));
                mahasiswa.setKasJul(rs.getString("kasJul"));

                // Data baris untuk CSV
                String line = String.join(",", mahasiswa.getNim(), mahasiswa.getNama(), mahasiswa.getKasSep(),
                        mahasiswa.getKasOkt(), mahasiswa.getKasNov(), mahasiswa.getKasDes(), mahasiswa.getKasFeb(),
                        mahasiswa.getKasMar(), mahasiswa.getKasApr(), mahasiswa.getKasMei(), mahasiswa.getKasJun(),
                        mahasiswa.getKasJul());
                writer.write(line + "\n");
            }

            System.out.println("Data Mahasiswa berhasil diekspor ke " + filePath);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    
    public void exportPembukuanToCSV(String filePath) {
        String sql = "SELECT * FROM Transaksi";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery();
             BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            // Header untuk file CSV
            writer.write("ID Transaksi,ID User,Tanggal,Pemasukan,Pengeluaran,Saldo,Keterangan\n");

            // Data dari database
            while (rs.next()) {
                Transaksi transaksi = new Transaksi();
                transaksi.setIdTransaksi(rs.getInt("idTransaksi"));
                transaksi.setIdUser(rs.getString("idUser"));
                transaksi.setTimestamp(rs.getString("tanggal"));
                transaksi.setPemasukan(rs.getDouble("pemasukan"));
                transaksi.setPengeluaran(rs.getDouble("pengeluaran"));
                transaksi.setSaldo(rs.getDouble("saldo"));
                transaksi.setKeterangan(rs.getString("keterangan"));

                // Data baris untuk CSV
                String line = String.join(",", String.valueOf(transaksi.getIdTransaksi()), transaksi.getIdUser(),
                        transaksi.getTimestamp(), String.valueOf(transaksi.getPemasukan()),
                        String.valueOf(transaksi.getPengeluaran()), String.valueOf(transaksi.getSaldo()),
                        transaksi.getKeterangan());
                writer.write(line + "\n");
            }

            System.out.println("Data Mahasiswa berhasil diekspor ke " + filePath);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public void createNewUser(String nim, String username, String password) {
        String sql = "INSERT INTO User (id, username, password) VALUES (?, ?, ?)";
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nim);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addNewMahasiswa(String nim, String nama) {
        String sql = "INSERT INTO Mahasiswa (nim, nama) VALUES (?, ?)";
        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, nim);
            pstmt.setString(2, nama);
            
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
}    
