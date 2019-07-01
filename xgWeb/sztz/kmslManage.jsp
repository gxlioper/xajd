<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type="text/javascript" src="js/commit.js"></script>
	</head>

	<script type="text/javascript">
		function delkm(){
			var pkValues = document.getElementsByName('primarykey_cbv');
			var pkValue ="";
			for (var i = 0 ; i < pkValues.length ; i++){
				if (pkValues[i].checked){
					pkValue +=pkValues[i].value+"!@!";
				}
			}
			if(pkValue ==""){
				alert("请至少选择一条记录！");
				return false;
				}else{
					jQuery.post('sztz.do?method=checkDelKmdm',
							{pkValue:pkValue},
							function(data){
							if(data==false){
								alertError("已使用的科目不能删除！");
								return false;
								}else{
									batchData('primarykey_cbv','sztz.do?method=kmslDel','del')
									}
						},'json')
					}
     			
		
			//batchData('primarykey_cbv','sztz.do?method=kmslDel','del')
		}
	</script>


	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置:</em><a>${title }</a>
			</p>
		</div>
		
		<input type="hidden" id="realTable" value=""/>
		
		<html:form action="/sztz" method="post">
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a href='#'
								   class="btn_zj"
								   onclick="showTopWin('sztz.do?method=kmslUpdate',600,450)"
								   id="btn_zj">增加</a>
							</li>
							<li>
								<a href="#" 
								   class="btn_xg"
								   onclick="showUpdateWindow('primarykey_cbv','sztz.do?method=kmslView',600,450)"
								   id="btn_xg">修改</a>
							</li>
							<li>
								<a href="#" 
								   class="btn_sc"
								   onclick="delkm()"
								   id="btn_sc">删除</a>
							</li>
							<li>
								<a href="#"
									id="btn_dr"
									onclick="jQuery('#realTable').val('xg_sztz_kmdmb');impAndChkData()"
									class="btn_dr"> 导入科目 </a>
							</li>
							<li>
								<a href="#"
									id="btn_dr"
									onclick="jQuery('#realTable').val('xg_sztz_hxnlb');impAndChkData()"
									class="btn_dr"> 导入核心能力 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0" id="searchTab">
						<tbody>
							<tr>
								<th>科目代码</th>
								<td>
									<html:text property="querylike_kmdm"></html:text>
								</td>
								<th>科目名称</th>
								<td>
									<html:text property="querylike_kmmc"></html:text>
								</td>
								<td colspan="2">
									<div class="btn">
										<button class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('sztz.do?method=kmslManage&doType=query')">
											查 询
										</button>
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; 
						<logic:notEmpty name="rs">
							<font color="blue">提示：单击表头可以排序</font>
						</logic:notEmpty>
					</span>
				</h3>
				<div class="con_overlfow">
					<table class="dateline tablenowrap" width="100%">
						<thead>
							<tr>
								<td width="17px">
									<input type="checkbox" disabled="disabled" />
								</td>
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
									<tr>
										<td>
											<input type="checkbox" id="cbv" name="primarykey_cbv"
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										</td>
										<logic:iterate id="v" name="s" offset="1">
											<td>
												${v }
											</td>
										</logic:iterate>
									</tr>
								</logic:iterate>

								<%
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum")
											- ((List) request.getAttribute("rs")).size(); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
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
											for (int i = 0; i < (Integer) request
											.getAttribute("maxNum"); i++) {
								%>
								<tr>
									<td>
										<input type="checkbox" disabled="disabled" />
									</td>
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

				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=sztzActionForm"></jsp:include>
			</div>
			
		</html:form>
	</body>
</html>
