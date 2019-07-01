<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>	
	</head>
	<body onload="checkWinType();dataManLoad();">
		<html:form action="/data_search" method="post">
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
				    alert("�������ѧ����Ч!");
				    </script>
				</logic:equal>
				<input type="hidden" id="doType" name="doType"
					value="<bean:write name="doType" scope="request"/>" />
				<input type="hidden" id="pkValue" name="pkValue"
					value="<bean:write name="pkValue" scope="request"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url"
					value="/sjcz/knsshhdxxb.jsp" />
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="<bean:write name="userOnLine" scope="session"/>" />

					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>�����Ϣά��</span>
								</th>
							</tr>
						</thead>
						<tbody>
					<tr>
						<td align="right">
							<font color="red">*</font>ѧ�ţ�
						</td>
						<td align="left">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="display:none">
								ѡ��
							</button>
						</td>
						<td align="right">
							������
						</td>
						<td align="left">
							<html:text name='rs' property="xm" styleId="xm" />
						</td>
					</tr>
					<tr>
						<td align="right">
							�Ա�
						</td>
						<td align="left">
							<html:text name='rs' property="xb" styleId="xb" />
						</td>
						<td align="right">
							�꼶��
						</td>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
					</tr>
					<tr>
						<td align="right">
							<bean:message key="lable.xsgzyxpzxy" />��
						</td>
						<td align="left">
							<html:text name='rs' property="xymc" styleId="xy" />
						</td>
						
							<td align="right">
								<logic:equal value="13742" name="xxdm" scope="session">
									�Ŷ����ʣ�
								</logic:equal>
								<logic:notEqual value="13742" name="xxdm" scope="session">
									������ʣ�
								</logic:notEqual>
							</td>
							<td align="left">
								<html:select name="rs" property="hdxz" 
									styleId="hdxz">
									<html:option value=""></html:option>
									<html:option value="0">����</html:option>
									<html:option value="1">����������</html:option>
								</html:select>
							</td>
						
					</tr>
					<tr>
						<td align="right">
							רҵ��
						</td>
						<td align="left">
							<html:text name='rs' property="zymc" styleId="zy" />
						</td>
						<td align="right">
							ʱ�䣺
						</td>
						<td align="left">
							<html:text name='rs' property="sj" styleId="sj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('sj','y-mm-dd');" />
						</td>
					</tr>
					<tr>
						
						<td align="right">
							�༶��
						</td>
						<td align="left">
							<html:text name='rs' property="bjmc" styleId="bj" />
						</td>
						<td align="right">
							ʵ��������
						</td>
						<td align="left">
							<html:text property="xjts" name="rs" onkeyup="value=value.replace(/[^\d]/g,'')"/>
						</td>
					</tr>
					<tr>
						<td align="right">
							����������
						</td>
						<td>
							<html:text name="rs" property="shry"/>
						</td>
						<logic:present name="showhzy">
							<td align="right">
								���˽����
							</td>
							<td align="left">
								<html:text name='rs' property="khjg" styleId="kh" />
							</td>
						</logic:present>
						<logic:notPresent name="showhzy">
							<td></td>
							<td></td>
						</logic:notPresent>
					</tr>
					
					<tr align="left">
						<td align="right">
							�����Ŀ��
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='hdxm' style="width:99%"
								rows='5' />
						</td>
					</tr>
					<tr align="left">
						<td align="right">
							�������:
						</td>
						<td colspan="3">
							<html:textarea name='rs' property='hdnr' style="width:99%"
								rows='5' />
						</td>
					</tr>
					</tbody>
				<tfoot>
					<tr>
						<td colspan="4">
							<div class="bz">
								"<span class="red">*</span>"Ϊ������
							</div>
							<div class="btn">
								<button type="button" onclick="dataCanModi(true)" id="buttonModi">
									�� ��
								</button>
								<button type="button" onclick="dataDoSave('xh-sj')" id="buttonSave">
									�� ��
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button type="button" onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
			</div>
			</logic:notEmpty>
			<logic:present name="isSuccess">
				<logic:equal value="true" name="isSuccess">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="isSuccess">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
