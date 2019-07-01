<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 咨询师信息 - 查看咨询师信息</a>
			</p>
		</div>
		<html:form action="/xljk_zxsxx_view">
			<logic:present name="list" scope="request">
				<div class="tab">
					<table width="100%" border="0" class="formlist">

						<logic:iterate id="li" name="list" scope="request">
							<tbody>
								<tr>
									<th>
										咨询师编号
									</th>
									<td>
										<html:text name="li" property="ZXXBH" readonly="true" />
									</td>
									<th>
										性别
									</th>
									<td>
										<html:text name="li" property="ZXXXB" readonly="true" />
									</td>
								</tr>
								<tr>
									<th>
										咨询师姓名
									</th>
									<td>
										<html:text name="li" property="ZXXXM" readonly="true" />
									</td>
									<logic:present name="showZxszc">
										<th>
											专长
										</th>
										<td>
											<html:select property="zxszc" styleId="zxszc" value="${li.ZC}" style="width:70%">
												<html:options collection="zxszcList" property="dm"
													labelProperty="mc" />
											</html:select>
										</td>
									</logic:present>
									<logic:notPresent name="showZxszc">
										<th></th>
										<td></td>
									</logic:notPresent>
								</tr>
								<tr>
									<th>
										资格
									</th>
									<td colspan="3">
										<html:textarea name="li" property="ZXXZG" rows="8" cols="60"
											readonly="true"></html:textarea>
									</td>
								</tr>
								<tr>
									<th>
										简 介
									</th>
									<td colspan="3">
										<html:textarea name="li" property="JJ" rows="8" cols="60"
											readonly="true"></html:textarea>
									</td>
								</tr>
							</tbody>
						</logic:iterate>

						<tfoot>
							<tr>
								<td colspan="4">
									<div class="btn">
										<button id="Close" onclick="window.close();return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</logic:present>
		</html:form>
	</body>
	</html>