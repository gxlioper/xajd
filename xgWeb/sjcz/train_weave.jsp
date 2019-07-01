<%@ page language="java" contentType="text/html; charset=gb2312"%>

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
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<meta http-equiv="Content-Language" content="GBK" />
		<meta name="Copyright" content="正方软件 zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<script language="javascript">

    </script>
	<script language="javascript" src="js/function.js"></script>
	<base target="_self" />
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<html:form action="/train_weave" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					当前所在位置：军训管理 - 信息维护 - 建制维护
				</div>
			</div>
			<input type="hidden" name="mappingItems" value="" />
			<fieldset>
				<legend>
					建制维护
				</legend>
				<table width="98%" align="center" class="tbstyle">
					<thead>
						<tr align="center">
							<td width="30%">
								学校编制
							</td>
							<td width="30%">
								军训编制
							</td>
							<td width="40%">
								对应建制列表
							</td>
						</tr>
					</thead>
					<tr align="center">
						<td>
							<html:select property="xxbz" style="width:70px"
								onchange="refreshForm('/xgxt/train_weave.do')">
								<html:option value=""></html:option>
								<html:option value="xy"><bean:message key="lable.xsgzyxpzxy" /></html:option>
								<html:option value="zy">专业</html:option>
								<html:option value="bj">班级</html:option>
							</html:select>
							<html:select property="xxbzfx" style="width:150px"
								onchange="refreshForm('/xgxt/train_weave.do')">
								<html:option value=""></html:option>
								<logic:notEmpty name="xxbzfxList">
									<html:options collection="xxbzfxList" labelProperty="comments"
										property="column_name" />
								</logic:notEmpty>
							</html:select>
						</td>
						<td>
							<html:select property="jxbz" style="width:70px"
								onchange="changeJxbz()">
								<html:option value=""></html:option>
								<html:option value="团">团</html:option>
								<html:option value="营">营</html:option>
								<html:option value="连">连</html:option>
								<html:option value="排">排</html:option>
							</html:select>
							<input type="text" id="jxbzfx" name="jxbzfx" style="width: 150px"
								readonly="readonly"
								value="<bean:write name="bz" scope="request"/>" />
						</td>
						<td rowspan="3">
								<html:select property="mappingItem" style="width:270px;cursor:hand;" size="30" styleId="mappingList"
									ondblclick="deleteItemList()">
									<logic:notEmpty name="mappingList">
										<html:options collection="mappingList"
											labelProperty="comments" property="column_name" />
									</logic:notEmpty>
								</html:select>
						</td>
					</tr>
					<tr align="center">
						<td>
							<html:select property="excelItem" style="width: 200px" size="27"
								styleId="excelList">
								<logic:notEmpty name="excelItemList">
									<html:options collection="excelItemList"
										labelProperty="comments" property="column_name" />
								</logic:notEmpty>
							</html:select>
						</td>
						<td>
							<html:select property="oracleItem" style="width:200px;" size="27"
								styleId="oracleList"
								ondblclick="if(document.forms[0].excelList.selectedIndex>=0) addItemList();">
							</html:select>
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<input class="button2"  name="button1"
								style="width:70px" value=" + " onclick="addItemList()" />
							<input class="button2"  name="button2"
								style="width:70px" value=" - " onclick="deleteItemList()" />
							<input  name="button3" style="width:70px"
								class="button2" value="默 认" onclick="defaultItemList()" />
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input class="button2"  name="button1"
								style="width:100px" value="确 定" onclick="subBzList()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input  class="button2" name="button2"
								style="width:100px" value="重 置" onclick="clearList()" />
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<input  class="button2" name="button2"
								style="width:100px" value="重 新 编 制"
								onclick="if(confirm('确定要重新编制吗?')) refreshForm('/xgxt/train_weave.do?delete=do'); else return false;" />
						</td>
					</tr>
				</table>
			</fieldset>
			<div id="tmpdiv"></div>
		</html:form>
		<logic:notEmpty name="doFlag">
			<logic:equal name="doFlag" value="ok">
				<script>
					alert("操作成功!");
					document.forms[0].submit();
				</script>
			</logic:equal>
			<logic:equal name="doFlag" value="no">
				<script>alert("操作失败!")</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>
