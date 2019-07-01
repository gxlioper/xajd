<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>Ȩ��ά�� - �û���Ȩ - ѧ��������Ȩ</a>
			</p>
		</div>
		<html:form action="/yhwhManage" method="post">
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=yhqxfpManage');return false;">
								<span>�����û���Ȩ</span>
							</a>
						</li>
						<li id="li">
							<a href="/xgxt/yhwhManage.do?method=jsBatchManage">
								<span>��ɫ������Ȩ���û�</span>
							</a>
						</li>
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=xsqxfpManage');return false;">
								<span>����ѧ����Ȩ</span>
							</a>
						</li>
						<li id="li"  class="ha">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=xsBatchManage');return false;">
								<span>��ɫ������Ȩ��ѧ��</span>
							</a>
						</li>
					</ul>				
				</div>
			</div>		
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_xg" onclick="batchData('jsIds','yhwhManage.do?method=xsBatchUpdate','');return false;">��ѡ��ɫ����ѧ��</a></li>
					</ul>
				</div>
			</div>

	<div class="rightframe04"><!--�������Ŀ��������ʱ����rightframe04_hidden���class��-->
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>��ɫ����</th>
							<td>
								<input type="hidden" name="jslxdm" value="001" />
								<html:select property="jslxdm" disabled="true" value="001">
									<html:options collection="jslxList" property="jslxdm" labelProperty="jslxmc"/>
								</html:select>
							</td>
							<th>��ɫ������Χ</th>
							<td>
								<html:select property="jscmdm">
									<html:option value=""></html:option>
									<html:options collection="jsczfwList" property="jscmdm" labelProperty="jscmmc"/>
								</html:select>
							</td>
							<th>��ɫ��</th>
							<td><html:text property="jsmc" styleId="jsmc"></html:text>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('yhwhManage.do?method=xsBatchManage');">
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
							<td>
								<input type="checkbox" name="all" value="all" onclick="chec()"/>
							</td>
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
							<td>
								<input type="checkbox" name="pk" value="" disabled="disabled"></input>
							</td>
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
								<td>								
									<input type="checkbox" name="jsIds" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									
								</td>
								<logic:iterate id="v" name="s" offset="1">
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
							<td>
								<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
							</td>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglForm"></jsp:include>
				<!--��ҳ��ʾ-->
			</div>
		</div>
		<div class="tab" style="display: none" id="tempDiv"></div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>