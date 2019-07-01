<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/dtjs/dtjsFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/GetFdyList.js"></script>
	</head>
	<body>
		<html:form action="/zgdzdxXxwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�о����ϵ���ά��</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="xh" scope="request"/>" />
					<div class="tab">
					<table width="100%" class="formlist">
					<thead>
						<tr><th colspan="4"><span>�о�����</span></th></tr>
					</thead>
					<tbody>
						<tr>
						<logic:equal name="rs" property="xh" value="">
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								ϵͳ����
							</td>
						</logic:equal>
						<logic:notEqual name="rs" property="xh" value="">
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								<bean:write name="rs" property="xh"/>
							</td>
						</logic:notEqual>
							<th>
								������
							</th>
							<td align="left">
								<html:text name='rs' property="cbdw" styleId="cbdw" maxlength="100" />
							</td>
						</tr>
						<tr>
							<th>
								����ʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="cbsj" styleId="cbsj" readonly="true"
									onblur="dateFormatChg(this)" style="width:180px;cursor:hand;"
									onclick="return showCalendar('cbsj','y-mm-dd');" />
							</td>
							<th>
								�۸�
							</th>
							<td align="left">
								<html:text name='rs' property="jg" styleId="jg" maxlength="50"  onkeypress="return sztzNumInputValue(this,6,event)"/>
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>����(�ڿ���)
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="sm" styleId="sm" maxlength="50" style="width:90%"/>
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
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
								<button type="button" onclick="dtjsCommonSave('zgdzdxXxwh.do?method=yjzlSave','bz','300','sm');" 
									id="buttonSave">
									�� ��
								</button>
					            <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
					          </div></td>
					      </tr>
					    </tfoot>
					</table>
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
