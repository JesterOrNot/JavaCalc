package org.test;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.material.Material;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("JavaCalc")
@Theme(value = Material.class, variant = Material.DARK)
@PWA(name = "JavaCalc", shortName = "JCalc")
public class MainView extends AppLayout {

    private Tabs appTabs = new Tabs(new Tab("Quadratic"), new Tab("Speed"));

    public MainView() {
        addToNavbar(true, new DrawerToggle());
        this.appTabs.setOrientation(Tabs.Orientation.HORIZONTAL);
        addToDrawer(this.appTabs);
        setContent(selectLayout());
        this.appTabs.addSelectedChangeListener(event -> {
            setContent(selectLayout());
        });
    }

    public VerticalLayout selectLayout() {
        switch (this.appTabs.getSelectedIndex()) {
        case 0:
            return createQuadraticLayout();
        case 1:
            return createSpeedLayout();
        }
        return null;
    }

    public VerticalLayout createSpeedLayout() {
        VerticalLayout layout = new VerticalLayout();
        TextField timeField = new TextField("Time");
        TextField distancField = new TextField("Distance");
        Button submitButton = new Button("Submit", new Icon(VaadinIcon.ARROW_RIGHT), event -> {
            double distance = Double.parseDouble(distancField.getValue());
            double time = Double.parseDouble(timeField.getValue());
            double speed = distance / time;
            Notification.show("Speed: " + Double.toString(speed), 3000, Position.BOTTOM_END);
            timeField.clear();
            distancField.clear();
        });
        submitButton.setIconAfterText(true);
        layout.add(distancField, timeField, submitButton);
        return layout;
    }

    public VerticalLayout createQuadraticLayout() {
        VerticalLayout layout = new VerticalLayout();
        TextField aField = new TextField("A");
        TextField bField = new TextField("B");
        TextField cField = new TextField("C");
        Button submitButton = new Button("Submit", new Icon(VaadinIcon.ARROW_RIGHT), event -> {
            aField.clear();
            bField.clear();
            cField.clear();
        });
        submitButton.setIconAfterText(true);
        layout.add(aField, bField, cField, submitButton);
        return layout;
    }
}
