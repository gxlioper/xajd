	var api = frameElement.api;
	W = api.get('parentDialog');
function editOk(){
	//��ʼʱ��
	var kssj=jQuery("#kssj").val();
	var dxjy = jQuery("#dxjy").val();
	var jysj = jQuery("#zd1").val();
	var zjsydxjy = jQuery("#zjsydxjy").val();
	//���ʱ���Ƿ�Ϸ�
	if(!checkDate(kssj)){return false;}
	if("true"==dxjy&&compareDate(jysj,kssj)==2){
		showAlert("��ҵʱ�䲻��С�ڿ�ʼʱ��");
		return false;
	}
	W.jQuery(".edit").text(kssj);
	W.jQuery(".finish")
	//������Ϣ
	var grxj;
	//��������������̲Ų�������С��
	var ishave=jQuery("#ishave").val();
	if(ishave=="true"){
		grxj=jQuery("#grxj").val();
		W.jQuery(".edit").parents("li").find("input[name='grxj']").val(grxj);
		zd5 = jQuery("#zd5").val();
		W.jQuery(".edit").parents("li").find("input[name='zd5']").val(zd5);
	}
	if((kssj!=""&&null!=kssj)||(grxj!=""&&null!=grxj)){
		W.jQuery(".edit").parents("li").attr("class","current");
	}
	//��������У��ҵ����
	
	if("true"==dxjy){
		W.jQuery(".edit").parents("li").find("input[name='zd1']").val(jQuery("#zd1").val());
		W.jQuery(".edit").parents("li").find("input[name='zd2']").val(jQuery("#zd2").val());
	}
	if("true"==zjsydxjy){
		W.jQuery(".edit").parents("li").find("input[name='zd8']").val(jQuery("#zd8").val());
		W.jQuery(".edit").parents("li").find("input[name='zd9']").val(jQuery("#zd9").val());
		W.jQuery(".edit").parents("li").find("input[name='zd10']").val(jQuery("#zd10").val());
	}
	W.jQuery(".edit").parents("li").find("input[name='zd3']").val(jQuery("#zd3").val());
	
	W.autoChange();
	W.jQuery(".edit").attr("class","finish");
	iFClose();
}
function editClose(){
	W.jQuery(".edit").attr("class","finish");
	iFClose();
}
function checkDate(nowDate){
	var check=true;
	//���ڱ༭��
	var li=W.jQuery(".edit").parents("li").eq(0);
	var nowtext=W.jQuery(".edit").prevAll("span").text();
	//���ڱ༭�Ľ׶�
	var jddm=W.jQuery(".edit").parents("li").find("input[name='jddm']").val();
	
	W.jQuery(".Join_party ul li").each(function(){
		var checkDate=jQuery(this).find("span[name='sj']").text();
		var checkJddm=jQuery(this).find("input[name='jddm']").val();
		var checkJdmc=jQuery(this).find("input[name='jdmc']").val();
		//�ȵ�ǰ�׶ο���
		//if(checkJddm>jddm && !(checkJddm == '05' && jddm == '04')){
		if(checkJddm>jddm){
			if(compareDate(nowDate,checkDate)==1){
				showAlert("<font color='red'>["+nowtext+"]</font>�Ŀ�ʼʱ��("+nowDate+")���ܴ���<font color='red'>["+checkJdmc+"]</font>�Ŀ�ʼʱ��("+checkDate+").");
				check=false;
				return false;
			}
		}
		//�ȵ�ǰ�׶ο�ǰ
		//if(checkJddm<jddm && !(checkJddm == '04' && jddm == '05')){
		if(checkJddm<jddm){
			if(compareDate(nowDate,checkDate)==2){
				showAlert("<font color='red'>["+nowtext+"]</font>�Ŀ�ʼʱ��("+nowDate+")����С��<font color='red'>["+checkJdmc+"]</font>�Ŀ�ʼʱ��("+checkDate+").");
				check=false;
				return false;
			}
		}
	});
	
	
/*	//����Ƿ����֮ǰ�׶�ʱ��
	jQuery(li).nextAll().each(function(){
		var prevDate=jQuery(this).find("span[name='sj']").text();
		//alert(nowDate+":"+prevDate+":"+compareDate(nowDate,prevDate));
		if(compareDate(nowDate,prevDate)==2){
			var nowtext=W.jQuery(".edit").prevAll("span").text();
			var prevtext=jQuery(this).find("span[name='sj']").prevAll("span").text();
			showAlert("<font color='red'>["+nowtext+"]</font>�Ŀ�ʼʱ��("+nowDate+")����С��<font color='red'>["+prevtext+"]</font>�Ŀ�ʼʱ��("+prevDate+").");
			check=false;
			return false;
		}
	});
	//�ж��Ƿ�С�ں����׶�
	if(!check){return check;}
	jQuery(li).prevAll().each(function(){
		var nextDate=jQuery(this).find("span[name='sj']").text();
		//alert(nowDate+":"+prevDate+":"+compareDate(nowDate,prevDate));
		if(compareDate(nowDate,nextDate)==1){
			var nowtext=W.jQuery(".edit").prevAll("span").text();
			var nexttext=jQuery(this).find("span[name='sj']").prevAll("span").text();
			showAlert("<font color='red'>["+nowtext+"]</font>�Ŀ�ʼʱ��("+nowDate+")���ܴ���<font color='red'>["+nexttext+"]</font>�Ŀ�ʼʱ��("+nextDate+").");
			check=false;
			return false;
		}
	});*/
	return check;
}