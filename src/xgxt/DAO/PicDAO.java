package xgxt.DAO;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import xgxt.DAO.DBPool;

public class PicDAO {

	private Connection conn = null;

	private PreparedStatement stmt = null;

	// private PreparedStatement pstmt = null;

	private ResultSet rs = null;

	private DataSource db = null;

	public PicDAO() {
		db = DBPool.getPool();
	}

	public void closeAllStmtAndRs(ResultSet rs, PreparedStatement pstmt,
			Statement stat, CallableStatement cstmt) {
		// 通用模块，关闭所有事务
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {// PreparedStatement
				pstmt.close();
			}
			if (stat != null) {
				stat.close();
			}
			if (cstmt != null) {
				cstmt.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection(Connection conn) {
		try {
			if (conn != null && !conn.isClosed()) {
				conn.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// --------保存图片到数据库--------------
	public void savePic(InputStream is, String id, String type) {
		String insertsql;
		String sql;
		if (type.equalsIgnoreCase("stu")) {
			sql = "select count(1) num from xszpb where xh=?";
		} else {
			sql = "select count(1) num from fdy_pic where zgh=?";
		}
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DAO dao = DAO.getInstance();
		String num=dao.getOneRs(sql, new String[]{id}, "num");
		if("0".equals(num)){//若没有照片则插入
			if(type.equalsIgnoreCase("stu")){
				sql="insert into xszpb(xh,zp) values(?,empty_blob())";
			}else{
				sql="insert into fdy_pic(zgh,pic) values(?,empty_blob())";
			}
		}else{
			if(type.equalsIgnoreCase("stu")){
				sql="update xszpb set zp=empty_blob() where xh=?";
			}else{
				sql="update fdy_pic set pic=empty_blob() where zgh=?";
			}
		}
			try {
				conn = db.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				int rt = stmt.executeUpdate();
				if (rt > 0) {
					String preCursor;
					if (type.equalsIgnoreCase("stu")) {
						preCursor = "select xh,zp pic from xszpb where xh=? for update";
					} else {
						preCursor = "select zgh,pic from fdy_pic where zgh=? for update";
					}
					stmt = conn.prepareStatement(preCursor);
					stmt.setString(1, id);
					rs = stmt.executeQuery();
					rs.next();
					Blob b = (Blob) rs.getBlob("pic");
					BufferedOutputStream bos = new BufferedOutputStream(b
							.setBinaryStream(0L));
					BufferedInputStream bis = new BufferedInputStream(is);
					int bytes;
					try {
						while ((bytes = bis.read()) != -1) {
							bos.write(bytes);
						}

						bis.close();
						bos.close();
						conn.commit();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	
	
	// --------保存图片到数据库 专门用于保存学生的照片--------------
	public void savePic_stu(InputStream is, String id) {
		String sql="select count(1) num from xszpb where xh=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DAO dao = DAO.getInstance();
		String num=dao.getOneRs(sql, new String[]{id}, "num");
		boolean del = true;
		

		if("0".equals(num)){//若没有照片则插入
			sql="insert into xszpb(xh,xszp) values(?,empty_blob())";
		}else{
			sql="update xszpb set xszp=empty_blob() where xh=?";
		}
//		if (del) {
			try {
				conn = db.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				int rt = stmt.executeUpdate();
				if (rt > 0) {
					System.out.println("initialization succeed!!");
					String preCursor;
					preCursor = "select xh,zp pic from xszpb where xh=? for update";
					stmt = conn.prepareStatement(preCursor);
					stmt.setString(1, id);
					rs = stmt.executeQuery();
					rs.next();
					Blob b = (Blob) rs.getBlob("pic");
					BufferedOutputStream bos = new BufferedOutputStream(b
							.setBinaryStream(0L));
					BufferedInputStream bis = new BufferedInputStream(is);
					int bytes;
					try {
						while ((bytes = bis.read()) != -1) {
							bos.write(bytes);
						}

						bis.close();
						bos.close();
						conn.commit();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
//		}
	}
	
	// --------保存图片到数据库 专门用于保存学生的高考照片--------------
	public void saveGkPic_stu(InputStream is, String id) {
		String sql="select count(1) num from xszpb where xh=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DAO dao = DAO.getInstance();
		String num=dao.getOneRs(sql, new String[]{id}, "num");
		boolean del = true;
		

		if("0".equals(num)){//若没有照片则插入
			sql="insert into xszpb(xh,xszp) values(?,empty_blob())";
		}else{
			sql="update xszpb set xszp=empty_blob() where xh=?";
		}

			try {
				conn = db.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				int rt = stmt.executeUpdate();
				if (rt > 0) {
					System.out.println("initialization succeed!!");
					String preCursor;
					preCursor = "select xh,xszp pic from xszpb where xh=? for update";
					stmt = conn.prepareStatement(preCursor);
					stmt.setString(1, id);
					rs = stmt.executeQuery();
					rs.next();
					Blob b = (Blob) rs.getBlob("pic");
					BufferedOutputStream bos = new BufferedOutputStream(b
							.setBinaryStream(0L));
					BufferedInputStream bis = new BufferedInputStream(is);
					int bytes;
					try {
						while ((bytes = bis.read()) != -1) {
							bos.write(bytes);
						}

						bis.close();
						bos.close();
						conn.commit();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
	}

	public InputStream getPic(String id, String type) throws IOException {
		try {
			
			String sql;
			// ==============2010.9.8 edit by 伟大的luo=======================
			if (type.equalsIgnoreCase("fdy")) {
				sql = "select pic from fdy_pic where zgh= '" + id + "' ";
			} else if (type.equalsIgnoreCase("wjdc")) {
				sql = "select tbjg from wjdc_jgfxb where wjbh||xh = '" + id + "'";
			} else {
				sql = "select zp,(case when nvl(length(zp),'0')=0 then '未导入' else '已导入' end) zpzt," +
						"xszp,(case when nvl(length(xszp),'0')=0 then '未导入' else '已导入' end) xszpzt from xszpb where xh = '" + id + "'";
			}
			// ==============2010.9.8 edit end=======================
			
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			InputStream is = null;
			if (rs.next()) {
				if (type.equalsIgnoreCase("fdy")) {
					is = rs.getBinaryStream(1);
				} else if (type.equalsIgnoreCase("wjdc")) {
					is = rs.getBinaryStream(1);
				} else if("已导入".equals(rs.getString(4))){//先获取新生照片
					is = rs.getBinaryStream(3);
				}else{
					if("已导入".equals(rs.getString(2))){
						is = rs.getBinaryStream(1);
					}
				}
            } 
			
			return is;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 湖北国土资源个性化，获取zp字段
	 * @param id
	 * @param res
	 * @param request
	 * @param type
	 * @return
	 * @throws IOException
	 *
	 * @author honglin
	 * @date 2012-6-13
	 */
	public JPEGImageEncoder getPic2(String id, HttpServletResponse res,HttpServletRequest request,
			String type) throws IOException {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BufferedInputStream imageStream = null;
		BufferedImage image = null;
		JPEGImageEncoder encoder = null;
		ServletOutputStream sos = res.getOutputStream();
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String preCursor;
			// ==============2010.9.8 edit by 伟大的luo=======================
			if (type.equalsIgnoreCase("fdy")) {
				preCursor = "select pic from fdy_pic where zgh= '" + id + "' ";
			} else if (type.equalsIgnoreCase("wjdc")) {
				preCursor = "select tbjg from wjdc_jgfxb where wjbh||xh = '" + id + "'";
			} else {
				preCursor = "select zp,(case when nvl(length(zp),'0')=0 then '未导入' else '已导入' end) zpzt," +
						"xszp,(case when nvl(length(xszp),'0')=0 then '未导入' else '已导入' end) xszpzt from xszpb where xh = '" + id + "'";
			}
			// ==============2010.9.8 edit end=======================
			rs = stmt.executeQuery(preCursor);
			if (rs.next()) {
				if (type.equalsIgnoreCase("fdy")) {
					imageStream = new BufferedInputStream(rs.getBinaryStream(1));
				} else if (type.equalsIgnoreCase("wjdc")) {
					imageStream = new BufferedInputStream(rs.getBinaryStream(1));
				}else{
					if("已导入".equals(rs.getString(2))){
						imageStream = new BufferedInputStream(rs.getBinaryStream(1));
					}else{
						String filePath = request.getRealPath("/images/type_pic.gif");
		            	File file = new File(filePath);
		            	imageStream = new BufferedInputStream(new FileInputStream(file));
					}
				}
			 //---------------edit by quph 2010-10-19 begin------------------------
            } else {
            	String filePath = request.getRealPath("/images/type_pic.gif");
            	File file = new File(filePath);
            	imageStream = new BufferedInputStream(new FileInputStream(file));
            }
            //---------------edit by quph 2010-10-19 end------------------------
			
			try {
				image = ImageIO.read(imageStream);
				encoder = JPEGCodec.createJPEGEncoder(sos);
				encoder.encode(image);
				imageStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encoder;
	}
	
	/**
	 * 湖北国土资源个性化，获取xszp字段
	 * @param id
	 * @param res
	 * @param request
	 * @param type
	 * @return
	 * @throws IOException
	 *
	 * @author honglin
	 * @date 2012-6-13
	 */
	public JPEGImageEncoder getPic3(String id, HttpServletResponse res,HttpServletRequest request,
			String type) throws IOException {
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		BufferedInputStream imageStream = null;
		BufferedImage image = null;
		JPEGImageEncoder encoder = null;
		ServletOutputStream sos = res.getOutputStream();
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String preCursor;
			// ==============2010.9.8 edit by 伟大的luo=======================
			if (type.equalsIgnoreCase("fdy")) {
				preCursor = "select pic from fdy_pic where zgh= '" + id + "' ";
			} else if (type.equalsIgnoreCase("wjdc")) {
				preCursor = "select tbjg from wjdc_jgfxb where wjbh||xh = '" + id + "'";
			} else {
				preCursor = "select zp,(case when nvl(length(zp),'0')=0 then '未导入' else '已导入' end) zpzt," +
						"xszp,(case when nvl(length(xszp),'0')=0 then '未导入' else '已导入' end) xszpzt from xszpb where xh = '" + id + "'";
			}
			// ==============2010.9.8 edit end=======================
			rs = stmt.executeQuery(preCursor);
			if (rs.next()) {
				if("已导入".equals(rs.getString(4))){//先获取新生照片
					imageStream = new BufferedInputStream(rs.getBinaryStream(3));
				}else{
					String filePath = request.getRealPath("/images/type_pic.gif");
	            	File file = new File(filePath);
	            	imageStream = new BufferedInputStream(new FileInputStream(file));
				}
			 //---------------edit by quph 2010-10-19 begin------------------------
            } else {
            	String filePath = request.getRealPath("/images/type_pic.gif");
            	File file = new File(filePath);
            	imageStream = new BufferedInputStream(new FileInputStream(file));
            }
            //---------------edit by quph 2010-10-19 end------------------------
			
			try {
				image = ImageIO.read(imageStream);
				encoder = JPEGCodec.createJPEGEncoder(sos);
				encoder.encode(image);
				imageStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encoder;
	}
	
	/**
	 * 湖北国土资源个性化，保存图片到数据库 专门用于保存学生的照片,by  zp 字段
	 * @param id
	 * @param res
	 * @param request
	 * @param type
	 * @return
	 * @throws IOException
	 *
	 * @author honglin
	 * @date 2012-6-13
	 */
	public void savePic_stu2(InputStream is, String id) {
		String sql="select count(1) num from xszpb where xh=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DAO dao = DAO.getInstance();
		String num=dao.getOneRs(sql, new String[]{id}, "num");
		boolean del = true;
		

		if("0".equals(num)){//若没有照片则插入
			sql="insert into xszpb(xh,zp) values(?,empty_blob())";
		}else{
			sql="update xszpb set zp=empty_blob() where xh=?";
		}
//		if (del) {
			try {
				conn = db.getConnection();
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, id);
				int rt = stmt.executeUpdate();
				if (rt > 0) {
					System.out.println("initialization succeed!!");
					String preCursor;
					preCursor = "select xh,zp pic from xszpb where xh=? for update";
					stmt = conn.prepareStatement(preCursor);
					stmt.setString(1, id);
					rs = stmt.executeQuery();
					rs.next();
					Blob b = (Blob) rs.getBlob("pic");
					BufferedOutputStream bos = new BufferedOutputStream(b
							.setBinaryStream(0L));
					BufferedInputStream bis = new BufferedInputStream(is);
					int bytes;
					try {
						while ((bytes = bis.read()) != -1) {
							bos.write(bytes);
						}

						bis.close();
						bos.close();
						conn.commit();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
//		}
	}
	
//	 --------保存图片到数据库--------------
	public void saveQjclPic(InputStream is, String id) {
		String insertsql;
		String delsql;
		
		delsql = "delete from xg_rcsw_qjgl_qjclb where clid=?";
		insertsql = "insert into xg_rcsw_qjgl_qjclb(clid,qjcl) values(?,empty_blob())";
		
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DAO dao = DAO.getInstance();
		boolean del = false;
		try {
			del = dao.runUpdate(delsql, new String[] { id });
		} catch (Exception e1) {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
		if (del) {
			try {
				conn = db.getConnection();
				stmt = conn.prepareStatement(insertsql);
				stmt.setString(1, id);
				int rt = stmt.executeUpdate();
				if (rt > 0) {
					System.out.println("initialization succeed!!");
					String preCursor;
					
					preCursor = "select clid,qjcl pic from xg_rcsw_qjgl_qjclb where clid=? for update";
					
					stmt = conn.prepareStatement(preCursor);
					stmt.setString(1, id);
					rs = stmt.executeQuery();
					rs.next();
					Blob b = (Blob) rs.getBlob("pic");
					BufferedOutputStream bos = new BufferedOutputStream(b
							.setBinaryStream(0L));
					BufferedInputStream bis = new BufferedInputStream(is);
					int bytes;
					try {
						while ((bytes = bis.read()) != -1) {
							bos.write(bytes);
						}

						bis.close();
						bos.close();
						conn.commit();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					rs.close();
					stmt.close();
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public JPEGImageEncoder getQjclPic(String id, HttpServletResponse res,HttpServletRequest request) throws IOException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		BufferedInputStream imageStream = null;
		BufferedImage image = null;
		JPEGImageEncoder encoder = null;
		ServletOutputStream sos = res.getOutputStream();
		try {
			conn = db.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			String preCursor;
			
			preCursor = "select qjcl from xg_rcsw_qjgl_qjclb where clid= '" + id + "' ";
			
			rs = stmt.executeQuery(preCursor);
			if (rs.next()) {
				
				imageStream = new BufferedInputStream(rs.getBinaryStream(1));
				
            } else {
            	String filePath = request.getRealPath("/images/type_pic.gif");
            	File file = new File(filePath);
            	imageStream = new BufferedInputStream(new FileInputStream(file));
            }
			
			try {
				image = ImageIO.read(imageStream);
				encoder = JPEGCodec.createJPEGEncoder(sos);
				encoder.encode(image);
				imageStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return encoder;
	}
	/**
	 * 
	 * @描述:保存爱心超市物品图片
	 * @作者：xiaxia[工号：1104]
	 * @日期：2014-12-4 上午09:03:55
	 * @修改记录: 修改者名字-修改日期-修改内容
	 * @param is
	 * @param id
	 * void 返回类型 
	 * @throws
	 */
	public void saveWpPic(InputStream is, String xmdm) {
		String sql = "select count(1) num from xg_xszz_wptpb where xmdm=?";
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		DAO dao = DAO.getInstance();
		String num = dao.getOneRs(sql, new String[] { xmdm }, "num");
		if ("0".equals(num)) {// 若没有照片则插入
			sql = "insert into xg_xszz_wptpb(xmdm,xmtp) values(?,empty_blob())";
		} else {
			sql = "update xg_xszz_wptpb set xmtp=empty_blob() where xmdm=?";
		}
		try {
			conn = db.getConnection();
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, xmdm);
			int rt = stmt.executeUpdate();
			if (rt > 0) {
				String preCursor;
				preCursor = "select xmdm,xmtp pic from xg_xszz_wptpb where xmdm=? for update";
				stmt = conn.prepareStatement(preCursor);
				stmt.setString(1, xmdm);
				rs = stmt.executeQuery();
				rs.next();
				Blob b = (Blob) rs.getBlob("pic");
				BufferedOutputStream bos = new BufferedOutputStream(b.setBinaryStream(0L));
				BufferedInputStream bis = new BufferedInputStream(is);
				int bytes;
				try {
					while ((bytes = bis.read()) != -1) {
						bos.write(bytes);
					}

					bis.close();
					bos.close();
					conn.commit();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
