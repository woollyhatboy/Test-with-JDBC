package org.example;

import org.example.Connector.DataSource;
import org.example.Service.Service;
import org.example.Service.ServiceAdm;
import org.example.dao.PessoaDAO;
import org.example.initializer.Initializer;
import org.example.model.Pessoa;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        Initializer initializer = new Initializer(new Service(new PessoaDAO(new DataSource())));

        initializer.inicializer();






    }
}