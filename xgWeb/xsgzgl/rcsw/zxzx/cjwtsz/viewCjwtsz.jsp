<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script	type="text/javascript">
			jQuery(function(){
			});
		</script>
	</head>
	<body>
		<html:form action="/rcsw_zxzx_cjwtszgl" method="post" styleId="cjwtszForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;height: 265px;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѯ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="13%">��ѯ���</th>
							<td width="">
								${rs.bkmc }
							</td>
					    </tr>
					    <logic:notEqual value="cjwt" name="tab">
						<tr>
							<th width="">�Ƿ��ö�</th>
							<td width="">
								${rs.sfzdmc }
							</td>
					    </tr>
					    </logic:notEqual>
						<tr>
							<th width="">��ѯ����</th>
							<td width="">
								${rs.zxzt }
							</td>
					    </tr>
						<tr>
							<th width="">��ѯ����
							</th>
							<td colspan="3">
								${rs.zxnr }
							</td>
					    </tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>�ظ���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="">�ظ�����
							</th>
							<td colspan="3">
								${rs.hfnr }
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

