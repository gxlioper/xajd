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
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ��Ԣ��������</a>
			</p>
			<p class="help">
				<a href="#" onclick="return false;" onmousedown ="showHelpDiv();">ʹ�ð���</a>
			</p>
		</div>			
		<!-- ���� end-->
		<!-- ��ʾ��Ϣ START-->
		<div class="prompt" id="div_help" style="display: none">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					��ҳ��չʾ��ֻ�ǹ�Ԣ��������ͼ���������ӵ������������������˵���
				</p>
				<a class="close" title="����"
				   onclick="this.parentNode.style.display='none';"></a>
			</div>
		<!-- ��ʾ��Ϣ end-->
		<!-- ���� end-->
		
		<html:form action="/gyglJbsz">
		<div class="procedure_xg">	
			<!-- �������� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num01_xg"></span><span class="title">��������</span></h3>
				<ul>
					<!-- ����ʱ������ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('czsjsz_msg');" 
								onmouseout="hideMsgDiv('czsjsz_msg')">
							<a>
								<span>����ʱ������</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="czsjsz_msg" class="hide" style="left: 120px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							���ô�λ��סҳ�桰ȡ����ס����ť�����Ƿ�����
						</p>	
					</div>
				</div>
				
			</div>
			<!-- �������� end-->
		
			<div class="arrow_02"></div>
		
			<!-- ��Դ����-->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num02_xg"></span><span class="title">��Դ����</span></h3>
				<ul>
					<!-- ¥������ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('ldgl_msg');" 
								onmouseout="hideMsgDiv('ldgl_msg')">
							<a>
								<span>¥����Ϣ����</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ���ҹ��� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('qsgl_msg');" 
								onmouseout="hideMsgDiv('qsgl_msg')">
							<a >
								<span>������Ϣ����</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ��λ���� -->
					<li>
						<div style="position:relative;z-index:0;" 
								onmousemove="displayMsgDiv('cwgl_msg');" 
								onmouseout="hideMsgDiv('cwgl_msg')">
							<a  >
								<span>��λ��Ϣ����</span>				
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="ldgl_msg" class="hide" style="left: 120px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							ά��¥����Ϣ����ͬʱ�������Ҵ�λ
						</p>	
					</div>
				</div>
				
				<div id="qsgl_msg" class="hide" style="left: 280px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							ά��������Ϣ
						</p>	
					</div>
				</div>
				
				<div id="cwgl_msg" class="hide" style="left: 440px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							ά����λ��Ϣ��������λ��ס���������ޣ���λ�Ե�������ס����Ϣ
						</p>	
					</div>
				</div>
			</div>
			<!-- ��Դ���� end-->
			
			<div class="arrow_02"></div>
			
			<!-- ס�޹��� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num03_xg"></span><span class="title">ס�޹���</span></h3>
				<ul>
					<!-- ��λ���� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xssq_msg');" 
								onmouseout="hideMsgDiv('xssq_msg')">
							<a >
								<span>��λ�������</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ��λ��ס -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xmsh_msg');" 
								onmouseout="hideMsgDiv('xmsh_msg')">
							<a  >
								<span>��λ��ס����</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ס�޹��� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('jgcx_msg');" 
								onmouseout="hideMsgDiv('jgcx_msg')">
							<a  >
								<span>ס����Ϣ����</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="xssq_msg" class="hide" style="left: 120px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�����Ҵ�λ�������꼶<bean:message key="lable.xsgzyxpzxy" />��༶
						</p>
					</div>
				</div>

				<div id="xmsh_msg" class="hide" style="left: 280px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							<bean:message key="lable.xsgzyxpzxy" />���Ž�ѧ����ס����<bean:message key="lable.xsgzyxpzxy" />�Ĵ�λ
						</p>	
					</div>
				</div>
				
				<div id="jgcx_msg" class="hide" style="left: 440px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							��Ԣ����Ա������¥����ס����Ϣ
						</p>	
					</div>
				</div>
			</div>
			<!-- ס�޹��� end-->
			
			<div class="arrow_02"></div>
			
			<!-- ͳ�Ʋ�ѯ -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num04_xg"></span><span class="title">ͳ�Ʋ�ѯ</span></h3>
				<ul>
					<!-- ��Ԣס�����ͳ�� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('gyzstj_msg');" 
								onmouseout="hideMsgDiv('gyzstj_msg')">
							<a >
								<span>��Ԣס�����ͳ��</span>			
							</a>
						</div>
					</li>
					<!-- ѧ��ס�����ͳ�� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xszstj_msg');" 
								onmouseout="hideMsgDiv('xszstj_msg')">
							<a  >
								<span>ѧ��ס�����ͳ��</span>			
							</a>
						</div>
					</li>
					<!-- ѧ��ס����Ϣ��ѯ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xszsgl_msg');" 
								onmouseout="hideMsgDiv('xszsgl_msg')">
							<a  >
								<span>ѧ��ס����Ϣ��ѯ</span>			
							</a>
						</div>
					</li>
					<!-- ѧ��������Ϣ��ѯ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('tsgl_msg');" 
								onmouseout="hideMsgDiv('tsgl_msg')">
							<a  >
								<span>ѧ��������Ϣ��ѯ</span>			
							</a>
						</div>
					</li>
				</ul>
				
			</div>
			<!-- ס�޹��� end-->
			<div class="arrow_02"></div>
			
			<div class="procedure_row_xg">
				<h3><span class="num_xg num05_xg"></span><span class="title">��Ԣ����</span></h3>
				<ul>
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('wdbxsq_msg');" 
								onmouseout="hideMsgDiv('wdbxsq_msg')">
							<a>
								<span>�ҵı�������</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('bxsqcl_msg');" 
								onmouseout="hideMsgDiv('bxsqcl_msg')">
							<a>
								<span>�����������</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="wdbxsq_msg" class="hide" style="left: 120px;top: 490px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�Թ�Ԣ��Ʒ���б���
						</p>	
					</div>
				</div>
				
				<div id="bxsqcl_msg" class="hide" style="left: 280px;top: 490px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							�Ա��޼�¼���д���
						</p>	
					</div>
				</div>
			</div>
		</div>
		</html:form>
	</body>
</html>