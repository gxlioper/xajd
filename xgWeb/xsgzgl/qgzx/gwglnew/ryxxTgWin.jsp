<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type='text/javascript' src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/gwglnew/js/gwgl.js"></script>
		<script type='text/javascript'>
			jQuery(document).ready(function(){
				jQuery('#tgry').html("��ǰ��ѡ<font class='red'>${len }</font>���ڸ�ѧ��");
			});
		</script>
	</head>
	<body>
		<html:form action="/qgzx_gwglnew" method="post">
			<div style="height:190px;overflow-x:hidden;overflow-y:auto;">
				<table align="center" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>��λ�˸�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								�˸���Ա
							</th>
							<td>
								<font id="tgry"></font>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>�˸�ԭ��<br/><font color="red">(��1000��)</font>
							</th>
							<td>
								<textarea name='tgyy' id="tgyy" style="word-break:break-all;width:97%" onblur="chLeng(this,1000);"
									rows='5'></textarea>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" name="����" onclick="bcTgryxx();">
										����
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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

