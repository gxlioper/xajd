<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript'
			src='/xgxt/dwr/interface/xljk_zxzx_dwr.js'></script>
		<script type="text/javascript" src="js/lrh_new_js.js"></script>
		<script type='text/javascript'>
		function dd_bc()
		{
			var dd2=document.all['dd2'].value;
			if(""==dd2)
			{
				alert("����д��������Ϣ��");
				return false;
			}
			else
			{
//			alert(document.all['ZY_XN_ID'].value);
				document.all["modi_flag"].value="yes";
				refreshForm("/xgxt/xljk_zxzx_dmwh.do?act=xljk_zxzx_dmwh&doType=dd_modi");
			}
		}
	</script>
	</head>

	<body>

		<html:form action="/xljk_zxzx_dmwh">
			<input type="hidden" id="modi_flag" name="modi_flag" value="no" />
			<html:text property="ZY_XN_ID" styleId="ZY_XN_ID"
				style="display:none" />
			<html:text property="ZY_XN_ID2" styleId="ZY_XN_ID2"
				style="display:none" />

			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�ص�ά��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="dd_bc()" id="zj_but">
										����
									</button>
									<button onclick="Close();return false;" >
										�ر�
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								������
							</th>
							<td>
								<html:text property="dd_dm" styleId="dd_dm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								��ѯ�ص�
							</th>
							<td>
								<html:text property="dd2" styleId="dd2" />
							</td>
						</tr>
					</tbody>

				</table>
			</div>
			<logic:notEmpty name="message">
				<logic:equal name="message" value="update_success">
					<script>
							alert("�����ɹ�!");
							window.dialogArguments.document.getElementById("search_go").click();
							Close();
						</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	</html>