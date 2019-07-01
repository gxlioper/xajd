<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"��Ŀ�����б�",
				pager:"pager",
				url:"xpjpy_cprytzjl.do?method=getCprytzjlDate",
				colList:[
						   {label:'ѧ��',name:'xh', index: 'xh',width:'11%'},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�Ա�',name:'xb', index: 'xb',width:'5%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'24%'},
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

			//������¼����
			function cprytzjlExport(){
				var DCCLBH="xpjpy_zhcp_cprytzjl.do";
				customExport(DCCLBH, exportData);
			}
			function exportData(){
				var DCCLBH="xpjpy_zhcp_cprytzjl.do";
				setSearchTj();//���ø߼���ѯ����
				var url = "xpjpy_cprytzjl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>
	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/xpjpy_cprytzjl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
			
				<logic:equal value="zf01" name="userName">
					<!-- ��ť -->
					<div class="buttonbox">
						<ul>
							<li>
								<a href="javascript:void(0);" class="btn_dc" onclick="cprytzjlExport();return false;">����</a>
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
				<span><font color="blue">${cssz.xn}</font>&nbsp;&nbsp;������Ա������¼&nbsp;&nbsp;</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>