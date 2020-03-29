package com.projet.korector.controller;

import com.projet.korector.model.User;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService service;
    private UserRepository userRepository;

    /*********** Method for authentification *********/
  /*  @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public User getCurrentUser(@CurrentUser UserDTO userPrincipal, HttpServletRequest request) throws IOException {
        // System.out.println("oauth " + oAuth2UserRequest.getAccessToken().getTokenValue());
        System.out.println(request.getAuthType());
        System.out.println(request.getHeader("Authorization"));
        System.out.println(userPrincipal.getToken());
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }  */

    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User saveUser(User user) {
        return service.saveUser(user);

    }

    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public User updateUser(User user) {
        return service.updateUser(user);
    }
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public void deleteUser(Long userID) {
        service.deleteUser(userID);

    }

    @RequestMapping(value = "/findAllUser", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> findAllUser()
    {
        return service.findAllUser();
    }

    @RequestMapping(value = "/findByIdr", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public User findById(Long userID) {

        return service.findById(userID);
    }
    @RequestMapping(value = "/findByUsername", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public Optional<User> findByUsername(String username) {
        return service.findByUsername(username);

    }


}


