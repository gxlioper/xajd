<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/pagehead_V4.ini"%>
		<script language="javascript" src="js/function.js"></script>
		<script type='text/javascript' src='/xgxt/dwr/interface/getSztzData.js'></script>
		<script type='text/javascript' src='/xgxt/dwr/engine.js'></script>
		<script type='text/javascript' src='dwr/util.js'></script>		
	</head>
	<body>
		<!-- ���� -->
		<div class="tab_cur">
			<p class="location">
				<em>���ĵ�ǰλ�ã�</em><a>��Ԣ���� - ԰������ - ԰��������</a>
			</p>
		</div>
		<!-- ���� end-->
		<html:form action="/zgdzdx_Gygl" method="post">
			 <table class="formlist" border="0" align="center" style="width: 100%">
				<thead>
					<tr>
						<th colspan="4">
							<span>԰����������Ϣ</span>
						</th>
					</tr>
				</thead>
				<tbody>		
					<tr>
						<th height="22" align="right">
							<font color="red">*</font>԰����
						</th>
						<td height="22" align="left">
							<html:select  property="yqdm"  style="width:120px" styleId="yqdm" >
								<html:options collection="yqList" property="dm"
									labelProperty="mc" />
							</html:select>
						</td>
						<th height="22" align="right">
							<font color="red">*</font>�����ˣ�
						</th>
						<td height="22" align="left">
							<html:text property="xm"></html:text>
						</td>
					</tr>					
					<tr>
						<th height="22" align="right">
							��ϵ�绰��
						</th>
						<td height="22" align="left">
							<html:text property="lxdh"></html:text>
						</td>
						<th height="22" align="right">
							<font color="red">*</font>��ְ���ڣ�
						</th>
						<td height="22" align="left">
						<html:text  property="rzrq" styleId="rzrq"
							onblur="dateFormatChg(this)" style="cursor:hand;"
							onclick="return showCalendar('rzrq','y-mm-dd');" readonly="true" />
					    </td>
					</tr>		
					<tr>
						<th height="22" align="right">
							�������䣺
						</th>
						<td height="22" align="left">
							<html:text property="dzyx"></html:text>
						</td>
						<th height="22" align="right">
							
						</th>
						<td height="22" align="left">
							
						</td>
					</tr>						
					<tr align="left">
							<th align="right">
								��ע��
							</th>
							<td colspan="3">
								<html:textarea  property='bz' style="width:99%"
									rows='5' />
							</td>
						</tr>
					</tbody>
					<tfoot>
						<tr bgcolor="EEF4F9" align="center">
							<td colspan="4">
								<div class="btn">
									<logic:notEqual name="doType" value="view">
										<button id="buttonSave" 
											onclick="yqMAddSave('yqdm-xm-rzrq')"
											style="width: 80px">
											��	��
										</button>
									</logic:notEqual>
									&nbsp;&nbsp;
									<button id="buttonClose" onclick="Close();return false;"
										style="width: 80px">
										��	��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>				
		</html:form>
		<logic:equal value="true" name="done">
			<script type="text/javascript">
				alert("�����ɹ���");
				Close();
				dialogArgumentsQueryChick();
			</script>
		</logic:equal>
		<logic:equal value="false" name="done">
			<script type="text/javascript">
				alert("����ʧ��,��ϵͳ���Ѵ������\"*\"����Ŀ��ͬ�ļ�¼��������������ݺ����ύ��");
			</script>
		</logic:equal>
	</body>
	<script type="text/javascript">
	     function yqMAddSave(mustFill){
	           var eles = mustFill.split("-");
	           for (i = 0; i < eles.length; i++) {
		           if (document.getElementById(eles[i]).value == "") {
			       alert("�뽫��\"*\"�ŵ���Ŀ����������");
			       return false;
		           }		
	           }
	           var pkValue=$("yqdm").value;
	           getSztzData.getInfoEx("yqfzrb","yqdm",pkValue,"sfzz='��'",function(str){
		         if(str){		         	
		            if(confirm("��԰��������ְ�����ˣ��Ƿ�Ҫ�ύ��")){
		               refreshForm("/xgxt/zgdzdx_Gygl.do?method=yqManagerAdd&doType=Save");
		            }else{
		               return false;
		            }		          			        
		         }else{
                    refreshForm("/xgxt/zgdzdx_Gygl.do?method=yqManagerAdd&doType=Save");
		         }
    	       });	 	           
	     }
	</script>
</html>
