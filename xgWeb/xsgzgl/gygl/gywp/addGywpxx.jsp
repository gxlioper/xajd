<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/gywp/js/gywp.js"></script>
	</head>
	<body>
		<html:form action="gygl_qywpxx.do?method=addGywpxx" styleId="gywpxxForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="ids" id="ids" value="${ids }"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ԣ��Ʒ��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td colspan="4" align="left">
								<font color="red">��ѡ�С�${num }����¥�������Ҽ�¼</font>
							</td>
						</tr>
						<tr>
							<th style="width: 20%"><font color="red">*</font>��Ʒ����</th>
							<td><html:text maxlength="25" property="wpmc" styleId="wpmc" style="width:90%"></html:text></td>
							<th style="width: 20%"><font color="red">*</font>��������</th>
							<td>
								<html:select property="wpdldm" styleId="wpdldm" style="width:90%">
									<html:options collection="wpdlList" property="wpdldm" labelProperty="wpdlmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th style="width: 20%"><font color="red">*</font>��Ʒ���</th>
							<td>
								<html:select property="wplbdm" styleId="wplbdm" style="width:90%">
									<html:options collection="wplbList" property="wplbdm" labelProperty="wplbmc"/>
								</html:select>
							</td>
							<th style="width: 20%"><font color="red">*</font>����</th>
							<td>
								<html:text onkeyup="checkInputData(this)" maxlength="5" property="sl" styleId="sl" style="width:90%"></html:text>
							</td>
						</tr>
						<tr>
							<th style="width: 20%">�Ƿ��ںϸ���</th>
							<td>
								<html:select property="sfzhgq" styleId="sfzhgq" style="width:90%">
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							<th style="width: 20%">�Ƿ����</th>
							<td>
								<html:select property="sfwh" styleId="sfwh" style="width:90%" onchange="changeSfwh()">
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						
						<tr>
							<th style="width: 20%">
								�ٻ�ԭ��
								<br/>
								<font color="red">��������200��</font>
							</th>
							<td colspan="3">
								<html:textarea onblur="chLengs(this,200);" readonly="true" property="hhyy" styleId="hhyy" rows="4" style="width:98%" title="�Ƿ�����ֶ�Ϊ��ʱ����"></html:textarea>
							</td>
						</tr>
						<tr>
							<th style="width: 20%" rowspan="4">
								��ע
								<br/>
								<font color="red">��������200��</font>
							</th>
							<td rowspan="4" colspan="3">
								<html:textarea onblur="chLengs(this,200);" property="bz" styleId="bz" rows="4" style="width:98%" title="�ڴ��������ٻ�����Ʒ"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz" align="left">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" onclick="saveAdd();">
										����
									</button>
									<button type="button" onclick="refreshParent2();">
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