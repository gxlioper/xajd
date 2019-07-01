<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.gygl.gyjldmgl.GyjldmglForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script language="javascript">
		//公寓纪律类别代码div操作 增加、修改
		var doType;
		function jllbDiv(type,topMsg){
			$("jllbdm").readOnly="";
			$("jllbdm").value="";
			$("jllbmc").value="";
			doType=type;
			var title = topMsg;
			if(type!="add"){
				if(curr_row != null){
					jllbdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				//tipsWindown(topMsg,"id:tempDiv","350","220","true","","true","id");
				$("jllbdm").value=jQuery(curr_row.cells[1]).text();
				$("jllbmc").value=jQuery(curr_row.cells[2]).text();
				$("jldldm").value=curr_row.getElementsByTagName('input')[1].value;
				$("jllbkf").value=jQuery(curr_row.cells[3]).text();
				
				$("jllbdm").readOnly="true";
				jQuery("#jllbdm").css("color","grey");

				url="gyglnew_gyjldmgl.do?method=gyjllbZjxg&doType="+doType+"&jllbdm=" + jQuery(curr_row.cells[1]).text(); 
				url = url + "&jllbmc=" +jQuery(curr_row.cells[2]).text();
				url = url + "&jldldm=" +curr_row.getElementsByTagName('input')[1].value;
				url = url + "&jllbkf=" +jQuery(curr_row.cells[3]).text();
				
			}else{	
				if(type=="add"){
					url="gyglnew_gyjldmgl.do?method=gyjllbZjxg&doType="+doType;
				}		
				//tipsWindown(topMsg,"id:tempDiv","350","220","true","","true","id");
			}
			showDialog(title,420, 200, url);
		}
		function jllbDivSave(){
			if(doType!="update"){
				if($("jllbdm").value==""){
					alertInfo("请输入纪律类别代码！");
					return false;
				}
			}
			if($("jllbmc").value.trim()==""){
				alertInfo("请输入纪律类别名称！");
				return false;
			}
			if($("jllbkf").value.trim()==""){
				alertInfo("请输入纪律类别扣分数！");
				return false;
			}
			if($("jldldm").value.trim()==""){
				alertInfo("请选择纪律大类！");
				return false;
			}
			allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjllbManage&doType='+doType);
		}
			
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjllbManage');
			}
			

			//删除
			function deljllb(doType){
				var jllbdm;
				if(curr_row != null){
					jllbdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				confirmInfo("确定删除吗？",function(data){
					if("ok"==data){
						allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjllbManage&doType=del&jllbdm='+jllbdm);
					}
					return false;
				});
			}

			function newChgCode(obj){
				allNotEmpThenGo(obj.id);
			}
		</script>
	</head>
	<body >
		<html:form action="/gyglnew_gyjldmgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<button type="button" style="display:none" id="search_go" onclick="searchRs()">
			</button>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
			</div>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="jllbDiv('add','增加纪律类别');return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="jllbDiv('update','修改纪律类别');return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="deljllb();return false;" class="btn_sc">删除</a></li>
					</ul>
				</div>
				</logic:equal>
			</div>
			    <div class="compTab" id="card">
				<div class="comp_title"><ul>
									<li>
										<a href="#" onclick="newChgCode(this);return false;" id="gyglnew_gyjldmgl.do?method=gyjldlManage">
											<span>
												<logic:equal value="11799" name="xxdm">
													奖惩大类
												</logic:equal>
												<logic:notEqual value="11799" name="xxdm">
													纪律大类
												</logic:notEqual>
											</span>
										</a>
									</li>
									<li class="ha">
										<a href="#" onclick="newChgCode(this);return false;" id="gyglnew_gyjldmgl.do?method=gyjllbManage">
											<span>
												<logic:equal value="11799" name="xxdm">
													奖惩类别
												</logic:equal>
												<logic:notEqual value="11799" name="xxdm">
													纪律类别
												</logic:notEqual>
											</span>
										</a>
									</li>
									<li>
										<a href="#" onclick="newChgCode(this);return false;" id="gyglnew_gyjldmgl.do?method=gyjlcfManage">
											<span>处分类别</span> 
										</a>
									</li>
				</div>
			    
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;
						</span>
					</h3>
					<table width="99%" id="rsTable" class="dateline">
						<thead>
							<tr align="center" style="cursor:hand">
								<td style="display: none;">
									<input type="checkbox" name="all" value="all" onclick="chec()"/>
								</td>
								<logic:iterate id="tit" name="topTr" offset="0">
									<td id="${tit }" onclick="tableSort_hc(this,1)">
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
								<td style="display: none;">
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
							<logic:iterate name="rs" id="s" indexId="ind">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none;">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="1" length="1">${v }</logic:iterate>" />
										
									</td>
									<logic:iterate id="v" name="s" offset="2" length="5">
										<td><bean:write name="v" format="ture"/></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							GyjldmglForm form = (GyjldmglForm)request.getAttribute("gyjldmglForm");
							int pageSize = form.getPages().getPageSize();
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td style="display: none;">
									<input type="checkbox" value="" disabled="disabled"></input>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyjldmglForm"></jsp:include>
		   	 	<script type="text/javascript">
					$('choose').className="hide";
				</script>
				<!--分页显示-->
			</div>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span></span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_select_xn">
								<th>
									<span class="red">*</span>
									<logic:equal value="11799" name="xxdm">
										奖惩类别代码
									</logic:equal>
									<logic:notEqual value="11799" name="xxdm">
										纪律类别代码
									</logic:notEqual>
								</th>
								<td>
									<input type="text" id="jllbdm" name="jllbdm" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
										<logic:equal value="11799" name="xxdm">
											奖惩类别名称
										</logic:equal>
										<logic:notEqual value="11799" name="xxdm">
											纪律类别名称
										</logic:notEqual>										
								</th>
								<td>
									<input type="text" id="jllbmc" name="jllbmc" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>
										<logic:equal value="11799" name="xxdm">
											奖惩类别扣分
										</logic:equal>
										<logic:notEqual value="11799" name="xxdm">
											纪律类别扣分
										</logic:notEqual>
								</th>
								<td>
									<input type="text" id="jllbkf" name="jllbkf" onkeyup="value=value.replace(/[^\d]/g,'') " maxlength="100"/>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>
										<logic:equal value="11799" name="xxdm">
											所属奖惩大类
										</logic:equal>
										<logic:notEqual value="11799" name="xxdm">
											所属纪律大类
										</logic:notEqual>										
								</th>
								<td>
									<html:select property="jldldm" styleId="jldldm" style="width:150px;">
										<html:option value=""></html:option>
										<html:options collection="jldlList" property="jldldm" labelProperty="jldlmc" />
									</html:select>
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
										<button type="button" name="保存" onclick="jllbDivSave()">
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
