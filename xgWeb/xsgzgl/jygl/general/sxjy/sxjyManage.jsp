<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/jygl/jyglComm.js"></script>
		<script language="javascript" defer="defer">
		//初始化
		function onShow(){ 
			searchRs();
		}

		//查询结果集
		function searchRs(){
			
			jQuery.ajaxSetup({async:false});
			
			var url = "general_jygl_sxjy_ajax.do?method=searchResult";
			var ie = "ie";
			
			var otherValue = [ie];

			$("divWaiting").style.display="";
			$("divDisable").style.display="";
			
			searchRsByAjax(url,otherValue);
			
			jQuery.ajaxSetup({async:true});
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
<%--		<!-- 提示信息 end-->--%>
		
		<html:form action="/general_jygl" method="post">
			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- 多功能操作区 -->
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="#" onclick="showSxjy('view');return false;" class="btn_ck">
								查看
							</a>
						</li>
						<!-- 读写权  begin -->
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="#" onclick="showSxjy('add');return false;" class="btn_zj">
									增加
								</a>
							</li>
							<li>
								<a href="#" onclick="showSxjy('edit');return false;" class="btn_xg">
									修改
								</a>
							</li>
							<li>
								<a href="#" onclick="checkDelSxjy();return false;" class="btn_sc">
									删除
								</a>
							</li>
							<logic:equal name="userType" value="admin">
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a>
							</li>
							</logic:equal>
							<logic:equal name="userType" value="xx">
							<li>
								<a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a>
							</li>
							</logic:equal>
						</logic:equal>
						<!-- 读写权  end -->
						<li>
							<a href="#" class="btn_qx" onclick="choiceFields();return false;">
								导出设置
							</a>
						</li>
						<li>
							<a href="#" onclick="configureExportData();return false;" class="btn_dc">
								导出
							</a>
						</li>
					</ul>
				</div>
				<!-- 按钮 end-->
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
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
				<div id="div_rs" style="height:380px;overflow-x:auto;overflow-y:auto;">
				</div>
				
				<!--分页显示-->
				<div style="clear:both;">
					<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalJyglGeneralForm"></jsp:include>
					 <script type="text/javascript">
				     //$('choose').className="hide";
				     </script>
				</div>
				<!--分页显示-->
			</div>
			<!-- 内容显示区开始 end-->
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>