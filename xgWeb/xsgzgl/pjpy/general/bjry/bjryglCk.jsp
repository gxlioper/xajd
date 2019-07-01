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
	
		<html:form action="/pjpy_bjrygl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>

			<div style='width:98%;height:400px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>�鿴�༶����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="15%">
								�༶����
							</th>
							
							<td width="35%" colspan="3" >
								${rs.bjmc }
							</td>
							<th width="15%">
								�����꼶
							</th>
							
							<td width="35%" colspan="3" id="ssnj">
								${rs.nj }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="15%">
								����Ժϵ
							</th>
							
							<td width="35%" colspan="3" id="ssyx">
								${rs.xymc }
							</td>
							<th width="15%">
								����רҵ
							</th>
							
							<td width="35%" colspan="3" id="sszy">
								${rs.zymc }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="20%">
								����ѧ��
							</th>
							
							<td width="40%" colspan="3" >
								${rs.xn }
							</td>
							
							<th width="20%">
								����ѧ��
							</th>
							
							<td width="40%" colspan="3" >
								${rs.xqmc }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="20%">
								�������
							</th>
							
							<td width="40%" colspan="3" >
								${rs.mc }
							</td>
							<th width="15%">
								���ʱ��
							</th>
							
							<td width="25%" colspan="3">
								${rs.hdsj }
							</td>
						</tr>
						<tr style="height:90px">
							<th align="right" >
								��ע
							</th>
							<td colspan="7" style="word-break:break-all;width:97%">
								${rs.bz }
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">
									<button type="button"  onclick="Close();return false;">
										�� ��
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

