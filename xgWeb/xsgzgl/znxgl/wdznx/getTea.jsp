<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption:"查询结果",
				pager:"pager",
				rowNum:10,
				url:"wdznx.do?method=getTea&type=query"+"&teaArr="+jQuery("#teaArr").val(),
				params:getSuperSearch(),
				colList:[
				   {label:'工号',name:'zgh', index: 'zgh',width:'12%',key:true},
				   {label:'姓名',name:'xm', index: 'xm',width:'8%'},
				   {label:'性别',name:'xb', index: 'xb',width:'5%'},
				   {label:'联系电话',name:'lxdh', index: 'lxdh',width:'10%'},
				   {label:'所在部门',name:'bmmc', index: 'bmdm',width:'8%'},
				   {label:'bmdm',name:'bmdm', index: 'bmdm',hidden:true}],
				sortname: "zgh",
			 	sortorder: "desc"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
			function sqxsZj() {
				var rows = jQuery("#dataTable").getSeletRow();
				jQuery.ajaxSetup({async:false});
				
				var map = getSuperSearch();
				if (rows.length == 0) {
					var url="wdznx.do?method=getTea&type=query"+"&teaArr="+jQuery("#teaArr").val();
					jQuery.post(url, {
						sffy:"0",
						searchLx:map.searchLx,
						searchTj:map.searchTj,
						searchTjz:map.searchTjz,
						input_mhcx:map.input_mhcx,
						mhcx_lx:map.mhcx_lx,
						path:map.path
					}, function(data) {
						rows=data;
						}, 'json');
				}
				var api = frameElement.api;
				var parentsW = api.get('parentDialog');
				var html = "";
				var jsrhtml = "";
				var oldjsrhtml = parentsW.jQuery("#jsrxm").val();
				var oldnum = parentsW.jQuery("#teagroup").length-1;
				for(var i=0;i<rows.length;i++){
					var jsrbh = rows[i]['zgh'];
					var jsrxm = rows[i]['xm'];
					var lxdh = rows[i]['lxdh'];
					if(jsrbh != null && jsrbh != ""){
						html += "<input type='hidden' id='tea_"+(oldnum+i+1)+"' name='jsrbh'" + " value='"+jsrbh+"' />"+"<br/>";
						html += "<input type='hidden' id='tea_lxdh_"+(oldnum+i+1)+"' name='lxdh'" + " value='"+lxdh+"' />"+"<br/>";
					}
					if(jsrxm != null || jsrxm != ""){
						jsrhtml +=jsrxm+";";
					}else{
						jsrhtml +=jsrbh+";";
					}
				}
				parentsW.jQuery("#teagroup").append(html);
				parentsW.jQuery("#jsrxm").val(oldjsrhtml+jsrhtml);
				closeDialog();
				jQuery.ajaxSetup({async:false});
			}
		</script>
	</head>

	<body>
		<html:form method="post" styleId="form" action="/wdznx">
		<input type="hidden" name="teaArr" id="teaArr" value="${teaArr}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- 按钮 -->
				<div class="buttonbox">
				<ul>
						<li>
							<a href="javascript:void(0);" onclick="sqxsZj();return false;" class="btn_zj">添加</a>
						</li>
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
				<span> 查询结果
				 </span>
			</h3>
		</div>
		<div class="formbox" style="width:100%;overflow-x:hidden;overflow-y:auto;">
			<table id="dataTable" ></table>
		</div>
			<div id="pager"></div>
	</body>
</html>
