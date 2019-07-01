<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		jQuery(function(){
            var gridSetting;
				gridSetting = {
					caption : "100场活动列表",
					pager : "pager",
					url : "hdgl_hdjg.do?method=hdxjgList&type=query",
					colList : [
						{ label : '学号', name : 'xh', index : 'xh', width : '15%'},
						{ label : '姓名', name : 'xm', index : 'xm', width : '10%' },
						{ label : '性别', name : 'xb', index : 'xb', width : '5%' },
						{ label : '学院', name : 'xymc', index : 'xymc', width : '15%' },
                        { label : '书院', name : 'symc', index : 'symc', width : '15%' },
						{ label : '专业', name : 'zymc', index : 'zymc', width : '15%' },
						{ label : '行政班级', name : 'bjmc', index : 'bjmc', width : '15%' },
                        { label : '专业班级', name : 'zybjmc', index : 'zybjmc', width : '15%' },
                        <logic:equal value="stu" name="userType">
							{ label : '活动名称', name : 'hdmc', index : 'hdmc', width : '10%' },
							{ label : '获得奖项', name : 'jxmc', index : 'jxmc', width : '10%' },
							{ label : '学分', name : 'xf', index : 'xf', width : '10%' }
                        </logic:equal>
                        <logic:notEqual value="stu" name="userType">
                        	{ label : '活动个数', name : 'hdgs', index : 'hdgs', width : '10%' ,formatter:xhLink }
						</logic:notEqual>
						],
					sortname : "xh",
					sortorder : "desc"
				}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})

        function viewPjxq(xh) {
            showDialog("查看活动详情", 720, 350, "hdgl_hdjg.do?method=hdxjgXqList&xh=" + xh);
        }
        function xhLink(cellValue, rowObject) {
            return "<a href='javascript:void(0);' class='name' onclick='viewPjxq(\""
                + rowObject["xh"] +"\");'>" + cellValue
                + "</a>";
        }

		function searchRs() {
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		//打印
		function print() {
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if(ids.length == 0  || ids.length > 1) {
				showAlertDivLayer("请选择一条记录进行导出！");
			}else{
				var xh = rows[0]['xh'];
				window.open("hdgl_hdxx.do?method=xscjdPri&xh="+xh);
			}
		}
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/hdgl_hdjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="print();return false;" class="btn_down">打印成绩单</a></li>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>100场活动列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
