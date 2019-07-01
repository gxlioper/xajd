<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/qsppgl/js/qsppgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/comm/js/comm.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/tjcxgl/js/tjcxgl.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"寝室统计",
				pager:"pager",
				url:"qsppgl.do?method=getQstjList&type=query",
				colList:[
					{label:'lddm',name:'lddm', index: 'lddm',hidden:true},
					{label:'dldm',name:'dldm', index: 'dldm',hidden:true},
					{label:'nj',name:'nj', index: 'nj',hidden:true},
					{label:'qsgxid',name:'qsgxid', index: 'qsgxid',hidden:true},
					{label:'qsfpid',name:'qsfpid', index: 'qsfpid',hidden:true},
					{label:'寝室号',name:'qsh', index: 'qsh',width:'5%',formatter:qshLink},
					{label:'楼栋名称',name:'ldmc', index: 'ldmc',width:'8%'},
					{label:'学园',name:'xymc', index: 'xymc',width:'8%'},
					{label:'性别',name:'qsxb', index: 'qsxb',width:'5%'},
					{label:'大类',name:'dl', index: 'dl',width:'15%'},
					{label:'入住<br/>人数',name:'rzrs', index: 'rzrs',width:'5%'},
					{label:'是否混<br/>合寝室',name:'sfhhqs', index: 'bz',width:'5%'},
					{label:'所属院系',name:'ssyxmc', index: 'ssyxmc',width:'10%'},
					{label:'新生之<br/>友工号',name:'zgh', index: 'zgh',width:'5%'},
					{label:'新生之<br/>友姓名',name:'xm', index: 'xm',width:'5%'},
					{label:'新生之友<br/>联系电话',name:'lxdh', index: 'lxdh',width:'10%'}
					],
			 	radioselect:false
		}
		jQuery(function(){
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qsppgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					    <li>
							<a href="javascript:void(0);" class="btn_dc" onclick="qstjExport();return false;"  >导出</a>
						</li>
						 <li>
							<a href="javascript:void(0);" class="btn_dc" onclick="qstjExportGroup();return false;"  >按部门导出</a>
						</li>
						 <li>
							<a href="javascript:void(0);" class="btn_dc" onclick="fwExport();return false;"  >发文导出</a>
						</li>
					<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="ryzsPrint();return false;" class="btn_dy" >荣誉证书下载打印</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="lxkPrint();return false;" class="btn_dy" >联系卡下载打印</a>
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
				<span>寝室统计列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
