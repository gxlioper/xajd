<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/bjhdgl/bjhdsh/js/bjhdSh.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/comm/js/comm.js"></script>
		<script type="text/javascript">
		
			var colList = [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
                			{ label : 'sqr', name : 'sqr', index : 'sqr',hidden : true },
							{ label : '活动名称', name : 'hdmc', index : 'hdmc', width : '11%',formatter: hdLink},
							{ label : '班级名称', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '活动主题', name : 'hdzt', index : 'hdzt', width : '11%' },
                			{ label : '活动预算', name : 'hdys', index : 'hdys', width : '6%' },
							{ label : '活动日期', name : 'hdrq', index : 'hdrq', width : '6%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '6%' },
							{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true},
							{label:'审核Id',name:'shid', index: 'shid',hidden:true},
							{label:'岗位Id',name:'gwid', index: 'gwid',hidden:true}
			              ];
			
			var gridSetting = {
					pager:"pager",
					url:"bjhdgl_bjhdsh.do?method=bjhdSh&type=query",
					colList:colList,
					sortname: "hdrq",
				 	sortorder: "asc",
				 	radioselect:false
			}
			
			jQuery(function(){
				var map = getSuperSearch();
				map["shlx"]="dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

            //活动查看
            function hdLink(cellValue, rowObject) {
                return "<a href='javascript:void(0);' class='name' onclick='View(\""
                    + rowObject['sqid'] + "\");'>" + cellValue
                    + "</a>";
            }


            function View(sqid) {
                showDialog("活动信息", 900, 450, "bjhdgl_bjhdsq.do?method=getHdInfo&sqid=" + sqid );
            }
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/bjhdgl_bjhdsh">
			<input type="hidden" id="shlx" value="dsh"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_sh">
							<a href="javascript:void(0);" onclick="sh();return false;" 
							   title="选中您要审核的记录，点击该按钮可以打开审核页面。"
							   class="btn_sh">审核</a>
						</li>						
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelSh();return false;" 
							   title="选中一条记录，点击该按钮您可以撤消失误的审核操作。"
							   class="btn_qxsh">撤消</a>
						</li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="lcgz();return false;" 
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
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
