package net.java.cargotracker.interfaces.web.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 * @author paoesco
 */
@ApplicationScoped
public class WebProducer {

    @Inject
    private FacesContext facesContext;

    @Produces
    @RequestScoped
    public FacesContext produceFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    @Produces
    @RequestParameter
    String getRequestParameter(InjectionPoint ip) {
        String name = ip.getAnnotated().getAnnotation(RequestParameter.class)
                .value();

        if ("".equals(name)) {
            name = ip.getMember().getName();
        }

        return facesContext.getExternalContext().getRequestParameterMap()
                .get(name);
    }

}
