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
public class CpuController
{
	@Autowired
	private CpuRepository cpuRepository;

	public CpuController(CpuRepository cpuRepository)
	{
		super();
		this.cpuRepository = cpuRepository;
	}

	@GetMapping("/cpus")
	public List<Cpu> getCpus() throws RecordNotFoundException
	{
		List<Cpu> cpus = null;
		cpus = (List<Cpu>) cpuRepository.findAll();
		if (cpus.isEmpty())
			throw new RecordNotFoundException("Cannot find any record");

		return cpus;
	}

	@GetMapping("/cpus/{id}")
	public Optional<Cpu> getCpu(@PathVariable int id)
			throws RecordNotFoundException
	{
		Optional<Cpu> cpu = null;

		cpu = cpuRepository.findById(id);
		if (!cpu.isPresent())
			throw new RecordNotFoundException(
					"Cannot find the record with id " + id);
		
		return cpu;
	}

	@PostMapping("/cpus")
	public Cpu newCpu(@Valid @RequestBody Cpu cpu) throws EmptyRecordException
	{
		if (cpu == null)
			throw new EmptyRecordException("Failed to save a record");

		return cpuRepository.save(cpu);
	}

	@DeleteMapping("/cpus/{id}")
	public Cpu deleteCpu(@PathVariable int id) throws RecordNotFoundException
	{
		Cpu cpu = cpuRepository.findById(id).get();
		if (cpu == null)
			throw new RecordNotFoundException(
					"Cannot find the record with id " + id);

		cpuRepository.delete(cpu);
		return cpu;
	}

	@PutMapping("/cpus/{id}")
	public Cpu updatecpu(@PathVariable int id, @Valid @RequestBody Cpu newCpu)
			throws RecordNotFoundException, EmptyRecordException
	{
		Cpu cpu = cpuRepository.findById(id).get();

		if (cpu == null)
			throw new RecordNotFoundException(
					"Cannot find the record with id " + id);
		if (newCpu == null)
			throw new EmptyRecordException("Failed to save a record");

		cpu.setBrand(newCpu.getBrand());
		cpu.setModel(newCpu.getModel());
		cpu.setCores_amount(newCpu.getCores_amount());
		cpu.setThreads_amount(newCpu.getThreads_amount());
		cpu.setClockspeed(newCpu.getClockspeed());
		cpu.setPrice(newCpu.getPrice());
		cpu.setTdp(newCpu.getTdp());
		cpu.setSocket(newCpu.getSocket());

		return cpuRepository.save(cpu);
	}
}
