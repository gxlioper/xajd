
function xscjck(xh) {
    var url = "xyfd_xyyj.do?method=xscjList&xh=" + xh + "&t=" + new Date().getTime();
    showDialog(xh + "�ɼ���", 800, 350, url);
}

//ѧ���ɼ�����
function xscjfx() {
    var rows = jQuery("#dataTable").getSeletRow();
    if (rows.length != 1) {
        showAlertDivLayer("��ѡ��һ����Ҫ�鿴��ѧ����");
        return false;
    }
    var url = "xyfd_xyyj.do?method=xscjfx&xh=" + rows[0]["xh"] + "&t=" + new Date().getTime();
    window.open(url);
}
//�γ̳ɼ�����
function kccjfx() {
    var url = "xyfd_xyyj.do?method=kccjfx&t=" + new Date().getTime();
    window.open(url);
}
//רҵ�ɼ�����
function zycjfx() {
    var url = "xyfd_xyyj.do?method=zycjfx&t=" + new Date().getTime();
    window.open(url);
}