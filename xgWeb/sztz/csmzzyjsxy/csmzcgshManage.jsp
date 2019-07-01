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
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />

	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<body
		onload="bjDisabled('nj-xy-zy-bj');removeXnXq();myxyDisabled('xy');">
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/sztzFunction.js"></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>

		<html:form action="/csmz_sztz.do?method=data_search" method="post">
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<input type="hidden" id="tableName" name="tableName"
				value="<bean:write name="tableName" scope="request"/>" />
			<input type="hidden" id="act" name="act"
				value="<bean:write name="act" scope="request"/>" />
			<input type="hidden" id="pk" name="pk"
				value="<bean:write name="pk" scope="request"/>" />
			<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope="request"/>" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="xxdm" name="xxdm"
				value="<bean:write name="xxdm" scope="request"/>" />
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType" scope="request"/>">
			<input type="hidden" name="userName" id="userName"
				value="<bean:write name="userName" scope="session"/>">
			<input type="hidden" id="delPk" name="delPk" value="" />
			<input type="hidden" id="pkVStr" name="pkVStr"
				value="" />					
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：
					<bean:write name="tips" scope="request" />
				</div>
			</div>

			<fieldset>
				<legend>
					查 询
				</legend>
				<table width="100%" class="tbstyle">
					<thead>
						<tr>
							<td align="left">
								年级：
								<html:select property="nj" style="width:80px"
									onchange="initZyList();initBjList()">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								<logic:equal value="no" name="ptcx">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学年：
										<html:select property="xn" style="width:100px" styleId="xn"
										onchange="genNdList(this)">
										<html:options collection="xnList" property="xn"
											labelProperty="xn" />
									</html:select>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;学期：
										<html:select property="xq" style="width:90px" styleId="xq">
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
									</html:select>
								</logic:equal>
								&nbsp;&nbsp;&nbsp;学号：
								<html:text property="xh" style="width:85px"></html:text>
								&nbsp;&nbsp;&nbsp;姓名：
								<html:text property="xm" style="width:85px"></html:text>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<input type="hidden" name="go" value="a" />
								<button class="button2" style="height:40px" id="search_go"
									onclick="document.forms[0].go.value='go';refreshForm('/xgxt/csmz_sztz.do?method=data_search&act='+$('act').value)">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td align="left" >
								<bean:message key="lable.xsgzyxpzxy" />(部门)：
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
								<br>审核状态：
								<html:select property="yesNo" style="width:80px" styleId="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
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

				<ul style="color: red;list-style:none;">
					<li>
						1、已分配权限的班干部用户只能查看及审核本班学生申报的项目申报成果。
					</li>
					<li>
						2、已分配权限的<bean:message key="lable.xsgzyxpzxy" />(部门)用户只能查看及审核本<bean:message key="lable.xsgzyxpzxy" />内学生申报的项目申报成果(或本部门内项目申报成果)。
					</li>
					<li>
						3、已分配权限的学校用户能查看及审核所有的项目申报成果。
					</li>
					<li>
						4、审核流程：所有项目申报成果均需&nbsp;&nbsp;'班干部'->'<bean:message key="lable.xsgzyxpzxy" />(或部门)'->'学校'&nbsp;&nbsp;三级审核，
					</li>
					
				</ul>
			</logic:empty>
			<logic:notEmpty name="rs">
				<fieldset>
					<legend>
						记录数：
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">提示：双击一行可以查看详细信息并进行审核；单击表头可以排序;</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
										<input type="checkbox" name="fyxx" value="all"
											onclick="chec()">
									</td>
								<logic:iterate id="tit" name="topTr" offset="2">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);"
								style="cursor:hand;background-color:
                                        <logic:iterate id="v" name="s" offset="0" length="1">
                                        <bean:write name="v"/>
                                        </logic:iterate>
                                        "
								ondblclick="CheckDo()">
									<td>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<input type="checkbox" name="pkV"
												value="<bean:write name="v"/>">
										</logic:iterate>
									</td>
								<td>
									<logic:iterate id="v" name="s" offset="1" length="1">
										<input type="hidden" value="<bean:write name="v"/>" />
									</logic:iterate>
									<logic:iterate id="v" name="s" offset="2" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="3">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
					</table>
				</fieldset>
				<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
				<logic:equal value="xx" name="userType" >
					<div class="buttontool" id="btn"
						style="position: absolute;left:1%;top:100px" width="100%">					
					<button class="button2" onclick="xmShBatch('yes')"
								style="width:80px">
							 通  过
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2" onclick="xmShBatch('no')"
								style="width:80px">
							 不通过
					</button>						
					</div>
					</logic:equal>
<%--				<div class="buttontool" id="btn"--%>
<%--					style="position: absolute;left:1%;top:100px" width="100%">--%>
<%--					<button class="button2"--%>
<%--						onclick="allBjSh('/xgxt/csmz_sztz.do?method=all_check&bjdm='+document.forms[0].bjdm.value)"--%>
<%--						style="width:80px">--%>
<%--						全班通过--%>
<%--					</button>--%>
<%--				</div>--%>
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
	<logic:equal name="autoChk" value="ok" scope="request">
		<script language="javascript">
                   alert("自动审核完成！");
                   document.getElementById('search_go').click();	      
	    </script>
	</logic:equal>
	<logic:equal name="autoChk" value="no" scope="request">
		<script language="javascript">
                 alert("自动审核失败！");
                 document.getElementById('search_go').click();	      
 	    </script>
	</logic:equal>
	<script type="text/javascript">
function chec(){
        for(i=0;i<document.getElementsByName("pkV").length;i++){
      	document.getElementsByName("pkV")[i].checked=document.getElementsByName("fyxx")[0].checked;
       }
}	
function CheckDo(){
    var	w = 700;
	var	h = 500;
	var realTable = document.getElementById("realTable").value;
	url = "/xgxt/csmz_sztz.do?method=tzcg_sh";	
	url += "&pkValue=";
	url += curr_row.cells[0].getElementsByTagName("input")[0].value;
	showTopWin(url, w, h);	
}

function allBjSh(url){
    confirmTxt = "全班通过审核将以某学年、学期项目\n及班级为单位进行批量通过审核! \n";
    if(document.forms[0].xn.value==""){
		alert(confirmTxt+"请选择学年！");
		return false;
    }
    if(document.forms[0].xq.value==""){
        alert(confirmTxt+"请选择学期！");
        return false;
    }
    if (document.forms[0].bjdm.value == "") {
		alert("请选择班级！");
		return false;
	}else{
	   confirmTxt = "全班通过审核将耗费较长的时间，确定要开始自动审核吗？";
		if (confirm(confirmTxt)) {		
		    alert("点击\"确定\"后开始审核！");		    
		    sztzShowTips('正在进行整批审核，请稍候......');
			refreshForm(url);
			return true;
		} else {
			return false;
		}
	}	
}
function myxyDisabled(ele) {
    var userT = document.getElementById("userType").value;
	if (userT == "xy"||userT =="stu") {
		var tmp = ele.split("-");
		for (i = 0; i < tmp.length; i++) {
			document.getElementById(tmp[i]).disabled = true;
		}
	}
}
function xmShBatch(str){
           var url = "/xgxt/csmz_sztz.do?method=plCheck&check="+str; 
		   var RowsStr="!!";		  
		   var userType = $("userType").value;		   
		   xyshDone = (str=="yes")?"通过":"不通过";
		   var pkVArray = "'";
		   for (i=0; i<document.getElementsByName("pkV").length; i++){
	    	  if(document.getElementsByName("pkV")[i].checked){
	    		 RowsStr+=document.getElementsByName("pkV")[i].value+"!!";
	    		 pkVArray+=document.getElementsByName("pkV")[i].value+"','";
	    	  }	    	  
		   }
		   
		   if (RowsStr=="!!"){
			   alert("请选择要操作的记录！\n(单击每条记录前复选框)");
			   return false;
		   }
		   document.forms[0].pkVStr.value = RowsStr;
		   pkVArray=pkVArray.substring(0,pkVArray.length-2);
		   		      
		   getSztzData.getInfoEx2("csmz_tzcgb"," rowid in ("+pkVArray+") and xysh<>'通过' ",function(boolean){
		        if(boolean){
		             if (confirm("所选中记录中，有<bean:message key="lable.xsgzyxpzxy" />审核\"不通过\"或\"未审核\"的记录！\n\n确定要\""+xyshDone+"\"所选记录？")){
		             refreshForm(url);   
		             }
		        }else{		            		
		         if (confirm("确定要\""+xyshDone+"\"所选记录？")){
			        refreshForm(url);   
		         }
		       }
		   });
}
</script>
</html>
