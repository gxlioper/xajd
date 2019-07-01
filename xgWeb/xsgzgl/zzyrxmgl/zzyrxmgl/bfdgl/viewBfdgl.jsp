<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.*" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
	</head>
	<body style="width: 100%">
		<html:form action="/zzyrxmglbfdgl" method="post" styleId="bfdglForm" onsubmit="return false;">
			<input type="hidden" name="fdfbid" id="fdfbid" value="${rs.fdfbid }"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>发布人信息</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th>姓名</th>
						<td>${jbxx.xm }</td>
						<th>学号</th>
						<td>${jbxx.xh }</td>
					</tr>
					<tr>
						<th>所在学院</th>
						<td>${jbxx.xymc }</td>
						<th>专业</th>
						<td>${jbxx.zymc }</td>
					</tr>
					<tr>
						<th>班级</th>
						<td>${jbxx.bjmc }</td>
						<th>联系电话</th>
						<td>${jbxx.sjhm }</td>
					</tr>
					<thead>
						<tr>
							<th colspan="4">
								<span>信息详情</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								需要辅导科目
							</th>
							<td width="30%">
								${rs.fdkm}
							</td>
							</td>
							<th>
								辅导时间
							</th>
							<td>
								${rs.fdsj }
							</td>
			      		</tr>
						<tr>
							<th>开放<bean:message key="lable.xb" /></th>
							<td colspan="3">
								<table width="100%" border="0" class="formlist">
									<tr>
									<logic:iterate name="xyList" id="l" indexId="index">
										<td style="border:0px;">
										<span style="word-break:break-all;margin-right:15px;">${l.kfxymc }</span>
										</td>
										<%if(index.intValue()%3==2 && index.intValue() != 0){ %>
										</tr><tr>
										<%} %>
									</logic:iterate>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<th>
								备注
							</th>
							<td colspan="3">
								${rs.fbbz }
							</td>
			      		</tr>
					</tbody>
				 </table>
				</div>
			  <div style="height:35px"></div>   
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

