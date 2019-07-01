var ywxtym = "http://127.0.0.1:80/xgxt";//业务系统域名:端口:工程名
var sjzxyh = "http://10.71.19.18:7070"; //数据中心域名：端口
function srcChange(obj){	
    var href = obj.href;
    var indexZfdxc = (obj.href).indexOf("/zfdxc",0);
    var indexYwxtym = (obj.href).indexOf("ywxtym",0);
    var truehref = obj.href;
    if(indexZfdxc != -1 && indexYwxtym == -1){
    	truehref = sjzxyh + (obj.href).substr(indexZfdxc,(obj.href).length) +"&ywxtym=" +ywxtym ;
    }
    obj.href = truehref;
}
