package esipe.dataaccess.user.controllers;

// import org.springframework.data.domain.PageRequest;
import gokan.ekinci.API;
import gokan.ekinci.models.UserDto;
		import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

/**
 * @author Gokan EKINCI
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {
/*
	@RequestMapping(path = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UserDto> get(@PathVariable Long id) {
		// TODO
		final Optional<UserDto> dtoOpt = Optional.of(new UserDto());
		return (dtoOpt.isPresent()) ?
			new ResponseEntity<>(dtoOpt.get(), HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
*/
	/**
	 * If page and size request parameters are filled, return a page. Otherwise, return a list of all elements.
	 *
	 * @param page      Page index, starts with 0
	 * @param size      Page size
	 * @return          Can return a TODO @link org.springframework.data.domain.Page OR a {@link List} of DTO
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> get(
		@RequestParam(value = "page", required = false) Integer page,
		@RequestParam(value = "size", required = false) Integer size
	) throws IOException {
		// Pagination
		if (page != null && size != null) {
			// TODO
		}

		// TODO
		final List<UserDto> userDtoList = API.get().getRetrofitService().users().execute().body();
		//final List<UserDto> userDtoList = Collections.emptyList();
		return (!userDtoList.isEmpty()) ?
			new ResponseEntity<>(userDtoList, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	/*
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<UserDto> create(@RequestBody UserDto user) {
		return new ResponseEntity<>(new UserDto(), HttpStatus.OK);
	}*/

	@RequestMapping(path = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody UserDto user) {
		// TODO
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> delete(@PathVariable String id) {
		// TODO
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
