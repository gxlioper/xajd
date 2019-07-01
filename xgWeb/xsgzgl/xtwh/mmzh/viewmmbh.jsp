<%@ page language="java" contentType="text/html; charset=GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css" media="all"/>
		<link rel="stylesheet" type="text/css" href="css/base.css" media="all"/>
		<link rel="stylesheet" type="text/css" href="css/style.css" media="all"/>
		<script type='text/javascript' src="js/comm/message.js"></script>
		<script language="javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/jquery/jquery.form.js"></script>
	    <script type="text/javascript" src="js/jquery/ajaxSubmit.js"></script>
	    <script type="text/javascript" src="xsgzgl/xtwh/mmzh/js/mmzh.js"></script>
		<script type="text/javascript">
		
		jQuery(function() {
			jQuery("#yhm").val('${yhm}');
			jQuery("#wtid").attr({disabled:"true"});
			jQuery("#wtid").val('${wtid}');
		});
		
		
	</script>
	
	<style>
	.resetCss tr{
		border-collapse: collapse;
		border-spacing: 0;
		font:inherit;
		list-style: none;
	}
	
	.resetCss tr td{
		border: 0;
		padding: 0;
		vertical-align: 0px;
		
	}
	.tab .top-title {
		width: 490px;
		margin: 0 auto;
		height: 120px;
		overflow: hidden;
	}
	.tab .top-title li {
		width: 140px;
		float: left;
		min-height: 100px;
		position: relative;
	}
	</style>
	</head>
	<body class="student-worker-page">
		<!-- ������ end-->
		<div class="mainbody type_mainbody">	
			<div class="topframe">
				<!-- TOP -->
				<div class="head">	
				
					<!-- ѧУLOGO -->
					<%@ include file="/homepage/info/logo.jsp"%>
		            <!-- ѧУLOGO end-->
		            
				</div>
				<!-- TOP END-->
				<!--
				
				<div class="menu">
					<div class="nav">
						<ul class="ul_find"> 
							<li>
								<a href="javascript:void(0);"
									id="li_page">�����һ�</a>
							</li>

						</ul>
					</div>
				</div>
				
				-->
				<div class="mainframe" style="width:100%; text-align: left;" id="mainBody" >
				<div class="prompt">
				<h3>
					<span>��ʾ��</span>
				</h3>
				<p>
					<font color="red">
						��ѡ���ܱ����⣬�����ܱ��𰸣������һ�������޸����룬��֤�ɹ����ܽ�����һ����
					</font>
				</p>
				<a class="close" title="����" onclick="this.parentNode.style.display='none';"></a>
				</div>
				<html:form action="/mmzhgl_mmzh" method="post" styleId="MmZhForm" onsubmit="return false;">
					<div class="tab">
						<ul class="top-title">
									<li class="infocus ico1">
										<a href="#"><span>�û�����֤</span></a>	
									</li>
							<li>
								<a href="#"><span>�һط�ʽ</span></a>
							</li>
									<li class="infocus ico2">
										<a href="#"><span>�ܱ���֤</span></a>
									</li>
									<li class="ico3">
										<a href="#"><span>�޸�����</span></a>
									</li>
							</ul>
							<div class="tab-con">
								<div class="tab-list">
									<label><span class="red">*</span>�ܱ�����</label>
									<div class="tab-text">
										<html:select   property="wtid" styleId="wtid" style="width:154px;">
							                <html:options collection="mbList" property="wtid" labelProperty="mbwt" />
						                </html:select>	
										<input type="hidden" id = "yhm" name ="yhm" />
									</div>
								</div>
								<div class="tab-list">
									<label><span class="red">*</span>�ܱ���</label>
									<div class="tab-text">
										<html:text   property="wtda" styleId="wtda" style="width:154px"/>
									</div>
								</div>
								<div class="tab-list">
									<label><span class="red">*</span>��֤��</label>
									<div class="tab-text">
										<input name="yzm" type="text" id="yzm_t" class="text_nor" style="width:60px;" maxlength="4" id="yzm"/>
										<img id="yzmImg" src="yzm.jsp?rand=<%=System.currentTimeMillis() %>" border="0" align="absmiddle" width="70px" height="19px"/>	
									</div>
								</div>
								<div class="tab-list">
									<!--<label><span class="red">*</span>Ϊ������</label>-->
									<button type="button" class="" onclick="checkwtda();">��һ��</button>
								</div>
							</div>
							
							<!--
							<table width="100%" border="0" class="formlist">
							<thead>
								<tr>
									<th colspan="2">
										<span>�ܱ���֤</span>
									</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<th align="right">
										<span class="red">*</span>�ܱ�����
									</th>
									<td>
										<html:select   property="wtid" styleId="wtid" style="width:154px;">
						                   <html:options collection="mbList" property="wtid" labelProperty="mbwt" />
					                    </html:select>	
									    <input type="hidden" id = "yhm" name ="yhm" />
									</td>
								</tr>
								<tr>
									<th align="right">
										<span class="red">*</span>�ܱ���
									</th>
									<td>
										<html:text   property="wtda" styleId="wtda" style="width:154px"/>
									   <input type="text" id="wtda"  style="width:154px" name="wtda">   
									</td>
								</tr>
								<tr>
									<th align="right">
										��֤�룺
									</th>
									<td>
									<input name="yzm" type="text" id="yzm_t" class="text_nor" 
										style="width:45px;" maxlength="4" id="yzm"/>
									<img id="yzmImg" src="yzm.jsp?rand=<%=System.currentTimeMillis() %>" border="0" align="absmiddle" width="70px"
										height="19px"/> 
									</td>
								</tr>
								<tr>
									<td colspan="2">
										&nbsp;
									</td>
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<td colspan="2">
										<div class="bz">
											"
											<span class="red">*</span>"Ϊ������
										</div>
										<div class="btn">
											<button type="button" class=""
												onclick="checkwtda();">
												��һ��
											</button>
										</div>
									</td>

								</tr>
							</tfoot>
						</table>
					-->
					</div>
					
				</html:form>
			</div>
			
				<!-- MAIN END -->
				<!-- BOTTOM-->
				<%@ include file="/homepage/info/companyInfo.jsp"%>
				<!-- BOTTOM end-->
			</div>
		</div>
		
	</body>
</html>
