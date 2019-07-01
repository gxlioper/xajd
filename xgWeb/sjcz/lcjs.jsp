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

		<meta name="Copyright" content="正方软件 zfsoft" />
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
								<font size="5">流程</font>
							</center>
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;学校经过20年的不断实践与探索，形成了一套管理科学规范、质量保障有力、运行高效稳定的“一年三学期、工学交替”的产学合作教育运行机制。我们将合作教育的基本特征和基本内涵凝练为28个字：工学结合、完整计划、定岗工作、过程监督、全面考核、略有报酬、时间保证。
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;学校设计了一整套较为周到细致的工作学期操作程序，主要分为：准备阶段、实施阶段、总结阶段这三个阶段。合作教育学生的安置，是工作学期准备阶段的中心；考核监督是实施阶段的主题；总结表彰会则是工作学期总结阶段的凝练与提升。
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;学校构建了三纵三横的管理体系。三纵：是指管理体系中组织结构纵向的三个层面，即决策层、组织层和执行层。学校合作教育的决策层是校合作教育领导小组，由校长亲自担任组长。为了保证校合作教育领导小组做出的各项决策能够顺利贯彻，各<bean:message key="lable.xsgzyxpzxy" />还分别建立了合作教育工作小组，这就是学校产学合作教育的组织层。各<bean:message key="lable.xsgzyxpzxy" />从教学和学生工作两条线上构建覆盖整个<bean:message key="lable.xsgzyxpzxy" />的协调员网，这就是学校产学合作教育的执行层。三横：是指管理体系中组织结构三个横向的职能部门。即教务处、学生处和校合作教育指导中心。
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;通过建立以“三表一证”为主要特征的循环式质量保证体系，实现了合作教育工作学期由定性的考核向定量考核的转变。学校制定的有关合作教育的一系列规章制度，使合作教育的操作过程，更加规范、更加合理，并形成了常态管理制度，使合作教育质量得以保证。
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;学校的产学合作教育经过长期的实践探索，形成了自身的特色，实现了与教学、毕业生就业、大学生科研的良性互动。
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</body>
</html>
