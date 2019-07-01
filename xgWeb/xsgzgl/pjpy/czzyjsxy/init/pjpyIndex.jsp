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
		//初始化
		function onShow(){
			//初始化已定制评奖流程
			defaultCustomPjlc();
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  ondrag="return false">
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
<%--			<p class="help">--%>
<%--				<a href="#" onclick="return false;" --%>
<%--					onmouseover ="showHelpDiv()"--%>
<%--					onmouseout="showHelpDiv()"--%>
<%--				>--%>
<%--				帮助中心</a><img src="<%=stylePath%>/images/ico_new02.gif" />--%>
<%--			</p>--%>
		</div>
		<!-- 标题 end-->
		
<%--		<!-- 提示信息 end-->--%>
<%--		<div class="prompt">--%>
<%--			<h3>--%>
<%--				<span>系统提示：</span>--%>
<%--			</h3>--%>
<%--			<p>--%>
<%--				鼠标移动到右上角<font color="blue">帮助中心</font>，可查看本模块的相关说明。</br>--%>
<%--				<span id="div_help" style="display: none">--%>
<%--				1.本功能默认展示的是本评奖学年学期的数据。</br>--%>
<%--				</span>--%>
<%--			</p>--%>
<%--		</div>--%>
		<!-- 提示信息 end-->
		
		<html:form action="/general_pjpy" method="post">
		
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input  id="btn_sx" onclick="onShow()" style="display:none"/>
			<input type="hidden" id="lcdj_submit"/>
			
			<table width="100%">
				<tr>
					<td>
						<!-- 内容显示区开始 -->
						<div class="main_box" id="div_custom_pjlc">
							
						</div>
						<!-- 内容显示区 end-->
					</td>
				</tr>
			</table>	
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>