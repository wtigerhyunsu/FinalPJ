package top.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import top.vo.SalesVO;

public interface SalesMapper {

	public SalesVO select(String salesID);

	public ArrayList<SalesVO> selectall();

	public ArrayList<SalesVO> selectYear(@Param(value = "year") String year);

	public ArrayList<SalesVO> selectMonth(@Param(value = "year") String year, @Param(value = "chainID") String chainID);

	public ArrayList<SalesVO> selectYearly(String chainID);

	public ArrayList<SalesVO> selectMonthly(String chainID);
	
	public ArrayList<SalesVO> selectDailyAllChain(String salesRegDate);

	
	// ����
	
	public ArrayList<SalesVO> selectdailysales();
	
	public SalesVO selectbychain(String chainID);
	
	public void insert(SalesVO model);

	public void update(SalesVO model);

	public void delete(String salesID);
	

}