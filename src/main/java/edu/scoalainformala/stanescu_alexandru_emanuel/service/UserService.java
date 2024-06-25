package edu.scoalainformala.stanescu_alexandru_emanuel.service;

import edu.scoalainformala.stanescu_alexandru_emanuel.domain.User;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Component
@Service
@RequiredArgsConstructor
@AllArgsConstructor
public class UserService {

    private List<User> users = new ArrayList<>();

    {
        final User user1 = new User();
        user1.setId(1);
        user1.setName("user1");
        user1.setEmail("user1@gmail.com");

        final User user2 = new User();
        user2.setId(2);
        user2.setName("user2");
        user2.setEmail("user2@gmail.com");

        final User user3 = new User();
        user3.setId(3);
        user3.setName("user3");
        user3.setEmail("user3@gmail.com");

        users.add(user1);
        users.add(user2);
        users.add(user3);

    }

    public List<User> getAll() {
        return users;
    }

    public User get(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .get();
    }

    public void add(User user) {

        user.setId(getGreatestId() + 1);
        users.add(user);
    }

    public void update(Integer id,User updatedUser) {

        final User user = users.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .get();

        user.setName(updatedUser.getName());
        user.setEmail(updatedUser.getEmail());
    }

    public void delete(Integer id) {
        users = users.stream()
                .filter(user -> !user.getId().equals(id))
                .collect(Collectors.toList());
    }

    private int getGreatestId() {
        return users.stream()
                .sorted(Comparator.comparingInt(User::getId).reversed())
                .findFirst()
                .get()
                .getId();
    }


}
