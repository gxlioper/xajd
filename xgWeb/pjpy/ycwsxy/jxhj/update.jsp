<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead.ini" %>
<script language="javascript" src="/xgxt/pjpy/xibeierminyuan/js/xbemyJs.js"></script>
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<body onload="xyDisabled('xy');">
	<html:form action="/pjpyycwswh" method="post">
		<div class="title">
       		<div class="title_img" id="title_m">
         		��ǰ����λ�ã��������� - ��Ϣά�� - ��ѵ��
       		</div>
    	</div>
    	<input type="hidden" id="zyV" name="zyV" value=""/>
    	<input type="hidden" id="bjV" name="bjV" value=""/>
    	<input type="hidden" id="act" name="act" value="save"/>
    	<input type="hidden" id="pkValue" name="pkValue" value="${pkValue }"/>
    	<input type="hidden" id="userType" name="userType" value="${userType }"/>
			<table style="width:100%" class="tbstyle">
				<thead>
					<tr>
						<td colspan="4" align="center">
							�����޸�
						</td>
					</tr>
				</thead>
				<tr style="width:22px">
					<td align="right">
						�꼶:
					</td>
					<td align="left">
						<html:select property="nj" styleId="nj" style="width:90px"
							onchange="initZyList();initBjList()" styleClass="select">
								<html:option value=""></html:option>
								<html:options collection="njList" property="nj" labelProperty="nj"/>
							</html:select>
					</td>
					<td align="right">
						<bean:message key="lable.xsgzyxpzxy" />:
					</td>
					<td align="left">
						<html:select property="xydm" onchange="initZyList();initBjList()"
								styleClass="select" style="width:180px" styleId="xy">
									<html:option value=""></html:option>
									<html:options collection="xyList" property="xydm"
										labelProperty="xymc" />
								</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
						רҵ:
					</td>
					<td align="left">
						<html:select property="zydm" onchange="initBjList()" style="width:180px" 
								styleClass="select" styleId="zy">
									<html:option value=""></html:option>
									<html:options collection="zyList" property="zydm"
										labelProperty="zymc" />
								</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>�༶:
					</td>
					<td align="left">
						<html:select property="bjdm" style="width:180px" 
								styleClass="select" styleId="bj">
									<html:option value=""></html:option>
									<html:options collection="bjList" property="bjdm"
										labelProperty="bjmc" />
								</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
						<font color="red">*</font>ѧ��:
					</td>
					<td align="left">
						<html:select property="xn" style="width:90px" styleClass="select"
									styleId="xn">
									<html:options collection="xnList" property="xn"
										labelProperty="xn" />
								</html:select>
					</td>
					<td align="right">
						<font color="red">*</font>��ѵ����Ŀ:
					</td>
					<td align="left">
						<html:select property="jxhjxm" styleClass="select" style="width:80px" styleId="jxhjxm">
								<html:option value=""></html:option>
									<html:options collection="jxjxdmList" property="dm" labelProperty="mc"/>
								</html:select>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
						����ϸ:
					</td>
					<td align="left" colspan="3">
						<html:textarea property="hjmx" styleId="hjmx" style="width:95%" rows="5" ></html:textarea>
					</td>
				</tr>
				<tr style="width:22px">
					<td align="right">
						��ע:
					</td >
					<td align="left" colspan="3">
						<html:textarea property="bz" styleId="bz" style="width:95%" rows="5"></html:textarea>
					</td>
				</tr>
			</table>
			<div class="buttontool" align="center">
			<logic:notEqual value="view" name="act">
					<button type="button" class="button2" id="btn_save" 
						onclick="saveinfo('pjpy_ycws_modijxhjwh.do','bj-xn-jxhjxm');"
						style="width:80px">
						�� ��
					</button>
					&nbsp;&nbsp;&nbsp;&nbsp;
					</logic:notEqual>
					<button type="button" class="button2" id="btn_close" onclick="Close();return false;" style="width:80px"
						id="buttonClose">
						�� ��
					</button>
				</div>	
    	<jsp:include flush="true" page="/syscommon/saveprompt.jsp"></jsp:include>
	</html:form>
</body>