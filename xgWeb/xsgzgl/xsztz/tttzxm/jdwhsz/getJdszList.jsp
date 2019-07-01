<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/tttzxm/jdwhsz/js/jdwhsz.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��Ŀ�׶�ά���б�",
				pager : "pager",
				url : "grttxm_jdsz.do?method=getJdszList&type=query",
				colList : [ {
					label : 'key',
					name : 'jdid',
					index : 'jdid',
					key : true,
					hidden : true
				}, {
					label : 'key',
					name : 'xmdm',
					index : 'xmdm',
					hidden : true
				}, {
					label : '��Ŀ����',
					name : 'xmmc',
					index : 'xmmc',
					width : '10%',
				    formatter:xmmcLink
				}, {
					label : '��Ŀ����',
					name : 'csmsmc',
					index : 'csmsmc',
					width : '10%'
					
				},{
					label : '��Ŀ����',
					name : 'xmjbmc',
					index : 'xmjbmc',
					width : '10%'
				}, {
					label : '������Ŀ',
					name : 'sskmmc',
					index : 'sskmmc',
					width : '10%'
				}, {
					label : '��Ŀ��ʼʱ��',
					name : 'xmkssj',
					index : 'xmkssj',
					width : '10%'
				},{
					label : '��Ŀ�׶�',
					name : 'jdmc',
					index : 'jdmc',
					width : '10%'
				},
				{
					label : '��Ա������/�ţ�',
					name : 'jdcynum',
					index : 'jdcynum',
					width : '5%'
				},
				{
					label : '�϶�״̬',
					name : 'rdzt',
					index : 'rdzt',
					width : '5%'
				}
				],
				sortname : "xn,xq,xmjbdm,sskmdm,sbbmdm,xmdm,to_number(jdsx)",
				sortorder : "desc"
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
		<html:form action="/gygl_xyzsjggl">
			
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="jdwhsz();return false;" class="btn_xg" >��Աά��</a>
						</li>
						<li><a href="#" class="btn_dr" onclick="drjdwhcy();return false;">����</a></li>	
						</logic:equal>
						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		<%@include file="/comm/hiddenValue.jsp"%></html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>��Ŀ�׶�ά���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
