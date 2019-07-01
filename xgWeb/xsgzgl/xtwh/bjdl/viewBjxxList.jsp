<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�༶��Ϣ�б�",
				pager:"pager",
				url:"xtwh_bjdl.do?method=viewBjxxList&type=query",
				colList:[
				   {label:'�꼶',name:'nj', index: 'nj',width:'10%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'20%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'20%'},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'20%'},
				   {label:'�༶����',name:'lbmc', index: 'lbdm',width:'20%'},
				   {label:'bjdm',name:'bjdm', index: 'bjdm',width:'20%',key:true,hidden:true}
				],
				sortname: "bjdm",
			 	sortorder: "asc"
			};

			jQuery(function(){
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function searchRs(){
				var map = getSuperSearch();
				jQuery("#dataTable").reloadGrid(map);
			}

			function szBjdl(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				
				if (rows.length == 0){
					if(jQuery("#dataTable").getRowCount() == '0'){
							showAlertDivLayer("û�а༶�����ã������²�ѯ��");
							return ;
						}

					//showAlertDivLayer("��ѡ����Ҫ���õİ༶��");
					var url = "xtwh_bjdl.do?method=szBjdl&szType=szdl_all&path=xg_bjdl.do";
					var map = getSuperSearch();
					//�߼���ѯ
					url +="&searchTj=";
					url +=map["searchTj"];
					url +="&searchTjz=";
					url +=map["searchTjz"];
					url +="&mhcx_lx=";
					url +=map["mhcx_lx"];
					url +="&searchLx=";
					url +=map["searchLx"];

					//ģ����ѯ
					url +="&input_mhcx=";
					url +=map["input_mhcx"];
					url +="&mhcx_lx=";
					url +=map["mhcx_lx"];
					
					showDialog("���ð༶����",500,300,url);
				} else {
					var ids = jQuery("#dataTable").getSeletIds();
					showDialog("���ð༶����",500,300,"xtwh_bjdl.do?method=szBjdl&bjdm="+ids);
				}
				
			}

			//ȡ���༶����
			function qxBjdl(){
				
				var rows = jQuery("#dataTable").getSeletRow();
				
				if(jQuery("#dataTable").getRowCount() == '0'){
					showAlertDivLayer("û�а༶�ɳ����������²�ѯ��");
					return ;
				}

				var ids = jQuery("#dataTable").getSeletIds();
				var map = getSuperSearch(); 
				var values = ids.toString();
				
				var show = "";
				if(rows.length == 0){
					show="��ȷ��Ҫ������ѯ��������еİ༶������"
				}else{
					show="��ȷ��Ҫ����ѡ��İ༶������"
				}
				showConfirmDivLayer(show,{"okFun":function(){
					jQuery.post("xtwh_bjdl.do?method=delBjdl&values="+values,map,function(data){
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}});
				
			}
			
			// ��������
			var DCCLBH = "xg_bjdl.do";//dcclbh,�������ܱ��
			function exportConfig() {
				customExport(DCCLBH, exportData);
			}
			function exportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "xtwh_bjdl.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
	<html:form action="/xtwh_bjdl">
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">				
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		
		<!-- ��ʾ��Ϣ end-->
		<div id="div_help" class="prompt" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					�˴��ɶ԰༶������л��֣���Ҫ�������������������������ã�����ĳЩ���������÷�Χ
				</span>
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<%@ include file="/comm/hiddenValue.jsp"%>
	
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li><a href="javascript:void(0);" 
						   onclick="szBjdl();" 
						   class="btn_xg">���ð༶����</a></li>
					<li><a href="javascript:void(0);" 
						   onclick="qxBjdl();" 
						   class="btn_sc">�����༶����</a></li>
					<li><a href="#" class="btn_dc" onclick="exportConfig();return false;" >����</a></li>	
				</ul>
			</div>
			<!-- �������� -->
			<%@ include file="/comm/search/superSearchArea.jsp"%>
		</div>
		
	
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> �༶��Ϣ�б� </span>
			</h3>

			<table id="dataTable"></table>
			<div id="pager"></div>
			<input type="hidden" value="0" name="hiddenRowCount" id="hiddenRowCountID"/>
		</div>
		</html:form>
	</body>
</html>
