<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript" src="xsgzgl/xyfd/jzgzwh/gzalwh/js/gzal.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script type="text/javascript">
		jQuery(function(){			
			var gridSetting = {
				caption : "工作案例列表",
				pager : "pager",
                url : "xyfd_gzaljl.do?method=gzalList&type=query",
                colList : [
                    { label : 'jdh', name : 'jdh', index : 'jdh',key : true,hidden : true },
                    { label : '学生姓名', name : 'xm', index : 'xm',width:'10%'},
                    { label : '学号', name : 'xh', index : 'xh',width:'10%'},
                    { label : '书院', name : 'symc', index : 'symc',width:'10%'},
                    { label : '学院', name : 'xymc', index : 'xymc',width:'10%'},
                    { label : '案例级别', name : 'aljb', index : 'aljb',width:'10%'},
                    { label : '状态', name : 'alzt', index : 'alzt', width : '5%',hidden : true },
                    { label : '状态', name : 'alztmc', index : 'alztmc', width : '5%' },
                    { label : '建档人', name : 'jdr', index : 'jdr', width : '5%',hidden : true },
                    { label : '建档时间', name : 'jdsj', index : 'jdsj', width : '5%',hidden : true },
                    { label : '是否转介', name : 'sfzj', index : 'sfzj', width : '5%',hidden : true }
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
		<html:form action="/xyfd_gzaljl">
			<input type="hidden" id="userName" name="userName" value="${userName}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="printGzal('xyfd_gzaljl.do?method=printJsp');return false;"  >下载模板</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_dr" onclick="importConfig();return false;"  >导入信息</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="ckal();return false;"  >查看案例详情</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addGzal();return false;"  >新建帮扶档案</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="addGzjl();return false;"  >添加工作记录</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cd();return false;" class="btn_shuc" >撤档</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="zj();return false;" class="btn_xg" >转介</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delAl();return false;" class="btn_sc" >删除</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="xgjb();return false;" class="btn_shuc">修改级别</a>
						</li>
						</logic:equal>
							<%--<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>--%>
							<%--<li><a href="javascript:void(0);" onclick="printzzdsqb('xgygl_zdsq.do?method=printzzdsqb');return false;" class="btn_down">下载登记表</a></li>	--%>
					</ul>
				</div>
				<!-- 过滤条件 -->	
				<%@ include file="/xsgzgl/xyfd/jzppwh/fdyywh/superSearchAreaforFdyy.jsp"%>
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
