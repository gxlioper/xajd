/** 
 * use: 
 * var str = '     chenhuamao is a student!      '; 
 * str.trim(); 
 * out:chenhuamao is a student! 
 */ 
String.prototype.trim = function() { 
 //��������ʽ��ǰ��ո��ÿ��ַ������ 
    return this.replace(/(^\s*)|(\s*$)/g, "");   
}

String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}