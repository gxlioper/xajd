<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!-- ���ߣ�ΰ����� -->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsxx/comm/jbsz/ymsz.js"></script>
		<script language="javascript" defer="defer">

		</script>
	</head>
	<body onload="">
		
		<!-- ��ʾ��Ϣ end-->
		<div class="prompt">
			<h3>
				<span>��ʾ��</span>
			</h3>
			<p>
				��ѡ����Ҫ���õĹ���
			</p>
			<a class="close" title="����"
			   onclick="this.parentNode.style.display='none';"></a>
		</div>
		<!-- ��ʾ��Ϣ end-->		
		
		<html:form action="/sjyJcsjsz">
			<div class="procedure_xg">	
				<!-- ѧ����Ϣ -->
				<div class="procedure_row_xg">
					<h3><span class="num_xg num01_xg"></span><span class="title">ѧ����Ϣ</span></h3>
					<ul>
						<!-- ѧ��������Ϣ -->
						<li>
							<div style="position:relative;z-index:0;">
								<a href="sjyJcsjsz.do?method=jcsjszManage">
									<span>ѧ��������Ϣ</span>			
								</a>
							</div>
						</li>
						<em class="arrow_non"></em>
					</ul>
				</div>
				<!-- ѧ����Ϣ end-->
				
				<div class="arrow_02"></div>
				
				<!-- ������Ϣ -->
				<div class="procedure_row_xg">
					<h3><span class="num_xg num02_xg"></span><span class="title">������Ϣ</span></h3>
					<ul>
						<!-- ������Ϣ -->
						<li>
							<div style="position:relative;z-index:0;">
								<a>
									<span>�� �� �� Ϣ</span>			
								</a>
							</div>
						</li>
						<em class="arrow_non"></em>
					</ul>
				</div>
				<!-- ������Ϣ end-->
			</div>
		</html:form>
	</body>
</html>