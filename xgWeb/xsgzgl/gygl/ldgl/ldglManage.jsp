<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/BatAlert.js"></script>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript">
		
		function addLdsj(){
			var url="gyglnew_ldgl.do?method=ldxxwh&doType=add";
			showDialog("增加楼栋信息", 760, 505, url);
		}

		function chec_page(){
		      for(i=0;i<document.getElementsByName("checkVal").length;i++){
		    	if(document.getElementsByName("checkVal")[i].disabled == false){
		    		document.getElementsByName("checkVal")[i].checked=document.getElementsByName("all")[0].checked;
		    	}
		      }
		}
	
		
		//修改
		function modiLdsj(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			
			//if(n>1){
				//viewTempDiv("寝室信息设置","plUpdate",410,245);
			//}else 
			if(n==1 || curr_row){
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				pkValue = encodeURIComponent(pkValue);//特殊字符URL编码
				var url = 'gyglnew_ldgl.do?method=ldxxwh&doType=update&lddm='+pkValue;
				showDialog("修改楼栋信息", 760, 505, url);
				
			}else if (null == curr_row) {
					alertInfo('请选择一行');
					return false;
			}
		}

		function delLdsj(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
				}
			}
			if(blog){
				confirmInfo("确定要删除选中的记录吗?",function(ok){
					if(ok=="ok"){
						var mklx=$("mklx").value;
						var url="gyglnew_ldgl.do?method=ldglManage";
						url+="&doType=delete";
						refreshForm(url);
						hiddenMessage(true,true);
						BatAlert.showTips('正在操作，请稍等...');						
					}
				});
			}else{
				alertInfo("没有需要删除的记录!");
				return false;
			}
		}
		
		function fpLdsj(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			var str = "";
			
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
					str+="&checkVal="+encodeURIComponent(pkV[i].value);//特殊字符URL编码
				}
			}
			if(blog){
				var url="gyglnew_ldgl.do?method=ldfpUpdateNew";
				url+="&doType=fp"+str;
				
				showDialog("分配公寓管理员", 760, 525, url);
				hiddenMessage(true,true);
			}else{
				alertInfo("没有需要分配的记录!");
				return false;
			}
		}
		function fpLdsj_12861(){
			var pkV=document.getElementsByName("checkVal");
			blog=false;
			var str = "";
			
			for(i=0;i<pkV.length;i++){
				if(pkV[i].checked && !pkV[i].disabled){
					blog=true;
					str+="&checkVal="+encodeURIComponent(pkV[i].value);//特殊字符URL编码
				}
			}
			if(blog){
				var url="gyglnew_ldgl.do?method=ldfpUpdate_12861";
				url+="&doType=fp"+str;
				
				showDialog("分配公寓辅导员", 760, 525, url);
				hiddenMessage(true,true);
			}else{
				alertInfo("没有需要分配的记录!");
				return false;
			}
		}
		
		function searchRs(){
			allNotEmpThenGo('gyglnew_ldgl_ldgl.do');
		}

		function modi(url,h,w){
			if(curr_row != null){
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				pkValue = encodeURIComponent(pkValue);//特殊字符URL编码
				showDialog("查看楼栋信息",h,w,url + '&pkValue='+pkValue);
				return true;
			}else{
				alertInfo('请选择要操作的数据行！');
				return false;
			}
		}

		//寝室批量维护
		function qsplwh(){
			var pkValue=document.getElementsByName("checkVal");
			var n=0;
			for(i=0;i<pkValue.length;i++){
				if(pkValue[i].checked){
					n++;
				}
			}
			
			if(n==1 || curr_row){
				var pkValue = curr_row.getElementsByTagName('input')[0].value;
				var url = 'gyglnew_ldgl.do?method=ldglQsxxplwh&lddm='+pkValue;
				showOpenWindow(url,1024,768);
			}else if (null == curr_row) {
					alertInfo('请选择一行');
					return false;
			}
		}
		
		function ldxxglExportConfig() {
			//DCCLBH导出功能编号,执行导出函数 
			customExport("gyglnew_ldgl_ldgl.do", ldxxglExportData);
		}
			
		
			
		// 导出方法
		function ldxxglExportData() {
			setSearchTj();//设置高级查询条件
			var url = "gyglnew_ldgl.do?method=ldxxglExportData&dcclbh=" + "gyglnew_ldgl_ldgl.do";//dcclbh,导出功能编号
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		</script>
	</head>
	<body>

		<html:form action="/gyglnew_ldgl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="text" id="nouse" style="display: none"/>
			<!-- 隐藏域 -->
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
					当楼栋中的床位已分配、已入住或有退宿信息时，楼栋不可删除
				</p>
				<a class="close" title="隐藏"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- 提示信息 end-->	
			<!-- 模块类型 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>						
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" onclick="addLdsj();return false;" class="btn_zj"> 增加 </a></li>
						<li><a href="#" onclick="modiLdsj();return false;" class="btn_xg"> 修改 </a></li>
						<li><a href="#" onclick="delLdsj();return false;" class="btn_sc"> 删除 </a></li>
						<%-- 
						<li><a href="#" onclick="qsplwh();return false;" class="btn_sq"> 寝室批量维护 </a></li>
						--%>
						<li><a href="#" onclick="fpLdsj();return false;" class="btn_sq"> 分配公寓管理员 </a></li>
						<logic:equal name="xxdm" value="12861">
							<li><a href="#" onclick="fpLdsj_12861();return false;" class="btn_sq"> 分配公寓辅导员 </a></li>
						</logic:equal>
						<%--<li><a href="#" class="btn_dr" onclick="impAndChkData();return false;">导入数据</a></li>--%>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="setSearchTj();configureExportData();return false;">导出数据</a></li>
						--%>
						<li><a href="#" class="btn_dc" onclick="ldxxglExportConfig();return false;">导出</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
			<div class="formbox" id="cxjg">
				<h3 class="datetitle_01">
					<span>
						查询结果&nbsp;&nbsp;<font color="blue">不可勾选的楼栋为已有床位分配或入住</font>
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
								<logic:iterate id="tit" name="topTr"   indexId="index">
									<td>
										${tit }
									</td>
								</logic:iterate>
							</tr>
						</thead>
						<tbody>
							<% String xxdm = (String) request.getAttribute("xxdm"); %>
							 <% if("12861".equals(xxdm)){ %>
								 <logic:iterate name="rsList" id="s" indexId="index">	
								<%int num = Integer.valueOf(request.getAttribute("colnum").toString())-3;%>					
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="modi('gyglnew_xxtj.do?method=xxtjDetail',1024,505);">
										<td>
											<input type="checkbox" name="checkVal" id="pkV" 
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											/>
										</td>
										<logic:iterate id="v" name="s" offset="0" length="<%=String.valueOf(num)%>">
											<td nowrap>
												<bean:write name="v"/>
											</td>
										</logic:iterate>
										<td title="<%=((String[])s)[num+1]==null?"":((String[])s)[num+1] %>">
											<%
												String[] strs=(String[])s;
												%><%=strs[num]%><%
												num=num+2;
											%>
										</td>
										<td title="<%=((String[])s)[num+1]==null?"":((String[])s)[num+1] %>">
											<%
												%><%=strs[num]%><%
												num=num+2;
											%>
										</td>
										<td title="<%=((String[])s)[num]==null?"":((String[])s)[num].replace("\r","").replace("\n","") %>">
											<%
												if(strs[num]!=null&&strs[num].length()>10){
													%><%=strs[num].substring(0,10)%>...<%
												}else{
													%><%=strs[num]%><%
												}
											%>
										</td>
									</tr>
								</logic:iterate>
							 <% }else{ %>
								<logic:iterate name="rsList" id="s" indexId="index">	
								<%int num = Integer.valueOf(request.getAttribute("colnum").toString())-2;%>					
									<tr onclick="rowMoreClick(this,'',event);" style="cursor:hand" ondblclick="modi('gyglnew_xxtj.do?method=xxtjDetail',1024,505);">
										<td>
											<input type="checkbox" name="checkVal" id="pkV" 
												value="<logic:iterate id="v" name="s" offset="0" length="1">${v}</logic:iterate>" 
											/>
										</td>
										<logic:iterate id="v" name="s" offset="0" length="<%=String.valueOf(num)%>">
											<td nowrap>
												<bean:write name="v"/>
											</td>
										</logic:iterate>
										<td title="<%=((String[])s)[num+1]==null?"":((String[])s)[num+1] %>">
											<%
												String[] strs=(String[])s;
												%><%=strs[num]%><%
												num=num+2;
											%>
										</td>
										<td title="<%=((String[])s)[num]==null?"":((String[])s)[num].replace("\r","").replace("\n","") %>">
											<%
												if(strs[num]!=null&&strs[num].length()>10){
													%><%=strs[num].substring(0,10)%>...<%
												}else{
													%><%=strs[num]%><%
												}
											%>
										</td>
									</tr>
								</logic:iterate>
							<% } %>
						</tbody>
					</table>
					</div>
				</logic:notEmpty>
			</div>
		</html:form>
		<logic:notEmpty name="result">
				<logic:equal value="false" name="result">
					<script language="javascript">
					alertInfo("操作失败！已分配或已入住的楼栋不可删除!");
					</script>
				</logic:equal>
				<logic:equal value="true" name="result">
					<script language="javascript">
					alertInfo("操作成功！");
					</script>
				</logic:equal>
		</logic:notEmpty>
	</body>
</html>
