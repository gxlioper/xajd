<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<jsp:directive.page import="java.util.*" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
</head>
<html>
	<body>		
		<html:form action="/xxcshgl.do?method=xsxxcsh" method="post">
			<input type="hidden" id="tableName" name="tableName" value="${tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable}" />
			<input type="hidden" id="message" name="message" value="${message}" />
			<input type="hidden" id="flag" name="flag" value="0" />

			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title}</a>
				</p>
			</div>
			<!--读写权-->
			<logic:equal value="yes" name="writeAble">
			  <!--允许初始化数据-->
			  <logic:notEqual value="true" name="cshFlag">
				<div class="prompt">
		          <h3><span>提示：</span></h3>
		          <p>
					数据初始化已经完成！当前只能查询，必须执行过反初始化功能后才可操作！
				  </p>
		          <a class="close" title="隐藏" onclick="this.parentNode.style.display='none'"></a>
		      	</div>
			  </logic:notEqual>
			</logic:equal>
			
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<logic:notEmpty name="mkList">
							<logic:iterate id="card" name="mkList" scope="request" indexId="s">	
								<logic:equal value="学生信息" name="card" property="cn" >
									<li id="${card.en}li" class="ha">
										<a href="#" onclick="refreshForm('${card.en}')" id="${card.en}">
											<span>${card.cn}</span>
										</a>
									</li>
								</logic:equal>						
								<logic:notEqual value="学生信息" name="card" property="cn">
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
				  <!-- 按钮 -->				  
				  <div class="buttonbox">
				    <ul>
					  <!--读写权-->
					  <logic:equal value="yes" name="writeAble">
				        <!--允许初始化操作-->
						<logic:equal value="true" name="cshFlag">
						 <li> <a href="#" onclick="showUpdateWindow('primarykey_cbv','xxcshgl.do?method=xsxxUpdate',800,600)" class="btn_xg">修&nbsp;&nbsp;改</a> </li>
						 <li> <a href="#" onclick="bachAction('xxcshgl.do?method=xsxxcsh&doType=del','primarykey_cbv','您确定删除所选记录吗？')" class="btn_sc">删&nbsp;&nbsp;除</a> </li>
						 <li> <a href="#" onclick="impAndChkData();" class="btn_dr">数据导入</a> </li>
						 <li> <a href="#" onclick="confirmAction('xxcshgl.do?method=xsxxcsh&doType=ljje','该操作将从外部接口接入数据，您确认继续操作吗？')" class="btn_csh">连接接口</a> </li>
						 <li> <a href="#" onclick="bachAction('xxcshgl.do?method=xsxxcsh&doType=bjwzqsj','primarykey_cbv','您确定将所选择的数据标记为正式数据吗？')" class="btn_sz">数据确认</a> </li>
						 <li> <a href="#" onclick="confirmAction('xxcshgl.do?method=xsxxcsh&doType=qbbjwzqsj','该操作将所有数据标记为正式数据，您确认继续操作吗？')"" class="btn_sz">全部数据确认</a> </li>
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
										onclick="allNotEmpThenGo('/xgxt/xxcshgl.do?method=xsxxcsh')">
										查询
									</button>
									<button type="button"
										onclick="searchReset();return false;">
										重置
									</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>

					  <tbody>	
						  <tr>
							<th>学号</th>
							<td>
								<html:text property="querylike_xh"></html:text>
							</td>
							<th>姓名</th>
							<td>	
								<html:text property="querylike_xm"></html:text>
							</td>
							<th>性别</th>
							<td>
								<html:text property="querylike_xb"></html:text>
							</td>
						  </tr>				  
						  <tr>
							<th><bean:message key="lable.xb" />代码</th>
							<td>
								<html:text property="querylike_xydm"></html:text>
							</td>
							<th><bean:message key="lable.xb" />名称</th>
							<td>	
								<html:text property="querylike_xymc"></html:text>
							</td>
							<th>年级</th>
							<td>
								<html:text property="querylike_nj"></html:text>
							</td>
						  </tr>
						  <tr>
							<th>专业代码</th>
							<td>
								<html:text property="querylike_zydm"></html:text>
							</td>
							<th>专业名称</th>
							<td>	
								<html:text property="querylike_zymc"></html:text>
							</td>
							<th>学籍状态</th>
							<td>
								<html:text property="querylike_xjztm"></html:text>
							</td>
						  </tr>	
						  <tr>
							<th>班级代码</th>
							<td>
								<html:text property="querylike_bjdm"></html:text>
							</td>
							<th>班级名称</th>
							<td>	
								<html:text property="querylike_bjmc"></html:text>
							</td>
							<th>是否在校</th>
							<td>
								<html:text property="querylike_sfzx"></html:text>
							</td>
						  </tr>	
					  </tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
					<h3 class="datetitle_01">
					    <span>
					    	查询结果&nbsp;&nbsp;
					    	<font color="blue">提示：双击一行可以查看详细；单击表头可以排序！</font>
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
										<tr onclick="rowOnClick(this)" ondblclick="showDetailWindow('xxcshgl.do?method=xsxxUpdate&doType=view',800,600)">
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
				<!--分页显示-->
			      <jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xsxxcshForm"></jsp:include>
			 	<!--分页显示-->
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
