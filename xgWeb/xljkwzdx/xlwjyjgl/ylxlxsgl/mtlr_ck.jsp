<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/xljk_xlwjyjgl_ylxlxsglwh" method="post" styleId="ylxlxsglForm">
			<div style='tab;width:100%;height:480px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>面谈记录信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>面谈时间</th>
							<td  colspan="3">
									${ylxlxsxx.mtkssj}
									至
									${ylxlxsxx.mtjssj}
							</td>
						</tr>
						<tr>
							<th>面谈地点</th>
							
							<td colspan="3">
								${ylxlxsxx.mtdd}
							</td>
						</tr>
						<tr>
							<th>
								访谈内容
							</th>
							<td colspan="3" style="word-break: break-all;">
								${ylxlxsxx.ftnr}
							</td>
						</tr>
						<tr>
							<th>
								心理问题类型
							</th>
							<td colspan="3">
								${ylxlxsxx.lxmc}
							</td>
						</tr>
						<tr>
							<th>
								判断等级
							</th>
							<td colspan="3">
								${ylxlxsxx.gzdj}
							</td>
						</tr>
						<tr>
							<th>
								面谈小结及<br />
								建设辅导措施
							</th>
							<td colspan="3" style="word-break: break-all;">
								${ylxlxsxx.mtxjjjsfdcs}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>心理危机预警信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								心理问题类型
							</th>
							<td colspan="3">
								${yjkxx.lxmc}
							</td>
						</tr>
						<tr>
							<th>
								关注等级
							</th>
							<td colspan="1">
								${yjkxx.gzdj}
							</td>
							<th>
								入库时间
							</th>
							<td colspan="1">
									${yjkxx.rksj}
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3" style="word-break: break-all;">
								${yjkxx.bz}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
			<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" name="关 闭" onclick="iFClose();">
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

