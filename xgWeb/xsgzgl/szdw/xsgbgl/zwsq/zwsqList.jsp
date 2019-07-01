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
		<script type="text/javascript" src="xsgzgl/szdw/xsgbgl/js/zwsq.js"></script>
		<script type="text/javascript">
		var gridSetting = {
				caption:"ѧ���ɲ�ְ������",
				pager:"pager",
				url:"szdw_zwsq.do?method=zwsqList&type=query",
				colList:[
				   {label:'�������',name:'sqid', index: 'sqid',width:'1%',key:true,hidden:true},
				   {label:'ѧ��',name:'xh', index: 'xh',width:'15%',formatter:xhLink},
				   {label:'����',name:'xm', index: 'xm',width:'12%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xymc',width:'15%'},
				   {label:'רҵ',name:'zymc', index: 'zymc',width:'15%'},
				   {label:'����ְ��',name:'zwmc', index: 'zwmc',width:'15%'},
                   {label:'ְ������',name:'lxmc', index: 'lxmc',width:'15%'},
				   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'18%'},
				   {label:'���״̬',name:'shzt', index: 'shzt',width:'15%'},
				   {label : 'shztdm', name : 'shztdm', index : 'shztdm', hidden : true },
				   {label:'��������',name:'splc', index: 'splc',width:'1%',hidden:true}
				],
				sortname: "sqsj",
			 	sortorder: "desc"
			};
				
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//Ϊbuttonע���¼�
				jQuery("#btn_zj").click(add);
				jQuery("#btn_sc").click(qx_sh);
				//jQuery("#search_go").click(query);
				jQuery('#btn_del').click(del);
				jQuery("#btn_xg").click(update);
				jQuery("#btn_cz").click(function(){searchReset()});
				jQuery("#btn_cs").click(lcgz);
			});

			function submitBusi(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
				} else if (ids.length >1 ) {
					showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='δ�ύ' && rows[i]['shzt']!='�˻�' ){
							showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼��");
							return false;
						}
					}
					showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("szdw_zwsq.do?method=submitZwsq",
							{values:ids.toString(),
							 xh : rows[0]['xh'],
							 splcid : rows[0]['splc'],
							 shzt:rows[0]['shztdm']
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}

			function cancel(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0) {
					showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
				} else if (ids.length >1 ) {
					showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='�����'){
							showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
							return false;
						}
					}
					showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("szdw_zwsq.do?method=qxsq",
							{
							 values:ids.toString(),
							 splcid : rows[0]['splc'] 
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json');
					}});
				}
			}
        var DCCLBH = "szdw_zwsq.do";//dcclbh,�������ܱ��

        //�Զ��嵼�� ����
        function exportConfig() {
            //DCCLBH�������ܱ��,ִ�е�������
            customExport(DCCLBH, ExportDatas);
        }

        //��������
        function ExportDatas() {
            setSearchTj();//���ø߼���ѯ����
            var url = "szdw_zwsq.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
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
				<logic:equal value="yes" name="writeAble">
					<div class="buttonbox">
						<ul>
							<li>
							  <a href="javascript:void(0);" id="btn_zj" class="btn_zj">����</a>
							</li>
							<li>
							  <a href="javascript:void(0);"  id="btn_xg" class="btn_xg">�޸�</a>
							</li>
							<li>
							<a href="javascript:void(0);"  id="btn_del" class="btn_sc" >ɾ��</a>
							</li>
							<li>
							  <a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
							</li>
							<li>
								<a href="javascript:void(0);" id="btn_sr"  class="btn_sr" onclick="cancel();return false;">����</a>
							</li>
							<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">���̸���</a></li>	
							<logic:equal value="11067" name="xxdm">
								<li><a href="javascript:void(0);" onclick="printXsgbbab('szdw_zwsq.do?method=printXsgbbab');return false;" class="btn_down">���ر�����</a></li>	
							</logic:equal>
							<logic:equal value="10351" name="xxdm">
								<li><a href="javascript:void(0);" onclick="exportConfig();return false;" class="btn_down">����</a></li>
							</logic:equal>
						</ul>
					</div>
				</logic:equal>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>ѧ���ɲ�ְ�������б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
