
//�����𷢷ű�
function saveForm(method){
	if(yzForm()){
		var ffsj=jQuery("#ffsj").val();
		var ffje=jQuery("#je").val();
		var yrdwdm=jQuery("#yrbm").val();
		var oldyrbm=jQuery("#oldyrbm").val();
		var oldje=jQuery("#oldje").val();
		//��֤�Ƿ񷢷Ź�����
		var wbh=jQuery("#wbh").val();
		var xh=jQuery("#xh").val();
		var xn=jQuery("#xn").val();
		var yrdwdm=jQuery("#yrbm").val();
		var gwdm=jQuery("#gwdm").val();
		var ffsj=jQuery("#ffsj").val();
		if(!checkFfxx(wbh,xh,xn,yrdwdm,gwdm,ffsj)){
			return false;
		}
		if(!checkCjsx()){
			return false;
		}
		// if(checkJe(ffje,yrdwdm,ffsj,oldje,oldyrbm) == true){
				var url = "qgzx_jfcjgl_cjff.do?method="+method+"&type=save";
				removeDisabled();
				ajaxSubFormWithFun("demoForm",url,function(data){
					alertInfo(data["message"],function(ty){
						if(ty=="ok"){
							//window.parent.searchRs();
							var api = frameElement.api,W = api.opener;
							W.searchRs();
							btn_close();
						}
					});
					addDisabled();
				});
		// }
	}
}
function removeDisabled(){
	//�ύʱ disabled��ֵ���ύ ��������ȥ��
	jQuery("#xn").removeAttr("disabled");
	jQuery("#yrbm").removeAttr("disabled");
	jQuery("#gwdm").removeAttr("disabled");
	jQuery("#ffsj").removeAttr("disabled");
}
function addDisabled(){
	//jQuery("#gwdm").removeAttr("disabled");
	//jQuery("#ffsj").removeAttr("disabled");
}
function checkFfxx(wbh,xh,xn,yrdwdm,gwdm,ffsj){
	var isok=false;
	jQuery.ajaxSetup({async:false});
	jQuery.post("qgzx_jfcjgl_cjff.do?method=checkFfxx",
			{wbh:wbh,xh:xh,xn:xn,yrdwdm:yrdwdm,gwdm:gwdm,ffsj:ffsj},
			function(result){
				if(result!="false"){
					var ts="��ѧ����<font color='blue'>"+xn+"</font>ѧ��<font color='blue'>"+ffsj+"</font> ���������Ѵ���ͬ��λ������Ϣ�����飡";
					alertInfo(ts,function(tag){
						if(tag=="ok"){
							isok=false;
						}
					});
				}else{
					isok=true;
				}
			}
		);
	jQuery.ajaxSetup({async:true});
	return isok;
}
//��֤���ύ��Ϣ
function yzForm(){
	var result =false;
	var xh = jQuery("#xh").val();
	var xn = jQuery("#xn").val();
	var xm = jQuery("#xm").val();
	var gs = jQuery("#gs").val();
	var je = jQuery("#je").val();
	var gwdm = jQuery("#gwdm option:selected").val();
	var ffsj = jQuery("#ffsj").val();
	var bz = jQuery("#bz").val();
	if(xh==null||""==xh){
		alertInfo("ѧ�Ų��ܿգ�");
	}else if(xn==null||""==xn){
		alertInfo("ѧ�겻�ܿգ�");
	}else if(gs==null||""==gs){
		alertInfo("��ʱ���ܿգ�");
	}else if(je==null||""==je){
		alertInfo("���ܿգ�");
	}else if(gwdm==null||""==gwdm){
		alertInfo("��ѡ���λ���ƣ�");
	}else if(ffsj==null||""==ffsj){
		alertInfo("����д��𷢷�ʱ�䣡");
	}else if(isNaN(gs)){
		alertInfo("��ʱ���������֣�");
	}else if(isNaN(je)){
		alertInfo("�����������֣�");
	}else if(bz.length>2000){
		alertInfo("��ע���ܳ���1000�֣�");
	}else{
		result =true;
	}
	return result;
}
function checkJe(ffje,yrdwdm,nd,oldje,oldyrbm){
	var isok=false;
	jQuery.ajaxSetup({async:false});
	jQuery.ajax({
		url:"qgzx_jfcjgl_cjff.do?method=checkJe",
		cache:false,
		async:false,
		data:{ffje:ffje,yrdwdm:yrdwdm,nd:nd,oldje:oldje,oldyrbm:oldyrbm},
		success:function(data){
			if(data["message"]!="true"){
				alertInfo(data["message"]);
				isok=false;
			}else{
				isok=true;
			}
		},
		dataType:"json"}
	);
	jQuery.ajaxSetup({async:true});
	return isok;
/*	jQuery.post("qgzx_jfcjgl_cjff.do?method=checkJe",{ffje:ffje,yrdwdm:yrdwdm,nd:nd,oldje:oldje},function(data){
		if(data["message"]!="true"){
			alertInfo(data["message"]);
			isok= false;
		}else{
			isok= true;
		}
	},"json");*/
}
function checkCjsx(){ 
	var cjsx=jQuery("#cjsx").text();
	if(cjsx==""||!cjsx){
		return true;
	}
	var je=jQuery("#je").val();
	if(parseInt(je)<=parseInt(cjsx)){
		return true;
	}
	alertInfo("���<font color='blue'>"+je+"</font>���ܳ�����������<font color='blue'>"+cjsx+"</font>",function(tag){
		if(tag=="ok"){
			return false;
		}
	});
}
function showCjsx(){
	jQuery("#cjsxTr").show();
}
function hideCjsx(){
	jQuery("#cjsxTr").hide();
}
function getCjsx() {
	var xn = jQuery("#xn").val();
	var bmdm = jQuery("#yrbm").val();
	var gwdm = jQuery("#gwdm").val();
	// alert(xn+":"+bmdm+":"+gwdm);
	jQuery.ajax( {
		url : "qgzx_jfcjgl_cjff.do?method=getCjsxForGw",
		dataType : "json",
		data : {
			xn : xn,
			bmdm : bmdm,
			gwdm : gwdm
		},
		success : function(data) {
			if (data) {
				showCjsx();
			} else {
				hideCjsx();
			}
			jQuery("#cjsx").text(data);
		}
	});
    jQuery.ajax( {
        url : "qgzx_jfcjgl_cjff.do?method=getSxForGw",
        dataType : "json",
        data : {
            xn : xn,
            bmdm : bmdm,
            gwdm : gwdm
        },
        success : function(data) {
            jQuery("#sx").text(data);
            jQuery("#sx").val(data);

        }
    });
}
function xhLink(cellValue,row){
	return "<a href='javascript:void(0);' class='name' onClick='seeInfo(\""+row["wbh"]+"\")'>"+cellValue+"</a>";
}
function add(){
	
	showDialog('��ӳ�𷢷���Ϣ', 760, 505, 'qgzx_jfcjgl_cjff.do?method=zjCjff');
}
function searchRs(){
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function seeInfo(wbh){
	//flag�鿴��ʾ
	showDialog("�鿴��𷢷���Ϣ",650,430,"qgzx_jfcjgl_cjff.do?method=xgCjff&flag=see&wbh="+wbh,null);
	//showWindow("�鿴��𷢷���Ϣ",650,360,"qgzx_jfcjgl_cjff.do?method=xgCjff&flag=see&wbh="+wbh,null);
}
function btn_close(){
	frameElement.api.close();	
}
//���ز�ѯ���
function query(){
	var map ={};
	var array = jQuery("#myForm").serializeArray();
		jQuery(array).each(function(index) {
			map[jQuery(this).attr("name")] = jQuery(this).val();
	 });
	jQuery("#dataTable").reloadGrid(map);
}
//�޸�һ����¼
function update(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		alertInfo("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		showDialog('�޸ĳ�𷢷���Ϣ', 760, 505, 'qgzx_jfcjgl_cjff.do?method=xgCjff&wbh='+rows[0]["wbh"]);
		//refreshForm("qgzx_jfcjgl_cjff.do?method=xgCjff&wbh="+rows[0]["wbh"]);
	}
}
//ɾ����¼
function deletes(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		alertInfo("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		var check = false;
		var rows = jQuery("#dataTable").getSeletRow();
		jQuery.each(rows,function(i){
			var sjbs = rows[i]['sjbs'];
			if(sjbs=='1'){
				alertInfo("��ѡ��ĵ�"+(parseInt(i)+1)+"����¼����𷢷š��ύ�����ݣ�����ɾ����");
				check = false;
				return false;
			}else{
				check = true;
			}
		});
		if(!check){
			return false;
		}
		//alert(check);
		if(check){
			confirmInfo("��ȷ��Ҫɾ��"+ids.length +"����¼��?",function(ty){
				//alert(ty);
				if(ty=="ok"){
					jQuery.post("qgzx_jfcjgl_cjff.do?method=scCjff",{values:ids.toString()},function(data){
						alertInfo(data["message"]);
						jQuery("#dataTable").reloadGrid();
					},'json');
				}
			});
		}
		
	}
}
function setSfkxg(){
	var sjly=jQuery("#sjly").val();
	if(sjly=="1"){
		jQuery("#xn").attr("disabled","disabled");
		jQuery("#yrbm").attr("disabled","disabled");
		jQuery("#gwdm").attr("disabled","disabled");
		jQuery("#ffsj").attr("disabled","disabled");
	}
}
//��Ⱥ����˲��� ������ȡ��λ����
//��Ⱥ����˲��� ������ȡ��λ����
function getGwmc(){
	var yrbm = jQuery("#yrbm  option:selected").val();
	var xn = jQuery("#xn option:selected").val();
	var xq = jQuery("#xq").val();
	xq = (!xq)?"":xq;
	var parameter = {yrbm:yrbm,xn:xn,xq:xq}
	jQuery("#gwdm").empty();
	var oldgwdm=jQuery("#oldgwdm").val();
	//alert(jQuery("#gwmc").html());
	jQuery.ajax({
		   type: "POST",
		   url: "qgzx_jfcjgl_cjff.do?method=getGwmc",
		   dataType:"json",
		   data: parameter,
		   success: function(result){
				var html="";
			   jQuery.each(result,function(i){
				   html+="<option value='"+result[i].gwdm+"'";
				  // alert(result[i].gwdm+":"+oldgwdm+":"+(result[i].gwdm==oldgwdm));
				   if(result[i].gwdm==oldgwdm){
					   html+=" selected='true'";
				   }
				   html+=">";
				   html+=result[i].gwmc;
				   html+="</option>";
		       });
			   jQuery("#gwdm").append(html);
			   getCjsx();
		   }
		});
}
function checkStudent(){
	showWindow('��ѡ��һ��ѧ��',680,450,'xsxx_xsgl.do?method=showStudents&goto=qgzx_jfcjgl_cjff.do?method=zjCjff');
}

function qgjgwhExportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport("qgzx_jfcjgl_qgjgwh.do", qgjgwhExportData);
}
	

	
// ��������
function qgjgwhExportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "qgzx_jfcjgl_cjff.do?method=qgjgwhExportData&dcclbh=" + "qgzx_jfcjgl_qgjgwh.do";//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��������
function jsCjJe(){
	var gs=jQuery("#gs").val();
	var cjbz=jQuery("#cjbz").val();
	var jqJe=jQuery("#je");
	if(gs == null || gs == "" || cjbz == null || cjbz == ""){
		jqJe.val("0");
		return false;
	}
	jqJe.val(parseInt(gs)*parseInt(cjbz));
}

//������ҵ��ѧ �����걨��
function cjffcxExportData_10022() {
	var ndNum = jQuery("a[name=a_name_nd]").length;
	if(ndNum != 1){
		showAlertDivLayer("��ѡ��һ��������ȣ�");
		return false;
	}
	var yfs = jQuery("a[name=a_name_yf]");
	var yfNum = yfs.length;
	if(yfNum > 0){
		if(yfNum > 1){
			var yfNewArr = new Array();
			for(var i = 0; i < yfNum; i++){
				// ���滻a_id_�õ�"01","02"..."10","11","12"�����滻��õ�"1","2"..."10","11","12"
				yfNewArr[i] = yfs.eq(i).attr("id").replace("a_id_","").replace(/0(\d)/g, "$1");
			}
			var count = 0; // ����
			var yfNewStr = yfNewArr.join("!!@@!!") + "!!@@!!"; // �����·���ɵ��ַ���
			for(var i = 0; i < yfNewArr.length; i++){
				// ����ʱ���ѵ�ǰ���·ݼ�1��Ȼ���ڡ������·���ɵ��ַ������в��ң�û�ҵ��Ļ���������1
				if(yfNewStr.indexOf((parseInt(yfNewArr[i]) + 1) + "!!@@!!") < 0){
					count++;
				}
			}
			if(count != 1){
				showAlertDivLayer("��ѡ�������ķ����·ݣ�");
				return false;
			}
		}
	}else{
		showAlertDivLayer("��ѡ������һ�������·ݣ�");
		return false;
	}
	setSearchTj();//���ø߼���ѯ����
	var url = "qgzx_jfcjgl_cjff.do?method=getCjffSbbDclist";
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

jQuery(function(){
    jQuery("#je").attr('readonly','readonly');
    console.log('loading');
    jQuery("#gs").change(function(){
		var sx = jQuery("#sx").val();
        console.log('sx:'+sx);
        console.log('gs:'+ this.value);
		var money = this.value * sx;
        jQuery("#je").val(money);
	});
});