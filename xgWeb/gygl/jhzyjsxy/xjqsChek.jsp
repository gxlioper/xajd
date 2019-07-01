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
<html:html>
<base target="_self" />
<head>


	<title><bean:message key="lable.title" />
	</title>
	<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
	<meta name="Copyright" content="正方软件 zfsoft" />

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<link id="csss" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<%
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);
	%>
	<script language="javascript" src="js/commanFunction.js"></script>
	<script language="javascript" src="js/function.js"></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
	<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
	<script type='text/javascript' src='dwr/util.js'></script>
	<script language="javascript" src="js/AjaxFunction.js"></script>
	<script language="javascript">
function dataSave(){
	var dj = document.getElementById('dj').value;
	if(dj == ''){  
		alert('申报等级不能为空！'); 
		return false;   
	}
	refreshForm("/xgxt/jhzy_gygl.do?method=xjqsChek&doType=save");	         
}
	</script>
</head>
<body>
	<div class="title">
		<div class="title_img" id="title_m">
			当前所在位置：公寓管理 - 星级文明寝室管理 - 审核 - 星级寝室单个审核
		</div>
	</div>
	<html:form action="/jhzy_gygl" method="post">
		<input type="hidden" name="userType" value="${userType}">
		<input type="hidden" name="pkValue" value="${pkValue}">
		<table class="tbstyle" width="90%">
			<tr>
				<td align="center" width="16%">
					楼栋
				</td>
				<td align="left" width="34%">
					<bean:write name='rs' property="ldmc" />
				</td>
				<td width="16%">
					<div align="center">
						楼层
					</div>
				</td>
				<td width="34%">
					<bean:write name='rs' property="cs" />
				</td>
			</tr>
			<tr>
				<td width="16%">
					<div align="center">
						寝室
					</div>
				</td>
				<td width="34%">
					<bean:write name="rs" property="qsh" />
				</td>			
				<td>
					<div align="center">
						学年
					</div>
				</td>
				<td>
					<bean:write name="xn" />
				</td>
			</tr>
			<tr>
				
								<td width="16%">
									<div align="center">
										联系电话
									</div>
								</td>
								<td width="34%">
									<bean:write name='rs' property="lxdh" />
								</td>
				<td>
					<div align="center">
						学期
					</div>
				</td>
				<td>
					<bean:write name="xqmc"/>
				</td>
			</tr>
			<tr>
					<td>
						<div align="center">
						
						</div>
					</td>
					<td>
						
					</td>
					<td>
						<div align="center">
							月份
						</div>
					</td>
					<td>
                       <bean:write name="yf"/>
					</td>
				</tr>
				<tr>
					<td>
						<div align="center">
							卫生优秀次数
						</div>
					</td>
					<td>
						<bean:write name="wsJc" property="anum"/>
					</td>
					<td>
						<div align="center">
							卫生达标次数
						</div>
					</td>
					<td>
                      <bean:write name="wsJc" property="dbnum"/>
					</td>
				</tr>
			<tr>
				<td>
					<div align="center">
						<font color="red">*</font>申报等级
					</div>
				</td>
				<td align="left">
					<html:select name='rs' property="dj" styleId="dj">
						<html:option value=""></html:option>
						<html:option value="三星级">三星级</html:option>
						<html:option value="四星级">四星级</html:option>
						<html:option value="五星级">五星级</html:option>
					</html:select>
				</td>
				<td>
					<div align="center">
						<font color="red">*</font>
						<logic:notEqual value="yes" name="view">
							审核状态
						</logic:notEqual>
						<logic:equal value="yes" name="view">
							学校审核
						</logic:equal>
					</div>
				</td>
				<td>
					<html:select name='rs' property="shzt" styleId="shzt">
						<html:options collection="chkList" property="en" labelProperty="cn"></html:options>
					</html:select>
				</td>
			</tr>
			<tr>
				<td>
					<div align="center">
						寝室建设情况
						<br>
						<font color="red"> </font>
					</div>
				</td>
				<td colspan="3">
					<html:textarea name="rs" property="qsjsqk" rows='8' readonly="true"
						style="width:100%" />
				</td>
			</tr>
		</table>
		<div class="buttontool" id="btn" style="position: absolute;width:90%">
			<logic:notEqual value="yes" name="view">
			<button class="button2" onClick="dataSave();" id="buttonSave" 
			<logic:present name="rs" property="sjsh">
			<logic:notEqual value="未审核" name="rs" property="sjsh">
				disabled="true"
			</logic:notEqual>
			</logic:present>
			>		
				提 交
			</button>
			</logic:notEqual>
			&nbsp;&nbsp;
			<button class="button2" onClick="Close()">
				关 闭
			</button>
		</div>

	</html:form>
	<logic:equal value="true" name="result">
		<script type="text/javascript">
			    alert('操作成功！');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
	</logic:equal>
	<logic:equal value="false" name="result">
		<script type="text/javascript">
			    alert('操作失败！');
			  </script>
	</logic:equal>
</body>
</html:html>
