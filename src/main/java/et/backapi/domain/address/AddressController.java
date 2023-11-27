package et.backapi.domain.address;

import et.backapi.adapter.dto.CreateAddressDTO;
import et.backapi.domain.curriculum.Curriculum;
import et.backapi.domain.user.User;
import et.backapi.domain.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressController {
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressController(AddressRepository addressRepository , UserRepository userRepository){
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    @PostMapping("create/{id}")
    public ResponseEntity<String> createAddress(@PathVariable Long id,  @RequestBody CreateAddressDTO data){

        Optional<User> userExists = userRepository.findById(id);

        if(userExists.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario com o id " + id + " não encontrado");

        User user = userExists.get();
        Address address = new Address();

        address.setAddressAddress(data.addressAddress());
        address.setAddressNeighborhood(data.addressNeighborhood());
        address.setAddressNumber(data.addressNumber());
        address.setAddressStreet(data.addressStreet());
        address.setAddressZipCode(data.addressZipCode());
        address.setUser(user);

        addressRepository.save(address);

        String responseMessage = "ENDEREÇO CRIADO COM SUCESSO";
        return new ResponseEntity<>(responseMessage, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Address>>listAddress(){
        List<Address> addresses = addressRepository.findAll();
        return ResponseEntity.ok().body(addresses);
    }
    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAddress(@PathVariable Long id){
        Optional<Address> addressExist = addressRepository.findById(id);

        if(addressExist.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Endereço com o id " + id + " não encontrado");

        return ResponseEntity.noContent().build();

    }

}
