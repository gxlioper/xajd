<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/jtff/jtmdwh/js/jtmdwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"�������ܽ�����Ա��ϸ",
				pager:"pager",
				url:"jtff_jtmdwh.do?method=getJtmdwhcx&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'ְ����',name:'zgh', index: 'zgh',width:'15%',formatter:xhLink},
					{label:'����',name:'xm', index: 'xm',width:'10%'},
					{label:'�Ա�',name:'xb', index: 'xb',width:'8%'},
					{label:'��������',name:'bmmc', index: 'bmmc',width:'25%'},
					{label:'��λ',name:'gw', index: 'gw',width:'15%'},
					{label:'����ѧ����',name:'dbrs', index: 'dbrs',width:'25%'}
				],
				params:{jtlb:"zc"},
				sortname: "zgh",
			 	sortorder: "asc"
		}

		var gridSetting2 = {
				caption:"�̶�������Ա��ϸ",
				pager:"pager",
				url:"jtff_jtmdwh.do?method=getJtmdwhcx&type=query",
				colList:[
					{label:'key',name:'id', index: 'id',key:true ,hidden:true},
					{label:'ְ����',name:'zgh', index: 'zgh',width:'15%',formatter:xhLink1},
					{label:'����',name:'xm', index: 'xm',width:'10%'},
					{label:'�Ա�',name:'xb', index: 'xb',width:'8%'},
					{label:'��������',name:'bmmc', index: 'bmmc',width:'25%'},
					{label:'��λ',name:'gw', index: 'gw',width:'15%'},
					{label:'����ѧ����',name:'dbrs', index: 'dbrs',width:'10%'},
					{label:'�̶�����(Ԫ)',name:'gdffje', index: 'gdffje',width:'15%'}
				],
				params:{jtlb:"gd"},
				sortname: "zgh",
			 	sortorder: "asc"
		}
			
		jQuery(function(){
			var map = getSuperSearch();
			map["jtlb"]="zc";
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
	
			
		
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jtff_jtmdwh">
			<input type="hidden" id="jtlb" value="zc"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li id="li_zc">
							<a href="javascript:void(0);" onclick="addzcjtff();return false;" 	
							   class="btn_zj">����</a>
							<a href="javascript:void(0);" onclick="updatezcjt();return false;" 	
							   class="btn_xg">�޸�</a>
							<a href="javascript:void(0);" onclick="delzcjt();return false;" 	
							   class="btn_sc">ɾ��</a>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
						</li>						
						<li id="li_gd" style="display: none;">
							<a href="javascript:void(0);" onclick="addgdjtff();return false;" 
							   class="btn_zj">����</a>
							<a href="javascript:void(0);" onclick="updategdjt();return false;" 	
							   class="btn_xg">�޸�</a>
							<a href="javascript:void(0);" onclick="delgdjt();return false;" 	
							   class="btn_sc">ɾ��</a>
						</li>	
						</logic:equal>
						<li>
						<a href="javascript:void(0);" onclick="exportConfig();return false;" 	
							   class="btn_dc">����</a>	
					    </li>		
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab(this,'zc');"><span>��������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab(this,'gd');"><span>�̶�����</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span id="bt">�������ܽ�����Ա��ϸ&nbsp;&nbsp; </span>
				<span id="bt1" style="display:none">�̶�������Ա��ϸ&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
