<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<%@ include file="/syscommon/pagehead_V4.ini"%>

	<script language="javascript" src="js/qtsjFunction.js"></script>
	<script type="text/javascript" src="js/commit.js"></script>
	<script type="text/javascript">	
		
		function checkOne(obj){
			var id = obj.id;
			var ids = id.split('_');
			
			if(ids[1] == 'yy'){
				var oid = ids[0] + "_gl";
				
				if($(oid)){
					$(oid).checked = "";	
				}
			}else{
				var oid = ids[0] + "_yy";
				if($(oid)){
					$(oid).checked = "";
				}
			}
		}
		 
	</script>
	</head>
	
	<body>
		<html:form action="/yhwhManage">
			<input type="hidden" name="userName" id="userName" value="${user.userName }"/>
			
			<div class="tab_cur" id="jd">
				<p class="location">
					<em>您的当前位置:</em><a>系统维护-权限维护-用户授权</a>
				</p>
			</div>
			
			<div class="compTab" id="card" style="width: 100%">
				<div class="comp_title" id="div1">
					<ul id="ul1">
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=yhqxfpManage');return false;" id="">
								<span>单个用户授权</span>
							</a>
						</li>
						<li id="li" class="ha">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=jsBatchManage');return false;" id="">
								<span>角色批量授权于用户</span>
							</a>
						</li>
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=xsqxfpManage');return false;" id="">
								<span>单个学生授权</span>
							</a>
						</li>
						<li id="li">
							<a href="#" onclick="refreshForm('/xgxt/yhwhManage.do?method=xsBatchManage');return false;" id="">
								<span>角色批量授权于学生</span>
							</a>
						</li>
					</ul>				
				</div>
			</div>		
						
			<div class="toolbox">
			  <!-- 按钮 -->
			  <div class="buttonbox">
			    <ul>
			      <li><a id="add" class="btn_xy" onclick="batchData('jsIds','yhwhManage.do?method=yhBatchManage','');return false;">所选角色授予用户</a></li>
			    </ul>
			  </div>
			  <!-- 按钮 -->
			  <p class="toolbox_fot"><em></em> </p>
			</div>
			
			<div class="rightframe04"><!--当左边栏目导航隐藏时调用rightframe04_hidden这个class名-->
			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>角色分类</th>
							<td>
								<html:select property="jslxdm">
									<html:option value=""></html:option>
									<html:options collection="jslxList" property="jslxdm" labelProperty="jslxmc"/>
								</html:select>
							</td>
							<th>角色操作范围</th>
							<td>
								<html:select property="jscmdm">
									<html:option value=""></html:option>
									<html:options collection="jsczfwList" property="jscmdm" labelProperty="jscmmc"/>
								</html:select>
							</td>
							<th>角色名</th>
							<td>
								<html:text property="jsmc" styleId="jsmc"></html:text>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('yhwhManage.do?method=jsBatchManage');">
										查 询
									</button>
									 <button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										重 置
							 		</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<font color="blue">单击表头可以排序；</font>
					</span>
				</h3>
				<table width="99%" id="rsTable" class="dateline">
					<thead>
						<tr align="center" style="cursor:hand">
							<td>
								<input type="checkbox" name="all" value="all" onclick="chec()"/>
							</td>
							<logic:iterate id="tit" name="topTr" offset="0">
								<td id="${tit }" onclick="tableSort(this)">
									${tit }
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<logic:empty name="rs">
					  <%
						for(int i=0; i<11; i++){
						%>
						<tr>
							<td>
								<input type="checkbox" name="pk" value="" disabled="disabled"></input>
							</td>
							<logic:iterate id="tit" name="topTr" offset="1">
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
								<td>								
									<input type="checkbox" name="jsIds" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>${v }</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						<%
						int rsNum = ((List)request.getAttribute("rs")).size();
						int pageSize = 11;
						if(rsNum < pageSize){
							for(int i=0; i<(pageSize-rsNum); i++){
						%>
						<tr>
							<td>
								<input type="checkbox" name="primarykey_cbv" value="" disabled="disabled"></input>
							</td>
							<logic:iterate id="tit" name="topTr" offset="1">
								<td>
									&nbsp;
								</td>
							</logic:iterate>
					 	</tr>
						<%}}%>
					</logic:notEmpty>
				</table>
				<!--分页显示-->
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglForm"></jsp:include>
				<!--分页显示-->
			</div>
			<div id="tmpdiv1"></div>
			<%@ include file="/comm/other/tsxx.jsp"  %>
		</html:form>
		<script language="javascript" src="js/bottomButton.js"></script>
	</body>
</html>
