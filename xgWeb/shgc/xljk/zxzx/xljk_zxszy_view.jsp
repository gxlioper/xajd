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
				<em>您的当前位置:</em><a>心理健康 - 心理咨询 - 预约查询 - 详细信息</a>
			</p>
		</div>


	<html:form action="/xljk_zxszyAtion">
		<logic:present name="list" scope="request">
			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="window.close();return false;">
										关 闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								日期
							</th>
							<td>
								<html:text name="list" property="rq" readonly="true" />
							</td>
							<th>
								时间段
							</th>
							<td>
								<html:text name="list" property="sjd" readonly="true" />
							</td>
						</tr>
				<tr>

					<th>
						地点
					</th>
					<td>
						<html:text name="list" property="dd" readonly="true" />
					</td>
					<th>
						咨询师编号
					</th>
					<td width="156" height="20%">
						<html:text name="list" property="zxxbh" readonly="true" />
					</td>
				</tr>
				<tr >
					<th>
						咨询师姓名
					</th>
					<td>
						<html:text name="list" property="zxxxm" readonly="true" />
					</td>
					<th>
						咨询师性别
					</th>
					<td>
						<html:text name="list" property="sex" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
							咨询师资格
					</th>
					<td align="left" style="height:60px" colspan="3">
						<html:textarea name="list" property="zxxzg" rows="5"
							style="width:100%" readonly="true" />
					</td>
				</tr>
				<tr>
					<th>
							咨询师简介
					</th>
					<td colspan="3" align="left" style="height:60px;word-break:break-all;">
						<html:textarea name="list" property="jj" rows="5"
							style="width:100%" readonly="true" />
					</td>
				</tr>
				</tbody>
			</table>
			</div>
		</logic:present>
	</html:form>
</body>
</html>
