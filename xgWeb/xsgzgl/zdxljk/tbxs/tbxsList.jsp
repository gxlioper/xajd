<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"zdxljkTbxs.do?method=getTbxsList",
				colList:[
				   {label:'学号',name:'xh', index: 'xh',key:true,formatter:function(v,r){
					   return "<a class='name' href='javascript:cksq(\""+v+"\")'>"+v+"</a>";
				   }},
				   {label:'canDel',name:'candel', index: 'candel',hidden:true},
				   {label:'姓名',name:'xm', index: 'xm'},
				   {label:'年级',name:'nj', index: 'nj'},
				   {label:'学院',name:'xymc', index: 'xydm'},
				   {label:'班级',name:'bjmc', index: 'bjdm'},
				   {label:'学生类型',name:'gxlx', index: 'gxlx'},
				   {label:'访谈时间',name:'thsj', index: 'thsj'},
				   {label:'关注状态',name:'gzlx', index: 'gzlx'}
				],
				params:{zxzt:"在校"}
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(xh){
				showDialog('查看',700,500,'zdxljkTbxs.do?method=viewThjl&xh='+xh);
			}

			function searchRs(){
				var map = getSuperSearch();
				var zxzt = jQuery("#zxzt").val();
				map["zxzt"] = zxzt;
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("请选择一条您要修改的记录！");
				} else {
					showDialog('修改',700,500,'zdxljkTbxs.do?method=editThjl&xh='+rows[0]["xh"]);
				}
			}

			function thjlDel(){
				var ids = jQuery("#dataTable").getSeletIds();
				var warnMsg="";
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0){
					showAlertDivLayer("请选择您要删除的记录！");
					return false;
				} 
				for(var i=0;i<ids.length;i++){
					if(rows[i]['candel']=='N'){
						if(warnMsg!=""){
							warnMsg+=",";
							}
						warnMsg+=rows[i]["xh"];
					}
				}
				if(warnMsg!=""){
					showAlertDivLayer("["+warnMsg+"]有超过24小时的操作记录，不允许删除！");
					return false;
				}
				showConfirmDivLayer("您确定要删除该记录吗？",{"okFun":function(){
					jQuery.post("zdxljkTbxs.do?method=delThjl",{ids:ids.toString()},function(data){
						alertInfo(data["message"]);
						searchRs();
						},'json');
				}});
				
			}
			
			function addThjl(){
				showDialog('谈话记录录入',700,500,'zdxljkTbxs.do?method=addThjl');;
			}
			
			function importJdqk(){
				toImportDataNew("xljk_zjdx_tbxs");
				return false;
			}
			
			//导出
			function exportConfig(){
				var DCCLBH='xljk_zjdx_tbxs.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='xljk_zjdx_tbxs.do';
				setSearchTj();//设置高级查询条件
				var zxzt=jQuery("#zxzt").val();
				var url = "zdxljkTbxs.do?method=export&dcclbh=" + DCCLBH+"&zxzt="+zxzt;//dcclbh,导出功能编号
				url = addSuperSearchParams(url);//设置高级查询参数
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function selectTab(obj,xszt){
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				
				var zxzt = xszt == "zxs" ? "在校" : "不在校";
				jQuery("#zxzt").val(zxzt);
				var map = getSuperSearch();
				map["zxzt"] = zxzt;
				jQuery("#dataTable").reloadGrid(map);
				
				if (xszt == "bys"){
					jQuery(".buttonbox ul li:not(:last)").hide();
				} else {
					jQuery(".buttonbox ul li").show();
				}
			}
			
			
			function szgz(){
				var ids = jQuery("#dataTable").getSeletIds();
                var flag = true;
				if (ids.length == 0){
					showAlertDivLayer("请选择您需要操作的学生！");
				} else {
					showDialog("设置关注",480,200,"zdxljkTbxs.do?method=szgz&ids="+ids.toString());
				}
			}
			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jddj_jdqk" method="post">
			<input type="hidden" id="zxzt" value="在校"/>
		
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- 按钮 -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="addThjl()" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="thjlDel();" class="btn_sc">删除</a></li>	
					<li><a href="javascript:void(0);" onclick="szgz();" class="btn_csh">设置关注</a></li>							
					<li><a href="javascript:void(0);" onclick="importJdqk();" class="btn_dr">导入</a></li>						
					<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">导出</a></li>						
				</ul>
			</div>
			<!-- 过滤条件 -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- 过滤条件 end-->
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%">
		        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'zxs');"><span>在校生</span></a></li>
		        <li><a href="javascript:void(0);" onclick="selectTab(this,'bys');"><span>非在校生</span></a></li>
		      </ul>
			</div>
		</div>
	
		<div class="formbox">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>特别关心学生列表 </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
