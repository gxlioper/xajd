<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true" ></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/zdzm/js/print.js"></script>
		<script type="text/javascript" defer="defer">
			jQuery(function(){

				
				
			});
		</script>
	</head>
	<body>
		<button id="search_go" type="button" style="display:none" onclick="reloadWindow();"></button>
		<html:form action="/rcsw_zdzm_sqgl" method="post" styleId="rcswZdzmSqForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>�����ڶ�֤��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								<span class="red"></span>ѡ���¼��
							</th>
							<td colspan="3">
								<span class="red">${xzModel.ts}</span>��
								<input type="hidden" id="xhs" value="${xzModel.xhs}"/>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red"></span>�������
							</th>
							<td colspan="3">
								${xzModel.pyccmc}
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>��ѡ������
							</th>
							<td colspan="3">
								<logic:present name="zdzmlbList">
									<html:select property="type" styleId="type" >
										<html:options collection="zdzmlbList" property="dm" labelProperty="mc" />
									</html:select>								
								</logic:present>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button id="save_button" type="button"  onclick="print('rcsw_zdzm_jggl.do?method=doPrint')">
										����
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

