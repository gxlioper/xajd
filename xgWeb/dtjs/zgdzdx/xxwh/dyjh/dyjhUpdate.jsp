<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
</head>
	<body onload="checkWinType();">
		<html:form action="/zgdzdxXxwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>���мƻ�ά��</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="xh" scope="request"/>" />
				<fieldset>
				
					<div class="tab">
					<table width="100%" class="formlist">
						<thead>
							<tr>
								<th colspan="4"><span>���мƻ�</span></th>
							</tr>
						</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					          <button type="button" onclick="dtjsCommonSave('zgdzdxXxwh.do?method=dyjhSave','dytg-dynr-bz','300-2000-300','dytm');" id="buttonSave">�� ��
							  </button>
							<button type="button" onclick="Close();return false;" id="buttonClose">
								�� ��
							</button>
					        </div></td>
					      </tr>
					    </tfoot>
						
						<tbody>
						<tr>
							<th>
								������
							</th>
							<td align="left">
								<html:text name='rs' property="cyr" maxlength="50"/>
							</td>
							<th>
								����ʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="dysj" styleId="dysj" readonly="true"
									onblur="dateFormatChg(this)" style="width:180px;cursor:hand;"
									onclick="return showCalendar('dysj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th >
								���еص�
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="dydd" styleId="dydd" maxlength="100" style="width:90%"/>
							</td>
						</tr>
						<tr>
							<th nowrap="nowrap">
								<font color="red">*</font>������Ŀ
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="dytm" styleId="dytm" maxlength="100" style="width:90%"/>
							</td>
						</tr>
						
						<tr align="left" id="dytg">
							<th>
								�������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='dytg' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left" id="dynr">
							<th >
								��������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='dynr' style="width:99%"
									rows='10' />
							</td>
						</tr>
						<tr align="left" id="bz">
							<th>
								��ע
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
				</fieldset>
				<div class="buttontool">
					
				</div>
			</logic:notEmpty>
			<logic:present name="update">
				<logic:equal name="update" value="yes">
					<script>
    alert("�ύ�ɹ���");
    dialogArgumentsQueryChick();
	Close()
    </script>
				</logic:equal>
				<logic:equal name="update" value="no">
					<script>
    alert("�ύʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
