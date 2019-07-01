<%@ page language="java" contentType="text/html; charset=GBK"%>

		<%@ include file="/syscommon/pagehead_V4.ini"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-bean"
	prefix="bean"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-html"
	prefix="html"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-logic"
	prefix="logic"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-tiles"
	prefix="tiles"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-template"
	prefix="template"%>
<%@ taglib uri="http://jakarta.apache.org/struts/tags-nested"
	prefix="nested"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title><bean:message key="lable.title" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control"
			http-equiv="no-cache, must-revalidate" />
		<meta http-equiv="Expires" http-equiv="0" />

		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss" rel="stylesheet" rev="stylesheet" href="style/main.css"
		type="text/css" media="all" />
	<base target="_self">
	<script language="javascript">
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body onload="checkWinType();dataManLoad();">
		<script language="javascript" src="js/function.js"></script>
		<html:form action="/data_search" method="post">
		    <div class="tab_cur">
			<p class="location">
				<em><span id="tipFollow"></span></em>
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
				<input type="hidden" id="userOnLine" name="userOnLine"
					value="<bean:write name="userOnLine" scope="session"/>" />
				<input type="hidden" id="readonlyEle" name="readonlyEle" value="" />
				<input type="hidden" id="getStuInfo" name="getStuInfo"
					value="xm-xb-xymc-nj-zymc-bjmc" />
				<input type="hidden" id="url" name="url" value="/sjcz/jszhkpb.jsp" />
				<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								��ʵ�ۺϿ�����Ϣά��
							</th>
						</tr>
					</thead>
				<tfoot>
								<tr>
									<td colspan="4">
										<div class="btn">
												<button type="button" onclick="dataCanModi(true)"
													id="buttonModi">
													�� ��
											</button>
													<button type="button"
														onclick="if(checkXnNd('xn','nd'))dataDoSave('xn-xq-xh');"
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
							<font color="red">*</font>ѧ��
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
						<th width="16%">
							<font color="red">*</font>���
						</th>
						<td width="34%">
							<html:select name="rs" property="nd" style="width:90px"
								styleId="nd">
								<html:options collection="xnList" property="nd"
									labelProperty="nd" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							����
						</th>
						<td>
							<html:text name='rs' property="xm" styleId="xm" />
						</td>
						<th>
							<font color="red">*</font>ѧ��
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
							<font color="red">*</font>ѧ��
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
							�꼶
						</th>
						<td align="left">
							<html:text name='rs' property="nj" styleId="nj" />
						</td>
						<th>
							˼��Ʒ������
						</th>
						<td>
							<html:select name="rs" property="sxpdpj" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="scoreList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						 <th>
							<bean:message key="lable.xsgzyxpzxy" />��
						</th>
						<td>
							<html:text name='rs' property="xymc" styleId="xy" />
						</td>
						<th>
							֪ʶˮƽ����
						</th>
						<td>
							<html:select name="rs" property="zssppj" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="scoreList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							רҵ
						</th>
						<td>
							<html:text name='rs' property="zymc" styleId="zy" />
						</td>
						<th>
							ѧҵ��������
						</th>
						<td>
							<html:select name="rs" property="xynlpj" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="scoreList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>
							�༶
						</th>
						<td>
							<html:text name='rs' property="bjmc" styleId="bj" />
						</td>
						<th>
							��������
						</th>
						<td>
							<html:select name="rs" property="nlpj" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="scoreList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td >
						</td>
						<td>
						</td>
						<th>
							��������
						</th>
						<td>
							<html:select name="rs" property="szpj" style="width:90px"
								styleId="xq">
								<html:option value=""></html:option>
								<html:options collection="scoreList" property="en"
									labelProperty="cn" />
							</html:select>
						</td>
					</tr>
					<tr>
						<td>
						</td>
						<td>
						</td>
						<th>
							ѧ��
						</th>
						<td>
							<html:text name='rs' property="xf" styleId="pxjssj"
								onkeypress="return numInputValue(this,4,event)" maxlength="4" />
						</td>
					</tr>
					<tr align="left">
						<th>
							��ע
						</th>
						<td colspan="3">
							<html:textarea name='rs' property='bz' style="width:99%" rows='5' />
						</td>
					</tr>
				</table>
				
			</logic:notEmpty>
			<jsp:include flush="true" page="/sjcz/include/modiPageJudge.jsp"></jsp:include>
			<logic:equal value="true" name="isSuccess">
			  <script type="text/javascript">
			    alert('�����ɹ���');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>	
			<logic:equal value="false" name="isSuccess">
			  <script type="text/javascript">
			    alert('����ʧ�ܣ�');
			    Close();
	         	window.dialogArguments.document.getElementById('search_go').click();
			  </script>
			</logic:equal>
		</html:form>
	</body>
</html>
