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
				<em>���ĵ�ǰλ�ã�</em><a>�������� - ��������չʾ</a>
			</p>
		</div>
		<!-- ���� end-->
		
		<html:form action="/gyglJbsz">
		<div class="procedure_xg">	
			<!-- �������� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num01_xg"></span><span class="title">��������</span></h3>
				<ul>
					<!-- ������������ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjjbsz_msg');" 
								onmouseout="hideMsgDiv('pjjbsz_msg')">
							<a>
								<span>������������</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ������Աȷ�� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjryqd_msg');" 
								onmouseout="hideMsgDiv('pjryqd_msg')">
							<a>
								<span>������Աȷ��</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ������Ŀ���� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjxmsz_msg');" 
								onmouseout="hideMsgDiv('pjxmsz_msg')">
							<a>
								<span>������Ŀ����</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="pjjbsz_msg" class="hide" style="left: 120px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							��������������ѧ�꣬ѧ�ڣ���ȵ�ȫ����Ϣ��Ӱ�������������̡�
						</p>	
					</div>
				</div>
				
				<div id="pjryqd_msg" class="hide" style="left: 280px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							����ѧ��������ѧ�����ڵİ༶�����뵱ǰѧ�����ڵİ༶��ͬ��������������ʼ֮ǰ��
							��Ҫ��ȷ����Щѧ�����ʸ��������Լ������ڵĲ��š�
							</br>
							ע��ֻ����������Ա���е�ѧ���������ʸ�������������⣬
							ÿ��������һ�β�����ģ��ʱ������ִ�и�ģ��ġ�ͬ�����š��Լ���ͬ��ѧ������
						</p>	
					</div>
				</div>
				
				
				<div id="pjxmsz_msg" class="hide" style="left: 440px;top: 80px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							����ά��������������������Щ��Ŀ����Ҫά������룬���ƣ��Ƿ���Ҫ�������ã���˼���
							���ȵ�������ԣ�ֻ��ά����ɲ����������ѡ�����Ŀ�������ڡ���Ŀ���á�ģ����Զ����������
							���ã��������õȵȣ�ȫ����ɺ󷽿�ʹ����Ŀ����������̣��Ա�ѧ�����룬��ʦ��ˡ�
							</br>
							ע��������Ŀ��Ҫÿ���������ڶ��������ã���ģ���ṩ�����ơ���һ�������ڵĹ��ܣ����������������һ������Ŀ
							�����Ա仯�Ļ�������ֱ��ִ�дˡ����ơ����ܡ�
						</p>	
					</div>
				</div>
				
			</div>
			<!-- �������� end-->
		
			<div class="arrow_02"></div>
		
			<!-- ��Ŀ���� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num02_xg"></span><span class="title">��Ŀ����</span></h3>
				<ul>
					<!-- ������������ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjrssz_msg');" 
								onmouseout="hideMsgDiv('pjrssz_msg')">
							<a>
								<span>������������</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- ������������ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('pjtjsz_msg');" 
								onmouseout="hideMsgDiv('pjtjsz_msg')">
							<a >
								<span>������������</span>			
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- ����������� -->
					<li>
						<div style="position:relative;z-index:0;" 
								onmousemove="displayMsgDiv('pjjdsz_msg');" 
								onmouseout="hideMsgDiv('pjjdsz_msg')">
							<a  >
								<span>�����������</span>				
							</a>
						</div>
					</li>
					<em class="arrow_non"></em>
					<!-- ��Ŀ������Χ���� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xmtzsz_msg');" 
								onmouseout="hideMsgDiv('xmtzsz_msg')">
							<a >
								<span>��Ŀ������Χ����</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="pjrssz_msg" class="hide" style="left: 120px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							����Ŀ���ô����õĲ�����Ҫ�������õ���Ŀ�������ٴ˴����������������á�
							</br>
							�����������ÿ����Ŀ�������������꼶������<bean:message key="lable.xb" />������רҵ���꼶�������༶��������������̵����һ�����п��ƣ�
							ͨ���������������������򲻿ɱ����ͨ����
							</br>
							ע���������<bean:message key="lable.xb" />�������ǰ༶��������Ŀ���ô�����ָ��������Ŀ��Ҫ�������ö�û�д˴��������õ���Ŀ��
							����޷���ͨ����
						</p>	
					</div>
				</div>
				
				<div id="pjtjsz_msg" class="hide" style="left: 280px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							Ϊ��Ŀ��������������������������ѧ�����޷��ڡ���Ŀ���롱ģ��Ը���Ŀ�������롣
						</p>	
					</div>
				</div>
				
				<div id="pjjdsz_msg" class="hide" style="left: 440px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							����������Ŀ�Ĳ��ɼ����Ŀ�����磬��á�һ�Ƚ�ѧ�𡱵�ǰ���ǲ����Ի�á����Ƚ�ѧ���Լ�
							�����Ƚ�ѧ�𡱣���һ�����Ƚ�ѧ��֮�䲻�ɼ�ã���������̵����һ����������ơ�
						</p>	
					</div>
				</div>
				
				<div id="xmtzsz_msg" class="hide" style="left: 580px;top: 180px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							��������̵����һ������������</br>
							���ޣ���õ�������µĲ��ܻ��</br>
							����Ŀ������������߾����Ƿ���</br>
							Ҫ����������һ��������Ŀ������</br>
							���Ե�������Щ��Ŀ�ڱ�ģ�����</br>
							���á�</br>
							ע�������ɹ��󣬽�������һ����</br>
							ѧ���ĵ�����Ŀ�������¼������</br>
							�����̣��������¼��Ȼ��Ҫ����</br>
							������̲���ʹ��ѧ����ø���Ŀ��
						</p>	
					</div>
				</div>

			</div>
			<!-- ��Ŀ���� end-->
			
			<div class="arrow_02"></div>
			
			<!-- �������� -->
			<div class="procedure_row_xg">
				<h3><span class="num_xg num03_xg"></span><span class="title">��������</span></h3>
				<ul>
					<!-- ѧ������ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xssq_msg');" 
								onmouseout="hideMsgDiv('xssq_msg')">
							<a >
								<span>ѧ �� �� ��</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- ��Ŀ��� -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('xmsh_msg');" 
								onmouseout="hideMsgDiv('xmsh_msg')">
							<a  >
								<span>�� Ŀ �� ��</span>			
							</a>
						</div>
					</li>
					<em class="arrow_01"></em>
					<!-- �����ѯ -->
					<li>
						<div style="position:relative;z-index:0;"
								onmousemove="displayMsgDiv('jgcx_msg');" 
								onmouseout="hideMsgDiv('jgcx_msg')">
							<a  >
								<span>�� �� �� ѯ</span>			
							</a>
						</div>
					</li>
				</ul>
				<!-- ��ʾ��Ϣ -->
				<div id="xssq_msg" class="hide" style="left: 120px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							��ѧ���û���½�����뿪�������������Ŀ�����п�������Щ��Ŀ������Ŀ���������������ơ�
							</br>
							ע����ʦ�û�Ҳ���԰�ѧ�����룬ͬ�����������ơ�
						</p>
					</div>
				</div>

				<div id="xmsh_msg" class="hide" style="left: 280px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							��½�û����ڵĸ�λ��������У��������Ӧ�ø����������Ŀ������û�ӵ��2��������
							�ĸ�λʱ�����ʱ��Ҫȷ��������һ���������ˣ�����Ŀ������Ϊ���һ��ͨ����ʱ��
							��Ҫ�������������Լ�����������ͬʱ������Ŀ���������
							</br>
							ע�������ֻ�ܹ����ǰһ�������ͨ���������¼���������߽�ĳ�����¼���˻ء��Ļ���
							����Ҫǰһ����������˺󣬲ſ��Լ�����ˡ�
						</p>	
					</div>
				</div>
				
				<div id="jgcx_msg" class="hide" style="left: 440px;top: 285px;">
					<div class="prompcon" style="width: 250px">
						<p id="tsxx_span">
							��ѯ������Ŀ��������˽�������
						</p>	
					</div>
				</div>
			</div>
			<!-- �������� end-->
			
		</div>
		</html:form>
	</body>
</html>