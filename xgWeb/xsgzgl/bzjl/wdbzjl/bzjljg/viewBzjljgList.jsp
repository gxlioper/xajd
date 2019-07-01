<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/bzjl/wdbzjl/bzjljg/js/bzjljg.js"></script>
		<script type="text/javascript" src="xsgzgl/comm/exportNew/import.js"></script><!-- ���빦����Ҫ -->
		<script language="javascript" src="js/LodopFuncs.js"></script>
		<object  id="LODOP_OB" classid="clsid:2105C259-1E0C-4534-8141-A753534CB4CA" width=0 height=0> 
		       <embed id="LODOP_EM" type="application/x-print-lodop" width=0 height=0></embed>
		</object>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
		<script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
		<script type="text/javascript">
			// ����ģ��
			function szRyzsCommon(csdm) {
			 	jQuery.post("bzjl_bzjg.do?method=cxRyzs",{csdm:csdm},function(data){
				 	 var csz = data["csz"];
					 if(csz == null){
						 showAlertDivLayer("ģ��δ��ʼ����");
						 return false;
					 }else{
						// ��ʼ�����
						var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
						LODOP.PRINT_INIT("��ӡ");
						// ����ģ��Ԫ��
						eval(csz); 
						// ���÷���ֵ
						LODOP.SET_PRINT_MODE("PRINT_SETUP_PROGRAM",true);
						
						//var csz = LODOP.PRINT_SETUP(); // ��ͨ�û�ģʽ��ֻ�ܵ���ģ�����ݵ�λ�ã�
						var csz = LODOP.PRINT_DESIGN(); // ������Աģʽ�������޸�ģ�����ݣ�
						
						confirmInfo("�Ƿ񱣴�ģ�壿",function(data){
							if("ok"==data){
								jQuery.post("bzjl_bzjg.do?method=bcRyzs",{csdm:csdm, csz:csz},function(data){
									showAlertDivLayer(data["message"]);
								},'json');
							}
						});
					 }
				},'json');
			}
			// ��ӡģ��
			function dyRyzsCommon(csdm) {
				 var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
				 } else {
					 var ids = jQuery("#dataTable").getSeletIds();
					 jQuery.post("bzjl_bzjg.do?method=dyRyzs",{csdm:csdm, value:ids+""},function(data){
							 var csz = data["csz"];
							 if(csz == null){
								 showAlertDivLayer("ģ��δ��ʼ����");
								 return false;
							 }else{
								// ��ʼ�����
								var LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
								LODOP.PRINT_INIT("��ӡ");
								// ����ģ��Ԫ��
								eval(csz);
								// ��ӡԤ��
								LODOP.PREVIEW();
							 }
						},'json');
			     }
			}

		  var gridSetting1 = {
					caption:"���ý�������б�",
					pager:"pager",
					url:"bzjl_bzjg.do?method=viewBzjljgList&type=query",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
						{label:'����',name:'xm', index: 'xm',width:'10%'},
						{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
						{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						{label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						{label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
						{label:'��������',name:'pjzq', index: 'pjzq',width:'13%'},
						{label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'16%'},
						{label:'���',name:'xmje', index: 'xmje',width:'11%'}
					],
					sortname: "xn,xq,sqsj",
				 	sortorder: "desc"
				};
			
			jQuery(function(){

				gridSetting1["params"]=getSuperSearch();
				jQuery("#dataTable").initGrid(gridSetting1);
			});

			
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					 var ids = jQuery("#dataTable").getSeletIds();
					 post('bzjl_bzjg.do?method=getDjbZip', {value:ids});
				 } else {
					var url="bzjl_bzjg.do?method=getDjbWord&id="+rows[0]["id"];
					url = addSuperSearchParams(url);
					document.forms[0].action = url;
					document.forms[0].submit();
			     }
			}

			function post(URL, PARAMS) {        
			    var temp = document.createElement("form");        
			    temp.action = URL;        
			    temp.method = "post";        
			    temp.style.display = "none";        
			    for (var x in PARAMS) {        
			        var opt = document.createElement("textarea");        
			        opt.name = x;        
			        opt.value = PARAMS[x];        
			        // alert(opt.name)         
			        temp.appendChild(opt);        
			    }        
			    document.body.appendChild(temp);        
			    temp.submit();        
			    return temp;        
			}        

			function drxx(){
				toImportData("IMPORT_N100102");
				return false;
			}
			function drxxNew(){
				toImportDataNew("IMPORT_N100102");
				return false;
			}

			// ����֤�顢��ѧ���ӡ
			function ryzsJxjDy(dytype){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="bzjl_bzjg.do?method=getRyzsJxjZip&value="+ids+"&dytype="+dytype;
					window.open(url);
				 } else {
					var url="bzjl_bzjg.do?method=getRyzsJxjWord&id="+rows[0]["id"]+"&dytype="+dytype;
					window.open(url);
			     }
			}

			function checkSearch_10022(){
				var flag = true;
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				if(xn_num != "1"){
					alertError("��ѡ��һ��ѧ�꣡");
					flag = false;
				}
				return flag;
			}
			
			//�������ܱ�
			function getHzbPrint(){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				if(xn_num != "1"){
					alertError("��ѡ��һ��ѧ�꣡");
					return false;
				}
				var xmmc_num =  jQuery("a[name=a_name_xmmc]").length;
				if(xmmc_num != "1"){
					alertError("��ѡ��һ����Ŀ���ƣ�");
					return false;
				}
				setSearchTj();
				var url="bzjl_bzjg.do?method=getHzbPrint";
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			function fw(){
				showDialog("��������", 500, 200, "bzjl_bzjg.do?method=mdfb");
			}

			//��ѧ���˺Ż��ܱ�
			function jxjzhhzb(){
				var tjXnArray = jQuery("[name='tj_xn'].selectedValue");
				var tjXqArray = jQuery("[name='tj_xq'].selectedValue");
				if(tjXnArray.length != 1 || tjXqArray.length !=1){
					showAlertDivLayer("ѧ�ꡢѧ��ֻ�ܱ����ѡ��һ���� ");
					return false;
				}
				setSearchTj();//���ø߼���ѯ����
				var url = "bzjl_bzjg.do?method=jxjzhhzb";//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
		</script>
	</head>

	<body>
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;"	onmousedown ="showHelpDiv();" >ʹ�ð���</a>
			</p>
		</div>
		<html:form action="/bzjl_bzjg">
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" id="pjzq" name="pjzq" value="${pjzq}"/>
			<input type="hidden" id="xzdm" name="xzdm" value="${xzdm}"/>
			<!-- ��ʾ��Ϣ end-->
			<div id="div_help" class="prompt" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<span>
						����δ������������̶��񽱵�ѧ�������ڴ˴�ֱ�ӽ�������ά��
					</span>
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
			<!-- ��ʾ��Ϣ end-->
			
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
				<ul>
				<logic:equal name="writeAble" value="yes">	
				
					
						<li><a href="javascript:void(0);" onclick="add();" class="btn_zj">����</a></li>
						<li><a href="javascript:void(0);" onclick="update();" class="btn_xg">�޸�</a></li>
						<li><a href="javascript:void(0);" onclick="del();" class="btn_sc">ɾ��</a></li>						
						
						<%--<li><a href="#" onclick="drxxNew();return false;" class="btn_dr">����</a></li>--%>
				</logic:equal>

				<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
				

				</ul>
				</div>

				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>���ý������</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
