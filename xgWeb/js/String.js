/** 
 * use: 
 * var str = '     chenhuamao is a student!      '; 
 * str.trim(); 
 * out:chenhuamao is a student! 
 */ 
String.prototype.trim = function() { 
 //用正则表达式将前后空格用空字符串替代 
    return this.replace(/(^\s*)|(\s*$)/g, "");   
}

String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}