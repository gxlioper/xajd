//根据异动类别初始化学籍信息
function initXjByYdlb(ydlbdm){
	getXjydInfo.getXjydlbxx(ydlbdm,function(data){
		if(data.xjzt != ''){
			setVal('ydhxj',data.xjzt);
			setVal('h_ydhxj',data.xjzt);
			
		}
		if(data.sfzx != ''){
			setVal('ydhsfzx',data.sfzx);
			setVal('h_ydhsfzx',data.sfzx);			
		}
	});
	
}