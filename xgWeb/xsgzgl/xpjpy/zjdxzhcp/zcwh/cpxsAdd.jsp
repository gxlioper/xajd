<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="js/comm/jcsjld.js"></script>
		<script type="text/javascript" src="js/uicomm.js"></script>
		<script type="text/javascript">
			jQuery(function(){
				var gridSetting = {
					caption:"ѧ����Ϣ�б�",
					pager:"pager",
					rowNum:10,
					url:"xpjpy_zcwh.do?method=cpxsAdd&doType=query",
					colList:[
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',key:true},
					   {label:'����',name:'xm', index: 'xm',width:'13%'},
					   {label:'�꼶',name:'cpnj', index: 'cpnj',width:'10%'},
					   {label:'<bean:message key="lable.xb" />',name:'cpxymc', index: 'cpxydm',width:'18%'},
					   {label:'רҵ',name:'cpzymc', index: 'cpzydm',width:'18%'},
					   {label:'�༶',name:'cpbjmc', index: 'cpbjdm',width:'18%'},
					   {label:'��ע',name:'tjzt', index: 'tjzt',width:'10%',noSort:true,formatter:function(cell,rowObject){
						   if (cell == "1"){
							   return "<font color='red' title='��ѧ�����ڰ༶���ύ�۲�֡�'>("+rowObject["cpxymc"]+")������</font>";
						   } else {
							   return "<font color='blue'>δ����</font>";
						   }
					   }}
					],
					params:{
						bjdm:jQuery("#bjdm").val()
					}
				}
				var map = getSuperSearch();
				gridSetting["params"] = map;
				
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			/*�߼���ѯ*/
			function searchRs(){
				var map = getSuperSearch();
				map["bjdm"] = jQuery("#bjdm").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			/*��������༶������*/
			function showBjtzDiv(){
				var rows = jQuery("#dataTable").getSeletRow();
				for(var i=0;i<rows.length;i++){
					if(rows[i]['tjzt']=='1'){
						showAlertDivLayer("����������Ա�����Ե�����");
						return false;
					}
				}
				if(rows.length == 0){
					showAlertDivLayer("����<font color='blue'>��ѡ</font>��Ҫ�����༶��ѧ��");
					return false;
				}else{
					tipsWindown("ϵͳ��ʾ","id:div_bjtz","360","200","true","","true","id");
				}
			}

			/*������Ա�༶����*/
			function tzCpry(){
				var bjdm = jQuery("#bjdm").val();
				var ids = jQuery("#dataTable").getSeletIds();
				jQuery.post("xpjpy_zcwh.do?method=updateCpbj",{bjdm:bjdm,ids:ids.toString()},function(data){
					showAlert(data["message"],{},{"clkFun":function(){
						if(parent.window){
							refershParent();
						}
					}});
				},"json");
			}
		</script>
	</head>

	<body>
		<html:form action="/xpj_cpmd" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<li><a href="javascript:void(0);" onclick="showBjtzDiv();" class="btn_zj">�����༶</a></li>						
					</ul>
				</div>
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
		
		<div class="formbox">
			<!--����start-->
			<h3 class="datetitle_01">
				<span> ѧ����Ϣ�б�
				 </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
		<!-- �༶���������� -->
		<div id="div_bjtz" style="display: none">
			<div style='tab'>
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="2">
								<span>�����༶</span>
							</th>
						</tr>
					</thead>
					<tbody id="tbody_jbxx">
						<tr>
							<th width="30%">�꼶</th>
							<td >
								<html:select property="nj" styleId="nj" 
									onchange="onChangJcsj('nj','xydm','zydm','bjdm','nj','1','1');" style="width:100px">
									<html:option value="">&nbsp;</html:option>
									<html:options collection="njList" property="nj"
										labelProperty="nj" />
								</html:select>
							</td>
						</tr>
						<tr>
							<th ><bean:message key="lable.xb" /></th>
							<td>											
								<html:select property="xydm" styleId="xydm"
								onchange="onChangJcsj('nj','xydm','zydm','bjdm','xy','1','1');"  style="width:180px;">
									<html:option value="">&nbsp;</html:option>
								</html:select>
				
							</td>
						</tr>
						<tr>
							<th >רҵ</th>
							<td>
								<html:select property="zydm" styleId="zydm"
								onchange="onChangJcsj('nj','xydm','zydm','bjdm','zy','1','1');"   style="width:180px;">
									<html:option value="">&nbsp;</html:option>
								</html:select>
							</td>
						</tr>
						<tr>
							<th ><span class="red">*</span>�༶</th>
							<td>
								<html:select property="bjdm" styleId="bjdm" style="width:180px;">
									<html:option value="">&nbsp;</html:option>
								</html:select>
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="2">
								<div class="bz">"<span class="red">*</span>"Ϊ������</div>
								<div class="btn">
									<button type="button" type="button" onclick="tzCpry();return false;">
										ȷ ��
									</button>
									<button type="button" type="button" onclick="closeWindown();return false;">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>
	</html:form>
	</body>
</html>