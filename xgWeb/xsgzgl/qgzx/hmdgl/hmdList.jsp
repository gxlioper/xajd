<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/qgzx/hmdgl/js/hmdgl.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
		var gridSetting = {
			caption:"�ڹ���ѧ�������б�",
			pager:"pager",
			url:"qgzxhmdgl.do?method=getHmdList&type=query",
			colList:[
			   {label:'key',name:'id', index: 'id',key:true ,hidden:true},
			   {label:'��λ/�ҳ�',name:'mc', index: 'mc',width:'8%'},
			   {label:'����',name:'dwlx', index: 'dwlx',width:'8%',formatter:function(value,cell){
			       if(value == "01"){
			           return "У�ڵ�λ";
				   } else if(value == "02"){
			           return "У�ⵥλ";
				   } else{
				       return "�ҽ̼ҳ�";
				   }
			   }},
			   {label:'����ʱ��',name:'lhrq', index: 'lhrq',width:'15%'},
               {label:'������',name:'xm', index: 'xm',width:'15%'},
			],
			sortname: "lhrq",
			sortorder: "desc"
		}
		gridSetting["params"]=getSuperSearch();
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
		<html:form action="/qgzxhmdgl">
		<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="addHmd();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="delHmd();return false;" class="btn_sc"
							>ɾ��</a>
						</li>
						</logic:equal>
						<%--<li>
							<a href="javascript:void(0);" onclick="xgHmd();return false;" class="btn_xg"
							>�޸�</a>
						</li>--%>
						<%--<li>
							<a href="javascript:void(0);" onclick="dr();return false;" class="btn_dr"
							>����</a>
						</li>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>--%>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�ڹ���ѧ�������б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
