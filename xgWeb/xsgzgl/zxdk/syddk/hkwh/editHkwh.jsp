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
		<script type="text/javascript" src="xsgzgl/zxdk/syddk/hkwh/js/syddkHkwh.js"></script>
	</head>
	<body>	
		<html:form action="/syddk_hk" method="post" styleId="syddkhkwhForm">
			<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
				<html:hidden property="jgid" styleId="jgid"/>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/comm/bdpz/viewStudent.jsp" %>
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
							<th><span class="red">*</span>����״̬</th>
							<td>
								<html:select property="hkzt" styleId="hkzt"  style="width:130px">
									<option value='��ȷ�����ϵ��'>��ȷ�����ϵ��</option>
								</html:select>
							</td>
							<th><span class="red">*</span>����ʱ��</th>
							<td>
								<html:text property="hksj" styleId="hksj" onfocus="showCalendar('hksj','y-mm-dd');"></html:text>
							</td>
					    </tr>
					    <tr>
							<th>
								��ע
								<br /><font color="red">&lt;��500��&gt;</font>
							</th>
							<td colspan="3">
								<html:textarea property='bz' style="width:98%" styleId="bz" rows='8' onblur="checkLen(this,500);"/>
							</td>
			      		</tr>
					</tbody>
				</table>
			</div>
			<div style="height:10px"></div>
				<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" id="bc" onclick="saveHkjg('update');">
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

