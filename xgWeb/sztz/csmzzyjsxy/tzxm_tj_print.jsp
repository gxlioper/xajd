<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/pjpyFunction.js"></script>

		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<!-- 打印控件begin -->
		<object id="WebBrowser" width=0 height=0
			classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2"></object>
		<style media='print'>
			.noPrin{display:none;}
		</style>
		<!-- end -->
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/xszzFunction.js"></script>
<script language="javascript">
function expTabT(the_table1, the_table2, the_table3, the_table4,tabTit, titSpan) {
	 
	var table_title = (titSpan == null || titSpan == "") ? tabTit : document.getElementById(titSpan).outerHTML;	
	var the_content = "<style media='print'>\n";
	the_content += ".noPrin{\n";
	the_content += "display:none;}\n";
	the_content += "</style>\n";
	the_content += "<link rel='stylesheet' rev='stylesheet' href='skin1/style/main.css' type='text/css' media='all' />\n";
	the_content += "<object id=\"WebBrowser\" width=0 height=0 classid=\"CLSID:8856F961-340A-11D0-A96B-00C04FD705A2\"></object>\n";
	the_content += "<script language='javascript' src='js/function.js'>";
	the_content += "</sc";
	the_content += "ript>\n";
	the_content += "<center><h3><b>";
	the_content += table_title;
	the_content += "</b></h3>";		
	the_content += document.all(the_table1).outerHTML;	
	the_content += "<div style='page-break-before:always;'>&nbsp;</div>";
	the_content += document.all(the_table2).outerHTML;	
	the_content += "<div style='page-break-before:always;'>&nbsp;</div>";
	the_content += document.all(the_table3).outerHTML;	
	the_content += "<div style='page-break-before:always;'>&nbsp;</div>";
	the_content += document.all(the_table4).outerHTML;	
	the_content = the_content.replace(/ style=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ onclick=\"[^\"]*\"/g, "");
	the_content = the_content.replace(/ mode=\"(false|true)"/g, "");
	the_content = the_content.replace(/ oBgc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/ oFc=\"[\w#\d]*\"/g, "");
	the_content = the_content.replace(/<span>(5|6)<\/span>/gi, "");
	the_content = the_content.replace(/<DIV contentEditable=false>(.*)<\/DIV>/ig, "$1");
	the_content += "\n<br><div class='noPrin'><input type='button' class='button2' value='页面设置' onclick=\"WebBrowser.ExecWB(8,1)\">";
	the_content += "<input type='button' class='button2' value='打印预览' onclick=\"WebBrowser.ExecWB(7,1)\">";
	the_content += "<input type='button' class='button2' value='直接打印' onclick=\"WebBrowser.ExecWB(6,6)\">";
	the_content += "<\/div>";
	//confirm(the_content);
	var newwin = window.open("about:blank", "_blank", "");
	newwin.document.open();
	newwin.document.write(the_content);
	newwin.document.close();
	newwin = null;
}

   function jd(){
		if($("jd")){
			$("jd").focus();
		}
	}
	</script>
	<body onload="jd()">
		<center>			
			<br><html:form action="/csmz_sztz.do?method=tzxm_tj" method="post">		
					<!-- 获取焦点 为纪念伟大的骆 -->		
					<input type="text" id="jd" name="jd"  
						style="text-align:left;ime-mode:disabled;width:100%;border:0;background-color: white" readonly="readonly"	/>								
					
					<table width="100%" border="0" id="theTable1" class="">
					
						<tr>
							<td>
								<div align="center">
									<h1>
										<strong> ${xxmc}
											<br /> <br /> 大学生素质拓展计划 <br /><br />  项目申请书 <br /><br /></strong>
									</h1>
								</div>
							</td>
						<tr>
							<td>
								<table width="100%">
									<tr height="50px">
										<td align="right">

											<font size="5" > 项 目 申 请 部 门：</font>
										</td>
										<td>
											<u>&nbsp;&nbsp;<font size="3"><bean:write
														name="sqbm" scope="request" />
											</font> &nbsp;&nbsp;</u>

										</td>
									</tr>
									<tr  height="50px">
										<td align="right">

											<font size="5">项 目 申 请 学 期：</font>
										</td>
										<td>
											<u>&nbsp;&nbsp;<font size="3"><bean:write
														name="xn" scope="request" />学年&nbsp;第 <bean:write
														name="xq" scope="request" /> 学期 &nbsp;&nbsp; </font>
											</u>

										</td>
									</tr>
									<tr  height="50px">
										<td align="right">
											<font size="5">项 目 申 请 日 期：</font>
										</td>
										<td>
											<u>&nbsp;&nbsp;<font size="3"><bean:write
														name="sqrq" scope="request" />
											</font> &nbsp;&nbsp;</u>

										</td>
									</tr>
									<tr  height="50px">
										<td align="right">
											<font size="5">项 目 申 请 类 别：</font>
										<td>
										<td>
										</td>
									</tr>
									<tr >
										<td align="right">
											<font size="3">思想政治与道德素养&nbsp;&nbsp;</font>
										</td>
										<td align="left">
											<font size="3">社会实践与志愿服务</font>
										</td>
									</tr>
									<tr>
										<td align="right">
											<font size="3">科技学术与创新创业&nbsp;&nbsp;</font>
										</td>
										<td align="left">
											<font size="3">文化艺术与身心发展</font>
										</td>
									</tr>
									<tr>
										<td align="right">
											<font size="3">社团活动与社会工作&nbsp;&nbsp;</font>
										</td>
										<td align="left">
											<font size="3">技能培训及其他</font>
										</td>
									</tr>
								</table>
						</td>
						</tr>		
						<tr>
						<td align="center">
							<br>
							<br>
							<br>
							<br>
							<font size="3"><bean:write name="xxmc" scope="request" />
							大学生素质拓展认证中心制</font>
							<br>
							<br>
						<div align="center"><font size="3">年&nbsp;&nbsp;&nbsp;&nbsp;月&nbsp;&nbsp;&nbsp;&nbsp;日</font></div>
						</td>
					</tr>
					</table>
														
					<table width="100%" border="0" id="theTable2">
							<tr>
								<td>
									
									<div align="center">
										<font size="3"><strong>填 表 说 明</strong> </font>
									</div>
									<br>
									<br>
									<p align="left">
										<font size="3">一、 本表所列项目，请按规定样式打印；</font>
									</p>
									<p align="left">
										<font size="3">二、 项目申请部门填写系部全称；</font>
									</p>
									<p align="left">
										<font size="3">三、 项目名称不超过14个字（包括标点符号），“活动时间”填写活动举行的时间；</font>
									</p>
									<p align="left">
										<font size="3">四、 请按项目开展的日期将项目顺序排列，再统计每个类别的活动数量；</font>
									</p>
									<p align="left">
										<font size="3">五、
											本表一式三份，请双面打印，系部自留一份，其余两份报送<bean:message key="lable.xsgzyxpzxy" />大学生素质拓展认证中心办公室；</font>
									</p>
									<p align="left">
										<font size="3">六、
											系部素质拓展项目负责人签字栏请党总支副书记签字，系部素质拓展领导小组负责人签字栏请系主任签字；</font>
									</p>
									<p align="left">
										<font size="3">七、 本表格需如实填写，如有弄虚作假系部责任自负。</font>
									</p>
									<br>
									<br>
									<br>
									<br>
								</td>
							</tr>							
						</table>
														
								<table class="printstyle" width="100%" id="theTable3">
									<thead>
										<tr>
											<td>
												序号
											</td>
											<td>
												项目类别
											</td>
											<td>
												项目名称
											</td>
											<td>
												活动时间
											</td>
											<td>
												项目级别
											</td>
											<td>
												活动对象
											</td>
										</tr>
									</thead>
									<logic:notEmpty name="rs2">
										<logic:iterate name="rs2" id="s">
											<tr>
												<td>
													<logic:iterate id="v" name="s" offset="0" length="1">
														<bean:write name="v" />
													</logic:iterate>
												</td>
												<logic:iterate id="v" name="s" offset="1">
													<td>
														<bean:write name="v" />
													</td>
												</logic:iterate>
											</tr>
										</logic:iterate>										
									</logic:notEmpty>
								</table>
				
							<table width="100%" class="printstyle" id="theTable4">
								<tr>
									<td>
										&nbsp;系部素质拓展项目主管部门审核意见：
										<br />
										<br />
										<br />
										<br />
										<br />
										<div align="right">
											负责人（签字）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<br />
										<div align="right">
											（系部素质拓展认证中心公章）
										</div>
										<br />
										<div align="right">
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日 &nbsp;&nbsp;
										</div>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;系部素质拓展领导小组审核意见：
										<br />
										<br />
										<br />
										<br />
										<br />
										<div align="right">
											负责人（签字）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<br />
										<div align="right">
											（系部素质拓展领导小组公章）
										</div>
										<br />
										<div align="right">
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日 &nbsp;&nbsp;
										</div>

									</td>
								</tr>
								<tr>
									<td>
										&nbsp;<bean:message key="lable.xsgzyxpzxy" />素质拓展项目主管部门审核意见：
										<br />
										<br />
										<br />
										<br />
										<br />
										<div align="right">
											负责人（签字）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<br />
										<div align="right">
											（<bean:message key="lable.xsgzyxpzxy" />素质拓展认证中心公章）
										</div>
										<br />
										<div align="right">
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日 &nbsp;&nbsp;
										</div>

									</td>
								</tr>
								<tr>
									<td>
										&nbsp;<bean:message key="lable.xsgzyxpzxy" />素质拓展领导小组审核意见:
										<br />
										<br />
										<br />
										<br />
										<br />
										<div align="right">
											负责人（签字）:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										</div>
										<br />
										<div align="right">
											（<bean:message key="lable.xsgzyxpzxy" />素质拓展领导小组公章）
										</div>
										<br />
										<div align="right">
											<u>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</u>年
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>月
											<u>&nbsp;&nbsp;&nbsp;&nbsp;</u>日 &nbsp;&nbsp;
										</div>

									</td>
								</tr>
							</table>
												
				<div class="buttontool" align="center">
						<button class="btn_dy" name="button_print" style="width:80px"
				onClick="expTabT('theTable1','theTable2','theTable3','theTable4','')">
				打 印
			</button>														
							
			</html:form>
		</center>
		<script type="text/javascript" src="js/bottomButton.js"></script>				
	</body>
</html>
