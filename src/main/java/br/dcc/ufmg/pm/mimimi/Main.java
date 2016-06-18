package br.dcc.ufmg.pm.mimimi;

import java.io.File;

import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import br.dcc.ufmg.pm.mimimi.beans.SignUpBean;
import br.dcc.ufmg.pm.mimimi.beans.FollowingBean;
import br.dcc.ufmg.pm.mimimi.beans.HeaderBean;
import br.dcc.ufmg.pm.mimimi.beans.AbstractBean;
import br.dcc.ufmg.pm.mimimi.beans.FeedBean;
import br.dcc.ufmg.pm.mimimi.beans.HashtagBean;
import br.dcc.ufmg.pm.mimimi.beans.LoginBean;
import br.dcc.ufmg.pm.mimimi.beans.FollowersBean;
import br.dcc.ufmg.pm.mimimi.beans.MimimisBean;
import br.dcc.ufmg.pm.mimimi.beans.LikesBean;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.dao.LikeDao;
import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.dao.DaoException;
import br.dcc.ufmg.pm.mimimi.dao.DaoFactory;
import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.dao.Dao;

import br.dcc.ufmg.pm.mimimi.dao.jpa.JpaConnectionDao;
import br.dcc.ufmg.pm.mimimi.dao.jpa.AbstractJpaDao;
import br.dcc.ufmg.pm.mimimi.dao.jpa.JpaLikeDao;
import br.dcc.ufmg.pm.mimimi.dao.jpa.JpaUserDao;
import br.dcc.ufmg.pm.mimimi.dao.jpa.JpaDaoFactory;
import br.dcc.ufmg.pm.mimimi.dao.jpa.JpaMimimiDao;

import br.dcc.ufmg.pm.mimimi.jsf.AuthenticationFilter;
import br.dcc.ufmg.pm.mimimi.jsf.ResourceHandlerImpl;
import br.dcc.ufmg.pm.mimimi.jsf.JpaFilter;

import br.dcc.ufmg.pm.mimimi.lazy.FeedLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.AbstractLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.LikesLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.FollowersLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.MimimisLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.HashtagLazyList;
import br.dcc.ufmg.pm.mimimi.lazy.FollowingLazyList;

import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.LikeId;
import br.dcc.ufmg.pm.mimimi.model.Like;
import br.dcc.ufmg.pm.mimimi.model.User;
import br.dcc.ufmg.pm.mimimi.model.EntityInterface;
import br.dcc.ufmg.pm.mimimi.model.ConnectionId;

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