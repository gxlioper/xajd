<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>

		<script type="text/javascript">

            //�жϷ����ն�
            var browser={
                versions:function(){
                    var u = navigator.userAgent, app = navigator.appVersion;
                    return {
                        trident: u.indexOf('Trident') > -1, //IE�ں�
                        presto: u.indexOf('Presto') > -1, //opera�ں�
                        webKit: u.indexOf('AppleWebKit') > -1, //ƻ�����ȸ��ں�
                        gecko: u.indexOf('Gecko') > -1 && u.indexOf('KHTML') == -1,//����ں�
                        mobile: !!u.match(/AppleWebKit.*Mobile.*!/), //�Ƿ�Ϊ�ƶ��ն�
                        ios: !!u.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/), //ios�ն�
                        android: u.indexOf('Android') > -1 || u.indexOf('Adr') > -1, //android�ն�
                        iPhone: u.indexOf('iPhone') > -1 , //�Ƿ�ΪiPhone����QQHD�����
                        iPad: u.indexOf('iPad') > -1, //�Ƿ�iPad
                        webApp: u.indexOf('Safari') == -1, //�Ƿ�webӦ�ó���û��ͷ����ײ�
                        weixin: u.indexOf('MicroMessenger') > -1, //�Ƿ�΢�� ��2015-01-22������
                        qq: u.match(/\sQQ/i) == " qq" //�Ƿ�QQ
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

		<!--��������start-->
		<div class="page_prompt">
			<div class="page_promptcon">
				<h3>
					��ܰ��ʾ��
				</h3>
				<p>
					��֧��IE�����������ʹ�ùȸ����������ʹ��360���������ѡ����ģʽ��
				</p>
			</div>
			<p>
				&nbsp;
			</p>
		</div>
		<!--��������end-->
	</body>
</html>
