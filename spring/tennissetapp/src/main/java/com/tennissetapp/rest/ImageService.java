package com.tennissetapp.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.tennissetapp.controller.ControllerBase;

@Controller
public class ImageService extends ControllerBase{
//	private DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
	
//	@Autowired
//	private HibernatePersistenceManager h;
	
//	@Autowired
//	private ModelService bean;
	
	@RequestMapping(value = "/imageservice", method = {RequestMethod.POST,RequestMethod.GET})
	public void handleRequest(HttpServletRequest request,HttpServletResponse response) 
	throws ServletException, IOException {
		try {
			streamImageFromSession(request, response);

		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
	protected static void writeFile(String path, OutputStream out) throws IOException {
		File file = new File(path);
		if (file.exists()) {
			InputStream in = new FileInputStream(file);

			int b;
			while ((b = in.read()) != -1) {
				out.write(b);
			}
			in.close();
			out.close();
		}
	}

//	protected void streamImageDisk(HttpServletRequest request,HttpServletResponse response) throws IOException {
//		String fileName = request.getParameter("file");
//		String path = Utils.getProperty("branchitup.rootDir") + "/" + fileName;
//		System.out.println("imageservice: " + path);
//		File file = new File(path);
//		if (file.exists()) {
//			InputStream in = new FileInputStream(file);
//			OutputStream out = response.getOutputStream();
//
//			int b;
//			while ((b = in.read()) != -1) {
//				out.write(b);
//			}
//
//			// out.flush();
//			System.out.println("finished writing image back to front end");
//			in.close();
//			out.close();
//		}
//	}
	
//	protected void generateAndStreamCaptcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String challenge = UUID.randomUUID().toString().substring(0,8);
//		request.getSession().setAttribute("challenge", challenge);
//		BufferedImage image = FileUtilities.generateCaptcha(challenge);
//		
//		response.setContentType("image/gif");
////		response.setContentLength(image);
//		ImageIO.write(image, "gif", response.getOutputStream());
//	}

//	
	
	/**
	 * pops an image from the session and writes it to the output stream of the
	 * response
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	protected void streamImageFromSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String fileItemId = request.getParameter("id");
		if (fileItemId != null) {
			FileItem fileItem = (FileItem)request.getSession().getAttribute(fileItemId);
			System.out.println("--U->ImageService.streamImageFromSession: " + fileItem + ", ");
			if (fileItem != null) {
				int length = IOUtils.copy(fileItem.getInputStream(), response.getOutputStream());
				response.setContentLength(length);
				response.setContentType("image/" + request.getParameter("fileType"));
				
				request.removeAttribute(fileItemId);
			} else {
				org.apache.log4j.Logger.getLogger(this.getClass()).warn("imageservice fileItem is null " + request.getParameterMap());
				System.out.println("imageservice.fileItemId is null, lets forward the request to the real image file::::"
					+ "images/"
					+ fileItemId
					+ "."
					+ request.getParameter("fileType"));
				response.sendRedirect("images/" + fileItemId + "." + request.getParameter("fileType"));
			}
		} else {
			System.out.println("imageservice.fileItemId is null, lets forward the request to the real image file");
			response.sendRedirect("/images/image_missing.png");
		}
	}
}
//	protected void streamImageFromSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
//		String fileItemId = request.getParameter("id");
//		if (fileItemId != null) {
//			FileItem fileItem = (FileItem)request.getSession().getAttribute(fileItemId);
//			this.logger.debug("--U->ImageService.streamImageFromSession: " + fileItem + ", ");
//			if (fileItem != null) {
//				// BufferedImage bufferedImage =
//				// ImageIO.read(fileItem.getInputStream());
//
//				//if bytes is null, possible because tmp directory is not set, so it tries to grab the file
//				//from a tomcat-node instance
//				//try turning off one of the tomcat instances and try again to confirm that this is the problem
//				byte[] bytes = fileItem.get();
//				if (bytes != null && bytes.length > 0) {
//					// response.setContentType("image/" +
//					// Utils.parseExtention(fileItem.getName()));
//					response.setContentType("image/" + request.getParameter("fileType"));
//					// System.out.println("imageservice::::setContentType::" +
//					// "image/" + request.getParameter("fileType"));
//					response.setContentLength(bytes.length);
//					
//					response.getOutputStream().write(bytes);
//					request.removeAttribute(fileItemId);
//				}
//			} else {
//				org.apache.log4j.Logger.getLogger(this.getClass()).warn("imageservice fileItem is null " + request.getParameterMap());
//				System.out.println("imageservice.fileItemId is null, lets forward the request to the real image file::::"
//					+ "images/"
//					+ fileItemId
//					+ "."
//					+ request.getParameter("fileType"));
//				response.sendRedirect("images/" + fileItemId + "." + request.getParameter("fileType"));
//			}
//		} else {
//			System.out.println("imageservice.fileItemId is null, lets forward the request to the real image file");
//			response.sendRedirect("/images/image_missing.png");
//		}
//	}
//}
