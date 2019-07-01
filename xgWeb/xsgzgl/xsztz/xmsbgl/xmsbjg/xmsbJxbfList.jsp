<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xsztz/xmsbgl/xmsbjg/js/xmsbjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "��Ŀ�걨�б�",
				pager : "pager",
				url : "xmsbXmsbjg.do?method=getXmsbjgList&type=query",
				colList : [
							{ label : 'key', name : 'jgid', index : 'jgid',key:true,hidden : true },
							{ label : 'key', name : 'xmdm', index : 'xmdm',hidden : true },
							{ label : 'splc', name : 'splc', index : 'splc',hidden : true },
							{ label : 'sfkxg', name : 'sfkxg', index : 'sfkxg',hidden : true },
							{ label : 'sfrm', name : 'sfrm', index : 'sfrm',hidden : true },
							{ label : 'sqkssj', name : 'sqkssj', index : 'sqkssj',hidden : true },
							{ label : 'sqjssj', name : 'sqjssj', index : 'sqjssj',hidden : true },
							{ label : 'sjly', name : 'sjly', index : 'sjly',hidden : true },
							{ label : 'sqkg', name : 'sqkg', index : 'sqkg',hidden : true },
							{ label : 'ѧ��', name : 'xn', index : 'xn', width : '10%' },
							{ label : 'ѧ��', name : 'xqmc', index : 'xqmc', width : '8%' },
							{ label : '��Ŀ����', name : 'xmmc', index : 'xmmc', width : '15%',formatter : xmcxLink  },
							{ label : '��Ŀ����', name : 'xmjbmc', index : 'xmjbmc', width : '10%' },
							{ label : '������Ŀ', name : 'sskmmc', index : 'xmjbmc', width : '10%' },
							{ label : '��Ŀ��ʼʱ��', name : 'xmkssj', index : 'xmkssj', width : '12%' },
							{ label : '����ѧ��', name : 'jcxf', index : 'jcxf', width : '5%' },
							{ label : '�걨��', name : 'sbrxm', index : 'sbrxm', width : '5%' },
							{ label : '�걨����', name : 'sbbmmc', index : 'sbbmmc', width : '15%' },
							{ label : '�Ѱ佱����', name : 'ybjrs', index : 'ybjrs', width : '5%' }]
					 }
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xmsbXmsbjg">
		<input type="hidden" name="currDate" id="currDate" value="${currDate}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_dy" onclick="jxbf();return false;"  >����䷢</a>
						</li>
						<logic:equal value="11032" name='xxdm'>
						<li>
							<a href="javascript:void(0);" onclick="jxdr();return false;" class="btn_dr">�����</a>
						</li>
						</logic:equal>
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
				<span>��Ŀ�걨�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
