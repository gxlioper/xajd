//���ڸ�ʽ��js
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, //month        
		"d+" : this.getDate(), //day        
		"h+" : this.getHours(), //hour        
		"m+" : this.getMinutes(), //minute        
		"s+" : this.getSeconds(), //second        
		"q+" : Math.floor((this.getMonth() + 3) / 3), //quarter        
		"S" : this.getMilliseconds()
	//millisecond        
	};
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
};

//���ڼ����������������
/**
*	date:	Date
*	days:	int
*/
function addDays(date,days){ 
	var nd = new Date(date); 
	   nd = nd.valueOf(); 
	   nd = nd + days * 24 * 60 * 60 * 1000; 
	   nd = new Date(nd); 
	   //alert(nd.getFullYear() + "��" + (nd.getMonth() + 1) + "��" + nd.getDate() + "��"); 
	return nd;
}

//���ڼ����������������
/**
*	date:	Date
*	days:	int
*/
function subtractDays(date,days){ 
	var nd = new Date(date); 
	   nd = nd.valueOf(); 
	   nd = nd - days * 24 * 60 * 60 * 1000; 
	   nd = new Date(nd); 
	   //alert(nd.getFullYear() + "��" + (nd.getMonth() + 1) + "��" + nd.getDate() + "��"); 
	return nd;
} 