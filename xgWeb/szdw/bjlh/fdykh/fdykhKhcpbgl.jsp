<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@page import="java.util.List"%>
<%@page import="xgxt.utils.Pages"%>
<%@page import="xgxt.szdw.bjlh.fdykh.BjlhFdykhForm"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type="text/javascript" src="js/qtsjFunction.js"></script>
		<script type="text/javascript" src="js/commit.js"></script>
		<script language="javascript">
		//测评问卷div操作 增加、修改、复制
		var doType;
		var khbid;
		function cpwjDiv(type,topMsg){
			$("khbmc").value="";
			doType=type;
			if(type!="add"){
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
			}
			var div_height="200";
			if(type=="update"){
				$("tr_select_xn").style.display="none";
				$("tr_select_pfdx").style.display="none";
				div_height="120";
				$("khbmc").value=curr_row.cells[3].innerText;
			}else if(type=="add"){
				$("tr_select_xn").style.display="";
				$("tr_select_pfdx").style.display="";
				div_height="200";
			}else if(type=="copy"){
				$("tr_select_xn").style.display="";
				$("tr_select_pfdx").style.display="none";
				div_height="150";
			}
			tipsWindown(topMsg,"id:tempDiv","350",div_height,"true","","true","id");
		}
		function cpwjDivSave(){
			if(doType!="update"){
				if($("select_xn").value==""){
					alertInfo("请选择学年！");
					return false;
				}
			}
			if($("khbmc").value.trim()==""){
				alertInfo("请输入问卷名称！");
				return false;
			}
			var url='bjlh_fdykh_khcpbgl.do?doType='+doType+'&khbid='+khbid
			if(doType=="copy"){
				var pfdx=curr_row.cells[4].innerText
				if(pfdx=="学生"){
					pfdx="xs";
				}else if(pfdx=="考核小组"){
					pfdx="pfxz";
				}
				url+="&pfdx="+pfdx;
			}
			allNotEmpThenGo(url);
		}
		
		function modiCpwj(type){
			var khbid;
			if(type=="update"){
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
			}
			var url="bjlh_fdykh.do?method=cpwjglEdit&doType="+type+"&khbid="+khbid;
			showTopWin(url,600,400);
		}
			
			//查询结果集
			function searchRs(){
				allNotEmpThenGo('bjlh_fdykh_khcpbgl.do');
			}
			

			//删除
			function delCpwj(doType){
				var khbid;
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				confirmInfo("确定删除吗？",function(data){
					if("ok"==data){
						allNotEmpThenGo('bjlh_fdykh_khcpbgl.do?doType=del&khbid='+khbid);
					}
				});
			}
			//是否启用测评问卷
			function sfqyCpwj(sfqy,msg){
				var khbid;
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				confirmInfo("确定"+msg+"吗？",function(data){
					if("ok"==data){
						allNotEmpThenGo('bjlh_fdykh_khcpbgl.do?doType=sfqy&khbid='+khbid+"&sfqy="+sfqy);
					}
				});
			}
			//复制
			function copyCpwj(doType){
				var khbid;
				if(type=="update"){
					if(curr_row != null){
						khbid=curr_row.getElementsByTagName('input')[0].value;
					}else{
						alertInfo('请选择要操作的数据行！');
						return false;
					}
				}
				confirmInfo("确定复制吗？",function(data){
					if("ok"==data){
						//var khbid=jQuery("input[type='checkbox'][name='primarykey_cbv']:checked").first().val();
						allNotEmpThenGo('bjlh_fdykh_khcpbgl.do?doType=copy&khbid='+khbid);
					}
				});
			}
			//测评问卷试题维护
			function cpwjStwh(){
				var khbid;
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				var url="bjlh_fdykh.do?method=fdykhXmwh&khbid="+khbid;
				allNotEmpThenGo(url);
			}
			//测评问卷预览
			function cpwjglView(){
				var khbid;
				if(curr_row != null){
					khbid=curr_row.getElementsByTagName('input')[0].value;
				}else{
					alertInfo('请选择要操作的数据行！');
					return false;
				}
				var url="bjlh_fdykh.do?method=fdykhView&khbid="+khbid;
				showTopWin(url,800,600);
			}
		</script>
	</head>
	<body onload="">
		<html:form action="/bjlh_fdykh" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
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
						<li><a href="#" onclick="cpwjDiv('add','增加考核表');return false;" class="btn_zj">增加</a></li>
						<li><a href="#" onclick="cpwjDiv('update','修改考核表名称');return false;" class="btn_xg">修改</a></li>
						<li><a href="#" onclick="delCpwj();return false;" class="btn_sc">删除</a></li>
						<li><a href="#" onclick="cpwjStwh();return false;" class="btn_xg">考核项目维护</a></li>
						<li><a href="#" onclick="cpwjglView();return false;" class="btn_yl">预览</a></li>
						<li><a href="#" onclick="cpwjDiv('copy','复制考核表');return false;" class="btn_fz">复制</a></li>
						<li><a href="#" onclick="sfqyCpwj('是','启用');return false;" class="btn_shtg">启用</a></li>
						<li><a href="#" onclick="sfqyCpwj('否','停用');return false;" class="btn_shbtg">停用</a></li>
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
									<logic:iterate id="v" name="s" offset="1" length="8">
										<td><bean:write name="v" format="ture"/></td>
									</logic:iterate>
								</tr>
							</logic:iterate>
							<%
							int rsNum = ((List)request.getAttribute("rs")).size();
							BjlhFdykhForm form = (BjlhFdykhForm)request.getAttribute("bjlhFdykhForm");
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
		   	 	<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=bjlhFdykhForm"></jsp:include>
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
									<span class="red">*</span>学年
								</th>
								<td>
									<html:select property="xn" styleId="select_xn" style="width:150px;">
										<html:options collection="xnList" property="xn" labelProperty="xn" />
									</html:select>
								</td>
							</tr>
							<tr>
								<th>
									<span class="red">*</span>问卷名称
								</th>
								<td>
									<input type="text" id="khbmc" name="khbmc" maxlength="100"/>
								</td>
							</tr>
							<tr id="tr_select_pfdx">
								<th>
									<span class="red">*</span>评分对象
								</th>
								<td>
									<html:select property="pfdx" styleId="select_pfdx" style="width:150px;">
										<html:options collection="pfdxList" property="dm" labelProperty="mc" />
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
										<button type="button" name="保存" onclick="cpwjDivSave()">
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
