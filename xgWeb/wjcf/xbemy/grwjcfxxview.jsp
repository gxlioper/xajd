<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<link rel="icon" href="images/icon.ico" type="image/x-icon" />
	<link id="csss"  />
	<base target="_self">
	<link id="csssDate" rel="stylesheet" rev="stylesheet"
		href="js/calendar.css" type="text/css" media="all" />
	<script language="javascript">
	function cfqr(obj) {
				if (confirm('ȷ��Ҫ�Ըô�����Ϣ����ȷ����,ȷ�Ϻ��ܽ��г�������������ϸ�˶ԣ�')) {
					var pk = $("pkValue").value;
					refreshForm("grwjcfxxview.do?act=save&pk="+pk);
				}
			}
</script>
	<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
%>
	<body>
		<script language="javascript" src="js/function.js"></script>
		<script language="javascript" src="js/wjcfFuction.js"></script>
		<script type="text/javascript" src="js/calendar.js"></script>
		<script type="text/javascript" src="js/calendar-zh.js"></script>
		<script type="text/javascript" src="js/calendar-setup.js"></script>
		<html:form action="/grwjcfxxview.do" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Υ�ʹ��� - ����ά�� - ����Υ����Ϣ</a>
			</p>
		</div>
				<input type="hidden" name="pkValue" id="pkValue" value="${pkValue }"/>
			
			      	<table border="0" id="rsTable" width="100%" class="formlist">
<%--			      			<div class="tab">--%>
<%--				<table width="100%" border="0" class="formlist">--%>
<%--					<thead>--%>
<%--						<tr>--%>
<%--							<th colspan="4">--%>
<%--								<span>���־�����򸽼�</span>--%>
<%--							</th>--%>
<%--						</tr>--%>
<%--					</thead>--%>
<%--					<tbody>--%>
<%--						<tr style="23px" onmouseover="rowOnClick(this)">--%>
<%--							<logic:empty name="fjList">--%>
<%--								<td colspan="5" align="center">���޾�����򸽼�</td>--%>
<%--							</logic:empty>--%>
<%--							<logic:notEmpty name="fjList">--%>
<%--								<logic:iterate id="s" name="fjList">--%>
<%--									<td align="center"><a href="downloadfilewj.do?len=&wjsclj=${s.fjsclj }" target=_black>����</a> </td>--%>
<%--									<td align="center">${s.cfwh }</td>--%>
<%--									<td align="center">${s.cflbmc }</td>--%>
<%--									<td align="center">${s.cfyymc }</td>--%>
<%--									<td align="center">${s.cfsj }</td>--%>
<%--								</logic:iterate>--%>
<%--							</logic:notEmpty>--%>
<%--						</tr>--%>
<%--						</tbody>--%>
<%--			      	</table>--%>
<%--			   </div>--%>
			     
			     
		<div class="tab">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="4">
								<span>����Υ����Ϣ</span>
							</th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<td colspan="4">
								<div class="btn">
									<logic:equal name="userType" value="stu">
										<logic:equal name="rs" property="xsqr" value="δȷ��">
										<button type="button" class="" onclick="cfqr()" 
											id="buttonClose">
											ѧ��ȷ��
										</button>
										</logic:equal>
									</logic:equal>
									<button type="button" class="" onclick="Close();return false;" 
										id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
				<tr style="height:22px">
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						${rs.xh}
					</td>
					<th align="right">
						����
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xm"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�Ա�
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xb"/></logic:present>
					</td>
					<th align="right">
						�꼶
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="nj"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xn"/></logic:present>
					</td>
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xq"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />����
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xymc"/></logic:present>
					</td>
					<th align="right">
						רҵ����
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="zymc"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�༶����
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="bjmc"/></logic:present>
					</td>
					<th align="right">
						Υ��ʱ��
					</th>
					<td align="left">
						${rs.wjsj }
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�����ĺ�
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="cfwh"/></logic:present>
					</td>
					<th align="right">
						����ʱ��
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="cfrq"/></logic:present>
					</td>
				</tr>
				<tr style="height:22px">
					<th align="right">
						�������
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="cflbmc"/></logic:present>
					</td>
					<th align="right">
						����ԭ��
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="cfyymc"/></logic:present>
					</td>
				</tr>
				<tr>
					<th align="right">
						Υ��<logic:equal value="10290" name="xxdm">��ʵ</logic:equal><logic:notEqual value="10290" name="xxdm">����</logic:notEqual>
					</th>
					<td align="left" colspan="3">
						<div style="word-break:break-all;width:95%">
							<logic:present name="rs"><bean:write name="rs" property="bz"/></logic:present>
						</div>
					</td>
				</tr>
				<!-- ���ݴ�ѧ��ѧ������ȷ��ʱ�� -->
				<logic:equal value="11078" name="xxdm">
				
				
				<tr style="height:22px">
					<th align="right">
						ȷ��ʱ��
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="qrsj"/></logic:present>
					</td>
					<th align="right">
						ѧ�������Ƿ�ȷ��
					</th>
					<td align="left">
						<logic:present name="rs"><bean:write name="rs" property="xsqr"/></logic:present>
					</td>
				</tr>
		</logic:equal>
			</table>
			</div>
	</body>
		<logic:equal value="true" name="result">
				<script>
					alert("�����ɹ�!");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
			<logic:equal value="false" name="result">
				<script>
					alert("����ʧ��!");
					Close();
					window.dialogArguments.document.getElementById('search_go').click();
				</script>
			</logic:equal>
	</html:form>
</html>
