<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer">
			function saveSpl(){
				var url="dtjs_dxbmgl_dxbmshlcszBc.do";
				var shl = jQuery('#shl').val();
				if (shl ==null || shl =="") {
					alertError("��*������Ϊ���");
					return false;
				}
				refreshForm(url);
			}
		</script>
		
	</head>
	<body >
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<!-- ���� end-->
		<logic:empty name="shlcList">
			<div class="prompt" id="pts">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��δ������������뵽ϵͳ���� - ������̹��������ñ�ģ���������̣�
			</p>
		</div>
		</logic:empty>
		<html:form action="/dxbmgl_dxbmshlcsz" method="post" styleId="shlForm">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ end-->
			
			<!-- ά����Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="2">
							<span>���������</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="30%">
							<font color="red">*</font>�������
						</th>
						<td width="">
							<html:select property="shl" styleId="shl" style="width:240px">
								<html:options collection="shlcList" labelProperty="lcxx" property="splc"/>
							</html:select>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='2'>
							<div class="btn">
								<button type="button"  onclick="saveSpl();return false;" id="btn_bc"  <logic:empty name="shlcList">disabled='disabled'</logic:empty>>
									����
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ά����Ϣ end-->
		</html:form>
		<logic:notEmpty name="result">
			<logic:equal value="false" name="result">
				<script language="javascript">
				alertInfo("����ʧ�ܣ�");
				</script>
			</logic:equal>
			<logic:equal value="true" name="result">
				<script language="javascript">
				alertInfo("�����ɹ���");
				</script>
			</logic:equal>
		</logic:notEmpty>
	</body>
</html>