package model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;

public class User_logout implements Command {
	public Object processCommand(HttpServletRequest req, HttpServletResponse resp) throws SerialException, IOException {
		// �α׾ƿ��� ������ ���� ���� 0 �̶�� ������ id�� �˻��ؼ� null�� �ǵ��� �Ͽ� �α׾ƿ���
		// id�� 1������ �����ϱ� ������ 0�� id�� ����� �������� �ʱ� ������ ��� ȸ���� �α׾ƿ��� �� �� ����
		User_Dao dao = new User_Dao();
		
		String out = req.getParameter("out");
		User_Dto list = dao.coutomerLogout(out);
		HttpSession session = req.getSession();
		session.setAttribute("list", list);
		return "index.jsp";
	}
}
