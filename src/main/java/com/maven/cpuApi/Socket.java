package com.maven.cpuApi;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Socket
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@NotEmpty(message = "Name cannot be empty")
	private String name;

	@JsonIgnoreProperties("socket")
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "socket")
	private List<Cpu> cpus;

	public Socket()
	{
		super();
	}

	public Socket(int id, String name, List<Cpu> cpus)
	{
		super();
		this.id = id;
		this.name = name;
		this.cpus = cpus;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Cpu> getCpus()
	{
		return cpus;
	}

	public void setCpus(List<Cpu> cpus)
	{
		this.cpus = cpus;
	}

	@Override
	public String toString()
	{
		return "Socket [id=" + id + ", name=" + name + ", cpus=" + cpus + "]";
	}

}
