<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript">
			function save(){
				var zd1 = jQuery("input:radio[name=zd1]:checked").val();
				if( zd1 == ""){
					return showAlert("����ѡ��ʵϰ״̬��");
				}
				var api = frameElement.api,W = api.opener;
				W.saveSxzt(zd1);
				closeDialog();
				
			}
		</script>
	</head>
	<html:form action="xsxx_xsxxgl">
	<body>
		<table class="formlist">
			<thead>
				<tr>
					<th colspan="2">
						<span>ʵϰ״̬ѡ��</span>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr height='30'>
					<th>
						ʵϰ״̬
					</th>
					<td>
					       <html:radio property="zd1" value="��">��</html:radio>
					       <html:radio property="zd1" value="��">��</html:radio>
					</td>
				</tr>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="2">
						<div class="btn">
							<button type="button" onclick="save();return false;">
								ȷ��
							</button>
							&nbsp;&nbsp;
							<button type="button"
								onclick="closeDialog();return false;">
								�ر�
							</button>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</body>
	</html:form>
</html>
