<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/xljk_xlwygl_xxsbjgglwh" method="post" styleId="xlwyglxxsbjgglForm">
            <div style='tab;width:100%;height:380px;overflow-x:hidden;overflow-y:auto;'>
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
								<span><font color="blue" style="font-weight: bold;">${sbxxdata.sblxmc}</font>-上报信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<logic:notEqual value="2" name="sbxxdata" property="sblx">
							<tr>
								<th>
									学年
								</th>
								<td>
									${sbxxdata.xn}
								</td>
								<th>
									学期
								</th>
								<td>
									${sbxxdata.xqmc}
								</td>	
							</tr>
							<tr>
								<th width="100px">
									周次
								</th>
								<td>
									${sbxxdata.zbzc}
								</td>
								<th width="100px">
									起止日期
								</th>
								<td>
									${sbxxdata.zbqzrq}
								</td>
							</tr>
						</logic:notEqual>
						<logic:equal value="2" name="sbxxdata" property="sblx">
							<tr>
								<th>
									学年
								</th>
								<td>
									${sbxxdata.xn}
								</td>
								<th>
									学期
								</th>
								<td>
									${sbxxdata.xqmc}
								</td>	
							</tr>
						</logic:equal>
						<tr>
							<th width="100px">
								总体情况
							</th>
							<td colspan="3" style="word-break: break-all;">
								${sbxxdata.ztqk}
							</td>
						</tr>
						<tr>
							<th>
								心理学生<br />
								详细情况<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
							${sbxxdata.xlxsxxqk}
							</td>
						</tr>
						<tr>
							<th>
								备注
								<br />
							</th>
							<td colspan="3" style="word-break: break-all;">
								${sbxxdata.bz}
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

