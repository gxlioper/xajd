<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
	</head>
	<script language="javascript" src="js/function.js"></script>
	<script language="javascript" src="js/fdyglFunction.js"></script>
	<body >
		<html:form action="/wjstwh" method="post">
			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>����Ա���� - ��Ϣά�� - ����Ա���������ʾ�ά��</a>
				</p>
			</div>
			<div class="toolbox">
			 <!-- ��ť -->
			 <logic:equal value="yes" name="writeAble" scope="request">
			 <div class="buttonbox">
			    <ul>
				<li> <a href="#" onclick="MoreDo('add');" class="btn_zj"> ���� </a> </li>
			      <li> <a href="#" onclick="MoreDo('modi');" class="btn_xg"> �޸� </a> </li>
				<li> <a href="#" onclick="MoreDo('del')" class="btn_sc"> ɾ�� </a> </li>
				<li> <a href="#" onclick="MoreDo('xxwh')" class="btn_sh"> ����ѡ��ά�� </a> </li>
				<li> <a href="#" onclick="MoreDo('wjyl')" class="btn_ck"> �ʾ�Ԥ�� </a> </li>
			    </ul>
			 </div>
			 </logic:equal>
			
			<input type="hidden" id="realTable" name="realTable"
				value="<bean:write name="realTable" scope="request"/>" />
			<input type="hidden" id="search_go" onclick="refreshForm('/xgxt/wjstwh.do')"/>
 			<div class="searchtab">
				<table width="100%" border="0">
				      <tfoot>
				        <tr>
				          <td colspan="6">
				            <div class="btn">
				              <button onclick="refreshForm('/xgxt/wjstwh.do?dcAct=save')">
										����
								</button>
				            </div>
				          </td>
				        </tr>
				      </tfoot>
				<thead>
					<tr>
						<td align="left" colspan="2">
							<bean:write name="fdyglForm" property="xn" />ѧ�� 
							(<bean:write name="fdyglForm" property="nd" />���)�� 
							��<bean:write name="fdyglForm" property="xq" />ѧ�� ����Ա���������ʾ�ά��			
						</td>
					</tr>
				</thead>
			<logic:empty name="rs">
				<br />
				<br />
				<p align="center">
				    û���κ����⣬�뵥��"���"��ť������⣡					
				</p>
			</logic:empty>
			<logic:notEmpty name="rs">
						<tbody>
							<tr>
								<th width="110" align="right">
								�����ʾ��Ƿ񿪷�:
								</th>
								<td align="left">
									<input type="radio" name="sfkf" value="1" <logic:equal name="sfkf" value="1"> checked </logic:equal>>
									���� &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="radio" name="sfkf" value="0"<logic:equal name="sfkf" value="0"> checked </logic:equal>>
									�ر�
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				</div>
				<div class="formbox">
			    <h3 class="datetitle_01">
			    <span>
			    	��ѯ���&nbsp;&nbsp;
			    	<logic:empty name="rs">
						<font color="red">δ�ҵ��κμ�¼��</font> 
			 		 </logic:empty>
			 		 <logic:notEmpty name="rs">
			 		 	��¼����
						<bean:write name="rsNum" />
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<font color="blue">��ʾ��˫��һ�п��Բ鿴����ѡ����ϸ��Ϣ��������ͷ��������</font>
			 		 </logic:notEmpty>
			    </span>
			    </h3>
					  <table summary="" class="dateline" align="" width="100%">
						<thead>
							<tr align="center" style="cursor:hand">
                              <logic:iterate id="tit" name="topTr" offset="0">
                                <td id="<bean:write name="tit" property="en"/>"
                                    onclick="tableSort(this)" nowrap>
                                    <bean:write name="tit" property="cn"/>
                                </td>
                              </logic:iterate>
							</tr>
						</thead>
						<tbody>
						<logic:iterate  name="rs" id="s">
						   <tr onclick="rowOnClick(this)" style="cursor:hand"
						       ondblclick="MoreDo('view')">
								<td>
									<logic:iterate id="v" name="s" offset="0" length="1">
										<bean:write name="v" />
									</logic:iterate>
								</td>
								<logic:iterate id="v" name="s" offset="1">
									<td>
										<bean:write name="v" />
									</td>
								</logic:iterate>
							</tr>
						</logic:iterate>
						</tbody>
					</table>
				</div>		
			</logic:notEmpty>
		</html:form>
		<script type="text/javascript" src="js/bottomButton.js"></script> 
	</body>
	<logic:equal value="yes" name="done">
	<script type="text/javascript" >	    
	    alert("�����ɹ���");
	</script> 
	</logic:equal>
	<logic:equal value="no" name="done">
	<script type="text/javascript" >	   
	    alert("����ʧ�ܣ�");
	</script> 
	</logic:equal>
</html>
