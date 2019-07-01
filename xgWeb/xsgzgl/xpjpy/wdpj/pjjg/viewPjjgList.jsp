<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/jquery/plugins/dataGrid/dataGrid.js"></script>
		<script type="text/javascript" src="xsgzgl/xpjpy/wdpj/pjjg/js/pjjg.js"></script>
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
			 	jQuery.post("xpj_pjjg.do?method=cxRyzs",{csdm:csdm},function(data){
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
								jQuery.post("xpj_pjjg.do?method=bcRyzs",{csdm:csdm, csz:csz},function(data){
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
					 jQuery.post("xpj_pjjg.do?method=dyRyzs",{csdm:csdm, value:ids+""},function(data){
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
					caption:"��������б�",
					pager:"pager",
					url:"xpj_pjjg.do?method=viewPjjgList&type=query&xzdm=${xzdm}",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
						{label:'����',name:'xm', index: 'xm',width:'10%'},
						{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
						{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						{label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						<logic:equal name="xxdm" value="10466">
		   					{label:'ѧԺ',name:'xymc',index:'xymc',width:'13%'},
		   				</logic:equal>
                        {label:'��Ժ',name:'symc', index: 'sydm',width:'13%'},
						{label:'�����༶',name:'bjmc', index: 'bjdm',width:'13%'},
                        {label:'רҵ�༶',name:'zybjmc', index: 'zybjmc',width:'13%'},
						<logic:equal name="xxdm" value="10466">
							{label:'�����',name:'qsh',index:'qsh',width:'10%'},
						</logic:equal>
						<logic:equal name="xxdm" value="10264">
				   			{label:'����Ǹ���',name:'sfwtgg',index:'sfwtgg',width:'13%'},
				   		</logic:equal>
						{label:'��������',name:'pjzq', index: 'pjzq',width:'13%'},
						{label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'16%'},
						{label:'���',name:'xmje', index: 'xmje',width:'11%'}
					],
					sortname: "xn,xq,sqsj",
				 	sortorder: "desc"
				};
		  var gridSetting2 = {
					caption:"��������б�",
					pager:"pager",
					url:"xpj_pjjg.do?method=viewPjjgList&type=query",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
						{label:'����',name:'xm', index: 'xm',width:'10%'},
						{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
						{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						{label:'���ڴ��',name:'ddmc', index: 'ddmc'},
						{label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						{label:'�༶',name:'bjmc', index: 'bjdm',width:'28%'},
						{label:'��������',name:'pjzq', index: 'pjzq',width:'13%'},
						{label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'16%'},
						{label:'���',name:'xmje', index: 'xmje',width:'11%'}
					],
					sortname: "xn,xq,sqsj",
				 	sortorder: "desc"
				};
			
		  var gridSetting3 = {
					caption:"��������б�",
					pager:"pager",
					url:"xpj_pjjg.do?method=viewPjjgList&type=query",
					colList:[
						{label:'key',name:'id', index: 'id',key:true ,hidden:true},
						{label:'ѧ��',name:'xh', index: 'xh',width:'13%',formatter:xhLink },
						{label:'����',name:'xm', index: 'xm',width:'10%'},
						{label:'�Ա�',name:'xb', index: 'xb',width:'3%'},
						{label:'sjly',name:'sjly', index: 'sjly',hidden:true},
						{label:'�꼶',name:'nj', index: 'nj',width:'7%'},
						{label:'�༶',name:'bjmc', index: 'bjdm',width:'28%'},
						<logic:equal name="xxdm" value="10264">
				   			{label:'����Ǹ���',name:'sfwtgg',index:'sfwtgg',width:'13%'},
				   		</logic:equal>
						{label:'��������',name:'pjzq', index: 'pjzq',width:'13%'},
						{label:'��Ŀ����',name:'xmmc', index: 'xmmc',width:'16%'},
						{label:'���',name:'ylzd1', index: 'ylzd1',width:'11%'}
					],
					sortname: "xn,xq,sqsj",
				 	sortorder: "desc"
				};
			
			
			jQuery(function(){
				var xxdm = jQuery("#xxdm").val();
				if(xxdm=="11483"){
					gridSetting2["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting2);
				} else if(xxdm=="11488"){
					gridSetting3["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting3);
				}else {
					gridSetting1["params"]=getSuperSearch();
					jQuery("#dataTable").initGrid(gridSetting1);
				}
			});

			
			function getWord(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
				 } else if (rows.length > 1){
					 var ids = jQuery("#dataTable").getSeletIds();
					 post('xpj_pjjg.do?method=getDjbZip', {value:ids});
				 } else {
					var url="xpj_pjjg.do?method=getDjbWord&id="+rows[0]["id"];
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
					var url="xpj_pjjg.do?method=getRyzsJxjZip&value="+ids+"&dytype="+dytype;
					window.open(url);
				 } else {
					var url="xpj_pjjg.do?method=getRyzsJxjWord&id="+rows[0]["id"]+"&dytype="+dytype;
					window.open(url);
			     }
			}
			// �����Ƽ�ʦ����ѧ ����֤�顢��ѧ���ӡ
			function ryzsJxjDy_11318(dytype_11318){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
				 } else if (rows.length > 1){
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjjg.do?method=getRyzsJxjZip_11318&value="+ids+"&dytype_11318="+dytype_11318;
					window.open(url);
				 } else {
					var url="xpj_pjjg.do?method=getRyzsJxjWord_11318&id="+rows[0]["id"]+"&dytype_11318="+dytype_11318;
					window.open(url);
			     }
			}
			// �������㽱ѧ���㽭��ѧ��
			function scyxjxj(){
				var tips = loading("�����������㽱ѧ��,�����ĵȴ�");
				try{ // ����������ӣ��ر����Ӵ��ڣ����ύʱ�ȴ����޷��ر�
					tips.show();
				}catch(e){
				}
				jQuery.post("xpj_pjjg.do?method=scyxjxj",{},function(data){
					setTimeout(function(){
						tips.close();
						showAlertDivLayer(data["message"]);
						jQuery("#dataTable").reloadGrid();
					}, 30000);
				},'json');
			}

			// �Ϻ������ѧ֤����ӡ
			function ryzsJxjDy_10264(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
				 } else {
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjjg.do?method=ryzsJxjDy_10264&value="+ids;
					window.open(url);
				 } 
			}
			//===������ҵ��ѧ�����ǼǱ���Ի�begin===
			function getPjdjb() {
				var rows = jQuery("#dataTable").getSeletRow();
				var ids = '';
				var user = jQuery("#userType").val();
				if(user != "stu"){
					if(checkSearch_10022()){
						var xn = jQuery("a[name=a_name_xn]").eq(0).attr("id").replace("a_id_","");
						if (rows.length == 1) {
							var url = "xpj_pjjg.do?method=getPjdjb";					
							var xn = jQuery("a[name=a_name_xn]").attr("id").replace("a_id_","");
							url += "&xh=" + rows[0]["xh"];
							url += "&xn=" + xn;
							window.open(url);
						} else if (rows.length == 0) {
							showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
							return false;
						} else {
							for(var i=0;i<rows.length;i++){
								ids = ids+rows[i]['xh']+',';
							}
							var url = "xpj_pjjg.do?method=getPjdjbZip&value="+ids;
							url += "&xn=" + xn;
							window.open(url);
						}
					}
				}else{
					if (rows.length == 1) {
						var url = "xpj_pjjg.do?method=getPjdjb&xn="+rows[0]["pjzq"].substr(0,9)
						url += "&xh=" + rows[0]["xh"];
						window.open(url);
					} else{
						showAlertDivLayer("��ѡ��һ����Ҫ��ӡ�ļ�¼��");
					}
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

			//ɽ��������ҽְҵѧԺ����άѧ����ܱ�
			function getshjxjHzexcel(){
				setSearchTj();
				var xmlb = '';
				jQuery("a[name='tj_xxmlx'].selectedValue").each(function(){
					xmlb = xmlb+jQuery(this).text()+',';
				})
				var value = xmlb.split(",");
				if(value.length != 2  ){
					showAlertDivLayer("��ѡ��һ����Ŀ���ͣ���ȷ�ϣ�");
					return false;
				}
				var url="xpj_pjjg.do?method=getSdxm_shjxjhzexcel&value="+xmlb+"&path=pj_pjjg.do";
				addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}

			//ɽ��������ҽְҵѧԺ��ʡ��־��ѧ����ܱ�
			function getslzjxjHzexcel(){
				var rows = jQuery("#dataTable").getSeletRow();
				
				 if (rows.length == 0){
					showAlertDivLayer("��ѡ����Ҫ��ӡ�ļ�¼��");
				 } else {
					var ids = jQuery("#dataTable").getSeletIds();
					var url="xpj_pjjg.do?method=getSdxm_slzjhzexcel&value="+ids;
					window.open(url);
				 } 
			}



			//ɽ�������Ƽ����ӡ
			function getSdxmTjb(){
			 	setSearchTj();
				var xmmc = '';
				jQuery("a[name='a_xmmc_mc'].selectedValue").each(function(){
					xmmc = xmmc+jQuery(this).text()+',';
				})
				var value = xmmc.split(",");
				if(value.length != 2  ){
					showAlertDivLayer("��ѡ��һ����Ŀ���ƣ���ȷ�ϣ�");
					return false;
				}
				var url="xpj_pjjg.do?method=getSdxmTjb&value="+xmmc+"&path=pj_pjjg.do";
				addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
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
				var url="xpj_pjjg.do?method=getHzbPrint";
				url = addSuperSearchParams(url);
				document.forms[0].action = url;
				document.forms[0].submit();
			}
			function fw(){
				showDialog("��������", 500, 200, "xpj_pjjg.do?method=mdfb");
			}

			//�������ᵼ�� (�Ͼ��ߵ�ְҵ����ѧԺ)
			function bzmcdc(){
				setSearchTj();
				var xn_num = jQuery("a[name=a_name_xn]").length;
				var xq_num = jQuery("a[name=a_name_xq]").length;
				//var xy_num = jQuery("a[name=a_name_xy]").length;

				if(xn_num != 1){
					showAlertDivLayer("����ѡ��һ��ѧ�꣬��ֻ��ѡ��һ����");
				}else if (xq_num != 1){
					showAlertDivLayer("����ѡ��һ��ѧ�ڣ���ֻ��ѡ��һ���� ");
				}
<%--				else if (Number(xy_num) > 1){--%>
<%--					showAlertDivLayer("ֻ��ѡ��һ��ѧԺ���б������������� ");--%>
<%--				}--%>
				else{
					var flg = true;
					var yzUrl = 'xpj_pjjg.do?method=yzbzmddc';
					jQuery("form").eq(0).attr("id","pjjgForm");
					
					jQuery.ajaxSetup({async:false});
						ajaxSubFormWithFun("pjjgForm", yzUrl, function(data) {
							if(data["result"] != true){
								flg = false;
							}
						});
						if(!flg){
							showAlertDivLayer("�ޱ����������Ե�����������ѡ��ѧԺ���ˣ�");
							return false;
						}
					jQuery.ajaxSetup({async:true});
					
					var url = "xpj_pjjg.do?method=bzmddc";
					url = addSuperSearchParams(url);//���ø߼���ѯ����	
					jQuery("form").eq(0).attr("action", url);
					jQuery("form").eq(0).submit();
				}
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
				var url = "xpj_pjjg.do?method=jxjzhhzb";//dcclbh,�������ܱ��
				url = addSuperSearchParams(url);//���ø߼���ѯ����
				jQuery("form").eq(0).attr("action", url);
				jQuery("form").eq(0).submit();
			}
			// ��������ְҵ����ѧԺ�����ñ��ӡ
			function cwyb(){
					//var url ="rcsw_rcxwwh_rcxwjggl.do?method=sxpdcjhzDc";
					var url="xpj_pjjg.do?method=cwybPrint";
					
					var xnLength=jQuery("a[name=a_name_xn]").length;
					var xmmcLength=jQuery("a[name=a_name_xmmc]").length;
					
					if(xnLength != "1"){
						showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
						return false;
					}
					if(xmmcLength < "1"){
						showAlertDivLayer("������ѡ��һ����Ŀ���Ʋ�ѯ������");
						return false;
					}
					setSearchTj();
					url = addSuperSearchParams(url);
					document.forms[0].action = url;
					document.forms[0].submit();
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
		<html:form action="/xpj_pjjg">
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
						
						<li><a href="#" onclick="drxxNew();return false;" class="btn_dr">����</a></li>
				</logic:equal>
				<logic:equal value="00000" name="xxdm">
					<li><a href="#" onclick="fw();return false;" class="btn_xg">����������ʾ</a></li>
				</logic:equal>
				
				<li><a href="#" class="btn_dc" onclick="exportConfig();return false;">����</a></li>
				
				<logic:equal value="10279" name="xxdm">
				<li><a href="#" class="btn_dc" onclick="getHzbPrint();return false;">�������ܱ�</a></li>
				</logic:equal>
				<!--���ɹŵ���-->
				<logic:equal value="12673" name="xxdm">
					<li><a href="javascript:void(0);" onclick="jxjzhhzb()" class="btn_dy">�񽱻��ܵ���</a></li>
				</logic:equal>
				<!-- �㽭��ѧ����ʾ���صȼ��� -->
				<logic:notEqual value="10335" name="xxdm">
				<logic:notEqual value="10022" name="xxdm">
					<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>	
				</logic:notEqual>
				</logic:notEqual>
				<!-- 10022������ҵ��ѧ���Ի� -->
				<logic:equal name="xxdm" value="10022">
				<logic:notEqual value="stu" name="userType">
					<li><a href="javascript:void(0);" onclick="getWord();return false;" class="btn_down">���صǼǱ�</a></li>	
				</logic:notEqual>
				<li><a href="javascript:void(0);" onclick="getPjdjb();return false;" class="btn_down">���ŵǼǱ�</a></li>
				</logic:equal>
				<!-- �����Ƽ�ʦ����ѧ begin -->
				<logic:equal name="xxdm" value="11318">	
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy_11318('ryzs');return false;" class="btn_dy">��ӡ����֤��</a></li>
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy_11318('jxj');return false;" class="btn_dy">��ӡ��ѧ��</a></li>
				</logic:equal>
				<!-- �����Ƽ�ʦ����ѧ end -->
				<!-- ���칤�̴�ѧ begin -->
				<logic:equal name="xxdm" value="11799">	
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy('ryzs');return false;" class="btn_dy">��ӡ����֤��</a></li>
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy('jxj');return false;" class="btn_dy">��ӡ��ѧ��</a></li>
				</logic:equal>
				<!-- ���칤�̴�ѧ end -->
				
				<!-- �Ϻ������ѧ begin -->
				<logic:equal name="xxdm" value="10264">	
					<li><a href="javascript:void(0);" onclick="ryzsJxjDy_10264();return false;" class="btn_dy">֤����ӡ</a></li>
				</logic:equal>
				<!-- �Ϻ������ѧ end -->
				<!-- ��������ְҵ����ѧԺ-begin -->
				<logic:equal name="xxdm" value="12688">	
					<li><a href="javascript:void(0);" onclick="cwyb();return false;" class="btn_dy">�����ñ��ӡ</a></li>
				</logic:equal>
				<!-- ��������ְҵ����ѧԺ end -->
				
				<!-- ����֤�� begin -->
				<logic:equal name="ryzsFlag" value="true">	
                <%--<li><a href="javascript:void(0);" onclick="szRyzsCommon('ryzs_${xxdm }');return false;" class="btn_dy">��������֤�飨�������ã�</a></li>--%>
					<li><a href="javascript:void(0);" onclick="dyRyzsCommon('ryzs_${xxdm }');return false;" class="btn_dy">��ӡ����֤��</a></li>
				</logic:equal>
				<!-- ����֤�� end -->
				<!-- �������㽱ѧ���㽭��ѧ�� begin -->
				<logic:equal name="xxdm" value="10335">
					<logic:equal value="true" name="cssz" property="kgzt">
						<logic:equal value="xy" name="userType">
							<li><a href="javascript:void(0);" onclick="scyxjxj();return false;" class="btn_tj">�������㽱ѧ��</a></li>
						</logic:equal>
					</logic:equal>
				</logic:equal>
				<!-- �������㽱ѧ���㽭��ѧ�� end -->
				<logic:equal name="writeAble" value="yes">	
					<li><a href="javascript:void(0);" onclick="hjwhDr();return false;" class="btn_dr">���ĺŵ���</a></li>
				</logic:equal>
				<!-- �Ͼ��ߵ�ְҵ����ѧԺ �������ᵼ�� -->
				<logic:equal value="10874" name="xxdm">
					<li><a href="javascript:void(0);" onclick="bzmcdc();return false;" class="btn_dc">�������ᵼ��</a></li>
				</logic:equal>
				</ul>
				</div>
				<!-- ɽ��������ҽ begin -->
				<logic:equal name="xxdm" value="12947">	
				<div>
					<ul class="buttonbox">
							<li><a href="javascript:void(0);" onclick="getSdxmTjb();return false;" class="btn_dy">�Ƽ����ӡ</a></li>
							<%-- 
							<li><a href="javascript:void(0);" onclick="getYxXsTjb();return false;" class="btn_dy">����ѧ���Ƽ����ӡ</a></li>
							<li><a href="javascript:void(0);" onclick="getYxXsGbTjb();return false;" class="btn_dy">����ѧ���ɲ��Ƽ����ӡ</a></li>
							--%>
							<li><a href="javascript:void(0);" onclick="getshjxjHzexcel();return false;" class="btn_dc">��άѧ����ܵ���</a></li>
							<%-- 
							<li><a href="javascript:void(0);" onclick="getslzjxjHzexcel();return false;" class="btn_dc">ʡ��־��ѧ����ܵ���</a></li>--%>
					</ul>
				</div>
				</logic:equal>
				<!-- ɽ��������ҽ end -->
				
			
				<!--���ɹŵ���end -->
				
				
				<!-- �������� -->	
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			
		</html:form>
		
		<div class="main_box">
			<h3 class=datetitle_01>
				<span>�������</span>
			</h3>
			<div class="con_overlfow">
				<table id="dataTable" ></table>
				<div id="pager"></div>
			</div>
		</div>
	</body>
</html>
