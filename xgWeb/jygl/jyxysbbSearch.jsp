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
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/cqkjFunc.js'></script>
	<script language="javascript" src="js/qgzxFunction.js"></script>
	<script type="text/javascript">
	function add(){
		var url = "/xgxt/jyxysbbsq.do";
		 //var tableName = document.getElementById("tableName").value;
		 //showOpenWindow(url, 650, 400);
		 //url += "&tableName="+tableName;
		 //showTopWin(url, 650, 400);
		var userType = document.getElementById("userType").value;
		if(userType != "stu"){
			alert("只有学生用户才能够申请！！");
			return false;
		}
		document.forms[0].action = url;
		document.forms[0].submit();
		disbtn();
	}
	function update(){
		var userType = document.getElementById("userType").value;
		if(userType != "stu"){
			alert("只有学生用户才能够修改！！");
			return false;
		}
	  if(curr_row==null){
	       alert('请选择要操作的记录!\n(单击一行即可)');
	       return false;
	    } 
	   var tableName = document.getElementById("tableName").value;
	  var url = "/xgxt/jyxysbbsq.do?act=update&pk=";
	  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	  url += pk;
	  url += "&tableName="+tableName;
	  document.forms[0].action = url;
	  document.forms[0].submit();
	  disbtn();
	  //showOpenWindow(url, 650, 400);
	  //showTopWin(url, 800, 600);
	}
	function viewjycxzmgl(){
		  if(curr_row==null){
		       alert('请选择要操作的记录!\n(单击一行即可)');
		       return false;
		    } 
		  var tableName = document.getElementById("tableName").value;
		  var url = "/xgxt/jyxysbbsq.do?act=view&pk=";
		  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		  url += pk;
		  url += "&tableName="+tableName;
		  //showOpenWindow(url, 650, 400);
		  showTopWin(url, 650, 400);
		}
	function xxshycxzmgl(){
		  if(curr_row==null){
		       alert('请选择要操作的记录!\n(单击一行即可)');
		       return false;
		    } 
		  var tableName = document.getElementById("tableName").value;
		  var url = "/xgxt/jyxysbbsq.do?act=xxsh&pk=";
		  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		  url += pk;
		  url += "&tableName="+tableName;
		  //showOpenWindow(url, 650, 400);
		  showTopWin(url, 650, 400);
		}
	//全部选中
	 function checValue(){
	     for(i=0;i<document.getElementsByName("pkV").length;i++){
	  	    document.getElementsByName("pkV")[i].checked=document.getElementsByName("pk")[0].checked;
	     }
	  }	
	 function xljkdel(){          
          var url = "/xgxt/jyxysbbgl.do?act=del"; 
		   var RowsStr="";		  		 
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){	    		 
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    	  }	    	  
		   }		   
		   if (RowsStr==""){
			   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   
		   //if (confirm("确定要删除所选记录？\n\n下级部门将无法删除上级部门已审核的记录")){
		   if (confirm("确定要删除所选记录？")){
			     refreshForm(url);
		   }         		             
		   disbtn();     
		}
		function xljkxysh(shlx){       
	          var url = "/xgxt/jyxysbbgl.do?act=xysh&shlx="+shlx; 
			   var RowsStr="";		  		 
			   var pkVArray = "'";
			   for (i=0; i<document.getElementsByName("pkV").length; i++){
		    	  if(document.getElementsByName("pkV")[i].checked){	    		 
		    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','"
		    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
		    	  }	    	  
			   }		   
			   if (RowsStr==""){
				   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
				   return false;
			   }
			   document.forms[0].pkVStr.value = RowsStr;
			   pkVArray=pkVArray.substring(0,pkVArray.length-2);		   
			   //if (confirm("确定要删除所选记录？\n\n下级部门将无法删除上级部门已审核的记录")){
			   //if (confirm("确定要删除所选记录？")){
				    refreshForm(url);
			   //}         		                  
			}
		function expDataJycxzmgl() {
			document.forms[0].action = "/xgxt/jyxysbbsq.do?act=exp";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
	}
		function isusertype(){
			var usertype = document.getElementById('userType').value;
			if("stu" == usertype){
				document.getElementById('xh').readOnly = true;
				document.getElementById('xm').readOnly = true;
				document.getElementById('xydm').disabled = true;
				document.getElementById('zydm').disabled = true;
				document.getElementById('bjdm').disabled = true;
			}
			if("xy" == usertype){
				document.getElementById('xydm').disabled = true;
			}
		}
		function disbtn(){
			var btn = document.getElementsByTagName("button");
			for(var i = 0;i<btn.length;i++){
				btn[i].disabled = true;
			}
		}
	</script>
	<body onload="isusertype();">
		<html:form action="/jyxysbbgl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前位置：就业管理 - 就业协议方案 - 就业协议书补办管理
				</div>
			</div>
			<logic:equal value="view_xslxfszsxx" name="tableName">
				<logic:equal value="stu" name="userType">
					此页面只有学校和<bean:message key="lable.xsgzyxpzxy" />用户可以访问
				</logic:equal>
			</logic:equal>
			<div class="rightcontent">

				<fieldset>
					<legend>
						查 询
					</legend>
					<input type="hidden" name="zyV" id="zyV"/>
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" id="userType" name="userType" value="<bean:write name="userType" scope="session"/>" />
					<input type="hidden" id="tableName" name="tableName" value="view_czxx_jycyzmb" />
					<input type="hidden" id="act" name="act" value="" />
					<input type="hidden" id="realTable" name="realTable" value="" />
					<input type="hidden" id="xxdm" name="xxdm" value="" />
					<input type="hidden" id="userName" name="userName" value="" />
					<input type="hidden" id="isFdy" name="isFdy" value="" />
					<input type="hidden" id="pkVStr" name="pkVStr"  value="" />
					<table width="100%" class="tbstyle">
						<thead>
							<tr>
								<td align="left">
									毕业年月：
									<html:select property="byny" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
									&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:120px"></html:text>
									&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:85px"></html:text>
									&nbsp;&nbsp;审核状态：
									<html:select property="xysh">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<button class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/jyxysbbgl.do?act=go&go=go');disbtn();">
										查询
									</button>
								</td>
							</tr>
							<tr>
								<td align="left" nowrap>
									年级：
									<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
									&nbsp;&nbsp;<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" style="width:180px" styleId="xy"
										onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
									&nbsp;&nbsp;专业：
									<html:select property="zydm" style="width:180px" styleId="zy"
											onchange="initBjList()">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
									&nbsp;&nbsp;班级：
									<html:select property="bjdm" style="width:180px" styleId="bj">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
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
							<font color="blue"> 提示：双击一行可以查看详细信息；单击表头可以排序;按住Ctrl可以多选</font>
						</legend>

						<table width="100%" id="rsTable" class="tbstyle">
							<thead>
								<tr align="center" style="cursor:hand">
									<td>
										<input type="checkbox" name="pk" value="all" onclick="checValue();">
									</td>
									<logic:iterate id="tit" name="topTr" offset="0" length="12">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap="nowrap">
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
								</tr>
							</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewjycxzmgl()">
								<td align="center">
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="checkbox" name="pkV" value="<bean:write name="v"/>">
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="0">
									<td align="center">
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
										page="/sjcz/turnpage.jsp?form=zjgxJyxxForm"></jsp:include>
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
				<div id="toolTipLayer"
					style="position:absolute; visibility: hidden" /></div>
				<center>
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">
						<logic:equal value="yes" name="writeAble" scope="request">
							<logic:equal value="xy" name="iswho">
							<button class="button2" onclick="xljkxysh('tg');"
								style="width:80px">
								<bean:message key="lable.xsgzyxpzxy" />通过
							</button>
								&nbsp;	
							<button class="button2" onclick="xljkxysh('btg');"
								style="width:80px">
								<bean:message key="lable.xsgzyxpzxy" />不通过
							</button>
							</logic:equal>
							&nbsp;	
							<logic:equal value="xx" name="iswho">
							<button class="button2" onclick="xxshycxzmgl();"
								style="width:80px">
								学校操作
							</button>
							</logic:equal>
							&nbsp;
							<button class="button2" onclick="add();"
								style="width:80px">
								增 加
							</button>
							&nbsp;	
							<button class="button2" onclick="update();"
								style="width:80px">
								修 改
							</button>
							&nbsp;
							<button class="button2"
								onclick="xljkdel();"
								style="width:80px">
								删 除
							</button>
<%--							&nbsp;--%>
<%--							<button class="button2" onclick="impAndChkData()"--%>
<%--								style="width:80px">--%>
<%--								导入数据--%>
<%--							</button>--%>
							&nbsp;
							<button class="button2" onclick="expDataJycxzmgl();"
								style="width:80px">
								导出数据
							</button>
						</logic:equal>
					</div>
				</center>
			</div>

			<div id="tmpdiv"></div>
		</html:form>
		<logic:equal name="done" value="yes">
			<script language="javascript">
      				alert("操作成功！");
	  		</script>
		</logic:equal>
		<logic:equal name="done" value="no">
			<script language="javascript">
	  				alert("操作失败! ");
	  		</script>
		</logic:equal>
		<!-- 	<script type="text/javascript" src="js/bottomButton.js"></script>  -->
		<script language="javascript">
					document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
					document.getElementById("btn").style.width = "96%";
					window.setInterval("initBTNTool('btn')",1);
		</script>
	</body>
</html>
