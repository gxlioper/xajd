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
								<font size="5">简介</font>
							</center>
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;1985年，学校借鉴加拿大滑铁卢大学“一年三学期，工学交替”的“薄三明治”模式，率先在国内进行合作教育模式的实践与探索。1988年，经国家教委和国家外经贸委的批准,
							学校接受加拿大国际开发署（CIDA）的资助，参加了“中加大学校际联合项目”（CCULP）。在加拿大滑铁卢大学的协助下，学校结合我国国情，进行了“一年三学期、工学交替、”的合作教育试点，在我国首先创建了具有中国特色的“一年三学期制”合作教育模式。
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;通过参加全国教育科学规划教育部重点课题《产学研合作教育研究》（产学合作教育“八五”试点）、《产学研合作教育的教育模式和办学模式的研究》（产学合作教育“九五”试点），和成功主持世行贷款项目《产学研合作教育培养创新人才的实践》，构建并推进了具有中国特色的“一年三学期，工学交替”的产学合作教育模式，这一模式已成为我校最主要的特色之一。
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;学校产学合作教育
							“一年三学期”制即一学年由两个理论学期和一个工作学期组成。学生在理论学期中，在校进行课程学习；在工作学期中，则以“职业人”身份参加校外工作。“一年三学期”制的合作教育模式，以职业为导向，充分利用校内、外两种不同的教育环境和资源，把以课堂教学为主的学校教育和直接获取实际经验的校外工作有机结合，贯穿于大学生的整个培养过程之中，这是对传统教育模式的重要突破。
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</body>
</html>
