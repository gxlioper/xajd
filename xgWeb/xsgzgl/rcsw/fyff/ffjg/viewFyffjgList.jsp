<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/fyff/ffjg/js/fyffjg.js"></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
		var gridSetting = {
				caption:"���÷��Ž��",
				pager:"pager",
				url:"rcsw_fyff_ffjg.do?method=viewFyffjgList&type=query",
				colList:[
					{label:'key',name:'guid', index: 'guid',key:true ,hidden:true},
					{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
					{label:'����',name:'xm', index: 'xm',width:'10%'},
					{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
					{label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'15%'},
					{label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
					{label:'������Ŀ',name:'ffxmmc', index: 'ffxmdm',width:'16%'},
					{label:'���Ž��',name:'sfje', index: 'sfje',width:'10%'},
					{label:'����ʱ��',name:'ffsj', index: 'ffsj',width:'18%'},
					{label:'����;��',name:'fftjmc', index: 'fftjdm',width:'11%'}
				],
				sortname: "ffsj",
			 	sortorder: "desc"
			};
			jQuery(function(){
				gridSetting["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//��ѯ
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
		<html:form action="/rcsw_fyff_ffjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">	
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
						<li><a href="#" class="btn_dr" onclick="dr();return false;">����</a></li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</ul>
				</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���÷��Ž��</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
