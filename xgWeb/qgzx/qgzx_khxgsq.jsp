<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
	</head>
	<body>
		<html:form action="/qgzx_kh_modicheck" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>
						<logic:equal value="10827" name="xxdm">
							��ǰλ��ѧ���幤 - ���Ÿ������� - ��д�����
						</logic:equal>
						<logic:notEqual value="10827" name="xxdm">
							��ǰλ���ڹ���ѧ - ���Ÿ������� - ��д�����
						</logic:notEqual>
					</a>
				</p>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
					�д�������
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    				alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/qgzx_kh_modiapply.do" />
				<input type="hidden" id="gwsbsj" name="gwsbsj" />
				
				<div class="tab">
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>��д�����</span>
							</th>
						</tr>
					</thead>
					<tbody>
					<tr style="height:22px">
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xh" styleId="xh"
									onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu">
									ѡ��
								</button>
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<bean:write name='rs' property="xh" />
							</td>
						</logic:equal>
						<th>
							<font color="red">*</font>�޸�����
						</th>
						<td align="left">
							<html:select name="rs" property="xglx" styleId="xglx" style="width:150px">
								<html:option value=""></html:option>
								<html:option value="��ʧ">��ʧ</html:option>
								<html:option value="�޸�">�޸�</html:option>
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							����
						</th>
						<td align="left">
							<bean:write name='rs' property="xm" />
						</td>
						<th>
							�޸�ǰ����
						</th>
						<td align="left">
							<input type="text" name="xgqkh" id="xgqkh" value="<bean:write name="rs" property="xgqkh" />" readonly="readonly"/>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�Ա�
						</th>
						<td align="left">
							<bean:write name='rs' property="xb" />
						</td>
						<th>
							�޸ĺ󿨺�
						</th>
						<td align="left">
							<html:text name="rs" property="xghkh"></html:text>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�꼶
						</th>
						<td align="left">
							<bean:write name='rs' property="nj" />
						</td>
						<th>
							��ϵ�绰
						</th>
						<td align="left">
							<html:text name='rs' property="lxdh" value="${rs.lxdh}"/>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							<bean:message key="lable.xsgzyxpzxy" />
						</th>
						<td align="left">
							<bean:write name='rs' property="xymc" />
						</td>
						<th>
							רҵ
						</th>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�༶
						</th>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<logic:present name="sh">
						<th>
							���
						</th>
						<td align="left">
						</td>
						</logic:present>
						<logic:notPresent name="sh">
						<th>
						</th>
						<td align="left">
						</td>
						</logic:notPresent>
					</tr>
					
					<tr align="left" style="height:22px">
						<th>
							����ԭ��
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='sqyy' styleId="sqyy"
								style="width:99%" rows='5' />
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<th>
							��ע
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' />
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
							<button type="button" name="�ύ" onclick="refreshForm('/xgxt/Savekhapply.do')">�ύ</button>
							<button type="button" onclick="expAppTab('rsT','�ڹ���ѧ��λ�����','')">��ӡ</button>
				          </div></td>
				      </tr>
				    </tfoot>
				</table>
				</div>
			</logic:notEmpty>
			<logic:notEmpty name="inserted">
				<logic:equal name="inserted" value="ok">
					<script>
    alert("����ɹ���");
    </script>
				</logic:equal>
				<logic:equal name="inserted" value="no">
					<script>
    alert("����ʧ�ܣ�");
    </script>
				</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
