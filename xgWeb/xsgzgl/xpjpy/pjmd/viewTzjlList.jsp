<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/pjmd/js/pjmd.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��������ȷ��",
				pager:"pager",
				url:"pj_tzjl.do?method=viewTzjlList&type=query",
				colList:[
				   {label:'ѧ��',name:'xh', index: 'xh',width:'11%'},
				   {label:'����',name:'xm', index: 'xm',width:'8%'},
				   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
				   {label:'�����༶',name:'bjmc', index: 'bjdm',width:'24%'},
				   {label:'������',name:'tzrxm', index: 'tzrxm',width:'6%'},
				   {label:'����ʱ��',name:'tzsj', index: 'tzsj',width:'11%'},
				   {label:'������ע',name:'tzbz', index: 'tzbz',width:'35%'}
				],
				multiselect:false,
				sortname: "tzsj",
			 	sortorder: "desc"
			}

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

		</script>
	</head>

	<body style="min-height: 800px;">
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpj_cpmd">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			<!-- ��ť -->
			
			<logic:equal value="10335" name="xxdm">
				<logic:equal value="zf01" name="userName">
					<div class="buttonbox">
						<ul>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
						</ul>
					</div>
				</logic:equal>
			</logic:equal>
			<logic:notEqual value="10335" name="xxdm">
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
					</ul>
				</div>
			</logic:notEqual>
			
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span><font color="blue">${zqmc }&nbsp;&nbsp;</font>������Ա������¼&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
