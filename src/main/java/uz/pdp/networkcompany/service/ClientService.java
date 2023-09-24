package uz.pdp.networkcompany.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uz.pdp.networkcompany.dto.request.user.ClientRequest;
import uz.pdp.networkcompany.dto.view.client.ClientView;
import uz.pdp.networkcompany.entity.Client;

public interface ClientService {
    Page<ClientView> getAll(Pageable pageable);

    ClientView getById(Long id);

    ClientView create(ClientRequest request);

    ClientView update(ClientRequest request, Long id);

    Client save(Client client);

    Client findById(Long id);

    void deleteById(Long id);
}
