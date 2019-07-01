<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
	</head>
	
		<script type="text/javascript">
		function jzcj(){
			var bjdm = jQuery('#bj').val();
			allNotEmpThenGo('/xgxt/jyglTables.do?method=plszDykc&bjdm='+bjdm)
		}

	</script>
	
	<body onload="xyDisabled('xy');">
		<html:form action="/jyglTables" method="post">
			<input type="hidden" name="isFdy" id="isFdy" value="${fdyQx }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="userDep" name="userDep" value="${userDep }" />
			<input type="hidden" name="njV" id="njV" />
			<input type="hidden" name="xyV" id="xyV" />
			<input type="hidden" name="zyV" id="zyV" />
			<input type="hidden" name="bjV" id="bjV" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"为必填项
								</div>
								<div class="btn">
									<button onclick="saveUpdate('jyglTables.do?method=plszDykc&doType=save','bjdm')">
										保存
									</button>
									<button id="buttonSave" onclick="window.close();return false;">
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th width="30%">
								年级
							</th>
							<td width="70%">
								<html:select property="nj" styleId="nj"
									onchange="initZyList();initBjList()" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm"
									onchange="initZyList();initBjList()" styleId="xy"
									style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm" onchange="initBjList()"
									styleId="zy" style="width:180px">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>班级
							</th>
							<td >
								<html:select property="bjdm" styleId="bj" style="width:180px" onchange="jzcj()">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								选择课程
							</th>
							<td>
								<logic:present name="kcList">
									<logic:iterate id="k" name="kcList" indexId="i">
										<%if (i % 3 == 0){
										%>
										<br/>
										<%
										} %>
										<html:checkbox property="xkkh" value="${k.xkkh }">${k.kcmc }</html:checkbox>
									</logic:iterate>
								</logic:present>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
		<logic:present name="message">
			<script defer="defer">
				alert('${message}');
				window.close();
			</script>
		</logic:present>
	</body>
</html>
