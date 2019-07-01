<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="js/xszz/whtl/ybbx/ybbxDetail.js"></script>
	</head>
	<body onload="">
	
		<html:form action="/gyglnew_jcrcgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>

			--%><div style='width:100%;height:450px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								学号
							</th>
							<td width="34%" colspan="3">
								${rs.xh }
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%" colspan="3">
								${rs.xm }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								年级
							</th>
							<td width="34%" colspan="3">
								${rs.nj }
							</td>
							<th width="16%">
								学院
							</th>
							<td width="34%" colspan="3">
								${rs.xy }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								专业
							</th>
							<td width="34%" colspan="3">
								${rs.zy }
							</td>
							<th width="16%">
								班级
							</th>
							<td width="34%" colspan="3">
								${rs.bj }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="8">
								<span>岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
						<th width="16%">
								学年
							</th>
							<td width="34%" colspan="3">
								${rs.xn}
							</td>
							<th width="16%">
								岗位名称
							</th>
							<td width="34%" colspan="3">
								${rs.gwmc }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								用人部门
							</th>
							<td width="34%" colspan="3">
								${rs.yrdw }
							</td>
							<th width="16%">
								岗位性质
							</th>
							<td width="34%" colspan="3">
								${rs.gwxz }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="8">
								<span>酬金明细</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<logic:empty name="cjrs">
						<tr>
							<td colspan="8" style="color: red; text-align: center;">
								<span>还未发放酬金</span>
							</td>
						</tr>
						</logic:empty>
						<logic:notEmpty name="cjrs">
						<logic:iterate id="s" name="cjrs" offset="0">
						
						<tr style="height:22px">
							<th width="16%">
								发放年月
							</th>
							<td width="34%" colspan="3">
								${s[0]}
							</td>
							<th width="16%">
								已发放金额
							</th>
							<td width="34%" colspan="3">
								${s[2]}
							</td>
							
						</tr>
						<tr style="height:22px">
							<th width="16%">
								工时
							</th>
							<td width="34%" colspan="3">
								${s[3]}
							</td>
							<th width="16%">
								酬金标准(元/小时)
							</th>
							<td width="34%" colspan="3">
								${cjbz}
							</td>
						</tr>
					</tbody>
						</logic:iterate>
						</logic:notEmpty>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">	
									<button type="button" onclick="Close();return false;">
										关 闭
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

