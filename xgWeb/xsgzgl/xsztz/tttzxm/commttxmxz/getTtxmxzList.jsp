<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"可参与团体项目选择列表",
					pager:"pager",
					url:"ttxm_comm.do?method=getXmSelect&type=query",
					colList:[
					   {label:'项目名称',name:'xmmc', index: 'xmmc',width:'12%',key:true},
					   {label:'项目代码',name:'xmdm', index: 'xmdm',width:'12%',hidden:true},
					   {label:'学年',name:'xn', index: 'xn',width:'12%',hidden:true},
					   {label:'学期',name:'xqmc', index: 'xqmc',width:'12%',hidden:true},
					   {label:'申报部门',name:'bmmc', index: 'bmmc',width:'12%',hidden:true},
					   {label:'联系电话',name:'lxdh', index: 'lxdh',width:'12%',hidden:true},
					   {label:'项目类别',name:'xmjbmc', index: 'xmjbmc',width:'8%'},
					   {label:'所属科目',name:'sskmmc', index: 'sskmmc',width:'5%'},
					   {label:'项目开始时间',name:'xmkssj', index: 'xmkssj',width:'8%'},
					   {label:'可参与团体数',name:'kcyrs', index: 'kcyrs',width:'20%'},
					   {label:'基础学分',name:'jcxf', index: 'jcxf',width:'20%'},
					   {label:'通过团体数',name:'tgrs', index: 'tgrs',width:'20%'},
					   {label:'申请团体数',name:'sqrs', index: 'sqrs',width:'20%',hidden:true},
					   {label:'申请团体数',name:'splc', index: 'splc',width:'20%',hidden:true}
					],
					sortname: "xmmc",
				 	sortorder: "asc"
				}
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function save() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1) {
					showAlertDivLayer("请选择一个项目！");
					return false;
				}
				var api = frameElement.api;
				var parentsW = api.get('parentDialog');
				parentsW.jQuery("#xmmc").val(rows[0]['xmmc']);
				parentsW.jQuery("#xmdm").val(rows[0]['xmdm']);
				parentsW.jQuery("#xmjbmc").text(rows[0]['xmjbmc']);
				parentsW.jQuery("#xn").text(rows[0]['xn']);
				parentsW.jQuery("#xq").text(rows[0]['xqmc']);
				parentsW.jQuery("#sbbmmc").text(rows[0]['bmmc']);
				parentsW.jQuery("#lxdh").text(rows[0]['lxdh']);
				parentsW.jQuery("#sskmmc").text(rows[0]['sskmmc']);
				parentsW.jQuery("#jcxf").text(rows[0]['jcxf']);
				parentsW.jQuery("#kcyrs").text(rows[0]['kcyrs']);
				parentsW.jQuery("#sqrs").text(rows[0]['sqrs']);
				parentsW.jQuery("#tgrs").text(rows[0]['tgrs']);
				parentsW.jQuery("#xmkssj").text(rows[0]['xmkssj']);
				parentsW.reSelectXm();
				parentsW.calculateRs();
				closeDialog();
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/ttxm_comm.do" onsubmit="return false;">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="save();return false;" class="btn_zj">添加</a>
						</li>
					</ul>
				</div>
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			</div>
		</html:form>
		<div class="toolbox">
			<h3 class="datetitle_01">
				<span>可参与团体项目选择列表</span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;height:330px;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
