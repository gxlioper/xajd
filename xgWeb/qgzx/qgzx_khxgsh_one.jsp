<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body onload="checkWinType();">
		<html:form action="/qgzx_kh_modicheck" method="post">
			<input type="hidden" name="xh" id="xh" value="<bean:write name="xh"/>"/>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>
					<logic:equal value="10827" name="xxdm">
						��ǰ����λ��ѧ���幤 - ��� - ���Ÿ������ - �������
					</logic:equal>
					<logic:notEqual value="10827" name="xxdm">
						��ǰ����λ���ڹ���ѧ - ��� - ���Ÿ������ - �������
					</logic:notEqual>	
					</a>
				</p>
			</div>

			<div class="tab">	
			<table width="100%" align="center" class="formlist">
				<thead>
					<tr style="height:22px">
						<th colspan="4">
							<span>����ѧ���������</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr style="height:22px">
						<th>
							ѧ��
						</th>
						<td align="left">
							<bean:write name='rs' property="xh" />
						</td>
						<th>
							�޸�����
						</th>
						<td align="left">
							<bean:write name='rs' property="xglx" />
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
							<bean:write name="rs" property="xgqkh" />
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
							<input type="text" id="xghkh" name="xghkh" readonly="readonly" value="<bean:write name='rs' property="xghkh" />"/>
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
							<bean:write name='rs' property="lxdh" />
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
							���
						</th>
						<td align="left">
							<html:select name="rs" property="sfsh" style="width:130px" styleId="sfsh">
								<html:options collection="chkList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							רҵ
						</th>
						<td align="left">
							<bean:write name='rs' property="zymc" />
						</td>
						<th>
						</th>
						<td align="left">
						</td>
					</tr>
					<tr style="height:22px">
						<th>
							�༶
						</th>
						<td align="left">
							<bean:write name='rs' property="bjmc" />
						</td>
						<th>
						</th>
						<td align="left">
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<th>
							����ԭ��
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='sqyy' styleId="sqyy"
								style="width:99%" rows='5' readonly="true"/>
						</td>
					</tr>
					<tr align="left" style="height:22px">
						<th>
							��ע
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='bz' styleId="bz"
								style="width:99%" rows='5' readonly="true"/>
						</td>
					</tr>
					</tbody>
					<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
							  <button type="button" onclick="refreshForm('/xgxt/SaveStuKhxgChkOne.do');" id="buttonSave">
								����
							  </button>
							  <button type="button" name="�ر�" onclick="window.close();return false;">�ر�</button>
				          </div></td>
				      </tr>
				    </tfoot>
				</table>
				</div>
		</html:form>
		<logic:equal value="view" name="result">
			<script>
				alert("�����ɹ���");
				Close();
				window.dialogArguments.document.getElementById('search_go').click();
			</script>
		</logic:equal>
	</body>
</html>
