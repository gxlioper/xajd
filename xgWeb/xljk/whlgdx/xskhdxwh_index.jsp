<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/jsFunction.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/GetListData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getXljkSjm.js'></script>
		<script language="javascript" src="js/xljkFunction.js"></script>	
	</head>
	<body onload="initPage();initBjList()">
		<html:form action="/xljk_whlgdx_xskhdxwh" method="post">
			<input type="hidden" name="xyV" id="xyV"
				value="<bean:write name="rs" property="xydm"/>" />
			<input type="hidden" name="zyV" id="zyV"
				value="<bean:write name="rs" property="zydm"/>" />
			<input type="hidden" name="bjV" id="bjV"
				value="<bean:write name="rs" property="bjdm"/>" />
			<input type="hidden" name="tableName" id="tableName"
				value="VIEW_XLJK_WHLGDX_KHXX" />
			<input type="hidden" name="realTable" id="realTable"
				value="xjlk_whlgdx_khxx" />		
			
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>������ - ����ά�� - ��������ѧ����Ϣ</a>
				</p>
			</div>
			
			<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_zj" onclick="showTopWin('/xgxt/xljk_whlgdx_xskhdxwh.do?doType=add',650,500)">����</a></li>
						<li><a href="#" class="btn_xg" onclick="stu_modify()">�޸�</a></li>
						<li><a href="#" class="btn_sc" onclick="stu_delete()">ɾ��</a></li>
						<li><a href="#" class="btn_qb" onclick="stu_deleteAll()">ȫ��ɾ��</a></li>
						<li><a href="#" class="btn_dr" onclick="impAndChkData()">����</a></li>
						<li><a href="#" class="btn_dc" onclick="dataExport()">����</a></li>								
					</ul>
				</div>
			</div>
			
			<div class="searchtab">		
				<table width="100%" class="" border="0" id="tableElem">
					<tbody>
						<tr>
							<th><bean:message key="lable.xsgzyxpzxy" /></th>
							<td><html:select property="xydm" style="width:150px" styleId="xy"
									onchange="setZyBjVNull();initZyList();initBjList()">
								</html:select></td>
							<th>רҵ</th>
							<td><html:select property="zydm" style="width:150px" styleId="zy"
									onchange="initBjList()">
								</html:select></td>
							<th>�༶</th>
							<td><html:select property="bjdm" style="width:150px" styleId="bj">
								</html:select>
							</td>
						</tr>
						<tr>
							<th>ѧ��</th>
							<td><html:text property="xh" styleId="xh" style="width:150px" /></td>
							<th>����</th>
							<td><html:text property="xm" styleId="xm" style="width:150px" />
							</td>
							<th></th>
							<td></td>
						</tr>
					</tbody>
					<tfoot>
					<tr>
						<td colspan="6">
						<div class="btn">
						<button id="search_go" name="go" onclick="refreshForm('/xgxt/xljk_whlgdx_xskhdxwh.do?doType=search')">
							��ѯ
						</button>
						 <button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
							����
						 </button>
						</div>
						</td>
					</tr>
				</tfoot>
				</table>
			</div>
			<span id='norecordSpan'></span>
			
			
			<div class="formbox">
				<logic:empty name="list">
				    <h3 class="datetitle_01">
				    <span>
				    	��ѯ���&nbsp;&nbsp;
							<font color="red">δ�ҵ��κμ�¼��</font> 
				    </span>
				    </h3>
				 </logic:empty>
				<logic:notEmpty name="list">
					<h3 class="datetitle_01">
						<span>
							��ѯ���&nbsp;&nbsp;<font color="blue">˫��һ�п���ѡ����������ͷ��������</font> 
						</span>
					</h3>
					<table width="100%" class="dateline" id="rsTable">
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
						<logic:iterate name="list" id="s">
							<tr onclick="rowOnClick(this)" style="cursor:hand"
								ondblclick="xskhdxwh_view()">
								<td>
									<input type="hidden"
										value="<bean:write name="s" property="xn_id"/>" name="xn_id" />
									<bean:write name="s" property="xh" />
								</td>
								<td>
									<bean:write name="s" property="nj" />
								</td>
								<td>
									<bean:write name="s" property="xm" />
								</td>
								<td>
									<bean:write name="s" property="xb" />
								</td>
								<td>
									<bean:write name="s" property="xymc" />
								</td>
								<td>
									<bean:write name="s" property="zymc" />
								</td>
								<td>
									<bean:write name="s" property="bjmc" />
								</td>
							</tr>
						</logic:iterate>
					</table>
<%--					<logic:notEqual value="12872" name="xxdm">--%>
<%--								<TABLE width="99%" id="rsTable1" class="tbstyle">--%>
<%--									<TR>--%>
<%--										<TD height=3></TD>--%>
<%--									</TR>--%>
<%--									<TR>--%>
<%--										<TD>--%>
<%--											<jsp:include flush="true"--%>
<%--												page="/sjcz/turnpage.jsp?form=xljk_XskhdxwhForm"></jsp:include>--%>
<%--										</TD>--%>
<%--									</TR>--%>
<%--									<TR>--%>
<%--										<TD height=3></TD>--%>
<%--									</TR>--%>
<%--								</TABLE>--%>
<%--								<br />--%>
<%--								<br />--%>
<%--							</logic:notEqual>--%>
			</logic:notEmpty>
			</div>
			<div id="toolTipLayer" style="position:absolute; visibility: hidden"></div>
		</html:form>
		<div id="tmpdiv"></div>
		<logic:present name="rs">
			<logic:equal value="yes" property="result" name="rs">
				<script>
				alert("�����ɹ�");
				document.getElementById("search_go").click();
			</script>
			</logic:equal>
			<logic:equal value="no" property="result" name="rs">
				<script>alert("����ʧ��")</script>
			</logic:equal>
		</logic:present>
	</body>
</html>
