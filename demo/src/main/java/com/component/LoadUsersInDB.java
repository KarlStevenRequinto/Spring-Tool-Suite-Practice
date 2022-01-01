package com.component;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.model.User;
import com.repository.UserRepository;

@Component
@Transactional
public class LoadUsersInDB implements CommandLineRunner{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		User user = new User("Georgina", "Wilson", "Brazil", 25);
		userRepository.save(user);		
		
		user = new User("Yudapota", "Kamo", "Pelepens", 25);
		userRepository.save(user);
		
		user = new User("Balatabaya", "NyoGed", "China", 39);
		userRepository.save(user);
		
		user = new User("Lastnlng", "Gid ah", "Japan", 20);
		userRepository.save(user);
	}
	
}
