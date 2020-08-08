package top.biz;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.frame.Biz;
import top.frame.Dao;
import top.vo.SalesVO;

@Service("salesbiz")
public class SalesBiz implements Biz<String, SalesVO> {

	@Resource(name = "salesdao")
	Dao<String, SalesVO> dao;

	@Override
	public SalesVO get(String salesID) {
		return dao.select(salesID);
	}

	@Override
	public ArrayList<SalesVO> get() {
		return dao.selectall();
	}

	public ArrayList<SalesVO> getYear(String year) {
		return dao.selectYear(year);
	}

	public ArrayList<SalesVO> getMonth(String year, String chainID) {
		return dao.selectMonth(year, chainID);
	}

	public ArrayList<SalesVO> getYearly(String salesID) {
		return dao.selectYearly(salesID);
	}

	public ArrayList<SalesVO> getMonthly(String salesID) {
		return dao.selectMonthly(salesID);
	}

	@Override
	public ArrayList<SalesVO> getDailyAllChain(String salesRegDate) {
		return dao.selectDailyAllChain(salesRegDate);
	}
	
	// 동현 -  poscontroller.top
	public SalesVO getbychain(String chainID) {
		return dao.selectbychain(chainID);
	}
	
	public ArrayList<SalesVO> getdailysales(){
		return dao.selectdailysales();
	}

	@Override
	public void register(SalesVO model){
		dao.insert(model);

	}

	@Override
	public void modify(SalesVO model) throws Exception {
		dao.update(model);

	}

	@Override
	public void remove(String salesID) throws Exception {
		dao.delete(salesID);

	}

}