<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xspjjg/js/xspjjg.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
					caption:"ѧ��������������",
					pager:"pager",
					url:"xspj_xspjjg.do?method=seachForXspjjg&type=nlsy",
					colList:[
					   {label:'guid',name:'guid', index: 'guid',key:true,hidden:true},
					   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true},
					   {label:'����¼��ʱ��',name:'sjlrsj', index: 'sjlrsj',hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'6%'},
					   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'6%'},
					   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'10%'},
					   {label:'��Ŀ���',name:'xmlbmc', index: 'xmlbmc',width:'8%'},
					   {label:'��ѧ��',name:'dxqmc', index: 'dxqmc',width:'5%'},
					   {label:'��ֵ',name:'fz', index: 'fz',width:'4%'},
					   {label:'����ʱ��',name:'cysj', index: 'cysj',width:'4%'}
					],
					sortname: "cysj",
				 	sortorder: "desc"
			}

			var gridSetting2 = {
					caption:"ѧ��������������",
					pager:"pager",
					url:"xspj_xspjjg.do?method=seachForXspjjg&type=szsz",
					colList:[
					   {label:'guid',name:'guid', index: 'guid',key:true,hidden:true},
					   {label:'������Դ',name:'sjly', index: 'sjly',hidden:true},
					   {label:'����¼��ʱ��',name:'sjlrsj', index: 'sjlrsj',hidden:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'8%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'6%'},
					   {label:'ѧԺ',name:'xymc', index: 'xymc',width:'10%'},
					   {label:'ѧ��',name:'xn', index: 'xn',width:'6%'},
					   {label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'10%'},
					   {label:'��Ŀ���',name:'xmlbmc', index: 'xmlbmc',width:'8%'},
					   {label:'��ѧ��',name:'dxqmc', index: 'dxqmc',width:'5%'},
					   {label:'��ֵ',name:'fz', index: 'fz',width:'4%'},
					   {label:'����ʱ��',name:'cysj', index: 'cysj',width:'4%'}
					],
					sortname: "cysj",
				 	sortorder: "desc"
			}
			jQuery(function(){
				var map = getSuperSearch();
				gridSetting1["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting1);
			});
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title}</a>
			</p>
		</div>
		<html:form action="/xspj_xspjsq">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
							<li id="zj" style="display:none;">
								<a href="javascript:void(0);" class="btn_zj" onclick="xspjjgAdd();return false;">����</a>
							</li>
							<li id="xg" style="display:none;">
								<a href="javascript:void(0);" class="btn_xg" onclick="xspjjgUpdate();return false;">�޸�</a>
							</li>
							<li id="sc" style="display:none;">
								<a href="javascript:void(0);" class="btn_sc" onclick="xspjjgDelete();return false;">ɾ��</a>
							</li>
							<li id="dr">
								<a href="javascript:void(0);" class="btn_dr" onclick="xspjjgImport();return false">����</a>
							</li>
						</logic:equal>
							<!-- ������ť�ı任BEGIN -->
							<li id="dc_Nlsy">
								<a href="javascript:void(0);" class="btn_dc" onclick="xspjjgExportNlsy();return false">����</a>
							</li>
							<li id="dc_Szsz" style="display:none;">
								<a href="javascript:void(0);" class="btn_dc" onclick="xspjjgExportSzsz();return false">����</a>
							</li>
							<!-- ������ť�ı任END -->
						<logic:notEqual value="stu" name="userType">
							<li>
								<a href="javascript:void(0);" class="btn_dc" onclick="DataStatistics();return false;">��ʵ������ͳ��</a>
							</li>
						</logic:notEqual>
					</ul>
				</div>
				
				<div class="comp_title" id="comp_title">
					<ul style="width:90%" id="tabUl">
						<li class="ha" tab="nlsy"><a href="javascript:void(0);" onclick="changeTab(this,'1');"><span>��������</span></a></li>
						<li tab="szsz"><a href="javascript:void(0);" onclick="changeTab(this,'2');"><span>˼����������</span></a></li>
					</ul>
				</div>	
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���۽���б�&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>