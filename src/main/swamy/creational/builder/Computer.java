package main.swamy.creational.builder;

import main.swamy.creational.builder.Computer.ComputerBuilder;

public class Computer {
	
	

	private String RAM;
	private String HDD;
	
	public String getRAM() {
		return RAM;
	}
	public String getHDD() {
		return HDD;
	}
	public boolean isGraphicsCardEnabled() {
		return isGraphicsCardEnabled;
	}
	public boolean isBluetoothEnabled() {
		return isBluetoothEnabled;
	}
	
	
	//optional
	private boolean isGraphicsCardEnabled;
	private boolean isBluetoothEnabled;
	
	private Computer(ComputerBuilder builder) {
		this.HDD = builder.HDD;
		this.RAM = builder.RAM;
		this.isBluetoothEnabled =  builder.isBluetoothEnabled;
		this.isGraphicsCardEnabled = builder.isGraphicsCardEnabled;
	}
	
	/**
	 * @author swamy
	 *
	 */
	public static class ComputerBuilder {
		private String RAM;
		private String HDD;
		
		private boolean isGraphicsCardEnabled;
		private boolean isBluetoothEnabled;
		
		public ComputerBuilder(String hdd, String ram) {
			this.RAM = ram;
			this.HDD =  hdd;
		}

		public ComputerBuilder setGraphicsCardEnabled(boolean isGraphicsCardEnabled) {
			this.isGraphicsCardEnabled = isGraphicsCardEnabled;
			return this;
		}

		public ComputerBuilder setBluetoothEnabled(boolean isBluetoothEnabled) {
			this.isBluetoothEnabled = isBluetoothEnabled;
			return this;
		}
		
		public Computer build() {
			return new Computer(this);
		}
		
	}
	
	

}
