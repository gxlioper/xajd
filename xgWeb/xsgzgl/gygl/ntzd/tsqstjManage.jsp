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
		<script type="text/javascript">
			var gridSetting = {
					caption:"���������б�",
					pager:"pager",
					url:"gygl_ntzd.do?method=tsqstjManage&type=query",
					colList:[
					   {label:'����',name:'ny', index: 'ny',width:'8%',key:true},
					   {label:'¥������',name:'ldmc', index: 'ldmc'},
					   {label:'���Һ�',name:'qsh', index: 'qsh',width:'8%'},
					   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc'},
					   {label:'�༶',name:'bjmc', index: 'bjmc'},
					   {label:'������',name:'bzr', index: 'bzr'},
					   {label:'����',name:'fs', index: 'to_number(fs)',width:'10%'},
					   {label:'¥������',name:'ldpm', index: 'to_number(ldpm)',width:'8%'}					 
					],
					sortname: "ny desc , to_number(ldpm), lddm, qsh, xymc, bjmc",
				 	sortorder: "asc"
			}
			function searchRs(){
				var map = getSuperSearch();
				map["qslx"] = jQuery("#qslx").val();
				jQuery("#dataTable").reloadGrid(map);
			}
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_dc").click(exportConfig);
			});
			function exportConfig() {
				customExport("gygl_ntzd_tsqstj.do", exportData,650,500);
			}
			// ��������
			function exportData() {
				setSearchTj();//���ø߼���ѯ����
				var qslx = jQuery("#qslx").val();
				var url = "gygl_ntzd.do?method=exportTsqstjData&dcclbh=gygl_ntzd_tsqstj.do&qslx="+qslx;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			function query(obj,qslx){
				jQuery("#comp_title li").removeClass();
				jQuery(obj).parent().attr("class","ha");
				jQuery("#qslx").val(qslx);
				var map = getSuperSearch();
				map["qslx"] = jQuery("#qslx").val();
				jQuery("#dataTable").reloadGrid(map);
			}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				<input type="hidden" id="qslx" value="wmqs" name="qslx" />
			</p>
		</div>
		<html:form action="/gygl_ntzd.do?method=nykhxsManage">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<logic:equal value="yes" name="writeAble">
							<li><a href="javascript:void(0);" id="btn_dc" class="btn_dc">����</a></li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>		
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->		
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="query(this,'wmqs');"><span>��������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="query(this,'bhgqs');"><span>���ϸ�����</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>�����б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
