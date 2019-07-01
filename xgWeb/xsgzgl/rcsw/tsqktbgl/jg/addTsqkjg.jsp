<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/tsqktbgl/jg/js/tsqkjg.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/tsqktbgl_jg" method="post" styleId="tsqkjgForm" onsubmit="return false;">
		<html:hidden property="xn" styleId="xn"/>
		<html:hidden property="xq" styleId="xq"/>
		<html:hidden property="txr" styleId="txr" value="${txr}"/>
		<input type="hidden" id="xxdm" name="xxdm" value="${xxdm}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/tsqktbgl/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>ͨ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								ѧ��
							</th>
							<td width="30%">
								${xnsr}
							</td>
							</td>
							<th>ѧ��</th>
							<td>
								${xqsr}
							</td>
						</tr>
						<tr>
					    	<th><span class="red">*</span>ѧ�����һ</th>
					    	<td>
								<html:select property="xqdm1" styleId="xqdm1" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xqflList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>							
							<th>ѧ������</th>
							<td>
								<html:select  property="xqdm2" styleId="xqdm2" style="width:200px">
									<html:option value=""></html:option>
									<html:options collection="xqflList" labelProperty="xqmc" property="xqdm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th><span class="red">*</span>ͨ��ʱ��</th>
					    	<td>
								<html:text property="tbsj" styleId="tbsj" onfocus="showCalendar('tbsj','y-mm-dd');"></html:text>
						   </td>
						   <th><span class="red">*</span>����㼶</th>
					    	<td>
								<html:select styleId="clcj" property="clcj">
									<html:option value="1">Ժϵ</html:option>
									<html:option value="2">ѧ����</html:option>
								</html:select>
						   </td>
						</tr>
					<logic:equal name="xxdm" value="10026">
						<tr>
							<th><span class="red">*</span>��������̶�</th>
					    	<td>
								<html:select  property="wtjjcd" styleId="wtjjcd" >
									<html:option value=""></html:option>
									<html:option value="һ�����">һ�����</html:option>
									<html:option value="�ȽϽ���">�ȽϽ���</html:option>
									<html:option value="�ǳ�����">�ǳ�����</html:option>
								</html:select>
						   </td>
						</tr>
				   </logic:equal>
								<tr>
									<th><span><font color="red">*</font></span>
										<logic:notEqual name="xxdm" value="10026">
											����ѧ��
										</logic:notEqual>
										<logic:equal name="xxdm" value="10026">
											����״������
										</logic:equal>
										<br /><font color="red">&lt;��500��&gt;</font>
									</th>
									<td colspan="3">
										<html:textarea property='tsxq' style="width:98%" styleId="tsxq" rows='8' onblur="checkLen(this,500);"/>
									</td>
								</tr>
						<tr>
							<th><span><font color="red">*</font></span>
								<logic:notEqual name="xxdm" value="10026">
									����ѧ���Ԥ���
								</logic:notEqual>
								<logic:equal name="xxdm" value="10026">
									�Ѳ�ȡ��ʩ������������ʩ
								</logic:equal>
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='tsxqgyqk' style="width:98%" styleId="tsxqgyqk" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:30px"></div>   
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="saveTsqkjg('save');">
										����
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
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

