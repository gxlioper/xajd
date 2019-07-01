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
<html:html>
<head>
	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta http-equiv="Pragma" content="no-cache" />
	<meta http-equiv="Cache-Control" content="no-cache" />
	<meta http-equiv="Expires" content="0" />
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
<script type="text/javascript" src="/xgxt/dwr/interface/getPjpyDao.js"></script>
<script type="text/javascript" src="/xgxt/dwr/interface/getXjydInfo.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type="text/javascript" src="/xgxt/dwr/engine.js"></script>
<script type="text/javascript" src="/xgxt/dwr/util.js"></script>
<script language="javascript" src="js/function.js"></script>
<script language="javascript" src="js/pjpyFunction.js"></script>
<script type="text/javascript" src="js/AjaxFunction.js"></script>
<script type="text/javascript">
/*
 显示年级、<bean:message key="lable.xsgzyxpzxy" />、专业、班级列表 
*/
function showItems(){
	var items = document.getElementById("items");
	items.style.left = (screen.availwidth)/6;
	items.style.top = ((screen.availheight)/6)-20;
	items.style.display = "block";
	if ($("userType").value == "xy") {
		var tmp = 'xy';
		document.getElementById('xy').disabled = true;
	}
}
/*
 隐藏年级、<bean:message key="lable.xsgzyxpzxy" />、专业、班级列表 
*/
function hideItems(){
	var items = document.getElementById("items");
	items.style.display = "none";
	document.getElementById("bjdm").value = document.getElementById("bj").value;
	refreshForm("/xgxt/zjlgPjpy.do?method=xjbjAdd");
}

function xjbjAddSave(){
    var bjdm = "";
    var bjqk = "";
    var xn   = "";
    if($("bjdm")){
       bjdm = $("bjdm").value;
    }
    if($("xn")){
       xn=$("xn").value;
    }
    if($("bjqk")){
       bjqk = $("bjqk").value;
    }
    if(bjdm==""){
       alert("班级代码不能为空！");
       return false;
    }
    if(xn==""){
       alert("学年不能为空！");
       return false;
    }
    if(bjqk.length>500){
       alert("班级情况字数过大，限500字内！");
       return false;
    }
    var tem = bjdm+xn;
     getPjpyDao.getInfoEx("zjlg_xjbjb","bjdm||xn",tem," 1=1",function(tag){
		     if(tag){
		        if(confirm("该学年、该班级数据已存在,确定要保存数据！\n\n点击\'确定\'更新数据，点击\'取消\'放弃保存")){
		           refreshForm("/xgxt/zjlgPjpy.do?method=xjbjAdd&doType=save");
                   if($("buttonSave")){
                      $("buttonSave").disabled =true;
                   }		        
		        }		           	         			        
		     }else{
		        if(confirm("确定要保存数据！")){
		           refreshForm("/xgxt/zjlgPjpy.do?method=xjbjAdd&doType=save");
                   if($("buttonSave")){
                      $("buttonSave").disabled =true;
                   }
		        }              
		     }
    	});	    
}
</script>
<body style="font-size:14px" onload="">
	<html:form action="/zjlgPjpy" method="post">
		<input type="hidden" id="userType" name="userType"
			value="<bean:write name="userType" scope="session"/>" />
		<input type="hidden" id="isFdy" name="isFdy"
			value="<bean:write name="fdyQx" scope="session"/>" />
		<input type="hidden" id="userName" name="userName"
			value="<bean:write name="userName" scope="session"/>" />
		<input type="hidden" name="njV" id="njV">
		<input type="hidden" name="xyV" id="xyV">
		<input type="hidden" name="zyV" id="zyV">
		<input type="hidden" name="bjV" id="bjV">

		<div class="title">
			<div class="title_img" id="title_m">
				当前位置：评奖评优 - 荣誉称号申请 - 先进班级增加
			</div>
		</div>
		<div id="items" name="items"
			style="display:none; position: absolute;background-color: #AFEEEE; ">
			<table class="tbstyle">
				<tr>
					<td>
						年级
					</td>
					<td>
						<html:select property="nj" onchange="initZyList();initBjList();"
							styleId="nj">
							<html:option value=""></html:option>
							<html:options collection="njList" property="nj"
								labelProperty="nj" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="lable.xsgzyxpzxy" />
					</td>
					<td>
						<html:select property="xydm" onchange="initZyList();initBjList();"
							styleId="xy">
							<html:option value=""></html:option>
							<html:options collection="xyList" property="xydm"
								labelProperty="xymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						专业
					</td>
					<td>
						<html:select property="zydm" onchange="initBjList();" styleId="zy">
							<html:option value=""></html:option>
							<html:options collection="zyList" property="zydm"
								labelProperty="zymc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td>
						班级
					</td>
					<td>
						<html:select property="bj" styleId="bj">
							<html:option value=""></html:option>
							<html:options collection="bjList" property="bjdm"
								labelProperty="bjmc" />
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan=2 align=center>
						<button type="button" class="button2" onclick="hideItems();">
							确 定
						</button>
					</td>
				</tr>
			</table>
		</div>
		<div align="left">
			<br>
			<font color="red"> 提示：此功能只有已分配权限的学校用户或管理员可操作，且添加信息后不需要审核即可通过。
			</font>
		</div>
		<table width="100%" class="tbstyle">
			<thead>
				<tr style="height:22px">
					<td colspan="4" align="center">
						<b>填写申请表</b>
					</td>
				</tr>
			</thead>
			<tr>
				<td scope="col" width="15%">
					<div align="right">
						<font color="red">*</font>班级代码：
					</div>
				</td>
				<td align="left" width="30%">
					<html:text property="bjdm" readonly="true" onclick="showItems()"
						styleId="bjdm" />

				</td>
				<td width="10%" align="right">
					学年：
				</td>
				<td align="left">
					<html:select property="xn" disabled="true">
						<html:options collection="xnList" property="xn" labelProperty="xn" />
					</html:select>
				</td>
			</tr>
			<tr>
				<td align="right">
					年级：
				</td>
				<td align="left">
					${rs.nj}
				</td>
				<td align="right">
					班级名称：
				</td>
				<td align="left">
					${rs.bjmc}
				</td>
			</tr>
			<tr>
				<td align="right">
					<bean:message key="lable.xsgzyxpzxy" />：
				</td>
				<td align="left">
					${rs.xymc}
				</td>
				<td align="right">
					辅导员：
				</td>
				<td align="left">
					${fdy}
				</td>
			</tr>
			<tr>
				<td align="right">
					专业：
				</td>
				<td align="left">
					${rs.zymc}
				</td>
				<td align="right">
					班级人数：
				</td>
				<td align="left">
					${xhnum}
				</td>
			</tr>
			<tr>
				<td align="right">
					班级平均成绩：
				</td>
				<td align="left">
					${bjpjf}
				</td>
				<td align="right">
					不及格率：
				</td>
				<td align="left">
					${bjbjdl}
				</td>
			</tr>			
			<tr>
				<td scope="row" align="right">
					文明寝室个数：
				</td>
				<td align="left">
					<html:text property="wmqsgs" styleId="wmqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)' maxlength="3"
						onblur="onlyNumInput(this)" />
				</td>
				<td align="right">
					A级寝室个数：
				</td>
				<td align="left">
					<html:text property="ajqsgs" styleId="ajqsgs"
						onkeypress='return sztzNumInputValue(this,3,event)'
						onblur="onlyNumInput(this)" />
				</td>
			</tr>
			<tr>
				<td scope="row" align="right">

				</td>
				<td align="left">

				</td>
				<td align="right">
					是否优秀班级：
				</td>
				<td align="left">
					<html:select property="sfyxbj" styleId="sfyxbj"
						style="width:120px;">
						<html:option value="否">否</html:option>
						<html:option value="是">是</html:option>
					</html:select>
				</td>
			</tr>			
			<tr>
				<td width="13%" scope="row" align="right">
					<p align="center">
						班
					</p>
					<p align="center">
						级
					</p>
					<p align="center">
						情
					</p>
					<p align="center">
						况
					</p>

					<span style="color: red">(限500字)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
				</td>
				<td colspan="3" scope="row" align="left">
					<html:textarea rows="12" style="width:98%" property="bjqk" />
				</td>
			</tr>
		</table>
		<div class="buttontool">
			<button type="button" class="button2" id="buttonSave" onclick="xjbjAddSave()">
				保 存
			</button>
			&nbsp;&nbsp;
			<button type="button" class="button2" id="buttonClose" onclick="Close();return false;">
				关 闭
			</button>
		</div>
	</html:form>
</body>
<logic:equal name="done" value="true">
	<script>
	alert("添加成功！");
    if(window.dialogArguments && window.dialogArguments.document.all("search_go")){
    	  window.dialogArguments.document.all("search_go").click();
    	  Close();
    }			          
    </script>
</logic:equal>
<logic:equal name="done" value="false">
	<script>
	alert("添加失败！");
	</script>
</logic:equal>
<logic:equal name="pass" value="no">
	<script>
	alert("该班级不满足先进班级申请条件！");			    
   </script>
</logic:equal>
</html:html>
