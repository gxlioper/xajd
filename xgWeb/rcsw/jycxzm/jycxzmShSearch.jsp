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
		 var url = "/xgxt/zjlg_xljk.do?method=xlzxsAdd&titleType=add";
		 var tableName = document.getElementById("tableName").value;
		 //showOpenWindow(url, 650, 400);
		 url += "&tableName="+tableName;
		 showTopWin(url, 650, 400);
	}
	function update(){
	  if(curr_row==null){
	       alert('请选择要操作的记录!\n(单击一行即可)');
	       return false;
	    } 
	   var tableName = document.getElementById("tableName").value;
	  var url = "/xgxt/zjlg_xljk.do?method=xlzxsAdd&titleType=update&pk=";
	  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
	  url += pk;
	  url += "&tableName="+tableName;
	  //showOpenWindow(url, 650, 400);
	  showTopWin(url, 650, 400);
	}
	function viewjycxzmgl(){
		  if(curr_row==null){
		       alert('请选择要操作的记录!\n(单击一行即可)');
		       return false;
		    } 
		  var tableName = document.getElementById("tableName").value;
		  var url = "/xgxt/jycxzmgl.do?method=jycczmView&pk=";
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
		function sqsh(chkVal){          
	          var url = "/xgxt/jycxzmgl.do?method=jycczmSh&go=go&chkVal="+chkVal; 
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
			   if (confirm("确定要审核？")){
				     refreshForm(url);
			   }         		                  
			}
		function zjlgdataExport() {
			document.forms[0].action = "/xgxt/zjlg_xljk.do?method=expData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
	}
	</script>
	<body>
		<html:form action="/jycxzmgl.do" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置: 日常事务 - 教育储蓄证明管理- 教育储蓄证明审核
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
					<input type="hidden" name="zyV" id="zyV" />
					<input type="hidden" name="bjV" id="bjV" />
					<input type="hidden" id="userType" name="userType" value="" />
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
									
									学年：
									<html:select property="xn" styleId="xn"
										onchange="">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
									&nbsp;&nbsp;年度：
									<html:select property="nd" styleId="nd">
										<html:options collection="ndList" property="nd"
											labelProperty="nd" />
									</html:select>
									&nbsp;&nbsp;学期：
									<html:select property="xq" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
									&nbsp;&nbsp;学号：
									<html:text property="xh" style="width:120px"></html:text>
									&nbsp;&nbsp;姓名：
									<html:text property="xm" style="width:85px"></html:text>
									&nbsp;&nbsp;审核状态：
									<html:select property="xxsh">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
								<td width="10" rowspan="3" align="center" valign="middle">
									<button type="button" class="button2" style="height:40px" id="search_go"
										onclick="refreshForm('/xgxt/jycxzmgl.do?method=auditJycczm&go=go');">
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
									<logic:iterate id="tit" name="topTr" offset="1" length="12">
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
								<logic:iterate id="v" name="s" offset="1">
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
										page="/sjcz/turnpage.jsp?form=jycxzmForm"></jsp:include>
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
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="sqsh('tg')">
								审核通过
							</button>
							&nbsp;&nbsp;
							<button type="button" class="button2" onclick="sqsh('btg')">
								审核不通过
							</button>
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
