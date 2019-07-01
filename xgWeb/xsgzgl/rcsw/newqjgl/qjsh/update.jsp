<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/newqjgl/qjsh/js/operation.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function() {
			});
		</script>
	</head>
	<body>
		<html:form method="post"
		 styleId="form" action="/qjsq">
		 <html:hidden property="qjsqid"/>
		 <html:hidden property="xn"/>
		 <html:hidden property="xq"/>
		 <html:hidden property="qjzt"/>
		<div style='tab;width:100%;height:400px;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/viewStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>�����Ϣ</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							ѧ��
						</th>
						<td align="left">
							${dqxn}
						</td>
						<th align="right">
							ѧ��
						</th>
						<td align="left">
							${dqxq}
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>�������
						</th>
						<td align="left">
							<html:text property="qjts" styleId="qjts" style="width:80%"></html:text>&nbsp;&nbsp;��&nbsp;&nbsp;
						</td>
						<th align="right">
							<span class="red">*</span>�������
						</th>
						<td align="left">
								<html:select property="qjlxid" styleId="qjlxid" disabled="false"
									style="width:94%">
									<html:options collection="qjlxList" property="qjlxid"
										labelProperty="qjlxmc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>��ٿ�ʼʱ��
						</th>
						<td align="left">
							<html:text property="kssj" styleId="kssj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:92%"/>
						</td>
						<th align="right">
							<span class="red">*</span>��ٽ���ʱ��
						</th>
						<td align="left">
							<html:text property="jssj" styleId="jssj" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" style="width:92%"/>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>�������
						</th>
						<td colspan="3">
							<html:textarea property="qjsy" styleId="qjsy" style="width:92%"></html:textarea>
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
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save('qjsq.do?method=update&type=save','qjts-qjlxid-kssj-jssj-qjsy');return false;" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button"  onclick="iFClose();" id="buttonClose">
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
