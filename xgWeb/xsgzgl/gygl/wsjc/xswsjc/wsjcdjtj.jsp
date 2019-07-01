<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/check.js"></script>
		<script	type="text/javascript">
		/* jQuery(function(){
			_w_table_rowspan("#gxssfb",1);
			_w_table_rowspan_merge("#gxssfb",16,2);
			_w_table_rowspan_merge("#gxssfb",1,6);
			_w_table_rowspan_merge("#gxssfb",16,7);
			_w_table_rowspan_merge("#gxssfb",1,8);
			_w_table_rowspan_merge("#gxssfb",16,9);
			_w_table_rowspan_merge_sum("#gxssfb",1,10);
			_w_table_rowspan_merge_sum("#gxssfb",16,11);
			_w_table_rowspan_merge_sum("#gxssfb",1,12);
			_w_table_rowspan_merge_sum("#gxssfb",16,13);
			_w_table_rowspan_merge_sum("#gxssfb",1,14);
			_w_table_rowspan_merge_sum("#gxssfb",16,15);
		}); */
		
		jQuery(function(){
			var gridSetting = {
				caption : "�����ȼ�ͳ��",
				url : "gyglnew_xswsjc.do?method=getWsjcdjtb",
				colList : [
					{label:'',name:'xymc0',index :'xymc0'},
					{label:'',name:'fdyxm0',index:'fdyxm0'},
					{label:'',name:'pyccmc',index:'pyccmc'},
					{label:'',name:'xqmc',index:'xqmc'},
					{label:'',name:'ydxsrs',index:'ydxsrs'},
					{label:'',name:'sjcqrs',index:'sjcqrs'},
					{label:'',name:'cql',index:'cql'},
					{label:'',name:'wsdja',index:'wsdja'},
					{label:'',name:'wsdjb',index:'wsdjb'},
					{label:'',name:'wsdjc',index:'wsdjc'},
					{label:'',name:'wsdjd',index:'wsdjd'},
					{label:'',name:'wsyxl',index:'wsyxl'},
					{label:'',name:'wsbhgl',index:'wsbhgl'},
					{label:'',name:'ybgs',index:'ybgs'},
					{label:'',name:'wg',index:'wg'},
					
				],
				multiselect:false,
				usedefined:true,
				usercols:15
			}
			jQuery("#dataTable").initGrid(gridSetting);
		})
		
		function query(){
			var jcrqSta= jQuery("#jcrqSta").val();
			var jcrqEnd= jQuery("#jcrqEnd").val();
			if(""==jcrqSta||""==jcrqEnd){
				showAlertDivLayer("����д������ڣ�");
				return false;
			}
			var map = {};
			map['jcrqSta']=jcrqSta;
			map['jcrqEnd']=jcrqEnd;
			jQuery("#dataTable").reloadGrid(map);
		}
		
		function exportFile(url){
			var jcrqSta= jQuery("#jcrqSta").val();
			var jcrqEnd= jQuery("#jcrqEnd").val();
			if(""==jcrqSta||""==jcrqEnd){
				showAlertDivLayer("����д������ڣ�");
				return false;
			}
			document.forms[0].action=url+"&jcrqSta="+jcrqSta+"&jcrqEnd="+jcrqEnd;
			document.forms[0].submit();
			
		}
		
		</script>
	</head>
	<body>
	<html:form action="/gyglnew_xswsjc" method="post">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" onclick="exportFile('gyglnew_xswsjc.do?method=getExportFile');return false;" class="btn_dc" >����</a>
						</li>
					</ul>
				</div>
			</div>
		<div class="toolbox">
			<!-- �������� -->
			<div class="searchtab">
				<table width="100%" border="0">
					<tbody id="tbody_search_query">
						<tr>
							<th>
								�������
							</th>
							<td>
								<input type="text"  id="jcrqSta" style="width:100px" readonly	onfocus="return showCalendar('jcrqSta','yyyyMMdd',true,'jcrqEnd');"/>
								��
								<input type="text"  id="jcrqEnd" style="width:100px" readonly	onfocus="return showCalendar('jcrqEnd','yyyyMMdd',false,'jcrqSta');"/>
							</td>	
							<td >
								<div class="btn">
									<button type="button"  class="btn_cx" id="search_go" onclick="query();return false;">
										�� ѯ
									</button>
							<!-- 		&nbsp;&nbsp;&nbsp;&nbsp;
									<button type="button"  class="btn_cz" id="btn_cz" onclick="searchReset();return false;">
										�� ��
									</button> -->
								</div>
							</td>						
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<div style='width:100%;'>
			<table width="100%" border="0" class="formlist">
				<thead>
					<tr>
						<th colspan="4">
							<span>�������ȼ�ͳ��ͼ</span>
						</th>
					</tr>
				</thead>
			</table>
		<!-- 	<table id="wsjcdjtb" width="100%" align="center" border="2">
			</table> -->
			<table id="dataTable" >
				<thead>
					<tr class="nowrap">
						<th width="12%" style="text-align:center" rowspan = "2" >ѧԺ</th>
						<th width="8%" style="text-align:center" rowspan = "2" >����Ա</th>
						<th width="8%" style="text-align:center" rowspan = "2" >ѧ��<br />����</th>
						<th width="8%" style="text-align:center" rowspan = "2" >У��</th>
					    <th width="16%" style="text-align:center" colspan = "3">�Ͽγ�������</th>
						<th width="28%" style="text-align:center" colspan = "6">��������</th>
						<th width="8%" style="text-align:center" colspan = "2">ҹ����<br />�ޡ����</th>
					</tr>
					<tr>
						<th width="4%" >Ӧ��<br />ѧ��<br />����</th>
						<th width="4%" >ʵ��<br />����<br />����</th>
						<th width="8%" >������<br />(%)</th>
						<th width="4%" >����<br />(A)</th>
						<th width="4%" >����<br />(B)</th>
						<th width="4%" >�ϸ�<br />(C)</th>
						<th width="4%" >����<br />��(D)</th>
						<th width="8%" >������<br />(%)</th>
						<th width="8%" >���ϸ�<br />��(%)</th>
						<th width="4%" >ҹ��<br />����(�˴�)</th>
						<th width="4%" >���<br />(�˴�)</th>
					</tr>
				</thead>
				</table>
		</div>
		<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>

