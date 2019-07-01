<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/data_search" method="post">
			<div class="tab_cur"  id="title_m">
			<p class="location">
				<em><span >��ѵ���� - ��ѵ���� - ��ѵ�Ŷӻ�</span></em>
			</p>
		</div>
			
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
				<input type="hidden" id="url" name="url" value="/sjcz/xsjxhjb.jsp" />
				<div class="tab">
				<table width="100%"  border="0" class="formlist">
					<thead>
						<tr>
							<td colspan="4">
								<span>��ѵ����Ϣά��</span>
							</td>
						</tr>
					</thead>
					<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
										
													<button type="button"
														onclick="if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh-hjsj-jxdm');"
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
					<tr>
						<th width="16%">
							<font color="red">*</font>ѧ�ţ�
						</th>
						<td width="34%">
							<html:text name='rs' property="xh" readonly="readonly"
								styleId="xh" onkeypress="autoFillStuInfo(event.keyCode,this)" />
							<logic:equal name="doType" value="add">
							<button type="button" onclick="showTopWin('/xgxt/stu_info.do',750,550);"
								class="btn_01" id="buttonFindStu" style="">
								ѡ��
							</button>
							</logic:equal>
						</td>
						<th>
							<font color="red">*</font>��ȣ�
						</th>
						<td>
							<html:select name="rs" property="nd" style="width:90px"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							������
						</th>
						<td>
							<html:text name='rs' property="xm" styleId="xm" />
						</td>
						<th>
							<font color="red">*</font>ѧ�꣺
						</th>
						<td>
							<html:select name="rs" property="xn" style="width:90px"
								styleId="xn">
								<html:options collection="xnList" property="xn"
									labelProperty="xn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							�Ա�
						</th>
						<td>
							<html:text name='rs' property="xb" styleId="xb" />
						</td>
						<th>
							<font color="red">*</font>ѧ�ڣ�
						</th>
						<td>
							<html:select name="rs" property="xq" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="xqList" property="xqdm"
									labelProperty="xqmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							�꼶��
						</th>
						<td>
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
						<th>
							<font color="red">*</font>��ʱ�䣺
						</th>
						<td>
							<html:text name='rs' property="hjsj" styleId="pxjssj"
								onblur="dateFormatChg(this)" style="cursor:hand;"
								onclick="return showCalendar('pxjssj','y-mm-dd');"  />
						</td>
					</tr>
					<tr>
						<th>
							<bean:message key="lable.xsgzyxpzxy" />��
						</th>
						<td >
							<html:text name='rs' property="xymc" styleId="xy" />
						</td>
						<th>
							<font color="red">*</font>���
						</th>
						<td>
							<html:select name="rs" property="jxdm" style="width:200px"
								styleId="jxdm">
								<html:option value=""></html:option>
								<html:options collection="jxList" property="jxdm"
									labelProperty="jxmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							רҵ��
						</th>
						<td>
							<html:text name='rs' property="zymc" styleId="zy" />
						</td>
						<td>
						</td>
						<td>
						</td>
					</tr>
					<tr>
						<th>
							�༶��
						</th>
						<td>
							<html:text name='rs' property="bjmc" styleId="bj" />
						</td>
						<td>
						</td>
						<td >
						</td>
					</tr>
					<tr align="left">
						<th>
							��ע��
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='bz' style="width:99%" rows='5' />
						</td>
					</tr>
				</table>
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
		</html:form>
	</body>
</html>
