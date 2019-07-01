<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="gygl/sspy/pyjg/js/sspyjg.js"></script>
		<script type="text/javascript">
			var gridSetting = {
					caption:"�������Ž��",
					pager:"pager",
					url:"sspyjg.do?method=seachForSspyjg",
					colList:[
					   {label:'guid',name:'guid', index: 'guid',key:true,hidden:true},
					   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true},
					   {label:'����¼��ʱ��',name:'sjlrsj', index: 'sjlrsj',hidden:true},
					   {label:'���Һ�',name:'qsh', index: 'qsh',width:'8%',formatter:qshLink},
					   {label:'¥��',name:'ldmc', index: 'ldmc',width:'8%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'8%'},
					   {label:'ѧ��',name:'xqmc', index: 'xqmc',width:'8%'},
					   {label:'������Ŀ',name:'pyxmmc', index: 'pyxmmc',width:'10%'}
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
		<html:form action="/sspyjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="sspyjgAdd();return false;">����</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_xg" onclick="sspyjgUpdate();return false;">�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" class="btn_sc" onclick="sspyjgDelete();return false;">ɾ��</a>
							</li>
							<!--  
							<li>
								<a href="javascript:void(0);" class="btn_dr" onclick="sspyjgImport();return false">����</a>
							</li>
							-->
						</logic:equal>
							<li>
								<a href="javascript:void(0);" class="btn_dc" onclick="sspyjgExport();return false">����</a>
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
				<span>�������Ž���б�&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>