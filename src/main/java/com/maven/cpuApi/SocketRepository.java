package com.maven.cpuApi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface SocketRepository extends JpaRepository<Socket, Integer>
{
	@Query(value = "select s.id, s.name from Socket s", nativeQuery = true)
	List<SocketSimplified> findAllSimplified();
	
	@Override
	List<Socket> findAll();
}
