package com.example.projeto2.services;

import com.example.projeto2.models.Eventos;
import com.example.projeto2.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("eventosService")
public class eventos {
    @Autowired
    private EventoRepository rep;


    public List<Eventos> getAllEventos() {
        List<Eventos> eventos = rep.findAll();
        return eventos;
    }

    public List<Eventos> getAllEventosOrganizador() {
        List<Eventos> eventos = rep.findAll();
        return eventos;
    }

    public List<Eventos> getListaEventos() {
        List<Eventos> eventos = rep.findAll();
        return eventos;
    }


    public void save(Eventos evento) {
        rep.save(evento);
    }

    public Eventos getUserById(Integer id_evento) throws EventoNotFoundException {
        Optional<Eventos> result = rep.findById(id_evento);
        if(result.isPresent()){
            return result.get();
        }
        throw new EventoNotFoundException("Não se encontra nenhum evento com esse id " + id_evento);
    }

    public Eventos getEventoById(Integer id_evento) throws EventoNotFoundException {
        Optional<Eventos> result = rep.findById(id_evento);
        if(result.isPresent()){
            return result.get();
        }
        throw new EventoNotFoundException("Não se encontra nenhum evento com esse id " + id_evento);
    }
//    public List<Bilhetes> getBilhetesByEventoId(Integer id_evento) {
//        Eventos evento = rep.findById(id_evento).orElse(null);
//        if (evento != null) {
//            return BilhetesRepository.findByEvento(evento);
//        }
//        return null;
//    }

    public List<Eventos> getByKeyword(String keyword){
        return rep.findByKeyword(keyword);
    }

}
