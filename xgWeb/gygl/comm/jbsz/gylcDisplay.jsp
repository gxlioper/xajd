<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" defer="defer">

		</script>
	</head>
	<body onload="">
		
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ԣ����չʾ</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/gyglJbsz">
		<div class="procedure_xg">	
			<!-- ��Ԣ���� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num01_xg"></span><span class="title">��Ԣ����</span></h3>
				<ul>
					<!-- �������� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('gyjbsz_msg');" 
								onmouseout="hideMsgDiv('gyjbsz_msg')">
							<a>
								<span>��������</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="gyjbsz_msg" class="hide" style="left: 120px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							���ù�Ԣ���̿�ʼǰ����ز���
						</p>	
					</div>
				</div>
			</div>
			<!-- ��Ԣ���� end-->
		
			<div class="arrow_02"></div>
		
			<!-- ��Ԣά�� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num02_xg"></span><span class="title">��Ԣά��</span></h3>
				<ul>
					<!-- �������ݴ��� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('jcsjcl_msg');" 
								onmouseout="hideMsgDiv('jcsjcl_msg')">
							<a>
								<span>�������ݴ���</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ҵ�����ݴ��� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('ywsjcl_msg');" 
								onmouseout="hideMsgDiv('ywsjcl_msg')">
							<a >
								<span>ҵ�����ݴ���</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ¥����Ϣά�� -->
					<li>
						<div style="position:relative;z-index:0;" 
								onmousemove="displayMsgDiv('ldxxwh_msg');" 
								onmouseout="hideMsgDiv('ldxxwh_msg')">
							<a  >
								<span>¥����Ϣά��</span>				
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- �Զ��������� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('zdscqs_msg');" 
								onmouseout="hideMsgDiv('zdscqs_msg')">
							<a >
								<span>�Զ���������</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ������Ϣά�� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qsxxwh_msg');" 
								onmouseout="hideMsgDiv('qsxxwh_msg')">
							<a  >
								<span>������Ϣά��</span>				
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="jcsjcl_msg" class="hide" style="left: 120px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�ڹ�Ԣ���̿�ʼǰ��Ԥ�ȵ�����ػ������ݣ�����ֱ��������¼�����ݣ���Ҫ��ȡ�������ʽ��������ǰ���쳣���ݼ�⴦���м�⣩
						</p>	
					</div>
				</div>
				
				<div id="ywsjcl_msg" class="hide" style="left: 280px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�ڹ�Ԣ���̿�ʼǰ��Ԥ�ȵ������ҵ�����ݣ�����ֱ��������¼�����ݣ���Ҫ��ȡ�������ʽ��������ǰ���쳣���ݼ�⴦���м�⣩
						</p>	
					</div>
				</div>
				
				<div id="zdscqs_msg" class="hide" style="left: 580px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							����¥����Ϣ(�����������¼��)������һ���Ĺ����Զ���������Ҫ��������Ϣ�Լ���λ��Ϣ��</br>��ɺ���ǰ��������Ϣά�����鿴���ɽ��
						</p>	
					</div>
				</div>
				
				<div id="ldxxwh_msg" class="hide" style="left: 440px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							��¥����Ϣ����ά��
							</br>
							ע��¥����ά�����ҵ�ǰ��
						</p>	
					</div>
				</div>
				
				<div id="qsxxwh_msg" class="hide" style="left: 120px;top: 230px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							��������Ϣ����ά��
							</br>
							ע��������Ϣ�����ҷ���ʹ�λ�����ǰ��
						</p>	
					</div>
				</div>
			</div>
			<!-- ��Ԣά�� end-->
			
			<div class="arrow_02"></div>
			
			<!-- ���ҹ��� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num03_xg"></span><span class="title">���ҹ���</span></h3>
				<ul>
					<!-- �����Զ����� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qszdfp_msg');" 
								onmouseout="hideMsgDiv('qszdfp_msg')">
							<a >
								<span>�����Զ�����</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- �����ֶ����� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qssdfp_msg');" 
								onmouseout="hideMsgDiv('qssdfp_msg')">
							<a  >
								<span>�����ֶ�����</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- �������鿴 -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qsfpjg_msg');" 
								onmouseout="hideMsgDiv('qsfpjg_msg')">
							<a  >
								<span>�������鿴</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="qszdfp_msg" class="hide" style="left: 120px;top: 340px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							���ݻ�������ģ�������õķ�����󣬽�δ��������ң��Զ��������ѡ��Ĳ��ţ��Բ�������Ϊ׼��
						</p>	
					</div>
				</div>

				<div id="qssdfp_msg" class="hide" style="left: 280px;top: 340px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							���û������ҷ������������ģ�������õķ���������⣬Ҳ��ִ��ȡ���ѷ������ҵĲ���
							</br>
							ע��Ϊ�����û������������Ƚ��С������Զ����䡱������Ȼ�����ڴ˶Խ������΢��
						</p>	
					</div>
				</div>
				
				<div id="qsfpjg_msg" class="hide" style="left: 440px;top: 340px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�鿴�Ѿ�������ҷ���ķ����¼�����⣬Ҳ��ִ��ȡ���ѷ������ҵĲ���
						</p>	
					</div>
				</div>
			</div>
			<!-- ���ҹ��� end-->
			
			<div class="arrow_02"></div>
			
			<!-- ��λ���� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num04_xg"></span><span class="title">��λ����</span></h3>
				<ul>
					<!-- ��λ�Զ����� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('cwzdfp_msg');" 
								onmouseout="hideMsgDiv('cwzdfp_msg')">
							<a  >
								<span>��λ�Զ�����</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- ��λ�ֶ����� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('cwsdfp_msg');" 
								onmouseout="hideMsgDiv('cwsdfp_msg')">
							<a  >
								<span>��λ�ֶ�����</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- ��λ�ֶ����� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('cwfpjg_msg');" 
								onmouseout="hideMsgDiv('cwfpjg_msg')">
							<a  >
								<span>�������鿴</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="cwzdfp_msg" class="hide" style="left: 120px;top: 440px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�������ҷ���ģ����������������ŵ����ң��Զ����佫��λ�������ѡ���ŵ�ѧ��
						</p>	
					</div>
				</div>

				<div id="cwsdfp_msg" class="hide" style="left: 280px;top: 440px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							���û���ѧ������ס��������ң�Ϊ����䴲λ�����⣬Ҳ��ִ��ȡ��ѧ����ס�Ĳ���
							</br>
							ע��Ϊ�����û������������Ƚ��С���λ�Զ����䡱������Ȼ�����ڴ˶Խ������΢��
						</p>	
					</div>
				</div>
				
				<div id="cwfpjg_msg" class="hide" style="left: 440px;top: 440px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�鿴�Ѿ���ɴ�λ����ķ����¼�����⣬Ҳ��ִ��ȡ���ѷ��䴲λ�Ĳ���
						</p>	
					</div>
				</div>
			</div>
			<!-- ��λ���� end-->
			
			<div class="arrow_02"></div>
			
			<!-- ס�޽�� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num05_xg"></span><span class="title">���ҹ���</span></h3>
				<ul>
					<!-- ס�޽������ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('zsggl_msg');" 
								onmouseout="hideMsgDiv('zsggl_msg')">
							<a >
								<span>ס�޽������</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- ��ʷ��Ϣ���� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('lsxxgl_msg');" 
								onmouseout="hideMsgDiv('lsxxgl_msg')">
							<a  >
								<span>��ʷ��Ϣ����</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- �����춯���� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qsydgl_msg');" 
								onmouseout="hideMsgDiv('qsydgl_msg')">
							<a >
								<span>�����춯����</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="zsggl_msg" class="hide" style="left: 120px;top: 540px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�����Ѿ���ס��ѧ����Ϣ�����ɶ���ִ�����޲���
						</p>	
					</div>
				</div>

				<div id="lsxxgl_msg" class="hide" style="left: 280px;top: 540px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�����Ѿ����޵���ʷѧ����Ϣ�����ɶ������ɾ������
						</p>	
					</div>
				</div>
				
				<div id="qsydgl_msg" class="hide" style="left: 440px;top: 540px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							���Ѿ���ס��ѧ�����������������
							</br>
							
						</p>	
					</div>
				</div>
			</div>
			<!-- ס�޽�� end-->
			
		</div>
		</html:form>
	</body>
</html>