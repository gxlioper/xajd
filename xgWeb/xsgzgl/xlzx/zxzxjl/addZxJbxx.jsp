<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xlzx/zxzxjl/js/zxzxjl.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script type='text/javascript'>
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/zxzx_zxzxjl" method="post" styleId="zxzxjlForm" onsubmit="return false;">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/selectStudentAjaxAll.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>�Ƿ������Ů
							</th>
							<td width="30%">
								<html:select property="sfdszn" styleId="sfdszn" >
									<html:options collection="sfList" labelProperty="dm" property="mc"/>
								</html:select>
							</td>							
							<th><span><font color="red">*</font></span>��ͥ���ڵ�</th>
							<td>
								<html:select property="jtszd" styleId="jtszd" >
									<html:options collection="jtszdList" labelProperty="dm" property="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>��ͥ����״��
							</th>
							<td width="30%">
								<html:select property="jtjjzk" styleId="jtjjzk" >
									<html:options collection="jjzkList" labelProperty="dm" property="mc"/>
								</html:select>
							</td>							
							<th><span><font color="red">*</font></span>�����Ļ��̶�</th>
							<td>
								<html:select property="fqwhcd" styleId="fqwhcd" >
									<html:options collection="whcdList" labelProperty="dm" property="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>ĸ���Ļ��̶�
							</th>
							<td width="30%">
								<html:select property="mqwhcd" styleId="mqwhcd" >
									<html:options collection="whcdList" labelProperty="dm" property="mc"/>
								</html:select>
							</td>
							<th><span><font color="red">*</font></span>��ĸ�Ļ���״��</th>
							<td>
								<html:select property="fmhyzk" styleId="fmhyzk" >
									<html:options collection="hyzkList" labelProperty="dm" property="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>��ͥ��Ա���޾���ʷ
							</th>
							<td width="30%">
								<html:select property="jtjsbs" styleId="jtjsbs" >
									<html:options collection="ywList" labelProperty="dm" property="mc"/>
								</html:select>
							</td>					
							<th><span><font color="red">*</font></span>�Լ�ͥ��ϲ���̶�</th>
							<td>
								<html:select property="jtxhcd" styleId="jtxhcd" >
									<html:options collection="xhcdList" labelProperty="dm" property="mc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th width="20%">
								<span><font color="red">*</font></span>�Ƿ���ܹ�������ѯ����������
							</th>
							<td width="30%">
								<html:select property="sfzl" styleId="sfzl" >
									<html:options collection="sfList" labelProperty="dm" property="mc"/>
								</html:select>
							</td>							
							<th><span><font color="red">*</font></span>�Ǽ�����</th>
							<td>
								<html:text property="djrq" styleId="djrq" onfocus="showCalendar('djrq','y-mm-dd');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								����ѯ����</br>
								<span><font color="red">������ѡ��������д��ѯ���ⲹ�䣩</font></span>
							</th>
							<td colspan="3">
								<logic:iterate name="zxwtList" id="wt">
									<html:checkbox property="yzxwts" value="${wt.dm}">${wt.mc}</html:checkbox>
<%--									<input type="checkbox" name="yzxwts" value="${wt.dm}">${wt.mc}</input>--%>
								</logic:iterate>
							</td>
			      		</tr>
			      		<tr>
						<th>
							��ѯ���ⲹ��<br /><font color="red">&lt;��100��&gt;
						</th>
						<td colspan="3">
							<html:textarea property="wtbc" styleId="wtbc" rows="4" style="width:99%" onblur="checkLen(this,100);">
							
							</html:textarea>					
						</td>
					</tr>
					<tr>
						<th>
							<span><font color="red">*</font></span>��ѯ��������Ԥ�ڽ��<br /><font color="red">&lt;��500��&gt;
						</th>
						<td colspan="3">
							<html:textarea property="zxqw" styleId="zxqw" rows="4" style="width:99%" onblur="checkLen(this,500);">
							
							</html:textarea>					
						</td>
					</tr>						
					</tbody>
				 </table>			
				</div>
			  <div style="height:35px"></div>  
			  <div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="save('add');">
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

