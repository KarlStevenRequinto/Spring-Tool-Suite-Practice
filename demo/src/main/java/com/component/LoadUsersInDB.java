package com.component;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.UserRepository;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user1 = new User("Dendi",UUID.randomUUID().toString(), "Dendi", "Ishutin", "Ukrain", 32);
		User user2 = new User("Topson",UUID.randomUUID().toString(), "Topias Miikka", "Taavitsainen ", "Finland", 23);
		User user3 = new User("Ana",UUID.randomUUID().toString(), "Anathan", "Pham ", "Australia", 22);
		User user4 = new User("Jerax",UUID.randomUUID().toString(), "Jesse", "Vainikka ", "Finland", 29);
		User user5 = new User("Gabbi",UUID.randomUUID().toString(), "Kim", "Villafuerte", "Philippines", 23);
		User user6 = new User("Ceb",UUID.randomUUID().toString(), "Sébastien", "Debs", "France", 23);
		User user7 = new User("Miracle",UUID.randomUUID().toString(), "Amer", "Al-Barkawi", "Jordan", 24);
		User user8 = new User("Kuroky",UUID.randomUUID().toString(), "Kuro Salehi", "Takhasomi", "Germany", 29);
		User user9 = new User("Puppey",UUID.randomUUID().toString(), "Clement", "Ivanov", "Estonia", 31);
		User user10 = new User("BSJ",UUID.randomUUID().toString(), "Brian", "Canavan", "United States", 29);
		
		List<User> usersList = Arrays.asList(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10);
		
		usersList = usersList.stream().map(user -> {
			
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			return user;
		}).collect(Collectors.toList());
		
		userRepository.saveAll(usersList);
	}
	
}
