<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script type="text/javascript">

            //判断访问终端
            var browser={
                versions:function(){
                    var u = navigator.userAgent, app = navigator.appVersion;
                    return {
                        trident: u.indexOf('Trident') > -1, //IE内核
                        presto: u.indexOf('Presto') > -1, //opera内核
                        webKit: u.indexOf('AppleWebKit') > -1, //苹果、谷歌内核
                        gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//火狐内核
                        mobile: !!u.match(/AppleWebKit.*Mobile.*!/), //是否为移动终端
                        ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios终端
                        android: u.indexOf('Android') > -1 || u.indexOf('Adr') > -1, //android终端
                        iPhone: u.indexOf('iPhone') > -1 , //是否为iPhone或者QQHD浏览器
                        iPad: u.indexOf('iPad') > -1, //是否iPad
                        webApp: u.indexOf('Safari') == -1, //是否web应该程序，没有头部与底部
                        weixin: u.indexOf('MicroMessenger') > -1, //是否微信 （2015-01-22新增）
                        qq: u.match(/\sQQ/i) == " qq" //是否QQ
                    };
                }(),
                language:(navigator.browserLanguage || navigator.language).toLowerCase()
            }

			var pg = '<%=request.getParameter("pg")%>';
			var redirect = "sessionForward.jsp";
			if(pg == "login"){
				redirect = "chkLogout.do";
			}
            if(browser.versions.webKit||browser.versions.gecko){
                window.location.href = "${pageContext.request.contextPath}/"+redirect;
            }
		</script>
	</head>
	<body>

		<!--友情提醒start-->
		<div class="page_prompt">
			<div class="page_promptcon">
				<h3>
					温馨提示：
				</h3>
				<p>
					不支持IE浏览器；建议使用谷歌浏览器，如使用360浏览器，请选择极速模式。
				</p>
			</div>
			<p>
				&nbsp;
			</p>
		</div>
		<!--友情提醒end-->
	</body>
</html>
