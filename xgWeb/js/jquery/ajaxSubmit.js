/*
 * 
 * Ajax���ύ
 * ���� jquery.form.js
 * @auth qph
 * ����2013-4-11
 */
/**
 * �ֲ����ύ���޷��ؽ��
 * @param url
 */
function ajaxSubForm(id,url){
	
	var tips = loading();	
	tips.show();
	
	jQuery("#"+id).ajaxSubmit({
		url:url,
		type:'post',
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		success:function(data){
			tips.close();
		}
	});
}

/**
 * �ֲ����ύ,�ύִ�гɹ���Ĭ�ϵ�����ʾmessage
 * @param url
 */
function ajaxSubFormWithMsg(id,url){
	
	var tips = loading();	
	tips.show();
	
	jQuery("#"+id).ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		contentType:"application/x-www-form-urlencoded;charset=utf-8",
		success:function(data){
			alertInfo(data["message"]);
			tips.close();
		}
	});	
}
/**
 * �ֲ����ύ,�ύִ�гɹ��󷵻ؽ��(���غ������Զ���)
 * @param url
 */
function ajaxSubFormWithFun(id,url,fun){
	
	var tips = loading();	
	try{ // �����쳣��SCRIPT5007: �޷���ȡ���ԡ�style����ֵ: ����Ϊ null ��δ����
		tips.show();
	}catch(e){
	}
	
	jQuery("#"+id).ajaxSubmit({
		url:url,
		type:"post",
		dataType:"json",
		success:function(data){
			fun(data);
			tips.close();
		},
		contentType:"application/x-www-form-urlencoded;charset=utf-8"
	});	
}
