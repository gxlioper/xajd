<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" href="xsgzgl/dtjs/dtxxglnew/color/dtxxglnew.css" type="text/css" media="all" />
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/xsgzgl/dtjs/dxbmgl/dxpxjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
			jQuery(function() {
				autoChange();
			});
			function tzXzpx(){
				var xh = jQuery('#xh').val();
				if(xh==undefined||xh==''){
					showAlert("��ѡ��ѧ��!");
				}else{
					showDialog('��ѡ����ѵ��Ϣ',800,500,'dtjs_dxbmgl_dxpxxzCx.do?xh='+xh);
				}
				return false;
			}
		</script>
	</head>
	<body>
		<html:form method="post" styleId="form" action="dxbmgl_dxpxjg.do">
		 <!-- ��ʾ��Ϣ end-->
			<div class="prompt" id="div_help">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p></p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
			</div>
		 <!-- ��ʾ��Ϣ end-->			
			<div style="tab;width:100%;height:380px;overflow-x:hidden;overflow-y:auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsxx/comm/selectStudent/selectStudentAll.jsp"%>
				</table>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��ѵ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">��ѵ�ڴ�</th>
							<td width="34%"><html:text property="pxqc" styleId="pxqc" readonly="true" value="${dxpxjgForm.pxqc }"/>
								<button class="btn_01" type="button" onclick="tzXzpx();">ѡ��</button>
							</td>
							<th width="16%">��ѵʱ��</th>
							<td id="pxsj" width="34%"><input type="hidden" id="pxid" name="pxid" value="${dxpxjgForm.pxid}"/>
							${dxpxjgForm.pxsj }</td>
						</tr>
						<tr>
							<th>���ڳɼ�</th>
							<td><input type="hidden" id="kqcjbfb" value="${dxpxjgForm.kqcjbfb }"/>
								<html:text maxlength="3" property="kqcj" styleId="kqcj" onblur="onlyNumInput(this);jszf();"/>
							</td>
							<th>ʵ���ɼ�</th>
							<td><input type="hidden" id="sjcjbfb" value="${dxpxjgForm.sjcjbfb}"/>
								<html:text maxlength="3" property="sjcj" styleId="sjcj" onblur="onlyNumInput(this);jszf();"/>
							</td>
						</tr>
						<tr>
							<th>�ʼǳɼ�</th>
							<td><input type="hidden" id="bjcjbfb" value="${dxpxjgForm.bjcjbfb }"/>
								<html:text maxlength="3" property="bjcj" styleId="bjcj" onblur="onlyNumInput(this);jszf();"/>
							</td>
						</tr>
					<logic:equal value="xx" name="userStatus" scope="session">
						<tr>
							<th>���Գɼ�</th>
							<td><input type="hidden" id="kscjbfb" value="${dxpxjgForm.kscjbfb}"/>
								<html:text maxlength="3" property="kscj" styleId="kscj" onblur="onlyNumInput(this);jszf();"/>
							</td>
							<th>�����ɼ�</th>
							<td>
								<html:text property="zpcj" styleId="zpcj" onblur="onlyNumInput(this)"/>
							</td>
						</tr>
					</logic:equal>
					<logic:equal value="admin" name="userStatus" scope="session">
						<tr>
							<th>���Գɼ�</th>
							<td><input type="hidden" id="kscjbfb" value="${dxpxjgForm.kscjbfb}"/>
								<html:text maxlength="3" property="kscj" styleId="kscj" onblur="onlyNumInput(this);jszf();"/>
							</td>
							<th>�����ɼ�</th>
							<td>
								<html:text property="zpcj" styleId="zpcj" onblur="onlyNumInput(this)"/>
							</td>
						</tr>
					</logic:equal>
						<tr>
							<th>���۽��</th>
							<td>
								<html:select property="pjjg">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
									<html:option value="����">����</html:option>
									<html:option value="������">������</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div>
				<table width="100%" border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="save('xh');return false;" id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="iFClose();" id="buttonClose">
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
