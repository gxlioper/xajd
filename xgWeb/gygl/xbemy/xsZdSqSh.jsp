<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/xsgyglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getStuDetails.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>公寓管理 - 申请 - 学生走读(外住)申请</a>
			</p>
		</div>
		<!-- 标题 end-->	
		<html:form action="/XsgyglDispatch.do?method=xsZdSqSh" method="post">
		<input type="hidden" name="pkValue"
				value="<bean:write name="pkValue" scope="request"/>" />			
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">							
				<table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>单个审核</span>
						</th>
					</tr>
				</thead>
				<tbody>
						<tr>
							<th align="right">
								学号：
							</th>
							<td align="left">	
							 <bean:write name="rs" property="xh"/>							
							</td>
							<th align="right">
								外住开始学年：
							</th>
							<td align="left">
								<bean:write name="rs" property="xn"/>	
							</td>
						</tr>
						<tr>
						    <th align="right">
								姓名：
							</th>
							<td align="left">
								<bean:write name="rs" property="xm"/>	
							</td>						
							<th align="right">
								外住开始学期：
							</th>
							<td align="left">
							 <bean:write name="rs" property="xq"/>	
							</td>
						</tr>
						<tr>
							<th align="right">
								年级：
							</th>
							<td align="left">
								<bean:write name="rs" property="nj"/>
							</td>
							 <th align="right">
								外住开始日期：
							</th>
							<td align="left">
                                  <bean:write name="rs" property="wzksrq"/>	
                            </td>
						</tr>
						<tr>
							<th align="right">
								性别：
							</th>
							<td align="left">
								<bean:write name="rs" property="xb"/>
							</td>
							<th align="right">
								计划外住时间：
							</th>
							<td align="left">
								<bean:write name="rs" property="jhwzsj"/>
							</td>
						</tr>
						
						<tr>						   
							<th align="right">
								专业：
							</th>
							<td align="left">
								<bean:write name="rs" property="zymc"/>
							</td>
							<th align="right">
								外住类型：
							</th>
							<td align="left">
								<bean:write name="rs" property="wzlxmc"/>	
							</td>
						</tr>
						<tr>						   
							<th align="right">
								班级：
							</th>
							<td align="left">
								<bean:write name="rs" property="bjmc"/>
							</td>							
							<th align="right">
								外住地址：
							</th>
							<td align="left">
								<bean:write name="rs" property="wzdz"/>	
							</td>
						</tr>
						<tr>
							<th align="right">
								手机号码：
							</th>
							<td align="left">
								<bean:write name="rs"  property="sjhm"/>
							</td>							
							<th align="right">
								家长是否同意：
							</th>
							<td align="left">
								<bean:write name="rs" property="jzsfty"/>
							</td>
						</tr>
						<tr>
							<th align="right">
								固定电话：
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdh"/>
							</td>
							<th align="right">
								审核：
							</th>
							<td align="left">
								<html:select name="rs" property="yesNo">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>						
						<tr>						   
							<th align="right">
								电子邮箱：
							</th>
							<td align="left">
								<bean:write name="rs"  property="lxdzxx"/>
							</td>							
							<th align="right">
								
							</th>
							<td align="left">								
							</td>
						</tr>					
	
						<tr>
							<th align="right">
								外住原因：
							</th>
							<td align="left" colspan="3">
							    <bean:write name="rs" property="wzyy"/>								
							</td>
							
						</tr>
						</tbody>
						<tfoot>
							<tr bgcolor="EEF4F9" align="center">
								<td colspan="4">
									<div class="btn">
										<logic:equal value="yes" name="writeAble">
											<button id="buttonSave" 
												onclick="refreshForm('/xgxt/XsgyglDispatch.do?method=xsZdSqSh&doType=save');this.disabled=true"
												style="width: 80px">
												保	存
											</button>
										</logic:equal>
										<logic:equal value="no" name="writeAble">
											<script>
									         alert("该用户只有读权限!");
									        </script>
										</logic:equal>
										&nbsp;&nbsp;
										<button id="buttonClose" onclick="Close();return false;"
											style="width: 80px">
											关	闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
			</logic:notEmpty>
		</html:form>
		<logic:equal value="yes" name="done">
			<script>
				alert("操作成功!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		   </logic:equal>
		   <logic:equal value="no" name="done">
			<script>
				alert("操作失败!");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>	
	</body>
	
</html>
