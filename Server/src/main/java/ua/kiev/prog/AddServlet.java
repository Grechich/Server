package ua.kiev.prog;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class AddServlet extends HttpServlet {

	private MessageList msgList = MessageList.getInstance();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		byte[] buf = requestBodyToArray(req);
        String bufStr = new String(buf, StandardCharsets.UTF_8);

		Message msg = Message.fromJSON(bufStr);
		if (msg != null){
            String mess = msg.toString();

            int indx = mess.indexOf(']');

            if (mess.charAt(indx+2) == '/'){
                String[] strs = mess.split("/");
                String all = Menu.check(strs[1]);
                msg.setText(all);
            }

			msgList.add(msg);
        }
		else
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	}

	private byte[] requestBodyToArray(HttpServletRequest req) throws IOException {
        InputStream is = req.getInputStream();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[10240];
        int r;

        do {
            r = is.read(buf);
            if (r > 0) bos.write(buf, 0, r);
        } while (r != -1);

        return bos.toByteArray();
    }
}
