package org.example.dao;

import org.example.Connector.DataSource;
import org.example.model.Pessoa;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private DataSource dataSource;

    public PessoaDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insert(Pessoa pessoa) throws SQLException {
        String SQL = "INSERT INTO Pessoa (name, cpf, balance, age) VALUES (?, ?, ?, ?)";
        try(PreparedStatement stmt = dataSource.getConnection().prepareStatement(SQL)) {
            stmt.setString(1, pessoa.getName());
            stmt.setString(2, pessoa.getCpf());
            stmt.setDouble(3, pessoa.getBalance());
            stmt.setInt(4, pessoa.getAge());
            stmt.execute();
        }catch (SQLException e) {
            System.out.println("Problema em adicionar ao banco de dados " + e);
        }catch (Exception e) {
            System.out.println("Um problema geral " + e);
        }
    }

    public void update(int id) throws SQLException {
        Pessoa pessoa = getPessoa(id);
        String SQL = "UPDATE pessoa SET name=?, cpf=?, balance=?, age=? WHERE id = ?";
        try(PreparedStatement stmt = dataSource.getConnection().prepareStatement(SQL)){
            stmt.setString(1, pessoa.getName());
            stmt.setString(2, pessoa.getCpf());
            stmt.setDouble(3, pessoa.getBalance());
            stmt.setInt(4, pessoa.getAge());
            stmt.setInt(5, id);
            stmt.execute();
        }catch (SQLException e) {
            System.out.println("Problem in add datas " + e);
        }catch (Exception e) {
            System.out.println("A general problem " + e);
        }
    }


    public List<Pessoa> listarPessoas() throws SQLException {
        String SQL = "SELECT * FROM pessoa";
        List<Pessoa> pessoas = new ArrayList<>();
        try(PreparedStatement stmt = dataSource.getConnection().prepareStatement(SQL)) {
            try(ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("name");
                    String cpf = rs.getString("cpf");
                    int age = rs.getInt("age");
                    double balance = rs.getDouble("balance");
                    Pessoa um = new Pessoa(name,cpf,age);
                    um.setBalance(balance);
                    pessoas.add(um);
                }
            }catch (SQLException e) {
                System.out.println("Problem to add data in data bank " + e);
            }catch (Exception e) {
                System.out.println("A general problem " + e);
            }
        }
        return pessoas;
    }

    public Pessoa getPessoa(int id) throws SQLException {
        String SQL = "SELECT * FROM pessoa WHERE id = ?";
        Pessoa pessoa = null;
        try(PreparedStatement stmt = dataSource.getConnection().prepareStatement(SQL)) {
            stmt.setInt(1, id);
            stmt.execute();
            try(ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String name = rs.getString("name");
                    String cpf = rs.getString("cpf");
                    int age = rs.getInt("age");
                    double balance = rs.getDouble("balance");
                    pessoa = new Pessoa(name,cpf,age);
                    pessoa.setBalance(balance);
                }
            }catch (SQLException e) {
                System.out.println("Problem to add data in data bank " + e);
            }catch (Exception e) {
                System.out.println("A general problem " + e);
            }

        }
        return pessoa;
    }

    public boolean deletPessoa(int id) throws SQLException {
        String SQL = "DELETE FROM pessoa WHERE id = ?";
        try(PreparedStatement stmt = dataSource.getConnection().prepareStatement(SQL)) {
            stmt.setInt(1, id);
            stmt.execute();
            return true;
        }catch (SQLException e) {
            System.out.println(e);
            System.out.println("erro");
            return false;
        }catch (Exception e) {
            System.out.println(e);
            System.out.println("error");
            return false;
        }
    }


}

