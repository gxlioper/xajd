//�����ļ��ϴ��ķ���·��
var uploadPath = 'editUpload.do?method=upload';
var showPath ='editUpload.do?method=showUpload';
var editor;
KindEditor.ready(function(K) {
	editor = K.create('#editorid', {
		cssPath : 'comm/editor/plugins/code/prettify.css',
		uploadJson : uploadPath,
		fileManagerJson : showPath,
		width : '100%',
		allowFileManager : true,
		items : [
				// 'code', �������
				// 'multiimage',�����ϴ�ͼƬ
				'source', '|', 'undo', 'redo', '|', 'preview', 'print',
				'template', 'cut', 'copy', 'paste', 'plainpaste', 'wordpaste',
				'|', 'justifyleft', 'justifycenter', 'justifyright',
				'justifyfull', 'insertorderedlist', 'insertunorderedlist',
				'indent', 'outdent', 'subscript', 'superscript', 'clearhtml',
				'quickformat', 'selectall', '/',
				'formatblock', 'fontname', 'fontsize', '|', 'forecolor',
				'hilitecolor', 'bold', 'italic', 'underline', 'strikethrough',
				'lineheight', 'removeformat', '|', 'image', 'flash', 'media',
				'insertfile', 'table', 'hr', 'emoticons', 'baidumap',
				'pagebreak', 'anchor', 'link', 'unlink', '|', 'about' ]
	});
});
