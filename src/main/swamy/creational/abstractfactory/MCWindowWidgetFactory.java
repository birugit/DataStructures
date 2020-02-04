package main.swamy.creational.abstractfactory;

public class MCWindowWidgetFactory implements AbstractWidgetFactory {

	@Override
	public Window createWindow() {
		MCWindow window = new MCWindow();
		return window;
	}

}
