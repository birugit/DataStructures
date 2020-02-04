package main.swamy.creational.abstractfactory;

public class Client {

	public static void main(String[] args) {
		GUIBuilder builder = new GUIBuilder();
		AbstractWidgetFactory widgetFactory = null;
		if(Platform.currentPlatform() == "MACOSX") {
			widgetFactory = new MCWindowWidgetFactory();
		}
			else {
				widgetFactory = new MSWindowWidgetFactory();
			}
		builder.buildWindow(widgetFactory);

	}

}
