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
								<font size="5">�ɹ�</font>
							</center>
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;ѧУ�ں�������21��ķ�չ�����У���ֲ�и�ؽ��к�������������̽�����о���Ϊ�������ḻ�����ƾ����й���ɫ�ĺ����������۶���֮�Ժ��Ŭ���������Ͻ�ʵ����ʹ������Ϊ���ۡ�ѧУ��������ʮ������Ҫ�㣺��1������������׼ȷ�綨�Ǻ�������������չ��ǰ�ᣨ2���ִ���ѧ�����Ǻ��������ĺ������ݣ�3�����ʽ����Ǻ�����������꣨4����ֶ�Ԫ���Ľ����������Ǻ���������չ����Ҫ������5��ѧУ�������Ƹĸ��Ǻ����������뿪չ��Ҫ��6�����������ķ�չ�벻����������г����õĻ�����7��������������ҪĿ��֮һ�����ѧ���ľ�ҵ������8��ѧ���Ļ������Ǻ���������չ�Ļ�������֮һ��9����ҵ֧���Ǻ���������չ�Ļ���������10���쵼�����Ǻ���������չ�Ļ������ϡ�
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;2004��12�£��ڱ����ٿ���ȫ����ѧ���������ƹ����ĳ�������ϣ���У��ѧ���������ĳɹ����飬�õ����ר�ҵĸ߶����ۡ�����У�����Ⱥ��Ĵδ���ѧУ��ȫ���Դ���������ڲ�ѧ������������ּ���ԣ�������ר�ҵ�һ�¿϶���
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;ѧУ����ھ����ѧ�������������������˲ŵ�ģʽ����ѧ���������ǿ�չ˼�������ץ�֡���ѧ����������ʵʩ���ʽ���������ȾŴ��ܡ������ڻ��ȫ����ѧ�����ྺ����28�����ȫ��һ�Ƚ�2����Ƚ�12����Ƚ�6�����Ϻ����ྺ����115����У�һ�Ƚ�16����Ƚ�33����Ƚ�47�ѧУ��ҵ���ܵ����˵�λ�㷺��ӭ����ʮ�塱�ڼ�ƽ����ҵ�ʱ�����95%���ϣ������Ϻ���ͬ���Уǰé��
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</body>
</html>
