package org.example.initializer;

import org.example.Connector.DataSource;
import org.example.Service.Service;
import org.example.Service.ServiceAdm;
import org.example.dao.PessoaDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class Initializer{
    Service service;

    public Initializer(Service service) {
        this.service = service;
    }

    public void inicializer()  throws SQLException {
        try(Scanner input = new Scanner(System.in)) {
            int id = id();
            boolean a = true;
            String choice;
            while (a) {
                System.out.println(
                        "1 - Create your account \n" +
                        "2 - Withdraw your money \n" +
                        "3 - Updade your account \n" +
                        "4 - Transfer money to someone \n" +
                        "5 - Delete your account \n" +
                        "else - exit");
                if (input.hasNextLine()) {
                    choice = input.nextLine();
                    if (choice.equals("1")) {
                        service.insert();
                        break;
                    } else if (choice.equals("2")) {
                        service.withdrawMoney(id);
                        break;
                    } else if (choice.equals("3")) {
                        service.update(id);
                        break;
                    } else if (choice.equals("4")) {
                        service.transferMoney(id);
                        break;
                    } else if (choice.equals("5")) {
                        service.delete(id);
                        break;
                    } else {
                        System.out.println("turning off...");
                        a = false;
                    }
                }

            }
        }catch (SQLException e) {
            System.out.println("error to connect in SQL");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public int id() {
        Scanner input = new Scanner(System.in);
        System.out.println("what is your id?: ");
        int id = input.nextInt();
        return id;
    }

}
