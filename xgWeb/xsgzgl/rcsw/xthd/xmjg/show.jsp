<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmjg/js/xmjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<html:form method="post"
		 styleId="form" action="/rcsw_txhd_xmjg">
		 <% String xxdm = (String) request.getAttribute("xxdm"); %>
		 <html:hidden property="guid"/>
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>

		<div style='tab;width:100%;height:395px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/xthd/comm/viewStudent.jsp"%>
				<thead>
						<tr>
							<th colspan="4">
								<span>���Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							ѧ��
						</th>
						<td align="left">
							${data.xn}
						</td>
						<th align="right">
							ѧ��
						</th>
						<td align="left">
							${data.xqmc}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							�����
						</th>
						<td >
							${data.xmmc}
						</td>
						
						<th align="right" width="10%">
							�ʱ��
						</th>
						<td align="left">
							${data.hdkssj}&nbsp;��&nbsp;${data.hdjssj }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							����
						</th>
						<td align="left">
							${data.lbmc }
						</td>
						<th align="right">
							��ص�
						</th>
						<td align="left">
							${data.hddd}
						</td>
					</tr>
					<tr>
					   <th align="right">
							����������
						</th>
						<td width="34%">
							${data.fzrxm}
						</td>
						<th align="right" width="10%">
						          ��ϵ�绰
						</th>
						<td align="left">
							${data.lxdh}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
						�а쵥λ
						</th>
						<td align="left" colspan="3" >
							${data.cbdw}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">�����</th>
						<td align="left" colspan="3" >
							${data.hdzt}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�Ŀ�ļ�����
						</th>
						<td colspan="3">
							${data.hdmdyy}
						</td>
					</tr>
					<tr>
					<th align="right" width="10%">
							�����
						</th>
						<td colspan="3">
							${data.hdfa}
						</td>
					</tr>
					<% if("13023".equals(xxdm)){ %>
					<tr>
						<th width="16%">
							����ѧ��
						</th>
						<td width="34%" >
							${data.syxf}
						</td>
						<th width="16%">
						</th>
						<td width="34%" >
						</td>
					</tr>
					<% } %>
					<tr>
						<th align="right">
							�˵��
						</th>
						<td align="left" colspan="3">
							${data.hdsm }
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							��������
						</th>
						<td colspan="3">
							${data.sqly}
						</td>
					</tr>
				</tbody>
				<logic:equal value="12309" name="xxdm">
				<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr>
				<th align="right" width="10%">��������</th>
						<td align="left" colspan="3" >
							${data.sqwz}
						</td>
				</tr>
					<tr>
					   <th align="right">
							ʹ��ʱ��
						</th>
						<td width="34%">
							${data.wzsysj}
						</td>
						<th align="right" width="10%">
						          �黹ʱ��
						</th>
						<td align="left">
							${data.wzghsj}
						</td>
					</tr>
				</tbody>
				<thead>
						<tr>
							<th colspan="4">
								<span>��������</span>
							</th>
						</tr>
				</thead>
				<tbody>
				<tr>
				<th align="right" width="10%">������ʽ</th>
						<td align="left">
							${data.xcfs}
						</td>
				<th align="right" width="10%">���������ҵص㣩</th>
						<td align="left" colspan="3" >
							${data.xgdd}
						</td>
				</tr>
					<tr>
					<th align="right" width="10%">��ֹʱ��</th>
					<td>
					   ${data.xckssj}&nbsp;��&nbsp;${data.xcjssj }
					</td>
					<th align="right" width="10%">���������������</th>
					<td>
					  ${data.hbsl }
					</td>
					</tr>
					<tr>
					<th align="right" width="10%">��������</th>
					<td colspan="3">
					   ${data.xcnr }
					</td>
					
					</tr>
					
				</tbody>
				</logic:equal>
				</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
