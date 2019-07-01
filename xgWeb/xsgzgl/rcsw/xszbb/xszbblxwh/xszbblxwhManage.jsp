<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/rcsw/xszbb/xszbblxwh/js/xszbblxwhManage.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		
			
		<script type="text/javascript">
		function add(){
			var url = "rcsw_xszbb_bblxwhgl.do?method=addXszbblx";
			var title = "增加证件";
			showDialog(title,400,200,url);
		}
		
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("请选择一条您要修改的记录！");
			} else {
				//var zjlxbs = rows[0]["zjlxbs"];
				//if(zjlxbs == "是"){
				//	showAlertDivLayer("系统内置记录不能修改和删除！");
				//}else{

					var url = 'rcsw_xszbb_bblxwhgl.do?method=updateXszbblx&xszbblxdm='+rows[0]["xszbblxdm"];
					var title = "修改证件";
					showDialog(title,400,200,url);
				//}
			}
		}

		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("请选择您要删除的记录！");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<rows.length;i++){
					if(rows[i]['zjlxbs']== "是"){
						showAlertDivLayer("系统内置记录不能修改和删除！");
						return false;
					}
				}

				showConfirmDivLayer("您确定要删除选择的记录吗？", {
					"okFun" : function() {
						jQuery.post("rcsw_xszbb_bblxwhgl.do?method=delXszbblx", {
							values : ids.toString()
						}, function(data) {
							var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
							mes+="</br>";
							if(data["nodel"]!="-1"){
								mes+="<font color='red'>"+data["nodel"]+"</font>";
								mes+="的证件名称已经使用不能删除!";
							}
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
				});
			}
		}

		var gridSetting = {
				caption:"证件列表",
				pager:"pager",
				url:"rcsw_xszbb_bblxwhgl.do?method=xszbblxwhManage&type=query",
				colList:[
				   {label:'证件代码',name:'xszbblxdm', index: 'xszbblxdm',key:true},
				   {label:'证件名称',name:'xszbblxmc', index: 'xszbblxmc'},
				   {label:'审核流程',name:'lcxx', index: 'lcxx'},
				   {label:'是否系统内置',name:'zjlxbs', index: 'zjlxbs'}
				   
				],
				sortname: "xszbblxdm",
			 	sortorder: "asc"
			} 


			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

		function query(){
			var map = {};
			var xszbblxmc = jQuery("#xszbblxmc").val();
			map["xszbblxmc"] = jQuery.trim(xszbblxmc);
			if (jQuery.trim(xszbblxmc) != ""){
				map["xszbblxmc"] = jQuery.trim(xszbblxmc);
			}
			jQuery("#dataTable").reloadGrid(map);
		}
		
		
		</script>
	</head>
	<body>
	<html:form action="/rcsw_xszbb_bblxwhgl" method="post">
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
						<th width="12%" >证件名称</th>
						<td>
							<input type="text" id="xszbblxmc" name="xszbblxmc" maxleng="20" 
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
					<span> 证件维护列表 </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
