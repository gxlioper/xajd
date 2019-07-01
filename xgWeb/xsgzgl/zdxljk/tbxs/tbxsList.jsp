<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"zdxljkTbxs.do?method=getTbxsList",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',key:true,formatter:function(v,r){
					   return "<a class='name' href='javascript:cksq(\""+v+"\")'>"+v+"</a>";
				   }},
				   {label:'canDel',name:'candel', index: 'candel',hidden:true},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�꼶',name:'nj', index: 'nj'},
				   {label:'ѧԺ',name:'xymc', index: 'xydm'},
				   {label:'�༶',name:'bjmc', index: 'bjdm'},
				   {label:'ѧ������',name:'gxlx', index: 'gxlx'},
				   {label:'��̸ʱ��',name:'thsj', index: 'thsj'},
				   {label:'��ע״̬',name:'gzlx', index: 'gzlx'}
				],
				params:{zxzt:"��У"}
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(xh){
				showDialog('�鿴',700,500,'zdxljkTbxs.do?method=viewThjl&xh='+xh);
			}

			function searchRs(){
				var map = getSuperSearch();
				var zxzt = jQuery("#zxzt").val();
				map["zxzt"] = zxzt;
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog('�޸�',700,500,'zdxljkTbxs.do?method=editThjl&xh='+rows[0]["xh"]);
				}
			}

			function thjlDel(){
				var ids = jQuery("#dataTable").getSeletIds();
				var warnMsg="";
				var rows = jQuery("#dataTable").getSeletRow();
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
					return false;
				} 
				for(var i=0;i<ids.length;i++){
					if(rows[i]['candel']=='N'){
						if(warnMsg!=""){
							warnMsg+=",";
							}
						warnMsg+=rows[i]["xh"];
					}
				}
				if(warnMsg!=""){
					showAlertDivLayer("["+warnMsg+"]�г���24Сʱ�Ĳ�����¼��������ɾ����");
					return false;
				}
				showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
					jQuery.post("zdxljkTbxs.do?method=delThjl",{ids:ids.toString()},function(data){
						alertInfo(data["message"]);
						searchRs();
						},'json');
				}});
				
			}
			
			function addThjl(){
				showDialog('̸����¼¼��',700,500,'zdxljkTbxs.do?method=addThjl');;
			}
			
			function importJdqk(){
				toImportDataNew("xljk_zjdx_tbxs");
				return false;
			}
			
			//����
			function exportConfig(){
				var DCCLBH='xljk_zjdx_tbxs.do';
				customExport(DCCLBH, exportData);
			}

			function exportData(){
				var DCCLBH='xljk_zjdx_tbxs.do';
				setSearchTj();//���ø߼���ѯ����
				var zxzt=jQuery("#zxzt").val();
				var url = "zdxljkTbxs.do?method=export&dcclbh=" + DCCLBH+"&zxzt="+zxzt;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			
			function selectTab(obj,xszt){
				jQuery(".ha").removeClass("ha");
				jQuery(obj).parent().addClass("ha");
				
				var zxzt = xszt == "zxs" ? "��У" : "����У";
				jQuery("#zxzt").val(zxzt);
				var map = getSuperSearch();
				map["zxzt"] = zxzt;
				jQuery("#dataTable").reloadGrid(map);
				
				if (xszt == "bys"){
					jQuery(".buttonbox ul li:not(:last)").hide();
				} else {
					jQuery(".buttonbox ul li").show();
				}
			}
			
			
			function szgz(){
				var ids = jQuery("#dataTable").getSeletIds();
                var flag = true;
				if (ids.length == 0){
					showAlertDivLayer("��ѡ������Ҫ������ѧ����");
				} else {
					showDialog("���ù�ע",480,200,"zdxljkTbxs.do?method=szgz&ids="+ids.toString());
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
		<html:form action="/jddj_jdqk" method="post">
			<input type="hidden" id="zxzt" value="��У"/>
		
		<%@ include file="/comm/hiddenValue.jsp"%>
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="addThjl()" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="thjlDel();" class="btn_sc">ɾ��</a></li>	
					<li><a href="javascript:void(0);" onclick="szgz();" class="btn_csh">���ù�ע</a></li>							
					<li><a href="javascript:void(0);" onclick="importJdqk();" class="btn_dr">����</a></li>						
					<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>						
				</ul>
			</div>
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
			<!-- �������� end-->
			<div class="comp_title" id="comp_title">
		      <ul style="width:90%">
		        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'zxs');"><span>��У��</span></a></li>
		        <li><a href="javascript:void(0);" onclick="selectTab(this,'bys');"><span>����У��</span></a></li>
		      </ul>
			</div>
		</div>
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>�ر����ѧ���б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
		</div>
		</html:form>
	</body>
</html>
