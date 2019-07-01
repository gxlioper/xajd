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
<script language="javascript" src="js/function.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "/xgxt/jhzyzphcs.do?method=jhzyzphcs";
		 	document.forms[0].submit();
    }
    /*
	全部选中
	*/    
      function chec(){
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }		
      /*
	批量删除
	*/
      function del(url){
	    var RowsStr="!!#!!";
	
	    for (i=0; i<document.getElementsByName("pk").length; i++){
    	   if(document.getElementsByName("pk")[i].checked){
    		RowsStr+=document.getElementsByName("pk")[i].value+"!!#!!";
    	   }
	    }
	    document.forms[0].pkstring.value = RowsStr;
	       if (RowsStr=="!!#!!"){
	         alert("请选择要批量删除的记录！");
		   return false;
    	}
	
	    if (!confirm("确定要删除所选记录？")){
		   return false;
	    }
	    document.forms[0].action=url;
        document.forms[0].submit();
     }

		function zphcsUpdate(){
		var url ="/xgxt/jhzyzphcs.do?method=zphcsUpdata&pkValue=";
		var pkValue ="";
		if (curr_row == null) {
			alert("请选择要修改的数据！\n（单击相应的行）");
			return false;
			 } else {
			if (confirm("确定要修改该行数据吗？")) {
				 pkValue = curr_row.getElementsByTagName("input")[0].value;
		         url += pkValue;
		         showTopWin(url, 600, 370);
				return true;
			} else {
				return false;
			}
		   }
	    }	
		
		function dataExport() {
	       document.forms[0].action = "/xgxt/zphcs.do?act=expdata";
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
        }
        
		function zphcsAdd(){
			showTopWin('/xgxt/jhzyzphcs.do?method=zphcsUpdata',800,350);
		}
		function check_user()
		{
			var user=document.all['userType'].value;
			if("xy"==user)
			{
			document.getElementById('xydm').disabled=true;
			}
			else if("xx"==user)
			{
				document.getElementById('xydm').disabled=false;
			}
		}
		</script>
<body onload="check_user()"> 
<html:form action="/jhzyzphcs" method="post">
	<input type="hidden" id="realTable" name="realTable"
		value="<bean:write name='realTable' scope="request" />" />
	<input type="hidden" id="userType" name="userType"
				value="<bean:write name="userType" scope = "session"/>" />
	<input type="hidden" name="pkstring" value="" />
	<div class="title">
	<div class="title_img" id="title_m">当前所在位置：就业管理 - 就业招聘 - 招聘会次数</div>
	</div>
	<fieldset><legend> 查 询 </legend>

	<table width="100%" class="tbstyle">
		<thead>
			<tr style="cursor: hand">
				<td><bean:message key="lable.xsgzyxpzxy" />： <html:select property="xydm"  style="width:160px" styleId="xy">
					<html:option value=""></html:option>
					<html:options collection="xyList" property="xydm"
						labelProperty="xymc" />
				</html:select> &nbsp;&nbsp;&nbsp; 年度： <html:select property="nd" style="width:60px" styleId="nd">
					<html:options collection="ndList" property="nd"
						labelProperty="nd" />
				</html:select></td>
				
				<td width="10" rowspan="2" align="center" valign="middle">
				<input type="hidden" name="go" value="go" />
				<button class="button2" style="height: 40px" onclick="querygo()"
					id="search_go">查询</button>
				</td>
			</tr>
			<tr>

			</tr>
		</thead>
	</table>
	</fieldset>
	<logic:equal name="rsNum" value = "0">
		<br />
		<br />
		<p align="center">未找到任何记录！</p>
	</logic:equal>
	<logic:notEqual name="rsNum" value = "0">
		<fieldset><legend> 记录数： <bean:write name="rsNum" />
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <font color="blue">提示：双击一行可以查看详细信息</font>
		</legend>
		<table width="100%" id="rsTable" class="tbstyle">
			<thead>
				<tr align="center" style="cursor: hand">
					<td><input type="checkbox" name="qbxz" value="all"
						onclick="chec('qbxz')"></td>
					<logic:iterate id="tit" name="topTr" offset="1" length="12">
						<td id="<bean:write name="tit" property="en"/>">
							<bean:write name="tit" property="cn" /></td>
					</logic:iterate>
				</tr>
			</thead>
			<logic:iterate name="rs" id="s">
				<tr onclick="rowOnClick(this)" style="cursor: hand" ondblclick="">
					<logic:iterate id="v" name="s" offset="0" length="1">
						<input type="hidden" value="<bean:write name="v"/>" />
					</logic:iterate>
					<td align="center">
					<logic:iterate id="v" name="s" offset="0" length="1">
						<input type="checkbox" name="pk" value="<bean:write name="v"/>">
					</logic:iterate></td>
					<logic:iterate id="v" name="s" offset="1" >
						<td align="center"><bean:write name="v" /></td>
					</logic:iterate>
				</tr>
			</logic:iterate>
		</table>
		</fieldset>
	</logic:notEqual>



	<div class="buttontool" id="btn"
		style="position: absolute; left: 0px; top: 100px" width="98%">
	&nbsp;
	<button class="button2" onclick="zphcsAdd()" style="width: 80px">
				增加</button>
	&nbsp;
	<button class="button2" onclick="zphcsUpdate()"
		style="width: 80px">修 改</button>
	&nbsp;
	<button class="button2" onclick="del('/xgxt/jhzyzphcs.do?method=dellZphcs')"
		style="width: 80px">删除</button>
	</div>

	<logic:notEmpty name="delall">
		<logic:equal name="delall" value="ok">
			<script>
                      alert("批量删除成功!");
                      document.getElementById('search_go').click();
                    </script>
		</logic:equal>
		<logic:notEqual name="delall" value="ok">
			<script type="text/javascript">
                      alert("批量删除失败!");
                      document.getElementById('search_go').click();
                    </script>
				<</logic:notEqual>
	</logic:notEmpty>
</html:form>
<script type="text/javascript" src="js/bottomButton.js"></script>
</body>
</html>
