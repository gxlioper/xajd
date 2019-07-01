<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
	    <script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxxgsq/js/xxxgsq.js"></script>
	</head>

	<body style="min-height: 800px;">
		<input type="hidden" name="kfxg" id="kfxg" value='${kfxg}'/>
		<input type="hidden" name="usertype" id="usertype" value='${usertype}'/>
		<input type="hidden" name="xh" id="xh" value='${xh}'/>
		<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="xxxgsq();return false;" id="btn_zj"
								class="btn_zj"> 申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xgsqXg();return false;" id="btn_xg"
								class="btn_xg"> 修改 </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xgsqSc();return false;" id="btn_sc"
								class="btn_sc"> 删除 </a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xgsqTj();return false;" id="btn_tj"
								class="btn_shuc"> 提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xgsqCx();return false;" id="btn_cx"
								class="btn_sr"> 撤销</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="shlcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>	
					    <li>
							<a href="javascript:void(0);" onclick="printByjdb('xsxx_bysxx_xxgl.do?method=printByjdb');return false;" id="btn_xz"
								class="btn_down"> 下载毕业生鉴定表</a>
						</li>	
					</ul>
				</div>
			</div>
				<!-- 按钮 end-->
		
		
		<html:form action="/xsxx_bysxx_xxxgsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>毕业生信息修改申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
