package com.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.model.User;
import com.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	public static List<User> usersList = new ArrayList<>();
	
	private static Long COUNTER = (long) 11;
	
	static {
		User user = new User(COUNTER++, "Georgina", "Wilson", "Brazil", 25);
		
		usersList.add(user);
		user = new User(COUNTER++,"Yudapota", "Kamo", "Pelepens", 25);
		
		usersList.add(user);
		user = new User(COUNTER++,"Balatabaya", "NyoGed", "China", 39);
		
		usersList.add(user);
		user = new User(COUNTER++,"Lastnlng", "Gid ah", "Japan", 20);
	}
	
	@Override
	public List<User> findAll() {
		
		return usersList.stream().sorted(Comparator.comparing(User::getId)).collect(Collectors.toList()); 
	}

	@Override
	public Optional<User> findById(Long id) {
		return usersList.stream().filter(user -> user.getId() == id).findFirst();
	
		
		
	}

	@Override
	public void add(User user) {
		user.setId(COUNTER++);
		usersList.add(user);
		
	}

	@Override
	public Optional<User> delete(Long id) {
		Optional<User> userOpt = usersList.stream().filter(user -> user.getId() == id).findFirst();

		if (userOpt.isPresent()) {
			usersList = usersList.stream().filter(user -> userOpt.get().getId() != user.getId())
					.collect(Collectors.toList());
			return userOpt;
		}
		
		return Optional.empty();
	}

	@Override
	public Optional<User> update(User user) {
		Optional<User> userOpt = usersList.stream().filter(u -> u.getId() == user.getId()).findFirst();
		
		if (userOpt.isPresent() ) {
			User existingUser = userOpt.get();
			
			if (user.getFirstName() != null) {
				existingUser.setFirstName(user.getFirstName());
			}
			
			if (user.getLastName() != null) {
				existingUser.setLastName(user.getLastName());
			}
			
			if (user.getAge() != null) {
				existingUser.setAge(user.getAge());
			}
			
			if (user.getCountry() != null) {
				existingUser.setCountry(user.getCountry());
			}
			
			usersList.stream().filter(u -> u.getId() != existingUser.getId()).collect(Collectors.toList());
			usersList.add(existingUser);
			
			return Optional.of(existingUser);
		}
		return Optional.empty();
	}

}
