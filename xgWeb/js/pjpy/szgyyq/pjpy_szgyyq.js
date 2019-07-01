//隐藏其他层
function hiddenOtherDiv(xmdm){

	var allxm = ["szyq_dshdjzb","szyq_yybdjzb","szyq_ivtltb","szyq_xthddjb","szyc_zznlfzb","szyc_shsjfzb"];
	
	for(var i=0;i<allxm.length;i++){
	
		var div_id = "div_"+allxm[i];
		
		if(xmdm == allxm[i]){
			if($(div_id)){
				$(div_id).style.display="";
			}
		}else {
			if($(div_id)){
				$(div_id).style.display="none";
			}
		}
	}
}

//选择全部
function clickAll(xmdm){

	var cb_id = "cb_"+xmdm;
	
	var num =  jQuery("input[name="+cb_id+"]").length;
			
	for(var i=0;i<num;i++){
		var obj = jQuery("input[name="+cb_id+"]")[i];
		if($(cb_id).checked){
			obj.checked = true;
		}else{
			obj.checked = false;
		}
	}
}

//创建分数申请显示Div
function createFssqDiv(){
	
	//项目代码
	var xmdm = $("shxm").value;
	//Div
	var div_id = "div_"+xmdm;
	var div_fssq_id = "div_fssq_"+xmdm;
	var max_text_id = "max_"+xmdm;
	hiddenOtherDiv(xmdm);
	
	if(!$(div_id)){
	
		if(xmdm == "szyq_dshdjzb"){//读书活动
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";
				divHtml+="<td width=\"15%\">书名</td>";
				divHtml+="<td width=\"15%\">读书日期</td>";
				divHtml+="<td>读书心得<font color=\"red\">(限输入150字)</font></td>";
				divHtml+="<td width=\"15%\">是否获奖</td>";
				divHtml+="<td width=\"15%\">申请加分</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyq_yybdjzb"){//语言表达
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";		
				divHtml+="<td>语言表达内容<font color=\"red\">(限输入150字)</font></td>";
				divHtml+="<td width=\"15%\">日期</td>";
				divHtml+="<td width=\"15%\">申请加分</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyq_ivtltb"){//IVT论坛
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";
				divHtml+="<td width=\"25%\">讲座题目</td>";
				divHtml+="<td width=\"15%\">日期</td>";
				divHtml+="<td width=\"\">进场登记</td>";
				divHtml+="<td width=\"20%\">出场登记</td>";
				divHtml+="<td width=\"15%\">申请加分</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyq_xthddjb"){//文体活动
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";		
				divHtml+="<td>活动内容<font color=\"red\">(限输入150字)</font></td>";
				divHtml+="<td width=\"15%\">日期</td>";
				divHtml+="<td width=\"15%\">奖励等级</td>";
				divHtml+="<td width=\"15%\">申请加分</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyc_zznlfzb"){//组织能力
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";		
				divHtml+="<td>活动主题<font color=\"red\">(限输入150字)</font></td>";
				divHtml+="<td width=\"15%\">活动日期</td>";
				divHtml+="<td width=\"15%\">活动等级</td>";
				divHtml+="<td width=\"15%\">申请加分</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
			
		}else if(xmdm == "szyc_shsjfzb"){//社会实践
		
			var divHtml = $("div_fssq").innerHTML;
			
				divHtml+="<div id=\""+div_id+"\">";
				divHtml+="<input type=\"hidden\" id=\""+max_text_id+"\" value=\"0\" />";
				divHtml+="<table align=\"center\" style=\"width: 100%\">";
				divHtml+="<thead>";
				divHtml+="<tr>";
				divHtml+="<td width=\"5px\"><input type='checkbox' id=\"cb_"+xmdm+"\" name=\"cb_all_"+xmdm+"\" onclick=\"clickAll('"+xmdm+"')\"/></td>";
				divHtml+="<td width=\"15%\">活动地点</td>";
				divHtml+="<td width=\"15%\">活动日期</td>";
				divHtml+="<td>活动内容<font color=\"red\">(限输入500字)</font></td>";
				divHtml+="<td width=\"15%\">活动时间</td>";
				divHtml+="<td width=\"15%\">申请加分</td>";
				divHtml+="</tr>";
				divHtml+="</thead>";
				divHtml+="</table>";
				divHtml+="<div id=\""+div_fssq_id+"\" style=\"height:330px;overflow-x:auto;overflow-y:auto;\">";
				divHtml+="</div>";
				divHtml+="</div>";
			
			$("div_fssq").innerHTML = "";
			$("div_fssq").innerHTML = divHtml;
		}
	}
	
	//初始化项目
	defalutSqxm();
}	

//增加分数申请
function addFssq(){

	if($("had_edit") && $("is_default") && $("is_default").value != ""){
		$("had_edit").value = "yes";
	}
	
	//项目代码
	var xmdm = $("shxm").value;
	//Div
	var div_id = "div_"+xmdm;
	var div_fssq_id = "div_fssq_"+xmdm;
	//checkBox Name
	var cb_name = "cb_"+xmdm;
	
	if($(div_id)){
		if(xmdm == "szyq_dshdjzb"){//读书活动	
			
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//读书名称
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"dsmc\" id=\"dsmc_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"40\"/></td>";
				//读书日期
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"dsrq\" id=\"dsrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('dsrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//读书心得
				divHtml+="<td align=\"center\" ><textarea name=\"dsxd\" style='word-break:break-all;' id=\"dsxd_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\"></textarea></td>";
				//是否获奖
				divHtml+="<td align=\"center\" width=\"15%\"><select name=\"sfhj\" id=\"sfhj_"+xmdm+"_"+max+"\"><option value=\"否\">否</option><option value=\"是\">是</option></select></td>";
				//申请分
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
				
		}else if(xmdm == "szyq_yybdjzb"){//语言表达
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//语言表达内容
				divHtml+="<td align=\"center\" ><textarea  style='word-break:break-all;width:90%' name=\"yybdnr\" id=\"yybdnr_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\"></textarea></td>";
				//日期
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//申请分
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
			
		}else if(xmdm == "szyq_ivtltb"){//IVT论坛
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//讲座题目
				divHtml+="<td align=\"center\" width=\"25%\"><input type=\"text\" name=\"jztm\" id=\"jztm_"+xmdm+"_"+max+"\" style=\"width:100px\" maxLength=\"60\"/></td>";
				//日期
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//进场登记
				divHtml+="<td align=\"center\" ><input type=\"text\" name=\"jcdj\" id=\"jcdj_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"40\"/></td>";
				//出场登记
				divHtml+="<td align=\"center\" width=\"20%\"><input type=\"text\" name=\"ccdj\" id=\"ccdj_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"40\"/></td>";
				//申请分
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
			
		}else if(xmdm == "szyq_xthddjb"){//文体活动
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//活动内同
				divHtml+="<td align=\"center\" ><textarea style='word-break:break-all;width:90%' name=\"hdnr\" id=\"hdnr_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\"></textarea></td>";
				//日期
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"xthdrq\" id=\"xthdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('xthdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//奖励等级
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"jldj\" id=\"jldj_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"20\"/></td>";
				//申请分
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
			
		}else if(xmdm == "szyc_zznlfzb"){//组织活动
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//活动内容
				divHtml+="<td align=\"center\" ><textarea style='word-break:break-all;width:90%' name=\"hdzt\" id=\"hdzt_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,150)\"></textarea></td>";
				//活动日期
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hdrq\" id=\"hdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('hdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//活动等级
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hddj\" id=\"hddj_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"5\"/></td>";
				//申请分
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
			
		}else if(xmdm == "szyc_shsjfzb"){//社会实践
		
			var max = parseInt($("max_"+xmdm).value)+1;
			var table_id = "tab_"+xmdm+"_"+max;
			
			var divHtml = $(div_fssq_id).innerHTML;
				divHtml+="<table align=\"center\" style=\"width: 100%\" id=\""+table_id+"\">";
				divHtml+="<tbody>";
				divHtml+="<tr>";
				divHtml+="<td align=\"center\" width=\"5px\"><input type='checkbox' name=\""+cb_name+"\"id=\"cb_"+xmdm+"_"+max+"\" value=\""+max+"\"/></td>";
				//活动地点
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hddd\" id=\"hddd_"+xmdm+"_"+max+"\" style=\"width:60px\" maxLength=\"50\"/></td>";
				//活动日期
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hdrq\" id=\"hdrq_"+xmdm+"_"+max+"\" style=\"width:60px\" onclick=\"return showCalendar('hdrq_"+xmdm+"_"+max+"','ymmdd');\" readOnly=\"true\" /></td>";
				//活动内容
				divHtml+="<td align=\"center\" ><textarea style='word-break:break-all;width:90%' name=\"hdnr\" id=\"hdnr_"+xmdm+"_"+max+"\" rows=\"1\" onfocus=\"changeTextArea(this.id,'focus')\" onblur=\"changeTextArea(this.id,'blur');chLeng(this,500)\"></textarea></td>";
				//活动时间
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"hdsj\" id=\"hdsj_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/>(小时)</td>";
				//申请分
				//divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeydown=\"return onlyNum(this,3)\" onmousedown=\"return onlyNum(this,3)\" maxlength=\"3\" style=\"width : 30px;ime-mode:disabled\"/></td>";
				divHtml+="<td align=\"center\" width=\"15%\"><input type=\"text\" name=\"sqf\" id=\"sqf_"+xmdm+"_"+max+"\" onkeyup=\"checkInputNum(this)\" onblur=\"checkInputNum(this)\" maxlength=\"5\" style=\"width : 30px;ime-mode:disabled;\"/></td>";
				divHtml+="</tr>";
				divHtml+="</tbody>";
				divHtml+="</table>";
				
			$(div_fssq_id).innerHTML = "";
			$(div_fssq_id).innerHTML = divHtml;
			
			$("max_"+xmdm).value = max;
		}
	}
}

//删除分数申请
function delFssq(tag){

	if(tag == "ok"){
		//项目代码
		var xmdm = $("shxm").value;
		//最大值
		var max = $("max_"+xmdm).value;
		
		for(var i=0;i<=max;i++){	
			var cb_id = "cb_"+xmdm+"_"+i;
			var table_id = "tab_"+xmdm+"_"+i;
			
			if($(cb_id) && $(cb_id).checked){
				if(xmdm == "szyq_dshdjzb"){//读书活动
			
					var dsmc_id = "dsmc_"+xmdm+"_"+i;//读书名称
					var dsrq_id = "dsrq_"+xmdm+"_"+i;//读书日期
					
					var url="szgyyq_mypj_stu.do?method=delDshdSqf";
					
					//参数
				 	var parameter = {
						"dsmc":escape($(dsmc_id).value),
						"dsrq":$(dsrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyq_yybdjzb"){//语言表达
				
					var yybdnr_id = "yybdnr_"+xmdm+"_"+i;//语言表达内容
					var xthdrq_id = "xthdrq_"+xmdm+"_"+i;//日期
					
					var url="szgyyq_mypj_stu.do?method=delYybdSqf";
					
					//参数
				 	var parameter = {
						"yybdnr":escape($(yybdnr_id).value),
						"xthdrq":$(xthdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyq_ivtltb"){//IVT论坛
				
					var jztm_id = "jztm_"+xmdm+"_"+i;//讲座题目
					var xthdrq_id = "xthdrq_"+xmdm+"_"+i;//日期
					
					var url="szgyyq_mypj_stu.do?method=delIvtltSqf";
					
					//参数
				 	var parameter = {
						"jztm":escape($(jztm_id).value),
						"xthdrq":$(xthdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyq_xthddjb"){//文体活动
					
					var hdnr_id = "hdnr_"+xmdm+"_"+i;//活动内容
					var xthdrq_id = "xthdrq_"+xmdm+"_"+i;//日期
					
					var url="szgyyq_mypj_stu.do?method=delWthdSqf";
					
					//参数
				 	var parameter = {
						"hdnr":escape($(hdnr_id).value),
						"xthdrq":$(xthdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyc_zznlfzb"){//组织活动
				
					var hdzt_id = "hdzt_"+xmdm+"_"+i;//活动主题
					var hdrq_id = "hdrq_"+xmdm+"_"+i;//活动日期
					
					var url="szgyyq_mypj_stu.do?method=delZznlSqf";
					
					//参数
				 	var parameter = {
						"hdzt":escape($(hdzt_id).value),
						"hdrq":$(hdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
					
				}else if(xmdm == "szyc_shsjfzb"){//社会实践
				
					var hddd_id = "hddd_"+xmdm+"_"+i;//活动地点
					var hdrq_id = "hdrq_"+xmdm+"_"+i;//活动日期
					
					var url="szgyyq_mypj_stu.do?method=delShsjSqf";
					
					//参数
				 	var parameter = {
						"hddd":escape($(hddd_id).value),
						"hdrq":$(hdrq_id).value
					};
					
					jQuery.post(url,parameter,function(result){});
				}
	
				jQuery("#"+table_id).remove();
			}
		}
	}
}

//改变TextArea大小
function changeTextArea(id,lx){
	if($(id)){
		if(lx == "focus"){
			$(id).rows = "5";
		}else{
			$(id).rows = "1";
		}
	}
}

//保存分数申请
function saveFssq(tag){

	if(tag == "ok"){
		//项目代码
		var xmdm = $("shxm").value;
		//Div
		var div_id = "div_"+xmdm;
		//CheckBox Name
		var cb_name = "cb_"+xmdm;
		var num = jQuery("input[name="+cb_name+"]",jQuery("#"+div_id)).length;
		
		var n = 0;
		
		var dsmc = new Array();//读书名称
		var dsrq = new Array();//读书日期
		var dsxd = new Array();//读书心得
		var sfhj = new Array();//是否获奖
		var sqf = new Array();//申请分
		
		var yybdnr = new Array();//语言表达内容
		var xthdrq = new Array();//日期
		
		var jztm = new Array();//讲座题目
		var jcdj = new Array();//进场登记
		var ccdj = new Array();//出场登记
		
		var hdnr = new Array();//活动内容
		var jldj = new Array();//奖励等级
			
		var hdzt = new Array();//活动主题
		var hdrq = new Array();//活动日期
		var hddj = new Array();//活动等级
		
		var hddd = new Array();//活动地点
		var hdsj = new Array();//活动时间
		
		if(num  == 0){
			alertError("如果需要申请分数的话，请至少增加一项");
			return false;
		}else{
			for(var i=0;i<num;i++){
			
				var obj = jQuery("input[name="+cb_name+"]",jQuery("#"+div_id))[i];
				var value = obj.value;
				
				if(xmdm == "szyq_dshdjzb"){//读书活动
				
					var dsmc_id = "dsmc_"+xmdm+"_"+value;//读书名称
					var dsrq_id = "dsrq_"+xmdm+"_"+value;//读书日期
					var dsxd_id = "dsxd_"+xmdm+"_"+value;//读书心得
					var sfhj_id = "sfhj_"+xmdm+"_"+value;//是否获奖
					var sqf_id = "sqf_"+xmdm+"_"+value;//申请分
				
					if($(dsmc_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,书名为空，请确认！");
						$(dsmc_id).focus();
						return false;
					}else if($(dsrq_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,读书日期为空，请确认！");
						$(dsrq_id).focus();
						return false;
					}else if($(dsxd_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,读书心得为空，请确认！");
						$(dsxd_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					dsmc[n] = escape($(dsmc_id).value);
					dsrq[n] = $(dsrq_id).value;
					dsxd[n] = escape($(dsxd_id).value);
					sfhj[n] = escape($(sfhj_id).value);
					sqf[n] = $(sqf_id).value;
					n++;
					
				}else if(xmdm == "szyq_yybdjzb"){//语言表达
				
					var yybdnr_id = "yybdnr_"+xmdm+"_"+value;//语言表达内容
					var xthdrq_id = "xthdrq_"+xmdm+"_"+value;//日期
					var sqf_id = "sqf_"+xmdm+"_"+value;//申请分
				
					if($(yybdnr_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,语言表达内容为空，请确认！");
						$(yybdnr_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,日期为空，请确认！");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					yybdnr[n] = escape($(yybdnr_id).value);
					xthdrq[n] = $(xthdrq_id).value;
					sqf[n] = $(sqf_id).value;	
					n++;
					
				}else if(xmdm == "szyq_ivtltb"){//IVT论坛
				
					var jztm_id = "jztm_"+xmdm+"_"+value;//讲座题目
					var xthdrq_id = "xthdrq_"+xmdm+"_"+value;//日期
					var jcdj_id = "jcdj_"+xmdm+"_"+value;//进场登记
					var ccdj_id = "ccdj_"+xmdm+"_"+value;//出场登记
					var sqf_id = "sqf_"+xmdm+"_"+value;//申请分
				
					if($(jztm_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,讲座题目为空，请确认！");
						$(jztm_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,日期为空，请确认！");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					jztm[n] = escape($(jztm_id).value);
					xthdrq[n] = $(xthdrq_id).value;
					jcdj[n] = escape($(jcdj_id).value);
					ccdj[n] = escape($(ccdj_id).value);
					sqf[n] = $(sqf_id).value;
					n++;
					
				}else if(xmdm == "szyq_xthddjb"){//文体活动
				
					var hdnr_id = "hdnr_"+xmdm+"_"+value;//活动内容
					var xthdrq_id = "xthdrq_"+xmdm+"_"+value;//日期
					var jldj_id = "jldj_"+xmdm+"_"+value;//奖励等级
					var sqf_id = "sqf_"+xmdm+"_"+value;//申请分
				
					if($(hdnr_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,活动内容为空，请确认！");
						$(hdnr_id).focus();
						return false;
					}else if($(xthdrq_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,日期为空，请确认！");
						$(xthdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					hdnr[n] = escape($(hdnr_id).value);
					xthdrq[n] = $(xthdrq_id).value;
					jldj[n] = escape($(jldj_id).value);
					sqf[n] = $(sqf_id).value;	
					n++;
					
				}else if(xmdm == "szyc_zznlfzb"){//组织活动
				
					var hdzt_id = "hdzt_"+xmdm+"_"+value;//活动主题
					var hdrq_id = "hdrq_"+xmdm+"_"+value;//活动日期
					var hddj_id = "hddj_"+xmdm+"_"+value;//活动等级
					var sqf_id = "sqf_"+xmdm+"_"+value;//申请分
				
					if($(hdzt_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,活动主题为空，请确认！");
						$(hdzt_id).focus();
						return false;
					}else if($(hdrq_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,活动日期为空，请确认！");
						$(hdrq_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					hdzt[n] = escape($(hdzt_id).value);
					hdrq[n] = $(hdrq_id).value;
					
					if($(hddj_id).value != ""){
						hddj[n] = escape($(hddj_id).value);
					}else{
						hddj[n] = " ";
					}
					sqf[n] = $(sqf_id).value;	
					n++;
					
				}else if(xmdm == "szyc_shsjfzb"){//社会实践
					var hddd_id = "hddd_"+xmdm+"_"+value;//活动地点
					var hdrq_id = "hdrq_"+xmdm+"_"+value;//活动日期
					var hdnr_id = "hdnr_"+xmdm+"_"+value;//活动内容
					var hdsj_id = "hdsj_"+xmdm+"_"+value;//活动时间
					var sqf_id = "sqf_"+xmdm+"_"+value;//申请分
				
					if($(hddd_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,活动地点为空，请确认！");
						$(hddd_id).focus();
						return false;
					}else if($(hdrq_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,活动日期为空，请确认！");
						$(hdrq_id).focus();
						return false;
					}else if($(hdnr_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,活动内容为空，请确认！");
						$(hdnr_id).focus();
						return false;
					}else if($(sqf_id).value == ""){
						alertError("第"+(parseInt(i)+1)+"行,申请分为空，请确认！");
						$(sqf_id).focus();
						return false;
					}
					
					hddd[n] = escape($(hddd_id).value);
					hdrq[n] = $(hdrq_id).value;
					hdnr[n] = escape($(hdnr_id).value);
					if($(hdsj_id).value != ""){
						hdsj[n] = $(hdsj_id).value;
					}else{
						hdsj[n] = " ";
					}
					sqf[n] = $(sqf_id).value;	
					n++;
				}
			}
			
			jQuery.ajaxSetup({async:false});
			
			if(xmdm == "szyq_dshdjzb"){//读书活动
			
				var url="szgyyq_mypj_stu.do?method=saveDshdSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//参数
			 	var parameter = {
					"dsmc":dsmc.join("!!@@!!"),
					"dsrq":dsrq.join("!!@@!!"),
					"dsxd":dsxd.join("!!@@!!"),
					"sfhj":sfhj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyq_yybdjzb"){//语言表达
			
				var url="szgyyq_mypj_stu.do?method=saveYybdSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//参数
			 	var parameter = {
					"yybdnr":yybdnr.join("!!@@!!"),
					"xthdrq":xthdrq.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyq_ivtltb"){//IVT论坛
			
				var url="szgyyq_mypj_stu.do?method=saveIvtltSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//参数
			 	var parameter = {
					"jztm":jztm.join("!!@@!!"),
					"xthdrq":xthdrq.join("!!@@!!"),
					"jcdj":jcdj.join("!!@@!!"),
					"ccdj":ccdj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyq_xthddjb"){//文体活动
			
				var url="szgyyq_mypj_stu.do?method=saveWthdSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//参数
			 	var parameter = {
					"hdnr":hdnr.join("!!@@!!"),
					"xthdrq":xthdrq.join("!!@@!!"),
					"jldj":jldj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyc_zznlfzb"){//组织活动
			
				var url="szgyyq_mypj_stu.do?method=saveZznlSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//参数
			 	var parameter = {
					"hdzt":hdzt.join("!!@@!!"),
					"hdrq":hdrq.join("!!@@!!"),
					"hddj":hddj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
				
			}else if(xmdm == "szyc_shsjfzb"){//社会实践
			
				var url="szgyyq_mypj_stu.do?method=saveShsjSqf";
				$("divWaiting").style.display="";
				$("divDisable").style.display="";
				
				//参数
			 	var parameter = {
					"hddd":hddd.join("!!@@!!"),
					"hdrq":hdrq.join("!!@@!!"),
					"hdnr":hdnr.join("!!@@!!"),
					"hdsj":hdsj.join("!!@@!!"),
					"sqf":sqf.join("!!@@!!")
				};
				
				jQuery.post(url,parameter,function(result){dcSuccess(result);});
			}
			
			jQuery.ajaxSetup({async:true});
		}
	}else if(tag == "cancel"){
		$("had_edit").value = "no";
	}
}

//初始化申请项目
function defalutSqxm(){
	//项目代码
	var xmdm = $("shxm").value;
	var max = $("max_"+xmdm).value;
	
	if(max == "0"){
		$("btn_add").click();
		$("had_edit").value = "no";
	}
	
	var url="szgyyq_mypj_stu.do?method=getSqxmInfo";
	
	//其他数据
 	var parameter = {
		"xmdm":xmdm
	};

	jQuery("#div_sqxm_info").load(url,parameter,function(){});
}

//显示申请项目详细情况
function showSqxxDetail(xn,xq,xh,xmdm,doType){

	var url = "/xgxt/szgyyq_mypj_stu.do?method=sqxxDetail";
		url+="&xn="+xn;
		url+="&xq="+xq;
		url+="&xh="+xh;
		url+="&xmdm="+xmdm;
		url+="&doType="+doType;
		
	showTopWin(url,'800','600');		
}


//初始化审核项目
function defalutShxm(){
	//项目代码
	alert(1);
	
	var xmdm = $("shxm").value;
	
	var url="szgyyq_mypj_stu.do?method=getSqxmInfo";
	
	//其他数据
 	var parameter = {
		"xmdm":xmdm
	};

	jQuery("#div_shxm_info").load(url,parameter,function(){});
}