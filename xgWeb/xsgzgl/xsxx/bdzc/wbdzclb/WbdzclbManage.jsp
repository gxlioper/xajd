<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/xsxx/bdzc/wbdzclb/js/wbdzclb.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		
			
		<script type="text/javascript">
		function add(){
			var url = "xsxx_wbdlbgl.do?method=addWbdzclb";
			var title = "增加";
			showDialog(title,380,190,url);
		}
		
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {
				var zjlxbs = rows[0]["bs"];
				if(zjlxbs == "是"){
					showAlertDivLayer("系统内置记录不能修改和删除！");
				}else{
					var url = 'xsxx_wbdlbgl.do?method=updateWbdzclb&wbdlbdm='+rows[0]["wbdlbdm"];
					var title = "修改";
					showDialog(title,400,250,url);
				}
			}
		}

		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要删除的记录！");
			}else {
				showConfirmDivLayer("您确定要删除选择的记录吗？",{"okFun":function(){
				jQuery.post("xsxx_wbdlbgl.do?method=delWbdzclb",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
					},'json');
				}});
	}
}

		var gridSetting = {
				caption:"理由列表",
				pager:"pager",
				url:"xsxx_wbdlbgl.do?method=wbdzclbManage&type=query",
				colList:[
				   {label:'类别代码',name:'wbdlbdm', index: 'wbdlbdm',key:true,width:'30%'},
				   {label:'类别名称',name:'wbdlbmc', index: 'wbdlbmc',width:'30%'}
<%--				暂时屏蔽，不显示   {label:'是否系统内置',name:'zjlxbs', index: 'zjlxbs',width:'30%'}--%>
				   
				],
				sortname: "wbdlbdm",
			 	sortorder: "asc"
			} 


			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

		function query(){
			var map = {};
			var wbdlbmc = jQuery("#wbdlbmc").val();
			map["wbdlbmc"] = jQuery.trim(wbdlbmc);
			if (jQuery.trim(wbdlbmc) != ""){
				map["wbdlbmc"] = jQuery.trim(wbdlbmc);
			}
			jQuery("#dataTable").reloadGrid(map);
		}
		
		
		</script>
	</head>
	<body>
	<html:form action="/xsxx_wbdlbgl" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- 按钮 -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">增加</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">修改</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">删除</a></li>						
				</ul>
			</div>
			</logic:equal>
			
			<!-- 过滤条件 -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%" >类别名称</th>
						<td>
							<input type="text" id="wbdlbmc"  maxleng="20" 
							   onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									查 询
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
			<div class="formbox">
			<!--标题start-->
				<h3 class="datetitle_01">
					<span> 未报到类别维护 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
