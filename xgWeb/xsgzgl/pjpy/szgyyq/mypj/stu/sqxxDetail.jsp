<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//ˢ��ҳ��
		function reflashSqxxDetail(){
			
			var xn = $("xn").value;
			var xq = $("xq").value;
			var xh = $("xh").value;
			var xmdm = $("xmdm").value;
			var doType = $("doType").value;
		
			var url = "szgyyq_mypj_stu.do?method=sqxxDetail";
				url+="&xn="+xn;
				url+="&xq="+xq;
				url+="&xh="+xh;
				url+="&xmdm="+xmdm;
				url+="&doType="+doType;
				
			refreshForm(url)
		}
		
		//��ʾѧ������Div
		function showXsssDiv(xmid,cz,ssnr,clyj,clrxm){
			$("xmid").value = xmid;
			
			if(cz == "����"){
				$("btn_bc").style.display = "";	
				$("tr_ssnr").style.display = "";
				$("tr_clr").style.display = "none";
				$("tr_clyj").style.display = "none";
				
				$("ssnr").value = "";
				$("clyj").value = "";
			}else if( cz == "�Ѵ���"){
				$("btn_bc").style.display = "none";
				$("tr_ssnr").style.display = "none";
				$("tr_clr").style.display = "";
				$("tr_clyj").style.display = "";
				
				$("p_clr").innerHTML = clrxm;
				$("ssnr").value = ssnr;
				$("clyj").value = clyj;
			}else if( cz == "������"){
				$("btn_bc").style.display = "none";
				$("tr_ssnr").style.display = "";
				$("tr_clr").style.display = "none";
				$("tr_clyj").style.display = "none";
				
				$("ssnr").value = ssnr;
				$("clyj").value = "";
			}
			
			tipsWindown("ϵͳ��ʾ","id:div_xsss","350","200","true","","true","id");
		}
		
		//������������
		function saveSsnr(tag){
		
			if(tag == "ok"){
				var tsnr = $("ssnr").value;
				
				if(tsnr == ""){
					alertError("�������ݲ���Ϊ�գ���ȷ�ϣ�");
					return false;
				}
				
				var url="szgyyq_mypj_stu.do?method=saveXsss";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				var xh = $("xh").value;
				var xmlx = $("xmdm").value;
				var xmid = $("xmid").value;
				var ssnr = $("ssnr").value;
				
				//����
			 	var parameter = {
					"xh":xh,
					"xmlx":xmlx,
					"xmid":xmid,
					"ssnr":escape(ssnr)
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
		}
		
		//ִ�гɹ�
		function dcSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			alertInfo(result,function(tag){if(tag == "ok"){$("btn_sx").click()}});
		}
		
		function showQtyy(qtyy){
			jQuery("#qtyy").val(qtyy);
			tipsWindown("ϵͳ��ʾ","id:div_qtyy","350","200","true","","true","id");
		}
		
		//��ʾ�޸�Div
		function checkKfcl(type){
		
			var num = jQuery("input[name=hid_id]",jQuery("#div_sqxx")).length;
			
			var flag = false;
			
			jQuery.ajaxSetup({async:false});
			
			for(var i=0;i<num;i++){
			
				var obj = jQuery("input[name=hid_id]",jQuery("#div_sqxx"))[i];
				
				if(obj.value != ""){
				
					var tr_id = "tr_"+obj.value;

					if($(tr_id).style.backgroundColor == "#ffdead"){
						flag = true;
						var sqid = obj.value;
						var xmdm = $("xmdm").value;
						$("sqid").value = sqid;
						
						var url="szgyyq_mypj_stu.do?method=checkKfcl";
				
						//����
					 	var parameter = {
					 		"xmdm":xmdm,
							"sqid":sqid
						};
						
						jQuery.post(url,parameter,function(result){
							if(result == "true"){
								if(type == "�޸�"){
									showEditDiv();
								}else{
									confirmInfo('���ȷ�ϣ�ɾ��������¼������ȷ��',deleteSqjl);
								}
							}else{
								alertError("���������¼�Ѿ�����˹��ˣ��޷�"+type+"����ȷ��");
								return false;
							}
						});
					}
				}
			}
			
			if(!flag){
				alertError("��ѡ����ϣ���޸ĵļ�¼");
				return false;
			}
			
			jQuery.ajaxSetup({async:true});
		}
		
		//��ʾ�޸�Div
		function deleteSqjl(tag){
		
			if(tag == "ok"){
				var url="szgyyq_mypj_stu.do?method=deleteSqjl";
				var xmdm = $("xmdm").value;
				var sqid = $("sqid").value;
				//����
			 	var parameter = {
			 		"xmdm":xmdm,
					"sqid":sqid
				};
				
				jQuery.post(url,parameter,function(result){
					alertInfo(result,function(){
						reflashFssqDetail();
					});
				});
			}
		}
		
		//ˢ��ҳ��
		function reflashFssqDetail(){
			
			var xn = $("xn").value;
			var xq = $("xq").value;
			var xh = $("xh").value;
			var xmdm = $("xmdm").value;
			
			var url = "/xgxt/szgyyq_mypj_stu.do?method=sqxxDetail";
				url+="&xn="+xn;
				url+="&xq="+xq;
				url+="&xh="+xh;
				url+="&xmdm="+xmdm;
				url+="&doType=edit";
				
			refreshForm(url)
		}
		
		//��ʾ�޸�Div
		function showEditDiv(){
		
			//��Ŀ����
			var xmdm = $("xmdm").value;
			//����ID
			var sqid = $("sqid").value;
			
			var html = "";
				html+="<div class=\"open_win01\">";
				html+="<table align=\"center\" class=\"formlist\">";
				html+="<thead>";
				html+="<tr>";
				html+="<th colspan=\"2\">";
				html+="<span>������Ϣ�޸�</span>";
				html+="</th>";
				html+="</tr>";
				html+="</thead>";
				
				if(xmdm == "szyq_dshdjzb"){//����
				
					var url = "szgyyq_mypj_stu.do?method=getDshdInfo";
			   			url+= "&sqid="+sqid;
   			
   					var dsmc = "";
   					var dsrq = "";
   					var dsxd = "";
   					var sfhj = "";
   					var sqf = "";
   					
					jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false,
						success:function(result){
							dsmc = result[0].dsmc;
		   					dsrq = result[0].dsrq;
		   					dsxd = result[0].dsxd;
		   					sfhj = result[0].sfhj;
		   					sqf = result[0].sqf;
						}
					});
					
					html+="<tr>";
					html+="<td width=\"30%\">����</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"dsmc\" id=\"dsmc_"+xmdm+"\" style=\"width:150px\" maxLength=\"40\" value=\""+dsmc+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">��������</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"dsrq\" id=\"dsrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('dsrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+dsrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td>�����ĵ�<bt/><font color=\"red\">(������150��)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"dsxd\" style='word-break:break-all;' id=\"dsxd_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+dsxd+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">�Ƿ��</td>";
					html+="<td width=\"\">";
					html+="<select name=\"sfhj\" id=\"sfhj_"+xmdm+"\"><option value=\"��\"";
					if(sfhj == "��"){
						html+="selected=\"selected\"";
					}
					html+=">��</option><option value=\"��\"";
					if(sfhj == "��"){
						html+="selected=\"selected\"";
					}
					html+=">��</option></select>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">����ӷ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";
				}else if(xmdm == "szyq_yybdjzb"){//���Ա��
				
					var url = "szgyyq_mypj_stu.do?method=getYybdInfo";
			   			url+= "&sqid="+sqid;
   			
   					var yybdnr = "";
   					var xthdrq = "";
   					var sqf = "";
   					
					jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false,
						success:function(result){
							yybdnr = result[0].yybdnr;
		   					xthdrq = result[0].xthdrq;
		   					sqf = result[0].sqf;
						}
					});
					
					html+="<tr>";
					html+="<td width=\"30%\">���Ա������<font color=\"red\">(������150��)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"yybdnr\" style='word-break:break-all;' id=\"yybdnr_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+yybdnr+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">����</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+xthdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">����ӷ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";
				}else if(xmdm == "szyq_ivtltb"){//IVT��̳
				
					var url = "szgyyq_mypj_stu.do?method=getIvtltInfo";
			   			url+= "&sqid="+sqid;
   			
   					var jztm = "";
   					var xthdrq = "";
   					var jcdj = "";
   					var ccdj = "";
   					var sqf = "";
   					
					jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false,
						success:function(result){
							jztm = result[0].jztm;
		   					xthdrq = result[0].xthdrq;
		   					jcdj = result[0].jcdj;
		   					ccdj = result[0].ccdj;
		   					sqf = result[0].sqf;
						}
					});

					html+="<tr>";
					html+="<td width=\"30%\">������Ŀ</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"jztm\" id=\"jztm_"+xmdm+"\" style=\"width:150px\" maxLength=\"60\" value=\""+jztm+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">����</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+xthdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">�����Ǽ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"jcdj\" id=\"jcdj_"+xmdm+"\" style=\"width:150px\" maxLength=\"40\" value=\""+jcdj+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">�����Ǽ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"ccdj\" id=\"ccdj_"+xmdm+"\" style=\"width:150px\" maxLength=\"40\" value=\""+ccdj+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">����ӷ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";

				}else if(xmdm == "szyq_xthddjb"){//����
				
					var url = "szgyyq_mypj_stu.do?method=getWthdInfo";
			   			url+= "&sqid="+sqid;
   			
   					var hdnr = "";
   					var xthdrq = "";
   					var jldj = "";
   					var sqf = "";
   					
					jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false,
						success:function(result){
							hdnr = result[0].hdnr;
		   					xthdrq = result[0].xthdrq;
		   					jldj = result[0].jldj;
		   					sqf = result[0].sqf;
						}
					});

					html+="<tr>";
					html+="<td width=\"30%\">�����<font color=\"red\">(������150��)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"hdnr\" style='word-break:break-all;' id=\"hdnr_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+hdnr+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">����</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+xthdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">�����ȼ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"jldj\" id=\"jldj_"+xmdm+"\" style=\"width:150px\" maxLength=\"20\" value=\""+jldj+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">����ӷ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";
					
				}else if(xmdm == "szyc_zznlfzb"){//��֯����
				
					var url = "szgyyq_mypj_stu.do?method=getZznlInfo";
			   			url+= "&sqid="+sqid;
   			
   					var hdzt = "";
   					var hdrq = "";
   					var hddj = "";
   					var sqf = "";
   					
					jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false,
						success:function(result){
							hdzt = result[0].hdzt;
		   					hdrq = result[0].hdrq;
		   					hddj = result[0].hddj;
		   					sqf = result[0].sqf;
						}
					});

					html+="<tr>";
					html+="<td width=\"30%\">�����<font color=\"red\">(������150��)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"hdzt\" style='word-break:break-all;' id=\"hdzt_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+hdzt+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">�����</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hdrq\" id=\"hdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('hdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+hdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">��ȼ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hddj\" id=\"hddj_"+xmdm+"\" style=\"width:150px\" maxLength=\"20\" value=\""+hddj+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">����ӷ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";
					
				}else if(xmdm == "szyc_shsjfzb"){//���ʵ��
				
					var url = "szgyyq_mypj_stu.do?method=getShsjInfo";
			   			url+= "&sqid="+sqid;
   			
   					var hddd = "";
   					var hdrq = "";
   					var hdnr = "";
   					var hdsj = "";
   					var sqf = "";
   					
					jQuery.ajax({
						type:'post',
						url:url,
						dataType:'json',
						async: false,
						success:function(result){
							hddd = result[0].hddd;
		   					hdrq = result[0].hdrq;
		   					hdnr = result[0].hdnr;
		   					hdsj = result[0].hdsj;
		   					sqf = result[0].sqf;
						}
					});

					html+="<tr>";
					html+="<td width=\"30%\">��ص�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hddd\" id=\"hddd_"+xmdm+"\" style=\"width:150px\" maxLength=\"50\" value=\""+hddd+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">�����</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hdrq\" id=\"hdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('hdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+hdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">�����<font color=\"red\">(������150��)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"hdnr\" style='word-break:break-all;' id=\"hdnr_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+hdnr+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">�ʱ��</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hdsj\" id=\"hdsj_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+hdsj+"\"/>";
					html+="(Сʱ)";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">����ӷ�</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";
				}
				
				html+="<tfoot>";
				html+="<tr>";
				html+="<td colspan=\"2\">";
				html+="<div class=\"bz\">";
										
				html+="</div>";
				html+="<div class=\"btn\">";
				html+="<button type='button' id=\"btn_bc\" onclick=\"confirmInfo('���ȷ�ϣ��������Ը�����¼���޸ģ���ȷ��',saveEdit)\">";
				html+="�� ��";
				html+="</button>";
				html+="<button type='button' id=\"btn_gb\" onclick=\"closeWindown()\">";
				html+="�� ��";
				html+="</button>";
				html+="</div>";
				html+="</td>";
				html+="</tr>";
				html+="</tfoot>";
				
			$("div_edit").innerHTML = html;
			
			tipsWindown("ϵͳ��ʾ","id:div_edit","350","350","true","","true","id");
		}
		
		//�����޸�
		function saveEdit(tag){
		
			if(tag == "ok"){
			
				var xmdm = $("xmdm").value;
				var sqid = $("sqid").value;
				
				if(xmdm == "szyq_dshdjzb"){//����
				
					var dsmc_id = "dsmc_"+xmdm;//��������
					var dsrq_id = "dsrq_"+xmdm;//��������
					var dsxd_id = "dsxd_"+xmdm;//�����ĵ�
					var sfhj_id = "sfhj_"+xmdm;//�Ƿ��
					var sqf_id = "sqf_"+xmdm;//�����
				
					if($(dsmc_id).value == ""){
						alertError("����Ϊ�գ���ȷ�ϣ�");
						$(dsmc_id).focus();
						return false;
					}else if($(dsrq_id).value == ""){
						alertError("��������Ϊ�գ���ȷ�ϣ�");
						$(dsrq_id).focus();
						return false;
					}else if($(dsxd_id).value == ""){
						alertError("�����ĵ�Ϊ�գ���ȷ�ϣ�");
						$(dsxd_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editDshdInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//����
				 	var parameter = {
				 		"sqid":sqid,
						"dsmc":escape($(dsmc_id).value),
						"dsrq":$(dsrq_id).value,
						"dsxd":escape($(dsxd_id).value),
						"sfhj":escape($(sfhj_id).value),
						"sqf":$(sqf_id).value
					};
					
					jQuery.post(url,parameter,function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(){
							reflashFssqDetail();
						});
					});
				}else if(xmdm == "szyq_yybdjzb"){//���Ա��
				
					var yybdnr_id = "yybdnr_"+xmdm;//���Ա������
					var xthdrq_id = "xthdrq_"+xmdm;//����
					var sqf_id = "sqf_"+xmdm;//�����
				
					if($(yybdnr_id).value == ""){
						alertError("���Ա������Ϊ�գ���ȷ�ϣ�");
						$(yybdnr_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("����Ϊ�գ���ȷ�ϣ�");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editYybdInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//����
				 	var parameter = {
				 		"sqid":sqid,
						"yybdnr":escape($(yybdnr_id).value),
						"xthdrq":$(xthdrq_id).value,
						"sqf":$(sqf_id).value
					};
					
					jQuery.post(url,parameter,function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(){
							reflashFssqDetail();
						});
					});
				}else if(xmdm == "szyq_ivtltb"){//IVT��̳
				
					var jztm_id = "jztm_"+xmdm;//������Ŀ
					var xthdrq_id = "xthdrq_"+xmdm;//����
					var jcdj_id = "jcdj_"+xmdm;//�����Ǽ�
					var ccdj_id = "ccdj_"+xmdm;//�����Ǽ�
					var sqf_id = "sqf_"+xmdm;//�����
				
					if($(jztm_id).value == ""){
						alertError("������ĿΪ�գ���ȷ�ϣ�");
						$(jztm_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("����Ϊ�գ���ȷ�ϣ�");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editIvtltInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//����
				 	var parameter = {
				 		"sqid":sqid,
						"jztm":escape($(jztm_id).value),
						"xthdrq":$(xthdrq_id).value,
						"jcdj":escape($(jcdj_id).value),
						"ccdj":escape($(ccdj_id).value),
						"sqf":$(sqf_id).value
					};
					
					jQuery.post(url,parameter,function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(){
							reflashFssqDetail();
						});
					});
					
				}else if(xmdm == "szyq_xthddjb"){//����
				
					var hdnr_id = "hdnr_"+xmdm;//�����
					var xthdrq_id = "xthdrq_"+xmdm;//����
					var jldj_id = "jldj_"+xmdm;//�����ȼ�
					var sqf_id = "sqf_"+xmdm;//�����
				
					if($(hdnr_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(hdnr_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("����Ϊ�գ���ȷ�ϣ�");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editWthdInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//����
				 	var parameter = {
				 		"sqid":sqid,
						"hdnr":escape($(hdnr_id).value),
						"xthdrq":$(xthdrq_id).value,
						"jldj":escape($(jldj_id).value),
						"sqf":$(sqf_id).value
					};
					
					jQuery.post(url,parameter,function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(){
							reflashFssqDetail();
						});
					});
					
				}else if(xmdm == "szyc_zznlfzb"){//��֯�
				
					var hdzt_id = "hdzt_"+xmdm;//�����
					var hdrq_id = "hdrq_"+xmdm;//�����
					var hddj_id = "hddj_"+xmdm;//��ȼ�
					var sqf_id = "sqf_"+xmdm;//�����
				
					if($(hdzt_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(hdzt_id).focus();
						return false;
					}else if($(hdrq_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(hdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editZznlInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//����
				 	var parameter = {
				 		"sqid":sqid,
						"hdzt":escape($(hdzt_id).value),
						"hdrq":$(hdrq_id).value,
						"hddj":escape($(hddj_id).value),
						"sqf":$(sqf_id).value
					};
					
					jQuery.post(url,parameter,function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(){
							reflashFssqDetail();
						});
					});
					
				}else if(xmdm == "szyc_shsjfzb"){//���ʵ��
				
					var hddd_id = "hddd_"+xmdm;//��ص�
					var hdrq_id = "hdrq_"+xmdm;//�����
					var hdnr_id = "hdnr_"+xmdm;//�����
					var hdsj_id = "hdsj_"+xmdm;//�ʱ��
					var sqf_id = "sqf_"+xmdm;//�����
				
					if($(hddd_id).value == ""){
						alertError("��ص�Ϊ�գ���ȷ�ϣ�");
						$(hddd_id).focus();
						return false;
					}else if($(hdrq_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(hdrq_id).focus();
						return false;
					}else if($(hdnr_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(hdnr_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("�����Ϊ�գ���ȷ�ϣ�");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editShsjInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//����
				 	var parameter = {
				 		"sqid":sqid,
						"hddd":escape($(hddd_id).value),
						"hdrq":$(hdrq_id).value,
						"hdnr":escape($(hdnr_id).value),
						"hdsj":$(hdsj_id).value,
						"sqf":$(sqf_id).value
					};
					
					jQuery.post(url,parameter,function(result){
						$("divWaiting").style.display="none";
						$("divDisable").style.display="none";
						alertInfo(result,function(){
							reflashFssqDetail();
						});
					});
					
				}
			}
		}
		</script>
	</head>
	<body onload="" >
	
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>�ۺ��������ɿ� - �ҵĹ��� - ������ϸ</a>
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
				1.�������в������ǻ���<font color="blue">${xn }</font>ѧ�꣬<font color="blue">${xqmc }</font>ѧ�� չ���ġ�
				</span>
			</p>
			<a class="close" title="����"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		
		<html:form action="/szgyyq_mypj_stu" method="post">

			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xn" id="xn" value="${xn }"/>
			<input type="hidden" name="xq" id="xq" value="${xq }"/>
			<input type="hidden" name="xh" id="xh" value="${xh }"/>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }"/>
			<input type="hidden" name="sqid" id="sqid" value=""/>
			<input type="hidden" name="xmid" id="xmid" value=""/>
			<!-- ˢ��  -->
			<button type="button" id="btn_sx" onclick="reflashSqxxDetail()" style="display:none">
				ˢ��
			</button>
			<!-- ������ end-->
			
			<!-- ѧ��������Ϣ -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>${xmmc }������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">ѧ��</th>
						<td width="30%">
							${xn }
						</td>
						<th width="20%">ѧ��</th>
						<td width="30%">
							${xqmc }
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div style="height:360px;overflow-x:auto;overflow-y:auto;" id="div_sqxx">
								<table class="dateline" width="100%">
									<!-- ���� -->
							    	<thead>
										<tr>
											<logic:iterate id="tit" name="topTr">
												<td>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
							      		</tr>
									</thead>
									<!-- ���� end-->
									
									<!-- ���� -->
									<logic:equal name="xmdm" value="szyq_dshdjzb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.alldsmc }">
													<!-- �������� -->
													${info.dsmc }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- �������� -->
													${info.dsrq }
												</td>
												<td title="${info.alldsxd }">
													<!-- �����ĵ� -->
													${info.dsxd }
												</td>
												<td>
													<!-- �Ƿ�� -->
													${info.sfhj }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<td>
													<!-- ��������˷� -->
													${info.bzrshf }
												</td>
												<td>
													<!-- ѧԺ��˷� -->
													${info.xyshf }
												</td>
												<td>
													<!-- ѧУ��˷� -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- ���� -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- ���� end-->
									
									<!-- ���Ա������ begin -->
									<logic:equal name="xmdm" value="szyq_yybdjzb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.allyybdnr }">
													<!-- ���Ա������ -->
													${info.yybdnr }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- ���� -->
													${info.xthdrq }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<td>
													<!-- ��������˷� -->
													${info.bzrshf }
												</td>
												<td>
													<!-- ѧԺ��˷� -->
													${info.xyshf }
												</td>
												<td>
													<!-- ѧУ��˷� -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- ���� -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- ���Ա������ end-->
									
									<!-- ivtl���� begin -->
									<logic:equal name="xmdm" value="szyq_ivtltb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.alljztm }">
													<!-- ������Ŀ -->
													${info.jztm }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- ���� -->
													${info.xthdrq }
												</td>
												<td>
													<!-- �����Ǽ� -->
													${info.jcdj }
												</td>
												<td>
													<!-- �����Ǽ� -->
													${info.ccdj }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<td>
													<!-- ��������˷� -->
													${info.bzrshf }
												</td>
												<td>
													<!-- ѧԺ��˷� -->
													${info.xyshf }
												</td>
												<td>
													<!-- ѧУ��˷� -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- ���� -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- ivtl���� end-->
									
									<!-- ���� begin -->
									<logic:equal name="xmdm" value="szyq_xthddjb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.allhdnr }">
													<!-- ����� -->
													${info.hdnr }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- ���� -->
													${info.xthdrq }
												</td>
												<td>
													<!-- �����ȼ� -->
													${info.jldj }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<td>
													<!-- ��������˷� -->
													${info.bzrshf }
												</td>
												<td>
													<!-- ѧԺ��˷� -->
													${info.xyshf }
												</td>
												<td>
													<!-- ѧУ��˷� -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- ���� -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- ���� end-->
									
									<!-- ��֯���� begin -->
									<logic:equal name="xmdm" value="szyc_zznlfzb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.allhdzt }">
													<!-- ����� -->
													${info.hdzt }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- ���� -->
													${info.hdrq }
												</td>
												<td>
													<!-- ��ȼ� -->
													${info.hddj }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<td>
													<!-- ��������˷� -->
													${info.bzrshf }
												</td>
												<td>
													<!-- ѧԺ��˷� -->
													${info.xyshf }
												</td>
												<td>
													<!-- ѧУ��˷� -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- ���� -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- ��֯���� end-->
									
									<!-- ���ʵ�� begin -->
									<logic:equal name="xmdm" value="szyc_shsjfzb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.allhddd }">
													<!-- ��ص� -->
													${info.hddd }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- ���� -->
													${info.hdrq }
												</td>
												<td title="${info.allhdnr }">
													<!-- ����� -->
													${info.hdnr }
												</td>
												<td>
													<!-- ����� -->
													${info.hdsj }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<td>
													<!-- ��������˷� -->
													${info.bzrshf }
												</td>
												<td>
													<!-- ѧԺ��˷� -->
													${info.xyshf }
												</td>
												<td>
													<!-- ѧУ��˷� -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- ���� -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- ���ʵ�� end-->
									
									<!-- 5s begin -->
									<logic:equal name="xmdm" value="szyc_5sb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td title="${info.allfzxm}">
													<!-- ��ص� -->
													${info.fzxm }
												</td>
												<td>
													<!-- �Ӽ��� -->
													${info.jjf }
												</td>
												<td>
													<!-- ����� -->
													${info.sqf }
												</td>
												<td>
													<!-- ���� -->
													${info.jfrq }
												</td>
												<td>
													<!-- ԭ�� -->
													<logic:equal name="info" property="yy" value="����">
														<a href="#"  onclick="showQtyy('${info.jfyy }')"><font color="blue">${info.yy }</font></a>
													</logic:equal>
													<logic:notEqual name="info" property="yy" value="����">
														${info.yy }
													</logic:notEqual>
													
												</td>
												<td>
													<!-- ѧԺ��˷� -->
													${info.xyshf }
												</td>
												<td>
													<!-- ѧУ��˷� -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- ���� -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- 5s end-->
									
									<!-- �ۺ����ʷ� begin -->
									<logic:equal name="xmdm" value="szgy_zhszcphzlsb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<!-- ��Ŀ���� -->
													${info.mkmc }
												</td>
												<td>
													<!-- ��ֵ -->
													${info.sqf }
												</td>
												<td>
													<!-- ��Ŀ���� -->
													${info.bzrshf }
												</td>
												<td>
													<!-- ��ֵ -->
													${info.xyshf }
												</td>
												<td>
													<!-- ��Ŀ���� -->
													${info.xxshf }
												</td>
												<td>
													<!-- ��ֵ -->
													${info.mkf }
												</td>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- �ۺ����ʷ� end-->
								</table>
							</div>
						</td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<td colspan='4'>
							<div class="btn">
								<logic:equal name="doType" value="edit">
									<!-- �޸� -->
									<button type="button" onclick="checkKfcl('�޸�')">
										�� ��
									</button>
									
									<!-- ɾ�� -->
									<button type="button" onclick="checkKfcl('ɾ��')">
										ɾ ��
									</button>
								</logic:equal>
								
								<!-- �ر�-->
								<button type="button" onclick="if(window.dialogArguments.document.getElementById('btn_sx')){window.dialogArguments.document.getElementById('btn_sx').click()};Close()">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- ѧ��������Ϣ end-->
			
			<!-- ѧ�����ߵ����� -->
			<div id="div_xsss" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>����д��������</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_ssnr">
								<th width="30%">
									��������<br/>
									<font color="red"><��500��></font>
								</th>
								<td>
									<textarea id="ssnr" rows="5" cols="" style="word-break:break-all;width:100%" onblur="chLeng(this,500)"></textarea>
								</td>
							</tr>
							<tr id="tr_clr">
								<th width="30%">
									������
								</th>
								<td>
									<p id="p_clr"></p>
								</td>
							</tr>
							<tr id="tr_clyj">
								<th width="30%">
									�������
								</th>
								<td>
									<textarea id="clyj" rows="5" cols="" style="word-break:break-all;width:100%"></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
										<button type="button" id="btn_bc" onclick="confirmInfo('��ȷ��Ҫ������',saveSsnr)">
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
			
			<!-- ����ԭ�� -->
			<div id="div_qtyy" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>����ԭ��</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_ssnr">
								<th width="30%">
									ԭ��
								</th>
								<td>
									<textarea id="qtyy" rows="5" cols="" style="word-break:break-all;width:100%" ></textarea>
								</td>
							</tr>
						</tbody>
						<tfoot>
							<tr>
								<td colspan="2">
									<div class="bz">
										
									</div>
									<div class="btn">
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
			
			<!-- �޸����� -->
			<div id="div_edit" style="display:none">

			</div>
			
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>