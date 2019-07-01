<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/zhcp/zcfs/js/zcfs.js"></script>
		<script type="text/javascript" src="js/json.js"></script>
		<script type="text/javascript">
			var gridSetting = {
				caption:"�۲�༶�б�",
				pager:"pager",
				url:"xpj_zcfs.do?method=viewBjzcListCk&doType=query",
				colList:[
				   {label:'key',name:'id', index: 'id',hidden:true,key:true},
				   {label:'�꼶',name:'nj', index: 'nj',width:'8%'},
				   {label:'<bean:message key="lable.xb" />',name:'xymc', index: 'xydm',width:'24%'},
				   {label:'רҵ',name:'zymc', index: 'zydm',width:'23%'},
				   {label:'�༶',name:'bjdm', index: 'bjdm',hidden:true},
				   {label:'�༶',name:'bjmc', index: 'bjdm',width:'17%'},
				   {label:'�༶����',name:'bjrs', index: 'bjrs',width:'8%',
					formatter:function(cellValue,rowObject){
								var html = jQuery("<a href='javascript:void(0);' class='name'>"+cellValue+"</a>");
								html.bind("click",function(){
									showDialog("�鿴�۲��",700,500,"xpj_zcfs.do?method=viewZcfs&id="+rowObject["id"]);
								});
								return html;
							 }
				   }
				],
				sortname: "tjzt,nj,xydm,zydm",
			 	sortorder: "desc"
			};
			
			jQuery(function(){
				gridSetting["params"] = {tjzt:jQuery("#tjzt").val(),yf:jQuery("#yf").val()};
				jQuery("#dataTable").initGrid(gridSetting);
			});
			
			function searchRs(){
				var map = getSuperSearch();
				map["tjzt"]= jQuery("#tjzt").val();
				map["yf"]= jQuery("#yf").val();
				jQuery("#dataTable").reloadGrid(map);
			}

			var DCCLBH = "tjqkdc.do";//dcclbh,�������ܱ��

			//�Զ��嵼�� ����
			function exportConfig() {
				//DCCLBH�������ܱ��,ִ�е������� 
				customExport(DCCLBH, bjExportData);
			}

			//��������
			function bjExportData() {
				setSearchTj();//���ø߼���ѯ����
				var url = "xpj_zcfs.do?method=exportDataDc&dcclbh=" + DCCLBH+"&yf="+jQuery("#yf").val()+"&tjzt="+jQuery("#tjzt").val();//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
						
			
		</script>
	</head>

	<body>
		<logic:present name="zcxmList">
			<logic:iterate id="z" name="zcxmList">
				<font style="display:none;" xmdm="${z.xmdm }"  zdfs="${z.zdfs }" zxfs="${z.zxfs }"
				      xmmc="${z.xmmc }" xmlx="${z.xmlx }" jktb="${z.jktb }" name="zcxm"></font>
			</logic:iterate>
		</logic:present>
		<input type="hidden" value="${cssz.xn }" id="xn"/>
		<input type="hidden" value="${cssz.xq }" id="xq"/>
		<input type="hidden" value="${szyf}"  id="szyf"/>
		<input type="hidden" value="${tjzt}" id="tjzt"/>
		<input type="hidden" value="${yf}" id="yf"/>
		
		<div class="tab_cur">
		</div>
		<html:form action="/xpj_zcfs" >
			<%@ include file="/comm/hiddenValue.jsp"%>
			
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						���༶����<font color="red">������Ա����</font>��ά���μ�����ѧ����<font color="red">�۲�ɼ�</font>
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			<div class="toolbox">
				<!-- ��ť -->
				<logic:equal name="writeAble" value="yes">	
					<div class="buttonbox">
						<ul>
							<logic:equal value="true" name="cssz" property="kgzt">
							</logic:equal>
							<li><a href="javascript:void(0);" onclick="exportConfig();" class="btn_dc">����</a></li>		
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
				<span><font color="blue">${cssz.zqmc}&nbsp;</font>�۲�༶�б� </span>
			</h3>

			<table id="dataTable" ></table>
			<div id="pager"></div>

		</div>
	</body>
</html>
