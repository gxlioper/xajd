<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript">
			function saveForm(){	  		
				var length=jQuery("[name=ry]:checked").length;
	
				if(length < 1){
					showAlert("��ѡ���ʦ���ͣ�");
					return false;
				}
				
				showDialog("��ѡ����Ľ�ʦ����", 800, 500, "szdw_szbb.do?method=jsbb&quantity="+length);
	
				iFClose();	
							
			}
		</script>
	</head>
	<body>
			<div style=''>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>����ʦ����</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="35%">
								<font color="red">*</font>����ʦ����
							</th>
							<td width="60%">
								<input type="radio" value="bzr" name="ry" />������
								<input type="radio" value="fdy" name="ry" />����Ա
							</td>
						</tr>
					</tbody>
				<tfoot>
						<tr>
							<td align="center" colspan="2">							
			        			<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveForm();return false;" id="buttonSave">
										ȷ��
									</button>
									<button type="button" onclick="iFClose();">
										ȡ��
									</button>	
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
	</body>
</html>

