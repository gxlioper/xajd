<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<%@ include file="/syscommon/autocomplete.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/khgl/khxmgl/js/khxmgl.js"></script>	
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
		
		</script>
	</head>
	<body>
		<html:form action="/khglKhxmgl" method="post"
			styleId="KhxmglForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table align="center" class="formlist">
						<thead>
						<tr>
							<th colspan="4">
								<span>������Ŀ��Ϣ</span>
							</th>
						</tr>
						</thead>
					<tbody>
						<tr>
							<th>
								<span class="red">*</span>��Ŀ����
							</th>
							<td >
								<html:text  property="xmmc" styleId="xmmc" maxlength="50"  styleClass="text_nor"></html:text>
							</td>
							<th>
								<span class="red">*</span>���˶���
							</th>
							<td>
								<html:select  property="khdxid" styleId="khdxid">
								<html:options collection="khdxList" labelProperty="khdxmc" property="khdxid"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<span class="red">*</span>���ֿ�ʼʱ��
							</th>
							<td>
								<html:text property="kssj"
									onclick="return showCalendar('kssj','yyyy-MM-dd HH:mm',true,'jssj');" styleId="kssj" ></html:text>
							</td>
							<th>
								<span class="red">*</span>���ֽ���ʱ��
							</th>
							<td>
								<html:text property="jssj"
									onclick="return showCalendar('jssj','yyyy-MM-dd HH:mm',false,'kssj');" styleId="jssj" ></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��Ŀ����</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property='xmms' style="width:98%" styleId="xmms" rows='4' onblur="checkLen(this,500);"/>
							</td>
						</tr>
					</tbody>
					</table>
			      </div>
			      <table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" type="button"
										onclick="saveForm('save');return false;">
										�� ��
									</button>
									<button type="button" type="button"
										onclick="iFClose();return false;">
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

