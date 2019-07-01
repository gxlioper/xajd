<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@ page import="java.io.*,xgxt.DAO.PicDAO"%>
<jsp:directive.page import="xgxt.action.Base" />
<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.setDateHeader("Expires", 0);
	response.reset();
	
	String zgh = request.getParameter("zgh");
	//ImageIO.write(image, "JPEG", response.getOutputStream());
	PicDAO picDAO = new PicDAO();
	InputStream is = picDAO.getPic(zgh, "fdy");
	
	if (is == null){
		is = new FileInputStream(new File(application.getRealPath("/images/type_pic.gif")));
	}
	
    BufferedImage bufi = ImageIO.read(is);  
    response.reset();  
    response.setContentType("image/gif");  
    ImageIO.write(bufi, "gif", response.getOutputStream());  
    out.clear();
    out=pageContext.pushBody();
	
%>
