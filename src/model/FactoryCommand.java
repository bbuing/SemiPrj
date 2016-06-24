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
		System.out.println(param);
		if (param.equals("getBoard")) {
			return new GetBoardCommand();
		} else if (param.equals("join")) {
			return new customerJoin();
		} else if (param.equals("view")) {
			return new customerView();
		} else if (param.equals("update")) {
			return new customerUpdate();
		} else if (param.equals("remove")) {
			return new customerDelete();
		} else if (param.equals("terms")) {
			return new customerTerms();
		} else if (param.equals("input")) {
			return new customerInput();
		} else if (param.equals("lose")) {
			return new customerLose();
		} else if (param.equals("main")) {
			return new customerMain();
		} else if (param.equals("loseid")) {
			return new customerLoseid();
		} else if (param.equals("losepw")) {
			return new customerLosepw();
		} else if (param.equals("losepw2")) {
			return new customerLosepw2();
		} else if (param.equals("newpw")) {
			return new customerNewpw();
		} else if (param.equals("login")) {
			return new customerLogin();
		}else if (param.equals("mywrite")) {
			return new customerMywrite();
		}else if (param.equals("log")) {
			return new customerLog();
		} else if (param.equals("mypage")) {
			return new customerMypage();
		}else if (param.equals("logout")) {
			return new customerlogout();
		} else if (param.equals("message")) {
			return new customerMymessage();
		}else if (param.equals("read")) {
			return new readBoard();
		}else if(param.equals("post")) {
			return new customerWrite();
		}else if(param.equals("save")) {
			return new customerSave(multi);
		}
		return null;

	}
}
