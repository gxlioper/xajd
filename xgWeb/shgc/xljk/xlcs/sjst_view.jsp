<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss"  />
	<base target="_self">
	<%
	response.setHeader("Pragma","No-cache");
	response.setHeader("Cache-Control","no-cache");
	response.setDateHeader("Expires", 0);
	%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/xljk_xlcs_tkwh" method="post">

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a><bean:write name="tips" scope="request"/></a>
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
									<span>试题详细信息</span>
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
								<font color="red">*</font>试卷名称
							</th>
							<td align="left">
								<html:text name='rs' property="sjm" style="width: 120px"
									styleId="stfz" readonly="true"/>
							</td>
							<th align="right">
								<font color="red">*</font>试卷流水号
							</th>
							<td align="left">
								<html:text name='rs' property="sjlsh" style="width: 120px"
									styleId="stfz" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>试题类型
							</th>
							<td align="left">
								<html:text name='rs' property="stlxmc" style="width: 120px"
									styleId="stfz" readonly="true"/>
							</td>
							<th align="right">
								试题难度级别
							</th>
							<td align="left">
								<html:text name='rs' property="stndjbmc" style="width: 120px"
									styleId="stfz" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								<font color="red">*</font>试题计分方式
							</th>
							<td align="left">
									<html:text name='rs' property="stjffs" style="width: 120px"
									styleId="stfz" readonly="true"/>
							</td>
							<th align="right">
								试题分值
							</th>
							<td align="left">
								<html:text name='rs' property="stfz" style="width: 120px"
									styleId="stfz" onblur="" readonly="true"/>
							</td>
						</tr>				
						<tr>
							<th align="right">
								<font color="red">*</font>试题答案
							</th>
							<td align="left">
								<html:text name='rs' property="stda" style="width: 120px"
									styleId="stda" readonly="true"/>
							</td>
							<th align="right">
								是否显示
							</th>
							<td align="left">
								<html:text name='rs' property="stxsbj" style="width: 120px"
									styleId="STXSBJ" readonly="true"/>
							</td>
						</tr>				
						<tr>
							<th align="right">
								试题所属类别
							</th>
							<td align="left">
								<html:text name='rs' property="sslxmc" style="width: 120px"
									styleId="stda" readonly="true"/>
							</td>
							<td align="right"></td>
							<td align="left"></td>
						</tr>			
						<tr align="left">
							<th align="right">
								<font color="red">*</font>试题内容
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='stnr' style="width:95%"
									rows='6' readonly="true"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								试题答案解释
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='stdajs' style="width:95%"
									rows='5' readonly="true"/>
							</td>
						</tr>
						</tbody>
					</table>
				</div>
				</fieldset>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
