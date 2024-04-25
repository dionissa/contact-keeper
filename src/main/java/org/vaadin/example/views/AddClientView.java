package org.vaadin.example.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

import org.vaadin.example.model.Client;
import org.vaadin.example.services.ClientService;

@Route("add-client")
public class AddClientView extends VerticalLayout {

    private final ClientService clientService;
    private final TextField nameField;
    private final TextField phoneNumberField;
    private final TextArea notesField;
    private final Header header;

    public AddClientView(ClientService clientService) {
        this.clientService = clientService;
        this.header = new Header();

        // Layout centralizado para os campos do formulário
        FormLayout formLayout = new FormLayout();

        // Campos do formulário
        nameField = new TextField("Nome");
        phoneNumberField = new TextField("Telefone");
        notesField = new TextArea("Informações Adicionais");

        // Botões
        Button addButton = new Button("Adicionar",
                event -> addClient(nameField.getValue(), phoneNumberField.getValue(), notesField.getValue()));
        Button backButton = new Button("Voltar", event -> getUI().ifPresent(ui -> ui.navigate("")));

        // Adiciona os campos e botões ao layout do formulário
        formLayout.add(nameField, phoneNumberField, notesField);

        // Layout para os botões
        HorizontalLayout buttonLayout = new HorizontalLayout(addButton, backButton);

        // Adiciona o formulário e os botões ao layout principal
        add(header, formLayout, buttonLayout);
    }

    private void addClient(String name, String phoneNumber, String notes) {
        if (name.isEmpty() || phoneNumber.isEmpty()) {
            Notification.show("Nome e telefone são obrigatórios");
            return;
        }

        Client client = new Client();
        client.setName(name);
        client.setPhoneNumber(phoneNumber);
        client.setNotes(notes);

        clientService.addClient(client);

        Notification.show("Cliente adicionado com sucesso");

        // Limpar campos do formulário após adicionar com sucesso
        nameField.clear();
        phoneNumberField.clear();
        notesField.clear();
    }
}