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
				alert("请填写好所有信息！");
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
								<span>地点维护</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button onclick="dd_bc()" id="zj_but">
										保存
									</button>
									<button onclick="Close();return false;" >
										关闭
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								代码编号
							</th>
							<td>
								<html:text property="dd_dm" styleId="dd_dm" readonly="true" />
							</td>
						</tr>
						<tr>
							<th>
								咨询地点
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
							alert("操作成功!");
							window.dialogArguments.document.getElementById("search_go").click();
							Close();
						</script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
	</html>