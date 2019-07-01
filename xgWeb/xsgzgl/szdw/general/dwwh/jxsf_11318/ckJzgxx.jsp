<%@ page language="java" contentType="text/html; charset=GBK"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/syscommon/head.ini"%>
		<script type="text/javascript" src="js/check.js"></script>
		<script type="text/javascript" src="js/calendar/calendar.js"></script>
		<script type="text/javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/szdw/dwwh.js"></script>
		<script language="javascript" src="js/comm/commFunction.js"></script>
		<script language="javascript" src="js/xsgzgl/xtwh/commUtil.js"></script>
		<script language="javascript" src="js/uicomm.js"></script>
		<script type="text/javascript"
			src="js/jquery/plugins/upload/ajaxfileupload.js"></script>

		<script language="javascript">
	/**
	 * ���������
	 */
	function showAlertDivLayer() {
		var argumentsArr = Array.prototype.slice.call(arguments);
		if (argumentsArr[0] == null)
			return;

		var clickFun = null;

		if (argumentsArr.length == 3) {
			clickFun = argumentsArr[2]["clkFun"];
		}
		ymPrompt.alert( {
			title : "ϵͳ��ʾ",
			useSlide : true,
			maskAlphaColor : "#FFFFFF",
			maskAlpha : 0.3,
			message : argumentsArr[0],
			width : 340,
			winPos : [ 180, 160 ],
			height : 160,
			//showMask:false,
			handler : clickFun
		});
		//setTimeout(function(){ymPrompt.doHandler();},3000);
	}
	jQuery(function() {

		//jQuery("img").attr('src',"/xgxt/teaPic.jsp?zgh=test");
		jQuery("#zhaopian").attr('src','<%=request.getContextPath()%>/teaPic.jsp?zgh=${map.zgh}&t='+new Date());
	});
</script>
	</head>
	<body>

		<html:form action="/data_search" method="post" styleId="dwwh_form">
			<%--<div class="tab_cur" id="jd">
				<p class="location">
					<em>���ĵ�ǰλ��:</em><a>${title}</a>
				</p>
			</div>--%>

			<div class="tab"
				style="width: 100%; height: 100%; overflow-x: hidden; overflow-y: auto;">
				<table width="100%" border="0" class="formlist">
					<thead>
						<tr>
							<th colspan="5">
								<span>��������</span>
							</th>
						</tr>
					</thead>

					<tbody>
						<tr>
							<th align="right">
								����
							</th>
							<td align="left" style="width: 25%">
								${map.zgh }
							</td>
							<th align="right">
								����
							</th>
							<td align="left" style="width: 25%">
								${map.xm }
							</td>
							<th align="left" rowspan="5">
								<div align="center">
									<img
										src=""
										style="height: 133px; width: 100px;" border="0" id="zhaopian" />
								</div>
							</th>
						</tr>
						<tr>
							<th align="right" width="15%">
								���ڲ���
							</th>
							<td align="left">
								${map.bmmc }
							</td>

							<th align="right">
								�Ա�
							</th>
							<td align="left">
								${map.xbmc }
							</td>
						</tr>
						<tr>
							<th align="right">
								���֤��
							</th>
							<td align="left">
								${map.sfzh }
							</td>
							<th align="right">
								��������
							</th>
							<td align="left">
								${map.csrq }
							</td>
						</tr>
						<tr>
							<th align="right">
								������ò
							</th>
							<td align="left">
								${map.zzmmmc }
							</td>
							<th align="right">
								��������
							</th>
							<td align="left">
								${map.bzlxmc }
							</td>
						</tr>
						<tr>
							<th align="right">
								ְ��
							</th>
							<td align="left">
								${map.zcmc }
							</td>
							<th align="right">
								��Ƹʱ��
							</th>
							<td align="left">
								${map.kzzd6 }
							</td>
						</tr>
						<tr>
							<th align="right">
								ְ��
							</th>
							<td align="left">
								${map.zwmc }
							</td>
							<th align="right">
								��ְʱ��
							</th>
							<td align="left" colspan="2">
								${map.kzzd7 }
							</td>
						</tr>
						<tr>
							<th align="right">
								��ְͬ��ʱ��
							</th>
							<td align="left">
								${map.kzzd8 }
							</td>
							<th align="right">
								�������
							</th>
							<td align="left" colspan="2">
								${map.kzzd16 }
							</td>
						</tr>
						<tr>
							<th align="right">
								��������
							</th>
							<td align="left">
								${map.sfjtlxmc }
							</td>
							<th align="right">
								����ʱ��
							</th>
							<td align="left" colspan="2">
								${map.kzzd12 }
							</td>
						</tr>
						<tr>
							<th align="right">
								��λ���
							</th>
							<td align="left">
								${map.dwlbdm }
							</td>
							<th align="right">
								����
							</th>
							<td align="left" colspan="2">
								${map.mzmc }
							</td>
						</tr>
						<tr>
							<th align="right">
								���ѧ��
							</th>
							<td align="left">
								${map.xl }
							</td>
							<th align="right">
								���ѧ��������
							</th>
							<td align="left" colspan="2">
								${map.kzzd20}
							</td>
						</tr>
						<tr>
							<th align="right">
								���ѧλ
							</th>
							<td align="left">
								${map.xw }
							</td>
							<th align="right">
								���ѧλ������
							</th>
							<td align="left" colspan="2">
								${map.kzzd13 }
							</td>
						</tr>
						<tr>
							<th align="right">
								��ҵԺУ
							</th>
							<td align="left">
								${map.byyx }
							</td>
							<th align="right">
								��ѧרҵ
							</th>
							<td align="left" colspan="2">
								${map.sxzy }
							</td>
						</tr>
						<tr>
							<th align="right">
								����
							</th>
							<td align="left">
								${map.jgxsmc }
							</td>
							<th align="right">
								��У����ʱ��
							</th>
							<td align="left" colspan="2">
								${map.lxgzsj }
							</td>
						</tr>
						<tr>
							<th align="right">
								�о�����
							</th>
							<td align="left">
								${map.xsgzyjfx }
							</td>
							<th aligh="right">
								��λ���
							</th>
							<td align="left" colspan="2">
								${map.gwlbmc }
							</td>
						</tr>
						<tr>
							<th align="right">
								��ͥסַ
							</th>
							<td align="left" colspan="4">
								${map.jtzz }
							</td>
						</tr>
						<tr>
							<th align="right">
								��Ҫְ��
							</th>
							<td align="left" colspan="4">
								<html:textarea property="zyzz" name="map" styleId="zyzz"
									rows="3" cols="80" readonly="true"></html:textarea>

							</td>
						</tr>
						<tr>
							<th align="right">
								�����
							</th>
							<td align="left" colspan="4">
								<html:textarea property="grhjqk" name="map" styleId="grhjqk"
									rows="3" cols="80" readonly="true"></html:textarea>

							</td>
						</tr>
					<thead>
						<tr>
							<th colspan="5">
								<span>��ϵ��ʽ</span>
							</th>
						</tr>
					</thead>
					<tr>
						
						<th align="right">
							�ƶ��绰
						</th>
						<td align="left" >
							${map.yddh }
						</td>
						<th align="right">
							�칫�绰
						</th>
						<td align="left" colspan="2">
							${map.bgdh }
						</td>
					</tr>
					<tr>
						<th align="right">
							��������
						</th>
						<td align="left">
							${map.dzyx }
						</td>
						<th align="right">
							QQ
						</th>
						<td align="left" colspan="2">
							${map.kzzd3 }
						</td>
					</tr>
					<tr>
						<th align="right">
							��ϵ�绰
						</th>
						<td align="left">
							${map.lxdh }
						</td>
						<th align="right">
							����
						</th>
						<td align="left" colspan="2">
							${map.cz }
						</td>

					</tr>
					<tr>
						<th align="right">
							΢����
						</th>
						<td align="left">
							${map.kzzd1 }
						</td>
						<th align="right">
							΢����
						</th>
						<td align="left" colspan="4">
							${map.kzzd2 }
						</td>
					</tr>
					<tr>
						<th align="right">
							�칫�ص�
						</th>
						<td align="left" colspan="4">
							${map.bgdd }
						</td>
					</tr>
					<tr>

						<th align="right">
							ͨѶ��ַ
						</th>
						<td align="left" colspan="4">
							${map.txdz }
						</td>
					</tr>

					<thead>
						<tr>
							<th colspan="5">
								<span>��������</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th align="right">
							�μӹ���ʱ��
						</th>
						<td align="left">
							${map.cjgzrq }
						</td>
						<th align="right">
							�μ�ѧ������ʱ��
						</th>
						<td align="left" colspan="2">
							${map.szgzsj }
						</td>
					</tr>
					<tr>
						<th align="right">
							��������
						</th>
						<td align="left" colspan="4">
							<html:textarea readonly="true" property="gzjl" name="map"
								styleId="gzjl" rows="3" cols="80"></html:textarea>
						</td>
					</tr>
					<thead>
						<tr>
							<th colspan="5">
								<span>����</span>
							</th>
						</tr>
					</thead>
					<tr>
						<th align="right">
							��������
						</th>
						<td align="left" colspan="4">
							<html:textarea readonly="true" property="kzzd4" name="map"
								styleId="kzzd4" rows="3" cols="80"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							��������
						</th>
						<td align="left" colspan="4">
							<html:textarea readonly="true" property="fblw" name="map"
								styleId="fblw" rows="3" cols="80"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							�μ���ѵ
						</th>
						<td align="left" colspan="4">
							<html:textarea readonly="true" property="pxqk" name="map"
								styleId="pxqk" rows="3" cols="80"></html:textarea>
						</td>
					</tr>
					
					<tr>
						<th align="right">
							��ע
						</th>
						<td align="left" colspan="4">
							<html:textarea readonly="true" property="bz" name="map"
								styleId="bz" rows="3" cols="80"></html:textarea>
						</td>
					</tr>
					<tr>
						<th align="right">
							������Ϣ
						</th>
						<td colspan="4">
							<%@ include file="/xsgzgl/comm/fileUpload/f_q.jsp"%>
							<html:hidden name="map" property="kzzd19" styleId="fjid" />
							<script type="text/javascript">
								//���ø��� 
								jQuery(function(){
									var gid = jQuery('#fjid').val();
									jQuery.MultiUploader_q({
										gid : gid
										});
								});
							</script>
						</td>
					</tr>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="bz"></div>
								<div class="btn">
									<button type="button" name="�ر�" onclick="Close(); return false;"id="buttonClose">
										�� ��
									</button>
								</div>
							</td>
						</tr>
					</tfoot>
				</table>
			</div>

			<!-- ���ƽ�ʦ��Ϣ - �ϴ���Ƭ -->
			<!-- �ϴ���Ƭ -->
			<div id="addPic" style="display: none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>�ϴ���Ƭ</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="teaPic" name="teaPic" style="width: 90%"
										onchange='uploadStuPic();' />
									<br />
									<span style="color: red">ע�����ϴ�jpg��gif��png��bmp ��ʽ���ļ����� 1
										M ��</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

		</html:form>
	</body>
</html>
