<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/interface/menuManage.js'></script>
		<script type="text/javascript">
			function setChildList(gnmkdm,id){
				
				DWRUtil.removeAllOptions(id);
				DWRUtil.addOptions(id,[{dm:"",mc:""}],"dm","mc")
			
				if ("" != gnmkdm){
					menuManage.getChildGnmkList(gnmkdm,function(data){
						DWRUtil.addOptions(id,data,"gnmkdm","gnmkmc");
					});
				} 
				
				if (id=="ejgnmkdm" && $("gnmkdm")){
					setChildList("","gnmkdm");
				}
			}
			
			
			function batchSave(){
				if (confirm('您确定要保存吗？')){
					refreshForm("/xgxt/menuManage.do?method=batchSaveSfqy&doType=save");
				}
			}
		</script>
	</head>
	<body onload="">
		<input type="hidden" value="${realTable }" id="realTable"/>
		<input type="hidden" value="${tableName }" id="tableName"/>
		<input type="hidden" value="${menuManageForm.queryequals_gnmkdm }" id="queryequals_gnmkdm"/>
		<input type="hidden" value="${menuManageForm.queryequals_ejgnmkdm }" id="queryequals_ejgnmkdm"/>
		
		<html:form action="/menuManage" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>


			<!-- 多功能操作区一 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
								<a onclick="showUpdateWindow('primarykey_cbv','/xgxt/menuManage.do?method=menuUpdate&doType=update','600','450')" href="#" class="btn_xg"> 修改 </a>
							</li>
							<li>
								<a onclick="batchSave();" href="#" class="btn_ccg"> 保存 </a>
							</li>
							<li>
								<a onclick="impAndChkData()"  href="#" class="btn_dr"> 导入 </a>
							</li>
							<li>
								<a onclick="expData('/xgxt/menuManage.do?method=menuQuery&doType=expData')" href="#" class="btn_dc"> 导出 </a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tfoot>
							<tr>
								<td colspan="6">
									<div class="btn">
										<button type="button" class="btn_cx" id="search_go"
											onclick="allNotEmpThenGo('/xgxt/menuManage.do?method=menuQuery&doType=query')">
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
									一级菜单
								</th>
								<td>
									<html:select property="queryequals_yjgnmkdm"
										styleId="yjgnmkdm"
										onchange="setChildList(this.value,'ejgnmkdm')"
										style="width:140px">
										<html:option value=""></html:option>
										<html:options collection="gnmkList" property="gnmkdm"
											labelProperty="gnmkmc" />
									</html:select>
								</td>
								<th>
									二级菜单
								</th>
								<td>
									<html:select property="queryequals_ejgnmkdm" styleId="ejgnmkdm"
										onchange="setChildList(this.value,'gnmkdm')"
										style="width:140px">
										<html:option value=""></html:option>
									</html:select>
								</td>
								<th>
									三级菜单
								</th>
								<td>
									<html:select property="queryequals_gnmkdm" styleId="gnmkdm"
										style="width:140px">
										<html:option value=""></html:option>
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									是否启用
								</th>
								<td>
									<html:select property="queryequals_sfqy" style="width:140px">
										<html:option value=""></html:option>
										<html:option value="是">是</html:option>
										<html:option value="否">否</html:option>
									</html:select>
								</td>
								<th>
									菜单级别
								</th>
								<td>
									<html:select property="queryequals_cdjb" style="width:140px">
										<html:option value=""></html:option>
										<html:option value="1">一级菜单</html:option>
										<html:option value="2">二级菜单</html:option>
										<html:option value="3">三级菜单</html:option>
									</html:select>
								</td>
								<th>
									访问路径
								</th>
								<td>
									<html:text property="querylike_dyym"></html:text>
								</td>
								
							</tr>
							<tr>
								<th>菜单代码</th>
								<td>
									<html:text property="querylike_gnmkdm"></html:text>
								</td>
								<th>
									菜单名称
								</th>
								<td>
									<html:text property="querylike_gnmkmc"></html:text>
								</td>
								<th></th>
								<td></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; </span>
				</h3>

				<div class="con_overlfow">
					<table class="dateline" width="100%" id="rsTable">
						<thead>
							<tr>
								<td>
									<input type="checkbox" disabled="true" />
								</td>
								<logic:iterate id="tit" name="topTr" offset="1" scope="request">
									<td onclick="tableSort(this)">
										${tit.cn}
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<logic:notEmpty name="rs">
								<logic:iterate name="rs" id="s" indexId="n">
									<tr onclick="rowOnClick(this)">
										<logic:iterate id="v" name="s" offset="0" length="1">
											<td>
												<input type="checkbox" value="${v }" name="primarykey_cbv"/>
												<input type="hidden" value="${v }" name="pkValue"/>
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="1" length="7">
											<td>
												${v }
											</td>
										</logic:iterate>
										<logic:iterate id="v" name="s" offset="8" length="1">
											<td>
												<logic:equal value="是" name="v">
													<input type="radio" name="sfqy_${n }" value="是"
														checked="true" />是
													<input type="radio" name="sfqy_${n }" value="否" />否
												</logic:equal>
												<logic:equal value="否" name="v">
													<input type="radio" name="sfqy_${n }" value="是" />是
													<input type="radio" name="sfqy_${n }" value="否"
														checked="true" />否
												</logic:equal>
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
					page="/sjcz/turnpage.jsp?form=menuManageForm"></jsp:include>
				<!--分页显示-->
			</div>
		</html:form>
		<logic:present name="message">
			<script>
				alert("${message}");
				if (document.getElementById("isPage")){
					document.getElementById("isPage").value="yes";
				}
				
				$('search_go').click();
			</script>
		</logic:present>
		<script defer>
			if ('' != $('yjgnmkdm').value){
				setChildList($('yjgnmkdm').value,'ejgnmkdm');
				setTimeout(function(){
					$('ejgnmkdm').value = $('queryequals_ejgnmkdm').value;
				},100)
			}
			
			if ('' != $('queryequals_ejgnmkdm').value){
				setChildList($('queryequals_ejgnmkdm').value,'gnmkdm');
				setTimeout(function(){
					$('gnmkdm').value = $('queryequals_gnmkdm').value;
				},200)
			}
			
			
			
		</script>		
	</body>
</html>
