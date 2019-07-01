<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
	</head>
	<body>
		<html:form action="/rwgl_rwtwgl" method="post">
			<div style='height:485px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								学号
							</th>
							<td width="34%">
								${rs.xh }
							</td>
							<th width="16%">
								姓名
							</th>
							<td width="34%" >
								${rs.xm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								性别
							</th>
							<td width="34%">
								${rs.xb }
							</td>
							<th width="16%">
								年级
							</th>
							<td width="34%">
								${rs.nj }
							</td>
						</tr>
						<tr>
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%">
								${rs.xymc }
							</td>
							<th width="16%">
								专业
							</th>
							<td width="34%">
								${rs.zymc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								班级
							</th>
							<td width="34%">
								${rs.bjmc }
							</td>
							<th width="16%">
								政治面貌
							</th>
							<td width="34%">
								${rs.zzmmmc }
							</td>
						</tr>
						<tr>
							<th width="16%">
								民族
							</th>
							<td width="34%">
								${rs.mzmc }
							</td>
							<th width="16%">
								出生日期
							</th>
							<td width="34%">
								${rs.csrq }
							</td>
						</tr>
						<tr>
							<th width="16%">
								身份证号
							</th>
							<td width="34%">
								${rs.sfzh }
							</td>
							<th width="16%">
								手机号码
							</th>
							<td width="34%">
								${rs.sjhm }
							</td>
						</tr>
						<tr>
							<th width="16%">
								家庭地址
							</th>
							<td width="34%">
								${rs.jtdz }
							</td>
							<th width="16%">
								家庭电话
							</th>
							<td width="34%">
								${rs.jtdh }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>入伍信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red"></font>入伍时间
							</th>
							<td width="34%">
								${rs.rwsj }
							</td>
							<th width="16%" >
								<font color="red"></font>入伍地
							</th>
							<td width="34%" style="word-break:break-all;width:99%">
								<!-- 温州大学 -->
								<logic:equal name="xxdm" value="10351">	
									${rs.rwdwdmc }
								</logic:equal>
								<logic:notEqual name="xxdm" value="10351">	
									${rs.rwd }
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th width="16%">
								入伍方式
							</th>
							<td width="34%" colspan="3">
								<font id="rwfs">${rs.rwfsmc}</font>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>退伍信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								入伍证号
							</th>
							<td width="34%">
								${rs.rwzh }
							</td>
							<th width="16%">
								退伍时间
							</th>
							<td width="34%">
								${rs.twsj }
							</td>
						</tr>
						<tr>
							<th width="16%">
								原专业
							</th>
							<td width="34%">
								${rs.yzy }
							</td>
							<th width="16%">
								原班级
							</th>
							<td width="34%">
								${rs.ybj }
							</td>
						</tr>
						<tr>
							<th width="16%">
								户籍关系
							</th>
							<td width="34%">
								${rs.hjgx }
							</td>
							<th width="16%">
								户口所在地
							</th>
							<td width="34%">
								${rs.hkszd }
							</td>
						</tr>
						<tr>
							<th width="16%">
								备注
							</th>
							<td width="84%" colspan="3" style="word-break:break-all;width:99%">
								${rs.bz }
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

