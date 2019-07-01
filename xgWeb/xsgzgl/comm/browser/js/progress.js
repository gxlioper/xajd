/**
 * user 982
 */
var myBar;
/**
 * 加载进度条
 * @param barkey  唯一key
 * @param calback 回调方法
 * @return
 */
function loadBar(barkey,calback,time){
	//默认时间
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
				//如果完成则结束
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
 * 停止进度条
 * @return
 */
function stopBar(){
	clearInterval(myBar);
	jQuery("#progress").hide();
}