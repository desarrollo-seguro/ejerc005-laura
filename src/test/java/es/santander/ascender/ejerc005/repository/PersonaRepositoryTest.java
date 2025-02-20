package es.santander.ascender.ejerc005.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import es.santander.ascender.ejerc005.model.Persona;

@SpringBootTest
public class PersonaRepositoryTest {

    @Autowired
    private PersonaRepository repository;

    private Persona personaCreada;

    @BeforeEach
    public void setUp() {
        personaCreada = getPersonaYAlmacena();
    }

    private Persona getPersonaYAlmacena() {
        Persona persona = new Persona();
        persona.setNombre("Laura");
        persona.setApellido("Obregon");
        persona.setProvincia_id(39l);
        repository.save(persona);
        return persona;
    }

    @Test
    public void findById() {
        Persona personaEncontrada = repository
                .findById(personaCreada.getId())
                .get();
        assertEquals("Laura", personaEncontrada.getNombre());
    }

    @Test
    public void testFind() {
        List<Persona> personas = repository
                .findAll();
        assertTrue(personas.size() <= 1);

    }

    @Test
    public void testCreate() {
        assertTrue(
                repository
                        .findById(personaCreada.getId())
                        .isPresent());
    }

    @Test
    public void delete() {
        assertTrue(
                repository
                        .findById(personaCreada.getId())
                        .isPresent());
        repository.deleteById(personaCreada.getId());
    }

    @Test
    public void update() {
        assertTrue(
                repository
                        .existsById(personaCreada.getId()));
        personaCreada.setNombre("Paula");
        repository.save(personaCreada);
        Optional<Persona> updatedPersona = repository.findById(personaCreada.getId());
        assertTrue(updatedPersona.isPresent());
        assertTrue(updatedPersona.get().getNombre() == "Paula");
    }

}
