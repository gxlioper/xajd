<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>

	<body onload="checkWinType();document.all('buttonClose').focus()">
		<html:form action="/data_search" method="post">
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>�ճ����� - Ʊ����� - ��Ʊ�����ѯ - ����ά��</a>
				</p>
			</div>

			<input type="hidden" name="pkVal"
				value="<bean:write name="rs" property="dph"/>" />


			<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>��Ʊ��¼ά��</span>
							</th>
						</tr>
					</thead>
					<tbody>
						<tr style="height:22px">
							<th>
								ѧ��
							</th>
							<td>
								<bean:write name="rs" property="xh" />
							</td>
							<th>
								���
							</th>
							<td>
								<bean:write name="rs" property="nd" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<bean:write name="rs" property="xm" />
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<bean:write name="rs" property="xn" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td>
								<bean:write name="rs" property="xb" />
							</td>
							<th>
								ѧ��
							</th>
							<td>
								<bean:write name="rs" property="xqmc" />
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td>
								<bean:write name="rs" property="nj" />
							</td>
							<th>
								��Ʊ��
							</th>
							<td>
								<bean:write name="rs" property="dph" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<bean:write name="rs" property="xymc" />
							</td>
							<th>
								����
							</th>
							<td>
								<bean:write name="rs" property="cc" />
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td>
								<bean:write name="rs" property="zymc" />
							</td>
							<th>
								��վ
							</th>
							<td>
								<bean:write name="rs" property="dz" />
							</td>
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td>
								<bean:write name="rs" property="bjmc" />
							</td>
							<th>
								Ʊ��
							</th>
							<td>
								<bean:write name="rs" property="pj" />
							</td>
						</tr>
						<tr>
							<th>
								���޳���
							</th>
							<td>
								<bean:write name="rs" property="bxcc" />
							</td>
							<th>
								��˳��
							</th>
							<td>
								<bean:write name="rs" property="ksy" />
							</td>
						</tr>
						<tr>
							<th>
								������
							</th>
							<td>
								<bean:write name="rs" property="klc" />
							</td>
							<th>
								������
							</th>
							<td>
								<bean:write name="rs" property="kwz" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td>
								<bean:write name="rs" property="qt" />
							</td>
							<th>
								Ԥ�����
							</th>
							<td>
								<html:select name="rs" property="ydjg">
									<html:option value=""></html:option>
									<html:options collection="jgList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�Ƿ���ȡ
							</th>
							<td>
								<html:select name="rs" property="sflq">
									<html:option value=""></html:option>
									<html:options collection="lqList" property="en"
										labelProperty="cn" />
								</html:select>
							</td>
							<th>
								������
							</th>
							<td>
								<html:select name="rs" property="jbr" style="width:90px"
									styleId="jbr">
									<html:option value=""></html:option>
									<html:options collection="jbrList" property="jbrgh"
										labelProperty="jbrxm" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�˳�����
							</th>
							<td>
								<html:text name="rs" property="ccrq" styleId="ccrq"
									onclick="return showCalendar('ccrq','y-mm-dd');"></html:text>
							</td>
							<th>

							</th>
							<td>

							</td>
						</tr>
						<tr>
							<th>
								��ע
							</th>
							<td colspan="3">
								<bean:write name="rs" property="bz" />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<button type="button" class="button2"
										onclick="refreshForm('/xgxt/ticket_book_edit.do?act=save');Close();window.dialogArguments.document.getElementById('search_go').click();"
										id="buttonSave">
										�� ��
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="button2" onclick="Close();return false;" id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
		</html:form>
	</body>
</html>
