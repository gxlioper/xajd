//��֤�����ʽ
function codeYz(){	

	var inputV = document.getElementsByName("newCode");
	var table = document.forms[0].tName.value;
	//�㽭��ý�������ȼ�
	if("zjcm_wsjcdjb" == table){
		var dm = inputV[0].value;//����
		var mc = inputV[1].value;//�ȼ�
		var xxf = inputV[2].value;//���޷�
		var sxf = inputV[3].value;//���޷�
		if(sxf !="" && xxf != ""){
			if(dm.length > 10){
				alert("�������ֻ��¼��10���ַ���");
				return false;
			}
			if(mc.length > 10){
				alert("�������ֻ��¼��10���֣�");
				return false;
			}
			if(xxf.length > 3){
				alert("���޷����ֻ��¼��3���ַ���");
				return false;
			}
			if(sxf.length > 3){
				alert("���޷����ֻ��¼��3���ַ���");
				return false;
			}
			if(isNumber(xxf) == false){
				alert("���޷ֱ��������ָ�ʽ��");
				return false;
			}
			if(isNumber(sxf) == false){
				alert("���޷ֱ��������ָ�ʽ��");
				return false;
			}
			if(parseFloat(xxf) > parseFloat(sxf)){
				alert("���޷ֲ��ܴ������޷֣�");
				return false;
			}
		}
	}else if("xszz_com_gjzxj1dmb" == table){
	//ͨ�ù����ȼ���֤
		var je = inputV[2].value
		if(je !=""){
			if(isNumber(je) == false){
				alert("�����������ָ�ʽ��");
				return false;
			}
			if(je.length > 20){
				alert("���ĳ��Ȳ��ܳ���20��");
				return false;
			}
		}
	
	}else if("pxxmdmb" == table){
	//ͨ����ѵ��Ŀ��֤
		var dm = inputV[0].value;
		if(dm !=""){
			if(isNumber(dm) == false){
				alert("��Ŀ������������ָ�ʽ��");
				return false;
			}
			if(dm.length > 5){
				alert("��Ŀ����ĳ��Ȳ��ܳ���5��");
				return false;
			}
		}
	}else if("dtjs_xsccb" == table){
	//ͨ��_���Ž���_ѧ����α�
		var xsccdm = inputV[0].value;//����
		var xsccmc = inputV[1].value;//����
		if(xsccdm.length > 20){
			alert("�������ֻ��¼��20���ַ���");
			return false;
		}
		if(xsccmc.length > 25){
			alert("�������ֻ��¼��25���֣�");
			return false;
		}
	}else if("jxjxdmb" == table){
	//��������-��ѵ����
		var jxdm = inputV[0].value;//����
		var jxmc = inputV[1].value;//����
		if(jxdm.length > 10){
			alert("�������ֻ��¼��10���ַ���");
			return false;
		}
		if(jxmc.length > 25){
			alert("�������ֻ��¼��25���֣�");
			return false;
		}
	}else if("dwjlfsdmb" == table){
	//���⽻��-���⽻����ʽ
		var dm = inputV[0].value;//����
		var mc = inputV[1].value;//����
		if(dm.length > 5){
			alert("�������ֻ��¼��5���ַ���");
			return false;
		}
		if(mc.length > 10){
			alert("�������ֻ��¼��10���֣�");
			return false;
		}
	}else if("jxjdmb" == table){
	//��������-��ѧ������
		var dm = inputV[0].value;//����
		var je = inputV[4].value;//���
		var jb = inputV[3].value;//����
		if(dm.length > 10){
			alert("�������ֻ��¼��10���ַ���");
			return false;
		}
		if(je.length > 5){
			alert("������ֻ��¼��5���֣�");
			return false;
		}
		if(jb !=""){
			if(isNumber(jb) == false){
				alert("��ѧ�𼶱���������ָ�ʽ��");
				return false;
			}
			if(jb.length > 2){
				alert("��ѧ�𼶱�ĳ��Ȳ��ܳ���2��");
				return false;
			}
		}
	}else if("dtjs_djdmb" == table){
	//���Ž���-�ȼ������
		var jb = inputV[2].value;//����
		if(jb.length > 10){
			alert("�������ֻ��¼��10���ַ���");
			return false;
		}
	}else if("rychdmb" == table){
	//�����ƺŴ���
		var dm = inputV[0].value;//����
		var mc = inputV[1].value;//����
		if(dm.length > 10){
			alert("�������ֻ��¼��10���ַ���");
			return false;
		}
		if(mc.length > 15){
			alert("�������ֻ��¼��15���֣�");
			return false;
		}
	}else if("dwjlxmdmb" == table){
	//���⽻�������
		var nd = inputV[4].value;//���
		if(isNumber(nd) == false){
				alert("��ȱ��������ָ�ʽ��");
				return false;
			}
		if(nd.length > 4){
			alert("������ֻ��¼��4���ַ���");
			return false;
		}
	}else if("dwjlfldmb" == table){
	//���⽻������
		var dm = inputV[0].value;//����
		var mc = inputV[1].value;//����
		if(dm.length > 5){
			alert("�������ֻ��¼��5���ַ���");
			return false;
		}
		if(mc.length > 10){
			alert("�������ֻ��¼��10���֣�");
			return false;
		}
	}else if("dtjs_djdmb" == table){
	//���Ž��裨�ȼ������
		var dm = inputV[0].value;//����
		var mc = inputV[1].value;//����
		var jb = inputV[2].value;//����
		if(dm.length > 10){
			alert("�������ֻ��¼��10���ַ���");
			return false;
		}
		if(mc.length > 20){
			alert("�������ֻ��¼��20���֣�");
			return false;
		}
		if(jb.length > 5){
			alert("�������ֻ��¼��5���֣�");
			return false;
		}
	}else if("gwxzdmb" == table){
	//��λ���ʴ����
		var dm = inputV[0].value;//����
		var mc = inputV[1].value;//����
		if(dm.length > 8){
			alert("��λ���ʴ������ֻ��¼��8���ַ���");
			return false;
		}
		if(mc.length > 25){
			alert("��λ�����������ֻ��¼��25���ַ���");
			return false;
		}
	}else if("yrdwdmb" == table){
	//���˵�λ�����
		var dm = inputV[0].value;//����
		var yrdwmc = inputV[1].value;//���˵�λ����
		var lxr = inputV[2].value;//��ϵ��
		var lxdh = inputV[3].value;//��ϵ�绰
		var dlm = inputV[7].value;//��¼��
		var yrdwdz = inputV[8].value;//���˵�λ��ַ 
		if(dm.length > 8){
			alert("�������ֻ��¼��8���ַ���");
			return false;
		}
		if(yrdwmc.length>25){
			alert("���˵�λ�������ֻ��¼��25���ַ���");
			return false;
		}
		if(lxr.length>10){
			alert("��ϵ�����ֻ��¼��10���ַ���");
			return false;
		}
		if(lxdh.length>50){
			alert("��ϵ�绰���ֻ��¼��50���ַ���");
		}
		if(dlm.length > 10){
			alert("��½�����ֻ��¼��10���֣�");
			return false;
		}
		if(yrdwdz.length>100){
			alert("���˵�λ��ַ�ֻ��¼��100����");
			return false;
		}
		if(isNumber(lxdh) == false){
			alert("��ϵ�绰���������ָ�ʽ��");
			return false;
		}
	}else if("qgzx_jcfsdmb" == table){
	//�ڹ���ѧ�Ƴ귽ʽ�����
		var bz = inputV[2].value;//�Ƴ��׼
		if(isNumber(bz) == false){
			alert("�Ƴ��׼���������ָ�ʽ��");
			return false;
		}
		if(bz.length > 4){
			alert("�Ƴ��׼���ֻ��¼��4���ַ���");
			return false;
		}
	}
	return true;
}
