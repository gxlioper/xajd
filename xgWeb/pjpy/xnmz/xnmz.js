function shqry(url) {
	if (document.getElementById('xn').value=='' 
		|| document.getElementById('jxjdm').value=='') {
		alert('��ѯ������ѧ��,��ѧ��Ϊ��ѡ!');
		return;
	}
	refreshForm(url);
}