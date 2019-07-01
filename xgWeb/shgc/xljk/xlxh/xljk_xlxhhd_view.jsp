<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
		<html:form action="/xljk_xlxhhd" method="post">
			<div class="tab">
				<table width="100%"  border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button class="button2" onclick="Close();return false;">
									关 闭
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				<tbody>
					<tr>
						<th> 
							主 题
						</th>
						<td colspan="3">
							<html:text style="width:98%" property="zt" styleId="zt"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							活 动 形 式
						</th>
						<td>
							<html:text property="hdxs" styleId="hdxs" readonly="true" />
						</td>
						<th>
							其 他 形 式
						</th>
						<td>
							<html:text property="qthdxs" styleId="qthdxs" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							地 点
						</th>
						<td>
							<html:text property="dd" styleId="dd" readonly="true" />
						</td>
						<th>
							活 动 日 期
						</th>
						<td>
							<html:text style="cursor:hand;" styleId="dateF" property="rq"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							开 始 时 间
						</th>
						<td>
							<html:text property="kssj" styleId="kssj" readonly="true" />
						</td>
						<th>
							结 束 时 间
						</th>
						<td>
							<html:text property="jssj" styleId="jssj" />
						</td>
					</tr>
					<tr>
						<th>
							主 持 人
						</td>
						<td>
							<html:text property="zcr" styleId="zy" readonly="true" />
						</td>
						<th>
							学 生
						</th>
						<td>
							<html:text property="xs" readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							参 与 学 生
						</td>
						<td colspan="3">
							<html:text style="width:98%" property="cyxs" styleId="cyxs"
								readonly="true" />
						</td>
					</tr>
					<tr>
						<th>
							参 与 学 生 人 数
						</th>
						<td colspan="3">
							<html:text property="rs" styleId="rs" readonly="true" />
						</td>
					</tr>
				<tr>
					<th>
						活 动 记 录
					</th>
					<td colspan="3">
						<html:textarea rows="5" style="width:98%" property="hdjl"
							styleId="a" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
						 活 动 效 果
					</th>
					<td colspan="3">
						<html:textarea rows="5" style="width:98%" property="hdxg"
							styleId="a" readonly="true" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</html:form>
	</body>
</html>
