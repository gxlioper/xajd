/**
 * ʡ��������������ͨ��js
 * ������proviceCiyyLocalMain
 * ���������������������json��������{type:"",id:"",flag:"",width:""}
 * type���͹̶�Ϊ��1.add 2.edit 3.view
 * idΪtdʡ����ҵ���ֶ�id
 * flag��1.yxxdz(����ϸ��ַ) 2.wxxdz(����ϸ��ַ)
 * width:
 * @param 
 * @return
 */




/*������Ҫ������ֶ�id,��ȡtd��Ԫ�����*/
function getBdzdParents(obj){
 if(obj.id == "" || obj.id == null){
	 showAlert("δ����ʡ����ҵ���ֶ�id��");
	 return false;
 }
 var parentTdObj = "";

 if(obj.flag == "wxxdz"){
	parentTdObj = jQuery("#"+obj.id).parent();
 }else{
    parentTdObj = jQuery("#"+obj.id);
 }
 if(parentTdObj.length == 0){
	showAlert("ʡ����ҵ���ֶ�id���ò���ȷ��");
	return false;
 }
 return parentTdObj;
}

//�õ�ʡ��Ϣ
function getProviceMap(){
	var url = "comm_provicectiylocal.do?method=getProviceMap";
	var data= {};
	var ProviceMap = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success:function(result){
		    //���δ�Ӻ�̨�õ���Ϣ������false
			if(result!=null||result!="null" || result != "undifined" || result != ""){
				ProviceMap = result;
			}
		}
	});
	return ProviceMap;
}

//�õ�����Ϣ
function getCityMap(provicedm){
	var url = "comm_provicectiylocal.do?method=getCtiyMap";
	var data= {provicedm:provicedm};
	var CityMap = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success:function(result){
		    //���δ�Ӻ�̨�õ���Ϣ������false
			if(result!=null||result!="null" || result != "undifined" || result != ""){
				CityMap = result;
			}
		}
	});
	return CityMap;
}

//�õ�������Ϣ
function getLocalMap(provicedm,citydm){
	var url = "comm_provicectiylocal.do?method=getLocalMap";
	var data= {provicedm:provicedm,citydm:citydm};
	var LocalMap = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success:function(result){
		    //���δ�Ӻ�̨�õ���Ϣ������false
			if(result!=null||result!="null" || result != "undifined" || result != ""){
				LocalMap = result;
			}
		}
	});
	return LocalMap;
	
}

function getView(provicedm){
	var url = "comm_provicectiylocal.do?method=getSsxQcName";
	var data= {provicedm:provicedm};
	var dqmc = null;
	jQuery.ajax({
		type:'post',
		url:url,
		dataType:'json',
		contentType:"application/x-www-form-urlencoded; charset=UTF-8",
		data:data,
		async: false,
		success:function(result){
		    //���δ�Ӻ�̨�õ���Ϣ������false
			if(result!=null||result!="null" || result != "undifined" || result != ""){
				dqmc = result["qcname"];
			}
		}
	});
	return dqmc;
}

//ҳ���ʼ��ƴ��html��������add����edit����view
function appendInitHtml(obj){
	var parentTdObj = getBdzdParents(obj);
	if(parentTdObj == false){
		return false;
	}
	var type = obj.type;
	addhtml(obj,parentTdObj);
	
}


//��������Ҫ����object����{type:"add"...}
function proviceCiyyLocalMain(obj){
	appendInitHtml(obj);
}

//ʡ������change�¼�
function proviceChange(obj,ywobj){
	var provicedm = jQuery(obj).val();
	/*�ж�ʡ������1Ҳ���ǳ�ֵ-"��ѡ��"��ʱ��ֵ����(city)������(local)��Ϊ��ֵ-"��ѡ��",
	   ҵ���ֶ�ֵ��ֵ��
	����ֵ*/
	if(provicedm == '1'){
		jQuery("#"+ywobj.id).val("");
		jQuery("#city").html("<option value='1' selected='selected'>��ѡ��</option>");
		jQuery("#local").html("<option value='1' selected='selected'>��ѡ��</option>");
	}else{
		//�����̨��ȡ������Ϣ,��ֵ�����п�,���ؿ򸳸���ֵ,ҵ���ֶθ���ʡ����
		jQuery("#"+ywobj.id).val(provicedm);
		var provicedm = provicedm.substr(0,2);
		var CityMap = getCityMap(provicedm);
		var CityObj = jQuery("#city");
	    var CityHtml ="<option value='1' selected='selected'>��ѡ��</option>";
	    for(var i=0;i<CityMap.length;i++){
	    	CityHtml += "<option value='"+CityMap[i]['citydm']+"'>"+
	    	CityMap[i]['cityname']+"</option >"
	    }
	    jQuery(CityObj).html(CityHtml);
		jQuery("#local").html("<option value='1' selected='selected'>��ѡ��</option>");
	}
	//ʡ������ֵ�ı䣬�������������������Ӧ������ֵ-"��ѡ��"
	jQuery("#city").val("1");
	jQuery("#local").val("1");
}



//��������change�¼�
function cityChange(obj,ywobj){
	var citydm = jQuery(obj).val();
	if(citydm == '1'){
		jQuery("#"+ywobj.id).val(jQuery("#provice").val());
		jQuery("#local").html("<option value='1' selected='selected'>��ѡ��</option>");
	}else{
		jQuery("#"+ywobj.id).val(citydm);
		var provicedm = citydm.substr(0,2);
		var citydm = citydm.substr(2,2);
		var LocalMap = getLocalMap(provicedm,citydm);
		var LocalObj = jQuery("#local");
	    var LocalHtml ="<option value='1' selected='selected'>��ѡ��</option>";
	    for(var i=0;i<LocalMap.length;i++){
	    	LocalHtml += "<option value='"+LocalMap[i]['localdm']+"'>"+
	    	LocalMap[i]['localname']+"</option >"
	    }
	    jQuery(LocalObj).html(LocalHtml);
	}
	//����������ֵ�ı�,��������Ӧ������ֵ-"��ѡ��"
	jQuery("#local").val("1");
}


//����������change�¼�
function localChange(obj,ywobj){
	var localdm = jQuery(obj).val();
	if(localdm == '1'){
		jQuery("#"+ywobj.id).val(jQuery("#city").val());
	}else{
		jQuery("#"+ywobj.id).val(localdm);
	}
}

//�����û�����ȥ������ʼ��html���󣬰�change�¼���ֱ��ƴ�ӵ���ǰ����ҳ���td��
function addhtml(obj,parentTdObj){
	var ywvalue = jQuery("#"+obj.id).val();
	if(obj.type == "edit" || obj.type == "add"){
		var ProviceMap = getProviceMap();
		if(ProviceMap == false){
			showAlert("���ݿ���ʡ��Ϣ��");
			return false;
		}
		var ProviceHtml = "";
		ProviceHtml =  "ʡ<select id='provice'>";
					if(obj.type == 'edit'){
						ProviceHtml += "<option value='1' >��ѡ��</option>";
					}else{
						ProviceHtml += "<option value='1' selected='selected'>��ѡ��</option>";
					}
		               
		               for(var i=0;i<ProviceMap.length;i++){
		            	   ProviceHtml = ProviceHtml+  "<option value='"+ProviceMap[i]['provicedm']+"'>"+
		            	   ProviceMap[i]['provicename']+"</option >"
		               }
		               ProviceHtml +="</select>";
		var ProviceObj = jQuery(ProviceHtml);
		var CityHtml = "";
		//�ж��Ƿ�Ϊ�޸����Ͳ��Ҹ�ֵ��Ϊ�գ���ֵ������
		if(obj.type == 'edit' &&��ywvalue != null && ywvalue != "" && !isNaN(parseInt(ywvalue))){
			var provicedm = ywvalue.substr(0,2);
			var CityMap = getCityMap(provicedm);
			if(CityMap == false){
				showAlert("���ݿ��޵�����Ϣ��");
				return false;
			}
			CityHtml = "��<select id='city'>"+"<option value='1' >��ѡ��</option>";
		    for(var i=0;i<CityMap.length;i++){
		    	CityHtml += "<option value='"+CityMap[i]['citydm']+"'>"+
		    	CityMap[i]['cityname']+"</option >"
		    }
			 CityHtml +="</select>";
		}else{
			CityHtml = "��<select id='city'>"+
	         "<option value='1' selected='selected'>��ѡ��</option>"+
             "</select>";
		}
		var CityObj = jQuery(CityHtml);
		//var LocalMap = getLocalMap();
		var LocalHtml = "";
		//�ж��Ƿ�Ϊ�޸����Ͳ��Ҹ�ֵ��Ϊ�գ���ֵ�����ֲ��Ҹ�ֵ���м���λ����'0'
		if(obj.type == 'edit' &&��ywvalue != null && ywvalue != "" && !isNaN(parseInt(ywvalue)) && ywvalue.substr(2,2) != '00'){
			var provicedm = ywvalue.substr(0,2);
			var citydm = ywvalue.substr(2,2);
			var LocalMap = getLocalMap(provicedm,citydm);
			LocalHtml ="��<select id='local'><option value='1' >��ѡ��</option>";
		    for(var i=0;i<LocalMap.length;i++){
		    	LocalHtml += "<option value='"+LocalMap[i]['localdm']+"'>"+
		    	LocalMap[i]['localname']+"</option >"
		    }
		    LocalHtml += "</select>";
		}else{
			  LocalHtml = "��<select id='local'>"+
		         "<option value='1' selected='selected'>��ѡ��</option>"+
		         "</select>";
		}
	  
		var LocalObj = jQuery(LocalHtml);
		jQuery(ProviceObj).change(function(){
			proviceChange(this,obj);
		});
		jQuery(CityObj).change(function(){
			cityChange(this,obj);
		});
		jQuery(LocalObj).change(function(){
			localChange(this,obj);
		});
		if(obj.type == "edit"){
			if(obj.type == 'edit' &&��ywvalue != null && ywvalue != "" && 
			 !isNaN(parseInt(ywvalue))){
				jQuery(ProviceObj).val(ywvalue.substr(0,2)+"0000");
				if(ywvalue.substr(2,2) != "00"){
					jQuery(CityObj).val(ywvalue.substr(0,4)+"00");
					if(ywvalue.substr(4,2) != "00"){
						jQuery(LocalObj).val(ywvalue);
					}
				}
			}
		}
		//���������������width,��ֵwidth,����ֱ�Ӹ�ֵ120px
		if(obj.width){
			jQuery(ProviceObj).css({width:obj.width});
			jQuery(CityObj).css({width:obj.width});
			jQuery(LocalObj).css({width:obj.width});
		}else{
			jQuery(ProviceObj).css({width:"120px"});
			jQuery(CityObj).css({width:"120px"});
			jQuery(LocalObj).css({width:"120px"});
		}
		if(obj.flag == "yxxdz"){
			jQuery(parentTdObj).after(ProviceObj,'',CityObj,'',LocalObj);
		}else{
			jQuery(parentTdObj).append(ProviceObj,'',CityObj,'',LocalObj);
		}
		
	}else if(obj.type == 'view'){
		if(ywvalue != null && ywvalue != "" && !isNaN(parseInt(ywvalue))){
			var dqmc = getView(ywvalue);
			if(obj.flag == "yxxdz"){
				jQuery(parentTdObj).after(dqmc)
			}else{
				jQuery(parentTdObj).append(dqmc);
			}
			
		}else{
//			if(ywvalue != null && ywvalue != ""){
//				jQuery(parentTdObj).append(ywvalue);
//			}else{
				return false;
//			}
			
		}
		
		
	}else{
		showAlert("ʡ���ص���ѡ�����ò���ȷ��");
		return false;
	}
	
			
}