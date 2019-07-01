function combogridKeyHandler(obj,step){
	var opts=jQuery.data(obj,"combogrid").options;
	var grid=jQuery.data(obj,"combogrid").grid;
	var _6b7=grid.datagrid("getRows").length;
	var _6b8;
	var _6b9=grid.datagrid("getSelections");
	if(_6b9.length){
		_6b8=grid.datagrid("getRowIndex",_6b9[_6b9.length-1][opts.idField]);
		_6b8+=step;
		if(_6b8<0){
			_6b8=0;
		}
		if(_6b8>=_6b7){
			_6b8=_6b7-1;
		}
	}else{
		if(step>0){
		_6b8=0;
		}else{
			if(step<0){
			_6b8=_6b7-1;
			}else{
			_6b8=-1;
			}
		}
	}
	if(_6b8>=0){
		grid.datagrid("clearSelections");
		grid.datagrid("selectRow",_6b8);
	}
	
	if (step == 0){
		jQuery(obj).combo("hidePanel");
	}
}

function _668(_676,_677,_678){
	var opts=jQuery.data(_676,"combobox").options;
	var data=jQuery.data(_676,"combobox").data;
	var _679=jQuery(_676).combo("panel");
	_679.find("div.combobox-item-selected").removeClass("combobox-item-selected");
	var vv=[],ss=[];
	for(var i=0;i<_677.length;i++){
		var v=_677[i];
		var s=v;
		for(var j=0;j<data.length;j++){
			if(data[j][opts.valueField]==v){
				s=data[j][opts.textField];
				break;
			}
		}
		vv.push(v);
		ss.push(s);
		_679.find("div.combobox-item[value="+v+"]").addClass("combobox-item-selected");
	}
	
	jQuery(_676).combo("setValues",vv);
	
	if(!_678){
		jQuery(_676).combo("setText",ss.join(opts.separator));
	}
};


function _65f(_660,_661){
	var _662=jQuery(_660).combo("panel");
	var item=_662.find("div.combobox-item[value="+_661+"]");
	if(item.length){
		if(item.position().top<=0){
			var h=_662.scrollTop()+item.position().top;
			_662.scrollTop(h);
		}else{
			if(item.position().top+item.outerHeight()>_662.height()){
				var h=_662.scrollTop()+item.position().top+item.outerHeight()-_662.height();
				_662.scrollTop(h);
			}
		}
	}
};

function combobxUpHandler(_664){
	var _665=jQuery(_664).combo("panel");
	var _666=jQuery(_664).combo("getValues");
	var item=_665.find("div.combobox-item[value="+_666.pop()+"]");
	if(item.length){
		var prev=item.prev(":visible");
		if(prev.length){
			item=prev;
		}
	}else{
		item=_665.find("div.combobox-item:visible:last");
	}
	var _667=item.attr("value");
	_668(_664,[_667]);
	_65f(_664,_667);
}


function comboboxDownHandler(_66a){
	var _66b=jQuery(_66a).combo("panel");
	var _66c=jQuery(_66a).combo("getValues");
	var item=_66b.find("div.combobox-item[value="+_66c.pop()+"]");
	if(item.length){
		var next=item.next(":visible");
		if(next.length){
			item=next;
		}
	}else{
		item=_66b.find("div.combobox-item:visible:first");
	}
	var _66d=item.attr("value");
	_668(_66a,[_66d]);
	_65f(_66a,_66d);	
}


function comboboxEnterHandler(obj){
	var _694=jQuery(obj).combobox("getValues");
	jQuery(obj).combobox("setValues",_694);
	jQuery(obj).combobox("hidePanel");
}

function comboboxQueryHandler(obj,url,q){
	jQuery(obj).combobox('reload',url);
	jQuery(obj).combobox('clear');
	jQuery(obj).combobox('setValue',q);
}

function combogridQueryHandler(obj,q){
	jQuery(obj).combogrid('grid').datagrid('reload');
	jQuery(obj).combogrid('clear');
	jQuery(obj).combogrid('setValue',q);
}