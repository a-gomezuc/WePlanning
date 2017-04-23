package es.WePlanning.User;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;
@Component
public class ModuleImages {
	
	//private String filePath="src/main/resources/static/planImages/";
	private String fileName;
	
	public ModuleImages(){
		
	}
	public ModuleImages(String fileName){
		this.fileName=fileName;
		//this.filePath=filePath;
	}
	
	public void handleFileDownload(String fName, HttpServletResponse res)throws FileNotFoundException, IOException{
		
		File file= new File(filePath);
		
		if (file.exists()) {
			res.setContentType("image/jpg");
			res.setContentLength(new Long(file.length()).intValue());
			FileCopyUtils.copy(new FileInputStream(file), res.getOutputStream());
		} else {
			res.sendError(404, "File" + fileName + "(" + file.getAbsolutePath() + ") does not exist");
		}
		
	}
	
	public boolean changePhoto(String id,MultipartFile file){
		fileName = "newPhoto"+  id  +  ".jpg";
		
		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

			} catch (Exception e) {

				return false;
			}
		}
		return true;
	}
	
	
	public boolean changePhotoPlan(long id,MultipartFile file){
		String FILES_FOLDER = "src\\main\\resources\\static\\planImages";
		fileName = "profile"+  id  +  ".jpg";
		
		if (!file.isEmpty()) {
			try {

				File filesFolder = new File(FILES_FOLDER);
				if (!filesFolder.exists()) {
					filesFolder.mkdirs();
				}

				File uploadedFile = new File(filesFolder.getAbsolutePath(), fileName);
				file.transferTo(uploadedFile);

			} catch (Exception e) {

				return false;
			}
		}
		return true;
	}
	
	/*public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}*/
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
