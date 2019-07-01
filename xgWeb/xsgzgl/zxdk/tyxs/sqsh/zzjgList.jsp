<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>		
		<script type="text/javascript" src="xsgzgl/zxdk/tyxs/sqsh/js/zzjg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script>
		
		<script type="text/javascript">
		
			jQuery(function(){
				var gridSetting = {
					caption:"��������б� ",
					pager:"pager",
					url:"tyxs_zzjg.do?method=getZzjgList",
					colList:[
					   {label:'key',name:'id', index: 'id',hidden:true,key:true},
					   {label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:function(cell,rowObject){
						   return "<a href=\"javascript:ckZzjg('"+rowObject["id"]+"');\" class='name'>"+cell+"</a>";}},
					   {label:'����',name:'xm', index: 'xm',width:'10%'},
					   {label:'ѧԺ',name:'xymc', index: 'xydm',width:'15%'},
                        {label:'��Ժ',name:'symc', index: 'sydm',width:'13%'},
                        {label:'�����༶',name:'bjmc', index: 'bjmc',width:'13%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
					   {label:'����ѧ��',name:'xn',index:'xn',width:'13%'},	
					   {label:'�����ܽ��',name:'sqxfzj',index:'sqxfzj',width:'13%'},				  				 	
					   {label:'����ʱ��',name:'sqsj',index:'sqsj',width:'13%'},
					   {label:'�����ܽ��',name:'zzzje',index:'zzzje',width:'13%'},
					   {label:'����ID',name:'sqid',index:'sqid',width:'13%',hidden:true}
					  
					],
					sortname:"sqsj",
					sortorder:"desc",
					radioselect:false
				};
				var map = getSuperSearch();
				gridSetting["params"] = map;
				jQuery("#dataTable").initGrid(gridSetting);
				
			});
			
			function importDkxx(){
				toImportDataNew("IMPORT_N9005_TYXSJGB");
				return false;
			}
			
			function printSqb(){
				var url = "tyxs_zzjg.do?method=printSqb";
				var rows = jQuery("#dataTable").getSeletRow();
				if (rows.length <=0) {
					showAlertDivLayer("��ѡ��һ����¼��");
				} else {
					var guid = jQuery("#dataTable").getSeletIds();
					var url = url + "&ids=" +guid;
					window.open(url);
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
	
		<html:form action="/tyxs_zzjg" method="post" styleId="TyxsZzjgForm">
			 <%@ include file="/comm/hiddenValue.jsp"%>
			<div class="toolbox">
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
						
							
								<li>
									<a href="javascript:void(0);" class="btn_zj"
									   onclick="addZzjg();return false;" 
									   title="����ð�ť�����������дҳ�档"
									>
									����
									</a>
								</li>
								<li>
									<a href="javascript:void(0);" onclick="editZzjg();return false;" class="btn_xg"
									   title="ѡ��һ����¼������ð�ť���޸������"
									>�޸�</a>
								</li>								
								<li>
									<a href="javascript:void(0);" onclick="delZzjg();return false;" class="btn_sc"
									   title="ֻ��ɾ��δ�ύ�ļ�¼��"
									>ɾ��</a>
								</li>
							<logic:equal value="10335" name="xxdm">
								<logic:equal value="zf01" name="userName">
									<li>
										<a href="javascript:void(0);"  onclick="exportConfig();return false;" class="btn_dc" >����</a>
									</li>
								</logic:equal>
							</logic:equal>
							<logic:notEqual value="10335" name="xxdm">
								<li>
									<a href="javascript:void(0);"  onclick="exportConfig();return false;" class="btn_dc" >����</a>
								</li>
							</logic:notEqual>
							
							<li><a href="#"  onclick="importDkxx();return false;" class="btn_dr">����</a></li>							
							<li><a href="javascript:void(0);" onclick="printSqb();" class="btn_dy">���صǼǱ�</a></li>						
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
				<span>��˽���б� </span>
			</h3>
			<table id="dataTable" ></table>
			<div id="pager"></div>
		</div>
	</body>
	
</html>
