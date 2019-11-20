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
			//js��ʹ��encodeURI("��ַ")��ȡ��ַ�����ĸ�ʽ�����IE��ַ�л��в������Ĳ��ܳɹ�����tomcat������
			request.setCharacterEncoding("utf-8");
			String header = request.getHeader("User-Agent");// ��ȡ�������Ϣͷ�����ļ�������
			header = header.toLowerCase();

			// ������Ϣͷ
			response.addHeader("content-Type", "application/octet-stream");// �������ص��ļ���ʽΪ�������ļ�
			if (header.contains("firefox")) {// ���Ի��
				response.addHeader("content-Disposition", "attachment;filename==?UTF-8?B?"
						+ new String(Base64.encodeBase64(filename.getBytes("utf-8"))) + "?=");
			} else {// �������������
				response.addHeader("content-Disposition",
						"attachment;filename=" + URLEncoder.encode(filename, "utf-8"));
			}
			// ��ȡ�ļ���������
			InputStream in = request.getServletContext().getResourceAsStream("/res/" + filename);
			byte[] buf = new byte[1024 * 1024];
			int len = -1;
			// ��ȡ�����
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
