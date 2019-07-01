function shqry(url) {
	if (document.getElementById('xn').value=='' 
		|| document.getElementById('jxjdm').value=='') {
		alert('查询条件中学年,奖学金为必选!');
		return;
	}
	refreshForm(url);
}