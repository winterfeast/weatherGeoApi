package kz.api.config;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyProducer {
    private Properties properties;

    @Property
    @Produces
    public String produceString(final InjectionPoint ip) {
        return this.properties.getProperty(getKey(ip));
    }

    @Property
    @Produces
    public int produceInt(final InjectionPoint ip) {
        return Integer.valueOf(this.properties.getProperty(getKey(ip)));
    }

    private String getKey(final InjectionPoint ip) {
        return (ip.getAnnotated()
                  .isAnnotationPresent(Property.class) && 
                !ip.getAnnotated()
                   .getAnnotation(Property.class)
                   .value().isEmpty()) ? ip.getAnnotated()
                                           .getAnnotation(Property.class)
                                           .value() 
                                       : ip.getMember()
                                           .getName();
    }

    @PostConstruct
    public void init() {
        this.properties = new Properties();
        final InputStream stream = PropertyProducer.class
                                      .getResourceAsStream("/application.properties");
        if (stream == null) {
            throw new RuntimeException("No properties!!!");
        }
        try {
            this.properties.load(stream);
        } catch (final IOException e) {
            throw new RuntimeException("Configuration could not be loaded!");
        }
    }
}
