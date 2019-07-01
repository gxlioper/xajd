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
			var title = "����֤��";
			showDialog(title,400,200,url);
		}
		
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				//var zjlxbs = rows[0]["zjlxbs"];
				//if(zjlxbs == "��"){
				//	showAlertDivLayer("ϵͳ���ü�¼�����޸ĺ�ɾ����");
				//}else{

					var url = 'rcsw_xszbb_bblxwhgl.do?method=updateXszbblx&xszbblxdm='+rows[0]["xszbblxdm"];
					var title = "�޸�֤��";
					showDialog(title,400,200,url);
				//}
			}
		}

		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			} else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<rows.length;i++){
					if(rows[i]['zjlxbs']== "��"){
						showAlertDivLayer("ϵͳ���ü�¼�����޸ĺ�ɾ����");
						return false;
					}
				}

				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
					"okFun" : function() {
						jQuery.post("rcsw_xszbb_bblxwhgl.do?method=delXszbblx", {
							values : ids.toString()
						}, function(data) {
							var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
							mes+="</br>";
							if(data["nodel"]!="-1"){
								mes+="<font color='red'>"+data["nodel"]+"</font>";
								mes+="��֤�������Ѿ�ʹ�ò���ɾ��!";
							}
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
				});
			}
		}

		var gridSetting = {
				caption:"֤���б�",
				pager:"pager",
				url:"rcsw_xszbb_bblxwhgl.do?method=xszbblxwhManage&type=query",
				colList:[
				   {label:'֤������',name:'xszbblxdm', index: 'xszbblxdm',key:true},
				   {label:'֤������',name:'xszbblxmc', index: 'xszbblxmc'},
				   {label:'�������',name:'lcxx', index: 'lcxx'},
				   {label:'�Ƿ�ϵͳ����',name:'zjlxbs', index: 'zjlxbs'}
				   
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
			
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tr>
						<th width="12%" >֤������</th>
						<td>
							<input type="text" id="xszbblxmc" name="xszbblxmc" maxleng="20" 
							   onkeypress="if(pressEnter(event)){query();return false;}"
							/>
						</td>
						<td>
							<div class="btn">
								<button type="button" class="btn_cx" id="search_go" onclick="query()">
									�� ѯ
								</button>
							</div>
						</td>
					</tr>					
				</table>
			</div>
		</div>
			<div class="formbox">
			<!--����start-->
				<h3 class="datetitle_01">
					<span> ֤��ά���б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
