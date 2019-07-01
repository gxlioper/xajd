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
				caption : "������Ŀ��ѯ�б�",
				pager : "pager",
				url : "grttxm_jdsz.do?method=getXsTtxmCx&type=query",
				colList : [ {
					label : '�Ŷ�����',
					name : 'tdmc',
					index : 'tdmc',
					width : '10%',
					formatter : ttsqLink
				},{
					label : '�ӳ�ѧ��',
					name : 'xh',
					index : 'xh',
					width : '10%'
					//formatter : xhLink
				}, {
					label : '�ӳ�����',
					name : 'xm',
					index : 'xm',
					width : '6%'
				}, {
					label : '�Ա�',
					name : 'xb',
					index : 'xb',
					width : '5%'
				}, {
					label : '�ӳ�����Ժϵ',
					name : 'xymc',
					index : 'xydm',
					width : '10%'
				},{
					label : '��Ŀ����',
					name : 'xmmc',
					index : 'xmmc',
					width : '10%'
				},{
					label : '��Ŀ�׶�',
					name : 'jdmc',
					index : 'jdmc',
					width : '15%'
				},{
					label : '�׶η�',
					name : 'jbf',
					index : 'jbf',
					width : '5%'
				},{
					label : 'ttjgid',
					name : 'ttjgid',
					index : 'jgid',
					hidden : true
				}],
				sortname : "jdsj",
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
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						</logic:equal>
						<li><a href="#" class="btn_dc" onclick="exportConfigTt();return false;">����</a></li>	
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>������Ŀ��ѯ�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
