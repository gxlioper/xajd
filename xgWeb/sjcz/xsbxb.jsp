<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="/xgxt/dwr/interface/getInsureInfo.js"></script>
	</head>
	<body onload="checkWinType();">
		
		<html:form action="/data_search" method="post">
			<div class="title">
				<div class="title_img" id="title_m">
					<span id="tipFollow"></span>
				</div>
			</div>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">

				</p>
			</logic:empty>
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
					value="xm-xb-xy-nj-zy-bj-xz-sfzh-sjhm" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc-sfzh-sjhm-xz" />
				<input type="hidden" id="url" name="url" value="/sjcz/xsbxb.jsp" />
				<input type="hidden" id="tabName" name="tabName" value="xsbxb" />
				<input type="hidden" id="isSchool" name="isSchool" value="" />
				<input type="hidden" id="sqsj" name="sqsj" value="${rs.sqsj }" />
				
				
				<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>ѧ��������Ϣά��</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<logic:notEqual value="view" name="doType">
									<button type="button" class="button2" onclick="dataDoSave('xh-nd-tbxzdm-tbsj-bxnx-tbgsdm')"
										 id="buttonSave">
										�� ��
									</button>
									</logic:notEqual>
									<button type="button" class="button2" onclick="Close();return false;" 
										id="buttonClose">
										�� ��
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
								<logic:equal value="view" name="doType">
									${rs.xh }
								</logic:equal>
								<logic:notEqual value="view" name="doType">
								<html:text name='rs' property="xh"
									styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this);" />
									<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
									class="btn_01" id="buttonFindStu" >
									ѡ��
								</button>
								</logic:notEqual>
							</td>
							<th>
								����ƾ֤��
							</th>
							<td align="left">
								<html:text name='rs' property="bxpzh" styleId="bxpzh" />
							</td>
						</tr>
						<tr>
							<th>
								����
							</th>
							<td align="left">
								<html:text name='rs' property="xm" styleId="xm" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>���
							</th>
							<td align="left">
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="nd" style="width:90px"
										styleId="nd" onchange="getInsureInfoExist();">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden name="rs" property="nd"/>
									<html:select name="rs" property="nd" style="width:90px" disabled="true"
										styleId="nd" onchange="getInsureInfoExist();">
										<html:options collection="xnList" property="nd"
											labelProperty="nd" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								�Ա�
							</th>
							<td align="left">
								<html:text name='rs' property="xb" styleId="xb" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>���չ�˾
							</th>
							<td align="left">
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="tbgsdm" style="width:150px" 
										styleId="tbgsdm" onchange="refreshForm('newStuInsureApply.do?page=xsbxb');">
										<html:option value=""></html:option>
										<html:options collection="tbgsdmList" property="bxgsdm"
											labelProperty="bxgsmc" />
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden name="rs" property="tbgsdm"/>
									<html:select name="rs" property="tbgsdm" style="width:150px" disabled="true"
										styleId="tbgsdm" onchange="refreshForm('newStuInsureApply.do?page=xsbxb');">
										<html:option value=""></html:option>
										<html:options collection="tbgsdmList" property="bxgsdm"
											labelProperty="bxgsmc" />
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								�꼶
							</th>
							<td align="left">
								<html:text name='rs' property="nj" styleId="nj" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>Ͷ������
							</th>
							<td align="left">
								<logic:equal name="doType" value="add">
									<html:select name="rs" property="tbxzdm" style="width:150px" styleId="tbxzdm"
										onchange="refreshForm('newStuInsureApply.do?page=xsbxb')">
										<html:option value=""></html:option>
										<logic:notEmpty name="tbxzdmList">
											<html:options collection="tbxzdmList" property="bxxzdm"
												labelProperty="bxxzmc" />
										</logic:notEmpty>
									</html:select>
								</logic:equal>
								<logic:notEqual name="doType" value="add">
									<html:hidden name="rs" property="tbxzdm"/>
									<html:select name="rs" property="tbxzdm" style="width:150px" styleId="tbxzdm" disabled="true"
										onchange="refreshForm('newStuInsureApply.do?page=xsbxb')">
										<html:option value=""></html:option>
										<logic:notEmpty name="tbxzdmList">
											<html:options collection="tbxzdmList" property="bxxzdm"
												labelProperty="bxxzmc" />
										</logic:notEmpty>
									</html:select>
								</logic:notEqual>
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td align="left">
								<html:text name='rs' property="xymc" styleId="xy" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>Ͷ������
							</th>
							<td align="left">
								<html:text name='rs' property="tbsj" styleId="tbsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tbsj','y-mm-dd');" />
							</td>
						</tr>
						<tr>
							<th>
								רҵ
							</th>
							<td align="left">
								<html:text name='rs' property="zymc" styleId="zy" readonly="true"/>
							</td>
							<th>
								<font color="red">*</font>��������
							</th>
							<td align="left">
							<logic:notPresent name="showGdnz">
								<html:text name="rs" property="bxnx" styleId="bxnx"/>(��)
							</logic:notPresent>
							<logic:present name="showGdnz">
								<html:text name="rs" property="bxnx" styleId="bxnx" onchange=""/>(��)
							</logic:present>
							</td>	
						</tr>
						<tr>
							<th>
								�༶
							</th>
							<td align="left">
								<html:text name='rs' property="bjmc" styleId="bj" readonly="true"/>
							</td>
							<th>
								����
							</th>
							<td align="left">
							<logic:notPresent name="showGdnz">
								<html:text name='rs' property="bf" styleId="bf" style="width:90px" />(Ԫ)
							</logic:notPresent>
							<logic:present name="showGdnz">
								<html:text name='rs' property="bf" styleId="bf" style="width:90px" />(Ԫ)
							</logic:present>
							</td>
						</tr>
						<tr>
							<th>
								ѧ��
							</th>
							<td align="left">
								<html:text name='rs' property="xz" styleId="xz" readonly="true"/>
							</td>
						    <th>
								�˱�����
							</th>
							<td align="left">
								<html:text name='rs' property="tuibsj" styleId="tuibsj"
									onblur="dateFormatChg(this)" style="cursor:hand;"
									onclick="return showCalendar('tuibsj','y-mm-dd');" />
							</td>	
						</tr>
						<tr>
							<th>
								���֤��
							</th>
							<td align="left">
								<html:text name='rs' property="sfzh" styleId="sfzh" readonly="true"/>
							</td>
							<th>
								�˱����
							</th>
							<td align="left">
								<html:select name="rs" property="tbbj" style="width:90px"
									styleId="tbbj">
									<html:option value=""></html:option>
									<html:option value="δ��">δ��</html:option>
									<html:option value="����">����</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>
								�ֻ�����
							</th>
							<td align="left">
								<html:text name='rs' property="sjhm" styleId="sjhm" readonly="true"/>
							</td>
							 <th>
								�ɷѱ��
							</th>
							<td align="left">
								<html:select name="rs" property="jfbj" style="width:90px" styleId="jfbj">
								    <html:option value="��">��</html:option>
								    <html:option value="��">��</html:option>
								</html:select>
							</td>
						</tr>
						<logic:present name="showGdnz">
						<tr>	
							<th>
							<logic:present name="showGdnz">
							���յ���
							</logic:present>
							</th>							
							<td align="left">
							<html:select name="rs" property="bxdc" onchange="">
								<html:option value="">---��ѡ��---</html:option>
								<html:options collection="bxdcList" property="dcdm" labelProperty="dcmc"/>
							</html:select>
							
							</td>
							<td></td>
							<td></td>
						</tr>
						</logic:present>
						<tr align="left">
							<th>
								��ע
							</th>
							<td colspan="3" style="word-break:break-all;">
								<html:textarea name='rs' property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
						</tbody>
					</table>
					</div>
			</logic:notEmpty>
			<logic:present name="result">
				<logic:equal value="true" name="result">
					<script>
						alert('�����ɹ���');
						dialogArgumentsQueryChick();
						window.close();
					</script>
				</logic:equal>
				<logic:equal value="false" name="result">
					<script>
						alert('����ʧ�ܣ�');
					</script>
				</logic:equal>
			</logic:present>
		</html:form>
	</body>
</html>
