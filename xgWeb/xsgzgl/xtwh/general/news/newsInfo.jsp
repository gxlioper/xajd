<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<body>
<%--		<div class="tab_cur">--%>
<%--			<p class="location">--%>
<%--				<em>���ĵ�ǰλ��:</em><a>�������</a>--%>
<%--			</p>--%>
<%--		</div>--%>

		<!--��-->
		<div class="mainframe">
			<!--��ͨ����ҳ-->
			<div class="newspage">
				<div class="newspagebg">
					<div class="title">
						<h3>
							<bean:write name="newstitle" />
						</h3>
						<h4>
							
							<span>
							<logic:equal value="10827" name="xxdm">
							�������ڣ�
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
							 ����ʱ�䣺
							</logic:notEqual>
							<bean:write name="pubtime" />
							</span>
							<logic:notEqual value="12869" name="xxdm">
							<span>
							�����ˣ�
							<logic:equal value="10827" name="xxdm">
								<bean:write name="xm" />
								</span><span>
								�����˲��ţ�
								<bean:write name="bmmc" />
							</logic:equal>
							<logic:notEqual value="10827" name="xxdm">
								<bean:write name="puber" />
							</logic:notEqual>
							</span>
							</logic:notEqual>
							<logic:equal value="12869" name="xxdm">
							<span>
								�������ţ�
								<bean:write name="bmmc" />
							</span>
							</logic:equal>
						</h4>
					</div>
					<div class="newcont">
						<bean:write name="newscont" filter="false" />

					</div>

					<div class="newsbar">
						<button type="button" onclick="javascript:window.window.close();" name="�ر�"
							class="button">
							�� ��
						</button>
					</div>
				</div>

			</div>


		</div>

<%--		<div class="botframe">--%>
<%--			<div class="copy">--%>
<%--				<span>������λ��������������ɷ����޹�˾ ��Ȩ������&copy;2010 ��ϵ�绰��0570-89902888</span>--%>
<%--			</div>--%>
<%--		</div>--%>
</body>
</html>
