<%@ page language="java" import="java.util.*" contentType="text/html; charset=GBK"%>
<!-- 设置登记表用 固定写法 -->
		<html:form action="/comm_bbdmpz?method=queryBbdmList" styleId="commBbdmpzForm" method="post">
			<!-- 模块名称 -->
			<input type="hidden" name="mkdm" value="${bbdmModel.mkdm}"/>
			<input type="hidden" name="ywid" value="${bbdmModel.ywid}"/>
			<input type="hidden" name="dybb" value="${bbdmModel.dybb}"/>
			<input type="hidden" name="thlj" value="${bbdmModel.thlj}"/>
			<!-- 模块名称 -->
			<!-- 当前对用报表的GUID -->
			<!-- 预留 标题 , 学期 ， 学年-->
			<input type="hidden" name="h_title" value="${bbdmModel.h_title}"/>
			<input type="hidden" name="h_xn" value="${bbdmModel.h_xn}"/>
			<input type="hidden" name="h_xqmc" value="${bbdmModel.h_xqmc}"/>
			<!-- 预留学期 ， 学年-->
			<!-- 自定义 可自己选用，目前最多只支持10个参数-->
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
			<!-- 自定义 可自己选用，目前最多只支持10个参数-->
		</html:form>
		<!-- 设置登记表用 -->