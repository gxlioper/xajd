<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xstgl/stgl/stzhwh/js/stzhwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
	jQuery(function(){
			var gridSetting = {
				caption : "���Ž���б�",
				pager : "pager",
				url : "stglStzhwh.do?method=getStzhwhList&type=query",
				colList : [
							{ label : 'key', name : 'stid', index : 'stid',key : true, hidden : true },
							{ label : 'bz', name : 'bz', index : 'bz',key : true, hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'sqsj', name : 'sqsj', index : 'sqsj', hidden : true },
							{ label : 'sqkg', name : 'sqkg', index : 'sqkg', hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly', hidden : true },
							{ label : 'sqkssj', name : 'sqkssj', index : 'sqkssj', hidden : true },
							{ label : 'sqjssj', name : 'sqjssj', index : 'sqjssj', hidden : true },
							{ label : 'xmlbdm', name : 'xmlbdm', index : 'xmlbdm', hidden : true },
							{ label : 'ѧ��', name : 'currxn', index : 'currxn', width : '15%' },
							{ label : '������Ŀ����', name : 'stxmmc', index : 'stxmmc', width : '15%',formatter : xmmcLink  },
							{ label : '�������', name : 'stlbmc', index : 'stlbmc', width : '10%' },
							{ label : '��Ŀ���', name : 'xmlbmc', index : 'xmlbmc', width : '12%' },
							{ label : '��Ա����', name : 'cysl', index : 'cysl', width : '12%' },
							/*
							{ label : '���ſ�ʼʱ��', name : 'kssj', index : 'kssj', width : '5%',},
							{ label : '���Ž���ʱ��', name : 'jssj', index : 'jssj', width : '5%' },
							*/
							<logic:equal value="12872" name = "xxdm">
							{ label : '�����Ǽ�', name : 'stxj', index : 'stxj', width : '5%' },
							</logic:equal>
							{ label : 'ָ����ʦ', name : 'zdlsxm', index : 'zdlsxm', width : '12%' }
							],
					sortname : "sqsj",
				    sortorder : "desc" }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="usertype" value="${usertype}">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/stglRtjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="stzhwhCyztwh();return false;" class="btn_xg" >��Ա״̬ά��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="stzhwhCycjpd();return false;" class="btn_xg" >��Ա�ɼ�����</a>
						</li>
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
				<span>���Ž���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
