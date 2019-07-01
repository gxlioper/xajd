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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<base target = "_self" />
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma","No-cache");
		response.setHeader("Cache-Control","no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="xyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
			<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
			<script type="text/javascript" src="js/AjaxFunction.js"></script>
		
		<html:form action="/czxxGygl" method="post">
			<input type="hidden" id="userType" name="userType" value="${userType}" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：公寓管理 - 信息维护 - 宿舍生活规范分 - 自动计算
				</div>
			</div>				 
				<table width="100%" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								规范分自动计算
							</td>
						</tr>
					</thead>
					<tr>
						
                        <td height="22" align="right">
							学年：
						</td>
						<td height="22" align="left">
							<html:select property="xn" styleId="xn">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
						</td>
											
					</tr>
					<tr>
					    <td height="22" align="right">
							学期：
						</td>
						<td height="22" align="left">
							<html:select property="xq" styleId="xq">
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
						</td>						
																	
					</tr>	
					<tr>
					<td height="22" align="right">
                        <bean:message key="lable.xsgzyxpzxy" />：
					</td>					
                    <td height="22" align="left">
						<html:select property="xydm" styleId="xy">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>	
				    </td>							                        					
					</tr>
																			
				</table>
				<br />
			<div class="buttontool" id="button" align="center">
				<button class="button2" onclick="toAtcout()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
			<div>
				<ul style="color: red">
					<li>
						宿舍生活规范分=内务分*60%+ 纪律分*40%；
					</li>
					<li>
						内务分=一个寝室在每学期内有三次及以上小于80分时，该寝室各成员的该学期内务分数为0分，
						小于三次的按照内务分算术平均进行计算；
					</li>
					<li>
						纪律分=在该学期内有住宿纪律违纪两次及两次以上时，则该学期该生纪律分为零分；
						唯有一次违纪，则纪律分为50分；没有违纪，则纪律分为100分，满分为100分；
					</li>
				</ul>
			</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("计算完成！");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("计算失败！");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function toAtcout(){
	        var xydm = $("xy").value;
	        var xymc = document.forms[0].xy.options[document.forms[0].xy.selectedIndex].text;
	        var xn = $("xn").value;
	        var xqmc = document.forms[0].xq.options[document.forms[0].xq.selectedIndex].text;
	        var clin="";
	        if(xydm==""){
	           clin="此操作将对"+xn+"学年"+xqmc+"学期"+"全校学生\n\n\"宿舍生活规范分\"进行自动计算， \n\n可能花费数分钟时间。\n\n确定要进行自动计算吗？";
	        }else{
	           clin="此操作将对"+xn+"学年"+xqmc+"学期\n\n\'"+xymc+"\'学生\n\n\"宿舍生活规范分\"进行自动计算， \n\n确定要进行自动计算吗？";
	        }
	        if(confirm(clin)){
	          refreshForm('/xgxt/czxxGygl.do?method=gffAutoCount&doType=doCout');
	          var dd_html = "";
		      dd_html += "<div><td height='60' align='center'><font color='red'>正在处理中，请稍候......<br><br></div>";		     			
	          showDiv(dd_html, 300, 120);
	        }
	     }
	</script>
</html>
