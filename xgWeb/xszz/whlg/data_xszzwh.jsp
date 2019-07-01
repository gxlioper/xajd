<%@ page language="java" contentType="text/html; charset=GBK"%>

<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
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
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
	<script language="javascript" src="/xgxt/js/AjaxFunction.js"></script>
	<script language="javascript">
		function del(url){
			var RowsStr="";	
			for (i=0; i<document.getElementsByName("pk").length; i++){
		    	if(document.getElementsByName("pk")[i].checked){
		    		RowsStr+=document.getElementsByName("pk")[i].value+"!!splitOne!!";
		    	}
			}
			document.forms[0].cbVal.value = RowsStr;	
			if (RowsStr==""){
				alert("请选择要批量删除的记录！");
				return false;
			}
			document.forms[0].action=url;
		    document.forms[0].submit();
		}
		
		/**view*/
		function chkAssisOne(url) {
			if (curr_row == null) {
				alert("请选择要选择的行数据\n点击双击");
				return false;
			} else {
				url += "&pkVal=";
				url += curr_row.getElementsByTagName("input")[0].value;
				showTopWin(url, 790, 550);
				return true;
			}
		}
		
		function add(url){
			return showTopWin(url,790,550);
		}
		
		function modi(url){
			if((curr_row == null) || (curr_row == "")){
				alert("请选择要修改的记录！");
				return false;
			}
			url += "&pkVal=";
			url += curr_row.getElementsByTagName("input")[0].value;
			return showTopWin(url,790,550);
		}
		
		function dataExport2() {
			document.forms[0].action = "/xgxt/whlg_xszz.do?method=jtjj_kns_exp";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
	</script>
	<body onload="xyDisabled('xy');initPage()">
		<html:form action="/whlg_xszz.do?method=xszzwh" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：学生资助 - 数据维护 - 学生资助维护
				</div>
			</div>
			<input type="hidden" id="userType" name="userType" 
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" name="xyV" value="<bean:write property="xydm" name="form"/>" id="xyV"/>
			<input type="hidden" name="zyV" value="<bean:write property="zydm" name="form"/>" id="zyV"/>
			<input type="hidden" name="bjV" value="<bean:write property="bjdm" name="form"/>" id="bjV"/>
			<input type="hidden" name="njV" value="<bean:write property="nj" name="form"/>" id="njV"/>
			<%--<input type="hidden" name="xnV" value="<bean:write property="xn" name="form"/>" id="xnV"/>
			--%><input type="hidden" name="ndV" value="<bean:write property="nd" name="form"/>" id="ndV"/>
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			<fieldset>
				<legend>
					条件选择
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								年度：
								<html:select property="nd" onchange="" 
								styleId="nd" style="width:90px;padding-left:80px">
								</html:select>&nbsp;&nbsp;
								年级：
								<html:select property="nj" onchange="initXyList();initZyList();initBjList()" 
								styleId="nj" style="width:90px;padding-left:80px">
								</html:select>&nbsp;&nbsp;
								学号：
								<html:text name="form" property="xh" styleId="xh" style="width:100px;inputtext"
								styleClass="inputtext"></html:text>&nbsp;&nbsp;
								姓名：
								<html:text  name="form" property="xm" styleId="xm" style="width:100px;inputtext"
								styleClass="inputtext"></html:text>
							</td>					
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/whlg_xszz.do?method=xszzwh&go=go&doType=search')">
									查询
								</button>
							</td>
						</tr>
						<tr>	
							<td>
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm" onchange="initZyList();initBjList()"
								 style="width:180px" styleId="xy">
								</html:select>&nbsp;&nbsp;
								专业：
								<html:select property="zydm" onchange="initBjList()" 
								style="width:180px" styleId="zy">
								</html:select>&nbsp;&nbsp;
								班级：
								<html:select property="bjdm" style="width:180px" styleId="bj">
								</html:select>
							</td>
						</tr>
					</thead>
				</table>
			</fieldset>
			<div id="result">
				<div class="searchcontent">
					<logic:empty name="rs">
						<p align="center">
							未找到任何记录！
						</p>
					</logic:empty>
					<logic:notEmpty name="rs">
						<fieldset>
							<legend>
								记录数：
								${rsNum}
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<font color="blue">提示：双击一行可以查看详细信息，并可以修改信息；单击表头可以排序</font>
							</legend>
							<table width="99%" id="rsTable" class="tbstyle">
								<thead>
									<tr align="center" style="cursor:hand">
									    <td nowrap>
											<input type="checkbox" id="cb" name="cb" onclick="selectAll()" />
										</td>
										<logic:iterate id="tit" name="topTr" offset="1">
											<td id="${tit.en}" onclick="tableSort(this)" nowrap>
												${tit.cn}
											</td>
										</logic:iterate>
									</tr>
								</thead>
								<logic:iterate name="rs" id="s">
									<tr onclick="rowOnClick(this)"
										ondblclick="chkAssisOne('/xgxt/whlg_xszz.do?method=xszzsq&doType=view')">
										<td align=center><input type="checkbox" id="pk" name="pk" 
											value="<bean:write name="s" property="xh"/><bean:write name="s" property="nd"/>" />
									    </td>			    
										<td><bean:write name="s" property="nj"/></td>
										<td><bean:write name="s" property="xh"/></td>
										<td><bean:write name="s" property="xm"/></td>
										<td><bean:write name="s" property="xb"/></td>
										<td><bean:write name="s" property="xymc"/></td>
										<td><bean:write name="s" property="zymc"/></td>
										<td><bean:write name="s" property="bjmc"/></td>
										<td><bean:write name="s" property="sqsj"/></td>
									</tr>
								</logic:iterate>
							</table>
						</fieldset>
					</logic:notEmpty>
					<logic:equal value="yes" name="writeAble" scope="request">
	                	<div class="buttontool" align="center" id="btn" style="position:absolute;width:95%;top:100px">
							<button type="button" class="button2" onclick="add('/xgxt/whlg_xszz.do?method=xszzsq');"
								style="width:80px">
								增 加
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="modi('/xgxt/whlg_xszz.do?method=xszzsq&doType=modi');"
								style="width:80px">
								修 改
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="del('/xgxt/whlg_xszz.do?method=xszzwh&doType=del');"
								style="width:80px">
								删 除
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2"
								onclick="impAndChkData();"
								style="width:80px">
								导入数据
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="dataExport2()" style="width:80px">
								导出数据
							</button><%--
							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button type="button" class="button2" onclick="data_HkxybbExp()" style="width:80px">
								输出报表
							</button>
							
							--%><a href="#" onclick="dataStencilExport()">模板下载</a>
						</div>
					</logic:equal>
					<div id="tmpdiv"></div>
				</div>
			</div>	
		</html:form>
		 <script type="text/javascript" src="js/bottomButton.js"></script> 
		<logic:present name="ok" scope="request">
		<logic:match value="ok" name="ok">
			<script language="javascript">
	   			alert("删除成功！");  	
	        </script>
		</logic:match>
		<logic:match value="no" name="ok">
			<script language="javascript">
	        	alert("删除失败！");
	        </script>
		</logic:match>
	</logic:present>		 
	</body>
</html>
