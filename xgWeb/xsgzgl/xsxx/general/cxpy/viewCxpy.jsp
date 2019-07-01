<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" >
		function saveForm(){

			var url = "xsxx_gygl_cxcxpy.do?method=updateCxpy&type=save";
			document.forms[0].action=url;
			document.forms[0].submit();
		}
		</script>
	</head>
	<body>
		<html:form action="/xsxx_gygl_cxcxpy.do?method=updateCxpy" method="post">
		<html:hidden property="pk"/>
		<%@ include file="/comm/hiddenValue.jsp"%>
			

				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生评定信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="xh"/>
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="xm"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								年级
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="nj"/>
							</td>
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="xymc"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								专业
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="zymc"/>
							</td>
							<th width="16%">
								班级
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="bjmc"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								学年
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="xn"/>
							</td>
							<logic:notEqual name="xxdm" value="13943">
								<th width="16%">
									学期
								</th>
								
								<td width="34%">
									<bean:write name="cxpyForm" property="xqmc"/>
								</td>
							</logic:notEqual>
							<logic:equal name="xxdm" value="13943">
								<th>评语日期</th>
								<td>
									<bean:write name="cxpyForm" property="sqsj"/>
								</td>
							</logic:equal>
						</tr>
						<tr>
							<th width="16%">
								操行等级
							</th>
							
							<td width="34%">
								<bean:write name="cxpyForm" property="cxdjmc"/>
							</td>
							<th width="16%">
								班主任
							</th>
							<td width="34%">
								<bean:write name="cxpyForm" property="bzr"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								操行评语
							</th>
							<td width="90%" colspan="3">
								<html:textarea rows="5"  property="cxpy" style="width:99%" readonly="true"></html:textarea>
							</td>
							
						</tr>
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="refreshParent2();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
		<logic:notEmpty name="message">
			<script type="text/javascript">
			alertInfo("${message }",function(tag){
			if(tag=="ok"){
				refreshParent2();
			}
			});
			</script>
		</logic:notEmpty>
	</body>
</html>

