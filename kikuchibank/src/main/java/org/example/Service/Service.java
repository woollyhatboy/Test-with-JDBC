package org.example.Service;

import org.example.Connector.DataSource;
import org.example.dao.PessoaDAO;
import org.example.model.Pessoa;
import java.sql.SQLException;
import java.util.Scanner;

public class Service {
    private PessoaDAO pessoaDAO;

    public Service(PessoaDAO pessoaDAO) {
        this.pessoaDAO =pessoaDAO;
    }

    public void insert() throws SQLException {
        String nome;
        String cpf;
        int idade;
        try( Scanner input = new Scanner(System.in)) {
            System.out.println("let started!");
            System.out.println("What is your, name?: ");
            nome = input.nextLine();
            System.out.println("Now your cpf please: ");
            cpf = input.nextLine();
            System.out.println("And finally your age, please");
            idade = input.nextInt();
            Pessoa pessoa = new Pessoa(nome,cpf,idade);
            pessoaDAO.insert(pessoa);
            System.out.println("Your account has been sucessfull created ! ");
        }catch (SQLException e) {
            System.out.println("Error to connect in data bank");
        }catch (Exception e) {
            System.out.println("A general error");
        }
    }

    public void withdrawMoney(int id) throws SQLException {
        Pessoa pessoa = pessoaDAO.getPessoa(id);
        try( Scanner input = new Scanner(System.in)) {
            System.out.println("How much you want withdraw?: ");
            double valor = input.nextDouble();
            pessoa.setBalance(pessoa.getBalance() - valor);
            pessoaDAO.update(id);
            System.out.println("You withdraw: " + valor + " $");
            System.out.println("You have now: " + pessoa.getBalance() + " $");
        }catch (SQLException e) {
            System.out.println("Error to connect in data bank");
        }catch (Exception e) {
            System.out.println("A general error");
        }
    }

    public void update(int id) throws SQLException {
        Pessoa pessoa = pessoaDAO.getPessoa(id);
        try(Scanner input = new Scanner(System.in);) {
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

    public void delete(int id) throws SQLException {
        try {
            pessoaDAO.deletPessoa(id);
            System.out.println("user deleted");
        }catch (SQLException e) {
            System.out.println("Error to connect in data bank");
        }catch (Exception e) {
            System.out.println("A general error");
        }
    }

    public void transferMoney(int id) throws SQLException {
        Pessoa pessoa = pessoaDAO.getPessoa(id);
        int idTarget;
        try(Scanner input = new Scanner(System.in)) {
            System.out.println("How much you want transfer?: ");
            double valor = input.nextDouble();
            if (valor > pessoa.getBalance()) {
                System.out.println("you dont have this money to transfer");
            } else {
                pessoa.setBalance(pessoa.getBalance() - valor);
                pessoaDAO.update(id);
                System.out.println("write the id to this person receive the money?: ");
                idTarget = input.nextInt();
                Pessoa pessoaTarget = pessoaDAO.getPessoa(idTarget);
                pessoaTarget.setBalance(pessoaTarget.getBalance() + valor);
                pessoaDAO.update(idTarget);
                System.out.println("Money transferred!");
            }
        }catch (SQLException e) {
            System.out.println("Error to connect in data bank");
        }catch (Exception e) {
            System.out.println("A general error");
        }
    }
}
