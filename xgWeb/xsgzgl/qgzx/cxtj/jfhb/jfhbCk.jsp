<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<style>.datelist td {word-break:break-all}</style>
	</head>
	<body>
			<%--<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			--%><div style="width:100%;height:450px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>勤工经费查看</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								年度
							</th>
							<td width="34%">
								<html:hidden name="rs" property="nd" styleId="nd"/>
								${rs.nd }
							</td>
							<th width="16%">
								部门
							</th>
							<td width="34%">
								<html:hidden name="rs" property="bm" styleId="bm"/>
								<bean:write name="rs" property="bmmc"/>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4"><span>划拨经费项</span></th>
						</tr>
					</thead>
				</table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width="5%">
								行号
							</td>
							<td width="15%">
								划拨时间
							</td>
							<td width="15%">
								划拨金额
							</td>
							<td width="65%">
								备注
							</td>
						</tr>
					</thead>
					<tbody id="tbody_jfxx">
						${rs.jfhbList}
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

