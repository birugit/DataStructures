package main.swamy.creational.builder;

public class BuilderTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//using builder to get object in single line of code
		Computer comp = new Computer.ComputerBuilder("500 GB", "16 GB").setBluetoothEnabled(true).setGraphicsCardEnabled(true).build();
	System.out.println("Builder:"+ comp.getHDD() +" "+comp.getRAM() +" "+comp.isBluetoothEnabled() +" "+comp.isGraphicsCardEnabled());
	}

}
