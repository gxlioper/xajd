<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- 作者：伟大的骆 -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link rel="stylesheet" type="text/css" href="js/jquery/themes/default/easyui.css" />
	    <link rel="stylesheet" type="text/css" href="js/jquery/themes/icon.css" />
	    <script language="javascript" src="js/check.js"></script>
	    <script language="javascript" src="js/pjpy/szgyyq/pjpy_szgyyq.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">
		
		//刷新页面
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
		
		//显示学生申诉Div
		function showXsssDiv(xmid,cz,ssnr,clyj,clrxm){
			$("xmid").value = xmid;
			
			if(cz == "申诉"){
				$("btn_bc").style.display = "";	
				$("tr_ssnr").style.display = "";
				$("tr_clr").style.display = "none";
				$("tr_clyj").style.display = "none";
				
				$("ssnr").value = "";
				$("clyj").value = "";
			}else if( cz == "已处理"){
				$("btn_bc").style.display = "none";
				$("tr_ssnr").style.display = "none";
				$("tr_clr").style.display = "";
				$("tr_clyj").style.display = "";
				
				$("p_clr").innerHTML = clrxm;
				$("ssnr").value = ssnr;
				$("clyj").value = clyj;
			}else if( cz == "已申诉"){
				$("btn_bc").style.display = "none";
				$("tr_ssnr").style.display = "";
				$("tr_clr").style.display = "none";
				$("tr_clyj").style.display = "none";
				
				$("ssnr").value = ssnr;
				$("clyj").value = "";
			}
			
			tipsWindown("系统提示","id:div_xsss","350","200","true","","true","id");
		}
		
		//保存申诉内容
		function saveSsnr(tag){
		
			if(tag == "ok"){
				var tsnr = $("ssnr").value;
				
				if(tsnr == ""){
					alertError("申诉内容不可为空，请确认！");
					return false;
				}
				
				var url="szgyyq_mypj_stu.do?method=saveXsss";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				var xh = $("xh").value;
				var xmlx = $("xmdm").value;
				var xmid = $("xmid").value;
				var ssnr = $("ssnr").value;
				
				//参数
			 	var parameter = {
					"xh":xh,
					"xmlx":xmlx,
					"xmid":xmid,
					"ssnr":escape(ssnr)
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
		}
		
		//执行成功
		function dcSuccess(result){
			$("divWaiting").style.display="none";
			$("divDisable").style.display="none";
			alertInfo(result,function(tag){if(tag == "ok"){$("btn_sx").click()}});
		}
		
		function showQtyy(qtyy){
			jQuery("#qtyy").val(qtyy);
			tipsWindown("系统提示","id:div_qtyy","350","200","true","","true","id");
		}
		
		//显示修改Div
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
				
						//参数
					 	var parameter = {
					 		"xmdm":xmdm,
							"sqid":sqid
						};
						
						jQuery.post(url,parameter,function(result){
							if(result == "true"){
								if(type == "修改"){
									showEditDiv();
								}else{
									confirmInfo('点击确认，删除该条记录，请您确认',deleteSqjl);
								}
							}else{
								alertError("该条申请记录已经被审核过了，无法"+type+"，请确认");
								return false;
							}
						});
					}
				}
			}
			
			if(!flag){
				alertError("请选择您希望修改的记录");
				return false;
			}
			
			jQuery.ajaxSetup({async:true});
		}
		
		//显示修改Div
		function deleteSqjl(tag){
		
			if(tag == "ok"){
				var url="szgyyq_mypj_stu.do?method=deleteSqjl";
				var xmdm = $("xmdm").value;
				var sqid = $("sqid").value;
				//参数
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
		
		//刷新页面
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
		
		//显示修改Div
		function showEditDiv(){
		
			//项目代码
			var xmdm = $("xmdm").value;
			//申请ID
			var sqid = $("sqid").value;
			
			var html = "";
				html+="<div class=\"open_win01\">";
				html+="<table align=\"center\" class=\"formlist\">";
				html+="<thead>";
				html+="<tr>";
				html+="<th colspan=\"2\">";
				html+="<span>申请信息修改</span>";
				html+="</th>";
				html+="</tr>";
				html+="</thead>";
				
				if(xmdm == "szyq_dshdjzb"){//读书活动
				
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
					html+="<td width=\"30%\">书名</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"dsmc\" id=\"dsmc_"+xmdm+"\" style=\"width:150px\" maxLength=\"40\" value=\""+dsmc+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">读书日期</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"dsrq\" id=\"dsrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('dsrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+dsrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td>读书心得<bt/><font color=\"red\">(限输入150字)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"dsxd\" style='word-break:break-all;' id=\"dsxd_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+dsxd+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">是否获奖</td>";
					html+="<td width=\"\">";
					html+="<select name=\"sfhj\" id=\"sfhj_"+xmdm+"\"><option value=\"否\"";
					if(sfhj == "否"){
						html+="selected=\"selected\"";
					}
					html+=">否</option><option value=\"是\"";
					if(sfhj == "是"){
						html+="selected=\"selected\"";
					}
					html+=">是</option></select>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">申请加分</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";
				}else if(xmdm == "szyq_yybdjzb"){//语言表达
				
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
					html+="<td width=\"30%\">语言表达内容<font color=\"red\">(限输入150字)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"yybdnr\" style='word-break:break-all;' id=\"yybdnr_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+yybdnr+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">日期</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+xthdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">申请加分</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";
				}else if(xmdm == "szyq_ivtltb"){//IVT论坛
				
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
					html+="<td width=\"30%\">讲座题目</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"jztm\" id=\"jztm_"+xmdm+"\" style=\"width:150px\" maxLength=\"60\" value=\""+jztm+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">日期</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+xthdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">进场登记</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"jcdj\" id=\"jcdj_"+xmdm+"\" style=\"width:150px\" maxLength=\"40\" value=\""+jcdj+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">出场登记</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"ccdj\" id=\"ccdj_"+xmdm+"\" style=\"width:150px\" maxLength=\"40\" value=\""+ccdj+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">申请加分</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";

				}else if(xmdm == "szyq_xthddjb"){//文体活动
				
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
					html+="<td width=\"30%\">活动内容<font color=\"red\">(限输入150字)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"hdnr\" style='word-break:break-all;' id=\"hdnr_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+hdnr+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">日期</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+xthdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">奖励等级</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"jldj\" id=\"jldj_"+xmdm+"\" style=\"width:150px\" maxLength=\"20\" value=\""+jldj+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">申请加分</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";
					
				}else if(xmdm == "szyc_zznlfzb"){//组织能力
				
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
					html+="<td width=\"30%\">活动主题<font color=\"red\">(限输入150字)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"hdzt\" style='word-break:break-all;' id=\"hdzt_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+hdzt+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">活动日期</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hdrq\" id=\"hdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('hdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+hdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">活动等级</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hddj\" id=\"hddj_"+xmdm+"\" style=\"width:150px\" maxLength=\"20\" value=\""+hddj+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">申请加分</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+sqf+"\"/>";
					html+="</td>";
					html+="</tr>";
					
				}else if(xmdm == "szyc_shsjfzb"){//社会实践
				
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
					html+="<td width=\"30%\">活动地点</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hddd\" id=\"hddd_"+xmdm+"\" style=\"width:150px\" maxLength=\"50\" value=\""+hddd+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">活动日期</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hdrq\" id=\"hdrq_"+xmdm+"\" style=\"width:150px\" onclick=\"return showCalendar('hdrq_"+xmdm+"','ymmdd');\" readOnly=\"true\" value=\""+hdrq+"\"/>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">活动内容<font color=\"red\">(限输入150字)</font></td>";
					html+="<td width=\"\">";
					html+="<textarea name=\"hdnr\" style='word-break:break-all;' id=\"hdnr_"+xmdm+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\">"+hdnr+"</textarea>";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">活动时间</td>";
					html+="<td width=\"\">";
					html+="<input type=\"text\" name=\"hdsj\" id=\"hdsj_"+xmdm+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\" value=\""+hdsj+"\"/>";
					html+="(小时)";
					html+="</td>";
					html+="</tr>";
					
					html+="<tr>";
					html+="<td width=\"30%\">申请加分</td>";
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
				html+="<button type='button' id=\"btn_bc\" onclick=\"confirmInfo('点击确认，保存您对该条记录的修改，请确认',saveEdit)\">";
				html+="保 存";
				html+="</button>";
				html+="<button type='button' id=\"btn_gb\" onclick=\"closeWindown()\">";
				html+="关 闭";
				html+="</button>";
				html+="</div>";
				html+="</td>";
				html+="</tr>";
				html+="</tfoot>";
				
			$("div_edit").innerHTML = html;
			
			tipsWindown("系统提示","id:div_edit","350","350","true","","true","id");
		}
		
		//保存修改
		function saveEdit(tag){
		
			if(tag == "ok"){
			
				var xmdm = $("xmdm").value;
				var sqid = $("sqid").value;
				
				if(xmdm == "szyq_dshdjzb"){//读书活动
				
					var dsmc_id = "dsmc_"+xmdm;//读书名称
					var dsrq_id = "dsrq_"+xmdm;//读书日期
					var dsxd_id = "dsxd_"+xmdm;//读书心得
					var sfhj_id = "sfhj_"+xmdm;//是否获奖
					var sqf_id = "sqf_"+xmdm;//申请分
				
					if($(dsmc_id).value == ""){
						alertError("书名为空，请确认！");
						$(dsmc_id).focus();
						return false;
					}else if($(dsrq_id).value == ""){
						alertError("读书日期为空，请确认！");
						$(dsrq_id).focus();
						return false;
					}else if($(dsxd_id).value == ""){
						alertError("读书心得为空，请确认！");
						$(dsxd_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editDshdInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//参数
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
				}else if(xmdm == "szyq_yybdjzb"){//语言表达
				
					var yybdnr_id = "yybdnr_"+xmdm;//语言表达内容
					var xthdrq_id = "xthdrq_"+xmdm;//日期
					var sqf_id = "sqf_"+xmdm;//申请分
				
					if($(yybdnr_id).value == ""){
						alertError("语言表达内容为空，请确认！");
						$(yybdnr_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("日期为空，请确认！");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editYybdInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//参数
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
				}else if(xmdm == "szyq_ivtltb"){//IVT论坛
				
					var jztm_id = "jztm_"+xmdm;//讲座题目
					var xthdrq_id = "xthdrq_"+xmdm;//日期
					var jcdj_id = "jcdj_"+xmdm;//进场登记
					var ccdj_id = "ccdj_"+xmdm;//出场登记
					var sqf_id = "sqf_"+xmdm;//申请分
				
					if($(jztm_id).value == ""){
						alertError("讲座题目为空，请确认！");
						$(jztm_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("日期为空，请确认！");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editIvtltInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//参数
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
					
				}else if(xmdm == "szyq_xthddjb"){//文体活动
				
					var hdnr_id = "hdnr_"+xmdm;//活动内容
					var xthdrq_id = "xthdrq_"+xmdm;//日期
					var jldj_id = "jldj_"+xmdm;//奖励等级
					var sqf_id = "sqf_"+xmdm;//申请分
				
					if($(hdnr_id).value == ""){
						alertError("活动内容为空，请确认！");
						$(hdnr_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("日期为空，请确认！");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editWthdInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//参数
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
					
				}else if(xmdm == "szyc_zznlfzb"){//组织活动
				
					var hdzt_id = "hdzt_"+xmdm;//活动主题
					var hdrq_id = "hdrq_"+xmdm;//活动日期
					var hddj_id = "hddj_"+xmdm;//活动等级
					var sqf_id = "sqf_"+xmdm;//申请分
				
					if($(hdzt_id).value == ""){
						alertError("活动主题为空，请确认！");
						$(hdzt_id).focus();
						return false;
					}else if($(hdrq_id).value == ""){
						alertError("活动日期为空，请确认！");
						$(hdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editZznlInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//参数
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
					
				}else if(xmdm == "szyc_shsjfzb"){//社会实践
				
					var hddd_id = "hddd_"+xmdm;//活动地点
					var hdrq_id = "hdrq_"+xmdm;//活动日期
					var hdnr_id = "hdnr_"+xmdm;//活动内容
					var hdsj_id = "hdsj_"+xmdm;//活动时间
					var sqf_id = "sqf_"+xmdm;//申请分
				
					if($(hddd_id).value == ""){
						alertError("活动地点为空，请确认！");
						$(hddd_id).focus();
						return false;
					}else if($(hdrq_id).value == ""){
						alertError("活动日期为空，请确认！");
						$(hdrq_id).focus();
						return false;
					}else if($(hdnr_id).value == ""){
						alertError("活动内容为空，请确认！");
						$(hdnr_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					var url="szgyyq_mypj_stu.do?method=editShsjInfo";
					
					$("divWaiting").style.display="";
					$("divDisable").style.display="";
					
					//参数
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
	
		<!-- 标题 -->
		<div class="tab_cur">
			<p class="location">
				<em>您的当前位置：</em><a>综合素质养成课 - 我的工作 - 分数详细</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">使用帮助</a>
			</p>
		</div>
		<!-- 标题 end-->
		
		<!-- 提示信息 end-->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>提示：</span>
			</h3>
			<p>
				<span>
				1.以下所有操作都是基于<font color="blue">${xn }</font>学年，<font color="blue">${xqmc }</font>学期 展开的。
				</span>
			</p>
			<a class="close" title="隐藏"  onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- 提示信息 end-->
		
		<html:form action="/szgyyq_mypj_stu" method="post">

			<!-- 隐藏域 -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<input type="hidden" name="xn" id="xn" value="${xn }"/>
			<input type="hidden" name="xq" id="xq" value="${xq }"/>
			<input type="hidden" name="xh" id="xh" value="${xh }"/>
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }"/>
			<input type="hidden" name="sqid" id="sqid" value=""/>
			<input type="hidden" name="xmid" id="xmid" value=""/>
			<!-- 刷新  -->
			<button type="button" id="btn_sx" onclick="reflashSqxxDetail()" style="display:none">
				刷新
			</button>
			<!-- 隐藏域 end-->
			
			<!-- 学生基本信息 -->
			<table class="formlist" width="">
				<thead onclick="">
					<tr>
						<th colspan="4">
							<span>${xmmc }申请信息</span>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th width="20%">学年</th>
						<td width="30%">
							${xn }
						</td>
						<th width="20%">学期</th>
						<td width="30%">
							${xqmc }
						</td>
					</tr>
					<tr>
						<td colspan="4">
							<div style="height:360px;overflow-x:auto;overflow-y:auto;" id="div_sqxx">
								<table class="dateline" width="100%">
									<!-- 标题 -->
							    	<thead>
										<tr>
											<logic:iterate id="tit" name="topTr">
												<td>
													<bean:write name="tit" property="cn" />
												</td>
											</logic:iterate>
							      		</tr>
									</thead>
									<!-- 标题 end-->
									
									<!-- 读书活动 -->
									<logic:equal name="xmdm" value="szyq_dshdjzb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.alldsmc }">
													<!-- 读书名称 -->
													${info.dsmc }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- 读书日期 -->
													${info.dsrq }
												</td>
												<td title="${info.alldsxd }">
													<!-- 读书心得 -->
													${info.dsxd }
												</td>
												<td>
													<!-- 是否获奖 -->
													${info.sfhj }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<td>
													<!-- 班主任审核分 -->
													${info.bzrshf }
												</td>
												<td>
													<!-- 学院审核分 -->
													${info.xyshf }
												</td>
												<td>
													<!-- 学校审核分 -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- 操作 -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- 读书活动 end-->
									
									<!-- 语言表达内容 begin -->
									<logic:equal name="xmdm" value="szyq_yybdjzb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.allyybdnr }">
													<!-- 语言表达内容 -->
													${info.yybdnr }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- 日期 -->
													${info.xthdrq }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<td>
													<!-- 班主任审核分 -->
													${info.bzrshf }
												</td>
												<td>
													<!-- 学院审核分 -->
													${info.xyshf }
												</td>
												<td>
													<!-- 学校审核分 -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- 操作 -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- 语言表达内容 end-->
									
									<!-- ivtl论团 begin -->
									<logic:equal name="xmdm" value="szyq_ivtltb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.alljztm }">
													<!-- 讲座题目 -->
													${info.jztm }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- 日期 -->
													${info.xthdrq }
												</td>
												<td>
													<!-- 进场登记 -->
													${info.jcdj }
												</td>
												<td>
													<!-- 出场登记 -->
													${info.ccdj }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<td>
													<!-- 班主任审核分 -->
													${info.bzrshf }
												</td>
												<td>
													<!-- 学院审核分 -->
													${info.xyshf }
												</td>
												<td>
													<!-- 学校审核分 -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- 操作 -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- ivtl论团 end-->
									
									<!-- 文体活动 begin -->
									<logic:equal name="xmdm" value="szyq_xthddjb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.allhdnr }">
													<!-- 活动内容 -->
													${info.hdnr }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- 日期 -->
													${info.xthdrq }
												</td>
												<td>
													<!-- 奖励等级 -->
													${info.jldj }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<td>
													<!-- 班主任审核分 -->
													${info.bzrshf }
												</td>
												<td>
													<!-- 学院审核分 -->
													${info.xyshf }
												</td>
												<td>
													<!-- 学校审核分 -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- 操作 -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- 文体活动 end-->
									
									<!-- 组织能力 begin -->
									<logic:equal name="xmdm" value="szyc_zznlfzb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.allhdzt }">
													<!-- 活动主题 -->
													${info.hdzt }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- 日期 -->
													${info.hdrq }
												</td>
												<td>
													<!-- 活动等级 -->
													${info.hddj }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<td>
													<!-- 班主任审核分 -->
													${info.bzrshf }
												</td>
												<td>
													<!-- 学院审核分 -->
													${info.xyshf }
												</td>
												<td>
													<!-- 学校审核分 -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- 操作 -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- 组织能力 end-->
									
									<!-- 社会实践 begin -->
									<logic:equal name="xmdm" value="szyc_shsjfzb">
										<logic:iterate name="infoList" id="info">
											<tr onclick="rowOnClick(this);" style="cursor:hand" id="tr_${info.id }">
												<td title="${info.allhddd }">
													<!-- 活动地点 -->
													${info.hddd }
													<input type="hidden" name="hid_id" value="${info.id }"/>
												</td>
												<td>
													<!-- 日期 -->
													${info.hdrq }
												</td>
												<td title="${info.allhdnr }">
													<!-- 活动内容 -->
													${info.hdnr }
												</td>
												<td>
													<!-- 活动内容 -->
													${info.hdsj }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<td>
													<!-- 班主任审核分 -->
													${info.bzrshf }
												</td>
												<td>
													<!-- 学院审核分 -->
													${info.xyshf }
												</td>
												<td>
													<!-- 学校审核分 -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- 操作 -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- 社会实践 end-->
									
									<!-- 5s begin -->
									<logic:equal name="xmdm" value="szyc_5sb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td title="${info.allfzxm}">
													<!-- 活动地点 -->
													${info.fzxm }
												</td>
												<td>
													<!-- 加减分 -->
													${info.jjf }
												</td>
												<td>
													<!-- 申请分 -->
													${info.sqf }
												</td>
												<td>
													<!-- 日期 -->
													${info.jfrq }
												</td>
												<td>
													<!-- 原因 -->
													<logic:equal name="info" property="yy" value="其它">
														<a href="#"  onclick="showQtyy('${info.jfyy }')"><font color="blue">${info.yy }</font></a>
													</logic:equal>
													<logic:notEqual name="info" property="yy" value="其它">
														${info.yy }
													</logic:notEqual>
													
												</td>
												<td>
													<!-- 学院审核分 -->
													${info.xyshf }
												</td>
												<td>
													<!-- 学校审核分 -->
													${info.xxshf }
												</td>
												<logic:equal name="canCz" value="yes">
												<td>
													<!-- 操作 -->
													<a href="#" onclick="showXsssDiv('${info.id }','${info.cz }','${info.ssnr }','${info.clyj }','${info.clrxm }');return false;">
														<font color="blue">${info.cz }</font>
													</a>
												</td>
												</logic:equal>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- 5s end-->
									
									<!-- 综合素质分 begin -->
									<logic:equal name="xmdm" value="szgy_zhszcphzlsb">
										<logic:iterate name="infoList" id="info">
											<tr>
												<td>
													<!-- 项目名称 -->
													${info.mkmc }
												</td>
												<td>
													<!-- 分值 -->
													${info.sqf }
												</td>
												<td>
													<!-- 项目名称 -->
													${info.bzrshf }
												</td>
												<td>
													<!-- 分值 -->
													${info.xyshf }
												</td>
												<td>
													<!-- 项目名称 -->
													${info.xxshf }
												</td>
												<td>
													<!-- 分值 -->
													${info.mkf }
												</td>
											</tr>
										</logic:iterate>
									</logic:equal>
									<!-- 综合素质分 end-->
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
									<!-- 修改 -->
									<button type="button" onclick="checkKfcl('修改')">
										修 改
									</button>
									
									<!-- 删除 -->
									<button type="button" onclick="checkKfcl('删除')">
										删 除
									</button>
								</logic:equal>
								
								<!-- 关闭-->
								<button type="button" onclick="if(window.dialogArguments.document.getElementById('btn_sx')){window.dialogArguments.document.getElementById('btn_sx').click()};Close()">
									<bean:message key="lable.btn_gb_space" />
								</button>
							</div>
						</td>
					</tr>
				</tfoot>
			</table>
			<!-- 学生基本信息 end-->
			
			<!-- 学生申诉弹出层 -->
			<div id="div_xsss" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>请填写申诉内容</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_ssnr">
								<th width="30%">
									申诉内容<br/>
									<font color="red"><限500字></font>
								</th>
								<td>
									<textarea id="ssnr" rows="5" cols="" style="word-break:break-all;width:100%" onblur="chLeng(this,500)"></textarea>
								</td>
							</tr>
							<tr id="tr_clr">
								<th width="30%">
									处理人
								</th>
								<td>
									<p id="p_clr"></p>
								</td>
							</tr>
							<tr id="tr_clyj">
								<th width="30%">
									处理意见
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
										<button type="button" id="btn_bc" onclick="confirmInfo('您确认要申诉吗？',saveSsnr)">
											保 存
										</button>
										<button type="button" id="btn_gb" onclick="closeWindown();return false;">
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 其它原因 -->
			<div id="div_qtyy" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>其它原因</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr id="tr_ssnr">
								<th width="30%">
									原因
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
											关 闭
										</button>
									</div>
								</td>
							</tr>
						</tfoot>
					</table>
				</div>
			</div>
			
			<!-- 修改内容 -->
			<div id="div_edit" style="display:none">

			</div>
			
			<!-- 提示信息 -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>