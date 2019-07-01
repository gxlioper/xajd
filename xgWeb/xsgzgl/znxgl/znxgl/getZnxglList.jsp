<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/znxgl/znxgl/js/znxgl.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "վ������Ϣ",
				pager : "pager",
				url : "znxgl.do?method=getZnxglList&type=query",
				colList : [ {
					label : 'key',
					name : 'xjbh',
					index : 'xjbh',
					key : true,
					hidden : true
				}, {
					label : '����',
					name : 'xjzt',
					index : 'xjzt',
					width : '40%',
				    formatter : btLink
				}, {
					label : '������',
					name : 'fsrxm',
					index : 'fsrxm',
					width : '10%'
				}, {
					label : '����ʱ��',
					name : 'fssj',
					index : 'fssj',
					width : '30%'
				}, {
					label : '�������',
					name : 'ztlb',
					index : 'ztlb',
					width : '20%'
				}, {
					label : '�������Ѷ����',
					name : 'jsrydbj',
					index : 'jsrydbj',
					hidden : true
				}, {
					label : '�����˱��',
					name : 'jsrbh',
					index : 'jsrbh',
					hidden : true
				}],
				sortname : "fssj",
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
		<html:form action="/znxgl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">
				<div class="buttonbox">
					<ul>
							<li>
								<a href="javascript:void(0);" class="btn_fs" onclick="znxfp();return false;"  >����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="znxhf();return false;" class="btn_sh" >�ظ�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="znxsc();return false" class="btn_sc" >ɾ��</a>
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
				<span>վ������Ϣ&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
