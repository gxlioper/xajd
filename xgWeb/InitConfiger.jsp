<%@ page language="java" contentType="text/html; charset=gb2312"%>
<%@ page import="org.w3c.dom.*"%>
<%@ page import="java.io.*"%>
<%@ page import="javax.xml.parsers.*"%>
<%@ page import="javax.xml.transform.*"%>
<%@ page import="javax.xml.transform.dom.DOMSource"%>
<%@ page import="javax.xml.transform.stream.StreamResult"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
	<body>
		<%

response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);

String configURI = request.getRealPath("WEB-INF/conf.xml");
//String configURI = "c:\\conf.xml";
String url= request.getRemoteAddr();
if(!url.equalsIgnoreCase("127.0.0.1")){
%>
		<center>
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<font color=red>非法访问！请使用 LocalHost 访问本页面！</font>
		</center>
		<%
}else{
out.print("配置文件路径:<br>" + configURI);
	try {
		Document document;
		String poolforStr = "xsgzgl";
		String poolnameStr = "zfconnpool";
		String urlDStr = "";
		String jdbcdriverStr = "oracle.jdbc.driver.OracleDriver";
		String dbusernameStr = "";
		String dbpasswordStr = "";
		String maxconnectionStr = "";
		String logfileStr = "";
		String ipStr = "";
		String portStr = "";
		String sidStr = "";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		DocumentBuilder builder = factory.newDocumentBuilder();
		document = builder.parse(new File(configURI));
		document.getDocumentElement().normalize();
		
		
		if(request.getParameter("tag") != null && request.getParameter("tag").equalsIgnoreCase("ok")){
			ipStr = request.getParameter("ip");
			portStr = request.getParameter("port");
			sidStr = request.getParameter("sid");
			dbusernameStr = request.getParameter("uname");
			dbpasswordStr = request.getParameter("pass");
			maxconnectionStr = request.getParameter("max");
			logfileStr = request.getParameter("log");
			urlDStr = "jdbc:oracle:thin:@" + ipStr + ":" + portStr + ":" + sidStr;
			//urlDStr = deal.toUtf8String(urlDStr);
			
			DocumentBuilder writer = factory.newDocumentBuilder();
			Document docToWrite = writer.newDocument();
			Element rootW = docToWrite.createElement("configuration");
			docToWrite.appendChild(rootW);
			
			Element title = docToWrite.createElement("database");
			rootW.appendChild(title);
			
			Element poolfor = docToWrite.createElement("poolfor");
			poolfor.appendChild(docToWrite.createTextNode(poolforStr));
			title.appendChild(poolfor);
			
			Element poolname = docToWrite.createElement("poolname");
			poolname.appendChild(docToWrite.createTextNode(poolnameStr));
			title.appendChild(poolname);
			
			Element urlD = docToWrite.createElement("url");
			urlD.appendChild(docToWrite.createTextNode(urlDStr));
			title.appendChild(urlD);
			
			Element jdbcdriver = docToWrite.createElement("jdbcdriver");
			jdbcdriver.appendChild(docToWrite.createTextNode(jdbcdriverStr));
			title.appendChild(jdbcdriver);
			
			Element dbusername = docToWrite.createElement("dbusername");
			dbusername.appendChild(docToWrite.createTextNode(dbusernameStr));
			title.appendChild(dbusername);
			
			Element dbpassword = docToWrite.createElement("dbpassword");
			dbpassword.appendChild(docToWrite.createTextNode(dbpasswordStr));
			title.appendChild(dbpassword);
			
			Element maxconnection = docToWrite.createElement("maxconnection");
			maxconnection.appendChild(docToWrite.createTextNode(maxconnectionStr));
			title.appendChild(maxconnection);
			
			Element logfile = docToWrite.createElement("logfile");
			logfile.appendChild(docToWrite.createTextNode(logfileStr));
			title.appendChild(logfile);
			
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer = tf.newTransformer();
			DOMSource source = new DOMSource(docToWrite);
			transformer.setOutputProperty(OutputKeys.ENCODING, "GB2312");
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			PrintWriter pw = new PrintWriter(new FileOutputStream(configURI));
			StreamResult result = new StreamResult(pw);
			transformer.transform(source, result);
			%>
		<script>
			alert("保存成功！");
			</script>
		<%
		}
		
		Node node = document.getFirstChild().getChildNodes().item(1);
		NodeList list = node.getChildNodes();
		for (int i = 0; i < list.getLength(); i++) {
			String tmpN = list.item(i).getNodeName();
			if(tmpN.equals("url")){
				urlDStr = list.item(i).getFirstChild().getNodeValue();
				if(urlDStr.split(":").length == 6){
					ipStr = urlDStr.split(":")[3].substring(1,urlDStr.split(":")[3].length());
					portStr = urlDStr.split(":")[4];
					sidStr = urlDStr.split(":")[5];
				}
			}
			if(tmpN.equals("dbusername"))dbusernameStr = list.item(i).getFirstChild().getNodeValue();
			if(tmpN.equals("dbpassword"))dbpasswordStr = list.item(i).getFirstChild().getNodeValue();
			if(tmpN.equals("maxconnection"))maxconnectionStr = list.item(i).getFirstChild().getNodeValue();
			if(tmpN.equals("logfile"))logfileStr = list.item(i).getFirstChild().getNodeValue();
		}
%>
		<center>
			<form action="/xgxt/InitConfiger.jsp" method="post">
				<table width="60%" border="1">
					<tr>
						<td colspan="2">
							服务器参数配置
							<input type="hidden" name="tag" value="ok" />
						</td>
					</tr>
					<tr>
						<td align="right">
							数据库服务器IP：
						</td>
						<td align="left">
							<input type="text" name="ip" value="<%=ipStr%>" />
						</td>
					</tr>
					<tr>
						<td align="right">
							数据库连接端口：
						</td>
						<td align="left">
							<input type="text" name="port" value="<%=portStr%>" />
						</td>
					</tr>
					<tr>
						<td align="right">
							数据库SID：
						</td>
						<td align="left">
							<input type="text" name="sid" value="<%=sidStr%>" />
						</td>
					</tr>
					<tr>
						<td align="right">
							数据库登陆名：
						</td>
						<td align="left">
							<input type="text" name="uname" value="<%=dbusernameStr%>" />
						</td>
					</tr>
					<tr>
						<td align="right">
							数据库登陆密码：
						</td>
						<td align="left">
							<input type="password" name="pass" value="<%=dbpasswordStr%>" />
						</td>
					</tr>
					<tr>
						<td align="right">
							允许最大连接数：
						</td>
						<td align="left">
							<input type="text" name="max" value="<%=maxconnectionStr%>" />
						</td>
					</tr>
					<tr>
						<td align="right">
							日志地址：
						</td>
						<td align="left">
							<input type="text" name="log" value="<%=logfileStr%>" />
						</td>
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" value=" 保 存 "
								onclick="document.forms[0].submit();" />
						</td>
					</tr>
				</table>
			</form>
		</center>
		<%
	}catch(Exception e){
		out.println(e.toString());
		e.printStackTrace();
	}
}
%>
	</body>
</html>
