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
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
		<script language="javascript" src="js/jsFunction.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/lrh_new_js.js"></script>
		<script language="JavaScript">
		function jygl_hyxx_view(){
			var pk=curr_row.getElementsByTagName("input")[0].value;
			showTopWin("/xgxt/jyglInfo.do?method=view_hyxx&pk="+pk,525,400);
		}
		
		function jygl_hyxx_add(){
			showTopWin("/xgxt/jyglInfo.do?method=add_hyxx",550,400);
		}
		
		function jygl_hyxx_del(){
			if (curr_row == null) {
				alert("请选择要删除的数据！\n（单击相应的行）");
				return false;
			}else if (confirm("确定要删除该行数据吗？")) {
				var pk=curr_row.getElementsByTagName("input")[0].value;
				refreshForm("/xgxt/jyglInfo.do?method=delete_hyxx&pk="+pk);
			} 
		}
		
		function jygl_hyxx_modi(){	
			if (curr_row == null) {
				alert("请选择要修改的数据！\n（单击相应的行）");
				return false;
			}else if(confirm("确定要修改该行数据吗？")) {
				var pk=curr_row.getElementsByTagName("input")[0].value;
				showTopWin("/xgxt/jyglInfo.do?method=modi_hyxx&pk="+pk,550,400);
			}
		}	
	</script>
	</head>
	<body>
		<html:form action="/jyglInfo" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				当前所在位置：就业管理 - 就业促进会 - 会员基本信息
			</div>
		</div>	
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<fieldset>
				<legend>
					查询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								会员编号：
								<html:text property="hybh" styleId="hybh" />
								学号：
								<html:text property="hyxh" styleId="hyxh" />
								性别：
								<html:select property="xb" style="width:120px"
									styleId="xb">
									<html:option value=""></html:option>
									<html:options collection="sexList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px;" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/jyglInfo.do?method=searchHyxx&doType=Search')">
									查询
								</button>
								<button class="button2" style="height:40px;display:none;"
									id="search_go1"
									onclick="refreshForm('/xgxt/jyglInfo.do?method=searchHyxx&doType=Search')">
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" style="width:180px" styleId="xy"
									onchange="refreshForm('/xgxt/jyglInfo.do?method=searchHyxx')">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" style="width:180px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								姓名：	
								<html:text property="xm" styleId="xm" />
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>" onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)"
								style="cursor:hand;background-color:"
								ondblclick="jygl_hyxx_view()">
								<logic:iterate id="v" name="s" offset="0">
									<input type="hidden" value="${v}"/>
								</logic:iterate>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										${v}
									</td>
								</logic:iterate>							
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<logic:equal value="yes" name="writeAble" scope="request">
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px;width:100%" >
						&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="jygl_hyxx_add()"
							style="width:100px">
							增加
						</button>&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="jygl_hyxx_modi()"
							style="width:100px">
							修改
						</button>&nbsp;&nbsp;&nbsp;
						<button class="button2" onclick="jygl_hyxx_del()"
							style="width:100px">
							删除
						</button>
					</div>
				</center>
			</logic:equal>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="xlxhhy_del_success">
					<script>alert("删除成功!")</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "98%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
		<logic:equal value="true" name="result">
			<script language="javascript">
				alert('删除成功！');
			</script>
		</logic:equal>
		<logic:equal value="false" name="result">
			<script language="javascript">
				alert('删除失败！');
			</script>
		</logic:equal>
	</body>
</html>
