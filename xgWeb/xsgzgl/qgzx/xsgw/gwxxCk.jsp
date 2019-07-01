<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src="js/check.js"></script>
	</head>
	<body>
	
		<html:form action="/qgzx_gwgl" method="post">
			<input type="hidden" id="isFdy" name="isFdy" value="${fdyQx }" />
			<input type="hidden" id="isBzr" name="isBzr" value="${bzrQx }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="doType" id="doType" value="${doType}" />
			<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }" />
			<input type="hidden" name="userType" id="userType" value="${userType }" />
			
			<%--<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title }</a>
				</p>
			</div>
			--%><div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��λ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="16%">
								ѧ��
							</th>
							<td width="34%">
								${rs.xn}
							</td>
							<th width="16%">
								���˲���
							</th>
							
							<td width="34%">
								${rs.yrdwmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%">
								${rs.gwmc}
							</td>
							<th width="16%">
								��λ����
							</th>
							
							<td width="34%">
								${rs.gwxzmc}
							</td>
						</tr>
						<tr>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${rs.xqrs}(��)
							</td>
							<th width="16%">
								��������
							</th>
							<td width="34%">
								${rs.knsrs}(��)
							</td>
						</tr>
						<logic:equal name="isshow" value="true" >
							<tr id="gwcjsxTr">
								<th width="16%">
									��λ�������
								</th>
								<td width="34%">
									<span id="gwcjsxh">${rs.gwcjsx}</span>
									<span>Ԫ/��  &nbsp;&nbsp;(�ø�λÿ��ÿ�³������)</span>
								</td>
								<th width="16%">
									��¼������
								</th>
								<td width="84%">
									${rs.zgrs}(��)
								</td>
							</tr>
						</logic:equal>
						<logic:notEqual name="isshow" value="true">
							<tr>
								<th width="16%">
									��¼������
								</th>
								<td width="84%" colspan="3">
									${rs.zgrs}(��)
								</td>
							</tr>
						</logic:notEqual>
						<tr style="height: 80px">
							<th align="right" >
								��λ����
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwms}
							</td>
						</tr>
						<tr style="height: 80px">
							<th align="right" >
								��λ��ԱҪ��
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.gwryyq}
							</td>
							
						</tr>
						<tr  style="height: 80px">
							<th align="right" >
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;width:100%">
								${rs.bz}
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
								<button type="button"  onclick="Close();return false;" id="buttonClose">
									�� ��
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

