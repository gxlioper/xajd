<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/zjdx/cjff/js/cjff.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "列表",
				pager : "pager",
				url : "cjff_zjdx.do?method=cjffCx&type=query",
				colList : [ {
					label : 'key',
					name : 'id',
					index : 'id',
					key : true,
					hidden : true
				}, {
					label : '年度',
					name : 'nd',
					index : 'nd',
					width : '5%'
				}, {
					label : '月份',
					name : 'yf',
					index : 'yf',
					width : '5%'
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					width : '10%'
				}, {
					label : '姓名',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '用人单位',
					name : 'yrdwmc',
					index : 'yrdwmc',
					width : '15%'
				},{
					label : '工时(小时)',
					name : 'gss',
					index : 'gss',
					width : '5%'
				},{
					label : '发放金额(元)',
					name : 'bcje',
					index : 'bcje',
					width : '5%'
				},{
					label : '提交状态',
					name : 'tjzt',
					index : 'tjzt',
					width : '5%'
				},{
					label : 'sftj',
					name : 'sftj',
					index : 'sftj',
					hidden : true
				}],
				sortname : "lrsj",
				sortorder : "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmouseover ="showHelpDiv()">使用帮助</a>
			</p>
		</div>
		<div class="prompt" id="div_help">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				1.${dqnd}年度总经费为${tsxx.hbje}元，当前已提交金额${tsxx.tjje}元，剩余金额${tsxx.syje}元，未提交金额${tsxx.wtjje}元。<br/>
				2.请于每月${jssj}日24点前提交当月上报数据，逾期未提交的系统将自动提交。
			</p>
			<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<!-- 可写权限判断 -->
						<logic:equal name="writeAble" value="yes">
						
							<!-- 非管理员用户  酬金发放按钮  受到参数设置时间控制 -->
							<logic:equal value="false" name="sfqggly">
								<logic:equal value="open" name="sqkg">
									<li>
										<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >酬金发放</a>
									</li>
									<li>
										<a href="javascript:void(0);" class="btn_tj" onclick="tj();return false;"  >提交</a>
									</li>
									<li>
										<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
									</li>
									<li>
										<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
									</li>
									<li>
										<a href="#" class="btn_dr" onclick="dr();return false;">导入</a>
									</li>
								</logic:equal>
							</logic:equal>
							
							<!-- 管理员用户  酬金发放按钮  不受到参数设置时间控制并且拥有【取消提交】的功能 -->
							<logic:equal value="true" name="sfqggly">
								<li>
									<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >酬金发放</a>
								</li>
								<li>
									<a href="javascript:void(0);" class="btn_tj" onclick="tj();return false;"  >提交</a>
								</li>
								<li>
									<a href="javascript:void(0);" class="btn_qxsh" onclick="cancel();return false;"  >取消提交</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >删除</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
								</li>
								<li>
									<a href="#" class="btn_dr" onclick="dr();return false;">导入</a>
								</li>
							</logic:equal>
							
						</logic:equal>
						
							<!-- 只读权限、可写权限都有这两个按钮 -->
							<li>
								<a href="javascript:void(0);" onclick="ck();return false;" class="btn_ck" >查看明细</a>
							</li>
							<logic:equal value="true" name="sfqggly">
								<li>
									<a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a>
								</li>
							</logic:equal>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>酬金发放列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
