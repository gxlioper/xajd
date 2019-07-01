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
		<script type="text/javascript" src="xsgzgl/wjcf/cfssglnew/cfsssh/js/cfsssh.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/shlc/js/splc.js"></script>
		<script type="text/javascript">

			
			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
				jQuery("#btn_sh").click(go_sh);
				jQuery("#btn_cs").click(lcgz);
				jQuery("#btn_qxsh").click(cxshnew);
				jQuery("#btn_qxsh").hide();
			});

			function cxshnew(){
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length != 1){
					alertInfo("��ѡ��һ����Ҫ������˵ļ�¼��");
				} else {
					splc_cx_new(rows[0]["splcid"],rows[0]["shid"],rows[0]["cfid"]);
				}
			}
			/*
			 * �������̳���[���һ���ɳ���]
			 * shid ���id
			 * splc ��������id 
			 */
			function splc_cx_new(splc,shid,cfid){
				//���һ��������˺���õ�·��
				var cancelPath = jQuery("#cancelPath").val();
				confirmInfo("��ȷ��Ҫ����������?",function(ty){
					if(ty=="ok"){
						jQuery.post("comm_spl.do?method=cxshnew",{shlc:splc,shid:shid},function(data){
								// �ж��Ƿ����һ������(1:���һ�������ɹ���
								if("1" == data["cancelFlg"]){
									jQuery.post(cancelPath,{splcid:splc,shid:shid,cfid:cfid},function(result){
										showAlertDivLayer(result["message"],{},{"clkFun":function(){
											jQuery("#dataTable").reloadGrid();
										}});
									},'json');
								}else{
									showAlertDivLayer(data["message"],{},{"clkFun":function(){
										jQuery("#dataTable").reloadGrid();
									}});
								}
							
						},'json');
					}
				});
			}
			function showView() {
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length == 1) {
					var cfid = rows[0]["cfid"];
					var url = 'wjcf_cfsssh.do?method=ssshCk&ywid='+rows[0]["ywid"]+"&shid="+rows[0]["shid"]+"&gwid="+rows[0]["gwid"]+"&splcid="+rows[0]["splcid"]+"&cfid="+rows[0]["cfid"];
					showDialog("�鿴�����Ϣ", 820, 500, url);
				} else {
					showAlertDivLayer("�빴ѡһ����Ҫ�鿴�ļ�¼��");
					return false;
				}
			}
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				<input type="hidden" name="shlx" id="shlx" value="dsh"/>
			</p>
		</div>
		<html:form action="/szdw_zwsh.do">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="cancelPath" id="cancelPath" value="${cancelPath}"/>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal value="yes" name="writeAble">
						<li><a href="javascript:void(0);" id="btn_sh" class="btn_sh">���</a>
						<a href="javascript:void(0);" id="btn_qxsh" class="btn_qxsh">����</a>
						</li>
						</logic:equal>
						<li><a href="javascript:void(0);" id="btn_cs" class="btn_cs">���̸���</a></li>
						<li>
							<a href="#" onclick="showView(); return false;" class="btn_ck">�鿴</a>
						</li>
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="query(this,'dsh');"><span>������</span></a></li>
			        <li><a href="javascript:void(0);" onclick="query(this,'ysh');"><span>�Ѵ���</span></a></li>
			      </ul>
			    </div>
			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span>������������б�</span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
