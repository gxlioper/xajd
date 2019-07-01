<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>	
		<script type="text/javascript" src="xsgzgl/xsztz/xntzjg/jg/js/jcftzjg.js"></script>	
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script language="javascript" >
		jQuery(function() {
			var gridSetting = {
					caption : "�������б�",
					//radioselect:true,
					pager : "pager",
					url : "jcftz_jg.do?method=getJcftzJgList&type=query",
					colList : [ 
					   {label : 'jgid',name : 'jgid',index : 'jgid',key:true ,hidden:true},					   
					   {label : 'ѧ��',name : 'xh',index : 'xh',width : '6%',formatter:xhLink}, 
					   {label : '����',name : 'xm',index : 'xm',width : '5%'},
					   {label : '�༶',name : 'bjmc',index : 'bjmc',width : '7%'},
					   {label : '��Ŀ����',name : 'xmmc',index : 'xmmc',width : '6%',formatter:xmmcLink},
					   {label : '��Ŀ</br>����',name : 'csmsmc',index : 'csmsmc',width : '4%'},
					   {label : '��Ŀ</br>������',name : 'jcxf',index : 'jcxf',width : '4%'},
					   {label : '������</br>������',name : 'tzhjcf',index : 'tzhjcf',width : '4%'},
					   {label : '��ý���',name : 'jxmc',index : 'jxmc',width : '6%'},
					   {label : '�Ƿ�</br>ȱ��',name : 'sfqqmc',index : 'sfqqmc',width : '4%'},
					   {label : '���</br>��ѧ��',name : 'zf',index : 'zf',width : '4%'},
					   {label : 'lylcywid',name : 'lylcywid',index : 'lylcywid',hidden:true},
					   {label : 'xmdm',name : 'xmdm',index : 'xmdm',hidden:true},
					   {label : 'xmckjgid',name : 'xmckjgid',index : 'xmckjgid',hidden:true},
					   {label : 'xsckjgid',name : 'xsckjgid',index : 'xsckjgid',hidden:true}
					   
					   <logic:equal name="xxdm" value="13627">
					   ,{label : '��ע1',name : 'bz1',index : 'bz1',width : '10%'}
					   ,{label : '��ע2',name : 'bz2',index : 'bz2',width : '10%'}
					   ,{label : '��ע3',name : 'bz3',index : 'bz3',width : '10%'}
					   ,{label : '��ע4',name : 'bz4',index : 'bz4',width : '10%'}
					   ,{label : '��ע5',name : 'bz5',index : 'bz5',width : '10%'}
					   </logic:equal>
					   ],
					sortname : "xmdm",
					sortorder : "desc",
					shrinkToFit:false,    
					autoScroll: true,
					<logic:equal name="xxdm" value="13627">
					width:1400
					</logic:equal>
				}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);				
		});		
		</script>
		
	</head>
	<body>
	<html:form action="/jcftz_jg" method="post">
	<%@ include file="/comm/hiddenValue.jsp"%>
<%--	<input type="hidden" id="sqkg" name="sqkg" value="${sqkg}"/>--%>
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
					<li><a href="javascript:void(0);" onclick="add();" class="btn_xg" >ѧ���϶�</a></li>	
					<li><a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a></li>
					<li><a href="javascript:void(0);" onclick="del();return false" class="btn_sc" >ɾ��</a></li>					
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
					<span>У����չ����б� </span>
				</h3>	
				<div class="con_overlfow">
				<table id="dataTable"></table>
				</div>
				<div id="pager"></div>
			</div>
	</html:form>		
	</body>
</html>
