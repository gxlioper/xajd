<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/jtff/jtff/js/jtff.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "����������Ϣ�б�",
				pager : "pager",
				url : "jtff_jtff.do?method=getJtffcx&type=query",
				colList : [ 
							{label:'key',name:'id', index: 'id',key:true ,hidden:true},
							{label:'ְ����',name:'zgh', index: 'zgh',width:'8%',formatter:xhLink},
							{label:'����',name:'xm', index: 'xm',width:'6%'},
							{label:'�Ա�',name:'xb', index: 'xb',width:'6%'},
							{label:'��������',name:'bmmc', index: 'bmmc',width:'10%'},
							{label:'��λ',name:'gw', index: 'gw',width:'10%'},
							{label:'����ѧ����',name:'dbrs', index: 'dbrs',width:'6%'},
							{label:'�����·�',name:'ffny', index: 'ffny',width:'6%'},
							{label:'���Ž��(Ԫ)',name:'ffje', index: 'ffje',width:'6%'}
							]
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jtff_jtff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="scjt();return false;"  >���ɽ���</a>
						</li>
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����������Ϣ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
