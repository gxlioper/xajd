<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript">
			jQuery(function(){
				var tr = jQuery('tr',jQuery('#tbody'));
				for (var i = 0 ; i < tr.length ; i++){
					var td = jQuery('td',tr[i])[2];
					if ('' != td.innerText.trim()){
						jQuery('input[type="checkbox"]',tr[i]).attr('disabled',true);
					}
				}
			})
		</script>
	</head>
	<body>

		<html:form action="/xtwhGnsz" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a	onclick="showUpdateWindow('primarykey_cbv','xtwhGnsz.do?method=gnszUpdate',500,400)"
									href="#"
									id="btn_sz"
									class="btn_sz"> 设置 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('xtwhGnsz.do?method=gnszManage&doType=query')">
											查 询
										</button>
										<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>功能名称</th>
								<td>
									<html:text property="querylike_gnmc"/>
								</td>
								<th>流程名称</th>
								<td>
									<html:text property="querylike_mc"/>
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
					<font color="blue">提示：单击表头可以排序</font>
					</span>
					
				</h3>
				
					<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%" id="rsTable">
						<thead>
							<tr>
								<td style="width:17.5px">
									<input type="checkbox" disabled="true"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
							<tbody id="tbody">
								<logic:notEmpty name="rs">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" >
											<logic:iterate id="v" name="s" offset="0" length="1">
												<td>
													<input type="checkbox" value="${v }" 
														   name="primarykey_cbv"
														   />
												</td>
											</logic:iterate>
											<logic:iterate id="v" name="s" offset="1">
												<td>
													${v }
												</td>
											</logic:iterate>
										</tr>
									</logic:iterate>
									
									<%
										for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") - ((List)request.getAttribute("rs")).size() ; i++){
									
									 %>
										<tr>
											<td>
												<input type="checkbox" disabled="true"/>
											</td>
											<logic:iterate id="t" name="topTr" offset="1" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
									<%
										}
									 %>
									
								</logic:notEmpty>
								<logic:empty name="rs">
									<%
										for (int i = 0 ; i < (Integer)request.getAttribute("maxNum") ; i++){
									 %>
										<tr>
											<td>
												<input type="checkbox" disabled="true"/>
											</td>
											<logic:iterate id="t" name="topTr" offset="1" scope="request">
												<td>
													&nbsp;
												</td>
											</logic:iterate>
										</tr>
									<%
										}
									 %>
								</logic:empty>
							</tbody>
						</table>
					</div>
					<!--分页显示-->
					<jsp:include flush="true"
						page="/sjcz/turnpage.jsp?form=gnszForm"></jsp:include>
					<!--分页显示-->
			</div>
		</html:form>
	</body>
</html>
