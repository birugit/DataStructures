package main.swamy.creational.abstractfactory;

public class GUIBuilder {
	
	public void buildWindow(AbstractWidgetFactory widgetFactory) {
		Window window = widgetFactory.createWindow();
		window.setTitle("New Window");
	}

}
