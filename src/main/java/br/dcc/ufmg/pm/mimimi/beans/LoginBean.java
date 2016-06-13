package br.dcc.ufmg.pm.mimimi.beans;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;

import org.primefaces.event.FileUploadEvent;

import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean extends AbstractBean {

	private static final long serialVersionUID = 4L;

	private User user;
	
	private String mimimiMsg;
	
	private String username;

	private String password;

	private String searchQuery;
	
	public void sendMimimi(){
		MimimiDao dao = getDao(MimimiDao.class);
			try {
				Mimimi mimimi = new Mimimi(getMimimiMsg(),getUser());
				dao.save(mimimi);
			
			} catch (Exception e){
				addError("Não foi possível Mimimizar.");
				e.printStackTrace();
			}
	}
	
	public String login() {
		user = getDao(UserDao.class).login(username,password);
		if(user==null){
			addError("Usuários ou senha inválidos");
			return null;
		} else {
			return "pretty:feed";
		}
	}

	public void uploadPicture(FileUploadEvent event) {
		try {
			BufferedImage image = ImageIO.read(event.getFile().getInputstream());
			BufferedImage newImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
			newImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
			ImageIO.write(newImage, "jpg", new File(getRealPath("/resources/user"),getUser().getId()+".jpg"));
		} catch (IOException e) {
			addError("Não foi possível carregar a imagem");
			e.printStackTrace();
		}
	}

	public void uploadCover(FileUploadEvent event) {
		try {
			BufferedImage image = ImageIO.read(event.getFile().getInputstream());
			BufferedImage newImage = new BufferedImage(image.getWidth(),image.getHeight(),BufferedImage.TYPE_INT_RGB);
			newImage.createGraphics().drawImage(image, 0, 0, Color.WHITE, null);
			ImageIO.write(newImage, "jpg", new File(getRealPath("/resources/cover"),getUser().getId()+".jpg"));
		} catch (IOException e) {
			addError("Não foi possível carregar a imagem");
			e.printStackTrace();
		}
	}

	public void logout() {
		getSession().invalidate();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSearchQuery() {
		return searchQuery;
	}

	public void setSearchQuery(String searchQuery) {
		this.searchQuery = searchQuery;
	}
	
	public String getMimimiMsg() {
		return mimimiMsg;
	}

	public void setMimimiMsg(String mimimiMsg) {
		this.mimimiMsg = mimimiMsg;
	}
}
