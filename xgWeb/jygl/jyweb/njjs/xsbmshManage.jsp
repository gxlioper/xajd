<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>
		<!--选项卡-->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>学生报名审核</a>
			</p>
		</div>
		<!--选项卡-->
	
		<html:form action="/jyweb" method="post">
			<!-- 多功能操作区一 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="viewTempDiv('学生报名审核','shdiv',380,150);"
								class="btn_sh">审核 </a>
						</li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('jywebNjjs.do?method=xsbmshManage')">
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
								<td>
									学号
								</td>
								<td>
									<html:text property="querylike_xh"></html:text>
								</td>
								<td>
									姓名
								</td>
								<td>
									<html:text property="querylike_xm"></html:text>
								</td>
								<td>
									单位名称
								</td>
								<td>
									<html:text property="querylike_dwmc"></html:text>
								</td>
							</tr>
							<tr>
								<td>
									班主任审核
								</td>
								<td>
									<html:select property="queryequals_bzrsh">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
								<td>
									系主任审核
								</td>
								<td>
									<html:select property="queryequals_xysh">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
								<td>
									就业处审核
								</td>
								<td>
									<html:select property="queryequals_xxsh">
										<html:option value=""></html:option>
										<html:option value="未审核">未审核</html:option>
										<html:option value="通过">通过</html:option>
										<html:option value="不通过">不通过</html:option>
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- From内容 start-->
			<!---------------------表格--有复选框的数据表单------------------->
			<div class="formbox">
				<h3 class="datetitle_01" >
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue" >提示：单击表头可以排序</font>
						</logic:notEmpty>
					</span>
				</h3>
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="true" style="height:17.5px"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s">
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										
											<td>
												<a
												onclick="window.open('jywebUseCheckSession.do?method=resumeView&pkValue=<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>');"
												class="pointer" style="color:#0A63BF" href="#"> 
													<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
												</a>
											</td>
										
										<logic:iterate id="v" name="s" offset="2">
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
												<input type="checkbox" disabled="disabled"/>
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
												<input type="checkbox" disabled="disabled"/>
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
					<!--分页显示-->
					<jsp:include flush="true"
						page="/jygl/jyweb/turnPage.jsp?form=jyglActionForm"></jsp:include>
					<!--分页显示-->
			</div>
			
			
			<div class="open_win01" style="display:none;" id="shdiv">
					<table width='80%' class='formlist'>
						<tbody>
							<tr>
								<th>
					      		审核状态
					      		</th>
					      		<td colspan="3">
					      			<select id="select_shzt" >
					      				<option value="通过">通过</option>
					      				<option value="不通过">不通过</option>
					      			</select>
					      		</td>
							</tr>
							<tr>
					      		<th>审核人</th>
					      		<td>
					      			${jyweb_realName }
					      		</td>
					      		<th>审核时间</th>
					      		<td>
					      			${nowTime }
					      		</td>
					      	</tr>
						<tbody>
						<tfoot>
							<tr>
								<td colspan='4'>
									<div class='btn'>
										<button onclick="if (confirm('您确定要审核所勾选的记录吗？')){refreshForm('jywebNjjs.do?method=xsbmAuditing&save_shzt='+jQuery('#select_shzt').val())}">
											确定
										</button>
										<button onclick="hiddenMessage(true,true);return false;">
											关闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
		</html:form>
	</body>
</html>
