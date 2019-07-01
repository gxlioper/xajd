<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/dekt/qnzyhd/js/qnzyhd.js"></script>
		<script type="text/javascript">
		function searchRs() {
			var map = getSuperSearch();
			var shzt = jQuery("#shzt").val();
			if (null!=shzt&&shzt != "") {
				map["shzt"] = shzt;
			}else{
				map["shzt"] = "dsh";
			}
			jQuery("#dataTable").reloadGrid(map);
		}
		
		var gridSetting = {
				caption:"志愿者服务活动发布审核列表",
				pager:"pager",
				url:"zyhd.do?method=hdfbshList&type=query",
				colList : [
							{ label : 'hdid', name : 'hdid', index : 'hdid',key : true, hidden : true },
							{ label : '活动名称', name : 'hdmc', index : 'hdmc', width : '15%' },
							{ label : '服务类型', name : 'fwlxmc', index : 'fwlxmc', width : '10%' },
							{ label : '基本服务工时', name : 'jbfwgs', index : 'jbfwgs', width : '10%' },
							{ label : '开始时间', name : 'hdkssj', index : 'hdkssj', width : '10%' },
							{ label : '服务对象', name : 'fwdx', index : 'fwdx', width : '10%' },
							{ label : '限定人数', name : 'xdrs', index : 'xdrs', width : '10%' },
							{ label : '负责人', name : 'hdfzrxm', index : 'hdfzrxm', width : '10%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '18%' },
							{ label : 'rs', name : 'rs', index : 'rs', hidden : true }
							],
				params:{shzt:"dsh"},
			 	radioselect:false
		}

		var gridSetting2 = {
				caption:"志愿者服务活动发布审核列表",
				pager:"pager",
				url:"zyhd.do?method=hdfbshList&type=query",
				colList : [
							{ label : 'hdid', name : 'hdid', index : 'hdid',key : true, hidden : true },
							{ label : '活动名称', name : 'hdmc', index : 'hdmc', width : '15%' },
							{ label : '服务类型', name : 'fwlxmc', index : 'fwlxmc', width : '10%' },
							{ label : '基本服务工时', name : 'jbfwgs', index : 'jbfwgs', width : '10%' },
							{ label : '开始时间', name : 'hdkssj', index : 'hdkssj', width : '10%' },
							{ label : '服务对象', name : 'fwdx', index : 'fwdx', width : '10%' },
							{ label : '限定人数', name : 'xdrs', index : 'xdrs', width : '10%' },
							{ label : '负责人', name : 'hdfzrxm', index : 'hdfzrxm', width : '10%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '18%' },
							{ label : 'rs', name : 'rs', index : 'rs', hidden : true }
							],
				params:{shzt:"ysh"},
			 	radioselect:false
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["shzt"]="dsh";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
			
		//撤销审核
		function cancelSh() {
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length == 0) {
				showAlertDivLayer("请选择您要撤销的记录")
				return false;
			}
			var flg = true;
			var guid = new Array();
			jQuery.each(rows, function(i, row) {
				if(row["rs"] > 0){
					flg = false;
					return false;
				}
				guid.push(row["hdid"]);
			});
			if(!flg){
				showAlertDivLayer("存在活动已有学生报名，不能撤销！")
				return false;
			}
			showConfirmDivLayer("您确定要撤销选择的记录吗？", {
				"okFun" : function() {
					jQuery.post("zyhd.do?method=cancelSh", {
						ids : guid,
					}, function(data) {		
						showAlertDivLayer(data["message"], {}, {
							"clkFun" : function() {
								jQuery("#dataTable").reloadGrid();
							}
						});
					}, 'json');
				}
			})	
		}

		// 批量审核
		function savePlsh(shzt, shyj) {
			var rows = jQuery("#dataTable").getSeletRow();
			var guid = new Array();
			jQuery.each(rows, function(i, row) {
				guid.push(row["hdid"]);
			});
			jQuery.post("zyhd.do?method=sbPlsh&type=save", {
				shzt : shzt,
				ids : guid,
				shyj:shyj
			}, function(data) {
				
				showAlertDivLayer(data["message"], {}, {
					"clkFun" : function() {
						jQuery("#dataTable").reloadGrid();
					}
				});
			}, 'json');
		}
				
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/zyhdry">
			<input type="hidden" id="shzt" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li id="li_sh">
								<a href="javascript:void(0);" onclick="sbsh();return false;" 
								   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
								   class="btn_sh">审核</a>
							</li>
							<li id="li_qx" style="display: none;">
								<a href="javascript:void(0);" onclick="cancelSh();return false;" 
								   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
								   class="btn_qxsh">撤消</a>
							</li>
						</logic:equal>						
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
				<span>志愿者服务活动发布审核列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
