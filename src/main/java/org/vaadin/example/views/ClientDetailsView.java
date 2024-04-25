package org.vaadin.example.views;

// Importe as classes necessárias
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import org.vaadin.example.services.ClientService;
import org.vaadin.example.model.Client;

// Certifique-se de que ClientDetailsView implemente RouterLayout
@Route("client-details")
public class ClientDetailsView extends VerticalLayout implements RouterLayout {

    private final ClientService clientService;
    private final Client client;
    private final Header header;

    // Adicione um construtor que aceita um cliente como parâmetro
    public ClientDetailsView(ClientService clientService, Client client) {
        this.clientService = clientService;
        this.client = client;
        this.header = new Header();

        // Inicialize e configure os componentes da visualização
        // Por exemplo:
        add(header);
        add(new H1("Client Details"));
        add(new Paragraph("Name: " + client.getName()));
        add(new Paragraph("Phone Number: " + client.getPhoneNumber()));
        add(new Paragraph("Notes: " + client.getNotes()));

        // Adicione um botão de voltar
        add(new RouterLink("Back", MainView.class));
    }

}
