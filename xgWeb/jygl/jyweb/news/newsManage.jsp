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
				<em>您的当前位置:</em><a>新闻管理</a>
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
							<a
								href="javascript:showTopWin('jywebUseCheckSession.do?method=newUpdate','800','600');"
								class="btn_zj"> 增加 </a>
						</li>
						<li>
							<a  href="#"
								onclick="showUpdateWindow('primarykey_cbv','jywebUseCheckSession.do?method=newUpdate&type=wjbt&doType=update','800','600')"
								class="btn_xg"> 修改 </a>
						</li>
						<li>
							<a
								href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=newsManage&doType=del','del')"
								class="btn_sc"> 删除 </a>
						</li>
						<li>
							<a
								href="javascript:expData('jywebUseCheckSession.do?method=newsManage&doType=expData')"
								class="btn_dc"> 导出 </a>
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
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=newsManage&doType=query')">
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
									新闻类型
								</td>
								<td>
									<html:select property="queryequals_wjlx">
										<html:option value=""></html:option>
										<html:options collection="newLxList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<td>
									标题
								</td>
								<td>
									<html:text property="querylike_wjbt" maxlength="25" />
								</td>
								<td>
									发布时间
								</td>
								<td>
									<html:text property="querygreaterequal_fbsj"
										style="width:100px"
										onblur='dateFormatChg(this)' styleId="querygreaterequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')" />
									-
									<html:text property="querylessequal_fbsj"
										style="width:100px"
										onclick="showCalendar(this.id,'y-mm-dd')"
										styleId="querylessequal_fbsj"
										onblur='dateFormatChg(this)' />
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
										<logic:iterate id="v" name="s" offset="1" length="1">
											<td>
												<a onclick="window.open('/xgxt/jyweb.do?method=newsInfo&type=wjbt&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>');" 
													class="pointer" style="color:#0A63BF" href="#">
													${v }
												</a>
											</td>
										</logic:iterate>
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
		</html:form>
	</body>
</html>
