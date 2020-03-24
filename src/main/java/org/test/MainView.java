package org.test;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("JavaCalc")
@Theme(value = Material.class, variant = Material.DARK)
@PWA(name = "JavaCalc", shortName = "JCalc")
public class MainView extends VerticalLayout {

    public MainView() {
        Button button = new Button("Click me",
                event -> Notification.show("Clicked!"));
        add(button);
    }
}
