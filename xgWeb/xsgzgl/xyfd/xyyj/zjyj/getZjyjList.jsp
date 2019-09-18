<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/xyyj/zjyj/js/zjyj.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "工作案例列表",
				pager : "pager",
                url : "xyfd_zjyj.do?method=getZjyjList&type=query",
                colList : [
                    { label : 'zjid', name : 'zjid', index : 'zjid',key : true,hidden : true },
                    { label : '学生姓名', name : 'xm', index : 'xm',width:'10%'},
                    { label : '学号', name : 'xh', index : 'xh',width:'10%'},
                    { label : '转介原因', name : 'zjyy', index : 'zjyy',width:'10%'},
                    { label : '转介人', name : 'zjr', index : 'zjr', width : '10%',hidden:true },
                    { label : '转介人', name : 'zjrxm', index : 'zjrxm', width : '10%' },
                    { label : '详细信息', name : 'xxxx', index : 'xxxx', width : '25%'},
                    { label : '确认信息', name : 'qrsj', index : 'qrsj', width : '15%',formatter : function (cell,rowObject) {
						if(rowObject["qrsj"]!=null&&rowObject["qrsj"]!=""){
						    return "于"+rowObject["qrsj"]+"接收";
						}else {
						    return "尚未确认";
						}
                    } }
                ],
                sortname : "jdsj",
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
		<html:form action="/xyfd_zjyj">
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="jsyj();return false;" class="btn_sr" >接收</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="fhyj();return false;" class="btn_shuc">返回</a>
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
