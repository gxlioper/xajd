<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" >
			var gridSetting = {
					caption:"�ȼ��б�",
					pager:"pager",
					url:"xsxx_gygl_cxcxdj.do?method=cxdjwhList&type=query",
					colList:[
					   {label:'���еȼ�����',name:'cxdjdm', index: 'cxdjdm',key:true},
					   {label:'���еȼ�����',name:'cxdjmc', index: 'cxdjmc',width:'60%'}
					],
					sortname: "cxdjdm",
				 	sortorder: "asc"
				}
	
	
	
				jQuery(function(){
					jQuery("#dataTable").initGrid(gridSetting);
				});
			function add(){
				var url = "xsxx_gygl_cxcxdj.do?method=addCxdj";
				var title = "���Ӳ��еȼ�";
				showDialog(title,400,250,url);
			}
			function query(){
				var map = {};
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();

				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					var url = 'xsxx_gygl_cxcxdj.do?method=updateCxdj&cxdjdm='+rows[0]["cxdjdm"];
					var title = "�޸Ĳ��еȼ�";
					showDialog(title,400,250,url);
				}
			}

			function del(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
							jQuery.post("xsxx_gygl_cxcxdj.do?method=delCxdj",{values:ids.toString()},function(data){
								showAlertDivLayer(data["message"]);
								jQuery("#dataTable").reloadGrid();
							},'json');
					}});
					
				}
			}
			</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	
		<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>		
				</ul>
			</div>
			</logic:equal>				
		</div>
		<button type="button" class="btn_cx" id="search_go" onclick="query()" style="display: none">
			�� ѯ
		</button>

		
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �ȼ�����ά���б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
