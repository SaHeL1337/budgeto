package service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;


public class Patch {
	
	private static String TMP_DIR = "C:\\Users\\SaHeL\\Desktop\\extracted";
	private static String TRANSFER_DIR = "C:\\Users\\SaHeL\\Desktop\\transfer";
	private static String BACKUP_DIR = "C:\\Users\\SaHeL\\Desktop\\backup";
	private static String APPLICATION_DIR = "C:\\Users\\SaHeL\\Desktop";

	public enum PatchStatus{
		UPLOADED, READY, PATCHED, ROLLED_BACK
	}
	
	private int id;
	private String name;
	private MultipartFile file;
	private List<String> fileList;
	private PatchStatus status;
	private Date date;
	
	public Patch(String p_name, MultipartFile p_file, int p_id) {
		if(p_name.equals("")) {
			this.name = p_file.getOriginalFilename();
		}else {
			this.name = p_name;
		}
		this.file = p_file;
		this.fileList = new ArrayList<String>();
		this.status = PatchStatus.UPLOADED;
		this.date = new Date();
		this.id = p_id;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public PatchStatus getStatus() {
		return this.status;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public int getID() {
		return this.id;
	}
	
	
	
}


