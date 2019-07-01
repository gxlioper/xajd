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
                <span>系统已到期，暂时不可访问！</span>
            </div>
            <div class="term-of-validity">
                <img src="images/ico-clock.png">
                <span>有效期：${startDate}- ${expireDate}</span>
            </div>
        </div>
        <div class="support">
            <div class="support-word">
                <div class="support-word-title">版本</div>
                <div class="support-word-con">
                    <div>学工系统 <bean:message key="Version"/></div>
                    <div>&copy; Copyright 1999-2016 中国 正方软件股份有限公司 保留版权所有</div>
                </div>
            </div>
            <div class="support-word">
                <div class="support-word-title">支持</div>
                <div class="support-word-con">
                    <div>电话&nbsp;400-102-8686</div>
                    <div>邮箱&nbsp;zfsoft@zfsoft.com</div>
                    <div>网站&nbsp;WWW.zfsoft.com</div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bot-frame">
    <div class="container">
        <div class="copyright">
            <p>版权所有 Copyright 1999-2016 中国 正方软件股份有限公司 浙ICP备11024936</p>
            <p>浙江省杭州市西湖区紫霞街176号互联网创新创业园2号301</p>
        </div>
    </div>
</div>

</body>
	</body>
</html>
