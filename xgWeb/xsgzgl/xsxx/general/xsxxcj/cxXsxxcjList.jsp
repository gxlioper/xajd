<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xsxx/general/xsxxcj/js/xsxxcj.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"ѧ���ɼ���Ϣ�б�",
				pager:"pager",
				url:"xsxx_gygl_xsxxcj.do?method=cxXsxxcjList&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%' ,key:true ,formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'15%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'20%'}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}

			function xhLink(cellValue,rowObject){
				return "<a href='javascript:void(0);' class='name' onclick='xsxxcjView(\""+rowObject["xh"]+"\");'>"+cellValue+"</a>";
			}

			function zxsxxView(xh){

				showDialog("ѧ����Ϣ��ѯ",850,500,"xsxx_tygl.do?method=ckZxsxx&xh="+xh+"&xs");
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
						<li><a href="javascript:void(0);" onclick="addXsxxcj()" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="viewXsxxcj();" class="btn_ck">�鿴</a></li>
						<li><a href="javascript:void(0);" onclick="delXsxxcj();" class="btn_sc">ɾ��</a></li>		
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						<li><a href="#" class="btn_dc" onclick="exportXsjbxxtz();return false;">����������Ϣͳ��</a></li>	
						<li><a href="#" class="btn_dc" onclick="exportXsknxxtz();return false;">�����������ͳ��</a></li>				
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
				<span> ѧ���ɼ���Ϣ�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
