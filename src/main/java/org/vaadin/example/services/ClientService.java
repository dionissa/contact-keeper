package org.vaadin.example.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.vaadin.example.model.Client;
import org.vaadin.example.repository.ClientRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    public void deleteClient(Client client) {
        clientRepository.delete(client);
    }

    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findClientById(Long id) {
        return clientRepository.findById(id);
    }

    public List<Client> findClientsByName(String name) {
        return clientRepository.findByName(name);
    }

    public List<Client> filtrarClients(String termoBusca) {
        List<Client> clientsEncontrados = new ArrayList<>();

        // Filtrar termos de busca
        String[] termos = termoBusca.toLowerCase().split(" ");

        // Buscar clients no banco de dados
        List<Client> todosClients = clientRepository.findAll();

        for (Client client : todosClients) {
            String nomeClient = client.getName() != null ? client.getName().toLowerCase() : "";
            String telefoneClient = client.getPhoneNumber() != null ? client.getPhoneNumber().toLowerCase() : "";
            String descricaoClient = client.getNotes() != null ? client.getNotes().toLowerCase() : "";

            // Verificar se pelo menos um dos termos de busca está presente no nome,
            // telefone ou descrição do client
            boolean termoEncontrado = false;
            for (String termo : termos) {
                if (nomeClient.contains(termo) || telefoneClient.contains(termo) || descricaoClient.contains(termo)) {
                    termoEncontrado = true;
                    break;
                }
            }

            if (termoEncontrado) {
                clientsEncontrados.add(client);
            }
        }

        return clientsEncontrados;
    }
}
