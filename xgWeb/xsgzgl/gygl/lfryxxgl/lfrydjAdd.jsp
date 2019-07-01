<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript"
			src="<%=stylePath%>js/lhgdialog/lhgdialog.min.js?self=true"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/lfryxxgl/js/lfrydj.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	</head>
	<body>
		<button id="search_go" type="button" style="display: none" onclick="reloadWindow();"></button>
		<html:form action="/gygl_lfrydj" method="post" styleId="lfrydjForm">
			<div style="tab;overflow-x:hidden;overflow-y:auto;height:422px;margin-bottom: 0px;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>���õǼ�</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<th>
								¥������
							</th>
							<td colspan="3">
								<html:select property="lddm" styleId="lddm" onchange="emptyBfrxx()">
									<html:options collection="ldList" labelProperty="ldmc" property="lddm"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">* </font>����������
							</th>
							<td>
								<html:text property="lfrxm" styleId="lfrxm" maxlength="30"></html:text>
								<logic:equal value="12216" name="xxdm">
									<button class="btn_01" onclick="return false" disabled="disabled">��ȡ���֤</button>
								</logic:equal>
							</td>
							<th >
								<font color="red">* </font>�������Ա�
							</th>
							<td>
								<html:radio property="lfrxb" styleId="lfrxb" value="��">��</html:radio>
								<html:radio property="lfrxb" styleId="lfrxb" value="Ů">Ů</html:radio>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">* </font>������֤������
							</th>
							<td colspan="3">
								<html:text property="lfrsfzh" styleId="lfrsfzh"  maxlength="30"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								������ѧ��
							</th>
							<td >
								<%-- <html:text property="xh" styleId="xh" maxlength="20" styleClass="text_nor" readonly="true" /> --%>
								<input type="text" id="xh" name="xh" maxlength="20" class="text_nor" readonly="true"/>
								<button class="btn_01" type="button"  onclick="chooseStudent();return false;">
									ѡ��
								</button>
							</td>
							<th>
								����������
							</th>
							<td>
								<span id="xm">${jbxx.xm}</span>
							</td>
						</tr>
						
						<tr>
							<th >������ѧԺ����</th><td>	<span id="xymc">${jbxx.xymc}</span></td>
							<th >������רҵ����</th><td>	<span id="zymc">${jbxx.zymc}</span></td>
						</tr>
						
						<tr>
							<th >�����˰༶����</th><td>	<span id="bjmc">${jbxx.bjmc}</span></td>
							<th >¥������</th><td>	<span id="ldmc">${lfryxx.ldmc}</span></td>	
						</tr>
						
						<tr>
							<th >���Һ�</th><td colspan="3">	<span id="qsh">${lfryxx.qsh}</span></td>
						</tr>
						
						<tr>
							<th >��������</th>
							<td colspan="3">
								<html:select property="lfsydm" styleId="lfsydm">
									<%-- <html:option value=""></html:option> --%>
									<html:options collection="lfsyList" property="lfsydm"
										labelProperty="lfsymc" />
								</html:select>
							</td>
						</tr>
						
						<tr>
							<th>
								<font color="red">* </font>����ʱ��
							</th>
							<td>
								<html:text property="lfsj" styleId="lfsj"  onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm',true,'lqsj');"></html:text>
							</td>
							<th>
								��ȥʱ��
							</th>
							<td>
								<html:text property="lqsj" styleId="lqsj"  onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm',false,'lfsj');"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">* </font>ֵ����Ա
							</th>
							<td colspan="3">
								<html:text property="zbry" styleId="zbry"  maxlength="30"></html:text>
							</td>
						</tr>
						<tr>
							<th>
								��ע
								<br />
								<font color="red">(��500��)</font>
							</th>
							<td colspan="3" style="word-break: break-all;">
								<html:textarea property="bz" styleId="bz" style="width:95%;" rows="8"></html:textarea>
							</td>
						</tr>					
					</tbody>
				</table>
			</div>
				
			<div>	
				<table class="formlist">
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button id="save_button" type="button"
										onclick="addLfrydjxx();">
										�� ��
									</button>
									<button type="button" name="�� ��" onclick="iFClose();">
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

