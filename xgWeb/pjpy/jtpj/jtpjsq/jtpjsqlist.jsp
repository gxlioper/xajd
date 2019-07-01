<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript"
			src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="pjpy/jtpj/jtpjsq/js/jtpjsq.js"></script>
		<script type="text/javascript">
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="jtpjsq.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="jtpjsq.do?method=getDjbWord&sqid="+rows[0]["sqid"];
					window.open(url);
			     }
			}
			//通用登记表下载功能
			function getDjb(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("请选择您要下载的记录！");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="jtpjsq.do?method=getDjbZip&value="+ids;
					window.open(url);
				 } else {
					var url="jtpjsq.do?method=getDjbWord&sqid="+rows[0]["sqid"];
					window.open(url);
			     }
			}
			jQuery(function(){
			     var gridSetting = {
							pager:"pager",
							url:"jtpjsq.do?method=list&type=query",
							params:getSuperSearch(),
							colList:[
							   {label:'奖项申请id',name:'sqid', index: 'sqid',key:true,hidden:true},
							   {label:'审批流程',name:'splcid', index: 'splcid',hidden:true},
							   {label:'申请集体 ',name:'pjjtmc', index: 'pjjtmc',formatter:pjjtmc},
							   {label:'人数',name:'rs', index: 'rs'},
							   {label:'申请奖项名称',name:'jxmc', index: 'jxmc'},
							   {label:'评定学年',name:'pdxn', index: 'pdxn',hidden:true},
							   {label:'评定学期',name:'pdxqmc', index: 'pdxqmc',hidden:true},
							   {label:'奖项评定周期',name:'jxpdzq', index: 'pdxn,pdxq',formatter:jxpdzq},
							   {label:'申请时间',name:'sqsj', index: 'sqsj'},
							   {label:'审核状态',name:'shzt', index: 'shzt',hidden:true},
							   {label:'申请奖项ID',name:'jxid', index: 'jxid',hidden:true},
							   {label:'审核状态',name:'shztmc', index: 'shzt',formatter:shzt}
							],
							sortname: "pdxn,pdxq,sqsj",
						 	sortorder: "desc"
						}
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
		<html:form action="/jtpjsq.do?method=list&type=query">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" onclick="add();return false;" class="btn_zj">申请</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">修改</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">删除</a>
						</li>
						<li style="display:none">
							<a href="javascript:void(0);" onclick="tj();return false;" class="btn_tj">提交</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancle();return false;" class="btn_sr">撤销</a>
						</li>
					</logic:equal>
					<li><a href="javascript:void(0);" onclick="rcxwshLcinfo();return false;" 
						   title="选中一条记录，点击该按钮可以查看审核流程。"
						   class="btn_cs">流程跟踪</a></li>
					<!-- 上海电机学院 begin -->
					<logic:equal name="xxdm" value="11458">	
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">下载登记表</a></li>	
					</logic:equal>
					<logic:notEqual name="xxdm" value="11458">	
					<logic:notEqual name="xxdm" value="11067">	
						<li><a href="javascript:void(0);" onclick="getDjb();return false;" class="btn_down">下载登记表</a></li>	
					</logic:notEqual>
					</logic:notEqual>
					<!-- 上海电机学院 end -->
				</ul>
				</div>
				<!-- 过滤条件 -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		<div class="toolbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span style="width: 50%">集体评奖申请列表</span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
