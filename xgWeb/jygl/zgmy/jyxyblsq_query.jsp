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
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" rel="stylesheet" rev="stylesheet"
			href="style/main.css" type="text/css" media="all" />
	</head>
	<base target="_self">
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript" src="js/jsFunction.js"></script>
	<script type="text/javascript" src="js/BatAlert.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getTowdays.js'></script>
	<script type='text/javascript'
		src='/xgxt/dwr/interface/getScoreinfo.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
	function querygo(){
		 	document.forms[0].action = "jygl_xyblgl.do?act=go&doType=query";
		 	document.forms[0].submit();
    }
   
   	function viewmore(){
		var url ="jyxyBlsqViewMore.do?act=view&pkValue=";
		var pkValue ="";

		   pkValue = curr_row.getElementsByTagName("input")[0].value;
		   url += pkValue;
		   showTopWin(url, 620, 470);
	
		}

	 function  jyglDataExport(){
	       var realTable = $("table").value;
	       document.forms[0].action = "jyxyslqdjDataExport.do?tableName="+realTable;
	       document.forms[0].target = "_blank";
	       document.forms[0].submit();
	       document.forms[0].target = "_self";
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
	      BatAlert.showTips('正在删除，请等待...');
	    document.forms[0].action=url;
        document.forms[0].submit();
     }
     
     	function xssqxxsh(doType){
			var url ="jyxyBlsqViewMore.do?act=view&doType=xxsh&pkValue=";
			var pkValue ="";
		
			if (doType == "xxshenhe") {
		   		if (curr_row == null) {
					alert("请选择要审核的数据！\n（单击相应的行）");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 620, 500);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}
	function xssqxysh(doType){
			var url ="jyxyBlsqViewMore.do?act=view&doType=xysh&pkValue=";
			var pkValue ="";
		
			if (doType == "xyshenhe") {
		   		if (curr_row == null) {
					alert("请选择要审核的数据！\n（单击相应的行）");
					return false;
		    	} else {	 
		   		 	pkValue = curr_row.getElementsByTagName("input")[0].value;
		    		url += pkValue;
		    		showTopWin(url, 620, 500);
		    		return true;
		    	}
		 	}else{
		    	return true;
		 	}
		}	
 
     
     
     
	</script>
	<body>
		<html:form action="/jygl_xylqdj" method="post">
			<input type="hidden" name="pkstring" value="" />
			<input type="hidden" id="querry" name="querry"
				value="<bean:write name='querry' scope="request" />" />
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name='realTable' scope="request" />" />
			<input type="hidden" id="table" name="table"
				value="<bean:write name='table' scope="request" />" />
			<input type="hidden" id="tableName" name="tableName"
				value="${tableName }" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：就业管理 - 就业协议方案 - 补领申请管理
				</div>
			</div>
			<fieldset>
				<legend>
					查 询
				</legend>

				<table width="100%" class="tbstyle">
					<thead>
						<tr style="cursor:hand">
							<td>
								学号：
								<html:text name="rs1" property="xh" style="width:100px"/>
								&nbsp;&nbsp;姓名：
								<html:text name="rs1" property="xm" style="width:70px" />
								&nbsp;&nbsp;学年:
								<html:select name="rs1" property="nj" style="width:100"
									styleId="nj" onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
								&nbsp;&nbsp;
								<bean:message key="lable.xsgzyxpzxy" />审核：
								<html:select name="rs1" property="xysh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="已通过√">已通过√</html:option>
									<html:option value="未通过X">未通过X</html:option>
								</html:select>
								&nbsp;&nbsp;
								学校审核：
								<html:select name="rs1" property="xxsh">
									<html:option value=""></html:option>
									<html:option value="未审核">未审核</html:option>
									<html:option value="已通过√">已通过√</html:option>
									<html:option value="未通过X">未通过X</html:option>
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button class="button2" style="height:40px" id="search_go"
									onclick="querygo()">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								<logic:equal name="who" value="xx">
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select name="rs1" property="xydm" style="width:180px"
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								<logic:equal name="who" value="xy">
									<bean:message key="lable.xsgzyxpzxy" />：
									<html:select name="rs1" property="xydm" style="width:180px"
										styleId="xy" disabled="true">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</logic:equal>
								&nbsp;&nbsp;专业：
								<html:select name="rs1" property="zydm" style="width:190px"
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select name="rs1" property="bjdm" style="width:190px"
									styleId="bj">
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
						<font color="blue">提示：单击表头可以排序,双击一行可查看详细信息</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="qbxz" value="all"
										onclick="chec('qbxz')">
								</td>
								<logic:iterate id="tit" name="topTr" offset="0" length="8">
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap>
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
								<td>
									<bean:message key="lable.xsgzyxpzxy" />审核
								</td>
								<td>
									学校审核
								</td>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="viewmore()" align="center">
								<td align="center">
								<input type="hidden"
									value="<bean:write name='s' property='xh' />" />
									<input type="checkbox" name="pk"
										value="<bean:write name="s" property="xh"/>" />
								</td>
								<td>
									<bean:write name='s' property='行号' />
								</td>
								<td>
									<bean:write name='s' property='xh' />
								</td>
								<td>
									<bean:write name='s' property='xm' />
								</td>
								<td>
									<bean:write name='s' property='xb' />
								</td>
								<td>
									<bean:write name='s' property='nj' />
								</td>
								<td>
									<bean:write name='s' property='xymc' />
								</td>
								<td>
									<bean:write name='s' property='zymc' />
								</td>
								<td>
									<bean:write name='s' property='bjmc' />
								</td>
								<td>
									<bean:write name='s' property='xyshimg' filter="false" />
								</td>
								<td>
									<bean:write name='s' property='xxshimg' filter="false" />
								</td>
							</tr>
						</logic:iterate>
					</table>
					<table width="100%" id="Table" class="tbstyle">
						<TR>
							<TD>
								<jsp:include flush="true"
									page="/sjcz/turnpage.jsp?form=commanForm"></jsp:include>
							</TD>
						</TR>
					</table>
				</fieldset>
			</logic:notEmpty>
			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="100%">
				<button class="button2" onclick="xssqxysh('xyshenhe');"
					style="width:80px">
					<bean:message key="lable.xsgzyxpzxy" />审核
				</button>
				&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="xssqxxsh('xxshenhe');"
					style="width:80px">
					学校审核
				</button>
				&nbsp;&nbsp;&nbsp;
				<button class="button2" onclick="dataExport()" style="width:80px">
					导出数据
				</button>
				&nbsp;&nbsp;&nbsp;
				<button class="button2"
					onclick="del('jygl_xylqdj.do?doType=delall&act=go')"
					style="width:80px">
					批量删除
				</button>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
		<logic:notEmpty name="delall">
			<logic:equal name="delall" value="ok">
				<script>
                      alert("批量删除成功!");
                    </script>
			</logic:equal>
			<logic:equal name="delall" value="no">
				<script>
                      alert("批量删除失败");
                    </script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
