package top.biz;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import top.frame.Biz;
import top.frame.Dao;
import top.vo.OrderDetailVO;

@Service("orderdetailbiz")
public class OrderDetailBiz implements Biz<String, OrderDetailVO> {
	@Resource(name = "orderdetaildao")
	Dao<String, OrderDetailVO> dao;

	@Override
	public OrderDetailVO get(String orderDetailID) {
		return dao.select(orderDetailID);
	}

	@Override
	public ArrayList<OrderDetailVO> get() {
		return dao.selectall();
	}

	@Override
	public void modify(OrderDetailVO orderDetail) throws Exception {
		dao.update(orderDetail);
	}

	@Override
	public void register(OrderDetailVO m){
		dao.insert(m);
	}

	@Override
	public ArrayList<OrderDetailVO> gethq(String hqid) throws Exception {
		return dao.selecthq(hqid);
	}

	@Override
	public ArrayList<OrderDetailVO> getuser(String userid) throws Exception {
		return dao.selecthq(userid);
	}

	@Override
	public void registernew(OrderDetailVO orderdetail) {
		dao.insertnew(orderdetail);
	}
}