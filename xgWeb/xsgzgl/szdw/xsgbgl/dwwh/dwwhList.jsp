<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src="js/xgutil.js"></script>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src='dwr/interface/exportData.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/dwwh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script type="text/javascript">
		var gridSetting = {
				caption:"ѧ���ɲ�����ά���б�",
				pager:"pager",
				url:"szdw_xsgb_dwwh.do?method=dwwhList&type=query",
				colList:[
                    {label:'�����༶',name:'bjdm', index: 'bjdm',key:true,width:'10%',hidden:true},
                    {label:'�꼶',name:'nj', index: 'nj',width:'10%'},
				   {label:'�����༶',name:'bjmc', index: 'bjmc',width:'10%'},
				   {label:'������Ժ',name:'symc', index: 'symc',width:'10%'},
                    {label:'����Ա',name:'fdyxm', index: 'fdyxm',width:'10%'},
                    {label:'��ɲ�',name:'bgb', index: 'bgb',width:'15%'}
				],
				sortname: "nj",
			 	sortorder: "asc"
		};
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_ck").click(go_ck);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_zj").click(add);
				jQuery("#btn_sc").click(deletes);
			});

			/**
			����
			*/
			function importConfig(){
                toImportDataNew("IMPORT_SZDW_ZWWH");
				return false;
			}

        //dcglbh,�������ܱ��
        var DCGLBH = "szdw_xsgb_dwwh_10698.do";

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCGLBH, exprotData);
        }

        //��������
        function exprotData() {
            setSearchTj();//���ø߼���ѯ����
            var url = "szdw_xsgb_dwwh.do?method=exportData&dcglbh=" + DCGLBH;//dcglbh,�������ܱ��
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
		<html:form action="/szdw_fdypxxmwh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li><a href="javascript:void(0);" id="btn_zj" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" id="btn_xg" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" id="btn_sc" class="btn_sc">ɾ��</a></li>	
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_ck" class="btn_ck">�鿴</a></li>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" onclick="importConfig();return false;" class="btn_dr">����</a>
						</li>
							<logic:equal value="11067" name="xxdm">
								<li><a href="javascript:void(0);" onclick="printXsgbbab('szdw_zwsq.do?method=printXsgbbab');return false;" class="btn_down">���ر�����</a></li>
							</logic:equal>
						</logic:equal>
						<%--<li><a href="#" class="btn_dc" onclick="ZwdwwhExportConfig();return false;">����</a></li>--%>
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
				<span>ѧ���ɲ�����ά��</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
