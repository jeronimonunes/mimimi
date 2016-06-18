package br.dcc.ufmg.pm.mimimi.jsf;

import javax.faces.application.Resource;
import javax.faces.application.ResourceHandler;
import javax.faces.application.ResourceHandlerWrapper;

/**
 * {@link ResourceHandler} that will send a default picture if the user doesn't have a picture or cover
 * @author Alexandre Alphonsos Rodrigues Pereira
 * @author Jeronimo Nunes Rocha
 * @author Felipe Marcelino
 *
 */
public class ResourceHandlerImpl extends ResourceHandlerWrapper {

	private static final String USER_PICTURES_LIBRARY = "user";
	private static final String COVER_PICTURES_LIBRARY = "cover";

	private javax.faces.application.ResourceHandler wrapped;

	public ResourceHandlerImpl(ResourceHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ResourceHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public Resource createResource(String resourceName, String libraryName) {
		if(USER_PICTURES_LIBRARY.equals(libraryName)||COVER_PICTURES_LIBRARY.equals(libraryName)){			
			Resource r = super.createResource(resourceName.endsWith(".jpg")?resourceName:resourceName+".jpg", libraryName);
			if(r==null){
				return super.createResource("default.png", libraryName);
			} else {
				return r;
			}
		} else {
			return super.createResource(resourceName,libraryName);
		}
	}

}
