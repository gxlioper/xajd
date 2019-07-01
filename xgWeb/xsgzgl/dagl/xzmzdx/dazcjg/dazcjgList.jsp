<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript" src="xsgzgl/dagl/xzmzdx/dazcjg/js/dazcjg.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"����ת������б�",
					pager:"pager",
					url:"dagl_dazcjg.do?method=dazcjgList",
					colList:[
					   {label:'guid',name:'guid', index: 'guid',key:true,hidden:true},
					   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true},
					   {label:'����¼��ʱ��',name:'sjlrsj', index: 'sjlrsj',hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'6%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'6%'},
					   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'8%'},
					   {label:'�༶',name:'bjmc', index: 'bjmc',width:'8%'},
					   {label:'ת����ʽ',name:'zcfsmc', index: 'zcfsmc',width:'4%'},
					   {label:'����ת����Ϣ',name:'dazcxxmc', index: 'dazcxxmc',width:'4%'}
					],
					sortname: "sjlrsj",
				 	sortorder: "desc"
			};
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
			})
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/dagl_dazcjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="dazcjgAdd();return false;">����</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="dazcjgUpdate();return false;">�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="dazcjgDelete();return false;">ɾ��</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_ck" onclick="dazcsqView();return false;">�鿴</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_dr" onclick="dazcjgImport();return false">����</a>
							</li>
						</logic:equal>
							<li>
								<a href="javascript:void(0);" class="btn_dc" onclick="dazcjgExport();return false">����</a>
							</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>����ת������б�&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>