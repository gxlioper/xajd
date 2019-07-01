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
			<input type="hidden" id="url" name="url"
				value="xljk_hzny.do?method=zxsglDetail" />
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ճ�����-��ٹ���-������� </a>
				</p>
			</div>

			<div style='width:98%;height:600px;overflow:hidden;overflow-x:hidden'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="8">
								<span>ѧ�������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%">
								ѧ��
							</th>
							<td width="34%" colspan="3">
								${rs.xh }
							</td>
							<th width="16%">
								����
							</th>
							<td width="34%" colspan="3">
								${rs.xm }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								<bean:message key="lable.xb" />
							</th>
							<td width="34%" colspan="3">
								${rs.xy }
							</td>
							<th width="16%">
								רҵ
							</th>
							<td width="34%" colspan="3">
								${rs.zy }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								�꼶
							</th>
							<td width="34%" colspan="3">
								${rs.nj }
							</td>
							<th width="16%">
								�༶
							</th>
							<td width="34%" colspan="3">
								${rs.bj }
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								�������
							</th>
							<td width="34%" colspan="3">
								${rs.qjlx }
							</td>
							<th width="16%">
								�������
							</th>
							<td width="34%" colspan="3">
								${rs.qjts }��
							</td>
						</tr>
						<tr style="height:22px">
							<th width="16%">
								��ٿ�ʼʱ��
							</th>
							<td width="34%" colspan="3">
								${rs.qjkssj }
							</td>
							<th width="16%">
								��ٽ���ʱ��
							</th>
							<td width="34%" colspan="3">
								${rs.qjjssj }
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="8">
								<span>���������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr style="height:22px">
							<th width="16%" colspan="1">
								������
							</th>
							<td width="34%" colspan="7">
								${rs.cxr }
							</td>
						</tr>
						<tr style="height:22px">
							<th align="right" colspan="1">
								����ʱ��
							</th>
							<td align="left" colspan="7">
								${rs.cxsj }
							</td>
						</tr>
						<tr style="height:90px">
							<th width="16%" colspan="1">
								����ԭ��
							</th>
							<td width="34%" colspan="7" >
								${rs.cxly }
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="8">
								<div class="btn">	
									<button type="button" onclick="Close();return false;">
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