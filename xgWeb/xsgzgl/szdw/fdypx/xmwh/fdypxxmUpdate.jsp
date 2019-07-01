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
				jQuery("#but_save").click(function(){save("fdypxxmUpdate")});
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
								<span>�޸ĸ���Ա��ѵ��Ŀ</span>
								<html:hidden property="xmdm" styleId="xmdm" name="model" />
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							
							<th width="16%">
								<font color="red">*</font>��Ŀ����
							</th>
							<td width="34%">
								<html:text property="xmmc" styleId="xmmc" name="model" maxlength="20"></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>��ѵ�ص�
							</th>
							<td width="34%" >
								<html:text property="pxdd"  styleId="pxdd"  name="model" maxlength="20"></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>���뿪��
							</th>
							<td width="34%">
								<logic:equal value="1" property="sqkg" name="model">
									<input type="radio" name="sqkg" value="1" checked="checked">��</input>
									<input type="radio" name="sqkg" value="0">��</input>
								</logic:equal>
								<logic:equal value="0" property="sqkg" name="model">
									<input type="radio" name="sqkg" value="1">��</input>
									<input type="radio" name="sqkg" value="0" checked="checked">��</input>
								</logic:equal>
								<logic:empty property="sqkg" name="model">
									<input type="radio" name="sqkg" value="1">��</input>
									<input type="radio" name="sqkg" value="0">��</input>
								</logic:empty>
							</td>
							<th width="16%">
								����ʱ��
							</th>
							<td width="34%" >
							<html:text property="sqkssj" styleId="sqkssj"  name="model" style="width: 80px;"  onclick="return showCalendar('sqkssj','yyyy-MM-dd');" readonly="true" ></html:text>��
							<html:text property="sqjssj" styleId="sqjssj"  name="model" style="width: 80px;"  onclick="return showCalendar('sqjssj','yyyy-MM-dd');" readonly="true" ></html:text>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>��ѵʱ��
							</th>
							<td width="34%">
							<html:text property="pxsj"  styleId="pxsj"  name="model" style="width: 80px;" onclick="return showCalendar('pxsj','yyyy-MM-dd');" readonly="true"></html:text>
							</td>
							<th width="16%">
								<font color="red">*</font>��ѵѧʱ
							</th>
							<td width="34%">
								<html:text property="pxxs"  styleId="pxxs"  name="model" style="width: 80px;" ></html:text>
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
								<html:textarea property="pxjj" styleId="pxjj"   name="model" style="width: 500px;height: 100px;"></html:textarea>
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

