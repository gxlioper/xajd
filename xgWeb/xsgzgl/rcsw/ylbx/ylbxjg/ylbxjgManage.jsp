<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="xsgzgl/rcsw/ylbx/ylbxjg/js/ylbxjgManage.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		<script type="text/javascript">

		jQuery(function(){
			var gridSetting = {
						caption:"ҽ�Ʊ��ս���б�",
						pager:"pager",
						url:"rcsw_ylbx_ylbxjggl.do?method=ylbxjgManage&type=query",
						colList:[
							{label:'key',name:'jgid', index: 'jgid',key:true ,hidden:true},
							<logic:notEqual name="xxdm" value="14073">
						   {label:'ѧ��',name:'xn', index: 'xn',width:'10%'},
						   </logic:notEqual>
						   <logic:equal name="xxdm" value="14073">
						   {label:'���',name:'zd5', index: 'zd5',width:'10%'},
						   </logic:equal>
						   {label:'ѧ��',name:'xh', index: 'xh',width:'12%',formatter:xhLink},
						   {label:'����',name:'xm', index: 'xm',width:'8%'},
						   {label:'�꼶',name:'nj', index: 'nj',width:'6%'},
						   {label:jQuery("#xymc").val(),name:'xymc', index: 'xymc',width:'15%'},
						   {label:'רҵ',name:'zymc', index: 'zymc',width:'15%'},
						   {label:'�༶',name:'bjmc', index: 'bjdm',width:'15%'},
						   {label:'����ʱ��',name:'sqsj', index: 'sqsj',width:'17%'},
						   <logic:equal name="xxdm" value="13573">
						   {label:'�ͱ���־',name:'sfkns', index: 'sfkns',width:'6%'},
						   </logic:equal>
						   <logic:equal name="xxdm" value="10335">
						   {label:'���չ�˾',name:'zd8mc', index: 'zd8mc',width:'10%'},
						   {label:'Ͷ������',name:'zd2', index: 'zd2',width:'10%'},
						   {label:'Ͷ�����',name:'zd3', index: 'zd3',width:'10%'},
						   </logic:equal>
						   {label:'��������',name:'sjly', index: 'sjly',hidden:true}
						],
						sortname: "sqsj",
					 	sortorder: "desc"
			};
			gridSetting["params"]=getSuperSearch();
			jQuery("#dataTable").initGrid(gridSetting);			

		});

		function searchRs(){
			var map = getSuperSearch();
			jQuery("#dataTable").reloadGrid(map);
		}

		function add(){
			var url = "rcsw_ylbx_ylbxjggl.do?method=addYlbxsqjg";
			var title = "����"+jQuery("#gnmkmc").val();
			showDialog(title,790,476,url);
		}



		function update() {
			var rows = jQuery("#dataTable").getSeletRow();
			var sjly = rows[0]["sjly"];
			
			if (rows.length != 1) {
				showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
			}else if(sjly == '1'){
				showAlertDivLayer("�������ݲ����޸ģ�");
			}else {
				var url = 'rcsw_ylbx_ylbxjggl.do?method=updateYlbxjg&jgid='+ rows[0]["jgid"]
				+ '&xh=' + rows[0]["xh"];
				var title = "�޸�"+jQuery("#gnmkmc").val();
				showDialog(title, 790,470, url);
			}

		}

		function del(){
			var ids = jQuery("#dataTable").getSeletIds();
			var rows = jQuery("#dataTable").getSeletRow();
			if (ids.length == 0){
				showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
			}else {
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<ids.length;i++){
					if(rows[i]['sjly']=='1'){
						showAlertDivLayer("�������ݲ���ɾ����");
						return false;
					}
				}
				
				showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
					"okFun" : function() {
						jQuery.post("rcsw_ylbx_ylbxjggl.do?method=delYlbxjg", {
							values : ids.toString()
						}, function(data) {
							var mes="�ɹ�ɾ����<font color='green'>&nbsp;"+data["num"]+"&nbsp;</font>������";
							mes+="</br>";
							if(data["nodel"]!="-1"){
								mes+="<font color='red'>"+data["nodel"]+"</font>";
								mes+="�������ݲ����޸ģ�";
							}
							showAlertDivLayer(mes);
							jQuery("#dataTable").reloadGrid();
						}, 'json');
					}
				});
			}
		}

		function ylbxjgView(jgid, xh) {
			showDialog(jQuery("#gnmkmc").val()+"�鿴", 700, 401, "rcsw_ylbx_ylbxjggl.do?method=viewOneYlbxjg&jgid=" + jgid
					+ "&xh=" + xh);
		}

		function xhLink(cellValue, rowObject) {
			return "<a href='javascript:void(0);' class='name' onclick='ylbxjgView(\""
					+ rowObject["jgid"] + "\",\"" + cellValue + "\");'>" + cellValue
					+ "</a>";
		}

		var DCCLBH = "rcsw_ylbx_ylbxjg.do";//dcclbh,�������ܱ��

		//�Զ��嵼�� ����
		function exportConfig() {
			//DCCLBH�������ܱ��,ִ�е������� 
			customExport(DCCLBH, ylbxjgExportData);
		}

		// ��������
		function ylbxjgExportData() {
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_ylbx_ylbxjggl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
			url = addSuperSearchParams(url);//���ø߼���ѯ����
			jQuery("form").eq(0).attr("action", url);
			jQuery("form").eq(0).submit();
		}

		
		// ����
		function zpdc() {
			showDialog("", 400, 150, 'rcsw_ylbx_ylbxjggl.do?method=zpdc');
		}
		// ����
		function dszmdy() {
			var ids = jQuery("#dataTable").getSeletIds();
			if(ids.length!=1){
				showAlertDivLayer("��ѡ��һ����Ҫ��ӡ�ļ�¼��");
				return false;
				}
			var url="rcsw_ylbx_ylbxjggl.do?method=dszmdy&jgid="+ids;
			window.open(url);
		}

		
		function exportZpxx(mmfs,zpType){
			setSearchTj();//���ø߼���ѯ����
			var url = "rcsw_ylbx_ylbxjggl.do?method=zpdc&type=exp&photoNameType="+mmfs+"&zpType="+zpType;
				url = addSuperSearchParams(url);
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
		}

		//�°浼��
		function dr() {
			// ����ͨ�õĵ���function�������ǵ��빦��ģ����롣
			toImportDataNew("IMPORT_N732605_YLBXGLNEW");
			return false;

		}
				
		</script>
	</head>

	<body>
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
				</p>
				<p class="help">
					<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();">ʹ�ð���</a>
				</p>
			</div>
			
		<logic:equal name="xxdm" value="13573">
			<!-- ��ʾ��Ϣ start-->
			<div  id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						��Ƭ�����Ǹ���<font color="red">�߼���ѯ���������</font>�����ģ�
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
		</logic:equal>
		
		<html:form action="/rcsw_ylbx_ylbxjggl">
		
		<input type="hidden" id="xymc" value="<bean:message key="lable.xy" />"/>
		<input type="hidden" id="gnmkmc" value="${gnmkmc}"/>
		<%@ include file="/comm/hiddenValue.jsp"%>
			
			<div class="toolbox">
				
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<logic:notEqual name="userType" value="stu">
				  		    <logic:equal name="writeAble" value="yes">
							<li>
								<a href="javascript:void(0);" class="btn_zj" onclick="add();return false;" >����</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="update();return false;" class="btn_xg" >�޸�</a>
							</li>
							<li>
								<a href="javascript:void(0);" onclick="del();return false;" class="btn_sc" >ɾ��</a>
							</li>
							<li><a href="#" class="btn_dr" onclick="dr();return false;" id="btn_dr">����</a></li>
							</logic:equal>
							<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>	
							<logic:equal name="xxdm" value="13573">
								<li><a href="javascript:void(0);" onclick="zpdc();return false;" class="btn_dc">��Ƭ����</a></li>
							</logic:equal>
							<logic:equal name="xxdm" value="12688">
								<li><a href="javascript:void(0);" onclick="zpdc();return false;" class="btn_dy">��Ƭ����</a></li>
							</logic:equal>
						</logic:notEqual>
						
						<logic:equal name="xxdm" value="10335">
							<li><a href="javascript:void(0);" onclick="dszmdy();return false;" class="btn_dy">��ʧ֤����ӡ</a></li>
						</logic:equal>						
					</ul>
				</div>
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>${gnmkmc}�б�&nbsp;&nbsp; </span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
