var DCCLBH = "xszz_knsrd_sq.do";//dcclbh,�������ܱ��
/**
 * ���ؼ�ͥ���������Ϣ
 * @param obj
 * @return
 */
function showJtqk(obj){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		var className = jQuery(obj).attr("class");
		var newClass = className == "up" ? "down" : "up";

		jQuery(obj).attr("class",newClass);
		jQuery("#t_jtqk").toggle();
	}else{
		showAlertDivLayer("����ѡ��ѧ����");
	}
}

/**
 * �����������϶���ʷ��¼
 * @param obj
 * @return
 */
function showLsjl(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_rdlsjl").toggle();
}

/**
 * ��������������
 * @return
 */
function saveKnssq(saveType){
    if("12389"==jQuery("#xxdm").val()) {
        var sqlydmIds = "";
        var checkSqlydm = "";
        jQuery("input[name=sqly_ckb]:checked").each(function(){
            sqlydmIds += jQuery(this).val()+ "," ;
        });
        if(sqlydmIds.length>0){
            sqlydmIds = sqlydmIds.substring(0, sqlydmIds.length-1);
            checkSqlydm ="1";
        }
        if (jQuery.trim(checkSqlydm) == ""){
            showAlert("��ѡ���������ɣ�");
            return false;
        }
    }

	if("10742"==jQuery("#xxdm").val()) {		
		var sqlydmIds = "";
		var checkSqlydm = "";
		jQuery("input[name=sqlydm]:checked").each(function(){
			sqlydmIds += jQuery(this).val()+ "," ;
		});
		if(sqlydmIds.length>0){
			sqlydmIds = sqlydmIds.substring(0, sqlydmIds.length-1);
			checkSqlydm ="1";
		}
		if (jQuery.trim(checkSqlydm) == ""){
			showAlert("�뽫��������д������");
			return false;
		}
	}
	if("10344"==jQuery("#xxdm").val()){
		if(jQuery(".MultiFile-label").length == 0){
			showAlert("������Ϣ����Ϊ�գ�");
			return false;
		}
	}
	if (!checkMustNotNull() || jQuery("#xh").val() == ""){
		showAlert("�뽫��������д������");
		return false;
	}
	
	var shzt = jQuery("#shzt").val();
	var isopen = jQuery("#isopen").val();
	
	if(isopen==null||isopen==''){
		showAlert('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
	if ("false" == isopen && '3'!=shzt){
		showAlert("��ǰδ�������������룬����ϵ����Ա��");
		return false;
	}
	
	
	//�Ƿ񵯳���ͥ���������дҳ��
	var openJtqk = jQuery("#openJtqk").val();

	if ("true" == openJtqk){
		var xh = jQuery("#xh").val();

		showAlert("������д��ͥ��������",{},{"clkFun":function(){
			editJtqk();
		}});
		return false;
	}
	
    var sqsftxdc=jQuery("#sqsftxdc").val();
    var xxdm=jQuery("#xxdm").val();
	if(sqsftxdc=='1' && (xxdm=='12861' || xxdm=='10718' || xxdm=='11318' || xxdm=='70002')){
		if(null==jQuery("#ylzd1").val()||""==jQuery("#ylzd1").val()){
			showAlert("���ѵ��β���Ϊ��,��ѡ��");
			return false;
		}
	}
	if(xxdm=='12861' || '10335'==xxdm || '12389'==xxdm ){
	   if(jQuery(".MultiFile-label").length<=0){
		   showAlert("���ϴ�������");
			return false;
	   }
	}
	//����ʦ����ѧ���Ի�
	if(xxdm == '10346'){
		var jtknlx = jQuery("#ylzd9").val();
		var gdxfplx = jQuery("#ylzd5").val();
		if(jtknlx == '' || jtknlx == null || gdxfplx == '' || gdxfplx == null){
			showAlert("��Ѵ�*������д������");
			return false;
		}
	}
	var shzt = jQuery("#shzt").val();
	var isopen = jQuery("#isopen").val();
	
	if("submit"== saveType && "3"!=shzt && "false" == isopen){
		showAlert("��ǰδ�������������룬����ϵ����Ա��");
		return false;
	}
	
	if("10742"==xxdm ) {
		var url = "xszz_knsrd.do?method=saveKnssq&type="+saveType+"&shzt="+shzt+"&sqlydm="+sqlydmIds;
	}else if("10277"==jQuery("#xxdm").val()){
		var url = "xszz_knsrd.do?method=saveKnssq&type="+saveType+"&shzt="+shzt+"&ylzd5="+pjyydm();
	}else if("12389"==jQuery("#xxdm").val()){
        var url = "xszz_knsrd.do?method=saveKnssq&type="+saveType+"&shzt="+shzt+"&ylzd9="+sqlydmIds;
    }else {
		var url = "xszz_knsrd.do?method=saveKnssq&type="+saveType+"&shzt="+shzt;
	}
	
	ajaxSubFormWithFun("knsrdForm",url,function(data){
		var msg = data["message"];
		if("save"==saveType){
			msg = msg.replace('��','��') + '�����ύ�����Ч��';
		} else if ("submit"==saveType) {
			msg = '�ύ�ɹ���';
		}
		showAlert(msg,{},{"clkFun":function(){
			if (parent.window){
				refershParent();
			}		
		}});
	});
}

/**
 * ����������
 * @return
 */
function knssq(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var sfysq = jQuery("#sfysq").val();
	
	if ("true" == sfysq){
		showAlertDivLayer("��ǰ�������������¼�������ظ�����");
		return false;
	}
	showDialog('�������϶�����',780,550,'xszz_knsrd.do?method=knssq');
}


/**
 * �������������--�޸�
 * @return
 */
function knssqUpdate(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var shzt = rows[0]["shzt"];
		if ("0" != shzt&&"3" != shzt){
			showAlertDivLayer(jQuery("#lable_wjt_yth_xg").val());
			return false;
		}
		showDialog("�������϶������޸�",780,550,"xszz_knsrd.do?method=knssq&type=update&xh="+rows[0]["xh"]+"&xn="+rows[0]["xn"]+"&xq="+rows[0]["xq"]);
	}
}

/**
 * �������������--ɾ��
 * @return
 */
function knssqDelete(){
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0){
		showAlertDivLayer("��ѡ����Ҫɾ���������¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_yth_sc").val());
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("xszz_knsrd.do?method=delKnssq",{values:ids.toString()},function(data){
				showAlertDivLayer(data["message"]);
				jQuery("#dataTable").reloadGrid();
			},'json');
		}});
	}
}


function cancle(){
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	if ("false" == isopen){
		showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫ�����ļ�¼��");
	} else if (ids.length >1 ) {
		showAlertDivLayer("��ѡ��һ����Ҫ�����ļ�¼��");
	} else {
		var rows = jQuery("#dataTable").getSeletRow();
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='5'){
				showAlertDivLayer("ֻ������еļ�¼���ܱ�������");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xszz_knsrd.do?method=cancle", {
					values : ids.toString(),
					lcid : rows[0]['shlc']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

function submitBusi(){
	
	var isopen = jQuery("#isopen").val();
	if(isopen==null||isopen==''){
		showAlertDivLayer(jQuery("#lable_jcszwcsh").val());
		return false;
	}
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length != 1){
		if ("false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		showAlertDivLayer(jQuery("#lable_one_tj").val());
	}else{
		var rows = jQuery("#dataTable").getSeletRow();
		if ('3'!=rows[0]["shzt"] && "false" == isopen){
			showAlertDivLayer(jQuery("#lable_dqwkfsq").val());
			return false;
		}
		
		for(var i=0;i<ids.length;i++){
			if(rows[i]['shzt']!='0'&&rows[i]['shzt']!='3'){
				showAlertDivLayer(jQuery("#lable_wjt_yth_tj").val());
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ�ύѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xszz_knsrd.do?method=subBusi", {
					values : ids.toString(),
					lcid : rows[0]['shlc'],
					xh : rows[0]['xh'],
					shzt : rows[0]['shzt']
				}, function(data) {
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});
	}
}

/**
 * �������������--���̸���
 * @return
 */
function knssqLcinfo(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		if(rows[0]["shzt"]=="0"){
			showAlertDivLayer(jQuery("#lable_wxglcxx").val());
			return false;
		}
		showDialog("�������̸���",530,310,'comm_spl.do?method=lcgz&sqid='+rows[0]['guid']+"&splc="+rows[0]['shlc']);
	}
}

/**
 * ���������--�������Ѵ�����ǩ�л�
 * @param obj
 * @param shzt
 * @return
 */
function selectTab(obj,shzt){
	jQuery("#shzt").val(shzt);

	if (shzt == "dsh"){
		if("14008" == jQuery("#xxdm").val()) {
			var isopensh = jQuery("#isopensh").val();
			if ("false" == isopensh) {
				jQuery('#li_sh').css('display','none');
				jQuery("#li_qx").css("display","none");
			}else {
				jQuery("#li_sh").css("display","");
				jQuery("#li_qx").css("display","none");
			}
		}else {
			jQuery("#li_sh").css("display","");
			jQuery("#li_qx").css("display","none");
		}
		
		jQuery("#dataTable").initGrid(gridSetting);
	} else {
		jQuery("#li_sh").css("display","none");
		jQuery("#li_qx").css("display","");
		
		jQuery("#dataTable").initGrid(gridSetting2);
	}
	
	jQuery(".ha").removeClass("ha");
	jQuery(obj).parent().addClass("ha");
	
	searchRs();
}

/**
 * �������϶�--���
 * @return
 */
function knsrdSh(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var flag=true;
	var xxdm = jQuery("#xxdm").val();
	
	if("14008" == xxdm) {
		var isopenSh = jQuery("#isopensh").val();
		if ("false" == isopenSh) {
			showAlertDivLayer("����ѹرգ�����ϵ����Ա��");
			return false;
		}
	}
	
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��˼�¼��");
	} else if (rows.length == 1){
		var url = "xszz_knsrd.do?method=knsrdDgsh&guid="+rows[0]["guid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&shlc="+rows[0]["shlc"];
		if("11998" == jQuery("#xxdm").val()){
			var zf = rows[0]['zf'];
			if(zf == null){
				zf = "";
			}
			url +="&zf="+zf;
		}
		showDialog("�������϶����",750,500,url);
	} else {
		for ( var i = 1; i < rows.length; i++) {
			if(rows[i]["sjdcmc"]!=rows[i-1]["sjdcmc"]){
				flag=false;
				break;
			}

			if("12866" == xxdm || "13871" == xxdm){
                if(rows[i]["sjdcmc"]==""||rows[i]["sjdcmc"]==null){
                    showAlertDivLayer("ǰ���Ƽ�����Ϊ�ղ���������ˣ�");
                    return false;
                }
			}
		}
		if(!flag){
			showAlertDivLayer("��ѡ����ͬ���ε���������");
			return false;
		}
		showDialog("�������",500,300,"xszz_knsrd.do?method=knsrdPlsh&sjdcmc="+rows[0]["sjdc"]);
	}
}

/**
 * �������
 * @return
 */
function cancelKnssh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��������˼�¼��");
	} else {
		
		//���һ��������˺���õ�·��
		var cancelPath = "xszz_knsrd.do?method=cancelKnssh";
		var msg="<div><ul>";
		var callBackMsg="";
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			jQuery.ajaxSetup({async:false});
			if(ty=="ok"){
				for ( var i = 0; i < rows.length; i++) {
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[i]["shlc"],shid:rows[i]["shid"]},function(data){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{guid:rows[i]["guid"]},function(result){
								callBackMsg=result["message"];
							},'json');
						}else{
							callBackMsg=data["message"];
						}
						msg+="<li><span style='width:40%;display:inline-block'>"+rows[i]["xh"]+"</span><font class='have'>"+callBackMsg+"</font></li>";
				},'json');
				}
				msg+="</ul></div>";
				showAlertDivLayer(msg,{},{"clkFun":function(){
					jQuery("#dataTable").reloadGrid();
			}});
			}
			
			jQuery.ajaxSetup({async:true});
		});
		
	}
}

/**
 * ������˱���
 * @param shzt
 * @return
 */
function savePlsh(shzt,rddc,ylzd3,shyj){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var guid = new Array();
	var gwid = new Array();
	var xhs = new Array();
	var thgw = "";
	jQuery.each(rows,function(i,row){
		guid.push(row["guid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
	});
	if("13871" == jQuery("#xxdm").val()){
		if(shzt == '3'){
			thgw = '-1';
		}
	}
	jQuery.post(
		"xszz_knsrd.do?method=savePlsh",
		{
		 shzt:shzt,
		 id:guid,
		 gwid:gwid,
		 xhs:xhs,
		 rddc:rddc,
		 ylzd3:ylzd3,
		 shyj:shyj,
		 thgw:thgw
		},function(data){
			
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
				jQuery("#dataTable").reloadGrid();
			}});
		},
		'json'
	);
}

/**
 * ������˲���
 * @param shzt
 * @param message
 * @return
 */
function saveKnssh(){
	var shzt = jQuery("#shjg").val();

	if("12866"==jQuery("#xxdm").val()&&(jQuery("#ylzd4").val() == "")){
		showAlert("��ѡ��������������");
		return false;
	}
	
	if ("1" == shzt && jQuery("#rddc").val() == ""){
		showAlert("��ѡ���Ƽ����Σ�");
		return false;
	}
	if("13871" == jQuery("#xxdm").val()){
		if(jQuery.trim(jQuery("#knpx").val()) == ""){
			showAlert("����д��������");
			return false;
		}
	}
	/*�������Ķ������ֶ�ȥ����by yxy 2015-11-20
	if("10335"==jQuery("#xxdm").val()&&(jQuery("#ylzd3").val() == "")){
		showAlert("��ѡ���޳�������");
		return false;
	}*/

	if (jQuery("#shyj").val() == ""){
		showAlert("����д��������");
		return false;
	}
	
	showConfirmDivLayer("��ȷ����˸�������",{"okFun":function(){
		var url = "xszz_knsrd.do?method=saveKnssh";
		ajaxSubFormWithFun("knsrdForm",url,function(data){
			showAlert(data["message"],{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
			}});
		});
	}});
}

//��ӡ����
function printKnssq(url){

	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		var guid = jQuery("#dataTable").getSeletIds();
		var url = url + "&guid=" +guid;
		window.open(url);
	}
}

//��ӡ��������������ͼ
function printSqlct(url){
	window.open(url);
}

/**
 * ˢ���������϶��������
 * @return
 */
function reloadWindow(){
	var xh = jQuery("#xh").val();
	document.location.href="xszz_knsrd.do?method=knssq&xh="+xh;
}

/**
 * �������϶��������༭��ͥ���
 * @return
 */
function editJtqk(){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		showDialog('��ͥ�������',945,500,'xszz_jtqkdc.do?method=dcxxModify&writeAble=yes&type=update&xh='+xh,{
			close:function(){
				loadJtqk(xh);
			}
		});
	}else{
		showAlertDivLayer("����ѡ��ѧ����");
	}
}

/**
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue,rowObject){
	if("11998" == jQuery("#xxdm").val()){
		return "<a href='javascript:void(0);' class='name' onclick='knsrdView(\""+rowObject["guid"]+"\",\""+rowObject["zf"]+"\");'>"+cellValue+"</a>";
	}else{
		return "<a href='javascript:void(0);' class='name' onclick='knsrdView(\""+rowObject["guid"]+"\",\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
	}
}

function sqxzLink(cellValue,rowObject){
	if(null==cellValue||""==cellValue){
		return "������";
	}else{
		return cellValue;
	}
}
	

/**
 * �������϶���ѯ
 * @param guid
 * @return
 */
function knsrdView(guid,para){
	if(guid == para){
		showDialog('��������˲鿴',780,520,'xszz_knsrd.do?method=knsrdView&guid='+guid);
	}else{
		if(para == "null"){
			para = "";
		}
		showDialog('��������˲鿴',780,520,'xszz_knsrd.do?method=knsrdView&guid='+guid+"&zf="+para);
	}
}

/**
 * ���ͳ��
 */
function knsrdShqk(){
	showDialog("�������϶����ͳ�����",470,300,"xszz_knsrd.do?method=knsrdShqk",{max:false,min:false});
}



//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_knsrd.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�Զ��嵼�����ܣ���ˣ�
function exportConfigSh() {	
	var DCCLBH = "xszz_knsrd_sh.do";//dcclbh,�������ܱ�ţ�ִ�е������� 
	customExport(DCCLBH, exportDataSh);
}

//������������ˣ�
function exportDataSh() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var DCCLBH = "xszz_knsrd_sh.do";
	var url = "xszz_knsrd.do?method=exportDataSh&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
/* �����Ի� 
       ��Ժϵ���ͨ�������ѡ������������ͱ���
   2016��10��17��     ������Ա������*/
function viewYxKnsfp(){
	showDialog("��Ժϵ���ͨ�������������",750,400,"xszz_knsrd.do?method=viewYxKnsfp");
}

function pjyydm(){
	var values=[];
	jQuery("input[name=ylzd5]:checked").each(function(){
		values.push(jQuery(this).val());
	});
	return values.join(",");
}

//��ʦ����Ի�����
function downloadDjb(lx){

	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
	} else {
		var guid = jQuery("#dataTable").getSeletIds();
		var url = url + "&guid=" +guid;
		window.open(url);
	}
}
//�㽭ͬ�����صǼǱ���Ի�
function viewPrint(){
	var rows = jQuery("#dataTable").getSeletRow();
	var action = "xszz_knsrd.do?method=viewPrint";
	if (rows.length <=0) {
		showAlertDivLayer("��ѡ��һ����¼��");
	}else{
		var guid = jQuery("#dataTable").getSeletIds();
		for(var i = 0; i < guid.length ; i++){
			var url = action + "&guid=" +guid[i];
			window.open(url);
		}
	}
	
	
}
