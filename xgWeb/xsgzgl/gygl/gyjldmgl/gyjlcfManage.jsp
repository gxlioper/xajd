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
		//公寓处分类别代码div操作 增加、修改
		var doType;
		function jlcfDiv(type,topMsg){
			$("cflbdm").readOnly="";
			$("cflbdm").value="";
			$("cflbmc").value="";
			doType=type;
			var title = topMsg;
			if(type!="add"){
				if(curr_row != null){
					cflbdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}	
				//tipsWindown(topMsg,"id:tempDiv","350","200","true","","true","id");			
				//$("cflbdm").value=jQuery(curr_row.cells[1]).text();
				//$("cflbmc").value=jQuery(curr_row.cells[2]).text();
				//$("cflbdm").readOnly="true";
				//jQuery("#cflbdm").css("color","grey");
				url="gyglnew_gyjldmgl.do?method=gyjlcfZJxg&doType="+doType+"&cflbdm=" + jQuery(curr_row.cells[1]).text(); 
				url = url + "&cflbmc=" +jQuery(curr_row.cells[2]).text();
			}else{
				//tipsWindown(topMsg,"id:tempDiv","350","200","true","","true","id");
				if(type=="add"){
					url="gyglnew_gyjldmgl.do?method=gyjlcfZJxg&doType="+doType;
				}
			}
			showDialog(title, 350, 200, url);
		}
		function cflbDivSave(){
			if(doType!="update"){
				
				if($("cflbdm").value==""){
					alertInfo("请输入处分类别代码！");
					return false;
				}
			}
			
			if($("cflbmc").value.trim()==""){
				alertInfo("请输入处分类别名称！");
				return false;
			}
			
			allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjlcfManage&doType='+doType);
		}
			
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjlcfManage');
			}
			

			//删除
			function delcflb(doType){
				var cflbdm="";
				if(curr_row != null){
					cflbdm=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				confirmInfo("确定删除吗？",function(data){
					if("ok"==data){
						allNotEmpThenGo('gyglnew_gyjldmgl.do?method=gyjlcfManage&doType=del&cflbdm='+cflbdm);
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
						<li><a href="#" onclick="jlcfDiv('add','增加处分类别');return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="jlcfDiv('update','修改处分类别');return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="delcflb();return false;" class="btn_sc">删除</a></li>
					</ul>
				</div>
				</logic:equal>
			</div>
			    <div class="compTab" id="card">
				<div class="comp_title"><ul>
									<li><a href="#" onclick="newChgCode(this);return false;" id="gyglnew_gyjldmgl.do?method=gyjldlManage"><span>纪律大类</span> </a>
									<li><a href="#" onclick="newChgCode(this);return false;" id="gyglnew_gyjldmgl.do?method=gyjllbManage"><span>纪律类别</span> </a>
									<li class="ha"><a href="#" onclick="newChgCode(this);return false;" id="gyglnew_gyjldmgl.do?method=gyjlcfManage"><span>处分类别</span> </a>
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
							<logic:iterate name="rs" id="s">
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td style="display: none;">								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										
									</td>
									<logic:iterate id="v" name="s" offset="1" length="2">
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
									<span class="red">*</span>处分类别代码
								</th>
								<td>
									<input type="text"  onkeypress="if(event.keyCode==13){alert(event.keyCode);return false;}"  id="cflbdm" name="cflbdm" maxlength="100"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>处分类别名称
								</th>
								<td>
									<input type="text"  onkeypress="if(event.keyCode==13){return false;}"  id="cflbmc" name="cflbmc" maxlength="100"/>
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
										<button type="button" name="保存" onclick="cflbDivSave();return false;">
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
