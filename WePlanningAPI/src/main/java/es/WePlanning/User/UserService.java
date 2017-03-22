package es.WePlanning.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	ModuleImages  img;
	
	public UserService (){
		
	}
	
	public UserService (ModuleImages img){
		this.img=img;
	}

	public ModuleImages getImg() {
		return img;
	}

	public void setImg(ModuleImages img) {
		this.img = img;
	}
	
	
}
