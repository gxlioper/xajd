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
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>	
		<body onload="checkWinType();dataManLoad();">
	<script language="javascript" src="js/function.js"></script>
	<script type="text/javascript" src="js/calendar.js"></script>
	<script type="text/javascript" src="js/calendar-zh.js"></script>
	<script type="text/javascript" src="js/calendar-setup.js"></script>
	<script language="javascript">
			function checkNull(){
				for(var i=1;i<4;i++){
					var nd = document.getElementById("nd"+i).value;
					var je = document.getElementById("hjje"+i).value;
					if(je!=null && je!="" && nd!=null && nd!="" ){
						if(document.getElementById("hjqx"+i).value==null || document.getElementById("hjqx"+i).value=="" ){
							 alert("缓交期限不能为空！");
							 return false;
						}
					}
				}
				return true;
			}
			
			function submitForm(){//判断是否选中
			var valueOfXn="";
			var values="";
			var valStr="";
			 for(var i=1;i<4;i++){			
			 	values=document.getElementById("nd"+i).value;
			 	//alert(values);
				valueOfXn=valueOfXn+values;
				if(values!=""){
					 valStr=valStr+("nd-"+values+"!!"+"hjje-"+document.getElementById("hjje"+i).value+"!!"+"hjqx-"+document.getElementById("hjqx"+i).value+"!!"+"bz-"+document.getElementById("bz"+i).value);
					 valStr=valStr+"@@";
				}				
			}
			//alert(valStr);
			if(valueOfXn==null || valueOfXn==""){
				alert("请选择！");
				return false;
			}else{
				document.forms[0].action ="gzcjXfhj.do?val="+valStr;
				document.forms[0].submit();
			}
			}			
			
			function totalRate(){//总费用
			var sum=0;
			var tem=0;
			for(var i=1;i<4;i++){
				tem=document.getElementById("hjje"+i).value;
				sum=sum+tem*1;
			}
			document.getElementById("zje").value=sum;
		}		
</script>
		

	<html:form action="/gzcjXfhj" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				<span id="tipFollow"></span>
			</div>
		</div>
		<logic:notEmpty name="rs">
			<logic:equal name="rs" property="stuExists" value="no">
				<script>
    				alert("您输入的学号无效!");
   					 </script>
			</logic:equal>
			<input type="hidden" id="doType" name="doType"
				value="<bean:write name="doType" scope="request"/>" />
			<input type="hidden" id="pkValue" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />
			<input type="hidden" id="disableEle" name="disableEle"
				value="xm-xb-nj-xymc-zymc-bjmc-xz" />
			<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
			<input type="hidden" id="getStuInfo" name="getStuInfo"
				value="xm-xb-nj-xymc-zymc-bjmc-xz" />
			<input type="hidden" id="url" name="url" value="/rcsw/gzcj/xfhjb.jsp" />
			<table width="100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							学费缓缴
						</td>
					</tr>
				</thead>
				<tr>
					<td>
						<table class="tbstyle" width="100%">
							<tr>
								<td align="right">
									<font color="red">*</font>学号：
								</td>
								<td align="left">
									<html:text name='rs' property="xh" readonly="readonly"
										styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu" style="display:none">
										选择
									</button>
								</td>
								<td align="right">
									年级：
								</td>
								<td align="left">
									<html:text name='rs' property="nj" styleId="nj" />
								</td>
							</tr>
							<tr>
								<td align="right">
									姓名：
								</td>
								<td align="left">
									<html:text name='rs' property="xm" styleId="xm" />
								</td>
								<td align="right">
									<bean:message key="lable.xsgzyxpzxy" />：
								</td>
								<td align="left">
									<html:text name='rs' property="xymc" styleId="xymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									性别：
								</td>
								<td align="left">
									<html:text name='rs' property="xb" styleId="xb" />
								</td>
								<td align="right">
									专业：
								</td>
								<td align="left">
									<html:text name='rs' property="zymc" styleId="zymc" />
								</td>
							</tr>
							<tr>
								<td align="right">
									学制：
								</td>
								<td align="left">
									<html:text name='rs' property="xz" styleId="xz" />
								</td>
								<td align="right">
									班级：
								</td>
								<td align="left">
									<html:text name='rs' property="bjmc" styleId="bjmc" />
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td>
						<table class="tbstyle">
							<logic:equal value="add" name="doType">
								<tr height="15">
									<td align="right" width="40">
										<font color="red">*</font>年度：
									</td>
									<td align="left">
										<html:select name="rs" property="nd" style="width:90px" styleId="nd1">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
									<td align="right" width="60">
										缓交金额：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjje1" onchange="if(numFormatChk(this))totalRate();"></input>
									</td>
									<td align="right" width="60">
										缓交期限：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjqx1" onclick="return showCalendar('hjqx1','y-mm-dd');">
									</td>
									<td align="right" width="40">
										备注：
									</td>
									<td align="left" width="40">
										<input type="text" id="bz1"></input>
									</td>
								</tr>
								<tr height="15">
									<td align="right" width="40">
										<font color="red">*</font>年度：
									</td>
									<td align="left">
										<html:select name="rs" property="nd" style="width:90px" styleId="nd2">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
									<td align="right" width="60">
										缓交金额：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjje2" onchange="if(numFormatChk(this))totalRate();"></input>
									</td>
									<td align="right" width="60">
										缓交期限：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjqx2" onclick="return showCalendar('hjqx2','y-mm-dd');">
									</td>
									<td align="right" width="40">
										备注：
									</td>
									<td align="left" width="40">
										<input type="text" id="bz2"></input>
									</td>
								</tr>
								<tr height="15">
									<td align="right" width="40">
										<font color="red">*</font>年度：
									</td>
									<td align="left">
										<html:select name="rs" property="nd" style="width:90px" styleId="nd3">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
									<td align="right" width="60">
										缓交金额：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjje3" onchange="if(numFormatChk(this))totalRate();"></input>
									</td>
									<td align="right" width="60">
										缓交期限：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjqx3" onclick="return showCalendar('hjqx3','y-mm-dd');">
									</td>
									<td align="right" width="40">
										备注：
									</td>
									<td align="left" width="40">
										<input type="text" id="bz3"></input>
									</td>
								</tr>
								<td colspan="8" align="right">
									总计:
									<input type="text" id="zje" name="zje" />
								</td>
							</logic:equal>
							<logic:equal value="modi" name="doType">
							
								<logic:iterate id="hjxx" name="xfhjList" length="3"
									indexId="index">

									<tr height="15">
										<td align="right" width="40">
											<font color="red">*</font>年度：
										</td>
										<td align="left">
											<html:select name="hjxx" property="nd" style="width:90px"
												styleId="nd${index+1}">
												<html:options collection="xnList" property="nd"
													labelProperty="nd" />
											</html:select>
										</td>
										<td align="right" width="60">
											缓交金额：
										</td>
										<td align="left" width="40">
											<input type="text" id="hjje${index+1}" value="${hjxx.hjje}"
												onchange="if(numFormatChk(this))totalRate();"></input>
										</td>
										<td align="right" width="60">
											缓交期限：
										</td>
										<td align="left" width="40">
											<input type="text" id="hjqx${index+1}" value="${hjxx.hjqx}" onclick="return showCalendar('hjqx${index+1}','y-mm-dd');">
										</td>
										<td align="right" width="40">
											备注：
										</td>
										<td align="left" width="40">
											<input type="text" id="bz${index+1}" value="${hjxx.bz}"></input>
										</td>
									</tr>
								</logic:iterate>							
							<logic:equal value="1" name="rsNum">
								<tr height="15">
									<td align="right" width="40">
										<font color="red">*</font>年度：
									</td>
									<td align="left">
										<html:select name="rs" property="nd" style="width:90px" styleId="nd2">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
									<td align="right" width="60">
										缓交金额：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjje2" onchange="if(numFormatChk(this))totalRate();"></input>
									</td>
									<td align="right" width="60">
										缓交期限：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjqx2" onclick="return showCalendar('hjqx2','y-mm-dd');">
									</td>
									<td align="right" width="40">
										备注：
									</td>
									<td align="left" width="40">
										<input type="text" id="bz2"></input>
									</td>
								</tr>
								<tr height="15">
									<td align="right" width="40">
										<font color="red">*</font>年度：
									</td>
									<td align="left">
										<html:select name="rs" property="nd" style="width:90px" styleId="nd3">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
									<td align="right" width="60">
										缓交金额：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjje3" onchange="if(numFormatChk(this))totalRate();"></input>
									</td>
									<td align="right" width="60">
										缓交期限：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjqx3" onclick="return showCalendar('hjqx3','y-mm-dd');">
									</td>
									<td align="right" width="40">
										备注：
									</td>
									<td align="left" width="40">
										<input type="text" id="bz3"></input>
									</td>
								</tr>
							</logic:equal>
							<logic:equal value="2" name="rsNum">
								<tr height="15">
									<td align="right" width="40">
										<font color="red">*</font>年度：
									</td>
									<td align="left">
										<html:select name="rs" property="nd" style="width:90px" styleId="nd3">
											<html:options collection="xnList" property="nd"
												labelProperty="nd" />
										</html:select>
									</td>
									<td align="right" width="60">
										缓交金额：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjje3" onchange="if(numFormatChk(this))totalRate();"></input>
									</td>
									<td align="right" width="60">
										缓交期限：
									</td>
									<td align="left" width="40">
										<input type="text" id="hjqx3" onclick="return showCalendar('hjqx3','y-mm-dd');">
									</td>
									<td align="right" width="40">
										备注：
									</td>
									<td align="left" width="40">
										<input type="text" id="bz3"></input>
									</td>
								</tr>							
							</logic:equal>
							<td colspan="8" align="right">
									总计:
									<input type="text" id="zje" name="zje" />
								</td>
							</logic:equal>
						</table>
					</td>
				</tr>
			
						<logic:equal value="modi" name="doType">
							<script>
								totalRate();
							</script>
						</logic:equal>
					
			</table>
			<div class="buttontool" align="center">
				<button type="button" class="button2" onclick="dataCanModi(true)"
					style="width:80px" id="buttonModi">
					修 改
				</button>
				<button type="button" class="button2" onclick="if(checkNull())submitForm()" style="width:80px"
					id="buttonSave">
					保 存
				</button>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button type="button" class="button2" onclick="Close();return false;" style="width:80px"
					id="buttonClose">
					关 闭
				</button>
			</div>
		</logic:notEmpty>
	</html:form>
	<logic:notEmpty name="result" scope="request">
			<logic:equal value="true" name="result" scope="request">
				<script>
					alert("操作成功！");
					Close();					
					window.dialogArguments.document.getElementById('search_go').click();		
				</script>
			</logic:equal>
			<logic:equal value="false" name="result" scope="request">
				<script>
					alert("操作失败！");
					Close();					
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
