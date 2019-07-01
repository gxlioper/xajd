<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
	</head>
	<body>
		<html:form action="/dtjs_tyzc" method="post" styleId="tyzcForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:271px;margin-bottom: 0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span class="red"></span>学号
							</th>
							<td width="30%">
								<a href="javascript:void(0);" class="name" 
									   onclick="showDialog('学生详细信息',700,500,'xsxx_xsxxgl.do?method=xsxxglCk&xh=<bean:write name="jbxx" property="xh"/>')"
									   style="margin-left: 1px;"
									 ><bean:write name="jbxx" property="xh"/>
							</td>
							<th width="20%">
								姓名
							</th>
							<td width="30%">
								${jbxx.xm }
							</td>
						</tr>
						<tr>
							<th>
								性别
							</th>
							<td>
								${jbxx.xb }
							</td>
							<th>
								身份证号
							</th>
							<td>
								${jbxx.sfzh }
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${jbxx.nj }
							</td>
							<th>
								<bean:message key="lable.xb" />
							</th>
							<td>
								${jbxx.xymc }
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${jbxx.zymc }
							</td>
							<th>
								班级
							</th>
							<td>
								${jbxx.bjmc }
							</td>
						</tr>
						<tr>
							<th>
								政治面貌
							</th>
							<td>
								${jbxx.zzmmmc }
							</td>
							<th>
								联系方式
							</th>
							<td>
								${jbxx.sjhm }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>注册信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>学年</th>
							<td>${tyzcxx.xn }</td>
							<th>注册状态</th>
							<td>${tyzcxx.zcztmc }</td>
						</tr>
						<tr>
							<th>注册时间</th>
							<td colspan="3">
								${tyzcxx.zcsj}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		
			<div>
				<table class="formlist">
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

