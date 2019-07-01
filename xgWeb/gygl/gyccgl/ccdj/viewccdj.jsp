<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/gyccgl_ccdj" method="post" styleId="CcdjForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>寝室信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">学年</th>
							<td width="30%">
								${rs.xn}
							</td>
							<th width="20%">学期</th>
							<td width="30%">
								${rs.xqmc}
							</td>
						</tr>
						<tr>
							<th>楼栋</th>
							<td>
								${rs.ldmc}
							</td>
							<th>层号</th>
							<td>
								${rs.ch}
							</td>
						</tr>
						<tr>
							<th>寝室号</th>
							<td>
								${rs.qsh}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>登记信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4">
								<table width="100%">
									<thead>
										<th width="30%" style="text-align:left">物品名称</th>
										<th width="40%" style="text-align:left">损害程度</th>
										<th width="30%" style="text-align:left">估算费用(元)</th>
								    </thead>
								    <tbody id="iterate">
								    	<logic:iterate name="shcdList" id="i">
								    		<tr>
								    			<td>${i.wpmc}</td>
								    			<td>
								    				${i.shcdmc}
								    			</td>
								    			<td name="je" >${i.je}</td>
								    		</tr>
								    	</logic:iterate>
								    </tbody>
								</table>
							</td>
						</tr>
						<tr>
							<th>费用总计(元)</th>
							<td colspan="3">
								${rs.zje}
							</td>
						</tr>
						<tr>
							<th>备注(限1000字)</th>
							<td colspan="3">
								${rs.bz}
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" onclick="iFClose();">
										关闭
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