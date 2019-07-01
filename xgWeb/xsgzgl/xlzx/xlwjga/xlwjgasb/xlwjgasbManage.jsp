<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"心理危机个案列表",
				pager:"pager",
				url:"xlzx_xlwjga_xlwjgasbgl.do?method=xlwjgasbManage&type=query",
				colList:[
				   {label:'主键',name:'id', index: 'id',key:true,hidden : true},
				   {label:'学号',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
				  {label:'姓名',name:'xm', index: 'xm',width:'10%'},
				  {label:'性别',name:'xb', index: 'xb',width:'7%'},
				  {label:'班级',name:'bjmc', index: 'bjmc',width:'10%'},
				  {label:'联系电话',name:'lxdh', index: 'lxdh',width:'10%'},
				   {label:'上报人',name:'sbrxm', index: 'sbrxm',width:'10%'},
				   {label:'上报人联系电话',name:'sbrlxfs', index: 'sbrlxfs',width:'10%'},
				   {label : '危机程度',name : 'wjcdmc',index : 'wjcdmc',width : '8%'},
				   {label : '中心反馈',name : 'zzfkmc',index : 'zzfkmc',width : '8%'},
				   {label : '危机个案状态',name : 'wjgabzmc',index : 'wjgabzmc',width : '9%'},
				   {label : '危机程度代码',name : 'wjcd',index : 'wjcd',hidden : true},
				   {label : '中心反馈代码',name : 'zzfk',index : 'zzfk',hidden : true},
				   {label : '危机个案状态代码',name : 'wjgabz',index : 'wjgabz',hidden : true},
				   {label : '上报时间',name : 'sbsj',index : 'sbsj',hidden : true}
				],
				sortname: "sbsj",
			 	sortorder: "desc"
			}
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='xlwjgasbView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}
			function xlwjgasbView(xh) {
				showDialog("查看心理危机个案", 750,500, "xlzx_xlwjga_xlwjgasbgl.do?method=viewXlwjgasb&xh=" + xh);
			}
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});
			function add(){
				var url = "xlzx_xlwjga_xlwjgasbgl.do?method=addXlwjgasb";
				var title = "增加心理危机个案";
				showDialog(title,750,500,url);
			}
			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer(jQuery("#lable_one_xg").val());
				} else {
					if(rows[0]['zzfk']!='0'){
						showAlertDivLayer("只能修改未反馈的记录！");
						return false;
					}
					var url = 'xlzx_xlwjga_xlwjgasbgl.do?method=updateXlwjgasb&xh='+rows[0]["xh"];
					var title = "修改心理危机个案";
					showDialog(title,750,500,url);
				}
			}
			function del(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0){
					showAlertDivLayer(jQuery("#lable_some_sc").val());
				} else {
					for(var i=0;i<ids.length;i++){
						if(rows[i]['zzfk']!='0'){
							showAlertDivLayer("只能删除未反馈的记录！");
							return false;
						}
					}
					showConfirmDivLayer(jQuery("#lable_confirm_sc").val(),{"okFun":function(){
							jQuery.post("xlzx_xlwjga_xlwjgasbgl.do?method=delXlwjgasb",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			function cancel(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要解除的记录！");
				} else {
					for(var i=0;i<ids.length;i++){
						if(rows[i]['zzfk']!='1'){
							showAlertDivLayer("只能解除已反馈的记录！");
							return false;
						}
						if(rows[i]['wjgabz']!='1'){
							showAlertDivLayer("只能解除危机个案的记录！");
							return false;
						}
					}
					showConfirmDivLayer("您确定要解除选择的记录吗？",{"okFun":function(){
							jQuery.post("xlzx_xlwjga_xlwjgasbgl.do?method=cancelXlwjgasb",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
				}
			}
			var DCCLBH = "xlzx_xlwjga_xlwjgasb.do";//dcclbh,导出功能编号
			//自定义导出 功能
			function exportConfig() {
				//DCCLBH导出功能编号,执行导出函数 
				customExport(DCCLBH, kycxxmjgExportData);
			}
			// 导出方法
			function kycxxmjgExportData() {
				setSearchTj();//设置高级查询条件
				var url = "xlzx_xlwjga_xlwjgasbgl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
	<!-- 标题 -->
	<div class="tab_cur">
		<p class="location">
			<em>您的当前位置：</em><a>${title }</a>
		</p>
		<p class="help">
			<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">使用帮助</a>
		</p>
	</div>
	<!-- 标题 end-->
	<!-- 提示信息 -->
	<div class="prompt" id="div_help" style="display: none">
		<h3>
			<span>提示：</span>
		</h3>
		<p>
			<span>
				已上报危机个案学生，点击【增加】重复新增时，会新产生一条危机个案记录，而前台只显示最新记录，可通过点击学号查看该学生历史危机个案情况。
			</span>
		</p>
		<a class="close" title="隐藏" onclick="this.parentNode.style.display='none';"></a>
	</div>
	<!-- 提示信息 end-->
	<html:form action="/xlzx_xlwjga_xlwjgasbgl" method="post">
		<%@ include file="/comm/hiddenValue.jsp"%>
		
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
						<li><a href="javascript:void(0);" onclick="cancel();" class="btn_qxsh">危机解除</a></li>						
					</logic:equal>
					<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">导出</a></li>
				</ul>
			</div>
			</logic:equal>
			<!-- 过滤条件 -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
		</div>
			<div class="formbox">
				<h3 class="datetitle_01">
					<span>心理危机个案列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
