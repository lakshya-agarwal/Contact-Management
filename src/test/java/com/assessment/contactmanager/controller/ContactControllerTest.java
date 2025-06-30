package com.assessment.contactmanager.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;

import com.assessment.contactmanager.dto.ContactDTO;
import com.assessment.contactmanager.service.ContactService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ContactController.class)
class ContactControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ContactService contactService;

	@Autowired
	private ObjectMapper objectMapper;

	private ContactDTO sampleContact() {
		ContactDTO dto = new ContactDTO();
		dto.setName("Lakshya");
		dto.setEmail("lakshya@example.com");
		dto.setPhone("9876543210");
		return dto;
	}

	@Test
	@WithMockUser
	void shouldCreateContact() throws Exception {
		when(contactService.save(any())).thenReturn(sampleContact());

		mockMvc.perform(post("/contacts").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(sampleContact()))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.name").value("Lakshya"));
	}

	@Test
	@WithMockUser
	void shouldUpdateContact() throws Exception {
		when(contactService.update(Mockito.eq(1L), any())).thenReturn(sampleContact());

		mockMvc.perform(put("/contacts/1").with(csrf()).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(sampleContact()))).andExpect(status().isOk())
				.andExpect(jsonPath("$.email").value("lakshya@example.com"));
	}

	@Test
	@WithMockUser
	void shouldGetContactById() throws Exception {
		when(contactService.get(1L)).thenReturn(sampleContact());

		mockMvc.perform(get("/contacts/1")).andExpect(status().isOk())
				.andExpect(jsonPath("$.phone").value("9876543210"));
	}

	@Test
	@WithMockUser
	void shouldGetAllContacts() throws Exception {
		when(contactService.getAll()).thenReturn(List.of(sampleContact()));

		mockMvc.perform(get("/contacts")).andExpect(status().isOk()).andExpect(jsonPath("$.size()").value(1));
	}

	@Test
	@WithMockUser
	void shouldDeleteContact() throws Exception {
		mockMvc.perform(delete("/contacts/1").with(csrf()))

				.andExpect(status().isNoContent());
	}

	@Test
	@WithMockUser(roles = "ADMIN")
	void shouldReturnContactCountForAdmin() throws Exception {
		when(contactService.countContacts()).thenReturn(10L);

		mockMvc.perform(get("/contacts/count")).andExpect(status().isOk())
				.andExpect(jsonPath("$.totalContacts").value(10));
	}


}
