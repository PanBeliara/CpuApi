package com.maven.cpuApi;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@CrossOrigin
public class SocketController
{
	@Autowired
	private SocketRepository socketRepository;

	public SocketController(SocketRepository socketRepository)
	{
		super();
		this.socketRepository = socketRepository;
	}

	@GetMapping("/sockets")
	public List<SocketSimplified> getSockets() throws RecordNotFoundException
	{
		List<SocketSimplified> sockets = null;
		sockets = (List<SocketSimplified>) socketRepository.findAllSimplified();
		if (sockets.isEmpty())
			throw new RecordNotFoundException("Cannot find any record");

		return sockets;
	}

	@GetMapping("/sockets/{id}")
	public Optional<Socket> getSocket(@PathVariable int id)
			throws RecordNotFoundException
	{
		Optional<Socket> socket = null;

		socket = socketRepository.findById(id);
		if (!socket.isPresent())
			throw new RecordNotFoundException(
					"Cannot find the record with id " + id);

		return socket;
	}

	@PostMapping("/sockets")
	public Socket newSocket(@Valid @RequestBody Socket socket)
			throws EmptyRecordException
	{
		if (socket == null)
			throw new EmptyRecordException("Failed to save a record");

		return socketRepository.save(socket);
	}

	@DeleteMapping("/sockets/{id}")
	public Socket deleteSocket(@PathVariable int id)
			throws RecordNotFoundException
	{
		Socket socket = socketRepository.findById(id).get();
		if (socket == null)
			throw new RecordNotFoundException(
					"Cannot find the record with id " + id);

		System.out.println("Deleting/n" + socket.toString()); //with this there's no CORS error on the client side, hell if I know why
		socketRepository.delete(socket);
		return socket;
	}

	@PutMapping("/sockets/{id}")
	public Socket updatesocket(@PathVariable int id,
			@Valid @RequestBody Socket newSocket)
			throws RecordNotFoundException, EmptyRecordException
	{
		Socket socket = socketRepository.findById(id).get();

		if (socket == null)
			throw new RecordNotFoundException(
					"Cannot find the record with id " + id);
		if (newSocket == null)
			throw new EmptyRecordException("Failed to save a record");

		socket.setName(newSocket.getName());

		return socketRepository.save(socket);
	}
}
