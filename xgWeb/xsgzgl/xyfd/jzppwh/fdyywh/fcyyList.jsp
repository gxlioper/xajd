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
                url : "xyfd_fqyy.do?method=fcyyList&type=query",
                colList : [
                    { label : 'yyid', name : 'yyid', index : 'yyid',key : true,hidden : true },
                    { label : '预约号', name : 'yyh', index : 'yyh',width:'8%',formatter:yyhLink},
                    { label : '预约学生', name : 'xh', index : 'xh',hidden:true},
                    { label : '预约学生', name : 'xm', index : 'xm',width:'10%'},
                    { label : '预约人', name : 'yyr', index : 'yyr',width:'5%'},
                    { label : '辅导时间', name : 'fdsj', index : 'fdsj',width:'10%'},
                    { label : '辅导教师', name : 'fdjs', index : 'fdjs', width : '10%',hidden:true },
                    { label : '辅导教师', name : 'yhm', index : 'yhm', width : '10%',hidden:true },
                    { label : '辅导教师', name : 'fdjsxm', index : 'fdjsxm', width : '10%',hidden:true },
                    { label : '课程名称', name : 'kcmc', index : 'kcmc', width : '15%',hidden:true },
                    { label : '辅导室', name : 'fdsmc', index : 'fdsmc', width : '15%',hidden:true },
                    { label : '辅导室地点', name : 'fdsdd', index : 'fdsdd', width : '15%',hidden:true },
                    { label : '审核状态', name : 'shztmc', index : 'shztmc', width : '5%' },
                    { label : '审核状态', name : 'zt', index : 'zt', hidden : true},
                    { label : '预约详情', name : 'shztmc', index : 'shztmc', width : '40%',formatter:function (cellValue, rowObject) {
						return "（" + rowObject['fdsj'] + "）在（" + rowObject['fdsdd'] + "）由（" + rowObject['fdjsxm'] + "）进行的（" + rowObject['kcmc'] + "）";
                    } }
                ],
                sortname : "yyh",
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
		<html:form action="/xyfd_fqyy">
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<%--<li>--%>
							<%--<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;"  >发起</a>--%>
						<%--</li>--%>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">提交预约</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">取消预约</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="pjkc();return false;" class="btn_sr">评价课程</a>
						</li>
						</logic:equal>
							<%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>
							<%--<li><a href="javascript:void(0);" onclick="printzzdsqb('xgygl_zdsq.do?method=printzzdsqb');return false;" class="btn_down">下载登记表</a></li>	--%>
					</ul>
				</div>
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
