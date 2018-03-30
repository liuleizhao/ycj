package com.cs.mvc.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cs.common.utils.DateUtils;
import com.cs.common.utils.ImageUploadUtil;


@Controller
@RequestMapping(value = "/web")
public class UploadController extends BaseController {
	
	/**
	 * 上传文件 
	 * @param request
	 * @param response
	 * @return
	 * @date 2017-3-3 下午5:59:31
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody LinkedList<FileMeta> upload(MultipartHttpServletRequest request,
			HttpServletResponse response) {
		LinkedList<FileMeta> files = new LinkedList<FileMeta>();
		//获取所有文件
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf = null;
		FileMeta fileMeta = null;
		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());

			fileMeta = new FileMeta();
			fileMeta.setFileSize(mpf.getSize() / 1024 + " Kb");
			fileMeta.setFileType(mpf.getContentType());
			try {
				String originalFilename = mpf.getOriginalFilename();
				//fileMeta.setBytes(mpf.getBytes());暂时不添加字节数据
				//复制文件到存放路径（确保该目录一定存在）
				String suffix = originalFilename.substring(originalFilename.lastIndexOf(".")).toLowerCase();
               
				String fileName = DateUtils._YMDHMS_DATETIME_FORMAT.format(new Date()) + suffix;
				String filePath = request.getSession().getServletContext().getRealPath("/static/upload/"+fileName);
				fileMeta.setFileName(fileName);
				fileMeta.setFilePath(request.getContextPath()+"/static/upload/"+fileName);
				FileCopyUtils.copy(mpf.getBytes(), new FileOutputStream(filePath));
				files.add(fileMeta);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return files;
	}
	
	/**
	 * ckeditor图片上传
	 * 
	 * @Title imageUpload
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/imageUpload", method = RequestMethod.POST)
	public void imageUpload(HttpServletRequest request, HttpServletResponse response) {
	    String DirectoryName = "static/upload";
	    try {
	    	ImageUploadUtil.ckeditor(request, response, DirectoryName);
	    } catch (IllegalStateException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	/**
	 * 获取指定名称的附件
	 * @param response
	 * @param value
	 * @date 2017-3-3 下午5:58:59
	 */
	@RequestMapping(value = "/get/{value:.*}", method = RequestMethod.GET)
	public void get(HttpServletRequest request,HttpServletResponse response, @PathVariable String value) {
		File file = new File(request.getSession().getServletContext().getRealPath("/")+value);
		try {
			
			response.setContentType(Files.probeContentType(file.toPath()));
			response.setHeader("Content-disposition", "attachment; filename=\""
					+ file.getName() + "\"");
			FileCopyUtils.copy(Files.readAllBytes(file.toPath()), response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class FileMeta {
	private String fileName;
	private String fileSize;
	private String fileType;
	private String filePath;
	private byte[] bytes;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	
}