<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdypx/js/fdypxxmwh.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//Ϊbuttonע���¼�
				jQuery("#but_save").click(function(){save("fdypxxmAdd")});
				//jQuery("#but_close").click();
				
			});
		</script>
	</head>
	<body style="width:100%">
		<html:form action="/rcsw_xsxxgl" method="post" styleId="demoForm">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���Ӹ���Ա��ѵ��Ŀ</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								<font color="red">*</font>��Ŀ����
							</th>
							<td width="34%">
								<input id="xmmc" name="xmmc" maxlength="20"/>
							</td>
							<th width="16%">
								<font color="red">*</font>��ѵ�ص�
							</th>
							<td width="34%" >
								<input id="pxdd" name="pxdd" maxlength="20"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>���뿪��
							</th>
							<td width="34%">
								<input type="radio" name="sqkg" value="1">��</input>
								<input type="radio" name="sqkg" value="0">��</input>
							</td>
							<th width="16%">
								����ʱ��
							</th>
							<td width="34%" >
								<input type="text" id="sqkssj" name="sqkssj" style="width: 80px;" onclick="return showCalendar('sqkssj','yyyy-MM-dd');" readonly="true"/>
								<input type="text" id="sqjssj" name="sqjssj" style="width: 80px;" onclick="return showCalendar('sqjssj','yyyy-MM-dd');" readonly="true"/>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��ѵʱ��
							</th>
							<td width="34%">
								<input type="text" id="pxsj" name="pxsj" style="width: 80px;" onclick="return showCalendar('pxsj','yyyy-MM-dd');" readonly="true"/>
							</td>
							<th width="16%">
								<font color="red">*</font>��ѵѧʱ
							</th>
							<td width="34%">
								<input id="pxxs" name="pxxs" style="width: 80px;"/>
							</td>
							
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��֯����
							</th>
							<td width="34%" colspan="3">
								<html:select property="bmdm" styleId="bmdm" name="model">
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc"></html:options>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								��ѵ���
							</th>
							<td width="34%" colspan="3">
								<textarea name="pxjj" id="pxjj" style="width: 500px;height: 100px;"></textarea>
							</td>
						</tr>
						
						
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" id = "but_save" >
										�� ��
									</button>
									<button type="button" type="button" id= "but_close" onclick="iFClose();">
										�ر�
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

