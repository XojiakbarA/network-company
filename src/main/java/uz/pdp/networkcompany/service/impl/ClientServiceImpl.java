package uz.pdp.networkcompany.service.impl;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import uz.pdp.networkcompany.dto.request.user.ClientRequest;
import uz.pdp.networkcompany.dto.view.client.ClientView;
import uz.pdp.networkcompany.entity.Client;
import uz.pdp.networkcompany.mapper.ClientMapper;
import uz.pdp.networkcompany.repository.ClientRepository;
import uz.pdp.networkcompany.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;
    private final String existsByUsername = "Client with username = %s already exists";
    private final String notFoundById = "Client with id = %d not found";

    @Override
    public Page<ClientView> getAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(c -> clientMapper.mapToClientView(c));
    }

    @Override
    public ClientView getById(Long id) {
        return clientMapper.mapToClientView(findById(id));
    }

    @Override
    public ClientView create(ClientRequest request) {
        if (clientRepository.existsByUsername(request.getUsername())) {
            throw new EntityExistsException(String.format(existsByUsername, request.getUsername()));
        }
        Client client = clientMapper.mapToClient(request);

        return clientMapper.mapToClientView(save(client));
    }

    @Override
    public ClientView update(ClientRequest request, Long id) {
        if (clientRepository.existsByUsernameAndIdNot(request.getUsername(), id)) {
            throw new EntityExistsException(String.format(existsByUsername, request.getUsername()));
        }
        Client client = findById(id);

        clientMapper.mapToClient(request, client);

        return clientMapper.mapToClientView(save(client));
    }

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException(String.format(notFoundById, id))
        );
    }

    @Override
    public void deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new EntityNotFoundException(String.format(notFoundById, id));
        }
        clientRepository.deleteById(id);
    }
}
