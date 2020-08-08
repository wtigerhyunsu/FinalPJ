package top.frame;

import java.util.ArrayList;

import org.springframework.transaction.annotation.Transactional;

public interface Biz<Id, Model> {

	@Transactional
	public void register(Model model);

	@Transactional
	default public void modify(Model model) throws Exception {

	}

	@Transactional
	default public void remove(Id id) throws Exception {

	}

	@Transactional
	default public void modify2(Model model) throws Exception {

	}

	// HYUN SU

	default public ArrayList<Model> getnotifi(Id id) {
		return null;
	};

	public Model get(Id id);

	public ArrayList<Model> get();

	// refresh
	default void refreshstate(String chainId) {

	}

	default void refreshStateTrue(String chainId) {

	}

	default public Model getname(String id) {
		return null;
	};

	default public ArrayList<Model> getForChain(String id) {
		return null;
	}

	default public Model getchainname(String id) {
		return null;
	};

	default public ArrayList<Model> getYear(String year) {
		return null;
	}

	default public ArrayList<Model> getMonth(String year, Id id) {
		return null;
	}

	default public ArrayList<Model> getYearly(Id id) {
		return null;
	}

	default public ArrayList<Model> getMonthly(String year, Id id) {
		return null;
	}

	default public ArrayList<Model> getDailyAllChain(String salesRegDate) {
		return null;
	}

	default public ArrayList<Model> getbyhq(String id) {
		return null;
	}

	// YDH - poscontroller.top(sale.top)//

	default public Model getbychain(Id id) {
		return null;
	}

	default public ArrayList<Model> getdailysales() {
		return null;
	}

	default public ArrayList<Model> getUserSpecific(String hqID) {
		return null;
	}

	default public ArrayList<Model> selectUserSpecificOneIng(String hqID) {
		return null;
	}

	default public ArrayList<Model> getForDelivery(Model model) {
		return null;
	}

	// MIN JAE - ORDER && ORDER DETAIL'S CONTROLLER

	@Transactional
	default public ArrayList<Model> getuser(Id id) throws Exception {
		return null;
	}

	@Transactional
	default public ArrayList<Model> gethq(Id id) throws Exception {
		return null;
	}

	@Transactional
	default public void modifyondelivered(Model model) {

	}

	default public Model getjustregistered(Model model) {
		return null;
	};

	default public Model getordertoday(Model model) {
		return null;
	};

	@Transactional
	default public void registertoday(Model model) {

	};

	@Transactional
	default public void registernew(Model model) {

	};

}
