<%@ page language="java" contentType="text/html; charset=GBK"%>

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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link rel="stylesheet" rev="stylesheet" href="style/calendar.css"
		type="text/css" media="all" />
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body bgcolor="#99CCFF">
		<script language="javascript" src="style/function.js"></script>
		<script type="text/javascript" src="style/calendar.js"></script>
		<script type="text/javascript" src="style/calendar-zh.js"></script>
		<script type="text/javascript" src="style/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div id="title">
				<div id="title_l"></div>
				<div id="title_m">
					<span id="tipFollow"></span>
				</div>
				<div id="title_r"></div>
			</div>
			<div id="main" style="heigth:100px;">
				<div id="search_m" style="heigth:100px;">
					<div id="result">
						<div class="searchcontent">
							<center>
								<font size="5">���</font>
							</center>
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;1985�꣬ѧУ������ô���¬��ѧ��һ����ѧ�ڣ���ѧ���桱�ġ��������Ρ�ģʽ�������ڹ��ڽ��к�������ģʽ��ʵ����̽����1988�꣬�����ҽ�ί�͹����⾭óί����׼,
							ѧУ���ܼ��ô���ʿ�����CIDA�����������μ��ˡ��мӴ�ѧУ��������Ŀ����CCULP�����ڼ��ô���¬��ѧ��Э���£�ѧУ����ҹ����飬�����ˡ�һ����ѧ�ڡ���ѧ���桢���ĺ��������Ե㣬���ҹ����ȴ����˾����й���ɫ�ġ�һ����ѧ���ơ���������ģʽ��
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;ͨ���μ�ȫ��������ѧ�滮�������ص���⡶��ѧ�к��������о�������ѧ�������������塱�Ե㣩������ѧ�к��������Ľ���ģʽ�Ͱ�ѧģʽ���о�������ѧ�������������塱�Ե㣩���ͳɹ��������д�����Ŀ����ѧ�к����������������˲ŵ�ʵ�������������ƽ��˾����й���ɫ�ġ�һ����ѧ�ڣ���ѧ���桱�Ĳ�ѧ��������ģʽ����һģʽ�ѳ�Ϊ��У����Ҫ����ɫ֮һ��
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;ѧУ��ѧ��������
							��һ����ѧ�ڡ��Ƽ�һѧ������������ѧ�ں�һ������ѧ����ɡ�ѧ��������ѧ���У���У���пγ�ѧϰ���ڹ���ѧ���У����ԡ�ְҵ�ˡ���ݲμ�У�⹤������һ����ѧ�ڡ��Ƶĺ�������ģʽ����ְҵΪ���򣬳������У�ڡ������ֲ�ͬ�Ľ�����������Դ�����Կ��ý�ѧΪ����ѧУ������ֱ�ӻ�ȡʵ�ʾ����У�⹤���л���ϣ��ᴩ�ڴ�ѧ����������������֮�У����ǶԴ�ͳ����ģʽ����Ҫͻ�ơ�
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</body>
</html>
