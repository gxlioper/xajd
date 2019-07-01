<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/syscommon/pagehead_V4.ini"%>
<!-- ͷ�ļ� -->
<script language="javascript" src="/xgxt/pjpy/zjjd/zjjdjs/zjjdjs.js"></script>
<script type="text/javascript" src="js/rcsw/rcswFunction.js"></script>
<script type='text/javascript' src='/xgxt/dwr/interface/getDtjsInfo.js'></script>
<script type='text/javascript' src='/xgxt/dwr/interface/pjpyService.js'></script>
<script type="text/javascript" src="js/check.js"></script>
<script type="text/javascript" src="js/pjpy/typj.js"></script>
<script type="text/javascript">
	function tjgl(xxdm){
		var xh = $('xh').value;
		var xn = $('xn').value;
		var jxjdm = $('jxjdm').value;
		var userType = $('userType').value;
		
		if (''==xh || ''==xn || ''==jxjdm){
			alert('��*���Ϊ�գ�');
			return false;
		}
		
		if ('10657' == xxdm) {
			pjpyService.pjpyTjsz({xh:xh,xn:xn,lx:'jxj',jxjdm:jxjdm},function(data){
				if ('false' == data.result && userType!="admin" && userType!="xx"){
					alert(data.message);
				} else {
					saveUpdate('/xgxt/typj.do?method=jxjsq&doType=save','save_xh-save_xn-save_jxjdm');
				}
			}); 
		} else if ('12645'==xxdm) {
			pjpyService.nbcxPjpyTjsz({xh:xh,xn:xn,lx:'jxj',jxjdm:jxjdm},function(data){
				if ('false' == data.result){
					alert(data.message);
				} else {
					saveUpdate('/xgxt/typj.do?method=jxjsq&doType=save','save_xh-save_xn-save_jxjdm');
				}
			}); 
		}
	}
	
	function printSqb() {
		var text = DWRUtil.getText('jxjdm');
		var pkValue = $('pkValue').value;
		var url = '/xgxt/typj.do?method=jxjPrint&jxjmc='+text+'&pkValue='+pkValue;
					
		window.open(url);
	}
</script>
</head>
<body onload="setSqsj();display();">
	<html:form action="/typj" method="post">
		<input type="hidden" id="url" name="url" value="/typj.do?method=jxjsq" />
		<input type="hidden" id="getStuInfo" name="getStuInfo"
			value="xm-xb-zymc-xymc-bjmc-mzmc-zzmmmc-csrq-lxdh" />
		<input type="hidden" name="message" id="message" value="${message }" />
		<input type="hidden" name="yhInfo" id="yhInfo" value="${yhInfo }" />
		<input type="hidden" name="save_nd" value="${sqsjInfo.nd}"/>
		<input type="hidden" name="save_xq" value="${sqsjInfo.xq}"/>
		<input type="hidden" name="save_sqsj" value="${sqsjInfo.sqsj}" id="sqsj"/>
		<input type="hidden" id="xxdm" value="${xxdm }"/>
		<input type="hidden" name="now" value="${nowTime }" id="now"/>
		<input type="hidden" name="pkValue" value="${pkValue }" id="pkValue"/>
		<input type="hidden" name="userType" value="${userType }" id="userType"/>

		<logic:equal value="10657" name="xxdm" scope="session">
			<logic:notEqual value="stu" name="userType" scope="session">
				<logic:notEqual value="xy" name="userType" scope="session">
					<input type="hidden" name="save_shzt" value="ͨ��" />
					<input type="hidden" name="save_fdysh" value="ͨ��" />
					<input type="hidden" name="save_xysh" value="ͨ��" />
					<input type="hidden" name="save_xxsh" value="ͨ��" />
				</logic:notEqual>
			</logic:notEqual>
		</logic:equal>	
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ��:</em><a>${title }</a>
			</p>
		</div>
		<div class="tab">
			<table class="formlist" width="100%">
				<thead>
					<tr>
						<th colspan="4">
						<span>������Ϣ
						<logic:equal value="12645" name="xxdm" scope="session">
							<font color="red">���������������ɾ���չ��ѧ���⣬ÿ��ÿѧ��ֻ������һ�ѧ��</font>
						</logic:equal>
						</span>
						</th>
					</tr>
				</thead>
				<tbody>
				<tr>
					<th width="24%"><font color="red">*</font>ѧ��</th>
					<td width="26%">
						<logic:equal value="stu" name="userType">
						<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
							onkeypress="autoFillStuInfo(event.keyCode,this)"
							onblur="chkIsStu(this,'view_xsjbxx','xh')"  readonly="true"
						/>
						</logic:equal>
						<logic:notEqual value="stu" name="userType">
						<html:text property="save_xh" styleId="xh" value="${rs.xh}" 
							onkeypress="autoFillStuInfo(event.keyCode,this)"
							onblur="chkIsStu(this,'view_xsjbxx','xh')" 
						/>
						
							<button onclick="showTopWin('/xgxt/stu_info.do',800,600);"
								class="btn_01" id="buttonFindStu">
								ѡ��
							</button>
						</logic:notEqual>
						
					</td>
					<th width="24%">����</th>
					<td width="26%">
						${rs.xm }
					</td>
				</tr>
				<tr>
					<th>�Ա�</th>
					<td>${rs.xb }</td>
					<th><bean:message key="lable.xsgzyxpzxy" /></th>
					<td>
						${rs.xymc }
					</td>
				</tr>
				<tr>
					<th>רҵ</th>
					<td>
						${rs.zymc }
					</td>
					<th>�༶</th>
					<td>
						${rs.bjmc }
					</td>
				</tr>
				<tr>
					<th>����</th>
					<td>${rs.mzmc }</td>
					<th>������ò</th>
					<td>${rs.zzmmmc }</td>
				</tr>
				<tr>
					<th>��������</th>
					<td>${rs.csrq }</td>
					<th>��ϵ�绰</th>
					<td>${rs.lxdh }</td>
				</tr>
				<tr>
					<th><font color="red">*</font>ѧ��</th>
					<td>
						<html:text property="save_xn" readonly="true" value="${sqsjInfo.xn }" styleId="xn"></html:text>
					</td>
					<th><font color="red">*</font>��ѧ������</th>
					<td>
						<html:select property="save_jxjdm" styleId="jxjdm" onchange="setSqsj();">
							<html:options collection="jxjList" property="dm"
								labelProperty="mc" />
						</html:select>
					</td>
				</tr>
				</tbody>
			</table>
		
			<logic:notEqual value="11355" name="xxdm" scope="session">
				<table width="99%" id="rsTable" class="formlist">
					<logic:empty  name="zcpm">
						<thead>
						<tr><th  colspan="6"><span>�ۺ����ʲ���<font color="blue">(���ݲ�������ģ�������õ��������ڻ�ȡ�۲�����)</font></span></th></tr>						
						</thead>
						<tbody>
							<tr><td  colspan="6"><span>û�м�¼��</span></td></tr>
						</tbody>
					</logic:empty>

					<logic:notEmpty name="zcpm">
					<thead>
						<tr><th  colspan="6"><span>�ۺ����ʲ���<font color="blue">(���ݲ�������ģ�������õ��������ڻ�ȡ�۲�����)</font></span></th></tr>
						<tr>
							<td align="center">ѧ��</td>
							<td align="center">ѧ��</td>
							<td align="center">���</td>
							<td align="center">����</td>
							<td align="center">����</td>
							<td align="center">����</td>
						</tr>
					</thead>
					<tbody>
						<logic:iterate id="v" name="zcpm" offset="0" scope="request">
							<tr align="center" style="cursor:hand">
								<td>
									${v.xn }
								</td>
								<td>
									${v.xqmc }
								</td>
								<td>
									${v.nd }
								</td>
								<td>
									${v.mc }
								</td>
								<td>
									${v.fs }
								</td>
								<td>
									${v.pm }
								</td>
							</tr>
						</logic:iterate>
					</tbody>
				
				<logic:equal name="xxdm" value="10657">
				<table width="99%" id="rsTable" class="formlist">
					<tbody>
					<tr><th>�ǳɼ��ܼ���</th><td>${rs.zyjd }</td><th>�ܷ���������<font color="red">(%)</font></th><td>${rs.zpmbl }</td></tr>
					<tr><th>�ǳɼ�����</td><td>${rs.zypm }</td><th>�ǳɼ���������<font color="red">(%)</font></th><td>${rs.zypmbl }</td></tr>
					</tbody>
				</table>
				</logic:equal>
				</logic:notEmpty>
				</table>
			</logic:notEqual>
			<!-- ���ݴ�ѧ -->
			<logic:equal value="10657" name="xxdm" scope="session">
				<jsp:include flush="true" page="/pjpy/typj/guizhdx_jxjsq.jsp"></jsp:include>
			</logic:equal>
			<!-- ����ְҵ -->
			<logic:equal value="11355" name="xxdm" scope="session">
				<jsp:include flush="true" page="/pjpy/typj/nnzy_jxjsq.jsp"></jsp:include>
			</logic:equal>
			<!-- �������� -->
			<logic:equal value="12645" name="xxdm" scope="session">
				<jsp:include flush="true" page="/pjpy/typj/nbcs_jxjsq.jsp"></jsp:include>
			</logic:equal>
			<table class="formlist" width="100%">
				<tfoot>
			      <tr>
			        <td colspan="4"><div class="bz">"<span class="red">*</span>"Ϊ������</div>
			          <div class="btn">
			            <logic:equal value="10657" name="xxdm" scope="session">
							<button class="" style="" id="buttonSave" onclick="tjgl('${xxdm}');">
								��&nbsp;&nbsp;��
							</button>
						</logic:equal>
						<logic:equal value="12645" name="xxdm" scope="session">
							<button class="" style="" id="buttonSave"  onclick="tjgl('${xxdm}');">
								��&nbsp;&nbsp;��
							</button>
						</logic:equal>
						<logic:notEqual value="12645" name="xxdm" scope="session">
						<logic:notEqual value="10657" name="xxdm" scope="session">
							<button class="" style="" id="buttonSave"
								 onclick="saveUpdate('/xgxt/typj.do?method=jxjsq&doType=save','save_xh-save_xn-save_jxjdm');">
								��&nbsp;&nbsp;��
							</button>
						</logic:notEqual>
						</logic:notEqual>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<logic:equal value="10657" name="xxdm" scope="session">
							<button class="" onclick="printSqb();" >
									��ӡ����
							</button>
						</logic:equal>
						
						<logic:equal value="gb" name="lx">
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="button2" onclick="Close();return false;" >
									��&nbsp;&nbsp;��
							</button>
						</logic:equal>
			          </div>
			        </td>
			      </tr>
			    </tfoot>
			</table>
	</html:form>
	<logic:present name="yhInfo">
	
		<script>
				alert('��ʾ��Ϣ: '+$('yhInfo').value != null ? $('yhInfo').value : "����ʧ��!");
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
	<logic:present name="message">
		<script>
				alert("${message}");
				if (window.dialogArguments) {
					window.close();
					window.dialogArguments.document.getElementById('search_go').click();
				}
			</script>
	</logic:present>
</body>
</html>