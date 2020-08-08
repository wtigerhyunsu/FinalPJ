package top.mapper;

import java.util.ArrayList;

import top.vo.OrderDetailVO;

public interface OrderDetailMapper {

	public void insert(OrderDetailVO orderDetail);

	public OrderDetailVO select(String orderDetailID);

	public ArrayList<OrderDetailVO> selectall();

	public void update(String orderDetailID);

	public ArrayList<OrderDetailVO> selecthq(String hqid);

	public ArrayList<OrderDetailVO> selectuser(String userid);

	public void insertnew(OrderDetailVO orderdetail);

	public void update(OrderDetailVO model);

}