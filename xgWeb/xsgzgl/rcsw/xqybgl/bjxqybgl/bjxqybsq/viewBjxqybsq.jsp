<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
		
				jQuery("#shlccx").load(
						"comm_spl.do?method=lccx&sqid=${model.sqid}&tt="
								+ new Date().getTime());
			});
		</script>
	</head>
	<body>

		<html:form action="/rcsw_xqybgl_bjxqybgl_bjxqybsqgl" method="post"
			styleId="bjxqybsqForm">
			<html:hidden property="sqid" styleId="sqid" />

			<div style="height:460px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�༶�±���Ϣ</span>
							</th>
						</tr>
					</thead>
					
					<tbody>
					     <tr>
							<th align="right" width="20%">
									ѧ��
							</th>
							<td align="left" width="30%" >
								${infoList.xn}
							</td>
							<th align="right" width="20%">
									ѧ��
							</th>							
							<td align="left" width="30%">
								${infoList.xqmc}
							</td>
					    </tr>
					    
					     <tr>
					    	<th align="right" width="20%">
									��д��
							</th>
							<td align="left" width="30%">
								${infoList.lrrxm}						
							</td>
							<th align="right" width="20%">
									�·�
							</th>
							<td >									
								${infoList.yf}				    	
							</td>
					    </tr>
					     <tr>
							<th align="right" width="20%">
									ѧԺ
							</th>
							<td width="30%" >
								${infoList.xymc}	
							</td>
							
							<th align="right" width="20%">
									�༶
							</th>
							<td width="80%" colspan="3">
								${infoList.bjmc}							
							</td>
					    </tr>
					    <tr>
							<th align="right" width="20%">
								���¹�����չ���
							</th>
							<td colspan="3">
								${infoList.bygzkzqk}
								
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								ѧ����ע�ȵ� 
							</th>
							<td colspan="3">
								${infoList.xsgzrd}
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								ѧ��˼�붯̬ 
							</th>
							<td colspan="3">
								${infoList.xssxdt}
							</td>
			      		</tr>
			      		<tr>
							<th align="right" width="20%">
								ѧ�����󼰹������� 
							</th>
							<td colspan="3">
								${infoList.xstsjgzjy}
							</td>
			      		</tr>
					   
					</tbody>
				</table>
				<logic:notEqual value="�������" name="shztmc">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td colspan="4" id="shlccx">

								</td>
							</tr>

						</tbody>

					</table>
				</logic:notEqual>

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
		</html:form>
	</body>
</html>