

/**
 * ��ʼ����
 * @return
 */

function quePf(){
	
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	var url = "khglKhpf.do?method=quePf&xmid=";
	if(ids.length != 1){
		showAlertDivLayer("��ѡ��һ����Ŀ");
		return false;
	}
	if(rows[0]["sfjs"]=='1'){
		showAlertDivLayer("����Ŀ�����ѽ���������������");
		return false;
	}else if(rows[0]["sfjs"]=='2'){
		showAlertDivLayer("����Ŀ����δ��ʼ������������");
		return false;
	}
	
		var rows = jQuery("#dataTable").getSeletRow();
		url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khlx="+rows[0]["khlx"];
		document.location.href=url;
}

/**
 * ��ʼ����
 */
function quePfe(xmid,khbid,khlx){
	
	var url = "khglKhpf.do?method=quePf&xmid="+xmid+"&khbid="+khbid+"&khlx="+khlx;
	document.location.href=url;
}


/**
 * ����
 * @return
 */
function addPf(){
	var ids = jQuery("#dataTable").getSeletIds();
	var url = "khglKhpf.do?method=addPf&xmid=";
	if(ids.length == 1){
		var rows = jQuery("#dataTable").getSeletRow();
		url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khdxr="+rows[0]["khdxr"]+"&xmszid="+rows[0]["xmszid"];
		if(jQuery("#xxdm").length>0){			
			if((jQuery("#xxdm").val()=='12309' || jQuery("#xxdm").val()=='33333') && jQuery("#khlx").length>0){
				url+="&khlx="+jQuery("#khlx").val();
			}
		}
		showDialog("����",800,520,url,{close:function(){
			if (jQuery("#search_go")){
				jQuery("#search_go").click();
			}
		}});
	}else{
		showAlertDivLayer("��ѡ��һ����Ҫ���˵���Ա");
		return false;
	}
}
//�ϲ���ͬ��һ��ָ��
function autoRowSpan(){
	var xxdm = jQuery("#xxdm").val();
	var colV = 8;
	if(xxdm == "11527"){
		colV = 7;
	}
	var lastValue=""; 
	var value=""; 
	var pos=1; 
	for(var i=1;i<dataTable.rows.length;i++){ 
	value = dataTable.rows[i].cells[colV].innerText;
	if(lastValue == value){ 
	dataTable.rows[i].cells[colV].style.display="none";
	dataTable.rows[i-pos].cells[colV].rowSpan =dataTable.rows[i-pos].cells[colV].rowSpan+1;
	pos++; 
	}else{ 
	lastValue = value; 
	pos=1; 
	} 
	}
}			
/**
 * ���濼�˷���
 * @param obj
 * @param zbmxid	ָ����ϸID
 * @return
 */
function saveKhfs(obj){
	var xmid = jQuery("#xmid").val();
	var khbid = jQuery("#khbid").val();
	var khdxr = jQuery("#khdxr").val();
	var xmszid=jQuery("#xmszid").val();
    var pflx = jQuery("#pflx").val();
	var fs = obj.value;
	var zbmxid = jQuery(obj).attr("zb");
	var max = jQuery(obj).attr("max");
	var min = jQuery(obj).attr("min");
	if (max != "" && fs != "" && Number(fs) > Number(max)){
		showAlertDivLayer("¼��������ܴ������֣�"+max,{},{"clkFun":function(){
			obj.focus(); 
		}});
		return false;
	}
	if (min != "" && fs != "" && Number(fs) < Number(min)){
		showAlertDivLayer("¼��������ܵ�����С�֣�"+min,{},{"clkFun":function(){
			obj.focus();
		}});
		return false;
	}
	clickToTotal();


    jQuery.post("khglKhpf.do?method=saveKhfs",{xmid:xmid,khbid:khbid,khdxr:khdxr,zbmxid:zbmxid,xmszid:xmszid,fs:fs,pflx:pflx},function(data){
        if (data != null){
            showAlert(data["message"]);
        }
    },"json");

}

function saveBz(obj,zbmxid){
	
	var xmid = jQuery("#xmid").val();
	var khbid = jQuery("#khbid").val();
	var khdxr = jQuery("#khdxr").val();
	var bz = obj.value;
	clickToTotal();
	jQuery.post("khglKhpf.do?method=saveBz",{xmid:xmid,khbid:khbid,khdxr:khdxr,zbmxid:zbmxid,bz:bz},function(data){
		if (data){
			showAlert(data["message"]);
		}
	},"json");
	
}

/**
 * ����������飨���������Ի���
 */
function saveYjjy(obj){
	if(checkLenForYjjy(obj,1000)){		
		var xmid = jQuery("#xmid").val();
		var khbid = jQuery("#khbid").val();
		var khdxr = jQuery("#khdxr").val();
		var yjjy = obj.value;
		jQuery.post("khglKhpf.do?method=saveKhYjjy",{xmid:xmid,khbid:khbid,khdxr:khdxr,yjjy:yjjy},function(data){
			if (data){
				showAlert(data["message"]);
			}
		},"json");
	}else{
		return;
	}
};

//������������֤
function checkLenForYjjy(obj,leng){
	var flag = true;
	if(obj.value.length > leng){
		flag = false;
		showAlert("���������������Ϊ"+leng+",���Ѿ���������ȷ�ϣ�",{},{"clkFun":function(){
				obj.focus();
		}});
	}
	return flag;
}


/**
 * ����� �ϡ��¡����Ҽ��¼�
 * @param obj
 */
function toNext(obj,event){
	var event = event || window.event;
	//��   
	if (event.keyCode==37){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index-1).select();
	}
	
	//��      
	if (event.keyCode==38){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.prev()).eq(index).select();
	}
	
	//��   ���� �س�
	if (event.keyCode==39 || event.keyCode==13){
		var inputs = jQuery("#dataTable input:not(:checkbox)");
		var index = inputs.index(jQuery(obj));
		inputs.eq(index+1).select();
	}
	
	//�� 
	if (event.keyCode==40){
		var tr = jQuery(obj).parents("tr");
		var index = jQuery("input:not(:checkbox)",tr).index(jQuery(obj));
		jQuery("input:not(:checkbox)",tr.next()).eq(index).select();
	}
	
}



/**
 * �л�������������Ա
 * @param obj
 * @param sftj
 */
function selectTab(obj,sftj){
	
	var map = getSuperSearch();
	map["xmid"] = jQuery("#xmid").val();
	map["khbid"] = jQuery("#khbid").val();
	map["sftj"] = sftj;
	if (sftj == "2"){
		jQuery("#li_pf").css("display","");
		jQuery("#li_ck").css("display","none");
		jQuery("#li_xz").css("display","none");
		jQuery("#li_qx").css("display","none");
		jQuery("#sftj").val("2");
		gridSetting["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting);
	} else {			
		jQuery("#li_pf").css("display","none");
		jQuery("#li_ck").css("display","");
		jQuery("#li_xz").css("display","");
		jQuery("#li_qx").css("display","");
		jQuery("#sftj").val("1");
		gridSetting1["params"] = map;
		jQuery("#dataTable").initGrid(gridSetting1);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	searchRs();
}

/**
 * ���������� 20170925
 * �ύǰ��֤
 * @param xmid	��Ŀid
 * @param khbid	���˱�id
 * @param khdxr	���˶���
 * @return
 */
function checkIsCanSubmit(xmid,khbid,khdxr){
	
	var bool = false;
	
	jQuery.post("khglKhpf.do?method=checkIsCanSubmit",{xmid:xmid,khbid:khbid,khdxr:khdxr},function(data){
		if (data == "true"){
			bool = true;
		} 
	});
	
	return bool;
}

/**
 * �ύ�ڲ�����
 */
function submitTjInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr) {
    jQuery.ajaxSetup({async:false});
    showConfirmDivLayer("���˱��ύ�󽫲������޸ģ���ȷ��Ҫ�ύ��",{"okFun":function(){
        jQuery.post("khglKhpf.do?method=submitTj",{xmid:xmid,khbid:khbid,khdxr:khdxr,pflx:pflx,xmszid:xmszid,zbmxidArr:zbmxidArr,fsArr:fsArr},
			function(data){
				showAlertDivLayer(data["message"],{},{"clkFun":function(){
					window.location.reload();
					refershParent();
				}});
			},"json");
    }});
    jQuery.ajaxSetup({async:true});
}

/**
 * �ύ
 * @returns {Boolean}
 */
function submitTj(){
	
	var xmid = jQuery("#xmid").val();
	var khbid = jQuery("#khbid").val();
	var khdxr = jQuery("#khdxr").val();
	var xmszid = jQuery("#xmszid").val();
	var xxdm = jQuery("#xxdm").val();
	var pflx = jQuery("#pflx").val();
	//ָ����id����
	var zbmxidArr = [];
	//��ֵ
	var fsArr = [];

    var flag = false;
    //�ǿ���֤
    jQuery(".data-type").each(function(){
        var v = jQuery(this).val();
        if(v == ""||v == null){
            flag = true;
            return false;
        }
        zbmxidArr.push(jQuery(this).attr("zb"));
        fsArr.push(v);
    });
    
    if(flag){
        showAlertDivLayer("����ɿ��˱�������ϸ��֣�");
        return false;
    }
    if(xxdm == "11799"){
	    if(fsArr.length>1){
	    	for (var i = 0 ; i < fsArr.length ; i++){
	    		if(fsArr[0] !=fsArr[i]){
	    			break;
	    		}
	    		if(i == fsArr.length-1 ){
	    			showAlertDivLayer("������ֵ����ȫ����ͬ��");
	    			return false;
	    		}
	    	}
	    }
    }
	//ǰ��δ�ύ��֤
	if (xxdm == "11527"&&pflx == "bzpf"&&jQuery("#sftj").val() != "1"){
		showConfirmDivLayer("������δ�ύ���Ƿ�ǿ�����ͨ����",{"okFun":function () {
			submitTjInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr);
		}});
	}else if (xxdm == "11527"&&pflx == "bzrpf"&&jQuery("#bztjzt").val() != "1"){
		showConfirmDivLayer("��������δ�ύ���Ƿ�ǿ�����ͨ����",{"okFun":function () {
			submitTjInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr);
		}});
	}else {
		submitTjInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr);
	}
}

/**
 * submitNext�ڲ�����
 */
function submitNextInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr,map) {
    jQuery.ajaxSetup({async:false});
    showConfirmDivLayer("���˱��ύ�󽫲������޸ģ���ȷ��Ҫ�ύ��",{"okFun":function(){
        jQuery.post("khglKhpf.do?method=submitTj",{xmid:xmid,khbid:khbid,khdxr:khdxr,xmszid:xmszid,pflx:pflx,zbmxidArr:zbmxidArr,fsArr:fsArr},
			function(data){
				if (data["message"] == "�ύ�ɹ���"){
					jQuery.post("khglKhpf.do?method=submitNext",map,function(data){
						if (data == "null"){
							showAlertDivLayer("��ǰ�������һ�����˶���",{},{"clkFun":function(){
								refershParent();
							}});
						}else{
							var url = "khglKhpf.do?method=xgpf&xmid="+xmid+"&khbid="+khbid+"&khdxr="+data+"&xmszid="+xmszid+"&pflx="+pflx;
							document.location.href=url;
						}
					});
				} else {
					showAlertDivLayer(data["message"]);
				}
        },"json");
    }});
    jQuery.ajaxSetup({async:true});
}

/**
 * �ύ����һ��
 * @return
 */
function submitNext(){
	
	var xmid = jQuery("#xmid").val();
	var khbid = jQuery("#khbid").val();
	var khdxr = jQuery("#khdxr").val();
	var xmszid = jQuery("#xmszid").val();
    var xxdm = jQuery("#xxdm").val();
    var pflx = jQuery("#pflx").val();

	var api = frameElement.api,W = api.opener;
	var map = W.getSuperSearch();
	map["xmid"] = xmid;
	map["khbid"] = khbid;
	map["khdxr"] = khdxr;
	map["xmszid"] = xmszid;
	map["pflx"] = pflx;

    //ָ����id����
    var zbmxidArr = [];
    //��ֵ
    var fsArr = [];

    var flag = false;
    //�ǿ���֤
    jQuery(".data-type").each(function(){
        var v = jQuery(this).val();
        if(v == ""||v == null){
            flag = true;
            return false;
        }
        zbmxidArr.push(jQuery(this).attr("zb"));
        fsArr.push(v);
    });
    if(flag){
        showAlertDivLayer("����ɿ��˱�������ϸ��֣�");
        return false;
    }

	//ǰ��δ�ύ��֤
	if (xxdm == "11527"&&pflx == "bzpf"&&jQuery("#sftj").val() != "1"){
			showConfirmDivLayer("������δ�ύ���Ƿ�ǿ�����ͨ����",{"okFun":function () {
				submitNextInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr,map);
			}});
	}else if (xxdm == "11527"&&pflx == "bzrpf"&&jQuery("#bztjzt").val() != "1"){
			showConfirmDivLayer("��������δ�ύ���Ƿ�ǿ�����ͨ����",{"okFun":function () {
				submitNextInner(xmid,khbid,khdxr,pflx,xmszid,zbmxidArr,fsArr,map);
			}});
	}else {
        jQuery.ajaxSetup({async:false});
        showConfirmDivLayer("���˱��ύ�󽫲������޸ģ���ȷ��Ҫ�ύ��",{"okFun":function(){
            jQuery.post("khglKhpf.do?method=submitTj",{xmid:xmid,khbid:khbid,khdxr:khdxr,xmszid:xmszid,pflx:pflx,zbmxidArr:zbmxidArr,fsArr:fsArr},
                function(data){
                    if (data["message"] == "�ύ�ɹ���"){
                        jQuery.post("khglKhpf.do?method=submitNext",map,function(data){
                            if (data == "null"){
                                showAlertDivLayer("��ǰ�������һ�����˶���",{},{"clkFun":function(){
                                    refershParent();
                                }});
                            }else{
                                var url = "khglKhpf.do?method=addPf&xmid="+xmid+"&khbid="+khbid+"&khdxr="+data+"&xmszid="+xmszid+"&pflx="+pflx;
								if((xxdm=='12309' || xxdm=='33333') && jQuery("#khlx").length>0){
									url+="&khlx="+jQuery("#khlx").val();
								}
                                document.location.href=url;
                            }
                        });
                    } else {
                        showAlertDivLayer(data["message"]);
                    }
                },"json");
        }});
        jQuery.ajaxSetup({async:true});
	}
}

/**
 * ��������
 * @return
 */
function viewPf(){
	var rows = jQuery("#dataTable").getSeletRow();
	var xxdm = jQuery("#xxdm").val();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�鿴�ļ�¼��");
	} else {
        var khdxr = rows[0]["khdxr"];
        var xmszid = rows[0]["xmszid"];
        if(xxdm=="11527"){
            khdxr = rows[0]["xh"];
            xmszid=jQuery("#xmszid").val();
        }
		showDialog("�鿴",800,520,"khglKhpf.do?method=viewPf&xmid="+rows[0]["xmid"]+"&pflx="+jQuery("#pflx").val()+"&xmszid="+xmszid+"&khbid="+rows[0]["khbid"]+"&khdxr="+khdxr);
	}	
}

/**
 * �����ܷ�
 * @return
 */
function clickToTotal(){
	var total = 0;
	jQuery(".data-type").each(function(i,n){
		var m = jQuery(n).attr('lx');
		if("2" == m ){
			total = total-new Number(jQuery(n).val());
		}else{
			total= total+new Number(jQuery(n).val());
		}
	});
	jQuery("#total").html(total.toFixed(2));
}


/**
 * �鿴ҳ���ּܷ���
 * @return
 */
function viewTotal(){
	
	var total = 0;
	
	jQuery(".data-type").each(function(i,n){
		var m = jQuery(n).attr('lx');
		var v = jQuery(n).html();
		if(v==null||v==""){
			v = 0;
		}
		if("2" == m ){
			total= total-new Number(v);
		}else{
			total= total+new Number(v);
		}
		
	});
    jQuery("#total").html(total.toFixed(2));
}

function setZt(cellValue, rowObject){
	var ypcount = rowObject.ypcount;
	var sfjs=rowObject.sfjs;//�����Ƿ����
	var value = "δ����";
	var color;
	if ("2"==sfjs) {
		value="δ��ʼ";
		color = "have";
	} else if("1"==sfjs){
		value="�ѽ���";
		color = "have";
	}else if (cellValue - ypcount == '0') {
		value="������";
		color = "have";
	}else  {
		color = "non";
	}
	if ("non"==color){
		value = "<a  href='javascript:void(0);' onclick='return quePfe(\"" + rowObject.xmid
		+ "\",\"" + rowObject.khbid+ "\",\""+rowObject.khlx+"\");' ><font class='" + color + "'>" + value + "</font></a>";
	} else {
		value = "<font class='" + color + "'>" + value + "</font>";
	}
	 return value;
}

function setYl(cellValue, rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='khbYl(\""+rowObject.khbid+"\",\""+cellValue+"\");'>"+cellValue+"</a>";
}

function khbYl(khbid,khbmc){
	var url="khglKhbgl.do?method=khnryl&khbid="+khbid+"&khbmc="+khbmc;
	showDialog('��������Ԥ��',800,550,url);
}

function getCpcjWord(){
	var khdxrs=[];
	var url="khglKhpf.do?method=getCpcjWord&xmid=";
	
	var rows = jQuery("#dataTable").getSeletRow();
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	 } else if (rows.length > 1){
		 var ids = jQuery("#dataTable").getSeletIds();
		 for ( var int = 0; int < ids.length; int++) {
			 khdxrs.push(rows[int]["xh"]);
		}
		 url="khglKhpf.do?method=getCpcjWordZip&xmid=";
		 url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&value="+khdxrs.toString()+"&xmszid="+rows[0]["xmszid"]+"&xn="+rows[0]["xn"];
	 } else {
		url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khdxr="+rows[0]["xh"]+"&xmszid="+rows[0]["xmszid"]+"&xn="+rows[0]["xn"];
     }
	 	url = addSuperSearchParams(url);
		document.forms[0].action = url;
		document.forms[0].submit();
}

function getStuCptjWord(){
	var url="khglKhpf.do?method=getCpcjWord&xmid=";
	var xmid=jQuery("#xmid").val();
	var khbid=jQuery("#khbid").val();
	var khdxr=jQuery("#khdxr").val();
	var xmszid=jQuery("#xmszid").val();
	url+=xmid+"&khbid="+khbid+"&khdxr="+khdxr+"&xmszid="+xmszid;
	document.forms[0].action = url;
	document.forms[0].submit();
}

function bzsh(){
	var warnMessage="ȷ��Ҫ���ͨ����";
	var xmid=jQuery("#xmid").val();
	var khbid=jQuery("#khbid").val();
	var xmszid = jQuery("#xmszid").val();
	var rows = jQuery("#dataTable").getSeletRow();
	 var ids = jQuery("#dataTable").getSeletIds();
	var pflx = jQuery("#pflx").val();
	 if (rows.length == 0){
		showAlertDivLayer("��ѡ������Ҫ��˵ļ�¼��");
		return false;
	 }
	 for ( var int = 0; int < rows.length; int++) {
	 	if(pflx=="bzpf"){
            if(rows[int]["bzshztmc"]=="���ͨ��"){
                showAlertDivLayer("����ѡ���Ѿ���˵ļ�¼��");
                return false;
            }else{
                if(rows[int]["zpyp"]=="δ�ύ"){
                    warnMessage="��������δ�ύ�ļ�¼���Ƿ�ǿ�����ͨ����";
                    break;
                }
			}
		}else {
            if(rows[int]["bzrshztmc"]=="���ͨ��"){
                showAlertDivLayer("����ѡ���Ѿ���˵ļ�¼��");
                return false;
            }else {
                if(rows[int]["bzyp"]=="δ�ύ"){
                    warnMessage="�а�������δ�ύ�ļ�¼���Ƿ�ǿ�����ͨ����";
                    break;
                }
			}
		}
	}

	showConfirmDivLayer(warnMessage,{"okFun":function(){
		jQuery.post("khglKhpf.do?method=sh",{xmid:xmid,khbid:khbid,xmszid:xmszid,khdxr:ids.toString(),pflx:pflx},function(data){
						showAlertDivLayer(data["message"],{},{"clkFun":function(){
							jQuery("#search_go").click();
						}});
					
		},"json");
	}});	 
}

/**
 * ���ϳ��� �������
 * @returns {boolean}
 */
function cxsh(){
    var warnMessage="ȷ��Ҫ���������";
    var xmid=jQuery("#xmid").val();
    var khbid=jQuery("#khbid").val();
    var xmszid = jQuery("#xmszid").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    var pflx = jQuery("#pflx").val();
    if (rows.length == 0){
        showAlertDivLayer("��ѡ������Ҫ�����ļ�¼��");
        return false;
    }
    for ( var int = 0; int < rows.length; int++) {
        if(pflx=="bzpf"){
            if(rows[int]["bzshztmc"]=="δ���"){
                showAlertDivLayer("����ѡ��δ��˵ļ�¼��");
                return false;
            }else{
                if(rows[int]["bzrshzt"]=="1"){
                    showAlertDivLayer("�����������ͨ�����޷�������");
                    return false;
                }
			}
        }else {
            if(rows[int]["bzrshztmc"]=="δ���"){
                showAlertDivLayer("����ѡ��δ��˵ļ�¼��");
                return false;
            }
        }
    }

    showConfirmDivLayer(warnMessage,{"okFun":function(){
        jQuery.post("khglKhpf.do?method=cx",{xmid:xmid,khbid:khbid,xmszid:xmszid,khdxr:ids.toString(),pflx:pflx},function(data){
            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                jQuery("#search_go").click();
            }});

        },"json");
    }});
}

/**
 * ���ϳ��� ����ѧ������
 * @returns {boolean}
 */
function cxxszp(){
    var warnMessage="ȷ��Ҫ����ѧ��������";
    var xmid=jQuery("#xmid").val();
    var khbid=jQuery("#khbid").val();
    var xmszid = jQuery("#xmszid").val();
    var rows = jQuery("#dataTable").getSeletRow();
    var ids = jQuery("#dataTable").getSeletIds();
    // var pflx = jQuery("#pflx").val();
	var pflx = "xszp";
    if (rows.length == 0){
        showAlertDivLayer("��ѡ������Ҫ�����ļ�¼��");
        return false;
    }
    for ( var int = 0; int < rows.length; int++) {
		if(rows[int]["zpyp"]=="δ�ύ"){
			showAlertDivLayer("����ѡ��ѧ������δ�ύ�ļ�¼��");
			return false;
		}
    }

    showConfirmDivLayer(warnMessage,{"okFun":function(){
        jQuery.post("khglKhpf.do?method=cx",{xmid:xmid,khbid:khbid,xmszid:xmszid,khdxr:ids.toString(),pflx:pflx},function(data){
            showAlertDivLayer(data["message"],{},{"clkFun":function(){
                jQuery("#search_go").click();
            }});

        },"json");
    }});
}

/**
 * ȡ���ύ
 * @return
 */
function cancelTjRecord(){
	var ids = jQuery("#dataTable").getSeletIds();
	var url = "khglKhpf.do?method=cancelTjRecord&xmid=";
	if(ids.length == 1){
		var rows = jQuery("#dataTable").getSeletRow();
		url+=rows[0]["xmid"]+"&khbid="+rows[0]["khbid"]+"&khdxr="+rows[0]["khdxr"]+"&xmszid="+rows[0]["xmszid"];
		showConfirmDivLayer("��ȷ��Ҫȡ�����ύ��¼��",{"okFun":function(){
			jQuery.post(url,{},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}else{
		showAlertDivLayer("��ѡ��һ����Ҫȡ���ύ�ļ�¼��");
		return false;
	}
}

/**
 *���ɰ�����������
 */
function scbzpfmm(){
	showDialog("���ɰ�����������",500,280,"khglKhpf.do?method=scbzpfmm");
}

/**
 * ���ɰ�����������ı���
 */
function scbzpfmmSave () {
	//����ǰ��֤
	if(!checkPassword()){
        return false;
    }

    ajaxSubFormWithFun("scbzpfmmForm", "khglKhpf.do?method=scbzpfmmSave", function(data) {
		showAlert(data["message"]);
    });
}

function checkPassword ()  {
	var flag = true;
	jQuery("input.password").each(
		function () {
			var psd = jQuery(this).val();
			if(psd != ""){
                if(!checkPsw(psd)){
                	flag = false;
                    return false;
                }
                if(!CheckChineseByString(psd)){
                	flag = false;
					return false;
				}
			}
        }
	);
	return flag;
}

/**
 * ���ϳ���ѧԺ�༶�۲�ɼ��ܱ�����
 * @returns {boolean}
 */
function bjzccjzbxz(){
    var url ="khglKhpf.do?method=bjzccjzbxz";

    var xnLength=jQuery("a[name=a_name_xn]").length;
    var xyLength=jQuery("a[name=a_name_xy]").length;
    // var bjLength=jQuery("a[name=a_name_bj]").length;

    if(xnLength != "1"){
        showAlertDivLayer("��ѡ��һ��ѧ���ѯ������");
        return false;
    }

    if(xyLength != "1"){
        showAlertDivLayer("��ѡ��һ��ѧԺ��ѯ������");
        return false;
    }

    setSearchTj();
    url = addSuperSearchParams(url);
    document.forms[0].action = url;
    document.forms[0].submit();
}