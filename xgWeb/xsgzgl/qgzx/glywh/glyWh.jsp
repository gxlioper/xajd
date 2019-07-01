<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/util.js'></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type='text/javascript' src="js/uicomm.js"></script>
		<script type='text/javascript' src="js/String.js"></script>
		
		<script type="text/javascript" src="js/qgzx/glywh/glyWh.js"></script>
		<script>
		//��ʼ��
		jQuery(document).ready(function(){ 
			searchRs();
		});
		function selectTab(url) {
			document.location.href = url ;
		}
		</script>
	</head>
	<body>
		<div class="tab_cur" >
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv()">ʹ�ð���</a>
			</p>
		</div>
		<!-- ��ʾ��Ϣ -->
		<div class="prompt" id="div_help" style="display: none">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				<span>
					���û��ڴ˴���ȨΪ�ڹ�����Ա�����ڹ�����ģ��Ĺ��ܲ˵��У��û��ɲ��������˵�λ�����پ����ڱ������ڲ��ţ�<br/>
					��Ϊ���в���������λ�������ڹ�ѧ����Ҳ��Ϊȫ�����˵�λ�������ѣ�ά����λ��Ա�����Ϣ
				</span>
			</p>
			<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->
		<html:form action="/qgzx_glygl" method="post">
			<!-- ������ -->
			<%@ include file="/comm/hiddenValue.jsp"%>
			<!-- ������ -->
			<input type="hidden" name="xmdm" id="xmdm" value="${xmdm }" />
			<div class="toolbox" id="dgncz">
				<!-- ��ť -->
				<logic:equal value="yes" name="writeAble">
				<div class="buttonbox">
					<ul>
						<li><a href="#" onclick="showDialog('', 760, 520, 'qgzx_glygl.do?method=glyZj');return false;" class="btn_zj">����</a></li>
						<li><a href="#" onclick="glySc();return false;" class="btn_sc">ɾ��</a></li>
					</ul>
				</div>
				</logic:equal>
				<div class="comp_title" id="comp_title">
			      <ul style="width:90%">
			        <li class="ha"><a href="javascript:void(0);" onclick="selectTab('qgzx_glygl_glywh.do');"><span>�ڹ�����Ա</span></a></li>
			        <li><a href="javascript:void(0);" onclick="selectTab('qgzx_glygl_xqgly.do');"><span>У������Ա</span></a></li>
			      </ul>
			    </div>
				<div style="display: none;">
				<!-- �������� -->
				<%@ include file="/comm/search/superSearchArea.jsp"%>
				</div>
			</div>
				<!-- ������ʾ����ʼ -->
			<div class="main_box">
				<div class="mid_box">
					<div class="title">
						<p>
							<!-- ��ѯ�õ�����������ʾ���� -->
						</p>
					</div>
				</div>
				<h3 class="datetitle_01">
					<span> ��ѯ���&nbsp;&nbsp;</span>
				</h3>
				<div id="div_rs"
					style="width:100%;overflow-x:auto;overflow-y:hidden;">
				</div>

				<!--��ҳ��ʾ-->
				<jsp:include flush="true"
					page="/sjcz/turnpage.jsp?form=qgzxGlyglForm"></jsp:include>
			</div>
			<div id="div_detail" style="display:none">
			</div>	
			<!-- ��ʾ��Ϣ -->
			<%@ include file="/comm/other/tsxx.jsp"%>
		</html:form>
	</body>
</html>
