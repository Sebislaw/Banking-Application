package application.menu;

import java.awt.Component;
import net.miginfocom.swing.MigLayout;

public class MenuAnimation {

    public static void showMenu(Component component, MenuItem item, MigLayout layout, boolean show) {
        int height = component.getPreferredSize().height;
        float f = show ? 1f : 0f;
        layout.setComponentConstraints(component, "h " + height * f + "!");
        component.revalidate();
        item.repaint();
    }
}