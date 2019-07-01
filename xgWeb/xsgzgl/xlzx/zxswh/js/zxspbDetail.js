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
				  showAlert("�밲������һλ��ѯʦ��");
				  return false;
			  }else if(jQuery("#doType").val()=="update"){//ȡ�����е���ѯʦ�Ű࣬��ɾ����ǰ���ڵ��Ű���Ϣ��
				  var pbid = jQuery("#pbid").val();
				  var url = "xlzx_zxspb.do?method=delZxspbById";
				  var parameter ={id:pbid};
				  saveData(url,parameter);
			  }
		}
		var parameter ={};
	     var url=''; 
	     if(jQuery("#doType").val()=="add"){
	     	 //��֤ͬһ��ֻ�������Ű�һ��
		     if(getPbInfoByDate()=="true"){//���Ű�
	     		return false;
	     	}
	     	if(jQuery("input[type='radio'][name=sfCopyPb][value=1]").prop("checked")==true){//�����Ű�
	     		if(jQuery("#pbjssj").val()==''){
	     			showAlert("��ֹʱ�䲻��Ϊ�գ�");
	     			return false;
	     		}
	     		var pbqssj = new Date((jQuery("#pbqssj").val()).replace(/\-/gi,"/")).getTime();
	     		var pbjssj = new Date((jQuery("#pbjssj").val()).replace(/\-/gi,"/")).getTime();
	     		if(pbqssj>=pbjssj){
	     			showAlert("�������ڵ�ǰ�Ű����ڣ�");
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
								showAlert("����ɹ���",{},{"clkFun":function(){
										frameElement.api.opener.loadData();
										iFClose();
								}});
						}else{
							showAlert("����ʧ�ܣ�");
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
		 
	//������ѯʦ��Ϣ
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
	 * ����ʱ����Ű��ʼ��HTML
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
			//Ϊ��ֹҳ�����"null"���ַ�����δȷ������ȡֵǰ�����ж�
			var xm = (temp['xm'] == "null" || !temp["xm"]) ? "":temp["xm"];
			var bmmc = (temp['bmmc'] == "null" || !temp["bmmc"]) ? "":temp["bmmc"];
			var xqmc = (temp['xqmc'] == "null" || !temp["xqmc"]) ? "":temp["xqmc"];
			var xqdm = (temp['xqdm'] == "null" || !temp["xqdm"]) ? "":temp["xqdm"];
			var pbid = (temp['pbid'] == "null" || !temp["pbid"]) ? "":temp["pbid"];
			if(pbid == "" && doType == "view"){
				continue;
			}
			
			//ƴ���ж���
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
			//�Ѱ���ʱ���
			var realSjdList = temp["sjdList"];
			//��realSjdListת�����ַ���,���ڱȽ�ѡ��
			var compareSjdStr = JSON.stringify(realSjdList);
			var sjdLength = sjdList.length;
			//ʱ��ΰ�4��һ������,����ѭ��
			var modcnt =  sjdLength % 4
			var r = parseInt(sjdLength/4);
			if(modcnt != 0){
				r = r+1;
			}
			var sjdOuterTdObj = jQuery("<td></td>");
			//�Ƿ���޸�
			var disabled = "";
			if(doType == "view"){
				disabled = "disabled";
			}
			var sjdTableObj = jQuery("<table width='100%'></table>");
			//��(tr)ѭ��
			for ( var k = 0; k < r; k++) {
				var compareColNum = (k+1)*4
				if(compareColNum > sjdLength){
					compareColNum = sjdLength - k*4;
				}else{
					compareColNum = 4;
				}
				var sjdTrObj = jQuery("<tr></tr>");
				//��(td)ѭ��
				for ( var x = k*4; x < k*4+compareColNum; x++) {
					var checked = "";
					if(compareSjdStr.indexOf(sjdList[x]['sjddm']) != -1){
						checked = "checked";
						var tempsjddm = sjdList[x]['sjddm'];
						//�ж��Ƿ���Ű��ʱ����ѱ�ԤԼ
						if(doType != "view" &&  compareSjdStr.indexOf("yes"+sjdList[x]['sjddm']) != -1 ){
							disabled = "disabled";
						}
					}
					
					var sjdInnerTdObj = jQuery("<td  width='25%' style='text-align:left;border:none'><input name='sjddm' value ='"+sjdList[x]['sjddm']+"' width='80%' type='checkbox'  "+checked+" "+disabled+" />"+sjdList[x]['sjdmc']+"</td>");
					jQuery(sjdTrObj).append(sjdInnerTdObj);
					//����disabled״̬
					if(doType != "view"){
						disabled = "";
					}
				}
				jQuery(sjdTableObj).append(sjdTrObj);
				//����disabled
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
	 *��ȡʱ����Ű��json����
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
	 * ������ѯʦ�Ű���Ϣ
	 * @return
	 */
	function savePbxxSjd(){
		jQuery("#sjddmdiv").empty();
		//�Ƿ�Ϊ�������
		var isAdd = (jQuery("#doType").val()=="add");
		//�Ƿ������Ű�
		var isPlpb = jQuery("[name='sfCopyPb']:checked").val() == "1";
		//�����Ű��������ֹʱ��
		if(isAdd){
			if(isPlpb && jQuery("#pbjssj").val()==''){
				showAlert("��ֹʱ�䲻��Ϊ�գ�");
     			return false;
			}
			var isExistData = jQuery("[name='sjddm']:checked").length > 0;
			if(!isExistData){
				showAlert("ʱ��β���Ϊ�գ�");
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
			 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
		    			 frameElement.api.opener.loadData();
					     iFClose();
					}});
	    	 }else{
	    		 showAlert(data["message"]);
	    		}
			});
		
	}
		