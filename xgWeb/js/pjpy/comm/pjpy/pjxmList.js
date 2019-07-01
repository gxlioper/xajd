/**
	评奖模块下拉列表连动
	作者：鲁大
**/
var pjpyXmszModel = {};
var colList = ["xmdm","xmmc"];
pjpyXmszModel.sfqy='是';

function initPjpy(){
	initPjpyById('pjxn','pjxq','pjnd','xmlx','xmxz','xmfw','xmdm');
}


function initPjpyById(pjxnId,pjxqId,pjndId,xmlxId,xmxzId,xmfwId,xmdmId){
	if ($(pjxnId)){
		$(pjxnId).onchange = function(){
			getPjxm(pjxnId,pjxqId,pjndId,xmlxId,xmxzId,xmfwId,xmdmId);
		};
	}
	if ($(pjxqId)){
		$(pjxqId).onchange = function(){
			getPjxm(pjxnId,pjxqId,pjndId,xmlxId,xmxzId,xmfwId,xmdmId);
		};
	}
	if ($(pjndId)){
		$(pjndId).onchange = function(){
			getPjxm(pjxnId,pjxqId,pjndId,xmlxId,xmxzId,xmfwId,xmdmId);
		};
	}
	
	if ($(xmlxId)){
		$(xmlxId).onchange = function(){
			getPjxm(pjxnId,pjxqId,pjndId,xmlxId,xmxzId,xmfwId,xmdmId);
		};
	}
	if ($(xmxzId)){
		$(xmxzId).onchange = function(){
			getPjxm(pjxnId,pjxqId,pjndId,xmlxId,xmxzId,xmfwId,xmdmId);
		};
	}	
	
	if ($(xmfwId)){
		$(xmfwId).onchange = function(){
			getPjxm(pjxnId,pjxqId,pjndId,xmlxId,xmxzId,xmfwId,xmdmId);
		};
	}
	getPjxm(pjxnId,pjxqId,pjndId,xmlxId,xmxzId,xmfwId,xmdmId);
}


function getPjxm(pjxnId,pjxqId,pjndId,xmlxId,xmxzId,xmfwId,xmdmId){
	if ($(pjxnId)){
		pjpyXmszModel.pjxn = $(pjxnId).value;
	}
	if ($(pjxqId)){
		pjpyXmszModel.pjxq = $(pjxqId).value;
	}
	if ($(pjndId)){
		pjpyXmszModel.pjnd = $(pjndId).value;
	}
	if ($(xmlxId)){
		pjpyXmszModel.xmlx = $(xmlxId).value;
	}
	if ($(xmxzId)){
		pjpyXmszModel.xmxz = $(xmxzId).value;
	}
	if ($(xmfwId)){
		pjpyXmszModel.xmfw = $(xmfwId).value;
	}
	
	if ($(xmdmId)){
		dwr.engine.setAsync(false);
		var option = [{xmdm:'',xmmc:'-------请选择------'}];
		pjpyCommService.getPjxm(pjpyXmszModel,colList,function(data){
			DWRUtil.removeAllOptions(xmdmId);
			DWRUtil.addOptions(xmdmId,option,'xmdm','xmmc');
			DWRUtil.addOptions(xmdmId,data,'xmdm','xmmc');
		})
		dwr.engine.setAsync(true);
	}
}
