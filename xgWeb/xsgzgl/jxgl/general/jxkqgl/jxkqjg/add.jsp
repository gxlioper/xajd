<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript" src="xsgzgl/jxgl/general/jxkqgl/jxkqjg/js/jxkqjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/browser/js/browser.js"></script>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<html:form method="post" styleId="JxkqjgForm" action="/jxkqjg" >
		<div style='tab;width:100%;overflow-x:hidden;overflow-y:auto;'>
			<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣ</span>
							</th>
						</tr>
					</thead>
					<%@ include file="/xsgzgl/rcsw/rcxwwh/comm/selectStudent.jsp" %>
				<thead>
						<tr>
							<th colspan="4">
								<span>������Ϣ
								</span>
							</th>
						</tr>
				</thead>
				<tbody>
					<tr>
						<th align="right" width="10%">
							��ѵ����
						</th>
						<td align="left">
							<input type="hidden" name="jxid" id="jxid" value="${jxxxMap.jxid}"/>
							${jxxxMap.jxmc}
						</td>
						<th align="right">
							<span class="red">*</span>�������
						</th>
						<td align="left">
								<html:select property="kqlb" styleId="kqlb" style="width:125px;">
									<html:options collection="kqlbList" property="lbdm"
										labelProperty="lbmc" />
								</html:select>
						</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							<span class="red">*</span>����ʱ��
						</th>
							<td align="left">
								<html:text property="kqsj" styleId="kqsj" style="width:120px;"
									onfocus="return showCalendar('kqsj','yyyy-MM-dd HH:mm');" />
							</td>
						<th align="right">
							<span class="red">*</span>��������
						</th>
							<td align="left">
								<html:select property="kqlx" styleId="kqlx" style="width:120px;">
									<html:options collection="kqlxList" property="lbdm"
										labelProperty="lbmc" />
								</html:select>
							</td>
					</tr>
					<tr>
						<th align="right" width="10%">
							�������&nbsp;
							<br />
							<font color="red">(��500��)</font>	
						</th>
						<td colspan="3">
							<html:textarea rows="4" property="kqqk" styleId="kqqk" style="width:97%" onblur="checkLen(this,500);"></html:textarea>
						</td>
					</tr>
					
				</tbody>
			</table>
		</div>
		<div>
			<table width="100%" border="0" class="formlist" style="position: fixed; _position: absolute; bottom: 0;">
					<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button"  onclick="save('jxkqjg.do?method=save','xh-kqlx-kqlb-kqsj','false');return false;" id="buttonSave">
									�� ��
								</button>
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
