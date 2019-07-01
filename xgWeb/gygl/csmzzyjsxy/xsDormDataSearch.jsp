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

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body onload="">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetGyglDataInfo.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
        <script language="javascript" src="js/gygl/csmzGyglFunction.js"></script>
		<html:form action="/csmz_gygl" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					公寓管理 - 信息维护 - 住宿信息维护 - 新生住宿信息
				</div>
			</div>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="delPk" name="delPk" value="" />
					<input type="hidden" name="userType" id="userType"
				     value="<bean:write name="userType"/>" />
				     <input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>	
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									年级：
									<html:select property="nj" style="width:90px"  disabled="true">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nd"
											labelProperty="nd" />
									</html:select>
									<bean:message key="lable.xsgzyxpzxy" />：
									<logic:equal name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="ssFpZyListInit();" disabled="true">
										<html:option value="">--请选择--</html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:equal>
									<logic:notEqual name="userType" value="xy" scope="session">
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="ssFpZyListInit();">
										<html:option value="">--请选择--</html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									</logic:notEqual>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:180px" styleId="zy">										
										<html:options collection="zyList" property="dm"
											labelProperty="mc" />
									</html:select>
								
								</td>
								<td width="10" rowspan="2" align="center" valign="middle">
									<input type="hidden" name="go" value="a" />
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/csmz_gygl.do?method=xsDormDSExecute')">
										查询
									</button>
								</td>						
							</tr>
							<tr>
								<td align="left" nowrap>
									
                                        考生号：
										<html:text property="xh" styleId="xh" style="width:100px"></html:text>
										姓名：
										<html:text property="xm" styleId="xm" style="width:100px"></html:text>
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
							记录数：
							<bean:write name="rsNum" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<font color="blue">提示：单击表头可以排序</font>
						</legend>
						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
								<td title="全选">
										<input type="checkbox" name="fyxx" value="all"
											onclick="chec()" >
									</td>
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" >
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
							<logic:iterate name="rs" id="s">
								<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
									ondblclick="">
									<td nowrap title="单击选中" align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
									<td align="center">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="1">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
						</table>
							<!-- 分页 -->
							<TABLE width="100%" id="Table" class="tbstyle">
								<TR>
									<TD height=3></TD>
								</TR>
								<TR>
									<TD>
										<jsp:include flush="true"
											page="/sjcz/turnpage.jsp?form=csmz_gyglForm"></jsp:include>
									</TD>
								</TR>
								<TR>
									<TD height=3></TD>
								</TR>
							</TABLE>
							<!-- 分页 end-->
					</fieldset>
				</logic:notEmpty>
				<div id="tmpdiv"></div>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
					<center>
						<div class="buttontool" id="btn"
							style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
							<button class="button2" onclick="showTopWin('/xgxt/csmz_gygl.do?method=xsZsxxAdd','500','300')"
								style="width:80px">
								增 加
							</button>							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="batch()"
								style="width:80px">
								删 除
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="xhKshSynchro()"
								style="width:80px">
								学号同步
							</button>
							</logic:equal>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="dataExport()" style="width:80px">
								导出数据
							</button>
						</div>
					</center>
					</div>
				<logic:present name="synchroResult">
				     <input type="hidden" name="synchroResult" value="<bean:write name="synchroResult" scope="request"/>">
				     <script language="javascript">
			          alert($("synchroResult").value);
			          document.getElementById("search_go").click();
		             </script>
				</logic:present>
				<logic:equal value="true" name="flag" >
				<script language="javascript">
			     alert("操作成功！");
			     document.getElementById("search_go").click();
		       </script>	
				</logic:equal>
				<logic:equal value="false" name="flag" >
				<script language="javascript">
			     alert("操作失败！");
			     document.getElementById("search_go").click();
		       </script>	
				</logic:equal>
		</html:form>
		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight -32;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>	
		<script type="text/javascript">
		 function chec(){
             for(i=0;i<document.getElementsByName("pkV").length;i++){
      	         document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
             }
         }
         function batch(){
           var url = "/xgxt/csmz_gygl.do?method=xsZsxxPlDel"; 
		   var RowsStr="!!";		  	  
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }
		   }
		   document.forms[0].delPk.value = RowsStr;	
		   if (RowsStr=="!!"){
			   alert("请选择要删除的记录！\n(单击每条记录前复选框)");
			   return false;
		   }	
		   if (!confirm("确定要删除所选记录？")){
			  return false;
		   }else{
		      refreshForm(url);
		   }	         
       }      
         </script>	
	</body>
</html>
