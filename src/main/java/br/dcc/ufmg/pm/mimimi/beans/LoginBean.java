package br.dcc.ufmg.pm.mimimi.beans;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;

import org.primefaces.event.FileUploadEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.dcc.ufmg.pm.mimimi.dao.ConnectionDao;
import br.dcc.ufmg.pm.mimimi.dao.DaoException;
import br.dcc.ufmg.pm.mimimi.dao.LikeDao;
import br.dcc.ufmg.pm.mimimi.dao.MimimiDao;
import br.dcc.ufmg.pm.mimimi.dao.UserDao;
import br.dcc.ufmg.pm.mimimi.model.Connection;
import br.dcc.ufmg.pm.mimimi.model.ConnectionId;
import br.dcc.ufmg.pm.mimimi.model.Like;
import br.dcc.ufmg.pm.mimimi.model.LikeId;
import br.dcc.ufmg.pm.mimimi.model.Mimimi;
import br.dcc.ufmg.pm.mimimi.model.User;

@ManagedBean(name="loginBean")
@SessionScoped
public class LoginBean extends AbstractBean {

	private static final long serialVersionUID = 4L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(LoginBean.class);

	private User user;
	
	private String mimimiMsg;
	
	private String username;

	private String password;

	private String searchQuery;
	
	public String login() {
		user = getDao(UserDao.class).login(username,password);
		if(user==null){
			addError("Usuários ou senha inválidos");
			return null;
		} else {
			return "pretty:feed";
		}
	}
	
	public void sendMimimi(){
		MimimiDao dao = getDao(MimimiDao.class);
			try {
				Mimimi mimimi = new Mimimi(getMimimiMsg(),getUser());
				dao.save(mimimi);
				setMimimiMsg(null);
			} catch (Exception e){
				addError("Não foi possível Mimimizar.");
				e.printStackTrace();
			}
	}
	
	public void deleteMimimi() {
		try {
			Long id = Long.parseLong(getExternalContext().getRequestParameterMap().get("mimimi"));
			MimimiDao dao = getDao(MimimiDao.class);
			Mimimi mimimi = dao.find(id);
			dao.delete(mimimi);
		} catch (DaoException e) {
			addError("Não foi possível apagar o Mimimi");
		}
	}
	
	public void toggleFollow() {
		try {
			String id = getExternalContext().getRequestParameterMap().get("user");
			UserDao dao = getDao(UserDao.class);
			User user = dao.find(id);
			ConnectionDao connectionDao = getDao(ConnectionDao.class);
			ConnectionId conId = new ConnectionId(dao.find(getUser().getId()),user);
			Connection con = connectionDao.find(conId);
			if(con==null){
				connectionDao.save(new Connection(conId));
			} else {
				connectionDao.delete(con);
			}
		} catch (DaoException e) {
			addError("Não foi possível apagar a conexão...");
		}
	}
	
	public void likeMimimi() {
		try {
			setUser(getDao(UserDao.class).find(getUser().getId()));
			Long id = Long.parseLong(getExternalContext().getRequestParameterMap().get("mimimi"));
			Mimimi mimimi = getDao(MimimiDao.class).find(id);
			LikeId likeId = new LikeId(getUser(),mimimi);
			LikeDao dao = getDao(LikeDao.class);
			Like like = dao.find(likeId);
			if(like==null){
				like = dao.save(new Like(likeId));
			} else {
				dao.delete(like);
			}
		} catch (DaoException e) {
			//If like is duplicated it doesn't matter
		}
	}
	
	public boolean isMimimiLiked(Mimimi mimimi){
		try {
			this.user = getDao(UserDao.class).find(this.user.getId());
			LikeId id = new LikeId(getUser(),mimimi);
			return getDao(LikeDao.class).find(id)!=null;
		} catch (DaoException e) {
			LOGGER.error("Error while finding out if mimimi was liked",e);
			return false;
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
