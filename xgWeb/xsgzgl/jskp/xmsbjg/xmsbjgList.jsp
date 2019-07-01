<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/jskp/xmsbjg/js/xmsbjg.js"></script>
		<script type="text/javascript">
			var gridSetting1 = {
				caption:"��Ŀ�걨����б�",
				pager:"pager",
				url:"jskpXmjg.do?method=getXmsbjgList&type=query",
				colList:[
					   {label:'����id',name:'jgid', index: 'jgid',key:true,hidden:true},
				       {label:'��Ŀid',name:'xmid', index: 'xmid',hidden:true},
				       {label:'sbly',name:'sbly', index: 'sbly',hidden:true},
				       {label:'xn',name:'xn', index: 'xn',hidden:true},
				       { label : '�꼶', name : 'nj', index : 'nj', width : '4%' },
					   { label : '<bean:message key="lable.xb" />', name : 'xymc', index : 'xymc', width : '15%' },
					   {label:'ѧ��',name:'xh', index: 'xh',width:'10%',formatter:xhLink},
					   {label:'����',name:'xm', index: 'xm',width:'12%'},
					   {label:'�걨��Ŀ',name:'xmmc', index: 'xmmc',width:'12%'},
					   {label:'��Ŀ����',name:'xmdlmc', index: 'xmdlmc',width:'8%'},
					   {label:'��ʱ��',name:'hjsj', index: 'hjsj',width:'8%'},
					   {label:'�÷�',name:'fs', index: 'fs',width:'4%'},
					   {label:'�걨ʱ��',name:'sbsj', index: 'sbsj',width:'12%'}
				],
				sortname: "sbsj",
			 	sortorder: "desc"
			}

			var gridSetting2 = {
					caption:"˼�����ʽ���б�",
					pager:"pager",
					url:"jskpXmjg.do?method=getSzszList",
					colList:[
						   {label:'jgid',name:'jgid', index: 'jgid',key:true,hidden:true},
					       {label:'�꼶',name:'nj',index:'nj',width:'4%' },
						   {label:'ѧԺ',name:'xymc',index:'xymc',width:'10%' },
						   {label:'ѧ��',name:'xh',index:'xh',width:'8%'},
						   {label:'����',name:'xm',index:'xm',width:'8%'},
						   {label:'�걨��Ŀ',name:'xmmc',index:'xmmc',width:'10%'},
						   {label:'��Ŀ���',name:'xmlbmc',index:'xmlbmc',width:'12%'},
						   {label:'��ʱ��',name:'hjsj',index:'hjsj',width:'6%'},
						   {label:'�÷�',name:'fs', index: 'fs',width:'2%'},
						   {label:'��ѧ��',name:'dxqmc',index:'dxqmc',width:'8%'}
					],
					sortname: "jgid",
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
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<input type="hidden" name="sfsh" id="sfsh" value="${sfsh}" />
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/jskpXmjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="1" name="sfsh">
							<li>
								<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
							</li>
						</logic:equal>
						
						<logic:equal value="0" name="sfsh">
							<li id="li_exportOne">
								<a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a>
							</li>
							<li id="li_exportTwo" style="display:none;">
								<a href="#" class="btn_dc" onclick="szszExport();return false;">����</a>
							</li>
							<logic:notEqual value="stu" name="userType">
								<li>
									<a href="#" class="btn_dc" onclick="DataStatistics();return false;">��ʵ������ͳ��</a>
								</li>
								<li id="li_dr" style="display:none;">
									<a href="#" class="btn_dr" onclick="szszDataImport();return false;">�������</a>
								</li>
							</logic:notEqual>
						</logic:equal>
						
					</ul>
				</div>
				<logic:equal value="0" name="sfsh">
					<div class="comp_title" id="comp_title">
						<ul style="width:90%" id="tabUl">
							<li class="ha" tab="nlsy"><a href="javascript:void(0);" onclick="changeTab(this,'1');"><span>��������</span></a></li>
								<li tab="szsz"><a href="javascript:void(0);" onclick="changeTab(this,'2');"><span>˼����������</span></a></li>
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
				<span>��Ŀ�걨����б�&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
