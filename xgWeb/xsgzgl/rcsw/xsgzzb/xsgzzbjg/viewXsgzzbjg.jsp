<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
				<link rel="stylesheet" href="xsgzgl/rcsw/xsgzzb/css/xsgzzb.css" type="text/css" />
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			});
	   </script>
	</head>
	<body>
		<html:form action="/rcsw_xsgzzb_xsgzzbjggl" method="post" styleId="xsgzzbjgForm">
		<html:hidden property="jgid" styleId="jgid" />	
			<div style='tab;width:100%;height:306px;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ܱ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${model.xn}
							</td>
							<th width="18%">ѧ��</th>
							<td width="32%">
								${model.xqmc}
							</td>
					    </tr>
					    <tr>
							<th><bean:message key="lable.xy" /></th>
							<td>
								${model.bmdmmc}
							</td>
							<th>��д��</th>
							<td>
								${model.lrrxm}
							</td>
					    </tr>
							<th>�ܴ�</th>
							<td>
								${model.zcmc}
							</td>
							<th>�ܴ���ֹ����</th>
							<td>
								${model.zcksjsrq}
							</td>
					    </tr>
					    <tr>
							<th>
								һ����Ҫ����
							</th>
							<td colspan="3">
							    ${model.yzzygz}
							</td>
			      		</tr>
					    <tr>
							<th>
								������Ҫ����
							</th>
							<td colspan="3">
							    ${model.xzzygz}
							</td>
			      		</tr>
			      		<tr>
							<th>
								��������
							</th>
							<td colspan="3">
							    ${model.qtgz}
							</td>
			      		</tr>
					    <tr>
							<th>
								��ѧ��������<br />����ͽ���
							</th>
							<td colspan="3">
							    ${model.yj}
							</td>
			      		</tr>
					</tbody>
					<%--�Ĵ���Ϣְҵ����ѧԺ���Ի�--%>
				<logic:equal name="xxdm" value="13815">
					<logic:notEmpty name="yscfjlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����鿴</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr id="tablehead">
							<td colspan="1" class="center">���</th>
							<td colspan="1" class="center">�ϴ��ļ����</th>
							<td colspan="4" class="center">����</th>
						</tr>
						<logic:iterate id="yscfj" name="yscfjlist">
							<tr>
								<td colspan="1" class="center">
									${yscfj.rownum}
								</td>
								<td colspan="1" class="center">
									${yscfj.wjlxmc}
								</td>
								<td colspan="4" class="center">
									<a href="rcsw_xsgzzb_xsgzzbsqgl.do?method=downloadFile&id=${yscfj.id}" >����</a>
									${yscfj.fjmc}
								</td>
							</tr>
						</logic:iterate>
					</tbody>
					</logic:notEmpty>
				</logic:equal>
				</table>				
			</div>
			<table width="100%" border="0" class="formlist">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="btn">
								<button type="button" type="button" onclick="iFClose();">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
		</table>
		</html:form>
	</body>
</html>

