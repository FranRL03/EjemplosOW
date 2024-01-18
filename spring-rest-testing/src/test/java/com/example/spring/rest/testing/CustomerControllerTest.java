package com.example.spring.rest.testing;

import com.example.spring.rest.testing.customer.BadRequestException;
import com.example.spring.rest.testing.customer.Customer;
import com.example.spring.rest.testing.customer.CustomerController;
import com.example.spring.rest.testing.customer.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

//    hace peticiones a la API REST
    @Autowired
    MockMvc mvc;

//    pasa el formato json a formato java
    @Autowired
    ObjectMapper mapper;

//    esta anotacion marca que es una dependencia
    @MockBean
    CustomerRepository repository;

    @Test
    void findAllSuccess() throws Exception {

        List<Customer> customers = List.of(
                new Customer(1L, "customer1", 30),
                new Customer(2L, "customer2", 40),
                new Customer(3L, "customer3", 50)
        );

        when(repository.findAll()).thenReturn(customers);

        mvc.perform(get("/api/customers").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                //devuelva 3
                .andExpect(jsonPath("$", hasSize(3)))
                //el que este en la posicion 3 de la lista tenga el nombre customer3
                .andExpect(jsonPath("$[2].name", is("customer3")));

    }

    /*
    Como el findAll tiene tmb en el controlador el NotFound hacemos un test que cubra
    esa parte devolviendo una lista vacía, entonces si ejecutamos el test a nivel de clase
    con covertura (with Coverege) ya estaríamos cubriendo los dos casos.
     */
    @Test
    void findAllNotFound() throws Exception {

        List<Customer> customers = List.of();

        when(repository.findAll()).thenReturn(customers);

        mvc.perform(get("/api/customers").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());

    }

    @Test
    void findByIdOk() throws Exception {
        Customer customer = new Customer(1L, "customer1", 35);
        when(repository.findById(1L)).thenReturn(Optional.of(customer));

        mvc.perform(get("/api/customers/1").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                //no devuelva nulo
                .andExpect(jsonPath("$", notNullValue()))
                //el nombre sea customer1
                .andExpect(jsonPath("$.name", is("customer1")))
                //que la edad sea 35
                .andExpect(jsonPath("$.age", is(35)));

    }

    @Test
    void findByIdNotFound() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        mvc.perform(get("/api/customers/1").contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void createOk() throws Exception {

        //el cliente que enviamos para crear
        Customer customer = new Customer(null, "customer1", 35);
        //el cliente que se deberia de guardar en la base de datos
        Customer customerDB = new Customer(1L, "customer1", 35);
        //que debe guardar cualquier cosa y debe de devolver el cliente
        when(repository.save(any())).thenReturn(customerDB);

        mvc.perform(post("/api/customers")
                //al crear el cliente enviamos un json
                        .contentType(MediaType.APPLICATION_JSON)
                //aceptamos el json
                        .accept(MediaType.APPLICATION_JSON)
                //mapeamos el json a formato java con el ObjectMapped
                        .content(this.mapper.writeValueAsString(customer))
                )
                .andExpect(status().isOk())
                //no devuelva nulo
                .andExpect(jsonPath("$", notNullValue()))
                //el id sea 1
                .andExpect(jsonPath("$.id", is(1)))
                //el nombre sea customer1
                .andExpect(jsonPath("$.name", is("customer1")))
                //que la edad sea 35
                .andExpect(jsonPath("$.age", is(35)));
    }

    @Test
    void createBadRequest() throws Exception {

        //el cliente que enviamos para crear y de fallo le añadimos el id
        Customer customer = new Customer(1L, "customer1", 35);
        Customer customerDB = new Customer(1L, "customer1", 35);

        mvc.perform(post("/api/customers")
                        //al crear el cliente enviamos un json
                        .contentType(MediaType.APPLICATION_JSON)
                        //aceptamos el json
                        .accept(MediaType.APPLICATION_JSON)
                        //mapeamos el json a formato java con el ObjectMapped
                        .content(this.mapper.writeValueAsString(customer))
                )
                .andExpect(status().isBadRequest());

    }

    @Test
    void UpdateOk() throws Exception {

        //el cliente que vamos a editar
        Customer customer= new Customer(1L, "customer1", 35);
        //el cliente ya editado
        Customer customerUpdated = new Customer(1L, "customer1 edited", 35);

        when(repository.findById(1L)).thenReturn(Optional.of(customer));
        //que debe guardar cualquier cosa y debe de devolver el cliente
        when(repository.save(any())).thenReturn(customerUpdated);

        mvc.perform(put("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(customer))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", notNullValue()))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("customer1 edited")))
                .andExpect(jsonPath("$.age", is(35)));
    }

    @Test
    void UpdateBadRequestException() throws Exception {

        //el cliente que enviamos para editar le ponemos id null
        Customer customer = new Customer(null, "customer1", 35);

        mvc.perform(post("/api/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(this.mapper.writeValueAsString(customer))
                )
                .andExpect(status().isBadRequest())
                //aseguramos de que envie la excepcion
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof BadRequestException))
                //comprobamos que el mensaje que envia la excepcion sea ese
                .andExpect(result -> assertEquals("id customer con not be null", result.getResolvedException().getMessage()));

    }

}
