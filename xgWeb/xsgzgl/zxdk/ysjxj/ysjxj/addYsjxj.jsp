<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>		
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/ysjxj/ysjxj/js/ysjxj.js"></script>		
	</head>
	<body>
		<html:form action="/ysjxj_ysjxjwh" method="post" styleId="ysjxjForm">
		<html:hidden property="xn" value="${xn}"/>
		<html:hidden property="xq" value="${xq}"/>
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/zxdk/ysjxj/ysjxj/comm/selectStudent.jsp" %>
					<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>ѧ��</th>
							<td>
								${xn}
							</td>
							<th>ѧ��</th>
							<td>
								${xq}
							</td>
					    </tr>
					    <tr>
					    	<th><font color="red">*</font>��Ŀ����
							</th>
							<td>
								<html:text property="xmmc" styleId="xmmc" onblur="checkLen(this,16);"/>
							</td>
							
							<th><font color="red">*</font>���</th>
							<td>
								<html:text property="je" styleId="je" style="width:120px;" maxlength="5"  styleClass="text_nor" onkeyup="javascript:this.value.substring(0,1)=='0'?this.value='0':this.value=this.value.replace(/\D/g, '')" />
							</td>
					    </tr>
					    
					    <tr>
					    	<th width="20%"><span class="red">*</span>��Ŀ����</th>
<!--							<td>-->
<!--								<input type="radio" name="xmlx" value="��ѧ��" checked="checked" />��ѧ��-->
<!--								<input type="radio" name="xmlx" value="��ѧ��" />��ѧ��-->
<!--							</td>-->
							<td>
								<html:radio property="xmlx" styleId="xmlx" value="��ѧ��">��ѧ��</html:radio>
								<html:radio property="xmlx" styleId="xmlx" value="��ѧ��">��ѧ��</html:radio>
							</td>
							<th><font color="red">*</font>�ʽ���Դ</th>
							<td>
								<html:select property="zjly" styleId="zjly" style="width:170px">
									<html:options collection="zjlyList" property="zjlydm" labelProperty="zjlymc" />
								</html:select>
							</td>
				    	</tr>
				    	
				    	<tr>
							<th><span class="red">*</span>����ʱ��</th>
							<td colspan="3">
								<html:text property="ffsj" styleId="ffsj" onfocus="showCalendar('ffsj','y-mm-dd');"></html:text>
							</td>
				    	</tr>
					    
					    <tr>
							<th>
								��ע
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='5' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height:30px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveYsjxj('save');">
										����
									</button>
									<button type="button" type="button" onclick="iFClose();">
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