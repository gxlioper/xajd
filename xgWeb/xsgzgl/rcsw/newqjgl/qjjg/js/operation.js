var action="qjjg.do";
function searchRs() {
	var map = getSuperSearch();
	jQuery("#dataTable").reloadGrid(map);
}
function reload(){
	jQuery("#dataTable").reloadGrid();
}
//��������ת
function dcmcLink(cellValue, rowObject) {
	var qjjgid = rowObject["qjjgid"];
	return "<a href='javascript:void(0);' onclick=\"ckxx('" + qjjgid
			+ "')\" class='name'>" + cellValue + "</a>";
}
//�鿴��Ϣ
function ckxx(qjjgid) {
	var query=jQuery("#query").val();
	var url = action+"?method=showView&qjjgid=" + qjjgid;
	var title = "���������Ϣ";
	showDialog(title, 800, 500, url);
}
//����
function add() {
		var url =action+"?method=add";
		var title = "���������Ϣ";
		showDialog(title, 800, 500, url);
		jQuery("#dataTable").reloadGrid();
}
//������ʽ��֤
function postfixCheck(){
	var wjm=jQuery("#formfile").val();
	if(wjm==""||wjm==null){
		return true;
	}
	var wjms=wjm.split(".");
	var hz=",bmp,jpg,jpeg,gif,png,pdf,doc,BMP,JPG,JPEG,GIF,PNG,PDF,DOC";
	if(hz.indexOf(wjms[wjms.length-1])<0){
		return false;
	}
	return true;
}
function save(url,checkId,sfjc){
	if(!postfixCheck()){
		return showAlert("���ϴ�֧�ֵĸ�����ʽ��");
	}
	var xxdm=jQuery("#xxdm").val();
	if("12866"==xxdm){
		checkId+="-jzdh";
	}
	if("70002"==xxdm){
		checkId+="-xnxw";
	}
	if("12872"==xxdm){
		var  myselect=document.getElementById("qjlxid");
		var index=myselect.selectedIndex ;
		var store = myselect.options[index].text;
		if(store==("����")){			
			if (jQuery(".MultiFile-label").length == 0){
				showAlert("�뽫��<font color='red'>*</font>����Ŀ��д����");
				return false;
			}
		}
	}
	if("12727"==xxdm||"12688"==xxdm){//��ٽڴ��ֶ�
		var b = false;
		var qjjcstr="";
		var qjjc = document.getElementsByName("mdd");
		for(var i=0;i<qjjc.length;i++){
		    if(qjjc[i].checked){
		       b=true;
		       qjjcstr+=qjjc[i].value+",";
		    }
		}
		var qjts =jQuery("#qjts").val();
		if(qjts<1){
			if(b==false){
				return showAlert("��ѡ����ٽڴ�!");
			}
		}
		url+="&mdd="+ encodeURI(encodeURI(qjjcstr.substring(0,qjjcstr.length-1)));
	}
	if(!checkNotNull(checkId)){
		return showAlert("�뽫��<font color='red'>*</font>����Ŀ��д������");
	}
	
	if(!checkother()){
		return false;
	}
	lock();
	var qjts=jQuery("#qjts").val();
	jQuery.post("qjjg.do?method=checkTs", {
		qjts:qjts,sfjc:sfjc
	}, function(data) {
		if(data["success"]=="true"){
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
		}else{
			showAlert("����������������Ӧ��ٹ���!");
		}
		unlock();
	}, 'json');
}
function checkother(){
	if(chkNumInputForThis("qjts")){
		showAlert("�����������Ϊ����!");
		return false;
	}
	
	if(0 == jQuery("#qjts").val()) {
		showAlert("�����������Ϊ0!");
		return false;
	}
	
	if(chkNumInputForThis("sjqjts")){
		showAlert("ʵ�������������Ϊ����!");
		return false;
	}
	var xxdm = jQuery("#xxdm").val();
	if(xxdm == "10351"){
		var qjjs = jQuery("#qjjs").val();
		if(jQuery.trim(qjjs) == ""){
			showAlert("��ٽ�������Ϊ��!");
			return false;
		}
	}
	/*
	var sjqjts=jQuery("#sjqjts").val();
	if(null!=sjqjts&&sjqjts.indexOf(".")!=-1){
		showAlert("ʵ�������������Ϊ��������!");
		return false;
	}*/
	return true;
}
function chkNumInputForThis(obj){
	//�����ǹ���js ����ͬҳ�� ��Щ���󲻴��ڵ�����
	if(null==obj||null==$(obj)){
		return false;
	}
	return chkNumInput(obj);
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
			//alert(id[i]);
			return false;
		}
	}
	return true;
}
//�޸�
function update() {
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ�޸ĵļ�¼��");
	} else {
		var xh=rows[0]["xh"];
		var qjzt=rows[0]["qjzt"];
		if(0==qjzt||"0"==qjzt){
			showAlertDivLayer("�������ݲ����޸�!");
		}else{
			var url = action+'?method=update&xh='+xh+'&qjjgid=' + rows[0]["qjjgid"];
			var title = "�޸������Ϣ";
			showDialog(title, 800, 500, url);
			jQuery("#dataTable").reloadGrid();
		}
	}
}
//����
function xjcl(){
	var userStatus = jQuery("#userStatus").val();
	var myDate = jQuery("#currTime").val();
	var xxdm=jQuery("#xxdm").val();
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length != 1) {
		showAlertDivLayer("��ѡ��һ����Ҫ���ٵļ�¼��");
		return false;
	} 
	var xh =rows[0]["xh"];
	var kssj =rows[0]["kssj"];
	var qjsqid =rows[0]["qjsqid"];
	jQuery.ajaxSetup({async:false});
	var haveNewSqjl = false;
	/*
	jQuery.post("qjjg.do?method=haveNewSqjl", {
		qjsqid:qjsqid,xh:xh,kssj:kssj
	}, function(data) {
	
		if("true"==data["message"]){
			haveNewSqjl=true;
		}
	},'json');*/
	
	var xh=rows[0]["xh"];
	var xjzt=rows[0]["xjzt"];
	var jssj = rows[0]["jssj"];
	if(xjzt=="1"){//1��ʾ�Ѿ�����
		showAlertDivLayer("����Ϣ�Ѿ����٣������ظ�������");
		return false;
	}
	var days = dateDiff(myDate,jssj);
	//������Ͽ:δ���ٳ���8�죬��ʾ��������
	if(days>jQuery("#xjts").val()&&"14008"==xxdm&&"xx"!=userStatus&&false==haveNewSqjl){
		showAlertDivLayer("���δ���ٳ���"+jQuery("#xjts").val()+"�죬�������٣�");
		return false;
	}
	//������Ͽҽ��ר�����ٸ��Ի� 
	//���� XG_2016-0135 RA002 ���������������������������ʾȡ�� 20161215
	//	var qjts = rows[0]["qjts"];
	//	var qjzt = rows[0]["qjzt"];	
	//	var nzcs = false;
	//	if("14008" == xxdm && "0" == qjzt && "xx"!=userStatus) {
	//		var xjUrl = "qjjg.do?method=xjrpdOneToN";
	//		if(qjts > 9){
	//			xjUrl = "qjjg.do?method=xjrpdTenD";
	//		}
	//		jQuery.post(xjUrl, {
	//			qjsqid:qjsqid,xh:xh
	//		}, function(data) {
	//			if("true"==data["message"]){
	//				nzcs=true;
	//			}
	//		},'json');
	//		if(true == nzcs) {
	//			showAlertDivLayer("����Ȩ�Ը�ѧ���������ٲ�������ȷ�ϣ�");
	//			return false;
	//		}
	//	}

	var url = action+'?method=xjcl&xh='+xh+'&qjjgid=' + rows[0]["qjjgid"];
	var title = "���ٹ���";
	showDialog(title, 800,450, url);
	jQuery("#dataTable").reloadGrid();
	jQuery.ajaxSetup({async:true});
	
}
//ɾ��
function del() {
	var ids = jQuery("#dataTable").getSeletIds();
	if (ids.length == 0) {
		showAlertDivLayer("��ѡ����Ҫɾ���ļ�¼��");
	} else {
		showConfirmDivLayer("��ȷ��Ҫɾ��ѡ��ļ�¼��", {
			"okFun" : function() {
				jQuery.post(action+"?method=del", {
					values : ids.toString()
				}, function(data) {
					var mes="�ɹ�ɾ��"+data["num"]+"����";
					mes+="</br>";
					if(data["nodel"]!="-1"){
						mes+="<font color='red'>"+data["nodel"]+"</font>";
						mes+="Ϊ�������ݲ���ɾ����";
					}
					showAlertDivLayer(mes);
					jQuery("#dataTable").reloadGrid();
				}, 'json');
			}
		});

	}
}
function rcxwshLcinfo(){
	var ids = jQuery("#dataTable").getSeletIds();
	var rows = jQuery("#dataTable").getSeletRow();
	if (ids.length != 1){
		showAlertDivLayer("��ѡ��һ�����̸��ټ�¼��");
	} else {		
		showDialog("����������̸���",480,380,'comm_spl.do?method=lcgz&sqid='+rows[0]['qjsqid']+"&splc="+rows[0]['splcid']);
	}
}
//�Զ��嵼�� ����
function exportConfig() {
	//DCCLBH�������ܱ��,ִ�е������� 
	customExport('qjjgbase.do',exportData);
}

// ��������
function exportData(dcclbh) {
	setSearchTj();//���ø߼���ѯ����
	var url = "qjjg.do?method=exportData&dcclbh=" + dcclbh;//dcclbh,�������ܱ��
	url = addSuperSearchParams(url);//���ø߼���ѯ����
	jQuery("form").eq(0).attr("action", url);
	jQuery("form").eq(0).submit();
}

//��ӡ�����
function printQjjgb(url){
	var qjjgid="";
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length <1) {
		showAlertDivLayer("������ѡ��һ����¼��");
	} else {
		for(var i=0;i<rows.length;i++){
			if(i==rows.length-1){
				qjjgid +=rows[i]["qjjgid"];
			}else{
				qjjgid +=rows[i]["qjjgid"]+",";
			}
		}
		if("10511"==jQuery("#xxdm").val()){
			var url = "qjjg.do?method=printQjjgbOfHs&qjjgid="+qjjgid;
		}
		else{
			var url = url + "&qjjgid=" +qjjgid;
		}
		window.open(url);
	}
}

function selectQjkc(){
	var xh = jQuery("#xh").val();
	if (jQuery.trim(xh) != ""){
		showDialog("ѡ����ٿγ�",800,500,"qjsq.do?method=selectQjkc&xh="+xh);
	} else {
		showAlertDivLayer("����ѡ��ѧ����");
	}
}

function addQjkc(){
	var rows = jQuery("#dataTable").getSeletRow();
	if (rows.length == 0) {
		showAlertDivLayer("������ѡ��һ�ſγ̣�");
		return false;
	}
	var api = frameElement.api;
	var parentTbody = jQuery(api.get('parentDialog').document).find("#qjkc");
	jQuery("tr",parentTbody).remove();
	var kcbhs="";
	for ( var i = 0; i < rows.length; i++) {
		if(i!=0){
			kcbhs+=";"+rows[i]['kcbh'];
		}
		else{
			kcbhs+=rows[i]['kcbh'];
		}
		var tr = jQuery("<tr></tr>");
		var kcmcTd = jQuery("<td></td>");
		var rklsxmTd = jQuery("<td></td>");
		var rklslxfsTd = jQuery("<td></td>");
		var kcbh = jQuery("<input type='hidden' name='kcbh' id='kcbh"+i+"' value='"+rows[i]['kcbh']+"'/>");
		var kcmc = rows[i]['kcmc'];
		var rklsxm = rows[i]['rklsxm'];
		var rklslxfs = rows[i]['rklslxfs'];
		kcmcTd.append(kcmc);
		kcmcTd.append(kcbh);
		rklsxmTd.append(rklsxm);
		rklslxfsTd.append(rklslxfs);
		tr.append(kcmcTd).append(rklsxmTd).append(rklslxfsTd);
		parentTbody.append(tr);
	}
	parentTbody.append(jQuery("<input type='hidden' name='kcbhs' id='kcbhs' value='"+kcbhs+"'/>"));
	api.close();
	
}

function dateDiff(sDate1, sDate2){ 
	  var aDate, oDate1, oDate2, iDays;
	   // aDate = sDate1.split("-");
	  //  oDate1 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]); //ת��Ϊ12-18-2002��ʽ
	  oDate1= new Date(sDate1.substring(0,10).replaceAll("-","/"));
	   // aDate = sDate2.split("-");
	   // oDate2 = new Date(aDate[1] + '/' + aDate[2] + '/' + aDate[0]);
	  oDate2= new Date(sDate2.substring(0,10).replaceAll("-","/"));
	  iDays = parseInt((oDate1 - oDate2) / 1000 / 60 / 60 /24); //�����ĺ�����ת��Ϊ����
	  return iDays;
}

/**
 * �㽭����ְҵѧԺ�������Ի����������
 * @return
 */
function printXsqjspb(){
	/*ѡ��ļ�¼*/
	var rows = jQuery("#dataTable").getSeletRow();
	/*��ѡ��¼*/
	var ids = jQuery("#dataTable").getSeletIds();
	/*ѡ���¼�����������ȣ�*/
	var len = ids.length;
	if(0 == len ){/*ѡ��0����ʾ*/
		showAlertDivLayer("��ѡ����Ҫ���صļ�¼��");
		return false;
	}else if(1 == len){/*ѡ��һ����¼*/
		var url = "qjjg.do?method=getXsqjspbOne&xh="+rows[0]["xh"]+"&qjjgid="+rows[0]["qjjgid"];
		window.open(url);
	}else{
		var url = "qjjg.do?method=getXsqjspbZip&value="+ids;
		window.open(url);
	}
}