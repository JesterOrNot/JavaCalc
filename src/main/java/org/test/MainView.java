package org.test;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
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
import com.vaadin.flow.theme.lumo.*;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route
@PageTitle("JavaCalc")
@Theme(value = Lumo.class, variant = Lumo.DARK)
@PWA(name = "JavaCalc", shortName = "JCalc")
public class MainView extends AppLayout {

    private Tabs appTabs = new Tabs(new Tab("Quadratic"), new Tab("Speed"));

    public MainView() {
        Image img = new Image("https://raw.githubusercontent.com/jesterornot/javacalc/2a20faeb8fe541084e64fb5bf15e21aa940ebbd9/src/main/webapp/icons/icon.png", "JavaCalc Logo");
        img.setHeight("22px");
        img.setWidth("22px");
        addToNavbar(true, new DrawerToggle(), img);
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
        Paragraph title = new Paragraph("Speed Calculator");
        TextField timeField = new TextField("Time (seconds)");
        TextField distancField = new TextField("Distance (meters)");
        Button submitButton = new Button("Submit", new Icon(VaadinIcon.ARROW_RIGHT), event -> {
            double distance = parseDouble(distancField.getValue());
            double time = parseDouble(timeField.getValue());
            double speed = distance / time;
            Notification.show("Speed: " + Double.toString(speed), 3000, Position.BOTTOM_END);
            timeField.clear();
            distancField.clear();
        });
        submitButton.setIconAfterText(true);
        layout.add(title, distancField, timeField, submitButton);
        return layout;
    }

    public VerticalLayout createQuadraticLayout() {
        VerticalLayout layout = new VerticalLayout();
        Paragraph title = new Paragraph("Quadratic Equation Calculator");
        TextField aField = new TextField("A");
        TextField bField = new TextField("B");
        TextField cField = new TextField("C");
        Button submitButton = new Button("Submit", new Icon(VaadinIcon.ARROW_RIGHT), event -> {
            double a = parseDouble(aField.getValue());
            double b = parseDouble(bField.getValue());
            double c = parseDouble(cField.getValue());
            double[] awnser = quadraticEquation(a, b, c);
            String x1 = Double.toString(awnser[0]);
            String x2 = Double.toString(awnser[1]);
            String finalAwnser = "(" + x1 + ", " + x2 + ")";
            Notification.show("Awnser: " + finalAwnser, 3000, Position.BOTTOM_END);
            aField.clear();
            bField.clear();
            cField.clear();
        });
        submitButton.setIconAfterText(true);
        layout.add(title, aField, bField, cField, submitButton);
        return layout;
    }

    public double[] quadraticEquation(double a, double b, double c) {
        return new double[] { (-b + Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / 2 * a,
                (-b - Math.sqrt(Math.pow(b, 2) - 4 * a * c)) / 2 * a };
    }

    public double parseDouble(String str) {
        return Double.parseDouble(str);
    }
}
