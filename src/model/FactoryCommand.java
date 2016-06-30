package model;

import com.oreilly.servlet.MultipartRequest;

//Factory Command : �ν��Ͻ� ������ �ϴ� ��� ������ ���� Ŭ������ ���� �����ش�.
public class FactoryCommand {
	private FactoryCommand() {
	}

	private static FactoryCommand instance = new FactoryCommand();

	public static FactoryCommand newInstance() {
		return instance;
	}

	public Command createCommand(String param, MultipartRequest multi) {
		if (param.equals("getBoard")) {
			return new GetBoardCommand();
		} else if (param.equals("join")) {
			return new User_Join();
		} else if (param.equals("view")) {
			return new User_View();
		} else if (param.equals("update")) {
			return new User_Update();
		} else if (param.equals("remove")) {
			return new User_Delete();
		} else if (param.equals("terms")) {
			return new User_Terms();
		} else if (param.equals("input")) {
			return new User_Input();
		} else if (param.equals("lose")) {
			return new User_Lose();
		} else if (param.equals("main")) {
			return new User_Main();
		} else if (param.equals("loseid")) {
			return new User_Loseid();
		} else if (param.equals("losepw")) {
			return new User_Losepw();
		} else if (param.equals("losepw2")) {
			return new User_Losepw2();
		} else if (param.equals("newpw")) {
			return new User_Newpw();
		} else if (param.equals("login")) {
			return new User_Login();
		}else if (param.equals("mywrite")) {
			return new User_Mywrite();
		}else if (param.equals("log")) {
			return new User_Log();
		} else if (param.equals("mypage")) {
			return new User_Mypage();
		}else if (param.equals("logout")) {
			return new User_logout();
		} else if (param.equals("message")) {
			return new User_Mymessage();
		}else if (param.equals("read")) {
			return new readBoard();
		}else if(param.equals("write_log")) {
			return new User_Write();
		}else if(param.equals("save")) {
			return new User_Save(multi);
		} else if(param.equals("mylike")) {
			return new User_Mylike();
		}
		return null;

	}
}
