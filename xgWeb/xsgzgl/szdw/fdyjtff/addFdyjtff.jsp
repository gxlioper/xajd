<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdyjtff/js/fdyjtff.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			changbzlx();
		});
		</script>
	</head>
	<body>
		<html:form action="/szdw_fdyjtff.do" styleId="fdyjtffForm" method="post">
			<%@ include file="/comm/hiddenValue.jsp"%>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����Ա��Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th style="width:15%"><font color="red">*</font>ְ����</th>
							<td style="width:35%"><html:text property="zgh" readonly="true" styleId="zgh" style="width:60%" />
							<button class="btn_01" type="button"  
										onclick="showDialog('��ѡ��һ������Ա',680,480,'szdw_fdyjtff.do?method=showFdys&goto=${path}');return false;">ѡ��</button>
							</td>
							<th style="width:15%">����</th>
							<td style="width:35%">
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xm"/>
								</logic:present>
							</td>
						</tr>
						
						<tr>
							<th>�Ա�</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="xbmc"/>
								</logic:present>
							</td>
							<th>��ϵ�绰</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="lxdh"/>
								</logic:present>
							</td>
						</tr>
						<tr>
							<th>����</th>
							<td>
								<logic:present name="jbxx">
									<bean:write name="jbxx" property="bmmc"/>
								</logic:present>
							</td>
							<th></th>
							<td>
								
							</td>
						</tr>
					</tbody>
					<thead>
						<tr>
							<th colspan="4">
								<span>����������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="16%">
								<font color="red">*</font>��������
							</th>
							<td width="34%">
								<html:select property="bzlx" styleId="bzlx" onchange="changbzlx()" style="width:70%">
									<html:option value=""></html:option>
									<html:option value="0">��λ����</html:option>
									<html:option value="1">��Ч���˲���</html:option>
								</html:select>
							</td>
							<th width="16%">
								�����ȼ�
							</th>
							
							<td width="34%">
								<html:select property="kpdj" styleId="kpdj" disabled="true" style="width:70%">
									<html:option value=""></html:option>
									<html:option value="0">����</html:option>
									<html:option value="1">����</html:option>
									<html:option value="2">�ϸ�</html:option>
									
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							<td width="34%">
								<html:select property="xn" name="map" styleId="xn" onchange="" style="width:70%">
									<html:options collection="xnList" property="xn" labelProperty="xn"/>
								</html:select>
							</td>
							<th width="16%">
								<font color="red">*</font>ѧ��
							</th>
							
							<td width="34%">
								<html:select property="xq" name="map" styleId="xq" onchange="" style="width:70%">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="16%">
								<font color="red">*</font>�������
							</th>
							<td width="34%">
								<html:text onkeyup="checkInputNum(this)" maxlength="10" property="bzje" styleId="bzje" style="width:70%"></html:text>
							</td>
							<th width="16%">
								
							</th>
							
							<td width="34%">
								
							</td>
						</tr>
					</tbody>
				</table>
				<table border="0" class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
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