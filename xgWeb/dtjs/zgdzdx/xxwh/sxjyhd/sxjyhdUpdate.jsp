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
					<em>���ĵ�ǰλ��:</em><a>�������� ά��</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
				<fieldset>
					
					<div class="tab">
					<table width="100%" class="formlist" border="0">
						<thead>
							<tr>
								<th colspan="4"><span>��������</span></th>
							</tr>
						</thead>
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
					        	  <button type="button" 
									onclick="dtjsCommonSave('zgdzdxXxwh.do?method=sxjyhdSave','hdnr-hdxg-bz','600-600-300','xh-xydm');"
									id="buttonSave">
									�� ��
								</button>
								<button type="button" onclick="Close();return false;"
									id="buttonClose">
									�� ��
								</button>
					          </div></td>
					      </tr>
					    </tfoot>
						
						<tbody>
						<tr>
							<th>
								<font color="red">*</font><bean:message key="lable.xsgzyxpzxy" />��
							</th>
							<td align="left">
								<html:select name = "rs" property="xydm" style="width:180px" styleId="xy" >
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
								</html:select>
							</td>
							<th>
							</th>
							<td align="left">
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh" maxlength="10" onkeypress='return numInputValue(this,10,event)' style="width:180px"/>
							</td>
							<th>
								����ʱ��
							</th>
							<td align="left">
								<html:text name='rs' property="fssj" styleId="fssj" readonly="true"
									onblur="dateFormatChg(this)" style="width:180px;cursor:hand;"
									onclick="return showCalendar('fssj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap">
								�����
							</td>
							<td align="left" colspan="3">
								<html:text name='rs' property="hdzt" styleId="hdzt" maxlength="100" style="width:90%"/>
							</td>
						</tr>
						<tr>
							<th>
								��ص�
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="hddd" styleId="hddd" maxlength="100" style="width:90%"/>
							</td>
						</tr>
						
						<tr align="left" id="hdnr">
							<th>
								�����
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='hdnr' style="width:99%"
									rows='5' />
							</td>
						</tr>
						<tr align="left" id="hdxg">
							<td align="right" >
								�Ч��
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='hdxg' style="width:99%"
									rows='5' />
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
