<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
		%>
		<title><bean:message key="lable.title" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="正方软件 zfsoft" />

		<link rel="icon" href="images/icon.ico" type="image/x-icon" />
		<link id="csss"  />
		<base target="_self">
		<link id="csssDate" rel="stylesheet" rev="stylesheet"
			href="js/calendar.css" type="text/css" media="all" />
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
	function st_xx_modi(){
		var xxxh=document.all["xxxh"].value;
		if(xxxh==""){
			alert ("请填写选项序号");
			document.all["xxxh"].focus();
			return false;
		}
		var xxnr=document.all["xxnr"].value;
		if(xxnr==""){
			alert ("请填写选项内容");
			document.all["xxnr"].focus();
			return false;
		}
		var stlsh=document.all["stlsh"].value;
		document.all["st_xx_modi_flag"].value="yes";
		underDealWith();
		refreshForm('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=st_xx_modi&stlsh='+stlsh);
	}
	</script>
	</head>
	<body onload="checkWinType();">
		<html:form action="/xljk_xlcs_tkwh" method="post">
			<input type="hidden" id="xxlsh" name="xxlsh"
				value="<bean:write name="rs" property="xxlsh"/>" />
			<input type="hidden" id="st_xx_modi_flag" name="st_xx_modi_flag"
				value="no" />
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 心理测试 - 题库维护 - 选项维护</a>
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
			
			<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>修改选项信息</span>
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
										<button class="" onclick="st_xx_modi()"
										id="buttonSave">
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
					<tbody width="100%" class="">
						<tr>
							<th align="right">
								<font color="red">*</font>试题流水号
							</th>
							<td align="left">
								<html:text name='rs' property="stlsh" style="width: 120px"
									styleId="stlsh" readonly="true" />
							</td>
							<th align="right">
								<font color="red">*</font>选项序号
							</th>
							<td align="left">
								<html:text name='rs' property="xxxh" style="width: 120px"
									styleId="xxxh" />
							</td>
						</tr>
						<tr>
							<th align="right">
								选项分值
							</th>
							<td align="left">
								<html:text name='rs' property="xxfz" style="width: 120px"
									styleId="xxfz" />
							</td>
							<th align="right">
								是否显示
							</th>
							<td align="left">
								<html:select name="rs" property="xxxsbj" style="width:120px"
									styleId="xxxsbj">
									<html:options collection="ynList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>选项内容
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xxnr' style="width:100%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="update success">
					<script>
					alert("更新成功!");
					dialogArgumentsQueryChick();
					Close();
					</script>
				</logic:equal>
				<logic:equal name="message" value="update fail">
					<script>
					alert("更新失败!");
					document.getElementById("tmpdiv").innerHTML = "";
					</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
