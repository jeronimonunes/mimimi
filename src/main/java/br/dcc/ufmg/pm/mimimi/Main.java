package br.dcc.ufmg.pm.mimimi;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import br.dcc.ufmg.pm.mimimi.beans.*;
import br.dcc.ufmg.pm.mimimi.dao.*;
import br.dcc.ufmg.pm.mimimi.dao.jpa.*;
import br.dcc.ufmg.pm.mimimi.jsf.*;
import br.dcc.ufmg.pm.mimimi.lazy.*;
import br.dcc.ufmg.pm.mimimi.model.*;

/**
 * Main class for starting Tomcat Embeded with the Mimimi application
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
@SuppressWarnings("unused") // The unused imports will guarantee that java compiler on makefile compiles all the necessary classes
public class Main {
	
	public static void main(String[] args) throws Exception {

        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));

        StandardContext ctx = (StandardContext) tomcat.addWebapp("", new File("WebContent").getAbsolutePath());

        // Declare an alternative location for your "WEB-INF/classes" dir
        // Servlet 3.0 annotation will work
        File additionWebInfClasses = new File("target/classes");
        WebResourceRoot resources = new StandardRoot(ctx);
        resources.addPreResources(new DirResourceSet(resources, "/WEB-INF/classes",
                additionWebInfClasses.getAbsolutePath(), "/"));
        ctx.setResources(resources);

        tomcat.start();
        tomcat.getServer().await();
    }
	
}