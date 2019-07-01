<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				pager:"pager",
				url:"zdxljkEcmm.do?method=getEcmmList",
				colList:[
				   {label:'ְ����',name:'zgh', index: 'zgh',key:true},
				   {label:'����',name:'xm', index: 'xm'},
				   {label:'�Ա�',name:'xb', index: 'xb'},
				   {label:'����',name:'bmmc', index: 'bmdm'},
				   {label:'����ʱ��',name:'cjsj', index: 'cjsj'}
				],
				sortname: "cjsj",
			 	sortorder: "desc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function cksq(xh){
				showDialog('�޸�',700,500,'zdxljkTbxs.do?method=viewThjl&xh='+xh);
			}

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function update(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
				} else {
					showDialog('���������޸�',650,350,'zdxljkEcmm.do?method=editEcmm&zgh='+rows[0]["zgh"]);
				}
			}

			function thjlDel(){
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					showConfirmDivLayer("��ȷ��Ҫɾ���ü�¼��",{"okFun":function(){
						jQuery.post("zdxljkEcmm.do?method=del",{ids:ids.toString()},function(data){
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								searchRs();
							}});
						},'json');
					}});
				}
			}
			
			function addThjl(){
				showDialog('��������ά��',650,350,'zdxljkEcmm.do?method=addEcmm');
			}
			
			function initEcmm(){
				
				var ids = jQuery("#dataTable").getSeletIds();

				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
				} else {
					showDialog('���������ʼ��',450,200,'zdxljkEcmm.do?method=initEcmm');
				}
			}
			function saveEcmm(ecmm){
				var ids = jQuery("#dataTable").getSeletIds();
				jQuery.post("zdxljkEcmm.do?method=saveInitEcmm",{ids:ids.toString(),ecmm:ecmm},function(data){
					showAlertDivLayer(data["message"]);
				},"json");
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
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="addThjl()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="thjlDel();" class="btn_sc">ɾ��</a></li>						
						<li><a href="javascript:void(0);" onclick="initEcmm();" class="btn_sz">���������ʼ��</a></li>						
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
					<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		
			<div class="formbox">
				<!--����start-->
				<h3 class="datetitle_01">
					<span>��������ά����ʦ�б� </span>
				</h3>
	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
		</html:form>
	</body>
</html>
