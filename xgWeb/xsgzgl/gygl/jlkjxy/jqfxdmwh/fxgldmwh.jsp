<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript">

		            var doType;
					jQuery(function(){
					     var gridSetting = {
									caption:"返校类别维护列表",
									pager:"pager",
									url:"jlkjxy_jqfxdmwh.do?method=jlkjfxglDmwh&type=query",
									colList:[
									   {label:'返校类别代码',name:'fxdm', index: 'fxdm',key:true},
									   {label:'返校类别名称',name:'fxmc', index: 'fxmc'}
									],
									sortname: "fxdm",
								 	sortorder: "asc"
								}
						jQuery("#dataTable").initGrid(gridSetting);
					}); 
					
					function add(){
						
						var url = "jlkjxy_jqfxdmwh.do?method=addFxglDmwh";
						var title = "增加返校类别";
						showDialog(title,470,200,url);
					}
		

					function update(){												
						var rows = jQuery("#dataTable").getSeletRow();
						if (rows.length != 1){
							showAlertDivLayer("请选择一条您要修改的记录！");
						} else {
							
							var url = 'jlkjxy_jqfxdmwh.do?method=updateFxglDmwh&fxdm='+ encodeURI(encodeURI(rows[0]["fxdm"]));			
							var title = "修改返校类别";
							showDialog(title,470,190,url);
						}
					}

					
					function del(){
						var ids = jQuery("#dataTable").getSeletIds();
						if (ids.length == 0) {
							showAlertDivLayer("请选择您要删除的记录！");
						} else {
							var rows = jQuery("#dataTable").getSeletRow();
							showConfirmDivLayer("您确定要删除选择的记录吗？", {
								"okFun" : function() {
									jQuery.post("jlkjxy_jqfxdmwh.do?method=delFxglDmwh", {
										values : ids.toString()
									}, function(data) {
										var mes="成功删除了<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>条数据";
										mes+="</br>";
										if(data["nodel"]!="-1"){
											mes+="<font color='red'>"+data["nodel"]+"</font>";
											mes+="的返校名称已经使用不能删除!";
										}
										showAlertDivLayer(mes);
										jQuery("#dataTable").reloadGrid();
									}, 'json');
								}
							});
						}
					}
								
					
		</script>
	</head>
	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>${title}</a>
			</p>
		</div>
	
			<div class="toolbox">
			<div class="buttonbox">
				<ul>
					<li><a href="#" onclick="add();return false;" class="btn_zj">增加</a></li>
					<li><a href="#" onclick="update();return false;" class="btn_xg">修改</a></li>
					<li><a href="#" onclick="del();return false;" class="btn_sc">删除</a></li>					
				</ul>
			</div>
		</div>
		
		
		<div class="main_box">
			<!--标题start-->
			<h3 class="datetitle_01">
				<span>返校类别维护列表 </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		
		
	</body>
</html>
