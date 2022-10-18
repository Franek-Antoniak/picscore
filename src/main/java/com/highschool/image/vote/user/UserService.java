package com.highschool.image.vote.user;


import com.highschool.image.vote.freemarker.FreeMarkerService;
import com.highschool.image.vote.image.Image;
import com.highschool.image.vote.image.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

	private final WebUserService webUserService;
	private final UserRepository userRepository;
	private final ImageRepository imageRepository;
	private final FreeMarkerService freeMarkerService;

	public ResponseEntity<String> getCreatorIfUserCanUpload() {
		return freeMarkerService.getResponseEntityHTML("creator.ftl");
	}

	public ResponseEntity<String> getHomePage() {
		User user = getUserOrElseCreate();
		List<Image> imageList = imageRepository.findAll();
		return freeMarkerService.getResponseEntityHTML("homepage.ftl", new String[]{"user", "imageList"},
				new Object[]{user, imageList});
	}

	public User getUserOrElseCreate() {
		Optional<User> optionalUser = userRepository.findByName(webUserService.getUserNameId());
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		createUser();
		return userRepository.findByName(webUserService.getUserNameId())
		                     .get();
	}

	public void createUser() {
		User user = new User();
		String name = webUserService.getUserNameId();
		user.setName(name);
		userRepository.save(user);
	}

	public ResponseEntity<String> getSettings() {
		return freeMarkerService.getResponseEntityHTML("settings.ftl");
	}

	public void saveUser(User user) {
		userRepository.save(user);
	}
}