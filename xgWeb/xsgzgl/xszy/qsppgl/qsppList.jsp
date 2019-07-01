<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/copyDataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/qsppgl/js/qsppgl.js"></script>
		<script type="text/javascript" src="xsgzgl/xszy/comm/js/comm.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"寝室匹配",
				pager:"pager",
				url:"qsppgl.do?method=getQsppList&type=query",
				colList:[
					{label:'lddm',name:'lddm', index: 'lddm',hidden:true},
					{label:'dldm',name:'dldm', index: 'dldm',hidden:true},
					{label:'nj',name:'nj', index: 'nj',hidden:true},
					{label:'qsgxid',name:'qsgxid', index: 'qsgxid',hidden:true},
					{label:'qsfpid',name:'qsfpid', index: 'qsfpid',hidden:true},
					{label:'寝室号',name:'qsh', index: 'qsh',width:'5%',formatter:qshLink},
					{label:'楼栋名称',name:'ldmc', index: 'ldmc',width:'10%'},
					{label:'学园',name:'xymc', index: 'xymc',width:'8%'},
					{label:'性别',name:'qsxb', index: 'qsxb',width:'5%'},
					{label:'大类',name:'dl', index: 'dl',width:'13%'},
					{label:'班级',name:'bjmc', index: 'bjmc',width:'13%'},
					{label:'入住人数',name:'rzrs', index: 'rzrs',width:'5%'},
					{label:'是否混<br/>合寝室',name:'sfhhqs', index: 'bz',width:'5%'},
					{label:'所属院系代码',name:'ssyxdm', index: 'ssyxdm',hidden:true},
					{label:'所属院系',name:'ssyxmc', index: 'ssyxmc',width:'12%'},
					{label:'新生之<br/>友工号',name:'zgh', index: 'zgh',width:'8%'},
					{label:'新生之<br/>友姓名',name:'xm', index: 'xm',width:'8%'}
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
		<html:form action="/xszygl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
					<logic:equal value="xy" name="userStatus">
						<li>
							<a href="javascript:void(0);" class="btn_xg" onclick="zdpp();return false;"  >自动分配</a>
						</li>
					</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_xg" onclick="sgpp();return false;"  >手动分配</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qkppjg();return false;" class="btn_sc" >清空匹配结果</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="qsppTh();return false;" class="btn_sr" >退回至学园</a>
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
				<span>寝室匹配列表&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
