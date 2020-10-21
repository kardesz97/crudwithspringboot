package com.demo;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	private final UserRepository repository;

	public UserController(UserRepository repository) {
		super();
		this.repository = repository;
	}

	@GetMapping("/users")
	List<User> all() {
		return repository.findAll();
	}

	@PostMapping("/users")
	User newUser(@RequestBody User newUser) {
		return repository.save(newUser);
	}

	@GetMapping("/users/{id}")
	User one(@PathVariable long id) {

		return repository.findById(id)
				.orElseThrow();
	}

	@PutMapping("/users/{id}")
	User replaceUser(@RequestBody User newUser, @PathVariable long id) {

		return repository.findById(id)
				.map(user -> {
					user.setUsername(newUser.getUsername());
					user.setFirstName(newUser.getFirstName());
					user.setLastName(newUser.getLastName());
					user.setAge(newUser.getAge());
					user.setPlaceOfBirth(newUser.getPlaceOfBirth());
					user.setDateOfBirth(newUser.getDateOfBirth());
					user.setGender(newUser.getGender());
					user.setProfession(newUser.getProfession());
					user.setEmail(newUser.getEmail());
					return repository.save(user);
				})
				.orElseGet(() -> {
					newUser.setId(id);
					return repository.save(newUser);
				});
	}

	@DeleteMapping("/users/{id}")
	void deleteUser(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
