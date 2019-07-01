<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"社团列表",
						pager:"pager",
						url:"ttgl_stgl.do?method=stglList&type=query",
						colList:[
						   {label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
						   {label:'社团名称',name:'stqc', index: 'stqc',width:'10%',formatter:stLink},
						   {label:'社团类型',name:'stlx', index: 'stlx',width:'8%'},
						   {label:'业务指导单位',name:'bmmc', index: 'bmmc',width:'15%'},
						   {label:'社团指导老师',name:'stzdlsxm', index: 'stzdlsxm',width:'6%'},
						   {label:'社团人数',name:'strs', index: 'strs',width:'6%',formatter:ryLink},
						   {label:'数据来源',name:'sjly', index: 'sjly',hidden:true},
						   {label:'状态',name:'stzt', index: 'stzt',width:'10%'}
						],
						sortname: "stqc",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var url = "ttgl_stgl.do?method=addst";
            var height = jQuery(window).height()-210;
			showDialog("增加社团",790,height,url);
		}
		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			var sjly = rows[0]["sjly"];
            var height = jQuery(window).height()-210;
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要修改的记录！");
			}else if(sjly == '流程数据'){
				showAlertDivLayer("审核流数据，不能修改！");
			}else {
				var url = 'ttgl_stgl.do?method=updatest&jgid='+ rows[0]["jgid"];
				showDialog("修改社团", 790,height, url);
			}

		}
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("请选择您要删除的记录！");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		showConfirmDivLayer("您确定要删除选择的记录吗？", {
			"okFun" : function() {
				jQuery.post("ttgl_stgl.do?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="已走完审核流不能删除!";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}

}

		function stLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='View(\""
					+ rowObject["jgid"] + "\");'>" + cellValue
					+ "</a>";
		}
		function View(jgid) {
            var height = jQuery(window).height()-210;
			showDialog("查看社团信息", 790,height, "ttgl_stgl.do?method=view&jgid=" + jgid);
		}
		function ryLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='ViewRy(\""
					+ rowObject["jgid"] + "\");'>" + cellValue
					+ "</a>";
		}
		function ViewRy(jgid) {
			showDialog("社团人员信息", 550,300, "ttgl_stgl.do?method=viewry&jgid=" + jgid);
		}
		//自定义导出 功能
		function exportConfig() {
			customExport( "xg_ttgl_stgl.do", ExportData);
		}

		// 导出方法
		function ExportData() {
			setSearchTj();//设置高级查询条件
			var url = "ttgl_stgl.do?method=exportData&dcclbh=xg_ttgl_stgl.do";
			url = addSuperSearchParams(url);//设置高级查询参数
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}
		function zx() {
			var rows = jQuery("#dataTable").getSeletRow();
			var stzt = rows[0]["stzt"];
			if (rows.length != 1) {
				showAlertDivLayer("请选择一条您要注销的记录！");
			}else if(stzt == '已注销'){
				showAlertDivLayer("该团体已注销！");
			}else {
			showConfirmDivLayer("您确定要注销选择的记录吗？", {
			"okFun" : function() {
				var url = 'ttgl_stgl.do?method=stzx&jgid='+ rows[0]["jgid"];
				jQuery.post(url, {}, function(data) {
						showAlert(data["message"], {}, {
							"clkFun" : function() {
								jQuery('#search_go').click();
							}
						});
						}, 'json');
					}
				});
			}
		}
		//新版导入
		function dr() {
			toImportDataNew("IMPORT_N830105_STGLJG");
			return false;

		}
				
		</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>您的当前位置：</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">使用帮助</a>
				</p>
			</div>
		<html:form action="/xg_dwjl_dwjl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
					<ul>
						<logic:notEqual name="userType" value="stu">
				  		    <logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >增加</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >修改</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >删除</a>
							</li>
							<li><a href="#" class="btn_dr" onclick="dr();return false;" id="btn_dr">导入</a></li>
							<li><a href="#" class="btn_shbtg" onclick="zx();return false;">注销</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>	
						</logic:notEqual>
						
					</ul>
				</div>
				
				<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- 过滤条件 end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>对外列表</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
