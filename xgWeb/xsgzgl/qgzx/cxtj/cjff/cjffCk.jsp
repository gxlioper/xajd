<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%><div style="height:450px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>学生岗位信息</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_gwxx">
						<tr>
							<th width="16%">
								学年
							</th>
							<td width="34%">
								${rs.xn}
							</td>
							<th width="16%">
								用人部门
							</th>
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th>
								岗位名称
							</th>
							<td>
								${rs.gwmc}
							</td>
							<th>
								岗位性质
							</th>
							<td>
								${rs.gwxzmc}
							</td>
						</tr>
						<tr>
							<th>
								学号
							</th>
							<td>
								${rs.xh}
							</td>
							<th>
								姓名
							</th>
							<td>
								${rs.xm}
							</td>
						</tr>
						<tr>
							<th>
								年级
							</th>
							<td>
								${rs.nj}
							</td>
							<th>
								学院
							</th>
							<td>
								${rs.xymc}
							</td>
						</tr>
						<tr>
							<th>
								专业
							</th>
							<td>
								${rs.zymc}
							</td>
							<th>
								班级
							</th>
							<td>
								${rs.bjmc}
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<td colspan="4"><span>酬金信息</span></td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								月份
							</th>
							<td width="34%">
								${rs.ffny}
							</td>
							<th width="16%">
								总工时
							</th>
							<td width="34%">
								${rs.gs }
							</td>
						</tr>
						<tr>
							<th>
								金额
							</th>
							<td colspan="3">
								${rs.je }
							</td>
						</tr>
						<tr style="height:66px">
							<th>
								备注
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.bz}
							</td>
						</tr>
					</tbody>
					</table>
				</div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
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

	</body>
</html>

