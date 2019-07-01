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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script type='text/javascript'src='/xgxt/dwr/interface/getOtherData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/checkXsInfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript" src="js/xsgyglFunction.js"></script>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<script type="text/javascript">
			function zsjlAdd(){
			 var url = "/xgxt/zjcmxy_Gygl.do?method=zsjlAdd&doType=add";
			 //var tableName = document.getElementById("tableName").value;
			 //showOpenWindow(url, 650, 400);
			 //url += "&tableName="+tableName;
			 showTopWin(url, 680, 420);
			 //showOpenWindow(url, 680, 420);
		}
		function update(){
		  if(curr_row==null){
		       alert('请选择要操作的记录!\n(单击一行即可)');
		       return false;
		    } 
		   var tableName = document.getElementById("tableName").value;
		  var url = "/xgxt/zjcmxy_Gygl.do?method=zsjlAdd&doType=update&pk=";
		  var pk = curr_row.cells[0].getElementsByTagName("input")[0].value;
		  url += pk;
		  //url += "&tableName="+tableName;
		  //showOpenWindow(url, 650, 400);
		  showTopWin(url, 650, 400);
		}
		function viewxljk(){
			  if(curr_row==null){
			       alert('请选择要操作的记录!\n(单击一行即可)');
			       return false;
			    } 
			  var tableName = document.getElementById("tableName").value;
			  var url = "/xgxt/zjlg_xljk.do?method=xlzxsAdd&titleType=view&pk=";
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
		function zsjldel(doType){
		var url = "/xgxt/zjcmxy_Gygl.do?method=zsjldel&go=go&pkValue=";
		var pkValue = "";
			
		if (doType == "del") {
		   if (curr_row == null) {
			alert("请选择要删除的数据！\n（单击相应的行）");
			return false;
		    } else {
		    pkValue = curr_row.getElementsByTagName("input")[0].value;
		    checkXsInfo.checkXsInfo("","","","",pkValue,function initTjList(data){
					       if (data != "") {
								alert("无法删除上级部门已处理的记录");
								return ;
							}else{
								//showMsgWin("有错误出现：远程数据读取失败！");
								//zsjlSave('xh-xn-xq-lddm-qsh-fs-wjsj-wjlbdm-wjsy');
								if (confirm("确定要删除该行数据吗？")) {
									url += pkValue;
									refreshForm(url);
									return true;
								} else {
									return false;
			}
							}	
					    });
		  }
		   return;
	      }
		}
		  
		 function xljkdel(){          
	           var url = "/xgxt/zjlg_xljk.do?method=xlzxsDel&go=go"; 
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
	 }
	 function expDatazjcm() {
			document.forms[0].action = "/xgxt/zjcmxy_Gygl.do?method=expData";
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";
		}
		function disabtext(){
			var userType = document.getElementById("userType").value;
			if(userType == "xy"){
				document.getElementById("xydm").disabled = true;
			}
		}
	</script>
	<body onload="bjLimit('bj');disabtext();">

		<html:form action="/zjcmxy_Gygl" method="post">
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="dxq" name="dxq"
				value="<bean:write name="writeAble" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="session"/>" />
			<input type="hidden" name="lcV" id="lcV" value="" />
			<input type="hidden" name="qshV" id="qshV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" name="isFdy " id="isFdy" value="<bean:write name="fdyQx" scope="session"/>" />	
		     <div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					公寓管理 - 住宿纪律管理 - 录入
				</div>
			</div>

			<fieldset>
				<legend>
					查询条件
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left" nowrap>
								年级：
									<html:select property="nj"  onchange="initZyList();initBjList()">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								&nbsp;&nbsp;学年：
								<html:select property="xn" styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期：
								<html:select property="xq"  styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>								
								<bean:message key="lable.xsgzyxpzxy" />：
								<html:select property="xydm"  styleId="xy"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
								
							</td>
							<td width="10" rowspan="4" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="allNotEmpThenGo('/xgxt/zjcmxy_Gygl.do?method=zsjlInput&go=go');this.disabled=true">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								
								&nbsp;&nbsp;专业：
								<html:select property="zydm"  styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;
								班级：
								<html:select property="bjdm"  styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
<%--								<logic:present name="showhzy">--%>
								    楼栋名：
									<html:select property="lddm"  styleId="lddm"
										onchange="getLcList()">

										<html:options collection="ldList" property="lddm"
											labelProperty="ldmc" />
									</html:select>
										&nbsp;&nbsp;楼层：
										<html:select property="lc"  styleId="lc"
										onchange="getQshList2()">
										<html:options collection="lcList" property="dm"
											labelProperty="mc" />
									</html:select>
										&nbsp;&nbsp;寝室号：
										<html:select property="qsh"  styleId="qsh">
										
										<html:options collection="qshList" property="dm"
											labelProperty="mc" />
									</html:select>
							</td>
						</tr>
						<tr>
							<td align="left" nowrap>
								纪律类别：
								<html:select property="wjlbdm" styleId="wjlbdm">
									<html:option value=""></html:option>
									<html:options collection="wjlbList" property="wjlbdm"
										labelProperty="wjlbmc" />
								</html:select>
								&nbsp;&nbsp;学号：
								<html:text property="xh" style="width: 90px" styleId="xh" />
								 &nbsp;&nbsp;姓名：				
							    <html:text property="xm" styleId="xm" style="width:80px"></html:text>
								&nbsp;&nbsp;违纪时间：
								<html:text property="kssj" readonly="true"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('kssj','y-mm-dd','ag');"
									style="cursor:hand;width:80px " />
								-
								<html:text property="jssj" readonly="true"
									onblur="dateFormatChg(this)"
									onclick="return showCalendar('jssj','y-mm-dd','ag');"
									style="cursor:hand;width:80px " />
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
						<font color="blue">提示：双击一行可以查看相信信息，并可以填写维修情况；单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand"
								ondblclick="update()">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="2">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<TABLE width="99%" id="rsTable" class="tbstyle">
					<TR>
						<TD height=3></TD>
					</TR>
					<TR>
						<TD>
							<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=zjcmxyForm"></jsp:include>
						</TD>
					</TR>
					<TR>
						<TD height=3></TD>
					</TR>
				</TABLE>
			</logic:notEmpty>
			<br>
			<br>
			<br>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
			<center>
				<div class="buttontool" id="btn"
					style="position: absolute;left:1%;top:100px" width="100%">
					<logic:equal value="yes" name="writeAble" scope="request">
						<button class="button2" onclick="zsjlAdd();"
							style="width:80px">
							增 加
						</button>							
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="update();return false;"
							style="width:80px">
							修 改
						</button>
							&nbsp;&nbsp;&nbsp;&nbsp;							
							<button class="button2" onclick="zsjldel('del')"
							style="width:80px">
							删 除
						</button>							
<!--							&nbsp;&nbsp;&nbsp;&nbsp;-->
<!--							<button class="button2"-->
<!--							onclick="impAndChkData();"-->
<!--							style="width:80px">-->
<!--							导入数据-->
<!--						</button>-->
					</logic:equal>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="expDatazjcm()" style="width:80px">
						导出数据
					</button>
					<logic:equal value="10822" name="xxdm">
						<!--广东白云<bean:message key="lable.xsgzyxpzxy" />  -->
						&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2"
							onclick="mydormDataExp('/xgxt/XsGyGlLogic.do?method=gdby_dormJlTj')"
							style="width:120px">
							宿舍纪律记录表
						</button>
					</logic:equal>
				</div>
			</center>
		</html:form>

		<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
			
			function chDate(){
				if($("gzkssj").value!=""&&$("gzjssj").value!=""){
					if($("gzkssj").value > $("gzjssj").value){
						alert("开始时间大于结束时间，请确认！！");
						return false;
					}
				}
				return true;
			}
		</script>
		<script type="text/javascript">
			function mydormDataExp(url){
				document.forms[0].action = url;
				document.forms[0].target = "_blank";
				document.forms[0].submit();
				document.forms[0].target = "_self";
			}
		</script>
		<logic:equal name="done" value="ok">
			<script language="javascript">
      				alert("操作成功！");
	  		</script>
		</logic:equal>
		<logic:equal name="done" value="no">
			<script language="javascript">
	  				alert("操作失败! ");
	  		</script>
		</logic:equal>
	</body>
</html>
