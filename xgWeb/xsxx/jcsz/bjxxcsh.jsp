<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.*" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<script type="text/javascript" src="js/xgutil.js"></script>
<script type="text/javascript" src="js/xgservice.js"></script>
</head>
<html>
	<body>		
		<html:form action="/xxcshgl.do?method=bjxxcsh" method="post">
			<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
			<input type="hidden" id="message" name="message" value="${message}" />
			<input type="hidden" id="flag" name="flag" value="0" />

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>
			<!--��дȨ-->
			<logic:equal value="yes" name="writeAble">
			  <!--�����ʼ������-->
			  <logic:notEqual value="true" name="cshFlag">
				<div class="prompt">
		          <h3><span>��ʾ��</span></h3>
		          <p>
					���ݳ�ʼ���Ѿ���ɣ���ǰֻ�ܲ�ѯ������ִ�й�����ʼ�����ܺ�ſɲ�����
				  </p>
		          <a class="close" title="����" onclick="this.parentNode.style.display='none'"></a>
		      	</div>
			  </logic:notEqual>
			</logic:equal>
			
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<logic:notEmpty name="mkList">
							<logic:iterate id="card" name="mkList" scope="request" indexId="s">	
								<logic:equal value="�༶��Ϣ" name="card" property="cn" >
									<li id="${card.en}li" class="ha">
										<a href="#" onclick="refreshForm('${card.en}')" id="${card.en}">
											<span>${card.cn}</span>
										</a>
									</li>
								</logic:equal>						
								<logic:notEqual value="�༶��Ϣ" name="card" property="cn">
									<li id="${card.en}li">
										<a href="#" onclick="refreshForm('${card.en}')" id="${card.en}">
											<span>${card.cn}</span>
										</a>
									</li>
								</logic:notEqual>
							</logic:iterate>						
						</logic:notEmpty>
					</ul>				
				</div>
			</div>		

			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
				        <!--�����ʼ������-->
						<logic:equal value="true" name="cshFlag">
						 <li> <a href="#" onclick="showUpdateWindow('primarykey_cbv','xxcshgl.do?method=bjxxUpdate',600,400)" class="btn_xg">��&nbsp;&nbsp;��</a> </li>
						 <li> <a href="#" onclick="bachAction('xxcshgl.do?method=bjxxcsh&doType=del','primarykey_cbv','��ȷ��ɾ����ѡ��¼��')" class="btn_sc">ɾ&nbsp;&nbsp;��</a> </li>
						 <li> <a href="#" onclick="impAndChkData();" class="btn_dr">���ݵ���</a> </li>
						 <li> <a href="#" onclick="confirmAction('xxcshgl.do?method=bjxxcsh&doType=ljje','�ò��������ⲿ�ӿڽ������ݣ���ȷ�ϼ���������')" class="btn_csh">���ӽӿ�</a> </li>
						 <li> <a href="#" onclick="bachAction('xxcshgl.do?method=bjxxcsh&doType=bjwzqsj','primarykey_cbv','��ȷ������ѡ������ݱ��Ϊ��ʽ������')" class="btn_sz">����ȷ��</a> </li>
						 <li> <a href="#" onclick="confirmAction('xxcshgl.do?method=bjxxcsh&doType=qbbjwzqsj','�ò������������ݱ��Ϊ��ʽ���ݣ���ȷ�ϼ���������')"" class="btn_sz">ȫ������ȷ��</a> </li>
						</logic:equal>
					  </logic:equal>	
					</ul>					
				  </div>
					
				  <div class="searchtab">
					<table width="100%" border="0">
				      <tfoot>
				        <tr>	  
				          <td colspan="6">
				            <div class="btn">
							  	<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go"
										onclick="allNotEmpThenGo('/xgxt/xxcshgl.do?method=bjxxcsh')">
										��ѯ
									</button>
									<button type="button"
										onclick="searchReset();return false;">
										����
									</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>

					  <tbody>	
						  <tr>
							<th>�༶����</th>
							<td>
								<html:text property="querylike_bjdm"></html:text>
							</td>
							<th>�༶����</th>
							<td>	
								<html:text property="querylike_bjmc"></html:text>
							</td>
							<th>�༶���</th>
							<td>
								<html:text property="querylike_bjjc"></html:text>
							</td>
						  </tr>				  
						  <tr>
							<th>���Ŵ���</th>
							<td>
								<html:text property="querylike_bmdm"></html:text>
							</td>
							<th>��������</th>
							<td>	
								<html:text property="querylike_bmmc"></html:text>
							</td>
							<th>�꼶</th>
							<td>
								<html:text property="querylike_nj"></html:text>
							</td>
						  </tr>
						  <tr>
							<th>רҵ����</th>
							<td>
								<html:text property="querylike_zydm"></html:text>
							</td>
							<th>רҵ����</th>
							<td>	
								<html:text property="querylike_zymc"></html:text>
							</td>
							<th></th>
							<td>
								
							</td>
						  </tr>
					  </tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
					<h3 class="datetitle_01">
					    <span>
					    	��ѯ���&nbsp;&nbsp;
					    	<font color="blue">��ʾ��˫��һ�п��Բ鿴��ϸ��������ͷ��������</font>
					    </span>
				    </h3>
					<div class="con_overlfow">
						<table summary="" class="dateline tablenowrap" width="100%" id="rsTable">
							<thead>
						      <tr>
						       <td>
									<input type="checkbox" name="cb" onclick="selectAll()" style="height:17.5px" />
							   </td>
							   <logic:iterate id="tit" name="topTr">
									<td id="${tit.en}"
										onclick="tableSort(this)">
										${tit.cn}
									</td>
								</logic:iterate>
						      </tr>
						    </thead>
							
							<tbody>
							  <%
								int pageSize = (Integer) request.getAttribute("pageSize");
							  %>
							  <logic:empty name="rs">
									<%
									for(int i=0; i<pageSize; i++){
									%>
									<tr>
										<td align="center">
											<input type="checkbox" name="primarykey_cbv" id="cbv" value="" disabled="disabled"></input>
										</td>
										<logic:iterate id="tit" name="topTr">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
								 	</tr>
					
									<%}%>
					 		  </logic:empty>
							  <logic:notEmpty name="rs">
							      <logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" ondblclick="showDetailWindow('xxcshgl.do?method=bjxxUpdate&doType=view',600,400)">
											<td>
												<logic:iterate id="v" name="s" offset="0" length="1">
													<input type="checkbox" name="primarykey_cbv" id="cbv" value="${v}"/>
												</logic:iterate>
										    </td>
											<logic:iterate id="v" name="s">
												<td>
													${v}
												</td>
											</logic:iterate>
										</tr>
								</logic:iterate>
								<%
									int rsNum = (Integer)request.getAttribute("rsNum");									
									for(int i=0; i<(pageSize-rsNum); i++){
								%>
									<tr>
										<td align="center">
											<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
										</td>
										<logic:iterate id="tit" name="topTr">
											<td>
												&nbsp;
											</td>
										</logic:iterate>
										
								 	</tr>
								<%}%>
								</logic:notEmpty>
						    </tbody>
						</table>
					</div>
				<!--��ҳ��ʾ-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxcshForm"></jsp:include>
			 	<!--��ҳ��ʾ-->
				</div>
		</html:form>
		 
		 <logic:notEmpty name="message">
			<script>
				alert(''+$('message').value);
				//document.getElementById('search_go').click();
			</script>		
		</logic:notEmpty>
	</body>
</html>
