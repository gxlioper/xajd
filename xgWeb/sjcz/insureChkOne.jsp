<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript">
		function sele(){
			var userType = document.getElementById("userType").value;
			if(userType=="xy"){
				document.getElementById("xxyj").disabled="disabled";
			}else{
				document.getElementById("xyyj").disabled="disabled";
			}
		}
	</script>
	</head>
	<body onload="checkWinType();sele()">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>�������� - ������Ϣ - ѧУ��� - �������</a>
			</p>
		</div>

		<html:form action="/data_search" method="post">
			<%--���Ϻ����̼�����ѧ--%>
			<logic:notEqual value="10856" name="xxdm">
				<input type="hidden" name="pkVal"
					value="<bean:write name="xh||nd||tbgsdm||tbxzdm"/>" />
			</logic:notEqual>
			<%--�Ϻ����̼�����ѧ--%>
			<logic:equal value="10856" name="xxdm">
				<input type="hidden" name="pkVal"
					value="<bean:write name="xh||nd"/>" />
			</logic:equal>
			<input type="hidden" name="userType" id="userType"
				value="<bean:write name="userType"/>" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ�����յ������</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">
									"
									<span class="red">*</span>"Ϊ������
								</div>
								<div class="btn">
									<button type="button"
										onclick="refreshForm('/xgxt/insureChkOne.do?act=save');Close();window.dialogArguments.document.getElementById('search_go').click();"
										id="buttonSave">
										�� ��
									</button>
									<button type="button" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="XH" />
							</td>
							<th>
								���
							</th>
							<td align="left">
								<bean:write name="ND" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<bean:write name="XM" />
							</td>
							<th>
								ѧ��
							</th>
							<td align="left">
								<bean:write name="XN" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<bean:write name="XB" />
							</td>
							<th>
								���չ�˾
							</th>
							<td align="left">
								${bxgsmc }
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<bean:write name="NJ" />
							</td>
							<th>
								��������
							</th>
							<td align="left">
								${bxxzmc }
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />

							</th>
							<td align="left">
								${XYMC}
							</td>
							<th>
								��������
							</th>
							<td align="left">
								${bxnx }
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								${ZYMC}
							</td>
							<th>
								����
							</th>
							<td align="left">
								${bf}
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								${BJMC}
							</td>
							<th>
								���
							</th>
							<td align="left">
								<html:select property="yesNo" name="rs">
									<html:options collection="chkList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
								���
							</th>
							<td align="left" colspan="3">
								<html:textarea property="xyyj" styleId="xyyj"
									style="width:100%;height:45" name="rs"
									onblur="chLeng(this,100)"></html:textarea>
							</td>
						</tr>
						<tr>
							<th>
								ѧУ���
							</th>
							<td align="left" colspan="3">
								<html:textarea property="xxyj" styleId="xxyj"
									style="width:100%;height:45" name="rs"
									onblur="chLeng(this,100)"></html:textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</html:form>
	</body>
</html>
