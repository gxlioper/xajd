<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά��-Ȩ��ά��-��ɫȨ�޲�ѯ </a>
			</p>
		</div>
		<html:form action="/qxcxManage" method="post">
			<div class="toolbox">
				<div class="buttonbox">	
					<ul>
						<li><a href="#" class="btn_fh" id="btn_fh" onclick="refreshForm('/xgxt/qxwh_cxManage.do');return false;">����</a></li>
					</ul>
				</div>
			</div>	
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>��ɫ��</th>
							<td>
								<html:text property="jsmc" styleId="jsmc"></html:text>
							</td>
							<th>��ɫ����</th>
							<td>
								<html:select property="jslxdm" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="jslxList" property="jslxdm" labelProperty="jslxmc"/>
								</html:select>
							</td>
							<th>��ɫ����</th>
							<td>
								<html:select property="jscmdm" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="jsszfwList" property="jscmdm" labelProperty="jscmmc"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<td></td>
							<th></th>
							<td colspan="3"></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('qxcxManage.do?method=jsqxManage');">
										�� ѯ
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										�� ��
							 		</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>
						��ѯ���&nbsp;&nbsp;<font color="blue">������ͷ��������</font>
					</span>
				</h3>
				<table width="99%" id="rsTable" class="dateline">
					<thead>
						<tr align="center" style="cursor:hand">
							<logic:iterate id="tit" name="topTr" offset="0">
								<td id="${tit }" onclick="tableSort(this)">
									${tit }
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:empty name="rs">
					  <%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td>
									&nbsp;
								</td>
							</logic:iterate>	
					 	</tr>
						<%}%>
					 </logic:empty>
					<logic:notEmpty name="rs">
						<logic:iterate name="rs" id="s">
							<tr onclick="rowOnClick(this);" style="cursor:hand">
								<logic:iterate id="v" name="s" offset="0">
									<td>${v }</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						<%
						int rsNum = ((List)request.getAttribute("rs")).size();
						int pageSize = 11;
						if(rsNum < pageSize){
							for(int i=0; i<(pageSize-rsNum); i++){
						%>
						<tr>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td>
									&nbsp;
								</td>
							</logic:iterate>
					 	</tr>
						<%}}%>
				</logic:notEmpty>
			</table>
			<!--��ҳ��ʾ-->
	   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=qxwhForm"></jsp:include>
			<!--��ҳ��ʾ-->
		</div>
		<div class="tab" style="display: none" id="tempDiv">
		</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>