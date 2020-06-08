package spring.intro.controller;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.intro.dto.UserResponseDto;
import spring.intro.model.User;
import spring.intro.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/inject")
    public void injectUsers() {
        userService.add(new User("Alexey"));
        userService.add(new User("Vadim"));
        userService.add(new User("Matvey"));
        userService.add(new User("Alina"));
    }

    @RequestMapping("/get/{id}")
    public UserResponseDto get(@PathVariable Long id) {
        return getDtoFromUser(userService.get(id));
    }

    @RequestMapping
    public List<UserResponseDto> getAll() {
        return userService.listUsers().stream()
                .map(this::getDtoFromUser)
                .collect(Collectors.toList());
    }

    private UserResponseDto getDtoFromUser(User user) {
        UserResponseDto userResponseDto = new UserResponseDto();
        userResponseDto.setId(user.getId());
        userResponseDto.setName(user.getName());
        return userResponseDto;
    }
}
