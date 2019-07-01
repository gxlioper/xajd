<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='dwr/interface/gyfdyDWR.js'></script>
		<script language="javascript">
		function save(){
			var pkV=document.getElementsByName("primarykey_cbv");
			var blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				var url="gyglnew_ldgl.do?method=ldfpUpdate";
				url+="&doType=save";
				refreshForm(url);
			}else{
				alertInfo("没有需要分配的记录!");
				return false;
			}
		}
		</script>
	</head>
	<body style="width: 100%">
		<html:form action="/gyglnew_ldgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			
			<!-- 提示信息 start-->
			<div class="prompt" id="promptTs">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					勾选用户，点击“保存”，系统会将选中楼栋的管理员替换为当前选中用户
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="save();return false;" class="btn_ccg">保存</a></li>
					</ul>
				</div>
			</div>
			
			<!-- 模块类型 -->
			<div class="toolbox">
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('gyglnew_ldgl.do?method=ldfpUpdate');">
											查 询
										</button>
										<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
											重 置
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
						<tbody>
							<tr>
								<th>
									用户名
								</th>
								<td>
									<html:text property="yhm" styleId="yhm" style="width:150px" />
								</td>
								<th>
									姓名
								</th>
								<td>
									<html:text property="xm" styleId="xm" style="width:150px" />
								</td>
								<th>
									性别
								</th>
								<td>
									<html:select property="xb" styleId="xb" style="width:150px">
										<html:option value=""></html:option>
										<html:option value="男">男</html:option>
										<html:option value="女">女</html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									部门
								</th>
								<td>
									<html:select property="bmdm" styleId="bmdm" style="width:150px">
										<html:option value=""></html:option>
										<html:options collection="bmList" property="bmdm"
											labelProperty="bmmc" />
									</html:select>
								</td>
								<th>
								</th>
								<td>
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
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <logic:empty name="rs">
							<font color="red">未找到任何记录！</font>
						</logic:empty> </span>
				</h3>
					<table width="100%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td>
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
								<tr onclick="rowOnClick(this);" style="cursor:hand;">
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
							int pageSize = (Integer)request.getAttribute("pageSize");
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewLdglForm"></jsp:include>
				<!--分页显示-->
			</div>
		</html:form>
		<logic:present name="message">
			<input type="hidden" id="message" value="${message }"/>
			<script type="text/javascript">
			
				
				 showAlert(jQuery('#message').val(),{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
			</script>
		</logic:present>
	</body>
</html>
