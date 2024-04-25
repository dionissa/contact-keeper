package org.vaadin.example.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import org.vaadin.example.model.Client;
import org.vaadin.example.services.ClientService;

import java.util.List;

@Route("")
public class MainView extends VerticalLayout {

    private final ClientService clientService;
    private final Grid<Client> grid;
    private final TextField searchField;
    private final Header header;

    public MainView(ClientService clientService) {
        this.clientService = clientService;

        this.header = new Header();
        // Layout horizontal para os elementos de busca e botões
        HorizontalLayout searchLayout = new HorizontalLayout();

        // Campo de busca
        searchField = new TextField();
        searchField.setPlaceholder("Search");
        searchField.setValueChangeMode(ValueChangeMode.LAZY);

        // Botão de adicionar cliente
        Button addButton = new Button("Adicionar Cliente", e -> {
            UI.getCurrent().navigate(AddClientView.class);
        });

        // Adiciona os elementos ao layout horizontal
        searchLayout.add(searchField, addButton);

        // Cria um componente de tabela
        grid = new Grid<>();
        grid.addColumn(Client::getName).setHeader("Name");
        grid.addColumn(Client::getPhoneNumber).setHeader("Phone Number");
        grid.addColumn(Client::getNotes).setHeader("Notes");

        // Adiciona a tabela ao layout vertical
        add(header, searchLayout, grid);

        // Atualiza a tabela com todos os clientes inicialmente
        updateGrid();
    }

    private void updateGrid() {
        String searchTerm = searchField.getValue();
        List<Client> clients;

        // Se não houver termo de busca, exibe todos os clientes
        if (searchTerm.isEmpty()) {
            clients = clientService.findAllClients();
        } else {
            // Caso contrário, busca os clientes com base no termo de busca
            clients = clientService.filtrarClients(searchTerm);
        }

        // Atualiza os itens na tabela
        grid.setItems(clients);
    }
}
