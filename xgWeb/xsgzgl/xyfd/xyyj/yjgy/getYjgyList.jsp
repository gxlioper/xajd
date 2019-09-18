<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/xyyj/yjgy/js/yjgy.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "学业预警列表",
				pager : "pager",
                url : "xyfd_yjgy.do?method=yjgyList&type=query",
                colList : [
                    { label : '学生姓名', name : 'xm', index : 'xm',width:'20%'},
                    { label : '学号', name : 'xh', index : 'xh',width:'10%',formatter:function (cell,rowObject) {
                        return "<a href='#' onclick=xscjck('" + rowObject["xh"] + "')>" + rowObject["xh"] + "</a>";
                    }},
                    { label : '预警原因', name : 'yjyy', index : 'yjyy',width:'10%'},
                    { label : '详细信息', name : 'xxxx', index : 'xxxx', width : '60%'}
                ],
                sortname : "xh",
                sortorder : "desc"
            }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})

        function searchRs() {
            var map = getSuperSearch()
            jQuery("#dataTable").reloadGrid(map);
        }

		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xyfd_xyyj">
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="yjzj();return false;"  >转介</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="gyjs();return false;"  >干预结束</a>
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
				<span>活动补录申请列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
