<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<!-- ���ߣ�qph. ��ע�������˵�ҳ�������ע��ȥ���Ǻ��ˣ��� -->

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script type="text/javascript">

		function sjDc(){
			var url = "general_xsxx_tjcx.do?method=dcsjByType&type=nj";
			document.forms[0].action = url;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
			document.forms[0].target = "_self";	
		}
	
	</script>
	<body>
	<html:form action="/general_xsxx_tjcx" method="post">
		<!-- �๦�ܲ����� -->
		<div class="toolbox">
			<!-- ��ť -->
			<div class="buttonbox">
				<ul>
					<li>
						<a id="add" onclick="sjDc();" class="btn_dc"> �� �� </a>
					</li>
				</ul>
			</div>
		</div>
		<div class="class_infor after">
			<ul>
				<logic:notEmpty name="rsList">
					<logic:iterate id="r" name="rsList">
						<li>
							<h2>
								<a href="general_xsxx_tjcx.do?method=tjcxByNjXy&nj=${r.nj }">${r.nj }</a>
							</h2>
							<div class="con">
								<p class="num_all">
									ѧ����
									<em>${r.allrs }</em>��
								</p>
								<p>
									����
									<em>${r.man }</em>��&nbsp;&nbsp;&nbsp;ռ ${r.manbl }&nbsp;&nbsp;&nbsp;
									Ů��
									<em>${r.woman }</em>��&nbsp;&nbsp;&nbsp;ռ  ${r.womanbl }
								</p>
							</div>
						</li>
					</logic:iterate>
				</logic:notEmpty>
			</ul>
		</div>
		</html:form>
	</body>
</html>
