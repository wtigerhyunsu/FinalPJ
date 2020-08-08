package top.frame;

import java.util.ArrayList;

import top.vo.ChainVO;
import top.vo.IngredientVO;
import top.vo.OrderVO;
import top.vo.SalesVO;

public interface Dao<Id, Model> {

	// �⺻�� CRUD //

	public ArrayList<Model> selectall();

	public Model select(Id id);

	public void insert(Model model);

	default public void update(Model model) throws Exception {
	}

	default public void delete(Id id) throws Exception {
	}

	// hyun su ' ingredient START
	default public void update2(Model model) throws Exception {
	}

	default public ArrayList<Model> selectUserSpecificOneIng(Id id) {
		return null;

	}

	// read.top + adminpage.top(hennie)
	default public ArrayList<ChainVO> selectnotifi(String id) {
		return null;
	};

	// refresh_change(hennie)
	default public void updatestate(String chainid) {

	}

	// refresh_ notification_state_change((hennie)
	default public void updatestatetrue(String chainid) {

	}

	// user apply.top ���� used!
	default public ChainVO selectname(String id) {
		return null;
	}

	// start hyun min' function START
	default public ArrayList<Model> selectYear(String year) {
		return null;
	}

	default public ArrayList<Model> selectMonth(String year, String id) {
		return null;
	}

	default public ArrayList<Model> selectYearly(String id) {
		return null;
	}

	default public ArrayList<Model> selectMonthly(String id) {
		return null;
	}

	default public ArrayList<SalesVO> selectDailyAllChain(String salesRegDate) {
		return null;
	};

	// get container data for specific chainID
	default public ArrayList<Model> selectForChain(String id) {
		return null;
	}

	// get container data for specific hq=ID
	default public ArrayList<Model> selectbyhq(String id) {
		return null;
	}
	
	// user apply.top 에서 used!
		default public ChainVO selectchainname(String id) {
			return null;
		}

	// start hyun min' function END //

	// poscontroller - ����

	default public Model selectbychain(Id id) {
		return null;
	}

	default public ArrayList<Model> selectdailysales() {
		return null;
	};

	// min jae' order && orderdetail controller

	default public ArrayList<Model> selectuser(Id id) throws Exception {
		return null;//
	}

	default public ArrayList<Model> selecthq(Id id) throws Exception {
		return null;
	}

	default public Model selectjustregistered(Model model) {
		return null;
	};

	default public Model selectordertoday(Model model) {
		return null;
	};

	default public Model selectbyorderid(String id) {
		return null;
	};

	default public ArrayList<Model> selectlistbyorderid(String id) {
		return null;
	};

	default public void updateondelivered(Model model) {

	};

	default public void inserttoday(Model model) {

	}

	default public void insertnew(Model model) {

	}

	default public ArrayList<OrderVO> selectForDelivery(Model model) {
		return null;
	}

}
