<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!-- ���õǼǱ��� �̶�д�� -->
		<html:form action="/comm_bbdmpz?method=queryBbdmList" styleId="commBbdmpzForm" method="post">
			<!-- ģ������ -->
			<input type="hidden" name="mkdm" value="${bbdmModel.mkdm}"/>
			<input type="hidden" name="ywid" value="${bbdmModel.ywid}"/>
			<input type="hidden" name="dybb" value="${bbdmModel.dybb}"/>
			<input type="hidden" name="thlj" value="${bbdmModel.thlj}"/>
			<!-- ģ������ -->
			<!-- ��ǰ���ñ����GUID -->
			<!-- Ԥ�� ���� , ѧ�� �� ѧ��-->
			<input type="hidden" name="h_title" value="${bbdmModel.h_title}"/>
			<input type="hidden" name="h_xn" value="${bbdmModel.h_xn}"/>
			<input type="hidden" name="h_xqmc" value="${bbdmModel.h_xqmc}"/>
			<!-- Ԥ��ѧ�� �� ѧ��-->
			<!-- �Զ��� ���Լ�ѡ�ã�Ŀǰ���ֻ֧��10������-->
			<input type="hidden" name="h_zd1" value="${bbdmModel.h_zd1}"/>
			<input type="hidden" name="h_zd2" value="${bbdmModel.h_zd2}"/>
			<input type="hidden" name="h_zd3" value="${bbdmModel.h_zd3}"/>
			<input type="hidden" name="h_zd4" value="${bbdmModel.h_zd4}"/>
			<input type="hidden" name="h_zd5" value="${bbdmModel.h_zd5}"/>
			<input type="hidden" name="h_zd6" value="${bbdmModel.h_zd6}"/>
			<input type="hidden" name="h_zd7" value="${bbdmModel.h_zd7}"/>
			<input type="hidden" name="h_zd8" value="${bbdmModel.h_zd8}"/>
			<input type="hidden" name="h_zd9" value="${bbdmModel.h_zd9}"/>
			<input type="hidden" name="h_zd10" value="${bbdmModel.h_zd10}"/>
			<!-- �Զ��� ���Լ�ѡ�ã�Ŀǰ���ֻ֧��10������-->
		</html:form>
		<!-- ���õǼǱ��� -->