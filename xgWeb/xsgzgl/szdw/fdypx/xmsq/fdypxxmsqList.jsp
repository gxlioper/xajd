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
		<script type="text/javascript" src="xsgzgl/szdw/fdypx/js/fdypxsq.js"></script>
		<script type="text/javascript">
		
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				//Ϊbuttonע���¼�
				jQuery("#btn_zj").click(add);
				jQuery("#btn_sc").click(qx_sh);
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
						jQuery.post("szdw_fdypxxmsq.do?method=submitFdypxsq",
							{values:ids.toString(),
							 fbr : rows[0]['fbr'],
							 splcid : rows[0]['splc'],
							 shzt : rows[0]['shztdm'],
							 xmdm : rows[0]['xmdm'],
							 shzt : rows[0]['shzt']
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
						jQuery.post("szdw_fdypxxmsq.do?method=qxsq",
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
							  <a href="javascript:void(0);" id="btn_xg" class="btn_xg">�޸�</a>
							</li>
							
							<li>
							  <a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
							</li>
							
							<li>
							  <a href="javascript:void(0);" id="btn_sr"  class="btn_sr" onclick="cancel();return false;">����</a>
							</li>
							
							<li>
							  <a href="javascript:void(0);" id="btn_cs" class="btn_cs">���̸���</a>
							</li>		
							
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
				<span>����Ա��ѵ��Ŀ����</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
