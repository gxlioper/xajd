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
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="<bean:write name="userOnLine" scope="session"/>" />
				<input type="hidden" id="disableEle" name="disableEle"
					value="xm-xb-xy-nj-zy-bj" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/modiData.do?realTable=zyzfwdjb&doType=add&tableName=view_zyzfwdj&pk=xh" />
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>־Ը�߷�����Ϣά��</span>
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
								����־Ը����ʱ�䣺
							</td>
							<td align="left">
							<html:text name='rs' property="cjzyzfwsj"   readonly="true"
									styleId="cjzyzfwsj" 
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('cjzyzfwsj','y-mm-dd');"
									/>
							</td>
						</tr>
						<tr>
							<td align="right">
								������
							</td>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
							<td align="right">
								<font color="red">*</font>��ȣ�
							</td>
							<td align="left">
								<html:select name="rs" property="nd" style="width:90px"
									styleId="nd">
									<html:options collection="xnList" property="nd"
										labelProperty="nd" />
								</html:select>
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
								<font color="red">*</font>ѧ�꣺
							</td>
							<td align="left">
								<html:select name="rs" property="xn" style="width:90px"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
							</td>
							
						</tr>
						<tr>
							<td align="right">
								�꼶��
							</td>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<td align="right">
								ѧ�ڣ�
							</td>
							<td align="left">
								<html:select name="rs" property="xq" style="width:90px"
									styleId="xq">
									<html:option value=""></html:option>
									<html:options collection="xqList" property="xqdm"
										labelProperty="xqmc" />
								</html:select>
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
							<font color="red">*</font>־Ը�߱�ţ�</td>
							<td align="left"><html:text name="rs" property="drzw" styleId="drzw" maxlength="10"/>
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
							<font color="red">*</font>����ʱ�䣺</td>
							<td align="left">
								<html:text name='rs' property="fwsj" styleId="fwsj" readonly="readonly"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('fwsj','y-mm-dd');" />
							
								
							</td>
						
						</tr>
						<tr>
							<td align="right">
								�༶��
							</td>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
								<td align="right">������:</td>
							<td align="left"><html:text name='rs' property="fwl" styleId="fwl" maxlength="5" 
							 onkeyup="value=value.replace(/[^\d]/g,'')" />
								(Сʱ)</td>
							
						</tr>
						<logic:present name="showhzy">
							<tr>
								<td align="right">
									���˽����
								</td>
								<td>
									<html:text name="rs" property="khjg" styleId="kh" />
								</td>
								<td align="right">����ְ��</td>
							<td align="left"><html:text name="rs" property="zyzbh" styleId="drzw" maxlength="20"/></td>
							</tr>
						</logic:present>
						<tr align="left">
							<td align="right">
								�������ݣ�
							</td>
							<td colspan="3">
								<html:textarea name='rs' property='fwnr' style="width:99%"
									rows='5' onblur="chLeng(this,60)"/>
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
								<button type="button" name="�޸�" onclick="dataCanModi(true)" id="buttonModi">
									�� ��
								</button>
								<button type="button" name="����"onclick="if(checkXnNd('xn','nd'))dataDoSave('xh-fwsj');"
									id="buttonSave">
									�� ��
								</button>
								<button type="button" name="�ر�" onclick="Close();return false;" id="buttonClose">
									�� ��
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
				</table>
			</div>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
