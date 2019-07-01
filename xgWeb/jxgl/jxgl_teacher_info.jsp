<%@ page language="java" contentType="text/html; charset=GBK"%>

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
<html xmlns="http://www.w3.org/1999/xhtml" lang="gb2312">
	<head>
		<title><bean:message key="lable.title" /></title>
		<!-- ͷ�ļ� -->
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<%@ include file="/syscommon/pagehead_xg.ini"%>
		<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
		<meta http-equiv="Pragma" http-equiv="no-cache" />
		<meta http-equiv="Cache-Control" http-equiv="no-cache" />
		<meta http-equiv="Expires" http-equiv="0" />
		<meta name="Copyright" content="������� zfsoft" />
	</head>
	<%
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
	%>

	<body onload="xyDisabled('xy');initPage();">
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<script type="text/javascript" src="js/jxglFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>
		<script language="javascript" src="js/AjaxFunction.js"></script>
		<html:form action="/ArmyCalendarArrange" method="post">
			<div class="tab_cur">
	          <p class="location">
	          	<em>��ǰ����λ��:</em>
		          <a>
		          	��ѵ���� - ��Ϣά�� - ������ʦ��Ϣ
		          </a>
	          </p>
	        </div>
	        <div class="toolbox">
				<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<logic:equal value="yes" name="writeAble" scope="request">
							<li>
								<a href="#" onclick="showDataFrame('viewTeacherInfo.do','add',600,400);return false;" class="btn_zj">����</a>
							</li>
							<li>
								<a href="#" onclick="showDataFrame('viewTeacherInfo.do','modi',600,400);return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="#" onclick="showDataFrame('viewTeacherInfo.do','del',600,400);return false;" class="btn_sc">ɾ��</a>
							</li>
							</logic:equal>
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">��������</a>
							</li>
							<li>
								<a href="#" onclick="dataExport();return false;" class="btn_dc">��������</a>
							</li>
						</ul>
					</div>
				</div>
				<!-- �������� -->
				<div class="searchtab">
					<input type="hidden" name="xyV" id="xyV" />
					<input type="hidden" id="tableName" name="tableName"
						value="<bean:write name="tableName" scope="request"/>" />
					<input type="hidden" id="realTable" name="realTable"
						value="<bean:write name="realTable" scope="request"/>" />
					<input type="hidden" id="userType" name="userType"
						value="<bean:write name="userType" scope="request"/>" />
						
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
									<input type="hidden" name="go" value="a" />
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/fugleTeacherInfo.do');">
											�� ѯ
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th style="width:10%">
									����<bean:message key="lable.xsgzyxpzxy" />��
								
								</th>
								<td style="width:20%">
									<html:select property="xydm" styleId="xy" style="width:200px">
									</html:select>
								</td>
								<th style="width:10%">
									������
								</th>
								<td style="width:20%">
									<html:text property="xm" style="width:120px" maxlength="20" styleId="xm"></html:text>
								</td>
								<td colspan="2" style="width:40%">
								</td>
							</tr>
						</tbody>
					</table>
				<div class="formbox">
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">δ�ҵ��κμ�¼��</font>
						</logic:empty> <logic:notEmpty name="rs">
							<font color="blue">��ʾ��������ͷ��������;˫��һ����¼���Խ����޸�ҳ�档</font>
							
						</logic:notEmpty> </span>
				</h3>
				<logic:notEmpty name="rs">
					<table summary="" id="rsTable" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
									<logic:iterate id="tit" name="topTr" offset="1">
										<td id="<bean:write name="tit" property="en"/>"
											onclick="tableSort(this)" nowrap>
											<bean:write name="tit" property="cn" />
										</td>
									</logic:iterate>
							</tr>
						</thead>
						<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand"
									ondblclick="showDataFrame('viewTeacherInfo.do','modi',550,350)">
									<td>
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="<bean:write name="v"/>" />
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="1">
											<bean:write name="v" />
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="2">
										<td nowrap>
											<bean:write name="v" />
										</td>
									</logic:iterate>
								</tr>
						</logic:iterate>
					</table>
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=jxglForm"></jsp:include>
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:equal value="yes" name="result">
			<script>
				alert("�����ɹ�!");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<logic:equal value="no" name="result">
			<script>
				alert("����ʧ��!");
				document.getElementById("search_go").click();
			</script>
		</logic:equal>
		<script type="text/javascript" src="js/bottomButton.js"></script>
	</body>
</html>