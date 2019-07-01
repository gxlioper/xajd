<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzsq.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"�����б� ",
					pager:"pager",					
					url:"tyxs_zzsq.do?method=getZzshList",
					colList:[
							   {label:'key',name:'id', index: 'id',hidden:true,key:true},
							   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
								   return "<a href=\"javascript:ckShbb('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";					   }},
							   {label:'����',name:'xm', index: 'xm',width:'10%'},
							   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'15%'},
						       {label:'��Ժ',name:'symc', index: 'sydm',width:'13%'},
						       {label:'�����༶',name:'bjmc', index: 'bjmc',width:'13%'},
                               {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
							   {label:'����ѧ��',name:'xn',index:'xn',width:'13%'},	
							    {label:'�����ܽ��',name:'sqxfzj',index:'sqxfzj',width:'13%'},							   				
							   {label:'����ʱ��',name:'sqsj',index:'sqsj',width:'13%'},
							   {label:'shzt',name:'shzt', index: 'shzt',hidden:true},
							   {label:'splcid',name:'splcid', index: 'splcid',hidden:true},
							   {label:'���״̬',name:'shztmc', index: 'shzt',width:'5%'},							 
								{label:'���Id',name:'shid', index: 'shid',hidden:true},
								{label:'��λId',name:'xtgwid', index: 'xtgwid',hidden:true}
							],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:true
				};
				
				var map = getSuperSearch();
				map["shzt"] = "dsh";
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cancelAuding(){
				var ids = jQuery("#dataTable").getSeletIds();
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length != 1){
					showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
				}else if (rows[0]["shzt"] != "5"){
					showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
					return false;	
				}else {	
					showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
						"okFun" : function(){
						jQuery.post("tyxs_zzsq.do?method=cancelAudit",{id:ids.toString(),gwid:rows[0]["xtgwid"]},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');

						}});				
					
				}
			}


			function showAuding() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 0) {
					showAlert("��ѡ��һ����Ҫ��˵ļ�¼��");
				} else if(rows.length == 1){
					var id = rows[0]["id"];
					var url = "tyxs_zzsq.do?method=zzsh&id="+id;
					showDialog("����ѧ���������",800,500,url);
				}
			}
						
			/**
			 * ���̸���
			 * 
			 * @return
			 */
			function viewLcgz(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					if(rows[0]["shzt"]=="0"){
						showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
						return false;
					}
					showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['id']+"&splc="+rows[0]['splcid']);
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

		<html:form action="/tyxs_zzsq" method="post" styleId="tyxsZzsqForm">
			<html:hidden property="shzt" styleId="shzt"/>
		
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul><logic:equal value="1" name="cssz" property="xfzzshkg">
						
						<li id="li_sh"><a href="#" class="btn_sh" onclick="showAuding();return false;">���</a></li>
						<li id="li_qx" style="display: none;">
							<a href="javascript:void(0);" onclick="cancelAuding();return false;" 
							   class="btn_qxsh">����</a>
						</li>
							</logic:equal>
						<li><a href="#" class="btn_cs" onclick="viewLcgz();return false;">���̸���</a></li>
					</ul>
				</div>
			</logic:equal>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%">
		        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'dsh');"><span>������</span></a></li>
		        <li><a href="javascript:void(0);" onclick="selectTab(this,'ysh');"><span>�Ѵ���</span></a></li>
		      </ul>
			</div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
					<logic:notEqual value="12688" name="xxdm" >
						<span>��������б� </span>
					</logic:notEqual>
					<logic:equal value="12688" name="xxdm" >
						<span>��������б� </span>
					</logic:equal>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
</html>
