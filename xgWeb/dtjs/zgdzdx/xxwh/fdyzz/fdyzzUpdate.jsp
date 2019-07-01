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
					<em>���ĵ�ǰλ��:</em><a>����Ա��������ά��</a>
				</p>
			</div>
			<logic:notEmpty name="rs">
				<input type="hidden" id="pk" name="pk"
					value="<bean:write name="rs" property="pk" scope="request"/>" />
					
					<div class="tab">
					<table width="100%" class="formlist">
						<thead><tr><th colspan="4"><span>����Ա����</span></th></tr></thead>
						<tbody>
						<tr>
							<th>
								<font color="red">*</font>����
							</th>
							<td align="left">
								<html:select name = "rs" property="bmdm" style="width:180px" styleId="xy" onchange="getFdyList();">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
								</html:select>
							</td>
							<th>
								<font color="red">*</font>����
							</th>
							<td align="left">
								<html:select name = "rs" property="zgh"  styleId="zgh" style="width:150px" >
										<html:option value=""></html:option>
										<html:options collection="fdyList" property="zgh"
											labelProperty="xm" />
								</html:select>
							<input type="hidden" name="zghV" value="<bean:write name="rs" property="zgh" scope="request"/>"/>
							</td>
						</tr>
						<tr>
						<logic:equal name="rs" property="pk" value="">
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								ϵͳ����
							</td>
						</logic:equal>
						<logic:notEqual name="rs" property="pk" value="">
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								<bean:write name="rs" property="xh"/>
							</td>
						</logic:notEqual>
							<th>
								���浥λ
							</th>
							<td align="left">
								<html:text name='rs' property="cbdw" styleId="cbdw" maxlength="100" />
							</td>
						</tr>
						<tr>
							<th>
								<font color="red">*</font>��������
							</th>
							<td align="left" colspan="3">
								<html:text name='rs' property="zzmc" styleId="zzmc" maxlength="50" style="width:90%"/>
							</td>
						</tr>
						<tr align="left" id="zzjs">
							<th>
								��������
							</th>
							<td colspan="3">
								<html:textarea name='rs' property='zzjs' style="width:99%"
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
						<tfoot>
					      <tr>
					        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
					          <div class="btn">
								<button type="button" onclick="dtjsCommonSave('zgdzdxXxwh.do?method=fdyzzSave','zzjs-bz','300-300','zgh-bmdm-zzmc');" 
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
