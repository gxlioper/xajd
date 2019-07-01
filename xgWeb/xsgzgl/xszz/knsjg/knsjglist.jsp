<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xszz/knsjg/js/knsjglist.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">

		jQuery(function() {
			var gridSetting=initGridSetting();
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);
		});
		
		function drxx(){
			if("xn"==jQuery("#sqzq").val()){
				toImportData("IMPORT_N100103_XN");
			} else {
				toImportData("IMPORT_N100103");
			}
			return false;
		}
		
		function export_12688(){
			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var xq_num =  jQuery("a[name=a_name_xq]").length;
			if(xn_num != "1"){
				alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
				return false;
			}
			if ( xq_num != "1"){
				alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ���� ");
				return false;
			}
			var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
			var xq = jQuery("a[name=a_name_xq]").eq(0).attr("id").replace("a_id_","");
			var url = "xszz_knsjg.do?method=export_12688&xn="+xn+"&xq="+xq;
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}

		var DCCLBHS = "kns_jtjjkn.do";//dcclbh,�������ܱ��
		//��������ҽҩ���Ի�
		function exportConfigXzyy(dcclbh){
			DCCLBHS = dcclbh;
			customExport(dcclbh, jgExportData);
		}

		//��������
		function jgExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xszz_knsjg.do?method=exportConfigXzyy&dcclbh=" + DCCLBHS;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		function exportConfigZjzyy(dcclbh){
			customExport(dcclbh, jgExportDataZjzyy);
		}

		//��������
		function jgExportDataZjzyy() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xszz_knsjg.do?method=exportConfigZjzyy&dcclbh=kns_jtjjkn_jtxx_zjzyy.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//���ػ��ܱ�
		function downloadHzb() {
			var selObj = jQuery("#xy_ul").find(".selectedValue");
			if (selObj.length == 1) {
				var idArray = jQuery(selObj).attr("id").split("_");
				var indexLast = idArray.length-1;
				var xydm = idArray[indexLast];
				var url = "xszz_knsjg.do?method=printHzb";
				url += "&xydm=" + xydm;
				window.open(url);
			} else if (selObj.length == 0) {
				showAlertDivLayer("��ѡ��һ��ѧԺ��");
                return false;
			} else {
				showAlertDivLayer("ֻ��ѡ��һ��ѧԺ��");
				return false;
			}
		}

		//��������������
		function knspddc() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xszz_knsjg.do?method=knspddc&dcclbh=xg_knsrd_knsrddc.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//��������ͥ�������
		function knsjtqkdc() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xszz_knsjg.do?method=knspddc&dcclbh=kns_jtjjkn_jtxx_ahjzdx.do";//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//������������������
		function knsjdlkdc() {
			setSearchTj();//���ø߼���ѯ����
			var url = "xszz_knsjg.do?method=knspddc&dcclbh=kns_knsjg_knsjdlkdc.do";//dcclbh,�������ܱ��
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
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					ѧ������ʦ�ύ���������϶��������������ͨ���������������˲˵�<br/>
					�û�Ҳ���ڴ˴�ֱ��ά������������					
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/xszz_knsjg" >
			<input type="hidden" id="iscanCopy" value="${iscanCopy}"/> 
			<input type="hidden" name="tableName" id="tableName" value="view_xg_xszz_new_knsjgb"/>
			<input type="hidden" id="xbmc" value="<bean:message key='lable.xb' />"/>
			<html:hidden property="sqzq" styleId="sqzq" value="${sqzq}"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
							<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
							<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>
							<logic:notEqual value="10335" name="xxdm">
							<li><a href="javascript:void(0);" onclick="copy();" class="btn_tj">����</a></li>	
							</logic:notEqual>
						</logic:equal>
						<logic:equal name="writeAble" value="yes">	
							<li><a href="#" onclick="drxx();return false;" class="btn_dr">����</a></li>
						</logic:equal>
			                  <li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>

						<logic:equal value="10657" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="knsrdTjExport();return false;">����ѧ��ͳ�Ƶ���</a></li>
						</logic:equal>
						<logic:equal value="10335" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="knsExport();return false;">����ѧ��ͳ�ƻ��ܵ���</a></li>
						<li><a href="#" class="btn_dc" onclick="knstksPercent();return false;" title="����鿴��Ժϵ����������������">����������������</a></li>
						</logic:equal>
						<%-- ������ͨ��ѧ��ѧԺ ���Ի�����--%>
						<logic:equal value="13431 " name="xxdm">
							<li><a href="javascript:void(0);" onclick="printKnssq('xszz_knsjg.do?method=printJsp');return false;" class="btn_down">�����������϶������</a></li>	
						</logic:equal>
						<%--�ǻ�����ͨ��--%>
						<logic:notEqual value="13431" name="xxdm">
						<li><a href="javascript:void(0);" onclick="printKnssq('xszz_knsjg.do?method=printJsp');return false;" class="btn_down">���صǼǱ�</a></li>	
						</logic:notEqual>
						<logic:equal value="12898" name="xxdm">
						<li><a href="javascript:void(0);" onclick="exportKnsjg('dashk');return false;" class="btn_down">�����������ݿ�</a></li>
						<li><a href="javascript:void(0);" onclick="exportKnsjg('rdpxb');return false;" class="btn_down">�����϶������</a></li>
						</logic:equal>
						<logic:equal value="12930" name="xxdm">
						<li><a href="#" class="btn_dc" onclick="rdhzbExport();return false;">���ػ��ܱ�</a></li>
						</logic:equal>
						<logic:equal value="12688" name="xxdm">
						<li><a href="#" onclick="export_12688();return false;" class="btn_dc">����ͳ�Ʊ�</a></li>
						</logic:equal>
						<logic:equal value="11998" name="xxdm">
			                  <li><a href="#" class="btn_dc" onclick="exportConfigXzyy('kns_jtjjkn.do');return false;">��ͥ��������ѧ������</a></li>
			                  <li><a href="#" class="btn_dc" onclick="exportConfigXzyy('kns_jtjjkn_jtxx.do');return false;">��ͥ������Ϣ¼�뵼��</a></li>
						</logic:equal>
						<logic:equal value="10344" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="exportConfigZjzyy('kns_jtjjkn_jtxx_zjzyy.do');return false;">��ͥ������Ϣ¼�뵼��</a></li>
						</logic:equal>
						<logic:equal value="12309" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rdhzbExport();return false;">����ѧ���϶����ܱ�</a></li>
							<li><a href="#" class="btn_dc" onclick="exportConfigZjzyy('kns_jtjjkn_jtxx_zjzyy.do');return false;">��ͥ������Ϣ¼�뵼��</a></li>
						</logic:equal>
						<logic:equal value="10098" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="rdhzbExport();return false;">����ѧ���϶����ܱ�</a></li>
							<li><a href="#" class="btn_dc" onclick="exportConfigZjzyy('kns_jtjjkn_jtxx_zjzyy.do');return false;">��ͥ������Ϣ¼�뵼��</a></li>
						</logic:equal>
						<logic:equal value="11842" name="xxdm">
							<li><a href="#" class="btn_dc" onclick="exportConfigZjzyy('kns_jtjjkn_jtxx_zjzyy.do');return false;">��ͥ������Ϣ¼�뵼��</a></li>
						</logic:equal>
						<%--��ʦ����Ի�����--%>
						<logic:equal value="10346" name="xxdm">
							<li><a href="javascript:void(0);" onclick="downloadHzb();return false;" class="btn_down">������Ϣ���ܱ�</a></li>
						</logic:equal>
						
						<!-- ���ս�����ѧ -->
						<logic:equal value="10878" name="xxdm">
							<li><a href="javascript:void(0);" onclick="knspddc();return false;" class="btn_dc">�������϶���������</a></li>
							<li><a href="javascript:void(0);" onclick="knsjtqkdc();return false;" class="btn_dc">��������ͥ��Ϣ����</a></li>
							<li><a href="javascript:void(0);" onclick="knsjdlkdc();return false;" class="btn_dc">������������������</a></li>
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
				<span> ����������б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
