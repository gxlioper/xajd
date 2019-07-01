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
		<script language="javascript" src="xsxx/comm/xjydnew/js/xjydjg.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">

			//��ʼ����ѯ
			var gridSetting = {
					caption:"ѧ���춯����б�",
					pager:"pager",
					url:"xjydjg.do?method=xjydjgList&type=query",
					colList:[
							   {label:'ѧ��',name:'xh', index: 'xh',formatter:xhLink},
							   {label:'����',name:'xm', index: 'xm'},
							   {label:'ԭ�꼶',name:'ydqnj', index: 'ydqnj'},
							   {label:'ԭ<bean:message key="lable.xb" />',name:'ydqxymc', index: 'ydqxymc',formatter:xyfmt},
                        	   {label:'ԭרҵ',name:'ydqzymc', index: 'ydqzymc'},
							   {label:'ԭ�����༶',name:'ydqbjmc', index: 'ydqbjmc'},
							   {label:'ԭרҵ�༶',name:'ydqzybjmc', index: 'ydqzybjmc'},
                        	   {label:'ԭ��Ժ',name:'ydqsymc', index: 'ydqsymc'},
							   {label:'�춯���',name:'ydlbmc', index: 'ydlbmc'},
							   {label:'ѧ���춯�ĺ�',name:'xjydwh', index: 'xjydwh'},
							   {label:'�춯ʱ��',name:'xjydsj', index: 'xjydsj'},
							   {label:'sjly',name:'sjly', index: 'sjly',hidden:true},
							   {label:'xjydjgid',name:'xjydjgid', index: 'xjydjgid',hidden:true,key:true}
					],
					sortname: "jrsj",
				 	sortorder: "desc"
			}

			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();

				var ids = jQuery("#dataTable").getSeletIds();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
					return;
				 }
				var url="xjydjg.do?method=doPrint&value="+ids ;
				window.open(url);
			}
			
		
			jQuery(function(){
				gridSetting.params = getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting);
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
							<li>
								<a href="javascript:void(0);" onclick="updYdjg();return false;" class="btn_xg">�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="delYdjg();return false;" class="btn_sc">ɾ��</a>
							</li>
							<logic:notEqual value="12309" name="xxdm">
							<li>
								<a href="javascript:void(0);" onclick="plXjyd();return false;" class="btn_xg">����ѧ���춯</a>
							</li>
							</logic:notEqual>
						</logic:equal>
						<!-- 
						<li>
							<a href="javascript:void(0);" onclick="ckYdjg();return false;" class="btn_ck">�鿴</a>
						</li>
						 -->
						<logic:equal name="writeAble" value="yes">	
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</logic:equal>
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>
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
				<span>ѧ���춯����б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
