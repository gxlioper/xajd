<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<base target="_self" />
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
	function sj_save(){
		var sjm=document.all["sjm"].value;
		if ( sjm==""){
			alert ("请填写试卷名");
			document.all["sjm"].focus();
			return false;
		}
		document.getElementById("add_flag").value="yes"; 
		underDealWith();
		refreshForm('/xgxt/xljk_xlcs_tkwh.do?act=tkwh&doType=Insert');
	}
</script>
	</head>
	<body onload="checkWinType();">
		<html:form action="/data_search" method="post">
			
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>心理健康 - 心理测试 - 试卷试题维护</a>
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
				<input type="hidden" id="disableEle" name="disableEle" value="" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="url" name="url" value="/sjcz/sjb.jsp" />
				<input type="hidden" id="add_flag" name="add_flag" value="no" />
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>试卷试题维护</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"
										<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button class="" onclick="sj_save()"
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
					<tbody>
						<tr>
							<th align="right" width="20%">
								<font color="red">*</font>测试项目名
							</th>
							<td align="left">
								<html:select name="rs" property="xlcsxmdm" style="width:200px"
									styleId="xlcsxmdm">
									<html:options collection="csxmList" property="dm"
										labelProperty="mc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th align="right" >
								<font color="red">*</font>试卷名
							</th>
							<td align="left">
								<html:text name='rs' property="sjm" style="width: 300px"
									styleId="sjm" />
							</td>
						</tr>
						<tr>
							<th align="right">
								时间限定
							</th>
							<td align="left">
								<html:text name='rs' property="sjxd" style="width: 100px"
									styleId="sjxd" onblur="numFormatChk(this)" />
								(分钟)
							</td>
						</tr>
						<tr>
							<th align="right">
								是否显示
							</th>
							<td align="left">
								<html:select name="rs" property="sjxsbj" style="width:100px"
									styleId="sjxsbj" value="否">
									<html:options collection="ynList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr align="left">
							<th align="right">
								试卷说明
							</th>
							<td>
								<html:textarea name='rs' property='sjsm' style="width:340px"
									rows='8' />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
			</logic:notEmpty>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="insert success">
					<script>
							alert("保存成功!");
							dialogArgumentsQueryChick();
							Close();
						</script>
				</logic:equal>
				<logic:equal name="message" value="insert fail">
					<script>
							alert("保存失败!");
							document.getElementById("tmpdiv").innerHTML = "";
						</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
