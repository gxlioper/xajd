<%@ page language="java" contentType="text/html; charset=GBK"%>
				<div id="wpImg" class="open_img" style="margin-left:0px;margin-top: 0px;height: 130px">
					<img style="width:160px;height:144px" id="axwptp"
						src="axcsWpsz.do?method=showPhoto&xmdm=${xmdm}&type=add"
						border="0">
				</div>
				<div id ="zpscbtn" align="center">
					<button  type="button"  onclick="showZpZjDiv();" >
					上传照片
					</button>
				</div>
				
				
			<!-- 上传照片 -->
			<div id="addPic" style="display:none">
				<div class="open_win01">
					<table align="center" class="formlist">
						<thead>
							<tr>
								<th colspan="2">
									<span>上传照片</span>
								</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="file" id="xmtp" name="xmtp" style="width:90%"
										   onchange='uploadWpPic();closeWindown();'
									/>
									<br/>
									<span style="color:red">注：请上传jpg，gif，png，bmp 格式的文件，限 1 M 。</span>
								</td>
							</tr>
						</tbody>
				</table>
				</div>
			</div>	