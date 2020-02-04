package main.swamy.creational.abstractfactory;

public class MSWindowWidgetFactory implements AbstractWidgetFactory {

	@Override
	public Window createWindow() {
		MSWindow window = new MSWindow();
		return window;
	}

}
