<%@ page language="java" contentType="text/html; charset=GBK"%>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="customTag"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/xgutil.js"></script>
<script>
	//�ύ����
	function tjbc(){
		var shjb = val('shjb');//��˼���
		if(shjb == "3"){//����
			if(val('isFdy') == "true"){//����Ա
				if(val('xyshjg') == "ͨ��"){
					alert("<bean:message key="lable.xsgzyxpzxy" />����Ѿ�ͨ������ʱ���ܽ�����˲�����");
					return false;
				}
			}else if(val('userType') == "xy" && val('xxshjg') == "ͨ��"){
				alert('ѧУ����Ѿ�ͨ������ʱ���ܽ�����˲�����');
				return false;
			}			
		}
		if(shjb == "2"){
			if(val('userType') == "xy" && val('xxshjg') == "ͨ��"){
				alert('ѧУ����Ѿ�ͨ������ʱ���ܽ�����˲�����');
				return false;
			}
		}
		
		saveinfo('pjpyTybZhszcp.do?method=zhszcpShOne&act=save&shjb='+shjb,'');
    }
</script>
<body>
	<html:form action="/pjpyTybZhszcp" method="post">
		<input type="hidden" name="userType" id="userType" value="${userType }" />
		<input type="hidden" name="bm" id="bm" value="${bm }"/>
		<input type="hidden" name="shjb" id="shjb" value="${shjb}"/>
		<input type="hidden" name="pkValue" id="pkValue" value="${rs.pkValue}"/>
		<input type="hidden" name="fdyshjg" id="fdyshjg" value="${rs.fdysh}"/>
		<input type="hidden" name="xyshjg" id="xyshjg" value="${rs.xysh}"/>
		<input type="hidden" name="xxshjg" id="xxshjg" value="${rs.xxsh}"/>
		<input type="hidden" name="isFdy" id="isFdy" value="${isFdy}"/>
		
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a></a>
			</p>
		</div>

		<table class="formlist" width="100%">
			<thead>
				<tr>
					<th colspan="4">
						<span>�������</span>
					</th>
				</tr>
			</thead>
			<tbody>
			<tr style="height:23px">
				<th align="right" style="width: 25%">
					<font color="red">*</font>ѧ��
				</th>
				<td align="left"  style="width: 25%">
					${rs.xh}
					
				</td>
				<th align="right"  style="width: 25%">
					����
				</th>
				<td align="left"  style="width: 25%">
					${rs.xm }
				</td>
			</tr>
			<tr style="height:23px">
				<th align="right">
					�Ա�
				</th>
				<td align="left">
					${rs.xb }
				</td>
				<th align="right">
					�꼶
				</th>
				<td align="left">
					${rs.nj }
				</td>

			</tr>
			<tr style="height:23px">
				<th align="right">
					<bean:message key="lable.xsgzyxpzxy" />
				</th>
				<td align="left">
					${rs.xymc }
				</td>
				<th align="right">
					רҵ
				</th>
				<td align="left">
					${rs.zymc }
				</td>
			</tr>
			<tr style="height:23px">

				<th align="right">
					�༶
				</th>
				<td align="left">
					${rs.bjmc }
				</td>
				<th align="right">
					ѧ��
				</th>
				<td align="left">
					${rs.xz }
				</td>
			</tr>

			<tr>
				<logic:equal value="nd" name="pjzq">
					<th align="right">
						���
					</th>
					<td align="left">
						${rs.nd }
					</td>
					<td></td>
					<td></td>
				</logic:equal>
				<logic:equal value="xn" name="pjzq">
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						${rs.xn }
					</td>
					<td></td>
					<td></td>
				</logic:equal>
				<logic:equal value="xq" name="pjzq">
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						${rs.xn }
					</td>
					<th align="right">
						ѧ��
					</th>
					<td align="left">
						${rs.xqmc }
					</td>
				</logic:equal>				
			</tr>
			<customTag:customTable  rsname="rs" nodeslist = "pjpyZhszcpwhActionForm" doType="view" scope="request"/>
			<!--�����Ϣ-->
			<!--�������-->
			<logic:equal value="3" name="shjb">
				<!--����Ա-->
				<logic:equal value="true" name="isFdy">				
					<tr>
						<th align="right">
							����Ա���
						</th>
						<td colspan="3">
							<html:select property="save_fdysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							����Ա���
						</th>
						<td colspan="3">
							<html:textarea property="save_fdyyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />���
						</th>
						<td colspan="">
							${rs.xysh}
						</td>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />���
						</th>
						<td colspan="">
							${rs.xyyj}
						</td>
					</tr>
			
					<tr>
						<th align="right">
							ѧУ���
						</th>
						<td colspan="">
							${rs.xxsh}
						</td>
						<th align="right">
							ѧУ���
						</th>
						<td colspan="">
							${rs.xxyj}
						</td>
					</tr>
					
				</logic:equal>
				<!--end����Ա-->
				<!--�Ǹ���Ա-->
				<logic:notEqual value="true" name="isFdy">
					<!--<bean:message key="lable.xsgzyxpzxy" />-->
					<logic:equal value="xy" name="userType">
					<tr>
						<th align="right">
							����Ա���
						</th>
						<td colspan="">
							${rs.fdysh}
						</td>
						<th align="right">
							����Ա���
						</th>
						<td colspan="">
							${rs.fdyyj}
						</td>
					</tr>
					
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />���
						</th>
						<td colspan="3">
							<html:select property="save_xysh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />���
						</th>
						<td colspan="3">
							<html:textarea property="save_xyyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							ѧУ���
						</th>
						<td colspan="">
							${rs.xxsh}
						</td>
						<th align="right">
							ѧУ���
						</th>
						<td colspan="">
							${rs.xxyj}
						</td>
					</tr>
			
					</logic:equal>
					<!--end<bean:message key="lable.xsgzyxpzxy" />-->
					<!--ѧУ-->
					<logic:notEqual value="xy" name="userType">
					<tr>
						<th align="right">
							����Ա���
						</th>
						<td colspan="">
							${rs.fdysh}
						</td>
						<th align="right">
							����Ա���
						</th>
						<td colspan="">
							${rs.fdyyj}
						</td>
					</tr>
		
					<tr>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />���
						</th>
						<td colspan="">
							${rs.xysh}
						</td>
						<th align="right">
							<bean:message key="lable.xsgzyxpzxy" />���
						</th>
						<td colspan="">
							${rs.xyyj}
						</td>
					</tr>
					<tr>
						
					</tr>
					<tr>
						<th align="right">
							ѧУ���
						</th>
						<td colspan="3">
							<html:select property="save_xxsh" name="rs">
								<html:option value=""></html:option>
								<html:options collection="chkList" property="en" labelProperty="cn"/>
							</html:select>
						</td>
					</tr>
					<tr>
						<th align="right">
							ѧУ���
						</th>
						<td colspan="3">
							<html:textarea property="save_xxyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
						</td>
					</tr>	
					</logic:notEqual>
					<!--endѧУ-->
				</logic:notEqual>
				<!--end�Ǹ���Ա-->			
			</logic:equal>
			<!--end�������-->	

			
			<!--�������-->
			<logic:equal value="2" name="shjb">
			<!--<bean:message key="lable.xsgzyxpzxy" />���-->
			<logic:equal value="xy" name="userType">
				<tr>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />���
					</th>
					<td colspan="3">
						<html:select property="save_xysh" name="rs">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />���
					</th>
					<td colspan="3">
						<html:textarea property="save_xyyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
					</td>
				</tr>
				<tr>
					<th align="right">
						ѧУ���
					</th>
					<td colspan="3">
						${rs.xxsh}
					</td>
				</tr>
				<tr>
					<th align="right">
						ѧУ���
					</th>
					<td colspan="3">
						${rs.xxyj}
					</td>
				</tr>
			</logic:equal>
			<!--end<bean:message key="lable.xsgzyxpzxy" />���-->
			<!--ѧУ���-->
			<logic:notEqual value="xy" name="userType">
			<tr>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />���
					</th>
					<td colspan="3">
						${rs.xysh}
					</td>
				</tr>
				<tr>
					<th align="right">
						<bean:message key="lable.xsgzyxpzxy" />���
					</th>
					<td colspan="3">
						${rs.xyyj}
					</td>
				</tr>
				<tr>
					<th align="right">
						ѧУ���
					</th>
					<td colspan="3">
						<html:select property="save_xxsh" name="rs">
							<html:option value=""></html:option>
							<html:options collection="chkList" property="en" labelProperty="cn"/>
						</html:select>
					</td>
				</tr>
				<tr>
					<th align="right">
						ѧУ���
					</th>
					<td colspan="3">
						<html:textarea property="save_xxyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
					</td>
				</tr>
			</logic:notEqual>
			<!--endѧУ���-->
			</logic:equal>
			<!--end�������-->
			
			<!--һ�����-->
			<logic:equal value="1" name="shjb">
			<tr>
				<th align="right">
					ѧУ���
				</th>
				<td colspan="3">
					<html:select property="save_xxsh" name="rs">
						<html:option value=""></html:option>
						<html:options collection="chkList" property="en" labelProperty="cn"/>
					</html:select>
				</td>
			</tr>
			<tr>
				<th align="right">
					ѧУ���
				</th>
				<td colspan="3">
					<html:textarea property="save_xxyj" name="rs" onblur="chLeng(this,1000)" rows="5" style="width:550px"></html:textarea>
				</td>
			</tr>	
			</logic:equal>		
			<!--endһ�����-->
			<tfoot>
				      <tr>
				        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
				          <div class="btn">
				            <logic:notEqual value="view" name="flag">
								<button type="button" class="" id="btn_save"
					onclick="tjbc()">
					�� ��
				</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									</logic:notEqual>
							<button type="button" class="" id="btn_close" onclick="Close();return false;"id="buttonClose">
								�� ��
							</button>		
				          </div>
				        </td>
				      </tr>
				    </tfoot>	
		</table>	
		
		<!-- ������ʾ��Ϣ -->
		<jsp:include flush="true" page="/syscommon/ty_saveprompt.jsp"></jsp:include>
	</html:form>
</body>

