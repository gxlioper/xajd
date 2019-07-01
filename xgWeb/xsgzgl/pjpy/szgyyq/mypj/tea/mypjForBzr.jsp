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
		
		//前往结果查询
		function goJgcx(){
			var url = "pjpy_szgyyq_jgcx.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往5S分录入
		function goFiveS(){
			var url = "pjpy_szgyyq_fives.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}
		
		//前往分数审核
		function goFssh(xmdm,shtj){
			var url = "pjpy_szgyyq_fssh.do";
			
			showWaitingDiv("30000");
			
			if(xmdm != ""){
				url = "szgyyq_mypj_tea.do?method=fsshManage";
				url+= "&xmdm="+xmdm;
				url+= "&shtj="+shtj;
			}
			
			refreshForm(url);
		}
		
		//前往综合测评
		function goZhcp(){
			var url = "pjpy_szgyyq_zhcp.do";
			
			showWaitingDiv("30000");
			
			refreshForm(url);
		}	
		
		</script>
	</head>
	
	<body onload="" >
	
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
				3.点击<font color="blue">分数审核</font>，可以审核学生的申请。</br>
				4.点击<font color="blue">综合测评</font>，可以查看学生的综合测评情况。</br>
				5.点击<font color="blue">结果查询</font>，可以查看学生的申请审核情况。</br>
				5.点击<font color="blue">5S分录入</font>，可以录入学生的5S分。</br>
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
							<a href="#" onclick="goFiveS();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function28.png" />
								<p>5S分录入</p>
							</a>
						</div>
						
					    <div class="liucheng_font floatleft">
					    	<a href="#" onclick="goFssh('','');return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
								<p>分数审核</p>
							</a>   	
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goZhcp();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>综合测评</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goJgcx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function21.png" />
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
									<table width="100%" class="dateline">
										<thead>
											<tr>
												<td>综测项目</td>
												<td>需审核人数</td>
												<td><bean:message key="lable.xb" />审核通过人数</td>
												<td>学校审核通过人数</td>
											</tr>
										</thead>
										<tbody>
											<logic:present name="rs">
											<logic:iterate id="s" name="rs">
												<tr>
													<td>${s.xmmc }</td>		
													<!-- 需审核人数 -->										
													<td>
														<logic:equal name="s" property="xshrs" value="0">
															${s.xshrs }
														</logic:equal>
														<logic:notEqual name="s" property="xshrs" value="0">
															<a href="#" onclick="goFssh('${s.xmlx }','xsh');return false;">
																<font color="blue">${s.xshrs }</font>
															</a>
														</logic:notEqual>
													</td>	
													<!-- 学院审核通过人数 -->										
													<td>${s.xyshtgrs }</td>
													<!-- 学校审核通过人数 -->											
													<td>${s.xxshtgrs }</td>												
												</tr>
											</logic:iterate>
											</logic:present>
										</tbody>
									</table>
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