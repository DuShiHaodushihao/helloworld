package com.how2java.tmall.service;

import com.how2java.tmall.pojo.Order;
import com.how2java.tmall.pojo.OrderItem;

import java.util.List;

public interface OrderItemService {
    void add(OrderItem c);
    void update(OrderItem c);
    void delete(int id);
    OrderItem get(int id);
    List list();

    void fill(List<Order> os);
    void fill(Order o);
    List<OrderItem> listByUser(int uid);
    int getSaleCount(int pid);
}
