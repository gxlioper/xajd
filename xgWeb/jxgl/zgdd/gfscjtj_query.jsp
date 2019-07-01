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
	    var xn= "";
	    var xq="";
		var fwqx = document.getElementById("fwqx").value;
		var usertype = document.getElementById("usertype").value;
		if($("xn")){
		   xn=$("xn").value;
		}
		if($("xq")){
		   xq=$("xq").value;
		}
		if(xn==""){
		  alert("请选择学年！");
		  return false;
		}
		if(xq==""){
		  alert("请选择学期！");
		  return false;
		}
		
		if(fwqx == "no"){
			alert("本模块只能国防生进行操作!!");
			return false;
		}
			var sztj = document.getElementById("sztj").value;
			var tjlx = document.getElementById("tjlx").value;
			var szsz = document.getElementById("szsz").value;
			var bool = false;
			if(sztj!=""){
				bool = true;
			}
			if(tjlx!=""){
				bool = true;
			}
			if(szsz!=""){
				bool = true;
				if(!isNumber(szsz)){
					alert("条件输入框只能是数字");
					return false;
				}
			}
			if(bool){
				if(sztj==""){
					bool = false;
					alert("条件选择或者输入为空！！");
					return false;
				}
				
				if(tjlx==""){
					bool = false;
					alert("条件选择或者输入为空！！");
					return false;
				}
				
				if(szsz==""){
					bool = false;
					alert("条件选择或者输入为空！！");
					return false;
					}
			}
			
		 	document.forms[0].action = "zgdd_jxgl.do?method=gfscjgl&act=go&doType=query";
		 	document.forms[0].submit();
    }

	function isNumber(s){
		var p = /^(-|\+)?\d+$/;
		return p.test(s); 
	    } 

	 function  expdc(){
		 var xn= "";
	     var xq="";
		 var fwqx = document.getElementById("fwqx").value;
		 var usertype = document.getElementById("usertype").value;
		 	if($("xn")){
		   xn=$("xn").value;
		}
		if($("xq")){
		   xq=$("xq").value;
		}
		if(xn==""){
		  alert("请选择学年！");
		  return false;
		}
		if(xq==""){
		  alert("请选择学期！");
		  return false;
		}
		if(fwqx == "no"){
				alert("本模块只能国防生进行操作!!");
				return false;
		}
 		//if(fwqx == "no"){
		//		alert("本模块只能国防生进行操作!!");
		//		return false;
 		//}
		   document.forms[0].action = "zgdd_jxgl.do?method=gfscjgldc";
		   document.forms[0].target = "_blank";
		   document.forms[0].submit();
		   document.forms[0].target = "_self";
        }
     function isgfs(){
    		var fwqx = document.getElementById("fwqx").value;
    		if(fwqx == "yes"){
				alert("本模块只能国防生进行操作!!");
				return false;
    		}
     }
        
        	/*
	全部选中
	*/    
      function chec(){
         
         for(i=0;i<document.getElementsByName("pk").length;i++){
      	    document.getElementsByName("pk")[i].checked=document.getElementsByName("qbxz")[0].checked;
         }
      }		

	</script>
	<body>
		<html:form action="/zgdd_jxgl" method="post">
			
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />
			
			<input type="hidden" name="fwqx" id="fwqx" value="<bean:write name="fwqx"/>"/>
			<input type="hidden" name="usertype" id="usertype" value="<bean:write name="userType" scope="session"/>"/>
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 国防生管理 - 国防生成绩统计
				</div>
			</div>

				<legend>
					查 询
				</legend>

				<table width="100%" class="tbstyle">
					<thead>
						<tr style="cursor:hand">
							<td>
								学号：
								<logic:equal value="stu" name="userType" scope="session">
								 <html:text property="xh" style="width:100px" readonly="true"/>
								</logic:equal>
								<logic:notEqual value="stu" name="userType" scope="session">
									<html:text property="xh" style="width:100px"/>
								</logic:notEqual>
								&nbsp;&nbsp;姓名：
								<html:text property="xm" style="width:70px" />
								&nbsp;&nbsp;学年:
								<html:select property="xn" 
									styleId="nj" onchange="">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
								&nbsp;&nbsp;学期:
								<html:select property="xq" 
									styleId="xq" >
										<html:option value=""></html:option>
										<html:options collection="xqList" property="xqdm"
											labelProperty="xqmc" />
								</html:select>
							</td>
							<td width="10" rowspan="2" align="center" valign="middle">
								<button type="button" class="button2" style="height:40px" id="search_go"
									onclick="querygo()">
									查询
								</button>
							</td>
						</tr>
						<tr>
							<td>
								<bean:message key="lable.xsgzyxpzxy" />：
									<html:select property="xydm" 
										styleId="xy" onchange="initZyList();initBjList();">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
							
								&nbsp;&nbsp;专业：
								<html:select property="zydm" 
									styleId="zy" onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
								&nbsp;&nbsp;班级：
								<html:select property="bjdm" 
									styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
								<br>
								&nbsp;&nbsp;条件：
								<html:select property="sztj" >
									<html:option value=""></html:option>
									<html:option value="zkcs">总课程数</html:option>
									<html:option value="gkms">挂科门数</html:option>
									<html:option value="yxms">优秀门数</html:option>
									<html:option value="pjf">平均分</html:option>
									<html:option value="jd">学分绩点</html:option>
									<html:option value="cpzf">班级综合测评</html:option>
								</html:select>
								<html:select property="tjlx" >
									<html:option value=""></html:option>
									<html:option value=">">></html:option>
									<html:option value=">=">>=</html:option>
									<html:option value="=">=</html:option>
									<html:option value="<"><</html:option>
									<html:option value="<="><=</html:option>
								</html:select>
								<html:text property="szsz"></html:text>
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
						<font color="blue">提示：单击表头可以排序</font>
					</legend>
					<table width="100%" id="rsTable" class="tbstyle">
						<thead>
							<tr align="center" style="cursor:hand">
<%--								<td>--%>
<%--									<input type="checkbox" name="qbxz" value="all"--%>
<%--										onclick="chec('qbxz')">--%>
<%--								</td>--%>
								<logic:iterate id="tit" name="topTr" offset="0" >
									<td id="<bean:write name="tit" property="en"/>"
										onclick="tableSort(this)" nowrap="nowrap">
										<bean:write name="tit" property="cn" />
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="hidden" value="<bean:write name="v"/>" />
								</logic:iterate>
<%--								<td align="center">--%>
<%--									<logic:iterate id="v" name="s" offset="0" length="1">--%>
<%--										<input type="checkbox" name="pk"--%>
<%--											value="<bean:write name="v"/>">--%>
<%--									</logic:iterate>--%>
<%--								</td>--%>
									<td align="center">
										<bean:write name="s" property="xh"/>
									</td>
									<td align="center">
										<bean:write name="s" property="xm"/>
									</td>									
									<td align="center">
										<bean:write name="s" property="bjmc"/>
									</td>
									<td align="center">
										<bean:write name="s" property="zymc"/>
									</td>
                                    <td align="center">
										<bean:write name="s" property="xn"/>
									</td>
									<td align="center">
										<bean:write name="s" property="xq"/>
									</td>									
									<td align="center">
										<bean:write name="s" property="zkcs"/>
									</td>
									<td align="center">
										<bean:write name="s" property="gkms"/>
									</td>
									<td align="center">
										<bean:write name="s" property="yxms"/>
									</td>
									<td align="center">
										<bean:write name="s" property="pjf"/>
									</td>
									<td align="center">
										<bean:write name="s" property="jd"/>
									</td>
									<td align="center">
										<bean:write name="s" property="cpzf"/>
									</td>
							</tr>
						</logic:iterate>
					</table>
					<TABLE width="99%" id="rsTable1" class="tbstyle">
									<TR>
										<TD height="30px"></TD>
									</TR>
									<TR>
										<TD>
											<jsp:include flush="true"
												page="/sjcz/turnpage.jsp?form=zgddJxglForm"></jsp:include>
										</TD>
									</TR>
									<TR>
										<TD height="30px"></TD>
									</TR>
								</TABLE>
				</fieldset>
			</logic:notEmpty>
			<div class="buttontool" id="btn"
				style="position: absolute;left:0px;top:100px" width="100%">
				
				&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="expdc();" style="width:80px">
					导出数据
				</button>
			</div>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>
