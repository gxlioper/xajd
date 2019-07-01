<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
			
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('/xgxt/gyglnew_qsgl_qsgl.do?doType=go');
			}
			
			jQuery(function(){
				disXy();
			})
		</script>
	</head>
	<body>
		<html:form action="/njjs_jygl" method="post">
			<input type="hidden" name="xyV" id="xyV" value="" />
			<input type="hidden" name="zyV" id="zyV" value="" />
			<input type="hidden" name="bjV" id="bjV" value="" />
			<input type="hidden" name="njV" id="njV" value="" />
			
			<input type="hidden" id="userType" value="${user.userStatus }" />
			<input type="hidden" name="isFdy" value="${isFdy }"/>
			<input type="hidden" name="isBzr" value="${isBzr }" />
			<input type="hidden" name="userName" value="${user.userName }"/>
			<logic:equal value="xy" name="user" property="userStatus">
				<input type="hidden" id="userDep" name="xydm" value="${userDep }" />
			</logic:equal>
			
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>			
			
			<logic:equal value="yes" name="writeAble">
				<div class="toolbox" id="dgncz">
					<!-- 按钮 -->
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_xg" onclick="modi('njjs_jygl.do?method=xssbUpdate',800,600);return false;">毕业生上报</a></li>
						</ul>
					</div>
				</div>
			</logic:equal>

			<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button class="btn_cx" id="search_go" 
										onclick="allNotEmpThenGo('njjs_jygl.do?method=xssbManage');">
										查询
									</button>
									<button class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重置
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
								<html:select property="nj" onchange="initZyList();initBjList()" styleId="nj">
									<html:option value=""></html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
							<th>
								学号
							</th>
							<td>
								<html:text property="xh" styleId="xh" />
							</td>
							<th>
								姓名
							</th>
							<td>
								<html:text property="xm" styleId="xm" />
							</td>
						</tr>
						<tr>
							<th>
								<bean:message key="lable.xsgzyxpzxy" />
							</th>
							<td>
								<html:select property="xydm" style="width: 150px" styleId="xy"
									onchange="initZyList();initBjList();">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
							</td>
							<th>
								专业
							</th>
							<td>
								<html:select property="zydm" style="width: 150px" styleId="zy"
									onchange="initBjList()">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
							</td>
							<th>
								班级
							</th>
							<td>
								<html:select property="bjdm" style="width:150px" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
							</td>
						</tr>
						<!--						<tr>-->
						<!--							<th>是否已上报</th>-->
						<!--							<td>-->
						<!--								<html:select property="sfsb">-->
						<!--									<html:option value=""></html:option>-->
						<!--									<html:option value="是">是</html:option>-->
						<!--									<html:option value="否">否</html:option>-->
						<!--								</html:select>-->
						<!--							</td>-->
						<!--							<td colspan="4"></td>-->
						<!--						</tr>-->
					</tbody>
				</table>
			</div>

			<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序;</font>
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit.en }" onclick="tableSort(this)">
										${tit.cn }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<logic:empty name="rs">
						  <%
							for(int i=0; i<11; i++){
							%>
							<tr>
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
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none"> 
										<logic:iterate id="v" name="s" offset="0" length="1">
											<input type="hidden" value="${v }"/>
										</logic:iterate>
									</td>
									<logic:iterate id="v" name="s" offset="0" length="9">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							int pageSize = (Integer)request.getAttribute("pageSize");
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
										&nbsp;
									</td>
								</logic:iterate>
						 	</tr>
							<%}}%>
					</logic:notEmpty>
				</table>
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=njjsJyglForm"></jsp:include>
				<!--分页显示-->
			</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" name="message" value="${message }"/>
			<script type="text/javascript">
				alertInfo($('message').value);
			</script>
		</logic:present>
	</body>
</html>
