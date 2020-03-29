package com.todolist.app;

import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
class AppApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void todo() throws Exception {
		final JSONObject newTodo = new JSONObject();
		newTodo.put("name", "learn java");

		this.mockMvc
				.perform(MockMvcRequestBuilders.post("/todos").contentType(MediaType.APPLICATION_JSON)
						.content(newTodo.toString()))
				.andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("learn java"));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/todos")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
				.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)));

		this.mockMvc.perform(MockMvcRequestBuilders.get("/todos/0")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(0))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("learn java"));

		final JSONObject updateTodo = new JSONObject();
		updateTodo.put("name", "learn golang");

		this.mockMvc.perform(MockMvcRequestBuilders.put("/todos/0").contentType(MediaType.APPLICATION_JSON)
				.content(updateTodo.toString())).andExpect(MockMvcResultMatchers.status().isNoContent());

		this.mockMvc.perform(MockMvcRequestBuilders.get("/todos/0")).andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(MockMvcResultMatchers.jsonPath("$.name").value("learn golang"));

		this.mockMvc.perform(MockMvcRequestBuilders.delete("/todos/0"))
				.andExpect(MockMvcResultMatchers.status().isNoContent());

		this.mockMvc.perform(MockMvcRequestBuilders.get("/todos/0"))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
	}
}
