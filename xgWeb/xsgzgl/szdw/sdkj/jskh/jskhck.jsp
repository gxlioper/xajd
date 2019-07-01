<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script language="javascript" src="js/xgutil.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/exportData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script> 
		<script type="text/javascript">
			var gridSetting = {
				caption:"����Ա����¼���б�",
				pager:"pager",
				url:"sdkj_zdjskh.do?method=ckxx&type=query",
				colList:[
				   {label:'id',name:'id', index: 'id',width:'1%',hidden:true,key:true},
				   {label:'ϵ��  &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;',name:'xb', index: 'xb',width:'20%'},
				   {label:'ָ����ʦ',name:'zdls', index: 'zdls',width:'30%'},
				   {label:'������',name:'zrs', index: 'zrs',width:'20%'},
				   {label:'��У����',name:'zxrs', index: 'zxrs',width:'20%'},
				   {label:'�۷ֺϼ�',name:'kfhj', index: 'kfhj',width:'20%'},
				   {label:'У��÷�',name:'xfdf', index: 'xfdf',width:'20%'},
				   {label:'�����������',name:'jsjgjl', index: 'jsjgjl',width:'20%'},
				   {label:'Ӣ�������',name:'yygjl', index: 'yygjl',width:'20%'},
				   {label:'��Ԣ����',name:'gycx', index: 'gycx',width:'20%'},
				   {label:'��ҵ��',name:'jyl', index: 'jyl',width:'20%'},
				   {label:'��Ϣ�� &nbsp; &nbsp; ',name:'xxj', index: 'xxj',width:'20%'},
				   {label:'ʹ���ʵ÷�',name:'syldf', index: 'syldf',width:'20%'},
				   {label:'�Ŷӽ���',name:'tdjs', index: 'tdjs',width:'20%'},
				   {label:'������  &nbsp; &nbsp; ',name:'dkl', index: 'dkl',width:'20%'},
				   {label:'�����ʵ÷�',name:'dkldf', index: 'dkldf',width:'20%'},
				   {label:'ְҵ���Ĺ滮�ϸ���',name:'zysyghhgl', index: 'zysyghhgl',width:'20%'},
				   {label:'ְҵ���Ĺ滮�÷�',name:'zysyghdf', index: 'zysyghdf',width:'20%'},
				   {label:'��ʦָ���ֲ�',name:'jszdsc', index: 'jszdsc',width:'20%'},
				   {label:'ѧ����ʧ',name:'xsls', index: 'xsls',width:'20%'},
				   {label:'ѧ��ר��',name:'xszl', index: 'xszl',width:'20%'},
				   {label:'��ȫ',name:'aq', index: 'aq',width:'20%'},
				   {label:'������',name:'dshj', index: 'dshj',width:'20%'},
				   {label:'�÷�  &nbsp; &nbsp; ',name:'df', index: 'df',width:'20%'},
				   {label:'������÷�',name:'xzhdf', index: 'xzhdf',width:'20%'}
				],
				sortname: "xb",
			 	sortorder: "asc"
			}

			
			jQuery(function(){
				var nf = jQuery("#nf").val();
				var yf = jQuery("#yf").val();
				gridSetting.params={nf:nf,yf:yf};
				jQuery("#dataTable").initGrid(gridSetting);
			});

			function query(){
				var map = {};
				map["nf"] = jQuery("#nf").val();
				map["yf"] = jQuery("#yf").val();
				map["xb"] = jQuery("#xb").val();
				map["zdls"] = jQuery("#zdls").val();
				map["zgh"] = jQuery("#zgh").val();

				jQuery("#dataTable").reloadGrid(map);
			}

			function delKhjl(){
				var ids = jQuery("#dataTable").getSeletIds();
				if (ids.length == 0){
					alertInfo("��ѡ����Ҫɾ���ļ�¼��");
				} else {
					jQuery.post("sdkj_zdjskh.do?method=delKhjl",{values:ids.toString()},function(data){
						alert(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			}
			function goHome(){
				url='szdw_sdkj_zdjskh.do';
				refreshForm(url);
			}




			
		</script>
	</head>

	<body>
	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
		</div>
		<html:form action="/sdkj_zdjskh">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="goHome();return false;" class="btn_fh">����</a></li>
						<li><a href="javascript:void(0);" onclick="delKhjl()" class="btn_sc">ɾ��</a></li>
						<li><a href="#" class="btn_qx" onclick="choiceFields();return false;">��������</a></li>
						<li><a href="#" class="btn_dc" onclick="configureExportData();return false;">��������</a></li>
					</ul>
				</div>
				<div class="searchtab">
				<table width="100%" border="0">
					<tfoot>
						<tr>
							<td colspan="6">
								<div class="btn">
									<button type="button" class="btn_cx" id="search_go" onclick="query()">
										�� ѯ
									</button>
									<button type="button" class="btn_cz" id="btn_cz" onclick="searchReset()">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
					<tbody>
						<tr>
							<th>ϵ��</th>
							<td><input type="text" id="xb" name="xb"/>
							</td>
							<th>���</th>
							<td>
								<html:select name="map" property="nf" styleId="nf">
									<html:options collection="nfList" property="nd" labelProperty="nd"/>
								</html:select>
							</td>
							<th>�·�</th>
							<td>
								<html:select name="map" property="yf" styleId="yf">
									<html:option value=""></html:option>
									<html:options collection="yfList" property="yf" labelProperty="yf"/>
								</html:select>
							</td>
						</tr>
						<tr>
							<th>��ʦ����</th>
							<td><input type="text" id="zdls" name="zdls"/>
							</td>
							<th>ְ����</th>
							<td><input type="text" id="zgh" name="zgh"/>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
				

			</div>
		</html:form>
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ָ����ʦ����¼����Ϣ�б� </span>
			</h3>

			<div  style="hoverflow-x:hidden;overflow-y:auto;">
				<table id="dataTable" ></table>
			</div>
			<div id="pager"></div>

		</div>
	</body>
</html>
