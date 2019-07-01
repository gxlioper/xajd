<%@ page language="java" contentType="text/html; charset=GBK"%>
<jsp:directive.page import="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：zhangh -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%--<%@ include file="/syscommon/pagehead_V4.ini"%>
		--%><%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="dwr/interface/getCommGygl.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>

		<script type='text/javascript' src="js/xgutil.js"></script>
		<script language="javascript" defer="defer">
		//查询结果集
		function searchRs(){
			allNotEmpThenGo('gyglnew_gybxgl_gybxgl_stu.do');
		}

		function add(){
			var userType = jQuery("#userType").val();
			if(userType == "stu"){ 
				jQuery.post('gyglnew_gybxgl.do?method=viewXsxx',{},function(data){
					var lddm = data.lddm;
		
					if(!lddm){
						alertError("您尚未入住！");
					}else{
						//showTopWin("gyglnew_gybxgl.do?method=gybxglAdd",800,600);
						showDialog("新增报修申请", 760, 430, "gyglnew_gybxgl.do?method=gybxglAdd");
					}					
				},'json');
			}else{
				showDialog("新增报修申请", 760, 430, "gyglnew_gybxgl.do?method=gybxglAdd");
			}
		}

		// 修改操作
		function update(url){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size != 1){
				alertInfo('请选择一行要操作记录！');
				return false;
			}

			var flag = false;
			
			jQuery("td:has(input[type='checkbox'][name='primarykey_checkVal']:checked) > input[id*='clzt_']").each(function(){
				if("未处理" != jQuery(this).val()){
					flag = true;
				}
			});
			
			if(flag){
				alertInfo("只能修改'未处理'的记录！");
			}else{
				url += "&pk="+curr_row.getElementsByTagName('input')[0].value;
				url += "&xh="+curr_row.getElementsByTagName('input')[1].value;
				//showTopWin(url,800,600);
				showDialog("修改报修申请", 760, 430, url);
			}
		}	
		//查看
		function view(url){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size != 1){
				alertInfo('请选择一行要操作记录！');
				return false;
			}
			url += "&pk="+curr_row.getElementsByTagName('input')[0].value;
			url += "&xh="+curr_row.getElementsByTagName('input')[1].value;
			showDialog("查看报修申请", 760, 500, url);
			//showTopWin(url,800,600);
		}
		
		// 评价操作
		function pjUpdate(){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size == 0){
				alertInfo('请选择要操作记录！');
				return false;
			}
			
			var flag = false;
			
			jQuery("td:has(input[type='checkbox'][name='primarykey_checkVal']:checked) > input[id*='clzt_']").each(function(){
				if("已处理" != jQuery(this).val()){
					flag = true;
				}
			});
			
			if(flag){
				alertInfo("只能评价'已处理'的记录！");
			}else{
				//tipsWindown("满意程度调查","id:tempDiv","400","230","true","","true","id");
				var idList = "";        

				jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").each(function(){
					idList += jQuery(this).val() + ',';        
				});
				url = "gyglnew_gybxgl.do?method=gybxglStudentpj&idList="+idList;
				showDialog("满意程度调查", 400, 230, url);
			}
		}
		
		// 删除操作
		function delBatch(){
			var size = jQuery("input[type='checkbox'][name='primarykey_checkVal']:checked").size();
			
			if(size == 0){
				alertInfo('请选择要操作记录！');
				return false;
			}
			
			var flag = false;
			
			jQuery("td:has(input[type='checkbox'][name='primarykey_checkVal']:checked) > input[id*='clzt_']").each(function(){
				if("未处理" != jQuery(this).val()){
					flag = true;
				}
			});
			
			if(flag){
				alertInfo("只能删除'未处理'的记录！");
			}else{
				batchData('primarykey_checkVal','gyglnew_gybxgl.do?method=gybxglStudent&doType=del','del');
			}
		}
		</script>
	</head>
	<body>
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>

		<html:form action="/gyglnew_gybxgl">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="search_go" name="search_go" onclick="searchRs();return false;"/>
			<!-- 隐藏域 -->

			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="add();return false;" class="btn_zj"> 新增申请 </a></li>
						</logic:equal>
						<li><a href="#" onclick="view('gyglnew_gybxgl.do?method=gybxglView');return false;" class="btn_qb"> 详细信息</a></li>
						<logic:equal value="yes" name="writeAble">
							<logic:notEqual value="1103202" name="xxdm">
								<li><a href="#" onclick="pjUpdate();return false;" class="btn_sh"> 评价 </a></li>
							</logic:notEqual>
						<li><a href="#" onclick="update('gyglnew_gybxgl.do?method=gybxglAdd');return false;" class="btn_xg"> 修改 </a></li>
						<li><a href="#" onclick="delBatch();return false;" class="btn_sc"> 删除 </a></li>
						</logic:equal>
					</ul>
				</div>
				<!-- 按钮 end-->	
			</div>
			<!-- 多功能操作区 end -->
			
			<!-- 查询结果-->
			<div class="formbox">		
				<h3 class="datetitle_01">
					<span>
						查询结果
						<logic:empty name="rsArrList">
							&nbsp;&nbsp;<font color="red">未找到任何记录！</font> 
						</logic:empty>
					</span>
				</h3>				
				<div class="con_overlfow" style="min-height: 600px">
				<table summary="" class="dateline" width="100%">
					<!-- 表头 -->
					<thead>
						<tr align="center" style="cursor:hand">
							<td width="5px">
								<input type="checkbox" id="selall" name="selall" onclick="selAll()" />
							</td>
							<logic:iterate id="tit" name="topTr" offset="0" >
								<td id="<bean:write name="tit" property="en"/>"
									onclick="tableSort(this)">
									<bean:write name="tit" property="cn" />
								</td>
							</logic:iterate>
						</tr>
					</thead>
					<!-- 表头 end-->
					<!--内容 -->
					<logic:iterate name="rsArrList" id="s" indexId="index">
					 	<tr onclick="rowOnClick(this);" style="cursor:hand" title="<logic:iterate id="v" name="s" offset="3" length="1">报修内容：${v }</logic:iterate>">
							<td align="center" width="5px">
								<logic:iterate id="v" name="s" offset="0" length="1">
									<input type="checkbox" id="pk_${index }" name="primarykey_checkVal" value="${v }"/>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="1" length="1">
									<input type="hidden" value="${v }"/>
								</logic:iterate>
								
								<logic:iterate id="v" name="s" offset="9" length="1">
									<input type="hidden" value="${v }" id="clzt_${index }"/>
								</logic:iterate>
							</td>
							
							<logic:iterate id="v" name="s" offset="2" length="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
							<logic:iterate id="v" name="s" offset="11" length="1">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
								
							<!-- 显示信息 -->
							<logic:iterate id="v" name="s" offset="4" length="${xxdm=='1103202'?6:7}">
								<td align="left">
									${v }
								</td>
							</logic:iterate>
						</tr>
					</logic:iterate>
					<%
					int rsNum = ((List)request.getAttribute("rsArrList")).size();
					int pageSize = (Integer)(request.getAttribute("pageSize"));
					if(rsNum < pageSize){
						for(int i=0; i<(pageSize-rsNum); i++){
					%>
					<tr>
						<td>
							<input type="checkbox" disabled="disabled"></input>
						</td>
						<logic:iterate id="tit" name="topTr" offset="0">
							<td>
								&nbsp;
							</td>
						</logic:iterate>
				 	</tr>
					<%}}%>
				</table>
				<!--分页-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewGybxglForm"></jsp:include>
				<!--分页end-->
				</div>
			</div>
			<!-- 查询结果 end-->
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>服务评价</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="35%">
									<span class="red">*</span>满意程度
								</th>
								<td>
									<select name="mycd">
										<option value="非常满意">非常满意</option>
										<option value="比较满意">比较满意</option>
										<option value="不满意">不满意</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>
									评价<br/><span>(限制字数200)</span>
								</th>
								<td>
									<textarea name="pj" rows="3" style="width: 90%" cols="1" onblur="chLeng(this,200);"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										"<span class="red">*</span>"为必填项
									</div>
									<div class="btn">
										<button type="button" name="保存" onclick="saveData('gyglnew_gybxgl.do?method=gybxglStudent&doType=pj','mycd');">
											保 存
										</button>
										<button type="button" name="取消" onclick="closeWindown();return false;">
											取 消
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
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