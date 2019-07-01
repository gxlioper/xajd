<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />    
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//查询结果集
		function searchRs(){
			var url = "rcsw_qjgl.do?method=getMygzList";	
			var otherValue = ["1"];
			
			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}	
		
		//前往请假审核
		function goMysh(czxm){
			var url	="rcsw_qjgl.do?method=myshManage";
				url+="&czxm="+czxm;
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往请假查询
		function goJgcx(){
			var url = "rcsw_qjgl_jgcx.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往插销请假
		function goCxqj(){
			var url = "rcsw_qjgl_cxqj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往请假查询
		function goBjfx(){
			var url = "rcsw_qjgl_bjfxdj.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//显示我的请假详细
		function showMyqjDetail(type){
		
			var id="";
			var flag = false;
			
			if(type == "edit"){
				var num = jQuery("input[name=checkVal]:checked").length;
				
				if(num == 0){
					alertError("请勾选您需要维护记录");
					flag = false;
				}else if(num > 1){
					alertError("不能勾选多条记录，请勾选一位您需要维护的记录");
					flag = false;
				}else{
					id=jQuery("input[name=checkVal]:checked")[0].value;
					flag = true;
				}				
			}else{
				flag = true;
			}

			if(flag){
			
				var url = "/xgxt/rcsw_qjgl.do?method=myqjDetail";
					url+= "&id="+id;
				showDialog("", 800, 520, url);
				//showTopWin(url,'800','620');	
			}	
		}
		</script>
	</head>
	
	<body onload="searchRs()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>日常事务 - 请假管理 - 我的工作</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.结果集中展示的数据，是您所在岗位本学期需要审核的人数。</br>
				2.数据范围以您所在岗位和身份决定。</br>
				3.点击<font color="blue">请假审核,</font>，可以开始您的审核工作。</br>
				4.点击项目后的<font color="blue">审核</font>连接，可以直接针对该项目进行审核。
				</span>
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/rcsw_qjgl">
			
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 用户类型 -->
			<input type="hidden" name="yhlx" id="yhlx" value="${yhlx }"/>
			<!-- 隐藏域 end-->
			
			<!-- 过滤条件 -->
			<div style="display:none">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<button type="button" class="btn_cx" id="search_go" style="display: none" onclick="searchRs();return false;"></button>
			<!-- 过滤条件 end-->
			
			<!-- 快捷方式 -->
			<div class="liucheng_xg_pj" style="">
			
				<div class="liucheng_bar">
					<h3>快<br/>捷<br/>功<br/>能</h3>
					<div class="con">
					
						
						 <div class="liucheng_font floatleft">
					    	<a href="#" onclick="showMyqjDetail('add');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function61.png" />
								<p>请假申请</p>
							</a>   	
						</div>
						<!-- 
					    <div class="liucheng_font floatleft">
					    	<a href="#" onclick="goMysh('');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>请假审核</p>
							</a>   	
						</div>
						 -->
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goJgcx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>申请结果</p>
							</a>
						</div>
						<!-- 
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goCxqj();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function35.png" />
								<p>撤销请假</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goBjfx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function34.png" />
								<p>病假返校</p>
							</a>
						</div>
						 -->
					</div>
				</div>
				
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
						<tr style="">
							<td width="100%" valign="top" style="position: relative;">
								<div id="div_rs">
								
								</div>
							</td>
						</tr>
					</table>
					<!--分页显示-->
					<script type="text/javascript" defer="defer">
						setTimeout("$('choose').className='hide'",100);
					</script>
					<div style="display:none">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=rcswQjglForm"></jsp:include>
					</div>
					<!--分页显示-->
				</div>
				<!-- 内容显示区开始 end-->
				
			</div>
			<!-- 快捷方式 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>