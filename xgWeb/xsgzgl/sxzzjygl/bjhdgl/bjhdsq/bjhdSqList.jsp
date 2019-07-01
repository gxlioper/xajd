<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/sxzzjygl/bjhdgl/bjhdsq/js/bjhdSq.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "bjhdgl_bjhdsq.do?method=bjhdSq&type=query",
				colList : [
							{ label : 'key', name : 'sqid', index : 'sqid',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : '活动名称', name : 'hdmc', index : 'hdmc', width : '11%',formatter: hdLink},
							{ label : '班级名称', name : 'bjmc', index : 'bjmc', width : '10%' },
							{ label : '活动主题', name : 'hdzt', index : 'hdzt', width : '11%' },
                    		{ label : '活动预算', name : 'hdys', index : 'hdys', width : '6%' },
							{ label : '活动日期', name : 'hdrq', index : 'hdrq', width : '6%' },
							{ label : '审核状态', name : 'shztmc', index : 'shztmc', width : '6%' },
							{ label : '审核状态', name : 'shzt', index : 'shzt', hidden : true}],
					sortname : "hdrq",
				    sortorder : "desc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
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
		<logic:equal value="0" name="sqkg">
			<div class="prompt">
				<h3>
					<span>提示：</span>
				</h3>
				<p>
					当前未开放申请！
				</p>
				<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
			</div>
		</logic:equal>
		
		<html:form action="/ztbhgl_ztbhsq">
		<input type="hidden" name="sqkg" id="sqkg" value="${sqkg}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="edit();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submit();return false;" class="btn_shuc">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">撤销</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="bjhdLcinfo();return false;"
							   title="选中一条记录，点击该按钮可以查看审核流程。"
							   class="btn_cs">流程跟踪</a>
						</li>
						
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
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
