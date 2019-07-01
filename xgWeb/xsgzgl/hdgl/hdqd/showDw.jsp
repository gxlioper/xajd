<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			function dcmcLink(cellValue, rowObject) {
				return '<button type=\'button\' onclick=\'jrzd("'+rowObject["dwid"]+'","'+rowObject["dzxm"]+'","'+rowObject["hdid"]+'");\' class=\'btn_01\' >选择</button>';
			}
			
			var gridSetting = {
				caption:"组队列表",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"hdgl_hdgl_hdqd_wh.do?method=getDwList&type=query&hdid="+'${hdid}'+"&xh="+'${xh}',
				colList:[
				   {label:'hdid',name:'hdid', index: 'hdid',hidden : true},
				   {label:'队伍编码',name:'dwid', index: 'dwid',width:'20%',key : true},
				   {label:'队长',name:'dzxm', index: 'dzxm',width:'20%'},
                   {label:'队伍人数',name:'rs', index: 'rs',width:'20%'},
				   {label:'操作',name:'cz', index: 'cz',width:'20%',formatter:dcmcLink}
				]
			}

			function jrzd(dwid,dzxm,hdid){
				var xh = jQuery("#xh").val();
                jQuery.post("hdgl_hdgl_hdqd_wh.do?method=jrdwCheck",{hdid:hdid,dwid:dwid,xh:xh},function(data){
                    if(data["message"] == "true"){
						debugger;
						var gotoPath = jQuery("#gotoPath").val();
						if (gotoPath.split("?").length > 1){
							gotoPath = gotoPath+"&dwid="+dwid+"&dzxm="+encodeURI(encodeURI(dzxm));
						} else {
							gotoPath = gotoPath+"&dwid="+dwid+"&dzxm="+encodeURI(encodeURI(dzxm));
						}
						var api = frameElement.api;
						if (api){
							if (api.get('childDialog')){
								api.reload(api.get('parentDialog') ,gotoPath);
							} else {
								var W = api.opener;
								W.location=gotoPath;			
							}
						} else if (parent.window){
							parent.window.document.location=gotoPath;						
						}
						api.close();			
                        // var url = "hdgl_hdxx.do?method=bm&hdid="+hdid+"&lx=jrdw&dwid="+dwid;//加入队伍报名
                        // showDialog("活动报名表单", 800, 550, url);
                    } else {
                        showAlert(data["message"]);
                    }
                },'json');
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
		</script>
	</head>

	<body>
		<div class="formbox">
			<!--标题start-->
			<input type="hidden" value="${gotoPath}" id="gotoPath"/>
			<input type="hidden" name="xh" id="xh" value="${xh}" />
			<h3 class="datetitle_01">
				<span> 教师信息列表
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
