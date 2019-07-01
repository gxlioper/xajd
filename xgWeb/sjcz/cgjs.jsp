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
								<font size="5">成果</font>
							</center>
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;学校在合作教育21年的发展历程中，坚持不懈地进行合作教育的理论探索和研究，为构建、丰富和完善具有中国特色的合作教育理论而持之以恒地努力，并不断将实践并使其上升为理论。学校逐步凝炼出十个理论要点：（1）合作教育的准确界定是合作教育健康发展的前提（2）现代科学技术是合作教育的核心内容（3）素质教育是合作教育的灵魂（4）坚持多元化的教育质量观是合作教育发展的重要条件（5）学校教育体制改革是合作教育深入开展的要求（6）合作教育的发展离不开社会主义市场经济的环境（7）合作教育的主要目标之一是提高学生的就业能力（8）学生的积极性是合作教育发展的基本动力之一（9）企业支持是合作教育发展的基本条件（10）领导重视是合作教育发展的基本保障。
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;2004年12月，在北京召开的全国产学合作教育推广中心成立大会上，我校产学合作教育的成功经验，得到与会专家的高度评价。汪泓校长曾先后四次代表学校在全国性大会上作关于产学合作教育的主旨发言，获得与会专家的一致肯定。
							<br></br>
							&nbsp;&nbsp;&nbsp;&nbsp;学校充分挖掘出产学合作教育是培养创新人才的模式、产学合作教育是开展思想教育的抓手、产学合作教育是实施素质教育的载体等九大功能。五年内获得全国大学生各类竞赛奖28项，其中全国一等奖2项，二等奖12项，三等奖6项，获得上海各类竞赛奖115项。其中，一等奖16项，二等奖33项，三等奖47项。学校毕业生受到用人单位广泛欢迎，“十五”期间平均就业率保持在95%以上，名列上海市同类高校前茅。
						</div>
					</div>
				</div>
			</div>
		</html:form>
	</body>
</html>
