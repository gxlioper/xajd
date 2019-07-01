<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xthd/xmsq/js/xmsq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			jQuery("#dataTable").initGrid(gridSetting1);
			auotSetCanCheck
		});
		</script>
	</head>

	<body>
		<input type="hidden" name="curXn" id="curXn" value="${curXn}"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_txhd_xs_xmsq" method="post" styleId="form">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
					<div class="buttonbox">
						<ul>
							<logic:equal name="writeAble" value="yes">	
							<li id="li_cx" style="display:none;">
								<a href="javascript:void(0);" onclick="cxSqb();return false;" class="btn_xg"
						   			title="只能取消未审核或退回的记录，已被在审核的不能取消。"
									>撤销</a>
							</li>
							</logic:equal>
							<li id="li_lc" style="display: none;">
									<a href="javascript:void(0);" onclick="viewLcgz();return false;" 
							   			title="选中一条记录，点击该按钮可以查看审核流程。"
							   			class="btn_cs">流程跟踪</a>
							</li>
							<logic:equal name="writeAble" value="yes">	
							<li id="li_sq">
								<a href="javascript:void(0);" class="btn_zj"
								   onclick="xmsq();return false;" 
								   title="点击该按钮，打开申请表填写页面。">申请</a>
							</li>
							
							<li id="li_xg" >
								<a href="javascript:void(0);" onclick="xgSqb();return false;" class="btn_xg"
						   			title="只能修改未提交或退回的记录，已被在审核的不能修改。"
									>修改</a>
							</li>
							
							<li id="li_sc" >
								<a href="javascript:void(0);" onclick="delSqb();return false;" class="btn_sc"
						   			title="只能删除未提交或退回的记录，已被在审核的不删除。"
									>删除</a>
							</li>
							
							<li id="li_tj" >
								<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc"
						   			title="只能提交未提交或退回的记录，已被在审核的不能提交。"
									>提交</a>
							</li>
							</logic:equal>
						</ul>
					</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'wsq');"><span>未申请项目</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysq');"><span>已申请项目</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>
					<li id="li_ts" ><font color="blue">${curXn}</font></li>&nbsp;项目列表
				 </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>

		</div>
	</body>
</html>
