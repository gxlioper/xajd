//��������ת
function dcmcLink(cellValue, rowObject) {
	var gypyid = rowObject["gypyid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + gypyid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(gypyid) {
	var url = "gypy.do?method=detail&gypyid=" + gypyid;
	var title = "����������Ϣ";
	showDialog(title,  600, 280,  url);
}
function add() {
	var url = "gypy.do?method=add";
	var title = "������������";
	showDialog(title,600, 280,url);
	jQuery("#dataTable").reloadGrid();
}
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var url = 'gypy.do?method=update&gypyid=' + rows[0]["gypyid"];
		var title = "�޸���������";
		showDialog(title,600, 280, url);
		//jQuery("#dataTable").reloadGrid();
	}
}
//ɾ��
function del() {
	var rows=jQuery("#dataTable").getSeletRow();
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("gypy.do?method=del", {
					values : ids.toString()
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function loadLdxx(){
	jQuery.post('gyglnew_qsgl.do?method=loadLdxx',{lddm:jQuery('#lddm').val()},function(data){
		var qsch = parseInt(data.qsch);
		var ldcs = parseInt(data.ldcs);
		var sfhlc = data.sfhlc;
		var ldxb = data.ldxb;
		var count = 0;
		jQuery('#ch').empty();
		jQuery('#ch').append("<option value=''>--��ѡ��--</option>");
		while(count<ldcs){
			var chdm;
			var chmc;
			if('��' == sfhlc){
				
				if((qsch+count)>= 0){
					chdm = qsch>0 ? qsch+count:qsch+count+1;
					chmc = chdm + "��";
				}else{
					chdm = qsch+count;
					chmc = "B" + Math.abs(chdm) + "��";	
				}
				
			}else{
				chdm = qsch+count;
				chmc = chdm<0 ? "B" + Math.abs(chdm) + "��" : chdm + "��";
			}
			
			var option = "<option value=\"" + chdm + "\">" + chmc + "</option>"
			jQuery('#ch').append(option);
			
			count ++;
		}
		loadQs();
	},'json');
}
function loadQs(){
	jQuery.getJSON('gyglnew_cwgl.do?method=getQshForLddm',{lddm:jQuery('#lddm').val(), ch:jQuery('#ch').val()},function(data){
		jQuery('#qsh').empty();
		jQuery('#qsh').append("<option value=''>--��ѡ��--</option>");
		if(data != null && data.length != 0){
			for(var i=0; i<data.length; i++){
				var option = "<option value=\"" + data[i].qsh + "\">" + data[i].qsh + "</option>";
				jQuery('#qsh').append(option);
			}
		}
	});
}
function loadQsdh(){
	var obj = new Object;
	obj.lddm = jQuery('#lddm').val();
	obj.ch = jQuery('#ch').val();
	obj.qsh = jQuery('#qsh').val();
	jQuery.getJSON('gypy.do?method=loadQsxxdh',obj,function(data){
			var qsdh = data.qsdh;
			jQuery('#qsdh').val(qsdh);
			jQuery('#xydm').val(data.xydm);
	});
}
function checkChar(obj){
	if (obj.value.match(/[\u4e00-\u9fa5]/g)) {
		alertInfo("ֻ�������ַ�,������������!",function (){obj.value="";});

		//return false;
	 }	
}
function save(url,checkId){
	if(!check(checkId)){
		return alertError("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	if(!checkData("�Ѿ����ڴ��������ᣡ")){
		return false;
	}
 	jQuery("#form").ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
	 		 if(data["message"]=="����ɹ���"){
	    		 showAlert(data["message"],{},{"clkFun":function(){
	    				if (parent.window){
	    					refershParent();
	    				}
	    			}});
	    	 }else{
	    		 showAlert(data["message"]);
	    	 }
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}
//��ⳤ��
function checkLength(obj,len){
	var str=jQuery("#"+obj).val();
	alert(str);
     	if(str.replace(/[^\u0000-\u00ff]/g, "**").length > len){	         
     		alertError("����ܴ���"+len+"���ַ���");
     		jQuery("#"+obj).focus();
      		return false;
   		}
}
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
/**
 * ��֤�Ƿ���ڿ���
 * @param ids Ҫ��֤�Ŀؼ�id "-"�ָ�
 * @return
 */
function check(ids){
	var id=ids.split("-");
	for(var i=0;i<id.length;i++){
		var lddm=jQuery("#"+id[i]).val();
		if(lddm==null||""==lddm){
			return false;
		}
	}
	return true;
}

function exportConfig() {
	customExport("gypy.do", exportData);
}
	

// ��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "gypy.do?method=export&pylx=1&dcclbh=" + "gypy.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
/**
 * ��֤��������ʾ
 * @param gypyid ��֤����id
 * @param mes	��ʾ��Ϣ
 * @return
 */
function checkData(mes){
	var isCanDo=true;
	if(mes==""||null==mes){
		mes="�Ѿ��������ݣ�";
	}
 	jQuery("#form").ajaxSubmit({
		url:"gypy.do?method=checkData",
		type:"post",
		async:false,
		dataType:"json",
		success:function(data){
	 		if(data["message"]=="false"||data["message"]==false){
				showAlert(mes);
				isCanDo=false;
			}
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
 	return isCanDo;
}