function getSelectedZgh(){
     		var zghs = "";
			var flag = false;
			jQuery("input[type='checkbox'][name=zxsBoxList]:checked").each(function(index){
				if(flag){
					zghs += ",";
				}else{
					flag = true;
				}
				zghs += jQuery(this).val();
			});
     		return zghs;
     }
     
        
     function saveZxsPbInfo(){
     	var zghs = getSelectedZgh();
		if(zghs==""){
			  if(jQuery("#doType").val()=="add"){
				  showAlert("请安排至少一位咨询师！");
				  return false;
			  }else if(jQuery("#doType").val()=="update"){//取消所有的咨询师排班，则删除当前日期的排班信息。
				  var pbid = jQuery("#pbid").val();
				  var url = "xlzx_zxspb.do?method=delZxspbById";
				  var parameter ={id:pbid};
				  saveData(url,parameter);
			  }
		}
		var parameter ={};
	     var url=''; 
	     if(jQuery("#doType").val()=="add"){
	     	 //验证同一天只能新增排班一次
		     if(getPbInfoByDate()=="true"){//已排班
	     		return false;
	     	}
	     	if(jQuery("input[type='radio'][name=sfCopyPb][value=1]").prop("checked")==true){//批量排班
	     		if(jQuery("#pbjssj").val()==''){
	     			showAlert("截止时间不得为空！");
	     			return false;
	     		}
	     		var pbqssj = new Date((jQuery("#pbqssj").val()).replace(/\-/gi,"/")).getTime();
	     		var pbjssj = new Date((jQuery("#pbjssj").val()).replace(/\-/gi,"/")).getTime();
	     		if(pbqssj>=pbjssj){
	     			showAlert("不得早于当前排班日期！");
	     			return false;
	     		}	
	     		url="xlzx_zxspb.do?method=saveBatchZxspbInfo&doType=add&startDate="+jQuery("#pbqssj").val()+"&endDate="+jQuery("#pbjssj").val();
	     	}else{
	     		url="xlzx_zxspb.do?method=saveZxspbInfo&doType=add";
	     	}
	     }else if(jQuery("#doType").val()=="update"){
	     	url="xlzx_zxspb.do?method=updateZxspbInfo&doType=update";
	     	parameter["id"]=jQuery("#pbid").val();
	     }
	     parameter["bz"]=jQuery("#bz").val();
	     parameter["datazt"]=1;
	     parameter["pbdate"]=jQuery("#date").val();
	     parameter["zgh"]=zghs;
	     saveData(url,parameter);
     }
     
     
     function saveData(url,parameter){
    	 jQuery.ajaxSetup({async : false});
			jQuery.post(url,parameter,function(data){
					if(data=="true"){
								showAlert("保存成功！",{},{"clkFun":function(){
										frameElement.api.opener.loadData();
										iFClose();
								}});
						}else{
							showAlert("保存失败！");
							return false;
						}
			});
		 jQuery.ajaxSetup({async : true});
     } 
     
     function getPbInfoByDate(){
     	var result = '';
     	var url="xlzx_zxspb.do?method=getPbInfoByDate";
     	 jQuery.ajaxSetup({async : false});
			jQuery.post(url,{pbdate:$("date").value},function(data){
						result = data;
			},'json');
		 jQuery.ajaxSetup({async : true});
		 return result;
	}
		 
	//加载咨询师信息
	function loadZxsxx(){
		var zxsZgh=document.getElementsByName('zxsZgh');
		var zxsXm=document.getElementsByName('zxsXm');
		var zxsXb=document.getElementsByName('zxsXb');
		var zxsBmmc=document.getElementsByName('zxsBmmc');
		var zxsCheckboxzt=document.getElementsByName('zxsCheckboxzt');
		var checkFlag = false;
		var len=zxsZgh.length;
		var html="";
		for(i=1;i<=len;i++){
			html+="<div class='demo_data2'>";
			html+="<input type='checkbox' "+zxsCheckboxzt[i-1].value+" name='zxsBoxList' id='zxs_"+i+"'  value='"+zxsZgh[i-1].value+"'  ><label style='cursor:pointer' for='zxs_"+i+"'>"+zxsXm[i-1].value;
			if(zxsXb[i-1].value!=null && zxsXb[i-1].value!=''){
				html+="["+zxsXb[i-1].value+"]";
			}
			if(zxsBmmc[i-1].value!=null && zxsBmmc[i-1].value!=''){
				html+="["+zxsBmmc[i-1].value+"]";
			}
			html+="["+zxsZgh[i-1].value+"]";
			html+="</label></div>";
		}
		$("zxsInfos").innerHTML=html;
	}
	

	function init(){
		sfqyCopyPb();
		loadZxsxx();
		if($("doType").value!="add"){
			var zghs = $("zghs").value;
			var zghList = zghs.split(",");
			for(var i=0;i<zghList.length;i++){
				jQuery("input[type='checkbox'][name=zxsBoxList][value='"+zghList[i]+"']").attr("checked",true);
			}
			if($("doType").value=="view"){
				jQuery("input[type='checkbox'][name=zxsBoxList]").attr("disabled",true);
			}
		}
		
		if($("doType").value=="view"){
			jQuery("#buttonSave").hide();
		}
	}
	
	function sfqyCopyPb(){
		if(jQuery("input[type='radio'][name=sfCopyPb][value=1]").prop("checked")==true){
			jQuery("th[name=copyQzrqName]").show();
			jQuery("td[name=copyQzrqName]").show();
		}else if(jQuery("input[type='radio'][name=sfCopyPb][value=2]").prop("checked")==true){
			jQuery("th[name=copyQzrqName]").hide();
			jQuery("td[name=copyQzrqName]").hide();
		}
	}
	
	/**
	 * 创建时间段排班初始化HTML
	 * @return
	 */
	function creareSjdPbHtml(){
		var data = getSjdPbData();
		var dataList = data["dataList"];
		var xqList = data["xqList"];
		var sjdList = data["sjdList"];
		var tbodyObj = jQuery("#databody");
		var doType = jQuery("#doType").val();
		for ( var i = 0; i < dataList.length; i++) {
			var temp = dataList[i];
			//为防止页面出现"null"空字符串，未确定变量取值前都先判断
			var xm = (temp['xm'] == "null" || !temp["xm"]) ? "":temp["xm"];
			var bmmc = (temp['bmmc'] == "null" || !temp["bmmc"]) ? "":temp["bmmc"];
			var xqmc = (temp['xqmc'] == "null" || !temp["xqmc"]) ? "":temp["xqmc"];
			var xqdm = (temp['xqdm'] == "null" || !temp["xqdm"]) ? "":temp["xqdm"];
			var pbid = (temp['pbid'] == "null" || !temp["pbid"]) ? "":temp["pbid"];
			if(pbid == "" && doType == "view"){
				continue;
			}
			
			//拼接行对象
			var trobj = jQuery("<tr></tr>");
			var tdxm = jQuery("<td>"+xm+"<input type='hidden' name='zghs' value='"+temp['zgh']+"' /></td>");
			var tdbmmc = jQuery("<td>"+bmmc+"</td>");
			jQuery(trobj).append(tdxm).append(tdbmmc);
			if(jQuery("#xxdm").val() == "10026"){
				if(doType == "view"){
					jQuery(trobj).append("<td>"+xqmc+"</td>");
				}else{
					var selectObj = jQuery("<select name='xqdm'></select>");
					for ( var j = 0; j < xqList.length; j++) {
						jQuery(selectObj).append("<option value='"+xqList[j]["dm"]+"'>"+xqList[j]["xqmc"]+"</option>");
					}
					jQuery(selectObj).find("option[value='"+xqdm+"']").attr("selected",true);
					var xqObj = jQuery("<td></td>").append(selectObj);
					jQuery(trobj).append(xqObj);
				}
			}
			//已安排时间段
			var realSjdList = temp["sjdList"];
			//将realSjdList转换成字符串,用于比较选中
			var compareSjdStr = JSON.stringify(realSjdList);
			var sjdLength = sjdList.length;
			//时间段按4个一行排列,两个循环
			var modcnt =  sjdLength % 4
			var r = parseInt(sjdLength/4);
			if(modcnt != 0){
				r = r+1;
			}
			var sjdOuterTdObj = jQuery("<td></td>");
			//是否可修改
			var disabled = "";
			if(doType == "view"){
				disabled = "disabled";
			}
			var sjdTableObj = jQuery("<table width='100%'></table>");
			//行(tr)循坏
			for ( var k = 0; k < r; k++) {
				var compareColNum = (k+1)*4
				if(compareColNum > sjdLength){
					compareColNum = sjdLength - k*4;
				}else{
					compareColNum = 4;
				}
				var sjdTrObj = jQuery("<tr></tr>");
				//列(td)循环
				for ( var x = k*4; x < k*4+compareColNum; x++) {
					var checked = "";
					if(compareSjdStr.indexOf(sjdList[x]['sjddm']) != -1){
						checked = "checked";
						var tempsjddm = sjdList[x]['sjddm'];
						//判断是否该排班的时间段已被预约
						if(doType != "view" &&  compareSjdStr.indexOf("yes"+sjdList[x]['sjddm']) != -1 ){
							disabled = "disabled";
						}
					}
					
					var sjdInnerTdObj = jQuery("<td  width='25%' style='text-align:left;border:none'><input name='sjddm' value ='"+sjdList[x]['sjddm']+"' width='80%' type='checkbox'  "+checked+" "+disabled+" />"+sjdList[x]['sjdmc']+"</td>");
					jQuery(sjdTrObj).append(sjdInnerTdObj);
					//重置disabled状态
					if(doType != "view"){
						disabled = "";
					}
				}
				jQuery(sjdTableObj).append(sjdTrObj);
				//充置disabled
				if(doType != "view"){
					disabled = "";
				}
			}
			jQuery(sjdOuterTdObj).append(sjdTableObj);	
			jQuery(trobj).append(sjdOuterTdObj);
			jQuery(tbodyObj).append(trobj);
		}
		
	}
	
	/**
	 *获取时间段排班的json数据
	 * @return
	 */
	function getSjdPbData(){
		var rs = null;
		var url = "xlzx_zxspb.do?method=initSjdPb";
		jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:'pbdate='+jQuery("#date").val(),
		async: false,
		success:function(result){
			rs = result;
		 }
	    });
		return rs;
	}
	
	/**
	 * 保存咨询师排班信息
	 * @return
	 */
	function savePbxxSjd(){
		jQuery("#sjddmdiv").empty();
		//是否为保存操作
		var isAdd = (jQuery("#doType").val()=="add");
		//是否批量排班
		var isPlpb = jQuery("[name='sfCopyPb']:checked").val() == "1";
		//批量排班必须填充截止时间
		if(isAdd){
			if(isPlpb && jQuery("#pbjssj").val()==''){
				showAlert("截止时间不得为空！");
     			return false;
			}
			var isExistData = jQuery("[name='sjddm']:checked").length > 0;
			if(!isExistData){
				showAlert("时间段不可为空！");
     			return false;
			}
			
		}
		var sjddmArray = new Array();
		jQuery("#databody > tr").each(function(i,n){
			var sjdCheckObj = jQuery(n).find("[name='sjddm']:checked");
			var sjdChecklen = sjdCheckObj.length;
			var sjdstr = "";
			if(sjdChecklen > 0){
				jQuery(sjdCheckObj).each(function(x,o){
					sjdstr +=o.value;
					if(x != sjdChecklen-1){
						sjdstr +=";";
					}
				});
				
			}
			if(sjdstr == ""){
				sjdstr = "none";
			}
			sjddmArray.push(sjdstr);
		});
		for ( var j = 0; j < sjddmArray.length; j++) {
			jQuery("#sjddmdiv").append("<input type='hidden' name='sjdm' value='"+sjddmArray[j]+"' />");
		}
		var url = "xlzx_zxspb.do?method=savePbxx";
		ajaxSubFormWithFun("zxspbForm", url, function(data) {
			 if(data["message"]=="保存成功！"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
		    			 frameElement.api.opener.loadData();
					     iFClose();
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			});
		
	}
		