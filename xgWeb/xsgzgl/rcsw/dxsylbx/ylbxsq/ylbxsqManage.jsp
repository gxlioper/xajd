<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/dxsylbx/ylbxsq/js/ylbxsqManage.js"></script>
		<script type="text/javascript">

		
		function submitBusi(){
			var ids = jQuery("#dataTable").getSeletIds();
			if(ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫ�ύ�ļ�¼��");
			}else if (ids.length >1 ){
				showAlertDivLayer("��ѡ��һ����Ҫ�ύ�ļ�¼��");
			}else{
				var rows = jQuery("#dataTable").getSeletRow();
				var isopen = jQuery("#isopen").val();
				if('3'!=rows[0]["shzt"]&&"true"!=isopen){
					showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
					return false;
				}
				var url = "";
				for(var i=0;i<ids.length;i++){
					if(rows[i]['shztmc']!='δ�ύ' && rows[i]['shztmc']!='�˻�' ){
						showAlertDivLayer("��ѡ��δ�ύ�����˻صļ�¼��");
						return false;
					}
					if(rows[i]['shztmc']!='�˻�'){
						url = "rcsw_dxsylbx_ylbxsqgl.do?method=submitYlbxsq";
					}else{
						url = "rcsw_dxsylbx_ylbxsqgl.do?method=submitYlbxsq";
					}
					
				}

				var isopen = jQuery("#isopen").val();
				if(isopen==null||isopen==''){
					showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
					return false;
				}
				if ("false" == isopen && '3'!=rows[0]["shzt"]){
					showAlertDivLayer("��ǰδ�������룬����ϵ����Ա��");
					return false;
				}

				showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
					jQuery.post(url,
						{values:ids.toString(),
						 xh : rows[0]['xh'],
						 splc : rows[0]["splc"],
						 shzt : rows[0]["shzt"]
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
					if(rows[i]['shztmc']!='�����'){
						showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
						return false;
					}
				}
				showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
					jQuery.post("rcsw_dxsylbx_ylbxsqgl.do?method=cancelYlbxsq",
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

		function ylbxLcinfo(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			var shzt = rows[0]["shztmc"];
			if (ids.length != 1){
				showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
			} else {
				if ("δ�ύ" == shzt){
					showAlertDivLayer("�ü�¼Ϊδ�ύ״̬�������ύ��");
					return false;
				}	
				showDialog("��ѧ��ҽ�Ʊ����������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['ylsqid']+"&splc="+rows[0]['splc']);
			}
		}

		function getWord(){
			 var rows = jQuery("#dataTable").getSeletRow();
			 if (rows.length == 0){
				showAlertDivLayer("��ѡ��һ����¼��");
			 } else if (rows.length > 1){
				var url="rcsw_dxsylbx_ylbxsqgl.do?method=getDxsylbxZip";
				var ids = jQuery("#dataTable").getSeletIds();
				var url= url+"&value="+ids;
				window.open(url);
			 } else {
				var url="rcsw_dxsylbx_ylbxsqgl.do?method=getDxsylbxWord";
				var url= url+"&ylsqid="+rows[0]["ylsqid"]+"&xh="+rows[0]["xh"];
				window.open(url);
		     }
		}

	
		</script>
	</head>

	<body>
		<input type="hidden" name="isopen" id="isopen" value="${jcszModel.isopen }"/>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/rcsw_dxsylbx_ylbxjggl">
			<input type="hidden" name="tableName" id="tableName" value="xg_rcsw_ylbx_ylbxsqb"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_zj"
							   onclick="add();return false;" >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg">�޸�</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc">ɾ��</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="submitBusi();return false;" class="btn_shuc">�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" onclick="cancel();return false;" class="btn_sr">����</a>
						</li>
						</logic:equal>
						<li>
							<a href="javascript:void(0);" onclick="ylbxLcinfo();return false;" 
								   title="ѡ��һ����¼������ð�ť���Բ鿴������̡�"
								   class="btn_cs">���̸���</a>
						</li>	
						<!-- ��дȨ -->
						<logic:equal name="writeAble" value="yes">					
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
						</logic:equal>
						
						<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>		
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>ҽ�Ʊ���������Ϣά���б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
