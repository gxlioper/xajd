<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/general/cxpy/js/cxpy.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ��������Ϣ�б�",
				pager:"pager",
				url:"xsxx_gygl_cxcxpy.do?method=cxCxpyList&type=query",
				colList:[
				   {label:'ID',name:'pk',index:'pk',key:true ,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%' ,formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
				   <logic:notEqual name="xxdm" value="13943">
				   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'10%'},
				   </logic:notEqual>
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
				   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
				   {label:'�ȼ�',name:'cxdjmc', index: 'cxdjmc',width:'10%'}
				]
				
			}
			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='cxpyView(\""+rowObject["pk"]+"\");'>"+cellValue+"</a>";
			}

			
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
			}
			function delCxpy(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					
					var rows = jQuery("#dataTable").getSeletRow();
					for ( var i = 0; i < ids.length; i++) {
						if (rows[i]['sjly'] == '1') {
							showAlertDivLayer("�������ݲ���ɾ����");
							return false;
						}
					}
					
					showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("xsxx_gygl_cxcxpy.do?method=delCxpy",{values:ids.toString()},function(data){
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
		<html:form action="/demo">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li><a href="javascript:void(0);" onclick="addCxpy()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="delCxpy()" class="btn_sc">ɾ��</a></li>	
						</logic:equal>					
						<li><a href="javascript:void(0);" onclick="viewCxpy()" class="btn_ck">�鿴</a></li>
						
						<logic:equal name="writeAble" value="yes">
							<li><a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a><li>
						</logic:equal>	
						
						<li><a href="#" class="btn_dc" onclick="exportData();return false;">����</a></li>
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ��������Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
