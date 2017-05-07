package es.WePlanning;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.WePlanning.User.ModuleImages;

@Service
public class ApiService {
	
	@Autowired
	ModuleImages  img;
	
	public ApiService (){
		
	}
	
	public ApiService (ModuleImages img){
		this.img=img;
	}

	public ModuleImages getImg() {
		return img;
	}

	public void setImg(ModuleImages img) {
		this.img = img;
	}
	
	
}
