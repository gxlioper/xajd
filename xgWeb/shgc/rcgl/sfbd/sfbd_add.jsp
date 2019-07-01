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
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
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
	<base target="_self">
	<script language="javascript">
	function sfbd_save()
	{
		var xz_flag =document.all["xz_flag"].value;
		if(xz_flag=="xnxq")
		{
			var xn_dm=document.all["xn_dm"].value;
			if ( xn_dm==""){
				alert ("请选择学年信息！");
				document.all["xn_dm"].focus();
				return false;
			}
			var xq_dm=document.all["xq_dm"].value;
			if ( xq_dm==""){
				alert ("请选择学期信息！");
				document.all["xq_dm"].focus();
				return false;
			}
		}
		else if(xz_flag=="xs")
		{
			var xh=document.all["xh"].value;
			if ( xh==""){
				alert ("请单击>>按键选择学生信息！");
				document.all["xh"].focus();
				return false;
			}
			var xn_dm=document.all["xn_dm"].value;
			if ( xn_dm==""){
				alert ("请选择学年信息！");
				document.all["xn_dm"].focus();
				return false;
			}
			var xq_dm=document.all["xq_dm"].value;
			if ( xq_dm==""){
				alert ("请选择学期信息！");
				document.all["xq_dm"].focus();
				return false;
			}
		}	
			document.all["add_flag"].value="yes";
			refreshForm('/xgxt/rcgl_sfbd.do?act=sfbd&doType=sfbd_add');
	}
		function check(obj)
		{
			var checkVal = obj.value;
				if(checkVal == "xs"){	
					document.all["xz_flag"].value="xs";
					document.getElementById('a1').style.display="block";
					document.getElementById('a2').style.display="block";
					document.getElementById('a3').style.display="block";
				}
			 	else if(checkVal == "xnxq")
			 	{		
					document.all["xz_flag"].value="xnxq";
					document.getElementById('a1').style.display="none";
					document.getElementById('a2').style.display="none";
					document.getElementById('a3').style.display="none";
					document.all["xh"].value="";
				}
		}
		function xzxs()
		{
			var xh=document.all["xh"].value;
			if(xh!="")
			{
				document.getElementById('xnxq').checked=false;
				document.getElementById('xs').checked=true;
				check(document.getElementById('xs'));
			}
			else
			{
				check(document.getElementById('xnxq'));
			}
		}
	</script>
	<body onload="xzxs();">
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/jsfunction.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/rcgl_sfbd" method="post">
		<input type="hidden" id="add_flag" name="add_flag" value="no" />
		<input type="hidden" id="xz_flag" name="xz_flag" value="xnxq" />
				<table  class="tbstyle" align="center">
					<thead>
						<tr style="height:22px">
							<td colspan="6" align="center">
								<b>学生报到信息</b>
							</td>
						</tr>
					</thead>
				<tr>
					 <td colspan="6"><div align="center">选择增加方式:</div></td>
				</tr>
				<tr style="height:22px">
					
                    <td align="right" colspan="2" >
                      
                	</td>
                	<td>
                		<input type="radio" name="xz" id="xnxq"  value="xnxq" checked onclick="check(this)" >
                		按学年学期增加
                	</td>
                	<td colspan="3">
                		<input type="radio" name="xz" id="xs" value="xs" onclick="check(this)" >
                		按具体学生
                	</td>
                	
                </tr>
					<tr style="height:22px" name="aa" id="a1">
							<td align="right" colspan="2">
								学 号：
							</td>
							<td align="left">
								<html:text  property="xh" styleId="xh"
									onblur="" onkeypress=""  readonly="true"/>
									<button onclick="showTopWin('/xgxt/rcgl_sfbd.do?act=sfbd&doType=stu_info',750,550);"
										class="btn_01" id="buttonFindStu">
										选择
									</button>
							</td>
							
						<td align="right">
							姓 名 ：
						</td>
						<td align="left" colspan="2">
							<html:text  property="xm" styleId="xm" readonly="true"/>
						</td>
					</tr >
					<tr style="height:22px" name="aa" id="a2">
						<td align="right" colspan="2" readonly="true" >
							性 别：
						</td>
						<td align="left">
							<html:text  property="xb" styleId="xb" readonly="true"/>
						</td>
						<td align="right">
							班 级:
						</td>
						<td align="left" colspan="2">
							<html:text  property="bjmc" styleId="bjmc" readonly="true"/>
						</td>
					</tr>
					<tr style="height:22px" name="aa" id="a3">
						<td align="right" colspan="2">
							学 院：
						</td>
						<td align="left">
							<html:text  property="xymc" styleId="xymc"  readonly="true"/>
						</td>
						<td align="right" >
						
						</td>
						<td align="left" colspan="2">
						</td>
					</tr>
					<tr style="height:22px">
						<td align="right" colspan="2">
							学年:
						</td>
						<td align="left">
							<html:select property="xn_dm" style="width:100px" styleId="xn_dm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="xnList" property="XN_ID"
										labelProperty="MKMC" />
							</html:select>
						</td>
						<td align="right" >
							学期：
						</td>
						<td align="left"  colspan="2">
							<html:select property="xq_dm" style="width:100px" styleId="xq_dm"
									onchange="">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="XN_ID"
										labelProperty="MKMC" />
							</html:select>
						</td>
					</tr>			
				</table>
				<div class="buttontool" align="center">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button class="button2"  onclick="sfbd_save()">
						保 存
					</button>
				</div>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
				<logic:notEmpty name="message">
					<logic:equal name="message" value="insert_success">
						<script>
						alert("增加成功!");
						//window.dialogArguments.document.getElementById("search_go1").click();
						</script>
					</logic:equal>
					<logic:equal name="message" value="insert_fail">
						<script>alert("增加失败!")</script>
					</logic:equal>
					<logic:equal name="message" value="date_exits">
						<script>alert("记录已经存在！增加失败!")</script>
					</logic:equal>
				</logic:notEmpty>
</html>
