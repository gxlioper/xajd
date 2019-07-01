<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
	</head>
	<body>
		<html:form action="/rcswSbglJyjl" method="post" styleId="form">
			<html:hidden property="id" />
		
			<div style='overflow-x:hidden;overflow-y:auto;height:360px;margin-bottom: 0px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>教职工信息</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%">职工号</th>
							<td style="width:35%">
								${jbxx.zgh }
							</td>
							<th style="width:15%">姓名</th>
							<td style="width:35%">
								${jbxx.xm }
							</td>
						</tr>
						
						<tr>
							<th>性别</th>
							<td>
								${jbxx.xbmc }
							</td>
							<th>联系电话</th>
							<td>
								${jbxx.lxdh }
							</td>
						</tr>
						<tr>
							<th>部门</th>
							<td>
								${jbxx.bmmc }
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>借用设备信息
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>设备分类</th>
							<td>
								${jyjlForm.flmc }
							</td>
							<th>
								设备名称
							</th>
							<td>
								${jyjlForm.sbmc }
							</td>
						</tr>
						<tr>
							<th>借用时间</th>
							<td>
								${jyjlForm.jysj }
							</td>
							<th>
								借出经办人
							</th>
							<td>
								${jyjlForm.jbrxm }
							</td>
						</tr>
						<tr>
							<th>备注
							</th>
							<td colspan="3">
								${jyjlForm.bz }
							</td>
						</tr>
						<tr>
							<th>借用原因
							</th>
							<td colspan="3">
								${jyjlForm.jyyy }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>归还设备信息
								</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>归还时间</th>
							<td>
								${jyjlForm.ghsj }
							</td>
							<th>
								归还经办人
							</th>
							<td>
								${jyjlForm.ghjbrxm }
							</td>
						</tr>
						<tr>
							<th>归还备注
							</th>
							<td colspan="3">
								${jyjlForm.ghbz }
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

