package zzz.Handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DownloadHandler {
	@RequestMapping("download")
	public void download(@RequestParam("filename") String filename, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			//js中使用encodeURI("地址")获取地址编码后的格式，解决IE地址中还有部分中文不能成功请求tomcat的问题
			request.setCharacterEncoding("utf-8");
			String header = request.getHeader("User-Agent");// 获取请求的消息头处理文件名乱码
			header = header.toLowerCase();

			// 设置消息头
			response.addHeader("content-Type", "application/octet-stream");// 设置下载的文件格式为二进制文件
			if (header.contains("firefox")) {// 来自火狐
				response.addHeader("content-Disposition", "attachment;filename==?UTF-8?B?"
						+ new String(Base64.encodeBase64(filename.getBytes("utf-8"))) + "?=");
			} else {// 来自其他浏览器
				response.addHeader("content-Disposition",
						"attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
			}
			// 获取文件的输入流
			InputStream in = request.getServletContext().getResourceAsStream("/res/" + filename);
			byte[] buf = new byte[1024 * 1024];
			int len = -1;
			// 获取输出流
			OutputStream out = response.getOutputStream();
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
