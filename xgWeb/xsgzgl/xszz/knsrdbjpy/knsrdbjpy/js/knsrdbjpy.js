var DCCLBH = "xszz_knsrdbjpy_sq.do";//dcclbh,�������ܱ��
/**
 * ���ؼ�ͥ���������Ϣ
 * @param obj
 * @return
 */
function showJtqk(obj){
	var className = jQuery(obj).attr("class");
	var newClass = className == "up" ? "down" : "up";

	jQuery(obj).attr("class",newClass);
	jQuery("#t_jtqk").toggle();
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

	if (!checkMustNotNull() || jQuery("#xh").val() == ""){
		showAlertDivLayer("�뽫��������д������");
		return false;
	}
	
	var shzt = jQuery("#shzt").val();
	var isopen = jQuery("#isopen").val();
	
	if(isopen==null||isopen==''){
		showAlertDivLayer('��������δ��ʼ��������ϵ����Ա��');
		return false;
	}
	if ("false" == isopen && '3'!=shzt){
		showAlertDivLayer("��ǰδ�������������룬����ϵ����Ա��");
		return false;
	}
	
	
	//�Ƿ񵯳���ͥ���������дҳ��
	var openJtqk = jQuery("#openJtqk").val();

	if ("true" == openJtqk){
		var xh = jQuery("#xh").val();

		showAlertDivLayer("������д��ͥ��������",{},{"clkFun":function(){
			editJtqk();
		}});
		return false;
	}
	
    var sqsftxdc=jQuery("#sqsftxdc").val();
    var xxdm=jQuery("#xxdm").val();
	if(sqsftxdc=='1'&& (xxdm=='12861' || xxdm=='10718')){
		if(null==jQuery("#ylzd1").val()||""==jQuery("#ylzd1").val()){
			showAlertDivLayer("���ѵ��β���Ϊ��,��ѡ��");
			return false;
		}
	}
	if(xxdm=='12861'){
	   if(jQuery(".MultiFile-label").length<=0){
			showAlertDivLayer("���ϴ�������");
			return false;
	   }
	}
	var shzt = jQuery("#shzt").val();
	var isopen = jQuery("#isopen").val();
	
	if("submit"== saveType && "3"!=shzt && "false" == isopen){
		showAlertDivLayer("��ǰδ�������������룬����ϵ����Ա��");
		return false;
	}
	

	var url = "xszz_knsrdbjpy.do?method=saveKnssq&type="+saveType+"&shzt="+shzt;

	ajaxSubFormWithFun("knsrdbjpyForm",url,function(data){
		showAlertDivLayer(data["message"],{},{"clkFun":function(){
			if("save"==saveType){
				showAlertDivLayer("�ѱ���ɹ������ύ�����Ч��",{},{"clkFun":function(){
				if (parent.window){
					refershParent();
				}
				}});
			}
			else{
				if (parent.window){
					refershParent();
				}
				
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
	showDialog('�������϶�����',780,520,'xszz_knsrdbjpy.do?method=knssq');
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
		showDialog("�������϶������޸�",780,450,"xszz_knsrdbjpy.do?method=knssq&type=update&xh="+rows[0]["xh"]+"&xn="+rows[0]["xn"]+"&xq="+rows[0]["xq"]);
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
				showAlertDivLayer(jQuery("#lable_wjt_sc").val());
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫɾ����������",{"okFun":function(){
			jQuery.post("xszz_knsrdbjpy.do?method=delKnssq",{values:ids.toString()},function(data){
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
			if(rows[i]['shzt']!='6'){
				showAlertDivLayer("ֻ�а༶�����еļ�¼���ܱ�������");
				return false;
			}
		}
		
		showConfirmDivLayer("��ȷ��Ҫ����ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post("xszz_knsrdbjpy.do?method=cancle", {
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
				jQuery.post("xszz_knsrdbjpy.do?method=subBusi", {
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
		if(rows[0]["shzt"]=="0" || rows[0]["shzt"]=="6" || rows[0]["shzt"]=="7"){
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
		jQuery("#li_sh").css("display","");
		jQuery("#li_qx").css("display","none");
		
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
function knsrdbjpySh(){
	
	var rows = jQuery("#dataTable").getSeletRow();
	var flag=true;
    
	if (rows.length == 0){
		showAlertDivLayer("��ѡ����Ҫ��˼�¼��");
	} else if (rows.length == 1){
		showDialog("�������϶����",750,500,"xszz_knsrdbjpy.do?method=knsrdbjpyDgsh&guid="+rows[0]["guid"]+"&xtgwid="+rows[0]["xtgwid"]+"&shid="+rows[0]["shid"]+"&shlc="+rows[0]["shlc"]);
	} else {
		for ( var i = 1; i < rows.length; i++) {
			if(rows[i]["sjdcmc"]!=rows[i-1]["sjdcmc"]){
				flag=false;
				break;
				
			}
		}
		if(!flag){
			showAlertDivLayer("��ѡ����ͬ���ε���������");
			return false;
		}
		showDialog("�������",500,300,"xszz_knsrdbjpy.do?method=knsrdbjpyPlsh");
	}
}

/**
 * �������
 * @return
 */
function cancelKnssh(){
	var rows = jQuery("#dataTable").getSeletRow();

	if (rows.length != 1){
		showAlertDivLayer("��ѡ��һ����Ҫ��������˼�¼��");
	} else {
		
		//���һ��������˺���õ�·��
		var cancelPath = "xszz_knsrdbjpy.do?method=cancelKnssh";
		confirmInfo("��ȷ��Ҫ����������?",function(ty){
			if(ty=="ok"){
				jQuery.post("comm_spl.do?method=cxshnew",{shlc:rows[0]["shlc"],shid:rows[0]["shid"]},function(data){
						// �ж��Ƿ����һ������(1:���һ�������ɹ���
						if("1" == data["cancelFlg"]){
							jQuery.post(cancelPath,{guid:rows[0]["guid"]},function(result){
								showAlertDivLayer(result["message"],{},{"clkFun":function(){
									jQuery("#dataTable").reloadGrid();
								}});
							},'json');
						}else{
							showAlertDivLayer(data["message"],{},{"clkFun":function(){
								jQuery("#dataTable").reloadGrid();
							}});
						}
					
				},'json');
			}
		});
		/*
		showConfirmDivLayer("��ȷ��Ҫ�����Ը������¼����˲�����",{"okFun":function(){
			jQuery.post("xszz_knsrdbjpy.do?method=cancelKnssh",
				{
				 guid:rows[0]["guid"],
				 xtgwid:rows[0]["xtgwid"]
				},
				function(data){
					showAlertDivLayer(data["message"]);
					jQuery("#dataTable").reloadGrid();
				},
			'json');
		}});
		*/
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
	
	jQuery.each(rows,function(i,row){
		guid.push(row["guid"]);
		gwid.push(row["xtgwid"]);
		xhs.push(row["xh"]);
	});

	jQuery.post(
		"xszz_knsrdbjpy.do?method=savePlsh",
		{
		 shzt:shzt,
		 id:guid,
		 gwid:gwid,
		 xhs:xhs,
		 rddc:rddc,
		 ylzd3:ylzd3,
		 shyj:shyj
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

	if ("1" == shzt && jQuery("#rddc").val() == ""){
		showAlertDivLayer("��ѡ���Ƽ����Σ�");
		return false;
	}
	if("10335"==jQuery("#xxdm").val()&&(jQuery("#ylzd3").val() == "")){
		showAlertDivLayer("��ѡ���޳�������");
		return false;
	}

	if (jQuery("#shyj").val() == ""){
		showAlertDivLayer("����д��������");
		return false;
	}
	
	showConfirmDivLayer("��ȷ����˸�������",{"okFun":function(){
		var url = "xszz_knsrdbjpy.do?method=saveKnssh";
		ajaxSubFormWithFun("knsrdbjpyForm",url,function(data){
			showAlertDivLayer(data["message"],{},{"clkFun":function(){
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

/**
 * ˢ���������϶��������
 * @return
 */
function reloadWindow(){
	var xh = jQuery("#xh").val();
	document.location.href="xszz_knsrdbjpy.do?method=knssq&xh="+xh;
}

/**
 * �������϶��������༭��ͥ���
 * @return
 */
function editJtqk(){
	var xh = jQuery("#xh").val();
	showDialog('��ͥ�������',780,500,'xszz_jtqkdc.do?method=dcxxModify&writeAble=yes&type=update&xh='+xh,{
		close:function(){
			reloadWindow();
		}
	});
}

/**
 * ѧ������
 * @param cellValue
 * @param rowObject
 * @return
 */
function xhLink(cellValue,rowObject){
	return "<a href='javascript:void(0);' class='name' onclick='knsrdbjpyView(\""+rowObject["guid"]+"\");'>"+cellValue+"</a>";
}

/**
 * �������϶���ѯ
 * @param guid
 * @return
 */
function knsrdbjpyView(guid){
	showDialog('�������϶�����',780,520,'xszz_knsrdbjpy.do?method=knsrdbjpyView&guid='+guid);
}

/**
 * ���ͳ��
 */
function knsrdbjpyShqk(){
	showDialog("�������϶����ͳ�����",470,300,"xszz_knsrdbjpy.do?method=knsrdbjpyShqk",{max:false,min:false});
}



//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport(DCCLBH, exportData);
}

//��������
function exportData() {
	setSearchTj();//���ø߼���ѯ����
	var url = "xszz_knsrdbjpy.do?method=exportData&dcclbh=" + DCCLBH;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//�Զ��嵼�����ܣ���ˣ�
function exportConfigSh() {	
	var DCCLBH = "xszz_knsrdbjpy_sh.do";//dcclbh,�������ܱ�ţ�ִ�е������� 
	customExport(DCCLBH, exportDataSh);
}

//������������ˣ�
function exportDataSh() {
	var shlx = jQuery("#shzt").val();
	setSearchTj();//���ø߼���ѯ����
	var DCCLBH = "xszz_knsrdbjpy_sh.do";
	var url = "xszz_knsrdbjpy.do?method=exportDataSh&dcclbh=" + DCCLBH + "&shlx=" + shlx;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}
