package uz.muzaffar.codingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.muzaffar.codingbat.entity.ApiResponse;
import uz.muzaffar.codingbat.entity.Help;
import uz.muzaffar.codingbat.entity.Language;
import uz.muzaffar.codingbat.entity.User;
import uz.muzaffar.codingbat.payload.HelpDto;
import uz.muzaffar.codingbat.payload.UserDto;
import uz.muzaffar.codingbat.repository.HelpRepository;
import uz.muzaffar.codingbat.repository.LanguageRepository;
import uz.muzaffar.codingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public ApiResponse getUserById(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        return byId.map(help -> new ApiResponse("Success", true, help)).orElseGet(() -> new ApiResponse("User not found!", false));
    }

    public ApiResponse addUser(UserDto userDto) {

        boolean b = userRepository.existsByName(userDto.getName());
        if (b)
            return new ApiResponse("This User already exist! ", false);
        User user = new User();
        user.setName(userDto.getName());
        return new ApiResponse("Successfully added!", true);
    }

    public ApiResponse deleteUser(Integer id) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent())
            new ApiResponse("User not found!", false);
        userRepository.deleteById(id);
        return new ApiResponse("Deleted successfully!", true);
    }

    public ApiResponse editHelp(Integer id, UserDto userDto) {
        Optional<User> byId = userRepository.findById(id);
        if (!byId.isPresent())
            return new ApiResponse("User not found!", false);

        boolean b = userRepository.existsByName(userDto.getName());
        if (b)
            return new ApiResponse("This User already exist! ", false);

        User user = byId.get();
        user.setName(userDto.getName());
        userRepository.save(user);
        return new ApiResponse("Edited successfully!", true);
    }

}