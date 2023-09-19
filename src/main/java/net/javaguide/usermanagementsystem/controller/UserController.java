package net.javaguide.usermanagementsystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.javaguide.usermanagementsystem.exception.ResourceNotFoundException;
import net.javaguide.usermanagementsystem.model.UserModel;
import net.javaguide.usermanagementsystem.repository.UserRepository;

@RestController
@RequestMapping("/api/v1")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	// Get all users
	@GetMapping("/users")
	public List<UserModel> getAllUsers(){
		return this.userRepository.findAll();
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<UserModel> getUserById(@PathVariable(value = "id") long id)
	throws ResourceNotFoundException{
		UserModel userModel = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found for this id:: " + id));
		return ResponseEntity.ok().body(userModel);
	}
	
	@PostMapping("/user")
	public UserModel createUser(@RequestBody UserModel user) {
		return this.userRepository.save(user);
	}
	
	@PutMapping("/users/{id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable("id") long id, @RequestBody UserModel user) {
		Optional<UserModel> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			UserModel _user = userData.get();
			_user.setFirstName(user.getFirstName());
			_user.setLastName(user.getLastName());
			_user.setEmail(user.getEmail());
			_user.setPhoneNo(user.getPhoneNo());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/user/{id}")
	  void deleteEmployee(@PathVariable Long id) {
	    userRepository.deleteById(id);
	  }
}
