<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/jfgl/js/jfhbzjdx.js"></script>
		<style>.datelist td {word-break:break-all}</style>
	</head>
	<body>
		<html:form action="/qgzx_jfgl" method="post">
		<div style="tab;width:100%;height:500px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ڹ����Ѳ鿴</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								���
							</th>
							<td width="34%">
								<html:hidden name="rs" property="nd" styleId="nd"/>
								${rs.nd }
							</td>
							<th width="16%">
								���˵�λ
							</th>
							<td width="34%">
								<html:hidden name="rs" property="bm" styleId="bm"/>
								<bean:write name="rs" property="bmmc"/>
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4"><span>����������</span></th>
						</tr>
					</thead>
				</table>
				<table width="100%" border="0" class="datelist" style="margin:2px auto;">
					<thead>
						<tr>
							<td width="5%">
								�к�
							</td>
							<td width="15%">
								����ʱ��
							</td>
							<td width="15%">
								�������<Ԫ>
							</td>
							<td width="65%">
								��ע
							</td>
						</tr>
					</thead>
					<tbody id="tbody_jfxx">
						${rs.rsList}
					</tbody>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������ϸ����λ��<Ԫ></span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="24%"  style="text-align:center;">1�·���</th><td align="center">${ffmx.yiyue}</td>
							<th width="24%"  style="text-align:center;">2�·���</th><td align="center">${ffmx.eryue}</td>
						</tr>
						<tr>
							<th width="24%"  style="text-align:center;">3�·���</th><td align="center">${ffmx.sanyue}</td>
							<th width="24%"  style="text-align:center;">4�·���</th><td align="center">${ffmx.siyue}</td>
						</tr>
						<tr>
							<th width="24%"  style="text-align:center;">5�·���</th><td align="center">${ffmx.wuyue}</td>
							<th width="24%"  style="text-align:center;">6�·���</th><td align="center">${ffmx.liuyue}</td>
						</tr>
						<tr>
							<th width="24%"  style="text-align:center;">7�·���</th><td align="center">${ffmx.qiyue}</td>
							<th width="24%"  style="text-align:center;">8�·���</th><td align="center">${ffmx.bayue}</td>
						</tr>
						<tr>
							<th width="24%"  style="text-align:center;">9�·���</th><td align="center">${ffmx.jiuyue}</td>
							<th width="24%"  style="text-align:center;">10�·���</th><td align="center">${ffmx.shiyue}</td>
						</tr>
						<tr>
							<th width="24%"  style="text-align:center;">11�·���</th><td align="center">${ffmx.shiyiyue}</td>
							<th width="24%"  style="text-align:center;">12�·���</th><td align="center">${ffmx.shieryue}</td>
						</tr>
						<tr>
							<th width="24%"  style="text-align:center;">�����ܽ��</th><td colspan="3" align="center">${ffmx.hbzje}</td>
						</tr>
						<tr>
							<th width="24%"  style="text-align:center;">�ѷ����ܽ��</th><td colspan="3" align="center">${ffmx.ffzje}</td>
						</tr>
						<tr>
							<th width="24%"  style="text-align:center;">ʣ����</th><td colspan="3" align="center">${ffmx.syje}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div style="height:1px"></div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" onclick="Close();return false;">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>