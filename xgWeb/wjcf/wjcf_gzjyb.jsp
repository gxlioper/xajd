<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
	</head>
	<body>



		<html:form action="/data_search" method="post">
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					有错误发生！
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    alert("您输入的学号无效!");
				    </script>
				</logic:equal>
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />



				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>跟踪教育</span>
								</th>
							</tr>
						</thead>
						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button type="button"
											onclick="refreshForm('/xgxt/SaveStuTrackTeach.do');alert('保存成功！');Close();window.dialogArguments.document.getElementById('search_go').click();"
											id="buttonSave">
											保 存
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button type="button" onclick="Close();return false;" id="buttonClose">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th width="16%">
									学号
								</th>
								<td width="34%">
									<bean:write name="rs" property="xh" scope="request" />
								</td>
								<th width="16%">
									年度
								</th>
								<td width="34%">
									<bean:write name="rs" property="nd" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									姓名
								</th>
								<td>
									<bean:write name="rs" property="xm" scope="request" />
								</td>
								<th>
									学年
								</th>
								<td>
									<bean:write name="rs" property="xn" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									性别
								</th>
								<td>
									<bean:write name="rs" property="xb" scope="request" />
								</td>
								<th>
									学期
								</th>
								<td>
									<bean:write name="rs" property="xq" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									年级
								</th>
								<td>
									<bean:write name="rs" property="nj" scope="request" />
								</td>
								<th>
									处分类别
								</th>
								<td>
									<bean:write name="rs" property="cflb" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<bean:write name="rs" property="xymc" scope="request" />
								</td>
								<th>
									处分事由
								</th>
								<td>
									<bean:write name="rs" property="cfyy" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									专业
								</th>
								<td>
									<bean:write name="rs" property="zymc" scope="request" />
								</td>
								<th>
									<font color="red">*</font>处分时间
								</th>
								<td>
									<bean:write name="rs" property="cfsj" scope="request" />
								</td>
							</tr>
							<tr>
								<th>
									班级
								</th>
								<td>
									<bean:write name="rs" property="bjmc" scope="request" />
								</td>
								<th>
									<font color="red">*</font>处分文号
								</th>
								<td>
									<bean:write name="rs" property="cfwh" scope="request" />
								</td>
							</tr>
						</tbody>
						<thead>
							<tr>
								<td colspan="4">
									<span>教育跟踪记录 &nbsp;&nbsp;<a
										onclick="document.all.child2.style.display=(document.all.child2.style.display =='none')?'':'none'">查看</a>
									</span>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4">
									<div id="child2" style="display:none">
										<table width="100%" border="1" align="center" class="tbstyle">
											<thead>
												<tr>
													<td>
														<div align="center" class="style2">
															第一季度教育情况记录
															<br />
															<font color="red">(限制在800字以内)</font>
														</div>
													</td>
												</tr>
											</thead>
											<tr>
												<td align="center">
													<html:textarea name='rs' property='xsbx1'
														style="width:500px" rows='6' onblur="checkLen(this,800)" />
												</td>
											</tr>
										</table>
										<table width="100%" border="1" align="center" class="tbstyle">
											<thead>
												<tr>
													<td>
														<div align="center" class="style2">
															第二季度教育情况记录
															<br />
															<font color="red">(限制在800字以内)</font>
														</div>
													</td>
												</tr>
											</thead>
											<tr>
												<td align="center">
													<html:textarea name='rs' property='xsbx2'
														style="width:500px" rows='6' onblur="checkLen(this,800)" />
												</td>
											</tr>
										</table>
										<table width="100%" border="1" align="center" class="tbstyle">
											<thead>
												<tr>
													<td>
														<div align="center" class="style2">
															第三季度教育情况记录
															<br />
															<font color="red">(限制在800字以内)</font>
														</div>
													</td>
												</tr>
											</thead>
											<tr>
												<td align="center">
													<html:textarea name='rs' property='xsbx3'
														style="width:500px" rows='6' onblur="checkLen(this,800)" />
												</td>
											</tr>
										</table>
										<table width="100%" border="1" align="center" class="tbstyle">
											<thead>
												<tr>
													<td>
														<div align="center" class="style2">
															第四季度教育情况记录
															<br />
															<font color="red">(限制在800字以内)</font>
														</div>
													</td>
												</tr>
											</thead>
											<tr>
												<td align="center">
													<html:textarea name='rs' property='xsbx4'
														style="width:500px" rows='6' onblur="checkLen(this,800)" />
												</td>
											</tr>
										</table>
									</div>
								</td>
							</tr>
						</tbody>
						<tr align="left">
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								意见
								<br />
								<font color="red">(限制在100字以内)</font>
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='xyyj' style="width:450px"
									rows='5' onblur="checkLen(this,100)" />
							</td>
						</tr>
					</table>
				</div>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
