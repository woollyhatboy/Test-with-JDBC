package org.example.Service;

import org.example.dao.PessoaDAO;
import org.example.model.Pessoa;

import java.sql.SQLException;
import java.util.Scanner;

public class ServiceAdm {
    PessoaDAO pessoaDAO;

    public ServiceAdm(PessoaDAO pessoaDAO) {
        this.pessoaDAO = pessoaDAO;
    }

    public void getPerson(int id) throws SQLException {
        try {
            System.out.println(pessoaDAO.getPessoa(id));
        }catch (SQLException e) {
            System.out.println("Error to connect in data bank");
        }catch (Exception e) {
            System.out.println("A general error");
        }
    }

    public void listUsers() throws SQLException {
        try {
            System.out.println(pessoaDAO.listarPessoas());
        }catch (SQLException e) {
            System.out.println("Error to connect in data bank");
        }catch (Exception e) {
            System.out.println("A general error");
        }
    }

    public void update() throws SQLException {
        try(Scanner input = new Scanner(System.in);) {
            System.out.println("which id you want correct?: ");
            int id = input.nextInt();
            Pessoa pessoa = pessoaDAO.getPessoa(id);
            System.out.println("which information you want correct?");
            System.out.println("1 - name");
            System.out.println("2 - cpf");
            System.out.println("3 - age");
            System.out.println("other number - exit");
            String choice = input.nextLine();
            if (choice.equals("1")) {
                System.out.println("what name you are goint to insert?: ");
                String nameChange = input.nextLine();
                pessoa.setName(nameChange);
                pessoaDAO.update(id);
            } else if (choice.equals("2")) {
                System.out.println("what cpf you are goint to insert?: ");
                String cpfChange = input.nextLine();
                pessoa.setCpf(cpfChange);
                pessoaDAO.update(id);
            } else if (choice.equals("3")) {
                System.out.println("what age you are now ?: ");
                int newAge = input.nextInt();
                pessoa.setAge(newAge);
                pessoaDAO.update(id);
            } else {
                System.out.println("exit...");
            }
            System.out.println("your information has been updated !");
        }catch (SQLException e) {
            System.out.println("Error to connect in data bank");
        }catch (Exception e) {
            System.out.println("A general error");
        }
    }


}
