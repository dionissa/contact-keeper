package org.vaadin.example.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

public class Header extends HorizontalLayout {

    public Header() {
        // Adicione o título do aplicativo
        H1 title = new H1("Contact Keeper");

        // Botões de navegação
        Button homeButton = new Button("Home", e -> getUI().ifPresent(ui -> ui.navigate("")));
        Button addButton = new Button("Adicionar cliente", e -> getUI().ifPresent(ui -> ui.navigate("add-client")));

        // Configurar layout do cabeçalho
        setWidthFull();
        setDefaultVerticalComponentAlignment(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.BETWEEN);

        // Adicione os elementos ao layout do cabeçalho
        add(title, new HorizontalLayout(homeButton, addButton));

        // Alinhe o título à esquerda
        title.getStyle().set("margin-left", "10px");
    }
}
