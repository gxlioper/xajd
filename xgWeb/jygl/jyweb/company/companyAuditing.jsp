<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript">
			
			function plsh(){
				viewTempDiv('单位审核','shdiv',380,200);
			}
			
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>单位审核</a>
			</p>
		</div>

		<html:form action="/jyweb" method="post">
			<!-- 多功能操作区一 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="showAuditingWindow('primarykey_cbv','jywebUseCheckSession.do?method=companyUpdate&doType=sh',800,580);"
								class="btn_sh">审核 </a>
						</li>
						<li>
							<a href="#"
								onclick="if(isChecked('primarykey_cbv') && confirm('您确定要取消审核所勾选的记录吗？')){refreshForm('jywebUseCheckSession.do?method=companyAuditing&doType=qxsh')}"
								class="btn_qxsh">取消审核 </a>
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
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=companyAuditing&doType=query')">
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
								<th>
									单位名称
								</th>
								<td>
									<html:text property="querylike_dwmc" maxlength="25" style="width:175px"></html:text>
								</td>
								<th>
									注册时间
								</th>
								<td>
									<html:text property="querygreaterequal_zcsj"
										onblur="dateFormatChg(this);"
										onclick="showCalendar(this.id,'y-mm-dd')"
										style="width:80px"
										styleId="querygreaterequal_zcsj"></html:text>
									-
									<html:text property="querylessequal_zcsj"
										onblur="dateFormatChg(this);"
										onclick="showCalendar(this.id,'y-mm-dd')"
										style="width:80px"
										styleId="querylessequal_zcsj"></html:text>
								</td>
								<th>
									行业分类
								</th>
								<td>
									<html:select property="queryequals_hyfl" style="width:180px">
										<html:options collection="hyflList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									单位性质
								</th>
								<td>
									<html:select property="queryequals_dwxz" style="width:180px">
										<html:options collection="dwxzList" property="dm"
											labelProperty="mc" />
									</html:select>
								</td>
								<th>
									审核状态
								</th>
								<td>
									<html:select property="queryequals_shzt"  style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="shztList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
								<th>
								</th>
								<td>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<!-- From内容 start-->
			<!---------------------表格--有复选框的数据表单------------------->
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue" >提示：单击表头可以排序</font>
							<font color="red" style="font-weight:normal;"> </font>
						</logic:notEmpty>
					</span>
				</h3>

				
					<table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="true" style="height:17.5px" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="2" scope="request">
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
											<input type="checkbox" name="primarykey_cbv" value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" alt="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>"/>
										</td>
										<td>
											<a onclick="showTopWin('jywebUseCheckSession.do?method=companyUpdate&doType=view&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>','800','580');" 
												class="pointer" style="color:#0A63BF" href="#">
												<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>
											</a>
										</td>
										<logic:iterate id="v" name="s" offset="3" >
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
										<logic:iterate id="t" name="topTr" offset="2" scope="request">
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
										<logic:iterate id="t" name="topTr" offset="2" scope="request">
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
					      			审核意见<br/>
					      			<font color="red"><限500字></font>
					      		</th>
					      		<td colspan="3">
					      			<html:textarea property="save_shyj"  onblur="checkLen(this,500)" style="width:90%" rows="5"></html:textarea>
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
										<button onclick="if (confirm('您确定要审核所勾选的记录吗？')){refreshForm('jywebUseCheckSession.do?method=companyAuditing&save_shzt=通过&doType=sh')}">
											通过
										</button>
										<button onclick="if (confirm('您确定要审核所勾选的记录吗？')){refreshForm('jywebUseCheckSession.do?method=companyAuditing&save_shzt=不通过&doType=sh')}">
											不通过
										</button>
										<button onclick="if (confirm('您确定要退回所勾选的记录吗？')){refreshForm('jywebUseCheckSession.do?method=companyAuditing&save_shzt=退回&doType=sh')}">
											退回
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
		
		<logic:present name="message">
			<script defer="defer">
				alert("${message}");
			</script>
		</logic:present>
	</body>
</html>
