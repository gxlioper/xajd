//ѧ��������ʼ��js
//��ΪĳԪ������¼������������������
//ע��һ��Ҫ�ӣ�
var gjzxdk = "5003";//������ѧ����
var xshjxf = "1003";//ѧ������ѧ��
var jyfzjj = "3002";//������չ����
var jtqkdc = "5001";//��ͥ�������



//ѧ��������ʼ��
function xszzInit() {
	var xmdm = $('xmdm').value;
	var xxdm = $('xxdm').value;
	
	if (gjzxdk==xmdm) {//������ѧ����
		
	} else if(xshjxf==xmdm) { //ѧ������ѧ��
		if ("10395"==xxdm){//����ѧԺ
			setHjxf();
		}else{
		
		}
	}else if(jyfzjj == xmdm){
		if("10589" == xxdm) {// ���ϴ�ѧ
			setXxjd();
			setHjxfHndx('xxsheng','xxshi','xxxian');
		}
	}else if(jtqkdc == xmdm){ //��ͥ�������
		if("10589" == xxdm){ //���ϴ�ѧ
			if($("pkxjb")){
				if($('sfpkx').value!="��"){
					$("pkxjb").disabled = true;
				}
				setJtqkdc();
			}
		}
	}
}


//ѧ������ѧ��
function setHjxf(){
	if ($('qjzfy')) {//Ƿ���ܷ�����Ϊֻ��
		$('qjzfy').readOnly=true;
	}
	
		//ΪǷ���ܷ�������ֵ
	if ($('qjzfy') && $('yjzfy') && $('sjzfy')) {
		$('yjzfy').attachEvent('onblur',function(){
			hjxf_qjzfy();
		});
		
		$('sjzfy').attachEvent('onblur',function(){
			hjxf_qjzfy();
		});
	}
}

//����ѧ��--ΪǷ���ܷ�������ֵ
function hjxf_qjzfy(){	
	$('qjzfy').value = Number($('yjzfy').value) - Number($('sjzfy').value);
}

//���ϴ�ѧ --ʡ��������
function setHjxfHndx(sheng,shi,xian){
	//����ʡ,�У���
	if($(sheng) && $(shi) && $(xian)){
		var options = [{dm:"",mc:"--��ѡ��--"}];
		
		DWRUtil.addOptions(shi,options,"dm","mc");
		DWRUtil.addOptions(xian,options,"dm","mc");
		
		$(sheng).attachEvent('onchange', function(){loadShi(sheng,shi,xian)});
		$(shi).attachEvent('onchange', function(){loadXian(shi,xian)});
	}
	
	//��ʼ��ʡ
	initsheng(sheng);
	if($("select_"+sheng).value != ""){
		$(sheng).value = $("select_" + sheng).value;

		// ��ʼ������
		dwr.engine.setAsync(false);
		loadShi(sheng, shi, xian);
		dwr.engine.setAsync(true);
		
		$(shi).value = $("select_" + shi).value;
		$(xian).value = $("select_" + xian).value;
	}
}

//��ʼ��ʡ
function initsheng(sheng){
	if($(sheng)){
		var id = sheng;
		var tableName = "dmk_qx";
		var pk = "qxdm";
		var msg = "--��ѡ��--"
		var pkValue = "__0000";
		var bsf = "like"
		var dm = "qxdm";
		var mc = "qxmc";
		
		dwr.engine.setAsync(false);
		getXszzInfo.getXszzLikeList(tableName, dm, mc, msg, pk, bsf, pkValue,function(data){
			if(data!=null){
				DWRUtil.removeAllOptions(id);
				DWRUtil.addOptions(id,data,"dm","mc");
			}
		});
		dwr.engine.setAsync(true);
	}
}

//���ϴ�ѧ--ѧϰ�׶�
function setXxjd(){
	if($('xxjd')){
		var id = "xxjd";
		var options = [{dm:"",mc:"--��ѡ��--"},{dm:"��ѧ������ְ����ר��",mc:"��ѧ������ְ����ר��"},{dm:"���У���ְ�ߡ���ר���м���",mc:"���У���ְ�ߡ���ר���м���"},
		               {dm:"����",mc:"����"},{dm:"Сѧ",mc:"Сѧ"}];
		DWRUtil.removeAllOptions(id);
		DWRUtil.addOptions(id,options,"dm","mc");
		$('xxjd').value = $('select_xxjd').value;
	}
}

//���ϴ�ѧ��ͥ�������
function setJtqkdc(){
	if($('sfpkx')){
		$('sfpkx').attachEvent('onchange', function(){
			var value = $('sfpkx').value;
			if(value=="��"){
				$('pkxjb').disabled="";
			}else{
				$('pkxjb').value="";
				$('pkxjb').disabled=true;
			}
		});
	}
}
