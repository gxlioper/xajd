<%@page contentType="text/html; charset=GBK"%>
<%@page language="java" 
		 errorPage="/error.jsp" 
		 import="java.sql.Connection,
		 		 java.sql.DriverManager,
		 		 java.sql.ResultSet,
		 		 java.sql.Statement"
%>
<html>
<head>
<title>������ҵ��ѧ..Welcome to Beijing Forestry University</title>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link id="csss" rel="stylesheet" rev="stylesheet" href="/xgxt/skin2/style/main.css" type="text/css" media="all" />
</head>
<body>
<center>
<%
  Connection con=null;
  Statement stmt=null;
  ResultSet rs=null;
  try{
   Object obj = Class.forName("oracle.jdbc.driver.OracleDriver");
   obj = ((Class) obj).newInstance();
   con=DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:ora805","zf01","zf0123");
   stmt=con.createStatement();
   rs=stmt.executeQuery("select count(*) from view_newstureportinfo where xybd='��' or yybd='��' or stbd='��' or ssbd='��'");
   if(rs.next())
   {
%>
<strong><font color="#009900">����ӭ��ϵͳʵʱ��������ǰ��������������<font color="#FF0000"><%=rs.getInt(1)%></font>��</font></strong>
<%    
   }
  }catch(Exception e){
   e.printStackTrace() ;
  }finally{
   rs.close();
   stmt.close();
   con.close();
  }
%>
</center>
</body>
</html>
