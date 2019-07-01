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
									caption:"��У���ά���б�",
									pager:"pager",
									url:"rcsw_jqfxdmwh.do?method=jqfxDmwh&type=query",
									colList:[
									   {label:'��У������',name:'fxdm', index: 'fxdm',key:true},
									   {label:'��У�������',name:'fxmc', index: 'fxmc'}
									],
									sortname: "fxdm",
								 	sortorder: "asc"
								}
						jQuery("#dataTable").initGrid(gridSetting);
					}); 
					
					function add(){
						
						var url = "rcsw_jqfxdmwh.do?method=addJqfxDmwh";
						var title = "���ӷ�У���";
						showDialog(title,470,250,url);
					}
		

					function update(){												
						var rows = jQuery("#dataTable").getSeletRow();
						if (rows.length != 1){
							showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
						} else {
							
							var url = 'rcsw_jqfxdmwh.do?method=updateJqfxDmwh&fxdm='+ encodeURI(encodeURI(rows[0]["fxdm"]));			
							var title = "�޸ķ�У���";
							showDialog(title,470,250,url);
						}
					}

					
					function del(){
						var ids = jQuery("#dataTable").getSeletIds();
						if (ids.length == 0) {
							showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
						} else {
							var rows = jQuery("#dataTable").getSeletRow();
							showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
								"okFun" : function() {
									jQuery.post("rcsw_jqfxdmwh.do?method=delJqfxDmwh", {
										values : ids.toString()
									}, function(data) {
										var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
										mes+="</br>";
										if(data["nodel"]!="-1"){
											mes+="<font color='red'>"+data["nodel"]+"</font>";
											mes+="�ķ�У�����Ѿ�ʹ�ò���ɾ��!";
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
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
	
			<div class="toolbox">
			<div class="buttonbox">
				<ul>
					<li><a href="#" onclick="add();return false;" class="btn_zj">����</a></li>
					<li><a href="#" onclick="update();return false;" class="btn_xg">�޸�</a></li>
					<li><a href="#" onclick="del();return false;" class="btn_sc">ɾ��</a></li>					
				</ul>
			</div>
		</div>
		
		
		<div class="main_box">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>��У���ά���б� </span>
			</h3>
			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		
		
	</body>
</html>
