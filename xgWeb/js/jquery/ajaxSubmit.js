/*
 * 
 * Ajax表单提交
 * 基于 jquery.form.js
 * @auth qph
 * 日期2013-4-11
 */
/**
 * 局部表单提交，无返回结果
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
 * 局部表单提交,提交执行成功后默认弹窗显示message
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
 * 局部表单提交,提交执行成功后返回结果(返回后处理函数自定义)
 * @param url
 */
function ajaxSubFormWithFun(id,url,fun){
	
	var tips = loading();	
	try{ // 捕获异常：SCRIPT5007: 无法获取属性“style”的值: 对象为 null 或未定义
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
