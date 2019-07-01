<%@ page contentType="image/jpeg"%>
<%@ page language="java"%>
<%@ page import="java.io.*"%>
<%@ page import="xgxt.base.*"%>
<%@ page import="java.sql.*"%>
<jsp:directive.page import="javax.sql.DataSource"/>
<jsp:directive.page import="xgxt.DAO.DBPool"/>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html:html locale="true">
<head>
	<title>xszp.jsp</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
</head>

<%
    response.setHeader("Pragma","No-cache");
    response.setHeader("Cache-Control","no-cache");
    response.setDateHeader("Expires", 0);
  %>

<%
       String xh = request.getParameter("xh");
       ResultSet rs=null;
       Connection con=null;
       PreparedStatement pst=null;
       byte bu[]=new byte[205600];
       DataSource db = DBPool.getPool();
       con = db.getConnection();
       try{
           pst=con.prepareStatement("select xh,zp from xszpb where xh=?");
           pst.setString(1,xh);
           rs = pst.executeQuery();
           while(rs.next())
                {
                        response.reset();       
                        InputStream in2=rs.getBinaryStream(2);
                        in2.read(bu);
                        response.setContentType("jpeg");
                        in2.close();
                }
        response.getOutputStream().write(bu);                         
        rs.close();  
        pst.close();
        con.close();
       }catch (SQLException e){
          e.printStackTrace();
        }
      %>
<body>
</body>
</html:html>
