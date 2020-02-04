package main.swamy.creational.abstractfactory;

public class MCWindow implements Window {

	@Override
	public void setTitle(String title) {
		System.out.println("MC Title");
	}

	@Override
	public void repaint() {
		System.out.println("MC repaint");
	}

}
