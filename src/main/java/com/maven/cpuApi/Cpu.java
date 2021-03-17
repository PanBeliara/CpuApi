package com.maven.cpuApi;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Cpu
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;

	@NotEmpty(message = "Brand cannot be empty")
	private String brand;

	@NotEmpty(message = "Model cannot be empty")
	private String model;

	@NotEmpty(message = "Clockspeed cannot be empty")
	private String clockspeed;

	@NotNull(message = "Cores amount cannot be empty")
	@Min(value = 1, message = "Cores amount cannot be less than 1")
	private int cores_amount;

	@NotNull(message = "Threads amount cannot be empty")
	@Min(value = 1, message = "Threads amount cannot be less than 1")
	private int threads_amount;

	@NotNull(message = "TDP cannot be empty")
	@Min(value = 1, message = "TDP cannot be less than 1")
	private int tdp;

	@NotNull(message = "Price cannot be empty")
	@Min(value = 1, message = "Price cannot be less than 1")
	private double price;

	@JsonIgnoreProperties("cpus")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "socket_id")
	private Socket socket;

	public Cpu()
	{
		super();
	}

	public Cpu(int id, String brand, String model, String clockspeed,
			int cores_amount, int threads_amount, int tdp, double price,
			Socket socket)
	{
		super();
		this.id = id;
		this.brand = brand;
		this.model = model;
		this.clockspeed = clockspeed;
		this.cores_amount = cores_amount;
		this.threads_amount = threads_amount;
		this.tdp = tdp;
		this.price = price;
		this.socket = socket;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getBrand()
	{
		return brand;
	}

	public void setBrand(String brand)
	{
		this.brand = brand;
	}

	public String getModel()
	{
		return model;
	}

	public void setModel(String model)
	{
		this.model = model;
	}

	public String getClockspeed()
	{
		return clockspeed;
	}

	public void setClockspeed(String clockspeed)
	{
		this.clockspeed = clockspeed;
	}

	public int getCores_amount()
	{
		return cores_amount;
	}

	public void setCores_amount(int cores_amount)
	{
		this.cores_amount = cores_amount;
	}

	public int getThreads_amount()
	{
		return threads_amount;
	}

	public void setThreads_amount(int threads_amount)
	{
		this.threads_amount = threads_amount;
	}

	public int getTdp()
	{
		return tdp;
	}

	public void setTdp(int tdp)
	{
		this.tdp = tdp;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public Socket getSocket()
	{
		return socket;
	}

	public void setSocket(Socket socket)
	{
		this.socket = socket;
	}

	@Override
	public String toString()
	{
		return "Cpu [id=" + id + ", brand=" + brand + ", model=" + model
				+ ", clockspeed=" + clockspeed + ", cores_amount="
				+ cores_amount + ", threads_amount=" + threads_amount + ", tdp="
				+ tdp + ", price=" + price + ", socket_id=" + socket.getId()
				+ ", socket_name=" + socket.getName() + "]";
	}

}
