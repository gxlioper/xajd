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
		<script type="text/javascript">
			function initMm(){
				var pkValue = document.getElementsByName('primarykey_cbv');
				var sfxz = false;
				
				for(var i=0; i<pkValue.length; i++){
					if(pkValue[i].checked){
						sfxz = true;
					}
				} 
				if(!sfxz){
					if(confirm('δѡ���û���ʼ�����뽫�����������û�����Ϊ888888����ȷ������ִ�иò�����')){
						 refreshForm('yhwhManage.do?method=yhwhManage&doType=init');
					}
				}else{
					if(confirm('ָ����ѡ�û����뽫������Ϊ888888����ȷ������ִ�иò�����')){
						 refreshForm('yhwhManage.do?method=yhwhManage&doType=init');
					}
				}
			}
			
			function qxfp(url){
				if(curr_row != null){
					refreshForm(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
					return true;
				}else{
					alert('��ѡ��Ҫ�����������У�');
					return false;
				}
			}
			
			function modi(url,h,w){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
					return true;
				}else{
					alert('��ѡ��Ҫ�����������У�');
					return false;
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>ϵͳά��-Ȩ��ά��-�û�ά�� </a>
			</p>
		</div>
		<html:form action="/yhwhManage" method="post">
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_zj" onclick="showTopWin('yhwhManage.do?method=yhwhUpdate',800,600);return false;">�����û�</a></li>
						<li><a href="#" class="btn_xg" onclick="modi('yhwhManage.do?method=yhwhModi',800,600);return false;">�޸��û�</a></li>
						<!-- <li><a href="#" class="btn_ck" onclick="batchData('primarykey_cbv','yhwhManage.do?method=yhjsUpdate','');return false;">�û���ɫ����</a></li>-->
						<li><a href="#" class="btn_xy" onclick="qxfp('yhwhManage.do?method=yhqxfpManage');return false;">ָ���û�����Ȩ��</a></li>
						<li><a href="#" class="btn_sc" onclick="batchData('primarykey_cbv','yhwhManage.do?method=yhwhManage&doType=del','del');return false;">ɾ���û�</a></li>
						<li><a href="#" class="btn_csh" onclick="initMm();return false;">�û������ʼ��</a></li>
						<li><a href="#" class="btn_sx" onclick="setMrrs();return false;">ѧ���û�ͬ��</a></li>
					</ul>
				</div>
			</div>

			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>ӵ�н�ɫ</th>
							<td>
								<html:select property="jsdm" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="jsList" property="jsdm" labelProperty="jsmc"/>
								</html:select>
							</td>
							<th>�Ƿ�ӵ�п���Ȩ��ɫ</th>
							<td>
								<html:select property="sfksq" style="width: 150px">
									<html:option value=""></html:option>
									<html:option value="��">��</html:option>
									<html:option value="��">��</html:option>
								</html:select>
							</td>
							
							<th>�û���</th>
							<td>
								<html:text property="yhm" styleId="yhm"></html:text>
							</td>
						</tr>
						<tr>
							<th>�û�����</th>
							<td colspan="2">
								<html:select property="szbm" style="width: 250px" styleId="szbm" onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</td>
							<td colspan="3"></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('yhwhManage.do?method=yhwhManage');">
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
							<tr onclick="rowOnClick(this);" style="cursor:hand" 
								ondblclick="modi('yhwhManage.do?method=yhwhView',700,500);">
								<td>								
									<input type="checkbox" name="primarykey_cbv" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									
								</td>
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