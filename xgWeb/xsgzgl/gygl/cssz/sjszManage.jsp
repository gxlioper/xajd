<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xsgzgl.gygl.cssz.CsszForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script language="javascript">
			function saveData(url,sfqy){
				var form = document.forms[0];
				var kssj = jQuery('#kssj').val();
				var jssj = jQuery('#jssj').val();
				if(jQuery('#kssj').val()=="" && jQuery('#jssj').val()=="" && sfqy=="yes"){
					alertInfo('开始时间与结束时间，至少填写一项，才能开启“取消入住”按钮！');
					return false;
				}
				
				if(jQuery('#kssj').val()!="" && jQuery('#jssj').val()!="" && jQuery('#kssj').val()>= jQuery('#jssj').val()){
					alertError('结束时间必须大于开始时间！');
					return false;
				}
				url += "&kssj=" + kssj;
				url += "&jssj=" + jssj;
				
				form.action = url;
				form.submit();
			}
			
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('gyglnew_cssz_sjsz.do');
			}
			
			// 批量操作
			function batchOption(sfqy){
				var size = jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").size();
				var idList = "";        

				jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").each(function(){
					//alert(this.val());
					//alert(jQuery(this).val())
					idList += jQuery(this).val() + ',';        
					// alert(jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").val());
				});
				//alert(jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").val());
				//alert(idList);
				if(size == 0){
					alertInfo('请选择要操作记录！');
					return false;
				}
				
				if('yes' == sfqy){
					//tipsWindown("启用时间设置","id:tempDiv","350","150","true","","true","id");
					//var form = document.forms[0];
					var url = "gyglnew_cssz.do?method=qySjsz&idList="+idList;
					//form.action = url;
					//form.submit();
					showDialog("启用时间设置",450,320,url);
				}else{
					var ts = "所选" + size + "条记录开启状态设为否，确认选择？"; 
					confirmInfo(ts, function(t){
						if(t == 'ok'){
							saveData('gyglnew_cssz.do?method=sjszManage&sfqy=no&doType=xg');
						}
					});	
				}
			}
		</script>
	</head>
	<body>
		<html:form action="/gyglnew_cssz" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help" >
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					此功能用途为设置“床位入住管理”中的“取消入住”按钮开放使用权限，开启状态为“是”且在设置的时间段内，<br/>该年级<bean:message key="lable.xsgzyxpzxy" />可进行“取消入住”操作；
					勾选多个年级<bean:message key="lable.xsgzyxpzxy" />可批量设置“启用”或“不启用”；
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="batchOption('yes');return false;" class="btn_shtg">启用</a></li>
						<li><a href="#" onclick="batchOption('no');return false;" class="btn_shbtg">不启用</a></li>
					</ul>
				</div>
				</logic:equal>
			</div>
				<logic:equal name="superSearch" value="yes">
			     	<%@ include file="/comm/search/superSearchArea.jsp"%>
			    </logic:equal>
				<div class="formbox">
					<h3 class="datetitle_01">
						<span>
							查询结果&nbsp;&nbsp;
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
								<tr onclick="rowOnClick(this);" style="cursor:hand">
									<td>								
										<input type="checkbox" name="primarykey_cbv" id="pkV"
											value="<logic:iterate id="v" name="s" offset="0" length="1">${v }</logic:iterate>" />
										
									</td>
									<logic:iterate id="v" name="s" offset="1" length="8">
										<td>${v }</td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							CsszForm form = (CsszForm)request.getAttribute("gyglnewCsszForm");
							int pageSize = form.getPages().getPageSize();
							if(rsNum < pageSize){
								for(int i=0; i<(pageSize-rsNum); i++){
							%>
							<tr>
								<td>
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewCsszForm"></jsp:include>
				<!--分页显示-->
			</div>
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请选择“取消入住”按钮可操作时间</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<span class="red">*</span>开始时间
								</th>
								<td>
									<input type="text" id="kssj" name="kssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')"/>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>结束时间
								</th>
								<td>
									<input type="text" id="jssj" name="jssj" onkeypress="onlyBackSpace(this,event);"
										onclick="return showCalendar(this.id,'yyyy-MM-dd HH:mm:ss')"/>
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
										<button type="button" name="保存" onclick="saveData('gyglnew_cssz.do?method=sjszManage&sfqy=yes&doType=xg','yes');return false;">
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
