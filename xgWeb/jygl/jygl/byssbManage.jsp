<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="pjpy/xibeierminyuan/js/xbemyJs.js"></script>
		<script type="text/javascript" src="pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
		<script type="text/javascript" src="pjpy/czxx/czxx.js"></script>
		<script type="text/javascript" src="js/jygl/jygl.js"></script>
		<script type="text/javascript">
			function showStu() {
				if (null == curr_row) {
					alert('请选择一行');
				} else {
					var xh = curr_row.getElementsByTagName('input')[0].value;
					var url = '/xgxt/stu_info_details.do?xh='+xh;
					showTopWin(url,'820','600');
				}
			}
		</script>
	</head>
	<body onload="xyDisabled('xy');purviewControl();">

		<html:form action="/jygl" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<input type="hidden" name="isFdy" id="isFdy" value="${isFdy }" />
			<input type="hidden" id="userType" name="userType" value="${userType }" />
			<input type="hidden" id="userName" name="userName" value="${userName }" />
			<input type="hidden" id="tableName" name="tableName" value="${ tableName}" />
			<input type="hidden" id="realTable" name="realTable" value="${realTable }" />
			<input type="hidden" name="message" id="message" value="${message }" />
			<input type="hidden" name="xyV" value="" />
			<input type="hidden" name="zyV" value="" />
			<input type="hidden" name="bjV" value="" />
			<input type="hidden" id="cbVal" name="cbVal" value="" />
			
			<!-- 大师权限控制按钮用的，不服可以砍他 -->
			<logic:present name="purview">
				<input type="hidden" id="purview" name="purview" value="${ purview }" /> 
				<input type="hidden" id=" operateBound " name="operateBound" value="${ operateBound }" />
			</logic:present>
			
			 <logic:equal value="close" name="flg">
				<div class="prompt" id="div_help">
		          <h3><span>提示：</span></h3>
		          <p>现在不是毕业生上报时间。</p>
		          <a class="close" title="隐藏" onclick="this.parentNode.style.display='none'"></a>
		      	</div>
		     </logic:equal>

			<!-- 多功能操作区一 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
				<logic:equal value="open" name="flg">
					<div class="buttonbox">
						<ul>
							<li>
								<a	onclick="showTopWin('jygl.do?method=byssb',800,600)"
									href="#"
									id="btn_up"
									class="btn_up"> 毕业生上报 </a>
							</li>
						</ul>
					</div>
					</logic:equal>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/jygl.do?method=byssbManage&doType=query')">
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
									年级
								</th>
								<td>
									<html:select property="queryequals_nj" styleId="nj"
										onchange="initZyList();initBjList()" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="njList" property="nj"
											labelProperty="nj" />
									</html:select>
								</td>
								<th>
									学号
								</th>
								<td>
									<html:text property="xh" maxlength="20" style="width:175px"></html:text>
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" maxlength="20" style="width:175px"></html:text>
								</td>
							</tr>
							<tr>
								<th>
									<bean:message key="lable.xsgzyxpzxy" />
								</th>
								<td>
									<html:select property="xydm"  
										onchange="initZyList();initBjList()" styleId="xy"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="xyList" property="xydm"
											labelProperty="xymc" />
									</html:select>
								</td>
								<th>
									专业
								</th>
								<td>
									<html:select property="zydm"
										onchange="initBjList()" styleId="zy" style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="zyList" property="zydm"
											labelProperty="zymc" />
									</html:select>
								</td>
								<th>
									班级
								</th>
								<td>
									<html:select property="bjdm" styleId="bj"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="bjList" property="bjdm"
											labelProperty="bjmc" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>上报人</th>
								<td>
									<html:text property="sbr" style="width:175px"></html:text>
								</td>
								<th>上报年度</th>
								<td>
									<html:select property="sbnd" style="width:180px">
										<html:options collection="ndList" property="nd" labelProperty="nd"/>
									</html:select>
								</td>
								<th>
									届
								</th>
								<td>
									<html:select property="je" styleId="je"
										style="width:180px">
										<html:option value=""></html:option>
										<html:options collection="nfList" property="en"
											labelProperty="cn" />
									</html:select>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
					提示：
					您在本年度已上报毕业生<font color="red">${ysbrs }</font>人
					</span>
					
				</h3>
				
					<div class="con_overlfow">
					<table class="dateline tablenowrap" align="" width="100%" id="rsTable">
						<thead>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0" scope="request">
									<td onclick="tableSort(this)">
										${tit}
									</td>
								</logic:iterate>
							</tr>
						</thead>
							<tbody>
								<logic:notEmpty name="rs">
									<logic:iterate name="rs" id="s">
										<tr onclick="rowOnClick(this)" >
											<logic:iterate id="v" name="s" offset="0" length="1">
												<td>
													<input type="hidden" value="${v }"/>
<%--													<a href="javascript:showStu();" class="pointer" style="color:#0A63BF">${v }</a>--%>
													${v }
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
											<logic:iterate id="t" name="topTr" offset="0" scope="request">
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
											<logic:iterate id="t" name="topTr" offset="0" scope="request">
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
						page="/sjcz/turnpage.jsp?form=jyglActionForm"></jsp:include>
						<script type="text/javascript">
							$('choose').className="hide";
						</script>
					<!--分页显示-->
			</div>
		</html:form>
	</body>
</html>
