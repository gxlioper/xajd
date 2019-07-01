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
		<html:form action="/xjyd.do?method=xjydshlc" method="post">
			<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
			<input type="hidden" id="message" name="message" value="${message}" />
			<input type="hidden" id="flag" name="flag" value="0" />

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>

			<div class="toolbox">
				  <!-- ��ť -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--��дȨ-->
					  <logic:equal value="yes" name="writeAble">
						 <li> <a href="#" onclick="showUpdateWindow('primarykey_cbv','xjyd.do?method=updateXjydshlc',700,350)" class="btn_xg">�����޸�</a> </li>
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
										onclick="allNotEmpThenGo('/xgxt/xjyd.do?method=xjydshlc')">
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
							<th>�춯���</th>
							<td>
								<html:select property="ydlbdm">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="ydlbList" property="dm" labelProperty="mc"/>
								</html:select>
							</td>
							<th>ѧ��״̬</th>
							<td>	
								<html:select property="xjzt">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="xjztmList" property="zxdmmc" labelProperty="zxdmmc"/>
								</html:select>
							</td>
							<th>ͬ������뷽ʽ</th>								
							<td>
								<html:select property="tjbcyfs">
									<html:option value="">--��ѡ��--</html:option>
									<html:options collection="cyfsList" property="en" labelProperty="cn"/>
								</html:select>
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
										<tr onclick="rowOnClick(this)" ondblclick="showDetailWindow('xjyd.do?method=updateXjydshlc&doType=view',600,300)">
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
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=StudentInfoForm"></jsp:include>
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
