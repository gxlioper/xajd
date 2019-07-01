<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/gygl/ssyd/js/ydjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				//��ʼ����ѯ
				var gridSetting = {
						caption:"�����춯����б�",
						pager:"pager",
						url:"ydjg.do?method=list&type=query",
						colList:[
								   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
								   {label:'����',name:'xm', index: 'xm'},
								   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
								   {label:'�Ա�',name:'xb', index: 'xb'},
								   {label:'�춯����',name:'ssydlxmc', index: 'ssydlxmc'},
								   {label:'��������',name:'tsqs', index: 'tsqs',formatter:tscwLink},
								   {label:'��ס����',name:'rzqs', index: 'rzqs',formatter:rzcwLink},
								   {label:'�춯ʱ��',name:'tstzsj', index: 'tstzsj',formatter:rzsjLink},
								   {label:'��¼ʱ��',name:'czsj', index: 'czsj',formatter:rzsjLink,hidden:true},
								   {label:'��ע',name:'bz', index: 'bz',title:'bz',formatter:bzSubstring},
								   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},	
								   {label:'ydqlddm',name:'ydqlddm', index: 'ydqlddm',hidden:true},	
								   {label:'ydqqsh',name:'ydqqsh', index: 'ydqqsh',hidden:true},	
								   {label:'ydqcwh',name:'ydqcwh', index: 'ydqcwh',hidden:true},	
								   {label:'ydhlddm',name:'ydhlddm', index: 'ydhlddm',hidden:true},	
								   {label:'ydhqsh',name:'ydhqsh', index: 'ydhqsh',hidden:true},	
								   {label:'ydhcwh',name:'ydhcwh', index: 'ydhcwh',hidden:true},	
								   {label:'ssydid',name:'ssydid', index: 'ssydid',key:true,hidden:true}
						],
						sortname: "czsj",
					 	sortorder: "desc"
				}
				
				var gridSetting2 = {
						caption:"�����춯����б�",
						pager:"pager",
						url:"ydjg.do?method=list&type=query",
						colList:[
								   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
								   {label:'����',name:'xm', index: 'xm'},
								   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
								   {label:'�Ա�',name:'xb', index: 'xb'},
								   {label:'�춯����',name:'ssydlxmc', index: 'ssydlxmc'},
								   {label:'��������',name:'tsqs', index: 'tsqs',formatter:tscwLink},
								   {label:'��ס����',name:'rzqs', index: 'rzqs',formatter:rzcwLink},
								   {label:'�춯ʱ��',name:'tstzsj', index: 'tstzsj',formatter:rzsjLink},
								   {label:'��¼ʱ��',name:'czsj', index: 'czsj',formatter:rzsjLink,hidden:true},
								   {label:'��������',name:'bz', index: 'bz',title:'bz',formatter:bzSubstring},
								   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},	
								   {label:'ydqlddm',name:'ydqlddm', index: 'ydqlddm',hidden:true},	
								   {label:'ydqqsh',name:'ydqqsh', index: 'ydqqsh',hidden:true},	
								   {label:'ydqcwh',name:'ydqcwh', index: 'ydqcwh',hidden:true},	
								   {label:'ydhlddm',name:'ydhlddm', index: 'ydhlddm',hidden:true},	
								   {label:'ydhqsh',name:'ydhqsh', index: 'ydhqsh',hidden:true},	
								   {label:'ydhcwh',name:'ydhcwh', index: 'ydhcwh',hidden:true},	
								   {label:'ssydid',name:'ssydid', index: 'ssydid',key:true,hidden:true}
						],
						sortname: "czsj",
					 	sortorder: "desc"
				}
				if(jQuery("#xxdm").val() == '12303'){				
					jQuery("#dataTable").initGrid(gridSetting2);
				}else{
					jQuery("#dataTable").initGrid(gridSetting);
				}
				//jQuery("#btn_sh").click(go_sh);
				//jQuery("#btn_cs").click(lcgz);
			});
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
							<li>
								<a href="javascript:void(0);" onclick="addYdjg();return false;" class="btn_zj">����</a>
							</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="ckYdjg();return false;" class="btn_ck">�鿴</a>
						</li>
						<logic:equal value="yes" name="writeAble">
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</logic:equal>
						<logic:notEqual name="xxdm" value="12861">
							<li>
								<a href="javascript:void(0);" onclick="printTstzd();return false;" class="btn_down">����֪ͨ��</a>
							</li>
						</logic:notEqual>
						<logic:equal name="xxdm" value="12861">
							<li>
								<a href="javascript:void(0);" onclick="printTstzd();return false;" class="btn_down">���޵�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="printTsd();return false;" class="btn_down">���޵�</a>
							</li>
						</logic:equal>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>�����춯����б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
