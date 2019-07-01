<%@ page contentType="image/jpeg"
	import="java.awt.*,java.awt.image.*,java.util.*,javax.imageio.*,com.sun.image.codec.jpeg.JPEGCodec,com.sun.image.codec.jpeg.JPEGImageEncoder"%>
<%@ page contentType="image/jpeg" import="java.io.OutputStream,xgxt.DAO.PicDAO"%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
int width=65, height=19;
BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
OutputStream os=response.getOutputStream();  
request.setAttribute("gzip", "false"); 
String fdy = request.getAttribute("zgh").toString();
//ImageIO.write(image, "JPEG", response.getOutputStream());
PicDAO picDAO = new PicDAO();
JPEGImageEncoder encoder = picDAO.getPic(fdy,response,request,"fdy");
encoder.encode(image); 
//ImageIO.write(image,"JPEG",os);
os.flush();
os.close();
os=null;
out.clear();
out=pageContext.pushBody();
//response.flushBuffer();
//
//out = pageContext.pushBody();  
//return;  
%>
