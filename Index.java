import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.VaadinServlet;
import com.vaadin.flow.server.VaadinServletConfiguration;
import javax.servlet.annotation.WebServlet;

@Route("")
@PWA(name = "Simple Vaadin App", shortName = "Vaadin App")
public class SimpleVaadinApp extends VaadinServlet {

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = SimpleVaadinApp.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }

    @Override
    protected void service(VaadinRequest vaadinRequest, VaadinResponse vaadinResponse) {
        super.service(vaadinRequest, vaadinResponse);
        vaadinRequest.getServletContext().setAttribute("org.eclipse.jetty.servlet.ServletHandler", new FullscreenServlet());
    }

    public static class FullscreenServlet extends MyUIServlet {
    }

    public class MainView extends VerticalLayout {
        public MainView() {
            TextField nameField = new TextField("Nama");
            Button greetButton = new Button("Sapa");
            greetButton.addClickListener(e -> {
                String name = nameField.getValue();
                Notification.show("Halo, " + name + "!");
            });

            add(nameField, greetButton);
        }
    }
}
