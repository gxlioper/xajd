<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<base target="_self">
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<title><bean:message key="lable.title" />
		</title>
		
		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss" />
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script language="javascript">
		function xljk_xlcs_zjst(){
			var stlxdm=document.all["stlxdm"].value;
			if(stlxdm==""){
				alert ("请选择试题类型代码");
				document.all["stlxdm"].focus();
				return false;
			}
			var stjffs=document.all["stjffs"].value;
			if(stjffs==""){
				alert ("请选择试题记分方式");
				document.all["stjffs"].focus();
				return false;
			}else if(stjffs=="1"){
				var stfz=document.all["stfz"].value;
				if(stfz==""){
					alert ("请选择试题分值");
					document.all["stfz"].focus();
					return false;
				}
			}
			
			var stnr=document.all["stnr"].value;
			if(stnr==""){
				alert ("请填写试题内容");
				document.all["stnr"].focus();
				return false;
			}
			
			var stda=document.all["stda"].value;
			if(stda==""){
				alert ("请填写试题答案");
				document.all["stda"].focus();
				return false;
			}
			document.getElementById("add_flag").value="yes";
			underDealWith();
			refreshForm('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_add');
		}
		
		function afterSaveSuccess(){
			alert("保存成功!");
			if(confirm("是否要继续增加试题?")){
				var clearSelect = "stlxdm-stndjbdm-stjffs";
				var clearText = "stfz-stda-stnr-stdajs-stxsbj";
				var clearSelectArr = clearSelect.split("-");
				for(var i=0;i<clearSelectArr.length;i++){
					document.getElementById(clearSelectArr[i]).options[0].selected = true;
				}
				var clearTextArr = clearText.split("-");
				for(var i=0;i<clearTextArr.length;i++){
					document.getElementById(clearTextArr[i]).value = "";
				}
			}else{
				dialogArgumentsQueryChick();
				Close();
			}	
		}	
	</script>
	</head>
	<body onload="checkWinType();">
		<html:form action="/xljk_xlcs_tkwh" method="post">
		<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 心理测试 - 题库维护</a>
				</p>
			</div>
			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					未找到任何记录！
				</p>
			</logic:empty>
			
			<logic:notEmpty name="rs">
				<input type="hidden" id="add_flag" name="add_flag" value="no" />
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>题库维护</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button class="" onclick="xljk_xlcs_zjst()" id="buttonSave">
											保存
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button class="" onclick="Close();return false;"
											id="buttonClose">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>	
					<tbody>
						<tr>
							<th align="right">
								<font color="red">*</font>试题类型
							</th>
							<td align="left">
								<html:select name="rs" property="stlxdm" style="width:120px" styleId="stlxdm">
									<html:option value=""></html:option>
									<html:options collection="stlxList" property="STLXDM"
										labelProperty="STLXMC" />
								</html:select>
							</td>
							<th align="right">
								试题难度级别
							</th>
							<td align="left">
								<html:select name="rs" property="stndjbdm" style="width:120px" styleId="stndjbdm">
									<html:option value=""></html:option>
									<html:options collection="stndjbList" property="STNDJBDM"
										labelProperty="STNDJBMC" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>试题计分方式
							</th>
							<td align="left">
								<html:select name="rs" property="stjffs" style="width:120px"
									styleId="stjffs"
									onchange="if(document.forms[0].stjffs.value==1) document.forms[0].stfz.disabled=false;else{ document.forms[0].stfz.value='';document.forms[0].stfz.disabled=true;}">
									<html:option value=""></html:option>
									<html:option value="1">按题</html:option>
									<html:option value="0">按选项</html:option>
								</html:select>
							</td>
							<th align="right">
								试题分值
							</th>
							<td align="left">
								<html:text name='rs' property="stfz" style="width: 120px"
									styleId="stfz" onblur="numFormatChk(this)" />
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>试题答案
							</th>
							<td align="left">
								<html:text name='rs' property="stda" style="width: 120px"
									styleId="stda" />
							</td>
							<th align="right">
								是否显示
							</th>
							<td align="left">
								<html:select name="rs" property="stxsbj" style="width:120px"
									styleId="stxsbj">
									<html:options collection="ynList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								<font color="red">*</font>试题内容
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='stnr' style="width:95%" styleId="stnr"
									rows='6' />
							</td>
						</tr>
						<tr>
							<th align="right">
								试题答案解释
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='stdajs' style="width:95%" styleId="stdajs"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="insert success">
					<script type="text/javascript">
						afterSaveSuccess();
					</script>
				</logic:equal>
				<logic:equal name="message" value="no">
					<script type="text/javascript">
						alert("保存失败!");
						document.getElementById("tmpdiv").innerTTML = "";
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
