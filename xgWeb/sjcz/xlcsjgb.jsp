<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		 <script language="javascript">
				function checkzf(type){
					var bz = document.getElementById("bz").value;
					if(bz.length>125){
						alert("��ע���ܳ���125�����֣���");
						return false;
					}
				if(type == "modify"){
					dataCanModi(true);
				}
				if(type == "save"){
					dataDoSave('xh');
				}
				}
		</script>
	</head>

	<body onload="checkWinType();">
		<html:form action="/data_search" method="post">
			<logic:equal value="true" name="isSuccess">
				<script>
					alert('����ɹ�!');
					if (window.dialogArguments) {
						window.close();
						window.dialogArguments.document.getElementById('search_go').click();
					}
				</script>
			</logic:equal>
		
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
				<input type="hidden" id="readonlyEle" name="readonlyEle"
					value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/xlcsjgb.jsp" />
				
				<div class="tab">
					<table width="100%" border="0" class="formlist">
						<thead>
							<tr>
								<th colspan="4">
									<span>������Խ��ά��</span>
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
											<button type="button" class="" onclick="checkzf('save');" id="buttonSave">
										����
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button" class="" onclick="Close();return false;" 
										id="buttonClose">
										�ر�
									</button>
									</div>
								</td>
							</tr>
						</tfoot>
					<tbody>
						<tr>
							<th>
								<font color="red">*</font>ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xh" readonly="readonly"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
								<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									ѡ��
								</button>
							</td>
							<th>
								����
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" />
							</td>
							<logic:equal value="10290" name="xxdm">
							<th>
								<font color="red">*</font>������Ŀ
							</th>
							<td align="left">
								<html:text property="csxmdm" value="�й���ѧ������������" style="width:150px" readonly="true"></html:text>
							</td>
							</logic:equal>
							<logic:notEqual value="10290" name="xxdm">
							<th>
								<font color="red">*</font>������Ŀ
							</th>
							<td align="left">
								<html:select name="rs" property="csxmdm" style="width:90px"
									styleId="csxmdm" onchange="chgCsxmdm()">
									<html:option value=""></html:option>
									<html:options collection="csxmList" property="xlcsxmdm"
										labelProperty="xlcsxmmc" />
								</html:select>
							</td>
							</logic:notEqual>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" />
							</td>
							<logic:equal value="10290" name="xxdm">
							<th>
								���Խ��
							</th>
							<td align="left">
								<html:select name="rs" property="csjg" style="width:90px"
									styleId="csjg">
									<html:option value="0001">����</html:option>
									<html:option value="0002">����</html:option>
								</html:select>
							</td>
							</logic:equal>
							<logic:notEqual value="10290" name="xxdm">
							<th>
								���Խ��
							</th>
							<td align="left">
								<html:select name="rs" property="csjg" style="width:90px"
									styleId="csjg">
									<html:option value=""></html:option>
									<logic:notEmpty name="csjgList">
										<html:options collection="csjgList" property="xlcsjgdm"
											labelProperty="xlcsjgmc" />
									</logic:notEmpty>
								</html:select>
							</td>
							</logic:notEqual>
						</tr>
						<logic:equal value="10290" name="xxdm">
							<tr>
								<th>
									ѧ��
								</th>
								<td align="left">
									<logic:notEqual value="xshsxfb" name="realTable">
										<html:select name="rs" property="xn" style="width:90px"
											styleId="xn" onchange="">
											<html:options collection="xnList" property="xn"
												labelProperty="xn" />
										</html:select>
									</logic:notEqual>
								</td>
								<logic:notEqual value="10290" name="xxdm">
								<th>
									ѧ��
								</th>
								<td align="left">
									<logic:notEqual value="xshsxfb" name="realTable">
										<html:select name="rs" property="xq" style="width:60px"
											styleId="xq">
											<html:option value=""></html:option>
											<html:options collection="xqList" property="xqdm"
												labelProperty="xqmc" />
										</html:select>
									</logic:notEqual>
								</td>
								</logic:notEqual>
							<logic:equal value="10290" name="xxdm">
							<th>
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							</logic:equal>
							</tr>
						</logic:equal>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" />
							</td>
							<th>
								<logic:notEqual value="10290" name="xxdm">
									<font color="red">*</font>����ʱ��
									</logic:notEqual>
								<logic:equal value="10290" name="xxdm">
									<font color="red"></font>����ʱ��
									</logic:equal>
							</th>
							<td align="left">
								<logic:notEqual value="10290" name="xxdm">
									<html:text name='rs' property="cssj" styleId="cssj"
										onblur="dateFormatChg(this)" style="cursor:hand;"
										onclick="return showCalendar('cssj','y-mm-dd');" />
								</logic:notEqual>
								<logic:equal value="10290" name="xxdm">

									<logic:empty name="rs" property="cssj">
										<html:hidden property="cssj" value="${csnj}" />
									</logic:empty>
									<logic:notEmpty name="rs" property="cssj">
										<html:hidden name='rs' property="cssj" styleId="cssj"
											onblur="dateFormatChg(this)" style="cursor:hand;"
											onclick="return showCalendar('cssj','y-mm-dd');" />
									</logic:notEmpty>
									<html:hidden name='rs' property="cssj" styleId="cssj"
										onblur="dateFormatChg(this)" style="cursor:hand;" />
									<html:select property="csnj" name="rs">
										<html:option value=""></html:option>
										<html:option value="һ�꼶"></html:option>
										<html:option value="���꼶"></html:option>
										<html:option value="���꼶"></html:option>
										<html:option value="���꼶"></html:option>
										<html:option value="���꼶"></html:option>
										<html:option value="���꼶"></html:option>
									</html:select>
								</logic:equal>
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" />
							</td>
							<logic:notEqual value="10290" name="xxdm">
							<th>
								���ͱ��
							</th>
							<td align="left">
								<html:text name='rs' property="fsbj" styleId="fsbj" maxlength="15"/>
							</td>
							</logic:notEqual>
						</tr>
						<tr>
						
							<th>
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" />
							</td>
							<logic:equal value="10290" name="xxdm" scope="session">
								<th>
									��������
								</th>
								<td align="left">
									<html:text name="rs" property="sylb" maxlength="12">
									</html:text>
								</td>
							</logic:equal>
							<logic:notEqual value="10290" name="xxdm">
								<td></td>
								<td></td>
							</logic:notEqual>
						</tr>
						<tr align="left">
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
			</logic:notEmpty>
		</html:form>
	</body>
</html>
