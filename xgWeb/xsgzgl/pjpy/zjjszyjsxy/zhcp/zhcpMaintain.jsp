<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	    <script language="javascript" src="js/check.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		//��ʼ��
		function onShow(){ 
			var id = "left_a_0";
			if($(id)){
				$(id).click();
			}
			
			
		}
		
		//��ť����
		function controlBtn(){
		
			var xmdm = $("shxm").value;
			
			if(xmdm == "zd1"){
				if($("li_js")){//����
					$("li_js").style.display="";
				}
				if($("li_sz")){//����
					$("li_sz").style.display="";
				}
				if($("li_tj")){//ͳ��
					$("li_tj").style.display="";
				}
				
				if($("li_bc")){//����
					$("li_bc").style.display="none";
				}
				if($("li_tij")){//�ύ
					$("li_tij").style.display="none";
				}
				if($("li_dc")){//����
					$("li_dc").style.display="none";
				}
				if($("li_dr")){//����
					$("li_dr").style.display="none";
				}
			}else{
				if($("li_js")){//����
					$("li_js").style.display="none";
				}
				if($("li_sz")){//����
					$("li_sz").style.display="none";
				}
				if($("li_tj")){//ͳ��
					$("li_tj").style.display="none";
				}
				
				if($("li_bc")){//����
					$("li_bc").style.display="";
				}
				if($("li_tij")){//�ύ
					$("li_tij").style.display="";
				}
				if($("li_dc")){//����
					$("li_dc").style.display="";
				}
				if($("li_dr")){//����
					$("li_dr").style.display="";
				}
			}
		}
		
		//��ѯ�����
		function searchRs(){
			//��ť����
			controlBtn();
			
			var url = "general_zhcp_ajax.do?method=getZhcpInfoList";
			var czxm = $("czxm").value;
			var ie = "ie";
			
			var otherValue = [czxm,ie];

			//showWaitingDiv("1000");
			
			if(checkSearch()){
				searchRsByAjax(url,otherValue);
				$("is_default").value = "no";
			}
		}
		
		//����ɷ��ѯ
		function checkSearch(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;
				
				if(xn_num != "1"){
					alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
					flag = false;
				}else if( xq_num != "1"){
					alertError("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
					flag = false;
				}
			}
			return flag;
		}
		
		//�����ۺϲ�����
		function saveZhcpf(tag){
			
			if(tag == "ok"){
				var czxm = $("czxm").value;
				
				var url="zjjs_zhcp_ajax.do?method=";
					
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//ѧ��
				var i = 0;
				var xn = new Array();   
				jQuery("a[name=a_name_xn]").each(function(){
					var id = jQuery(this).attr("id");
					xn[i] = id.replace("a_id_","");
					i++;
				});
				
				//ѧ��
				i = 0;
				var xq = new Array();   
				jQuery("a[name=a_name_xq]").each(function(){
					var id = jQuery(this).attr("id");
					xq[i] = id.replace("a_id_","");
					i++;
				});
				
				//ѧ��
				i = 0;
				var xh = new Array();   				  
				jQuery("input[name=input_xh]").each(function(){xh[i] = jQuery(this).val();i++;});
				
				//�����
				i = 0;
				var dyf = new Array();   				  
				jQuery("input[name=input_dyf]").each(function(){
					if(jQuery(this).val()!=""){
						dyf[i] = jQuery(this).val();
					}else{
						dyf[i] = " ";
					}
					i++;
				});
				
				//�ӷ�
				i = 0;
				var addf = new Array();   				  
				jQuery("input[name=input_addf]").each(function(){
					if(jQuery(this).val()!=""){
						addf[i] = jQuery(this).val();
					}else{
						addf[i] = " ";
					}
					i++;
				});
				
				//����
				i = 0;
				var decf = new Array();   				  
				jQuery("input[name=input_decf]").each(function(){
					if(jQuery(this).val()!=""){
						decf[i] = jQuery(this).val();
					}else{
						decf[i] = " ";
					}
					i++;
				});
				
				//ԭ��
				i = 0;
				var yy = new Array();   				  
				jQuery("input[name=input_yy]").each(function(){
					if(jQuery(this).val()!=""){
						yy[i] = escape(jQuery(this).val());
					}else{
						yy[i] = " ";
					}
					i++;
				});
				
				//���ܷ�
				i = 0;
				var cpf = new Array();   				  
				jQuery("input[name=input_cpf]").each(function(){
					if(jQuery(this).val()!=""){
						cpf[i] = jQuery(this).val();
					}else{
						cpf[i] = " ";
					}
					i++;
				});
				//ISEDIT
				i = 0;
				var isEditArr = new Array();   				  
				jQuery("input[name=isEditArr]").each(function(){
					if(jQuery(this).val()!=""){
						isEditArr[i] = jQuery(this).val();
					}else{
						isEditArr[i] = " ";
					}
					i++;
				});
				
				//����
			 	var parameter;
				
				if(czxm == "zd2"){//������
				
					url+="saveDyf";
					
					//����
				 	var parameter = {
						"xn":xn.join("!!@@!!"),
						"xq":xq.join("!!@@!!"),
						"xh":xh.join("!!@@!!"),
						"dyf":dyf.join("!!@@!!")
					};
				
				}else if(czxm == "zd3"){//������
					url+="saveZyf";
					
					//����
				 	var parameter = {
						"xn":xn.join("!!@@!!"),
						"xq":xq.join("!!@@!!"),
						"xh":xh.join("!!@@!!"),
						"addf":addf.join("!!@@!!"),
						"decf":decf.join("!!@@!!"),
						"yy":yy.join("!!@@!!"),
						"isEditArr":isEditArr.join("!!@@!!")
					};
					
				}else if(czxm == "zd4"){//������
				
					url+="saveTyf";
					
					//����
				 	var parameter = {
						"xn":xn.join("!!@@!!"),
						"xq":xq.join("!!@@!!"),
						"xh":xh.join("!!@@!!"),
						"cpf":cpf.join("!!@@!!")
					};
				}
				
				if(checkSearch()){
					jQuery.post(url,parameter,function(result){
						$("had_edit").value = "no";
						alertInfo(result);
						searchRs();
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
					});
				}
			}else{
				$("had_edit").value = "no";
			}
		}
		
		//�����ۺϲ�����
		function submitZhcpf(tag){
			
			if(tag == "ok"){
			
				setSearchTj();
				
				var czxm = $("czxm").value;
				
				var url="zjjs_zhcp_ajax.do?method=submitZhcpf";
				
				//ģ����ѯ
				var input_mhcx = "";
				if($("input_mhcx")){
					input_mhcx = $("input_mhcx").value;
				}
				
				var mhcx_lx = "";
				if($("mhcx_lx")){
					mhcx_lx = $("mhcx_lx").value;
				}
			
				//�����ѯ
				var searchLx = new Array();
				var searchTj = new Array();
				var searchTjz = new Array();
				
				var n=0;
				var m=3;
				
				searchLx[0]="xy";
				searchLx[1]="zy";
				searchLx[2]="bj";
				
				for(var i=0;i<jytj.length;i++){
					searchLx[m]=jytj[i];
					m++;
				}
			
				var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
					
				for(var j=0;j<tj_num;j++){
					var obj = $("searchTjDiv").getElementsByTagName('input')[j];
					searchTj[n]=obj.name;
					searchTjz[n]=escape(obj.value);
					n++;
				}
					
				//��������
			 	var parameter = {
			 		"czxm":czxm,
					"searchTj":searchTj.join("!!@@!!"),
					"searchTjz":searchTjz.join("!!@@!!"),
					"searchLx":searchLx.join("!!@@!!")
				};			
					
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
							
				jQuery.post(url,parameter,function(result){
					alertInfo(result);
					searchRs();
					$("divWaiting").style.display="none";
					$("divDisable").style.display="none";
				});
			}
		}
		
		//����Ƿ��޸�
		function saveMethod(){
			confirmInfo('���Ѿ��޸�����ط����������Ƿ���Ҫ���棿',saveZhcpf);
		}
		
		//��ʾ�����޸�Div
		function showFsxgDiv(xh,xgf,lx){	
			
			if(xgf!="" && xgf!=null){
				$("input_xgf").value = xgf;
			}else{
				$("input_xgf").value = "";
			}
			
			if(xh!="" && xh!=null){
				$("input_xgf_xh").value = xh;
			}else{
				$("input_xgf_xh").value = "";
			}
	
			if(lx!="" && lx!=null){
				$("input_xgf_lx").value = lx;
			}else{
				$("input_xgf_lx").value = "";
			}
			
			tipsWindown("ϵͳ��ʾ","id:div_xgf","350","150","true","","true","id");
		}
		
		//�����޸ķ�
		function saveXgf(tag){
		
			var xgf = $("input_xgf").value;
			var xgf_xh = $("input_xgf_xh").value;
			var lx = $("input_xgf_lx").value;

			if(xgf == ""){
				alertError("�޸ķֲ���Ϊ��");
				return false;
			}
			
			if(tag == "ok"){
				
				if(checkSearch()){
					var url;
					var parameter;
					
					//ѧ��
					var i = 0;
					var xn = new Array();   
					jQuery("a[name=a_name_xn]").each(function(){
						var id = jQuery(this).attr("id");
						xn[i] = id.replace("a_id_","");
						i++;
					});
					
					//ѧ��
					i = 0;
					var xq = new Array();   
					jQuery("a[name=a_name_xq]").each(function(){
						var id = jQuery(this).attr("id");
						xq[i] = id.replace("a_id_","");
						i++;
					});
					
					url="zjjs_zhcp_ajax.do?method=saveXgf";
				
					//����
				 	parameter = {
				 		"xn":xn[0],
				 		"xq":xq[0],
						"xh":xgf_xh,
						"xgf":xgf,
						"czxm":lx
					};
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					jQuery.post(url,parameter,function(result){
						alertInfo(result);
						searchRs();
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						closeWindown();
					});	
				}
			}
		}
		
		//��ʾԭ��Div
		function showYyDiv(xh,yy,sftj){	
			
			if(xh!="" && xh!=null){
				$("input_yy_xh").value = xh;
			}else{
				$("input_yy_xh").value = "";
			}

			if(yy!="" && yy!=null){
				jQuery("#textarea_yy").text(yy);
			}else{
				$("textarea_yy").value = "";
			}
			
			var id = "input_yy_"+xh;
			if($(id).value != ""){
				$("textarea_yy").value = $(id).value;
			}

			if(sftj == "yes"){
				$("btn_bc_yy").style.display="none";
			}else{
				$("btn_bc_yy").style.display="";
			}
			
			tipsWindown("ϵͳ��ʾ","id:div_yy","350","210","true","","true","id");
		}
		
		//����ԭ��
		function saveYy(){
		
			setHadEdit();
			
			var xh = $("input_yy_xh").value;
			var yy = $("textarea_yy").value;
			
			var id = "input_yy_"+xh;
			
			if(yy != ""){
				$(id).value = yy;
			}else{
				$(id).value = "";
			}
			
			closeWindown();
		}

		function mbsc(){
			setSearchTj();
				
			var czxm = jQuery("#shxm").val();
			var url="zjjs_zhcp_ajax.do?method=submitZhcpf";
				
			//�����ѯ
			var searchLx = ['xy','zy','bj'];
			var searchTj = new Array();
			var searchTjz = new Array();
				
			var n=0;
			var m=3;
				
			for(var i=0;i<jytj.length;i++){
				searchLx[m]=jytj[i];
				m++;
			}
			
			var tj_num = jQuery('input',jQuery('#searchTjDiv')).length;
					
			for(var j=0;j<tj_num;j++){
				var obj = jQuery('input',jQuery('#searchTjDiv'))[j];
				searchTj[n]=jQuery(obj).attr('name');
				searchTjz[n]=escape(jQuery(obj).val());
				n++;
			}
					
			//��������
			var parameter = {
			 	"czxm":czxm,
				"searchTj":searchTj.join("!!@@!!"),
				"searchTjz":searchTjz.join("!!@@!!"),
				"searchLx":searchLx.join("!!@@!!")
			};		
			
			expData('zjjs_zhcp_ajax.do?method=mbsc&czxm='+parameter.czxm+'&searchTj='+parameter.searchTj+'&searchTjz='+parameter.searchTjz+'&searchLx='+parameter.searchLx);
		}

		
		// ----------------------made by qlj begin----------------
		
		//-----------------------�۲���Ϣͳ��----------------------
		function zcxxtj(){
		
			var bjlen=jQuery("a[name=a_name_bj]").length;
			var xn_num =  jQuery("a[name=a_name_xn]").length;
			var xq_num =  jQuery("a[name=a_name_xq]").length;
				
			
			if(bjlen!=1){
				alertInfo("��ѡ��һ���༶����ͳ��!",function(t){
					if(t=="ok"){
						return false;
					}
				});
			}else if(xn_num != "1"){
				
				alertInfo("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
				flag = false;
			
			}else if( xq_num != "1"){
				
				alertInfo("ѧ����������Ϊ�գ���ֻ��ѡ��һ����");
				flag = false;
			
			}else{

				setSearchTj();
				
				//�����ѯ
				var searchLx = new Array();
				var searchTj = new Array();
				var searchTjz = new Array();
				
				var n=0;
				var m=3;
				
				searchLx[0]="xy";
				searchLx[1]="zy";
				searchLx[2]="bj";
				
				for(var i=0;i<jytj.length;i++){
					searchLx[m]=jytj[i];
					m++;
				}
			
				var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
					
				for(var j=0;j<tj_num;j++){
					var obj = $("searchTjDiv").getElementsByTagName('input')[j];
					searchTj[n]=obj.name;
					searchTjz[n]=escape(obj.value);
					n++;
				}
					
				//��������
			 	var parameter = {
					"searchTj":searchTj.join("!!@@!!"),
					"searchTjz":searchTjz.join("!!@@!!"),
					"searchLx":searchLx.join("!!@@!!")
				};			
				var url='/xgxt/zjjs_zhcp_ajax.do?method=printZhtj';
				url+='&searchTj='+parameter.searchTj;
				url+='&searchTjz='+parameter.searchTjz;
				url+='&searchLx='+parameter.searchLx;
				
				expData(url);
			}
		}
		//-----------------------�۲���Ϣͳ�� ----------------------
		
		// -------------------��ʾ�۲������Ϣ�б� begin---------------------
		function showZcblDiv(){
			
			jQuery.ajaxSetup({async:false});
			var url="/xgxt/zjjs_zhcp_ajax.do?method=showZcblInfo";
			var html="";
			$("div_blsz_blxx").innerHTML="";
			
			jQuery.post(url,function(result){
				var data=eval(result);
				html+="<table align='center' class='formlist'>";
				html+="<thead><tr><th colspan='2'><span>�۲��������</span></th></tr></thead>";
				html+="<tbody>";
				for(var i=0;i<data.length;i++){
					var trHtml="<tr>";
					trHtml+="<th>";
					trHtml+="<input type='hidden' name='xmdmArr' id='xmdm_"+i+"' value='"+data[i].xmdm+"' />"
					trHtml+="<input type='hidden' name='bldmArr' id='bldm_"+i+"' value='"+data[i].bldm+"' />";
					trHtml+="<input type='hidden' name='xmmcArr' id='xmmc_"+i+"' value='"+data[i].xmmc+"' />";
					trHtml+=data[i].xmmc+data[i].blmc;	
					trHtml+="</th>";
					trHtml+="<td>";
					trHtml+="<input type='text' name='blArr' id='bl_"+i+"' value='"+data[i].bl+"' maxlength='10' onkeyup='checkInputNum(this)' style='ime-mode:disabled'/>%";	
					trHtml+="</td>";
					trHtml+="</tr>"
					html+=trHtml;	
				}
				html+="</tbody>";
				html+="<tfoot>";
				html+="<tr>";
				html+="<td colspan='2'>";
				html+="<div class='bz'>";
				html+="</div>";
				html+="<div class='btn'>";
				html+="<button type='button' id='btn_bc' onclick='saveZcbl()'>�� ��</button>";
				html+="<button type='button' id='btn_gb' onclick='closeWindown()'>�� ��</button>";
				html+="</div></td></tr></tfoot></table>";
			});
			
			$("div_blsz_blxx").innerHTML=html;
			tipsWindown("ϵͳ��ʾ","id:div_blsz","350","200","true","","true","id");
			jQuery.ajaxSetup({async:true});
			
		}
		
		function saveZcbl(){
			
			var url="/xgxt/zjjs_zhcp_ajax.do?method=saveZcbl";
			
			var xmdmArr=document.getElementsByName("xmdmArr");	
			var xmmcArr=document.getElementsByName("xmmcArr");					
			var blArr=document.getElementsByName("blArr");	
			var bldmArr=document.getElementsByName("bldmArr");	
			
			var xmArr=new Array();
			var blzArr=new Array();		
			var bldmzArr=new Array();
			
			var flag=true;
			
			var xmmc="";
			for(var i=0;i<xmdmArr.length;i++){
				xmArr[i]=xmdmArr[i].value;
				blzArr[i]=blArr[i].value;
				bldmzArr[i]=bldmArr[i].value;
				if(blzArr[i]==""){
					flag=false;
					xmmc=xmmcArr[i].value;
					break;
				}
			}
			
			//��������
		 	var parameter = {
				"xmdm":xmArr.join("!!@@!!"),
				"bldm":bldmzArr.join("!!@@!!"),
				"bl":blzArr.join("!!@@!!")
			};	
			
			if(flag){
				confirmInfo("ȷ��Ҫ�����۲������?",function(t){
					if(t=="ok"){
						jQuery.post(url,parameter,function(result){
							alertInfo(result);
							closeWindown();
						});	
					}
				});
			}else{
				alertInfo(xmmc+"������Ϊ��!",function(t){
					if(t=="ok"){
						
						return false;
					}
				});
			}
			
		}
	
		//�������ʱ��ѧ��ѧ��
		function checkXnXq(){
		
			var flag = true;
			var is_default = $("is_default").value;
			
			if(is_default!=""){
				var xn_num =  jQuery("a[name=a_name_xn]").length;
				var xq_num =  jQuery("a[name=a_name_xq]").length;

				if(xn_num != 1 && flag){
					alertInfo("�۲�ּ���ʱ��ѧ����������Ϊ�գ���ֻ��Ϊ��ǰ����ѧ�꣡");
					flag = false;
				}
				
				if( xq_num != 1 && flag){
					alertInfo("�۲�ּ���ʱ��ѧ����������Ϊ�գ���ֻ��Ϊ��ǰ����ѧ�ڣ�");
					flag = false;
				}
				
				if(flag){
					var xnid = jQuery("a[name=a_name_xn]")[0].id;
					var xqid = jQuery("a[name=a_name_xq]")[0].id;
					
					var xn_value =xnid.replace("a_id_","");
					var xq_value =xqid.replace("a_id_","");
					var dqxn=jQuery("#pjxn").val();
					var dqxq=jQuery("#pjxq").val();
					
					
					if(xn_value!=dqxn){
						alertInfo("�۲�ּ���ʱ������ѡ��ǰ����ѧ�꣡");
						flag = false;
					}
					
					if(xq_value!=dqxq && flag){
						alertInfo("�۲�ּ���ʱ������ѡ��ǰ����ѧ�ڣ�");
						flag = false;
					}
				}
				
			}
			return flag;
		}
		
		function zczfAccount(){
			
			var url="/xgxt/zjjs_zhcp_ajax.do?method=zczfAccount";
			
			if(checkXnXq()){
				
				confirmInfo("ȷ��Ҫ�����۲����?",function(t){
				
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					if(t=="ok"){
						setSearchTj();
					
					//�����ѯ
					var searchLx = new Array();
					var searchTj = new Array();
					var searchTjz = new Array();
					var jslx = new Array();
					var pmjs = new Array();
					
					var n=0;
					var m=3;
					
					searchLx[0]="xy";
					searchLx[1]="zy";
					searchLx[2]="bj";
					
					for(var i=0;i<jytj.length;i++){
						searchLx[m]=jytj[i];
						m++;
					}
				
					var tj_num = $("searchTjDiv").getElementsByTagName('input').length;
						
					for(var j=0;j<tj_num;j++){
						var obj = $("searchTjDiv").getElementsByTagName('input')[j];
						searchTj[n]=obj.name;
						searchTjz[n]=escape(obj.value);
						n++;
					}
					
					var jslxArr =document.getElementsByName("jslx");
					for(var i=0;i<jslxArr.length;i++){
						jslx[i]=jslxArr[i].value;
					}	
					
					var pmjsArr =document.getElementsByName("pmjs");
					for(var i=0;i<pmjsArr.length;i++){
						pmjs[i]=pmjsArr[i].value;
					}		
						
					//��������
				 	var parameter = {
						"searchTj":searchTj.join("!!@@!!"),
						"searchTjz":searchTjz.join("!!@@!!"),
						"searchLx":searchLx.join("!!@@!!"),
						"jslx":jslx.join("!!@@!!"),
						"pmjs":pmjs.join("!!@@!!")
					};			
						
						jQuery.post(url,parameter,function(result){
							alertInfo(result);
							$("divWaiting").style.display="none";
							$("divDisable").style.display="none";
							closeWindown();
							searchRs();
						});	
					}
				});
			}else{
				return false
			}
		}
		// -------------------��ʾ�۲������Ϣ�б� end---------------------
		function showPm(){
			//ѡ������
			
			if($("jslx_pm").checked){
				$("pmjs").style.display="";
			}else{
				$("pmjs").style.display="none";
			}
		}
		
		function showDiv(){
			tipsWindown("���㷽ʽѡ��","id:zcfjsDiv","350","170","true","","true","id");
		}
		// ----------------------made by qlj begin---------------------
		
		function setIsEdit(id){
			
			$(id).value="yes"
		}
		
		jQuery(function(){
			onShow();
		})
		</script>
	</head>
	<body  >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
				1.������Ĭ��չʾ���Ǳ�����ѧ��ѧ�ڵ����ݡ�</br>
				2.�ɸ���Ա�û�¼���������ִ�б���������ύ��<font color="blue">���ɽ����޸�</font>��</br>
				3.ѧУ�û����Բ鿴����Ա�ύ��ķ�����������Է��������޸ģ�������޸ģ����Ը���Ա<font color="blue">�ύΪ׼</font>��</br>
				4.���ݵ�����ṩѧУ�û�ʹ�ã�����ʱ����ʹ�ñ�����ģ���ṩ��<font color="blue">ģ��</font>��</br>
				5.�۲�ּ���������ʱ������ѡ���ѯ�������������ѡ��ѯ���������ѯ��Χ�ڵ����ݵ��۲�ֺ�������Ϣ��
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/general_pjpy" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- �Ƿ��ʼ��  -->
			<input type="hidden" name="is_default" id="is_default" value=""/>
			<!-- �Ƿ��޸� -->
			<input type="hidden" id="had_edit" value="no"/>
			<input type="hidden" name="czxm" id="shxm" value="${czxm }"/>
			<input type="hidden" name="pjxn" id="pjxn" value="${pjxn }"/>
			<input type="hidden" name="pjxq" id="pjxq" value="${pjxq }"/>
			
			<!-- ������ end-->
			
			<!-- �๦�ܲ����� -->
			<div class="toolbox">
				<!-- ��ť -->
				<div class="buttonbox">
					<ul>
						<!-- �ۺϷ� -->
						<logic:equal name="userType" value="admin">
							<li id="li_js">
								<a href="#" onclick="showDiv();return false;" class="btn_yl">
									�ۺϷּ���
								</a>
							</li>
							<li id="li_sz">
								<a href="#" onclick="showZcblDiv();return false;" class="btn_sz">
									��������
								</a>
							</li>
						</logic:equal>
						<li id="li_tj">
							<a href="#" onclick="zcxxtj();return false;" class="btn_tj">
								����ͳ�Ʊ���
							</a>
						</li>	
						<!-- �ۺϷ� end-->	
						
						<!-- ���� -->
						<logic:equal name="writeAble" value="yes">
							<logic:notEqual name="userType" value="admin">
								<li id="li_bc" style="display: none">
									<a href="#" onclick="confirmInfo('��Ҫ��������¼��ķ�������ȷ��',saveZhcpf);return false;" class="btn_ccg">
										����
									</a>
								</li>
								<li id="li_tij" style="display: none">
									<a href="#" onclick="confirmInfo('�ύ�󲻿��ٽ��з����޸ģ���ȷ���Ƿ��ύ��(�Թ���������ѡ��Ĳ���Ϊ׼)',submitZhcpf);return false;" class="btn_fs">
										�ύ
									</a>
								</li>
							</logic:notEqual>
						</logic:equal>
						
						<logic:equal name="userType" value="admin">
							<li id="li_dc" style="display: none">
								<a href="#" onclick="mbsc();return false;" class="btn_dc">
									ģ������
								</a>
							</li>
							<li id="li_dr" style="display: none">
								<a href="#" onclick="showTopWin('zjjs_zhcp.do?method=inZcf&czxm='+jQuery('#shxm').val(),'450','280');return false;" class="btn_dr">
									���ݵ���
								</a>
							</li>
						</logic:equal>
						<!-- ���� end-->	
					</ul>
				</div>
				<!-- ��ť end-->
		
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				<!-- �������� end-->
			</div>
			<!-- �๦�ܲ����� end-->
			
			<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<!-- From���� -->
				<table align="center" width="100%">
					<tr>
						<td colspan="2">
							<h3 class="datetitle_01">
								<span>
									
								</span>
							</h3>
						</td>
					</tr>
					<tr>
						<td width="15%" valign="top" style="overflow-x: auto;">
							<!-- ����� -->
							<div class="listbox">	
								<div class="titlelist" style="height: 352px;">
									<ul id="left_ul">
										<logic:notEmpty name="cshXmList">
											<logic:iterate id="xmnr" name="cshXmList" indexId="index">	
												<li id="li_${index}" class="Child">
													<a href="#" name="left_a" id="left_a_${index}" 
														onclick="if(checkHadEdit()){setLiClick('${index}');searchRs();};return false;">
														<span class="ico"></span>${xmnr.xmmc}
													</a>
													<input type="hidden" id="xmdm_${index }" value="${xmnr.xmdm}"/>
												</li>
											</logic:iterate>
										</logic:notEmpty>
									</ul>
								</div>
							</div>
							<!-- ����� end-->
						</td>
						<td width="85%" valign="top" style="position: relative;">
							<div id="div_rs" style="width:100%;height:360px;overflow-x:auto;overflow-y:auto;">
							</div>
						</td>
					</tr>
				</table>
				<!--��ҳ��ʾ-->
				<jsp:include flush="true" page="/sjcz/turnpage.jsp?form=generalPjpyGeneralForm"></jsp:include>
				<script type="text/javascript">
						$('choose').className="hide";
				</script>
				<!--��ҳ��ʾ-->
			</div>
			<!-- ������ʾ�� end-->
			
			<!-- ѧ�����ߵ����� -->
			<div id="div_xgf" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�޸���Ϣ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									�޸ķ�
								</th>
								<td>
									<input type="hidden" id="input_xgf_xh" value=""/>	
									<input type="hidden" id="input_xgf_lx" value=""/>	
									<input type="text" id="input_xgf" value="" 
										onkeyup="checkInputNum(this);"
										onblur="checkInputNum(this)" 
										maxlength="5"
										style="width : 50px;ime-mode:disabled;" 
									/>	
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="confirmInfo('���������޸ĺ�Ϊ׼����ȷ���Ƿ񱣴��޸ģ�',saveXgf)">
											�� ��
										</button>
										
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>



			<!-- ѧ�����ߵ����� -->
			<div id="div_yy" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�Ӽ���ԭ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th width="30%">
									ԭ��<br/>
									<font color="red"><��250��></font>
								</th>
								<td>
									<input type="hidden" id="input_yy_xh" value=""/>	
									<textarea id="textarea_yy" rows="5" cols="" 
										style="word-break:break-all;width:100%" onblur="chLeng(this,250)"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc_yy" onclick="saveYy()">
											ȷ ��
										</button>
										
										<button type="button" id="btn_gb_yy" onclick="closeWindown();return false;">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!--  made by (be sweet on master)  begin -->
			<div id="div_blsz" style="display:none">
				<div class="open_win01">
					<div id="div_blsz_blxx">
					
					</div>
				</div>
			</div>
			
			<!-- �ּܷ���ѡ��DIV -->
			<div id="zcfjsDiv" style="display: none">
				<div class="tab">
					<table class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span> �ۺ����ʲ����ּ��� </span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th>
									��������
								</th>
								<td>
									<input type="checkbox" name='jslx' id='jslx_zf' value='zfjs'
										checked />
									�۲�ּ���
									<input type="checkbox" name='jslx' id='jslx_pm' value='pmjs'
										onclick="showPm()" />
									�۲���������
								</td>
							</tr>
							<tr id="pmjs" style="display:none">
								<th>
									�۲����������
								</th>
								<td>
									<input type="checkbox" name='pmjs' id='pmjs_bj' value='bj'
										checked />�༶����
									<input type="checkbox" name='pmjs' id='pmjs_zy' value='njzy' />�꼶רҵ����
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="btn">
										<button type="button" onclick="zczfAccount()">
											ȷ ��
										</button>
										<button type="button" onclick="closeWindown();">
											�� ��
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			<!-- made by (be sweet on master)  end -->
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>