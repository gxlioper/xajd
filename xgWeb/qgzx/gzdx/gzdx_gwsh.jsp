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
		<meta http-equiv="Cache-Control" http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css" type="text/css" media="all" />
	<link id="csssDate" rel="stylesheet" rev="stylesheet" href="js/calendar.css" type="text/css" media="all" />
	<base target="_self" />
	<script language="javascript" src="js/function.js"></script>
		<script language="javascript">
		function save(){
			var xscyj = document.getElementById('xscyj').value;
			var sqsyrs = document.getElementById('sqsyrs').value;
			if (sqsyrs == null || sqsyrs == "") {
				alert("审批使用人数不能为空!");
				return false;
			}
			if(xscyj != ''){
					if(xscyj.replace(/[^\u0000-\u00ff]/g, "**").length > 150){	         
	          		 alert("学生处意见不能大于150个字符");
	          		 return false;
	       		 }
			}
			refreshForm('/xgxt/postChkOne.do?act=save');
		}
	</script>
	<body>		
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
						当前所在位置：勤工助学 - 审核 - 岗位审核
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">	
			<input type="hidden" name="pkVal" value="<bean:write name="rs" property="gwdm||gwsbsj"/>" />
			<input type="hidden" name="xhStr" value="<bean:write name="xhStr" />" />
			<input type="hidden" name="xxyjStr" value="" />			
				<table width="100%" id="rsT" class="tbstyle">
					<thead>
						<tr align="center">
							<td height="22" colspan="4">
								单个岗位审核
							</td>
						</tr>
					</thead>
					<tr>
						<td height="22" align="right">
							岗位名称：
						</td>
						<td height="22" align="left">
								${rs.gwdm }
						</td>
						<td height="22" align="right">
							用人单位(部门)：
						</td>
						<td height="22" align="left">
							${rs.yrdwmc }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							用人单位管理员姓名：
						</td>
						<td height="22" align="left" colspan="">
							${rs.fzr }
						</td>
						<td height="22" align="right">
							联系电话：
						</td>
						<td height="22" align="left" colspan="">
							${rs.lxdh }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							学年：
						</td>
						<td height="22" align="left">
							${rs.xn }
						</td>
						<td height="22" align="right">
							年度：
						</td>
						<td height="22" align="left">
							${rs.nd }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							工作开始日期：
						</td>
						<td height="22" align="left">
							${rs.gzksrq }
						</td>
						<td height="22" align="right">
							工作结束日期：
						</td>
						<td height="22" align="left">
							${rs.gzjsrq }
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							拟申请设立岗位数：
						</td>
						<td height="22" align="left">
							${rs.xyrs }
						</td>
						<td align="right">
							审核结果：
						</td>
						<td align="left">
							<html:select name="rs" property="yesNo">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td align="right">
							岗位性质：
						</td>
						<td align="left">
							${rs.gwxzmc}
						</td>
						<td height="22" align="right">
							<font color="red">*</font>审批使用人数：
						</td>
						<td height="22" align="left">
							<html:text name="rs" property="sqsyrs" style="width:130px" styleId="sqsysr" maxlength="4" onkeyup="value=value.replace(/[^\d]/g,'') "></html:text>
							人
						</td>						
					</tr>
					<tr>
						<td height="22" align="right">
							工作时间：
						</td>
						<td height="22" align="left" colspan="3">
							<html:textarea name="rs" property="gzsj" styleId="gzsj"
								style="width:100%;height:80px" disabled="true"/>
							(例：周一上，周二下...)
							<span id="gzsjDw"></span>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							工作内容：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gznr" styleId="gznr"
								style="width:100%" rows="5" onblur="chLeng(this,'150')" disabled="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							工作要求：
						</td>
						<td height="22" colspan="3" align="left">
							<html:textarea name="rs" property="gzyd" styleId="gzyd"
								style="width:100%" rows="5" onblur="chLeng(this,'150')" disabled="true"></html:textarea>
						</td>
					</tr>
					<tr>
						<td height="22" align="right">
							用人单位(部门)意见：
						</td>						
						<td height="22" colspan="3" align="left">
							<logic:equal value="yes" name="notYrdw">
								<html:textarea name="rs" property="sqdwyj" style="width:100%"
									onblur="chLeng(this,'100')" rows="5" readonly="true"></html:textarea>
							</logic:equal>
							<logic:notEqual value="yes" name="notYrdw">
								<html:textarea name="rs" property="sqdwyj" style="width:100%"
									onblur="chLeng(this,'100')" rows="5"></html:textarea>	
							</logic:notEqual>
						</td>
					</tr>					
					<tr>
						<td height="22" align="right">
							学工部意见：
						</td>
						<td height="22" align="left" colspan="3">
							<logic:equal value="yes" name="notYrdw">
								<html:textarea name="rs" property="xgbyj" styleId="xscyj" style="width:100%"
									onblur="chLeng(this,'100')" rows="5" ></html:textarea>
							</logic:equal>
							<logic:notEqual value="yes" name="notYrdw">
								<html:textarea name="rs" property="xgbyj" styleId="xscyj" style="width:100%"
									onblur="chLeng(this,'100')" rows="5" readonly="true"></html:textarea>
							</logic:notEqual>
						</td>
					</tr>
					
				</table>
				<div class="buttontool" align="center">
				<button type="button" class="button2"
					onclick="save();return false;"
					style="width:80px" id="buttonSave">
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
		<logic:equal value="view" name="result">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
