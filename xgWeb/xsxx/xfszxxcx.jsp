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

	
	<script type="text/javascript" src="/xgxt/dwr/interface/GetListData.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
	<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
	<script type="text/javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	
	<script language="javascript">
		function modi(){
			if(curr_row != null){
				showTopWin('/xgxt/xsxxgl.do?method=xscjbgdxxModi&pkValue='+curr_row.getElementsByTagName('input')[0].value,700,500);
				return true;
			}else{
				alert('请选择要修改的行！');
				return false;
			}
		}
		
		function del(){
			var RowsStr="!!";	
			var mes = "确定要操作所选记录？";
			for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	if(document.getElementsByName("pkV")[i].checked){
		    		RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    	}
			}
			
			if (RowsStr=="!!"){
				alert("请选择要批量操作的记录！");
				return false;
			}
			
			if (!confirm(mes)){
				return false;
			}
			
			url = "xsxxgl.do?method=delXscjbgdxx";
			refreshForm(url);
		}
	</script>
	<body>
		<html:form action="/xsxxgl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生信息 - 学生信息 - 学费设置					
				</div>
			</div>
			<input type="hidden" name="xyV" value=""/>
		    <input type="hidden" name="zyV" value=""/>
		    <input type="hidden" name="bjV" value=""/>
			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								年级：
								<html:select property="nj"  styleId="nj"
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" styleId="xy" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm" labelProperty="xymc" />
								</html:select>		
								&nbsp;&nbsp;专业：
								<html:select property="zydm" styleId="zy" onchange="initBjList();">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm" labelProperty="zymc" />
								</html:select>	
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm" labelProperty="bjmc" />
								</html:select>
							</td>
							<td width="10" align="center" valign="middle" rowspan="2">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" id="search_go" style="height:40px"
									onclick="allNotEmpThenGo('/xgxt/xsxxgl.do?method=xscjbgdzyxfsz')">
									查询
								</button>
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
					<table width="99%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()">
								</td>
								<logic:iterate id="tit" name="topTr" offset="1">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);"
								ondblclick="" style="cursor:hand">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pkV" value="<bean:write name="v"/>">
										<input type="hidden" value="<bean:write name="v"/>" />
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
				</fieldset>
				<TABLE width="99%" id="Table" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true"
								page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<div class="buttontool" id="btn" style="position: absolute;left:1%;top:100px" width="100%">
				<logic:equal value="yes" name="writeAble" scope="request">		
					<button type="button" class="button2"
						onclick="showTopWin('xsxxgl.do?method=showXfsz',400,300)"
						style="width:80px">
						设置
					</button>
<!--					&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--					<button type="button" class="button2" onclick="modi()" style="width:80px">-->
<!--						修 改-->
<!--					</button>-->
				</logic:equal>
				</div>

				<script language="javascript">
						document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
						document.getElementById("btn").style.width = "96%";
						window.setInterval("initBTNTool('btn')",1);
				</script>
				<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('操作成功！');
					</script>			
				</logic:equal>
				<logic:notEqual value="true" name="result">
					<script>
						alert('操作失败！');
					</script>
				</logic:notEqual>
				</logic:present>
		</html:form>
	</body>
</html>
