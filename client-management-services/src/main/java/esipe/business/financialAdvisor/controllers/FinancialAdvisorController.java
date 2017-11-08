package esipe.business.financialAdvisor.controllers;

// import org.springframework.data.domain.PageRequest;
import esipe.business.financialAdvisor.services.FinancialAdvisorService;
import esipe.models.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author Gokan EKINCI
 */
@RestController
@RequestMapping(path = "/users")
public class FinancialAdvisorController {


	private final FinancialAdvisorService faService;

	@Autowired
	public FinancialAdvisorController(FinancialAdvisorService faService) { this.faService = faService ;}


	@RequestMapping(path = "users/{id}",method = RequestMethod.GET)
		public ResponseEntity<?> getById(@PathVariable String id) throws IOException {

		UserDto user = faService.getCustomerById(id);

		return (user!= null) ?
				new ResponseEntity<>(user,HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}

	@RequestMapping(path = "users/getby",method = RequestMethod.GET)
	public ResponseEntity<?> getByLastName(@RequestParam("lastname") String lastName) throws IOException {

		UserDto user = faService.getCustomerByLastName(lastName);

		return (user!= null) ?
				new ResponseEntity<>(user,HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NO_CONTENT);

	}

	@RequestMapping(path = "users",method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody UserDto userDto){
		faService.createNewCustomer(userDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = "/users/{id}",method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String id,@RequestBody UserDto userDto){

		faService.updateCustomer(id,userDto);
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
