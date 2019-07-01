<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='dwr/engine.js'></script> 
		<script type='text/javascript' src='dwr/util.js'></script>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/szdw/fdyrz/js/fdyrz_sq.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//Ϊbuttonע���¼�
				jQuery("#btn_zj").click(goFdyrzsq);
				jQuery("#btn_xg").click(update);
				//jQuery("#btn_sc").click(qx_sh);
				jQuery("#btn_sc").click(cancel);
				//jQuery("#search_go").click(query);
				jQuery("#btn_cz").click(function(){searchReset()});
				jQuery("#btn_cs").click(lcgz);
			});


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
						jQuery.post("szdw_fdyrz_sq.do?method=fdyrzqxsq",
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

			function submitBusi(){
				jQuery.ajaxSetup( {
					//��ֹ����ȫ�� AJAX �¼�
					async : false
				});
				
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length != 1) {
					showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
				} else {
					var rows = jQuery("#dataTable").getSeletRow();
					for(var i=0;i<ids.length;i++){
						if(rows[i]['shzt']!='δ�ύ' && rows[i]['shzt']!='�˻�' ){
							showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼��");
							return false;
						}
					}

					var tj = true;
					
					// ���˻ؼ�¼��Ҫ����ʱ�俪����֤
					if(rows[0]['shztdm']!='3'){
						jQuery.post("szdw_fdyrz_sq.do?method=timeSwitch",{},function(data){
							var message = data["message"];
							if(message!="true"){
								alertInfo(message);
								tj = false;
							}
						},'json');						
					};

					if(!tj){
						return false;
					}
					
					showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
						jQuery.post("szdw_fdyrz_sq.do?method=submitFdyrzsq",
							{values:ids.toString(),
							 zgh : rows[0]['zgh'],  
							 shzt : rows[0]['shztdm'],
							 splc : rows[0]['splc']
							},function(data){
							showAlertDivLayer(data["message"]);
							jQuery("#dataTable").reloadGrid();
						},'json')
					}});
				}
				jQuery.ajaxSetup( {
					//���¿�������ȫ�� AJAX �¼�
					async : true
				});
			}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/qgzx_jfcjgl_cjff.do?method=gjcxCjff">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
					<li>
					  <a href="javascript:void(0);" id="btn_zj" class="btn_zj">����</a>
					</li>
					<li>
					  <a href="javascript:void(0);" id="btn_xg" class="btn_xg">�޸�����</a>
					</li>
					<li>
					  <a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
					</li>
					<li>
						<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr" >����</a>
					</li>
					</logic:equal>
					<li>
						<a href="javascript:void(0);" id="btn_cs" class="btn_cs">���̸���</a>
					</li>			
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
				<span>�ҵ���ְ�����б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
