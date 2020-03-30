package com.projet.korector.controller;

import com.projet.korector.model.ERole;
import com.projet.korector.model.Role;
import com.projet.korector.model.User;
import com.projet.korector.model.UserDTO;
import com.projet.korector.repository.RoleRepository;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository userRepository;
    Set<Role> roles = new HashSet<>();

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;
    /**
     * Ajoute un nouveau utilisateur dans la base de donnée. Si le client existe déjà, on retourne un code indiquant que la création n'a pas abouti.
     * @param userDTORequest
     * @return
     */

   // @GetMapping("/all")
    @PostMapping("/saveUser")
    @ApiOperation(value = "Add a new Customer in the Library", response = UserDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 409, message = "Conflict: the user already exist"),
            @ApiResponse(code = 201, message = "Created: the user is successfully inserted"),
            @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully inserted") })
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTORequest) {
        //, UriComponentsBuilder uriComponentBuilder
        User existingCustomer = service.findUserByEmail(userDTORequest.getEmail());
        if (existingCustomer != null) {
            return new ResponseEntity<UserDTO>(HttpStatus.CONFLICT);
        }
        User userRequest = mapUserDTOToUser(userDTORequest);

        // Create new user's account
        User user = new User(userRequest.getUsername(),
                userDTORequest.getEmail(),
                encoder.encode(userRequest.getPassword()),userRequest.getGithubAccount());

        User userResponse = service.saveUser(userRequest);


        // For adding a role to user
       /* Role etudiantRole = roleRepository.findByName(ERole.ROLE_ETUDIANT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));

        // Add a role to user
        roles.add(etudiantRole);
        user.setRoles(roles); */

        if (userResponse != null) {
            UserDTO UserDTO = mapUserToUserDTO(userResponse);
            return new ResponseEntity<UserDTO>(UserDTO, HttpStatus.CREATED);

        }
        return new ResponseEntity<UserDTO>(HttpStatus.NOT_MODIFIED);

    }

/*
    @GetMapping("/all")
    @RequestMapping(value = "/saveUser", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)

   // @PostMapping (value = "/saveUser")
   public User saveUser(User user) {
         user = new User(user.getUsername(),
               user.getEmail(),
                user.getPassword(), user.getGithubAccount());
        return service.saveUser(user);

    } */

    @PostMapping(value = "/updateUser")
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

    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public User findById(Long userID) {

        return service.findById(userID);
    }
    @RequestMapping(value = "/findByUsername", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public Optional<User> findByUsername(String username) {
        return service.findByUsername(username);

    }

    private UserDTO mapUserToUserDTO(User user) {
        ModelMapper mapper = new ModelMapper();
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        return userDTO;
    }

    /**
     * Transforme un POJO CustomerDTO en en entity Customer
     *
     * @param customerDTO
     * @return
     */
    private User mapUserDTOToUser(UserDTO customerDTO) {
        ModelMapper mapper = new ModelMapper();
        User customer = mapper.map(customerDTO, User.class);
        return customer;
    }




}


