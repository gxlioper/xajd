<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl//szdw/fdyjtff/js/fdyjtff.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"����������Ϣ�б�",
				pager:"pager",
				url:"szdw_fdyjtff.do?method=cxFdyjtffList&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',hidden:true,key:true},
				   {label:'ְ����',name:'zgh', index: 'zgh',width:'15%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'10%'},
				   {label:'�Ա�',name:'xbmc', index: 'xbmc',width:'5%'},
				   {label:'����',name:'bmmc', index: 'bmdm',width:'20%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
				   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'5%'},
				   {label:'��������',name:'bzlxmc', index: 'bzlxmc',width:'15%'},
				   {label:'���',name:'bzje', index: 'bzje',width:'20%'}
				],
				sortname: "zgh",
			 	sortorder: "asc"
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' onclick='jtffView(\""+rowObject["id"]+"\");' class='name'>"+cellValue+"</a>";
			}
			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();

				jQuery("#dataTable").reloadGrid(map);
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
						<li><a href="javascript:void(0);" onclick="add()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del()" class="btn_sc">ɾ��</a></li>
						</logic:equal>						
						<li><a href="javascript:void(0);" onclick="view()" class="btn_ck">�鿴</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
				<span> ����������Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
