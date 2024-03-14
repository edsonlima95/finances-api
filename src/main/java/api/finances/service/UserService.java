package api.finances.service;


import api.finances.exception.customExceptions.NotFoundException;
import api.finances.model.Category;
import api.finances.model.User;
import api.finances.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return this.userRepository.findAll();
    }



    public void create(User user) {
        this.userRepository.save(user);
    }

    public User findById(Long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public User update(Long id, User user) {

        this.findById(id);

        user.setId(id);

        return this.userRepository.save(user);
    }

    public void delete(Long id) {

        this.findById(id);

        this.userRepository.deleteById(id);
    }
}
