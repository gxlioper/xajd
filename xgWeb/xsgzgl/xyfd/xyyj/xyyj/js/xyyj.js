
function xscjck(xh) {
    var url = "xyfd_xyyj.do?method=xscjList&xh=" + xh + "&t=" + new Date().getTime();
    showDialog(xh + "成绩单", 800, 350, url);
}

//学生成绩分析
function xscjfx() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("请选择一个您要查看的学生！");
        return false;
    }
    var url = "xyfd_xyyj.do?method=xscjfx&xh=" + rows[0]["xh"] + "&t=" + new Date().getTime();
    window.open(url);
}
//课程成绩分析
function kccjfx() {
    var url = "xyfd_xyyj.do?method=kccjfx&t=" + new Date().getTime();
    window.open(url);
}
//专业成绩分析
function zycjfx() {
    var url = "xyfd_xyyj.do?method=zycjfx&t=" + new Date().getTime();
    window.open(url);
}