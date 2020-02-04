package main.swamy.creational.abstractfactory;

public class MSWindow implements Window {

	@Override
	public void setTitle(String title) {
		System.out.println("**MS Window**");

	}

	@Override
	public void repaint() {
		System.out.println("MS window repaint");

	}

}
