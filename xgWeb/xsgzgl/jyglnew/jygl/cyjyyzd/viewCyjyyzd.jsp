<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script	type="text/javascript">
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/jyglnew_jygl_cyjyyzdgl" method="post" styleId="cyjyyzdForm">
			<html:hidden property="jyid"/>
			<html:hidden property="xh"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:330px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生基本信息</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xszbb/comm/viewStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>就业创业教育与指导信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
					    <tr>
							<th width="20%">手机号码</th>
							<td width="30%">
								${rs.sjhm }
							</td>
							<th width="20%">QQ号码</th>
							<td width="30%">
								${rs.qqhm }
							</td>
						</tr>
						<tr>
							<th width="">公司名称</th>
							<td width="" colspan="3">
								${rs.gsmc }
							</td>
					    </tr>
						<tr>
							<th width="">公司类型</th>
							<td width="">
								${rs.gslxmc }
							</td>
							<th width="">注册时间</th>
							<td width="">
								${rs.zcsj }
							</td>
					    </tr>
						<tr>
							<th width="">注册资本</th>
							<td width="">
								${rs.zczb }
							</td>
							<th width="">就业人数</th>
							<td width="">
								${rs.jyrs }
							</td>
					    </tr>
					    <tr>
					    	<th width="">所属行业</th>
							<td width="" colspan="3">
								${rs.sshymc }
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

