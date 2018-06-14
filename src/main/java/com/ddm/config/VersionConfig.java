package com.ddm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.InputStream;
import java.util.Properties;

@Component
public class VersionConfig implements ServletContextAware {

    private static final String MANIFEST_PATH = "/META-INF/MANIFEST.MF";
    private final Logger log = LoggerFactory.getLogger(VersionConfig.class);

    private Properties props;

    private String version = "";
    private String project = "";

    @Override
    public void setServletContext(final ServletContext context) {
        try {
            InputStream stream = context.getResourceAsStream(MANIFEST_PATH);
            props = new Properties();
            props.load(stream);
            setVersion((String) props.get("version"));
            setProject((String) props.get("project"));

        } catch (Exception exception) {
            log.error("Failed to load Manifest from" + MANIFEST_PATH,
                    (Object) exception.getStackTrace());
        }
    }

    public String getProject() {
        return project;
    }

    public void setProject(final String project) {
        this.project = project;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(final String version) {
        this.version = version;
    }


}
