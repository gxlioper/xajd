<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini"%>

<script type="text/javascript">
</script>
<body onload="">
	<html:form action="/xmlgpjpy.do" method="post">
		<div class="title">
			<div class="title_img" id="title_m">
				��ǰλ��: �������� - ��� - �������
			</div>
		</div>
		<div id="toolTipLayer" style="position:absolute; visibility: hidden" /></div>
		<center>
			<div class="buttontool" id="btn"
				style="position: absolute;left:1%;top:100px" width="100%">
				<button class="button2" id="btn_add" onclick="showOpenWindow('/xgxt/xmlgpjpy.do?method=gsdsc&type=pytjb','500','200')">
					���ų����Ƽ������
				</button>
				&nbsp;&nbsp;
				<button class="button2" id="btn_xg" onclick="showOpenWindow('/xgxt/xmlgpjpy.do?method=gsdsc&type=jxjgsd','500','200')">
					��ѧ��ʾ�������
				</button>
				&nbsp;&nbsp;
				<button class="button2" id="btn_xg" onclick="showOpenWindow('/xgxt/xmlgpjpy.do?method=gsdsc&type=rychgsd','500','200')">
					�����ƺŹ�ʾ�������
				</button>
			</div>
		</center>
		<div id="tmpdiv"></div>
	</html:form>
	<script language="javascript">
			document.getElementById("btn").style.pixelTop = document.body.clientHeight - 35;
			document.getElementById("btn").style.width = "96%";
			window.setInterval("initBTNTool('btn')",1);
		</script>
</body>
