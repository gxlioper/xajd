/**
 * user 982
 */
var myBar;
/**
 * ���ؽ�����
 * @param barkey  Ψһkey
 * @param calback �ص�����
 * @return
 */
function loadBar(barkey,calback,time){
	//Ĭ��ʱ��
	if(!time){
		time=1000;
	}
	myBar=setInterval(function(){
	 	jQuery.ajax({
			url:"browser.do?method=getProgressBar",
			data:{barkey:barkey},
			type:"post",
			dataType:"json",
			success:function(data){
				jQuery("#bar").css("width",data.rate+"%");
				jQuery("#bl").text(data.rate+"%");
				if(data.finish){
					clearInterval(myBar);
				}
				//�����������
				if(calback&&!calback(data)){
					clearInterval(myBar);
				}else{
					jQuery("#progress").show();
				}
			}
		});	
	},time);
}
/**
 * ֹͣ������
 * @return
 */
function stopBar(){
	clearInterval(myBar);
	jQuery("#progress").hide();
}