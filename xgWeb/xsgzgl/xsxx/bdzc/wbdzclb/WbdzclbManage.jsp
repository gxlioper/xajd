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
			var title = "����";
			showDialog(title,380,190,url);
		}
		
		function update(){
			var rows = jQuery("#dataTable").getSeletRow();
			if (rows.length != 1){
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			} else {
				var zjlxbs = rows[0]["bs"];
				if(zjlxbs == "��"){
					showAlertDivLayer("ϵͳ���ü�¼�����޸ĺ�ɾ����");
				}else{
					var url = 'xsxx_wbdlbgl.do?method=updateWbdzclb&wbdlbdm='+rows[0]["wbdlbdm"];
					var title = "�޸�";
					showDialog(title,400,250,url);
				}
			}
		}

		function del() {
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0) {
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			}else {
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("xsxx_wbdlbgl.do?method=delWbdzclb",{values:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
					},'json');
				}});
	}
}

		var gridSetting = {
				caption:"�����б�",
				pager:"pager",
				url:"xsxx_wbdlbgl.do?method=wbdzclbManage&type=query",
				colList:[
				   {label:'������',name:'wbdlbdm', index: 'wbdlbdm',key:true,width:'30%'},
				   {label:'�������',name:'wbdlbmc', index: 'wbdlbmc',width:'30%'}
<%--				��ʱ���Σ�����ʾ   {label:'�Ƿ�ϵͳ����',name:'zjlxbs', index: 'zjlxbs',width:'30%'}--%>
				   
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
						<th width="12%" >�������</th>
						<td>
							<input type="text" id="wbdlbmc"  maxleng="20" 
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
					<span> δ�������ά�� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
