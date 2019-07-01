<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript">
		jQuery(function(){
			var gridSetting = {
				caption : "",
				pager : "pager",
				url : "tjcx_zjdx.do?method=getStucjffgrtj&type=query",
				colList : [ {label: 'ѧ��',name :'xh',index:'xh',width:'15%'},
							{label: '����',name :'xm',index:'xm',width:'5%'},
							{label: '���˵�λ',name:'bmmc',index:'bmmc',width:'15%'},
							{label: '�������',name:'bcje',index:'bcje',width:'5%'},
							{label: '��ע',name:'bz',index:'bz',width:'15%'}],
				multiselect:false,
				usedefined : true,
				uselastrow:true,
				uselastid:"dataTable1",
				usercols:16,
				useurl:"tjcx_zjdx.do?method=getStucjffgrtjSum"
			}
			var map = getSuperSearch();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		})
		
		//�߼���ѯ����
		function searchRs(){
			//Ĭ����Ȳ�ѯ
			if(jQuery("[name='tj_nd']").parent().find(".selectedValue").length != 1){
				showAlertDivLayer("��ȸ߼���ѯ��������ֻ��ѡ��һ����");
				return false;
			}
			//Ĭ���·ݲ�ѯ
			if(jQuery("[name='tj_yf']").parent().find(".selectedValue").length != 1){
				showAlertDivLayer("�·ݸ߼���ѯ��������ֻ��ѡ��һ����");
				return false;
			}
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}
		
		//����
		var DCCLBH='cjff_tjcx_zjdx_stucjffgrtj.do';
		function exportConfig(){ 
			customExport(DCCLBH, exportData);
		}
		function exportData(){
			setSearchTj();//���ø߼���ѯ����
			var url = "tjcx_zjdx.do?method=exportDataStucjffgrtj&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
		<html:form action="/gygl_xyzsjggl">
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
			
				<logic:equal value="true" name="sfqggly">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
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
				<span>ѧ�����귢����ϸ����&nbsp;&nbsp;<font color="blue">��λ(Ԫ)</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" >
					<thead>
							<tr class="nowrap">
								<th width="6%" style="text-align:center" sortname="xh">ѧ��</th>
								<th width="10%" style="text-align:center" sortname="xm">����</th>
								<th width="15%" style="text-align:center" sortname="bmmc">���˵�λ</th>
								<th width="6%" style="text-align:center" >���Ž��</th>
								<th width="15%" style="text-align:center">��ע</th>
							</tr>
					</thead>
				</table>
			<div style="display:none">
				<table  class="dateline" >
					<tbody id="dataTable1">
						<tr >
							<td width="35%" style="text-align:center" colspan="3">
								<font color="red" style="font-weight:bold">�ܼ�</font>
							</td>
							<td width="5%" style="text-align:center" name="zje" colspan="2">
								${zje}
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div id="pager"></div>
		</div>
	</body>
</html>