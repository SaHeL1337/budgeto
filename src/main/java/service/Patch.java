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

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.FileHeader;

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
		
		unzip();
		createBackup();		
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
	
	public void unzip() {
	    File  zip = null;
	    Calendar calendar = Calendar.getInstance();
		try {
		    zip = File.createTempFile(this.getName(), "_" + this.getID() + ".zip");
		    FileOutputStream o = new FileOutputStream(zip);
		    IOUtils.copy(file.getInputStream(), o);
		    o.close();
	        ZipFile zipFile = new ZipFile(zip);
	        zipFile.extractAll(TMP_DIR);
	        
	        int year = calendar.get(Calendar.YEAR);
	        int month = calendar.get(Calendar.MONTH);
	        int day = calendar.get(Calendar.DAY_OF_MONTH);
	        FileUtils.copyFile(zip, new File(TRANSFER_DIR + "/" + year + month + day + "/" + this.getID() + "_" + this.getName()));
	        
	        List fileHeaderList = zipFile.getFileHeaders();

	        for (int i = 0; i < fileHeaderList.size(); i++) {
	            FileHeader fileHeader = (FileHeader)fileHeaderList.get(i);              
	            String fileName = fileHeader.getFileName();
	            fileList.add(fileName);
	        }
	    } catch (ZipException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(zip != null) zip.delete();
	    }
		
		
	}
	
	public void applyPatch() {
		for(String file : fileList) {
			File applicationFile = new File(APPLICATION_DIR + "/" + file);
			File patchFile = new File(TMP_DIR + "/" + file);
			if(applicationFile.exists() && !applicationFile.isDirectory()) {
				try {
					FileUtils.copyFile(patchFile, applicationFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.status = PatchStatus.PATCHED;
	}
	
	public void rollback() {
		for(String file : fileList) {
			File backupFile = new File(BACKUP_DIR + "/" + file);
			File applicationFile = new File(APPLICATION_DIR + "/" + file);
			if(applicationFile.exists() && !applicationFile.isDirectory()) {
				try {
					FileUtils.copyFile(backupFile, applicationFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.status = PatchStatus.ROLLED_BACK;
	}
	
	public void createBackup() {
		for(String file : fileList) {
			File applicationFile = new File(APPLICATION_DIR + "/" + file);
			File backupFile = new File(BACKUP_DIR + "/" + file);
			if(applicationFile.exists() && !applicationFile.isDirectory()) {
				try {
					FileUtils.copyFile(applicationFile, backupFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		this.status = PatchStatus.READY;
	}
}


