<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="GBK">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<link href="systemauth/common.css" rel="stylesheet">
	</head>
	<body>
		<div class="top-frame mb20">
    <div class="container">
        <div class="top-con">
            <div class="logo pull-left">
                <img src="images/zf-logo.png">
            </div>
        </div>
    </div>
</div>
<div class="main-frame mb20">
    <div class="container">
        <div class="error-prompt">
            <div class="error-prompt-word">
                <img src="images/ico-lock.png">
                <span>ϵͳ�ѵ��ڣ���ʱ���ɷ��ʣ�</span>
            </div>
            <div class="term-of-validity">
                <img src="images/ico-clock.png">
                <span>��Ч�ڣ�${startDate}- ${expireDate}</span>
            </div>
        </div>
        <div class="support">
            <div class="support-word">
                <div class="support-word-title">�汾</div>
                <div class="support-word-con">
                    <div>ѧ��ϵͳ <bean:message key="Version"/></div>
                    <div>&copy; Copyright 1999-2016 �й� ��������ɷ����޹�˾ ������Ȩ����</div>
                </div>
            </div>
            <div class="support-word">
                <div class="support-word-title">֧��</div>
                <div class="support-word-con">
                    <div>�绰&nbsp;400-102-8686</div>
                    <div>����&nbsp;zfsoft@zfsoft.com</div>
                    <div>��վ&nbsp;WWW.zfsoft.com</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bot-frame">
    <div class="container">
        <div class="copyright">
            <p>��Ȩ���� Copyright 1999-2016 �й� ��������ɷ����޹�˾ ��ICP��11024936</p>
            <p>�㽭ʡ��������������ϼ��176�Ż��������´�ҵ԰2��301</p>
        </div>
    </div>
</div>

</body>
	</body>
</html>
