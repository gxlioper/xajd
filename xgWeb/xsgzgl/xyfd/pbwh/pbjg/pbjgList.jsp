<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/pbwh/pbjg/js/pbJg.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "活动结果列表",
				pager : "pager",
				url : "xyfd_pbjg.do?method=pbjgList&type=query",
				colList : [
					{ label : 'jgid', name : 'jgid', index : 'jgid',key : true,hidden : true },
                    { label : '登记号', name : 'djh', index : 'djh', width : '8%',formatter:xhLink  },
					{ label : '学号', name : 'xh', index : 'xh', width : '10%'},
					{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
					{ label : '辅导科目', name : 'fdkm', index : 'fdkm', width : '10%' },
					{ label : '辅导室', name : 'fdsmc', index : 'fdsmc', width : '10%' },
					{ label : '辅导室地点', name : 'fdsdd', index : 'fdsdd', width : '15%' },
					{ label : '使用日期',name:'syksrq', index: 'syksrq',width:'7%',formatter:function (cell,rowObject) {
                        return rowObject["syksrq"] + "-" + rowObject["syjsrq"];
                    }},
                    { label : '使用结束日期',name:'syjsrq', index: 'syjsrq',width:'1%',hidden:true},
                    { label : '联系电话', name : 'lxdh', index : 'lxdh', width : '10%' },
                    { label : '数据来源', name : 'sjly', index : 'sjly', hidden : true},
                    { label : 'lrsj', name : 'lrsj', index : 'lrsj', hidden : true}
				],
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
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/hdgl_hdbljg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >增加</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_shuc">导入</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_sr">导出</a>
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
				<span>活动结果列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
