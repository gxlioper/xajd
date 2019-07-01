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
								<font size="5">����</font>
							</center>
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;ѧУ����20��Ĳ���ʵ����̽�����γ���һ�׹����ѧ�淶�������������������и�Ч�ȶ��ġ�һ����ѧ�ڡ���ѧ���桱�Ĳ�ѧ�����������л��ơ����ǽ����������Ļ��������ͻ����ں�����Ϊ28���֣���ѧ��ϡ������ƻ������ڹ��������̼ල��ȫ�濼�ˡ����б��ꡢʱ�䱣֤��
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;ѧУ�����һ���׽�Ϊ�ܵ�ϸ�µĹ���ѧ�ڲ���������Ҫ��Ϊ��׼���׶Ρ�ʵʩ�׶Ρ��ܽ�׶��������׶Ρ���������ѧ���İ��ã��ǹ���ѧ��׼���׶ε����ģ����˼ල��ʵʩ�׶ε����⣻�ܽ���û����ǹ���ѧ���ܽ�׶ε�������������
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;ѧУ��������������Ĺ�����ϵ�����ݣ���ָ������ϵ����֯�ṹ������������棬�����߲㡢��֯���ִ�в㡣ѧУ���������ľ��߲���У���������쵼С�飬��У�����Ե����鳤��Ϊ�˱�֤У���������쵼С�������ĸ�������ܹ�˳���᳹����<bean:message key="lable.xsgzyxpzxy" />���ֱ����˺�����������С�飬�����ѧУ��ѧ������������֯�㡣��<bean:message key="lable.xsgzyxpzxy" />�ӽ�ѧ��ѧ�������������Ϲ�����������<bean:message key="lable.xsgzyxpzxy" />��Э��Ա���������ѧУ��ѧ����������ִ�в㡣���᣺��ָ������ϵ����֯�ṹ���������ְ�ܲ��š������񴦡�ѧ������У��������ָ�����ġ�
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;ͨ�������ԡ�����һ֤��Ϊ��Ҫ������ѭ��ʽ������֤��ϵ��ʵ���˺�����������ѧ���ɶ��ԵĿ����������˵�ת�䡣ѧУ�ƶ����йغ���������һϵ�й����ƶȣ�ʹ���������Ĳ������̣����ӹ淶�����Ӻ������γ��˳�̬�����ƶȣ�ʹ���������������Ա�֤��
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;ѧУ�Ĳ�ѧ���������������ڵ�ʵ��̽�����γ����������ɫ��ʵ�������ѧ����ҵ����ҵ����ѧ�����е����Ի�����
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</body>
</html>
