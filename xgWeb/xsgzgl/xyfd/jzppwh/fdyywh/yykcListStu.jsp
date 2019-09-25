<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/jzppwh/fdyywh/js/wfcyy.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "我的预约列表",
				pager : "pager",
                url : "xyfd_fdyy.do?method=fdyyListStu&type=query",
                colList : [
                    { label : 'jgid', name : 'jgid', index : 'jgid',key : true,hidden : true },
                    { label : '登记号', name : 'fdjs', index : 'fdjs', width : '8%',hidden:true  },
                    { label : '辅导教师用户名', name : 'yhm', index : 'yhm', width : '10%',hidden:true},
                    { label : '课程名称', name : 'kcmc', index : 'kcmc', width : '15%',formatter:kcLink },
                    { label : '辅导教师', name : 'xm', index : 'xm', width : '10%' ,formatter:jsLink},
                    { label : '辅导教师类型', name : 'fdjslx', index : 'fdjslx', width : '15%' },
                    { label : '辅导室', name : 'fdsmc', index : 'fdsmc', width : '15%' },
                    { label : '辅导室地点', name : 'fdsdd', index : 'fdsdd', width : '15%' },
                    { label : '数据来源', name : 'sjly', index : 'sjly', hidden : true},
                    { label : 'lrsj', name : 'lrsj', index : 'lrsj', hidden : true},
                    { label : '预约状态', name : 'lrsj', index : 'lrsj', hidden : true}
                ]
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
		<html:form action="/xyfd_fqyy">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<logic:equal name="userType" value="stu">
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add('stu');return false;"  >预约</a>
							</li>
						</ul>
					</div>
				</logic:equal>
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchAreaStu.jsp"%>
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
