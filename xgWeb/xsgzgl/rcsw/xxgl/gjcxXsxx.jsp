<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/xxgl/xsxxgl.js"></script>
		<script type="text/javascript">
		//��ʼ����ѯ
		var gridSetting = {
				caption:"ѧ����Ѫ��Ϣ�б�",
				pager:"pager",
				url:"rcsw_xsxxgl.do?method=gjcxXxgl&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'15%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'15%'},
				   {label:'�༶',name:'bjmc', index: 'bjmc',width:'15%'},
				   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
				   {label:'��Ѫʱ��',name:'xxsj', index: 'xxsj',width:'15%'},
				   {label:'����',name:'xxgldm', index: 'xxgldm',key:true,hidden:true}
				],
				sortname: "xh",
			 	sortorder: "asc"
			}
				
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//Ϊbuttonע���¼�
				jQuery("#btn_zj").click(add);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_sc").click(deletes);
				//jQuery("#search_go").click(query);
				jQuery("#btn_cz").click(function(){searchReset()});
			});
			
		
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">	
					<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">����</a></li>
					<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">�޸�</a></li>
					<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">ɾ��</a></li>	
					<li><a href="#" onclick="impAndChkData();return false;" class="btn_dr">����</a></li>	
					</logic:equal>
					<li><a href="#" class="btn_dc" onclick="xsxxxxwhExportConfig();return false;">����</a></li>				
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
				<span>ѧ����Ѫ�����б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
