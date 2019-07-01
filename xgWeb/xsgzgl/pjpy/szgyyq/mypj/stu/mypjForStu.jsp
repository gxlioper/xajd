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
	   	<script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//查询结果集
		function searchRs(){
			
			var yhlx = $("yhlx").value;
			var url = "szgyyq_mypj.do?method=getStuTjList";	
			var otherValue = [yhlx];

			showWaitingDiv("1000");
			searchRsByAjax(url,otherValue);
		}	
		
		//前往分数申请
		function goFssq(){
			var url = "pjpy_szgyyq_fssq.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往结果查询
		function goJgcx(){
			var url = "pjpy_szgyyq_jgcx.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往我的申诉
		function goMyss(){
			var url = "pjpy_szgyyq_myss.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往我的投诉
		function goMyts(){
			var url = "pjpy_szgyyq_myts.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		</script>
	</head>
	
	<body onload="searchRs()" >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>综合素质养成课 - 我的工作</a>
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
				<span>
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc }</font>学期 展开的。</br>
				2.您在本周期各个项目的分值和审核情况如下，<font color="blue">请注意</font>，当最终分数超过最高分时，计算总分时以最高分算。</br>
				3.<font color="blue">点击查看详细</font>，可以查看该项目的具体情况，如果对分数有异议，可以在此执行<font color="blue">申诉</font>操作。</br>
				4.点击<font color="blue">分数申请</font>，可以申请新的分数，<font color="blue">5S分</font>不能由您来申请，请注意。</br>
				5.点击<font color="blue">结果查询</font>，可以查看您所在班级的所有项目的情况，并可以针对有异议的人员进行<font color="blue">投诉</font>。</br>
				6.<font color="blue">我的申诉</font>和<font color="blue">我的申请</font>用来管理您已经投诉过和申诉过的记录。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/szgyyq_mypj">
			
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 用户类型 -->
			<input type="hidden" name="yhlx" id="yhlx" value="${yhlx }"/>
			<!-- 刷新  -->
			<button type="button" id="btn_sx" onclick="searchRs();return false;" style="display:none">
				刷新
			</button>
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
					    	<a href="#" onclick="goFssq();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>分数申请</p>
							</a>   	
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goJgcx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>结果查询</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goMyss();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function21.png" />
								<p>我的申诉</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goMyts();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function28.png" />
								<p>我的投诉</p>
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
					<script type="text/javascript" defer="defer">
						setTimeout("$('choose').className='hide'",100);
					</script>
					<div style="display:none">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=pjpySzgyyqMypjForm"></jsp:include>
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