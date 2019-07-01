<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			function dcmcLink(cellValue, rowObject) {
				return '<button type=\'button\' onclick=\'jrzd("'+rowObject["dwid"]+'","'+rowObject["hdid"]+'");\' class=\'btn_01\' >ѡ��</button>';
			}
			
			var gridSetting = {
				caption:"����б�",
				pager:"pager",
				multiselect:false,
				rowNum:10,
				url:"hdgl_hdxx.do?method=getDwList&type=query&hdid="+'${hdid}',
				colList:[
				   {label:'hdid',name:'hdid', index: 'hdid',hidden : true},
				   {label:'�������',name:'dwid', index: 'dwid',width:'20%',key : true},
				   {label:'�ӳ�',name:'dzxm', index: 'dzxm',width:'20%'},
                   {label:'��������',name:'rs', index: 'rs',width:'20%'},
				   {label:'����',name:'cz', index: 'cz',width:'20%',formatter:dcmcLink}
				]
			}

			function jrzd(dwid,hdid){
                jQuery.post("hdgl_hdxx.do?method=jrdwCheck",{hdid:hdid,dwid:dwid},function(data){
                    if(data["message"] == "true"){
                        var url = "hdgl_hdxx.do?method=bm&hdid="+hdid+"&lx=jrdw&dwid="+dwid;//������鱨��
                        showDialog("�������", 800, 550, url);
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
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ��ʦ��Ϣ�б�
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
