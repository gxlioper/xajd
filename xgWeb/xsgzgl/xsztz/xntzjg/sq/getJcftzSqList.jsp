<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/sq/js/jcftzsq.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
			jQuery(function() {
				var gridSetting = {
						caption : "�������б�",
						//radioselect:true,
						pager : "pager",
						url : "jcftz_sq.do?method=getJcftzSqList&type=query",
						colList : [ 
						   {label : 'jgid',name : 'jgid',index : 'jgid',key:true ,hidden:true},					   
						   {label : 'ѧ��',name : 'xn',index : 'xn',width : '20%'}, 
						   {label : 'ѧ��',name : 'xqmc',index : 'xq',width : '10%'},
						   {label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '20%',formatter : xmmcLink},
						   {label : '��Ŀ����',name : 'csmsmc',index : 'csmsmc',width : '5%'},
						   {label : '��Ŀ����',name : 'csms',index : 'csms',hidden:true},
						   {label : '��Ŀ����',name : 'xmjbmc',index : 'xmjbmc',width : '30%'},
						   {label : '������Ŀ',name : 'sskmmc',index : 'sskmmc',width : '30%'},
						   {label : '��Ŀ��ʼʱ��',name : 'xmkssj',index : 'xmkssj',width : '30%'},
						   {label : '��/����',name : 'ybjrs',index : 'ybjrs',width : '30%',formatter : rsLink},
						   {label : '���״̬',name : 'shztmc',index : 'xfrdsqzt',width : '30%'},
						   {label : 'xfrdsqzt',name : 'xfrdsqzt',index : 'xfrdsqzt',hidden:true},
						   {label : 'xmdm',name : 'xmdm',index : 'xmdm',hidden:true}
						   ],
						sortname : "xmkssj",
						sortorder : "desc"
					}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			});

			//�߼���ѯ
			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}
						
		</script>
		
	</head>
	<body>
	<html:form action="/jcftz_sq" method="post" onsubmit="return false;">
	<%@ include file="/comm/hiddenValue.jsp"%>
	<input type="hidden" id="sqkg" name="sqkg" value="${sqkg}"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
			<div class="toolbox">
			<!-- ��ť -->
			<logic:equal name="writeAble" value="yes">	
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" onclick="add();" class="btn_xg">ѧ���϶�</a></li>
					<li><a href="javascript:void(0);" onclick="tijiao();" class="btn_up" >�ύ</a></li>				
				</ul>
			</div>
			</logic:equal>
				
			<!-- �������� -->
			<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
		</div>
			<div class="formbox">
			<!--����start-->
				<h3 class="datetitle_01">
					<span>У����չ����ϱ��б� </span>
				</h3>	
				<table id="dataTable"></table>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
