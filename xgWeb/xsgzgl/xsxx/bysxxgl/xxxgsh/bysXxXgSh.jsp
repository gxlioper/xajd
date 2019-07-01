<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/bysxxgl/xxxgsh/js/xxxgsh.js"></script>
		<script type="text/javascript">
		var gridSetting1 = {
				caption : "毕业生信息修改申请待审核列表",
				pager : "pager",
				url : "xsxx_bysxx_xxxgsh.do?method=xgSqShList&type=query",
				colList : [ {
					label : 'sqid',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					formatter : dcmcLink
				}, {
					label : '姓名 ',
					name : 'xm',
					index : 'xm'
				}, {
					label : '<bean:message key="lable.xb" />',
					name : 'xymc',
					index : 'xymc'
				}, {
					label : '班级',
					name : 'bjmc',
					index : 'bjmc'
				}, {
					label : '申请时间',
					name : 'xgsj',
					index : 'xgsj'
				}, {
					label : '审核状态',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				}, {
					label : '审核状态',
					name : 'shztmc',
					index : 'shzt',
					formatter : shzt
				}, {
					label : '审核人名称',
					name : 'mc',
					index : 'mc',
					hidden : true
				}, {
					label : 'lcid',
					name : 'lcid',
					index : 'lcid',
					hidden : true
				}, {
					label : 'gwid',
					name : 'gwid',
					index : 'gwid',
					hidden : true
				}, {
					label : 'ywid',
					name : 'ywid',
					index : 'ywid',
					hidden : true
				}, {
					label : '审核id',
					name : 'guid',
					index : 'guid',
					hidden : true
				} ],
				sortname : "xgsj",
				sortorder : "desc"
			}
			var gridSetting2 = {
				caption : "毕业生信息修改申请已审核列表",
				pager : "pager",
				url : "xsxx_bysxx_xxxgsh.do?method=xgSqShList&type=query",
				colList : [ {
					label : 'sqid',
					name : 'sqid',
					index : 'sqid',
					key : true,
					hidden : true
				}, {
					label : '学号',
					name : 'xh',
					index : 'xh',
					formatter : dcmcLink
				}, {
					label : '姓名 ',
					name : 'xm',
					index : 'xm'
				}, {
					label : '<bean:message key="lable.xb" />',
					name : 'xymc',
					index : 'xymc'
				}, {
					label : '班级',
					name : 'bjmc',
					index : 'bjmc'
				}, {
					label : '审核时间',
					name : 'shsj',
					index : 'shsj'
				}, {
					label : '审核状态',
					name : 'shzt',
					index : 'shzt',
					hidden : true
				}, {
					label : '审核状态',
					name : 'shztmc',
					index : 'shzt',
					formatter : shzt
				}, {
					label : '审核人名称',
					name : 'mc',
					index : 'mc',
					hidden : true
				}, {
					label : 'lcid',
					name : 'lcid',
					index : 'lcid',
					hidden : true
				}, {
					label : 'gwid',
					name : 'gwid',
					index : 'gwid',
					hidden : true
				}, {
					label : 'ywid',
					name : 'ywid',
					index : 'ywid',
					hidden : true
				}, {
					label : '审核id',
					name : 'guid',
					index : 'guid',
					hidden : true
				} ],
				sortname : "shsj",
				sortorder : "desc"
			}

				
		jQuery(function() {
			
			var map = getSuperSearch();
			map["shjg"] = "dsh";
			gridSetting1["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting1);
		});
		// 点击序号跳转
		function dcmcLink(cellValue, rowObject) {
			var xh = rowObject.xh;
			var sqid = rowObject.sqid;
			if (xh == null) {
				xh = "";
			}
			cellValue = "<a  href='javascript:void(0);'  class='name'  onclick='ckxx(\""
					+ xh + "\",\"" + sqid + "\" );return false;' >" + xh + "</a>";
			return cellValue;
		}
		// 查看信息
		function ckxx(xh,sqid) {
			var url = "xsxx_bysxx_xxxgsq.do?method=showXgXx&xh=" + xh + "&sqid=" + sqid;
			var title = "毕业生信息修改申请查看";
			showDialog(title, 850, 550, url);
		}
				</script>

	</head>

	<body style="min-height: 800px;">
	
		<input type="hidden" value="dsh" id="shzt"/>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xsxx_bysxx_xxxgsh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="xbmc" value="<bean:message key="lable.xb" />"/>
			<div class="toolbox">
				<!-- 按钮 -->
					
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="xgSqSh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cxshnew();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>		
						</logic:equal>				
						<li><a href="javascript:void(0);" onclick="shlcInfo();return false;" 
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a></li>			
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>待处理</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>已处理</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>信息修改审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
