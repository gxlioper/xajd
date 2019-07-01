<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<!-- ͷ�ļ� -->
	<%@ include file="/syscommon/pagehead_V4.ini"%>
	<script language="javascript" src="js/stuinfoFunction.js"></script>
	<script type="text/javascript">
	function printChg(url){
		var xh = document.getElementById("xh").value;
		var jtdz = document.getElementById("jtdz").value;
		var zzmm = document.getElementById("zzmm").value;
		var lxdh = document.getElementById("lxdh").value;
		var qhgj = document.getElementById("gj").value;
		var cgyy= document.getElementById("cgyy").value;
		var jjdbqk = document.getElementById("jjdbqk").value;
		var sqrq = document.getElementById("sqrq").value;
		
		url +="&xh="+xh;
		url +="&jtdz="+jtdz;
		url +="&zzmm="+zzmm;
		url +="&lxdh="+lxdh;
		url +="&cgyy="+cgyy;
		url +="&qhgj="+qhgj;
		url +="&jjdbqk="+jjdbqk;
		url +="&sqrq=" + sqrq;
		
		window.open(url);
	}
	</script>
</head>
	<body>		
		<html:form action="/data_search" method="post">
			<input type="hidden" id="disableEle" name="disableEle" value="xm-xb-xy-nj-zy-bj" />
			<input type="hidden" id="getStuInfo" name="getStuInfo" value="xm-xb-xymc-nj-zymc-bjmc" />
			<input type="hidden" id="url" name="url" value="/stu_cgcj.do?act=stu_cgcj_sq" />

			<div class="tab_cur">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>ѧ����Ϣ - ��˽�������� - ��д�����</a>
				</p>
			</div>	
			<logic:notEmpty name="rs">
				<logic:equal name="rs" property="stuExists" value="no">
					<script>
    					alert("�������ѧ����Ч!");
    				</script>
				</logic:equal>
				<logic:equal name="dataSaved" value="ok" scope="request">
					<script>
    					alert("����ɹ���");
    				</script>
				</logic:equal>
			</logic:notEmpty>
			<div class="tab">
				<table width="100%" id="rsT" class="formlist">
					<thead>
						<tr style="height:22px">
							<th colspan="4">
								<span>ѧ���Էѳ�����ѧ�����Σ�����</b></span>
								<input type="hidden" id="sqrq" name="sqrq" value="${rs.sqrq}"/>
							</th>
						</tr>
					</thead>	
					<tbody>					
					<tr>
						<logic:equal name="userOnLine" value="teacher" scope="session">
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<logic:equal value="view" name="doType">
									<html:text name='rs' property="xh" styleId="xh" readonly="true"/>
								</logic:equal>
								<logic:notEqual value="view" name="doType">
									<html:text name='rs' property="xh" styleId="xh"
									onkeypress="if(event.keyCode == 13) return false;" />
									<button onclick="showTopWin('/xgxt/stu_info.do',750,550);"
										class="btn_01" id="buttonFindStu">
										ѡ��
									</button>
								</logic:notEqual>								
							</td>
						</logic:equal>
						<logic:equal name="userOnLine" value="student" scope="session">
							<th><span class="red">*</span>ѧ��</th>
							<td>
								<input type="text" id="xh" name="xh" value="<bean:write name='rs' property="xh" />" readonly="readonly" />
							</td>
						</logic:equal>
						<th>������ò</th>
						<td>
							<html:select name="rs" property="zzmm" styleId="zzmm" style="width:150px">
								<html:option value=""></html:option>
								<html:options collection="zzmmList" property="zzmmdm"
									labelProperty="zzmmmc" />
							</html:select>
						</td>
					</tr>
					<tr>
						<th>����</th>
						<td>
							<bean:write name='rs' property="xm" />
						</td>
						<th>��ͥסַ</th>
						<td>
							<html:text name="rs" property="jtzz" styleId="jtdz"></html:text>
						</td>
					</tr>
					<tr>
						<th>�Ա�</th>
						<td>
							<bean:write name='rs' property="xb" />
						</td>
						<th><span class="red">*</span>��ϵ�绰</th>
						<td>
							<html:text name="rs" property="lxdh" styleId="lxdh"/>
						</td>
					</tr>
					<tr>
						<th>�꼶</th>
						<td>
							<bean:write name='rs' property="nj" />
						</td>
						<th><span class="red">*</span>ȥ�ι���(ѧУ)</th>
						<td>
							<html:text name="rs" property="gj" styleId="gj"/>
						</td>
					</tr>
					<tr>
						<th><bean:message key="lable.xsgzyxpzxy" /></th>
						<td>
							<bean:write name='rs' property="xymc"/>
						</td>
						<th rowspan="3">����ԭ��</th>
						<td rowspan="3">
							<html:textarea name="rs" property="cgyy" styleId="cgyy" rows="4" cols="20"/>
						</td>					
					</tr>
					<tr>
						<th>רҵ</th>
						<td>
							<bean:write name='rs' property="zymc" />
						</td>	
					</tr>
					<tr>
						<th>�༶</td>
						<td>
							<bean:write name='rs' property="bjmc" />
						</td>
					</tr>
					<tr>
						<th><span class="red">*</span>���õ���������ṩ���õ����˹�ϵ��</th>
						<td colspan="3">
							<html:textarea name="rs" property="jjdbqk" rows="4" cols="60" styleId="jjdbqk"/>
						</td>
					</tr>		
				</tbody>
			    <tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <button class="button2"
							onclick="commSave('/xgxt/stu_cgcj.do?act=stu_cgcj_sq&doType=save','xh-lxdh-gj-jjdbqk')">
							�� �� �� ��
						</button>
						<button class="button2" onclick="printChg('business.do?method=printChgreporter')">
							�� ӡ �� ��
						</button>
			          </div>
			        </td>
			      </tr>
			    </tfoot>				
			</table>
			</div>

			<logic:notEmpty name="result">
			<logic:equal value="true" name="result">
			<script>
				alert("����ɹ���");
				Close();
			</script>
			</logic:equal>
			<logic:equal value="false" name="result">
			<script>
				alert("����ʧ�ܣ�");
				Close();
			</script>
			</logic:equal>
			</logic:notEmpty>
		</html:form>
	</body>
</html>
