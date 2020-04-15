package com.projet.korector.controller;

import com.projet.korector.entity.Session;
import com.projet.korector.model.ERole;
import com.projet.korector.model.Role;
import com.projet.korector.model.User;
import com.projet.korector.model.UserDTO;
import com.projet.korector.payload.response.MessageResponse;
import com.projet.korector.payload.response.StatistiqueResponse;
import com.projet.korector.repository.RoleRepository;
import com.projet.korector.repository.UserRepository;
import com.projet.korector.security.services.UserDetailsImpl;
import com.projet.korector.services.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.modelmapper.ModelMapper;


import java.util.*;
import java.util.stream.Collectors;
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
    private AuthenticationManager authenticationManager;

    /**
     * Ajoute un nouveau utilisateur dans la base de donnée. Si le client existe déjà, on retourne un code indiquant que la création n'a pas abouti.
     * @param userDTORequest
     * @return
     */

   // @GetMapping("/all")
    @PostMapping("/saveTeacher")
    @ApiOperation(value = "Add a new Customer in the Library", response = UserDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 409, message = "Conflict: the user already exist"),
            @ApiResponse(code = 201, message = "Created: the user is successfully inserted"),
            @ApiResponse(code = 304, message = "Not Modified: the customer is unsuccessfully inserted") })
    public ResponseEntity<?> saveTeacher(@RequestBody User userDTORequest) {
       if (userRepository.existsByUsername(userDTORequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(userDTORequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        Set<Role> roles = new HashSet<>();
        Role enseignantRole = roleRepository.findByName(ERole.ROLE_ENSEIGNANT)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(enseignantRole);
        userDTORequest.setRoles(roles);
        service.saveUser(userDTORequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));


    }



    @PostMapping(value = "/updateUser")
    public User updateUser(User user) {
        return service.updateUser(user);
    }

    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void deleteById(@PathVariable ("userId") Long userId) {
        service.deleteById(userId);

    }
    @RequestMapping(value = "/findAllStudent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List <User> >  findAllStudent()
    {
        List<User> allUsers = userRepository.findAll();
        ArrayList<User> studentsResponse = new ArrayList<User>();
        for(User user : allUsers){
            for(Role role : user.getRoles()){
                if(role.getName().toString() == ("ROLE_ETUDIANT")){
                   // usersResponse[allUsers.get()] = new List<User>();
                    studentsResponse.add(user) ;
                }
                }

            }
        return new  ResponseEntity <List <User> >(studentsResponse, HttpStatus.OK);
    }
    @RequestMapping(value = "/findAllProf", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public  ResponseEntity<List <User> > findAllProf()
    {
        List<User> allProfs = userRepository.findAll();
        ArrayList<User> profsResponse = new ArrayList<User>();
        for(User user : allProfs){
            for(Role role : user.getRoles()){
                if(role.getName().toString() == ("ROLE_ENSEIGNANT")){
                    profsResponse.add(user) ;
                }
            }

        }
        return new  ResponseEntity <List <User> >(profsResponse, HttpStatus.OK);

    }
    @RequestMapping(value = "/findAllClasses", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> findAllClasses()
    {
        Integer nb_classes = 0;
        return ResponseEntity.ok(new MessageResponse("Test"));
    }

    @RequestMapping(value = "/findById", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public User findById(Long userID) {

        return service.findById(userID);
    }
    @RequestMapping(value = "/findByUsername", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)

    public Optional<User> findByUsername(String username) {
        return service.findByUsername(username);

    }

    @GetMapping("/sessions")
    public ResponseEntity<Set<Session>> getSessions() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        User currentUser = findById(userDetails.getId());
        Set<Session> sessions = currentUser.getSessions();

        return new ResponseEntity<Set<Session>>(sessions, HttpStatus.OK);

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


