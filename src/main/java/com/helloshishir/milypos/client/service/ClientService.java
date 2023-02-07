package com.helloshishir.milypos.client.service;

import com.helloshishir.milypos.client.model.Client;
import com.helloshishir.milypos.client.repository.ClientRepository;
import com.helloshishir.milypos.util.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    CriteriaRepository criteriaRepository;

    public Page<Client> getClient(int pageNumber, int pageSize, String sortDirection, String sortBy, String searchQuery) {
        List<Client> clientList = criteriaRepository.findAllWithFilter(pageNumber, pageSize, sortDirection, sortBy, searchQuery, Client.class);
        Long resultCount = criteriaRepository.getResultCount(searchQuery, Client.class);
        Pageable pageable = criteriaRepository.getPageable(pageNumber, pageSize, sortDirection, sortBy);
        return new PageImpl<>(clientList, pageable, resultCount);
    }

    public Client findById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public void delete(Integer id) {
        Client client = findById(id);
        clientRepository.delete(client);
    }
}
