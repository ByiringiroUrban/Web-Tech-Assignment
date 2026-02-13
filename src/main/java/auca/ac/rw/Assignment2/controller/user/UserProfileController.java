package auca.ac.rw.Assignment2.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auca.ac.rw.Assignment2.model.user.ApiResponse;
import auca.ac.rw.Assignment2.model.user.UserProfile;

@RestController
@RequestMapping("/api/users")
public class UserProfileController {

    private List<UserProfile> users = new ArrayList<>();

    public UserProfileController() {
        users.add(new UserProfile(1L, "urban Bobola", "urbanbobola20.com",
                "byiringiro urban bobola", 23, "Rwanda", "Software developer", true));
        users.add(new UserProfile(2L, "gentil mugisha", "gentilmugisha.com",
                "gentil mugisha", 30, "Rwanda", "Designer", false));
        users.add(new UserProfile(3L, "lena paolla", "lenapaolla.com",
                "Lena Paolla", 14, "Rwanda", "Student", true));
    }

    // CREATE
    @PostMapping
    public ApiResponse<UserProfile> createUser(@RequestBody UserProfile userProfile) {
        users.add(userProfile);
        return new ApiResponse<>(true, "User profile created successfully", userProfile);
    }

    // READ all
    @GetMapping
    public ApiResponse<List<UserProfile>> getAllUsers() {
        return new ApiResponse<>(true, "All user profiles", users);
    }

    // READ by id
    @GetMapping("/{userId}")
    public ApiResponse<UserProfile> getUserById(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                return new ApiResponse<>(true, "User found", user);
            }
        }
        return new ApiResponse<>(false, "User not found", null);
    }

    // UPDATE
    @PutMapping("/{userId}")
    public ApiResponse<UserProfile> updateUser(@PathVariable Long userId,
                                               @RequestBody UserProfile updatedUser) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setUsername(updatedUser.getUsername());
                user.setEmail(updatedUser.getEmail());
                user.setFullName(updatedUser.getFullName());
                user.setAge(updatedUser.getAge());
                user.setCountry(updatedUser.getCountry());
                user.setBio(updatedUser.getBio());
                user.setActive(updatedUser.isActive());
                return new ApiResponse<>(true, "User updated", user);
            }
        }
        return new ApiResponse<>(false, "User not found", null);
    }

    // DELETE
    @DeleteMapping("/{userId}")
    public ApiResponse<Void> deleteUser(@PathVariable Long userId) {
        boolean removed = users.removeIf(user -> user.getUserId().equals(userId));
        if (removed) {
            return new ApiResponse<>(true, "User deleted", null);
        }
        return new ApiResponse<>(false, "User not found", null);
    }

    // SEARCH by username
    @GetMapping("/search/username")
    public ApiResponse<List<UserProfile>> searchByUsername(@RequestParam String username) {
        List<UserProfile> result = new ArrayList<>();

        for (UserProfile user : users) {
            if (user.getUsername().toLowerCase().contains(username.toLowerCase())) {
                result.add(user);
            }
        }

        return new ApiResponse<>(true, "Search by username", result);
    }

    // SEARCH by country
    @GetMapping("/search/country/{country}")
    public ApiResponse<List<UserProfile>> searchByCountry(@PathVariable String country) {
        List<UserProfile> result = new ArrayList<>();

        for (UserProfile user : users) {
            if (user.getCountry().equalsIgnoreCase(country)) {
                result.add(user);
            }
        }

        return new ApiResponse<>(true, "Search by country", result);
    }

    // SEARCH by age range ?minAge=&maxAge=
    @GetMapping("/search/age-range")
    public ApiResponse<List<UserProfile>> searchByAgeRange(@RequestParam int minAge,
                                                           @RequestParam int maxAge) {
        List<UserProfile> result = new ArrayList<>();

        for (UserProfile user : users) {
            if (user.getAge() >= minAge && user.getAge() <= maxAge) {
                result.add(user);
            }
        }

        return new ApiResponse<>(true, "Search by age range", result);
    }

    // ACTIVATE
    @PatchMapping("/{userId}/activate")
    public ApiResponse<UserProfile> activateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(true);
                return new ApiResponse<>(true, "User activated", user);
            }
        }
        return new ApiResponse<>(false, "User not found", null);
    }

    // DEACTIVATE
    @PatchMapping("/{userId}/deactivate")
    public ApiResponse<UserProfile> deactivateUser(@PathVariable Long userId) {
        for (UserProfile user : users) {
            if (user.getUserId().equals(userId)) {
                user.setActive(false);
                return new ApiResponse<>(true, "User deactivated", user);
            }
        }
        return new ApiResponse<>(false, "User not found", null);
    }
}

