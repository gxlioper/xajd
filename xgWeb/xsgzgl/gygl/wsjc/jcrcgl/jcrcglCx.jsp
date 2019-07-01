<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/wsjc/jcrcgl/js/jcrcgl.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" defer="defer">
			//初始化
			function onShow(){ 
				searchRs();
			}
		</script>
	</head>
	<body >

		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>


		<html:form action="/gyglnew_jcrcgl" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- 隐藏域 -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />

			<!-- 多功能操作区 -->
			<div class="toolbox" id="dgncz">
				<!-- 按钮 -->
				
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="showDialog('增加检查日程', 600, 380, 'gyglnew_jcrcgl.do?method=jcrcglZj');return false;" class="btn_zj">增加</a>
						</li>
						<logic:notEqual value="33333" name="xxdm">						
							<li>
								<a href="#" onclick="showModi();return false;" class="btn_xg">修改</a>
							</li>
						</logic:notEqual>
						<li>
							<a href="#" onclick="deleteJcrcgl();return false;" class="btn_sc">删除</a>
						</li>
						</logic:equal>
						<li>
							<a href="#" onclick="showView();return false;" class="btn_ck">查看</a>
						</li>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="#" onclick="tijiao();return false;" class="btn_shtg">提交</a>
						</li>
						<li>
							<a href="#" onclick="qxtj();return false;" class="btn_shbtg">撤销</a>
						</li>
						<%--<li>
							<a href="#" onclick="impAndChkData();return false;" class="btn_dr">导入数据</a>
						</li>
						--%></logic:equal>
						<li><a href="#" class="btn_dc" onclick="jcrcglExportConfig();return false;">导出</a></li>
						<%--<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">导出数据</a></li>
					--%></ul>
				</div>
				
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
			<!-- 多功能操作区 end-->

			<!-- 内容显示区开始 -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- 查询得到的数据量显示区域 -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> 查询结果&nbsp;&nbsp; <font color="blue">提示：已被维护的检查日程不能再进行操作</font><logic:notEmpty name="rsList">
							<font color="blue">提示：单击表头可以排序；双击记录可查看详细信息</font>
						</logic:notEmpty> </span>
				</h3>
				<div id="div_rs"
					style="width:100%;height:380px;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--分页显示-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=gyglJcrcglForm"></jsp:include>
<%--				<script type="text/javascript">--%>
<%--				$('choose').className="hide";--%>
<%--				</script>--%>
				<!--分页显示-->
			</div>
			<!-- 内容显示区 end-->
			
			
			<div id="div_detail" style="display:none">
			</div>		
				
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
