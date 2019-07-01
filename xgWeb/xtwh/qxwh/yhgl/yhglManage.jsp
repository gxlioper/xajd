<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript">
			function initMm(){
				var pkValue = document.getElementsByName('primarykey_cbv');
				var sfxz = false;
				
				for(var i=0; i<pkValue.length; i++){
					if(pkValue[i].checked){
						sfxz = true;
					}
				} 
				if(!sfxz){
					if(confirm('未选择用户初始化密码将会重置所有用户密码为888888，您确定继续执行该操作？')){
						 refreshForm('yhwhManage.do?method=yhwhManage&doType=init');
					}
				}else{
					if(confirm('指定勾选用户密码将会重置为888888，您确定继续执行该操作？')){
						 refreshForm('yhwhManage.do?method=yhwhManage&doType=init');
					}
				}
			}
			
			function qxfp(url){
				if(curr_row != null){
					refreshForm(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value);
					return true;
				}else{
					alert('请选择要操作的数据行！');
					return false;
				}
			}
			
			function modi(url,h,w){
				if(curr_row != null){
					showTopWin(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
					return true;
				}else{
					alert('请选择要操作的数据行！');
					return false;
				}
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>系统维护-权限维护-用户维护 </a>
			</p>
		</div>
		<html:form action="/yhwhManage" method="post">
			<div class="toolbox">
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_zj" onclick="showTopWin('yhwhManage.do?method=yhwhUpdate',800,600);return false;">增加用户</a></li>
						<li><a href="#" class="btn_xg" onclick="modi('yhwhManage.do?method=yhwhModi',800,600);return false;">修改用户</a></li>
						<!-- <li><a href="#" class="btn_ck" onclick="batchData('primarykey_cbv','yhwhManage.do?method=yhjsUpdate','');return false;">用户角色分配</a></li>-->
						<li><a href="#" class="btn_xy" onclick="qxfp('yhwhManage.do?method=yhqxfpManage');return false;">指定用户分配权限</a></li>
						<li><a href="#" class="btn_sc" onclick="batchData('primarykey_cbv','yhwhManage.do?method=yhwhManage&doType=del','del');return false;">删除用户</a></li>
						<li><a href="#" class="btn_csh" onclick="initMm();return false;">用户密码初始化</a></li>
						<li><a href="#" class="btn_sx" onclick="setMrrs();return false;">学生用户同步</a></li>
					</ul>
				</div>
			</div>

			<div class="searchtab">
				<table>
					<tbody>
						<tr>
							<th>拥有角色</th>
							<td>
								<html:select property="jsdm" style="width: 150px">
									<html:option value=""></html:option>
									<html:options collection="jsList" property="jsdm" labelProperty="jsmc"/>
								</html:select>
							</td>
							<th>是否拥有可授权角色</th>
							<td>
								<html:select property="sfksq" style="width: 150px">
									<html:option value=""></html:option>
									<html:option value="是">是</html:option>
									<html:option value="否">否</html:option>
								</html:select>
							</td>
							
							<th>用户名</th>
							<td>
								<html:text property="yhm" styleId="yhm"></html:text>
							</td>
						</tr>
						<tr>
							<th>用户部门</th>
							<td colspan="2">
								<html:select property="szbm" style="width: 250px" styleId="szbm" onmouseover="">
									<html:option value=""></html:option>
									<html:options collection="bmList" property="bmdm" labelProperty="bmmc" />
								</html:select>
							</td>
							<td colspan="3"></td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<input type="hidden" name="go" value="a" />
									<button type="button" id="search_go" onclick="allNotEmpThenGo('yhwhManage.do?method=yhwhManage');">
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
							<tr onclick="rowOnClick(this);" style="cursor:hand" 
								ondblclick="modi('yhwhManage.do?method=yhwhView',700,500);">
								<td>								
									<input type="checkbox" name="primarykey_cbv" id="pkV"
										value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
									
								</td>
								<logic:iterate id="v" name="s" offset="0">
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
	   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=yhglForm"></jsp:include>
			<!--分页显示-->
		</div>
		<div class="tab" style="display: none" id="tempDiv">
		</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript" defer="defer">
				alert(document.getElementById('message').value);
			</script>
		</logic:present>
	</body>
</html>