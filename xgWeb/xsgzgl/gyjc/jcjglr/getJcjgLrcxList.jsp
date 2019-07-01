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
				url : "gyjc_jcjglr.do?method=getJcjgLrcxList&type=query",
				colList : [ {
					label : 'key',
					name : 'mxid',
					index : 'mxid',
					key : true,
					hidden : true
				}, {
					label : 'key',
					name : 'rcid',
					index : 'rcid',
					hidden : true
				}, {
					label : 'ѧԺ',
					name : 'xymc',
					index : 'xymc',
					width : '10%'
				}, {
					label : '¥��',
					name : 'ldmc',
					index : 'ldmc',
					width : '10%'
				}, {
					label : '����',
					name : 'qsh',
					index : 'qsh',
					width : '10%'
				}, {
					label : '���',
					name : 'jjlxmc',
					index : 'jjlxmc',
					width : '10%'
				}
				<logic:notEqual value="wtjscx" name="JcjglrForm" property="flag">
				, {
					label : '�������ҵȼ�',
					name : 'dj',
					index : 'dj',
					width : '10%'
				},
				{
					label : '�����˵��',
					name : 'xh',
					index : 'xh',
					width : '10%'
				},
				<logic:equal value="lr" name="JcjglrForm" property="flag">
				{
					label : '�ύ״̬',
					name : 'tjztmc',
					index : 'tjztmc',
					width : '10%'
				},
				</logic:equal>
				{
					label : 'lddm',
					name : 'lddm',
					index : 'lddm',
					hidden : true
				},
				{
					label : 'tjzt',
					name : 'tjzt',
					index : 'tjzt',
					hidden : true
				},
				{
					label : 'xydm',
					name : 'xydm',
					index : 'xydm',
					hidden : true
				}
				</logic:notEqual>
				<logic:equal value="ytjscx" name="JcjglrForm" property="flag">
				, {
					label : '�����',
					name : 'tjrxm',
					index : 'tjrxm',
					width : '10%'
				}, {
					label : '���ʱ��',
					name : 'tjsj',
					index : 'tjsj',
					width : '10%'
				}
				
				</logic:equal>
				<logic:equal value="jgcx" name="JcjglrForm" property="flag">
				, {
					label : '�����',
					name : 'tjrxm',
					index : 'tjrxm',
					width : '10%'
				}, {
					label : '���ʱ��',
					name : 'tjsj',
					index : 'tjsj',
					width : '10%'
				}
				
				</logic:equal>
				],
				radioselect:false
			}
			var map = getSuperSearch();
			map['rcid'] = jQuery("#rcid").val();
			map['tjzt'] = jQuery("#tjzt").val();
			map['flag'] = jQuery("#flag").val();
			map['tjr'] = jQuery("#tjr").val();
			gridSetting["params"] = map;
			jQuery("#dataTable").initGrid(gridSetting);
		});
		var DCCLBH = "gyjc_jcjglr.do?method=getJcjgLrcxList";//dcclbh,�������ܱ��
		//����
		function dc(){
			customExport(DCCLBH, lrjgExportData);
		}
		//��������
		function lrjgExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "gyjc_jcjglr.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		//¼��
		function lr(){
			var rows = jQuery("#dataTable").getSeletRow();
			if(rows.length != 1){
				return showAlertDivLayer("��ѡ��һ����¼��");
			}
			if(rows[0]["tjzt"] != "0"){
				return showAlertDivLayer("���ύ��״̬�������޸ģ�");
			}
			var url = 'gyjc_jcjglr.do?method=addJcJgLr&rcid='+rows[0]["rcid"]+"&xydm="+rows[0]["xydm"]+"&qsh="+rows[0]["qsh"]+"&lddm="+rows[0]["lddm"]+"&js="+jQuery("#js").val(); 
            var title = "�����¼��";
            showDialog(title, 770, 552, url);
		}

		/**
		 * �鿴�����
		 * @return
		 */
		function jcjglrck(){
			var rows = jQuery("#dataTable").getSeletRow();
			if(rows.length != 1){
				return showAlertDivLayer("��ѡ��һ����¼��");
			}
			var url = 'gyjc_jcjglr.do?method=ckJcJgLr&rcid='+rows[0]["rcid"]+"&xydm="+rows[0]["xydm"]+"&qsh="+rows[0]["qsh"]+"&lddm="+rows[0]["lddm"]+"&js="+jQuery("#js").val()+"&flag="+jQuery("#flag").val(); 
            var title = "�����¼��";
            showDialog(title, 770, 552, url);
		}

		//�ύ
		function tj(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				return showAlertDivLayer("��ѡ������һ����Ҫ�ύ�ļ�¼��");
			}
			showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("gyjc_jcjglr.do?method=submitRecord",{mxids:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}

		//����
		function cancel(){
			var ids = jQuery("#dataTable").getSeletIds();
			if (ids.length == 0){
				return showAlertDivLayer("��ѡ������һ����Ҫ�����ļ�¼��");
			}
			showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��",{"okFun":function(){
				jQuery.post("gyjc_jcjglr.do?method=cxRecord",{mxids:ids.toString()},function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},'json');
			}});
		}

		//����
		function fh(){
			document.location.href = 'xg_gyjc_jcjglr.do';
		}

		function searchRs() {
			var map = getSuperSearch();
			map['rcid'] = jQuery("#rcid").val();
			map['tjzt'] = jQuery("#tjzt").val();
			map['flag'] = jQuery("#flag").val();
			map['tjr'] = jQuery("#tjr").val();
			jQuery("#dataTable").reloadGrid(map);
		}
		</script>
	</head>

	<body>
	<logic:equal value="jgcx" name="JcjglrForm" property="flag"> 
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
	</logic:equal>
		<html:form action="/gyjc_jcjglr">
			<html:hidden property="rcid" styleId="rcid" />
			<html:hidden property="tjzt" styleId="tjzt" />
			<html:hidden property="flag" styleId="flag" />
			<html:hidden property="tjr" styleId="tjr" />
			<html:hidden property="js" styleId="js"/>
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal value="lr" name="JcjglrForm" property="flag"> 
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li>
							<a href="javascript:void(0);" class="btn_fh" onclick="fh();return false;"  >����</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_zj" onclick="lr();return false;"  >�����¼��</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="jcjglrck();return false;"  >�鿴</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_tj" onclick="tj();return false;"  >�ύ</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="dc();return false;"  >����</a>
						</li>
						
					</ul>
				</div>
				</logic:equal>
				<logic:equal value="jgcx" name="JcjglrForm" property="flag"> 
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
					<logic:equal name="writeAble" value="yes">
						<li>
							<a href="javascript:void(0);" class="btn_qxsh" onclick="cancel();return false;"  >����</a>
						</li>
					</logic:equal>
						<li>
							<a href="javascript:void(0);" class="btn_ck" onclick="jcjglrck();return false;"  >�鿴</a>
						</li>
						<li>
							<a href="javascript:void(0);" class="btn_dc" onclick="dc();return false;"  >����</a>
						</li>
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
				<span>����ճ��б�&nbsp;&nbsp;<font color="blue">����������飬��ȫ��飬���ɼ��</font> </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
