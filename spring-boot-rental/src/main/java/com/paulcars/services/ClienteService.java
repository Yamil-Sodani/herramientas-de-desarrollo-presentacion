package com.paulcars.services;

import com.paulcars.models.Cliente;
import com.paulcars.repositories.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {
    private final ClienteRepository clienteRepository;

    public List<Cliente> getAllClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> getClienteById(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public void deleteCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Cliente updateCliente(Integer id, Cliente cliente) {
        Optional<Cliente> existing = clienteRepository.findById(id);
        if (existing.isPresent()) {
            Cliente c = existing.get();
            c.setTipoDocumento(cliente.getTipoDocumento());
            c.setNumeroDocumento(cliente.getNumeroDocumento());
            c.setNombres(cliente.getNombres());
            c.setApellidos(cliente.getApellidos());
            c.setEmail(cliente.getEmail());
            c.setTelefono(cliente.getTelefono());
            return clienteRepository.save(c);
        }
        return null;
    }

    public Cliente findByNumeroDocumento(String numeroDocumento) {
        return clienteRepository.findByNumeroDocumento(numeroDocumento);
    }
}
