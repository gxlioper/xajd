<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />    
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//查询结果集
		function searchRs(){
	
			var url = "pjpyMypj.do?method=getMypjTjList";	
			var otherValue = ["tea"];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}		

		//前往项目上报
		function goXmsb(){
			var url = "pjpyXmsb.do?method=xmsbManage";
				url+= "&xmdm="+$("xmdm").value;
				url+= "&bjdm="+$("bjdm").value;
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往项目申请
		function goXmsq(){
			var url = "pjpy_pjlc_xssq.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往项目审核
		function goXmsh(){
			var url = "pjpy_pjlc_xmsh.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往项目结果
		function goXmjg(){
			
			var url = "pjpy_pjlc_jgcx.do";
			
			var xxdm='${xxdm}';
			if(xxdm=="11032"){
				url="pjpy_tjzy_bcpj.do?method=bcpjManage";
			}
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		</script>
	</head>
	<body onload="searchRs()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>评奖评优 - 我的评奖</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.结果集中显示的是您<font color="blue">帮助学生上报</font>或者<font color="blue">需要审核的</font>的项目。</br>
				2.如果您想为某项目上报学生，请点击<font color="blue">老师上报</font>。</br>
				3.如果您想替某学生申请项目，请点击<font color="blue">学生申请</font>。</br>
				4.如果您想对审核相关项目，请点击<font color="blue">项目审核</font>。</br>
				5.如果您想查看相关的申请审核结果，请点击<font color="blue">结果查询</font>。
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->		
		
		<html:form action="/pjpyMypj">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="bjdm" id="bjdm" value=""/>
			<input type="hidden" name="xmdm" id="xmdm" value="" />
			<button type="button" id="forward" onclick="goXmsb()" style="display: none">跳转</button>
			<button type="button" class="btn_cx" id="search_go" onclick="searchRs();return false;" style="display: none">查询</button>
			<!-- 隐藏域 end-->
			
			<!-- 过滤条件 -->
			<div style="display:none">
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 过滤条件 end-->
				
			<!-- 快捷方式 -->
			<div class="liucheng_xg_pj" style="">
			
				<div class="liucheng_bar">
					<h3>快<br/>捷<br/>功<br/>能</h3>
					<div class="con">
					    <div class="liucheng_font floatleft">
					    	<a href="#" onclick="goXmsq();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>学生申请</p>
							</a>   	
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="showTopWin('/xgxt/pjpyXmsb.do?method=sbfwChoose',600,550);return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
								<p>老师上报</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goXmsh();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function72.png" />
								<p>项目审核</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goXmjg();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>结果查询</p>
							</a>
						</div>
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpyMypjForm"></jsp:include>
					<script type="text/javascript" defer="defer">
						setTimeout("$('choose').className='hide'",100);
					</script>
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