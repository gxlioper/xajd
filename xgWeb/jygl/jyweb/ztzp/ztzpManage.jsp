<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/jyweb.ini"%>
	</head>
	<body>

		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>校内招聘管理</a>
			</p>
		</div>

		<html:form action="/jyweb" method="post">
			<input type="hidden" name="realTable" id="realTable" value="${realTable }" />

			<!-- 多功能操作区一 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="refreshForm('jywebUseCheckSession.do?method=ztzpAdd');"
								class="btn_zj"> 增加 </a>
						</li>
						<li>
							<a href="#" 
							   onclick="showUpdateWindow('primarykey_cbv','jyweb.do?method=ztzpUpdate&doType=update','','',true)" 
							   class="btn_xg"> 修改 </a>
						</li>
						<li>
							<a
								href="javascript:batchData('primarykey_cbv','jywebUseCheckSession.do?method=ztzpManage&doType=del','del')"
								class="btn_sc"> 删除 </a>
						</li>
						<li>
							<a href="javascript:impAndChkData();" class="btn_dr"> 导入 </a>
						</li>
						<li>
							<a
								href="javascript:expData('jywebUseCheckSession.do?method=ztzpManage&doType=expData')"
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
											onclick="allNotEmpThenGo('jywebUseCheckSession.do?method=ztzpManage&doType=query')">
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
									招聘主题
								</th>
								<td>
									<html:text property="querylike_zpzt" style="width:175px"></html:text>
								</td>
								<th>
									招聘时间
								</th>
								<td>
									<html:text property="querygreaterequal_zpsj"
										styleId="querygreaterequal_zpsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);" style="width:80px"></html:text>
									-
									<html:text property="querylessequal_zpsj"
										styleId="querylessequal_zpsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);" style="width:80px"></html:text>
								</td>
								<th>
									发布时间
								</th>
								<td>
									<html:text property="querygreaterequal_fbsj"
										styleId="querygreaterequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);" style="width:80px"></html:text>
									-
									<html:text property="querylessequal_fbsj"
										styleId="querylessequal_fbsj"
										onclick="showCalendar(this.id,'y-mm-dd')"
										onblur="dateFormatChg(this);" style="width:80px"></html:text>
								</td>
								
								
							</tr>

							<tr>
								<th>
									招聘地点
								</th>
								<td>
									<html:text property="querylike_zpdd" style="width:175px"></html:text>
								</td>
								<th>
									审核状态
								</th>
								<td>
									<html:select property="queryequals_shzt" style="width:180px">
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
					<span> 查询结果&nbsp;&nbsp; <logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty> </span>
				</h3>


				<table summary="" class="dateline" width="100%">
					<thead>
						<tr>
							<td>
								<input type="checkbox" disabled="disabled" style="height:17.5px" />
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
										<input type="checkbox" id="cbv" name="primarykey_cbv"
											<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									</td>
									<td>
										<a
											onclick="showTopWin('/xgxt/jyweb.do?method=ztzpUpdate&doType=show&pkValue=<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>',800,600);"
											class="pointer" style="color:#0A63BF" href="#"> 
											<logic:iterate id="v" name="s" offset="2" length="1">${v }</logic:iterate>
										</a>
									</td>
									<logic:iterate id="v" name="s" offset="3">
										<td>
											${v }
										</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
										for (int i = 0; i < (Integer) request.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
							%>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
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
										for (int i = 0; i < (Integer) request
										.getAttribute("maxNum"); i++) {
							%>
							<tr>
								<td>
									<input type="checkbox" disabled="disabled" />
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
		</html:form>

		<logic:present name="message">
			<script>
				alert("${message}");
			</script>
		</logic:present>
	</body>
</html>
