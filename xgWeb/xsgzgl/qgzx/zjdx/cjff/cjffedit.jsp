<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/zjdx/cjff/js/cjff.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			jQuery(function(){
			})
		</script>
	</head>
	<body>
		<html:form action="/cjff_zjdx" method="post" styleId="CjffForm">
		<input type="hidden" name="sxsz" id="sxsz" value="${sxsz}" />
		<input type="hidden" name="cjbz" id="cjbz" value="${cjbz}" />
		<!-- �Ƿ�������������� -->
		<input type="hidden" name="sfyxcgcjsx" id="sfyxcgcjsx" value="${sfyxcgcjsx}" />
		<html:hidden property="id" styleId="id"/>
			<div style="tab;overflow-x:hidden;overflow-y:auto;margin-bottom:0px;" >
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/qgzx/zjdx/cjff/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>��𷢷���Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th><font color="red">*</font>��������</th>
							<td>
								${ffndyf}
								<html:hidden property="ffndyf" styleId="ffndyf" />
								
							</td>
							<th><font color="red">*</font>���˵�λ</th>
							<td>
								<html:select property="yrdwdm" styleId="yrdwdm" style="width:98%">
									<html:options collection="yrdwList" property="yrdwdm" labelProperty="yrdwmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��λ����</th>
							<td>
								<html:select property="gwxzdm" styleId="gwxzdm" style="width:98%">
									<html:options collection="gwxzList" property="gwxzdm" labelProperty="gwxzmc"/> 
								</html:select>
							</td>
							<th><font color="red">*</font>��λ���</th>
							<td>
								<html:select property="gwlbdm" styleId="gwlbdm" style="width:98%">
									<html:options collection="gwlbList" property="gwlbdm" labelProperty="gwlbmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>У��</th>
							<td>
								<html:select property="xqdm" styleId="xqdm" style="width:98%">
									<html:options collection="xqList" property="xqdm" labelProperty="xqmc"/>
								</html:select>
								
							</td>
							<th><font color="red">*</font>���(Ԫ)</th>
							<td>
								<html:text property="bcje" styleId="bcje" maxlength="10" onkeyup="checkMoneyBykeyUpByone(this)" style="width:98%" onblur="cjchange()"/>							
							</td>
						</tr>
						<tr>
							<th><font color="red">*</font>��ʱ(Сʱ)</th>
							<td >
								<html:text property="gss" styleId="gss" readonly="true" style="width:98%"/>
							</td>
							<th></th>
							<td></td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
								</br><font color="red">(��500��)</font>
							</th>
							<td colspan="3">
								<html:textarea property="gznr" styleId="gznr" 
								   onkeyup="checkLen(this,500);" 
								   style="width:98%;" rows="5"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>��ע
								</br><font color="red">(��500��)</font></th>
							<td colspan="3">
								<html:textarea property="bz" styleId="bz" 
								   onkeyup="checkLen(this,500);" 
								   style="width:98%;" rows="5"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
				</div>	
				<div style="height:30px;"></div>
				<%--;height:520px --%>
			<div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button" onclick="saveForm('update');">
										��    ��
									</button>
									<button type="button" onclick="saveForm('updatesubmit');">
										��    ��
									</button>
									<button type="button" onclick="iFClose();">
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