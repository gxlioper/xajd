/**
 * ������������
 * @return
 */
function checkzs(){
	if(jQuery("#sqyy").val().length > 500){
		showAlertDivLayer("��ͥ����������Ҫ��Դ���������������500��֮�ڣ�");
		return false;
	}
}

/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery.trim(jQuery("#"+id[i]).val()) || jQuery.trim(jQuery("#"+id[i]).text());
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}


//����ѧ�ѻ�ȡ�ȼ������Լ��ظ���֤
function selectXsYzFz(xh,xn,xq,flag){
	var rs = null;
	var data = {xh:xh,flag:flag,xn:xn,xq:xq}
	var url = "hjxf_sq.do?method=selectXsYzFz";
	jQuery.ajax({
	type:'post',
	url:url,
	dataType:'json',
	contentType:"application/x-www-form-urlencoded; charset=UTF-8",
	data:data,
	async: false,
	success:function(result){
			rs = result;
	 }
    });
	if(jQuery.trim(rs['djmc']) == ''){
		jQuery("#knsdj").text('��ͥ���ò�����');
		jQuery("#pkdj").val('��ͥ���ò�����');
	}else{
		jQuery("#knsdj").text(rs['djmc']);
		jQuery("#pkdj").val(rs['djmc']);
	}
	
	var datalist = rs['hjxx'];
	var html = ""
	  html += "<tr>"+ 
		"<th width='10%'>ѧ��</th>"+
		"<th width='10%'>ѧ��</th>"+	
	    "<th width='25%'>����ʱ��</th>"+
		"<th width='20%'>�������(Ԫ)</th>"+
		"<th width='35%'>�����ֹʱ��</th>"+
	    "</tr>";
	if(datalist.length>0){
		for(var i = 0;i < datalist.length;i++){
			html += "<tr width='100%'>"+
				    "<td width='10%'>"+datalist[i]['xn']+"</td>"+
			        "<td width='10%'>"+datalist[i]['xq']+"</td>"+
			        "<td width='25%'>"+datalist[i]['sqsj']+"</td>"+
			        "<td width='20%'>"+datalist[i]['hjje']+"</td>"+
			        "<td width='35%'>"+datalist[i]['jqjzsj']+"</td>"+
			        "</tr>";
		}
	}
	jQuery("#tbody_lsxx").html(html);
	
		if(rs["cfbz"] == "ysj"){
			jQuery("#bccg").hide();
			jQuery("#tjsq").hide();
			showAlert("��ǰѧ��ѧ�ڸ�ѧ�����������¼�������ظ���д���룡");
			return false;
		}else{
				jQuery("#bccg").show();
				jQuery("#tjsq").show();
				
		}
}

/**
 * �������϶��������༭��ͥ���
 * @return
 */
function editJtqk(){
	var xh = jQuery("#xh").val();
	showDialog('��ͥ�������',780,500,'xszz_jtqkdc.do?method=dcxxModify&writeAble=yes&type=update&xh='+xh,{
		close:function(){
			
		}
	});
}

/**
 * ���ؼ�ͥ���������Ϣ
 * @param obj
 * @return
 */
function showJtqk(obj,id){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#"+id).toggle();
}

function reloadWindow(){
	var xh = jQuery("#xh").val();
	document.location.href="xszz_knsrdbjpy.do?method=knssq&xh="+xh;
}