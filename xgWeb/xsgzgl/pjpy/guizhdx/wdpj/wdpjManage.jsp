<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/pjpy/pjpyComm.js"></script>
		<script language="javascript" defer="defer">
		//页面初始化
		function onShow(){
			searchRs();
		}

		//查询结果集
		function searchRs(){

			jQuery.ajaxSetup({async:false});
			
			var yhlx = $("yhlx").value;
			var url = "general_pjpy_wdpj_ajax.do?method=searchPjpyWdpj";	
			var otherValue = [yhlx];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);

			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			
			jQuery.ajaxSetup({async:true});
		}	
		
		//显示学生申请
		function showWdpjView(xmdm,xh){
			var url = "general_pjpy.do?method=wdpjXmshDetail&xmdm="+xmdm;
			url+="&opera=view";
			url+="&xh="+xh
			showTopWin(url,"800","600");
		}
		
		function checkShjl(){
		
			var flag=false;
			jQuery('tr', jQuery("#wdpjTable")).each(function(){
				flag=true;
			});
			
			if(!flag){
				alertInfo("您没有需要审核的记录，请确认！");
				return false;
			}else {
				return true;
			}
			
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
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
				1.本功能默认展示的是本评奖学年学期的数据。</br>
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="bjdm" id="bjdm" value=""/>
			<input type="hidden" name="xmdm" id="xmdm" value="" />
			<button type="button" id="forward" onclick="goWdpjLssb()" style="display: none">跳转</button>
			<!-- 多功能操作区 -->
			<div class="toolbox">				
				<div style="display:none">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				</div>
			</div>
			
			<!-- 快捷方式 -->
			<div class="liucheng_xg_pj" style="">
			
				<!-- 学生版本 -->
				<logic:equal name="userType" value="stu">
					<div class="liucheng_bar">
						<h3>快<br/>捷<br/>功<br/>能</h3>
						<div class="con">
						    <div class="liucheng_font floatleft">
						    	<a href="#" onclick="goWdpjXssq();return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function18.png" />
									<p>项目申请</p>
								</a>   	
							</div>
							
							<div class="liucheng_font floatleft">
								<a href="#" onclick="showTopWin('/xgxt/general_pjpy.do?method=wdpjXssqJgcx',600,550);return false;">
						    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
									<p>我的评奖</p>
								</a>
							</div>
						</div>
					</div>
				</logic:equal>
				<!-- 学生版本  end-->
				<logic:notEqual name="userType" value="stu">
				<div class="liucheng_bar">
					<h3>快<br/>捷<br/>功<br/>能</h3>
					<div class="con">
					   
						<div class="liucheng_font floatleft">
							<a href="#" onclick="showTopWin('/xgxt/general_pjpy.do?method=lssbFwChoose',600,550);return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function46.png" />
								<p>老师上报</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="if(checkShjl()){goWdpjXmsh()};return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function72.png" />
								<p>项目审核</p>
							</a>
						</div>
						
						<div class="liucheng_font floatleft">
							<a href="#" onclick="goWdpjJgcx();return false;">
					    		<img src="<%=stylePath%>/images/blue/48-1/Function12.png" />
								<p>结果查询</p>
							</a>
						</div>
					</div>
				</div>
				</logic:notEqual>
				
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
					<table align="center" width="100%" id="wdpjTable">
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
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
					<!--分页显示-->
				</div>
				<!-- 内容显示区开始 end-->
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>