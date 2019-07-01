<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//点击侧边栏
		function clickLeftTj(value){
			var num = document.getElementsByName("left_lx").length;
			for(var i=0;i<num;i++){
				var obj = document.getElementsByName("left_lx")[i];
				var obj_id = obj.id;
				var obj_value = obj.value;
				
				if(obj.checked){
					if(obj_value == "filess"){
						$("ssdm").value = value;
						$("lxdm").value = "";
					}else{
						$("lxdm").value = value;
						$("ssdm").value = "";
					}
				}
			}
			
			clickLeftMenu();
		}
		
		//点击侧边栏
		function clickLeftMenu(){
			
			dwr.engine.setAsync(false);
			
			var divHtml = "<div id=\"dy_area\" style=\"height: 450px;overflow:scroll;overflow-x:hidden\">"
				divHtml+= "<span class=\"formbox\">";
				divHtml+= "<table class=\"dateline\" width=\"100%\" id=\"showDiary\">";
				
				//-------------标题-------------
				divHtml+= "<thead><tr>";
				
				var tableName = $("tableName").value;
				var colList =["filemc","ss","lx"];
				var path = $("path").value;
				
				getOtherData.getTopTr(tableName,colList,path,function(data){
					if(data != null && data.length > 0){
						for(var i=0;i<data.length;i++){
							if(i == 0){
								divHtml+= "<td width='30%'>";
							}else{
								divHtml+= "<td width='20%'>";
							}
							divHtml+= data[i].cn;
							divHtml+= "</td>";
						}
					}
					divHtml+= "<td width='20%'>操作</td>";
				});
				divHtml+= "</tr></thead>";					
				//------------标题 end-----------
				
				//------------内容---------------
				
				colList = ["filepath","filesm","filemc","ss","lx"];
				var pk = "";
				var pkValue = "";
				var userType = $("userType").value;
				var query = "and (xzdx = '全部' or xzdx = '";
				
				var dataSize = "0";
				var pageSize = $("pageRsSize").value;
				var currentPage =  $("currentPage").value;
				var maxRecord = "0";
				var maxPage = "0";
				
				var pages = [pageSize,currentPage];
				
				//判断何种查询方式
				if($("ssdm").value != ""){
					pk = "filess";
					pkValue = $("ssdm").value;
				}else{
					pk = "filelx";
					pkValue = $("lxdm").value;
				}
				
				//登陆用户为学生
				if(userType == "stu"){
					query += "学生";
				}else{
					query += "老师";
				}
				query += "')";
				
				divHtml+= "<tbody>";
				getOtherData.getRsListForPage(tableName,colList,pk,pkValue,query,pages,function(data){
					if(data != null && data.length > 0){
						maxRecord = data[0][0];
						for(var i=1;i<data.length;i++){
							
							if(dataSize > pageSize){
								break;
							}
							
							var zdz = data[i];
							divHtml+= "<tr onclick=\"rowOnClick(this)\">";
							divHtml+= "<td title=\""+zdz[1]+"\">"+zdz[2]+"</td>"
							divHtml+= "<td>"+zdz[3]+"</td>"
							divHtml+= "<td>"+zdz[4]+"</td>"
							divHtml+= "<td><a href=\"czxxDtjsDyxx.do?method=downLoadFile&dir="+zdz[0]+"\" target=\"_blank\" style=\"color: blue\">下载附件</a></td>"
							divHtml+= "</tr>";
							
							dataSize++;
						}
					}
				});
				divHtml+= "</tbody>";
				//------------内容 end-----------
				
				//补空行	
				for(var i=0;i<pageSize - dataSize;i++){
					divHtml+= "<tr>";
					divHtml+= "<td>&nbsp;</td>"
					divHtml+= "<td>&nbsp;</td>"
					divHtml+= "<td>&nbsp;</td>"
					divHtml+= "<td>&nbsp;</td>"
					divHtml+= "</tr>";
				}
				
				divHtml+= "</table>";
				
				//分页显示
				maxPage = parseInt(maxRecord/pageSize) +1;
				$("maxPage").value = maxPage;
				
				divHtml+= "<div class=\"pagination\">";
					divHtml+= "<div class=\"pageleft\">";
						divHtml+= "<div class=\"pagenum\">";
						divHtml+= "<p class=\"pagenum\">第<span class=\"red\">";		
						divHtml+= "<input type=\"text\" class=\"text_nor\" id=\"pageNo\" value=\""+$("pageNum").value+"\"";
						divHtml+= "onkeydown=\"if(event.keyCode==13) {return submitNoPage()}\" size='2'";
						divHtml+= "name='pageno'  style=\"width:20px\"/></span> 页/ 共";
						divHtml+= "<span class=\"red\">"+maxPage+"</span>页，每页显示";
						divHtml+= "<input type=\"text\" value=\""+pageSize+"\" onkeydown=\"if(event.keyCode==13) {updatePageSize()}\"";
						divHtml+= "size=\"2\" style=\"width:20px\" class=\"text_nor\" id=\"showNum\" />";
						divHtml+= "条/共<span class=\"red\">"+maxRecord+"</span>条记录</p>";
						divHtml+= "</div>";
					divHtml+= "</div>";
					
					divHtml+= "<div class=\"pageright\">";
						divHtml+= "<div id=\"pagediv\" class=\"paging\">";
							divHtml+= "<span id=\"pagelist\" class=\"pagelist\"></span>";
							divHtml+= "<a id=\"first\" href=\"javascript:firstPage()\" class=\"first\" title=\"首页\">首 页</a>&nbsp;&nbsp;";
							divHtml+= "<a id=\"pre\" href=\"javascript:prePage()\" class=\"prev\" title=\"上一页\">上一页</a>&nbsp;&nbsp;";
							divHtml+= "<a id=\"next\" href=\"javascript:nextPage()\" class=\"next\" title=\"下一页\">下一页</a>&nbsp;&nbsp;";
							divHtml+= "<a id=\"last\" href=\"javascript:lastPage()\" class=\"last\" title=\"末页\">末 页</a>&nbsp;&nbsp;";
						divHtml+= "</div>";
					divHtml+= "</div>";
					
				divHtml+= "</div>";
				
			dwr.engine.setAsync(true);
				
			$("dy_area").outerHTML=divHtml;
		}
		
		//点击侧边栏过滤条件
		function chlckLeftLx(leftLx){
			var ul = document.getElementById("left_ul");
			var num = document.getElementsByName("left_a").length;

			for(var i=num-1;i>=0;i--){
		
				var obj = document.getElementsByName("left_a")[i];
				var li_id = "li_"+obj.id.replace("left_a_","");

				if($(li_id)){
					ul.removeChild($(li_id));
				}
			}
			
			dwr.engine.setAsync(false);
				
			var tableName = "";
			var colList = ["dm","mc"];
			var pk = "";
			var pkValue = "";
			var query = " order by dm ";
			
			if(leftLx == "filess"){
				tableName = "xg_xtwh_szzqssb";
			}else{
				tableName = "xg_xtwh_szzqlxb";
			}
				
			getOtherData.getTableListInfo(tableName,colList,pk,pkValue,query,function(data){
				if(data != null && data.length > 0){
				
					var li = document.createElement("li");  
						  
				    var value = "<a href=\"#\" name=\"left_a\"";
				    	value+= "id=\"left_a_0\"";
						value+= "onclick=\"clickLeftTj('');return false;\" style=\"\">";
						value+= "<span class=\"ico\"></span>";
						value+= "全部";
				    	value+= "</a>";
				    		
				    li.innerHTML = value;
				    li.id = "li_0";
				    ul.appendChild(li); 
				        
					for(var i=0;i<data.length;i++){
					
						li = document.createElement("li");  	  
				    	value = "<a href=\"#\" name=\"left_a\"";
				    	value+= "id=\"left_a_"+parseInt(i+1)+"\"";
						value+= "onclick=\"clickLeftTj('"+data[i].dm+"');return false;\" style=\"\">";
						value+= "<span class=\"ico\"></span>";
						value+= data[i].mc;
				    	value+= "</a>";
				    		
				    	li.innerHTML = value;
				    	li.id = "li_"+parseInt(i+1);
				        ul.appendChild(li); 
					}
				}
			});
			
			dwr.engine.setAsync(true);
		}
		
		//查询结果
		function searchRs(){
			allNotEmpThenGo("/xgxt/xtwhSysz.do?method=xzzqView");
		}
		
		function setLiClick(num){
		
			var li_num = $("li_num").value;
			
			for(var i=0;i<li_num;i++){
				var id = "li_"+i;
				$(id).style.background="";
			}
			
			if(num != ""){
				var id = "li_"+num;
				$(id).style.background="#dce8f8";
			}else{
				if($("li_0")){
					$("li_0").style.background="#dce8f8";
				}
			}
		}
		</script> 	
	</head>
	<body onload="setLiClick('')" >
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>下载专区</a>
			</p>
		</div>
		<!-- 标题 end-->
		<html:form action="/xtwhSysz">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<a href="xtwhSysz.do?method=xzzqView" target="framecon" id="abq"></a>
			<input type="hidden" name="filess" id="filess" value="${filess }"/>
			<input type="hidden" id="pageRsSize" name="pageRsSize" value="${pagesInfo.pageSize }"/>
			<input type="hidden" id="currentPage" name="currentPage" value="${pagesInfo.currentPage }"/>
			<input type="hidden" id="maxPage" name="maxPage" value="${pagesInfo.maxPage }"/>
			<input type="hidden" id="maxRecord" name="maxRecord" value="${pagesInfo.maxRecord }"/>
			<input type="hidden" id="pageNum" name="pageNum" value="1"/>

			<!-- 隐藏域 end-->
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#"
								onclick="returnHomPage('');return false;"
								class="btn_fh">返回</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				<!-- 过滤条件 -->
				<div class="searchtab">
					<table width="100%" border="0">
						<tbody>
							<!-- 第一行 -->
							<tr>
								<th width="10%">
			              			文件名
			              		</th>
			         			<td>
			         				<input type="text" name="searchModel.input_mhcx" id="input_mhcx" value="${searchTj.input_mhcx }" 
										style="width: 60%" onkeypress="if(pressEnter(event)){searchRs();return false;}"
										onfocus="displayMsgDiv('input_filemc_msg')" 
										onblur="hideMsgDiv('input_filemc_msg')"/>
										
									<div id="input_filemc_msg" class="hide" style="left: 90px;top: 80px;">
										<div class="prompcon" style="width: 250px">
											<p>可以录入多个文件名，以半角空格区分</p>	
										</div>
									</div>
									
									<input type="hidden" name="go" value="a"/>
									<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">
										查 询
									</button>
			         			</td>
							</tr>
						</tbody>
					</table>
				</div>
				<!-- 过滤条件 end-->
			</div>
			<!-- 多功能操作区 end-->
			
			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<!-- From内容 -->
				<table align="center" width="100%">
					<tr>
						<td width="20%" valign="top" style="overflow-x: auto;">
							<!-- 侧边栏 -->
							<div class="listbox">
								<input type="radio" style="cursor: hand" name="left_lx"
									onfocus="this.blur();" checked="checked"
									onclick="chlckLeftLx(this.value);" value="filess"/>文件所属									
								<input type="radio" style="cursor: hand" name="left_lx"
									onfocus="this.blur();"
									onclick="chlckLeftLx(this.value);" value="filelx"/>文件类型
									
								<div class="titlelist" style="height: 400px;">
									<ul id="left_ul">
										<%int count = 0; %>
										<logic:iterate id="ss" name="sslxList" indexId="index">
											<li id="li_${index}" class="Child">
												<logic:equal name="index" value="0">
													<a href="#" name="left_a" id="left_a_${index}" onclick="setLiClick('');clickLeftTj('');return false;" style=""><span class="ico"></span>全部</a>
												</logic:equal>
												<logic:notEqual name="index" value="0">
													<a href="#" name="left_a" id="left_a_${index}" onclick="setLiClick('${index}');clickLeftTj('${ss.dm }');return false;" style=""><span class="ico"></span>${ss.mc}</a>
												</logic:notEqual>
											</li>
											<% count++; %>
										</logic:iterate>
									</ul>
									<input type="hidden" id="ssdm" name="ssdm" value=""/>
									<input type="hidden" id="lxdm" name="lxdm" value=""/>
									<input type="hidden" id="li_num" name="li_num" value="<%=count %>"/>
								</div>
							</div>
							<!-- 侧边栏 end-->
						</td>
						<td width="80%" valign="top" style="position: relative;">
							<div id="dy_area">
								<span class="formbox">
									<table class="dateline" width="100%" id="showDiary">
										<!-- 标题 -->
								    	<thead>
											<tr>
												<logic:iterate id="tit" name="topTr" offset="2" length="1">
													<td width="30%">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												<logic:iterate id="tit" name="topTr" offset="3">
													<td width="20%">
														<bean:write name="tit" property="cn" />
													</td>
												</logic:iterate>
												<td width="20%">
													操作
												</td>
								      		</tr>
										</thead>
										<!-- 标题 end-->
										<!-- 内容 -->
										<logic:equal name="hadRs" value="yes">
								    	<tbody>
									    	<logic:iterate name="rsArrList" id="rs" indexId="index">
									    		<tr onclick="rowOnClick(this);">
									    			<!-- 文件内容 -->
									    			<logic:iterate id="v" name="rs" offset="2" length="1">
														<td align="left"
															title="<logic:iterate id="sm" name="rs" offset="1" length="1">${sm }</logic:iterate>">
															${v }
														</td>
													</logic:iterate>
									    			<logic:iterate id="v" name="rs" offset="3">
														<td align="left">
															${v }
														</td>
													</logic:iterate>
													<!-- 下载操作 -->
													<logic:iterate id="v" name="rs" offset="0" length="1">
														<td width="20%">
															<logic:notEmpty name="v">
																<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${v }" target="_blank" style="color: blue">下载附件</a>
															</logic:notEmpty>
														</td>
													</logic:iterate>	
												</tr>
									    	</logic:iterate>
										</tbody>
										</logic:equal>
										<!-- 补空行 -->
										<%@ include file="/comm/noRows.jsp"%>
										<!-- 补空行 end-->
									</table>
									<!--分页显示-->
									<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xtwhSyszForm"></jsp:include>
									<script type="text/javascript">
										$('choose').className="hide";
									</script>
									<!--分页显示-->
								</span>
							</div>
						</td>
					</tr>
				</table>
			</div>
			<!-- 内容显示区开始 end-->
			
			
<%--			<div class="toolbox" style="float:right;width: <%=width%>%">--%>
<%--			--%>
<%--				<!-- 按钮 -->--%>
<%--				<div class="buttonbox">--%>
<%--					<ul>--%>
<%--						<li>--%>
<%--							<a href="#"--%>
<%--								onclick="returnHomPage('');return false;"--%>
<%--								class="btn_fh">返回</a>--%>
<%--						</li>--%>
<%--					</ul>--%>
<%--				</div>--%>
<%--				<!-- 按钮 end-->	--%>
<%--				--%>
<%--				<!-- 过滤条件 -->--%>
<%--				<div class="searchtab">--%>
<%--					<table width="100%" border="0">--%>
<%--						<tfoot>--%>
<%--							<tr>--%>
<%--								<td colspan="10">--%>
<%--									<div class="btn">--%>
<%--										<input type="hidden" name="go" value="a"/>--%>
<%--										<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;">--%>
<%--											查 询--%>
<%--										</button>--%>
<%--										<button type="button" class="btn_cz" id="btn_cz" --%>
<%--											onclick="czSearchCond('filelx-filemc');">--%>
<%--											重 置--%>
<%--										</button>--%>
<%--									</div>--%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</tfoot>--%>
<%--						<tbody>--%>
<%--							<!-- 第一行 -->--%>
<%--							<tr>--%>
<%--								<th>--%>
<%--									文件类型--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:select property="filelx" style="" styleId="filelx">--%>
<%--										<html:options collection="filelxList" property="dm" labelProperty="mc" />--%>
<%--									</html:select>								--%>
<%--								</td>--%>
<%--								<th>--%>
<%--									文件名--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<html:text property="filemc" style="" maxlength="20" styleId="filemc"--%>
<%--										onkeypress="if(pressEnter(event)){searchRs();return false;}"/>--%>
<%--								</td>--%>
<%--								<th>--%>
<%--								--%>
<%--								</th>--%>
<%--								<td>--%>
<%----%>
<%--								</td>--%>
<%--							</tr>--%>
<%--						</tbody>--%>
<%--					</table>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--			<!-- 过滤条件 end-->--%>
<%--			--%>
<%--			<!-- 侧边栏-->--%>
<%--			<div class="menulist">--%>
<%--				<div class="menutitle">--%>
<%--    				<h3>--%>
<%--    					<span class="title">文件所属</span>	--%>
<%--      				</h3>--%>
<%--					<div class="CNLTreeMenu1" id="CNLTreeMenu1" style="height: 400px">--%>
<%--						<ul>--%>
<%--							<logic:iterate id="ss" name="sslxList" indexId="index">--%>
<%--								<li id="li${ss.dm}">--%>
<%--									<span>--%>
<%--										<logic:equal name="index" value="0">--%>
<%--											<a href="#" onclick="clickSs('');return false;" style="">全部</a>--%>
<%--										</logic:equal>--%>
<%--										<logic:notEqual name="index" value="0">--%>
<%--											<a href="#" onclick="clickSs('${ss.dm }');return false;" style="">${ss.mc}</a>--%>
<%--										</logic:notEqual>--%>
<%--									</span>--%>
<%--								</li>--%>
<%--							</logic:iterate>--%>
<%--						</ul>--%>
<%--					</div>--%>
<%--				</div>--%>
<%--			</div>--%>
<%--			<!-- 侧边栏 end-->--%>
<%--			--%>
<%--			<div class="formbox"  style="float:right;width: <%=width%>%">--%>
<%--				<h3 class="datetitle_01">--%>
<%--			    <span>--%>
<%--			    	提示&nbsp;&nbsp;--%>
<%--			    	<logic:empty name="rsArrList">--%>
<%--						<font color="red">未找到任何记录！</font> --%>
<%--			 		</logic:empty>--%>
<%--			 		<logic:notEmpty name="rsArrList">--%>
<%--			 		 	<font color ="blue">鼠标移至文件名称处可查看文件说明</font>--%>
<%--			 		</logic:notEmpty>--%>
<%--			    </span>--%>
<%--			    </h3>--%>
<%--				<!-- 结果集 -->--%>
<%--				<table summary="" class="dateline tablenowrap" width="100%">--%>
<%--					<!-- 标题 -->--%>
<%--			    	<thead>--%>
<%--						<tr>--%>
<%--							<logic:iterate id="tit" name="topTr" offset="2">--%>
<%--								<td>--%>
<%--									<bean:write name="tit" property="cn" />--%>
<%--								</td>--%>
<%--							</logic:iterate>--%>
<%--							<td>--%>
<%--								操作--%>
<%--							</td>--%>
<%--			      		</tr>--%>
<%--					</thead>--%>
<%--					<!-- 内容 -->--%>
<%--					<logic:equal name="hadRs" value="yes">--%>
<%--			    	<tbody>--%>
<%--				    	<logic:iterate name="rsArrList" id="rs" indexId="index">--%>
<%--				    		<tr onclick="rowOnClick(this);">--%>
<%--				    			<!-- 文件内容 -->--%>
<%--				    			<logic:iterate id="v" name="rs" offset="2" length="1">--%>
<%--									<td align="left" title="<logic:iterate id="sm" name="rs" offset="1" length="1">${sm }</logic:iterate>">--%>
<%--										${v }--%>
<%--									</td>--%>
<%--								</logic:iterate>--%>
<%--				    			<logic:iterate id="v" name="rs" offset="3">--%>
<%--									<td align="left">--%>
<%--										${v }--%>
<%--									</td>--%>
<%--								</logic:iterate>--%>
<%--								<!-- 下载操作 -->--%>
<%--								<logic:iterate id="v" name="rs" offset="0" length="1">--%>
<%--									<td>--%>
<%--										<logic:notEmpty name="v">--%>
<%--											<a href="czxxDtjsDyxx.do?method=downLoadFile&dir=${v }" target="_blank" style="color: blue">下载附件</a>--%>
<%--										</logic:notEmpty>--%>
<%--									</td>--%>
<%--								</logic:iterate>	--%>
<%--							</tr>--%>
<%--				    	</logic:iterate>--%>
<%--					</tbody>--%>
<%--					</logic:equal>--%>
<%--					<!-- 补空行 -->--%>
<%--					<%@ include file="/comm/noRows.jsp"%>--%>
<%--					<!-- 补空行 end-->--%>
<%--				</table>--%>
<%--				<!--分页显示-->--%>
<%--				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=xtwhSyszForm"></jsp:include>--%>
<%--				<script type="text/javascript">--%>
<%--					$('choose').className="hide";--%>
<%--				</script>--%>
<%--				<!--分页显示-->--%>
<%--			</div>--%>
			<!-- 高级查询 -->
			<%@ include file="/comm/search/searchInfo.jsp"%>
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>