<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	</head>
	<body>
		<html:form action="/xpjpy_rysq" method="post" styleId="rysqForm">
			<html:hidden property="xh" />
			<html:hidden property="xmdm" />
			<div style='tab;width:100%; overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>基本信息</span>
							</th>
						</tr>
					</thead>
						<%@ include file="/xsgzgl/xpjpy/wdpj/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>申请奖项</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								评奖周期
							</th>
							<td>
								<bean:write property="xn" name="rysqForm"/>
							</td>
							<th>项目名称</th>
							<td>${xmwhModel.xmmc}</td>
						</tr>
						<tr>
							<th>
								申请理由
							</th>
							<td colspan="3" style="word-break:break-all;">
								<bean:write property="sqly" name="rysqForm" filter="false"/>
							</td>
						</tr>
						<tr>
							<th>
								限制条件
							</th>
							<td colspan="3" id="checkTd">
								<logic:notEmpty name="checkResult">
									<logic:iterate id="check" name="checkResult" indexId="i">
										<logic:equal value="true" name="check" property="result">
											<img src='images/ico_38.gif' title='符合条件'/> ${i+1}、${check.sqts}<br/>
										</logic:equal>
										<logic:equal value="false" name="check" property="result">
											<img src='images/ico_39.gif' name='faidImg' title='不符合条件'/> ${i+1}、${check.sqts}<br/>
										</logic:equal>
									</logic:iterate>
								</logic:notEmpty>
								<logic:empty name="checkResult">
									<font color='green'>无限制条件</font>
								</logic:empty>								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>审核信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="99%" style="text-align: center;">
									<tr>
										<th style="text-align: center;">序号</th>
										<th style="text-align: center;">审核岗位</th>
										<th style="text-align: center;">审核人</th>
										<th style="text-align: center;">审核结果</th>
										<th style="text-align: center;">审核时间</th>
										<th style="text-align: center;" width="20%">审核意见</th>
										<th style="text-align: center;">审核/调整奖项</th>
									</tr>
									<logic:present name="spjlList">
										<logic:iterate id="s" name="spjlList" indexId="i">
											<tr>
												<td>${i+1}</td>
												<td>${s.gwmc}</td>
												<td>${s.xm}</td>
												<td>${s.shztmc}</td>
												<td>${s.shsj}</td>
												<td>${s.shyj}</td>
												<td>${s.xmmc}</td>
											</tr>
										</logic:iterate>
										<logic:empty name="spjlList">
											<tr>
												<td colspan="7">未找到任何记录！</td>
											</tr>
										</logic:empty>
									</logic:present>
								</table>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height: 30px"></div>
			<div class='tab'>
				<table width="100%" border="0" class="formlist"
					style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" type="button" onclick="iFClose();">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</html:form>
	</body>
</html>