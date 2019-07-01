<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		
		<script language="javascript" src="js/String.js"></script>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script language="javascript">
		//分配
		function gyglryFp(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			if(n==1 ){
				var pkValue = jQuery("input[name='checkVal']:checked").eq(0).val();
				var pks=pkValue.split("!$$!");
				var lddm=pks[0];
				var ch=pks[1].replace("#","").trim();
				var qsh=pks[2].replace("#","").trim();
				var url = 'gyglnew_gyglry.do?method=gyglryFp';
				url+="&lddm="+lddm;
				if(ch!=""){url+="&fp_ch="+ch;};
				if(qsh!=""){url+="&fp_qsh="+qsh;};
				url+="&old_xh="+jQuery("input[name='checkVal']:checked+input").eq(0).val();
				url+="&pkValue="+pkValue;
				//showTopWin(url,800,600);
				showDialog('管理人员分配', 800, 525, url);
				
			}else  {
				alertInfo('请选择一行');
				return false;
			}
		}
		//取消分配
		function gyglryQxfp(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			if(n==1 ){
				if(jQuery("input[name='checkVal']:checked+input").eq(0).val()==""){
					alertInfo("该条记录还未分配，不需取消！");
					return false;
				}
				var oldxh =jQuery("input[name='checkVal']:checked+input").eq(0).val();
				showDialog('', 350,220, 'gyglnew_gyglry.do?method=gyglryQxfp&oldxh='+oldxh);
			}else  {
				alertInfo('请选择一行');
				return false;
			}
		}

		function gyglryQxfp_save(){
			var pkValue = jQuery("input[name='checkVal']:checked").eq(0).val();
			var pks=pkValue.split("!$$!");
			var lddm=pks[0];
			var ch=pks[1].replace("#","").trim();
			var qsh=pks[2].replace("#","").trim();
			var url = 'gyglnew_gyglry.do?method=gyglryManage&doType=qxfp';
			url+="&lddm="+lddm;
			if(ch!=""){url+="&fp_ch="+ch};
			if(qsh!=""){url+="&fp_qsh="+qsh};
			url+="&old_xh="+jQuery("input[name='checkVal']:checked+input").eq(0).val();
			url+="&pkValue="+pkValue;
			confirmInfo("确定取消分配吗？",function(ok){
				if(ok=="ok"){
					allNotEmpThenGo(url);
				}
			})
		}
		
		function searchRs(){
			allNotEmpThenGo('gyglnew_gyglry_gyglry.do');
		}

		function modi(url,h,w){
			if(curr_row != null){
				showOpenWindow(url + '&pkValue='+curr_row.getElementsByTagName('input')[0].value,h,w);
				return true;
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}

		//同步干部数据至思政队伍--贵州大学 
      	function gyglryTbgbxx(){
      		jQuery.post('gyglnew_gyglry.do?method=gyglryTbgbxx',{},function(data){
      			if ("true" == data){
      				alertInfo('同步数据成功!');
      			} else {
      				alertInfo('同步数据失败!');
      			}
      		});
      	}
		
		function qszwhExportConfig() {
		//DCCLBH导出功能编号,执行导出函数 
		customExport("gyglnew_gyglry_gyglry.do", qszwhExportData);
		}
		
	
		
		// 导出方法
		function qszwhExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_gyglry.do?method=qszwhExportData&dcclbh=" + "gyglnew_gyglry_gyglry.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		jQuery(function(){
			var btndr=jQuery("#btn_dr");
			//导入
			if(btndr!=null){
				btndr.click(function () {
					//调用通用的导入function，参数是导入功能模块代码。
					toImportDataNew("IMPORT_N380501_GYGLRY");
					return false;
				});
			}
		});
		//进入导入页面方法
		function importGygly(){
			var url = "gyglnew_gyglry.do?method=gyglxDr";
			var title = "导入";
			showDialog(title, 770, 350, url);
		}
		</script>
	</head>
	<body >

		<html:form action="/gyglnew_gyglry" method="post">
			<div class="tab_cur" >
				<p class="location">
					<em>您的当前位置:</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
				</p>
			</div>
			
			<!-- 提示信息 end-->
			<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					结果集中若层号、寝室号值为“#”则维护楼长信息；若寝室号为“#”则维护层长信息；其他维护寝室长信息。
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<!-- 模块类型 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="gyglryFp();return false;" class="btn_xg"> 分配 </a></li>
						<li><a href="#" onclick="gyglryQxfp();return false;" class="btn_sc"> 取消分配 </a></li>
						<%--<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入数据</a></li>	--%>
						<logic:equal value="10657" name="xxdm">							
							<li><a href="#" onclick="gyglryTbgbxx();return false;" class="btn_sx"> 同步干部数据 </a></li>
						</logic:equal>
						
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">导出数据</a></li>
						--%>
						<logic:equal name="xxdm" value="10868">
						<li><a href="#" class="btn_dr" id="btn_dr">导入</a></li>	
						</logic:equal>
						<!-- 温州大学 -->
						<logic:equal name="xxdm" value="10351">	
						<li><a href="#" class="btn_dr" id="btn_dr">导入</a></li>	
						</logic:equal>
						<!-- 武昌首义学院 -->
						<logic:equal name="xxdm" value="12309">
						<li><a href="#" class="btn_dr" onclick="importGygly()">导入</a></li>
						</logic:equal>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="qszwhExportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<font color="blue"></font>
					</span>
				</h3>
				<logic:notEmpty name="rsList">
				<div class="con_overlfow" >
					<table summary="" class="dateline" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
								<td>
									<input type="checkbox" name="all" value="all" onclick="chec_page()" />
								</td>
								<logic:iterate id="tit" name="topTr" indexId="index">
									<td onclick="tableSort(this)" >
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:equal value="10466" name="xxdm">
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr onclick="rowMoreClick(this,'',event);"
											style="cursor: hand" ondblclick="">
											<td>
												<input type="checkbox" name="checkVal" id="pkV"
													value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="6" length="1">${v}</logic:iterate>" />
											</td>
											<logic:iterate id="v" name="s" offset="1" length="8">
												<td nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<td onmouseout="BatAlert.showInfo();"
												onmouseover="BatAlert.showInfo('<%=((String[]) s)[9] == null ? ""
									: ((String[]) s)[9].replace("\r", "")
											.replace("\n", "")%>');">
												<%
													String[] strs = (String[]) s;
																	if (strs[9] != null && strs[9].length() > 9) {
												%><%=strs[9].substring(0, 10)%>...<%
													} else {
												%><%=strs[9]%>
												<%
													}
												%>
											</td>
										</tr>
									</logic:iterate>
								</logic:equal>
								<logic:notEqual value="10466" name="xxdm">
									<logic:iterate name="rsList" id="s" indexId="index">
										<tr onclick="rowMoreClick(this,'',event);"
											style="cursor: hand" ondblclick="">
											<td>
												<input type="checkbox" name="checkVal" id="pkV"
													value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" />
												<input type="hidden"
													value="<logic:iterate id="v" name="s" offset="6" length="1">${v}</logic:iterate>" />
											</td>
											<logic:iterate id="v" name="s" offset="1" length="9">
												<td nowrap>
													<bean:write name="v" />
												</td>
											</logic:iterate>
											<td onmouseout="BatAlert.showInfo();"
												onmouseover="BatAlert.showInfo('<%=((String[]) s)[10] == null ? ""
									: ((String[]) s)[10].replace("\r", "")
											.replace("\n", "")%>');">
												<%
													String[] strs = (String[]) s;
																	if (strs[10] != null && strs[10].length() > 10) {
												%><%=strs[10].substring(0, 10)%>...<%
													} else {
												%><%=strs[10]%>
												<%
													}
												%>
											</td>
										</tr>
									</logic:iterate>
								</logic:notEqual>
							</tbody>
					</table>
					<!--分页显示-->
			   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=gyglnewGyglryForm"></jsp:include>
					<!--分页显示-->
					</div>
				</logic:notEmpty>
			</div>
			
			
			<div id="tempDiv" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>管理人员分配</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									<font color="red">*</font>离任时间
								</th>
								<td>
									<html:text property="rzjsrq" styleId="rzjsrq" onkeypress="onlyBackSpace(this,event);" 
										onclick="return showCalendar(this.id,'yyyy-MM-dd')"></html:text>
								</td>
							</tr>
<%--							<tr>--%>
<%--								<th>--%>
<%--									备注--%>
<%--								</th>--%>
<%--								<td>--%>
<%--									<input type="text" name="bz"  maxlength="100"/>--%>
<%--								</td>--%>
<%--							</tr>--%>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									
									<div class="btn">
										<button type="button" name="保存"  onclick="gyglryQxfp_save()">
											保存 
										</button>
										<button type="button" name="取消"  onclick="closeWindown();return false;">
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
			<script>
				alertInfo('${message}');
			</script>
		</logic:present>
	</body>
</html>
