package ua.kiev.prog;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.http.*;

public class GetListServlet extends HttpServlet {
	
	private MessageList msgList = MessageList.getInstance();

    @Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {


		String fromStr = req.getParameter("from");
		int from = 0;
		try {
			from = Integer.parseInt(fromStr);
			if (from < 0) from = 0;
		} catch (Exception ex) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
		}

		resp.setContentType("application/json");
		
		String json = msgList.toJSON(from);
		if (json != null) {
//			OutputStream os = resp.getOutputStream();
//            byte[] buf = json.getBytes(StandardCharsets.UTF_8);
//			os.write(buf);

			PrintWriter pw = resp.getWriter();
			pw.print(json);
		}
	}
}
