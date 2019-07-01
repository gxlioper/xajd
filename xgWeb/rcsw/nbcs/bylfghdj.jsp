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
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script language="javascript" src="js/xgutil.js"></script>
	<script type="text/javascript">
		function modi(){
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}
			var url = "bylfgl.do?method=bylfgh&pkValue=";
			var pkValue = curr_row.getElementsByTagName("input")[0].value;
			url += pkValue;
			showTopWin(url,800,600);
		}
		
		function print(){
			var url = "bylfgl.do?method=printBylfghllb&nd=" + val('nd') + "&xydm=" + val('xy');
			window.open(url);
		}
	</script>
	<body>
		<html:form action="/bylfgl.do" method="post">	
			<input type="hidden" id="realTable" value="${realTable}"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置: 日常事务 - 毕业典礼服装管理 - 毕业典礼服装归还登记
				</div>
			</div>
				<div class="rightcontent">
					<fieldset>
						<legend>
							查 询
						</legend>
						<table width="100%" class="tbstyle">
							<thead>
								<tr>
									<td align="left">
										年度：
										<html:select property="nd">
										<html:options collection="ndList" labelProperty="nd" property="nd"/>
										</html:select>
										&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
										<html:select property="xydm" styleId="xy">
											<html:option value=""></html:option>
											<html:options collection="xyList" property="xydm" labelProperty="xymc"/>
										</html:select>									
									</td>
									<td width="10" rowspan="3" align="center" valign="middle">
										<input type="hidden" name="go" value="a" />
										<button type="button" class="button2" style="height:40px" id="search_go"
											onclick="allNotEmpThenGo('bylfgl.do?method=bylfghdjcx');">
											查询
										</button>
									</td>
								</tr>
							</thead>
						</table>
					</fieldset>
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								显示记录数：
								<bean:write name="rsNum" />
								&nbsp;
								<font color="blue"> 提示：双击一行可以查看详细信息；单击表头可以排序</font>
							</legend>

							<table width="100%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
										<td nowrap>
											<input type="checkbox" id="all" name="all" onclick="chec()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="<bean:write name="tit" property="en"/>"
												onclick="tableSort(this)" nowrap>
												<bean:write name="tit" property="cn" />
											</td>
										</logic:iterate>
									</tr>
								</thead>
									<logic:iterate name="rs" id="s">
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="modi()">
										<td>
											<logic:iterate id="v" name="s" offset="0" length="1">
												<input type="checkbox" name="pkV" value="<bean:write name="v"/>">
											</logic:iterate>
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												<bean:write name="v" />
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>
							</table>
							<TABLE width="99%" id="rsTable1" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=bylfglForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
							<br />
							<br />
						</fieldset>
					</logic:notEmpty>
					<br />
					<br />
					<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<center>
						<logic:equal value="yes" name="writeAble" scope="request">		
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">							
								
								<button type="button" class="button2"
									onclick="modi()"
									style="width:80px">
									归还
								</button>
								&nbsp;&nbsp;
								<button type="button" class="button2"
									onclick="print()">
									归还情况一览表
								</button>
								<script language="javascript">
											document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
											document.getElementById("btn").style.width = "96%";
											window.setInterval("initBTNTool('btn')",1);
								</script>
						</div>
						</logic:equal>
					</center>
				</div>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal name="result" value="true">
			<script language="javascript">
      				alert("操作成功！");
	  		</script>
		</logic:equal>
		<logic:equal name="result" value="false">
			<logic:notEmpty name="mes">
				<input name="mes" id="mes" value="${mes}" />
				<script>
					alert(document.getElementById('mes').value);
				</script>
			</logic:notEmpty>
			<logic:empty name="mes">
				<script language="javascript">
	  				alert("操作失败! ");
	  			</script>
			</logic:empty>
		</logic:equal>		
	</body>
</html>
